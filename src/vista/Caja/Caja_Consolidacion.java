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

        jScrollPane8.setVisible(false);
        jScrollPane10.setVisible(false);
        lblProcedimientos.setVisible(false);
        lblLaboratorio.setVisible(false);
        lblRayos.setVisible(false);
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
            tb_Encabezado.setModel(new DefaultTableModel());
         
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
            tb_Encabezado.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Encabezado.setRowSorter(elQueOrdena);
            this.tb_Encabezado.setModel(m);
            formatoCabecera();
        } catch (Exception e) {
            System.out.println("Error doc: " + e.getMessage());
        }
      }
    public void formatoCabecera(){
    
    tb_Encabezado.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Encabezado.getColumnModel().getColumn(1).setPreferredWidth(200);
    tb_Encabezado.getColumnModel().getColumn(2).setPreferredWidth(100);
    tb_Encabezado.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Encabezado.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Encabezado.getColumnModel().getColumn(4).setMinWidth(0);
    tb_Encabezado.getColumnModel().getColumn(4).setMaxWidth(0);
    tb_Encabezado.getColumnModel().getColumn(5).setMinWidth(0);
    tb_Encabezado.getColumnModel().getColumn(5).setMaxWidth(0);
    tb_Encabezado.getColumnModel().getColumn(6).setPreferredWidth(200);
    tb_Encabezado.getColumnModel().getColumn(7).setPreferredWidth(100);
    tb_Encabezado.getColumnModel().getColumn(8).setPreferredWidth(100);
    tb_Encabezado.getColumnModel().getColumn(9).setPreferredWidth(100);
    tb_Encabezado.getColumnModel().getColumn(10).setPreferredWidth(100);
    tb_Encabezado.getColumnModel().getColumn(11).setPreferredWidth(200);
    tb_Encabezado.getColumnModel().getColumn(12).setPreferredWidth(100);
    tb_Encabezado.getColumnModel().getColumn(13).setMinWidth(0);
    tb_Encabezado.getColumnModel().getColumn(13).setMaxWidth(0);
    tb_Encabezado.getColumnModel().getColumn(14).setMinWidth(0);
    tb_Encabezado.getColumnModel().getColumn(14).setMaxWidth(0);

    }
    
    
    private void sumaAbono()
    {
        double total = 0;
        //recorrer todas las filas de la segunda columna y va sumando las cantidades
        for( int i=0 ; i<tb_Encabezado.getRowCount() ; i++)
        {
            double numero =0,st=0;
            try{
                //capturamos valor de celda
                numero = Double.parseDouble(tb_Encabezado.getValueAt(i, 10).toString() );
                
  
            }
            catch (NumberFormatException nfe){ //si existe un error se coloca 0 a la celda
                numero = 0;
                tb_Encabezado.setValueAt(0, i, 10);
                System.out.println("error" + nfe.getMessage());
            }
            //se suma al total
          total += numero;
        }
        //muestra en el componente
        this.abonod.setText( String.valueOf(total) );
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
            tb_Grupo9 = new javax.swing.JTable(){
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
                abonod = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                lblApellidos = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                lblActoMedico = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                lblDNI = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                lblHC = new javax.swing.JLabel();
                jScrollPane3 = new javax.swing.JScrollPane();
                tb_Encabezado = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jScrollPane1 = new javax.swing.JScrollPane();
                    jPanel3 = new javax.swing.JPanel();
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
                            tb_Grupo10 = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false; //Disallow the editing of any cell
                                }};
                                jScrollPane12 = new javax.swing.JScrollPane();
                                tb_Grupo11 = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false; //Disallow the editing of any cell
                                    }};
                                    jScrollPane13 = new javax.swing.JScrollPane();
                                    tb_Grupo12 = new javax.swing.JTable(){
                                        public boolean isCellEditable(int rowIndex, int colIndex){
                                            return false; //Disallow the editing of any cell
                                        }};
                                        lblProcedimientos = new javax.swing.JLabel();
                                        lblLaboratorio = new javax.swing.JLabel();
                                        lblRayos = new javax.swing.JLabel();
                                        lblRayos1 = new javax.swing.JLabel();
                                        lblRayos2 = new javax.swing.JLabel();

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

                                        abonod.setText("jLabel8");

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
                                                                .addGap(56, 56, 56)
                                                                .addComponent(bus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                                                .addComponent(abonod)))
                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                                .addContainerGap())
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                                .addGap(66, 66, 66)
                                                .addComponent(abonod)
                                                .addGap(29, 29, 29)
                                                .addComponent(bus1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 461, Short.MAX_VALUE)
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

                                        tb_Encabezado.setModel(new javax.swing.table.DefaultTableModel(
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
                                        tb_Encabezado.setGridColor(new java.awt.Color(255, 255, 255));
                                        tb_Encabezado.setRowHeight(25);
                                        tb_Encabezado.setSelectionBackground(new java.awt.Color(41, 127, 184));
                                        tb_Encabezado.getTableHeader().setReorderingAllowed(false);
                                        tb_Encabezado.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tb_EncabezadoMouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                tb_EncabezadoMousePressed(evt);
                                            }
                                        });
                                        tb_Encabezado.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                tb_EncabezadoKeyPressed(evt);
                                            }
                                        });
                                        jScrollPane3.setViewportView(tb_Encabezado);

                                        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

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

                                        tb_Grupo10.setModel(new javax.swing.table.DefaultTableModel(
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
                                        tb_Grupo10.setGridColor(new java.awt.Color(255, 255, 255));
                                        tb_Grupo10.setRowHeight(25);
                                        tb_Grupo10.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                        tb_Grupo10.getTableHeader().setReorderingAllowed(false);
                                        tb_Grupo10.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tb_Grupo10MouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                tb_Grupo10MousePressed(evt);
                                            }
                                        });
                                        tb_Grupo10.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                tb_Grupo10KeyPressed(evt);
                                            }
                                        });
                                        jScrollPane10.setViewportView(tb_Grupo10);

                                        jScrollPane12.setBorder(null);

                                        tb_Grupo11.setModel(new javax.swing.table.DefaultTableModel(
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
                                        tb_Grupo11.setGridColor(new java.awt.Color(255, 255, 255));
                                        tb_Grupo11.setRowHeight(25);
                                        tb_Grupo11.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                        tb_Grupo11.getTableHeader().setReorderingAllowed(false);
                                        tb_Grupo11.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tb_Grupo11MouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                tb_Grupo11MousePressed(evt);
                                            }
                                        });
                                        tb_Grupo11.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                tb_Grupo11KeyPressed(evt);
                                            }
                                        });
                                        jScrollPane12.setViewportView(tb_Grupo11);

                                        jScrollPane13.setBorder(null);

                                        tb_Grupo12.setModel(new javax.swing.table.DefaultTableModel(
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
                                        tb_Grupo12.setGridColor(new java.awt.Color(255, 255, 255));
                                        tb_Grupo12.setRowHeight(25);
                                        tb_Grupo12.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                        tb_Grupo12.getTableHeader().setReorderingAllowed(false);
                                        tb_Grupo12.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tb_Grupo12MouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                tb_Grupo12MousePressed(evt);
                                            }
                                        });
                                        tb_Grupo12.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                tb_Grupo12KeyPressed(evt);
                                            }
                                        });
                                        jScrollPane13.setViewportView(tb_Grupo12);

                                        lblProcedimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/proc.png"))); // NOI18N

                                        lblLaboratorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/lab.png"))); // NOI18N

                                        lblRayos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/ray.png"))); // NOI18N

                                        lblRayos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/eco.png"))); // NOI18N

                                        lblRayos2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/farm.png"))); // NOI18N

                                        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                                        jPanel3.setLayout(jPanel3Layout);
                                        jPanel3Layout.setHorizontalGroup(
                                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblRayos1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(lblRayos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(lblLaboratorio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(lblProcedimientos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                    .addComponent(lblRayos2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, 0)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jScrollPane12)
                                                    .addComponent(jScrollPane13))
                                                .addGap(0, 0, 0))
                                        );
                                        jPanel3Layout.setVerticalGroup(
                                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(0, 0, 0)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblProcedimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(0, 0, 0)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(0, 0, 0)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addComponent(lblRayos, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, 0)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblRayos1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(0, 0, 0)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblRayos2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        );

                                        jScrollPane1.setViewportView(jPanel3);

                                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                        getContentPane().setLayout(layout);
                                        layout.setHorizontalGroup(
                                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                                                        .addContainerGap())))
                                        );
                                        layout.setVerticalGroup(
                                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                                                .addContainerGap())
                                        );

                                        pack();
                                    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
     
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txPacienteCaretUpdate
        
        if (!txPaciente.getText().equals("")){
            jScrollPane3.setVisible(true);
            tb_Encabezado.setVisible(true);
            BusquedaGeneral();
            
//            jScrollPane7.setVisible(true);
//            jScrollPane8.setVisible(true);
//            jScrollPane10.setVisible(true);
            lblProcedimientos.setVisible(true);
            lblLaboratorio.setVisible(true);
            lblRayos.setVisible(true);

            int fila = tb_Encabezado.getSelectedRow();
            tb_Encabezado.getSelectionModel().setSelectionInterval (0,0) ;
   
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
            lblProcedimientos.setVisible(false);
            lblLaboratorio.setVisible(false);
            lblRayos.setVisible(false);
        }      
    }//GEN-LAST:event_txPacienteCaretUpdate

    private void T3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T3ActionPerformed

    }//GEN-LAST:event_T3ActionPerformed

    private void tb_EncabezadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_EncabezadoMouseClicked
        int fila = tb_Encabezado.getSelectedRow();
        lblActoMedico.setText(String.valueOf(tb_Encabezado.getValueAt(fila, 13)));
        lblApellidos.setText(String.valueOf(tb_Encabezado.getValueAt(fila, 5)));
        lblDNI.setText(String.valueOf(tb_Encabezado.getValueAt(fila, 3)));
        lblHC.setText(String.valueOf(tb_Encabezado.getValueAt(fila, 4)));
        bus1.setText(String.valueOf(tb_Encabezado.getValueAt(fila, 14)));
        
        BusquedaDet();
        BusquedaDetLA();
        BusquedaDetRX();
        jScrollPane7.setVisible(true);
        jScrollPane8.setVisible(true);
        jScrollPane10.setVisible(true);
        lblProcedimientos.setVisible(true);
        lblLaboratorio.setVisible(true);
        lblRayos.setVisible(true);
        sumaAbono();
        
        /////////ACTUALIZAR RX
        
    }//GEN-LAST:event_tb_EncabezadoMouseClicked

    private void tb_EncabezadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_EncabezadoMousePressed

    }//GEN-LAST:event_tb_EncabezadoMousePressed

    private void tb_EncabezadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_EncabezadoKeyPressed

    }//GEN-LAST:event_tb_EncabezadoKeyPressed

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

    private void tb_Grupo10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo10MouseClicked

    private void tb_Grupo10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo10MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo10MousePressed

    private void tb_Grupo10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo10KeyPressed

    private void tb_Grupo11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo11MouseClicked

    private void tb_Grupo11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo11MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo11MousePressed

    private void tb_Grupo11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo11KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo11KeyPressed

    private void tb_Grupo12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo12MouseClicked

    private void tb_Grupo12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo12MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo12MousePressed

    private void tb_Grupo12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo12KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo12KeyPressed

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
    private javax.swing.JLabel abonod;
    public static javax.swing.JButton btnNuevo;
    private javax.swing.JLabel bus1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblActoMedico;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblHC;
    private javax.swing.JLabel lblLaboratorio;
    private javax.swing.JLabel lblProcedimientos;
    private javax.swing.JLabel lblRayos;
    private javax.swing.JLabel lblRayos1;
    private javax.swing.JLabel lblRayos2;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JTable tb_Encabezado;
    private javax.swing.JTable tb_Grupo10;
    private javax.swing.JTable tb_Grupo11;
    private javax.swing.JTable tb_Grupo12;
    private javax.swing.JTable tb_Grupo6;
    private javax.swing.JTable tb_Grupo7;
    private javax.swing.JTable tb_Grupo8;
    private javax.swing.JTable tb_Grupo9;
    public static javax.swing.JTextField txPaciente;
    // End of variables declaration//GEN-END:variables
}
