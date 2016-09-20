package com.example.rm40300.demolivraria;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rm40300.demolivraria.adapter.LivroAdapter;
import com.example.rm40300.demolivraria.dao.LivroDAO;

public class ListaActivity extends AppCompatActivity {

    private ListView lvLivros;
    Toolbar toolbar;
    Button btnAtualizarLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvLivros = (ListView) findViewById(R.id.lvLivros);
        atualizarLista();

        btnAtualizarLista = (Button) findViewById(R.id.btnAtualizarLista);
        btnAtualizarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarLista();
            }
        });
    }

    public void atualizarLista() {
        LivroDAO crud = new LivroDAO(this);
        lvLivros.setAdapter(new LivroAdapter(this, crud.getLivros()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_activity, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        Intent intent = null;

        switch (id) {
            case R.id.mn_cadastrar:
                intent = new Intent(ListaActivity.this, CadastrarActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }

        return super.onOptionsItemSelected(menuItem);
    }
}
