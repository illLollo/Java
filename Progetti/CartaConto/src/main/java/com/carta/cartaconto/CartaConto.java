/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.carta.cartaconto;

import com.carta.model.TipoMovimento;
import com.carta.model.Conto;
import com.carta.model.Intestatario;
import com.carta.model.Banca;
import com.carta.model.Indirizzo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

/**
 *
 * @author Administrator
 */
public class CartaConto {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException 
    {
        final Banca b = new Banca("Intesa Sanpaolo", "IT", "03069", "01783");
        
        try (final FileInputStream fis = new FileInputStream("people.ser"))
        {
            final ObjectInputStream ois = new ObjectInputStream(fis);
            
            for (final Intestatario i : (Intestatario[]) ois.readObject())
            {
                final Conto c =  b.newConto(i);
                c.newOperazione(TipoMovimento.BONIFICO_RICEVUTO, 1700, LocalDate.now(), "Stipendio Mensile");
                c.newOperazione(TipoMovimento.PRELIEVO_CONTANTE, 130, LocalDate.now().plusDays(2), "Perlievio per spese");
            }
        }
        catch (final ClassNotFoundException ex)
        {
            System.err.println("CANNOT CAST READED OBJECT!");
        }
        catch (final FileNotFoundException ex)
        {
            System.err.println("FILE NOT FOUND!");
        }
        catch (final IOException ex)
        {
            System.err.println("ERROR IN I/O FILE OPERATION!");
        }
        
        System.out.println(b);
    }
    
}
