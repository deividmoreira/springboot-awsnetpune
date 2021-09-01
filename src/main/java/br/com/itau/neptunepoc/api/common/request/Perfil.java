package br.com.itau.neptunepoc.api.common.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

@JsonRootName("perfil")
@Getter
@Setter
public class Perfil {

    @JsonProperty("nome_pessoa")
    private String nomePessoa;

    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;

    @JsonProperty("nome_digital")
    private String nomeDigital;

    @JsonProperty("nome_fantasia")
    private String nomeFantasia;

    @JsonProperty("data_atualizacao")
    private String dataAtualizacao;

}
