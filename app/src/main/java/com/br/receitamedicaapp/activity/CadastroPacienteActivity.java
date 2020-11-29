package com.br.receitamedicaapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.br.receitamedicaapp.R;
import com.br.receitamedicaapp.model.Paciente;
import com.br.receitamedicaapp.processo.CadastroPaciente;

public class CadastroPacienteActivity extends AppCompatActivity {


    private AutoCompleteTextView txtCpfCadastroPaciente,txtNomeCadastroPaciente;
    private Button botaoCadastrarPaciente,botaoVoltarCadastroPaciente;

    private Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_paciente);

        bind();

        botaoCadastrarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isCamposPreenchidos()){
                    try {
                        cadastrarPaciente();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Erro ao cadastrar !!",Toast.LENGTH_LONG);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Todos os campos são obrigatórios!!",Toast.LENGTH_LONG);
                }

                limparCampos();
            }
        });

        botaoVoltarCadastroPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void limparCampos(){
        txtCpfCadastroPaciente.setText(null);
        txtNomeCadastroPaciente.setText(null);
    }

    private void cadastrarPaciente() throws Exception{
        paciente = new Paciente();
        paciente.setCpfPaciente(txtCpfCadastroPaciente.getText().toString());
        paciente.setNmPaciente(txtNomeCadastroPaciente.getText().toString());

        String msg = new CadastroPaciente(paciente).execute().get();
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    private boolean isCamposPreenchidos(){
        return !txtCpfCadastroPaciente.getText().toString().trim().equals("")
                && txtCpfCadastroPaciente.getText() != null
                && !txtNomeCadastroPaciente.getText().toString().trim().equals("")
                && txtNomeCadastroPaciente.getText() != null;
    }


    private void bind(){
        txtCpfCadastroPaciente = (AutoCompleteTextView) findViewById(R.id.txtCpfCadastroPaciente);
        txtNomeCadastroPaciente = (AutoCompleteTextView) findViewById(R.id.txtNomeCadastroPaciente);
        botaoCadastrarPaciente = (Button) findViewById(R.id.botaoCadastrarPaciente);
        botaoVoltarCadastroPaciente = (Button) findViewById(R.id.botaoVoltarCadastroPaciente);
    }

}
