package com.example.rm40300.demolivraria.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rm40300.demolivraria.model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public static final String ID = "_id";
    public static final String TITULO = "titulo";
    public static final String AUTOR = "autor";
    public static final String ISBN = "isbn";
    public static final String EDITORA = "editora";
    public static final String DESCRICAO = "descricao";

    private MeuBD meuBD;
    private SQLiteDatabase db;

    public LivroDAO(Context context) {
        meuBD = new MeuBD(context);
    }

    public String inserir(Livro livro) {
        ContentValues valores = new ContentValues();
        valores.put(TITULO, livro.getTitulo());
        valores.put(AUTOR, livro.getAutor());
        valores.put(ISBN, livro.getIsbn());
        valores.put(EDITORA, livro.getEditora());
        valores.put(DESCRICAO, livro.getDescricao());

        db = meuBD.getWritableDatabase();

        long resultado = db.insert(MeuBD.TABELA_LIVRO, null, valores);
        db.close();

        if (resultado == -1) {
            return "Erro ao cadastrar o livro";
        } else {
            return "Livro cadastrado com sucesso";
        }
    }

    public List<Livro> getLivros() {
        List<Livro> livros = new ArrayList<>();

        db = meuBD.getReadableDatabase();

        Cursor cursor = db.query(MeuBD.TABELA_LIVRO, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Livro livro = new Livro();
                livro.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                livro.setTitulo(cursor.getString(cursor.getColumnIndex(TITULO)));
                livro.setAutor(cursor.getString(cursor.getColumnIndex(AUTOR)));
                livro.setIsbn(cursor.getString(cursor.getColumnIndex(ISBN)));
                livro.setEditora(cursor.getString(cursor.getColumnIndex(EDITORA)));
                livro.setDescricao(cursor.getString(cursor.getColumnIndex(DESCRICAO)));

                livros.add(livro);
            }
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return livros;
    }

    public int deletar(int id) {
        db = meuBD.getWritableDatabase();

        return db.delete(MeuBD.TABELA_LIVRO, ID + "=" + id, null);
    }

    public Livro getLivroPorId(int id) {
        db = meuBD.getReadableDatabase();

        Livro livro = new Livro();

        Cursor cursor = db.rawQuery("SELECT * FROM " + MeuBD.TABELA_LIVRO + " WHERE " + ID + "=" + id, null);
        if (cursor.moveToFirst()) {
            livro.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            livro.setTitulo(cursor.getString(cursor.getColumnIndex(TITULO)));
            livro.setAutor(cursor.getString(cursor.getColumnIndex(AUTOR)));
            livro.setIsbn(cursor.getString(cursor.getColumnIndex(ISBN)));
            livro.setEditora(cursor.getString(cursor.getColumnIndex(EDITORA)));
            livro.setDescricao(cursor.getString(cursor.getColumnIndex(DESCRICAO)));
        } else {
            livro = null;
        }

        cursor.close();

        return livro;
    }

    public int atualizarLivro(Livro livro) {
        ContentValues valores = new ContentValues();
        valores.put(TITULO, livro.getTitulo());
        valores.put(AUTOR, livro.getAutor());
        valores.put(ISBN, livro.getIsbn());
        valores.put(EDITORA, livro.getEditora());
        valores.put(DESCRICAO, livro.getDescricao());

        db = meuBD.getWritableDatabase();
        return db.update(MeuBD.TABELA_LIVRO, valores, ID + "=" + livro.getId(), null);
    }
}
