package com.example.crueladeville;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper appDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDB = new DataBaseHelper(this);
    }

    public void LoginBtn(View view){
        switch (view.getId()){
            case R.id.button3:
                LoginCheck();
                break;
            case R.id.button4:
                Register();
                break;
        }
    }

    public void LoginCheck(){
        EditText editTextUsername = findViewById(R.id.mainUsername);
        EditText editTextPassword = findViewById(R.id.mainPassword);

        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        // check credentials here

        if (appDB.CheckLogin(username, password) == true)
        {
            Intent intent = new Intent(this, ListaDeFormandosActivity.class);

            startActivity(intent);
            finish();
            Toast.makeText(this, R.string.UserCredentialsCheckOut, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, R.string.UserCredentialsDOnOTCheckOut, Toast.LENGTH_SHORT).show();
        }
    }

    public void Register(){
        Intent intent=new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}