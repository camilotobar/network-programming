package com.company;
import com.sun.security.ntlm.Server;

import java.util.Random;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client extends Thread{

    public static void main(String[] args) {

      Socket client;
      BufferedReader readerC;
      PrintWriter writerC;
      String name, answer, host;
      int port;
      Scanner scanner = new Scanner(System.in);

	  try
      {
        client = new Socket("localhost", 8040);
        readerC = new BufferedReader(new InputStreamReader(client.getInputStream()));
        writerC = new PrintWriter(client.getOutputStream(), true);

        host = client.getLocalAddress().getHostName();
        port = client.getLocalPort();
        System.out.println("Write your name");
        name = scanner.nextLine();

        /* The server port is going to show in the screen to verify that
         * it is the same in the server
         */
        System.out.println(port);

        // The data is going to send to the server
        writerC.println(name);
        writerC.println(host + ("( port: " + port + ")"));

        // The server answer is going to process
        answer = readerC.readLine();
        System.out.println(answer);

        // We are going to close the streams and sockets
        readerC.close();
        writerC.close();
        client.close();

      }catch (UnknownHostException e){
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
    }
}