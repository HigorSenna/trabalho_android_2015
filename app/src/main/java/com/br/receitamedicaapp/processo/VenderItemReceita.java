package com.br.receitamedicaapp.processo;

import android.os.AsyncTask;

import com.br.receitamedicaapp.rest.ClientRest;

/**
 * Created by Higor Senna on 26/11/2016.
 */
public class VenderItemReceita extends AsyncTask<Void,Integer,String> {

    private int idItem;

    public VenderItemReceita(int idItem){
        this.idItem = idItem;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return new ClientRest().venderItemReceita(idItem);
    }
}
