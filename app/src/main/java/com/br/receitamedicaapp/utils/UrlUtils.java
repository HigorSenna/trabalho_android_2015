package com.br.receitamedicaapp.utils;

import com.br.receitamedicaapp.model.ItemReceita;

/**
 * Created by Higor Senna on 19/11/2016.
 */
public abstract class UrlUtils {

    public static final String CADASTRO_MEDICO = "ServicoMedico/medico/cadastroMedico";
    public static final String BUSCAR_MEDICO = "ServicoMedico/medico/buscarMedicoCRM";

    public static final String CADASTRO_PACIENTE = "ServicoPaciente/paciente/cadastroPaciente";
    public static final String BUSCAR_PACIENTE = "ServicoPaciente/paciente/buscarPacienteCPF";

    public static final String CADASTRO_FARMACIA = "ServicoFarmacia/farmacia/cadastroFarmacia";

    public static final String CADASTRO_RECEITA = "ServicoReceitaMedica/receita/cadastroReceita";
    public static final String BUSCAR_RECEITAS_PACIENTE = "ServicoReceitaMedica/receita/buscarReceitasPaciente";
    public static final String CANCELAR_RECEITA = "ServicoReceitaMedica/receita/cancelarReceita";
    public static final String BUSCAR_RECEITA_POR_NUMERO = "ServicoReceitaMedica/receita/buscarReceitaPorNumero";

    public static final String VALIDAR_LOGIN = "ServicoUsuario/usuario/validarUsuario";

    public static final String BUSCAR_ITENS_RECEITA = "ServicoItemReceita/itemReceita/buscarItensPorNumeroReceita";
    public static final String VENDER_ITEM_RECEITA = "ServicoItemReceita/itemReceita/venderItemReceita";

    public static String getUrlVenderItemReceita(){
        return getURLConnect(VENDER_ITEM_RECEITA);
    }

    public static String getUrlBuscarItensReceita(){
        return getURLConnect(BUSCAR_ITENS_RECEITA);
    }

    public static String getUrlValidarUsuario(){
        return getURLConnect(VALIDAR_LOGIN);
    }

    public static String getUrlBuscarReceitaPorNumero(){
        return getURLConnect(BUSCAR_RECEITA_POR_NUMERO);
    }

    public static String getUrlCancelarReceita(){
        return getURLConnect(CANCELAR_RECEITA);
    }

    public static String getUrlBuscaReceitasPaciente(){
        return getURLConnect(BUSCAR_RECEITAS_PACIENTE);
    }

    public static String getUrlBuscaMedico(){
        return getURLConnect(BUSCAR_MEDICO);
    }

    public static String getUrlBuscaPaciente(){
        return getURLConnect(BUSCAR_PACIENTE);
    }

    public static String getUrlCadastroReceita(){
        return getURLConnect(CADASTRO_RECEITA);
    }

    public static String getUrlCadastroFarmacia(){
        return getURLConnect(CADASTRO_FARMACIA);
    }

    public static String getUrlCadastroPaciente(){
        return getURLConnect(CADASTRO_PACIENTE);
    }

    public static String getUrlCadastroMedico(){
        return getURLConnect(CADASTRO_MEDICO);
    }

    public static String getURLConnect(String urlMetodoAcesso){

        StringBuilder sb = new StringBuilder();
            sb.append(ParamUtils.DOMINIO)
            .append(urlMetodoAcesso);

        return sb.toString();
    }
}
