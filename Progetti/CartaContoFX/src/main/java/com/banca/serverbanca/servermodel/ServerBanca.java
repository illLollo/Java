/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.banca.serverbanca.servermodel;

import com.carta.model.Banca;
import com.carta.model.Conto;
import com.carta.model.Iban;
import com.carta.model.Indirizzo;
import com.carta.model.Intestatario;
import com.carta.model.Movimento;
import com.carta.model.TipoIntestatario;
import com.carta.model.TipoMovimento;
import com.chat.messanger.ConnectionManager;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.time.LocalDate;
import com.conto.cartacontogui.Utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.json.*;
/**
 *
 * @author Administrator
 */
public class ServerBanca
{
    private static final Banca b = new Banca("Centromarca Banca", "IT", "08749", "36190");
    
    public static void main(String[] args) throws SocketException 
    {   
        
        try (final FileInputStream f = new FileInputStream("banca_database.json"))
        {
            final JSONObject database = new JSONObject(new JSONTokener(f));
            
           final JSONObject banca =  database.getJSONArray("banca").getJSONObject(0);
           
           final JSONObject conti = banca.getJSONObject("conti");
           
           
            readFromJson(banca);
                         
                     
        } 
        catch (final FileNotFoundException ex) 
        {
            ex.printStackTrace();
            
        } catch (final IOException ex)
        {
            ex.printStackTrace();
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        
//        
//        
//        b.registerUser(new Intestatario("lorenzo1", "12345", TipoIntestatario.ADMIN, "GMBLNZ06T16F241E", "Gambaro", "Lorenzo", LocalDate.now(), new Indirizzo("Via Accoppè Fratte", "85 int. 2", "30035", "Mirano", "VE"),"3899369940", "lorygamba06@gmail.com"));
//        b.registerUser(new Intestatario("lorenzo2", "12345", TipoIntestatario.ADMIN, "GMBLNZ06T16F241E", "Gambaro", "Lorenzo", LocalDate.now(), new Indirizzo("Via Accoppè Fratte", "85 int. 2", "30035", "Mirano", "VE"),"3899369940", "lorygamba06@gmail.com"));
//        
//        b.newConto(b.searchIntestatario("lorenzo1", Intestatario.calcolaHash("12345")));
//        b.newConto(b.searchIntestatario("lorenzo1", Intestatario.calcolaHash("12345")));
//        final Conto c = b.newConto(b.searchIntestatario("lorenzo1", Intestatario.calcolaHash("12345")));
//        
//        c.newOperazione(new TipoMovimento("Bonifico 1", 0, 1), new Iban("IT30K0987976587124213857632"), 1000, LocalDate.now(), "Bonifico 1");
//        
//        b.newConto(b.searchIntestatario("lorenzo2", Intestatario.calcolaHash("12345")));
//        b.newConto(b.searchIntestatario("lorenzo2", Intestatario.calcolaHash("12345")));
//        
//        b.newTipoMovimento("Bonifico 1", 123, -1);
//        b.newTipoMovimento("Bonifico 2", 124, 1);
//        
//        listenForLogin(9999, 18);
//        listenForLoginMobile(25565, 18);
//        listenGetTipiMovimento(9998, 19);
    }  
    public static Banca readFromJson(final JSONObject database)
    {
        final String name = (String) database.get("name");
        final String location = (String) database.get("location");
        final String abi = (String) database.get("abi");
        final String cab = (String) database.get("cab");
        
        final Map<Iban, Conto> conti = new HashMap<>();
        
        final JSONObject contiObject = database.getJSONObject("conti");
        
        for (final var iban : contiObject.keySet())
        {
            final JSONObject currentConto = contiObject.getJSONObject(iban);
            
            final Set<Intestatario> intestatari = new TreeSet<>();
            
            for (final var i : currentConto.getJSONArray("intestatari"))
            {
                final JSONObject obj = (JSONObject) i;
                final JSONObject indirizzo = obj.getJSONObject("address");
                final JSONArray contiAssociati = obj.getJSONArray("contiAssociati");
                
                final Map<Iban, Conto>
                
                intestatari.add(new Intestatario
                    (obj.getString("name"),
                     obj.getString("password"),
                      TipoIntestatario.ADMIN,
                     obj.getString("cf"),
                    obj.getString("cognome"),
                    obj.getString("name"),
             LocalDate.parse(obj.getString("birthdate")),
                   new Indirizzo(indirizzo.getString("via"),
               indirizzo.getString("numero"),
                indirizzo.getString("cap"),
              indirizzo.getString("comune"),
              indirizzo.getString("provincia")),
             obj.getString("name"),
                obj.getString("email")),
                       
                );
            }
            
            final List<Movimento> m = new ArrayList<>();
            
            for (final var i : currentConto.getJSONArray("movimenti"))
            {
                final JSONObject obj = (JSONObject) i;
                
                final JSONObject tipoMovimento = (JSONObject) obj.getJSONObject("type");
                
                m.add(new Movimento(obj.getInt("id"), new Iban(obj.getString("ibanRichiedente")), LocalDate.parse(obj.getString("operationDate")), LocalDate.parse(obj.getString("valueteDate")), obj.getString("descr"), new Iban(obj.getString("ibanDestinatario")), obj.getDouble("importo"), new TipoMovimento(tipoMovimento.getLong("code"), tipoMovimento.getString("descr"), tipoMovimento.getDouble("cost"), tipoMovimento.getDouble("amount"))));
            }
            conti.put(new Iban(iban), new Conto(new Iban(iban), intestatari, LocalDate.parse(currentConto.getString("openingDate")), LocalDate.parse(currentConto.getString("closingDate")), m));
           
            
            
            System.out.println(conti);
        }
        
        return null;
    }
    public static void listenForLogin(final int port, final int destPort) throws SocketException
    {
        final ConnectionManager c = new ConnectionManager(port);
        c.startReciving((final DatagramPacket p) -> 
        {
            System.out.println("RECIVED " + p);
            try 
            {
                final LoginInformations parsed = (LoginInformations) Utils.deserializeObject(p.getData()) ;
                
//                System.out.println("RECIVED: " + parsed.getUsername() + " - " + parsed.getHashedPassword());
                
                
                final Intestatario local = b.searchIntestatario(parsed.getUsername(), parsed.getHashedPassword());
                
//                System.out.println("FOUND: " + local);
                
                final byte[] serializedIntestatario = Utils.serializeObject(local == null || !local.getHashedPassword().equals(parsed.getHashedPassword()) ? null : local);    
                
//                System.out.println("Invio a : " + p.getAddress() + ": " + p.getPort());
                
                c.send(serializedIntestatario, p.getAddress(), destPort);
            } 
            catch (IOException | ClassNotFoundException ex) 
            {
                c.close();
                ex.printStackTrace();
            }
        });
    }
    public static void listenForLoginMobile(final int port, final int destPort) throws SocketException
    {
        final ConnectionManager c = new ConnectionManager(port);
        c.startReciving((final DatagramPacket p) -> 
        {
            System.out.println("RECIVED " + p);
            try 
            {
                final String[] spliced = new String(p.getData(), 0, p.getLength()).split(";");
                final LoginInformations parsed = new LoginInformations(spliced[0], spliced[1]);
                
                System.out.println("RECIVED: " + parsed.getUsername() + " - " + parsed.getHashedPassword());
                
                
                final Intestatario local = b.searchIntestatario(parsed.getUsername(), parsed.getHashedPassword());
                
//                System.out.println("FOUND: " + local);
                
                final byte[] serializedIntestatario = Utils.serializeObject(local == null || !local.getHashedPassword().equals(parsed.getHashedPassword()) ? null : local);    
                
//                System.out.println("Invio a : " + p.getAddress() + ": " + p.getPort());
                
                c.send(serializedIntestatario, p.getAddress(), destPort);
            } 
            catch (IOException ex) 
            {
                c.close();
                ex.printStackTrace();
            }
        });
    }

    private static void listenGetTipiMovimento(final int listenPort, final int sendPort) throws SocketException 
    {
        final ConnectionManager c = new ConnectionManager(listenPort);
        
        c.startReciving((final DatagramPacket packet) -> 
        {
            System.out.println("MANDO!");
            try
            {
                c.send(Utils.serializeObject(b.getTipiMovimento()), packet.getAddress(), sendPort);
                
            } catch (final IOException ex) 
            {
                ex.printStackTrace();
            }
        });
        
        
    }
}
