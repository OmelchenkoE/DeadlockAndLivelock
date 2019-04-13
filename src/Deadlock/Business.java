package Deadlock;

class Business {

    private A lock1 = new A();
    private B lock2 = new B();

    public void foo() {
        synchronized (lock1) {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println("foo");
            }
        }
    }

    public void bar() {
        synchronized (lock2) {
            synchronized (lock1) {
                System.out.println("bar");
            }
        }
    }
}