package com.br.receitamedicaapp.model;

import java.io.Serializable;

/**
 * Created by Higor Senna on 19/11/2016.
 */
public class Medico implements Serializable{

    private String crmMedico;
    private String nmMedico;

    public String getCrmMedico() {
        return crmMedico;
    }

    public void setCrmMedico(String crmMedico) {
        this.crmMedico = crmMedico;
    }

    public String getNmMedico() {
        return nmMedico;
    }

    public void setNmMedico(String nmMedico) {
        this.nmMedico = nmMedico;
    }
}
