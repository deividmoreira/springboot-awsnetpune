package br.com.itau.neptunepoc.api.partner.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PartnerResponse {

    @JsonProperty("id_cliente_pai")
    private String idClientePai;

    @JsonProperty("tipo_cliente_pai")
    private String tipoClientePai;

    @JsonProperty("id_cliente_filho")
    private String idClienteFilho;

    @JsonProperty("tipo_cliente_filho")
    private String tipoClienteFilho;

    @JsonProperty("id_relacionamento")
    private String idRelacionamento;

    @JsonProperty("tipo_relacionamento")
    private String tipoRelacionamento;

}
