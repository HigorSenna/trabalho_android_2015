package com.br.receitamedicaapp.listview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.br.receitamedicaapp.R;
import com.br.receitamedicaapp.enums.StatusReceitaEnum;
import com.br.receitamedicaapp.model.ItemReceita;
import com.br.receitamedicaapp.model.ReceitaMedica;

import java.util.List;

/**
 * Created by Higor Senna on 23/11/2016.
 */
public class ReceitaListView extends BaseAdapter {

    private LayoutInflater inflater;
    private List<ReceitaMedica> receitasMedicas;

    public ReceitaListView(Context context, List<ReceitaMedica> receitasMedicas) {
        this.receitasMedicas = receitasMedicas;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.receitasMedicas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.receitasMedicas.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_view_receitas,null);

            TextView nomeReceita = (TextView) convertView.findViewById(R.id.txtNomeReceitaView);
            TextView numReceita = (TextView) convertView.findViewById(R.id.txtNumeroReceitaView);

            convertView.setTag(R.id.txtNomeReceitaView,nomeReceita);
            convertView.setTag(R.id.txtNumeroReceitaView,numReceita);

        }

        ReceitaMedica receita = (ReceitaMedica) getItem(position);

        TextView textoNumero = (TextView) convertView.getTag(R.id.txtNumeroReceitaView);
        textoNumero.setText("Numero: " + receita.getNumReceita());
        TextView textNome = (TextView) convertView.getTag(R.id.txtNomeReceitaView);
        textNome.setText(validarStatusReceita(receita));
        textNome.setTextColor(retornarCorTexto(receita));
        return convertView;
    }

    private String validarStatusReceita(ReceitaMedica receita){
        if(receita.getFlStatus().equals(StatusReceitaEnum.ATIVA.getValor())){
            return StatusReceitaEnum.ATIVA.getLabel();
        }
        else if(receita.getFlStatus().equals(StatusReceitaEnum.UTILIZADA.getValor())){
            return StatusReceitaEnum.UTILIZADA.getLabel();
        }
        else{
            return StatusReceitaEnum.CANCELADA.getLabel();
        }
    }
    private int retornarCorTexto(ReceitaMedica receita){
        if(receita.getFlStatus().equals(StatusReceitaEnum.ATIVA.getValor())){
            return Color.GREEN;
        }
        else{
            return Color.RED;
        }
    }
}
