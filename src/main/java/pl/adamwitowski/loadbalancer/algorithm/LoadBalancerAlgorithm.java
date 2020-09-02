package pl.adamwitowski.loadbalancer.algorithm;

import pl.adamwitowski.loadbalancer.Host;
import pl.adamwitowski.loadbalancer.Request;

import java.util.List;

public interface LoadBalancerAlgorithm {

    void balance(List<Host> hosts, Request request);
}
