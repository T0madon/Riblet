/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.riblet.activity.RibletActivity;

/**
 *
 * @author joaod
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class userData extends JFrame {
    private JTextField tField, dr0Field, drField, hField, angleField;
    private JButton drawButton;

    public userData() {
        setTitle("Riblet Generator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Adding input fields and labels
        add(new JLabel("Tamanho:"));
        tField = new JTextField();
        add(tField);
        
        add(new JLabel("Altura:"));
        hField = new JTextField();
        add(hField);
        
        add(new JLabel("Angulo:"));
        angleField = new JTextField();
        add(angleField);
        
        add(new JLabel("dR:"));
        drField = new JTextField();
        add(drField);

        add(new JLabel("dR0:"));
        dr0Field = new JTextField();
        add(dr0Field);

        drawButton = new JButton("Desenhar Riblets");
        add(drawButton);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parsing input values
                    double tam = Double.parseDouble(tField.getText());
                    double h = Double.parseDouble(hField.getText());
                    double angulo = Double.parseDouble(angleField.getText());
                    double dR = Double.parseDouble(drField.getText());
                    double dR0 = Double.parseDouble(dr0Field.getText());
                    
                    // Creating a Riblet instance and passing the parameters
                    RibletActivity riblet = new RibletActivity(tam, h, angulo, dR, dR0);
                    riblet.setSize(1920, 1080);
                    riblet.setVisible(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira valores numéricos válidos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        userData gui = new userData();
        gui.setVisible(true);
    }
}
