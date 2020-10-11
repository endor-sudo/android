package com.example.crueladeville;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DataBaseHelper appDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        appDB=new DataBaseHelper(this);
    }

    public void RegisterBtns(View view){
        switch (view.getId()){
            case R.id.button:
                RegisterUSer();
                break;
            case R.id.button2:
                BackToMainActivity(view);
                break;
        }
    }

    public void BackToMainActivity(View view){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void RegisterUSer(){
        EditText editTextUsername = findViewById(R.id.nameregister);
        EditText editTextPassword = findViewById(R.id.passwordregister);
        EditText editTextEmail = findViewById(R.id.emailregister);

        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String email = editTextEmail.getText().toString();

        if (appDB.InserirUser(username, password, email) == true)
        {
            Toast.makeText(this, R.string.UserInserted, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, R.string.UserInsertedError, Toast.LENGTH_SHORT).show();
        }
    }
}