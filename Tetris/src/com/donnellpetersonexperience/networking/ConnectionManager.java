package com.donnellpetersonexperience.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Steven on 12/30/2014.
 */
public class ConnectionManager {
    private ServerSocket server;

    /**
     * 1. Check for an open ServerSocket with IPAddress.
     *
     * 2a. Open connection with the remote server.
     * 3a. Begin communicating.
     *
     * 2b. Open a ServerSocket and wait.
     *
     * @param IPAddress The IP address to connect to. If null, skip checking and open ServerSocket.
     */
    public ConnectionManager(String IPAddress){
        try{
            server = new ServerSocket(9898);
            try{
                while(true){
                    new Communication(server.accept());
                }
            }finally{
                terminate();
            }
        }catch(IOException e){
            System.out.print("Could not open connection." + e);
        }
    }

    public void terminate(){
        try{
            server.close();
        }catch(IOException e){
            System.out.println("Termination error: " + e);
        }
        System.exit(2);
    }
}
