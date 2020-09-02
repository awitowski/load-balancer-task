package pl.adamwitowski.loadbalancer;

public class Request {

    private final float weight;

    public Request(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }
}
