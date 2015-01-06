package com.donnellpetersonexperience.networking;

import com.donnellpetersonexperience.builder.WindowBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by mactop on 1/6/15.
 */
public class SocketManager extends Thread {
    private BufferedReader in;
    private PrintWriter out;
    private WindowBuilder window;
    private Socket socket;

    public SocketManager(Socket sock) {
        socket = sock;
        window = new WindowBuilder(this);
        System.out.println("Connection to server established.");
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        }catch(IOException e){ }
    }

    @Override
    public void run(){
        while(true){
            try {
                writeToWindow(in.readLine());
            }catch(IOException e){ }
        }
    }

    public void write(String str){
        out.println(str);
    }

    public void writeToWindow(String str){
        window.display(str);
    }
}
