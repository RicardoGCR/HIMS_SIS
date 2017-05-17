/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.LABORATORIO;

import vista.Programas.*;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Programas.FormatoTablasSolic;
import modelos.Programas.LAB_Solicitud_Inv_Bact;
import modelos.Usuario;
import servicios.Conexion;
import vista.LABORATORIO.frm_RECEPCION_INVESTIGACION_BACT;
import vista.PrincipalMDI;

/**
 *
 * @author PC-SISTEMA
 */
public class frm_RECEP_SOLICITUDES extends javax.swing.JFrame {
DefaultTableModel m,muestra;
Conexion c=new Conexion();
    /**
     * Creates new form frm_SOLICITUDES
     */
    public frm_RECEP_SOLICITUDES() {
        initComponents();
        c.conectar();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/laboratorio.png")).getImage());
        LAB_Solicitud_cargar();
        LAB_Solicitud_formato();
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        this.getContentPane().setBackground(Color.WHITE);
        
        //color a filas
        tb_Solicitudes.setDefaultRenderer(Object.class,new FormatoTablasSolic());
        
        
        
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
    tb_Solicitudes.getColumnModel().getColumn(8).setPreferredWidth(170);
    tb_Solicitudes.getColumnModel().getColumn(9).setPreferredWidth(80);
    tb_Solicitudes.getColumnModel().getColumn(10).setPreferredWidth(80);
    tb_Solicitudes.getColumnModel().getColumn(11).setPreferredWidth(80);
    tb_Solicitudes.getColumnModel().getColumn(21).setPreferredWidth(90);
    tb_Solicitudes.getColumnModel().getColumn(31).setPreferredWidth(120);
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
            jTextField1 = new javax.swing.JTextField();
            jLabel1 = new javax.swing.JLabel();

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

            jTextField1.setEditable(false);
            jTextField1.setBackground(new java.awt.Color(224, 196, 141));
            jTextField1.setForeground(new java.awt.Color(224, 196, 136));
            jTextField1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            jTextField1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextField1ActionPerformed(evt);
                }
            });

            jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            jLabel1.setText("Pendiente en Laboratorio");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbxSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtBuscarSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscarSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(105, 105, 105)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel1)
                            .addGap(0, 4, Short.MAX_VALUE)))
                    .addContainerGap())
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
                        .addComponent(jTextField1)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addContainerGap())
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
                   
                   
                         frm_RECEPCION_INVESTIGACION_BACT recp= new  frm_RECEPCION_INVESTIGACION_BACT();
                    recp.setVisible(true);
                        
                frm_RECEPCION_INVESTIGACION_BACT.lblCodigo.setText(tb_Solicitudes.getValueAt(filaselec, 1).toString());
                frm_RECEPCION_INVESTIGACION_BACT.lblCodPrecio.setText(tb_Solicitudes.getValueAt(filaselec,2).toString());
                frm_RECEPCION_INVESTIGACION_BACT.txtNomen.setText(tb_Solicitudes.getValueAt(filaselec,4).toString());
                frm_RECEPCION_INVESTIGACION_BACT.lblCodPer.setText(tb_Solicitudes.getValueAt(filaselec, 5).toString());
                frm_RECEPCION_INVESTIGACION_BACT.txtPersonal.setText(tb_Solicitudes.getValueAt(filaselec, 6).toString());
                frm_RECEPCION_INVESTIGACION_BACT.lblCodHC.setText(tb_Solicitudes.getValueAt(filaselec,7).toString());
                frm_RECEPCION_INVESTIGACION_BACT.txtPaciente.setText(tb_Solicitudes.getValueAt(filaselec,8).toString());
                frm_RECEPCION_INVESTIGACION_BACT.lblhc.setText(tb_Solicitudes.getValueAt(filaselec, 9).toString());
                frm_RECEPCION_INVESTIGACION_BACT.lblDNI.setText(tb_Solicitudes.getValueAt(filaselec,10).toString());
                frm_RECEPCION_INVESTIGACION_BACT.lblEdad.setText(tb_Solicitudes.getValueAt(filaselec,11).toString());
                frm_RECEPCION_INVESTIGACION_BACT.lblSexo.setText(tb_Solicitudes.getValueAt(filaselec,12).toString());
                frm_RECEPCION_INVESTIGACION_BACT.lblTelefono.setText(tb_Solicitudes.getValueAt(filaselec,13).toString());
                frm_RECEPCION_INVESTIGACION_BACT.lblDireccion.setText(tb_Solicitudes.getValueAt(filaselec,14).toString());
                frm_RECEPCION_INVESTIGACION_BACT.lblProvincia.setText(tb_Solicitudes.getValueAt(filaselec,15).toString());
                frm_RECEPCION_INVESTIGACION_BACT.lblDistrito.setText(tb_Solicitudes.getValueAt(filaselec,16).toString());
                frm_RECEPCION_INVESTIGACION_BACT.lblServicio.setText(tb_Solicitudes.getValueAt(filaselec, 17).toString());
                frm_RECEPCION_INVESTIGACION_BACT.lblCama.setText(tb_Solicitudes.getValueAt(filaselec, 18).toString());
                frm_RECEPCION_INVESTIGACION_BACT.txtReferencia.setText(tb_Solicitudes.getValueAt(filaselec,19).toString());
                
                frm_RECEPCION_INVESTIGACION_BACT.lblCodTMEspecificar.setText(tb_Solicitudes.getValueAt(filaselec,20).toString());
                
                if(tb_Solicitudes.getValueAt(filaselec, 21).toString().equalsIgnoreCase("Esputo")){
                frm_RECEPCION_INVESTIGACION_BACT.txtTMEsputo.setText("X");
                }else{
                frm_RECEPCION_INVESTIGACION_BACT.txtTMOtro.setText("X");
                frm_RECEPCION_INVESTIGACION_BACT.txtTMEspecificar.setText(tb_Solicitudes.getValueAt(filaselec, 21).toString());
                
                }
                
                //Antecedentes de Tratamiento
                if(tb_Solicitudes.getValueAt(filaselec, 22).toString().equalsIgnoreCase("Nunca Tratado")){
                frm_RECEPCION_INVESTIGACION_BACT.txtATNuncaT.setText("X");
                }else if(tb_Solicitudes.getValueAt(filaselec, 22).toString().equalsIgnoreCase("Antes Tratado: Recaída")){
                frm_RECEPCION_INVESTIGACION_BACT.txtATAntesT.setText("X");
                }else if(tb_Solicitudes.getValueAt(filaselec, 22).toString().equalsIgnoreCase("Abandono Recup.")){
                frm_RECEPCION_INVESTIGACION_BACT.txtATAbandonoR.setText("X");
                }else if(tb_Solicitudes.getValueAt(filaselec, 22).toString().equalsIgnoreCase("Fracaso")){
                frm_RECEPCION_INVESTIGACION_BACT.txtATFracaso.setText("X");
                }
                 //Diagnostico
                if(tb_Solicitudes.getValueAt(filaselec, 23).toString().equalsIgnoreCase("S.R.")){
                frm_RECEPCION_INVESTIGACION_BACT.txtDSR.setText("X");
                }else if(tb_Solicitudes.getValueAt(filaselec, 23).toString().equalsIgnoreCase("Seg. Diagnóstico")){
                frm_RECEPCION_INVESTIGACION_BACT.txtDSegDia.setText("X");
                }else if(tb_Solicitudes.getValueAt(filaselec, 23).toString().equalsIgnoreCase("RX Anormal")){
                frm_RECEPCION_INVESTIGACION_BACT.txtDRxAnormal.setText("X");
                }else {
                frm_RECEPCION_INVESTIGACION_BACT.txtDOtro.setText("X");
                frm_RECEPCION_INVESTIGACION_BACT.txtDEspecificar.setText(tb_Solicitudes.getValueAt(filaselec, 23).toString());
                }
                //mes control de tratamiento
                if(Integer.parseInt(tb_Solicitudes.getValueAt(filaselec, 24).toString())==0){
                frm_RECEPCION_INVESTIGACION_BACT.txtCTMes.setText("");
                }else{
                   frm_RECEPCION_INVESTIGACION_BACT.txtCTMes.setText(tb_Solicitudes.getValueAt(filaselec, 24).toString()); 
                }
                if(tb_Solicitudes.getValueAt(filaselec, 25).toString().equalsIgnoreCase("Esq. TB sensible")){
                frm_RECEPCION_INVESTIGACION_BACT.txtCTEsqTB.setText("X");
                }else if(tb_Solicitudes.getValueAt(filaselec, 25).toString().equalsIgnoreCase("Esq. DR")){
                frm_RECEPCION_INVESTIGACION_BACT.txtCTEsqDR.setText("X");
                }else if(tb_Solicitudes.getValueAt(filaselec, 25).toString().equalsIgnoreCase("Esq. MDR")){
                frm_RECEPCION_INVESTIGACION_BACT.txtCTEsqMDR.setText("X");
                }else if(tb_Solicitudes.getValueAt(filaselec, 25).toString().equalsIgnoreCase("Esq. XDR")){
                frm_RECEPCION_INVESTIGACION_BACT.txtCTEsqXDR.setText("X");
                }else if(tb_Solicitudes.getValueAt(filaselec, 25).toString().equalsIgnoreCase("Otro")){
                frm_RECEPCION_INVESTIGACION_BACT.txtCTOtros.setText("X");
                }
                
                 //N Solic Baciloscopia
                if(tb_Solicitudes.getValueAt(filaselec, 26).toString().equalsIgnoreCase("1")){
                frm_RECEPCION_INVESTIGACION_BACT.txtSB1.setText("X");
                }else if(tb_Solicitudes.getValueAt(filaselec, 26).toString().equalsIgnoreCase("2")){
                frm_RECEPCION_INVESTIGACION_BACT.txtSB2.setText("X");
                }else if(tb_Solicitudes.getValueAt(filaselec, 26).toString().equalsIgnoreCase("0")){
                    //
                }else {
                  frm_RECEPCION_INVESTIGACION_BACT.txtSBOtras.setText(tb_Solicitudes.getValueAt(filaselec, 26).toString());  
                }
                
                //Cultivo
                if(tb_Solicitudes.getValueAt(filaselec, 27).toString().equalsIgnoreCase("Cultivo")){
                frm_RECEPCION_INVESTIGACION_BACT.txtSBCultivo.setText("X");
                }
                
                //Tipo Prueba de Sensibilidad
                if(tb_Solicitudes.getValueAt(filaselec, 28).toString().equalsIgnoreCase("Rapida")){
                frm_RECEPCION_INVESTIGACION_BACT.txtPSRapida.setText("X");
                frm_RECEPCION_INVESTIGACION_BACT.txtPSREspecificar.setText(tb_Solicitudes.getValueAt(filaselec, 29).toString());
                } else if(tb_Solicitudes.getValueAt(filaselec, 28).toString().equalsIgnoreCase("Convencional")){
                frm_RECEPCION_INVESTIGACION_BACT.txtPSConvencional.setText("X");
                frm_RECEPCION_INVESTIGACION_BACT.txtPSCEspecificar.setText(tb_Solicitudes.getValueAt(filaselec, 29).toString());
                }else if(tb_Solicitudes.getValueAt(filaselec, 28).toString().equalsIgnoreCase("Otro")){
                frm_RECEPCION_INVESTIGACION_BACT.txtPSOtroEspecificar.setText(tb_Solicitudes.getValueAt(filaselec, 29).toString());
                }
                
                frm_RECEPCION_INVESTIGACION_BACT.txtFactores.setText(tb_Solicitudes.getValueAt(filaselec, 30).toString());
                
                //Paciente Solicita
                frm_RECEPCION_INVESTIGACION_BACT.lblCodHCSol.setText(tb_Solicitudes.getValueAt(filaselec, 33).toString());
                frm_RECEPCION_INVESTIGACION_BACT.txtPacienteSol.setText(tb_Solicitudes.getValueAt(filaselec, 34).toString());
                frm_RECEPCION_INVESTIGACION_BACT.lblTelefonoSol.setText(tb_Solicitudes.getValueAt(filaselec, 35).toString());
                frm_RECEPCION_INVESTIGACION_BACT.lblCel.setText(tb_Solicitudes.getValueAt(filaselec, 36).toString());
                if(tb_Solicitudes.getValueAt(filaselec, 37).toString().equalsIgnoreCase("          ")==false){
                frm_RECEPCION_INVESTIGACION_BACT.lblFechaObtencion.setText(tb_Solicitudes.getValueAt(filaselec, 37).toString());
                }         
                
                //Calidad de la Muestra
                if(tb_Solicitudes.getValueAt(filaselec, 39).toString().equalsIgnoreCase("Adecuada")){
                frm_RECEPCION_INVESTIGACION_BACT.txtMAdecuada.setText("X");
                }else if(tb_Solicitudes.getValueAt(filaselec, 39).toString().equalsIgnoreCase("Inadecuada")){
                frm_RECEPCION_INVESTIGACION_BACT.txtMInadecuada.setText("X");
                }
                
                 frm_RECEPCION_INVESTIGACION_BACT.txtObservaciones.setText(tb_Solicitudes.getValueAt(filaselec, 40).toString());
                
                
                frm_RECEPCION_INVESTIGACION_BACT.enableDatos(false);
                
                    
                 String u=PrincipalMDI.lblUsu.getText();
        frm_RECEPCION_INVESTIGACION_BACT.lblUsu.setText(u);
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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(frm_RECEP_SOLICITUDES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_RECEP_SOLICITUDES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_RECEP_SOLICITUDES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_RECEP_SOLICITUDES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_RECEP_SOLICITUDES().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarSolicitud;
    private javax.swing.JComboBox cbxSolicitud;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel jpanel;
    public static javax.swing.JTable tb_Solicitudes;
    private javax.swing.JLabel titulo5;
    private javax.swing.JTextField txtBuscarSolicitud;
    // End of variables declaration//GEN-END:variables
}
