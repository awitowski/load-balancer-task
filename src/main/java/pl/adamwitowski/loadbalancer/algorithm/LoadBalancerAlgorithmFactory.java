package pl.adamwitowski.loadbalancer.algorithm;

import pl.adamwitowski.loadbalancer.Host;
import pl.adamwitowski.loadbalancer.Request;

import java.util.List;

public enum LoadBalancerAlgorithmFactory {

    ROUND_ROBIN(new RoundRobinAlgorithm()),
    WEIGHTED(new WeightedAlgorithm());

    private final LoadBalancerAlgorithm loadBalance;

    LoadBalancerAlgorithmFactory(LoadBalancerAlgorithm loadBalance) {
        this.loadBalance = loadBalance;
    }

    public void balance(List<Host> hosts, Request request) {
        loadBalance.balance(hosts, request);
    }
}
