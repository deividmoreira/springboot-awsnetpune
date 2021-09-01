package br.com.itau.neptunepoc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerticePerfil extends Vertice{

    private String nome;
    private String cpfCnpj;
    private String nomeDigital;
    private String nomeFantasia;
    private String dataAtualizacao;

}
