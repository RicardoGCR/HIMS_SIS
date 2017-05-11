/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Programas;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Programas.LAB_Solicitud_Inv_Bact;
import modelos.Usuario;
import servicios.Conexion;

/**
 *
 * @author PC-SISTEMA
 */
public class frm_SOLICITUDES extends javax.swing.JFrame {
DefaultTableModel m,muestra;
Conexion c=new Conexion();
    /**
     * Creates new form frm_SOLICITUDES
     */
    public frm_SOLICITUDES() {
        initComponents();
        c.conectar();
        LAB_Solicitud_cargar();
        LAB_Solicitud_formato();
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        this.getContentPane().setBackground(Color.WHITE);
        
        //para no intercambiar columnas
        tb_Solicitudes.getTableHeader().setReorderingAllowed(false);
        
        //salir presionando escape
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();

            }
        });
    }
public void LAB_Solicitud_cargar(){
    try {
        String consulta="";
        tb_Solicitudes.setModel(new DefaultTableModel());
             String titulos[]={"Nº","cod_inv_bac", "cod_precio","Código CPT","Nomenclatura","cod_per","Personal Solicita","id_hc"
                     ,"Paciente" ,"H.C","DNI","Años","Sexo","Teléfono","Dirección","Provincia","Distrito","hosp_servicio"," ca_desc","dir_referencia","cod_muestra_para_exa","Muestra"
                     ,"Antecedentes de tratamiento","Diagnostico","mes_control_tratamiento ","Control_tratamiento",
                     "ex_nsolic_bacil","Ex Solicitado Bacil.","tipo_prueba_sens","prueba_sens","factores_riesgo_TB"
                     ,"Fecha de Solicitud","hora_soli","id_hc_solicita","Paciente Solicita","tel","cel","fecha_obtencion_muestra",
                     "hora_obtencion_muestra", "Calidad muestra", "Observaciones"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[42];

            LAB_Solicitud_Inv_Bact obj=new LAB_Solicitud_Inv_Bact();
            consulta="exec sp_SOLICITUD_INV_BACT_listar ?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, "");
            cmd.setString(2, "1");
            ResultSet r= cmd.executeQuery();
            int c=1;
        while(r.next()){
             fila[0]=String.valueOf(c)+"º";
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
                fila[6]=r.getString(6);
                fila[7]=r.getString(7);
                fila[8]=r.getString(8);
                fila[9]=r.getString(9);
                fila[10]=r.getString(10);
                fila[11]=r.getString(11);
                fila[12]=r.getString(12);
                fila[13]=r.getString(13);
                fila[14]=r.getString(14);
                fila[15]=r.getString(15);
                fila[16]=r.getString(16);
                fila[17]=r.getString(17);
                fila[18]=r.getString(18);
                fila[19]=r.getString(19);
                fila[20]=r.getString(20);
                fila[21]=r.getString(21);
                fila[22]=r.getString(22);
                fila[23]=r.getString(23);
                fila[24]=r.getString(24);
                fila[25]=r.getString(25);
                fila[26]=r.getString(26);
                fila[27]=r.getString(27);
                fila[28]=r.getString(28);
                fila[29]=r.getString(29);
                fila[30]=r.getString(30);
                fila[31]=r.getString(31);
                fila[32]=r.getString(32);
                fila[33]=r.getString(33);
                fila[34]=r.getString(34);
                fila[35]=r.getString(35);
                fila[36]=r.getString(36);
                fila[37]=r.getString(37);
                fila[38]=r.getString(38);
                fila[39]=r.getString(39);
                fila[40]=r.getString(40);
                m.addRow(fila);
                c++;
            }
            tb_Solicitudes.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Solicitudes.setRowSorter(elQueOrdena);
            this.tb_Solicitudes.setModel(m);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
    public void LAB_Solicitud_formato(){
    tb_Solicitudes.getColumnModel().getColumn(0).setPreferredWidth(30);
    tb_Solicitudes.getColumnModel().getColumn(3).setPreferredWidth(80);
    tb_Solicitudes.getColumnModel().getColumn(4).setPreferredWidth(130);
    tb_Solicitudes.getColumnModel().getColumn(6).setPreferredWidth(140);
    tb_Solicitudes.getColumnModel().getColumn(8).setPreferredWidth(150);
    tb_Solicitudes.getColumnModel().getColumn(9).setPreferredWidth(80);
    tb_Solicitudes.getColumnModel().getColumn(10).setPreferredWidth(80);
    tb_Solicitudes.getColumnModel().getColumn(11).setPreferredWidth(80);
    tb_Solicitudes.getColumnModel().getColumn(21).setPreferredWidth(90);
    tb_Solicitudes.getColumnModel().getColumn(31).setPreferredWidth(100);
            //Ocultar    
    tb_Solicitudes.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(5).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(5).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(7).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(7).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(12).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(12).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(13).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(13).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(14).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(14).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(15).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(15).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(16).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(16).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(17).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(17).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(18).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(18).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(19).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(19).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(20).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(20).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(22).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(22).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(23).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(23).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(24).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(24).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(25).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(25).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(26).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(26).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(27).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(27).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(28).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(28).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(29).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(29).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(30).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(30).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(32).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(32).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(33).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(33).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(34).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(34).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(35).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(35).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(36).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(36).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(37).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(37).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(38).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(38).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(39).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(39).setMaxWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(40).setMinWidth(0);
    tb_Solicitudes.getColumnModel().getColumn(40).setMaxWidth(0);
    tb_Solicitudes.getSelectionModel().setSelectionInterval(0, 0);
            tb_Solicitudes.requestFocus();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Solicitudes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            txtBuscarSolicitud = new javax.swing.JTextField();
            btnBuscarSolicitud = new javax.swing.JButton();
            cbxSolicitud = new javax.swing.JComboBox();
            jLabel2 = new javax.swing.JLabel();
            jpanel = new javax.swing.JPanel();
            titulo5 = new javax.swing.JLabel();
            lblTipo = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

            tb_Solicitudes.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            tb_Solicitudes.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ));
            tb_Solicitudes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tb_Solicitudes.setRowHeight(25);
            tb_Solicitudes.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tb_SolicitudesMouseClicked(evt);
                }
            });
            tb_Solicitudes.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tb_SolicitudesKeyPressed(evt);
                }
            });
            jScrollPane1.setViewportView(tb_Solicitudes);

            txtBuscarSolicitud.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtBuscarSolicitud.setForeground(new java.awt.Color(0, 51, 51));
            txtBuscarSolicitud.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtBuscarSolicitudActionPerformed(evt);
                }
            });
            txtBuscarSolicitud.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtBuscarSolicitudKeyPressed(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtBuscarSolicitudKeyTyped(evt);
                }
            });

            btnBuscarSolicitud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
            btnBuscarSolicitud.setMnemonic('B');
            btnBuscarSolicitud.setToolTipText("Buscar(Alt+B)");
            btnBuscarSolicitud.setBorder(null);
            btnBuscarSolicitud.setContentAreaFilled(false);
            btnBuscarSolicitud.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnBuscarSolicitud.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarSolicitudActionPerformed(evt);
                }
            });

            cbxSolicitud.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            cbxSolicitud.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Apellidos y Nombres del Paciente", " H.C /  DNI", "Codigo CPT / Nomenclatura", "Personal Solicita" }));
            cbxSolicitud.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    cbxSolicitudItemStateChanged(evt);
                }
            });
            cbxSolicitud.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxSolicitudActionPerformed(evt);
                }
            });

            jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel2.setText("Búsqueda por:");

            jpanel.setBackground(new java.awt.Color(2, 67, 115));

            titulo5.setBackground(new java.awt.Color(0, 102, 102));
            titulo5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
            titulo5.setForeground(new java.awt.Color(255, 255, 255));
            titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            titulo5.setText("Solicitudes de Investigación Bacteriológica");
            titulo5.setToolTipText("");
            titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

            javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
            jpanel.setLayout(jpanelLayout);
            jpanelLayout.setHorizontalGroup(
                jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(titulo5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jpanelLayout.setVerticalGroup(
                jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(titulo5, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(54, 54, 54)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbxSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(124, 124, 124)
                            .addComponent(txtBuscarSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscarSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(22, Short.MAX_VALUE))
                .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBuscarSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnBuscarSolicitud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void tb_SolicitudesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_SolicitudesMouseClicked

    }//GEN-LAST:event_tb_SolicitudesMouseClicked

    private void tb_SolicitudesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_SolicitudesKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{
                dispose();
                
                if( tb_Solicitudes.getRowCount()>0){
                    int filaselec=tb_Solicitudes.getSelectedRow();
                    
                    if(lblTipo.getText().equalsIgnoreCase("S")){
                    
                    frm_SOLICITUD_INVESTIGACION_BACT lme= new  frm_SOLICITUD_INVESTIGACION_BACT();
                    lme.setVisible(true);
                  frm_SOLICITUD_INVESTIGACION_BACT.lblCodigo.setText(tb_Solicitudes.getValueAt(filaselec, 1).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.lblCodPrecio.setText(tb_Solicitudes.getValueAt(filaselec,2).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.txtNomen.setText(tb_Solicitudes.getValueAt(filaselec,4).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.lblCodPer.setText(tb_Solicitudes.getValueAt(filaselec, 5).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.txtPersonal.setText(tb_Solicitudes.getValueAt(filaselec, 6).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.lblCodHC.setText(tb_Solicitudes.getValueAt(filaselec,7).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.txtPaciente.setText(tb_Solicitudes.getValueAt(filaselec,8).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.lblhc.setText(tb_Solicitudes.getValueAt(filaselec, 9).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.lblDNI.setText(tb_Solicitudes.getValueAt(filaselec,10).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.lblEdad.setText(tb_Solicitudes.getValueAt(filaselec,11).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.lblSexo.setText(tb_Solicitudes.getValueAt(filaselec,12).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.lblTelefono.setText(tb_Solicitudes.getValueAt(filaselec,13).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.lblDireccion.setText(tb_Solicitudes.getValueAt(filaselec,14).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.lblProvincia.setText(tb_Solicitudes.getValueAt(filaselec,15).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.lblDistrito.setText(tb_Solicitudes.getValueAt(filaselec,16).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.lblServicio.setText(tb_Solicitudes.getValueAt(filaselec, 17).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.lblCama.setText(tb_Solicitudes.getValueAt(filaselec, 18).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.txtReferencia.setText(tb_Solicitudes.getValueAt(filaselec,19).toString());
                
                frm_SOLICITUD_INVESTIGACION_BACT.lblCodTMEspecificar.setText(tb_Solicitudes.getValueAt(filaselec,20).toString());
                frm_SOLICITUD_INVESTIGACION_BACT.txtTMEspecificar.setText(tb_Solicitudes.getValueAt(filaselec, 21).toString());
                
                if(tb_Solicitudes.getValueAt(filaselec, 21).toString().equalsIgnoreCase("Esputo")){
                frm_SOLICITUD_INVESTIGACION_BACT.txtTMEsputo.setText("X");
                }else{
                frm_SOLICITUD_INVESTIGACION_BACT.txtTMOtro.setText("X");
                }
                
                frm_SOLICITUD_INVESTIGACION_BACT.lblCama.setText(tb_Solicitudes.getValueAt(filaselec, 18).toString());

                
                frm_SOLICITUD_INVESTIGACION_BACT.enableDatos(false);
                
                frm_SOLICITUD_INVESTIGACION_BACT.btnguardar.setEnabled(false);
                frm_SOLICITUD_INVESTIGACION_BACT.btnmodificar.setEnabled(true);
                frm_SOLICITUD_INVESTIGACION_BACT.btneliminar.setEnabled(true);
                    }else if(lblTipo.getText().equalsIgnoreCase("R")){
                        
                    }
                }
            }
            catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_SolicitudesKeyPressed

    private void txtBuscarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarSolicitudActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarSolicitudActionPerformed

    private void txtBuscarSolicitudKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarSolicitudKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_Solicitudes.getSelectionModel().setSelectionInterval(0, 0);
            tb_Solicitudes.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarSolicitudKeyPressed

    private void txtBuscarSolicitudKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarSolicitudKeyTyped
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscarSolicitud.doClick();
        }

    }//GEN-LAST:event_txtBuscarSolicitudKeyTyped

    private void btnBuscarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarSolicitudActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {

            tb_Solicitudes.setModel(new DefaultTableModel());
           String titulos[]={"Nº","cod_inv_bac", "cod_precio","Código CPT","Nomenclatura","cod_per","Personal Solicita","id_hc"
                     ,"Paciente" ,"H.C","DNI","Años","Sexo","Teléfono","Dirección","Provincia","Distrito","hosp_servicio"," ca_desc","dir_referencia","cod_muestra_para_exa","Muestra"
                     ,"Antecedentes de tratamiento","Diagnostico","mes_control_tratamiento ","Control_tratamiento",
                     "ex_nsolic_bacil","Ex Solicitado Bacil.","tipo_prueba_sens","prueba_sens","factores_riesgo_TB"
                     ,"Fecha de Solicitud","hora_soli","id_hc_solicita","Paciente Solicita","tel","cel","fecha_obtencion_muestra",
                     "hora_obtencion_muestra", "Calidad muestra", "Observaciones"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[42];

            LAB_Solicitud_Inv_Bact obj=new LAB_Solicitud_Inv_Bact();
            consulta="exec sp_SOLICITUD_INV_BACT_listar ?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarSolicitud.getText());
            cmd.setInt(2, cbxSolicitud.getSelectedIndex());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
                fila[6]=r.getString(6);
                fila[7]=r.getString(7);
                fila[8]=r.getString(8);
                fila[9]=r.getString(9);
                fila[10]=r.getString(10);
                fila[11]=r.getString(11);
                fila[12]=r.getString(12);
                fila[13]=r.getString(13);
                fila[14]=r.getString(14);
                fila[15]=r.getString(15);
                fila[16]=r.getString(16);
                fila[17]=r.getString(17);
                fila[18]=r.getString(18);
                fila[19]=r.getString(19);
                fila[20]=r.getString(20);
                fila[21]=r.getString(21);
                fila[22]=r.getString(22);
                fila[23]=r.getString(23);
                fila[24]=r.getString(24);
                fila[25]=r.getString(25);
                fila[26]=r.getString(26);
                fila[27]=r.getString(27);
                fila[28]=r.getString(28);
                fila[29]=r.getString(29);
                fila[30]=r.getString(30);
                fila[31]=r.getString(31);
                fila[32]=r.getString(32);
                fila[33]=r.getString(33);
                fila[34]=r.getString(34);
                fila[35]=r.getString(35);
                fila[36]=r.getString(36);
                fila[37]=r.getString(37);
                fila[38]=r.getString(38);
                fila[39]=r.getString(39);
                fila[40]=r.getString(40);
                m.addRow(fila);
                c++;
            }
            tb_Solicitudes.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Solicitudes.setRowSorter(elQueOrdena);
            this.tb_Solicitudes.setModel(m);

            LAB_Solicitud_formato();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarSolicitudActionPerformed

    private void cbxSolicitudItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSolicitudItemStateChanged
        // TODO add your handling code here:
        try
        {
            if(evt.getStateChange()==ItemEvent.SELECTED){
                if(this.cbxSolicitud.getSelectedIndex()>0){
                    txtBuscarSolicitud.setVisible(true);
                    btnBuscarSolicitud.setVisible(true);
                }

            }
            else{
                txtBuscarSolicitud.setVisible(false);
                btnBuscarSolicitud.setVisible(false);
            }}
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxSolicitudItemStateChanged

    private void cbxSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSolicitudActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSolicitudActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_SOLICITUDES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_SOLICITUDES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_SOLICITUDES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_SOLICITUDES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_SOLICITUDES().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarSolicitud;
    private javax.swing.JComboBox cbxSolicitud;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpanel;
    public static javax.swing.JLabel lblTipo;
    public static javax.swing.JTable tb_Solicitudes;
    private javax.swing.JLabel titulo5;
    private javax.swing.JTextField txtBuscarSolicitud;
    // End of variables declaration//GEN-END:variables
}
