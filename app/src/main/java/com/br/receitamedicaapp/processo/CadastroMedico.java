package com.br.receitamedicaapp.processo;

import android.os.AsyncTask;

import com.br.receitamedicaapp.model.Medico;
import com.br.receitamedicaapp.rest.ClientRest;

/**
 * Created by Higor Senna on 19/11/2016.
 */
public class CadastroMedico extends AsyncTask<Void,Integer,String> {

    private Medico medico;

    public CadastroMedico(Medico medico){
        this.medico = medico;
    }

    @Override
    protected String doInBackground(Void... params) {
        return new ClientRest().cadastrarMedico(medico);
    }
}
