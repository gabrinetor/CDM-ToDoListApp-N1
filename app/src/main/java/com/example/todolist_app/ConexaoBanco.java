package com.example.todolist_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoBanco extends SQLiteOpenHelper {

    private static final String NOME_DO_BANCO = "listaTarefas";
    private static final int VERSAO_DO_BANCO = 1;

    public ConexaoBanco(Context contexto) {
        super(contexto, NOME_DO_BANCO, null, VERSAO_DO_BANCO);  //parâmetros(context object, nome_do_banco_sqlite, factory, versão_do_banco)
        SQLiteDatabase bd = this.getWritableDatabase();
    }

    //sobrescritas dos métodos específicos (de criar banco e de alterar banco) da superclasse SQLiteOpenHelper
    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(" CREATE TABLE IF NOT EXISTS tarefas (" +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                " tarefa TEXT NOT NULL , " +
                " dataMarcada TEXT ) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        bd.execSQL( "DROP TABLE IF EXISTS tarefas" );
        onCreate(bd);
    }
}
