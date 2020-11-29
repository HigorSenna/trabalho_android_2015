package com.br.receitamedicaapp.soap;

import android.util.Log;

import com.br.receitamedicaapp.listview.ReceitaListView;
import com.br.receitamedicaapp.model.Paciente;
import com.br.receitamedicaapp.model.ReceitaMedica;
import com.br.receitamedicaapp.utils.ParamUtils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Higor Senna on 03/12/2016.
 */
public class ClienteSOAP {
    final String NAMESPACE = ParamUtils.DOMINIO + "ServicoPacienteSOAP/pacienteSOAP/";
    public String cadastroPacienteSOAP(String cpfPaciente,String nomePaciente){

        final String METHOD_NAME = "cadastroPacienteSOAP";
        final String SOAP_ACTION = NAMESPACE+METHOD_NAME;
        final String URL = "http://192.168.1.102:10080/ReceitaMedica-web/ServicoPacienteSOAP/pacienteSOAP/cadastroPacienteSOAP";

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("paciente", new Paciente());
        //request.addProperty(ParamUtils.CPF, cpfPaciente);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL,50000);
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject result = (SoapObject ) envelope.bodyIn;
            Log.i("", "cadastroPacienteSOAP: " +result.toString());

            SoapObject us = (SoapObject) result.getProperty(0);

            String nome  = us.getProperty(ParamUtils.NOME_PACIENTE).toString();
            if(!nome.equals("") && nome != null){
                return "Paciente inserido com sucesso";
            }
            else{
                return "Paciente ja existe";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }
}
