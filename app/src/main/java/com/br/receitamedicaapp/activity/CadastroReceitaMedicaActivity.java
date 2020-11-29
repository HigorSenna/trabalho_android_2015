package com.br.receitamedicaapp.activity;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.br.receitamedicaapp.R;
import com.br.receitamedicaapp.listview.ItemReceitaListView;
import com.br.receitamedicaapp.model.ItemReceita;
import com.br.receitamedicaapp.model.Medico;
import com.br.receitamedicaapp.model.Paciente;
import com.br.receitamedicaapp.model.ReceitaMedica;
import com.br.receitamedicaapp.model.ReciboReceita;
import com.br.receitamedicaapp.processo.BuscarMedicoCrm;
import com.br.receitamedicaapp.processo.BuscarPacienteCpf;
import com.br.receitamedicaapp.processo.CadastroReceitaMedica;

import java.util.ArrayList;
import java.util.List;

public class CadastroReceitaMedicaActivity extends AppCompatActivity {

    private AutoCompleteTextView
            txtInstrucaoReceita
            ,txtNomeReceita
            ,txtUsoReceita
            ,txtContraIndicacaoReceita
            ,txtNumeroAnvisaReceita;

    private EditText txtCpfPacienteCadastroReceita,txtCrmMedicoCadastroReceita;

    private Button botaoAddMedicamento,botaoCadastrarReceita,botaoVoltarCadastroReceita;
    private List<ItemReceita> itensReceita = new ArrayList<ItemReceita>();
    private ListView medicamentos;
    private ReceitaMedica receita;
    private Paciente paciente;
    private Medico medico;
    private ReciboReceita recibo;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_receita_medica);

        bind();

        botaoAddMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isCamposMedicamentoPeenchidos()){
                    ItemReceita item = getItemReceita();
                    addItemReceita(item);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Todos os campos são obrigatórios para adicionar medicamento",Toast.LENGTH_LONG).show();
                }
            }
        });

        botaoCadastrarReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(isReceitaValidaCadastro()){
                        cadastrarReceita();
                        limparCamposMedicamentos();
                        goToReciboReceita();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Todos os campos são obrigatórios e é necessário ao menos um medicamento.",Toast.LENGTH_LONG).show();
                    }

                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Falha ao recuperar recibo",Toast.LENGTH_LONG).show();
                }
            }
        });

        txtCpfPacienteCadastroReceita.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                try {
                    if(isCampoCpfPreenchido()) {
                        paciente = new BuscarPacienteCpf(txtCpfPacienteCadastroReceita.getText().toString()).execute().get();
                        validarRetornoJsonPaciente(paciente);
                    }
                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Falha ao recuperar paciente",Toast.LENGTH_LONG);
                }
            }
        });

        txtCrmMedicoCadastroReceita.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                try {
                    if(isCamporCrmPreenchido()){
                        medico = new BuscarMedicoCrm(txtCrmMedicoCadastroReceita.getText().toString()).execute().get();
                        validarRetornoJsonMedico(medico);
                    }
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Falha ao recuperar medico",Toast.LENGTH_LONG);
                }
            }
        });

        botaoVoltarCadastroReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private boolean isReceitaValidaCadastro(){
        return paciente != null
                && medico != null
                && !itensReceita.isEmpty();
    }

    private boolean isCamposMedicamentoPeenchidos(){
        return  txtInstrucaoReceita.getText() != null
                && !txtNomeReceita.getText().toString().trim().equals("")
                && txtNomeReceita.getText() != null
                && !txtUsoReceita.getText().toString().trim().equals("")
                && txtUsoReceita.getText() != null
                && !txtContraIndicacaoReceita.getText().toString().trim().equals("")
                && txtContraIndicacaoReceita.getText() != null
                && !txtNumeroAnvisaReceita.getText().toString().trim().equals("")
                && txtNumeroAnvisaReceita.getText() != null;
    }

    private void limparCamposMedicamentos(){
        txtInstrucaoReceita.setText("");
        txtNomeReceita.setText("");
        txtUsoReceita.setText("");
        txtContraIndicacaoReceita.setText("");
        txtNumeroAnvisaReceita.setText("");
    }

    private void goToReciboReceita(){
        intent = new Intent(getApplicationContext(),ReciboReceitaActivity.class);
        intent.putExtra("recibo",recibo);
        startActivity(intent);
    }

    private boolean isCamporCrmPreenchido(){
        return txtCrmMedicoCadastroReceita.getText() != null
                && !txtCrmMedicoCadastroReceita.getText().toString().trim().equals("");
    }

    private void validarRetornoJsonMedico(Medico medico){
        if (medico != null) {
            txtCrmMedicoCadastroReceita.setText(medico.getNmMedico());
        } else {
            txtCrmMedicoCadastroReceita.setText(null);
            Toast.makeText(getApplicationContext(),"Nenhum medico encontrado",Toast.LENGTH_LONG).show();
        }
    }

    private boolean isCampoCpfPreenchido(){
        return txtCpfPacienteCadastroReceita.getText() != null
                && !txtCpfPacienteCadastroReceita.getText().toString().trim().equals("");
    }

    private void validarRetornoJsonPaciente(Paciente paciente){
        if (paciente != null) {
            txtCpfPacienteCadastroReceita.setText(paciente.getNmPaciente());
        } else {
            txtCpfPacienteCadastroReceita.setText(null);
            Toast.makeText(getApplicationContext(),"Nenhum paciente encontrado",Toast.LENGTH_LONG).show();
        }
    }

    private void cadastrarReceita() throws Exception{
        receita = new ReceitaMedica();
        receita.setMedico(medico);
        receita.setPaciente(paciente);
        receita.setItensReceitas(itensReceita);
        recibo = new CadastroReceitaMedica(receita).execute().get();
    }

    private void addItemReceita(ItemReceita item){
        if(itensReceita == null){
            itensReceita = new ArrayList<>();
            itensReceita.add(item);
        }
        else{
            itensReceita.add(item);
        }
        medicamentos.setAdapter(null);
        ItemReceitaListView adapter = new ItemReceitaListView(getApplicationContext(),itensReceita);
        medicamentos.setAdapter(adapter);
        medicamentos.setBackgroundColor(Color.TRANSPARENT);
    }

    private ItemReceita getItemReceita(){
        ItemReceita item = new ItemReceita();
        item.setContraIndicacao(txtContraIndicacaoReceita.getText().toString());
        item.setInstrucao(txtInstrucaoReceita.getText().toString());
        item.setNmReceita(txtNomeReceita.getText().toString());
        item.setRegAnvisa(Integer.parseInt(txtNumeroAnvisaReceita.getText().toString()));
        item.setUso(txtUsoReceita.getText().toString());

        return item;
    }

    private void bind(){
        botaoAddMedicamento = (Button) findViewById(R.id.botaoAddMedicamento);
        botaoCadastrarReceita = (Button) findViewById(R.id.botaoCadastrarReceita);
        botaoVoltarCadastroReceita = (Button) findViewById(R.id.botaoVoltarCadastroReceita);
        txtInstrucaoReceita = (AutoCompleteTextView) findViewById(R.id.txtInstrucaoReceita);
        txtNomeReceita = (AutoCompleteTextView) findViewById(R.id.txtNomeReceita);
        txtUsoReceita = (AutoCompleteTextView) findViewById(R.id.txtUsoReceita);
        txtContraIndicacaoReceita = (AutoCompleteTextView) findViewById(R.id.txtContraIndicacaoReceita);
        txtNumeroAnvisaReceita = (AutoCompleteTextView) findViewById(R.id.txtNumeroAnvisaReceita);
        medicamentos = (ListView) findViewById(R.id.medicamentos);
        txtCpfPacienteCadastroReceita = (EditText) findViewById(R.id.txtCpfPacienteCadastroReceita);
        txtCrmMedicoCadastroReceita = (EditText) findViewById(R.id.txtCrmMedicoCadastroReceita);
    }
}
