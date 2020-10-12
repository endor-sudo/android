package com.example.crueladeville;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFormandoActivity extends AppCompatActivity{

    DataBaseHelper appDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_formando);
        setTitle("Registar NOVO Formando");

        appDB=new DataBaseHelper(this);
    }

    public void RegisterFormandoBtns(View view){
        switch (view.getId()){
            case R.id.button7:
                RegisterFormando();
                break;
            case R.id.button8:
                BackToMainActivity(view);
                break;
        }
    }

    public void RegisterFormando(){
        EditText editTextNumero = findViewById(R.id.registerformandonumero);
        EditText editTextNome = findViewById(R.id.registerformandonome);
        EditText editTextTelefone = findViewById(R.id.registerformandotelefone);
        EditText editTextIdade = findViewById(R.id.registerformandoidade);
        EditText editTextEmail = findViewById(R.id.registerformandoemail);

        String numero = editTextNumero.getText().toString();
        String nome = editTextNome.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String idade = editTextIdade.getText().toString();
        String email = editTextEmail.getText().toString();

        if (appDB.InserirFormando(numero, nome, telefone, idade, email) == true)
        {
            Toast.makeText(this, R.string.UserInserted, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, R.string.UserInsertedError, Toast.LENGTH_SHORT).show();
        }
    }

    public void BackToMainActivity(View view){
        Intent intent=new Intent(this, ListaDeFormandosActivity.class);
        startActivity(intent);
        finish();
    }
}
