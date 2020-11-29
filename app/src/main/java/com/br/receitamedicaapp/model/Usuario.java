package com.br.receitamedicaapp.model;

import java.io.Serializable;

/**
 * Created by Higor Senna on 25/11/2016.
 */
public class Usuario implements Serializable {

    private String login;
    private String senha;
    private String flTipoUsuario;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFlTipoUsuario() {
        return flTipoUsuario;
    }

    public void setFlTipoUsuario(String flTipoUsuario) {
        this.flTipoUsuario = flTipoUsuario;
    }
}
