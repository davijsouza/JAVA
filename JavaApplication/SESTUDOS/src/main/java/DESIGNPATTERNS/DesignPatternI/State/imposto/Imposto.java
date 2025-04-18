package DESIGNPATTERNS.DesignPatternI.State.imposto;

import DESIGNPATTERNS.DesignPatternI.State.orcamento.Orcamento;

import java.math.BigDecimal;

public interface Imposto {
    BigDecimal calcular(Orcamento orcamento);
}
