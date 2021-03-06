package com.br.receitamedicaapp.enums;

/**
 * Created by Higor Senna on 24/11/2016.
 */
public enum StatusReceitaEnum {

    ATIVA("A","ATIVA"),
    CANCELADA("C","CANCELADA"),
    UTILIZADA("U","UTILIZADA");

    private String label;
    private String valor;

    StatusReceitaEnum(String valor, String label) {
        this.valor = valor;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
