package com.br.receitamedicaapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Higor Senna on 20/11/2016.
 */
public class ReceitaMedica implements Serializable{

    private int numReceita;
    private Date data;
    private String flStatus;
    private List<ItemReceita> itensReceitas;
    private Medico medico;
    private Paciente paciente;

    public ReceitaMedica(Date data, String flStatus) {
        super();
        this.data = data;
        this.flStatus = flStatus;
    }

    public ReceitaMedica() {
    }

    public int getNumReceita() {
        return this.numReceita;
    }

    public void setNumReceita(int numReceita) {
        this.numReceita = numReceita;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getFlStatus() {
        return this.flStatus;
    }

    public void setFlStatus(String flStatus) {
        this.flStatus = flStatus;
    }

    public List<ItemReceita> getItensReceitas() {
        return this.itensReceitas;
    }

    public void setItensReceitas(List<ItemReceita> itemReceita) {
        this.itensReceitas = itemReceita;
    }

    public ItemReceita addItensReceita(ItemReceita itemReceita) {
        getItensReceitas().add(itemReceita);
        itemReceita.setReceitasMedica(this);

        return itemReceita;
    }

    public ItemReceita removeItensReceita(ItemReceita itemReceita) {
        getItensReceitas().remove(itemReceita);
        itemReceita.setReceitasMedica(null);

        return itemReceita;
    }

    public Medico getMedico() {
        return this.medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
