package com.example.rm40300.demolivraria;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rm40300.demolivraria.dao.LivroDAO;
import com.example.rm40300.demolivraria.model.Livro;

public class VisualizaActivity extends AppCompatActivity {

    TextInputLayout tlTitulo;
    TextInputLayout tlAutor;
    TextInputLayout tlIsbn;
    TextInputLayout tlEditora;
    TextInputLayout tlDescricao;
    Button btnAtualizar;
    Button btnApagar;
    Button btnCancelar;

    private int livroId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiza);

        Bundle bundle = getIntent().getExtras();
        livroId = bundle.getInt(LivroDAO.ID);

        tlTitulo = (TextInputLayout) findViewById(R.id.tlTitulo);
        tlAutor = (TextInputLayout) findViewById(R.id.tlAutor);
        tlIsbn = (TextInputLayout) findViewById(R.id.tlIsbn);
        tlEditora = (TextInputLayout) findViewById(R.id.tlEditora);
        tlDescricao = (TextInputLayout) findViewById(R.id.tlDescricao);
        btnAtualizar = (Button) findViewById(R.id.btnAtualizar);
        btnApagar = (Button) findViewById(R.id.btnApagar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (atualizarLivro() > 0) {
                    Toast.makeText(VisualizaActivity.this, "Atualizado com sucesso", Toast.LENGTH_LONG).show();
                    fecharTela();
                } else {
                    Toast.makeText(VisualizaActivity.this, "Erro ao tentar atualizar", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (apagarLivro() > 0) {
                    Toast.makeText(VisualizaActivity.this, "Apagado com sucesso", Toast.LENGTH_LONG).show();
                    fecharTela();
                } else {
                    Toast.makeText(VisualizaActivity.this, "Erro ao tentar apagar", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecharTela();
            }
        });

        getLivroPorId(livroId);
    }

    private Livro getLivroPorId(int id) {
        LivroDAO crud = new LivroDAO(this);
        Livro livro = crud.getLivroPorId(id);

        tlTitulo.getEditText().setText(livro.getTitulo());
        tlAutor.getEditText().setText(livro.getAutor());
        tlIsbn.getEditText().setText(livro.getIsbn());
        tlEditora.getEditText().setText(livro.getEditora());
        tlDescricao.getEditText().setText(livro.getDescricao());

        return livro;
    }

    private int atualizarLivro() {
        Livro livro = new Livro();
        livro.setId(livroId);
        livro.setTitulo(tlTitulo.getEditText().getText().toString());
        livro.setAutor(tlAutor.getEditText().getText().toString());
        livro.setIsbn(tlIsbn.getEditText().getText().toString());
        livro.setEditora(tlEditora.getEditText().getText().toString());
        livro.setDescricao(tlDescricao.getEditText().getText().toString());

        LivroDAO crud = new LivroDAO(this);

        return crud.atualizarLivro(livro);
    }

    private int apagarLivro() {
        LivroDAO crud = new LivroDAO(this);

        return crud.deletar(livroId);
    }

    private void fecharTela() {
        finish();
    }
}
