package pl.adamwitowski.loadbalancer.algorithm;

import pl.adamwitowski.loadbalancer.Host;
import pl.adamwitowski.loadbalancer.Request;

import java.util.List;

public class RoundRobinAlgorithm implements LoadBalancerAlgorithm {

    private int index = 0;

    @Override
    public void balance(List<Host> hosts, Request request) {
        if (index > hosts.size() - 1) {
            index = 0;
        }
        hosts.get(index).handleRequest(request);
        index++;
    }
}
