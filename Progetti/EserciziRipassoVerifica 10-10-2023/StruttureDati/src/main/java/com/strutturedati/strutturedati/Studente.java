package com.strutturedati.strutturedati;

public class Studente 
{
    private String nome;
    private int nMatricola;
    private int age;
    
    Studente(String nome, int nMatricola, int age)
    {
       this.nome  = nome;
       this.nMatricola = nMatricola;
       this.age = age;
    }
    
    public void view()
    {
        System.out.println("[ " + this.nome + " " + this.nMatricola + " " + this.age + "]");
    }  
    
    
    public int getAge() { return this.age; }
    public int getNmatricola() { return this.nMatricola; }
    public String getNome() { return this.nome; }
    
        
}
