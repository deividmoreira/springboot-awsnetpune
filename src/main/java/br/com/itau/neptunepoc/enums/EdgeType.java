package br.com.itau.neptunepoc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EdgeType {
    SOCIO(1,"Socio"),
    REPRESENTANTE_LEGAL(2, "Representante Legal"),
    PROCURADOR(3, "Procurador"),
    OPERADOR(4, "Operador"),
    PORTADOR_DO_CARTAO(5, "Portador do Cartao"),
    GARANTIDOR(6, "Garantidor"),
    MATRIZ_FILIAL(7, "Matriz/Filial"),
    CONTROLADORA(8, "Controladora"),
    SUBSIDIARIA(9, "Subsidiaria"),
    PAI(10, "Pai"),
    IRMAO(11, "Irmao"),
    SOBRINHO(12, "Sobrinho"),
    CONTATO(13, "Contato"),
    RESPONSAVEL(14, "Responsavel"),
    PORTA_VOZ(15, "Porta voz"),
    FUNCIONARIO(16, "Funcionario"),
    CLIENTE_DO_CLIENTE(17, "Cliente do Cliente"),
    PARCERIA(18, "Parceria"),
    GRUPO_ECONOMICO(19, "Grupo economico");

    private Integer codigo;
    private String descricao;

}
