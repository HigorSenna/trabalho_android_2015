package com.br.receitamedicaapp.utils;

import com.br.receitamedicaapp.model.Farmacia;
import com.br.receitamedicaapp.model.Medico;
import com.br.receitamedicaapp.model.Paciente;
import com.br.receitamedicaapp.model.ReceitaMedica;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Higor Senna on 19/11/2016.
 */
public abstract class JsonUtils {

    public static JSONObject convertParamIdItemReceitaToJson(int idItem) throws Exception{
        JSONObject obj = new JSONObject();
        obj.put(ParamUtils.ID_ITEM,String.valueOf(idItem));

        return obj;
    }

    public static JSONObject convertParamLoginSenhaToJson(String login,String senha) throws Exception{

        JSONObject obj = new JSONObject();
        obj.put(ParamUtils.LOGIN,login);
        obj.put(ParamUtils.SENHA,senha);

        return obj;
    }

    public static JSONObject convertParamNumReceitaToJson(int numReceita) throws Exception{
        JSONObject obj = new JSONObject();
        obj.put(ParamUtils.NUM_RECEITA,String.valueOf(numReceita));

        return obj;
    }

    public static Paciente getPacienteJson(JSONObject jsonObject) throws Exception{

        Paciente paciente = new Paciente();

        paciente.setNmPaciente(jsonObject.getString(ParamUtils.NOME_PACIENTE));
        paciente.setCpfPaciente(jsonObject.getString(ParamUtils.CPF));

        return paciente;
    }

    public static Medico getMedicoJson(JSONObject jsonObject) throws Exception{

        Medico medico = new Medico();
        medico.setNmMedico(jsonObject.getString(ParamUtils.NOME_MEDICO));
        medico.setCrmMedico(jsonObject.getString(ParamUtils.CRM));

        return medico;
    }

    public static JSONObject convertParamCrmTojJson(String crm) throws Exception{

        JSONObject jo = new JSONObject();
        jo.put(ParamUtils.CRM,crm);

        return jo;
    }

    public static JSONObject convertParamCpfTojJson(String cpf) throws Exception{

        JSONObject jo = new JSONObject();
        jo.put(ParamUtils.CPF,cpf);

        return jo;
    }

    public static String convertReceitaToJson(ReceitaMedica receita)throws Exception{

        Gson json = new Gson();
        String jsonString = json.toJson(receita);
        return jsonString;
    }

    public static JSONObject convertFarmaciaToJson(Farmacia farmacia)throws Exception{
        JSONObject objetoJson = new JSONObject();

        objetoJson.put(ParamUtils.NOME_FARMACIA,farmacia.getNome());
        objetoJson.put(ParamUtils.CNPJ_FARMACIA,farmacia.getCnpj());

        return objetoJson;
    }

    public static JSONObject convertPacienteToJson(Paciente paciente)throws Exception{
        JSONObject objetoJson = new JSONObject();

        objetoJson.put(ParamUtils.CPF,paciente.getCpfPaciente());
        objetoJson.put(ParamUtils.NOME_PACIENTE,paciente.getNmPaciente());

        return objetoJson;
    }

    public static JSONObject convertMedicoToJson(Medico medico)throws Exception{
        JSONObject objetoJson = new JSONObject();

        objetoJson.put(ParamUtils.CRM,medico.getCrmMedico());
        objetoJson.put(ParamUtils.NOME_MEDICO,medico.getNmMedico());

        return objetoJson;
    }
}
