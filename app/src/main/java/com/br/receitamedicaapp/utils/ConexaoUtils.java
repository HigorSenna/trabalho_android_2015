package com.br.receitamedicaapp.utils;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by Higor Senna on 19/11/2016.
 */
public abstract class ConexaoUtils {

    private static HttpURLConnection conexao;

    public static void sendPOST(Object objetoPost) throws Exception{
        OutputStreamWriter out = new OutputStreamWriter(conexao.getOutputStream());
        out.write(objetoPost.toString());
        out.close();
    }

    public static void conectar(HttpURLConnection conn) throws Exception{
        conexao = conn;
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type","application/json");
        conn.connect();
    }
}
