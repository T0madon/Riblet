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
        setLayout(new GridBagLayout());
        
        // Definindo uma cor de fundo personalizada
        getContentPane().setBackground(new Color(60, 63, 65));
        
        // Definindo fonte e cores para os campos de entrada e rótulos
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        Color labelColor = Color.WHITE;
        Color fieldBgColor = new Color(43, 43, 43);
        Color fieldFgColor = Color.WHITE;
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espaçamento entre os componentes
        
        // Adicionando os rótulos e campos de entrada
        addLabelAndField("Tamanho:", tField = new JTextField(), gbc, labelFont, labelColor, fieldFont, fieldBgColor, fieldFgColor);
        addLabelAndField("Altura:", hField = new JTextField(), gbc, labelFont, labelColor, fieldFont, fieldBgColor, fieldFgColor);
        addLabelAndField("Angulo:", angleField = new JTextField(), gbc, labelFont, labelColor, fieldFont, fieldBgColor, fieldFgColor);
        addLabelAndField("dR:", drField = new JTextField(), gbc, labelFont, labelColor, fieldFont, fieldBgColor, fieldFgColor);
        addLabelAndField("dR0:", dr0Field = new JTextField(), gbc, labelFont, labelColor, fieldFont, fieldBgColor, fieldFgColor);

        // Botão "Desenhar Riblets"
        drawButton = new JButton("Desenhar Riblets");
        drawButton.setFont(new Font("Arial", Font.BOLD, 14));
        drawButton.setBackground(new Color(75, 110, 175));
        drawButton.setForeground(Color.WHITE);
        drawButton.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(drawButton, gbc);
        
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

    private void addLabelAndField(String labelText, JTextField textField, GridBagConstraints gbc, Font labelFont, Color labelColor, Font fieldFont, Color fieldBgColor, Color fieldFgColor) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(labelColor);
        
        textField.setFont(fieldFont);
        textField.setBackground(fieldBgColor);
        textField.setForeground(fieldFgColor);
        textField.setCaretColor(Color.WHITE);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        add(textField, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            userData gui = new userData();
            gui.setVisible(true);
        });
    }
}
