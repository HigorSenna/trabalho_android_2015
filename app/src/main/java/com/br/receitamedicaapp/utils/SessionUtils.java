package com.br.receitamedicaapp.utils;

import com.br.receitamedicaapp.model.Usuario;

/**
 * Created by Higor Senna on 25/11/2016.
 */
public abstract class SessionUtils {

    private static Usuario usuarioLogado;

    public static void setUsuarioSession(Usuario usuario){
        usuarioLogado = usuario;
    }

    public static Usuario getUsuarioLogado(){

        return usuarioLogado;
    }
}
