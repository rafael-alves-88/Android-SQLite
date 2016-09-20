package com.example.rm40300.demolivraria.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rm40300.demolivraria.R;
import com.example.rm40300.demolivraria.VisualizaActivity;
import com.example.rm40300.demolivraria.dao.LivroDAO;
import com.example.rm40300.demolivraria.model.Livro;

import java.util.List;

public class LivroAdapter extends BaseAdapter {

    private Context context;
    private List<Livro> livros;

    public LivroAdapter(Context context, List<Livro> livros) {
        this.context = context;
        this.livros = livros;
    }

    @Override
    public int getCount() {
        return livros.size();
    }

    @Override
    public Object getItem(int position) {
        return livros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return livros.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_livro, parent, false);
            holder.tvTituloLivro = (TextView) convertView.findViewById(R.id.tvTituloLivro);
            holder.tvAutorLivro = (TextView) convertView.findViewById(R.id.tvAutorLivro);
            holder.tvIsbnLivro = (TextView) convertView.findViewById(R.id.tvIsbnLivro);
            holder.tvEditoraLivro = (TextView) convertView.findViewById(R.id.tvEditoraLivro);
            holder.tvDescricaoLivro = (TextView) convertView.findViewById(R.id.tvDescricaoLivro);
            holder.ivOpcoes = (ImageView) convertView.findViewById(R.id.ivOpcoes);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTituloLivro.setText(livros.get(position).getTitulo());
        holder.tvTituloLivro.setTag(livros.get(position).getId());
        holder.tvAutorLivro.setText(String.format("Autor: %s", livros.get(position).getAutor()));
        holder.tvIsbnLivro.setText(String.format("Isbn: %s", livros.get(position).getIsbn()));
        holder.tvEditoraLivro.setText(String.format("Editora:%s", livros.get(position).getEditora()));
        holder.tvDescricaoLivro.setText(livros.get(position).getDescricao());

        holder.ivOpcoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VisualizaActivity.class);
                intent.putExtra(LivroDAO.ID, livros.get(position).getId());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private class ViewHolder {
        protected TextView tvTituloLivro;
        protected TextView tvAutorLivro;
        protected TextView tvIsbnLivro;
        protected TextView tvEditoraLivro;
        protected TextView tvDescricaoLivro;
        protected ImageView ivOpcoes;
    }
}
