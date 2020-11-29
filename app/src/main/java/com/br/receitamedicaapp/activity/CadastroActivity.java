package com.br.receitamedicaapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.br.receitamedicaapp.R;
import com.br.receitamedicaapp.model.Farmacia;
import com.br.receitamedicaapp.model.Medico;
import com.br.receitamedicaapp.model.Paciente;
import com.br.receitamedicaapp.processo.CadastroFarmacia;
import com.br.receitamedicaapp.processo.CadastroMedico;
import com.br.receitamedicaapp.processo.CadastroPaciente;

public class CadastroActivity extends AppCompatActivity {

    private AutoCompleteTextView txtCadastro;
    private EditText txtNomeCadastro;
    private RadioButton opcaoFarmacia,opcaoMedico,opcaoPaciente;
    private Button botaoCadastrar,botaoVoltarCadastroUsuario;

    private Medico medico;
    private Farmacia farmacia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        bind();

        botaoVoltarCadastroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fecharTelaCadastro();
            }
        });

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(camposPreenchidos()){
                        validarTipoCadastro();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Todos os campos sao obrigatórios!!",Toast.LENGTH_LONG).show();
                    }
                    limparCampos();
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Deu erro",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limparCampos(){
        opcaoMedico.setChecked(false);
        opcaoFarmacia.setChecked(false);
        opcaoPaciente.setChecked(false);
        txtNomeCadastro.setText(null);
        txtCadastro.setText(null);
    }

    private void validarTipoCadastro()throws Exception{
        if(opcaoMedico.isChecked()){
            cadastrarMedico();
        }
        else if(opcaoFarmacia.isChecked()){
           cadastrarFarmacia();
        }
        else{
            Toast.makeText(getApplicationContext(),"Escolha o tipo de cadastro",Toast.LENGTH_LONG).show();
        }
    }

    private boolean camposPreenchidos(){
        return !txtCadastro.getText().toString().trim().equals("")
                && txtCadastro.getText() != null
                && !txtNomeCadastro.getText().toString().trim().equals("")
                && txtNomeCadastro.getText() != null;
    }

    private void cadastrarFarmacia()throws Exception{

        farmacia = new Farmacia();
        farmacia.setCnpj(txtCadastro.getText().toString());
        farmacia.setNome(txtNomeCadastro.getText().toString());

        String msg = new CadastroFarmacia(farmacia).execute().get();
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    private void cadastrarMedico() throws Exception{
        medico = new Medico();
        medico.setCrmMedico(txtCadastro.getText().toString());
        medico.setNmMedico(txtNomeCadastro.getText().toString());

        String msg = new CadastroMedico(medico).execute().get();

        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    private void fecharTelaCadastro(){
        finish();
    }

    private void bind(){
        txtNomeCadastro = (AutoCompleteTextView) findViewById(R.id.txtNomeCadastro);
        txtCadastro = (AutoCompleteTextView) findViewById(R.id.txtCadastro);
        opcaoFarmacia = (RadioButton) findViewById(R.id.opcaoFarmacia);
        opcaoMedico = (RadioButton) findViewById(R.id.opcaoMedico);
        botaoCadastrar = (Button) findViewById(R.id.botaoCadastrar);
        botaoVoltarCadastroUsuario = (Button) findViewById(R.id.botaoVoltarCadastroUsuario);
    }
}
