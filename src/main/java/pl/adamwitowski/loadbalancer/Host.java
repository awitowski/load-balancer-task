package pl.adamwitowski.loadbalancer;

public class Host {

    private final float capacity;
    private float load;


    public Host(float capacity) {
        this.capacity = capacity;
    }

    public float getLoad() {
        return this.load;
    }

    public void handleRequest(Request request) {
        this.load = this.load + (request.getWeight() / this.capacity);
    }
}
