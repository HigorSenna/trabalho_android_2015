package com.br.receitamedicaapp.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.br.receitamedicaapp.R;
import com.br.receitamedicaapp.model.ItemReceita;

import java.util.List;

/**
 * Created by Higor Senna on 20/11/2016.
 */
public class ItemReceitaListView extends BaseAdapter {

    private LayoutInflater inflater;
    private List<ItemReceita> itensReceita;

    public ItemReceitaListView(Context context, List<ItemReceita> itensReceita) {
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
            convertView = inflater.inflate(R.layout.item_list_view,null);
            Button botaoRemove = (Button) convertView.findViewById(R.id.botaoRemoveItem);
            TextView textMed = (TextView) convertView.findViewById(R.id.txtNomeMedicamento);

            convertView.setTag(R.id.txtNomeMedicamento,textMed);
            convertView.setTag(R.id.botaoRemoveItem,botaoRemove);
        }
        Button botaoRemover =(Button) convertView.getTag(R.id.botaoRemoveItem);

        botaoRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itensReceita.remove(position);
                notifyDataSetChanged();
            }
        });

        ItemReceita item = (ItemReceita) getItem(position);

        TextView texto = (TextView) convertView.getTag(R.id.txtNomeMedicamento);
        texto.setText("Nome: " + item.getNmReceita().toString());
        return convertView;
    }
}
