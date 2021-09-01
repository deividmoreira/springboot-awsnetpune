package br.com.itau.neptunepoc.api.common.request;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

@JsonRootName("pessoa_destino")
@Getter
@Setter
public class PessoaDestino extends Pessoa{}
