package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ClientHandler extends Thread{
    Socket clientSocket=null;
    ServerSocket serverSocket=null;
    ClientHandler(Socket clientSocket, ServerSocket serverSocket){
        this.clientSocket=clientSocket;
        this.serverSocket=serverSocket;
    }
    @Override
    public void run(){
        BufferedReader input = null;
        try {
            input = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()
                    )
            );
        } catch (IOException e) {
            System.out.println("Input streamer error\nError:" + e.toString());
        }
        //LETTURA E RISPOSTA AL CLIENT
        String message = "";
        String respond = "Messaggio ricevuto";
        PrintWriter output = null;
        try {
            output = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Errore nell'apertura output\nErrore:" + e.toString());
        }

        try {
            while ((message = input.readLine()) != null) {
                System.out.println(message);
                output.println(result());

                System.out.println(respond);
            }
        } catch (IOException e) {
            System.out.println("Errore in lettura");
        }

    }

    public void socket_close(){
        try {
            serverSocket.close();
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }


    private String result(){
        Random rand=new Random();
        Coordinates_json cj=new Coordinates_json();
        cj.xPoint= rand.nextInt(400)+50;
        cj.yPoint=rand.nextInt(200)+50;
        cj.raggioPoint=rand.nextInt(25)+50;
        String json="{\"xPoint\":\""+cj.xPoint+"\",\"yPoint\":\""+cj.yPoint+"\",\"raggioPoint\":\""+cj.raggioPoint+"\"}";
        return json;
    }
}