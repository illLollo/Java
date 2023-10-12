package com.strutturedati.strutturedati;


public class Registro
{
    private Studente[] studenti;
    private int numeroStudenti;
    
    Registro(int num)
    {
        this.studenti = new Studente[num];
    }
    
    public void add(Studente s)
    {
        if (numeroStudenti < studenti.length)
            studenti[numeroStudenti++] = s;
        else System.out.println("no space");
    }
    public void visual()
    {
        for (int i = 0; i < this.numeroStudenti; i++) {
            studenti[i].view();
        }
    }
    public Studente findMinAge()
    {
        Studente min = null;
        
        for (int i = 0; i < this.numeroStudenti; i++)
        {
            if (min == null || min.getAge() > studenti[i].getAge()) min = studenti[i];
        }
        
        return min;
    }
    public Studente findMaxAge()
    {
        Studente min = null;
        
        for (int i = 0; i < this.numeroStudenti; i++)
        {
            if (min == null || min.getAge() < studenti[i].getAge()) min = studenti[i];
        }
        
        return min;
    }
            
}
