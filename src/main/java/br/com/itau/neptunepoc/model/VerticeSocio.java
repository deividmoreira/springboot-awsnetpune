package br.com.itau.neptunepoc.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class VerticeSocio extends Vertice{
    private Double percentualParticipacao;
    private BigDecimal capitalInvestido;
    private String papel;
    private Date dataInicio;
}
