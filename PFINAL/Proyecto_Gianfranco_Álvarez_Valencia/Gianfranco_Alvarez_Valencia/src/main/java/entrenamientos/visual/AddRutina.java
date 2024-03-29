/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package entrenamientos.visual;

import entrenamientos.AccesoDatosSql;
import javax.swing.JOptionPane;

/**
 *
 * @author gianf
 */
public class AddRutina extends javax.swing.JFrame {

    /**
     * Creates new form addRutina
     */
    public AddRutina(Ventana ventanaPadre, String idCliente) {
        parent = ventanaPadre;
        clienteId = idCliente;
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelAddRutina = new javax.swing.JLabel();
        jLabelCodRutina = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCodRutina = new javax.swing.JTextField();
        jTextFieldFrecuencia = new javax.swing.JTextField();
        jButtonAddRutina = new javax.swing.JButton();
        jButtonsalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelAddRutina.setText("Añadir nueva rutina");

        jLabelCodRutina.setText("Código Rutina:");

        jLabel1.setText("Frecuencia semana: ");

        jTextFieldCodRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodRutinaActionPerformed(evt);
            }
        });

        jButtonAddRutina.setText("Añadir");
        jButtonAddRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddRutinaActionPerformed(evt);
            }
        });

        jButtonsalir.setText("Salir");
        jButtonsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                .addComponent(jTextFieldFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelCodRutina)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldCodRutina, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jLabelAddRutina))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jButtonAddRutina)
                                .addGap(37, 37, 37)
                                .addComponent(jButtonsalir)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabelAddRutina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodRutina)
                    .addComponent(jTextFieldCodRutina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldFrecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddRutina)
                    .addComponent(jButtonsalir))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCodRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodRutinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodRutinaActionPerformed

    private void jButtonAddRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddRutinaActionPerformed
        // TODO add your handling code here:
        if(!(jTextFieldCodRutina.getText().toString().equals("") && jTextFieldFrecuencia.getText().equals("")))
        {
            if(!(isNumeric(jTextFieldFrecuencia.getText())))
                JOptionPane.showMessageDialog(null, "El campo frecuencia tiene que ser un numero", "", JOptionPane.ERROR_MESSAGE);
            else
            {
                AccesoDatosSql.addRutina(jTextFieldCodRutina.getText().toString(),clienteId, jTextFieldFrecuencia.getText().toString());
                parent.actualizarTablaRutinas(clienteId);
                dispose();
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Hay que relleanr todos los campos", "", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jButtonAddRutinaActionPerformed

    private void jButtonsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButtonsalirActionPerformed

    /**
     * @param args the command line arguments
     */
    /** Funcion para comprobar que un striung es valdio como número
     * @param args the command line arguments
     */
    
    public static boolean isNumeric(String s)
    {
        if (s == null || s.equals("")) {
            return false;
        }
 
        return s.chars().allMatch(Character::isDigit);
    }
    
    private Ventana parent;
    private String clienteId = "";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddRutina;
    private javax.swing.JButton jButtonsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelAddRutina;
    private javax.swing.JLabel jLabelCodRutina;
    private javax.swing.JTextField jTextFieldCodRutina;
    private javax.swing.JTextField jTextFieldFrecuencia;
    // End of variables declaration//GEN-END:variables
}
