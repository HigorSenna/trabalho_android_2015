package com.br.receitamedicaapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.br.receitamedicaapp.R;
import com.br.receitamedicaapp.processo.BuscarReceitasPaciente;
import com.br.receitamedicaapp.processo.CadastroPacienteSOAP;

public class MedicoActivity extends AppCompatActivity {

    private Button botaoSendCadastroPaciente,botaoSendCadastroReceita,botaoBuscarReceitasPaciente,botaoCadastroPacienteSOAP;

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico);

        bind();

        botaoSendCadastroPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCadastroPaciente();
            }
        });

        botaoSendCadastroReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCadastroReceita();
            }
        });

        botaoBuscarReceitasPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBuscaReceitasPaciente();
            }
        });
        botaoCadastroPacienteSOAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCadastroPacienteSOAP();
            }
        });
    }

    private void goToCadastroPacienteSOAP(){
        intent = new Intent(getApplicationContext(), CadastroPacienteSOAPActivity.class);
        startActivity(intent);
    }

    private void goToBuscaReceitasPaciente(){
        intent = new Intent(getApplicationContext(),ConsultaReceitasPacienteActivity.class);
        startActivity(intent);
    }

    private void goToCadastroReceita(){
        intent = new Intent(getApplicationContext(),CadastroReceitaMedicaActivity.class);
        startActivity(intent);
    }

    private void goToCadastroPaciente(){
        intent = new Intent(getApplicationContext(),CadastroPacienteActivity.class);
        startActivity(intent);
    }

    private void bind (){
        botaoSendCadastroPaciente = (Button) findViewById(R.id.botaoSendCadastroPaciente);
        botaoSendCadastroReceita = (Button) findViewById(R.id.botaoSendCadastroReceita);
        botaoBuscarReceitasPaciente = (Button) findViewById(R.id.botaoBuscarReceitasPaciente);
        botaoCadastroPacienteSOAP = (Button) findViewById(R.id.botaoCadastroPacienteSOAP);

    }

}
