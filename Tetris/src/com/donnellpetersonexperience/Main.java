package com.donnellpetersonexperience;

import com.donnellpetersonexperience.networking.SocketManager;
import com.donnellpetersonexperience.networking.ServerManager;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Steven on 12/26/2014.
 */
public class Main {
    public static void main(String[] args){
        //String IPAddress = JOptionPane.showInputDialog("Enter server's IP address: ");
        try{
            new SocketManager(new Socket(/* IPAddress */"192.168.1.245", 9898)).start();
        }catch(IOException e){
            new ServerManager();
            try{
                new SocketManager(new Socket("127.0.0.1", 9898));
            }catch(Exception ex){
                System.out.println("Error starting server's client.");
            }
        }
    }
}
