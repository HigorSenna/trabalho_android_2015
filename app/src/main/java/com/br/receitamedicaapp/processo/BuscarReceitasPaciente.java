package com.br.receitamedicaapp.processo;

import android.os.AsyncTask;

import com.br.receitamedicaapp.model.Medico;
import com.br.receitamedicaapp.model.ReceitaMedica;
import com.br.receitamedicaapp.rest.ClientRest;

import java.util.List;

/**
 * Created by Higor Senna on 22/11/2016.
 */
public class BuscarReceitasPaciente extends AsyncTask<Void,Integer,List<ReceitaMedica>> {

    private String cpf;

    public BuscarReceitasPaciente(String cpf){
        this.cpf = cpf;
    }

    @Override
    protected List<ReceitaMedica> doInBackground(Void... voids) {
        return new ClientRest().buscarReceitasPaciente(cpf);
    }
}
