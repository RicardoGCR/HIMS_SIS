/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista.COSTOS;

import campos.LimitadorDeDocumento;
import static vista.COSTOS.Costos_Sustentacion.tbServiciosBasicos;
import static vista.COSTOS.TipoSustentacion.fechaActual;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import static vista.COSTOS.Costos_Sustentacion.lblGananciaPer;
import static vista.COSTOS.Costos_Sustentacion.tbPersonal;
import static vista.COSTOS.Costos_Sustentacion.tbResumenCostos;
import static vista.COSTOS.Costos_Sustentacion.txtGananciaPerdida;

/**
 *
 * @author USUARIO
 */
public class Costos_Sustentacion_Detalle_Servicio_Basicos extends javax.swing.JFrame implements Runnable{
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Conexion conectar=new Conexion();
    Connection con;
    CallableStatement cst;
    ResultSet r;
    Statement st;
    DefaultTableModel m, msb, modelo1, modelo2, modelo3;
    
    /**
     * Creates new form Costos_Servicio_Basicos
     */
    public Costos_Sustentacion_Detalle_Servicio_Basicos() {
        initComponents();
        try {
        txtCodigo_Sustentacion.setVisible(false);
        txtCodigo_Servicio.setVisible(false);
        txtSumatoria_Principal_E.setVisible(false);
        txtSumatoria_Principal_A.setVisible(false);
        //    setDefaultCloseOperation(0);
       //     setUndecorated(false);
        con=conectar.conectar();
        inicializar_tabla();
        deshabilitar();
   
        setLocationRelativeTo(null);
        BUSCAR_TIPO_SUSTENTACION.setLocationRelativeTo(null);
        BUSCAR_SERVICIOS_VARIOS.setLocationRelativeTo(null);
        
        this.getContentPane().setBackground(Color.WHITE);       
        BUSCAR_TIPO_SUSTENTACION.getContentPane().setBackground(Color.WHITE);
        BUSCAR_SERVICIOS_VARIOS.getContentPane().setBackground(Color.WHITE);
        
        btnBuscarSustentacion.setEnabled(true);
        btnBuscarServicios.setEnabled(false);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/hospital32x32.png")).getImage());
        //Hora y fecha
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance();          
        lblFecha.setText(fechaActual()); 
        cargarTipoSustentacion();
        cargarServiciosVariosB();
        
          } catch (Exception e) {
        }
        
        btn_agregar_SB.setMnemonic(KeyEvent.VK_ADD);
        btn_quitar_SB.setMnemonic(KeyEvent.VK_SUBTRACT);

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
        
        LimitadorDeDocumento limitConsumoMensual = new LimitadorDeDocumento(10);
        txtConsumoMensual.setDocument(limitConsumoMensual);
        LimitadorDeDocumento limitConsumoMensualAgua = new LimitadorDeDocumento(10);
        txtConsumoMensualAgua.setDocument(limitConsumoMensualAgua);
        LimitadorDeDocumento limitArea = new LimitadorDeDocumento(5);
        txtArea.setDocument(limitArea);
        LimitadorDeDocumento limitConsultasMensual = new LimitadorDeDocumento(5);
        txtConsultas_Mensuales.setDocument(limitConsultasMensual);
        LimitadorDeDocumento limitPonderacionEnergia = new LimitadorDeDocumento(3);
        txtPonderacionEnergia.setDocument(limitPonderacionEnergia);
        LimitadorDeDocumento limitPonderacionAgua = new LimitadorDeDocumento(3);
        txtPonderacionAgua.setDocument(limitPonderacionAgua);
    }
    
    
    
    public void inicializar_tabla(){
        
        try {
            
        //Servicios Basicos
            String titulosb[]={"Cod_Factor","Cod_Servicio","Factores de Produccion","Servicio","Área","Ponderación de Energía","Base de Asignacion Energía","Ponderación de Consumo Agua",
                "Base de Asignacion Consumo Agua","Consultas Mensuales"};
            msb=new DefaultTableModel(null,titulosb);
            JTable psb=new JTable(msb);
            String filasb[]=new String[9];
            tablaServiciosBasicosEA.setModel(msb);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(msb);
            tablaServiciosBasicosEA.setRowSorter(elQueOrdenasb);
            this.tablaServiciosBasicosEA.setModel(msb);
            
            formato();
        } catch (Exception e) {
        }
            
    }
    
    public void formato(){
            
        try {
            
            //Servicios Basicos
            tablaServiciosBasicosEA.getColumnModel().getColumn(0).setPreferredWidth(160);
            tablaServiciosBasicosEA.getColumnModel().getColumn(1).setPreferredWidth(180); 
            tablaServiciosBasicosEA.getColumnModel().getColumn(2).setPreferredWidth(200);
            tablaServiciosBasicosEA.getColumnModel().getColumn(3).setPreferredWidth(180);
            tablaServiciosBasicosEA.getColumnModel().getColumn(4).setPreferredWidth(80);
            tablaServiciosBasicosEA.getColumnModel().getColumn(5).setPreferredWidth(180);
            tablaServiciosBasicosEA.getColumnModel().getColumn(6).setPreferredWidth(180);
            tablaServiciosBasicosEA.getColumnModel().getColumn(7).setPreferredWidth(200);
            tablaServiciosBasicosEA.getColumnModel().getColumn(8).setPreferredWidth(220);
            tablaServiciosBasicosEA.getColumnModel().getColumn(9).setPreferredWidth(220);
            //Servicios Basicos- Ocultar
            tablaServiciosBasicosEA.getColumnModel().getColumn(0).setMinWidth(0);
            tablaServiciosBasicosEA.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaServiciosBasicosEA.getColumnModel().getColumn(1).setMinWidth(0);
            tablaServiciosBasicosEA.getColumnModel().getColumn(1).setMaxWidth(0);
  
                     

        } catch (Exception e) {
        }
    }
    
    public void habilitar(){
        try {
            
        txtConsumoMensual.setEnabled(true);
        txtConsumoMensualAgua.setEnabled(true);
        txtArea.setEnabled(true);
        txtConsultas_Mensuales.setEnabled(true);
        txtPonderacionEnergia.setEnabled(true);
        txtPonderacionAgua.setEnabled(true);
        
        } catch (Exception e) {
        }
    }
    
    public void habilitar_area(){
        
        try {

        txtArea.setEnabled(true);
        txtConsultas_Mensuales.setEnabled(true);
        txtPonderacionEnergia.setEnabled(true);
        txtPonderacionAgua.setEnabled(true);
                    
        } catch (Exception e) {
        }
    }
    
    public void deshabilitar(){
        try {
            
        txtConsumoMensual.setEnabled(false);
        txtConsumoMensualAgua.setEnabled(false);
        txtArea.setEnabled(false);
        txtConsultas_Mensuales.setEnabled(false);
        txtPonderacionEnergia.setEnabled(false);
        txtPonderacionAgua.setEnabled(false);
        txtConsultas_Mensuales.setEnabled(false);
        
        } catch (Exception e) {
        }
    }
    
    public void cargarTipoSustentacion(){
       
    try{
       DefaultTableModel tabla= new DefaultTableModel();
      
       tabla.addColumn("Codigo");
       tabla.addColumn("Tipo Sustentacion");
 
       
       cst=con.prepareCall("{call COSTOS_COSTOS_TIPO_SUSTENTACION_listar}");
       r=cst.executeQuery();
       while (r.next()){
       Object dato[]=new  Object[2];
       for (int i=0; i<2; i++){
           dato[i]=r.getString(i+1);
       }
       tabla.addRow(dato);
       }
       this.tablaTipoSB.setModel(tabla);
       //formato();
       
       }catch (Exception e){
       }
      
    }
    
    public void cargarServiciosVariosB(){
       
       try{ 
       DefaultTableModel tabla= new DefaultTableModel();
       
       tabla.addColumn("Codigo");
       tabla.addColumn("Servicio");
 
       
       cst=con.prepareCall("{call COSTOS_COSTOS_SERVICIOS_VARIOS_listar}");
       r=cst.executeQuery();
       while (r.next()){
       Object dato[]=new  Object[2];
       for (int i=0; i<2; i++){
           dato[i]=r.getString(i+1);
       }
       tabla.addRow(dato);
       }
       this.TablaServiciosB.setModel(tabla);
       //formato();
       
       }catch (Exception e){
       }
      
    }

    public void energiaElectrica(){
   try {   
       
        double metro_Cuadrado=0;
        double ponderacion=0;
        double base_Asignacion=0;
             
                    
        metro_Cuadrado = Double.parseDouble(txtArea.getText());
        ponderacion = Double.parseDouble(txtPonderacionEnergia.getText());
  
        base_Asignacion = metro_Cuadrado * ponderacion; 
        
        txtBaseAsignacionEnergia.setText(""+base_Asignacion); 
       
        } catch (Exception e) {
            
        }
    }
    
    public void consumoAgua(){
     try {   
         
        double metro_cuadrado_agua=0;
        double ponderacion_agua=0;
        double base_Asignacion_Agua=0;
 
            metro_cuadrado_agua = Double.parseDouble(txtArea.getText());
            ponderacion_agua = Double.parseDouble(txtPonderacionAgua.getText());
            
            base_Asignacion_Agua = metro_cuadrado_agua * ponderacion_agua;
            txtBaseAsignacionAgua.setText(""+base_Asignacion_Agua);
            
        } catch (Exception e) {
            
        }
    }
    
    public void mostrarDetalleServiciosBasicos(){
     
        try {

        DefaultTableModel modelo=(DefaultTableModel) tablaServiciosBasicosEA.getModel(); 
 
        Object [] fila=new Object[11]; 

        fila[0]=txtCodigo_Sustentacion.getText();
        fila[1]=txtCodigo_Servicio.getText();
        fila[2]=txtTipoSustentacionB.getText(); 
        fila[3]=txtServiciosVariosB.getText(); 
        fila[4]=txtArea.getText(); 
        fila[5]=txtPonderacionEnergia.getText(); 
        fila[6]=txtBaseAsignacionEnergia.getText(); 
        fila[7]=txtPonderacionAgua.getText();
        fila[8]=txtBaseAsignacionAgua.getText();
        fila[9]=txtConsultas_Mensuales.getText();
 
        if(tablaServiciosBasicosEA.getRowCount()==0){
            modelo.addRow(fila); 
            tablaServiciosBasicosEA.setModel(modelo);
            
                    double BAEnergia = Double.parseDouble(txtBaseAsignacionEnergia.getText());
                    double BAAgua = Double.parseDouble(txtBaseAsignacionAgua.getText());
                    double BAC1 = Double.parseDouble(txtSumatoriaEnergiaBA1.getText());
                    double BAC2 = Double.parseDouble(txtSumatoriaAguaBA1.getText());
                    double totE, totA;

                    totE = BAEnergia + BAC1;
                    Costos_Sustentacion.txtSumatoriaEnergiaBA.setText(""+totE);
                    txtSumatoriaEnergiaBA1.setText(""+totE);

                    totA = BAAgua + BAC2;
                    Costos_Sustentacion.txtSumatoriaAguaBA.setText(""+totA);
                    txtSumatoriaAguaBA1.setText(""+totA);

        }
        else{
           if(repiteDetalleServicioB()==true){
               JOptionPane.showMessageDialog(rootPane,"El Servicio ya ha sido ingresado.");   
          }
           else{
                modelo.addRow(fila); 
                tablaServiciosBasicosEA.setModel(modelo);
                
                double BAEnergia = Double.parseDouble(txtBaseAsignacionEnergia.getText());
                double BAAgua = Double.parseDouble(txtBaseAsignacionAgua.getText());
                double BAC1 = Double.parseDouble(txtSumatoriaEnergiaBA1.getText());
                double BAC2 = Double.parseDouble(txtSumatoriaAguaBA1.getText());
                double totE, totA;

                totE = BAEnergia + BAC1;
                Costos_Sustentacion.txtSumatoriaEnergiaBA.setText(""+totE);
                txtSumatoriaEnergiaBA1.setText(""+totE);

                totA = BAAgua + BAC2;
                Costos_Sustentacion.txtSumatoriaAguaBA.setText(""+totA);
                txtSumatoriaAguaBA1.setText(""+totA);
           }
          }
        
        } catch (Exception e) {
        }
 
    }
    
    public boolean repiteDetalleServicioB(){
         
         boolean c=false;
         for (int i = 0; i < tablaServiciosBasicosEA.getRowCount(); i++){    
               if(txtServiciosVariosB.getText().equalsIgnoreCase(tablaServiciosBasicosEA.getValueAt(i, 3).toString())){
                    c=true;
	}}
               return c;
    }

    
    public void coeficiente_consumo_luz(){
       //Calcular coeficiente de consumo
        try {
            
        double base_A, sumat, coeficiente_consumo;
        
        base_A = Double.parseDouble(txtBaseAsignacionEnergia.getText());
        sumat = Double.parseDouble(Costos_Sustentacion.txtSumatoriaEnergiaBA.getText());
        coeficiente_consumo = base_A / sumat;
        
        } catch (Exception e) {
        }
    }
    
    
    public void limpiarServicios(){
        
        try {
        
        txtCodigo_Sustentacion.setText("");
        txtCodigo_Servicio.setText("");
        txtTipoSustentacionB.setText("");
        txtServiciosVariosB.setText("");
        btnBuscarServicios.setEnabled(false);      
        txtArea.setText("");
        txtConsultas_Mensuales.setText("");
        txtPonderacionEnergia.setText("");
        txtBaseAsignacionEnergia.setText("");      
        txtPonderacionAgua.setText("");
        txtBaseAsignacionAgua.setText("");    
        
        } catch (Exception e) {
        
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

        BUSCAR_TIPO_SUSTENTACION = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtBuscarSustentacionSB = new javax.swing.JTextField();
        btn_buscar_sustentacionSB_enter = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTipoSB = new javax.swing.JTable();
        BUSCAR_SERVICIOS_VARIOS = new javax.swing.JDialog();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtServiciosVariosBuscar = new javax.swing.JTextField();
        btn_buscar_servicios_SB = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaServiciosB = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblUsu = new javax.swing.JLabel();
        btn_agregar_SB = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTipoSustentacionB = new javax.swing.JTextField();
        txtServiciosVariosB = new javax.swing.JTextField();
        btnBuscarSustentacion = new javax.swing.JButton();
        btnBuscarServicios = new javax.swing.JButton();
        txtConsumoMensual = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtConsumoMensualAgua = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtPonderacionEnergia = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtBaseAsignacionEnergia = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtSumatoriaEnergiaBA1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtPonderacionAgua = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtBaseAsignacionAgua = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtSumatoriaAguaBA1 = new javax.swing.JTextField();
        txtCodigo_Sustentacion = new javax.swing.JTextField();
        txtCodigo_Servicio = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtConsultas_Mensuales = new javax.swing.JTextField();
        btn_quitar_SB = new javax.swing.JButton();
        btn_grabar_SB = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaServiciosBasicosEA = new javax.swing.JTable();
        txtSumatoria_Principal_E = new javax.swing.JTextField();
        txtSumatoria_Principal_A = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();

        BUSCAR_TIPO_SUSTENTACION.setAlwaysOnTop(true);
        BUSCAR_TIPO_SUSTENTACION.setMinimumSize(new java.awt.Dimension(450, 500));

        jLabel7.setFont(new java.awt.Font("Palatino Linotype", 1, 18)); // NOI18N
        jLabel7.setText("FACTORES DE PRODUCCION");

        jLabel8.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel8.setText("Sustentación:");

        txtBuscarSustentacionSB.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarSustentacionSBCaretUpdate(evt);
            }
        });
        txtBuscarSustentacionSB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarSustentacionSBKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarSustentacionSBKeyTyped(evt);
            }
        });

        btn_buscar_sustentacionSB_enter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
        btn_buscar_sustentacionSB_enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_sustentacionSB_enterActionPerformed(evt);
            }
        });

        tablaTipoSB = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tablaTipoSB.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaTipoSB.setRowHeight(24);
        tablaTipoSB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaTipoSBKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaTipoSB);

        javax.swing.GroupLayout BUSCAR_TIPO_SUSTENTACIONLayout = new javax.swing.GroupLayout(BUSCAR_TIPO_SUSTENTACION.getContentPane());
        BUSCAR_TIPO_SUSTENTACION.getContentPane().setLayout(BUSCAR_TIPO_SUSTENTACIONLayout);
        BUSCAR_TIPO_SUSTENTACIONLayout.setHorizontalGroup(
            BUSCAR_TIPO_SUSTENTACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BUSCAR_TIPO_SUSTENTACIONLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(BUSCAR_TIPO_SUSTENTACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BUSCAR_TIPO_SUSTENTACIONLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarSustentacionSB, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_buscar_sustentacionSB_enter, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BUSCAR_TIPO_SUSTENTACIONLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(77, 77, 77))
        );
        BUSCAR_TIPO_SUSTENTACIONLayout.setVerticalGroup(
            BUSCAR_TIPO_SUSTENTACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BUSCAR_TIPO_SUSTENTACIONLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel7)
                .addGap(30, 30, 30)
                .addGroup(BUSCAR_TIPO_SUSTENTACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BUSCAR_TIPO_SUSTENTACIONLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(BUSCAR_TIPO_SUSTENTACIONLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtBuscarSustentacionSB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_buscar_sustentacionSB_enter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        BUSCAR_SERVICIOS_VARIOS.setAlwaysOnTop(true);
        BUSCAR_SERVICIOS_VARIOS.setMinimumSize(new java.awt.Dimension(450, 500));

        jLabel9.setFont(new java.awt.Font("Palatino Linotype", 1, 18)); // NOI18N
        jLabel9.setText("SERVICIOS VARIOS");

        jLabel10.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel10.setText("Servicio:");

        txtServiciosVariosBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtServiciosVariosBuscarKeyTyped(evt);
            }
        });

        btn_buscar_servicios_SB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
        btn_buscar_servicios_SB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_servicios_SBActionPerformed(evt);
            }
        });

        TablaServiciosB = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        TablaServiciosB.setModel(new javax.swing.table.DefaultTableModel(
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
        TablaServiciosB.setRowHeight(24);
        TablaServiciosB.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TablaServiciosBPropertyChange(evt);
            }
        });
        TablaServiciosB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TablaServiciosBKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(TablaServiciosB);

        javax.swing.GroupLayout BUSCAR_SERVICIOS_VARIOSLayout = new javax.swing.GroupLayout(BUSCAR_SERVICIOS_VARIOS.getContentPane());
        BUSCAR_SERVICIOS_VARIOS.getContentPane().setLayout(BUSCAR_SERVICIOS_VARIOSLayout);
        BUSCAR_SERVICIOS_VARIOSLayout.setHorizontalGroup(
            BUSCAR_SERVICIOS_VARIOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BUSCAR_SERVICIOS_VARIOSLayout.createSequentialGroup()
                .addGroup(BUSCAR_SERVICIOS_VARIOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BUSCAR_SERVICIOS_VARIOSLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addGroup(BUSCAR_SERVICIOS_VARIOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtServiciosVariosBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_buscar_servicios_SB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BUSCAR_SERVICIOS_VARIOSLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        BUSCAR_SERVICIOS_VARIOSLayout.setVerticalGroup(
            BUSCAR_SERVICIOS_VARIOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BUSCAR_SERVICIOS_VARIOSLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel9)
                .addGap(33, 33, 33)
                .addGroup(BUSCAR_SERVICIOS_VARIOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_buscar_servicios_SB)
                    .addGroup(BUSCAR_SERVICIOS_VARIOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtServiciosVariosBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISGESH .::. Servicios Básicos");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Determinación de Costos de los Servicios Energía Eléctrica y Agua");

        jLabel2.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("FECHA:");

        lblFecha.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("jLabel4");

        jLabel3.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("HORA:");

        lblHora.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        lblHora.setForeground(new java.awt.Color(255, 255, 255));
        lblHora.setText("jLabel4");

        jLabel6.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("USUARIO");

        lblUsu.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        lblUsu.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu.setText("Usuario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHora)
                    .addComponent(lblFecha))
                .addGap(66, 66, 66)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblFecha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblHora)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblUsu))))
                .addContainerGap())
        );

        btn_agregar_SB.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        btn_agregar_SB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btn_agregar_SB.setText("Agregar");
        btn_agregar_SB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_SBActionPerformed(evt);
            }
        });

        btn_cancelar.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dialog-close.png"))); // NOI18N
        btn_cancelar.setMnemonic('C');
        btn_cancelar.setText("CANCELAR");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel4.setText("Tipo Sustentacion:");

        jLabel5.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel5.setText("Servicio Varios:");

        txtTipoSustentacionB.setEditable(false);
        txtTipoSustentacionB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtTipoSustentacionB.setEnabled(false);

        txtServiciosVariosB.setEditable(false);
        txtServiciosVariosB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtServiciosVariosB.setEnabled(false);

        btnBuscarSustentacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
        btnBuscarSustentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarSustentacionActionPerformed(evt);
            }
        });

        btnBuscarServicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
        btnBuscarServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarServiciosActionPerformed(evt);
            }
        });

        txtConsumoMensual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsumoMensualActionPerformed(evt);
            }
        });
        txtConsumoMensual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConsumoMensualKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel19.setText("Consumo Mensual de Energía:");

        jLabel21.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel21.setText("Consumo Mensual de Agua:");

        txtConsumoMensualAgua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConsumoMensualAguaKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel11.setText("Área (a):");

        txtArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAreaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAreaKeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel18.setText("m²");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Energia Eléctrica"));
        jPanel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel12.setText("Ponderación de consumo (b):");

        txtPonderacionEnergia.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPonderacionEnergiaCaretUpdate(evt);
            }
        });
        txtPonderacionEnergia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPonderacionEnergiaActionPerformed(evt);
            }
        });
        txtPonderacionEnergia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPonderacionEnergiaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPonderacionEnergiaKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel13.setText("Base de Asignación (a)*(b)=(c):");

        txtBaseAsignacionEnergia.setEditable(false);
        txtBaseAsignacionEnergia.setEnabled(false);
        txtBaseAsignacionEnergia.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBaseAsignacionEnergiaCaretUpdate(evt);
            }
        });
        txtBaseAsignacionEnergia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBaseAsignacionEnergiaKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel16.setText("Σ de Bases de Asignación (BA):");

        txtSumatoriaEnergiaBA1.setEditable(false);
        txtSumatoriaEnergiaBA1.setText("0.00");
        txtSumatoriaEnergiaBA1.setEnabled(false);
        txtSumatoriaEnergiaBA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumatoriaEnergiaBA1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(txtPonderacionEnergia, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel16))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBaseAsignacionEnergia)
                            .addComponent(txtSumatoriaEnergiaBA1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtPonderacionEnergia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtBaseAsignacionEnergia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtSumatoriaEnergiaBA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Consumo de Agua"));
        jPanel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel14.setText("Ponderación de consumo (b):");

        txtPonderacionAgua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPonderacionAguaCaretUpdate(evt);
            }
        });
        txtPonderacionAgua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPonderacionAguaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPonderacionAguaKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel15.setText("Base de Asignación (a)*(b)=(c):");

        txtBaseAsignacionAgua.setEditable(false);
        txtBaseAsignacionAgua.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel17.setText("Σ de Bases de signación (BA):");

        txtSumatoriaAguaBA1.setEditable(false);
        txtSumatoriaAguaBA1.setText("0.00");
        txtSumatoriaAguaBA1.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17))
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBaseAsignacionAgua)
                    .addComponent(txtPonderacionAgua)
                    .addComponent(txtSumatoriaAguaBA1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtPonderacionAgua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtBaseAsignacionAgua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtSumatoriaAguaBA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel20.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel20.setText("Consultas Mensuales:");

        txtConsultas_Mensuales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConsultas_MensualesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConsultas_MensualesKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addGap(27, 27, 27)
                                        .addComponent(txtConsumoMensual))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTipoSustentacionB, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCodigo_Sustentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btnBuscarSustentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(36, 36, 36)
                                                .addComponent(txtServiciosVariosB, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnBuscarServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel21)
                                                    .addComponent(jLabel20))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtConsumoMensualAgua, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                                    .addComponent(txtConsultas_Mensuales)))))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(221, 221, 221)
                                        .addComponent(txtCodigo_Servicio, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(82, 82, 82)
                                .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigo_Sustentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo_Servicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtTipoSustentacionB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscarSustentacion)
                    .addComponent(btnBuscarServicios)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtServiciosVariosB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtConsumoMensual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(txtConsumoMensualAgua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20)
                    .addComponent(txtConsultas_Mensuales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_quitar_SB.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        btn_quitar_SB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btn_quitar_SB.setText("Quitar");
        btn_quitar_SB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quitar_SBActionPerformed(evt);
            }
        });

        btn_grabar_SB.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        btn_grabar_SB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/GRABA.png"))); // NOI18N
        btn_grabar_SB.setMnemonic('A');
        btn_grabar_SB.setText("CARGAR");
        btn_grabar_SB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_grabar_SBActionPerformed(evt);
            }
        });

        tablaServiciosBasicosEA = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tablaServiciosBasicosEA.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaServiciosBasicosEA.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaServiciosBasicosEA.setRowHeight(20);
        jScrollPane4.setViewportView(tablaServiciosBasicosEA);

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
        jLabel22.setText("Salir(Esc)");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/aceptar16x16.png"))); // NOI18N
        jLabel23.setText("Cargar(Alt+A)");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/cancelar16x16.png"))); // NOI18N
        jLabel37.setText("Cancelar(Alt+C)");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(667, 667, 667)
                .addComponent(jLabel23)
                .addGap(21, 21, 21)
                .addComponent(jLabel37)
                .addGap(18, 18, 18)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn_agregar_SB)
                                        .addComponent(btn_quitar_SB, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtSumatoria_Principal_E, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSumatoria_Principal_A, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(296, 296, 296)
                        .addComponent(btn_grabar_SB)
                        .addGap(133, 133, 133)
                        .addComponent(btn_cancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btn_agregar_SB)
                        .addGap(18, 18, 18)
                        .addComponent(btn_quitar_SB)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSumatoria_Principal_E, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumatoria_Principal_A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_grabar_SB))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getAccessibleContext().setAccessibleName("SERVICIOS BÁSICOS");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarSustentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarSustentacionActionPerformed
        BUSCAR_TIPO_SUSTENTACION.setVisible(true);  
        tablaTipoSB.getSelectionModel().setSelectionInterval(0, 0);
        tablaTipoSB.requestFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarSustentacionActionPerformed

    private void btnBuscarServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarServiciosActionPerformed
        BUSCAR_SERVICIOS_VARIOS.setVisible(true);
        TablaServiciosB.getSelectionModel().setSelectionInterval(0, 0);
        TablaServiciosB.requestFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarServiciosActionPerformed

    private void tablaTipoSBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaTipoSBKeyPressed
    
        try {
            
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila =tablaTipoSB.getSelectedRow();
           
             txtCodigo_Sustentacion.setText(String.valueOf(tablaTipoSB.getValueAt(fila, 0)));
             txtTipoSustentacionB.setText(String.valueOf(tablaTipoSB.getValueAt(fila, 1)));  
             
             BUSCAR_TIPO_SUSTENTACION.setVisible(false);
             btnBuscarServicios.setEnabled(true);
             btnBuscarServicios.requestFocus();
        }
        
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_tablaTipoSBKeyPressed

    private void TablaServiciosBPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TablaServiciosBPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_TablaServiciosBPropertyChange

    private void TablaServiciosBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaServiciosBKeyPressed
        
        try {
            
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila =TablaServiciosB.getSelectedRow(); 
            
             txtCodigo_Servicio.setText(String.valueOf(TablaServiciosB.getValueAt(fila, 0)));
             txtServiciosVariosB.setText(String.valueOf(TablaServiciosB.getValueAt(fila, 1)));             
             BUSCAR_SERVICIOS_VARIOS.setVisible(false);
             
             if(txtConsumoMensual.getText().equalsIgnoreCase("") && txtConsumoMensualAgua.getText().equalsIgnoreCase("") ){
                 habilitar();
                 txtConsumoMensual.requestFocus();
            }else {
                 habilitar_area();
                 txtArea.requestFocus();
             }
        }
        
        } catch (Exception e) {
        }
    }//GEN-LAST:event_TablaServiciosBKeyPressed

    private void txtPonderacionEnergiaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPonderacionEnergiaCaretUpdate
       
    }//GEN-LAST:event_txtPonderacionEnergiaCaretUpdate

    private void txtPonderacionAguaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPonderacionAguaCaretUpdate
      // consumoAgua();        // TODO add your handling code here:
    }//GEN-LAST:event_txtPonderacionAguaCaretUpdate

    private void btn_agregar_SBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_SBActionPerformed
   
    try {
            
        if(txtTipoSustentacionB.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane, "Ingrese el Factor de Producción"); 
        } 
        else if(txtServiciosVariosB.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane, "Ingrese el Servicio");
        }
        else if(txtConsumoMensual.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane, "Ingrese el Consumo Mensual de Energia Electrica"); 
            txtConsumoMensual.requestFocus();
        }
        else if(txtConsumoMensualAgua.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane, "Ingrese el Consumo Mensual de Agua"); 
            txtConsumoMensualAgua.requestFocus();
        }
        else if(txtArea.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane,"Ingrese Área");
        }
        else if(txtConsultas_Mensuales.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane,"Ingrese Cantidad de Consultas");
            txtConsultas_Mensuales.requestFocus();
        }
        else if(txtPonderacionEnergia.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane,"Ingrese la Ponderacion de Consumo de Energia Eléctrica");     
        }
        else if(txtPonderacionAgua.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane,"Ingrese la Ponderacion de Consumo de Agua");
        }
        else  {
                mostrarDetalleServiciosBasicos();
                deshabilitar();
                limpiarServicios();

        }
           
    } catch (Exception e) {
    
    }
    }//GEN-LAST:event_btn_agregar_SBActionPerformed

    
    private void txtPonderacionEnergiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPonderacionEnergiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPonderacionEnergiaActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed

        dispose();  
        double CSE = Double.parseDouble(txtSumatoria_Principal_E.getText());
        double CSA = Double.parseDouble(txtSumatoria_Principal_A.getText());
        Costos_Sustentacion.txtSumatoriaEnergiaBA.setText(String.valueOf(CSE));
        Costos_Sustentacion.txtSumatoriaAguaBA.setText(String.valueOf(CSA));

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void txtPonderacionEnergiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPonderacionEnergiaKeyReleased
        try {
        
        if(txtArea.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane,"Debe ingresar el Área");
             txtArea.requestFocus();   
             txtPonderacionEnergia.setText("");           
        }else if(txtPonderacionEnergia.getText().equalsIgnoreCase("")){
            txtBaseAsignacionEnergia.setText("");
        }else{
            energiaElectrica();
          
        }
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtPonderacionEnergiaKeyReleased

    private void txtPonderacionAguaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPonderacionAguaKeyReleased
        
        
        try {
            
        if(txtArea.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane,"Debe ingresar el Área");
            txtPonderacionAgua.setText("");
            txtArea.requestFocus();
        }else
        if(txtPonderacionAgua.getText().equalsIgnoreCase("")){
           txtBaseAsignacionAgua.setText("");
        }else{
            consumoAgua();
        }
        
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtPonderacionAguaKeyReleased

    private void txtBaseAsignacionEnergiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBaseAsignacionEnergiaKeyReleased
        
    }//GEN-LAST:event_txtBaseAsignacionEnergiaKeyReleased

    private void txtBaseAsignacionEnergiaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBaseAsignacionEnergiaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBaseAsignacionEnergiaCaretUpdate

    private void btn_quitar_SBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quitar_SBActionPerformed
        try{
            int filaselec=tablaServiciosBasicosEA.getSelectedRow();
            if( filaselec>=0){
                int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea QUITAR el servicio ?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(eliminar == 0 ){
                    
                    DefaultTableModel modelo = (DefaultTableModel)tablaServiciosBasicosEA.getModel();
                    
                    //Consumo de energia electrica
                    double nuevasuma;
                    double suma = Double.parseDouble(txtSumatoriaEnergiaBA1.getText().toString());
                    double quita = Double.parseDouble(tablaServiciosBasicosEA.getValueAt(filaselec, 6).toString());
                    
                    nuevasuma = suma - quita;
                    
                    txtSumatoriaEnergiaBA1.setText(String.valueOf(nuevasuma));
                    Costos_Sustentacion.txtSumatoriaEnergiaBA.setText(String.valueOf(nuevasuma));
 
                    
                    //consumo de agua
                    double nuevasumaA;
                    double sumaA = Double.parseDouble(txtSumatoriaAguaBA1.getText().toString());
                    double quitaA = Double.parseDouble(tablaServiciosBasicosEA.getValueAt(filaselec, 8).toString());
                    
                    nuevasumaA = sumaA - quitaA;
                    
                    txtSumatoriaAguaBA1.setText(String.valueOf(nuevasumaA));
                    Costos_Sustentacion.txtSumatoriaAguaBA.setText(String.valueOf(nuevasumaA));
                    
                    
                    modelo.removeRow(filaselec);
                            
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione el Servicio a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Detalle a eliminar");
        } 
    }//GEN-LAST:event_btn_quitar_SBActionPerformed

    private void btn_grabar_SBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_grabar_SBActionPerformed

        try {
            
            modelo1 = (DefaultTableModel) tablaServiciosBasicosEA.getModel();
            limpiarTabla();
            
            if(tablaServiciosBasicosEA.getRowCount()==0){
                JOptionPane.showMessageDialog(null, "No hay registros que cargar");
            }else{
            
            //pasar datos de una tabla a otra
            for (int i=0;i<modelo1.getRowCount(); i++){
            String cod_sustentacion, cod_servicio, sustentacion, servicio,
            consumo_E, consumo_A, area, ponderacion_energia,base_energia,sumatoria_BAE,
            energia1,energia2,consulta_E,ponderacion_agua,base_agua,sumatoria_BAA,agua1,agua2,consulta_A, 
            tot_consulta,costo_estandar;
            double CE, CA, e1,e2,a1,a2, costo_E;
                        
            double sumaE = Double.parseDouble(txtSumatoriaEnergiaBA1.getText().toString());
            double sumaA = Double.parseDouble(txtSumatoriaAguaBA1.getText().toString());
            double importeE = Double.parseDouble(txtConsumoMensual.getText().toString());
            double importeA = Double.parseDouble(txtConsumoMensualAgua.getText().toString());
            double consulta = Double.parseDouble(tablaServiciosBasicosEA.getValueAt(i, 9).toString());
            
            
            tot_consulta = String.valueOf(consulta);
            
            cod_sustentacion = tablaServiciosBasicosEA.getValueAt(i, 0).toString();
            cod_servicio = tablaServiciosBasicosEA.getValueAt(i, 1).toString();           
            sustentacion = tablaServiciosBasicosEA.getValueAt(i, 2).toString();
            servicio = tablaServiciosBasicosEA.getValueAt(i, 3).toString();
            area = tablaServiciosBasicosEA.getValueAt(i, 4).toString();
            ponderacion_energia = tablaServiciosBasicosEA.getValueAt(i, 5).toString();
            base_energia = tablaServiciosBasicosEA.getValueAt(i, 6).toString();
            ponderacion_agua = tablaServiciosBasicosEA.getValueAt(i, 7).toString();
            base_agua = tablaServiciosBasicosEA.getValueAt(i, 8).toString();
            consumo_E = txtConsumoMensual.getText().toString();
            consumo_A = txtConsumoMensualAgua.getText().toString();
            sumatoria_BAE = txtSumatoriaEnergiaBA1.getText().toString();
            sumatoria_BAA = txtSumatoriaAguaBA1.getText().toString();
            
            
            //Energia electrica
            e1 = Double.parseDouble(base_energia) / sumaE;   
            
            //Redondear e1
            double parteEntera, resultado;
            resultado = e1;
            parteEntera = Math.floor(resultado);
            resultado=(resultado-parteEntera)*Math.pow(10, 4);
            resultado=Math.round(resultado);
            resultado=(resultado/Math.pow(10, 4))+parteEntera;
                        
            e2 = resultado * importeE;  
            
            //CALCULAR COSTO ESTANDAR DE ENERGIA
            CE = e2/consulta;
                       
            //Redondear CE
            double parteEntera_CE, resultado_CE;
            resultado_CE = CE;
            parteEntera_CE = Math.floor(resultado_CE);
            resultado_CE=(resultado_CE-parteEntera_CE)*Math.pow(10, 2);
            resultado_CE=Math.round(resultado_CE);
            resultado_CE=(resultado_CE/Math.pow(10, 2))+parteEntera_CE;
            

            BigDecimal bdE1 = new BigDecimal(e1);
            BigDecimal bdE2 = new BigDecimal(e2);
            BigDecimal bdE3 = new BigDecimal(CE);
            
            bdE1 = bdE1.setScale(4, BigDecimal.ROUND_HALF_UP);
            bdE2 = bdE2.setScale(2, BigDecimal.ROUND_HALF_UP);
            bdE3 = bdE3.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            energia1 = (bdE1.toString());
            energia2 = (bdE2.toString());
            consulta_E = (bdE3.toString());
            
            
            //Consumo de Agua
            a1 = Double.parseDouble(base_agua) / sumaA;  
            
            //Redondear a1
            double parteEnteraA, resultadoA;
            resultadoA = a1;
            parteEnteraA = Math.floor(resultadoA);
            resultadoA=(resultadoA-parteEnteraA)*Math.pow(10, 4);
            resultadoA=Math.round(resultadoA);
            resultadoA=(resultadoA/Math.pow(10, 4))+parteEnteraA;
                       
            a2 = resultadoA * importeA;
            
            //CALCULAR COSTO ESTANDAR DE AGUA
            CA = a2 / consulta;
                      
            //Redondear CA
            double parteEnteraA_CA, resultadoA_CA;
            resultadoA_CA = CA;
            parteEnteraA_CA = Math.floor(resultadoA_CA);
            resultadoA_CA=(resultadoA_CA-parteEnteraA_CA)*Math.pow(10, 2);
            resultadoA_CA=Math.round(resultadoA_CA);
            resultadoA_CA=(resultadoA_CA/Math.pow(10, 2))+parteEnteraA_CA;
            
            
            BigDecimal bdA1 = new BigDecimal(a1);
            BigDecimal bdA2 = new BigDecimal(a2);
            BigDecimal bdA3 = new BigDecimal(CA);
            
            bdA1 = bdA1.setScale(4, BigDecimal.ROUND_HALF_UP);
            bdA2 = bdA2.setScale(2, BigDecimal.ROUND_HALF_UP);
            bdA3 = bdA3.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            agua1 = (bdA1.toString());
            agua2 = (bdA2.toString());
            consulta_A = (bdA3.toString());
            
            costo_E = resultado_CE + resultadoA_CA;
            
            BigDecimal bdCE = new BigDecimal(costo_E);
            
            bdCE = bdCE.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            //CALCULAR SUMA DEL COSTO ESTANDAR DE ENERGIA Y AGUA
            costo_estandar = (bdCE.toString());

            //Cargar los datos a la otra tabla 
            modelo2 = (DefaultTableModel) tbServiciosBasicos.getModel();
            
            String filaelemento[] = {cod_sustentacion, cod_servicio, sustentacion,servicio,area,ponderacion_energia,
            base_energia,sumatoria_BAE,energia1,consumo_E,energia2, consulta_E,ponderacion_agua, base_agua,
            sumatoria_BAA,agua1,consumo_A, agua2, consulta_A, tot_consulta, costo_estandar};
                                 
            modelo2.addRow(filaelemento);
            
            //Cargar consumo mensual de anergia y agua al formulario principal
            double con_E, con_A;
            con_E = Double.parseDouble(txtConsumoMensual.getText().toString());
            con_A = Double.parseDouble(txtConsumoMensualAgua.getText().toString());
            
            Costos_Sustentacion.txtConsumo_ME.setText(String.valueOf(con_E));
            Costos_Sustentacion.txtConsumo_MA.setText(String.valueOf(con_A));
           cargarResumenCostoSBasicos();
            dispose();
            }
        }    
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btn_grabar_SBActionPerformed

    
    public void limpiarTabla(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) Costos_Sustentacion.tbServiciosBasicos.getModel();
            int filas=tbServiciosBasicos.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingresar todos los campos");
        }
    }
    
    
    private void txtConsumoMensualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsumoMensualKeyTyped
    //permite escribir solo numeros y un punto decimal
    try {
  
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !='.'){
            evt.consume();            
        }
        if(tecla =='.' && txtConsumoMensual.getText().contains(".")){
            evt.consume();            
        }
                 
    } catch (Exception e) {
    }
    }//GEN-LAST:event_txtConsumoMensualKeyTyped

    private void txtConsumoMensualAguaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsumoMensualAguaKeyTyped
    //permite escribir solo numeros y un punto decimal
    try {
            
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !='.'){
            evt.consume();            
        }
        if(tecla =='.' && txtConsumoMensualAgua.getText().contains(".")){
            evt.consume();            
        }
        
    } catch (Exception e) {
    }
    }//GEN-LAST:event_txtConsumoMensualAguaKeyTyped

    private void txtAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaKeyTyped
    //permite escribir solo numeros y un punto decimal
    try {
            
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !='.'){
            evt.consume();            
        }
        if(tecla =='.' && txtArea.getText().contains(".")){
            evt.consume();            
        }
    
    } catch (Exception e) {
    }
    }//GEN-LAST:event_txtAreaKeyTyped

    private void txtPonderacionEnergiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPonderacionEnergiaKeyTyped
    //permite escribir solo numeros
    try {        

        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    
    } catch (Exception e) {
    }    
       
    }//GEN-LAST:event_txtPonderacionEnergiaKeyTyped

    private void txtPonderacionAguaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPonderacionAguaKeyTyped
    //permite escribir solo numeros y un punto decimal
    try {

        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
                } catch (Exception e) {
        }
    }//GEN-LAST:event_txtPonderacionAguaKeyTyped

    private void txtAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaKeyReleased
    
    try {

        if(txtArea.getText().equalsIgnoreCase("")){
           txtPonderacionEnergia.setText("");
           txtBaseAsignacionEnergia.setText("");
           txtPonderacionAgua.setText("");
           txtBaseAsignacionAgua.setText("");
       }else{
           energiaElectrica();
           consumoAgua();
       }
    
    } catch (Exception e) {
    }
    }//GEN-LAST:event_txtAreaKeyReleased

    private void btn_buscar_sustentacionSB_enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_sustentacionSB_enterActionPerformed
    
    try{  
    String tipo_sust =txtBuscarSustentacionSB.getText().toString();              
        
    DefaultTableModel tabla= new DefaultTableModel();
       
       tabla.addColumn("Codigo");
       tabla.addColumn("Tipo Sustentacion");
       cst=con.prepareCall("{call COSTOS_COSTOS_TIPO_SUSTENTACION_buscar(?)}");     
            cst.setString(1, tipo_sust);
            r=cst.executeQuery();
       while (r.next()){
       Object dato[]=new  Object[2];
       for (int i=0; i<2; i++){
           dato[i]=r.getString(i+1);

       }
       tabla.addRow(dato);
       }
       
       this.tablaTipoSB.setModel(tabla);
       tablaTipoSB.getSelectionModel().setSelectionInterval(0, 0);
       tablaTipoSB.requestFocus();
       
       }catch (Exception e){}
       
       txtBuscarSustentacionSB.setText("");
       //txtBuscarSustentacionSB.requestFocus();

    }//GEN-LAST:event_btn_buscar_sustentacionSB_enterActionPerformed

    private void txtBuscarSustentacionSBCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarSustentacionSBCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarSustentacionSBCaretUpdate

    private void txtBuscarSustentacionSBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarSustentacionSBKeyTyped
    try {
 
       char teclaPresionada = evt.getKeyChar();
       if(teclaPresionada==KeyEvent.VK_ENTER){
           btn_buscar_sustentacionSB_enter.doClick();
       }
       
   } catch (Exception e) {
   }

    }//GEN-LAST:event_txtBuscarSustentacionSBKeyTyped

    private void btn_buscar_servicios_SBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_servicios_SBActionPerformed
    try{ 
    
    String tipo_sust =txtServiciosVariosBuscar.getText().toString();              
      
    DefaultTableModel tabla= new DefaultTableModel();
       
       tabla.addColumn("Codigo");
       tabla.addColumn("Servicio");
       cst=con.prepareCall("{call COSTOS_COSTOS_SERVICIOS_VARIOS_buscar(?)}");     
            cst.setString(1, tipo_sust);
            r=cst.executeQuery();
       while (r.next()){
       Object dato[]=new  Object[2];
       for (int i=0; i<2; i++){
           dato[i]=r.getString(i+1);

       }
       tabla.addRow(dato);
       }
       
       this.TablaServiciosB.setModel(tabla);
       TablaServiciosB.getSelectionModel().setSelectionInterval(0, 0);
       TablaServiciosB.requestFocus();
     
       }catch (Exception e){}
       
       txtServiciosVariosBuscar.setText("");
       //txtServiciosVariosBuscar.requestFocus();

        
// TODO add your handling code here:
    }//GEN-LAST:event_btn_buscar_servicios_SBActionPerformed

    private void txtServiciosVariosBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServiciosVariosBuscarKeyTyped
    
    try {
        
        char teclaPresionada = evt.getKeyChar();
       if(teclaPresionada==KeyEvent.VK_ENTER){
           btn_buscar_servicios_SB.doClick();
       }
       
    } catch (Exception e) {
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServiciosVariosBuscarKeyTyped

    private void txtBuscarSustentacionSBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarSustentacionSBKeyPressed
        try {
            
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            TablaServiciosB.getSelectionModel().setSelectionInterval(0, 0);
            TablaServiciosB.requestFocus();
        }
        
        } catch (Exception e) {
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarSustentacionSBKeyPressed

    private void txtConsultas_MensualesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsultas_MensualesKeyReleased
     if(txtArea.getText().equalsIgnoreCase("")){
         JOptionPane.showMessageDialog(rootPane, "Debe ingresar el Área");
         txtConsultas_Mensuales.setText("");
         txtArea.requestFocus();
     }
        
    }//GEN-LAST:event_txtConsultas_MensualesKeyReleased

    private void txtSumatoriaEnergiaBA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumatoriaEnergiaBA1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSumatoriaEnergiaBA1ActionPerformed

    private void txtConsultas_MensualesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsultas_MensualesKeyTyped
    try {        

        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    
    } catch (Exception e) {
    } 
    }//GEN-LAST:event_txtConsultas_MensualesKeyTyped

    private void txtConsumoMensualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsumoMensualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsumoMensualActionPerformed

     public void cargarResumenCostoSBasicos(){
        try{
        double total=0;
        if(tbServiciosBasicos.getRowCount()>0){
            tbResumenCostos.setModel(new DefaultTableModel());
            String titulos2[]={"Tipo Sustentación","Valor Total"};
            modelo2=new DefaultTableModel(null,titulos2);
            JTable p2=new JTable(modelo2);

                for (int i = 0; i < tbServiciosBasicos.getRowCount(); i++){    
                    total=total+Double.parseDouble(tbServiciosBasicos.getValueAt(i, 20).toString());
                }
                BigDecimal totalsb = new BigDecimal(total);
                       totalsb = totalsb.setScale(2, BigDecimal.ROUND_HALF_UP);
                String fila[]=new String[2];
            for (int i=0; i<8; i++){
                if(i==0){
                fila[0]="RECURSO HUMANO";
                fila[1]=Costos_Sustentacion.txtTotalPersonal.getText();
                }
                else if(i==1){
                fila[0]="INSUMOS";
                fila[1]=Costos_Sustentacion.txtTotalInsumos.getText();
                } 
                else if(i==2){
                fila[0]="SERVICIOS BÁSICOS";
                fila[1]=String.valueOf(totalsb);
                } 
                else if(i==3){
                fila[0]="EQUIPAMIENTO BÁSICO";
                fila[1]=Costos_Sustentacion.txtTotalHerramienta.getText();
                } 
                else if(i==4){
                fila[0]="INFRAESTRUCTURA";
                fila[1]=Costos_Sustentacion.txtTotalInfraes.getText();;
                } 
                else if(i==5){
                fila[0]="SERVICIOS ADMINISTRATIVOS";
                fila[1]=Costos_Sustentacion.txtTotalSAdminis.getText();;
                } 
                else if(i==6){
                fila[0]="SERVICIOS GENERALES";
                fila[1]=Costos_Sustentacion.txtTotalSGenerales.getText();;
                } 
                else if(i==7){
                fila[0]="GASTOS INTERMEDIOS";
                fila[1]=Costos_Sustentacion.txtTotalSIntermedios.getText();;
                } 
                modelo2.addRow(fila);
                }
                
                Costos_Sustentacion.tbResumenCostos.setModel(modelo2);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(modelo2);
            tbResumenCostos.setRowSorter(elQueOrdenasb);
            tbResumenCostos.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbResumenCostos.getColumnModel().getColumn(1).setPreferredWidth(120);
    
            Costos_Sustentacion.txtTotalSBasicos.setText(String.valueOf(totalsb));
            //Total
            Double insumo,he,per,inf,sg,sa,si;
            he=Double.parseDouble(Costos_Sustentacion.txtTotalHerramienta.getText());
            insumo=Double.parseDouble(Costos_Sustentacion.txtTotalInsumos.getText());
            per=Double.parseDouble(Costos_Sustentacion.txtTotalPersonal.getText());
            inf=Double.parseDouble(Costos_Sustentacion.txtTotalInfraes.getText());
            sa=Double.parseDouble(Costos_Sustentacion.txtTotalSAdminis.getText());
            sg=Double.parseDouble(Costos_Sustentacion.txtTotalSGenerales.getText());
            si=Double.parseDouble(Costos_Sustentacion.txtTotalSIntermedios.getText());
            Double precioTotal=total+he+insumo+per+inf+sa+sg+si;
            BigDecimal ptotal = new BigDecimal(precioTotal);
                        ptotal = ptotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            Costos_Sustentacion.txtPrecioTotal.setText(String.valueOf(ptotal));
            //Ganancia
            double gan=Double.parseDouble(Costos_Sustentacion.txtPrecio.getText())-precioTotal;
             BigDecimal gtotal = new BigDecimal(gan);
                       gtotal = gtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            Costos_Sustentacion.txtGanancia.setText(String.valueOf(gtotal));
            if(gan>0){
               lblGananciaPer.setForeground(Color.black);
                txtGananciaPerdida.setForeground(Color.black);
                       Costos_Sustentacion.lblGananciaPer.setText("Ganancia Total");
            Costos_Sustentacion.txtGananciaPerdida.setText(String.valueOf(gtotal));
            }else{
                double g=-gan;
                BigDecimal gt = new BigDecimal(g);
                       gt = gt.setScale(2, BigDecimal.ROUND_HALF_UP);
                         lblGananciaPer.setForeground(Color.red);
                txtGananciaPerdida.setForeground(Color.red);
                 Costos_Sustentacion.lblGananciaPer.setText("Pérdida Total");
            Costos_Sustentacion.txtGananciaPerdida.setText(String.valueOf(gt));
            }
                }}
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
                }
            }
public void calcula() {
        
        try {        
        
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
    
            
        } catch (Exception e) {
        }
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
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Servicio_Basicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Servicio_Basicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Servicio_Basicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Servicio_Basicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Costos_Sustentacion_Detalle_Servicio_Basicos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BUSCAR_SERVICIOS_VARIOS;
    private javax.swing.JDialog BUSCAR_TIPO_SUSTENTACION;
    private javax.swing.JTable TablaServiciosB;
    private javax.swing.JButton btnBuscarServicios;
    public static javax.swing.JButton btnBuscarSustentacion;
    private javax.swing.JButton btn_agregar_SB;
    private javax.swing.JButton btn_buscar_servicios_SB;
    private javax.swing.JButton btn_buscar_sustentacionSB_enter;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_grabar_SB;
    private javax.swing.JButton btn_quitar_SB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JLabel lblFecha;
    public static javax.swing.JLabel lblHora;
    public static javax.swing.JLabel lblUsu;
    public static javax.swing.JTable tablaServiciosBasicosEA;
    public static javax.swing.JTable tablaTipoSB;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtBaseAsignacionAgua;
    private javax.swing.JTextField txtBaseAsignacionEnergia;
    private javax.swing.JTextField txtBuscarSustentacionSB;
    private javax.swing.JTextField txtCodigo_Servicio;
    private javax.swing.JTextField txtCodigo_Sustentacion;
    private javax.swing.JTextField txtConsultas_Mensuales;
    public static javax.swing.JTextField txtConsumoMensual;
    public static javax.swing.JTextField txtConsumoMensualAgua;
    private javax.swing.JTextField txtPonderacionAgua;
    private javax.swing.JTextField txtPonderacionEnergia;
    private javax.swing.JTextField txtServiciosVariosB;
    private javax.swing.JTextField txtServiciosVariosBuscar;
    public static javax.swing.JTextField txtSumatoriaAguaBA1;
    public static javax.swing.JTextField txtSumatoriaEnergiaBA1;
    public static javax.swing.JTextField txtSumatoria_Principal_A;
    public static javax.swing.JTextField txtSumatoria_Principal_E;
    private javax.swing.JTextField txtTipoSustentacionB;
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
