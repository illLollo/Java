package provasocket;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class ProvaSocket
{

    public static void main(String[] args) throws IOException
    {
        System.out.println("Plugin messaggi enabled");
        
        HttpServer endpoint = HttpServer.create(new InetSocketAddress(8000), 0);
        endpoint.createContext("/", new ClientHandler());
        endpoint.setExecutor(null);
        System.out.println("Attendo Client... (porta 1010)");
        endpoint.start();
 
    }
    public void onDisable()
    {
        System.out.println("plugin messaggi disabled");
    }
    
    
}

