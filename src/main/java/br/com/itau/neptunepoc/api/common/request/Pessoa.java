package br.com.itau.neptunepoc.api.common.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pessoa {

    @JsonProperty("id_pessoa")
    private String idPessoa;

    @JsonProperty("tipo_pessoa")
    private String tipoPessoa;

    @JsonProperty("perfil")
    private Perfil perfil;

}
