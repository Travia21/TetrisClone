package com.donnellpetersonexperience;

import com.donnellpetersonexperience.builder.WindowBuilder;
import com.donnellpetersonexperience.networking.Communication;
import com.donnellpetersonexperience.networking.ConnectionManager;

import javax.swing.*;

/**
 * Created by Steven on 12/26/2014.
 */
public class Main {
    public static void main(String[] args){
        String ipAddress = JOptionPane.showInputDialog("Enter IP Address of the server: ");
        ConnectionManager connection = new ConnectionManager(ipAddress);
        Communication comms = new Communication();
        WindowBuilder window = new WindowBuilder(comms);
    }
}
