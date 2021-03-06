package com.br.receitamedicaapp.utils;

/**
 * Created by Higor Senna on 19/11/2016.
 */
public abstract class ParamUtils {
    public static final String IP = "http://192.168.1.102";
    public static final String PORTA = "10080";
    public static final String DOMINIO = IP + ":" + PORTA + "/ReceitaMedica-web/";
    public static final String URL_VIANNA = "http://192.168.4.8:8084/ReceitaMedica-web/";

    public static final String MEDICO = "medico";
    public static final String PACIENTE = "paciente";
    public static final String RECEITA_MEDICA = "receita";


    /*Medico*/
    public static final String CRM = "crmMedico";
    public static final String NOME_MEDICO = "nmMedico";

    /*Paciente*/
    public static final String CPF = "cpfPaciente";
    public static final String NOME_PACIENTE = "nmPaciente";

	/*receita*/

    public static final String FL_STATUS = "flStatus";
    public static final String NUM_RECEITA = "numReceita";
    public static final String DATA_RECEITA = "data";
    public static final String CONTRA_INDICACAO = "contraIndicacao";
    public static final String USO = "uso";
    public static final String REG_ANVISA = "regAnvisa";
    public static final String NM_RECEITA = "nmReceita";
    public static final String INSTRUCAO = "instrucao";
    public static final String ITENS_RECEITAS = "itensReceitas";
    public static final String ID_ITEM = "idItem";
    public static final String STATUS = "status";

    public static final String MESSAGE = "message";
    public static final String MSG= "msg";
    public static final String MSG_CADASTRO_RECEITA = "Receita médica disponível na base de dados do ministério da saúde";

    public static final String NOME_FARMACIA = "nmFarmacia";
    public static final String CNPJ_FARMACIA= "cnpjFarmacia";

    /*usuario*/
    public static final String LOGIN= "login";
    public static final String SENHA = "senha";
    public static final String FL_TIPO_USUARIO ="flTipoUsuario";
}
