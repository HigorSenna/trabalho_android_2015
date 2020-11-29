package com.br.receitamedicaapp.model;

import java.io.Serializable;

/**
 * Created by Higor Senna on 19/11/2016.
 */
public class Paciente implements Serializable {

    private String cpfPaciente;
    private String nmPaciente;

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getNmPaciente() {
        return nmPaciente;
    }

    public void setNmPaciente(String nmPaciente) {
        this.nmPaciente = nmPaciente;
    }
}
