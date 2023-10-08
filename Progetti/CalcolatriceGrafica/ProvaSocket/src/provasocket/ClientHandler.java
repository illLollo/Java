package provasocket;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ClientHandler implements HttpHandler
{
    @Override
    public void handle(HttpExchange exchange) throws IOException 
    {
        if (!"POST".equals(exchange.getRequestMethod())) 
        {
            exchange.sendResponseHeaders(405, -1);
            return;
        }
        
        System.out.println("Client Connesso");
        
        InputStream reqBody = exchange.getRequestBody();
        
        String command = new String(reqBody.readAllBytes());
        
        String res = "Il comando è: " + command;
        
        System.out.println(res);
        
        exchange.sendResponseHeaders(200, res.length());
        
        try (OutputStream os = exchange.getResponseBody()) 
        {
            os.write(res.getBytes());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("Client Disconnesso");
    }
}
