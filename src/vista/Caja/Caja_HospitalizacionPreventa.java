/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;

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
import modelos.Caja.Caja_NuevaVenta;
import modelos.Caja.Caja_Preventa;
import modelos.admisionCentral.HistoriaClinica;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import modelos.hospitalizacion.HospitalizacionArticuloDetalle;
import modelos.hospitalizacion.HospitalizacionAsignacionCamas;
import modelos.hospitalizacion.HospitalizacionPapeletas;
import modelos.hospitalizacion.HospitalizacionPisos;
import servicios.Conexion;
import vista.admisionEmergencia.FrmFormatoEmergencia;
import vista.Caja.Caja_Pagos;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lblPestana;
import static vista.admisionEmergencia.FrmFormatoEmergencia.lblPestanaMod;
import static vista.admisionEmergencia.FrmFormatoEmergencia.pnlEObservación;
import static vista.admisionEmergencia.FrmFormatoEmergencia.txtNHC;

/**
 *
 * @author PC02
 */
public class Caja_HospitalizacionPreventa extends javax.swing.JFrame {

    DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    ResultSet r;
    HospitalizacionPapeletas hosP = new HospitalizacionPapeletas();
    public Caja_HospitalizacionPreventa() {
        initComponents();
        this.setLocationRelativeTo(null);//en el centro
        this.setResizable(false);//deshabilita boton maximizar
        this.getContentPane().setBackground(Color.WHITE);//color blanco del formulario
        conexion = c.conectar();
        
        cbxServicio.setModel(servicios());
        cbxServicio.setBackground(Color.white);
        cbxAreas.setBackground(Color.white);
        cbxCama.setBackground(Color.white);
        habilitarCampos(true);
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
//        lblMant.setVisible(false);
//        lblID.setVisible(false);
//        lblGenero.setVisible(false);
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
    
    
    
    public void habilitarCampos(boolean opcion){
        cbxServicio.setEnabled(opcion);
        cbxAreas.setEnabled(opcion);
        cbxCama.setEnabled(opcion);
        btnAgregar.setEnabled(opcion);
        txtIndicaciones.setEnabled(opcion);
        chkAislado.setEnabled(opcion);
        txtArea.setEnabled(opcion);
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
        lblIDHC.setText("");
        lblEstado.setText("");
        lblGenero.setText("");
    }
    
    public void limpiarDatosServicio(){
        txtMedico.setText("");
        txtArea.setText("");
    }
    
    public void limpiar(){
        lblIDHC.setText("");
        lblEstado.setText("");
        lblGenero.setText("");
        cbxServicio.setSelectedIndex(0);
        cbxAreas.setSelectedIndex(0);
        txtMedico.setText("");
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
    
    public DefaultComboBoxModel servicios(){
           DefaultComboBoxModel  listmodelS = new DefaultComboBoxModel ();        
           String   sql = null;
           ResultSet rs = null;
           Statement  st = null;   
            try {
                  st = conexion.createStatement();
                  r = st.executeQuery ("EXEC CAJA_SERVICIOS_LISTAR"); 
                  listmodelS.addElement("Seleccionar...");
                while( r.next() ){
                    listmodelS.addElement( r.getString( "SE_DESC" ) );                
                 }
                r.close();
                
            } catch (SQLException ex) {            
                System.err.println( "FrmHospitalizacionPapelete Error: servicios: EXEC HOSPITALIZACION_MOSTRAR_SERVICIOS :" + ex.getMessage() );
            }        
        return listmodelS;
    }
        public DefaultComboBoxModel areas(){
           DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
           String   sql = null;
           ResultSet rs = null;
           Statement  st = null;   
            try {
                  st = conexion.createStatement();
                  r = st.executeQuery ("EXEC SERVICIO_AREAS_LISTAR ?"); 
                  listmodel.addElement("Seleccionar...");
                while( r.next() ){
                    listmodel.addElement( r.getString( "AR_DESC" ) );                
                 }
                r.close();
                
            } catch (SQLException ex) {            
                System.err.println( "FrmHospitalizacionPapelete Error: servicios: EXEC HOSPITALIZACION_MOSTRAR_AREAS :" + ex.getMessage() );
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
     public void AsignarCamas(){
  
                        Caja_Preventa cnoac = new Caja_Preventa();
                        int cama = Integer.parseInt(cnoac.codCama(cbxCama.getSelectedItem().toString()));
                        cnoac.setCA_ID(cama);
                      
                        if(cnoac.camas()==true){
                                   System.out.println("cama asignada");
                                 
                        } else {
                           
                                System.out.println("cama NO asignada");
                         
                        }
                       
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
   
            int id_preventa = 0;
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png"));


                Caja_Preventa cp = new Caja_Preventa();
                Caja_Preventa cp2 = new Caja_Preventa();
                HospitalizacionAsignacionCamas hc = new HospitalizacionAsignacionCamas();
                AdmisionEmergenciaCabecera ademer = new AdmisionEmergenciaCabecera();
                String id_hc = lblIDHC.getText();
                int area = Integer.parseInt(cp.codArea(cbxAreas.getSelectedItem().toString()));
                int cama = Integer.parseInt(cp.codCama(cbxCama.getSelectedItem().toString()));
                String indicaciones = txtIndicaciones.getText();
                String usuario = ademer.codUsuario(lblUsuUsuario.getText());
                int AM = Integer.parseInt(lblActoMedico.getText());
                String FP= lblJerarFP.getText();
                String Medic = lblIdMedic.getText();
       
                int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                if(guardar == 0){
                    cp.setId_hc(id_hc);
                    cp.setCA_ID(cama);
                    cp.sethOS_Indicaciones(indicaciones);
                    cp.setCod_usu(usuario);
                    cp.setAR_ID(area);
                    cp.setACTO_MEDICO(AM);
                    cp.setCod_jerar_forma_pago(FP);
                    cp.setCod_medico(Medic);
                    
                    if(cp.CAJA_mantenimientoPreventaHospitalizacion()==true){ 
                        System.out.println("PAPELETA DE HOSPITALKIZACION GENERADA");
                        AsignarCamas();
                        Caja_Pagos.lblIdMedico.setText(txtMedico.getText());
                        Caja_Pagos.GuardarPapeleta.setVisible(true);
                        dispose();
//                        cp.setCA_ID(cama);
//                        cp.setCOD_USU(usuario);
//                        cp.setID_PREVENTA(cp.CajaPreventaID());
//                       if(cp.CAJA_mantenimientoPreventaHospitalizacion()==true){
//                        retorna = true;
//                        }
                    }
                }
            

       
        return retorna;
    }
    
    public boolean guardarDatos(){
        boolean retorna = false;
        if(guardarDatosHospitalizacion()==true){
            if(tbSelecArticulos.getRowCount()==0){
                retorna = true;
            } else{
                Caja_Preventa cp = new Caja_Preventa();
                for (int i = 0; i < tbSelecArticulos.getRowCount(); i++){      
                    HospitalizacionArticuloDetalle hopsArt=new HospitalizacionArticuloDetalle();
                    hopsArt.setId_preventa(cp.CajaPreventaID());
                    hopsArt.setDescripcion(tbSelecArticulos.getValueAt(i, 1).toString());
                    hopsArt.setCod_usu(cp.codUsuario(lblUsuUsuario.getText()));
                    if(hopsArt.insertarArticuloDetalle()==true)
                        retorna = true;
                }
            }
        } else 
            return retorna;
        return retorna;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgArticulos = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbArticulos = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        txtBuscarArt = new javax.swing.JTextField();
        btnBuscarPaciente3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lblUsuUsuario = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblGenero = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblMant = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        lblIDHC = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtIndicaciones = new javax.swing.JEditorPane();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbxCama = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSelecArticulos = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        lblEstado = new javax.swing.JLabel();
        cbxServicio = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        cbxAreas = new javax.swing.JComboBox();
        chkAislado = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        lblNomPaciente = new javax.swing.JLabel();
        panelCPT = new javax.swing.JPanel();
        txtMedico = new javax.swing.JTextField();
        btnBuscarCPT = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblIdMedic = new javax.swing.JLabel();
        lblActoMedico = new javax.swing.JLabel();
        lblJerarFP = new javax.swing.JLabel();
        lblArea = new javax.swing.JLabel();
        lblCPT = new javax.swing.JLabel();

        dlgArticulos.setAlwaysOnTop(true);
        dlgArticulos.setMinimumSize(new java.awt.Dimension(400, 300));
        dlgArticulos.setResizable(false);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jScrollPane4.setForeground(new java.awt.Color(51, 51, 51));
        jScrollPane4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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
        tbArticulos.setSelectionBackground(new java.awt.Color(235, 105, 57));
        tbArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbArticulosMouseClicked(evt);
            }
        });
        tbArticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbArticulosKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tbArticulos);

        jPanel11.setBackground(new java.awt.Color(235, 105, 57));
        jPanel11.setMinimumSize(new java.awt.Dimension(310, 441));

        jLabel21.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Articulos");

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        txtBuscarArt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtBuscarArt.setForeground(new java.awt.Color(98, 98, 98));
        txtBuscarArt.setBorder(null);
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

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(txtBuscarArt, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtBuscarArt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnBuscarPaciente3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
        btnBuscarPaciente3.setContentAreaFilled(false);
        btnBuscarPaciente3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarPaciente3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPaciente3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscarPaciente3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(353, 353, 353))
        );

        javax.swing.GroupLayout dlgArticulosLayout = new javax.swing.GroupLayout(dlgArticulos.getContentPane());
        dlgArticulos.getContentPane().setLayout(dlgArticulosLayout);
        dlgArticulosLayout.setHorizontalGroup(
            dlgArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlgArticulosLayout.setVerticalGroup(
            dlgArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dlgArticulosLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel8.setBackground(new java.awt.Color(235, 105, 57));
        jPanel8.setPreferredSize(new java.awt.Dimension(500, 65));

        lblUsuUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblUsuUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
        lblUsuUsuario.setText("Silvana");

        btnModificar.setBackground(new java.awt.Color(204, 204, 204));
        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
        btnModificar.setText("Editar");
        btnModificar.setContentAreaFilled(false);
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setEnabled(false);
        btnModificar.setFocusable(false);
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnModificar.setIconTextGap(30);
        btnModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuardar.setIconTextGap(30);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setEnabled(false);
        btnEliminar.setFocusable(false);
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminar.setIconTextGap(30);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        lblGenero.setForeground(new java.awt.Color(235, 105, 57));
        lblGenero.setText("jLabel14");

        lblID.setForeground(new java.awt.Color(235, 105, 57));
        lblID.setText("jLabel14");

        lblMant.setForeground(new java.awt.Color(235, 105, 57));
        lblMant.setText("jLabel14");

        jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("<html>Papeleta<span style=\"font-size:'14px'\"><br>Hospitalización</br></span></html>");

        lblIDHC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblIDHC.setForeground(new java.awt.Color(235, 105, 57));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblUsuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(lblGenero)
                            .addGap(10, 10, 10)
                            .addComponent(lblMant)
                            .addGap(20, 20, 20)
                            .addComponent(lblID)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblIDHC, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblIDHC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGenero)
                    .addComponent(lblMant)
                    .addComponent(lblID))
                .addGap(53, 53, 53)
                .addComponent(lblUsuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Servicio");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Indicaciones para la enfermera");

        txtIndicaciones.setEnabled(false);
        jScrollPane1.setViewportView(txtIndicaciones);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Nota de Admisión");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("El paciente se interna en el Servicio de:");

        txtArea.setEditable(false);
        txtArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtArea.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Cama");

        cbxCama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxCama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));
        cbxCama.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxCama.setEnabled(false);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tbSelecArticulos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
        tbSelecArticulos.setRowHeight(38);
        tbSelecArticulos.setSelectionBackground(new java.awt.Color(235, 105, 57));
        tbSelecArticulos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbSelecArticulos);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Artículos que debe traer el Paciente");

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        lblEstado.setForeground(new java.awt.Color(255, 255, 255));

        cbxServicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Area");

        cbxAreas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        jPanel5.setBackground(new java.awt.Color(43, 43, 43));
        jPanel5.setPreferredSize(new java.awt.Dimension(929, 115));

        lblNomPaciente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNomPaciente.setForeground(new java.awt.Color(204, 204, 204));
        lblNomPaciente.setText("jLabel1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNomPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNomPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
        );

        panelCPT.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtMedico.setEditable(false);
        txtMedico.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtMedico.setForeground(new java.awt.Color(51, 51, 51));
        txtMedico.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtMedico.setBorder(null);
        txtMedico.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMedicoCaretUpdate(evt);
            }
        });

        btnBuscarCPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        btnBuscarCPT.setContentAreaFilled(false);
        btnBuscarCPT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarCPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCPTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCPTLayout = new javax.swing.GroupLayout(panelCPT);
        panelCPT.setLayout(panelCPTLayout);
        panelCPTLayout.setHorizontalGroup(
            panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMedico, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        panelCPTLayout.setVerticalGroup(
            panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPTLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMedico, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Médico");

        lblIdMedic.setForeground(new java.awt.Color(255, 255, 255));
        lblIdMedic.setText("jLabel2");

        lblActoMedico.setForeground(new java.awt.Color(255, 255, 255));
        lblActoMedico.setText("jLabel2");

        lblJerarFP.setForeground(new java.awt.Color(255, 255, 255));
        lblJerarFP.setText("jLabel2");

        lblArea.setForeground(new java.awt.Color(255, 255, 255));
        lblArea.setText("jLabel2");

        lblCPT.setForeground(new java.awt.Color(255, 255, 255));
        lblCPT.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblIdMedic)
                        .addGap(18, 18, 18)
                        .addComponent(lblActoMedico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblJerarFP)
                        .addGap(18, 18, 18)
                        .addComponent(lblArea)
                        .addGap(18, 18, 18)
                        .addComponent(lblCPT))
                    .addComponent(jLabel1)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12)
                                .addGap(6, 6, 6)
                                .addComponent(cbxAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(chkAislado)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbxCama, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(jLabel12)
                            .addComponent(cbxAreas)
                            .addComponent(cbxServicio)))
                    .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(chkAislado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(cbxCama, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdMedic)
                    .addComponent(lblActoMedico)
                    .addComponent(lblJerarFP)
                    .addComponent(lblArea)
                    .addComponent(lblCPT))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void cbxAreasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxAreasItemStateChanged
        Caja_Preventa cp = new Caja_Preventa();
        if(cbxAreas.getSelectedIndex()>0){
            txtArea.setText(cbxAreas.getSelectedItem().toString());
            lblArea.setText((cp.codArea(cbxAreas.getSelectedItem().toString()))); 
            
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
                    
                    ResultSet rs=sta.executeQuery("EXEC CAJA_CAMAS_LISTAR_LIBRES '"+genero+"','" + cbxServicio.getSelectedItem() +"','" + tipo+"'");
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
        }
        else
            txtArea.setText("");
    }//GEN-LAST:event_cbxAreasItemStateChanged

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
                    
                    ResultSet rs=sta.executeQuery("EXEC CAJA_CAMAS_LISTAR_LIBRES '"+genero+"','" + cbxServicio.getSelectedItem() +"','" + tipo+"'");
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

    private void cbxServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxServicioActionPerformed

    private void cbxServicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxServicioItemStateChanged
          try{  
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(this.cbxServicio.getSelectedIndex()>0){
                        this.cbxAreas.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    String dpto=cbxServicio.getSelectedItem().toString();
                    ResultSet rs=sta.executeQuery("EXEC CAJA_SERVICIO_AREAS_LISTAR '"+dpto+"'");
                    this.cbxAreas.addItem("Seleccionar...");
                    while(rs.next()){
                     this.cbxAreas.addItem(rs.getString("AR_DESC"));
                  //  this.cbxAreas.setModel(null);
                    }
                     }else{
                            this.cbxAreas.removeAllItems();

                        this.cbxAreas.addItem("Seleccionar...");
                            }

            }
     
            HospitalizacionPisos hos = new HospitalizacionPisos();
            if(this.cbxServicio.getSelectedIndex()==1){
                this.cbxCama.removeAllItems();
                Statement sta=conexion.createStatement();
                //int servicio = Integer.parseInt(hos.codServicio(cbxServicio.getSelectedItem().toString()));
                String genero = lblGenero.getText();

                ResultSet rs=sta.executeQuery("EXEC CAJA_CAMAS_LISTAR_LIBRES '"+genero+"','" +cbxServicio.getSelectedItem()+"','" + "" +"'");
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

    private void tbArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbArticulosMouseClicked
        if(evt.getClickCount()==2){
            enviarArticulos();
        }
    }//GEN-LAST:event_tbArticulosMouseClicked

    private void txtBuscarArtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarArtCaretUpdate
        HospitalizacionPapeletas hosP = new HospitalizacionPapeletas();
        hosP.hospitalizacionArticulosListar(tbArticulos,txtBuscarArt.getText());
        
    }//GEN-LAST:event_txtBuscarArtCaretUpdate

    private void txtBuscarArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarArtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarArtActionPerformed

    private void txtBuscarArtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarArtKeyPressed
       if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbArticulos.getSelectionModel().setSelectionInterval(0,0);
            tbArticulos.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarArtKeyPressed

    private void btnBuscarPaciente3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente3ActionPerformed

    private void txtMedicoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMedicoCaretUpdate

    }//GEN-LAST:event_txtMedicoCaretUpdate

    private void btnBuscarCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPTActionPerformed
            Caja_NuevaVenta CNV = new Caja_NuevaVenta();
            Caja_Pagos.MedicosPepeleta.setVisible(true);
            CNV.listarMedicosPapeleta(lblArea.getText(),Caja_Pagos.tb_medicosPapeleta);
            Caja_Pagos.BMedicosPapeleta.setText(null);
            Caja_Pagos.BMedicosPapeleta.requestFocus();    
    }//GEN-LAST:event_btnBuscarCPTActionPerformed

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
            java.util.logging.Logger.getLogger(Caja_HospitalizacionPreventa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_HospitalizacionPreventa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_HospitalizacionPreventa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_HospitalizacionPreventa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_HospitalizacionPreventa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarCPT;
    private javax.swing.JButton btnBuscarPaciente3;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox cbxAreas;
    public static javax.swing.JComboBox cbxCama;
    public static javax.swing.JComboBox cbxServicio;
    private javax.swing.JCheckBox chkAislado;
    private javax.swing.JDialog dlgArticulos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JLabel lblActoMedico;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblCPT;
    public static javax.swing.JLabel lblEstado;
    public static javax.swing.JLabel lblGenero;
    public static javax.swing.JLabel lblID;
    public static javax.swing.JLabel lblIDHC;
    public static javax.swing.JLabel lblIdMedic;
    public static javax.swing.JLabel lblJerarFP;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblNomPaciente;
    public static javax.swing.JLabel lblUsuUsuario;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JTable tbArticulos;
    public static javax.swing.JTable tbSelecArticulos;
    public static javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtBuscarArt;
    public static javax.swing.JEditorPane txtIndicaciones;
    public static javax.swing.JTextField txtMedico;
    // End of variables declaration//GEN-END:variables
}
