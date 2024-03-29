/*
 * Acceso a Datos - Tarea UT6
 */
package com.ad06_tarea;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alba Tortosa
 */
public class Ventana extends javax.swing.JFrame {

    /**
     * Creates new form AD04_Tarea
     */
    public Ventana() {
        initComponents();
        
        //Abrimos una única conexión para toda la ejecución
        AccesoDatos.abrirConexion();
        //Inicializamos los desplegables de comunidades, provincias y municipios con los datos de la base de datos
        actualizarComboComunidades();
        actualizarComboProvincias(null);
        actualizarComboMunicipios(null, null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPlayas = new javax.swing.JTable();
        btConsulta = new javax.swing.JButton();
        comboComunidad = new javax.swing.JComboBox<>();
        comboProvincia = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        comboMunicipio = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 255, 204));
        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PLAYAS DE ESPAÑA");

        jLabel3.setText("Comunidad");

        jLabel4.setText("Provincia");

        tablaPlayas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Municipio", "Provincia", "Comunidad", "Logintud"
            }
        ));
        jScrollPane1.setViewportView(tablaPlayas);

        btConsulta.setText("BUSCAR");
        btConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConsultaActionPerformed(evt);
            }
        });

        comboComunidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboComunidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboComunidadActionPerformed(evt);
            }
        });

        comboProvincia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProvinciaActionPerformed(evt);
            }
        });

        jLabel5.setText("Municipio");

        comboMunicipio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboMunicipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMunicipioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboComunidad, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboMunicipio, 0, 249, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboComunidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(comboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(comboMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btConsulta))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // Cerramos la conexión cuando se cierra la aplicación
        AccesoDatos.cerrarConexion();

    }//GEN-LAST:event_formWindowClosed

    //Si se selecciona una comunidad, actualizamos los listados de provincias y municipios
    private void comboComunidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboComunidadActionPerformed
        String comunidadSeleccionada = (String) comboComunidad.getSelectedItem();
        actualizarComboProvincias(comunidadSeleccionada);
        actualizarComboMunicipios(comunidadSeleccionada, null);

    }//GEN-LAST:event_comboComunidadActionPerformed

    //Si se selecciona una provincia, seleccionamos la comunidad y actualizamos los municipios
    private void comboProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProvinciaActionPerformed
        //Obtenemos la provincia seleccionada
        String provinciaSeleccionada = (String) comboProvincia.getSelectedItem();
        String comunidadSeleccionada = (String) comboComunidad.getSelectedItem();;
        //Buscamos la comunidad asociada a la provincia seleccionada
        if (!provinciaSeleccionada.equals("TODAS")) {
            comunidadSeleccionada = AccesoDatos.buscarComunidad(provinciaSeleccionada);
                System.out.println("-Busqeuda comunidad "+comunidadSeleccionada);
        }
        //Seleccionamos la comunidad en el desplegable
        comboComunidad.setSelectedItem(comunidadSeleccionada);
        //Seleccionamos la provincia en el desplegable (se desconfigura al seleccionar la comunidad)
        comboProvincia.setSelectedItem(provinciaSeleccionada);

        //Actualizamos la lista de municipios de acuerdo a la provincia seleccionada
        actualizarComboMunicipios(comunidadSeleccionada, provinciaSeleccionada);
    }//GEN-LAST:event_comboProvinciaActionPerformed

    //Si se selecciona un municipio, seleccionamos la comunidad, actualizamos la lista de provincias y seleccionamos la provincia adecuada
    private void comboMunicipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMunicipioActionPerformed
        //Obtenemos el municipio seleccionado
        String municipioSeleccionado = (String) comboMunicipio.getSelectedItem();
        //Buscamos la comunidad y la provincia asociadas al municipio seleccionado
        String provinciaSeleccionada = AccesoDatos.buscarProvincia(municipioSeleccionado);
        String comunidadSeleccionada = AccesoDatos.buscarComunidad(provinciaSeleccionada);

        //Seleccionamos la comunidad en el desplegable
        comboComunidad.setSelectedItem(comunidadSeleccionada);
        //Seleccionamos la provincia en el desplegable
        comboProvincia.setSelectedItem(provinciaSeleccionada);
        //Seleccionamos el municipio en el desplegable (se desconfigura al seleccionar la comunidad y la provincia)
        comboMunicipio.setSelectedItem(municipioSeleccionado);

    }//GEN-LAST:event_comboMunicipioActionPerformed

    //Botón Buscar
    private void btConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultaActionPerformed
        
        //Obtenemos los elementos seleccionados de los listados y actualizo la tabla de playas
        String comunidadSeleccionada = (String) comboComunidad.getSelectedItem();
        String provinciaSeleccionada = (String) comboProvincia.getSelectedItem();
        String municipioSeleccionado = (String) comboMunicipio.getSelectedItem();
        actualizarTablaPlayas(comunidadSeleccionada, provinciaSeleccionada, municipioSeleccionado);

    }//GEN-LAST:event_btConsultaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConsulta;
    private javax.swing.JComboBox<String> comboComunidad;
    private javax.swing.JComboBox<String> comboMunicipio;
    private javax.swing.JComboBox<String> comboProvincia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPlayas;
    // End of variables declaration//GEN-END:variables

    public void actualizarTablaPlayas(String comunidad, String provincia, String municipio) {
        //Buscamos la lista de playas con los filtros introducidos
        ArrayList<Playa> resultList = AccesoDatos.buscarPlayas(comunidad, provincia, municipio);
        
        //Creamos la tabla y sus cabeceras
        Vector<String> tableHeaders = new Vector<String>();
        Vector tableData = new Vector();
        tableHeaders.add("Nombre");
        tableHeaders.add("Municipio");
        tableHeaders.add("Provincia");
        tableHeaders.add("Comunidad");
        tableHeaders.add("Longitud");
        
        //Añadimos las filas de playas
        for (Playa playa : resultList) {
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(playa.getNombre());
            oneRow.add(playa.getMunicipio());
            oneRow.add(playa.getProvincia());
            oneRow.add(playa.getComunidad());
            oneRow.add(playa.getLongitud());

            tableData.add(oneRow);
        }

        tablaPlayas.setModel(new DefaultTableModel(tableData, tableHeaders));

    }

    private void actualizarComboComunidades() {
        //Buscamos la lista completa de comunidades
        ArrayList<String> comunidades = AccesoDatos.buscarComunidades();
        //Añadimos la opción "TODAS" al comienzo de la lista
        comunidades.add(0, "TODAS");
        comboComunidad.setModel(new javax.swing.DefaultComboBoxModel<>(comunidades.toArray(new String[0])));
    }

    private void actualizarComboProvincias(String comunidad) {
        //Buscamos la lista de provincias según la comunidad seleccionada
        ArrayList<String> provincias = AccesoDatos.buscarProvincias(comunidad);
        //Añadimos la opción "TODAS" al comienzo de la lista
        provincias.add(0, "TODAS");       
        comboProvincia.setModel(new javax.swing.DefaultComboBoxModel<>(provincias.toArray(new String[0])));
    }

    private void actualizarComboMunicipios(String comunidad, String provincia) {
        //Buscamos la lista de municipios según la comunidad y/o provincia seleccionada
        ArrayList<String> municipios = AccesoDatos.buscarMunicipios(comunidad, provincia);
        //Añadimos la opción "TODOS" al comienzo de la lista
        municipios.add(0, "TODOS");
        comboMunicipio.setModel(new javax.swing.DefaultComboBoxModel<>(municipios.toArray(new String[0])));
    }

}
