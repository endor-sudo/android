package com.example.crueladeville;

public class Formando
{
    private String Numero, Nome, Telefone, Idade, Email;

    public Formando()
    {
    }

    public Formando(String Numero, String Nome, String Telefone, String Idade, String Email)
    {
        this.Numero = Numero;
        this.Nome = Nome;
        this.Telefone = Telefone;
        this.Idade = Idade;
        this.Email = Email;
    }

    public String getNumero() { return this.Numero; }

    public void setNumero(String Numero)
    {
        this.Numero = Numero;
    }

    public String getNome()
    {
        return this.Nome;
    }

    public void setNome(String Nome)
    {
        this.Nome = Nome;
    }

    public String getTelefone() { return this.Telefone;}

    public void setTelefone(String Telefone) { this.Telefone = Telefone; }

    public String getIdade()
    {
        return this.Idade;
    }

    public void setIdade(String Telefone)
    {
        this.Idade = Idade;
    }

    public String getEmail()
    {
        return this.Email;
    }

    public void setEmail(String Email)
    {
        this.Email = Email;
    }
}