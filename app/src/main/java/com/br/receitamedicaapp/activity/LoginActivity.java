package com.br.receitamedicaapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.br.receitamedicaapp.R;
import com.br.receitamedicaapp.enums.TipoUsuarioEnum;
import com.br.receitamedicaapp.model.Farmacia;
import com.br.receitamedicaapp.model.Usuario;
import com.br.receitamedicaapp.processo.BuscarUsuarioLoginSenha;
import com.br.receitamedicaapp.utils.SessionUtils;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView txtLogin;
    private EditText txtSenha;
    private Button botaoLogar,botaoRegistrar;
        private Intent intent;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bind();
        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCadastroUsuario();
            }
        });
        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(txtLogin.getText() != null && !txtLogin.getText().toString().trim().equals("") &&
                    txtSenha.getText() != null && !txtSenha.getText().toString().trim().equals("")){
                        validarLogin();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Por favor informe o login e a senha",Toast.LENGTH_LONG).show();
                    }

                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Falha ao recuperar usuario",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void validarLogin() throws Exception{
        usuario = new BuscarUsuarioLoginSenha(txtLogin.getText().toString(),txtSenha.getText().toString()).execute().get();

        if(usuario != null){
            SessionUtils.setUsuarioSession(usuario);
            validarTela();
        }
        else{
            txtLogin.setText("");
            txtSenha.setText("");
            Toast.makeText(getApplicationContext(),"Usuario nao encontrado",Toast.LENGTH_LONG).show();
        }
    }

    private void validarTela(){
        if(usuario.getFlTipoUsuario().equals(TipoUsuarioEnum.MEDICO.getValor())){
            goToTelaMedico();
        }
        else if(usuario.getFlTipoUsuario().equals(TipoUsuarioEnum.FARMACIA.getValor())){
            goToTelaFarmacia();
        }
        else{
            Toast.makeText(getApplicationContext(),"Pacientes nao tem acesso ao sistema",Toast.LENGTH_LONG).show();
        }
    }

    private void goToTelaFarmacia(){
        intent = new Intent(getApplicationContext(), FarmaciaActivity.class);
        startActivity(intent);
    }

    private void goToTelaMedico(){
        intent = new Intent(getApplicationContext(),MedicoActivity.class);
        startActivity(intent);
    }

    private void goToCadastroUsuario(){
        intent = new Intent(getApplicationContext(),CadastroActivity.class);
        startActivity(intent);
    }

    private void bind(){
        txtLogin = (AutoCompleteTextView) findViewById(R.id.txtLogin);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        botaoLogar = (Button) findViewById(R.id.botaoLogar);
        botaoRegistrar = (Button) findViewById(R.id.botaoRegistrar);
    }
}
