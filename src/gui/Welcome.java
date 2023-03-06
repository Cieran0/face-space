package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Welcome {

    public Welcome(){
        JFrame welcome = new JFrame("Welcome");

        welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcome.setSize(400,400);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 100, 100, 100);
        welcome.add(btnLogin);

        btnLogin.addActionListener(new ActionListener(){
            //@Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(300, 300, 100, 100);
        welcome.add(btnRegister);

        welcome.setLayout(null);

        welcome.setVisible(true);
    }

}
