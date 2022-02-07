package br.com.itau.neptunepoc.api.partner.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

@JsonRootName("relacionamento")
@Getter
@Setter
public class Relacionamento {

    @JsonProperty("tipo_relacionamento")
    private String tipoRelacionamento;

    @JsonProperty("inquilino")
    private String inquilino;

    @JsonProperty("percentual_participacao")
    private String percentualParticipacao;

}
