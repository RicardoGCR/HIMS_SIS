/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.RX_EC;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import modelos.RX_EC.*;
import modelos.Usuario;
import vista.RX_EC.RX_EC_EXAMEN_CAB;

/**
 *
 * @author MYS3
 */
public class RX_EC_BUSCAR_EXAMEN_C extends javax.swing.JFrame implements Runnable{
Conexion conectar=new Conexion();
Connection con;
String hora, minutos, segundos, ampm;
Calendar calendario;
Thread h1;
ResultSet r;
CallableStatement cst;
DefaultTableModel m, msb,m2, m3, m4;
static RX_EC_BUSCAR_EXAMEN_CAJA DT = new RX_EC_BUSCAR_EXAMEN_CAJA();  
/**
     * Creates new form BUSCAR_EXAMEN_C
     */
    public RX_EC_BUSCAR_EXAMEN_C() throws UnknownHostException {
        initComponents();
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        con=conectar.conectar();
        mostrarArea();
        inicializar_tabla_Examenes();
        seleccion();
        mostrarPacientes();
        
        lblFecha.setVisible(false);
        lblHora.setVisible(false);
        lblNumeArea.setVisible(false);
        
//////obtener el nombre de la pc
//        InetAddress localHost = InetAddress.getLocalHost();
//        txtNomPC.setText(localHost.getHostName());
        
         //FECHA Y HORA
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance();          
        lblFecha.setText(fechaActual());
        
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
        jTable1 = new javax.swing.JTable();
        jpanel = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        lblUsu = new javax.swing.JLabel();
        fecha_inicio = new com.toedter.calendar.JDateChooser();
        fecha_fin = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscarPaciente = new javax.swing.JTextField();
        btnBuscarP = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblNomArea = new javax.swing.JLabel();
        lblNumeArea = new javax.swing.JLabel();
        cbFecha = new javax.swing.JCheckBox();
        lblFecha = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Examenes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jpanel.setBackground(new java.awt.Color(40, 112, 99));

        titulo5.setBackground(new java.awt.Color(0, 102, 102));
        titulo5.setFont(new java.awt.Font("Palatino Linotype", 0, 28)); // NOI18N
        titulo5.setForeground(new java.awt.Color(255, 255, 255));
        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo5.setText("Examenes RX");
        titulo5.setToolTipText("");
        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblUsu.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        lblUsu.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
        lblUsu.setText("Usuario");

        fecha_inicio.setDateFormatString("dd-MM-yyyy");
        fecha_inicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fecha_inicioPropertyChange(evt);
            }
        });
        fecha_inicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fecha_inicioKeyTyped(evt);
            }
        });

        fecha_fin.setDateFormatString("dd-MM-yyyy");
        fecha_fin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fecha_finKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FECHA INICIO");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("FECHA TERMINO");

        txtBuscarPaciente.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarPacienteCaretUpdate(evt);
            }
        });
        txtBuscarPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarPacienteKeyTyped(evt);
            }
        });

        btnBuscarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
        btnBuscarP.setContentAreaFilled(false);
        btnBuscarP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("AM, DNI , HC, APELLIDOS Y NOMBRES");

        lblNomArea.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNomArea.setForeground(new java.awt.Color(255, 255, 255));
        lblNomArea.setText("jLabel3");

        lblNumeArea.setForeground(new java.awt.Color(255, 255, 255));
        lblNumeArea.setText("jLabel3");

        cbFecha.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        cbFecha.setForeground(new java.awt.Color(255, 255, 255));
        cbFecha.setText("BUSCAR POR RANGO DE FECHAS");
        cbFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFechaActionPerformed(evt);
            }
        });

        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("jLabel3");

        lblHora.setForeground(new java.awt.Color(255, 255, 255));
        lblHora.setText("jLabel3");

        javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
        jpanel.setLayout(jpanelLayout);
        jpanelLayout.setHorizontalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(fecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFecha)
                    .addComponent(titulo5))
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblHora)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNumeArea)
                                .addGap(48, 48, 48)))
                        .addComponent(lblNomArea, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(fecha_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtBuscarPaciente)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jpanelLayout.setVerticalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLayout.createSequentialGroup()
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(titulo5)
                        .addComponent(lblNumeArea))
                    .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFecha)
                        .addComponent(lblHora)))
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(0, 0, 0)
                        .addComponent(txtBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpanelLayout.createSequentialGroup()
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBuscarP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addComponent(cbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(0, 0, 0)
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fecha_fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNomArea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tb_Examenes.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_Examenes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tb_Examenes.setSelectionBackground(new java.awt.Color(40, 112, 99));
        tb_Examenes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_ExamenesKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tb_Examenes);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("EXAMENES DEL DIA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(364, 364, 364))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPActionPerformed
        try{
          
         String fecha = lblFecha.getText();
         int diaAC = Integer.parseInt(fecha.substring(0, 2));
         int mesAC = Integer.parseInt(fecha.substring(3, 5));
         int anioAC = Integer.parseInt(fecha.substring(6, 10));
        
         int diaIN = fecha_inicio.getCalendar().get(Calendar.DAY_OF_MONTH);
         int mesIN = fecha_inicio.getCalendar().get(Calendar.MONTH) + 1;
         int anioIN = fecha_inicio.getCalendar().get(Calendar.YEAR);
         
         int diaFN = fecha_fin.getCalendar().get(Calendar.DAY_OF_MONTH);
         int mesFN = fecha_fin.getCalendar().get(Calendar.MONTH) + 1;
         int anioFN = fecha_fin.getCalendar().get(Calendar.YEAR);
         

            if(fecha_inicio.getDate()==null || fecha_fin.getDate()==null){
              JOptionPane.showMessageDialog(rootPane, "Seleccione un rango de Fechas.");
            }else{
                 if(diaIN > diaAC || mesIN > mesAC || anioIN > anioAC){
                        JOptionPane.showMessageDialog(rootPane, "Seleccione una fecha de inicio \n menor a la actual: " + diaAC + "-" +
                        mesAC + "-" + anioAC);
                        fecha_inicio.setDate(null);
                 }else{
                       if(diaFN > diaAC || mesFN > mesAC || anioFN > anioAC){
                                JOptionPane.showMessageDialog(rootPane, "Seleccione una fecha de termino \n menor a la actual: " + diaAC + "-" +
                                mesAC + "-" + anioAC);
                                fecha_fin.setDate(null);
                       }else{
                                buscar_examen();
                       }  
                }
             
            }        
         
          }catch(Exception e) {
              JOptionPane.showMessageDialog(this, e.getMessage());           
          }
    }//GEN-LAST:event_btnBuscarPActionPerformed

    private void fecha_inicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fecha_inicioPropertyChange
    //validar_fecha();
    }//GEN-LAST:event_fecha_inicioPropertyChange

    private void txtBuscarPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarPacienteCaretUpdate
    BuscarPacientesDIA();
    }//GEN-LAST:event_txtBuscarPacienteCaretUpdate

    private void cbFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFechaActionPerformed
       seleccion();
    }//GEN-LAST:event_cbFechaActionPerformed

    private void tb_ExamenesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ExamenesKeyPressed
       char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){  
                    try{
                        if( tb_Examenes.getRowCount()>0){
                        int filaselec=tb_Examenes.getSelectedRow();
                        
                           dispose();
                            RX_EC_EXAMEN_CAB rx=new RX_EC_EXAMEN_CAB();
                            rx.setVisible(true);
                            RX_EC_EXAMEN_CAB.txtHC.setText(tb_Examenes.getValueAt(filaselec, 1).toString());
                            RX_EC_EXAMEN_CAB.txtNombreP.setText(tb_Examenes.getValueAt(filaselec, 2).toString());
                            RX_EC_EXAMEN_CAB.txtDNI.setText(tb_Examenes.getValueAt(filaselec, 3).toString());
                            RX_EC_EXAMEN_CAB.txtFechaNac.setText(tb_Examenes.getValueAt(filaselec, 4).toString());
                            RX_EC_EXAMEN_CAB.txtEdad.setText(tb_Examenes.getValueAt(filaselec, 5).toString());
                            RX_EC_EXAMEN_CAB.txtGenero.setText(tb_Examenes.getValueAt(filaselec, 6).toString());
                            RX_EC_EXAMEN_CAB.txtAM.setText(tb_Examenes.getValueAt(filaselec, 7).toString());
                            RX_EC_EXAMEN_CAB.txtCodigoDoc.setText(tb_Examenes.getValueAt(filaselec, 9).toString());
                            
                            RX_EC_EXAMEN_CAB.lblIDArea.setText(lblNumeArea.getText());
                            RX_EC_EXAMEN_CAB.lblNomA.setText(lblNomArea.getText());


                                  String u=lblUsu.getText();
                                  RX_EC_EXAMEN_CAB.lblUsu.setText(u);
                        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "cargar"+e.getMessage());
        }
        }
                
        
    }//GEN-LAST:event_tb_ExamenesKeyPressed

    private void fecha_inicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecha_inicioKeyTyped
//        char tecla= evt.getKeyChar();
//        if(tecla==KeyEvent.VK_ENTER){
//            btnBuscarP.doClick();
//        }
    }//GEN-LAST:event_fecha_inicioKeyTyped

    private void fecha_finKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecha_finKeyTyped

    }//GEN-LAST:event_fecha_finKeyTyped

    private void txtBuscarPacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPacienteKeyTyped
     char tecla= evt.getKeyChar();
       if(tecla==KeyEvent.VK_ENTER){
            tb_Examenes.getSelectionModel().setSelectionInterval(0, 0);
            tb_Examenes.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarPacienteKeyTyped

    public void seleccion(){
        if(cbFecha.isSelected()==true){
            fecha_inicio.setEnabled(true);
            fecha_fin.setEnabled(true);
        }else{
            fecha_inicio.setEnabled(false);
            fecha_fin.setEnabled(false);
        }
    }
    
    public void buscar_examen(){
    
           int dia = fecha_inicio.getCalendar().get(Calendar.DAY_OF_MONTH);
           int mes = fecha_inicio.getCalendar().get(Calendar.MONTH) + 1;
           int anio = fecha_inicio.getCalendar().get(Calendar.YEAR);
                       
           String fechaI;
                       
                       if(mes<10 && dia<10){
                           fechaI = "0" + dia + "/"+ "0" + mes + "/" + anio;
                       }else{                           
                           if(mes<10 && dia>=10){
                           fechaI = dia + "/" +"0"+ mes + "/" + anio;
                           }else{
                               if(mes >=10 && dia<10){
                                 fechaI = "0"+ dia + "/" + mes + "/" + anio;
                               }else{
                                   fechaI = dia + "/" + mes + "/" + anio;
                               }
                           }
                       }

                       
           int dia1 = fecha_fin.getCalendar().get(Calendar.DAY_OF_MONTH);
           int mes1 = fecha_fin.getCalendar().get(Calendar.MONTH) + 1;
           int anio1 = fecha_fin.getCalendar().get(Calendar.YEAR);
                       
           String fechaF;
                       
                       if(mes1<10 && dia1<10){
                           fechaF = "0" + dia1 + "/"+ "0" + mes1 + "/" + anio1;
                       }else{                           
                           if(mes1<10 && dia1>=10){
                           fechaF = dia1 + "/" +"0"+ mes1 + "/" + anio1;
                           }else{
                               if(mes1 >=10 && dia1<10){
                                 fechaF = "0"+ dia1 + "/" + mes1 + "/" + anio1;
                               }else{
                                   fechaF = dia1 + "/" + mes1 + "/" + anio1;
                               }
                           }
                       }

                       
        
        String buscar="",servicioArea="";
        buscar = txtBuscarPaciente.getText();
        servicioArea = lblNumeArea.getText();
        
        
        
    String consulta="";
        try {
            tb_Examenes.setModel(new DefaultTableModel());
            String titulos[]={"ID_HC","N° HC","Nombre del Paciente","DNI","Fecha Nac.","Edad","Sexo",
            "Acto Médico","Cant. Examenes","Fecha Examen","Codigo Documento"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[11];

            RX_EC_BUSCAR_EXAMEN_CAJA obj=new RX_EC_BUSCAR_EXAMEN_CAJA();
            consulta="exec RX_EC_BUSCAR_CAJA_RX ?,?,?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1,fechaI);
            cmd.setString(2, fechaF);
            cmd.setString(3, buscar);
            cmd.setString(4, servicioArea);
            
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){               
                fila[0]=r.getString(1);
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7);
                fila[7]=r.getString(8);
                fila[8]=r.getString(9);
                fila[9]=r.getString(10);
                fila[10]=r.getString(11);
                
                m.addRow(fila);
                c++;
            }
            tb_Examenes.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Examenes.setRowSorter(elQueOrdena);
            this.tb_Examenes.setModel(m);
            
            formatoExamen();
        } catch (Exception e) {
            System.out.println("Error buscar examen: " + e.getMessage());
        }
    }
    
    public void BuscarPacientesDIA(){
        try {
                     
            String consulta="";
            
            tb_Examenes.setModel(new DefaultTableModel());
            String titulos[]={"ID_HC","N° HC","Nombre del Paciente","DNI","Fecha Nac.","Edad","Sexo",
            "Acto Médico","Cant. Examenes","Fecha Examen","Codigo Documento"};
            m2=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m2);
            String fila[]=new String[11];
            Usuario obj=new Usuario();
            consulta="exec RX_EC_BUSCAR_CAJA_RX_DIA ?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);            
            cmd.setString(1, txtBuscarPaciente.getText());
            cmd.setString(2, lblNumeArea.getText());
            
            ResultSet r= cmd.executeQuery();
            while(r.next()){
            for (int i=0; i<11; i++){
            fila[i]=r.getString(i+1);
            }
                m2.addRow(fila);
            }
            tb_Examenes.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m2);
            tb_Examenes.setRowSorter(elQueOrdena);
            tb_Examenes.setModel(m2);
                       
            formatoExamen();
            
        } catch (Exception e) {
            System.out.println("Error mostrar paciente: " + e.getMessage());
        }
        
    }
    
        public void BuscarPacientes(){
        try {
                     
            String consulta="";
            
            tb_Examenes.setModel(new DefaultTableModel());
            String titulos[]={"ID_HC","N° HC","Nombre del Paciente","DNI","Fecha Nac.","Edad","Sexo",
            "Acto Médico","Cant. Examenes","Fecha Examen","Codigo Documento"};
            m2=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m2);
            String fila[]=new String[11];
            Usuario obj=new Usuario();
            consulta="exec RX_EC_BUSCAR_CAJA_RX_DIA ?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);            
            cmd.setString(1, txtBuscarPaciente.getText());
            cmd.setString(2, lblNumeArea.getText());
            
            ResultSet r= cmd.executeQuery();
            while(r.next()){
            for (int i=0; i<11; i++){
            fila[i]=r.getString(i+1);
            }
                m2.addRow(fila);
            }
            tb_Examenes.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m2);
            tb_Examenes.setRowSorter(elQueOrdena);
            tb_Examenes.setModel(m2);
                       
            formatoExamen();
            
        } catch (Exception e) {
            System.out.println("Error mostrar paciente: " + e.getMessage());
        }
        
    }
    
    public void mostrarArea(){
        String consulta="";
        try {
            consulta="EXEC RX_EC_SERVICIO ";
            PreparedStatement cmd = DT.getCn().prepareStatement(consulta);        
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblNumeArea.setText(r.getString(1));
                lblNomArea.setText(r.getString(2));
            }
            
        } catch (Exception e) {
            System.out.println("Error carga area : " + e.getMessage());
        }
    }   
    
    public void inicializar_tabla_Examenes(){       
        try {
            
            String titulosb[]={"ID_HC","N° HC","Nombre del Paciente","DNI","Fecha Nac.","Edad","Sexo",
            "Acto Médico","Cant. Examenes","Fecha Examen", "Codigo Documento"};
            msb=new DefaultTableModel(null,titulosb);
            JTable psb=new JTable(msb);
            String filasb[]=new String[11];
            tb_Examenes.setModel(msb);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(msb);
            tb_Examenes.setRowSorter(elQueOrdenasb);
            this.tb_Examenes.setModel(msb);
            
            formatoExamen();
            
        } catch (Exception e) {
            System.out.println("error inicializar tabla_RV: " + e);
        }
            
    }
        
    public void formatoExamen(){        
              
            tb_Examenes.getColumnModel().getColumn(0).setPreferredWidth(80); 
            tb_Examenes.getColumnModel().getColumn(1).setPreferredWidth(80);
            tb_Examenes.getColumnModel().getColumn(2).setPreferredWidth(300);
            tb_Examenes.getColumnModel().getColumn(3).setPreferredWidth(100);                
            tb_Examenes.getColumnModel().getColumn(4).setPreferredWidth(60); 
            tb_Examenes.getColumnModel().getColumn(5).setPreferredWidth(60);
            tb_Examenes.getColumnModel().getColumn(6).setPreferredWidth(60);
            tb_Examenes.getColumnModel().getColumn(7).setPreferredWidth(80); 
            tb_Examenes.getColumnModel().getColumn(8).setPreferredWidth(110);
            tb_Examenes.getColumnModel().getColumn(9).setPreferredWidth(110);
            tb_Examenes.getColumnModel().getColumn(10).setPreferredWidth(180);
            //Ocultar
            tb_Examenes.getColumnModel().getColumn(0).setMinWidth(0);
            tb_Examenes.getColumnModel().getColumn(0).setMaxWidth(0);    
            tb_Examenes.getColumnModel().getColumn(4).setMinWidth(0);
            tb_Examenes.getColumnModel().getColumn(4).setMaxWidth(0);
    }
    
    
    public void fecha_IF(){
        
         int diaIN = fecha_inicio.getCalendar().get(Calendar.DAY_OF_MONTH);
         int mesIN = fecha_inicio.getCalendar().get(Calendar.MONTH) + 1;
         int anioIN = fecha_inicio.getCalendar().get(Calendar.YEAR);
         
         int diaFN = fecha_fin.getCalendar().get(Calendar.DAY_OF_MONTH);
         int mesFN = fecha_fin.getCalendar().get(Calendar.MONTH) + 1;
         int anioFN = fecha_fin.getCalendar().get(Calendar.YEAR);
         
        if(diaIN > diaFN || mesIN > mesFN || anioIN > anioFN){
                 JOptionPane.showMessageDialog(rootPane, "La fecha de inicio debe ser menor \n o igual a la fecha de termino ");
                 fecha_inicio.setDate(null);
                 fecha_fin.setDate(null);
             }
    }
    
    public void mostrarPacientes(){
        try {
                     
            String consulta="";
            
            tb_Examenes.setModel(new DefaultTableModel());
            String titulos[]={"ID_HC","N° HC","Nombre del Paciente","DNI","Fecha Nac.","Edad","Sexo",
            "Acto Médico","Cant. Examenes","Fecha Examen","Codigo Documento"};
            m4=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m4);
            String fila[]=new String[11];
            Usuario obj=new Usuario();
            consulta="exec RX_EC_LISTAR_CAJA_RX ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, lblNumeArea.getText());
 
            ResultSet r= cmd.executeQuery();
            while(r.next()){
            for (int i=0; i<11; i++){
            fila[i]=r.getString(i+1);
            }
                m4.addRow(fila);
            }
            tb_Examenes.setModel(m4);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m4);
            tb_Examenes.setRowSorter(elQueOrdena);
            tb_Examenes.setModel(m4);
                       
            formatoExamen();
            
        } catch (Exception e) {
            System.out.println("Error mostrar paciente: " + e.getMessage());
        }
        
    }
    
    public static String fechaActual(){
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        return date.format(now);
    }
    
    public void calcula() {
        Calendar calendario = new GregorianCalendar();
        java.util.Date fechaHoraActual = new java.util.Date();


        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";

        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }
    
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
            java.util.logging.Logger.getLogger(RX_EC_BUSCAR_EXAMEN_C.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RX_EC_BUSCAR_EXAMEN_C.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RX_EC_BUSCAR_EXAMEN_C.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RX_EC_BUSCAR_EXAMEN_C.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new RX_EC_BUSCAR_EXAMEN_C().setVisible(true);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(RX_EC_BUSCAR_EXAMEN_C.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarP;
    private javax.swing.JCheckBox cbFecha;
    private com.toedter.calendar.JDateChooser fecha_fin;
    private com.toedter.calendar.JDateChooser fecha_inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel jpanel;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblNomArea;
    private javax.swing.JLabel lblNumeArea;
    public static javax.swing.JLabel lblUsu;
    public static javax.swing.JTable tb_Examenes;
    private javax.swing.JLabel titulo5;
    private javax.swing.JTextField txtBuscarPaciente;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            lblHora.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
