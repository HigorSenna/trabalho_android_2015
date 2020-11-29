package com.br.receitamedicaapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.br.receitamedicaapp.R;
import com.br.receitamedicaapp.enums.StatusReceitaEnum;
import com.br.receitamedicaapp.model.ReceitaMedica;
import com.br.receitamedicaapp.model.ReciboReceita;
import com.br.receitamedicaapp.processo.BuscarReceitaPorNumero;
import com.br.receitamedicaapp.processo.CancelarReceitaMedica;
import com.br.receitamedicaapp.utils.DataUtils;

import org.w3c.dom.Text;

public class VisualizarReceitaActivity extends AppCompatActivity {

    private Button botaoVoltarVisualizarReceita,botaoCancelarReceita;
    private TextView txtNumeroReceitaVisualizar,txtDataReceitaVisualizar,txtStatusReceitaVisualizar;
    private Intent intent;

    private ReceitaMedica receita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_receita);

        bind();
        getReceitaParamIntent();
        preencherTelaReceita();

        botaoVoltarVisualizarReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),ConsultaReceitasPacienteActivity.class);
                finish();
            }
        });

        botaoCancelarReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cancelarReceita();
                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(),"Falha ao cancelar receita",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void cancelarReceita() throws Exception{
        String msg = new CancelarReceitaMedica(receita.getNumReceita()).execute().get();


        if(msg.contains("sucesso")){
            atualizarReceitaNaView();
        }

        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    private void atualizarReceitaNaView() throws Exception{
        int numeroReceita = receita.getNumReceita();
        receita = new BuscarReceitaPorNumero(numeroReceita).execute().get();
        preencherTelaReceita();

        setResult(RESULT_OK);
    }

    private void preencherTelaReceita(){
        txtNumeroReceitaVisualizar.setText(String.valueOf(receita.getNumReceita()));
        txtDataReceitaVisualizar.setText(DataUtils.convertDateToStringFormat(receita.getData()));

        validarStatusReceita();

    }

    private void validarStatusReceita(){
        if(receita.getFlStatus().equals(StatusReceitaEnum.ATIVA.getValor())){
            txtStatusReceitaVisualizar.setTextColor(Color.GREEN);
            txtStatusReceitaVisualizar.setText("Ativa");
        }
        else if(receita.getFlStatus().equals(StatusReceitaEnum.CANCELADA.getValor())){
            txtStatusReceitaVisualizar.setTextColor(Color.RED);
            txtStatusReceitaVisualizar.setText("Cancelada");

        }
        else{
            txtStatusReceitaVisualizar.setTextColor(Color.GREEN);
            txtStatusReceitaVisualizar.setText("Utilizada");
        }
    }

    private void getReceitaParamIntent(){
        receita = (ReceitaMedica) getIntent().getExtras().get("receita");
    }

    private void bind(){
        txtNumeroReceitaVisualizar = (TextView) findViewById(R.id.txtNumeroReceitaVisualizar);
        txtDataReceitaVisualizar = (TextView) findViewById(R.id.txtDataReceitaVisualizar);
        txtStatusReceitaVisualizar = (TextView) findViewById(R.id.txtStatusReceitaVisualizar);
        botaoCancelarReceita = (Button) findViewById(R.id.botaoCancelarReceita);
        botaoVoltarVisualizarReceita = (Button) findViewById(R.id.botaoVoltarVisualizarReceita);
    }
}
