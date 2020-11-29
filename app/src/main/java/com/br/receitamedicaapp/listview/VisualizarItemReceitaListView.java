package com.br.receitamedicaapp.listview;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.receitamedicaapp.R;
import com.br.receitamedicaapp.activity.VisualizarItemReceitaActivity;
import com.br.receitamedicaapp.enums.StatusItemEnum;
import com.br.receitamedicaapp.model.ItemReceita;
import com.br.receitamedicaapp.model.ReceitaMedica;
import com.br.receitamedicaapp.processo.VenderItemReceita;

import java.util.List;

/**
 * Created by Higor Senna on 26/11/2016.
 */
public class VisualizarItemReceitaListView  extends BaseAdapter {

    private LayoutInflater inflater;
    private List<ItemReceita> itensReceita;

    public VisualizarItemReceitaListView(Context context, List<ItemReceita> itensReceita) {
        this.itensReceita = itensReceita;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.itensReceita.size();
    }

    @Override
    public Object getItem(int position) {
        return this.itensReceita.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.visualizar_itens_list_view,null);

            TextView txtNomeMedicamento = (TextView) convertView.findViewById(R.id.txtNomeMedicamento);
            TextView txtStatusMedicamento = (TextView) convertView.findViewById(R.id.txtStatusMedicamento);
            Button botaoVenderMedicamento = (Button) convertView.findViewById(R.id.botaoVenderMedicamento);

            convertView.setTag(R.id.txtStatusMedicamento,txtStatusMedicamento);
            convertView.setTag(R.id.txtNomeMedicamento,txtNomeMedicamento);
            convertView.setTag(R.id.botaoVenderMedicamento,botaoVenderMedicamento);
        }

        final ItemReceita itemReceita = (ItemReceita) getItem(position);
        setarTextos(itemReceita,convertView);
        final Button botaoVenderMedicamento = (Button) convertView.findViewById(R.id.botaoVenderMedicamento);
        botaoVenderMedicamento.setEnabled(validarHabilitacaoDoBotaoVender(itemReceita));

        botaoVenderMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String msg = new VenderItemReceita(itemReceita.getIdItem()).execute().get();
                    Toast.makeText(view.getContext(),msg,Toast.LENGTH_LONG).show();
                    notifyDataSetChanged();
                }
                catch (Exception ex){
                    Toast.makeText(view.getContext(),"Erro ao vender",Toast.LENGTH_LONG).show();
                }
            }
        });

        return convertView;
    }

    private boolean validarHabilitacaoDoBotaoVender(ItemReceita item){
        return item.getStatus().equals(StatusItemEnum.ATIVO.getValor());
    }

    private void setarTextos(ItemReceita itemReceita,View convertView){

        TextView txtNomeMedicamento = (TextView) convertView.getTag(R.id.txtNomeMedicamento);
        txtNomeMedicamento.setText(itemReceita.getNmReceita());

        TextView txtStatusMedicamento = (TextView) convertView.getTag(R.id.txtStatusMedicamento);

        txtStatusMedicamento.setText(retornarStatusItemReceita(itemReceita));
        txtStatusMedicamento.setTextColor(retornarCor(itemReceita));
    }

    private String retornarStatusItemReceita(ItemReceita item){
        if(item.getStatus().equals(StatusItemEnum.ATIVO.getValor())){
            return StatusItemEnum.ATIVO.getLabel();
        }
        else{
            return StatusItemEnum.VENDIDO.getLabel();
        }
    }

    private int retornarCor(ItemReceita item){
        if(item.getStatus().equals(StatusItemEnum.ATIVO.getValor())){
            return Color.GREEN;
        }
        else{
           return Color.RED;
        }
    }
}
