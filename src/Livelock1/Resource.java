package Livelock1;

class Resource {
private String info = "some information";
private Worker currentOwner;

    public Resource(Worker worker){
        currentOwner=worker;
    }

    synchronized public void setCurrentOwner(Worker currentOwner) {
        this.currentOwner = currentOwner;
    }
}