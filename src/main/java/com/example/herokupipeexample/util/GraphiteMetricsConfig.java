package com.example.herokupipeexample.util;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

@Configuration
public class GraphiteMetricsConfig {
    @Value("${graphite.host}")
    private String host;
    @Value("${graphite.apiKey}")
    private String apikey;
    @Bean
    public MetricRegistry getRegistry() {
        return new MetricRegistry();
    }
//test
    @Bean
    public GraphiteReporter getReporter(MetricRegistry registry) {
        System.out.println(host);
        System.out.println(apikey);
        //snore
        Graphite graphite = new Graphite(new InetSocketAddress(host, 2003));
        GraphiteReporter reporter = GraphiteReporter.forRegistry(registry)
                .prefixedWith(apikey)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite);
        reporter.start(1, TimeUnit.SECONDS);
        return reporter;
    }
}
