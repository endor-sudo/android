package com.example.crueladeville;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "ufcd5423.db";
    public static final int    DATABASE_VERSION = 1;

    public static final String USERS_TABLE_NAME = "users";
    public static final String USERS_COLUMN_ID = "Id";
    public static final String USERS_COLUMN_USERNAME = "Username";
    public static final String USERS_COLUMN_PASSWORD = "Password";
    public static final String USERS_COLUMN_EMAIL = "Email";

    public static final String STUDENTS_TABLE_NAME = "formandos";
    public static final String STUDENTS_COLUMN_ID = "Id";
    public static final String STUDENTS_COLUMN_NUMBER = "Numero";
    public static final String STUDENTS_COLUMN_NAME = "Nome";
    public static final String STUDENTS_COLUMN_PHONE = "Telefone";
    public static final String STUDENTS_COLUMN_AGE = "Idade";
    public static final String STUDENTS_COLUMN_EMAIL = "Email";

    public DataBaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase(); // força a chamada do método onCreate
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String queryCreateUsers = "create table if not exists " + USERS_TABLE_NAME + " (" + USERS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERS_COLUMN_USERNAME + " TEXT, " + USERS_COLUMN_PASSWORD + " TEXT, " + USERS_COLUMN_EMAIL + " TEXT)";
        sqLiteDatabase.execSQL(queryCreateUsers);

        String queryCreateStudents = "create table if not exists " + STUDENTS_TABLE_NAME + " (" + STUDENTS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STUDENTS_COLUMN_NUMBER + " TEXT, " + STUDENTS_COLUMN_NAME + " TEXT, " + STUDENTS_COLUMN_PHONE + " TEXT,"+ STUDENTS_COLUMN_AGE + " TEXT, "+ STUDENTS_COLUMN_EMAIL + " TEXT)";
        sqLiteDatabase.execSQL(queryCreateStudents);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int _old, int _new)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STUDENTS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public boolean InserirUser (String Username, String Password, String Email)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(USERS_COLUMN_USERNAME, Username);
        contentValues.put(USERS_COLUMN_PASSWORD, Password);
        contentValues.put(USERS_COLUMN_EMAIL, Email);

        long result = db.insert(USERS_TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean CheckLogin (String Username, String Password)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String queryUsers = "select count(*) from " + USERS_TABLE_NAME + " where " + USERS_COLUMN_USERNAME + " = '" + Username + "' and " + USERS_COLUMN_PASSWORD + " = '" + Password + "'";

        long NumEntries = DatabaseUtils.queryNumEntries(db, USERS_TABLE_NAME, USERS_COLUMN_USERNAME + " = ? AND " + USERS_COLUMN_PASSWORD + " = ?", new String[] {Username, Password});

        if (NumEntries == 0)
            return false;
        else
            return true;
    }

    public Formando get_formando(Cursor cursor){
        String Numero = cursor.getString(1);
        String Nome = cursor.getString(2);
        String Telefone = cursor.getString(3);
        String Idade = cursor.getString(4);
        String Email = cursor.getString(5);

        Formando formando = new Formando(Numero, Nome, Telefone, Idade, Email);

        return formando;
    }

    public Formando getFormandobyId(String id){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select * from formandos where Numero = " + id;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        Formando formando=get_formando(cursor);
        return formando;
    }

    public boolean InserirFormando (String Numero, String Nome, String Telefone, String Idade, String Email)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(STUDENTS_COLUMN_NUMBER, Numero);
        contentValues.put(STUDENTS_COLUMN_NAME, Nome);
        contentValues.put(STUDENTS_COLUMN_PHONE, Telefone);
        contentValues.put(STUDENTS_COLUMN_AGE, Idade);
        contentValues.put(STUDENTS_COLUMN_EMAIL, Email);

        long result = db.insert(STUDENTS_TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean UpdateFormando (String Id, String Numero, String Nome, String Telefone, String Idade, String Email)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(STUDENTS_COLUMN_NUMBER, Numero);
        contentValues.put(STUDENTS_COLUMN_NAME, Nome);
        contentValues.put(STUDENTS_COLUMN_PHONE, Telefone);
        contentValues.put(STUDENTS_COLUMN_AGE, Idade);
        contentValues.put(STUDENTS_COLUMN_EMAIL, Email);

        long result = db.update(STUDENTS_TABLE_NAME, contentValues, STUDENTS_COLUMN_NUMBER + " = ?", new String[] {Id});

        if (result == -1)
            return false;
        else
            return true;
    }

    public Integer DeleteFormando (String Id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(STUDENTS_TABLE_NAME, STUDENTS_COLUMN_NUMBER + " = ?", new String[] {Id});
    }

    public ArrayList<Formando> getAllContacts()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Formando> contactArrayList = new ArrayList<>();

        String query = "select * from formandos";
        Cursor cursor = db.rawQuery(query, null);


        while (cursor.moveToNext())
        {
            int Id = cursor.getInt(0);
            String Name = cursor.getString(1);
            String Phone = cursor.getString(2);

            Formando formando = get_formando(cursor);

            contactArrayList.add(formando);
        }

        return contactArrayList;
    }
}
