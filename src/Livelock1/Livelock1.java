package Livelock1;

public class Livelock1 {
    public static void main(String[] args) {
        final Worker worker1 = new Worker(1, true);
        final Worker worker2 = new Worker(2, true);

        Resource resource = new Resource(worker1);

        new Thread(() -> worker1.work(resource, worker2)).start();
        new Thread(() -> worker2.work(resource, worker1)).start();
    }
}