package com.donnellpetersonexperience.builder;

import com.donnellpetersonexperience.networking.SocketManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Steven Laverne Peterson Jr on 12/26/2014.
 */
public class WindowBuilder {
    private JFrame frame;
    private JButton button;
    private JTextArea textArea;
    private JTextField textField;

    private SocketManager socket;

    public WindowBuilder(SocketManager sock){
        socket = sock;

        frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

        componentBuilder();

        frame.pack();
        textField.requestFocusInWindow();
        frame.setVisible(true);
    }

    private void componentBuilder(){
        textBuilder();
        buttonBuilder();
        panelBuilder();
    }

    private void panelBuilder(){
        frame.getContentPane().add(textArea, "North");
        frame.getContentPane().add(textField, "Center");
        frame.getContentPane().add(button, "East");
    }

    private void textBuilder(){
        textField = new JTextField("", 30);
            textField.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    socket.write(textField.getText());
                    textField.setText("");
                }
            });
        textArea = new JTextArea("", 10, 30);
            textArea.setEditable(false);
    }

    private void buttonBuilder(){
        button = new JButton();
        button.setText("ENTER");
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                socket.write(textField.getText());
                textField.setText("");
            }
        });
    }

    public void display(String str){
        textArea.append(str + "\n");
    }
}
