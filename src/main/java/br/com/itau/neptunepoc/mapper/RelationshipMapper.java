package br.com.itau.neptunepoc.mapper;

import br.com.itau.neptunepoc.api.common.request.Pessoa;
import br.com.itau.neptunepoc.api.partner.request.Participacao;
import br.com.itau.neptunepoc.api.partner.request.PartnerRequest;
import br.com.itau.neptunepoc.api.partner.response.PartnerResponse;
import br.com.itau.neptunepoc.domain.Relationship;
import br.com.itau.neptunepoc.model.Aresta;
import br.com.itau.neptunepoc.model.Vertice;
import br.com.itau.neptunepoc.model.VerticePerfil;
import br.com.itau.neptunepoc.model.VerticeSocio;
import org.apache.tinkerpop.gremlin.structure.Property;

import static br.com.itau.neptunepoc.constants.LabelConstants.*;

public class RelationshipMapper {

    public Relationship toDomain(PartnerRequest request){
        Vertice verticeOrigem = populateVertice(request.getPessoaOrigem().getIdPessoa(), request.getPessoaOrigem().getTipoPessoa());
        Vertice verticeDestino = populateVertice(request.getPessoaDestino().getIdPessoa(), request.getPessoaDestino().getTipoPessoa());
        VerticePerfil verticeOrigemPerfil = populateVerticePerfil(request.getPessoaOrigem());
        VerticePerfil verticeDestinoPerfil = populateVerticePerfil(request.getPessoaDestino());
        VerticeSocio verticeSocio = populateVerticeSocio(request.getParticipacao());
        Aresta arestaOrigemPerfil = populateArestaPerfil();
        Aresta arestaDestinoPerfil = populateArestaPerfil();
        Aresta arestaOrigemRelacionamento = populateArestaOrigemRelacionamento();
        Aresta arestaDestinoRelacionamento = populateArestaDestinoRelacionamento();
        Relationship relationship = populateRelationship(verticeOrigem, verticeDestino, verticeOrigemPerfil, verticeDestinoPerfil,
            verticeSocio, arestaOrigemPerfil, arestaOrigemRelacionamento, arestaDestinoPerfil, arestaDestinoRelacionamento);
        return relationship;
    }

    private Relationship populateRelationship(Vertice origem, Vertice destino, VerticePerfil origemPerfil,
                                              VerticePerfil destinoPerfil, VerticeSocio socio, Aresta arestaOrigemPerfil,
                                              Aresta origemRelacionamento, Aresta arestaDestinoPerfil, Aresta destinoRelacionamento) {
        Relationship relationship = new Relationship();
        relationship.setVerticeOrigem(origem);
        relationship.setVerticeOrigemPerfil(origemPerfil);
        relationship.setVerticeDestino(destino);
        relationship.setVerticeDestinoPerfil(destinoPerfil);
        relationship.setVerticeSocio(socio);
        relationship.setArestaOrigemPerfil(arestaOrigemPerfil);
        relationship.setArestaDestinoPerfil(arestaDestinoPerfil);
        relationship.setArestaOrigemRelacionamento(origemRelacionamento);
        relationship.setArestaDestinoRelacionamento(destinoRelacionamento);
        return relationship;
    }

    private Vertice populateVertice(String idPessoa, String tipoPessoa) {
        Vertice vertice = new Vertice();
        vertice.setId(idPessoa);
        vertice.setLabel(tipoPessoa);
        return vertice;
    }

    private VerticePerfil populateVerticePerfil(Pessoa pessoa) {
        VerticePerfil vertice = new VerticePerfil();
        vertice.setLabel(pessoa.getTipoPessoa());
        vertice.setCpfCnpj(verifyNull(pessoa.getPerfil().getCpfCnpj()));
        vertice.setNome(verifyNull(pessoa.getPerfil().getNomePessoa()));
        vertice.setNomeDigital(verifyNull(pessoa.getPerfil().getNomeDigital()));
        vertice.setNomeFantasia(verifyNull(pessoa.getPerfil().getNomeFantasia()));
        vertice.setDataAtualizacao(pessoa.getPerfil().getDataAtualizacao());
        return vertice;
    }

    private Aresta populateArestaPerfil(){
        Aresta aresta = new Aresta();
        aresta.setLabel(IS);
        return aresta;
    }

    private Aresta populateArestaOrigemRelacionamento() {
        Aresta aresta = new Aresta();
        aresta.setLabel(HAS_PARTNER);
        return aresta;
    }

    private Aresta populateArestaDestinoRelacionamento() {
        Aresta aresta = new Aresta();
        aresta.setLabel(IS_PARTNER);
        return aresta;
    }

    public PartnerResponse populateRelationship(org.apache.tinkerpop.gremlin.structure.Edge edge) {
        PartnerResponse partnerResponse = new PartnerResponse();
        partnerResponse.setIdClientePai(edge.outVertex().id().toString());
        partnerResponse.setTipoClientePai(edge.outVertex().label());
        partnerResponse.setIdClienteFilho(edge.inVertex().id().toString());
        partnerResponse.setTipoClienteFilho(edge.inVertex().label());
        partnerResponse.setIdRelacionamento(edge.id().toString());
        partnerResponse.setTipoRelacionamento(edge.label());
        return partnerResponse;
    }

    private VerticeSocio populateVerticeSocio(Participacao participacao) {
        VerticeSocio vertice = new VerticeSocio();
        vertice.setLabel(PARTNER);
        vertice.setPercentualParticipacao(participacao.getPercentualParticipacao());
        vertice.setCapitalInvestido(participacao.getCapitalInvestido());
        vertice.setPapel(participacao.getPapel());
        vertice.setDataInicio(participacao.getDataInicio());
        return vertice;
    }

    private String verifyNull(String valor) {
        return valor == null ? "" : valor;
    }

//    public PartnerResponse populateRelationship(org.apache.tinkerpop.gremlin.structure.Edge edge) {
//        PartnerResponse partnerResponse = new PartnerResponse();
//        partnerResponse.setIdClientePai(edge.outVertex().id().toString());
//        partnerResponse.setTipoClientePai(edge.outVertex().label());
//        partnerResponse.setIdClienteFilho(edge.inVertex().id().toString());
//        partnerResponse.setTipoClienteFilho(edge.inVertex().label());
//        partnerResponse.setIdRelacionamento(edge.id().toString());
//        partnerResponse.setTipoRelacionamento(edge.label());
//        return partnerResponse;
//    }
}
