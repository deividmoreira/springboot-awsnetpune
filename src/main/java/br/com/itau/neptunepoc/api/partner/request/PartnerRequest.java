package br.com.itau.neptunepoc.api.partner.request;

import br.com.itau.neptunepoc.api.common.request.PessoaOrigem;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartnerRequest {

    @JsonProperty("pessoa_origem")
    private PessoaOrigem pessoaOrigem;

    @JsonProperty("pessoa_destino")
    private PessoaOrigem pessoaDestino;

    @JsonProperty("participacao")
    private Participacao participacao;

}
