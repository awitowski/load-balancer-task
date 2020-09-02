package pl.adamwitowski.loadbalancer;

import pl.adamwitowski.loadbalancer.algorithm.LoadBalancerAlgorithmFactory;

import java.util.List;

public class LoadBalancer {

    private final List<Host> hosts;
    private final LoadBalancerAlgorithmFactory algorithmFactory;

    public LoadBalancer(List<Host> hosts, LoadBalancerAlgorithmFactory algorithmFactory) {
        this.hosts = hosts;
        this.algorithmFactory = algorithmFactory;
    }

    public void handleRequest(Request request) {
        this.algorithmFactory.balance(this.hosts, request);
    }
}
