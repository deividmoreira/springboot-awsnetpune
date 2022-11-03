package br.com.itau.neptunepoc.service;

import br.com.itau.neptunepoc.api.partner.request.PartnerRequest;
import br.com.itau.neptunepoc.api.partner.response.PartnerResponse;
import br.com.itau.neptunepoc.domain.Relationship;
import br.com.itau.neptunepoc.mapper.RelationshipMapper;
import br.com.itau.neptunepoc.model.Aresta;
import br.com.itau.neptunepoc.model.Vertice;
import br.com.itau.neptunepoc.model.VerticePerfil;
import br.com.itau.neptunepoc.model.VerticeSocio;
import br.com.itau.neptunepoc.repository.RDPRepository;
import lombok.AllArgsConstructor;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class RDPService {

    private RDPRepository repository;
    private RelationshipMapper relationshipMapper;

    public PartnerResponse createRelationship(PartnerRequest partnerRequest) {
        Relationship relationship = relationshipMapper.toDomain(partnerRequest);
        Vertex vertexOrigem = getOrInsertVertex(relationship.getVerticeOrigem());
        Vertex vertexOrigemPerfil = getOrInsertVertexProfile(relationship.getVerticeOrigemPerfil());
        Vertex vertexDestino = getOrInsertVertex(relationship.getVerticeDestino());
        Vertex vertexDestinoPerfil = getOrInsertVertexProfile(relationship.getVerticeDestinoPerfil());
        Vertex vertexRelacionamento = getOrInsertVertexRelantioship(relationship.getVerticeSocio(),
                relationship.getVerticeOrigem().getId(), relationship.getVerticeDestino().getId());
        Edge arestaOrigemPerfil = getOrInsertAresta(vertexOrigem, vertexOrigemPerfil, relationship.getArestaOrigemPerfil());
        Edge arestaDestinoPerfil = getOrInsertAresta(vertexDestino, vertexDestinoPerfil, relationship.getArestaDestinoPerfil());
        Edge arestaOrigemRelacionamento = getOrInsertAresta(vertexOrigem, vertexRelacionamento, relationship.getArestaOrigemRelacionamento());
        Edge arestaDestinoRelacionamento = getOrInsertAresta(vertexDestino, vertexRelacionamento, relationship.getArestaDestinoRelacionamento());
        
        return relationshipMapper.populateRelationship(edge);       
    }

    private Vertex getOrInsertVertexProfile(VerticePerfil verticeOrigemPerfil) {
        return repository.getOrInsertVerticePerfil(verticeOrigemPerfil);
    }

    private Vertex getOrInsertVertexRelantioship(VerticeSocio verticeSocio, String idOrigem, String idDestino) {
        return repository.getOrInsertVerticeRelantioship(verticeSocio, idOrigem, idDestino);
    }

    public Edge getOrInsertAresta(Vertex vertexOrigem, Vertex vertexDestino, Aresta aresta) {
        return repository.getOrInsertAresta(vertexOrigem, vertexDestino, aresta);
    }

    public Vertex getOrInsertVertex(Vertice vertice){
        return repository.getOrInsertVertice(vertice);
    }

    public List<PartnerResponse> getRelationship(String idClientePai) {
        List<Edge> edges = repository.getRelationship(idClientePai);
        List<PartnerResponse> responses = new ArrayList<PartnerResponse>();
        edges.stream().forEach(e->responses.add(relationshipMapper.populateRelationship(e)));
        return responses;
    }

    public Edge findByIdRelationship(String idRelacionamento){
        return repository.findRelationshipById(idRelacionamento);
    }

//    public Edge patchRelationship(String idRelacionamento, InformacoesAdicionais informacoesAdicionais) {
//        Edge edge = findByIdRelationship(idRelacionamento);
//        return repository.updateRelationship(edge, informacoesAdicionais);
//    }

    public void deleteRelationship(String idRelacionamento) {
        Edge edge = findByIdRelationship(idRelacionamento);
        Long edgesPai = repository.countEdges(edge.outVertex());
        Long edgesFilho = repository.countEdges(edge.inVertex());
        if(edgesPai <= 1){
            repository.deleteVertex(edge.outVertex());
        }
        if(edgesFilho <= 1){
            repository.deleteVertex(edge.inVertex());
        }
        repository.deleteEdge(edge);
    }
}
