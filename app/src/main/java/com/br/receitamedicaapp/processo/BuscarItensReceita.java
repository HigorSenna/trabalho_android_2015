package com.br.receitamedicaapp.processo;

import android.os.AsyncTask;

import com.br.receitamedicaapp.model.ItemReceita;
import com.br.receitamedicaapp.model.Medico;
import com.br.receitamedicaapp.rest.ClientRest;

import java.util.List;

/**
 * Created by Higor Senna on 25/11/2016.
 */
public class BuscarItensReceita extends AsyncTask<Void,Integer,List<ItemReceita>> {

    private int numeroReceita;

    public BuscarItensReceita(int numeroReceita){
        this.numeroReceita = numeroReceita;
    }

    @Override
    protected List<ItemReceita> doInBackground(Void... voids) {
        return new ClientRest().buscarItensReceita(numeroReceita);
    }
}
