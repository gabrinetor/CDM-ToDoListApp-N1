package com.example.todolist_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TasksToDoActivity extends AppCompatActivity {

    private EditText editTextTarefa, editTextData;
    private Button btnCadastrar;
    private String acao;
    private Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_to_do);

        acao = getIntent().getExtras().getString("acao");

        editTextTarefa = findViewById(R.id.editTextTarefa);
        editTextData = findViewById(R.id.editTextData);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                salvar();
            }
        });

        if( acao.equals("editar") ) {
            int id = getIntent().getExtras().getInt("idTarefa");
            tarefa = TarefaDAO.getTarefaById(TasksToDoActivity.this, id);
            editTextTarefa.setText(tarefa.getTarefa());
            editTextData.setText(String.valueOf(tarefa.getDataMarcada()));
        }
    }

    private void salvar() {
        if(tarefa == null){
            tarefa = new Tarefa();
        }

        String task = editTextTarefa.getText().toString();

        if (task.isEmpty()) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Atenção!");
            alerta.setMessage("Os campos precisam ser preenchidos");
            alerta.setIcon(android.R.drawable.ic_dialog_alert);
            alerta.setPositiveButton("OK", null);
            alerta.show();
        } else {
            tarefa.setTarefa(task);
            String scheduledDate = editTextData.getText().toString();
            if(scheduledDate.isEmpty()) {
                tarefa.setTarefa( "00/00/0000" );
            } else {
                scheduledDate = scheduledDate.replace("/", ".");
                String dataM = String.valueOf(scheduledDate);
                tarefa.setDataMarcada(dataM);
            }

            if (acao.equals("inserir")) {
                TarefaDAO.insert(this, tarefa);
                tarefa = null;
                editTextTarefa.setText("");
                editTextTarefa.setText("");
            }else{
                TarefaDAO.editar(this, tarefa);
                finish();
            }
        }
    }
}