/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.hospitalizacion;

import campos.LimitadorDeDocumento;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Caja.Caja_Preventa;
import modelos.admisionCentral.HistoriaClinica;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import modelos.hospitalizacion.HospitalizacionArticuloDetalle;
import modelos.hospitalizacion.HospitalizacionAsignacionCamas;
import modelos.hospitalizacion.HospitalizacionPapeletas;
import modelos.hospitalizacion.HospitalizacionPisos;
import servicios.Conexion;
import vista.admisionEmergencia.FrmFormatoEmergencia;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lblPestana;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lblPestanaMod;
import static vista.admisionEmergencia.FrmFormatoEmergencia.pnlEObservación;
import static vista.admisionEmergencia.FrmFormatoEmergencia.txtNHC;

/**
 *
 * @author PC02
 */
public class FrmHospitalizacionCajaPreventa extends javax.swing.JFrame {

    DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    ResultSet r;
    HospitalizacionPapeletas hosP = new HospitalizacionPapeletas();
    public FrmHospitalizacionCajaPreventa() {
        initComponents();
        this.setLocationRelativeTo(null);//en el centro
        this.setResizable(false);//deshabilita boton maximizar
        this.getContentPane().setBackground(Color.WHITE);//color blanco del formulario
        conexion = c.conectar();
        limitaciones();
        cbxAreas.setModel(areas());
        cbxServicio.setBackground(Color.white);
        cbxAreas.setBackground(Color.white);
        cbxProcedencia.setBackground(Color.white);
        cbxCama.setBackground(Color.white);
        //BOTON CERRAR
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
    
    public void limitaciones(){
        LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(7);
        txtBuscarNHC.setDocument(limitObservacion);
    }
    
    public void habilitarCampos(boolean opcion){
        txtBuscarNHC.setEnabled(opcion);
        txtEdad.setEnabled(opcion);
        txtPaciente.setEnabled(opcion);
        txtDni.setEnabled(opcion);
        cbxServicio.setEnabled(opcion);
        cbxAreas.setEnabled(opcion);
        cbxCama.setEnabled(opcion);
        btnAgregar.setEnabled(opcion);
        txtIndicaciones.setEnabled(opcion);
        chkAislado.setEnabled(opcion);
        cbxProcedencia.setEnabled(opcion);
        txtArea.setEnabled(opcion);
        btnBuscarNHC.setEnabled(opcion);
    }
    public String formatoNHC(String nhc){
        String codigo = String.valueOf(nhc.charAt(0)) + 
                        String.valueOf(nhc.charAt(1)) + 
                        String.valueOf(nhc.charAt(2)) + 
                        String.valueOf(nhc.charAt(3)) + 
                        String.valueOf(nhc.charAt(5)) +
                        String.valueOf(nhc.charAt(6));
        return codigo;
    }
    
    public void limpiarDatosNHC(){
        txtPaciente.setText("");
        txtDni.setText("");
        txtEdad.setText("");
        lblIDHC.setText("");
        lblEstado.setText("");
        lblGenero.setText("");
    }
    
    public void limpiarDatosServicio(){
        lblServicio.setText("");
        txtArea.setText("");
        txtEdad.setText("");
    }
    
    public void limpiar(){
        txtBuscarNHC.setText("");
        txtDni.setText("");
        txtEdad.setText("");
        lblIDHC.setText("");
        lblEstado.setText("");
        lblGenero.setText("");
        txtPaciente.setText("");
        cbxServicio.setSelectedIndex(0);
        cbxAreas.setSelectedIndex(0);
        cbxProcedencia.setSelectedIndex(0);
        lblServicio.setText("");
        txtIndicaciones.setText("");
        txtArea.setText("");
        cbxCama.setSelectedIndex(0);
        chkAislado.setSelected(false);
        m = (DefaultTableModel)tbSelecArticulos.getModel();
        int filas = tbSelecArticulos.getRowCount();
        for(int i =0;i<filas;i++){
            m.removeRow(0);
        }
    }
    
    public DefaultComboBoxModel areas(){
           DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
           String   sql = null;
           ResultSet rs = null;
           Statement  st = null;   
            try {
                  st = conexion.createStatement();
                  r = st.executeQuery ("EXEC SERVICIO_AREAS_LISTAR"); 
                  listmodel.addElement("Seleccionar...");
                while( r.next() ){
                    listmodel.addElement( r.getString( "AR_DESC" ) );                
                 }
                r.close();
                
            } catch (SQLException ex) {            
                System.err.println( "FrmHospitalizacionPapelete Error: servicios: EXEC HOSPITALIZACION_MOSTRAR_SERVICIOS :" + ex.getMessage() );
            }        
        return listmodel;
    }
    
    public boolean repiteArticulos(){
        int filaselec=tbArticulos.getSelectedRow();
        boolean c=false;
        for (int i = 0; i < tbSelecArticulos.getRowCount(); i++){    
            if(tbSelecArticulos.getValueAt(i, 0).toString().equalsIgnoreCase(tbArticulos.getValueAt(filaselec, 0).toString())){
                c=true;
            }
        }
            return c;
     }
    
    public void enviarArticulos(){
        try {
        m = (DefaultTableModel) tbSelecArticulos.getModel();
        int fila = tbArticulos.getSelectedRow();
        if(tbSelecArticulos.getColumnCount()==0){
            dlgArticulos.dispose();
            //PASAR DATOS A TABLA
            String articulos[] = {
            String.valueOf(tbArticulos.getValueAt(fila, 0)),String.valueOf(tbArticulos.getValueAt(fila, 1))};
            m.addRow(articulos);
            //formatotbExamenAux(tbSelecArticulos);
        } else 
        if(repiteArticulos()==true)
            JOptionPane.showMessageDialog(dlgArticulos, "Este artículo ya se encuentra registrado");
        else{
            dlgArticulos.dispose();
            //PASAR DATOS A TABLA
            String articulos[] = {
            String.valueOf(tbArticulos.getValueAt(fila, 0)),String.valueOf(tbArticulos.getValueAt(fila, 1))};
            m.addRow(articulos);
            //formatotbExamenAux(tbSelecArticulos);
        }
        } catch (Exception e) {
            System.out.println("error: enviar" + e.getMessage());
        }
    }
    
    public boolean guardarDatosHospitalizacion(){
        boolean retorna = false;
        try {
            int id_preventa = 0;
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png"));
            if(txtBuscarNHC.getText().equals("") || cbxAreas.getSelectedIndex()== 0 || 
               cbxCama.getSelectedIndex()==0 || cbxServicio.getSelectedIndex()==0 ||
                    cbxProcedencia.getSelectedIndex()==0){ // validar datos vacios
                JOptionPane.showMessageDialog(this, "Debe completar los datos", "Alerta", WIDTH, i);
            }else{
                if(lblMant.getText().equals("U"))
                    id_preventa = Integer.parseInt(lblID.getText());
                Caja_Preventa cp = new Caja_Preventa();
                Caja_Preventa cp2 = new Caja_Preventa();
                HospitalizacionAsignacionCamas hc = new HospitalizacionAsignacionCamas();
                AdmisionEmergenciaCabecera ademer = new AdmisionEmergenciaCabecera();
                String id_hc = lblIDHC.getText();
                int area = Integer.parseInt(cp.codArea(cbxAreas.getSelectedItem().toString()));
                int cama = Integer.parseInt(cp.codCama(cbxCama.getSelectedItem().toString()));
                String indicaciones = txtIndicaciones.getText();
                String usuario = ademer.codUsuario(lblUsuUsuario.getText());
                String procedencia = "";
                if(cbxProcedencia.getSelectedIndex()==1)
                    procedencia = "CEX";
                else
                    procedencia = "EME";
                int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                if(guardar == 0){
                    cp.setId_preventa(id_preventa);
                    cp.setId_hc(id_hc);
                    cp.setCA_ID(cama);
                    cp.sethOS_Indicaciones(indicaciones);
                    cp.setCod_usu(usuario);
                    cp.setAR_ID(area);
                    cp.setProcedencia(procedencia);
                    if(cp.mantenimientoCajaPreventaHospitalizacion(lblMant.getText())==true){ 
                        
                        hc.setCA_ID(cama);
                        hc.setCOD_USU(usuario);
                        JOptionPane.showMessageDialog(this, cp.CajaPreventaID());
                        hc.setID_PREVENTA(cp.CajaPreventaID());
                       if(hc.mantenimientoAsignacionCama()==true){
                        retorna = true;
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error: guardarDatosHospitalizacion " + e.toString());
        }
        return retorna;
    }
    
    public boolean guardarDatos(){
        boolean retorna = false;
        if(guardarDatosHospitalizacion()==true){
            Caja_Preventa cp = new Caja_Preventa();
            for (int i = 0; i < tbSelecArticulos.getRowCount(); i++){      
                HospitalizacionArticuloDetalle hopsArt=new HospitalizacionArticuloDetalle();
                hopsArt.setId_preventa(cp.CajaPreventaID());
                hopsArt.setDescripcion(tbSelecArticulos.getValueAt(i, 1).toString());
                hopsArt.setCod_usu(cp.codUsuario(lblUsuUsuario.getText()));
                if(hopsArt.insertarArticuloDetalle()==true)
                    retorna = true;
            }
        } else 
            return retorna;
        return retorna;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgNHC = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbNHC = new javax.swing.JTable();
        pnlB = new javax.swing.JPanel();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscarN = new javax.swing.JButton();
        cbxTipoBusqueda = new javax.swing.JComboBox();
        dlgArticulos = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbArticulos = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtBuscarArt = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        lblUsuUsuario = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        lblGenero = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblMant = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtBuscarNHC = new javax.swing.JTextField();
        btnBuscarNHC = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblServicio = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtIndicaciones = new javax.swing.JEditorPane();
        jLabel6 = new javax.swing.JLabel();
        txtPaciente = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbxCama = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSelecArticulos = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        lblIDHC = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        cbxServicio = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        cbxAreas = new javax.swing.JComboBox();
        chkAislado = new javax.swing.JCheckBox();
        cbxProcedencia = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();

        dlgNHC.setAlwaysOnTop(true);
        dlgNHC.setMinimumSize(new java.awt.Dimension(393, 446));

        tbNHC = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbNHC.setModel(new javax.swing.table.DefaultTableModel(
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
        tbNHC.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbNHC.setGridColor(new java.awt.Color(204, 204, 204));
        tbNHC.setSelectionBackground(new java.awt.Color(217, 176, 86));
        tbNHC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNHCMouseClicked(evt);
            }
        });
        tbNHC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbNHCKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tbNHC);

        pnlB.setBackground(new java.awt.Color(255, 255, 255));
        pnlB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtBusqueda.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtBusqueda.setBorder(null);
        txtBusqueda.setEnabled(false);
        txtBusqueda.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBusquedaCaretUpdate(evt);
            }
        });
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
        });

        btnBuscarN.setBackground(new java.awt.Color(0, 153, 153));
        btnBuscarN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
        btnBuscarN.setMnemonic('B');
        btnBuscarN.setToolTipText("Buscar Nª H.C. (Alt + B)");
        btnBuscarN.setBorderPainted(false);
        btnBuscarN.setContentAreaFilled(false);
        btnBuscarN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarN.setDefaultCapable(false);
        btnBuscarN.setEnabled(false);
        btnBuscarN.setFocusPainted(false);
        btnBuscarN.setFocusable(false);
        btnBuscarN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBLayout = new javax.swing.GroupLayout(pnlB);
        pnlB.setLayout(pnlBLayout);
        pnlBLayout.setHorizontalGroup(
            pnlBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBLayout.createSequentialGroup()
                .addComponent(txtBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(btnBuscarN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        pnlBLayout.setVerticalGroup(
            pnlBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscarN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbxTipoBusqueda.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        cbxTipoBusqueda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Nº H.C.", "DNI", "Apellidos", "Nombres" }));
        cbxTipoBusqueda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoBusquedaItemStateChanged(evt);
            }
        });
        cbxTipoBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoBusquedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dlgNHCLayout = new javax.swing.GroupLayout(dlgNHC.getContentPane());
        dlgNHC.getContentPane().setLayout(dlgNHCLayout);
        dlgNHCLayout.setHorizontalGroup(
            dlgNHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgNHCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlgNHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dlgNHCLayout.createSequentialGroup()
                        .addComponent(cbxTipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dlgNHCLayout.setVerticalGroup(
            dlgNHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgNHCLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(dlgNHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlB, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        dlgArticulos.setMinimumSize(new java.awt.Dimension(400, 300));

        tbArticulos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbArticulos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbArticulos.setSelectionBackground(new java.awt.Color(217, 176, 86));
        tbArticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbArticulosKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tbArticulos);

        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 1, 13)); // NOI18N
        jLabel13.setText("Buscar:");

        txtBuscarArt.setFont(new java.awt.Font("Segoe UI Light", 1, 13)); // NOI18N
        txtBuscarArt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarArtCaretUpdate(evt);
            }
        });
        txtBuscarArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarArtActionPerformed(evt);
            }
        });
        txtBuscarArt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarArtKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout dlgArticulosLayout = new javax.swing.GroupLayout(dlgArticulos.getContentPane());
        dlgArticulos.getContentPane().setLayout(dlgArticulosLayout);
        dlgArticulosLayout.setHorizontalGroup(
            dlgArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgArticulosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dlgArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dlgArticulosLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarArt, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        dlgArticulosLayout.setVerticalGroup(
            dlgArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgArticulosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dlgArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtBuscarArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel8.setBackground(new java.awt.Color(217, 176, 86));
        jPanel8.setPreferredSize(new java.awt.Dimension(500, 65));
        jPanel8.setLayout(null);

        titulo5.setBackground(new java.awt.Color(153, 0, 51));
        titulo5.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
        titulo5.setForeground(new java.awt.Color(255, 255, 255));
        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo5.setText("Papeleta de Hospitalización");
        titulo5.setToolTipText("");
        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel8.add(titulo5);
        titulo5.setBounds(0, 11, 275, 30);

        lblUsuUsuario.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblUsuUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuUsuario.setText("Silvana");
        jPanel8.add(lblUsuUsuario);
        lblUsuUsuario.setBounds(320, 20, 85, 20);

        jLabel19.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
        jPanel8.add(jLabel19);
        jLabel19.setBounds(280, 20, 32, 24);

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
        btnNuevo.setMnemonic('N');
        btnNuevo.setToolTipText("Nuevo (Alt + N)");
        btnNuevo.setContentAreaFilled(false);
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setFocusable(false);
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel8.add(btnNuevo);
        btnNuevo.setBounds(10, 60, 24, 49);

        btnModificar.setBackground(new java.awt.Color(204, 204, 204));
        btnModificar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
        btnModificar.setMnemonic('M');
        btnModificar.setToolTipText("Modificar (Alt + M)");
        btnModificar.setContentAreaFilled(false);
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setEnabled(false);
        btnModificar.setFocusable(false);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel8.add(btnModificar);
        btnModificar.setBounds(74, 60, 28, 49);

        btnGuardar.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
        btnGuardar.setMnemonic('G');
        btnGuardar.setToolTipText("Guardar (Alt + G)");
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setEnabled(false);
        btnGuardar.setFocusable(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel8.add(btnGuardar);
        btnGuardar.setBounds(40, 60, 28, 49);

        btnEliminar.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
        btnEliminar.setMnemonic('E');
        btnEliminar.setToolTipText("Eliminar (Alt + E)");
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setEnabled(false);
        btnEliminar.setFocusable(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel8.add(btnEliminar);
        btnEliminar.setBounds(108, 60, 28, 49);

        btnBuscar.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscar.setToolTipText("Buscar (Alt + F3)");
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setFocusable(false);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel8.add(btnBuscar);
        btnBuscar.setBounds(140, 70, 38, 32);

        lblGenero.setForeground(new java.awt.Color(217, 176, 86));
        lblGenero.setText("jLabel14");
        jPanel8.add(lblGenero);
        lblGenero.setBounds(260, 100, 40, 14);

        lblID.setText("jLabel14");
        jPanel8.add(lblID);
        lblID.setBounds(390, 100, 40, 14);

        lblMant.setText("jLabel14");
        jPanel8.add(lblMant);
        lblMant.setBounds(330, 100, 40, 14);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 13)); // NOI18N
        jLabel1.setText("Buscar Nº H.C.");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtBuscarNHC.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        txtBuscarNHC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscarNHC.setBorder(null);
        txtBuscarNHC.setEnabled(false);
        txtBuscarNHC.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarNHCCaretUpdate(evt);
            }
        });
        txtBuscarNHC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarNHCKeyPressed(evt);
            }
        });

        btnBuscarNHC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/buscar.png"))); // NOI18N
        btnBuscarNHC.setContentAreaFilled(false);
        btnBuscarNHC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarNHC.setEnabled(false);
        btnBuscarNHC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarNHCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtBuscarNHC, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnBuscarNHC, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBuscarNHC)
            .addComponent(btnBuscarNHC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel2.setText("Nombres y Apellidos:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel3.setText("Requiere internarse en este hospital al Departamento de:");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 13)); // NOI18N
        jLabel4.setText("Servicio:");

        lblServicio.setBackground(new java.awt.Color(153, 153, 153));
        lblServicio.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lblServicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel5.setText("Indicaciones para la enfermera:");

        txtIndicaciones.setEnabled(false);
        jScrollPane1.setViewportView(txtIndicaciones);

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel6.setText("DNI:");

        txtPaciente.setEditable(false);
        txtPaciente.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtPaciente.setEnabled(false);

        txtDni.setEditable(false);
        txtDni.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtDni.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel7.setText("Edad:");

        txtEdad.setEditable(false);
        txtEdad.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtEdad.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("NOTA DE ADMISIÓN:");

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel9.setText("El paciente se interna en el Servicio de:");

        txtArea.setEditable(false);
        txtArea.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        txtArea.setEnabled(false);

        jLabel10.setText("Cama:");

        cbxCama.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        cbxCama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));
        cbxCama.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxCama.setEnabled(false);

        tbSelecArticulos.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        tbSelecArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Artículo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbSelecArticulos);

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 1, 11)); // NOI18N
        jLabel11.setText("Artículos que debe traer el paciente:");

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        lblIDHC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblIDHC.setForeground(new java.awt.Color(255, 255, 255));

        lblEstado.setForeground(new java.awt.Color(255, 255, 255));

        cbxServicio.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        cbxServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxServicio.setEnabled(false);
        cbxServicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxServicioItemStateChanged(evt);
            }
        });
        cbxServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxServicioActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 1, 13)); // NOI18N
        jLabel12.setText("Area:");

        cbxAreas.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        cbxAreas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxAreas.setEnabled(false);
        cbxAreas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxAreasItemStateChanged(evt);
            }
        });
        cbxAreas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAreasActionPerformed(evt);
            }
        });

        chkAislado.setFont(new java.awt.Font("Segoe UI Light", 1, 13)); // NOI18N
        chkAislado.setText("Aislado");
        chkAislado.setEnabled(false);
        chkAislado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkAisladoItemStateChanged(evt);
            }
        });
        chkAislado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAisladoActionPerformed(evt);
            }
        });

        cbxProcedencia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Consultorio Ex.", "Emergencia" }));
        cbxProcedencia.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel14.setText("Procedencia:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkAislado))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxCama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbxProcedencia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addGap(6, 6, 6)
                                .addComponent(cbxAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblIDHC, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEdad)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtDni))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jLabel12)
                    .addComponent(cbxAreas)
                    .addComponent(cbxServicio, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lblIDHC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbxProcedencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(chkAislado))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(cbxCama, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Caja_Preventa cp = new Caja_Preventa();
        btnGuardar.setEnabled(true);
        lblMant.setText("I");
        habilitarCampos(true);
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(guardarDatos()==true){
            JOptionPane.showMessageDialog(this,"Registro Guardado");
            limpiar();
            habilitarCampos(false);
            btnGuardar.setEnabled(false);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        FrmHospitalizacionListarCajaPreventa hospL = new FrmHospitalizacionListarCajaPreventa();
        dispose();
        hospL.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        dlgArticulos.setVisible(true);
        dlgArticulos.setLocationRelativeTo(null);//en el centro
        dlgArticulos.setResizable(false);
        dlgArticulos.getContentPane().setBackground(Color.WHITE);
        HospitalizacionPapeletas hosP = new HospitalizacionPapeletas();
        hosP.hospitalizacionArticulosListar(tbArticulos,txtBuscarArt.getText());
        tbArticulos.getSelectionModel().setSelectionInterval(0,0);
        txtBuscarArt.requestFocus();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBuscarNHCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNHCActionPerformed
        dlgNHC.setVisible(true);
        dlgNHC.setLocationRelativeTo(null);//en el centro
        dlgNHC.setResizable(false);
        dlgNHC.getContentPane().setBackground(Color.WHITE);
        hosP.buscar_HC(1, "A", txtBusqueda.getText(), tbNHC);
        tbNHC.getSelectionModel().setSelectionInterval(0,0);
        tbNHC.requestFocus();
    }//GEN-LAST:event_btnBuscarNHCActionPerformed

    private void txtBusquedaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBusquedaCaretUpdate
        hosP.buscar_HC(cbxTipoBusqueda.getSelectedIndex(), "A", txtBusqueda.getText(), tbNHC);
    }//GEN-LAST:event_txtBusquedaCaretUpdate

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbNHC.getSelectionModel().setSelectionInterval(0, 0);
            tbNHC.requestFocus();
        }
    }//GEN-LAST:event_txtBusquedaKeyPressed

    private void btnBuscarNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarNActionPerformed

    private void cbxTipoBusquedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoBusquedaItemStateChanged
        if(cbxTipoBusqueda.getSelectedIndex()>0){
            txtBusqueda.setEnabled(true);
            btnBuscarN.setEnabled(true);
            txtBusqueda.requestFocus();
        }else{
            txtBusqueda.setEnabled(false);
            btnBuscarN.setEnabled(false);
        }
    }//GEN-LAST:event_cbxTipoBusquedaItemStateChanged

    private void tbNHCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNHCKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbNHC.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            tbNHC.requestFocus();
            tbNHC.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tbNHC.getSelectedRow();
            dlgNHC.dispose();
            FrmHospitalizacionCajaPreventa.txtBuscarNHC.setText(String.valueOf(tbNHC.getValueAt(fila, 2)));
        }
    }//GEN-LAST:event_tbNHCKeyPressed

    private void txtBuscarNHCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarNHCCaretUpdate
        if(txtBuscarNHC.getText().length()==7){
            hosP.mostrarDatosPaciente(formatoNHC(txtBuscarNHC.getText()));
            cbxServicio.setSelectedIndex(0);
            cbxAreas.setSelectedIndex(0);
            if(lblEstado.getText().equals("D")){
                ImageIcon iT=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
                int pregunta = JOptionPane.showConfirmDialog(this, "Nº H.C. " + txtBuscarNHC.getText() + " eliminada, \ndesea restaurarla?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,iT);
                if(pregunta==0){
                    ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
                    int restaurar = JOptionPane.showConfirmDialog(this, "Seguro que desea restaurar? \n Nº H.C. " + txtBuscarNHC.getText() + "\nPaciente: " + txtPaciente.getText(),
                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    if(restaurar==0){
                        HistoriaClinica hC = new HistoriaClinica();
                        hC.setId_hc(lblIDHC.getText());
                        if(hC.restaurarHistoriaClinica()){
                            JOptionPane.showMessageDialog(this, "Nº H.C. " + txtBuscarNHC.getText() + "\n\t\t\trestaurada");
                            hosP.mostrarDatosPaciente(formatoNHC(txtBuscarNHC.getText()));
                        }
                    }else{
                        limpiarDatosNHC();
                        lblIDHC.setText("N");
                    }
                } else {
                    limpiarDatosNHC();
                    lblIDHC.setText("N");
                }
            }
            if(lblIDHC.getText().equals(""))
                JOptionPane.showMessageDialog(this, "H.C no existe");
        }else{
            limpiarDatosNHC();
        }
    }//GEN-LAST:event_txtBuscarNHCCaretUpdate

    private void cbxTipoBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoBusquedaActionPerformed

    private void cbxAreasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxAreasItemStateChanged
        if(cbxAreas.getSelectedIndex()>0){
            txtArea.setText(cbxAreas.getSelectedItem().toString());
        }
        else
            txtArea.setText("");
    }//GEN-LAST:event_cbxAreasItemStateChanged

    private void txtBuscarArtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarArtCaretUpdate
        HospitalizacionPapeletas hosP = new HospitalizacionPapeletas();
        hosP.hospitalizacionArticulosListar(tbArticulos,txtBuscarArt.getText());
        
        
    }//GEN-LAST:event_txtBuscarArtCaretUpdate

    private void txtBuscarArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarArtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarArtActionPerformed

    private void tbNHCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNHCMouseClicked
        if(evt.getClickCount()==2){
            int fila = tbNHC.getSelectedRow();
            dlgNHC.dispose();
            FrmHospitalizacionCajaPreventa.txtBuscarNHC.setText(String.valueOf(tbNHC.getValueAt(fila, 2)));
        }
    }//GEN-LAST:event_tbNHCMouseClicked

    private void tbArticulosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbArticulosKeyPressed
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_ENTER)
            enviarArticulos();
    }//GEN-LAST:event_tbArticulosKeyPressed

    private void chkAisladoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkAisladoItemStateChanged
        
    }//GEN-LAST:event_chkAisladoItemStateChanged

    private void chkAisladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAisladoActionPerformed
        String tipo = "";
        if(chkAislado.isSelected())
            tipo = "aislado";
        else
            tipo = "";
        try{  
            HospitalizacionPisos hos = new HospitalizacionPisos();
                    if(this.cbxServicio.getSelectedIndex()>0){
                        this.cbxCama.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    //int servicio = Integer.parseInt(hos.codServicio(cbxServicio.getSelectedItem().toString()));
                    String genero = lblGenero.getText();
                    
                    ResultSet rs=sta.executeQuery("EXEC HOSPITALIZACION_CAMAS_LISTAR_LIBRES '"+genero+"','" + tipo +"'");
                    this.cbxCama.addItem("Seleccionar...");
                    while(rs.next()){
                     this.cbxCama.addItem(rs.getString("CA_DESC"));
                  //  this.cbxProvincia.setModel(null);
                    }
                     }else{
                            this.cbxCama.removeAllItems();

                        this.cbxCama.addItem("Seleccionar...");
                            }

            }
            catch(Exception ex) 
            {
                System.out.println("Error: " + ex.getMessage());
            }
        if(cbxCama.getItemCount()==1){
            cbxCama.setEnabled(false);
        }else
            cbxCama.setEnabled(true);
    }//GEN-LAST:event_chkAisladoActionPerformed

    private void cbxAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAreasActionPerformed
      
    }//GEN-LAST:event_cbxAreasActionPerformed

    private void txtBuscarArtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarArtKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbArticulos.getSelectionModel().setSelectionInterval(0,0);
            tbArticulos.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarArtKeyPressed

    private void cbxServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxServicioActionPerformed

    private void cbxServicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxServicioItemStateChanged

        try{
            HospitalizacionPisos hos = new HospitalizacionPisos();
            if(this.cbxServicio.getSelectedIndex()==1){
                this.cbxCama.removeAllItems();
                Statement sta=conexion.createStatement();
                //int servicio = Integer.parseInt(hos.codServicio(cbxServicio.getSelectedItem().toString()));
                String genero = lblGenero.getText();

                ResultSet rs=sta.executeQuery("EXEC HOSPITALIZACION_CAMAS_LISTAR_LIBRES '"+genero+"','" + "" +"'");
                this.cbxCama.addItem("Seleccionar...");
                while(rs.next()){
                    this.cbxCama.addItem(rs.getString("CA_DESC"));
                    //  this.cbxProvincia.setModel(null);
                }
            }else{
                this.cbxCama.removeAllItems();

                this.cbxCama.addItem("Seleccionar...");
            }

        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        if(cbxCama.getItemCount()==1){
            cbxCama.setEnabled(false);
        }else
        cbxCama.setEnabled(true);
    }//GEN-LAST:event_cbxServicioItemStateChanged

    private void txtBuscarNHCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarNHCKeyPressed

    }//GEN-LAST:event_txtBuscarNHCKeyPressed

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
            java.util.logging.Logger.getLogger(FrmHospitalizacionCajaPreventa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionCajaPreventa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionCajaPreventa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionCajaPreventa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHospitalizacionCajaPreventa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarN;
    private javax.swing.JButton btnBuscarNHC;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox cbxAreas;
    public static javax.swing.JComboBox cbxCama;
    private javax.swing.JComboBox cbxProcedencia;
    public static javax.swing.JComboBox cbxServicio;
    private javax.swing.JComboBox cbxTipoBusqueda;
    private javax.swing.JCheckBox chkAislado;
    private javax.swing.JDialog dlgArticulos;
    private javax.swing.JDialog dlgNHC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JLabel lblEstado;
    public static javax.swing.JLabel lblGenero;
    public static javax.swing.JLabel lblID;
    public static javax.swing.JLabel lblIDHC;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblServicio;
    public static javax.swing.JLabel lblUsuUsuario;
    private javax.swing.JPanel pnlB;
    private javax.swing.JTable tbArticulos;
    private javax.swing.JTable tbNHC;
    public static javax.swing.JTable tbSelecArticulos;
    private javax.swing.JLabel titulo5;
    public static javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtBuscarArt;
    public static javax.swing.JTextField txtBuscarNHC;
    private javax.swing.JTextField txtBusqueda;
    public static javax.swing.JTextField txtDni;
    public static javax.swing.JTextField txtEdad;
    public static javax.swing.JEditorPane txtIndicaciones;
    public static javax.swing.JTextField txtPaciente;
    // End of variables declaration//GEN-END:variables
}
