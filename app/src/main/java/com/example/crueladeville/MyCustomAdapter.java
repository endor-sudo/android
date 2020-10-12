package com.example.crueladeville;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Formando> contactsArrayList;

    public MyCustomAdapter(Context context, ArrayList<Formando> contactsArrayList)
    {
        this.context = context;
        this.contactsArrayList = contactsArrayList;

        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return this.contactsArrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return contactsArrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            view = this.layoutInflater.inflate(R.layout.contactslistview, viewGroup, false);
        }

        Formando contact = contactsArrayList.get(position);

        TextView textViewNumber = view.findViewById(R.id.textViewNumber);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewPhone = view.findViewById(R.id.textViewPhone);

        textViewNumber.setText(contact.getNumero());
        textViewName.setText(contact.getNome());
        textViewPhone.setText(contact.getTelefone());

        return view;
    }
}
