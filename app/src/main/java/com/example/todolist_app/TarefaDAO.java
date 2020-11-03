package com.example.todolist_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

// Objeto de Acesso aos Dados de Tarefas
public class TarefaDAO {

    public static void insert(Context contexto, Tarefa tarefa) {

        //estancia objeto da classe para criar valores
        ContentValues valor = new ContentValues();
        //aplicar novo registro de tarefa
        valor.put("tarefa", tarefa.getTarefa());
        //aplicar novo registro de data
        valor.put("dataMarcada", tarefa.getDataMarcada());

        //estancia objeto da classe que permite a conexão com o banco SQLite.
        ConexaoBanco banco = new ConexaoBanco(contexto);
        //objeto estanciado que obtém os dados graváveis
        SQLiteDatabase db = banco.getWritableDatabase();

        //inserir na tabela tarefas
        db.insert("tarefas", null, valor);

    }

    public static void editar(Context contexto, Tarefa tarefa) {

        //estancia objeto da classe para criar valores
        ContentValues valor = new ContentValues();
        //aplicar edição tarefa
        valor.put( "tarefa", tarefa.getTarefa() );
        //aplicar edição data
        valor.put( "dataMarcada", tarefa.getDataMarcada() );

        //estancia objeto da classe que permite a conexão com o banco SQLite.
        ConexaoBanco banco = new ConexaoBanco(contexto);
        //objeto estanciado que obtém os dados graváveis
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("tarefas", valor, " id = " + tarefa.getId(), null );

    }

    public static void excluir(Context contexto, int idTarefa) {

        //estancia objeto da classe que permite a conexão com o banco SQLite.
        ConexaoBanco banco = new ConexaoBanco(contexto);
        //objeto estanciado que obtém os dados graváveis
        SQLiteDatabase db = banco.getWritableDatabase();

        //metodo do SQLite para deletar
        db.delete( "tarefas", " id = " + idTarefa, null );

    }

    public static List<Tarefa> getTarefas(Context contexto) {

        //criar estância de lista array
        List<Tarefa> lista = new ArrayList<>();
        //estancia objeto da classe que permite a conexão com o banco SQLite.
        ConexaoBanco banco = new ConexaoBanco(contexto);
        //objeto estanciado que obtém os dados legíveis para que seja fácil ler dentro da lista
        SQLiteDatabase db = banco.getReadableDatabase();
        //cursor para listar as tarefas na tabela
        Cursor cursor = db.rawQuery("SELECT * FROM tarefas ORDER BY tarefa", null);

        //se tiver registro, será incluído em todas as colunas até que vá para o próximo cursor
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                Tarefa t = new Tarefa();
                t.setId(cursor.getInt(0));
                t.setTarefa(cursor.getString(1));
                t.setDataMarcada(cursor.getString(2));
                lista.add(t);
            } while (cursor.moveToNext());
        }
        return lista; //retorna a lista que é o tipo do método

    }

    public static Tarefa getTarefaById(Context contexto, int idTarefa) {

        ConexaoBanco banco = new ConexaoBanco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        //cursor para listar as tarefas na tabela
        Cursor cursor = db.rawQuery("SELECT * FROM tarefas WHERE id = " + idTarefa , null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            Tarefa t = new Tarefa();
            t.setId(cursor.getInt(0));
            t.setTarefa(cursor.getString(1));
            t.setDataMarcada(cursor.getString(2));

            return t;
        }else{
            return null;
        }

    }

}