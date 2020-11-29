package com.br.receitamedicaapp.processo;

import android.os.AsyncTask;

import com.br.receitamedicaapp.soap.ClienteSOAP;

/**
 * Created by Higor Senna on 03/12/2016.
 */
public class CadastroPacienteSOAP extends AsyncTask<String,Void,String> {

    private String nomePaciente;
    private String cpfPaciente;

    public CadastroPacienteSOAP(String cpfPaciente,String nomePaciente){
        this.nomePaciente = nomePaciente;
        this.cpfPaciente = cpfPaciente;
    }

    @Override
    protected String doInBackground(String... params) {
        return new ClienteSOAP().cadastroPacienteSOAP(cpfPaciente,nomePaciente);
    }
}
