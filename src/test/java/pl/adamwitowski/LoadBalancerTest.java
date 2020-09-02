package pl.adamwitowski;

import org.junit.jupiter.api.Test;
import pl.adamwitowski.loadbalancer.Host;
import pl.adamwitowski.loadbalancer.LoadBalancer;
import pl.adamwitowski.loadbalancer.Request;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.adamwitowski.loadbalancer.algorithm.LoadBalancerAlgorithmFactory.WEIGHTED;
import static pl.adamwitowski.loadbalancer.algorithm.LoadBalancerAlgorithmFactory.ROUND_ROBIN;

class LoadBalancerTest {

    LoadBalancer subject;

    @Test
    public void shouldLoadBalanceHosts_usingRoundRobinAlgorithm() {
        //given
        Host firstHost = new Host(10);
        Host secondHost = new Host(5);
        Host thirdHost = new Host(10);

        List<Host> hosts = List.of(firstHost, secondHost, thirdHost);

        //when
        subject = new LoadBalancer(hosts, ROUND_ROBIN);

        subject.handleRequest(new Request(1));
        subject.handleRequest(new Request(2.5f));
        subject.handleRequest(new Request(1.9f));

        subject.handleRequest(new Request(3.5f));
        subject.handleRequest(new Request(2));
        subject.handleRequest(new Request(4.1f));

        //then
        assertThat(firstHost.getLoad()).isEqualTo(0.45f);
        assertThat(secondHost.getLoad()).isEqualTo(0.9f);
        assertThat(thirdHost.getLoad()).isEqualTo(0.6f);
    }

    @Test
    public void shouldTakeFirstHostThatHasLoadUnder75_usingWeightedAlgorithm() {
        //given
        Host firstHost = new Host(5);
        Host secondHost = new Host(10);
        Host thirdHost = new Host(12);

        firstHost.handleRequest(new Request(5));
        secondHost.handleRequest(new Request(7));
        thirdHost.handleRequest(new Request(6));

        List<Host> hosts = List.of(firstHost, secondHost, thirdHost);

        //when
        subject = new LoadBalancer(hosts, WEIGHTED);

        subject.handleRequest(new Request(1));

        //then
        assertThat(firstHost.getLoad()).isEqualTo(1);
        assertThat(secondHost.getLoad()).isEqualTo(0.8f);
        assertThat(thirdHost.getLoad()).isEqualTo(0.5f);
    }

    @Test
    public void shouldTakeLeastLoadedHost_whenAllHostsLoadAbove75_usingWeightedAlgorithm() {
        //given
        Host firstHost = new Host(5);
        Host secondHost = new Host(10);
        Host thirdHost = new Host(10);

        firstHost.handleRequest(new Request(5));
        secondHost.handleRequest(new Request(9));
        thirdHost.handleRequest(new Request(8));

        List<Host> hosts = List.of(firstHost, secondHost, thirdHost);

        //when
        subject = new LoadBalancer(hosts, WEIGHTED);

        subject.handleRequest(new Request(0.5f));

        //then
        assertThat(firstHost.getLoad()).isEqualTo(1);
        assertThat(secondHost.getLoad()).isEqualTo(0.9f);
        assertThat(thirdHost.getLoad()).isEqualTo(0.85f);
    }

}