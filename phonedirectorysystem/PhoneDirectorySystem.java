/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.phonedirectorysystem;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Mokhtar Mohamed
 */
public class PhoneDirectorySystem {

    public static void main(String[] args) {
        // Set the look and feel of the application to the system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }

        // Create and display the form
        java.awt.EventQueue.invokeLater(() -> {
            new PhoneDirectoryForm().setVisible(true);
        });
}
}
