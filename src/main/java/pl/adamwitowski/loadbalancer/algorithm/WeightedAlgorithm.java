package pl.adamwitowski.loadbalancer.algorithm;

import pl.adamwitowski.loadbalancer.Host;
import pl.adamwitowski.loadbalancer.Request;

import java.util.Comparator;
import java.util.List;

public class WeightedAlgorithm implements LoadBalancerAlgorithm {

    private static final float LOAD_75 = 0.75f;

    @Override
    public void balance(List<Host> hosts, Request request) {

        for (Host host : hosts) {
            if (host.getLoad() < LOAD_75) {
                host.handleRequest(request);
                return;
            }
        }
        findLeastLoadedHost(hosts).handleRequest(request);
    }

    private Host findLeastLoadedHost(List<Host> hosts) {
        return hosts.stream()
                .min(Comparator.comparing(Host::getLoad))
                .orElseThrow();
    }
}
