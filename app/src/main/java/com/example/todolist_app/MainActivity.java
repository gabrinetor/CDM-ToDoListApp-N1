package com.example.todolist_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvTarefas = findViewById(R.id.listviewTarefas);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //            .setAction("Action", null).show();

                Intent intencao = new Intent(MainActivity.this, TasksToDoActivity.class);
                intencao.putExtra("acao", "inserir");
                startActivity(intencao);
            }
        });

        carregarTarefas();

        lvTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tarefa tarefaSelecionada = (Tarefa) adapterView.getItemAtPosition(i);

                Intent intencao = new Intent(MainActivity.this, TasksToDoActivity.class);
                intencao.putExtra("acao", "editar");
                intencao.putExtra("idTarefa", tarefaSelecionada.getId());
                startActivity(intencao);
            }
        });

        lvTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tarefa tarefaSelecionada = (Tarefa) adapterView.getItemAtPosition(i);
                excluir(tarefaSelecionada);
                return true;
            }
        });

    }

    private void excluir(final Tarefa tassk){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Tarefa");
        alerta.setMessage("Confirma a exclusão da tarefa " + tassk.getTarefa() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TarefaDAO.excluir(MainActivity.this, tassk.getId());
                carregarTarefas();
            }
        });
        alerta.show();
    }

    private void carregarTarefas() {
        List<Tarefa> lista = TarefaDAO.getTarefas(this);
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lvTarefas.setAdapter(adaptador);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarTarefas();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}