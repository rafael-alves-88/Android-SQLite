package com.example.rm40300.demolivraria;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rm40300.demolivraria.dao.LivroDAO;
import com.example.rm40300.demolivraria.dao.MeuBD;
import com.example.rm40300.demolivraria.model.Livro;

public class CadastrarActivity extends AppCompatActivity {

    TextInputLayout tlTitulo;
    TextInputLayout tlAutor;
    TextInputLayout tlIsbn;
    TextInputLayout tlEditora;
    TextInputLayout tlDescricao;
    Button btnCadastrar;
    Button btnCancelar;
    boolean erro = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        tlTitulo = (TextInputLayout) findViewById(R.id.tlTitulo);
        tlAutor = (TextInputLayout) findViewById(R.id.tlAutor);
        tlIsbn = (TextInputLayout) findViewById(R.id.tlIsbn);
        tlEditora = (TextInputLayout) findViewById(R.id.tlEditora);
        tlDescricao = (TextInputLayout) findViewById(R.id.tlDescricao);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensagem = cadastrarLivro();
                Toast.makeText(CadastrarActivity.this, mensagem, Toast.LENGTH_SHORT).show();
                if (!erro) {
                    finish();
                }
            }
        });
    }

    private String cadastrarLivro() {
        erro = false;

        Livro livro = new Livro();
        if (tlTitulo.getEditText() != null && tlTitulo.getEditText().getText().length() > 0) {
            livro.setTitulo(tlTitulo.getEditText().getText().toString());
            tlTitulo.setError(null);
        } else {
            tlTitulo.setError("Por favor digite um Título");
            erro = true;
        }
        if (tlAutor.getEditText() != null && tlAutor.getEditText().getText().length() > 0) {
            livro.setAutor(tlAutor.getEditText().getText().toString());
            tlAutor.setError(null);
        } else {
            tlAutor.setError("Por favor digite um Autor");
            erro = true;
        }
        if (tlIsbn.getEditText() != null && tlIsbn.getEditText().getText().length() > 0) {
            livro.setIsbn(tlIsbn.getEditText().getText().toString());
            tlIsbn.setError(null);
        } else {
            tlIsbn.setError("Por favor digite o Isbn");
            erro = true;
        }
        if (tlEditora.getEditText() != null && tlEditora.getEditText().getText().length() > 0) {
            livro.setEditora(tlEditora.getEditText().getText().toString());
            tlEditora.setError(null);
        } else {
            tlEditora.setError("Por favor digite uma Editora");
            erro = true;
        }
        if (tlDescricao.getEditText() != null && tlDescricao.getEditText().getText().length() > 0) {
            livro.setDescricao(tlDescricao.getEditText().getText().toString());
            tlDescricao.setError(null);
        } else {
            tlDescricao.setError("Por favor digite uma Descrição");
            erro = true;
        }

        String mensagem;
        if (!erro) {
            LivroDAO crud = new LivroDAO(this);
            mensagem = crud.inserir(livro);
        } else {
            mensagem = "Verifique os campos";
        }

        return mensagem;
    }
}
