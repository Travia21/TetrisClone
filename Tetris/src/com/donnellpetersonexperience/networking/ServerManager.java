package com.donnellpetersonexperience.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Steven on 1/6/15.
 */
public class ServerManager {
    private Set<PrintWriter> connections;

    public ServerManager() {
        try{
            ServerSocket server = new ServerSocket(9898);
            System.out.println("Server started.");
            connections = new HashSet<PrintWriter>();
            while(true){
                try{
                    new Connection(server.accept()).start();
                }catch(IOException e) {
                }finally{
                    server.close();
                }
            }
        }catch(IOException e){}
    }

    private void broadcast(String message){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm zzz");
        String user = "<" + sdf.format(date) + "> Steven: ";

        for(PrintWriter connection : connections){
            connection.println(user + message);
        }
    }

    private class Connection extends Thread {
        private BufferedReader in;
        private PrintWriter out;
        private Socket socket;

        public Connection(Socket sock) {
            socket = sock;
            try{
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                connections.add(out);
            }catch(IOException e){ }
        }

        @Override
        public void run(){
            while(true){
                try {
                    broadcast(in.readLine());
                }catch(IOException e){ }
            }
        }
    }
}