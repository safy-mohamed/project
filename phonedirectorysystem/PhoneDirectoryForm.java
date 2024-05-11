
package com.mycompany.phonedirectorysystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;



public class PhoneDirectoryForm extends javax.swing.JFrame {
    
    private final DatabaseManager dbManager;
    public PhoneDirectoryForm() {
        initComponents();
        initEventHandlers();
        // Initialize DatabaseManager instance
        dbManager = new DatabaseManager();

        // Populate JTable with initial data from the database
        refreshTable();
    }
     private void initEventHandlers() {
     // Inside your add button ActionListener
addBTN.addActionListener((ActionEvent e) -> {
    // Add button clicked, implement functionality here
    String firstName = FNfield.getText();
    String lastName = LNfield.getText();
    String email = Emailfield.getText();
    String mobileNumber = MNfield.getText();
    String jobTitle = JTfield.getText();
    
    if (!isValidMobileNumber(mobileNumber) || !isValidEmail(email)) {
        JOptionPane.showMessageDialog(PhoneDirectoryForm.this, "Invalid mobile number or email format!", "Error", JOptionPane.ERROR_MESSAGE);
        // Don't proceed further if validation fails
        return;
    }
    
    // Add the contact to the database
    boolean success = dbManager.addContact(firstName, lastName, email, mobileNumber, jobTitle);
    if (success) {
        // If the contact was added successfully, refresh the table to reflect the changes
        refreshTable();
        // Optionally, clear the input fields after adding the contact
        FNfield.setText("");
        LNfield.setText("");
        Emailfield.setText("");
        MNfield.setText("");
        JTfield.setText("");
    } else {
        JOptionPane.showMessageDialog(PhoneDirectoryForm.this, "Error adding contact to the database!", "Error", JOptionPane.ERROR_MESSAGE);
    }
});





      deleteBTN.addActionListener((ActionEvent e) -> {
    // Delete button clicked, implement functionality here
    String mobileNumber = MNfield.getText();
    if (!isValidMobileNumber(mobileNumber)) {
        JOptionPane.showMessageDialog(this, "Invalid mobile number format!", "Error", JOptionPane.ERROR_MESSAGE);
        // Don't proceed further if validation fails
        return;
    }
    
    // If validation passes, proceed to delete the contact
    boolean success = dbManager.deleteContact(mobileNumber);
    if (success) {
        // Refresh the table to reflect the changes
        refreshTable();
        JOptionPane.showMessageDialog(this, "Contact deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Error deleting contact!", "Error", JOptionPane.ERROR_MESSAGE);
    }
});

    updateBTN.addActionListener((ActionEvent e) -> {
    // Get the selected row index from the table
    int selectedRowIndex = jTable1.getSelectedRow();
    
    // Check if a row is selected
    if (selectedRowIndex == -1) {
        // If no row is selected, display an error message to the user
        JOptionPane.showMessageDialog(this, "Please select a contact to update.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Retrieve contact information from the selected row
    String originalFirstName = (String) jTable1.getValueAt(selectedRowIndex, 0); // Assuming first name is in the first column
    String originalMobileNumber = (String) jTable1.getValueAt(selectedRowIndex, 3); // Assuming mobile number is in the fourth column
    
    // Get updated contact information from text fields or other input components
    String newFirstName = FNfield.getText();
    String newMobileNumber = MNfield.getText();
    
    // Validate the input (e.g., check if fields are empty, validate mobile number format)
    if (newFirstName.isEmpty() || newMobileNumber.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter first name and mobile number.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Perform the update operation
    boolean success = dbManager.updateContact(originalFirstName, originalMobileNumber, newFirstName, newMobileNumber);
    
    if (success) {
        // If update is successful, refresh the table to reflect the changes
        refreshTable();
        JOptionPane.showMessageDialog(this, "Contact updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    } else {
        // If update fails, display an error message to the user
        JOptionPane.showMessageDialog(this, "Error updating contact.", "Error", JOptionPane.ERROR_MESSAGE);
    }
});






      searchBTN.addActionListener((ActionEvent e) -> {
            // Search button clicked, implement functionality here
            String keyword = SearchField.getText();

            // Perform search in the database using the keyword
            List<Contact> searchResults = dbManager.searchContacts(keyword);

            // Update the table with the search results
            updateTableWithSearchResults(searchResults);
        });

        quitBTN.addActionListener((ActionEvent e) -> {
            // Quit button clicked, exit the application
            System.exit(0);
        });
      
     
     }
     
     
     private boolean isValidMobileNumber(String mobileNumber) {
        return mobileNumber.matches("\\d{11}");
    }
     private boolean isValidEmail(String email) {
        return email.matches(".*@gmail\\.com");
    }
     
      private void refreshTable() {
    // Get all contacts from the database
    List<Contact> contacts = dbManager.getAllContacts();

    // Clear the existing rows in the JTable
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    // Append the new data to the existing table without clearing it
    for (Contact contact : contacts) {
        Object[] rowData = {contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getMobileNumber(), contact.getJobTitle()};
        model.addRow(rowData);
    }
}

      
      private void updateTableWithSearchResults(List<Contact> searchResults) {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    // Clear existing rows only if there are search results
    if (!searchResults.isEmpty()) {
        model.setRowCount(0); // Clear existing rows
    }

    // Add search results to the table
    for (Contact contact : searchResults) {
        Object[] rowData = {contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getMobileNumber(), contact.getJobTitle()};
        model.addRow(rowData);
    }
}

    
     
     
     
      
        
        
      
      
    
   

      
      
      
      
     
     
     
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        addBTN = new javax.swing.JButton();
        deleteBTN = new javax.swing.JButton();
        updateBTN = new javax.swing.JButton();
        quitBTN = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        FNfield = new javax.swing.JTextField();
        Emailfield = new javax.swing.JTextField();
        LNfield = new javax.swing.JTextField();
        MNfield = new javax.swing.JTextField();
        SearchField = new javax.swing.JTextField();
        JTfield = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        searchBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addBTN.setText("ADD");

        deleteBTN.setText("DELETE");
        deleteBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBTNActionPerformed(evt);
            }
        });

        updateBTN.setText("UPDATE");

        quitBTN.setText("QUIT");

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel1.setText("Last Name");

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel2.setText("First Name");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel3.setText("Email");

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel4.setText("Job Title");

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel5.setText("Mobile Number");

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel6.setText("Search");

        Emailfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailfieldActionPerformed(evt);
            }
        });

        LNfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LNfieldActionPerformed(evt);
            }
        });

        SearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchFieldActionPerformed(evt);
            }
        });

        JTfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTfieldActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Email", "Mobile Number", "JobTitle"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        searchBTN.setText("SEARCH");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LNfield, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FNfield, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MNfield, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Emailfield, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTfield, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchField)))
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(addBTN)
                .addGap(18, 18, 18)
                .addComponent(deleteBTN)
                .addGap(18, 18, 18)
                .addComponent(updateBTN)
                .addGap(18, 18, 18)
                .addComponent(quitBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchBTN)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(FNfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(1, 1, 1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(LNfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Emailfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(MNfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(JTfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBTN)
                    .addComponent(deleteBTN)
                    .addComponent(updateBTN)
                    .addComponent(quitBTN)
                    .addComponent(searchBTN))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteBTNActionPerformed

    private void SearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchFieldActionPerformed

    private void JTfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTfieldActionPerformed

    private void LNfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LNfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LNfieldActionPerformed

    private void EmailfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailfieldActionPerformed

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Emailfield;
    private javax.swing.JTextField FNfield;
    private javax.swing.JTextField JTfield;
    private javax.swing.JTextField LNfield;
    private javax.swing.JTextField MNfield;
    private javax.swing.JTextField SearchField;
    private javax.swing.JButton addBTN;
    private javax.swing.JButton deleteBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton quitBTN;
    private javax.swing.JButton searchBTN;
    private javax.swing.JButton updateBTN;
    // End of variables declaration//GEN-END:variables
}
