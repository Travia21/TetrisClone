package com.donnellpetersonexperience.builder;

import com.donnellpetersonexperience.networking.Communication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Steven Laverne Peterson Jr on 12/26/2014.
 */
public class WindowBuilder implements ActionListener {
    private JFrame frame;
    private JButton button;
    private JTextArea textArea;
    private JTextField textField;

    private Date date;
    private SimpleDateFormat sdf;

    private Communication comms;

    public WindowBuilder(Communication comms){
        this.comms = comms;
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
            textField.addActionListener(this);
        textArea = new JTextArea("", 10, 30);
            textArea.setEditable(false);
    }

    private void buttonBuilder(){
        button = new JButton();
        button.setText("ENTER");
        button.addActionListener(this);
    }

    public void write(String str){
        textArea.append(str + "\n");
    }

    /**
     * This action should send the TextField through whatever connection the program has set up.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String text = textField.getText();
            if(text.compareTo(".") == 0) comms.connection.terminate();

        date = new Date();
        sdf = new SimpleDateFormat("HH:mm zzz");

        String user = "<" + sdf.format(date) + "> Steven: ";

        textField.setText("");
        comms.write(user + text);
    }
}
