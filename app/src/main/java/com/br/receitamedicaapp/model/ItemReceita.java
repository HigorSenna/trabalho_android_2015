package com.br.receitamedicaapp.model;

/**
 * Created by Higor Senna on 20/11/2016.
 */
public class ItemReceita {

    private int idItem;
    private String contraIndicacao;
    private String instrucao;
    private String nmReceita;
    private int regAnvisa;
    private String uso;
    private String status;
    private ReceitaMedica receitaMedica;


    public ItemReceita() {
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getContraIndicacao() {
        return contraIndicacao;
    }

    public void setContraIndicacao(String contraIndicacao) {
        this.contraIndicacao = contraIndicacao;
    }

    public String getInstrucao() {
        return instrucao;
    }

    public void setInstrucao(String instrucao) {
        this.instrucao = instrucao;
    }

    public String getNmReceita() {
        return nmReceita;
    }

    public void setNmReceita(String nmReceita) {
        this.nmReceita = nmReceita;
    }

    public int getRegAnvisa() {
        return regAnvisa;
    }

    public void setRegAnvisa(int regAnvisa) {
        this.regAnvisa = regAnvisa;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ReceitaMedica getReceitaMedica() {
        return receitaMedica;
    }

    public void setReceitaMedica(ReceitaMedica receitaMedica) {
        this.receitaMedica = receitaMedica;
    }

    public ReceitaMedica getReceitasMedica() {
        return receitaMedica;
    }

    public void setReceitasMedica(ReceitaMedica receitaMedica) {
        this.receitaMedica = receitaMedica;
    }
}
