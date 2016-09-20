package com.example.rm40300.demolivraria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class LoginActivity extends AppCompatActivity {

    private final String PREF_NAME = "LIVRARIA";
    private final String MANTER_CONECTADO = "MANTER_CONECTADO";

    private TextInputLayout tlNome;
    private TextInputLayout tlSenha;
    private CheckBox cbManterConectado;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (isLogado()) {
            iniciarApp();
        }

        tlNome = (TextInputLayout) findViewById(R.id.tlNome);
        tlSenha = (TextInputLayout) findViewById(R.id.tlSenha);
        cbManterConectado = (CheckBox) findViewById(R.id.cbManterConectado);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usuarioValido()) {
                    manterConectado();
                    iniciarApp();
                } else {
                    tlSenha.setError("Usuário ou senha inválido");
                }
            }
        });
    }

    private void iniciarApp() {
        Intent intent = new Intent(LoginActivity.this, ListaActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isLogado() {
        SharedPreferences settings = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        return settings.getBoolean(MANTER_CONECTADO, false);
    }

    private void manterConectado() {
        SharedPreferences settings = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(MANTER_CONECTADO, cbManterConectado.isChecked());
        editor.commit();
    }

    private boolean usuarioValido() {
        boolean valido = false;

        String usuario = tlNome.getEditText().getText().toString();
        String senha = tlSenha.getEditText().getText().toString();

        if (usuario.equals("admin") && senha.equals("123")) {
            valido = true;
        }

        return valido;
    }
}
