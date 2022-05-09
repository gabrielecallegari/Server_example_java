package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

public class Server {

    Server(int portNumber) {
        System.out.println("Server started at port " + portNumber);
        ServerSocket serverSocket = null;
        ClientHandler tread=null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println("port number exception\nError:" + e.toString());
        }
        Socket clientSocket = null;
        while (true) {

            try {
                clientSocket=null;
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Client socket exception\nError:" + e.toString());
                System.out.println("Sono uscito qui");
                break;
            }
            System.out.println("Richiesta in ingresso:");

            tread=new ClientHandler(clientSocket,serverSocket);
            tread.start();

        }
        tread.socket_close();

    }
}
