package Livelock1;

class Worker {
    private final int id;
    private boolean active;
    private int counter=0;

    public Worker(int id, boolean active) {
        this.id = id;
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public int getId() {
        return id;
    }

    synchronized public void work(Resource r, Worker otherWorker){
        while (isActive()) {
            if (otherWorker.isActive()) {
                System.out.println(getId() +" I'll wait");
                try {
                    wait(100L);
                    if (counter>=10){
                        throw new RuntimeException("Livelock detected!");
                    }
                    counter++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("I'm doing some work");
                System.out.println("I've finished");
                active = false;
                r.setCurrentOwner(otherWorker);
            }
        }
    }
}
