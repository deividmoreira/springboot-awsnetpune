package br.com.itau.neptunepoc.api.partner.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@JsonRootName("participacao")
@Getter
@Setter
public class Participacao {

    @JsonProperty("percentual_participacao")
    private Double percentualParticipacao;

    @JsonProperty("capital_investido")
    private BigDecimal capitalInvestido;

    @JsonProperty("data_inicio")
    private Date dataInicio;

    @JsonProperty("papel")
    private String papel;

}
