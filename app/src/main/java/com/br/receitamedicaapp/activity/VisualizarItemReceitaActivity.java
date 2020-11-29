package com.br.receitamedicaapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.br.receitamedicaapp.R;
import com.br.receitamedicaapp.enums.StatusItemEnum;
import com.br.receitamedicaapp.listview.VisualizarItemReceitaListView;
import com.br.receitamedicaapp.model.ItemReceita;
import com.br.receitamedicaapp.processo.BuscarItensReceita;

import java.util.ArrayList;
import java.util.List;

public class VisualizarItemReceitaActivity extends AppCompatActivity {

    private int idReceitaParam;
    private ListView visualizarItensListView;
    private List<ItemReceita> itensReceita;
    private Button botaoVoltarVisualizarItemReceita;
    public static int VISUALIZAR_ITEM_RECEITA = 1;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_item_receita);

        bind();
        getIdReceita();

        try {
            popularGridItens();
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(),"Falha ao recuperar Itens",Toast.LENGTH_LONG).show();
        }

        botaoVoltarVisualizarItemReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),FarmaciaActivity.class);
                finish();
            }
        });
    }

    public void popularGridItens() throws Exception{
        buscarItensReceita();
        VisualizarItemReceitaListView adapter = new VisualizarItemReceitaListView(getApplicationContext(),itensReceita);
        visualizarItensListView.setAdapter(adapter);
        visualizarItensListView.setBackgroundColor(Color.TRANSPARENT);

        setResult(RESULT_OK);

    }

    private void buscarItensReceita() throws Exception{
        itensReceita = new BuscarItensReceita(idReceitaParam).execute().get();
       validarStatusReceita();
    }

    private void validarStatusReceita(){
        String statusReceita = (String) getIntent().getExtras().get("statusReceita");
        if(!statusReceita.equals("")){
            List<ItemReceita> itensComNovoStatus = new ArrayList<>();

            for(ItemReceita item : itensReceita){
                item.setStatus(StatusItemEnum.VENDIDO.getValor());
                itensComNovoStatus.add(item);
            }
            itensReceita = itensComNovoStatus;
        }
    }

    private void getIdReceita(){
        idReceitaParam = (int) getIntent().getExtras().get("idReceita");
    }

    private void bind(){
        visualizarItensListView = (ListView) findViewById(R.id.visualizarItensListView);
        botaoVoltarVisualizarItemReceita = (Button) findViewById(R.id.botaoVoltarVisualizarItemReceita);
    }
}
