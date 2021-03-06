/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import vista.CRED.RegistroSeguimiento;
import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;
import modelos.ConsultorioEx.ConsultorioEXTriaje;
import modelos.ConsultorioEx.ConsultorioExConsultorio;
import modelos.ConsultorioEx.ConsultorioExtConsultorioCabecera;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import servicios.Conexion;


/**
 *
 * @author MYS1
 */
public class ConsultorioExtLista extends javax.swing.JInternalFrame implements Runnable{
    ConsultorioExtConsultorioCabecera Consulta = new ConsultorioExtConsultorioCabecera();
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    Thread h1;
    public ConsultorioExtLista() {
        initComponents();
        QuitarLaBarraTitulo();
        listar();
        panelTriaje.setVisible(false);
//        tbTriaje.setDefaultRenderer(Object.class,new FormatoTablaCEXLISTA());
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance(); 
    }
   public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
        
    }
    public void listar(){
        
             Consulta.TriajeListarReporte(ConsultorioExt.txPaciente.getText(), tbTriaje,"T");
    }
    
    public void Guardar( ){
        
    ConsultorioExtConsultorioCabecera CXRsR= new ConsultorioExtConsultorioCabecera();
    ConsultorioExtConsultorioCabecera CXRsR2= new ConsultorioExtConsultorioCabecera();
    AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
    
            CXRsR.setIdConsultorioEx(0);
            CXRsR.setId_hc(lblIdHC.getText());
            CXRsR.setId_ActoMedico(Integer.parseInt(lblIdActoM.getText()));
            CXRsR.setCodUsu(adEmerCab.codUsuario(ConsultorioExt.lblUsu.getText()));
                if(CXRsR.mantenimientoConsultorioExtCabecera(lblMant.getText())==true){
                    if (lblMant.getText().equals("I")){

                    jPanel3.setBackground(new Color(33,115,70));
                    System.out.println("Datos GUARDADOS de forma correcta");
//                    CXRsR2.ConsultoriosExtCABERCERAListar(lblIdHC.getText());     
                    }
                    if (lblMant.getText().equals("U")){
                    jPanel3.setBackground(new Color(33,115,70)); 
                    System.out.println("Datos Actualizados de forma correcta");
                    
 
//                    CXRsR2.ConsultoriosExtCABERCERAListar(lblIdHC.getText());    
                    }
//                    habilitarDatos(false);
                }else {

                        jPanel3.setBackground(new Color(255,91,70)); 
                        System.out.println("Ocurrio un error, Verifique");
                }  
    }
    
        public void ActualizarTriaje(){
        
        ConsultorioExtConsultorioCabecera CXRsR= new ConsultorioExtConsultorioCabecera();
        ConsultorioExtConsultorioCabecera CXRsR2= new ConsultorioExtConsultorioCabecera();
                CXRsR.setTriaje_id(lblIdTriaje.getText());
               
                    if(CXRsR.mantenimientoCXTriaje("U")==true){
                        System.out.println("Triaje Actualizado");
                        CXRsR2.TriajeListarReporte(ConsultorioExt.txPaciente.getText(), tbTriaje,"T");     
                       
                    }else {
                        jPanel3.setBackground(new Color(255,91,70)); 
                        System.out.println("Ocurrio un error, Verifique");
                    }  
    } 
   
    public void abrirHistoriaClinica(){
        HistoriaClinica hC = new HistoriaClinica();
        hC.setVisible(true);
        ConsultorioExConsultorio cabecera = new ConsultorioExConsultorio();
        cabecera.listarConsultorioCabecera(lblIdHC.getText(), HistoriaClinica.tbIngresosConsultorio);
        cabecera.listarEmergencia(lblIdHC.getText(), HistoriaClinica.tbIngresosEmergencia);
        cabecera.listarHospitalizacion(lblIdHC.getText(), HistoriaClinica.tbIngresosHospitalizacion);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTriaje = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel3 = new javax.swing.JPanel();
            lblIdHC = new javax.swing.JLabel();
            lblIdActoM = new javax.swing.JLabel();
            lblMant = new javax.swing.JLabel();
            jLabel32 = new javax.swing.JLabel();
            lblIdTriaje = new javax.swing.JLabel();
            lblNombres = new javax.swing.JLabel();
            lblDNI = new javax.swing.JLabel();
            panelTriaje = new javax.swing.JPanel();
            jLabel26 = new javax.swing.JLabel();
            jLabel35 = new javax.swing.JLabel();
            lblT = new javax.swing.JLabel();
            lblFC = new javax.swing.JLabel();
            jLabel28 = new javax.swing.JLabel();
            jLabel37 = new javax.swing.JLabel();
            lblTALLA = new javax.swing.JLabel();
            lblFR = new javax.swing.JLabel();
            jLabel31 = new javax.swing.JLabel();
            lblPA = new javax.swing.JLabel();
            jLabel39 = new javax.swing.JLabel();
            lblIDM = new javax.swing.JLabel();
            jLabel33 = new javax.swing.JLabel();
            lblPESO = new javax.swing.JLabel();
            jLabel30 = new javax.swing.JLabel();
            jLabel34 = new javax.swing.JLabel();
            lblEDAD = new javax.swing.JLabel();

            jMenuItem1.setText("Historia Clínica");
            jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem1ActionPerformed(evt);
                }
            });
            jPopupMenu1.add(jMenuItem1);

            setBorder(javax.swing.BorderFactory.createCompoundBorder());
            setVisible(true);

            jScrollPane3.setBorder(null);

            tbTriaje.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            tbTriaje.setForeground(new java.awt.Color(51, 51, 51));
            tbTriaje.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ));
            tbTriaje.setGridColor(new java.awt.Color(255, 255, 255));
            tbTriaje.setRowHeight(25);
            tbTriaje.setSelectionBackground(new java.awt.Color(39, 174, 97));
            tbTriaje.getTableHeader().setReorderingAllowed(false);
            tbTriaje.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbTriajeMouseClicked(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    tbTriajeMousePressed(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    tbTriajeMouseReleased(evt);
                }
            });
            tbTriaje.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbTriajeKeyPressed(evt);
                }
            });
            jScrollPane3.setViewportView(tbTriaje);

            jPanel3.setBackground(new java.awt.Color(43, 43, 43));
            jPanel3.setPreferredSize(new java.awt.Dimension(929, 115));

            lblIdActoM.setForeground(new java.awt.Color(255, 255, 255));
            lblIdActoM.setText("0");

            lblMant.setText("I");

            jLabel32.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
            jLabel32.setForeground(new java.awt.Color(255, 255, 255));
            jLabel32.setText("Pacientes");

            lblNombres.setText("jLabel1");

            lblDNI.setText("jLabel2");

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(lblIdHC, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblIdActoM)
                            .addGap(18, 18, 18)
                            .addComponent(lblMant)
                            .addGap(18, 18, 18)
                            .addComponent(lblIdTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46)
                            .addComponent(lblNombres)
                            .addGap(18, 18, 18)
                            .addComponent(lblDNI))
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(509, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblIdHC, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblIdActoM)
                                .addComponent(lblMant))
                            .addComponent(lblIdTriaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombres)
                            .addComponent(lblDNI)))
                    .addContainerGap())
            );

            panelTriaje.setBackground(new java.awt.Color(214, 217, 223));

            jLabel26.setBackground(new java.awt.Color(51, 51, 51));
            jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel26.setForeground(new java.awt.Color(51, 51, 51));
            jLabel26.setText("Frecuencia Cardiaca");

            jLabel35.setBackground(new java.awt.Color(51, 51, 51));
            jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel35.setForeground(new java.awt.Color(51, 51, 51));
            jLabel35.setText("Temperatura");

            lblT.setBackground(new java.awt.Color(51, 51, 51));
            lblT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblT.setForeground(new java.awt.Color(51, 51, 51));
            lblT.setText("  ");

            lblFC.setBackground(new java.awt.Color(51, 51, 51));
            lblFC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblFC.setForeground(new java.awt.Color(51, 51, 51));
            lblFC.setText("  ");

            jLabel28.setBackground(new java.awt.Color(51, 51, 51));
            jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel28.setForeground(new java.awt.Color(51, 51, 51));
            jLabel28.setText("Frecuencia Respiratoria");

            jLabel37.setBackground(new java.awt.Color(51, 51, 51));
            jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel37.setForeground(new java.awt.Color(51, 51, 51));
            jLabel37.setText("Talla");

            lblTALLA.setBackground(new java.awt.Color(51, 51, 51));
            lblTALLA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblTALLA.setForeground(new java.awt.Color(51, 51, 51));
            lblTALLA.setText("  ");

            lblFR.setBackground(new java.awt.Color(51, 51, 51));
            lblFR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblFR.setForeground(new java.awt.Color(51, 51, 51));
            lblFR.setText("  ");

            jLabel31.setBackground(new java.awt.Color(51, 51, 51));
            jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel31.setForeground(new java.awt.Color(51, 51, 51));
            jLabel31.setText("Presion Arterial");

            lblPA.setBackground(new java.awt.Color(51, 51, 51));
            lblPA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblPA.setForeground(new java.awt.Color(51, 51, 51));
            lblPA.setText("  ");

            jLabel39.setBackground(new java.awt.Color(51, 51, 51));
            jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel39.setForeground(new java.awt.Color(51, 51, 51));
            jLabel39.setText("Índice  de Masa Corporal");

            lblIDM.setBackground(new java.awt.Color(51, 51, 51));
            lblIDM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblIDM.setForeground(new java.awt.Color(51, 51, 51));
            lblIDM.setText("  ");

            jLabel33.setBackground(new java.awt.Color(51, 51, 51));
            jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel33.setForeground(new java.awt.Color(51, 51, 51));
            jLabel33.setText("Peso");

            lblPESO.setBackground(new java.awt.Color(51, 51, 51));
            lblPESO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblPESO.setForeground(new java.awt.Color(51, 51, 51));
            lblPESO.setText("  ");

            jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel30.setForeground(new java.awt.Color(255, 153, 51));
            jLabel30.setText("Datos de Triaje");

            jLabel34.setBackground(new java.awt.Color(51, 51, 51));
            jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel34.setForeground(new java.awt.Color(51, 51, 51));
            jLabel34.setText("Edad");

            lblEDAD.setBackground(new java.awt.Color(51, 51, 51));
            lblEDAD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblEDAD.setForeground(new java.awt.Color(51, 51, 51));
            lblEDAD.setText("  ");

            javax.swing.GroupLayout panelTriajeLayout = new javax.swing.GroupLayout(panelTriaje);
            panelTriaje.setLayout(panelTriajeLayout);
            panelTriajeLayout.setHorizontalGroup(
                panelTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTriajeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelTriajeLayout.createSequentialGroup()
                            .addGroup(panelTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel26)
                                .addComponent(jLabel35)
                                .addComponent(jLabel31)
                                .addComponent(jLabel33))
                            .addGap(18, 18, 18)
                            .addGroup(panelTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblPA, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                .addComponent(lblT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblFC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPESO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(50, 50, 50)
                            .addGroup(panelTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel37)
                                .addComponent(jLabel28)
                                .addComponent(jLabel39)
                                .addComponent(jLabel34))
                            .addGap(30, 30, 30)
                            .addGroup(panelTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblEDAD, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                .addComponent(lblFR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTALLA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblIDM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
            );
            panelTriajeLayout.setVerticalGroup(
                panelTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTriajeLayout.createSequentialGroup()
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(4, 4, 4)
                    .addGroup(panelTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelTriajeLayout.createSequentialGroup()
                            .addGroup(panelTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel28)
                                .addComponent(lblFR))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(panelTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel37)
                                .addComponent(lblTALLA)))
                        .addGroup(panelTriajeLayout.createSequentialGroup()
                            .addGroup(panelTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(lblFC))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(panelTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel35)
                                .addComponent(lblT))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblPA)
                        .addComponent(jLabel31)
                        .addComponent(lblIDM)
                        .addComponent(jLabel39))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblPESO)
                        .addComponent(jLabel33)
                        .addComponent(lblEDAD)
                        .addComponent(jLabel34))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelTriaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addGap(0, 0, 0)
                    .addComponent(panelTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void tbTriajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriajeMouseClicked
        
        int fila=tbTriaje.getSelectedRow();
        if(evt.getClickCount()==1){
            lblFC.setText(String.valueOf(tbTriaje.getValueAt(fila, 7)));
            lblFR.setText(String.valueOf(tbTriaje.getValueAt(fila, 8)));
            lblPA.setText(String.valueOf(tbTriaje.getValueAt(fila, 9)));
            lblPESO.setText(String.valueOf(tbTriaje.getValueAt(fila, 10)));
            lblT.setText(String.valueOf(tbTriaje.getValueAt(fila, 11)));
            lblTALLA.setText(String.valueOf(tbTriaje.getValueAt(fila, 12)));
            lblIDM.setText(String.valueOf(tbTriaje.getValueAt(fila, 13)));
            lblIdHC.setText(String.valueOf(tbTriaje.getValueAt(fila, 14)));
            lblEDAD.setText(String.valueOf(tbTriaje.getValueAt(fila, 6)));
            lblIdActoM.setText(String.valueOf(tbTriaje.getValueAt(fila, 19)));
            ////////////////////////////
            lblIdTriaje.setText(String.valueOf(tbTriaje.getValueAt(fila, 0)));
            //////////////////
            lblNombres.setText(String.valueOf(tbTriaje.getValueAt(fila, 4)));
            lblDNI.setText("DNI   "+String.valueOf(tbTriaje.getValueAt(fila, 2)));
            panelTriaje.setVisible(true);
            HistoriaClinica.lblHC.setText(String.valueOf(tbTriaje.getValueAt(fila, 3)));
        }
        if(evt.getClickCount()==2){
            abrirHistoriaClinica();
            Guardar( );
            ActualizarTriaje();
           
            ConsultoExtPrincipal principal =new ConsultoExtPrincipal();
            ConsultorioExt.PanelDetalle.add(principal);
             //////////////////////////////////
            ConsultorioExtConsultorioCabecera CXRsR2= new ConsultorioExtConsultorioCabecera();
            CXRsR2.ConsultoriosExtCABERCERAListar(lblIdHC.getText());
            ////////////////////////////////////
        
            ConsultorioExt.jTabbedPane1.setSelectedIndex(1);
            ConsultorioExtPerfilUsuario.lblHC.setText(lblIdHC.getText()); 
            ConsultorioExtPerfilUsuario.lblPaciente.setText(lblNombres.getText());
            ConsultorioExtPerfilUsuario.lblDNI.setText(lblDNI.getText());
            ConsultorioExt.btnActualizar.setVisible(false);
            ConsultorioExt.btnLista.setVisible(false); 
            ConsultorioExt.btnVer.setVisible(true); 
            ConsultorioExt.btnCerrar.setVisible(true); 
        
        try {
            principal.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            

            
        }
    }//GEN-LAST:event_tbTriajeMouseClicked

    private void tbTriajeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriajeMousePressed

    }//GEN-LAST:event_tbTriajeMousePressed

    private void tbTriajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTriajeKeyPressed

    }//GEN-LAST:event_tbTriajeKeyPressed

    private void tbTriajeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriajeMouseReleased
        if(evt.isPopupTrigger()){
            jPopupMenu1.show(evt.getComponent(),evt.getX(),evt.getY());
        }
    }//GEN-LAST:event_tbTriajeMouseReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        abrirHistoriaClinica();
    }//GEN-LAST:event_jMenuItem1ActionPerformed


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
    
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
//            calcula();
            try {
                Thread.sleep(5000);
                Consulta.TriajeListarReporte(ConsultorioExt.txPaciente.getText(), tbTriaje,"T");
            } catch (InterruptedException e) {
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JMenuItem jMenuItem1;
    public static javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblEDAD;
    private javax.swing.JLabel lblFC;
    private javax.swing.JLabel lblFR;
    private javax.swing.JLabel lblIDM;
    private javax.swing.JLabel lblIdActoM;
    private javax.swing.JLabel lblIdHC;
    private javax.swing.JLabel lblIdTriaje;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblPA;
    private javax.swing.JLabel lblPESO;
    private javax.swing.JLabel lblT;
    private javax.swing.JLabel lblTALLA;
    public static javax.swing.JPanel panelTriaje;
    public static javax.swing.JTable tbTriaje;
    // End of variables declaration//GEN-END:variables
}
