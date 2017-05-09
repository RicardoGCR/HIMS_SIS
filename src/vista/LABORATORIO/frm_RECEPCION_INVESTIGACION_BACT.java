/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.LABORATORIO;

import vista.Programas.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.COSTOS.serviciosVarios;
import modelos.LABORATORIO.LAB_Clasificacion_Examen;
import modelos.Programas.LAB_Solicitud_Inv_Bact;
import modelos.LABORATORIO.LAB_Toma_Muestra_Detalle;
import servicios.Conexion;

/**
 *
 * @author PC-SISTEMA
 */
public class frm_RECEPCION_INVESTIGACION_BACT extends javax.swing.JFrame {
DefaultTableModel m;
    /**
     * Creates new form frm_SOLICITUD_INVESTIGACION_BACT
     */
    public frm_RECEPCION_INVESTIGACION_BACT() {
        initComponents();
         buscar_HC.getContentPane().setBackground(Color.white); 
        buscar_HC.setLocationRelativeTo(null);
        buscar_muestras.getContentPane().setBackground(Color.white); 
        buscar_muestras.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white); 
        setExtendedState(JFrame.MAXIMIZED_BOTH);//maximizado
        setLocationRelativeTo(null);//en el centro
        
        LAB_Solicitud_Inv_Bact s=new LAB_Solicitud_Inv_Bact();
        lblDisa.setText(s.LAB_DISA_SALUD(1));
        LAB_Solicitud_Inv_Bact s1=new LAB_Solicitud_Inv_Bact();
        lblEess.setText(s1.LAB_DISA_SALUD(2));
        
        LAB_Solicitud_Inv_Bact m=new LAB_Solicitud_Inv_Bact();
        lblEess.setText(m.LAB_Cod_Muestra());
        
//        setResizable(false);//para que no funcione el boton maximizar
        
         //para no intercambiar columnas
        tb_Muestra_Examen.getTableHeader().setReorderingAllowed(false);
    }
  public void LAB_HC_cargar(){
    try {
             String titulos[]={"N°","Codigo","N° H.C.","Paciente","DNI","Telefono","Celular","Dirección","Fecha de Nac.","Edad","Sexo"
             ,"Distrito","Provincia","Departamento"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[14];

            LAB_Clasificacion_Examen obj=new LAB_Clasificacion_Examen();
        String consulta="exec sp_BuscarHC ?";
        
        PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, "");
        ResultSet r=cmd.executeQuery();
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
                m.addRow(fila);
                c++;
            }
            tb_HC.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_HC.setRowSorter(elQueOrdena);
            this.tb_HC.setModel(m);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
    public void LAB_HC_formato(){
    tb_HC.getColumnModel().getColumn(0).setPreferredWidth(40);
    tb_HC.getColumnModel().getColumn(2).setPreferredWidth(100);
    tb_HC.getColumnModel().getColumn(3).setPreferredWidth(230);
    tb_HC.getColumnModel().getColumn(4).setPreferredWidth(100);
    tb_HC.getColumnModel().getColumn(6).setPreferredWidth(100);
    tb_HC.getColumnModel().getColumn(7).setPreferredWidth(110);
    tb_HC.getColumnModel().getColumn(8).setPreferredWidth(100);
    tb_HC.getColumnModel().getColumn(9).setPreferredWidth(100);
    tb_HC.getColumnModel().getColumn(10).setPreferredWidth(100);
            //Ocultar    
    tb_HC.getColumnModel().getColumn(1).setMinWidth(0);
    tb_HC.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_HC.getColumnModel().getColumn(11).setMinWidth(0);
    tb_HC.getColumnModel().getColumn(11).setMaxWidth(0);
    tb_HC.getColumnModel().getColumn(12).setMinWidth(0);
    tb_HC.getColumnModel().getColumn(12).setMaxWidth(0);
    tb_HC.getColumnModel().getColumn(13).setMinWidth(0);
    tb_HC.getColumnModel().getColumn(13).setMaxWidth(0);
    tb_HC.getSelectionModel().setSelectionInterval(0, 0);
            tb_HC.requestFocus();
}
    
        public void LAB_Muestra_Examen_cargar() {
        try {
            String titulos[] = {"Nº", "Código", "Muestra"};
            m = new DefaultTableModel(null, titulos);
            JTable p = new JTable(m);
            String fila[] = new String[4];

            Conexion obj = new Conexion();
            String consulta = "exec sp_LAB_MUESTRA_EXAMEN_listar";
            ResultSet r;
            r = obj.Listar(consulta);
            int c = 1;
            while (r.next()) {
                fila[0] = String.valueOf(c) + "º";
                fila[1] = r.getString(1);
                fila[2] = r.getString(2);
                m.addRow(fila);
                c++;
            }
            tb_Muestra_Examen.setModel(m);
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(m);
            tb_Muestra_Examen.setRowSorter(elQueOrdena);
            this.tb_Muestra_Examen.setModel(m);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
        }
    }

    public void LAB_Muestra_Examen_formato() {
        tb_Muestra_Examen.getColumnModel().getColumn(0).setPreferredWidth(40);
        tb_Muestra_Examen.getColumnModel().getColumn(1).setPreferredWidth(78);
        tb_Muestra_Examen.getColumnModel().getColumn(2).setPreferredWidth(200);
        
        tb_HC.getColumnModel().getColumn(3).setMinWidth(0);
    tb_HC.getColumnModel().getColumn(3).setMaxWidth(0);
        tb_Muestra_Examen.getSelectionModel().setSelectionInterval(0, 0);
        tb_Muestra_Examen.requestFocus();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buscar_HC = new javax.swing.JDialog();
        btnBuscar1 = new javax.swing.JButton();
        txtbuscarHC = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_HC = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jpanel3 = new javax.swing.JPanel();
            titulo8 = new javax.swing.JLabel();
            jLabel25 = new javax.swing.JLabel();
            lblTipo = new javax.swing.JLabel();
            buscar_muestras = new javax.swing.JDialog();
            txtBuscar = new javax.swing.JTextField();
            jScrollPane2 = new javax.swing.JScrollPane();
            tb_Muestra_Examen = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                btnBuscarMuestra = new javax.swing.JButton();
                jpanel4 = new javax.swing.JPanel();
                titulo9 = new javax.swing.JLabel();
                txtDRxAnormal = new javax.swing.JTextField();
                txtTMEsputo = new javax.swing.JTextField();
                txtATFracaso = new javax.swing.JTextField();
                txtATNuncaT = new javax.swing.JTextField();
                txtATAntesT = new javax.swing.JTextField();
                txtATAbandonoR = new javax.swing.JTextField();
                txtTMOtro = new javax.swing.JTextField();
                jpanel = new javax.swing.JPanel();
                titulo5 = new javax.swing.JLabel();
                lblUsu = new javax.swing.JLabel();
                btnguardar = new javax.swing.JButton();
                btnmodificar = new javax.swing.JButton();
                btnBuscar = new javax.swing.JButton();
                btnNuevo = new javax.swing.JButton();
                jPanel14 = new javax.swing.JPanel();
                txtPaciente1 = new javax.swing.JTextField();
                btnBuscarHC1 = new javax.swing.JButton();
                jLabel6 = new javax.swing.JLabel();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                jPanel9 = new javax.swing.JPanel();
                txtPaciente = new javax.swing.JTextField();
                btnBuscarHC = new javax.swing.JButton();
                jLabel10 = new javax.swing.JLabel();
                lblhc = new javax.swing.JLabel();
                jLabel12 = new javax.swing.JLabel();
                jLabel13 = new javax.swing.JLabel();
                lblDireccion = new javax.swing.JLabel();
                jLabel15 = new javax.swing.JLabel();
                lblProvincia = new javax.swing.JLabel();
                jLabel18 = new javax.swing.JLabel();
                jLabel19 = new javax.swing.JLabel();
                jLabel20 = new javax.swing.JLabel();
                lblDNI = new javax.swing.JLabel();
                jLabel22 = new javax.swing.JLabel();
                lblEdad = new javax.swing.JLabel();
                lblSexo = new javax.swing.JLabel();
                lblTelefono = new javax.swing.JLabel();
                jLabel27 = new javax.swing.JLabel();
                lblDistrito = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                lblCama = new javax.swing.JLabel();
                lblServicio = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                jPanel10 = new javax.swing.JPanel();
                txtReferencia = new javax.swing.JTextField();
                lblDisa = new javax.swing.JLabel();
                lblEess = new javax.swing.JLabel();
                jLabel14 = new javax.swing.JLabel();
                jPanel4 = new javax.swing.JPanel();
                jLabel21 = new javax.swing.JLabel();
                jLabel23 = new javax.swing.JLabel();
                lblCodHC = new javax.swing.JLabel();
                jPanel6 = new javax.swing.JPanel();
                jLabel28 = new javax.swing.JLabel();
                jLabel29 = new javax.swing.JLabel();
                jLabel16 = new javax.swing.JLabel();
                jPanel11 = new javax.swing.JPanel();
                btnBuscarTM = new javax.swing.JButton();
                txtTMEspecificar = new javax.swing.JTextField();
                jLabel17 = new javax.swing.JLabel();
                jPanel8 = new javax.swing.JPanel();
                jLabel32 = new javax.swing.JLabel();
                jLabel31 = new javax.swing.JLabel();
                jLabel36 = new javax.swing.JLabel();
                jLabel37 = new javax.swing.JLabel();
                jLabel38 = new javax.swing.JLabel();
                jLabel39 = new javax.swing.JLabel();
                jPanel13 = new javax.swing.JPanel();
                jLabel40 = new javax.swing.JLabel();
                jLabel33 = new javax.swing.JLabel();
                jLabel44 = new javax.swing.JLabel();
                jLabel45 = new javax.swing.JLabel();
                txtDSegDia = new javax.swing.JTextField();
                jPanel15 = new javax.swing.JPanel();
                txtDEspecificar = new javax.swing.JTextField();
                jLabel47 = new javax.swing.JLabel();
                txtDSR = new javax.swing.JTextField();
                jLabel48 = new javax.swing.JLabel();
                txtDOtro = new javax.swing.JTextField();
                jPanel16 = new javax.swing.JPanel();
                jLabel46 = new javax.swing.JLabel();
                jLabel51 = new javax.swing.JLabel();
                jLabel52 = new javax.swing.JLabel();
                txtCTMes = new javax.swing.JTextField();
                jLabel53 = new javax.swing.JLabel();
                txtCTEsqTB = new javax.swing.JTextField();
                jLabel54 = new javax.swing.JLabel();
                txtCTEsqDR = new javax.swing.JTextField();
                jLabel55 = new javax.swing.JLabel();
                txtCTEsqMDR = new javax.swing.JTextField();
                jLabel56 = new javax.swing.JLabel();
                txtCTEsqXDR = new javax.swing.JTextField();
                jLabel57 = new javax.swing.JLabel();
                txtCTOtros = new javax.swing.JTextField();
                jPanel18 = new javax.swing.JPanel();
                jLabel58 = new javax.swing.JLabel();
                jLabel35 = new javax.swing.JLabel();
                jLabel62 = new javax.swing.JLabel();
                txtSB1 = new javax.swing.JTextField();
                jLabel63 = new javax.swing.JLabel();
                txtSB2 = new javax.swing.JTextField();
                jLabel64 = new javax.swing.JLabel();
                jPanel20 = new javax.swing.JPanel();
                txtSBOtras = new javax.swing.JTextField();
                jLabel65 = new javax.swing.JLabel();
                jPanel22 = new javax.swing.JPanel();
                jLabel66 = new javax.swing.JLabel();
                jLabel59 = new javax.swing.JLabel();
                jLabel70 = new javax.swing.JLabel();
                txtPSRapida = new javax.swing.JTextField();
                jLabel71 = new javax.swing.JLabel();
                jPanel24 = new javax.swing.JPanel();
                txtPSREspecificar = new javax.swing.JTextField();
                jLabel72 = new javax.swing.JLabel();
                txtPSConvencional = new javax.swing.JTextField();
                jLabel73 = new javax.swing.JLabel();
                jPanel25 = new javax.swing.JPanel();
                txtPSCEspecificar = new javax.swing.JTextField();
                jPanel26 = new javax.swing.JPanel();
                jLabel74 = new javax.swing.JLabel();
                jLabel42 = new javax.swing.JLabel();
                jLabel76 = new javax.swing.JLabel();
                jPanel27 = new javax.swing.JPanel();
                txtPSOtroEspecificar = new javax.swing.JTextField();
                txtSBCultivo = new javax.swing.JTextField();
                jPanel28 = new javax.swing.JPanel();
                txtReferencia8 = new javax.swing.JTextField();
                jLabel24 = new javax.swing.JLabel();
                jPanel12 = new javax.swing.JPanel();
                txtPacienteSol = new javax.swing.JTextField();
                btnBuscarHC2 = new javax.swing.JButton();
                jLabel26 = new javax.swing.JLabel();
                lblTelefonoSol = new javax.swing.JLabel();
                jLabel30 = new javax.swing.JLabel();
                lblCel = new javax.swing.JLabel();
                jPanel30 = new javax.swing.JPanel();
                txtReferencia9 = new javax.swing.JTextField();
                jLabel79 = new javax.swing.JLabel();
                jLabel80 = new javax.swing.JLabel();
                jLabel60 = new javax.swing.JLabel();
                txtMInadecuada = new javax.swing.JTextField();
                jLabel61 = new javax.swing.JLabel();
                txtMAdecuada = new javax.swing.JTextField();
                jLabel77 = new javax.swing.JLabel();
                jPanel23 = new javax.swing.JPanel();
                jLabel67 = new javax.swing.JLabel();
                jLabel68 = new javax.swing.JLabel();
                jPanel29 = new javax.swing.JPanel();
                jLabel75 = new javax.swing.JLabel();
                jLabel49 = new javax.swing.JLabel();
                lblCodHCSol = new javax.swing.JLabel();
                jPanel31 = new javax.swing.JPanel();
                jLabel69 = new javax.swing.JLabel();
                jLabel78 = new javax.swing.JLabel();
                lblCodTMEspecificar = new javax.swing.JLabel();
                jDateChooser1 = new com.toedter.calendar.JDateChooser();

                buscar_HC.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                buscar_HC.setTitle("SISGESH.::.Clasificación Examen");
                buscar_HC.setAlwaysOnTop(true);
                buscar_HC.setMinimumSize(new java.awt.Dimension(876, 692));
                buscar_HC.setResizable(false);

                btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscar1.setBorder(null);
                btnBuscar1.setContentAreaFilled(false);
                btnBuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscar1ActionPerformed(evt);
                    }
                });

                txtbuscarHC.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtbuscarHC.setForeground(new java.awt.Color(0, 51, 51));
                txtbuscarHC.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtbuscarHCActionPerformed(evt);
                    }
                });
                txtbuscarHC.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtbuscarHCKeyPressed(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtbuscarHCKeyTyped(evt);
                    }
                });

                tb_HC.setModel(new javax.swing.table.DefaultTableModel(
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
                tb_HC.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                tb_HC.setRowHeight(25);
                tb_HC.getTableHeader().setReorderingAllowed(false);
                tb_HC.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_HCMouseClicked(evt);
                    }
                });
                tb_HC.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_HCKeyPressed(evt);
                    }
                });
                jScrollPane1.setViewportView(tb_HC);

                jpanel3.setBackground(new java.awt.Color(2, 67, 115));

                titulo8.setBackground(new java.awt.Color(0, 102, 102));
                titulo8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
                titulo8.setForeground(new java.awt.Color(255, 255, 255));
                titulo8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                titulo8.setText("Historia Clínica");
                titulo8.setToolTipText("");
                titulo8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                javax.swing.GroupLayout jpanel3Layout = new javax.swing.GroupLayout(jpanel3);
                jpanel3.setLayout(jpanel3Layout);
                jpanel3Layout.setHorizontalGroup(
                    jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                );
                jpanel3Layout.setVerticalGroup(
                    jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                jLabel25.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
                jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel25.setText("Búsqueda por H.C / Paciente / DNI");

                lblTipo.setForeground(new java.awt.Color(255, 255, 255));
                lblTipo.setText("jLabel6");

                javax.swing.GroupLayout buscar_HCLayout = new javax.swing.GroupLayout(buscar_HC.getContentPane());
                buscar_HC.getContentPane().setLayout(buscar_HCLayout);
                buscar_HCLayout.setHorizontalGroup(
                    buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buscar_HCLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                        .addGap(19, 19, 19))
                    .addGroup(buscar_HCLayout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addGroup(buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtbuscarHC, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTipo)
                        .addGap(74, 74, 74))
                    .addGroup(buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                buscar_HCLayout.setVerticalGroup(
                    buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_HCLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(buscar_HCLayout.createSequentialGroup()
                                .addGroup(buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtbuscarHC, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jLabel25))
                            .addComponent(lblTipo))
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(38, Short.MAX_VALUE))
                    .addGroup(buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(buscar_HCLayout.createSequentialGroup()
                            .addComponent(jpanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 644, Short.MAX_VALUE)))
                );

                buscar_muestras.setTitle("Muestras");
                buscar_muestras.setMinimumSize(new java.awt.Dimension(353, 444));

                txtBuscar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
                txtBuscar.setText("Ingresar Muestra");
                txtBuscar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtBuscarActionPerformed(evt);
                    }
                });
                txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscarKeyPressed(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtBuscarKeyTyped(evt);
                    }
                });

                tb_Muestra_Examen.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                tb_Muestra_Examen.setModel(new javax.swing.table.DefaultTableModel(
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
                tb_Muestra_Examen.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                tb_Muestra_Examen.setRowHeight(25);
                tb_Muestra_Examen.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_Muestra_ExamenMouseClicked(evt);
                    }
                });
                tb_Muestra_Examen.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_Muestra_ExamenKeyPressed(evt);
                    }
                });
                jScrollPane2.setViewportView(tb_Muestra_Examen);

                btnBuscarMuestra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                btnBuscarMuestra.setBorder(null);
                btnBuscarMuestra.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarMuestraActionPerformed(evt);
                    }
                });

                jpanel4.setBackground(new java.awt.Color(2, 67, 115));

                titulo9.setBackground(new java.awt.Color(0, 102, 102));
                titulo9.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                titulo9.setForeground(new java.awt.Color(255, 255, 255));
                titulo9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                titulo9.setText("Muestras");
                titulo9.setToolTipText("");
                titulo9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                javax.swing.GroupLayout jpanel4Layout = new javax.swing.GroupLayout(jpanel4);
                jpanel4.setLayout(jpanel4Layout);
                jpanel4Layout.setHorizontalGroup(
                    jpanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(titulo9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                );
                jpanel4Layout.setVerticalGroup(
                    jpanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel4Layout.createSequentialGroup()
                        .addComponent(titulo9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                );

                javax.swing.GroupLayout buscar_muestrasLayout = new javax.swing.GroupLayout(buscar_muestras.getContentPane());
                buscar_muestras.getContentPane().setLayout(buscar_muestrasLayout);
                buscar_muestrasLayout.setHorizontalGroup(
                    buscar_muestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(buscar_muestrasLayout.createSequentialGroup()
                        .addGroup(buscar_muestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(buscar_muestrasLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(buscar_muestrasLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(21, Short.MAX_VALUE))
                );
                buscar_muestrasLayout.setVerticalGroup(
                    buscar_muestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_muestrasLayout.createSequentialGroup()
                        .addComponent(jpanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(buscar_muestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(39, Short.MAX_VALUE))
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setBackground(new java.awt.Color(255, 255, 255));

                txtDRxAnormal.setEditable(false);
                txtDRxAnormal.setBackground(new java.awt.Color(255, 255, 255));
                txtDRxAnormal.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtDRxAnormal.setForeground(new java.awt.Color(102, 102, 102));
                txtDRxAnormal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDRxAnormal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtDRxAnormal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtDRxAnormal.setPreferredSize(new java.awt.Dimension(18, 18));
                txtDRxAnormal.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtDRxAnormal.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDRxAnormalCaretUpdate(evt);
                    }
                });
                txtDRxAnormal.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtDRxAnormalMouseClicked(evt);
                    }
                });
                txtDRxAnormal.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtDRxAnormalActionPerformed(evt);
                    }
                });

                txtTMEsputo.setEditable(false);
                txtTMEsputo.setBackground(new java.awt.Color(255, 255, 255));
                txtTMEsputo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtTMEsputo.setForeground(new java.awt.Color(102, 102, 102));
                txtTMEsputo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtTMEsputo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtTMEsputo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtTMEsputo.setPreferredSize(new java.awt.Dimension(18, 18));
                txtTMEsputo.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                txtTMEsputo.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtTMEsputo.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtTMEsputoCaretUpdate(evt);
                    }
                });
                txtTMEsputo.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtTMEsputoMouseClicked(evt);
                    }
                });
                txtTMEsputo.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtTMEsputoActionPerformed(evt);
                    }
                });

                txtATFracaso.setEditable(false);
                txtATFracaso.setBackground(new java.awt.Color(255, 255, 255));
                txtATFracaso.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtATFracaso.setForeground(new java.awt.Color(102, 102, 102));
                txtATFracaso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtATFracaso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtATFracaso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtATFracaso.setPreferredSize(new java.awt.Dimension(18, 18));
                txtATFracaso.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                txtATFracaso.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtATFracaso.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtATFracasoCaretUpdate(evt);
                    }
                });
                txtATFracaso.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtATFracasoMouseClicked(evt);
                    }
                });

                txtATNuncaT.setEditable(false);
                txtATNuncaT.setBackground(new java.awt.Color(255, 255, 255));
                txtATNuncaT.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtATNuncaT.setForeground(new java.awt.Color(102, 102, 102));
                txtATNuncaT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtATNuncaT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtATNuncaT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtATNuncaT.setPreferredSize(new java.awt.Dimension(18, 18));
                txtATNuncaT.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                txtATNuncaT.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtATNuncaT.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtATNuncaTCaretUpdate(evt);
                    }
                });
                txtATNuncaT.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtATNuncaTMouseClicked(evt);
                    }
                });
                txtATNuncaT.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtATNuncaTActionPerformed(evt);
                    }
                });

                txtATAntesT.setEditable(false);
                txtATAntesT.setBackground(new java.awt.Color(255, 255, 255));
                txtATAntesT.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtATAntesT.setForeground(new java.awt.Color(102, 102, 102));
                txtATAntesT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtATAntesT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtATAntesT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtATAntesT.setPreferredSize(new java.awt.Dimension(18, 18));
                txtATAntesT.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtATAntesT.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtATAntesTCaretUpdate(evt);
                    }
                });
                txtATAntesT.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtATAntesTMouseClicked(evt);
                    }
                });

                txtATAbandonoR.setEditable(false);
                txtATAbandonoR.setBackground(new java.awt.Color(255, 255, 255));
                txtATAbandonoR.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtATAbandonoR.setForeground(new java.awt.Color(102, 102, 102));
                txtATAbandonoR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtATAbandonoR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtATAbandonoR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtATAbandonoR.setPreferredSize(new java.awt.Dimension(18, 18));
                txtATAbandonoR.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtATAbandonoR.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtATAbandonoRCaretUpdate(evt);
                    }
                });
                txtATAbandonoR.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtATAbandonoRMouseClicked(evt);
                    }
                });

                txtTMOtro.setEditable(false);
                txtTMOtro.setBackground(new java.awt.Color(255, 255, 255));
                txtTMOtro.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtTMOtro.setForeground(new java.awt.Color(102, 102, 102));
                txtTMOtro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtTMOtro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtTMOtro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtTMOtro.setPreferredSize(new java.awt.Dimension(18, 18));
                txtTMOtro.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                txtTMOtro.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtTMOtro.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtTMOtroCaretUpdate(evt);
                    }
                });
                txtTMOtro.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtTMOtroMouseClicked(evt);
                    }
                });

                jpanel.setBackground(new java.awt.Color(2, 67, 115));

                titulo5.setBackground(new java.awt.Color(0, 102, 102));
                titulo5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
                titulo5.setForeground(new java.awt.Color(255, 255, 255));
                titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                titulo5.setText("Solicitud de Investigación Bacteriológica");
                titulo5.setToolTipText("");
                titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                lblUsu.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                lblUsu.setForeground(new java.awt.Color(255, 255, 255));
                lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
                lblUsu.setText("Usuario");

                btnguardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
                btnguardar.setMnemonic('G');
                btnguardar.setToolTipText("Guardar (Alt-G)");
                btnguardar.setContentAreaFilled(false);
                btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnguardar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnguardarActionPerformed(evt);
                    }
                });

                btnmodificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
                btnmodificar.setMnemonic('M');
                btnmodificar.setToolTipText("Modificar (Alt-M)");
                btnmodificar.setContentAreaFilled(false);
                btnmodificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnmodificar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnmodificarActionPerformed(evt);
                    }
                });

                btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
                btnBuscar.setMnemonic('B');
                btnBuscar.setToolTipText("Buscar (Alt+B)");
                btnBuscar.setContentAreaFilled(false);
                btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarActionPerformed(evt);
                    }
                });

                btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
                btnNuevo.setMnemonic('N');
                btnNuevo.setToolTipText("Nuevo (Alt+N)");
                btnNuevo.setContentAreaFilled(false);
                btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnNuevoActionPerformed(evt);
                    }
                });

                jPanel14.setBackground(new java.awt.Color(255, 255, 255));
                jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtPaciente1.setEditable(false);
                txtPaciente1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtPaciente1.setForeground(new java.awt.Color(51, 51, 51));
                txtPaciente1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPaciente1.setBorder(null);
                txtPaciente1.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPaciente1CaretUpdate(evt);
                    }
                });

                btnBuscarHC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarHC1.setMnemonic('B');
                btnBuscarHC1.setToolTipText("");
                btnBuscarHC1.setBorderPainted(false);
                btnBuscarHC1.setContentAreaFilled(false);
                btnBuscarHC1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarHC1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarHC1ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                jPanel14.setLayout(jPanel14Layout);
                jPanel14Layout.setHorizontalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(txtPaciente1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarHC1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                jPanel14Layout.setVerticalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarHC1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPaciente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(255, 255, 255));
                jLabel6.setText("Nomenclatura");

                javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
                jpanel.setLayout(jpanelLayout);
                jpanelLayout.setHorizontalGroup(
                    jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(650, 650, 650)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                );
                jpanelLayout.setVerticalGroup(
                    jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(titulo5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnmodificar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscar))))
                );

                jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(51, 51, 51));
                jLabel1.setText("DISA / DIRESA:");

                jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(51, 51, 51));
                jLabel2.setText("Red de Salud:");

                jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(51, 51, 51));
                jLabel3.setText("EESS:");

                jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel9.setForeground(new java.awt.Color(51, 51, 51));
                jLabel9.setText("Apellidos y Nombres:");

                jPanel9.setBackground(new java.awt.Color(255, 255, 255));
                jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtPaciente.setEditable(false);
                txtPaciente.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtPaciente.setForeground(new java.awt.Color(51, 51, 51));
                txtPaciente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPaciente.setBorder(null);
                txtPaciente.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPacienteCaretUpdate(evt);
                    }
                });

                btnBuscarHC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarHC.setMnemonic('B');
                btnBuscarHC.setToolTipText("");
                btnBuscarHC.setBorderPainted(false);
                btnBuscarHC.setContentAreaFilled(false);
                btnBuscarHC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarHC.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarHCActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                jPanel9.setLayout(jPanel9Layout);
                jPanel9Layout.setHorizontalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnBuscarHC, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                jPanel9Layout.setVerticalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarHC, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel10.setForeground(new java.awt.Color(51, 51, 51));
                jLabel10.setText("Historia Clínica");

                lblhc.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                lblhc.setForeground(new java.awt.Color(51, 51, 51));

                jLabel12.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel12.setForeground(new java.awt.Color(51, 51, 51));
                jLabel12.setText("Dirección");

                jLabel13.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel13.setForeground(new java.awt.Color(51, 51, 51));
                jLabel13.setText("Referencia:");

                lblDireccion.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                lblDireccion.setForeground(new java.awt.Color(51, 51, 51));

                jLabel15.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel15.setForeground(new java.awt.Color(51, 51, 51));
                jLabel15.setText("Provincia:");

                lblProvincia.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                lblProvincia.setForeground(new java.awt.Color(51, 51, 51));

                jLabel18.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel18.setForeground(new java.awt.Color(51, 51, 51));
                jLabel18.setText("DNI");

                jLabel19.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel19.setForeground(new java.awt.Color(51, 51, 51));
                jLabel19.setText("Edad:");

                jLabel20.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel20.setForeground(new java.awt.Color(51, 51, 51));
                jLabel20.setText("Sexo:");

                lblDNI.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                lblDNI.setForeground(new java.awt.Color(51, 51, 51));

                jLabel22.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel22.setForeground(new java.awt.Color(51, 51, 51));
                jLabel22.setText("Teléfono");

                lblEdad.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                lblEdad.setForeground(new java.awt.Color(51, 51, 51));

                lblSexo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                lblSexo.setForeground(new java.awt.Color(51, 51, 51));

                lblTelefono.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                lblTelefono.setForeground(new java.awt.Color(51, 51, 51));

                jLabel27.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel27.setForeground(new java.awt.Color(51, 51, 51));
                jLabel27.setText("Distrito:");

                lblDistrito.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                lblDistrito.setForeground(new java.awt.Color(51, 51, 51));

                jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                jLabel4.setText("Servicio:");

                jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel5.setForeground(new java.awt.Color(51, 51, 51));
                jLabel5.setText("Cama N°");

                lblCama.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                lblCama.setForeground(new java.awt.Color(51, 51, 51));

                lblServicio.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                lblServicio.setForeground(new java.awt.Color(51, 51, 51));

                jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel7.setForeground(new java.awt.Color(51, 51, 51));
                jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel7.setText("CHINCHA");

                jPanel10.setBackground(new java.awt.Color(255, 255, 255));
                jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtReferencia.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtReferencia.setForeground(new java.awt.Color(51, 51, 51));
                txtReferencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtReferencia.setBorder(null);
                txtReferencia.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtReferenciaCaretUpdate(evt);
                    }
                });
                txtReferencia.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtReferenciaActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                jPanel10.setLayout(jPanel10Layout);
                jPanel10Layout.setHorizontalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtReferencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                );
                jPanel10Layout.setVerticalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                );

                lblDisa.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                lblDisa.setForeground(new java.awt.Color(51, 51, 51));

                lblEess.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                lblEess.setForeground(new java.awt.Color(51, 51, 51));

                jLabel14.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel14.setForeground(new java.awt.Color(51, 51, 51));
                jLabel14.setText("Esputo:");

                jPanel4.setBackground(new java.awt.Color(255, 255, 255));
                jPanel4.setLayout(null);

                jLabel21.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel21.setText("Paciente");
                jPanel4.add(jLabel21);
                jLabel21.setBounds(2, 0, 250, 20);

                jLabel23.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel23.setText("________________________________________________________________________________________________________________________________________________________________________________________________________________________");
                jPanel4.add(jLabel23);
                jLabel23.setBounds(0, 0, 1300, 30);

                lblCodHC.setText("jLabel8");
                jPanel4.add(lblCodHC);
                lblCodHC.setBounds(1030, 0, 170, 14);

                jPanel6.setBackground(new java.awt.Color(255, 255, 255));
                jPanel6.setLayout(null);

                jLabel28.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel28.setText("Tipo de Muestra");
                jPanel6.add(jLabel28);
                jLabel28.setBounds(2, 0, 250, 20);

                jLabel29.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel29.setText("_____________________________________________________________________________________________");
                jPanel6.add(jLabel29);
                jLabel29.setBounds(0, 0, 560, 30);

                jLabel16.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel16.setForeground(new java.awt.Color(51, 51, 51));
                jLabel16.setText("Otro:");

                jPanel11.setBackground(new java.awt.Color(255, 255, 255));
                jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                btnBuscarTM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarTM.setMnemonic('B');
                btnBuscarTM.setToolTipText("");
                btnBuscarTM.setBorderPainted(false);
                btnBuscarTM.setContentAreaFilled(false);
                btnBuscarTM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarTM.setEnabled(false);
                btnBuscarTM.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarTMActionPerformed(evt);
                    }
                });

                txtTMEspecificar.setEditable(false);
                txtTMEspecificar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtTMEspecificar.setForeground(new java.awt.Color(51, 51, 51));
                txtTMEspecificar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtTMEspecificar.setBorder(null);
                txtTMEspecificar.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtTMEspecificarCaretUpdate(evt);
                    }
                });
                txtTMEspecificar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtTMEspecificarActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                jPanel11.setLayout(jPanel11Layout);
                jPanel11Layout.setHorizontalGroup(
                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(txtTMEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnBuscarTM, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );
                jPanel11Layout.setVerticalGroup(
                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarTM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTMEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                );

                jLabel17.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel17.setForeground(new java.awt.Color(51, 51, 51));
                jLabel17.setText("Especificar:");

                jPanel8.setBackground(new java.awt.Color(255, 255, 255));
                jPanel8.setLayout(null);

                jLabel32.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel32.setText("Antecedentes de Tratamiento");
                jPanel8.add(jLabel32);
                jLabel32.setBounds(2, 0, 320, 20);

                jLabel31.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel31.setText("_________________________________________________________________________________________________________");
                jPanel8.add(jLabel31);
                jLabel31.setBounds(0, 0, 630, 30);

                jLabel36.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel36.setForeground(new java.awt.Color(51, 51, 51));
                jLabel36.setText("Nunca Tratado");

                jLabel37.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel37.setForeground(new java.awt.Color(51, 51, 51));
                jLabel37.setText("Antes Tratado: Recaída");

                jLabel38.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel38.setForeground(new java.awt.Color(51, 51, 51));
                jLabel38.setText("Abandono Recup.");

                jLabel39.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel39.setForeground(new java.awt.Color(51, 51, 51));
                jLabel39.setText("Fracaso");

                jPanel13.setBackground(new java.awt.Color(255, 255, 255));
                jPanel13.setLayout(null);

                jLabel40.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel40.setText("Diagnóstico");
                jPanel13.add(jLabel40);
                jLabel40.setBounds(2, 0, 250, 20);

                jLabel33.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel33.setText("_____________________________________________________________________________________________");
                jPanel13.add(jLabel33);
                jLabel33.setBounds(0, 0, 560, 30);

                jLabel44.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel44.setForeground(new java.awt.Color(51, 51, 51));
                jLabel44.setText("S.R.");

                jLabel45.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel45.setForeground(new java.awt.Color(51, 51, 51));
                jLabel45.setText("Seg. Diagnóstico");

                txtDSegDia.setEditable(false);
                txtDSegDia.setBackground(new java.awt.Color(255, 255, 255));
                txtDSegDia.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtDSegDia.setForeground(new java.awt.Color(102, 102, 102));
                txtDSegDia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDSegDia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtDSegDia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtDSegDia.setPreferredSize(new java.awt.Dimension(18, 18));
                txtDSegDia.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtDSegDia.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDSegDiaCaretUpdate(evt);
                    }
                });
                txtDSegDia.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtDSegDiaMouseClicked(evt);
                    }
                });

                jPanel15.setBackground(new java.awt.Color(255, 255, 255));
                jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtDEspecificar.setEditable(false);
                txtDEspecificar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtDEspecificar.setForeground(new java.awt.Color(51, 51, 51));
                txtDEspecificar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDEspecificar.setBorder(null);
                txtDEspecificar.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDEspecificarCaretUpdate(evt);
                    }
                });
                txtDEspecificar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtDEspecificarActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                jPanel15.setLayout(jPanel15Layout);
                jPanel15Layout.setHorizontalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDEspecificar, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                );
                jPanel15Layout.setVerticalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDEspecificar, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                );

                jLabel47.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel47.setForeground(new java.awt.Color(51, 51, 51));
                jLabel47.setText("RX Anormal");

                txtDSR.setEditable(false);
                txtDSR.setBackground(new java.awt.Color(255, 255, 255));
                txtDSR.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtDSR.setForeground(new java.awt.Color(102, 102, 102));
                txtDSR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDSR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtDSR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtDSR.setPreferredSize(new java.awt.Dimension(18, 18));
                txtDSR.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtDSR.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDSRCaretUpdate(evt);
                    }
                });
                txtDSR.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtDSRMouseClicked(evt);
                    }
                });

                jLabel48.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel48.setForeground(new java.awt.Color(51, 51, 51));
                jLabel48.setText("Otro");

                txtDOtro.setEditable(false);
                txtDOtro.setBackground(new java.awt.Color(255, 255, 255));
                txtDOtro.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtDOtro.setForeground(new java.awt.Color(102, 102, 102));
                txtDOtro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDOtro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtDOtro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtDOtro.setPreferredSize(new java.awt.Dimension(18, 18));
                txtDOtro.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtDOtro.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDOtroCaretUpdate(evt);
                    }
                });
                txtDOtro.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtDOtroMouseClicked(evt);
                    }
                });

                jPanel16.setBackground(new java.awt.Color(255, 255, 255));
                jPanel16.setLayout(null);

                jLabel46.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel46.setText("Control de Tratamiento");
                jPanel16.add(jLabel46);
                jLabel46.setBounds(2, 0, 250, 20);

                jLabel51.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel51.setText("_________________________________________________________________________________________________________");
                jPanel16.add(jLabel51);
                jLabel51.setBounds(0, 0, 630, 30);

                jLabel52.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel52.setForeground(new java.awt.Color(51, 51, 51));
                jLabel52.setText("Mes");

                txtCTMes.setEditable(false);
                txtCTMes.setBackground(new java.awt.Color(255, 255, 255));
                txtCTMes.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtCTMes.setForeground(new java.awt.Color(102, 102, 102));
                txtCTMes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtCTMes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtCTMes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtCTMes.setPreferredSize(new java.awt.Dimension(18, 18));
                txtCTMes.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtCTMes.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCTMesCaretUpdate(evt);
                    }
                });
                txtCTMes.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtCTMesMouseClicked(evt);
                    }
                });
                txtCTMes.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtCTMesActionPerformed(evt);
                    }
                });

                jLabel53.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel53.setForeground(new java.awt.Color(51, 51, 51));
                jLabel53.setText("Esq. TB sensible");

                txtCTEsqTB.setEditable(false);
                txtCTEsqTB.setBackground(new java.awt.Color(255, 255, 255));
                txtCTEsqTB.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtCTEsqTB.setForeground(new java.awt.Color(102, 102, 102));
                txtCTEsqTB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtCTEsqTB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtCTEsqTB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtCTEsqTB.setPreferredSize(new java.awt.Dimension(18, 18));
                txtCTEsqTB.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtCTEsqTB.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCTEsqTBCaretUpdate(evt);
                    }
                });
                txtCTEsqTB.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtCTEsqTBMouseClicked(evt);
                    }
                });

                jLabel54.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel54.setForeground(new java.awt.Color(51, 51, 51));
                jLabel54.setText("Esq. DR:");

                txtCTEsqDR.setEditable(false);
                txtCTEsqDR.setBackground(new java.awt.Color(255, 255, 255));
                txtCTEsqDR.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtCTEsqDR.setForeground(new java.awt.Color(102, 102, 102));
                txtCTEsqDR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtCTEsqDR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtCTEsqDR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtCTEsqDR.setPreferredSize(new java.awt.Dimension(18, 18));
                txtCTEsqDR.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtCTEsqDR.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCTEsqDRCaretUpdate(evt);
                    }
                });
                txtCTEsqDR.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtCTEsqDRMouseClicked(evt);
                    }
                });

                jLabel55.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel55.setForeground(new java.awt.Color(51, 51, 51));
                jLabel55.setText("Esq. MDR:");

                txtCTEsqMDR.setEditable(false);
                txtCTEsqMDR.setBackground(new java.awt.Color(255, 255, 255));
                txtCTEsqMDR.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtCTEsqMDR.setForeground(new java.awt.Color(102, 102, 102));
                txtCTEsqMDR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtCTEsqMDR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtCTEsqMDR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtCTEsqMDR.setPreferredSize(new java.awt.Dimension(18, 18));
                txtCTEsqMDR.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtCTEsqMDR.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCTEsqMDRCaretUpdate(evt);
                    }
                });
                txtCTEsqMDR.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtCTEsqMDRMouseClicked(evt);
                    }
                });

                jLabel56.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel56.setForeground(new java.awt.Color(51, 51, 51));
                jLabel56.setText("Esq. XDR:");

                txtCTEsqXDR.setEditable(false);
                txtCTEsqXDR.setBackground(new java.awt.Color(255, 255, 255));
                txtCTEsqXDR.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtCTEsqXDR.setForeground(new java.awt.Color(102, 102, 102));
                txtCTEsqXDR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtCTEsqXDR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtCTEsqXDR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtCTEsqXDR.setPreferredSize(new java.awt.Dimension(18, 18));
                txtCTEsqXDR.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtCTEsqXDR.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCTEsqXDRCaretUpdate(evt);
                    }
                });
                txtCTEsqXDR.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtCTEsqXDRMouseClicked(evt);
                    }
                });

                jLabel57.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel57.setForeground(new java.awt.Color(51, 51, 51));
                jLabel57.setText("Otros:");

                txtCTOtros.setEditable(false);
                txtCTOtros.setBackground(new java.awt.Color(255, 255, 255));
                txtCTOtros.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtCTOtros.setForeground(new java.awt.Color(102, 102, 102));
                txtCTOtros.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtCTOtros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtCTOtros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtCTOtros.setPreferredSize(new java.awt.Dimension(18, 18));
                txtCTOtros.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtCTOtros.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCTOtrosCaretUpdate(evt);
                    }
                });
                txtCTOtros.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtCTOtrosMouseClicked(evt);
                    }
                });

                jPanel18.setBackground(new java.awt.Color(255, 255, 255));
                jPanel18.setLayout(null);

                jLabel58.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel58.setText("Ex. Solicitado Baciloscopía");
                jPanel18.add(jLabel58);
                jLabel58.setBounds(2, 0, 250, 20);

                jLabel35.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel35.setText("_____________________________________________________________________________________________");
                jPanel18.add(jLabel35);
                jLabel35.setBounds(0, 0, 560, 30);

                jLabel62.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel62.setForeground(new java.awt.Color(51, 51, 51));
                jLabel62.setText("1ra M");

                txtSB1.setEditable(false);
                txtSB1.setBackground(new java.awt.Color(255, 255, 255));
                txtSB1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtSB1.setForeground(new java.awt.Color(102, 102, 102));
                txtSB1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtSB1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtSB1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtSB1.setPreferredSize(new java.awt.Dimension(18, 18));
                txtSB1.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtSB1.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtSB1CaretUpdate(evt);
                    }
                });
                txtSB1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtSB1MouseClicked(evt);
                    }
                });
                txtSB1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtSB1ActionPerformed(evt);
                    }
                });

                jLabel63.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel63.setForeground(new java.awt.Color(51, 51, 51));
                jLabel63.setText("2da M");

                txtSB2.setEditable(false);
                txtSB2.setBackground(new java.awt.Color(255, 255, 255));
                txtSB2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtSB2.setForeground(new java.awt.Color(102, 102, 102));
                txtSB2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtSB2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtSB2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtSB2.setPreferredSize(new java.awt.Dimension(18, 18));
                txtSB2.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtSB2.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtSB2CaretUpdate(evt);
                    }
                });
                txtSB2.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtSB2MouseClicked(evt);
                    }
                });

                jLabel64.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel64.setForeground(new java.awt.Color(51, 51, 51));
                jLabel64.setText("Otras(especificar N°)");

                jPanel20.setBackground(new java.awt.Color(255, 255, 255));
                jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtSBOtras.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtSBOtras.setForeground(new java.awt.Color(51, 51, 51));
                txtSBOtras.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtSBOtras.setBorder(null);
                txtSBOtras.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtSBOtrasCaretUpdate(evt);
                    }
                });
                txtSBOtras.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtSBOtrasMouseClicked(evt);
                    }
                });
                txtSBOtras.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtSBOtrasActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
                jPanel20.setLayout(jPanel20Layout);
                jPanel20Layout.setHorizontalGroup(
                    jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSBOtras, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                );
                jPanel20Layout.setVerticalGroup(
                    jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSBOtras, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                );

                jLabel65.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel65.setForeground(new java.awt.Color(51, 51, 51));
                jLabel65.setText("Cultivo:");

                jPanel22.setBackground(new java.awt.Color(255, 255, 255));
                jPanel22.setLayout(null);

                jLabel66.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel66.setText("Prueba de Sensibilidad");
                jPanel22.add(jLabel66);
                jLabel66.setBounds(2, 0, 250, 20);

                jLabel59.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel59.setText("_________________________________________________________________________________________________________");
                jPanel22.add(jLabel59);
                jLabel59.setBounds(0, 0, 630, 30);

                jLabel70.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel70.setForeground(new java.awt.Color(51, 51, 51));
                jLabel70.setText("Rápida");

                txtPSRapida.setEditable(false);
                txtPSRapida.setBackground(new java.awt.Color(255, 255, 255));
                txtPSRapida.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtPSRapida.setForeground(new java.awt.Color(102, 102, 102));
                txtPSRapida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPSRapida.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtPSRapida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtPSRapida.setPreferredSize(new java.awt.Dimension(18, 18));
                txtPSRapida.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtPSRapida.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPSRapidaCaretUpdate(evt);
                    }
                });
                txtPSRapida.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtPSRapidaMouseClicked(evt);
                    }
                });
                txtPSRapida.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtPSRapidaActionPerformed(evt);
                    }
                });

                jLabel71.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel71.setForeground(new java.awt.Color(51, 51, 51));
                jLabel71.setText("Especificar:");

                jPanel24.setBackground(new java.awt.Color(255, 255, 255));
                jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtPSREspecificar.setEditable(false);
                txtPSREspecificar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtPSREspecificar.setForeground(new java.awt.Color(51, 51, 51));
                txtPSREspecificar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPSREspecificar.setBorder(null);
                txtPSREspecificar.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPSREspecificarCaretUpdate(evt);
                    }
                });
                txtPSREspecificar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtPSREspecificarActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
                jPanel24.setLayout(jPanel24Layout);
                jPanel24Layout.setHorizontalGroup(
                    jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPSREspecificar)
                );
                jPanel24Layout.setVerticalGroup(
                    jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPSREspecificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                );

                jLabel72.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel72.setForeground(new java.awt.Color(51, 51, 51));
                jLabel72.setText("Convencional:");

                txtPSConvencional.setEditable(false);
                txtPSConvencional.setBackground(new java.awt.Color(255, 255, 255));
                txtPSConvencional.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtPSConvencional.setForeground(new java.awt.Color(102, 102, 102));
                txtPSConvencional.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPSConvencional.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtPSConvencional.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtPSConvencional.setPreferredSize(new java.awt.Dimension(18, 18));
                txtPSConvencional.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtPSConvencional.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPSConvencionalCaretUpdate(evt);
                    }
                });
                txtPSConvencional.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtPSConvencionalMouseClicked(evt);
                    }
                });
                txtPSConvencional.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtPSConvencionalActionPerformed(evt);
                    }
                });

                jLabel73.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel73.setForeground(new java.awt.Color(51, 51, 51));
                jLabel73.setText("Especificar:");

                jPanel25.setBackground(new java.awt.Color(255, 255, 255));
                jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtPSCEspecificar.setEditable(false);
                txtPSCEspecificar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtPSCEspecificar.setForeground(new java.awt.Color(51, 51, 51));
                txtPSCEspecificar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPSCEspecificar.setBorder(null);
                txtPSCEspecificar.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPSCEspecificarCaretUpdate(evt);
                    }
                });
                txtPSCEspecificar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtPSCEspecificarActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
                jPanel25.setLayout(jPanel25Layout);
                jPanel25Layout.setHorizontalGroup(
                    jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPSCEspecificar, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                jPanel25Layout.setVerticalGroup(
                    jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPSCEspecificar, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                );

                jPanel26.setBackground(new java.awt.Color(255, 255, 255));
                jPanel26.setLayout(null);

                jLabel74.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel74.setText("Factores de Riesgo TB resistente a medicamentos:");
                jPanel26.add(jLabel74);
                jLabel74.setBounds(2, 0, 320, 20);

                jLabel42.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel42.setText("_____________________________________________________________________________________________");
                jPanel26.add(jLabel42);
                jLabel42.setBounds(0, 0, 560, 30);

                jLabel76.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel76.setForeground(new java.awt.Color(51, 51, 51));
                jLabel76.setText("Otro Examen (Especificar):");

                jPanel27.setBackground(new java.awt.Color(255, 255, 255));
                jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtPSOtroEspecificar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtPSOtroEspecificar.setForeground(new java.awt.Color(51, 51, 51));
                txtPSOtroEspecificar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPSOtroEspecificar.setBorder(null);
                txtPSOtroEspecificar.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPSOtroEspecificarCaretUpdate(evt);
                    }
                });
                txtPSOtroEspecificar.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtPSOtroEspecificarMouseClicked(evt);
                    }
                });
                txtPSOtroEspecificar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtPSOtroEspecificarActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
                jPanel27.setLayout(jPanel27Layout);
                jPanel27Layout.setHorizontalGroup(
                    jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(txtPSOtroEspecificar, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                        .addContainerGap())
                );
                jPanel27Layout.setVerticalGroup(
                    jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPSOtroEspecificar, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                );

                txtSBCultivo.setEditable(false);
                txtSBCultivo.setBackground(new java.awt.Color(255, 255, 255));
                txtSBCultivo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtSBCultivo.setForeground(new java.awt.Color(102, 102, 102));
                txtSBCultivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtSBCultivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtSBCultivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                txtSBCultivo.setPreferredSize(new java.awt.Dimension(18, 18));
                txtSBCultivo.setSelectionColor(new java.awt.Color(0, 51, 102));
                txtSBCultivo.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtSBCultivoCaretUpdate(evt);
                    }
                });
                txtSBCultivo.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtSBCultivoMouseClicked(evt);
                    }
                });

                jPanel28.setBackground(new java.awt.Color(255, 255, 255));
                jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtReferencia8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtReferencia8.setForeground(new java.awt.Color(51, 51, 51));
                txtReferencia8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtReferencia8.setBorder(null);
                txtReferencia8.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtReferencia8CaretUpdate(evt);
                    }
                });
                txtReferencia8.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtReferencia8ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
                jPanel28.setLayout(jPanel28Layout);
                jPanel28Layout.setHorizontalGroup(
                    jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtReferencia8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                );
                jPanel28Layout.setVerticalGroup(
                    jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtReferencia8, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                );

                jLabel24.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel24.setForeground(new java.awt.Color(51, 51, 51));
                jLabel24.setText("Datos del Solicitante:");

                jPanel12.setBackground(new java.awt.Color(255, 255, 255));
                jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtPacienteSol.setEditable(false);
                txtPacienteSol.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtPacienteSol.setForeground(new java.awt.Color(51, 51, 51));
                txtPacienteSol.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPacienteSol.setBorder(null);
                txtPacienteSol.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPacienteSolCaretUpdate(evt);
                    }
                });

                btnBuscarHC2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarHC2.setMnemonic('B');
                btnBuscarHC2.setToolTipText("");
                btnBuscarHC2.setBorderPainted(false);
                btnBuscarHC2.setContentAreaFilled(false);
                btnBuscarHC2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarHC2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarHC2ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                jPanel12.setLayout(jPanel12Layout);
                jPanel12Layout.setHorizontalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(txtPacienteSol, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnBuscarHC2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );
                jPanel12Layout.setVerticalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarHC2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPacienteSol, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel26.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel26.setForeground(new java.awt.Color(51, 51, 51));
                jLabel26.setText("Teléfono");

                lblTelefonoSol.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                lblTelefonoSol.setForeground(new java.awt.Color(51, 51, 51));

                jLabel30.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel30.setForeground(new java.awt.Color(51, 51, 51));
                jLabel30.setText("Celular");

                lblCel.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                lblCel.setForeground(new java.awt.Color(51, 51, 51));

                jPanel30.setBackground(new java.awt.Color(255, 255, 255));
                jPanel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtReferencia9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtReferencia9.setForeground(new java.awt.Color(51, 51, 51));
                txtReferencia9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtReferencia9.setBorder(null);
                txtReferencia9.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtReferencia9CaretUpdate(evt);
                    }
                });
                txtReferencia9.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtReferencia9ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                jPanel30.setLayout(jPanel30Layout);
                jPanel30Layout.setHorizontalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(txtReferencia9, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                        .addContainerGap())
                );
                jPanel30Layout.setVerticalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtReferencia9, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                );

                jLabel79.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel79.setForeground(new java.awt.Color(51, 51, 51));
                jLabel79.setText("Observaciones:");

                jLabel80.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel80.setForeground(new java.awt.Color(51, 51, 51));
                jLabel80.setText("Fecha de Obtención:");

                jLabel60.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel60.setForeground(new java.awt.Color(51, 51, 51));
                jLabel60.setText("Inadecuada:");

                txtMInadecuada.setEditable(false);
                txtMInadecuada.setBackground(new java.awt.Color(255, 255, 255));
                txtMInadecuada.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtMInadecuada.setForeground(new java.awt.Color(102, 102, 102));
                txtMInadecuada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtMInadecuada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtMInadecuada.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtMInadecuada.setPreferredSize(new java.awt.Dimension(18, 18));
                txtMInadecuada.setSelectionColor(new java.awt.Color(255, 51, 51));
                txtMInadecuada.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtMInadecuadaCaretUpdate(evt);
                    }
                });
                txtMInadecuada.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtMInadecuadaMouseClicked(evt);
                    }
                });

                jLabel61.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel61.setForeground(new java.awt.Color(51, 51, 51));
                jLabel61.setText("Adecuada");

                txtMAdecuada.setEditable(false);
                txtMAdecuada.setBackground(new java.awt.Color(255, 255, 255));
                txtMAdecuada.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtMAdecuada.setForeground(new java.awt.Color(102, 102, 102));
                txtMAdecuada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtMAdecuada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtMAdecuada.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtMAdecuada.setPreferredSize(new java.awt.Dimension(18, 18));
                txtMAdecuada.setSelectionColor(new java.awt.Color(255, 51, 51));
                txtMAdecuada.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtMAdecuadaCaretUpdate(evt);
                    }
                });
                txtMAdecuada.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtMAdecuadaMouseClicked(evt);
                    }
                });

                jLabel77.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                jLabel77.setForeground(new java.awt.Color(51, 51, 51));
                jLabel77.setText("Calidad de la Muestra:");

                jPanel23.setBackground(new java.awt.Color(255, 255, 255));
                jPanel23.setLayout(null);

                jLabel67.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel67.setText("Muestra (Para ser llenado por Laboratorio)");
                jPanel23.add(jLabel67);
                jLabel67.setBounds(2, 0, 300, 20);

                jLabel68.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel68.setText("_________________________________________________________________________________________________________");
                jPanel23.add(jLabel68);
                jLabel68.setBounds(0, 0, 630, 30);

                jPanel29.setBackground(new java.awt.Color(255, 255, 255));
                jPanel29.setLayout(null);

                jLabel75.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel75.setText("Datos del Solicitante (Para ser llenado por Laboratorio)");
                jPanel29.add(jLabel75);
                jLabel75.setBounds(2, 0, 350, 20);

                jLabel49.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel49.setText("_____________________________________________________________________________________________");
                jPanel29.add(jLabel49);
                jLabel49.setBounds(0, 0, 560, 30);

                lblCodHCSol.setText("jLabel6");
                jPanel29.add(lblCodHCSol);
                lblCodHCSol.setBounds(380, 0, 180, 14);

                jPanel31.setBackground(new java.awt.Color(255, 255, 255));
                jPanel31.setLayout(null);

                jLabel69.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel69.setText("Observaciones (Para ser llenado por Laboratorio)");
                jPanel31.add(jLabel69);
                jLabel69.setBounds(2, 0, 390, 20);

                jLabel78.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel78.setText("_________________________________________________________________________________________________________");
                jPanel31.add(jLabel78);
                jLabel78.setBounds(0, 0, 630, 30);

                lblCodTMEspecificar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                lblCodTMEspecificar.setForeground(new java.awt.Color(51, 51, 51));
                lblCodTMEspecificar.setText("Esputo:");

                jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));
                jDateChooser1.setForeground(new java.awt.Color(51, 51, 51));
                jDateChooser1.setDateFormatString("dd-MM-yyyy");
                jDateChooser1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel1)
                                .addGap(358, 358, 358)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(110, 110, 110)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                            .addComponent(lblSexo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(101, 101, 101)
                                                .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(298, 298, 298)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblCama, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(155, 155, 155)
                                            .addComponent(txtDRxAnormal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(42, 42, 42)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtDSR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(lblDisa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblhc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(57, 57, 57)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lblEess, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(17, 17, 17)
                                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(7, 7, 7)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(lblDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(lblDNI, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                                                                .addComponent(lblEdad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(73, 73, 73)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtDOtro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel45)
                                                .addGap(12, 12, 12)
                                                .addComponent(txtDSegDia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCTOtros, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCTEsqDR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(107, 107, 107))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(41, 41, 41)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(63, 63, 63)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtTMOtro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTMEsputo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(76, 76, 76)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblCodTMEspecificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                                            .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel55)
                                                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtCTMes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtCTEsqMDR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(87, 87, 87))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(txtATNuncaT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(24, 24, 24))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(txtATAbandonoR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(24, 24, 24)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addGap(221, 221, 221)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel57)
                                                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel56)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(txtCTEsqXDR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(txtCTEsqTB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addGap(123, 123, 123)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtATAntesT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(txtATFracaso, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                .addGap(106, 106, 106))
                                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                                            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(58, 58, 58))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel26))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblTelefonoSol, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(44, 44, 44)
                                                .addComponent(jLabel30)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblCel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(109, 181, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(47, 47, 47)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(29, 29, 29)
                                                        .addComponent(txtMAdecuada, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(55, 55, 55)
                                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtMInadecuada, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(19, 19, 19))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(187, 187, 187)
                                                .addComponent(jLabel79)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(859, 859, 859)
                                                .addComponent(jLabel76))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(37, 37, 37)
                                                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(27, 27, 27))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(jLabel64)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtSB1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(64, 64, 64)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(50, 50, 50)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(txtSBCultivo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtSB2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtPSRapida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtPSConvencional, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(103, 103, 103)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel73)
                                                    .addComponent(jLabel71))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap())))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(10, 10, 10)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                                                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel3))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel1)
                                                                    .addComponent(lblEess, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblDisa, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, 0)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                                            .addComponent(lblSexo, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(lblServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(lblCama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(jLabel18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(lblhc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addComponent(lblDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel22))
                                                            .addComponent(jLabel12)
                                                            .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel27)
                                                        .addComponent(lblDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, 0)
                                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addGap(4, 4, 4)
                                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtTMEsputo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtATAntesT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtATNuncaT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(2, 2, 2))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblCodTMEspecificar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtTMOtro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtATFracaso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtATAbandonoR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDSR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(3, 3, 3)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtDOtro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtDRxAnormal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtDSegDia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCTMes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCTEsqTB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCTEsqDR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtCTEsqMDR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCTEsqXDR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCTOtros, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, 0)
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSB2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtSB1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1)
                                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPSRapida, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtSBCultivo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtPSConvencional, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel80, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMInadecuada, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel77)
                                        .addComponent(txtMAdecuada, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                        .addComponent(jLabel30))
                                    .addComponent(lblTelefonoSol, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblCel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void txtDRxAnormalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDRxAnormalCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDRxAnormalCaretUpdate

    private void txtDRxAnormalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDRxAnormalMouseClicked
       
            if(txtDRxAnormal.getText().equals("") && evt.getClickCount()==1){
                txtDRxAnormal.setText("X");
                txtDSR.setText("");
                txtDSegDia.setText("");
                txtDOtro.setText("");
                 txtDEspecificar.setEditable(false);
                 txtDEspecificar.setText("");
            }else
            if(txtDRxAnormal.getText().equals("X") && evt.getClickCount()==1){
                txtDRxAnormal.setText("");
            }

        
        
    }//GEN-LAST:event_txtDRxAnormalMouseClicked

    private void txtTMEsputoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTMEsputoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTMEsputoCaretUpdate

    private void txtTMEsputoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTMEsputoMouseClicked
      
            if(txtTMEsputo.getText().equals("") && evt.getClickCount()==1){
                txtTMEsputo.setText("X");
                txtTMOtro.setText("");
                txtTMEspecificar.setText("");
                lblCodTMEspecificar.setText("");
                try{
                    String cod="";
                    serviciosVarios obj = new serviciosVarios();
                    String consulta = "exec sp_LAB_MUESTRA_EXAMEN_buscar ?";
            
                    PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
                    cmd.setString(1, "Esputo");
                    ResultSet r = cmd.executeQuery();
                    while (r.next()) {
                    cod = r.getString(1);
                    }
                    lblCodTMEspecificar.setText(cod);
                } catch(Exception e){
                    
                }
              
            }else
            if(txtTMEsputo.getText().equals("X") && evt.getClickCount()==1){
                txtTMEsputo.setText("");
            }
           
        
    }//GEN-LAST:event_txtTMEsputoMouseClicked

    private void txtATFracasoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtATFracasoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtATFracasoCaretUpdate

    private void txtATFracasoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtATFracasoMouseClicked
      
            if(txtATFracaso.getText().equals("") && evt.getClickCount()==1){
                txtATFracaso.setText("X");
                txtATNuncaT.setText("");
                txtATAntesT.setText("");
                txtATAbandonoR.setText("");
            }else
            if(txtATFracaso.getText().equals("X") && evt.getClickCount()==1){
                txtATFracaso.setText("");
            }
            
       
    }//GEN-LAST:event_txtATFracasoMouseClicked

    private void txtATNuncaTCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtATNuncaTCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtATNuncaTCaretUpdate

    private void txtATNuncaTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtATNuncaTMouseClicked
        
            if(txtATNuncaT.getText().equals("") && evt.getClickCount()==1){
                txtATNuncaT.setText("X");
                txtATAntesT.setText("");
                txtATAbandonoR.setText("");
                txtATFracaso.setText("");
            }else
            if(txtATNuncaT.getText().equals("X") && evt.getClickCount()==1){
                txtATNuncaT.setText("");
            }
            
        
    }//GEN-LAST:event_txtATNuncaTMouseClicked

    private void txtATAntesTCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtATAntesTCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtATAntesTCaretUpdate

    private void txtATAntesTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtATAntesTMouseClicked
    
            if(txtATAntesT.getText().equals("") && evt.getClickCount()==1){
                txtATAntesT.setText("X");
                txtATNuncaT.setText("");
                txtATAbandonoR.setText("");
                txtATFracaso.setText("");
            }else
            if(txtATAntesT.getText().equals("X") && evt.getClickCount()==1){
                txtATAntesT.setText("");
            }
       
        
    }//GEN-LAST:event_txtATAntesTMouseClicked

    private void txtATAbandonoRCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtATAbandonoRCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtATAbandonoRCaretUpdate

    private void txtATAbandonoRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtATAbandonoRMouseClicked
      
            if(txtATAbandonoR.getText().equals("") && evt.getClickCount()==1){
                txtATAbandonoR.setText("X");
                 txtATNuncaT.setText("");
                txtATAntesT.setText("");
                txtATFracaso.setText("");
            }else
            if(txtATAbandonoR.getText().equals("X") && evt.getClickCount()==1){
                txtATAbandonoR.setText("");
            }
         
        
    }//GEN-LAST:event_txtATAbandonoRMouseClicked

    private void txtTMOtroCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTMOtroCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTMOtroCaretUpdate

    private void txtTMOtroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTMOtroMouseClicked
        
            if(txtTMOtro.getText().equals("") && evt.getClickCount()==1){
                txtTMOtro.setText("X");
                txtTMEsputo.setText("");

                btnBuscarTM.setEnabled(true);
            }else
            if(txtTMOtro.getText().equals("X") && evt.getClickCount()==1){
                txtTMOtro.setText("");

                btnBuscarTM.setEnabled(false);
            }
        
    }//GEN-LAST:event_txtTMOtroMouseClicked

    private void btnBuscarHCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarHCActionPerformed
  buscar_HC.setVisible(true);
        txtbuscarHC.setText("");
        LAB_HC_cargar();
        LAB_HC_formato();      
        lblTipo.setText("1");
    }//GEN-LAST:event_btnBuscarHCActionPerformed

    private void txtPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPacienteCaretUpdate

    }//GEN-LAST:event_txtPacienteCaretUpdate

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:
        String consulta="";
         try {
             String titulos[]={"N°","Codigo","N° H.C.","Paciente","DNI","Telefono","Celular","Dirección","Fecha de Nac.","Edad","Sexo"
             ,"Distrito","Provincia","Departamento"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[14];

            LAB_Clasificacion_Examen obj=new LAB_Clasificacion_Examen();
         consulta="exec sp_BuscarHC ?";
        
        PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtbuscarHC.getText());
        ResultSet r=cmd.executeQuery();
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
                m.addRow(fila);
                c++;
            }
            tb_HC.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_HC.setRowSorter(elQueOrdena);
            this.tb_HC.setModel(m);
            
            LAB_HC_formato();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void txtbuscarHCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarHCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarHCActionPerformed

    private void txtbuscarHCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarHCKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_HC.getSelectionModel().setSelectionInterval(0, 0);
            tb_HC.requestFocus();
        }
    }//GEN-LAST:event_txtbuscarHCKeyPressed

    private void txtbuscarHCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarHCKeyTyped
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscar1.doClick();
        }
    }//GEN-LAST:event_txtbuscarHCKeyTyped

    private void tb_HCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_HCMouseClicked

    }//GEN-LAST:event_tb_HCMouseClicked

    private void tb_HCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_HCKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{
                if(tb_HC.getRowCount()>0){
                buscar_HC.setVisible(false);
                int filaselec=tb_HC.getSelectedRow();

                if(lblTipo.getText().equalsIgnoreCase("1")){
                lblCodHC.setText(tb_HC.getValueAt(filaselec, 1).toString());
                lblhc.setText(tb_HC.getValueAt(filaselec, 2).toString());
                txtPaciente.setText(tb_HC.getValueAt(filaselec, 3).toString());
                lblDNI.setText(tb_HC.getValueAt(filaselec, 4).toString());
                lblTelefono.setText(tb_HC.getValueAt(filaselec, 5).toString());
                lblDireccion.setText(tb_HC.getValueAt(filaselec, 7).toString());
                lblEdad.setText(tb_HC.getValueAt(filaselec, 9).toString());
                lblSexo.setText(tb_HC.getValueAt(filaselec, 10).toString());
                lblDistrito.setText(tb_HC.getValueAt(filaselec, 11).toString());
                lblProvincia.setText(tb_HC.getValueAt(filaselec, 12).toString());
                
              
                //HOSPITALIZACION
                LAB_Toma_Muestra_Detalle hosp=new LAB_Toma_Muestra_Detalle();
                if(hosp.LAB_Toma_Muestra_Hospitalizacion_ver(tb_HC.getValueAt(filaselec, 1).toString())>0){
                   LAB_Toma_Muestra_Detalle td=new LAB_Toma_Muestra_Detalle();
                 String serv_hospi=  td.LAB_Toma_Muestra_Hospi_Servicio(tb_HC.getValueAt(filaselec, 1).toString());
                lblServicio.setText(serv_hospi);
                
                LAB_Toma_Muestra_Detalle td1=new LAB_Toma_Muestra_Detalle();
                String cama=  td1.LAB_Toma_Muestra_Hospi_cama(tb_HC.getValueAt(filaselec, 1).toString());
                lblCama.setText(cama);
                
                }else{
                    
                   lblServicio.setText("-");
                   lblCama.setText("-");
                }
                }else if(lblTipo.getText().equalsIgnoreCase("2")){
                    lblCodHCSol.setText(tb_HC.getValueAt(filaselec, 1).toString());
                
                txtPacienteSol.setText(tb_HC.getValueAt(filaselec, 3).toString());
                
                lblTelefonoSol.setText(tb_HC.getValueAt(filaselec, 5).toString());
                lblCel.setText(tb_HC.getValueAt(filaselec, 6).toString());
                }
            }}
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_HCKeyPressed

    private void txtReferenciaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtReferenciaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReferenciaCaretUpdate

    private void txtReferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReferenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReferenciaActionPerformed

    private void txtDRxAnormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDRxAnormalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDRxAnormalActionPerformed

    private void txtTMEsputoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTMEsputoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTMEsputoActionPerformed

    private void txtDSegDiaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDSegDiaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDSegDiaCaretUpdate

    private void txtDSegDiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDSegDiaMouseClicked
        if(txtDSegDia.getText().equals("") && evt.getClickCount()==1){
                txtDSegDia.setText("X");
                txtDSR.setText("");
                txtDRxAnormal.setText("");
                txtDOtro.setText("");
                 txtDEspecificar.setEditable(false);
                 txtDEspecificar.setText("");
            }else
            if(txtDSegDia.getText().equals("X") && evt.getClickCount()==1){
                txtDSegDia.setText("");
            }
    }//GEN-LAST:event_txtDSegDiaMouseClicked

    private void txtDEspecificarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDEspecificarCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDEspecificarCaretUpdate

    private void txtDEspecificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDEspecificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDEspecificarActionPerformed

    private void txtDSRCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDSRCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDSRCaretUpdate

    private void txtDSRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDSRMouseClicked
        if(txtDSR.getText().equals("") && evt.getClickCount()==1){
                txtDSR.setText("X");
                txtDSegDia.setText("");
                txtDRxAnormal.setText("");
                txtDOtro.setText("");
                
                 txtDEspecificar.setEditable(false);
                 txtDEspecificar.setText("");
            }else
            if(txtDSR.getText().equals("X") && evt.getClickCount()==1){
                txtDSR.setText("");
            }
    }//GEN-LAST:event_txtDSRMouseClicked

    private void txtDOtroCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDOtroCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDOtroCaretUpdate

    private void txtDOtroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDOtroMouseClicked
       if(txtDOtro.getText().equals("") && evt.getClickCount()==1){
                txtDOtro.setText("X");
                txtDSR.setText("");
                txtDSegDia.setText("");
                txtDRxAnormal.setText("");
                
                txtDEspecificar.setEditable(true);
            }else
            if(txtDOtro.getText().equals("X") && evt.getClickCount()==1){
                txtDOtro.setText("");
                txtDEspecificar.setEditable(false);
            }
    }//GEN-LAST:event_txtDOtroMouseClicked

    private void txtCTMesCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCTMesCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCTMesCaretUpdate

    private void txtCTMesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCTMesMouseClicked
       if(txtCTMes.getText().equals("") && evt.getClickCount()==1){
                txtCTMes.setText("X");
                txtCTEsqTB.setText("");
                txtCTEsqDR.setText("");
                txtCTEsqMDR.setText("");
                txtCTEsqXDR.setText("");
                txtCTOtros.setText("");
            }else
            if(txtCTMes.getText().equals("X") && evt.getClickCount()==1){
                txtCTMes.setText("");
            }
    }//GEN-LAST:event_txtCTMesMouseClicked

    private void txtCTMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCTMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCTMesActionPerformed

    private void txtCTEsqTBCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCTEsqTBCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCTEsqTBCaretUpdate

    private void txtCTEsqTBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCTEsqTBMouseClicked
         if(txtCTEsqTB.getText().equals("") && evt.getClickCount()==1){
                txtCTEsqTB.setText("X");
                txtCTMes.setText("");
                txtCTEsqDR.setText("");
                txtCTEsqMDR.setText("");
                txtCTEsqXDR.setText("");
                txtCTOtros.setText("");
            }else
            if(txtCTEsqTB.getText().equals("X") && evt.getClickCount()==1){
                txtCTEsqTB.setText("");
            }
    }//GEN-LAST:event_txtCTEsqTBMouseClicked

    private void txtCTEsqDRCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCTEsqDRCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCTEsqDRCaretUpdate

    private void txtCTEsqDRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCTEsqDRMouseClicked
       if(txtCTEsqDR.getText().equals("") && evt.getClickCount()==1){
                txtCTEsqDR.setText("X");
                txtCTMes.setText("");
                txtCTEsqTB.setText("");
                txtCTEsqMDR.setText("");
                txtCTEsqXDR.setText("");
                txtCTOtros.setText("");
            }else
            if(txtCTEsqDR.getText().equals("X") && evt.getClickCount()==1){
                txtCTEsqDR.setText("");
            }
    }//GEN-LAST:event_txtCTEsqDRMouseClicked

    private void txtCTEsqMDRCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCTEsqMDRCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCTEsqMDRCaretUpdate

    private void txtCTEsqMDRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCTEsqMDRMouseClicked
         if(txtCTEsqMDR.getText().equals("") && evt.getClickCount()==1){
                txtCTEsqMDR.setText("X");
                txtCTMes.setText("");
                txtCTEsqTB.setText("");
                txtCTEsqDR.setText("");
                txtCTEsqXDR.setText("");
                txtCTOtros.setText("");
            }else
            if(txtCTEsqMDR.getText().equals("X") && evt.getClickCount()==1){
                txtCTEsqMDR.setText("");
            }
    }//GEN-LAST:event_txtCTEsqMDRMouseClicked

    private void txtCTEsqXDRCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCTEsqXDRCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCTEsqXDRCaretUpdate

    private void txtCTEsqXDRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCTEsqXDRMouseClicked
         if(txtCTEsqXDR.getText().equals("") && evt.getClickCount()==1){
                txtCTEsqXDR.setText("X");
                txtCTMes.setText("");
                txtCTEsqTB.setText("");
                txtCTEsqDR.setText("");
                txtCTEsqMDR.setText("");
                txtCTOtros.setText("");
            }else
            if(txtCTEsqXDR.getText().equals("X") && evt.getClickCount()==1){
                txtCTEsqXDR.setText("");
            }
    }//GEN-LAST:event_txtCTEsqXDRMouseClicked

    private void txtCTOtrosCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCTOtrosCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCTOtrosCaretUpdate

    private void txtCTOtrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCTOtrosMouseClicked
         if(txtCTOtros.getText().equals("") && evt.getClickCount()==1){
                txtCTOtros.setText("X");
                txtCTMes.setText("");
                txtCTEsqTB.setText("");
                txtCTEsqDR.setText("");
                txtCTEsqMDR.setText("");
                txtCTEsqXDR.setText("");
            }else
            if(txtCTOtros.getText().equals("X") && evt.getClickCount()==1){
                txtCTOtros.setText("");
            }
    }//GEN-LAST:event_txtCTOtrosMouseClicked

    private void btnBuscarTMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTMActionPerformed
        buscar_muestras.setVisible(true);
        txtBuscar.setText("");
        LAB_Muestra_Examen_cargar();
        LAB_Muestra_Examen_formato();   
    }//GEN-LAST:event_btnBuscarTMActionPerformed

    private void txtSB1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSB1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSB1CaretUpdate

    private void txtSB1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSB1MouseClicked
        if(txtSB1.getText().equals("") && evt.getClickCount()==1){
                txtSB1.setText("X");
                txtSB2.setText("");
                
            }else
            if(txtSB1.getText().equals("X") && evt.getClickCount()==1){
                txtSB1.setText("");
            }
    }//GEN-LAST:event_txtSB1MouseClicked

    private void txtSB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSB1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSB1ActionPerformed

    private void txtSB2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSB2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSB2CaretUpdate

    private void txtSB2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSB2MouseClicked
         if(txtSB2.getText().equals("") && evt.getClickCount()==1){
                txtSB2.setText("X");
                txtSB1.setText("");
                
                
            }else
            if(txtSB2.getText().equals("X") && evt.getClickCount()==1){
                txtSB2.setText("");
            }
    }//GEN-LAST:event_txtSB2MouseClicked

    private void txtPSRapidaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPSRapidaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPSRapidaCaretUpdate

    private void txtPSRapidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPSRapidaMouseClicked
         if(txtPSRapida.getText().equals("") && evt.getClickCount()==1){
                txtPSRapida.setText("X");
                txtPSConvencional.setText("");
                txtPSCEspecificar.setText("");
                txtPSOtroEspecificar.setText("");
                
                txtPSREspecificar.setEditable(true);
                txtPSCEspecificar.setEditable(false);
            }else
            if(txtPSRapida.getText().equals("X") && evt.getClickCount()==1){
                txtPSRapida.setText("");
                txtPSREspecificar.setEditable(false);
            }
    }//GEN-LAST:event_txtPSRapidaMouseClicked

    private void txtPSRapidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPSRapidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPSRapidaActionPerformed

    private void txtPSREspecificarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPSREspecificarCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPSREspecificarCaretUpdate

    private void txtPSREspecificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPSREspecificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPSREspecificarActionPerformed

    private void txtPSConvencionalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPSConvencionalCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPSConvencionalCaretUpdate

    private void txtPSConvencionalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPSConvencionalMouseClicked
        if(txtPSConvencional.getText().equals("") && evt.getClickCount()==1){
                txtPSConvencional.setText("X");
                txtPSRapida.setText("");
                txtPSREspecificar.setText("");
                txtPSOtroEspecificar.setText("");
                
                txtPSCEspecificar.setEditable(true);
                txtPSREspecificar.setEditable(false);
            }else
            if(txtPSConvencional.getText().equals("X") && evt.getClickCount()==1){
                txtPSConvencional.setText("");
                
                txtPSCEspecificar.setEditable(false);
            }
    }//GEN-LAST:event_txtPSConvencionalMouseClicked

    private void txtPSConvencionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPSConvencionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPSConvencionalActionPerformed

    private void txtPSCEspecificarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPSCEspecificarCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPSCEspecificarCaretUpdate

    private void txtPSCEspecificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPSCEspecificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPSCEspecificarActionPerformed

    private void txtPSOtroEspecificarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPSOtroEspecificarCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPSOtroEspecificarCaretUpdate

    private void txtPSOtroEspecificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPSOtroEspecificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPSOtroEspecificarActionPerformed

    private void txtSBOtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSBOtrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSBOtrasActionPerformed

    private void txtSBOtrasCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSBOtrasCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSBOtrasCaretUpdate

    private void txtSBCultivoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSBCultivoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSBCultivoCaretUpdate

    private void txtSBCultivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSBCultivoMouseClicked
         if(txtSBCultivo.getText().equals("") && evt.getClickCount()==1){
                txtSBCultivo.setText("X");
                txtSBOtras.setText("");
            }else
            if(txtSBCultivo.getText().equals("X") && evt.getClickCount()==1){
                txtSBCultivo.setText("");
            }
    }//GEN-LAST:event_txtSBCultivoMouseClicked

    private void txtReferencia8CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtReferencia8CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReferencia8CaretUpdate

    private void txtReferencia8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReferencia8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReferencia8ActionPerformed

    private void txtPacienteSolCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPacienteSolCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPacienteSolCaretUpdate

    private void btnBuscarHC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarHC2ActionPerformed
        buscar_HC.setVisible(true);
        txtbuscarHC.setText("");
        LAB_HC_cargar();
        LAB_HC_formato();      
        lblTipo.setText("2");
    }//GEN-LAST:event_btnBuscarHC2ActionPerformed

    private void txtReferencia9CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtReferencia9CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReferencia9CaretUpdate

    private void txtReferencia9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReferencia9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReferencia9ActionPerformed

    private void txtMInadecuadaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMInadecuadaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMInadecuadaCaretUpdate

    private void txtMInadecuadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMInadecuadaMouseClicked
         if(txtMInadecuada.getText().equals("") && evt.getClickCount()==1){
                txtMInadecuada.setText("X");
                txtMAdecuada.setText("");
            }else
            if(txtMInadecuada.getText().equals("X") && evt.getClickCount()==1){
                txtMInadecuada.setText("");
            }
    }//GEN-LAST:event_txtMInadecuadaMouseClicked

    private void txtMAdecuadaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMAdecuadaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMAdecuadaCaretUpdate

    private void txtMAdecuadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMAdecuadaMouseClicked
            if(txtMAdecuada.getText().equals("") && evt.getClickCount()==1){
                txtMAdecuada.setText("X");
                txtMInadecuada.setText("");
            }else
            if(txtMAdecuada.getText().equals("X") && evt.getClickCount()==1){
                txtMAdecuada.setText("");
            }
    }//GEN-LAST:event_txtMAdecuadaMouseClicked

    private void txtTMEspecificarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTMEspecificarCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTMEspecificarCaretUpdate

    private void txtTMEspecificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTMEspecificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTMEspecificarActionPerformed

    private void txtATNuncaTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtATNuncaTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtATNuncaTActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
//        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
//        LAB_Analisis_Examen me=new  LAB_Analisis_Examen();
//        LAB_Analisis_Examen me1=new LAB_Analisis_Examen();
//        LAB_Analisis_Examen me2=new LAB_Analisis_Examen();
//        try{
//            if(txtGuarModif.getText().equalsIgnoreCase("G")){
//                if(txtClasificacion.getText().equalsIgnoreCase("")||txtCodClasificacion.getText().equalsIgnoreCase("")){
//                    JOptionPane.showMessageDialog(rootPane, "Seleccione una Clasificación del Examen");
//                }  else if(txtCodNomen.getText().equalsIgnoreCase("")||txtCodigoCPT.getText().equalsIgnoreCase("")
//                    ||txtNomen.getText().equalsIgnoreCase("")){
//                    JOptionPane.showMessageDialog(rootPane, "Seleccione un Código CPT");
//                }
//                else if(txtNombreExamen.getText().equalsIgnoreCase("")||txtAbrev.getText().equalsIgnoreCase("")||
//                    cbxTipoProc.getSelectedIndex()==0 || cbxRestric.getSelectedIndex()==0 ){
//                    JOptionPane.showMessageDialog(rootPane, "Ingrese todos los campos");
//                }
//                else if(me1.LAB_Analisis_Examen_ver(txtCodClasificacion.getText(),txtCodNomen.getText())>0){
//                    JOptionPane.showMessageDialog(rootPane, "El Registro ya ha sido ingresado\nIntente nuevamente");
//
//                    txtClasificacion.setText("");
//                    txtClasificacion.requestFocus();
//                }
//                else{
//                    int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
//                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
//                    if(guardar == 0 ){
//                        LAB_Analisis_Examen meGuardar = new LAB_Analisis_Examen();
//                        meGuardar.setCod_exa_ana(txtCodigo.getText());
//                        meGuardar.setCod_clasi_exa(txtCodClasificacion.getText());
//                        meGuardar.setCod_nomen_caja(txtCodNomen.getText());
//                        meGuardar.setNombre_ana_exa(txtNombreExamen.getText());
//                        meGuardar.setAbrev_ana_exa(txtAbrev.getText());
//                        meGuardar.setTiempo_hora(Integer.parseInt(txtTiempoHora.getText()));
//                        meGuardar.setTiempo_min(Integer.parseInt(txtTiempoMin.getText()));
//                        meGuardar.setTipo_procesamiento(cbxTipoProc.getSelectedItem().toString());
//                        meGuardar.setRestriccion_analisis(cbxRestric.getSelectedItem().toString());
//                        meGuardar.setExplicacion_met_proce(txtExplicacion.getText());
//                        if(chActivo.isSelected()){
//                            meGuardar.setEstado_detalle("A");
//                        }else{
//                            meGuardar.setEstado_detalle("D");
//                        }
//                        meGuardar.setObservacion_ana_exa(txtObservacion.getText());
//                        meGuardar.setNom_usu(lblUsu.getText());
//
//                        if(meGuardar.LAB_Analisis_Examen_guardar()){
//                            JOptionPane.showMessageDialog(null, "Datos Guardados");
//                            guardarDetalle();
//                            limpiar();
//                            enableDatos();
//                            txtGuarModif.setText("G");
//                        }
//                        else{
//                            JOptionPane.showMessageDialog(this, "El Registro ya ha sido ingresado\nIntente nuevamente");
//                        }}
//                    }}else{
//                        if(txtClasificacion.getText().equalsIgnoreCase("")||txtCodClasificacion.getText().equalsIgnoreCase("")){
//                            JOptionPane.showMessageDialog(rootPane, "Busque una Unidad Orgánica");
//                        }  else if(txtObservacion.getText().equalsIgnoreCase("")){
//                            JOptionPane.showMessageDialog(rootPane, "Ingrese la Observación");
//                        }
//                        else{
//                            int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
//                                "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
//                            if(modificar == 0 ){
//                                LAB_Analisis_Examen meModif = new LAB_Analisis_Examen();
//                                meModif.setCod_exa_ana(txtCodigo.getText());
//                                meModif.setCod_clasi_exa(txtCodClasificacion.getText());
//                                meModif.setCod_nomen_caja(txtCodNomen.getText());
//                                meModif.setNombre_ana_exa(txtNombreExamen.getText());
//                                meModif.setAbrev_ana_exa(txtAbrev.getText());
//                                meModif.setTiempo_hora(Integer.parseInt(txtTiempoHora.getText()));
//                                meModif.setTiempo_min(Integer.parseInt(txtTiempoMin.getText()));
//                                meModif.setTipo_procesamiento(cbxTipoProc.getSelectedItem().toString());
//                                meModif.setRestriccion_analisis(cbxRestric.getSelectedItem().toString());
//                                meModif.setExplicacion_met_proce(txtExplicacion.getText());
//                                if(chActivo.isSelected()){
//                                    meModif.setEstado_detalle("A");
//                                }else{
//                                    meModif.setEstado_detalle("D");
//                                }
//                                meModif.setObservacion_ana_exa(txtObservacion.getText());
//                                meModif.setNom_usu(lblUsu.getText());
//
//                                LAB_Analisis_Detalle rdeliminar=new LAB_Analisis_Detalle();
//                                rdeliminar.setCod_exa_ana(txtCodigo.getText());
//
//                                if(meModif.LAB_Analisis_Examen_modificar()&& rdeliminar.LAB_Analisis_Detalle_eliminar()){
//                                    JOptionPane.showMessageDialog(this, "Datos Modificados");
//                                    guardarDetalle();
//                                    limpiar();
//                                    enableDatos();
//                                    txtGuarModif.setText("G");
//                                }
//                                else{
//                                    JOptionPane.showMessageDialog(this, "El Registro ya ha sido ingresado\nIntente nuevamente");
//
//                                }}
//                            }}}catch(Exception e) {
//                                JOptionPane.showMessageDialog(this, "Ingrese todos los campos");
//
//                            }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
//        enableDatos();
//        btnBuscarClasif.setEnabled(false);
//        btnBuscarCPT.setEnabled(false);
//        txtGuarModif.setText("M");
//        btnguardar.setEnabled(true);
//        btneliminar.setEnabled(false);
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
    

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

       

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtSBOtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSBOtrasMouseClicked
               
                txtSBCultivo.setText("");
    }//GEN-LAST:event_txtSBOtrasMouseClicked

    private void txtPSOtroEspecificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPSOtroEspecificarMouseClicked
                  txtPSRapida.setText("");
                  txtPSREspecificar.setText("");
                txtPSConvencional.setText("");
                txtPSCEspecificar.setText("");
                
                txtPSREspecificar.setEditable(false);
                txtPSCEspecificar.setEditable(false);
    }//GEN-LAST:event_txtPSOtroEspecificarMouseClicked

    private void txtPaciente1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPaciente1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaciente1CaretUpdate

    private void btnBuscarHC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarHC1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarHC1ActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_DOWN) {
            tb_Muestra_Examen.getSelectionModel().setSelectionInterval(0, 0);
            tb_Muestra_Examen.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        char tecla = evt.getKeyChar();
        if (tecla == KeyEvent.VK_ENTER) {
            btnBuscarMuestra.doClick();
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void tb_Muestra_ExamenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Muestra_ExamenMouseClicked

    }//GEN-LAST:event_tb_Muestra_ExamenMouseClicked

    private void tb_Muestra_ExamenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Muestra_ExamenKeyPressed
        char tecla = evt.getKeyChar();
        if (tecla == KeyEvent.VK_ENTER) {
           
            try{
                if(tb_Muestra_Examen.getRowCount()>0){
                buscar_muestras.setVisible(false);
                int filaselec=tb_Muestra_Examen.getSelectedRow();

                lblCodTMEspecificar.setText(tb_Muestra_Examen.getValueAt(filaselec, 1).toString());
                txtTMEspecificar.setText(tb_Muestra_Examen.getValueAt(filaselec, 2).toString());
                
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Muestra_ExamenKeyPressed

    private void btnBuscarMuestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMuestraActionPerformed
        // TODO add your handling code here:
        String consulta = "";
        try {
            tb_Muestra_Examen.setModel(new DefaultTableModel());
            String titulos[] = {"Nº", "Código", "Muestra"};
            m = new DefaultTableModel(null, titulos);
            JTable p = new JTable(m);
            String fila[] = new String[4];

            serviciosVarios obj = new serviciosVarios();
            consulta = "exec sp_LAB_MUESTRA_EXAMEN_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            ResultSet r = cmd.executeQuery();
            int c = 1;
            while (r.next()) {
                fila[0] = String.valueOf(c) + "º";
                fila[1] = r.getString(1);
                fila[2] = r.getString(2);
                m.addRow(fila);
                c++;
            }
            tb_Muestra_Examen.setModel(m);
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(m);
            tb_Muestra_Examen.setRowSorter(elQueOrdena);
            this.tb_Muestra_Examen.setModel(m);

            LAB_Muestra_Examen_formato();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarMuestraActionPerformed

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
            java.util.logging.Logger.getLogger(frm_RECEPCION_INVESTIGACION_BACT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_RECEPCION_INVESTIGACION_BACT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_RECEPCION_INVESTIGACION_BACT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_RECEPCION_INVESTIGACION_BACT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_RECEPCION_INVESTIGACION_BACT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnBuscarHC;
    private javax.swing.JButton btnBuscarHC1;
    private javax.swing.JButton btnBuscarHC2;
    private javax.swing.JButton btnBuscarMuestra;
    private javax.swing.JButton btnBuscarTM;
    private javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btnguardar;
    public static javax.swing.JButton btnmodificar;
    private javax.swing.JDialog buscar_HC;
    private javax.swing.JDialog buscar_muestras;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel jpanel3;
    private javax.swing.JPanel jpanel4;
    private javax.swing.JLabel lblCama;
    private javax.swing.JLabel lblCel;
    private javax.swing.JLabel lblCodHC;
    private javax.swing.JLabel lblCodHCSol;
    private javax.swing.JLabel lblCodTMEspecificar;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDisa;
    private javax.swing.JLabel lblDistrito;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEess;
    private javax.swing.JLabel lblProvincia;
    private javax.swing.JLabel lblServicio;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTelefonoSol;
    private javax.swing.JLabel lblTipo;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JLabel lblhc;
    public static javax.swing.JTable tb_HC;
    public static javax.swing.JTable tb_Muestra_Examen;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo8;
    private javax.swing.JLabel titulo9;
    public static javax.swing.JTextField txtATAbandonoR;
    public static javax.swing.JTextField txtATAntesT;
    public static javax.swing.JTextField txtATFracaso;
    public static javax.swing.JTextField txtATNuncaT;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtCTEsqDR;
    public static javax.swing.JTextField txtCTEsqMDR;
    public static javax.swing.JTextField txtCTEsqTB;
    public static javax.swing.JTextField txtCTEsqXDR;
    public static javax.swing.JTextField txtCTMes;
    public static javax.swing.JTextField txtCTOtros;
    public static javax.swing.JTextField txtDEspecificar;
    public static javax.swing.JTextField txtDOtro;
    public static javax.swing.JTextField txtDRxAnormal;
    public static javax.swing.JTextField txtDSR;
    public static javax.swing.JTextField txtDSegDia;
    public static javax.swing.JTextField txtMAdecuada;
    public static javax.swing.JTextField txtMInadecuada;
    public static javax.swing.JTextField txtPSCEspecificar;
    public static javax.swing.JTextField txtPSConvencional;
    public static javax.swing.JTextField txtPSOtroEspecificar;
    public static javax.swing.JTextField txtPSREspecificar;
    public static javax.swing.JTextField txtPSRapida;
    public static javax.swing.JTextField txtPaciente;
    public static javax.swing.JTextField txtPaciente1;
    public static javax.swing.JTextField txtPacienteSol;
    public static javax.swing.JTextField txtReferencia;
    public static javax.swing.JTextField txtReferencia8;
    public static javax.swing.JTextField txtReferencia9;
    public static javax.swing.JTextField txtSB1;
    public static javax.swing.JTextField txtSB2;
    public static javax.swing.JTextField txtSBCultivo;
    public static javax.swing.JTextField txtSBOtras;
    public static javax.swing.JTextField txtTMEspecificar;
    public static javax.swing.JTextField txtTMEsputo;
    public static javax.swing.JTextField txtTMOtro;
    private javax.swing.JTextField txtbuscarHC;
    // End of variables declaration//GEN-END:variables
}
