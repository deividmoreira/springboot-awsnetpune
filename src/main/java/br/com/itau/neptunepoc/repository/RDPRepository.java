package br.com.itau.neptunepoc.repository;

import br.com.itau.neptunepoc.model.Aresta;
import br.com.itau.neptunepoc.model.Vertice;
import br.com.itau.neptunepoc.model.VerticePerfil;
import br.com.itau.neptunepoc.model.VerticeSocio;
import lombok.AllArgsConstructor;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.T;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.stereotype.Repository;

import java.util.List;

import static br.com.itau.neptunepoc.constants.LabelConstants.HAS_PARTNER;
import static br.com.itau.neptunepoc.constants.LabelConstants.IS_PARTNER;

@AllArgsConstructor
@Repository
public class RDPRepository {

    private GraphTraversalSource graph;

    public Edge getOrInsertAresta(Vertex vertexOrigem, Vertex vertexDestino, Aresta aresta) {
        return (Edge) graph.V(vertexOrigem).outE().hasLabel(aresta.getLabel()).as("e")
                .inV().has(T.id, vertexDestino.id()).select("e").tryNext()
                .orElseGet(() -> graph.addE(aresta.getLabel())
                                    .from(vertexOrigem).to(vertexDestino).next());
    }

    public List<Edge> getRelationship(String idClientePai) {
        List<Edge> list = graph.V(idClientePai).bothE().toList();
        return list;
    }

    public Edge findRelationshipById(String idRelacionamento) {
        return graph.E(idRelacionamento).next();
    }

    public Vertex getOrInsertVertice(Vertice vertice) {
        return graph.V(vertice.getId())
                .tryNext().orElseGet(() -> graph.addV(vertice.getLabel())
                        .property(T.id, vertice.getId()).next());
    }

    public Vertex getOrInsertVerticePerfil(VerticePerfil vertice) {
        return graph.V().hasLabel(vertice.getLabel()).has("cpf_cnpj",vertice.getCpfCnpj())
                .tryNext().orElseGet(() -> graph.addV(vertice.getLabel())
                        .property("cpf_cnpj",vertice.getCpfCnpj())
                        .property("nome_pessoa",vertice.getNome())
                        .property("nome_digital",vertice.getNomeDigital())
                        .property("nome_fantasia",vertice.getNomeFantasia())
                        .property("data_atualizacao",vertice.getDataAtualizacao())
                        .next());
    }


    public Vertex getOrInsertVerticeRelantioship(VerticeSocio vertice, String idOrigem, String idDestino) {
        return (Vertex) graph.V(idOrigem).outE(HAS_PARTNER).inV().hasLabel(vertice.getLabel()).as("R")
                .inE(IS_PARTNER).outV().hasId(idDestino).select("R")
                .tryNext().orElseGet(() -> graph.addV(vertice.getLabel())
                        .property("percentual_participacao",vertice.getPercentualParticipacao())
                        .property("capital_investido",vertice.getCapitalInvestido())
                        .property("papel",vertice.getPapel())
                        .property("data_inicio",vertice.getDataInicio())
                        .next());
    }

    public Long countEdges(Vertex outVertex) {
        return graph.V(outVertex).bothE().count().next();
    }

    public void deleteVertex(Vertex vertex) {
        graph.V(vertex).drop();
    }

    public void deleteEdge(Edge edge) {
        graph.E(edge).drop().iterate();
    }

}
