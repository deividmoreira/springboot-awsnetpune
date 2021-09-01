package br.com.itau.neptunepoc.config;

import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.util.empty.EmptyGraph;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;

@Configuration
public class NeptuneConfig {

    @Value("${neptune-writer-endpoint}")
    private String writerClusterEndpoint;

    @Bean
    public Cluster writerCluster() {
        return Cluster.build()
                .addContactPoint(writerClusterEndpoint)
                .port(8182)
                .enableSsl(true)
                .keyCertChainFile("./cert/neptunepockey.pem")
                .maxConnectionPoolSize(2)
                .maxInProcessPerConnection(1)
                .maxSimultaneousUsagePerConnection(1)
                .minSimultaneousUsagePerConnection(1)
                .reconnectInterval(2000)
                .create();
    }

    @Bean
    public GraphTraversalSource writerGraph(Cluster writerCluster) {
        return traversal().withRemote(DriverRemoteConnection.using(writerCluster));
    }


}
