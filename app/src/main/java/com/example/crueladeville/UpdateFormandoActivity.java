package com.example.crueladeville;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateFormandoActivity extends AppCompatActivity {

    DataBaseHelper myDB;

    EditText editTextNumero, editTextNome, editTextTelefone, editTextIdade, editTextEmail;
    String DatabaseTableItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateformando);
        setTitle("EDITAR Informação Formando");

        editTextNumero = findViewById(R.id.editTextNumero);
        editTextNome = findViewById(R.id.editTextNome);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextIdade = findViewById(R.id.editTextIdade);
        editTextEmail = findViewById(R.id.editTextEmail);

        myDB = new DataBaseHelper(this);

        Intent intent = getIntent();

        DatabaseTableItemId = intent.getStringExtra("ContactsSelectedId");

        Formando contact = myDB.getFormandobyId(DatabaseTableItemId);

        editTextNumero.setText(contact.getNumero());
        editTextNome.setText(contact.getNome());
        editTextTelefone.setText(contact.getTelefone());
        editTextIdade.setText(contact.getIdade());
        editTextEmail.setText(contact.getEmail());
    }

    public void UpdateBtns(View view){
        switch (view.getId()){
            case R.id.button9:
                UpdateFormandoEntry();
                break;
            case R.id.button10:
                DeleteFormandoEntry();
                break;
            case R.id.button11:
                BackToLista();
                break;
            case R.id.button12:
                ViewFormandoEntry();
                break;

        }
    }

    public void UpdateFormandoEntry(){
        String Numero = editTextNumero.getText().toString();
        String Nome = editTextNome.getText().toString();
        String Telefone = editTextTelefone.getText().toString();
        String Idade = editTextIdade.getText().toString();
        String Email = editTextEmail.getText().toString();

        boolean isUpdated = myDB.UpdateFormando(DatabaseTableItemId, Numero, Nome, Telefone, Idade, Email);

        if (isUpdated == true)
            Toast.makeText(this, "Formando Actualizado com SUCESSO", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "ERRO na Actualização do Formando", Toast.LENGTH_SHORT).show();
    }

    public void DeleteFormandoEntry() {
        Integer DeletedRows = myDB.DeleteFormando(DatabaseTableItemId);

        if (DeletedRows > 0)
            Toast.makeText(this, "Formando Eliminado com SUCESSO", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "ERRO na Eliminação do Formando", Toast.LENGTH_SHORT).show();
    }

    private void ShowAlertMessage(String Title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.setCancelable(true);
        builder.show();
    }

    public void ViewFormandoEntry() {
        Formando formando = myDB.getFormandobyId(DatabaseTableItemId);

        String AlertMessage="Nome:   "+formando.getNome()+"\n"+
                            "Numero: "+formando.getTelefone()+"\n"+
                            "Idade:  "+formando.getIdade()+"\n"+
                            "Email:  "+formando.getEmail();

        ShowAlertMessage("Número "+formando.getNumero(), AlertMessage);
    }

    public void BackToLista(){
        Intent intent=new Intent(this, ListaDeFormandosActivity.class);
        startActivity(intent);
        finish();
    }

}
