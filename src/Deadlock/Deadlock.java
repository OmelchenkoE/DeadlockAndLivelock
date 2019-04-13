package Deadlock;

public class Deadlock {
    public static void main(String[] args) throws InterruptedException {
        Business business = new Business();

        Thread t1 = new Thread(() -> business.foo());

        Thread t2 = new Thread(() -> business.bar());

        t1.start();
        t2.start();

        for (int i = 0; i < 10; i++) {
            if (t1.isAlive() && t2.isAlive()) {
                System.out.println("Attempt " + i);
                Thread.sleep(500L);
                if (i >= 9) {
                    System.err.println("Livelock1 detected. Threads will be interrupted in 1 seconds.");
                    Thread.sleep(1000);
                    t1.interrupt();
                    t2.interrupt();
                    //It doesn't work and thread.stop() too, besides it's deprecated
                    //Do exist some sly mechanism to stop thread exactly?

                    System.out.println("Threads is interrupted: " + (t1.isInterrupted() & t2.isInterrupted()));
                    System.out.println("Threads is alive:  " + (t1.isAlive() & t2.isAlive()));
                    try {
                        throw new DeadlockException();
                    } catch (DeadlockException e) {
                        t1.dumpStack();
                        t2.dumpStack();
                        e.printStackTrace();
                        //Except below, of course. Because I haven't found another approach
                        System.exit(0);
                    }
                }
            }
        }
    }
}