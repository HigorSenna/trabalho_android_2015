package com.br.receitamedicaapp.processo;

import android.os.AsyncTask;

import com.br.receitamedicaapp.rest.ClientRest;

/**
 * Created by Higor Senna on 25/11/2016.
 */
public class CancelarReceitaMedica extends AsyncTask<Void,Integer,String> {

    private int numReceita;

    public CancelarReceitaMedica(int numReceita){
        this.numReceita = numReceita;
    }

    @Override
    protected String doInBackground(Void... params) {
        return new ClientRest().cancelarReceita(numReceita);
    }
}
