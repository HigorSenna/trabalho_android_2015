package com.br.receitamedicaapp.processo;

import android.os.AsyncTask;

import com.br.receitamedicaapp.model.Medico;
import com.br.receitamedicaapp.model.Paciente;
import com.br.receitamedicaapp.rest.ClientRest;

/**
 * Created by Higor Senna on 19/11/2016.
 */
public class CadastroPaciente extends AsyncTask<Void,Integer,String>{

    private Paciente paciente;

    public CadastroPaciente(Paciente paciente){
        this.paciente = paciente;
    }

    @Override
    protected String doInBackground(Void... params) {
        return new ClientRest().cadastroPaciente(paciente);
    }
}
