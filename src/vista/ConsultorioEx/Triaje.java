/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.ConsultorioEx.ConsultorioEXTriaje;
import vista.admisionCentral.FrmNuevaHistoriaC;
import static vista.admisionEmergencia.FrmFormatoEmergencia.buscar_HC;
import static vista.admisionEmergencia.FrmFormatoEmergencia.txtNHCTri;

/**
 *
 * @author MYS1
 */
public class Triaje extends javax.swing.JFrame {
ConsultorioEXTriaje adEmerTr = new ConsultorioEXTriaje();
static ConsultorioEXTriaje movHC = new ConsultorioEXTriaje();
static DefaultTableModel m;
byte tg;
byte tge;
    /**
     * Creates new form Triaje
     */
    public Triaje() {
        initComponents();
        mensaje.setVisible(false);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
       
        tbTriaje.getSelectionModel().setSelectionInterval(0, 0);
        tbTriaje.requestFocus();
        listar();
        btnGuardar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btneditar.setEnabled(false);
        pnlTriaje.setVisible(false);
        jPanel29.setVisible(true);
         btnsubir.setEnabled(false);
        btnbuscar.setEnabled(false);
    
        
         getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        // BOTON ESCAPE (ESC)
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();
            }
        });
        cerrar();
    }
    
        public void listar(){
             adEmerTr.TriajeListarReporte(txtBuscar.getText(), tbTriaje, "","","T");
        }
    public void cerrar (){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    dispose();
                }
});
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void limpiarDatosTriaje(){
        txtNHCTri.setText("");
        txtFC.setText("");
        txtFR.setText("");
        txtPA.setText("");
        txtT.setText("");
        txtPeso.setText("");
        txtTalla.setText("");
        txtIDM.setText("");
        
        txtFC.setEditable(true);
        txtFR.setEditable(true);
        txtPA.setEditable(true);
        txtT.setEditable(true);
        txtPeso.setEditable(true);
        txtTalla.setEditable(true);
        txtIDM.setEditable(true);
    }
     public void habilitarDatosTriaje(){
        txtNHCTri.requestFocus();
        txtNHCTri.setEnabled(true);
        btnFiltrarTri.setEnabled(true);
        txtNHCTri.setText("");
    }
    public void buscarNHC(){
        
            pnlB.setEnabled(true);
            txtBusqueda.setText("");
        
    }
    public void filtrarDatos(){
        dlgBuscarPac.setVisible(true);
        dlgBuscarPac.setLocationRelativeTo(null);//en el centro
        dlgBuscarPac.setResizable(false);
        dlgBuscarPac.getContentPane().setBackground(Color.WHITE);
    }
    
    public void enviarDatosTbPaciente(){
        int fila = tbPaciente.getSelectedRow();
        dlgBuscarPac.dispose();
   
    
            HC.setText(String.valueOf(tbPaciente.getValueAt(fila, 4)));
            txtNHCTri.setText(String.valueOf(tbPaciente.getValueAt(fila, 3)));
            btnGuardar.setEnabled(true);
            
        
    }
    public String determinarFecha(JDateChooser calendario){
        String fecha = "";
        int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);
        int mes = calendario.getCalendar().get(Calendar.MONTH)+1;
        int anio = calendario.getCalendar().get(Calendar.YEAR); 
        if(dia < 10 && mes < 10){
            fecha = String.valueOf("0" + dia + "/" + "0" + mes + "/" + anio);
        }else 
            if(dia < 10 || mes < 10){
                if(dia < 10 && mes >=10){
                    fecha = String.valueOf("0" + dia + "/" + mes + "/" + anio);
                } else 
                    if(dia >= 10 && mes < 10){
                        fecha = String.valueOf(dia + "/" + "0" + mes + "/" + anio);
                    } 
            } else 
                fecha = String.valueOf(dia + "/" + mes + "/" + anio);
        return fecha;
    }
    
    public void BuscarHC(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tbPaciente.setModel(new DefaultTableModel());
             String titulos[]={"Nº H.C.","DNI","Nº H.C.","Paciente","","","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];

            ConsultorioEXTriaje obj=new ConsultorioEXTriaje();
                    consulta="exec CONSULTORIO_EXT_BUSCARHC ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBusqueda.getText());
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


                m.addRow(fila);
                c++;
            }
            tbPaciente.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbPaciente.setRowSorter(elQueOrdena);
            this.tbPaciente.setModel(m);

            formatoTablaBuscar();
         
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
     public void formatoTablaBuscar(){
        tbPaciente.getColumnModel().getColumn(0).setMinWidth(0);
        tbPaciente.getColumnModel().getColumn(0).setMaxWidth(0);
        tbPaciente.getColumnModel().getColumn(1).setPreferredWidth(80);//nhc
        tbPaciente.getColumnModel().getColumn(2).setPreferredWidth(80);//apellidos
        tbPaciente.getColumnModel().getColumn(3).setPreferredWidth(500);//nombres
        tbPaciente.getColumnModel().getColumn(4).setMinWidth(0);
        tbPaciente.getColumnModel().getColumn(4).setMaxWidth(0);
        tbPaciente.getColumnModel().getColumn(5).setMinWidth(0);
        tbPaciente.getColumnModel().getColumn(5).setMaxWidth(0);
        tbPaciente.getColumnModel().getColumn(6).setMinWidth(0);
        tbPaciente.getColumnModel().getColumn(6).setMaxWidth(0);
        tbPaciente.getColumnModel().getColumn(7).setMinWidth(0);
        tbPaciente.getColumnModel().getColumn(7).setMaxWidth(0);
        tbPaciente.setRowHeight(25);
    }
    public void Guardar(){
 if(txtPA.getText().equals("")|| txtFR.getText().equals("")){
            mensaje.setVisible(true);
            mensaje.setBackground(new Color(255,91,70)); 
            men.setText("Asegurese de completar todos los campos");
            b.setVisible(false);
            b1.setVisible(false);
        } else {
                            ConsultorioEXTriaje adEmerTr1 = new ConsultorioEXTriaje();
           
                            adEmerTr1.setTriaje_id("");
                            adEmerTr1.setTriaje_fv_pa(txtPA.getText());
                            adEmerTr1.setTriaje_fv_fc(txtFC.getText());
                            adEmerTr1.setTriaje_fv_t(txtT.getText());
                            adEmerTr1.setTriaje_fv_peso(txtPeso.getText());
                            adEmerTr1.setTriaje_fv_fr(txtFR.getText());
                            adEmerTr1.setTriaje_talla(txtTalla.getText());
                            adEmerTr1.setTRIAJE_IDM(txtIDM.getText());
                            adEmerTr1.setCod_usu(adEmerTr1.codUsuario(lblUsu.getText()));
                            adEmerTr1.setModulo("CEX");
                            adEmerTr1.setId_doc(HC.getText());
                            if(adEmerTr1.mantenimientoCXTriaje("I")==true){
                                mensaje.setVisible(true);
                                mensaje.setBackground(new Color(33,115,70)); 
                                men.setText("Datos Guardados de forma correcta");
                                b.setText("OK");
                                b.setVisible(true);
                                b1.setVisible(false);
                                pnlTriaje.setVisible(false);
                                btnFiltrarTri.setEnabled(false);
                               
                                txtNHCTri.setEnabled(false);
                                txtNHCTri.setText("");
                                limpiarDatosTriaje();
                      
                                btnGuardar.setEnabled(false);
                                btnsubir.setEnabled(false);
                                 btnbuscar.setEnabled(true);
                                tge=1;
                                listar();
                            
                        }else {
                           
                                mensaje.setVisible(true);
                                mensaje.setBackground(new Color(255,91,70)); 
                                men.setText("Ocurrio un error, Verifique");
                                b.setVisible(false);
                                b1.setVisible(false);
                                tge=7;
                        }
                             }
      
    }
    public void Modificar(){
 
                        ConsultorioEXTriaje adEmerTr1 = new ConsultorioEXTriaje();
                        adEmerTr1.setTriaje_id(id.getText());
                        adEmerTr1.setTriaje_fv_pa(txtPA.getText());
                        adEmerTr1.setTriaje_fv_fc(txtFC.getText());
                        adEmerTr1.setTriaje_fv_t(txtT.getText());
                        adEmerTr1.setTriaje_fv_peso(txtPeso.getText());
                        adEmerTr1.setTriaje_fv_fr(txtFR.getText());
                        adEmerTr1.setTriaje_talla(txtTalla.getText());
                        adEmerTr1.setTRIAJE_IDM(txtIDM.getText());
                        if(adEmerTr1.mantenimientoCXTriaje("U")==true){
                                mensaje.setVisible(true);
                                mensaje.setBackground(new Color(33,115,70)); 
                                men.setText("Datos Guardados de forma correcta");
                         
                                b.setText("OK");
                                b.setVisible(true);
                                b1.setVisible(false);
                               pnlTriaje.setVisible(false);
                                btnFiltrarTri.setEnabled(false);
                               
                                txtNHCTri.setEnabled(false);
                                txtNHCTri.setText("");
                                limpiarDatosTriaje();
                      
                                btnGuardar.setEnabled(false);
                                btnsubir.setEnabled(false);
                                btnbuscar.setEnabled(true);
                          tge=1;
                         listar();
                
                            }else {
                           
                                mensaje.setVisible(true);
                                mensaje.setBackground(new Color(255,91,70)); 
                                men.setText("Ocurrio un error, Verifique");
                                b.setVisible(false);
                                b1.setVisible(false);
                                tge=5;
                   
                    }   
    }
    public void Eliminar(){
  
                ConsultorioEXTriaje Tr = new ConsultorioEXTriaje();
                Tr.setTriaje_id(id.getText());
                if(Tr.mantenimientoCXTriaje("E")){
                                mensaje.setBackground(new Color(33,115,70)); 
                                men.setText("Registro Eliminado");
                                b.setText("OK");
                                b1.setVisible(true);
                                b1.setVisible(false);
                                listar();
                                jTabbedPane2.setSelectedIndex(0);
                                tge=9;
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

        dlgBuscarPac = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        pnlB = new javax.swing.JPanel();
        T4 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPaciente = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel13 = new javax.swing.JPanel();
            jLabel16 = new javax.swing.JLabel();
            jLabel17 = new javax.swing.JLabel();
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            btnNuevo = new javax.swing.JButton();
            btneditar = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            btnEliminar = new javax.swing.JButton();
            lblUsu = new javax.swing.JLabel();
            btneliminar1 = new javax.swing.JButton();
            btnbuscar = new javax.swing.JButton();
            btnsubir = new javax.swing.JButton();
            mensaje = new javax.swing.JPanel();
            men = new javax.swing.JLabel();
            b = new javax.swing.JButton();
            b1 = new javax.swing.JButton();
            pnlTriaje = new javax.swing.JPanel();
            jLabel22 = new javax.swing.JLabel();
            jPanel9 = new javax.swing.JPanel();
            txtNHCTri = new javax.swing.JTextField();
            btnFiltrarTri = new javax.swing.JButton();
            jLabel25 = new javax.swing.JLabel();
            txtFR = new javax.swing.JTextField();
            jLabel28 = new javax.swing.JLabel();
            txtPA = new javax.swing.JTextField();
            jLabel26 = new javax.swing.JLabel();
            jLabel27 = new javax.swing.JLabel();
            txtFC = new javax.swing.JTextField();
            jLabel29 = new javax.swing.JLabel();
            txtT = new javax.swing.JTextField();
            jLabel30 = new javax.swing.JLabel();
            txtPeso = new javax.swing.JTextField();
            jLabel3 = new javax.swing.JLabel();
            txtTalla = new javax.swing.JTextField();
            HC = new javax.swing.JLabel();
            id = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            txtIDM = new javax.swing.JTextField();
            jPanel2 = new javax.swing.JPanel();
            jScrollPane3 = new javax.swing.JScrollPane();
            tbTriaje = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel29 = new javax.swing.JPanel();
                fechai = new com.toedter.calendar.JDateChooser();
                fechaf = new com.toedter.calendar.JDateChooser();
                T5 = new javax.swing.JLabel();
                txtBuscar = new javax.swing.JTextField();
                jLabel23 = new javax.swing.JLabel();

                dlgBuscarPac.setAlwaysOnTop(true);
                dlgBuscarPac.setMinimumSize(new java.awt.Dimension(749, 338));
                dlgBuscarPac.setResizable(false);
                dlgBuscarPac.getContentPane().setLayout(null);

                jPanel8.setBackground(new java.awt.Color(0, 153, 102));
                jPanel8.setMinimumSize(new java.awt.Dimension(310, 441));

                jLabel20.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel20.setForeground(new java.awt.Color(255, 255, 255));
                jLabel20.setText("Cliente");

                jLabel14.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
                jLabel14.setForeground(new java.awt.Color(255, 255, 255));
                jLabel14.setText("Busqueda por DNI y H.C.");

                pnlB.setBackground(new java.awt.Color(255, 255, 255));

                T4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                T4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                T4.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        T4MouseClicked(evt);
                    }
                });

                txtBusqueda.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtBusqueda.setBorder(null);
                txtBusqueda.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBusquedaCaretUpdate(evt);
                    }
                });
                txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtBusquedaActionPerformed(evt);
                    }
                });
                txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBusquedaKeyPressed(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtBusquedaKeyTyped(evt);
                    }
                });

                javax.swing.GroupLayout pnlBLayout = new javax.swing.GroupLayout(pnlB);
                pnlB.setLayout(pnlBLayout);
                pnlBLayout.setHorizontalGroup(
                    pnlBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(T4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );
                pnlBLayout.setVerticalGroup(
                    pnlBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(pnlBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(T4))
                        .addGap(4, 4, 4))
                );

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel14)
                            .addComponent(pnlB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(501, Short.MAX_VALUE))
                );
                jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addGap(5, 5, 5)
                        .addComponent(pnlB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel14)
                        .addGap(335, 335, 335))
                );

                dlgBuscarPac.getContentPane().add(jPanel8);
                jPanel8.setBounds(0, 0, 780, 120);

                jPanel10.setBackground(new java.awt.Color(255, 255, 255));

                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                jPanel10.setLayout(jPanel10Layout);
                jPanel10Layout.setHorizontalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 750, Short.MAX_VALUE)
                );
                jPanel10Layout.setVerticalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 70, Short.MAX_VALUE)
                );

                dlgBuscarPac.getContentPane().add(jPanel10);
                jPanel10.setBounds(0, 310, 750, 70);

                jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                jPanel11.setBackground(new java.awt.Color(255, 255, 255));

                jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                jLabel9.setForeground(new java.awt.Color(102, 102, 102));
                jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-100.png"))); // NOI18N
                jLabel9.setText("Busqueda de Historias ");

                javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                jPanel11.setLayout(jPanel11Layout);
                jPanel11Layout.setHorizontalGroup(
                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabel9)
                        .addContainerGap(174, Short.MAX_VALUE))
                );
                jPanel11Layout.setVerticalGroup(
                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel9)
                        .addContainerGap(30, Short.MAX_VALUE))
                );

                jTabbedPane2.addTab("tab2", jPanel11);

                jPanel12.setBackground(new java.awt.Color(255, 255, 255));

                jScrollPane2.setBorder(null);

                tbPaciente.setModel(new javax.swing.table.DefaultTableModel(
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
                tbPaciente.setGridColor(new java.awt.Color(255, 255, 255));
                tbPaciente.setRowHeight(25);
                tbPaciente.setSelectionBackground(new java.awt.Color(0, 153, 102));
                tbPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbPacienteMouseClicked(evt);
                    }
                });
                tbPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbPacienteKeyPressed(evt);
                    }
                });
                jScrollPane2.setViewportView(tbPaciente);

                javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                jPanel12.setLayout(jPanel12Layout);
                jPanel12Layout.setHorizontalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                );
                jPanel12Layout.setVerticalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                );

                jTabbedPane2.addTab("tab2", jPanel12);

                jPanel13.setBackground(new java.awt.Color(255, 255, 255));

                jLabel16.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                jLabel16.setForeground(new java.awt.Color(102, 102, 102));
                jLabel16.setText("No se hallaron coincidencias");

                jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
                jLabel17.setForeground(new java.awt.Color(0, 153, 153));
                jLabel17.setText(":(");

                javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                jPanel13.setLayout(jPanel13Layout);
                jPanel13Layout.setHorizontalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addContainerGap(149, Short.MAX_VALUE))
                );
                jPanel13Layout.setVerticalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jLabel16))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel17)))
                        .addContainerGap(18, Short.MAX_VALUE))
                );

                jTabbedPane2.addTab("tab3", jPanel13);

                dlgBuscarPac.getContentPane().add(jTabbedPane2);
                jTabbedPane2.setBounds(0, 120, 760, 220);

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(0, 153, 102));

                jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("<html>Triaje <span style=\"font-size:'15px'\">Consultorios Externos</span></html>");

                btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                btnNuevo.setMnemonic('N');
                btnNuevo.setContentAreaFilled(false);
                btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnNuevo.setIconTextGap(30);
                btnNuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnNuevoActionPerformed(evt);
                    }
                });

                btneditar.setForeground(new java.awt.Color(240, 240, 240));
                btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
                btneditar.setMnemonic('N');
                btneditar.setContentAreaFilled(false);
                btneditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btneditar.setIconTextGap(30);
                btneditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btneditar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btneditarActionPerformed(evt);
                    }
                });

                btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
                btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
                btnGuardar.setMnemonic('N');
                btnGuardar.setContentAreaFilled(false);
                btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnGuardar.setIconTextGap(30);
                btnGuardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGuardarActionPerformed(evt);
                    }
                });

                btnEliminar.setForeground(new java.awt.Color(240, 240, 240));
                btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
                btnEliminar.setMnemonic('N');
                btnEliminar.setContentAreaFilled(false);
                btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnEliminar.setIconTextGap(30);
                btnEliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnEliminarActionPerformed(evt);
                    }
                });

                lblUsu.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                lblUsu.setForeground(new java.awt.Color(255, 255, 255));
                lblUsu.setText("Usuario");

                btneliminar1.setForeground(new java.awt.Color(240, 240, 240));
                btneliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
                btneliminar1.setMnemonic('N');
                btneliminar1.setToolTipText("");
                btneliminar1.setContentAreaFilled(false);
                btneliminar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btneliminar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btneliminar1.setIconTextGap(30);
                btneliminar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btneliminar1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btneliminar1ActionPerformed(evt);
                    }
                });

                btnbuscar.setForeground(new java.awt.Color(240, 240, 240));
                btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                btnbuscar.setMnemonic('N');
                btnbuscar.setContentAreaFilled(false);
                btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnbuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnbuscar.setIconTextGap(30);
                btnbuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnbuscar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnbuscarActionPerformed(evt);
                    }
                });

                btnsubir.setForeground(new java.awt.Color(240, 240, 240));
                btnsubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Subir-30.png"))); // NOI18N
                btnsubir.setMnemonic('N');
                btnsubir.setContentAreaFilled(false);
                btnsubir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnsubir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnsubir.setIconTextGap(30);
                btnsubir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnsubir.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnsubirActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 496, Short.MAX_VALUE)
                                .addComponent(btneliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblUsu)
                                .addGap(16, 16, 16))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnsubir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btneliminar1)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(lblUsu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btneditar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnEliminar))
                                .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(btnbuscar)
                            .addComponent(btnsubir, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(552, 552, 552))
                );

                mensaje.setBackground(new java.awt.Color(33, 115, 70));

                men.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                men.setForeground(new java.awt.Color(255, 255, 255));
                men.setText("Desea Actualizar el Registro ?");

                b.setForeground(new java.awt.Color(240, 240, 240));
                b.setText("Si");
                b.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                b.setContentAreaFilled(false);
                b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                b.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                b.setIconTextGap(30);
                b.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        bActionPerformed(evt);
                    }
                });

                b1.setForeground(new java.awt.Color(240, 240, 240));
                b1.setText("No");
                b1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                b1.setContentAreaFilled(false);
                b1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                b1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                b1.setIconTextGap(30);
                b1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        b1ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout mensajeLayout = new javax.swing.GroupLayout(mensaje);
                mensaje.setLayout(mensajeLayout);
                mensajeLayout.setHorizontalGroup(
                    mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mensajeLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(men)
                        .addGap(46, 46, 46)
                        .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                mensajeLayout.setVerticalGroup(
                    mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mensajeLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(men)
                            .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                pnlTriaje.setBackground(new java.awt.Color(255, 255, 255));

                jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel22.setForeground(new java.awt.Color(51, 51, 51));
                jLabel22.setText("Paciente");

                jPanel9.setBackground(new java.awt.Color(255, 255, 255));
                jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtNHCTri.setEditable(false);
                txtNHCTri.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtNHCTri.setForeground(new java.awt.Color(51, 51, 51));
                txtNHCTri.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtNHCTri.setBorder(null);
                txtNHCTri.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtNHCTriCaretUpdate(evt);
                    }
                });

                btnFiltrarTri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnFiltrarTri.setMnemonic('B');
                btnFiltrarTri.setToolTipText("Buscar Nª H.C. (Alt + B)");
                btnFiltrarTri.setContentAreaFilled(false);
                btnFiltrarTri.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnFiltrarTri.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnFiltrarTriActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                jPanel9.setLayout(jPanel9Layout);
                jPanel9Layout.setHorizontalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtNHCTri, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnFiltrarTri, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                );
                jPanel9Layout.setVerticalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNHCTri, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(btnFiltrarTri, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                jLabel25.setForeground(new java.awt.Color(51, 51, 51));
                jLabel25.setText("Funciones Vitales_____________________________________________________________________________________");

                txtFR.setEditable(false);
                txtFR.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtFRKeyPressed(evt);
                    }
                });

                jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel28.setForeground(new java.awt.Color(51, 51, 51));
                jLabel28.setText("FR");

                txtPA.setEditable(false);
                txtPA.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtPAKeyPressed(evt);
                    }
                });

                jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel26.setForeground(new java.awt.Color(51, 51, 51));
                jLabel26.setText("PA");

                jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel27.setForeground(new java.awt.Color(51, 51, 51));
                jLabel27.setText("FC");

                txtFC.setEditable(false);
                txtFC.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtFCKeyPressed(evt);
                    }
                });

                jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel29.setForeground(new java.awt.Color(51, 51, 51));
                jLabel29.setText("Tº");

                txtT.setEditable(false);
                txtT.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtTKeyPressed(evt);
                    }
                });

                jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel30.setForeground(new java.awt.Color(51, 51, 51));
                jLabel30.setText("Peso");

                txtPeso.setEditable(false);
                txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtPesoKeyPressed(evt);
                    }
                });

                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(51, 51, 51));
                jLabel3.setText("Talla");

                txtTalla.setEditable(false);
                txtTalla.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtTallaKeyPressed(evt);
                    }
                });

                HC.setForeground(new java.awt.Color(255, 255, 255));
                HC.setText("jLabel2");

                id.setForeground(new java.awt.Color(255, 255, 255));
                id.setText("jLabel2");

                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                jLabel4.setText("IDM");

                txtIDM.setEditable(false);
                txtIDM.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtIDMKeyPressed(evt);
                    }
                });

                javax.swing.GroupLayout pnlTriajeLayout = new javax.swing.GroupLayout(pnlTriaje);
                pnlTriaje.setLayout(pnlTriajeLayout);
                pnlTriajeLayout.setHorizontalGroup(
                    pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTriajeLayout.createSequentialGroup()
                        .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTriajeLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(HC)
                                .addGap(198, 198, 198)
                                .addComponent(id)
                                .addGap(60, 60, 60))
                            .addGroup(pnlTriajeLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
                                    .addGroup(pnlTriajeLayout.createSequentialGroup()
                                        .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlTriajeLayout.createSequentialGroup()
                                                .addComponent(jLabel22)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pnlTriajeLayout.createSequentialGroup()
                                                .addComponent(jLabel26)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pnlTriajeLayout.createSequentialGroup()
                                                        .addGap(80, 80, 80)
                                                        .addComponent(jLabel28)
                                                        .addGap(36, 36, 36)
                                                        .addComponent(txtFR, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(txtPA, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel27)
                                                .addGap(31, 31, 31)
                                                .addComponent(txtFC, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel29)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(jLabel30)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtIDM, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())
                );
                pnlTriajeLayout.setVerticalGroup(
                    pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTriajeLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(txtPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28)
                                .addComponent(jLabel27)
                                .addComponent(txtFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel29)
                                .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel30)
                                .addComponent(jLabel3)
                                .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(txtIDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(HC)
                            .addComponent(id))
                        .addGap(260, 260, 260))
                );

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                jScrollPane3.setBorder(null);

                tbTriaje.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
                tbTriaje.setGridColor(new java.awt.Color(255, 255, 255));
                tbTriaje.setRowHeight(25);
                tbTriaje.setSelectionBackground(new java.awt.Color(0, 153, 102));
                tbTriaje.getTableHeader().setReorderingAllowed(false);
                tbTriaje.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbTriajeMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbTriajeMousePressed(evt);
                    }
                });
                tbTriaje.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbTriajeKeyPressed(evt);
                    }
                });
                jScrollPane3.setViewportView(tbTriaje);

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                        .addContainerGap())
                );

                jPanel29.setBackground(new java.awt.Color(255, 255, 255));

                fechai.setBackground(new java.awt.Color(255, 255, 255));
                fechai.setDateFormatString("dd-MM-yyyy");

                fechaf.setBackground(new java.awt.Color(255, 255, 255));
                fechaf.setDateFormatString("dd-MM-yyyy");

                T5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                T5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                T5.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        T5MouseClicked(evt);
                    }
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        T5MouseEntered(evt);
                    }
                });

                txtBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel23.setForeground(new java.awt.Color(51, 51, 51));
                jLabel23.setText(" Busqueda");

                javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
                jPanel29.setLayout(jPanel29Layout);
                jPanel29Layout.setHorizontalGroup(
                    jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel23)
                        .addGap(5, 5, 5)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechai, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaf, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(T5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                );
                jPanel29Layout.setVerticalGroup(
                    jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(T5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechaf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mensaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTriaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
      
            
            btnEliminar.setEnabled(false);
            pnlTriaje.setVisible(true);
            btnsubir.setEnabled(true);
            jPanel29.setVisible(false);
            habilitarDatosTriaje();
            limpiarDatosTriaje();
            
             filtrarDatos();
     
            tbPaciente.getSelectionModel().setSelectionInterval(0,0);
            buscarNHC();
            tg=1;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
tg=2;
 btnGuardar.setEnabled(true);
 pnlTriaje.setVisible(true);
  jPanel29.setVisible(false);
        btneditar.setEnabled(false);
        txtFC.setEditable(true);
        txtFR.setEditable(true);
        txtPA.setEditable(true);
        txtPeso.setEditable(true);
        txtT.setEditable(true);
        txtTalla.setEditable(true);
        txtIDM.setEditable(true);

    }//GEN-LAST:event_btneditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    if(tg==1){
             Guardar();  
        }
        if(tg==2){
           mensaje.setVisible(true);
           mensaje.setBackground(new Color(255,153,51)); 
           men.setText("Desea Actualizar el Registro ?");
           b.setText("Si");
           b.setVisible(true);
           b1.setVisible(true); 
           tge=2;
        }       
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        mensaje.setVisible(true);
        mensaje.setBackground(new Color(255,91,70)); 
        men.setText("Desea Eliminar este registro?");
        b.setText("Si");
        b.setVisible(true);
        b1.setVisible(true);
        tge=8;
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btneliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneliminar1ActionPerformed

    private void txtNHCTriCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNHCTriCaretUpdate
//        if(lblNewMod.getText().equals("N")){
//            if(txtNHCTri.getText().length()==7){
//                adEmerCab.mostrarHCTriaje(formatoNHC(txtNHCTri.getText()));
//                pnlTriaje.setVisible(false);
//                adEmerCab.cargarFormatEmer(lblIDHCTr.getText(),"",tbFormatosEmer);
//                AdmisionEmergenciaTriaje ad = new AdmisionEmergenciaTriaje();
//                if(tbFormatosEmer.getRowCount()!=0){
//                    if(lblPestana.getText().equals("TR") || tbPaneles.getSelectedIndex()==1)
//                    lblPestanaMod.setText("TR");
//                    dlgModemergencia.setVisible(true);
//                    dlgModemergencia.setLocationRelativeTo(null);//en el centro
//                    dlgModemergencia.setResizable(false);
//                    dlgModemergencia.getContentPane().setBackground(Color.WHITE);
//                } else {
//                    JOptionPane.showMessageDialog(this,"No hay registros");
//                }
//            }
//            else{
//                lblIDHCTr.setText("");
//                lblPaciente.setText("");
//                txtIDTriaje.setText("");
//                pnlTriaje.setVisible(false);
//            }
//        } else { //MODIFICAR
//            if(txtNHCTri.getText().length()==7){
//                adEmerCab.mostrarHCTriaje(formatoNHC(txtNHCTri.getText()));
//                //pnlTriaje.setVisible(true);
//                adEmerCab.cargarFormatEmer(lblIDHCTr.getText(),"",tbFormatosEmer);
//                adEmerTr.admisionEmergenciaTriajeListar(lblIDHCTr.getText(),"" , tbModifTriaje,"A");
//                if(tbModifTriaje.getRowCount()!=0){
//                    dlgModTriaje.setVisible(true);
//                    dlgModTriaje.setLocationRelativeTo(null);//en el centro
//                    dlgModTriaje.setResizable(false);
//                    dlgModTriaje.getContentPane().setBackground(Color.WHITE);
//                } else {
//                    dlgModTriaje.setVisible(false);
//                    JOptionPane.showMessageDialog(this,"No hay registros");
//                    txtNHCTri.setText("");
//                }
//            }
//            else{
//                lblIDHCTr.setText("");
//                lblPaciente.setText("");
//                txtIDTriaje.setText("");
//                pnlTriaje.setVisible(false);
//            }
//        }
    }//GEN-LAST:event_txtNHCTriCaretUpdate

    private void btnFiltrarTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarTriActionPerformed
        filtrarDatos();
     
        tbPaciente.getSelectionModel().setSelectionInterval(0,0);
         buscarNHC();     
    }//GEN-LAST:event_btnFiltrarTriActionPerformed

    private void tbTriajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriajeMouseClicked

        int fila=tbTriaje.getSelectedRow();
        if(evt.getClickCount()==1){
       
            txtFC.setText(String.valueOf(tbTriaje.getValueAt(fila, 8)));
            txtFR.setText(String.valueOf(tbTriaje.getValueAt(fila, 9)));
            txtPA.setText(String.valueOf(tbTriaje.getValueAt(fila, 10)));
            txtPeso.setText(String.valueOf(tbTriaje.getValueAt(fila, 11)));
            txtT.setText(String.valueOf(tbTriaje.getValueAt(fila, 12)));
            txtTalla.setText(String.valueOf(tbTriaje.getValueAt(fila, 13)));
            txtIDM.setText(String.valueOf(tbTriaje.getValueAt(fila, 14)));
            id.setText(String.valueOf(tbTriaje.getValueAt(fila, 0)));
            txtNHCTri.setText(String.valueOf(tbTriaje.getValueAt(fila, 4)));
        txtFC.setEditable(false);
        txtFR.setEditable(false);
        txtPA.setEditable(false);
        txtT.setEditable(false);
        txtPeso.setEditable(false);
        txtTalla.setEditable(false);
        txtIDM.setEditable(false);
        }
        if(evt.getClickCount()==2){

        btnGuardar.setEnabled(false);
        btnEliminar.setEnabled(true);
        btneditar.setEnabled(true);
        pnlTriaje.setVisible(true);
        jPanel29.setVisible(false);
        btnsubir.setEnabled(true);
        txtFC.setEditable(false);
        txtFR.setEditable(false);
        txtPA.setEditable(false);
        txtPeso.setEditable(false);
        txtT.setEditable(false);
        txtTalla.setEditable(false);
        txtIDM.setEditable(false);
        }
        

        
        
    }//GEN-LAST:event_tbTriajeMouseClicked

    private void tbTriajeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriajeMousePressed

    }//GEN-LAST:event_tbTriajeMousePressed

    private void tbTriajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTriajeKeyPressed
 
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tbTriaje.getSelectedRow();
            txtFC.setText(String.valueOf(tbTriaje.getValueAt(fila, 8)));
            txtFR.setText(String.valueOf(tbTriaje.getValueAt(fila, 9)));
            txtPA.setText(String.valueOf(tbTriaje.getValueAt(fila, 10)));
            txtPeso.setText(String.valueOf(tbTriaje.getValueAt(fila, 11)));
            txtT.setText(String.valueOf(tbTriaje.getValueAt(fila, 12)));
            txtTalla.setText(String.valueOf(tbTriaje.getValueAt(fila, 13)));
            txtIDM.setText(String.valueOf(tbTriaje.getValueAt(fila, 14)));
            id.setText(String.valueOf(tbTriaje.getValueAt(fila, 0)));
            txtNHCTri.setText(String.valueOf(tbTriaje.getValueAt(fila, 4)));
            
        }
    }//GEN-LAST:event_tbTriajeKeyPressed

    private void T5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T5MouseClicked
        adEmerTr.TriajeListarReporte(txtBuscar.getText(),tbTriaje,determinarFecha(fechai),determinarFecha(fechaf),"T");
        txtBuscar.setEnabled(true);
    }//GEN-LAST:event_T5MouseClicked

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
  if (tge==3 || tge==1 || tge==9){
   mensaje.setVisible(false);  

   }
        
        if (tge==2){
        Modificar();

        btneditar.setEnabled(false);
        btnEliminar.setEnabled(false);

   }  
     if (tge==8){
        Eliminar();
        btnGuardar.setEnabled(false);
        btneditar.setEnabled(false);
        btnEliminar.setEnabled(false);
   } 
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        mensaje.setVisible(false);
    }//GEN-LAST:event_b1ActionPerformed

    private void T4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T4MouseClicked

    }//GEN-LAST:event_T4MouseClicked

    private void txtBusquedaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBusquedaCaretUpdate
        jTabbedPane2.setSelectedIndex(1);
        BuscarHC();  
        if (tbPaciente.getRowCount() == 0){
            jTabbedPane2.setSelectedIndex(2);
        }
        if (txtBusqueda.getText().length()==0){
            jTabbedPane2.setSelectedIndex(0);
        }
    }//GEN-LAST:event_txtBusquedaCaretUpdate

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
            tbPaciente.getSelectionModel().setSelectionInterval (0,0) ;
            tbPaciente.requestFocus();
        }
    }//GEN-LAST:event_txtBusquedaKeyPressed

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
       
    }//GEN-LAST:event_txtBusquedaKeyTyped

    private void tbPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacienteMouseClicked
   if(evt.getClickCount()==2){
            enviarDatosTbPaciente();
        }
    }//GEN-LAST:event_tbPacienteMouseClicked

    private void tbPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacienteKeyPressed
          if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbPaciente.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBusqueda.requestFocus();
            tbPaciente.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosTbPaciente();
        }
    }//GEN-LAST:event_tbPacienteKeyPressed

    private void txtPAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPAKeyPressed
          char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            
        txtFR.requestFocus();
        
          } 
    }//GEN-LAST:event_txtPAKeyPressed

    private void txtFCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFCKeyPressed
            char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            
        txtT.requestFocus();
        
          } 
    }//GEN-LAST:event_txtFCKeyPressed

    private void txtFRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFRKeyPressed
           char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            
        txtFC.requestFocus();
        
          } 
    }//GEN-LAST:event_txtFRKeyPressed

    private void txtTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTKeyPressed
           char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            
        txtPeso.requestFocus();
        
          } 
    }//GEN-LAST:event_txtTKeyPressed

    private void txtPesoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyPressed
               char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            
        txtTalla.requestFocus();
        
          } 
    }//GEN-LAST:event_txtPesoKeyPressed

    private void txtTallaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaKeyPressed
//       char teclaPresionada = evt.getKeyChar();
//        if(teclaPresionada==KeyEvent.VK_ENTER){
//             if(tg==1){
//             Guardar();  
//             TotalAbonos();
//             suma();
//        }
//        if(tg==2){
//           cargareliminar.setVisible(true);
//           cargareliminar.setBackground(new Color(255,153,51)); 
//           Mensaje.setText("Desea Actualizar el Registro ?");
//           eli.setText("Si");
//           eli.setVisible(true);
//           noeli.setVisible(true); 
//           tge=2;
//        }       
//          } 
    }//GEN-LAST:event_txtTallaKeyPressed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        jPanel29.setVisible(true);
        txtBuscar.requestFocus();
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnsubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsubirActionPerformed
        pnlTriaje.setVisible(false);
        btnsubir.setEnabled(false);
        btnbuscar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btneditar.setEnabled(false);
    }//GEN-LAST:event_btnsubirActionPerformed

    private void txtIDMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDMKeyPressed

    private void T5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_T5MouseEntered

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
            java.util.logging.Logger.getLogger(Triaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Triaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Triaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Triaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Triaje().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HC;
    private javax.swing.JLabel T4;
    private javax.swing.JLabel T5;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFiltrarTri;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar1;
    private javax.swing.JButton btnsubir;
    private javax.swing.JDialog dlgBuscarPac;
    private com.toedter.calendar.JDateChooser fechaf;
    private com.toedter.calendar.JDateChooser fechai;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    private javax.swing.JPanel pnlB;
    private javax.swing.JPanel pnlTriaje;
    private javax.swing.JTable tbPaciente;
    private javax.swing.JTable tbTriaje;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBusqueda;
    public static javax.swing.JTextField txtFC;
    public static javax.swing.JTextField txtFR;
    public static javax.swing.JTextField txtIDM;
    public static javax.swing.JTextField txtNHCTri;
    public static javax.swing.JTextField txtPA;
    public static javax.swing.JTextField txtPeso;
    public static javax.swing.JTextField txtT;
    public static javax.swing.JTextField txtTalla;
    // End of variables declaration//GEN-END:variables
}
