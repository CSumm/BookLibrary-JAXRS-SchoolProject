/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servs;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import javax.ws.rs.ext.RuntimeDelegate;

/**
 *
 * @author Olivier
 */
public class Publisher {

    final int port = 8080;
    final String uri = "/library";
    final String url = "http://localhost:" + port + uri;
    final int backlog = 8;

    public static void main(String[] args) {

        System.out.println("Starting the library server.");
        new Publisher().publish();
        System.out.println("Application exit.");

    }

    private void publish() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", port), backlog);
            HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new RestfulLibrary(), HttpHandler.class);
            server.createContext(uri, handler);
            server.start();
            System.out.println("Hit enter to quit");
            System.in.read();
            server.stop(0);
        } catch (Exception ex) {
            System.out.println("An error has occured.");
            System.out.println(ex.getMessage());
            for (int i = 0; i < ex.getStackTrace().length; i++){
                System.out.println(ex.getStackTrace()[i]);
            }
            
        }
    }
}
