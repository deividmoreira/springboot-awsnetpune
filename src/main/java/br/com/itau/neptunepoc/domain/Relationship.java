package br.com.itau.neptunepoc.domain;

import br.com.itau.neptunepoc.model.Aresta;
import br.com.itau.neptunepoc.model.Vertice;
import br.com.itau.neptunepoc.model.VerticePerfil;
import br.com.itau.neptunepoc.model.VerticeSocio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Relationship {
    private Vertice verticeOrigem;
    private VerticePerfil verticeOrigemPerfil;
    private Vertice verticeDestino;
    private VerticePerfil verticeDestinoPerfil;
    private VerticeSocio verticeSocio;
    private Aresta arestaOrigemPerfil;
    private Aresta arestaDestinoPerfil;
    private Aresta arestaOrigemRelacionamento;
    private Aresta arestaDestinoRelacionamento;
}
