package com.br.receitamedicaapp.processo;

import android.os.AsyncTask;

import com.br.receitamedicaapp.model.Medico;
import com.br.receitamedicaapp.model.Paciente;
import com.br.receitamedicaapp.rest.ClientRest;

/**
 * Created by Higor Senna on 22/11/2016.
 */
public class BuscarMedicoCrm extends AsyncTask<Void,Integer,Medico> {

    private String crm;

    public BuscarMedicoCrm(String crm){
        this.crm = crm;
    }

    @Override
    protected Medico doInBackground(Void... voids) {
        return new ClientRest().buscarMedicoCrm(crm);
    }
}
