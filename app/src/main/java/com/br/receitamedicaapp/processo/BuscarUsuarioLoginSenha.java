package com.br.receitamedicaapp.processo;

import android.os.AsyncTask;

import com.br.receitamedicaapp.model.Usuario;
import com.br.receitamedicaapp.rest.ClientRest;

/**
 * Created by Higor Senna on 25/11/2016.
 */
public class BuscarUsuarioLoginSenha extends AsyncTask<Void,Integer,Usuario> {

    private String login;
    private String senha;

    public BuscarUsuarioLoginSenha(String login,String senha){
        this.senha = senha;
        this.login = login;
    }

    @Override
    protected Usuario doInBackground(Void... params) {
        return new ClientRest().buscarUsuario(login,senha);
    }
}
