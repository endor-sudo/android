package com.example.crueladeville;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaDeFormandosActivity extends AppCompatActivity {


    DataBaseHelper myDB;

    ListView listViewContacts;

    MyCustomAdapter myAdapter;
    ArrayList<Formando> contactArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadeformandos);

        myDB = new DataBaseHelper(this);

        listViewContacts = findViewById(R.id.listViewContacts);

        LoadListView();
    }

    public void ListaBtns(View view){
        switch (view.getId()){
            case R.id.button5:
                ToRegisterFormando();
                break;
            case R.id.button6:
                BackToMainActivity(view);
                break;
        }
    }

    public void BackToMainActivity(View view){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void ToRegisterFormando(){
        Intent intent=new Intent(this, RegisterFormandoActivity.class);
        startActivity(intent);
        finish();
    }

    private void LoadListView()
    {
        contactArrayList = myDB.getAllContacts();

        myAdapter = new MyCustomAdapter(this, contactArrayList);
        listViewContacts.setAdapter(myAdapter);

        listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // https://developer.android.com/reference/android/widget/AdapterView.OnItemClickListener
            //
            // Callback method to be invoked when an item in this AdapterView has been clicked.
            // Implementers can call getItemAtPosition(position) if they need to access the data associated with the selected item.
            //
            // public abstract void onItemClick (AdapterView<?> parent,
            //                View view,
            //                int position,
            //                long id)
            //
            // Parameters
            // AdapterView parent:  The AdapterView where the click happened.
            // View view:           The view within the AdapterView that was clicked (this will be a view provided by the adapter)
            // int position:        The position of the view in the adapter.
            // long id:             The row id of the item that was clicked.

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(ListaDeFormandosActivity.this, UpdateFormandoActivity.class);

                Formando contact = (Formando) listViewContacts.getItemAtPosition(position);

                intent.putExtra("ContactsSelectedId", String.valueOf(contact.getNumero()));

                startActivity(intent);
            }
        });

        myAdapter.notifyDataSetChanged();
    }

}
