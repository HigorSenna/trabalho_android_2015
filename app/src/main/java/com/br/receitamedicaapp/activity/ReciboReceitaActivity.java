package com.br.receitamedicaapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.br.receitamedicaapp.R;
import com.br.receitamedicaapp.model.ReciboReceita;

public class ReciboReceitaActivity extends AppCompatActivity {

    private TextView txtNumReceitaRecibo
            ,txtNumCrmRecibo
            ,txtNumCpfRecibo
            ,txtDataRecibo
            ,txtAvisoRecibo;

    private Button botaoVoltarRecibo;
    private ReciboReceita recibo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_receita);

        bind();
        getObjectParamIntent();
        montarRecibo();

        botaoVoltarRecibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void getObjectParamIntent(){
        recibo = (ReciboReceita) getIntent().getExtras().get("recibo");
    }
    private void montarRecibo(){
        txtNumReceitaRecibo.setText(String.valueOf(recibo.getNumReceita()));
        txtNumCpfRecibo.setText(recibo.getCpfPaciente());
        txtAvisoRecibo.setText(recibo.getMsg());
        txtDataRecibo.setText(recibo.getData());
        txtNumCrmRecibo.setText(recibo.getCrmMedico());
    }

    private void bind(){
        txtNumReceitaRecibo = (TextView) findViewById(R.id.txtNumReceitaRecibo);
        txtNumCrmRecibo = (TextView) findViewById(R.id.txtNumCrmRecibo);
        txtNumCpfRecibo = (TextView) findViewById(R.id.txtNumCpfRecibo);
        txtDataRecibo = (TextView) findViewById(R.id.txtDataRecibo);
        txtAvisoRecibo = (TextView) findViewById(R.id.txtAvisoRecibo);
        botaoVoltarRecibo = (Button) findViewById(R.id.botaoVoltarRecibo);
    }
}
