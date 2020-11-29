package com.br.receitamedicaapp.processo;

import android.os.AsyncTask;

import com.br.receitamedicaapp.model.Farmacia;
import com.br.receitamedicaapp.rest.ClientRest;

/**
 * Created by Higor Senna on 19/11/2016.
 */
public class CadastroFarmacia extends AsyncTask<Void,Integer,String> {

    private Farmacia farmacia;

    public CadastroFarmacia(Farmacia farmacia){
        this.farmacia = farmacia;
    }

    @Override
    protected String doInBackground(Void... params) {
        return new ClientRest().cadastroFarmacia(farmacia);
    }
}
