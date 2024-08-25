/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.riblet.atividade;

/**
 *
 * @author joaod
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RibletAtividade extends JFrame{
    
    class AnguloInvalidoException extends Exception {
        public AnguloInvalidoException(String message) {
            super(message);
        }
    }
    class TamInvalidoException extends Exception {
        public TamInvalidoException(String message) {
            super(message);
        }
    }
    class DrException extends Exception {
        public DrException(String message) {
            super(message);
        }
    }

    private JTextField tField, dr0Field, drField, hField, angleField;
    private JButton drawButton;

    public RibletAtividade() {
        
        setTitle("Gerador de Riblet");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        
        // Definindo uma cor de fundo personalizada
        getContentPane().setBackground(new Color(60, 63, 65));
        
        // Definindo fonte e cores para os campos de entrada e rótulos
        Font labelFont = new Font("Arial", Font.BOLD, 10);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        Color labelColor = Color.WHITE;
        Color fieldBgColor = new Color(43, 43, 43);
        Color fieldFgColor = Color.WHITE;
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espaçamento entre os componentes
        
        // Adicionando os rótulos e campos de entrada
        addLabelAndField("Tamanho(μm):", tField = new JTextField(), gbc, labelFont, labelColor, fieldFont, fieldBgColor, fieldFgColor);
        addLabelAndField("Altura(μm):", hField = new JTextField(), gbc, labelFont, labelColor, fieldFont, fieldBgColor, fieldFgColor);
        addLabelAndField("Angulo(°):", angleField = new JTextField(), gbc, labelFont, labelColor, fieldFont, fieldBgColor, fieldFgColor);
        addLabelAndField("dR(μm):", drField = new JTextField(), gbc, labelFont, labelColor, fieldFont, fieldBgColor, fieldFgColor);
        addLabelAndField("dR0(μm):", dr0Field = new JTextField(), gbc, labelFont, labelColor, fieldFont, fieldBgColor, fieldFgColor);

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
                    
                    if (angulo <= 0 || angulo >= 180) {
                         throw new AnguloInvalidoException("O ângulo deve estar entre 0 e 180 graus.");
                     }
                    if (tam > 1460){
                        throw new TamInvalidoException("O tamanho máx da tela é de 1460 pixels");
                    }
                    if (dR <= h * 2 * Math.tan(Math.toRadians(angulo/2))){
                        throw new DrException("A distância dR deve ser maior que o xR");
                    }
                    
                    
                    // Creating a Riblet instance and passing the parameters
                    logica riblet = new logica(tam, h, angulo, dR, dR0);
                    riblet.setSize(1920, 1080);
                    riblet.setVisible(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor inserido inválido.", "Erro de Tipo", JOptionPane.ERROR_MESSAGE);
                } catch (AnguloInvalidoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro de Ângulo", JOptionPane.ERROR_MESSAGE);
                } catch (TamInvalidoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro de tamanho", JOptionPane.ERROR_MESSAGE);
                } catch (DrException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro de dR", JOptionPane.ERROR_MESSAGE);
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
            RibletAtividade gui = new RibletAtividade();
            gui.setVisible(true);
        });
    }
}
