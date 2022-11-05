package br.com.fatec.selecaosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.fatec.selecaosqlite.dao.SelecaoDao;
import br.com.fatec.selecaosqlite.model.Selecao;

public class InclusaoActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextTitulos;
    private Spinner spinnerContinente;
    private Button buttonIncluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inclusao);

        this.editTextNome = findViewById(R.id.editTextNome);
        this.editTextTitulos = findViewById(R.id.editTextTitulos);
        this.spinnerContinente = findViewById(R.id.editTextContinente);
        this.buttonIncluir = findViewById(R.id.buttonIncluir);

        carregarContinentes();

        buttonIncluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelecaoDao selecaoDao = new SelecaoDao(InclusaoActivity.this);
                Selecao selecao = new Selecao(
                        editTextNome.getText().toString(),
                        Integer.parseInt(editTextTitulos.getText().toString()),
                        spinnerContinente.getSelectedItem().toString());

                long id = selecaoDao.insert(selecao);
                if(id > 0){
                    Toast.makeText(InclusaoActivity.this, "Incluído", Toast.LENGTH_SHORT).show();
                    InclusaoActivity.this.finish(); // fecha a tela atual
                } else {
                    Toast.makeText(InclusaoActivity.this, "Algo deu errado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void carregarContinentes() {
        String[] continentes = {"Europa", "Ásia", "África", "América", "Oceania"};
        ArrayAdapter adapter = new ArrayAdapter(InclusaoActivity.this, android.R.layout.simple_spinner_dropdown_item, continentes);
        spinnerContinente.setAdapter(adapter);
    }
}