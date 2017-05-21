/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Caja.Caja_NuevaVenta;

/**
 *
 * @author MYS1
 */
public class Caja_Consolidacion extends javax.swing.JFrame {
DefaultTableModel m;
    /**
     * Creates new form Caja_Consolidacion
     */
    public Caja_Consolidacion() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        
        jScrollPane7.setVisible(false);
        jScrollPane3.setVisible(false);
        jScrollPane8.setVisible(false);
        jScrollPane10.setVisible(false);
        jPanel31.setVisible(false);
        jPanel32.setVisible(false);
        jPanel33.setVisible(false);
    }
    
    public void BusquedaDet(){
        String consulta="";
        try {
            tb_Grupo6.setModel(new DefaultTableModel());
         
             String titulos[]={"CPT","Precio","Servicio/Area/Dpto","Cantidad","Precio","Descuento","Total","Medico/Personal","Nº Atencion","Turno","doc"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[14];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec CAJA_CONSULTAR_ACTOMEDICODNI_DETALLE_CJ ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, bus1.getText());
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
                m.addRow(fila);
                c++;
            }
            tb_Grupo6.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo6.setRowSorter(elQueOrdena);
            this.tb_Grupo6.setModel(m);
            formatoCuerpo();
        } catch (Exception e) {
            System.out.println("Error CJ : " + e.getMessage());
        }
      }
    
    public void BusquedaDetLA(){
        String consulta="";
        try {
            tb_Grupo7.setModel(new DefaultTableModel());
         
             String titulos[]={"CPT","Precio","Servicio/Area/Dpto","Cantidad","Precio","Descuento","Total","Medico/Personal","Nº Atencion","Turno","doc"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[14];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec CAJA_CONSULTAR_ACTOMEDICODNI_DETALLE_LA ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, bus1.getText());
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
                m.addRow(fila);
                c++;
            }
            tb_Grupo7.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo7.setRowSorter(elQueOrdena);
            this.tb_Grupo7.setModel(m);
            formatoCuerpoLA();
        } catch (Exception e) {
            System.out.println("Error LA  " + e.getMessage());
        }
      }
    
    public void BusquedaDetRX(){
        String consulta="";
        try {
            tb_Grupo9.setModel(new DefaultTableModel());
         
             String titulos[]={"CPT","Precio","Servicio/Area/Dpto","Cantidad","Precio","Descuento","Total","Medico/Personal","Nº Atencion","Turno","doc"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[14];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec CAJA_CONSULTAR_ACTOMEDICODNI_DETALLE_RX ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, bus1.getText());
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
                m.addRow(fila);
                c++;
            }
            tb_Grupo9.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo9.setRowSorter(elQueOrdena);
            this.tb_Grupo9.setModel(m);
            formatoCuerpoRX();
        } catch (Exception e) {
            System.out.println("Error RX  " + e.getMessage());
        }
      }
    
    public void formatoCuerpo(){
    
    tb_Grupo6.getColumnModel().getColumn(0).setPreferredWidth(430);
    tb_Grupo6.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Grupo6.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_Grupo6.getColumnModel().getColumn(2).setPreferredWidth(150);
    tb_Grupo6.getColumnModel().getColumn(3).setPreferredWidth(80);
    tb_Grupo6.getColumnModel().getColumn(4).setPreferredWidth(80);
    tb_Grupo6.getColumnModel().getColumn(5).setPreferredWidth(80);
    tb_Grupo6.getColumnModel().getColumn(6).setPreferredWidth(100);
    tb_Grupo6.getColumnModel().getColumn(7).setPreferredWidth(220);
    tb_Grupo6.getColumnModel().getColumn(8).setPreferredWidth(80);
    tb_Grupo6.getColumnModel().getColumn(9).setPreferredWidth(100);
    tb_Grupo6.getColumnModel().getColumn(10).setMinWidth(0);
    tb_Grupo6.getColumnModel().getColumn(10).setMaxWidth(0);

    }
    
    public void formatoCuerpoLA(){

    
    tb_Grupo7.getColumnModel().getColumn(0).setPreferredWidth(430);
    tb_Grupo7.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Grupo7.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_Grupo7.getColumnModel().getColumn(2).setPreferredWidth(150);
    tb_Grupo7.getColumnModel().getColumn(3).setPreferredWidth(80);
    tb_Grupo7.getColumnModel().getColumn(4).setPreferredWidth(80);
    tb_Grupo7.getColumnModel().getColumn(5).setPreferredWidth(80);
    tb_Grupo7.getColumnModel().getColumn(6).setPreferredWidth(100);
    tb_Grupo7.getColumnModel().getColumn(7).setPreferredWidth(220);
    tb_Grupo7.getColumnModel().getColumn(8).setPreferredWidth(80);
    tb_Grupo7.getColumnModel().getColumn(9).setPreferredWidth(100);
    tb_Grupo7.getColumnModel().getColumn(10).setMinWidth(0);
    tb_Grupo7.getColumnModel().getColumn(10).setMaxWidth(0);


    }
    
    public void formatoCuerpoRX(){
  
    tb_Grupo9.getColumnModel().getColumn(0).setPreferredWidth(430);
    tb_Grupo9.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Grupo9.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_Grupo9.getColumnModel().getColumn(2).setPreferredWidth(150);
    tb_Grupo9.getColumnModel().getColumn(3).setPreferredWidth(80);
    tb_Grupo9.getColumnModel().getColumn(4).setPreferredWidth(80);
    tb_Grupo9.getColumnModel().getColumn(5).setPreferredWidth(80);
    tb_Grupo9.getColumnModel().getColumn(6).setPreferredWidth(100);
    tb_Grupo9.getColumnModel().getColumn(7).setPreferredWidth(220);
    tb_Grupo9.getColumnModel().getColumn(8).setPreferredWidth(80);
    tb_Grupo9.getColumnModel().getColumn(9).setPreferredWidth(100);
    tb_Grupo9.getColumnModel().getColumn(10).setMinWidth(0);
    tb_Grupo9.getColumnModel().getColumn(10).setMaxWidth(0);

    }
    
    public void BusquedaGeneral(){
        String consulta="";
        try {
            tb_Grupo1.setModel(new DefaultTableModel());
         
             String titulos[]={"Documento","Nº Documento","Forma de Pago","DNI","HC","C","Estado","Descuento","SubTotal","IGV","Total","Fecha","Hora","Am","ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec CAJA_CONSULTAR_ACTOMEDICODNI_CONSOLIDADO ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txPaciente.getText());
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
            fila[11]=r.getString(12);
            fila[12]=r.getString(13);
            fila[13]=r.getString(14);
            fila[14]=r.getString(15);
                m.addRow(fila);
                c++;
            }
            tb_Grupo1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo1.setRowSorter(elQueOrdena);
            this.tb_Grupo1.setModel(m);
            formatoCabecera();
        } catch (Exception e) {
            System.out.println("Error doc: " + e.getMessage());
        }
      }
    public void formatoCabecera(){
    
    tb_Grupo1.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(1).setPreferredWidth(200);
    tb_Grupo1.getColumnModel().getColumn(2).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Grupo1.getColumnModel().getColumn(4).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(4).setMaxWidth(0);
    tb_Grupo1.getColumnModel().getColumn(5).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(5).setMaxWidth(0);
    tb_Grupo1.getColumnModel().getColumn(6).setPreferredWidth(200);
    tb_Grupo1.getColumnModel().getColumn(7).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(8).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(9).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(10).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(11).setPreferredWidth(200);
    tb_Grupo1.getColumnModel().getColumn(12).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(13).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(13).setMaxWidth(0);
    tb_Grupo1.getColumnModel().getColumn(14).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(14).setMaxWidth(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane9 = new javax.swing.JScrollPane();
        tb_Grupo8 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            lblusu = new javax.swing.JLabel();
            btnNuevo = new javax.swing.JButton();
            jPanel9 = new javax.swing.JPanel();
            txPaciente = new javax.swing.JTextField();
            T3 = new javax.swing.JButton();
            lbldetalle = new javax.swing.JLabel();
            bus1 = new javax.swing.JLabel();
            jPanel2 = new javax.swing.JPanel();
            lblApellidos = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            lblActoMedico = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            lblDNI = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            lblHC = new javax.swing.JLabel();
            jScrollPane3 = new javax.swing.JScrollPane();
            tb_Grupo1 = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jScrollPane7 = new javax.swing.JScrollPane();
                tb_Grupo6 = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jScrollPane8 = new javax.swing.JScrollPane();
                    tb_Grupo7 = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        jScrollPane10 = new javax.swing.JScrollPane();
                        tb_Grupo9 = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            jPanel31 = new javax.swing.JPanel();
                            jLabel2 = new javax.swing.JLabel();
                            jPanel32 = new javax.swing.JPanel();
                            jLabel6 = new javax.swing.JLabel();
                            jPanel33 = new javax.swing.JPanel();
                            jLabel7 = new javax.swing.JLabel();

                            jScrollPane9.setBorder(null);

                            tb_Grupo8.setModel(new javax.swing.table.DefaultTableModel(
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
                            tb_Grupo8.setGridColor(new java.awt.Color(255, 255, 255));
                            tb_Grupo8.setRowHeight(25);
                            tb_Grupo8.setSelectionBackground(new java.awt.Color(50, 151, 219));
                            tb_Grupo8.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tb_Grupo8MouseClicked(evt);
                                }
                                public void mousePressed(java.awt.event.MouseEvent evt) {
                                    tb_Grupo8MousePressed(evt);
                                }
                            });
                            tb_Grupo8.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tb_Grupo8KeyPressed(evt);
                                }
                            });
                            jScrollPane9.setViewportView(tb_Grupo8);

                            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                            jPanel1.setBackground(new java.awt.Color(50, 151, 219));

                            jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel1.setText("<html>Consolidado<span style=\"font-size:'15px'\"><br> Cuenta Corriente</br></span></html>");

                            lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                            lblusu.setForeground(new java.awt.Color(255, 255, 255));
                            lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                            lblusu.setText("Silvana");
                            lblusu.setFocusable(false);
                            lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                            btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                            btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                            btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Actualizar-32 (1).png"))); // NOI18N
                            btnNuevo.setText("Refrescar");
                            btnNuevo.setContentAreaFilled(false);
                            btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                            btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                            btnNuevo.setIconTextGap(30);
                            btnNuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                            btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnNuevoActionPerformed(evt);
                                }
                            });

                            jPanel9.setBackground(new java.awt.Color(255, 255, 255));

                            txPaciente.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                            txPaciente.setForeground(new java.awt.Color(51, 51, 51));
                            txPaciente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                            txPaciente.setBorder(null);
                            txPaciente.addCaretListener(new javax.swing.event.CaretListener() {
                                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                    txPacienteCaretUpdate(evt);
                                }
                            });

                            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                            jPanel9.setLayout(jPanel9Layout);
                            jPanel9Layout.setHorizontalGroup(
                                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(txPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );
                            jPanel9Layout.setVerticalGroup(
                                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGap(0, 0, 0)
                                    .addComponent(txPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );

                            T3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                            T3.setToolTipText("");
                            T3.setContentAreaFilled(false);
                            T3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            T3.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    T3ActionPerformed(evt);
                                }
                            });

                            lbldetalle.setForeground(new java.awt.Color(255, 255, 255));
                            lbldetalle.setText("Acto Médico, DNI y Apellidos");

                            bus1.setText("<html><td style=\"layout-flow:'vertical-ideographic'\">prueba</td></html>");

                            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                            jPanel1.setLayout(jPanel1Layout);
                            jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(237, 237, 237)
                                                    .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(24, 24, 24)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lbldetalle)))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                            .addComponent(bus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(142, 142, 142))
                                                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGap(0, 0, Short.MAX_VALUE)))
                                    .addContainerGap())
                            );
                            jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(34, 34, 34)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbldetalle)
                                    .addGap(21, 21, 21)
                                    .addComponent(btnNuevo)
                                    .addGap(109, 109, 109)
                                    .addComponent(bus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 263, Short.MAX_VALUE)
                                    .addComponent(lblusu))
                            );

                            jPanel2.setBackground(new java.awt.Color(43, 43, 43));

                            lblApellidos.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
                            lblApellidos.setForeground(new java.awt.Color(255, 255, 255));
                            lblApellidos.setText("Nombres y Apellidos");

                            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                            jLabel3.setForeground(new java.awt.Color(204, 204, 204));
                            jLabel3.setText("Acto Médico");

                            lblActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                            lblActoMedico.setForeground(new java.awt.Color(204, 204, 204));
                            lblActoMedico.setText("  ");

                            jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                            jLabel5.setForeground(new java.awt.Color(204, 204, 204));
                            jLabel5.setText("DNI");

                            lblDNI.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                            lblDNI.setForeground(new java.awt.Color(204, 204, 204));
                            lblDNI.setText("  ");

                            jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                            jLabel4.setForeground(new java.awt.Color(204, 204, 204));
                            jLabel4.setText("Nº H.C.");

                            lblHC.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                            lblHC.setForeground(new java.awt.Color(204, 204, 204));
                            lblHC.setText("  ");

                            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                            jPanel2.setLayout(jPanel2Layout);
                            jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel4))
                                            .addGap(67, 67, 67)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblHC)
                                                .addComponent(lblDNI)
                                                .addComponent(lblActoMedico)))
                                        .addComponent(lblApellidos))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );
                            jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(lblApellidos)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(lblActoMedico))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(lblDNI))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblHC)
                                        .addComponent(jLabel4))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );

                            jScrollPane3.setBorder(null);

                            tb_Grupo1.setModel(new javax.swing.table.DefaultTableModel(
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
                            tb_Grupo1.setGridColor(new java.awt.Color(255, 255, 255));
                            tb_Grupo1.setRowHeight(25);
                            tb_Grupo1.setSelectionBackground(new java.awt.Color(41, 127, 184));
                            tb_Grupo1.getTableHeader().setReorderingAllowed(false);
                            tb_Grupo1.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tb_Grupo1MouseClicked(evt);
                                }
                                public void mousePressed(java.awt.event.MouseEvent evt) {
                                    tb_Grupo1MousePressed(evt);
                                }
                            });
                            tb_Grupo1.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tb_Grupo1KeyPressed(evt);
                                }
                            });
                            jScrollPane3.setViewportView(tb_Grupo1);

                            jScrollPane7.setBorder(null);

                            tb_Grupo6.setModel(new javax.swing.table.DefaultTableModel(
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
                            tb_Grupo6.setGridColor(new java.awt.Color(255, 255, 255));
                            tb_Grupo6.setRowHeight(25);
                            tb_Grupo6.setSelectionBackground(new java.awt.Color(50, 151, 219));
                            tb_Grupo6.getTableHeader().setReorderingAllowed(false);
                            tb_Grupo6.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tb_Grupo6MouseClicked(evt);
                                }
                                public void mousePressed(java.awt.event.MouseEvent evt) {
                                    tb_Grupo6MousePressed(evt);
                                }
                            });
                            tb_Grupo6.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tb_Grupo6KeyPressed(evt);
                                }
                            });
                            jScrollPane7.setViewportView(tb_Grupo6);

                            jScrollPane8.setBorder(null);

                            tb_Grupo7.setModel(new javax.swing.table.DefaultTableModel(
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
                            tb_Grupo7.setGridColor(new java.awt.Color(255, 255, 255));
                            tb_Grupo7.setRowHeight(25);
                            tb_Grupo7.setSelectionBackground(new java.awt.Color(50, 151, 219));
                            tb_Grupo7.getTableHeader().setReorderingAllowed(false);
                            tb_Grupo7.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tb_Grupo7MouseClicked(evt);
                                }
                                public void mousePressed(java.awt.event.MouseEvent evt) {
                                    tb_Grupo7MousePressed(evt);
                                }
                            });
                            tb_Grupo7.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tb_Grupo7KeyPressed(evt);
                                }
                            });
                            jScrollPane8.setViewportView(tb_Grupo7);

                            jScrollPane10.setBorder(null);

                            tb_Grupo9.setModel(new javax.swing.table.DefaultTableModel(
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
                            tb_Grupo9.setGridColor(new java.awt.Color(255, 255, 255));
                            tb_Grupo9.setRowHeight(25);
                            tb_Grupo9.setSelectionBackground(new java.awt.Color(50, 151, 219));
                            tb_Grupo9.getTableHeader().setReorderingAllowed(false);
                            tb_Grupo9.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tb_Grupo9MouseClicked(evt);
                                }
                                public void mousePressed(java.awt.event.MouseEvent evt) {
                                    tb_Grupo9MousePressed(evt);
                                }
                            });
                            tb_Grupo9.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tb_Grupo9KeyPressed(evt);
                                }
                            });
                            jScrollPane10.setViewportView(tb_Grupo9);

                            jPanel31.setBackground(new java.awt.Color(41, 127, 184));

                            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/CJ.png"))); // NOI18N

                            javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
                            jPanel31.setLayout(jPanel31Layout);
                            jPanel31Layout.setHorizontalGroup(
                                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0))
                            );
                            jPanel31Layout.setVerticalGroup(
                                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(72, 72, 72))
                            );

                            jPanel32.setBackground(new java.awt.Color(154, 89, 181));

                            jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/LA.png"))); // NOI18N

                            javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
                            jPanel32.setLayout(jPanel32Layout);
                            jPanel32Layout.setHorizontalGroup(
                                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel32Layout.createSequentialGroup()
                                    .addGap(0, 0, 0)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                            );
                            jPanel32Layout.setVerticalGroup(
                                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(44, 44, 44))
                            );

                            jPanel33.setBackground(new java.awt.Color(232, 76, 61));

                            jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/RX.png"))); // NOI18N

                            javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
                            jPanel33.setLayout(jPanel33Layout);
                            jPanel33Layout.setHorizontalGroup(
                                jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0))
                            );
                            jPanel33Layout.setVerticalGroup(
                                jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel33Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())
                            );

                            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                            getContentPane().setLayout(layout);
                            layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                            );
                            layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 134, Short.MAX_VALUE))
                                    .addGap(0, 0, 0)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, 132, Short.MAX_VALUE))
                                    .addGap(0, 0, 0)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            );

                            pack();
                        }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
     
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txPacienteCaretUpdate
        
        if (!txPaciente.getText().equals("")){
            jScrollPane3.setVisible(true);
            BusquedaGeneral();
//            jScrollPane7.setVisible(true);
//            jScrollPane8.setVisible(true);
//            jScrollPane10.setVisible(true);
            jPanel31.setVisible(true);
            jPanel32.setVisible(true);
            jPanel33.setVisible(true);

            int fila = tb_Grupo1.getSelectedRow();
            tb_Grupo1.getSelectionModel().setSelectionInterval (0,0) ;
   
//            lblActoMedico.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 13)));
//            lblApellidos.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
//            lblDNI.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
//            lblHC.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 4)));
//            
//            bus1.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 14)));
//            BusquedaDet();
            ////////////////////////////////////////

        }if (txPaciente.getText().length()==0){
 
            jScrollPane7.setVisible(false);
            jScrollPane3.setVisible(false);
            jScrollPane8.setVisible(false);
            jScrollPane10.setVisible(false);
            jPanel31.setVisible(false);
            jPanel32.setVisible(false);
            jPanel33.setVisible(false);
        }      
    }//GEN-LAST:event_txPacienteCaretUpdate

    private void T3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T3ActionPerformed

    }//GEN-LAST:event_T3ActionPerformed

    private void tb_Grupo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MouseClicked
        int fila = tb_Grupo1.getSelectedRow();
        lblActoMedico.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 13)));
        lblApellidos.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
        lblDNI.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
        lblHC.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 4)));
        bus1.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 14)));
        BusquedaDet();
        BusquedaDetLA();
        BusquedaDetRX();
        jScrollPane7.setVisible(true);
        jScrollPane8.setVisible(true);
        jScrollPane10.setVisible(true);
        jPanel31.setVisible(true);
        jPanel32.setVisible(true);
        jPanel33.setVisible(true);
        
    }//GEN-LAST:event_tb_Grupo1MouseClicked

    private void tb_Grupo1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MousePressed

    }//GEN-LAST:event_tb_Grupo1MousePressed

    private void tb_Grupo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo1KeyPressed

    }//GEN-LAST:event_tb_Grupo1KeyPressed

    private void tb_Grupo6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo6MouseClicked

    private void tb_Grupo6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo6MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo6MousePressed

    private void tb_Grupo6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo6KeyPressed

    private void tb_Grupo7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo7MouseClicked

    private void tb_Grupo7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo7MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo7MousePressed

    private void tb_Grupo7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo7KeyPressed

    private void tb_Grupo8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo8MouseClicked

    private void tb_Grupo8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo8MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo8MousePressed

    private void tb_Grupo8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo8KeyPressed

    private void tb_Grupo9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo9MouseClicked

    private void tb_Grupo9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo9MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo9MousePressed

    private void tb_Grupo9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo9KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo9KeyPressed

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
            java.util.logging.Logger.getLogger(Caja_Consolidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Consolidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Consolidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Consolidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Consolidacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton T3;
    public static javax.swing.JButton btnNuevo;
    private javax.swing.JLabel bus1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblActoMedico;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblHC;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JTable tb_Grupo1;
    private javax.swing.JTable tb_Grupo6;
    private javax.swing.JTable tb_Grupo7;
    private javax.swing.JTable tb_Grupo8;
    private javax.swing.JTable tb_Grupo9;
    public static javax.swing.JTextField txPaciente;
    // End of variables declaration//GEN-END:variables
}
