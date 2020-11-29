package com.br.receitamedicaapp.processo;

import android.os.AsyncTask;

import com.br.receitamedicaapp.model.ReceitaMedica;
import com.br.receitamedicaapp.rest.ClientRest;

/**
 * Created by Higor Senna on 25/11/2016.
 */
public class BuscarReceitaPorNumero extends AsyncTask <Void,Integer,ReceitaMedica> {

    private int numeroReceita;

    public BuscarReceitaPorNumero(int numeroReceita){
        this.numeroReceita = numeroReceita;
    }

    @Override
    protected ReceitaMedica doInBackground(Void... voids) {
        return new ClientRest().buscarReceitaPorNumero(numeroReceita);
    }
}
