package com.company;

import java.net.*;
import java.io.*;

public class Server {

    public static void main(String[] args){

        try {
            ServerSocket server = new ServerSocket(8030);
            while(true)
            {
                Socket c = server.accept();
                ThreadServer thread = new ThreadServer(c);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class ThreadServer extends Thread{

    private Socket client;
    private BufferedReader readerHS;
    private PrintWriter writerHS;

    public ThreadServer(Socket request){
        client = request;
    }

    @Override
    public void run() {
        try {
           readerHS = new BufferedReader(new InputStreamReader(client.getInputStream()));
           writerHS = new PrintWriter(client.getOutputStream(), true);

           // The next code lines are going to get the name and host from the user
           String name = readerHS.readLine();
           String host = readerHS.readLine();

           // we are going to write the answer and send it to the client
           String message = "Hi "+ name + " in " + host + ", Welcome!!";
           writerHS.println(message);

           // we are going to close the streams and the socket associated to the request
           readerHS.close();
           writerHS.close();
           client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}