package steven.com.builder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Steven Laverne Peterson Jr on 12/26/2014.
 */
public class WindowBuilder implements ActionListener {
    JFrame window;
    JPanel panel;
    JButton button;
    JTextArea textArea;
    JTextField textField;

    public WindowBuilder(){
        window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);

        componentBuilder();

        window.add(panel);
        window.pack();
        window.setVisible(true);
    }

    private void componentBuilder(){
        textBuilder();
        buttonBuilder();
        panelBuilder();
    }

    private void panelBuilder(){
        panel = new JPanel();
            panel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
            c.ipadx = 0; c.ipady = 10;
        panel.add(textArea, c);
        panel.add(textField, c);
        c.ipady = 3;
        panel.add(button, c);
    }

    private void textBuilder(){
        textField = new JTextField("", 30);
            textField.addActionListener(this);
        textArea = new JTextArea("", 10, 30);
    }

    private void buttonBuilder(){
        button = new JButton();
        button.setText("ENTER");
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = textField.getText();
        textField.setText("");
        textArea.append(text + "\n");
    }
}
