package com.example.rm40300.demolivraria.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MeuBD extends SQLiteOpenHelper {

    public static final String TABELA_LIVRO = "livro";
    public static final String NOME_BANCO = "livraria1.db";
    public static final int VERSAO_BANCO = 1;

    public MeuBD(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);

        //save to sd_card
        //remember to ask for permissions in runtime for storage
        /*super(context, Environment.getExternalStorageDirectory()
                + File.separator + FILE_NAME
                + File.separator + DB_NAME, null, DB_VERSION);*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ");
        sql.append(TABELA_LIVRO);
        sql.append("(");
        sql.append(LivroDAO.ID);
        sql.append(" integer primary key autoincrement, ");
        sql.append(LivroDAO.TITULO);
        sql.append(" text, ");
        sql.append(LivroDAO.AUTOR);
        sql.append(" text, ");
        sql.append(LivroDAO.ISBN);
        sql.append(" text, ");
        sql.append(LivroDAO.EDITORA);
        sql.append(" text, ");
        sql.append(LivroDAO.DESCRICAO);
        sql.append(" text ");
        sql.append(")");

        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_LIVRO);
        onCreate(db);
    }
}
