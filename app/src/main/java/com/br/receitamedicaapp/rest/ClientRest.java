package com.br.receitamedicaapp.rest;

import android.util.Log;

import com.br.receitamedicaapp.model.Farmacia;
import com.br.receitamedicaapp.model.ItemReceita;
import com.br.receitamedicaapp.model.Medico;
import com.br.receitamedicaapp.model.Paciente;
import com.br.receitamedicaapp.model.ReceitaMedica;
import com.br.receitamedicaapp.model.ReciboReceita;
import com.br.receitamedicaapp.model.Usuario;
import com.br.receitamedicaapp.utils.ConexaoUtils;
import com.br.receitamedicaapp.utils.DataUtils;
import com.br.receitamedicaapp.utils.JsonUtils;
import com.br.receitamedicaapp.utils.ParamUtils;
import com.br.receitamedicaapp.utils.UrlUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Higor Senna on 19/11/2016.
 */
public class ClientRest {

    public String venderItemReceita(int idItem){
        try {

            URL url = new URL(UrlUtils.getUrlVenderItemReceita());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            ConexaoUtils.conectar(conn);

            ConexaoUtils.sendPOST(JsonUtils.convertParamIdItemReceitaToJson(idItem));

            InputStream in = new BufferedInputStream(conn.getInputStream());

            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
            JSONObject msgVenda = new JSONObject(sb.toString());
            return msgVenda.getString(ParamUtils.MESSAGE);

        }catch (Exception e){
            Log.i("error:",e.getMessage());
            return null;
        }
    }

    public List<ItemReceita> buscarItensReceita(int numReceita){
        try {

            URL url = new URL(UrlUtils.getUrlBuscarItensReceita());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            ConexaoUtils.conectar(conn);

            ConexaoUtils.sendPOST(JsonUtils.convertParamNumReceitaToJson(numReceita));

            InputStream in = new BufferedInputStream(conn.getInputStream());

            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }

            List<ItemReceita> itensReceita = new ArrayList<>();
            JSONArray itensJson = new JSONArray(sb.toString());
            for( int i = 0 ; i < itensJson.length() ; i++){

                JSONObject objetoItem = itensJson.getJSONObject(i);

                ItemReceita item = new ItemReceita();

                item.setUso(objetoItem.getString(ParamUtils.USO));
                item.setRegAnvisa(objetoItem.getInt(ParamUtils.REG_ANVISA));
                item.setNmReceita(objetoItem.getString(ParamUtils.NM_RECEITA));
                item.setInstrucao(objetoItem.getString(ParamUtils.INSTRUCAO));
                item.setContraIndicacao(objetoItem.getString(ParamUtils.CONTRA_INDICACAO));
                item.setIdItem(objetoItem.getInt(ParamUtils.ID_ITEM));
                item.setStatus(objetoItem.getString(ParamUtils.STATUS));


                itensReceita.add(item);
            }

            return itensReceita;
        }catch (Exception e){
            Log.i("error:",e.getMessage());
            return null;
        }
    }

    public Usuario buscarUsuario(String login,String senha){
        try {

            URL url = new URL(UrlUtils.getUrlValidarUsuario());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            ConexaoUtils.conectar(conn);

            ConexaoUtils.sendPOST(JsonUtils.convertParamLoginSenhaToJson(login,senha));

            InputStream in = new BufferedInputStream(conn.getInputStream());

            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }

            JSONObject usuarioJson = new JSONObject(sb.toString());
            Usuario usuario = null;

            if(usuarioJson != null && !usuarioJson.toString().trim().equals("")){
                usuario = new Usuario();
                usuario.setLogin(usuarioJson.getString(ParamUtils.LOGIN));
                usuario.setSenha(usuarioJson.getString(ParamUtils.SENHA));
                usuario.setFlTipoUsuario(usuarioJson.getString(ParamUtils.FL_TIPO_USUARIO));
            }

            return usuario;

        }catch (Exception e){
            Log.i("error:",e.getMessage());
            return null;
        }
    }

    public ReceitaMedica buscarReceitaPorNumero(int numeroReceita){
        try {

            URL url = new URL(UrlUtils.getUrlBuscarReceitaPorNumero());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            ConexaoUtils.conectar(conn);

            ConexaoUtils.sendPOST(JsonUtils.convertParamNumReceitaToJson(numeroReceita));

            InputStream in = new BufferedInputStream(conn.getInputStream());

            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }

            JSONObject receitaJson = new JSONObject(sb.toString());
            ReceitaMedica receita = new ReceitaMedica();

            receita.setData(DataUtils.convertStringDateSqlToDateJava(receitaJson.getString(ParamUtils.DATA_RECEITA)));
            receita.setNumReceita(receitaJson.getInt(ParamUtils.NUM_RECEITA));
            receita.setFlStatus(receitaJson.getString(ParamUtils.FL_STATUS));
            receita.setMedico(JsonUtils.getMedicoJson(receitaJson.getJSONObject(ParamUtils.MEDICO)));
            receita.setPaciente(JsonUtils.getPacienteJson(receitaJson.getJSONObject(ParamUtils.PACIENTE)));

            return receita;

        }catch (Exception e){
            Log.i("error:",e.getMessage());
            return null;
        }
    }

    public String cancelarReceita(int numeroReceita){
        try {

            URL url = new URL(UrlUtils.getUrlCancelarReceita());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            ConexaoUtils.conectar(conn);

            ConexaoUtils.sendPOST(JsonUtils.convertParamNumReceitaToJson(numeroReceita));

            InputStream in = new BufferedInputStream(conn.getInputStream());

            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }

            JSONObject messageJson = new JSONObject(sb.toString());

            return messageJson.getString(ParamUtils.MESSAGE);

        }catch (Exception e){
            Log.i("error:",e.getMessage());
            return null;
        }
    }

    public List<ReceitaMedica> buscarReceitasPaciente(String cpf){
        try {

            URL url = new URL(UrlUtils.getUrlBuscaReceitasPaciente());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            ConexaoUtils.conectar(conn);

            ConexaoUtils.sendPOST(JsonUtils.convertParamCpfTojJson(cpf));

            InputStream in = new BufferedInputStream(conn.getInputStream());

            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }

            List<ReceitaMedica> receitas = new ArrayList<>();
            JSONArray receitaJson = new JSONArray(sb.toString());
            for( int i = 0 ; i < receitaJson.length() ; i++){

                JSONObject r = receitaJson.getJSONObject(i);

                ReceitaMedica receita = new ReceitaMedica();

                receita.setData(DataUtils.convertStringToDate(r.getString(ParamUtils.DATA_RECEITA)));

                receita.setNumReceita(r.getInt(ParamUtils.NUM_RECEITA));
                receita.setFlStatus(r.getString(ParamUtils.FL_STATUS));
                receita.setMedico(JsonUtils.getMedicoJson(r.getJSONObject(ParamUtils.MEDICO)));
                receita.setPaciente(JsonUtils.getPacienteJson(r.getJSONObject(ParamUtils.PACIENTE)));

                receitas.add(receita);
            }

            return receitas;

        }catch (Exception e){
            Log.i("error:",e.getMessage());
            return null;
        }
    }

    public Medico buscarMedicoCrm(String crm){
        try {

            URL url = new URL(UrlUtils.getUrlBuscaMedico());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            ConexaoUtils.conectar(conn);

            ConexaoUtils.sendPOST(JsonUtils.convertParamCrmTojJson(crm));

            InputStream in = new BufferedInputStream(conn.getInputStream());

            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }

            JSONObject medicoJSON = new JSONObject(sb.toString());

            Medico medico = null;

            if(medicoJSON != null && !medicoJSON.toString().trim().equals("")){
                medico = new Medico();

                medico.setCrmMedico(medicoJSON.getString(ParamUtils.CRM));
                medico.setNmMedico(medicoJSON.getString(ParamUtils.NOME_MEDICO));
            }

            return medico;

        }catch (Exception e){
            Log.i("error:",e.getMessage());
            return null;
        }
    }

    public Paciente buscarPacienteCpf(String cpf){
        try {

            URL url = new URL(UrlUtils.getUrlBuscaPaciente());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            ConexaoUtils.conectar(conn);

            ConexaoUtils.sendPOST(JsonUtils.convertParamCpfTojJson(cpf));

            InputStream in = new BufferedInputStream(conn.getInputStream());

            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }

            JSONObject pacienteJSON = new JSONObject(sb.toString());

            Paciente paciente = null;

            if(pacienteJSON != null && !pacienteJSON.toString().trim().equals("")){
                paciente = new Paciente();

                paciente.setCpfPaciente(pacienteJSON.getString(ParamUtils.CPF));
                paciente.setNmPaciente(pacienteJSON.getString(ParamUtils.NOME_PACIENTE));
            }

            return paciente;

        }catch (Exception e){
            Log.i("error:",e.getMessage());
            return null;
        }
    }

    public ReciboReceita cadastroReceitaMedica(ReceitaMedica receita){
        try {

            URL url = new URL(UrlUtils.getUrlCadastroReceita());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            ConexaoUtils.conectar(conn);

            ConexaoUtils.sendPOST(JsonUtils.convertReceitaToJson(receita));

            InputStream in = new BufferedInputStream(conn.getInputStream());

            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }

            JSONObject msgJson = new JSONObject(sb.toString());
            ReciboReceita recibo = new ReciboReceita(msgJson.getString(ParamUtils.CRM)
                    ,msgJson.getString(ParamUtils.CPF)
                    ,msgJson.getInt(ParamUtils.NUM_RECEITA)
                    ,msgJson.getString(ParamUtils.DATA_RECEITA)
                    ,msgJson.getString(ParamUtils.MSG));
            // String msg = msgJson.getString("message") ;

           // return msg;
            return recibo;

        }catch (Exception e){
            Log.i("error:",e.getMessage());
            return null;
        }
    }

    public String cadastroFarmacia(Farmacia farmacia){
        try {

            URL url = new URL(UrlUtils.getUrlCadastroFarmacia());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            ConexaoUtils.conectar(conn);

            ConexaoUtils.sendPOST(JsonUtils.convertFarmaciaToJson(farmacia));

            InputStream in = new BufferedInputStream(conn.getInputStream());

            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }

            JSONObject msgJson = new JSONObject(sb.toString());
            String msg = msgJson.getString("message") ;

            return msg;

        }catch (Exception e){
            Log.i("error:",e.getMessage());
            return null;
        }
    }

    public String cadastroPaciente(Paciente paciente){
        try {

            URL url = new URL(UrlUtils.getUrlCadastroPaciente());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            ConexaoUtils.conectar(conn);

            ConexaoUtils.sendPOST(JsonUtils.convertPacienteToJson(paciente));

            InputStream in = new BufferedInputStream(conn.getInputStream());

            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }

            JSONObject msgJson = new JSONObject(sb.toString());
            String msg = msgJson.getString("message") ;

            return msg;

        }catch (Exception e){
            Log.i("error:",e.getMessage());
            return null;
        }
    }

    public String cadastrarMedico(Medico medico){
        try {

            URL url = new URL(UrlUtils.getUrlCadastroMedico());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            ConexaoUtils.conectar(conn);

            ConexaoUtils.sendPOST(JsonUtils.convertMedicoToJson(medico));

            InputStream in = new BufferedInputStream(conn.getInputStream());

            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }

            JSONObject msgJson = new JSONObject(sb.toString());
            String msg = msgJson.getString("message") ;

            return msg;

        }catch (Exception e){
            Log.i("error:",e.getMessage());
            return null;
        }
    }


}
