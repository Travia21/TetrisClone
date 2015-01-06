package com.donnellpetersonexperience.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Steven on 1/1/2015.
 */
public class Communication extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Communication(Socket sock){
        try{
            socket = sock;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }catch(IOException e){
            System.out.println("Communication initialization error: " + e);
        }
    }

    public ArrayList<String> read(){
        ArrayList<String> messages = new ArrayList<String>();

        try{
            messages.add(in.readLine());
        }catch(IOException e){
            System.out.println("InputStream read error: " + e);
        }

        return messages;
    }

    public void write(String s){
        out.print(s);
    }

    public void terminate(){
        try{
            in.close();
            out.close();
        }catch(IOException e){
            System.out.println("Communication termination error: " + e);
        }
    }




}
