/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;

import servicios.Conexion;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import modelos.Caja.Caja_CPT;
import modelos.Caja.Caja_Cta6;

/**
 *
 * @author MYS1
 */
public class Caja_CPTS extends javax.swing.JFrame {
Conexion c=new Conexion();
Connection conexion=c.conectar();
byte tg;
byte tgm;
ResultSet r;
Caja_CPT nuevaR = new Caja_CPT();
    /**
     * Creates new form Caja_CPTS
     */
    public Caja_CPTS() {
        initComponents();
        Caja_CPT A = new Caja_CPT();
        Caja_CPT U = new Caja_CPT();
        Caja_CPT N = new Caja_CPT();
        A.LISTA_CPT("",tb_CPT);
        U.LISTAR_UIT();
//        N.LISTAR_PERMISOS(lblusu.getText());
        cargareliminar.setVisible(false);
//        this.setExtendedState(MAXIMIZED_BOTH);
        Cta6.setLocationRelativeTo(null);//en el centro
        NivelSuperior.setLocationRelativeTo(null);//en el centro
        LIMPIAR();
        HABILITAR(false);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        eli1.setVisible(false);
        cbxGrupo.setBackground(Color.WHITE);
        Paginas.setEnabled(false);
        Paginas.setEnabledAt(0,false);
        Paginas.setEnabledAt(1, false);
        this.cbxGrupo.setModel(CargarGrupo());
        panelH.setVisible(false);
        panelV.setVisible(false);
        save.setVisible(false);
        cancel.setVisible(false);
    }
    
        public void LIMPIAR(){
        txtct6.setText("");
        txtGrupo.setText("");
        txtGrupoDes.setText("");
        txtCPTDES.setText("");
        txtCPT_COMPLETO.setText("");
        txtNombre.setText("");
        txtPORCENTAJE.setText("");
        txtBASE_LEGAL.setText("");
        jLabel19.setText("");
//        cbxTipoDoc.getSelectedItem().toString());
    }
    
    public void HABILITAR (boolean opcion){
        txtct6.setEditable(opcion);
//        txtGrupo.setEditable(opcion);
        txtGrupoDes.setEditable(opcion);
        txtCPTDES.setEditable(opcion);
        cbxGrupo.setEnabled(opcion);
        txtCPT_COMPLETO.setEditable(opcion);
        txtNombre.setEditable(opcion);
        txtPORCENTAJE.setEditable(opcion);
        txtBASE_LEGAL.setEditable(opcion);
    }
    public void RESTAURAR_BARRA(){
        save.setVisible(false);
        cancel.setVisible(false);
        eli1.setVisible(false);
        txtPrecio_Base.setEditable(false);
        jPanel5.setBackground(new Color(230,230,230));
        jPanel4.setBackground(new Color(230,230,230));
        txtPrecio_Base.setBackground(new Color(230,230,230));
        jLabel33.setForeground(new Color(51,51,51));
        lblDocumento1.setForeground(new Color(51,51,51));
        txtPrecio_Base.setForeground(new Color(51,51,51));
        eli1.setForeground(new Color(51,51,51));
        save.setForeground(new Color(51,51,51));
        cancel.setForeground(new Color(51,51,51));
        ImageIcon CambioS=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Save-22.png"));
        save.setIcon(CambioS);
        ImageIcon CambioC=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Long Arrow Left-22.png"));
        cancel.setIcon(CambioC);
        lblEditar.setText("0");
        jLabel33.setText("Listado");
    }
    
    public void CARGAR(){
        RESTAURAR_BARRA();
        panelCPT2.setVisible(false);
        panelCPT3.setVisible(false);
        panelTUPA_COMPLETO.setVisible(true);
        int fila=tb_CPT.getSelectedRow();
        lblIDCPT.setText(String.valueOf(tb_CPT.getValueAt(fila, 0)));  
        cbxGrupo.setSelectedItem(String.valueOf(tb_CPT.getValueAt(fila, 1))); 
        txtct6.setText(String.valueOf(tb_CPT.getValueAt(fila, 2))); 
        lblIDGRUPO.setText(String.valueOf(tb_CPT.getValueAt(fila, 4)));  
        lblIDCTA6.setText(String.valueOf(tb_CPT.getValueAt(fila, 5))); 
        txtGrupo.setText(String.valueOf(tb_CPT.getValueAt(fila, 7))); 
        txtCPT_COMPLETO.setText(String.valueOf(tb_CPT.getValueAt(fila, 9)));  
        txtCPTDES.setText(String.valueOf(tb_CPT.getValueAt(fila, 11))); 
        txtNombre.setText(String.valueOf(tb_CPT.getValueAt(fila, 10))); 
        txtPORCENTAJE.setText(String.valueOf(tb_CPT.getValueAt(fila, 12))); 
        txtBASE_LEGAL.setText(String.valueOf(tb_CPT.getValueAt(fila, 13))); 
        
        double UIT=0.00,P=0.00,T=0.00;
        UIT=Double.parseDouble(txtPrecio_Base.getText());
        P=Double.parseDouble(txtPORCENTAJE.getText());
        T=UIT*P/100;
        BigDecimal bd4 = new BigDecimal(T);
        bd4 = bd4.setScale(2, BigDecimal.ROUND_HALF_UP);
        jLabel19.setText(String.valueOf(bd4));
    }
    
        public void NUEVO_REGISTRO(){
       if((txtGrupoDes.getText().equals(""))){
            cargareliminar.setVisible(true);        
            cargareliminar.setBackground(new Color(255,91,70)); 
            Mensaje.setText("Debe completar los campos requeridos");
            eli.setVisible(true);
            noeli.setVisible(false);
            tgm=0;                 
        } else {
                Caja_CPT cno1 = new Caja_CPT();
                cno1.setID_GRUPO(Integer.parseInt(lblIDGRUPO.getText()));
                cno1.setID_Cuenta7(Integer.parseInt(lblIDCTA6.getText()));
                cno1.setNRO_ITEM(txtGrupo.getText()+""+txtGrupoDes.getText());
                cno1.setNOMBRE(txtNombre.getText());
                cno1.setDESCRIPCION(txtCPTDES.getText());
                cno1.setBASE_LEGAL(txtBASE_LEGAL.getText());
                cno1.setPORCENTAJE(txtPORCENTAJE.getText());
                cno1.setUSUARIO(lblusu.getText());
                cno1.setID_CPT(0);
                    if(cno1.NUEVO_CPT()==true){
                            cargareliminar.setVisible(true);
                            cargareliminar.setBackground(new Color(0,153,102)); 
                            Mensaje.setText("Datos Guardados de forma correcta");
                            eli.setText("OK");
                            eli.setVisible(true);
                            noeli.setVisible(false);
                            tgm=1;
                            btnguardar.setEnabled(false);
                            btneditar.setEnabled(false);
                            btneliminar.setEnabled(true);
                            Caja_CPT A = new Caja_CPT();
                            A.LISTA_CPT("",tb_CPT);
                            jLabel33.setText("Listado");
                            Paginas.setSelectedIndex(0);
                    } else {
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(255,91,70)); 
                        Mensaje.setText("Ocurrio un error, Verifique");
                        eli.setVisible(false);
                        noeli.setVisible(false);
                    }
        }                 
    }
        
    public void MODIFICAR_REGISTRO(){
                Caja_CPT cno1 = new Caja_CPT();
                cno1.setID_CPT(Integer.parseInt(lblIDCPT.getText()));
                cno1.setID_GRUPO(Integer.parseInt(lblIDGRUPO.getText()));
                cno1.setID_Cuenta7(Integer.parseInt(lblIDCTA6.getText()));
                cno1.setNRO_ITEM(txtCPT_COMPLETO.getText());
                cno1.setNOMBRE(txtNombre.getText());
                cno1.setDESCRIPCION(txtCPTDES.getText());
                cno1.setBASE_LEGAL(txtBASE_LEGAL.getText());
                cno1.setPORCENTAJE(txtPORCENTAJE.getText());
                cno1.setUSUARIO(lblusu.getText());
                    if(cno1.MODIFICAR_CPT()==true){
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(0,153,102)); 
                        Mensaje.setText("Datos Actualizados de forma correcta");
                        eli.setText("OK");
                        eli.setVisible(true);
                        noeli.setVisible(false);
                        tgm=2;
                        btnguardar.setEnabled(false);
                        btneditar.setEnabled(false);
                        btneliminar.setEnabled(true);
                        Caja_CPT A = new Caja_CPT();
                        A.LISTA_CPT("",tb_CPT);
                        jLabel33.setText("Listado");
                        Paginas.setSelectedIndex(0);
                    } else {
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(255,91,70)); 
                        Mensaje.setText("Ocurrio un error, Verifique");
                        eli.setVisible(false);
                        noeli.setVisible(false);
                    }               
    }
    
    public void MODIFICAR_UIT(){
                Caja_CPT cno1 = new Caja_CPT();
                cno1.setPRECIO(Double.parseDouble(txtPrecio_Base.getText()));
                    if(cno1.MODIFICAR_UIT()==true){
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(0,153,102)); 
                        Mensaje.setText("Se cambió el valor de la UIT, y se Actualizó la Lista de Precios");
                        eli.setText("OK");
                        eli.setVisible(true);
                        noeli.setVisible(false);
                        Caja_CPT U = new Caja_CPT();
                        U.LISTAR_UIT();
                        jLabel33.setText("Listado");
                        buscartodo.setText("");
                        Caja_CPT A = new Caja_CPT();
                        A.LISTA_CPT("",tb_CPT);
                        Paginas.setSelectedIndex(0);
                        RESTAURAR_BARRA();
                    } else {
                        cargareliminar.setVisible(true);
                        cargareliminar.setBackground(new Color(255,91,70)); 
                        Mensaje.setText("Ocurrio un error, Verifique");
                        eli.setVisible(false);
                        noeli.setVisible(false);
                    }               
    }
    
    public void ELIMINAR_CPT(){ 
        try{
                Caja_CPT hCEl = new Caja_CPT();
                hCEl.setID_CPT(Integer.parseInt(lblIDCPT.getText()));
                if(hCEl.ELIMINAR_CPT()){
                    cargareliminar.setBackground(new Color(0,153,102)); 
                    Mensaje.setText("Registro Eliminado");
                    eli.setText("OK");
                    eli.setVisible(true);
                    noeli.setVisible(false);
                    tgm=9;
                    Caja_CPT A = new Caja_CPT();
                    A.LISTA_CPT("",tb_CPT);
                    jLabel33.setText("Listado");
                    Paginas.setSelectedIndex(0);
                    
                }
        }catch(Exception e){
            System.out.println("Error: " + e.toString());
        }   
      }
    
    public DefaultComboBoxModel CargarGrupo(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = conexion.createStatement();
              r = st.executeQuery ("CAJA_GRUPO_DESCRIPCION"); 
              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "DESCRIPCION" ) );                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "ERROR AL CARGAR :" + ex.getMessage() );
        }        
        return listmodel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cta6 = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        txtBuscar2 = new javax.swing.JTextField();
        btnBuscarPaciente2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_Grupos2 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            NivelSuperior = new javax.swing.JDialog();
            jPanel145 = new javax.swing.JPanel();
            jLabel64 = new javax.swing.JLabel();
            jPanel146 = new javax.swing.JPanel();
            btnAlertConsulta10 = new javax.swing.JButton();
            jLabel67 = new javax.swing.JLabel();
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            btnNuevo = new javax.swing.JButton();
            btneditar = new javax.swing.JButton();
            btnguardar = new javax.swing.JButton();
            btneliminar = new javax.swing.JButton();
            lblusu = new javax.swing.JLabel();
            jPanel23 = new javax.swing.JPanel();
            buscartodo = new javax.swing.JTextField();
            btnBuscarPaciente = new javax.swing.JButton();
            lbldetalle = new javax.swing.JLabel();
            btnLista = new javax.swing.JButton();
            btnImprimir = new javax.swing.JButton();
            panelH = new javax.swing.JPanel();
            lblH = new javax.swing.JLabel();
            panelV = new javax.swing.JPanel();
            lblV = new javax.swing.JLabel();
            lblCrick = new javax.swing.JLabel();
            lblNivel = new javax.swing.JLabel();
            lblPermiso = new javax.swing.JLabel();
            lblEditar = new javax.swing.JLabel();
            jPanel5 = new javax.swing.JPanel();
            jLabel33 = new javax.swing.JLabel();
            jPanel4 = new javax.swing.JPanel();
            eli1 = new javax.swing.JButton();
            lblDocumento1 = new javax.swing.JLabel();
            txtPrecio_Base = new javax.swing.JTextField();
            save = new javax.swing.JButton();
            cancel = new javax.swing.JButton();
            cargareliminar = new javax.swing.JPanel();
            Mensaje = new javax.swing.JLabel();
            eli = new javax.swing.JButton();
            noeli = new javax.swing.JButton();
            Paginas = new javax.swing.JTabbedPane();
            jPanel2 = new javax.swing.JPanel();
            jScrollPane3 = new javax.swing.JScrollPane();
            tb_CPT = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel3 = new javax.swing.JPanel();
                jLabel11 = new javax.swing.JLabel();
                cbxGrupo = new javax.swing.JComboBox();
                jLabel12 = new javax.swing.JLabel();
                panelCPT1 = new javax.swing.JPanel();
                txtct6 = new javax.swing.JTextField();
                b1 = new javax.swing.JButton();
                jLabel13 = new javax.swing.JLabel();
                panelCPT2 = new javax.swing.JPanel();
                txtGrupo = new javax.swing.JTextField();
                panelCPT3 = new javax.swing.JPanel();
                txtGrupoDes = new javax.swing.JTextField();
                jScrollPane1 = new javax.swing.JScrollPane();
                txtCPTDES = new javax.swing.JEditorPane();
                jLabel14 = new javax.swing.JLabel();
                lblIDGRUPO = new javax.swing.JLabel();
                lblIDCTA6 = new javax.swing.JLabel();
                lblIDCPT = new javax.swing.JLabel();
                panelTUPA_COMPLETO = new javax.swing.JPanel();
                txtCPT_COMPLETO = new javax.swing.JTextField();
                jLabel15 = new javax.swing.JLabel();
                panelCPT5 = new javax.swing.JPanel();
                txtNombre = new javax.swing.JTextField();
                jLabel16 = new javax.swing.JLabel();
                panelCPT6 = new javax.swing.JPanel();
                txtPORCENTAJE = new javax.swing.JTextField();
                jLabel18 = new javax.swing.JLabel();
                jLabel19 = new javax.swing.JLabel();
                jLabel20 = new javax.swing.JLabel();
                jScrollPane2 = new javax.swing.JScrollPane();
                txtBASE_LEGAL = new javax.swing.JEditorPane();

                Cta6.setAlwaysOnTop(true);
                Cta6.setMinimumSize(new java.awt.Dimension(700, 422));
                Cta6.setResizable(false);

                jPanel8.setBackground(new java.awt.Color(230, 230, 230));

                jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 30)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(102, 102, 102));
                jLabel4.setText("Cuenta Contable");

                jPanel30.setBackground(new java.awt.Color(255, 255, 255));

                txtBuscar2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtBuscar2.setBorder(null);
                txtBuscar2.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscar2CaretUpdate(evt);
                    }
                });
                txtBuscar2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtBuscar2ActionPerformed(evt);
                    }
                });
                txtBuscar2.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscar2KeyPressed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                jPanel30.setLayout(jPanel30Layout);
                jPanel30Layout.setHorizontalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 5, Short.MAX_VALUE))
                );
                jPanel30Layout.setVerticalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );

                btnBuscarPaciente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarPaciente2.setContentAreaFilled(false);
                btnBuscarPaciente2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarPaciente2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarPaciente2ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(414, Short.MAX_VALUE))
                );
                jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(410, 410, 410))
                );

                jScrollPane5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                tb_Grupos2.setModel(new javax.swing.table.DefaultTableModel(
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
                tb_Grupos2.setGridColor(new java.awt.Color(255, 255, 255));
                tb_Grupos2.setRowHeight(25);
                tb_Grupos2.setSelectionBackground(new java.awt.Color(102, 102, 102));
                tb_Grupos2.getTableHeader().setReorderingAllowed(false);
                tb_Grupos2.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_Grupos2MouseClicked(evt);
                    }
                });
                tb_Grupos2.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_Grupos2KeyPressed(evt);
                    }
                });
                jScrollPane5.setViewportView(tb_Grupos2);

                javax.swing.GroupLayout Cta6Layout = new javax.swing.GroupLayout(Cta6.getContentPane());
                Cta6.getContentPane().setLayout(Cta6Layout);
                Cta6Layout.setHorizontalGroup(
                    Cta6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                );
                Cta6Layout.setVerticalGroup(
                    Cta6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Cta6Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                        .addContainerGap())
                );

                NivelSuperior.setAlwaysOnTop(true);
                NivelSuperior.setMinimumSize(new java.awt.Dimension(430, 200));
                NivelSuperior.setResizable(false);

                jPanel145.setBackground(new java.awt.Color(230, 230, 230));

                jLabel64.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel64.setForeground(new java.awt.Color(51, 51, 51));
                jLabel64.setText("Error");

                jPanel146.setBackground(new java.awt.Color(23, 160, 134));

                btnAlertConsulta10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                btnAlertConsulta10.setForeground(new java.awt.Color(240, 240, 240));
                btnAlertConsulta10.setText("Entendido");
                btnAlertConsulta10.setContentAreaFilled(false);
                btnAlertConsulta10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnAlertConsulta10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnAlertConsulta10.setIconTextGap(30);
                btnAlertConsulta10.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnAlertConsulta10ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel146Layout = new javax.swing.GroupLayout(jPanel146);
                jPanel146.setLayout(jPanel146Layout);
                jPanel146Layout.setHorizontalGroup(
                    jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAlertConsulta10, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                );
                jPanel146Layout.setVerticalGroup(
                    jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel146Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAlertConsulta10))
                );

                jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jLabel67.setForeground(new java.awt.Color(51, 51, 51));
                jLabel67.setText("<html>Necesita permisos de administrador <BR>Para realizar esta acción.</html>");

                javax.swing.GroupLayout jPanel145Layout = new javax.swing.GroupLayout(jPanel145);
                jPanel145.setLayout(jPanel145Layout);
                jPanel145Layout.setHorizontalGroup(
                    jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel145Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64)
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(171, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel145Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel146, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                );
                jPanel145Layout.setVerticalGroup(
                    jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel145Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel64)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel146, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout NivelSuperiorLayout = new javax.swing.GroupLayout(NivelSuperior.getContentPane());
                NivelSuperior.getContentPane().setLayout(NivelSuperiorLayout);
                NivelSuperiorLayout.setHorizontalGroup(
                    NivelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                NivelSuperiorLayout.setVerticalGroup(
                    NivelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(23, 160, 134));
                jPanel1.setPreferredSize(new java.awt.Dimension(284, 678));
                jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        jPanel1MouseEntered(evt);
                    }
                });

                jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("<html>TUPA<span style=\"font-size:'14px'\"><br>Items</br></span></html>");

                btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Documento-32.png"))); // NOI18N
                btnNuevo.setText("Nuevo");
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

                btneditar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btneditar.setForeground(new java.awt.Color(240, 240, 240));
                btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Editar-32.png"))); // NOI18N
                btneditar.setText("Editar");
                btneditar.setContentAreaFilled(false);
                btneditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btneditar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btneditar.setIconTextGap(30);
                btneditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btneditar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btneditarActionPerformed(evt);
                    }
                });

                btnguardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnguardar.setForeground(new java.awt.Color(240, 240, 240));
                btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Guardar-32.png"))); // NOI18N
                btnguardar.setText("Guardar");
                btnguardar.setContentAreaFilled(false);
                btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnguardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnguardar.setIconTextGap(30);
                btnguardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnguardar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnguardarActionPerformed(evt);
                    }
                });

                btneliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btneliminar.setForeground(new java.awt.Color(240, 240, 240));
                btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Basura-32.png"))); // NOI18N
                btneliminar.setText("Eliminar");
                btneliminar.setContentAreaFilled(false);
                btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btneliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btneliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btneliminar.setIconTextGap(30);
                btneliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btneliminar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btneliminarActionPerformed(evt);
                    }
                });

                lblusu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                lblusu.setForeground(new java.awt.Color(255, 255, 255));
                lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Usuario-40.png"))); // NOI18N
                lblusu.setText("MARTHA");
                lblusu.setFocusable(false);
                lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                jPanel23.setBackground(new java.awt.Color(255, 255, 255));

                buscartodo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                buscartodo.setForeground(new java.awt.Color(51, 51, 51));
                buscartodo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                buscartodo.setBorder(null);
                buscartodo.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        buscartodoCaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
                jPanel23.setLayout(jPanel23Layout);
                jPanel23Layout.setHorizontalGroup(
                    jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel23Layout.setVerticalGroup(
                    jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                btnBuscarPaciente.setContentAreaFilled(false);
                btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarPacienteActionPerformed(evt);
                    }
                });

                lbldetalle.setForeground(new java.awt.Color(255, 255, 255));
                lbldetalle.setText("CPT, Grupo");

                btnLista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnLista.setForeground(new java.awt.Color(240, 240, 240));
                btnLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Orden de compra-32.png"))); // NOI18N
                btnLista.setText("Listado");
                btnLista.setContentAreaFilled(false);
                btnLista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnLista.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnLista.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnLista.setIconTextGap(30);
                btnLista.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnLista.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        btnListaMouseClicked(evt);
                    }
                });
                btnLista.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnListaActionPerformed(evt);
                    }
                });

                btnImprimir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnImprimir.setForeground(new java.awt.Color(240, 240, 240));
                btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Imprimir-32.png"))); // NOI18N
                btnImprimir.setText("Imprimir");
                btnImprimir.setContentAreaFilled(false);
                btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnImprimir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnImprimir.setIconTextGap(30);
                btnImprimir.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnImprimirActionPerformed(evt);
                    }
                });

                panelH.setBackground(new java.awt.Color(255, 255, 255));
                panelH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                lblH.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                lblH.setForeground(new java.awt.Color(102, 102, 102));
                lblH.setText("Horizontal");
                lblH.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        lblHMouseClicked(evt);
                    }
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        lblHMouseEntered(evt);
                    }
                });

                javax.swing.GroupLayout panelHLayout = new javax.swing.GroupLayout(panelH);
                panelH.setLayout(panelHLayout);
                panelHLayout.setHorizontalGroup(
                    panelHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                );
                panelHLayout.setVerticalGroup(
                    panelHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblH, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                );

                panelV.setBackground(new java.awt.Color(255, 255, 255));
                panelV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                lblV.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                lblV.setForeground(new java.awt.Color(102, 102, 102));
                lblV.setText("Vertical");
                lblV.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        lblVMouseClicked(evt);
                    }
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        lblVMouseEntered(evt);
                    }
                });

                javax.swing.GroupLayout panelVLayout = new javax.swing.GroupLayout(panelV);
                panelV.setLayout(panelVLayout);
                panelVLayout.setHorizontalGroup(
                    panelVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                );
                panelVLayout.setVerticalGroup(
                    panelVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblV, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                );

                lblCrick.setForeground(new java.awt.Color(23, 160, 134));
                lblCrick.setText("0");

                lblNivel.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
                lblNivel.setForeground(new java.awt.Color(23, 160, 134));
                lblNivel.setText("jLabel2");

                lblPermiso.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N
                lblPermiso.setForeground(new java.awt.Color(23, 160, 134));
                lblPermiso.setText("jLabel2");

                lblEditar.setForeground(new java.awt.Color(23, 160, 134));
                lblEditar.setText("0");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblNivel)
                                .addGap(18, 18, 18)
                                .addComponent(lblPermiso))
                            .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbldetalle)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btneditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(panelH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnImprimir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                                    .addComponent(panelV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCrick, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(13, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEditar)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbldetalle))
                            .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnguardar)
                        .addGap(18, 18, 18)
                        .addComponent(btneditar)
                        .addGap(18, 18, 18)
                        .addComponent(btneliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLista)
                        .addGap(18, 18, 18)
                        .addComponent(btnImprimir)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCrick))
                        .addGap(1, 1, 1)
                        .addComponent(panelV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNivel)
                            .addComponent(lblPermiso))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblusu)
                        .addContainerGap())
                );

                jPanel5.setBackground(new java.awt.Color(230, 230, 230));
                jPanel5.setPreferredSize(new java.awt.Dimension(929, 115));
                jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        jPanel5MouseEntered(evt);
                    }
                });

                jLabel33.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
                jLabel33.setForeground(new java.awt.Color(51, 51, 51));
                jLabel33.setText("Listado");

                jPanel4.setBackground(new java.awt.Color(230, 230, 230));
                jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        jPanel4MouseEntered(evt);
                    }
                });

                eli1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                eli1.setForeground(new java.awt.Color(51, 51, 51));
                eli1.setText("Editar");
                eli1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                eli1.setContentAreaFilled(false);
                eli1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                eli1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                eli1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                eli1.setIconTextGap(10);
                eli1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        eli1ActionPerformed(evt);
                    }
                });

                lblDocumento1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblDocumento1.setForeground(new java.awt.Color(51, 51, 51));
                lblDocumento1.setText("Valor de la UIT");

                txtPrecio_Base.setEditable(false);
                txtPrecio_Base.setBackground(new java.awt.Color(230, 230, 230));
                txtPrecio_Base.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtPrecio_Base.setForeground(new java.awt.Color(51, 51, 51));
                txtPrecio_Base.setText("452.0");
                txtPrecio_Base.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                txtPrecio_Base.setCaretColor(new java.awt.Color(255, 255, 255));
                txtPrecio_Base.setSelectionColor(new java.awt.Color(255, 51, 51));
                txtPrecio_Base.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtPrecio_BaseMouseClicked(evt);
                    }
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        txtPrecio_BaseMouseEntered(evt);
                    }
                });

                save.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                save.setForeground(new java.awt.Color(51, 51, 51));
                save.setText("Guardar");
                save.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                save.setContentAreaFilled(false);
                save.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                save.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                save.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                save.setIconTextGap(10);
                save.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        saveActionPerformed(evt);
                    }
                });

                cancel.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                cancel.setForeground(new java.awt.Color(51, 51, 51));
                cancel.setText("Calcelar");
                cancel.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cancel.setContentAreaFilled(false);
                cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                cancel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                cancel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                cancel.setIconTextGap(10);
                cancel.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cancelActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(lblDocumento1)
                        .addGap(18, 18, 18)
                        .addComponent(txtPrecio_Base, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eli1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel4Layout.setVerticalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDocumento1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrecio_Base, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eli1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))
                );

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel5Layout.setVerticalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                );

                cargareliminar.setBackground(new java.awt.Color(255, 153, 51));

                Mensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                Mensaje.setForeground(new java.awt.Color(255, 255, 255));
                Mensaje.setText("Desea Actualizar el Registro ?");

                eli.setForeground(new java.awt.Color(240, 240, 240));
                eli.setText("Si");
                eli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                eli.setContentAreaFilled(false);
                eli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                eli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                eli.setIconTextGap(30);
                eli.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        eliActionPerformed(evt);
                    }
                });

                noeli.setForeground(new java.awt.Color(240, 240, 240));
                noeli.setText("No");
                noeli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                noeli.setContentAreaFilled(false);
                noeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                noeli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                noeli.setIconTextGap(30);
                noeli.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        noeliActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout cargareliminarLayout = new javax.swing.GroupLayout(cargareliminar);
                cargareliminar.setLayout(cargareliminarLayout);
                cargareliminarLayout.setHorizontalGroup(
                    cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cargareliminarLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(Mensaje)
                        .addGap(46, 46, 46)
                        .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                cargareliminarLayout.setVerticalGroup(
                    cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cargareliminarLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Mensaje)
                            .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                Paginas.setForeground(new java.awt.Color(255, 255, 255));
                Paginas.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
                Paginas.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                tb_CPT.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                tb_CPT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                tb_CPT.setModel(new javax.swing.table.DefaultTableModel(
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
                tb_CPT.setGridColor(new java.awt.Color(255, 255, 255));
                tb_CPT.setRowHeight(25);
                tb_CPT.setSelectionBackground(new java.awt.Color(102, 102, 102));
                tb_CPT.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_CPTMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tb_CPTMousePressed(evt);
                    }
                });
                tb_CPT.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_CPTKeyPressed(evt);
                    }
                });
                jScrollPane3.setViewportView(tb_CPT);

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE))
                );

                Paginas.addTab(".", jPanel2);

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel11.setForeground(new java.awt.Color(51, 51, 51));
                jLabel11.setText("Grupo");

                cbxGrupo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                cbxGrupo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 Doc. Trib.NO.DOM.SIN.RUC", "1 DNI", "4 CARNET DE EXTRANJERIA", "6 RUC", "7 PASAPORTE", "A CED.DIPLOMATICA DE IDENTIDAD" }));
                cbxGrupo.addItemListener(new java.awt.event.ItemListener() {
                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                        cbxGrupoItemStateChanged(evt);
                    }
                });

                jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel12.setForeground(new java.awt.Color(51, 51, 51));
                jLabel12.setText("Cuenta Contable");

                panelCPT1.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtct6.setEditable(false);
                txtct6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtct6.setForeground(new java.awt.Color(51, 51, 51));
                txtct6.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtct6.setBorder(null);
                txtct6.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtct6CaretUpdate(evt);
                    }
                });

                b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                b1.setContentAreaFilled(false);
                b1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                b1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        b1ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT1Layout = new javax.swing.GroupLayout(panelCPT1);
                panelCPT1.setLayout(panelCPT1Layout);
                panelCPT1Layout.setHorizontalGroup(
                    panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtct6, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                );
                panelCPT1Layout.setVerticalGroup(
                    panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT1Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtct6, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel13.setText("Item");

                panelCPT2.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtGrupo.setEditable(false);
                txtGrupo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtGrupo.setForeground(new java.awt.Color(51, 51, 51));
                txtGrupo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtGrupo.setBorder(null);
                txtGrupo.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtGrupoCaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT2Layout = new javax.swing.GroupLayout(panelCPT2);
                panelCPT2.setLayout(panelCPT2Layout);
                panelCPT2Layout.setHorizontalGroup(
                    panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                );
                panelCPT2Layout.setVerticalGroup(
                    panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT2Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                panelCPT3.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtGrupoDes.setEditable(false);
                txtGrupoDes.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtGrupoDes.setForeground(new java.awt.Color(51, 51, 51));
                txtGrupoDes.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtGrupoDes.setBorder(null);
                txtGrupoDes.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtGrupoDesCaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT3Layout = new javax.swing.GroupLayout(panelCPT3);
                panelCPT3.setLayout(panelCPT3Layout);
                panelCPT3Layout.setHorizontalGroup(
                    panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtGrupoDes, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                );
                panelCPT3Layout.setVerticalGroup(
                    panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT3Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtGrupoDes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                txtCPTDES.setForeground(new java.awt.Color(51, 51, 51));
                txtCPTDES.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtCPTDESKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtCPTDESKeyTyped(evt);
                    }
                });
                jScrollPane1.setViewportView(txtCPTDES);

                jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel14.setText("Número y Denominación ");

                lblIDGRUPO.setForeground(new java.awt.Color(255, 255, 255));
                lblIDGRUPO.setText("jLabel2");

                lblIDCTA6.setForeground(new java.awt.Color(255, 255, 255));
                lblIDCTA6.setText("jLabel2");

                lblIDCPT.setForeground(new java.awt.Color(255, 255, 255));
                lblIDCPT.setText("jLabel2");

                panelTUPA_COMPLETO.setBackground(new java.awt.Color(255, 255, 255));
                panelTUPA_COMPLETO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtCPT_COMPLETO.setEditable(false);
                txtCPT_COMPLETO.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtCPT_COMPLETO.setForeground(new java.awt.Color(51, 51, 51));
                txtCPT_COMPLETO.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtCPT_COMPLETO.setBorder(null);
                txtCPT_COMPLETO.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCPT_COMPLETOCaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout panelTUPA_COMPLETOLayout = new javax.swing.GroupLayout(panelTUPA_COMPLETO);
                panelTUPA_COMPLETO.setLayout(panelTUPA_COMPLETOLayout);
                panelTUPA_COMPLETOLayout.setHorizontalGroup(
                    panelTUPA_COMPLETOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTUPA_COMPLETOLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtCPT_COMPLETO, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                );
                panelTUPA_COMPLETOLayout.setVerticalGroup(
                    panelTUPA_COMPLETOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTUPA_COMPLETOLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtCPT_COMPLETO, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel15.setText("Denominación Del Procedimiento");

                panelCPT5.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtNombre.setEditable(false);
                txtNombre.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtNombre.setForeground(new java.awt.Color(51, 51, 51));
                txtNombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtNombre.setBorder(null);
                txtNombre.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtNombreCaretUpdate(evt);
                    }
                });
                txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtNombreKeyReleased(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT5Layout = new javax.swing.GroupLayout(panelCPT5);
                panelCPT5.setLayout(panelCPT5Layout);
                panelCPT5Layout.setHorizontalGroup(
                    panelCPT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtNombre)
                        .addGap(3, 3, 3))
                );
                panelCPT5Layout.setVerticalGroup(
                    panelCPT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT5Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel16.setText("Derecho de tramitación UIT en % ");

                panelCPT6.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtPORCENTAJE.setEditable(false);
                txtPORCENTAJE.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtPORCENTAJE.setForeground(new java.awt.Color(51, 51, 51));
                txtPORCENTAJE.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtPORCENTAJE.setBorder(null);
                txtPORCENTAJE.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPORCENTAJECaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT6Layout = new javax.swing.GroupLayout(panelCPT6);
                panelCPT6.setLayout(panelCPT6Layout);
                panelCPT6Layout.setHorizontalGroup(
                    panelCPT6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT6Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtPORCENTAJE, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                );
                panelCPT6Layout.setVerticalGroup(
                    panelCPT6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT6Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtPORCENTAJE, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel18.setText("En S/.");

                jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel19.setText("0.00");

                jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel20.setText("Base Legal");

                txtBASE_LEGAL.setForeground(new java.awt.Color(51, 51, 51));
                txtBASE_LEGAL.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtBASE_LEGALKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtBASE_LEGALKeyTyped(evt);
                    }
                });
                jScrollPane2.setViewportView(txtBASE_LEGAL);

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(lblIDCPT)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(panelCPT5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(panelCPT6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel18)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel19))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(cbxGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblIDGRUPO))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblIDCTA6))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(panelCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(panelTUPA_COMPLETO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(58, 58, 58))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                );
                jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(lblIDCPT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(cbxGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblIDGRUPO)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIDCTA6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelTUPA_COMPLETO, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(panelCPT5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelCPT6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                Paginas.addTab(".", jPanel3);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                            .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Paginas)))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Paginas))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        if(lblPermiso.getText().equals("E")){
            RESTAURAR_BARRA();
            tg = 1;
            btnNuevo.setEnabled(true);
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            btneliminar.setEnabled(false);
            panelCPT2.setVisible(true);
            panelCPT3.setVisible(true);
            panelTUPA_COMPLETO.setVisible(false);
            LIMPIAR();
            HABILITAR(true);
            Caja_CPT cno2 = new Caja_CPT();
            cno2.DATOS_GRUPO(cbxGrupo.getSelectedItem().toString());
            Paginas.setSelectedIndex(1);
        }else if(!lblPermiso.getText().equals("E")){
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);
        }

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        if(lblPermiso.getText().equals("E")){
            tg=2;
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            HABILITAR(true);
            Paginas.setSelectedIndex(1);
        }else if(!lblPermiso.getText().equals("E")){
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);
            
        }    
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if(tg==1){
            Caja_CPT cn = new Caja_CPT();
            Caja_CPT cn1 = new Caja_CPT();
            if((cn.VALIDAR_TUPA(txtGrupo.getText()+txtGrupoDes.getText())>0)||(cn1.VALIDAR_TUPA_DES(txtNombre.getText())>0)){
                cargareliminar.setVisible(true);
                 cargareliminar.setBackground(new Color(255,91,70)); 
                 Mensaje.setText("El Ítem ingresado ya existe, Verifique ");
                 eli.setVisible(false);
                 noeli.setVisible(false);
                 txtGrupoDes.setText("");
                 txtGrupoDes.requestFocus();
                
            }else
                NUEVO_REGISTRO(); 
            
        }
        if(tg==2){
            cargareliminar.setVisible(true);
            cargareliminar.setBackground(new Color(255,153,51));
            Mensaje.setText("Desea Actualizar el Registro ?");
            eli.setText("Si");
            eli.setVisible(true);
            noeli.setVisible(true);
            tgm=3;
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        if(lblPermiso.getText().equals("E")){
            cargareliminar.setVisible(true);
            cargareliminar.setBackground(new Color(255,91,70));
            Mensaje.setText("Desea Eliminar este registro?");
            eli.setText("Si");
            eli.setVisible(true);
            noeli.setText("No");
            noeli.setVisible(true);
            tgm=8;
        }else if(!lblPermiso.getText().equals("E")){
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
        Caja_CPT A = new Caja_CPT();
        A.LISTA_CPT(buscartodo.getText(),tb_CPT);
        Paginas.setSelectedIndex(0);
        jLabel33.setText("Resultados de la Busqueda");
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btnListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaMouseClicked

    }//GEN-LAST:event_btnListaMouseClicked

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        jLabel33.setText("Listado");
        buscartodo.setText("");
        Caja_CPT A = new Caja_CPT();
        A.LISTA_CPT("",tb_CPT);
        Paginas.setSelectedIndex(0);
        RESTAURAR_BARRA();
    }//GEN-LAST:event_btnListaActionPerformed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        if (tgm==3){
            if(lblPermiso.getText().equals("E")){
                MODIFICAR_REGISTRO();
            }else if(!lblPermiso.getText().equals("E")){
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);
            
        }    
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            btneliminar.setEnabled(false);
        } else
        if (tgm==8){
            if(lblPermiso.getText().equals("E")){
                ELIMINAR_CPT();
            }else if(!lblPermiso.getText().equals("E")){
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);
        }
            btnguardar.setEnabled(true);
            btneditar.setEnabled(false);
            btneliminar.setEnabled(false);
        }
        else if (tgm==2 ||tgm==1||tgm==9){
            cargareliminar.setVisible(false);
        }
        if (tgm==0){
            cargareliminar.setVisible(false);
        }
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed
        cargareliminar.setVisible(false);
    }//GEN-LAST:event_noeliActionPerformed

    private void tb_CPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CPTMouseClicked

        if(evt.getClickCount()==1){
            CARGAR();
            btnguardar.setEnabled(false);
            btneditar.setEnabled(true);
            btneliminar.setEnabled(true);
        }
        if(evt.getClickCount()==2){
            jLabel33.setText("Edición");
            Paginas.setSelectedIndex(1);

        }

    }//GEN-LAST:event_tb_CPTMouseClicked

    private void tb_CPTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CPTMousePressed

    }//GEN-LAST:event_tb_CPTMousePressed

    private void tb_CPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_CPTKeyPressed

    }//GEN-LAST:event_tb_CPTKeyPressed

    private void txtct6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtct6CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtct6CaretUpdate

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        Caja_CPT A = new Caja_CPT();
        A.LISTA_CTA6("", tb_Grupos2);
        Cta6.setVisible(true);
    }//GEN-LAST:event_b1ActionPerformed

    private void txtGrupoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGrupoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrupoCaretUpdate

    private void txtGrupoDesCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGrupoDesCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGrupoDesCaretUpdate

    private void txtCPTDESKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPTDESKeyReleased
     

    }//GEN-LAST:event_txtCPTDESKeyReleased

    private void txtCPTDESKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPTDESKeyTyped

    }//GEN-LAST:event_txtCPTDESKeyTyped

    private void cbxGrupoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxGrupoItemStateChanged
        Caja_CPT cno2 = new Caja_CPT();
        cno2.DATOS_GRUPO(cbxGrupo.getSelectedItem().toString());
    }//GEN-LAST:event_cbxGrupoItemStateChanged

    private void txtCPT_COMPLETOCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT_COMPLETOCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT_COMPLETOCaretUpdate

    private void txtBuscar2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar2CaretUpdate
        Caja_CPT A = new Caja_CPT();
        A.LISTA_CTA6(txtBuscar2.getText(), tb_Grupos2);   
    }//GEN-LAST:event_txtBuscar2CaretUpdate

    private void txtBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar2ActionPerformed

    private void txtBuscar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar2KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
            tb_Grupos2.getSelectionModel().setSelectionInterval (0,0) ;
            tb_Grupos2.requestFocus();
        }
    }//GEN-LAST:event_txtBuscar2KeyPressed

    private void btnBuscarPaciente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente2ActionPerformed

    private void tb_Grupos2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupos2MouseClicked
        int fila=tb_Grupos2.getSelectedRow();
        if(evt.getClickCount()==2){
            Cta6.dispose();
            txtct6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 0))+"\t"+(tb_Grupos2.getValueAt(fila, 1)));
            lblIDCTA6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 2)));
            txtGrupoDes.setRequestFocusEnabled(true);

        }
    }//GEN-LAST:event_tb_Grupos2MouseClicked

    private void tb_Grupos2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupos2KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupos2.getSelectedRow();
            Cta6.dispose();
            txtct6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 0))+"\t"+(tb_Grupos2.getValueAt(fila, 1)));
            lblIDCTA6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 2)));
            txtGrupoDes.setRequestFocusEnabled(true);
        }

    }//GEN-LAST:event_tb_Grupos2KeyPressed

    private void txtNombreCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNombreCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCaretUpdate

    private void txtPORCENTAJECaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPORCENTAJECaretUpdate
        try {
            double UIT=0.00,P=0.00,T=0.00;
            UIT=Double.parseDouble(txtPrecio_Base.getText());
            P=Double.parseDouble(txtPORCENTAJE.getText());
            T=UIT*P/100;
            BigDecimal bd4 = new BigDecimal(T);
            bd4 = bd4.setScale(2, BigDecimal.ROUND_HALF_UP);
            jLabel19.setText(String.valueOf(bd4));
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_txtPORCENTAJECaretUpdate

    private void txtBASE_LEGALKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBASE_LEGALKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBASE_LEGALKeyReleased

    private void txtBASE_LEGALKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBASE_LEGALKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBASE_LEGALKeyTyped

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
           txtNombre.setText(txtNombre.getText().toUpperCase());
    }//GEN-LAST:event_txtNombreKeyReleased

    private void eli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eli1ActionPerformed
        if(lblNivel.getText().equals("ADMINISTRADOR")&&lblPermiso.getText().equals("E")){
            txtPrecio_Base.setEditable(true);
            txtPrecio_Base.requestFocus();
            jPanel5.setBackground(new Color(209,52,56));
            jPanel4.setBackground(new Color(209,52,56));
            txtPrecio_Base.setBackground(new Color(209,52,56));
            ImageIcon CambioS=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Save-22B.png"));
            save.setIcon(CambioS);
            ImageIcon CambioC=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/icons8-Long Arrow Left-22B.png"));
            cancel.setIcon(CambioC);
            jLabel33.setForeground(new Color(255,255,255));
            lblDocumento1.setForeground(new Color(255,255,255));
            txtPrecio_Base.setForeground(new Color(255,255,255));
            eli1.setForeground(new Color(255,255,255));
            save.setForeground(new Color(255,255,255));
            cancel.setForeground(new Color(255,255,255));
            
            jLabel33.setText("Cambio de UIT");
            save.setVisible(true);
            cancel.setVisible(true);
            eli1.setVisible(false);
            lblEditar.setText("1");
            
        }else if(!lblNivel.getText().equals("ADMINISTRADOR")&&!lblPermiso.getText().equals("E")){
            NivelSuperior.setUndecorated(true);
            NivelSuperior.setVisible(true);
            
        }
            
    }//GEN-LAST:event_eli1ActionPerformed

    private void txtPrecio_BaseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPrecio_BaseMouseEntered
        if(lblEditar.getText().equals("0")){
            eli1.setVisible(true);
        }
    }//GEN-LAST:event_txtPrecio_BaseMouseEntered

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
        eli1.setVisible(false);
    }//GEN-LAST:event_jPanel5MouseEntered

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
       
    }//GEN-LAST:event_jPanel4MouseEntered

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        RESTAURAR_BARRA();
        if(lblCrick.getText().equals("0")){
            panelH.setVisible(true);
            panelV.setVisible(true);
            lblCrick.setText("1");
        }else if(lblCrick.getText().equals("1")){
            panelH.setVisible(false);
            panelV.setVisible(false);
            lblCrick.setText("0");
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void lblHMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHMouseEntered
        lblH.setForeground(new Color(0,130,114));
        lblV.setForeground(new Color(102,102,102));
    }//GEN-LAST:event_lblHMouseEntered

    private void lblVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVMouseEntered
        lblV.setForeground(new Color(0,130,114));
        lblH.setForeground(new Color(102,102,102));
    }//GEN-LAST:event_lblVMouseEntered

    private void lblHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHMouseClicked
 
        panelH.setVisible(false);
        panelV.setVisible(false);
        nuevaR.ReporteDiarioH();
        lblCrick.setText("0");
    }//GEN-LAST:event_lblHMouseClicked

    private void lblVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVMouseClicked
        panelH.setVisible(false);
        panelV.setVisible(false);
        nuevaR.ReporteDiarioV();
        lblCrick.setText("0");
    }//GEN-LAST:event_lblVMouseClicked

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
//        panelH.setVisible(false);
//        panelV.setVisible(false);
    }//GEN-LAST:event_jPanel1MouseEntered

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        MODIFICAR_UIT();
    }//GEN-LAST:event_saveActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        RESTAURAR_BARRA();
    }//GEN-LAST:event_cancelActionPerformed

    private void txtPrecio_BaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPrecio_BaseMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecio_BaseMouseClicked

    private void btnAlertConsulta10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta10ActionPerformed
        NivelSuperior.dispose();
    }//GEN-LAST:event_btnAlertConsulta10ActionPerformed

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
            java.util.logging.Logger.getLogger(Caja_CPTS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_CPTS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_CPTS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_CPTS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_CPTS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Cta6;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JDialog NivelSuperior;
    private javax.swing.JTabbedPane Paginas;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnAlertConsulta10;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarPaciente2;
    public static javax.swing.JButton btnImprimir;
    public static javax.swing.JButton btnLista;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    public static javax.swing.JTextField buscartodo;
    private javax.swing.JButton cancel;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JComboBox cbxGrupo;
    private javax.swing.JButton eli;
    private javax.swing.JButton eli1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblCrick;
    public static javax.swing.JLabel lblDocumento1;
    private javax.swing.JLabel lblEditar;
    private javax.swing.JLabel lblH;
    private javax.swing.JLabel lblIDCPT;
    private javax.swing.JLabel lblIDCTA6;
    public static javax.swing.JLabel lblIDGRUPO;
    public static javax.swing.JLabel lblNivel;
    public static javax.swing.JLabel lblPermiso;
    private javax.swing.JLabel lblV;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JPanel panelCPT2;
    private javax.swing.JPanel panelCPT3;
    private javax.swing.JPanel panelCPT5;
    private javax.swing.JPanel panelCPT6;
    private javax.swing.JPanel panelH;
    private javax.swing.JPanel panelTUPA_COMPLETO;
    private javax.swing.JPanel panelV;
    private javax.swing.JButton save;
    private javax.swing.JTable tb_CPT;
    private javax.swing.JTable tb_Grupos2;
    private javax.swing.JEditorPane txtBASE_LEGAL;
    private javax.swing.JTextField txtBuscar2;
    private javax.swing.JEditorPane txtCPTDES;
    public static javax.swing.JTextField txtCPT_COMPLETO;
    public static javax.swing.JTextField txtGrupo;
    public static javax.swing.JTextField txtGrupoDes;
    public static javax.swing.JTextField txtNombre;
    public static javax.swing.JTextField txtPORCENTAJE;
    public static javax.swing.JTextField txtPrecio_Base;
    public static javax.swing.JTextField txtct6;
    // End of variables declaration//GEN-END:variables
}
