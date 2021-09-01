package br.com.itau.neptunepoc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum VertexType {
    PERSON("F","Pessoa Fisica"),
    COMPANY("J", "Pessoa Juridica");

    private String codigo;
    private String descricao;
}
