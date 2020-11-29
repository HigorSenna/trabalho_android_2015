package com.br.receitamedicaapp.processo;

import android.graphics.Paint;
import android.os.AsyncTask;

import com.br.receitamedicaapp.model.Paciente;
import com.br.receitamedicaapp.rest.ClientRest;

/**
 * Created by Higor Senna on 22/11/2016.
 */
public class BuscarPacienteCpf extends AsyncTask<Void,Integer,Paciente> {

    private String cpf;

    public BuscarPacienteCpf(String cpf){
        this.cpf = cpf;
    }

    @Override
    protected Paciente doInBackground(Void... voids) {
        return new ClientRest().buscarPacienteCpf(cpf);
    }
}
