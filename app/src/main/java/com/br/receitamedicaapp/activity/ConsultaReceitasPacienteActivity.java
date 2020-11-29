package com.br.receitamedicaapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.receitamedicaapp.R;
import com.br.receitamedicaapp.listview.ReceitaListView;
import com.br.receitamedicaapp.model.ReceitaMedica;
import com.br.receitamedicaapp.processo.BuscarReceitasPaciente;

import java.util.List;

public class ConsultaReceitasPacienteActivity extends AppCompatActivity {

    private Button botaoBuscarReceitasPaciente;
    private EditText txtFiltroCpfPaciente;
    private TextView txtNomePacienteConsultaReceita;
    private List<ReceitaMedica> receitas;
    private ListView receitasListView;
    private Intent intent;
    private static int TELA_VISUALIZAR_RECEITA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_receitas_paciente);

        bind();

        botaoBuscarReceitasPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isCampoCpfPreenchido()){
                    fecharTecladoAndroid();
                    try {
                        receitas = new BuscarReceitasPaciente(txtFiltroCpfPaciente.getText().toString()).execute().get();
                        if(receitas != null){
                            exibirNomePaciente();
                            popularGridReceitas();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Nenhum registro encontrado",Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (Exception ex){

                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Por favor preencha o CPF ",Toast.LENGTH_LONG).show();
                }
            }
        });

        receitasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ReceitaMedica receita = receitas.get(i);
                goToVisualizarReceita(receita);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            validarRespostaDaTela(requestCode,resultCode);
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),"Falha ao atualizar grid de receitas ",Toast.LENGTH_LONG).show();
        }
    }
    private void validarRespostaDaTela(int requestCode, int resultCode) throws Exception{
        if(requestCode == TELA_VISUALIZAR_RECEITA){
            if(resultCode == RESULT_OK){
                receitas = new BuscarReceitasPaciente(txtFiltroCpfPaciente.getText().toString()).execute().get();
                popularGridReceitas();
            }
        }
    }

    private void popularGridReceitas(){
        ReceitaListView adapter = new ReceitaListView(getApplicationContext(),receitas);
        receitasListView.setAdapter(adapter);
        receitasListView.setBackgroundColor(Color.TRANSPARENT);
    }

    private void goToVisualizarReceita(ReceitaMedica receita){
        intent = new Intent(getApplicationContext(),VisualizarReceitaActivity.class);
        intent.putExtra("receita",receita);
        startActivityForResult(intent,TELA_VISUALIZAR_RECEITA);
    }

    private void exibirNomePaciente(){
        txtNomePacienteConsultaReceita.setText(receitas.get(0).getPaciente().getNmPaciente().toUpperCase());
    }

    private void fecharTecladoAndroid(){
        ((InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                txtFiltroCpfPaciente.getWindowToken(), 0);
    }

    private boolean isCampoCpfPreenchido(){
        return !txtFiltroCpfPaciente.getText().toString().trim().equals("")
                && txtFiltroCpfPaciente.getText() != null;
    }

    private void bind(){
        botaoBuscarReceitasPaciente = (Button) findViewById(R.id.botaoBuscarReceitasPaciente);
        txtFiltroCpfPaciente = (EditText) findViewById(R.id.txtFiltroCpfPaciente);
        txtNomePacienteConsultaReceita = (TextView) findViewById(R.id.txtNomePacienteConsultaReceita);

        receitasListView = (ListView) findViewById(R.id.receitasListView);
    }
}
