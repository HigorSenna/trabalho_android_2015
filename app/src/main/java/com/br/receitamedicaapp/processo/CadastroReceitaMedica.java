package com.br.receitamedicaapp.processo;

import android.os.AsyncTask;

import com.br.receitamedicaapp.model.ReceitaMedica;
import com.br.receitamedicaapp.model.ReciboReceita;
import com.br.receitamedicaapp.rest.ClientRest;

/**
 * Created by Higor Senna on 21/11/2016.
 */
public class CadastroReceitaMedica extends AsyncTask<Void,Integer,ReciboReceita> {

    private ReceitaMedica receita;

    public CadastroReceitaMedica(ReceitaMedica receita){
        this.receita = receita;
    }

    @Override
    protected ReciboReceita doInBackground(Void... voids) {
        return new ClientRest().cadastroReceitaMedica(receita);
    }
}
