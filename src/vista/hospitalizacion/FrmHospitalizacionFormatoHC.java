/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.hospitalizacion;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelos.Caja.Caja_Preventa;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import modelos.hospitalizacion.HospitalizacionAsignacionCamas;
import modelos.hospitalizacion.HospitalizacionHCHospitalaria;
import modelos.hospitalizacion.HospitalizacionPapeletas;
import static vista.hospitalizacion.FrmHospitalizacionListarCajaPreventa.tbListarPapeleta;

/**
 *
 * @author PC02
 */
public class FrmHospitalizacionFormatoHC extends javax.swing.JFrame {

    /**
     * Creates new form FrmHospitalizacionFormatoHC
     */
    public FrmHospitalizacionFormatoHC() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        cbxTipoAtencion.setBackground(Color.WHITE);
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
    
    public void limpiar(){
        txtActoMedico.setText("");
        txtNHC.setText("");
        txtDNI.setText("");
        txtPaciente.setText("");
        txtNroCama.setText("");
        txtAnamnesis.setText("");
        txtEnfInfancia.setText("");
        txtEnfAdulto.setText("");
        txtTraumaAcci.setText("");
        txtHabitos.setText("");
        txtTiempEnf.setText("");
        txtMotEnfer.setText("");
        txtSintPrincipales.setText("");
        cbxTipoAtencion.setSelectedIndex(0);
        lblMant.setText("");
        lblID_Preventa.setText("");
        lblIdHc.setText("");
    }
    
    public void habilitarCajas(boolean opcion){
        txtActoMedico.setEnabled(opcion);
        txtNHC.setEnabled(opcion);
        txtDNI.setEnabled(opcion);
        txtPaciente.setEnabled(opcion);
        txtNroCama.setEnabled(opcion);
        txtAnamnesis.setEnabled(opcion);
        txtEnfInfancia.setEnabled(opcion);
        txtEnfAdulto.setEnabled(opcion);
        txtTraumaAcci.setEnabled(opcion);
        txtHabitos.setEnabled(opcion);
        txtTiempEnf.setEnabled(opcion);
        txtMotEnfer.setEnabled(opcion);
        txtSintPrincipales.setEnabled(opcion);
        cbxTipoAtencion.setEnabled(opcion);
        btnBuscar.setEnabled(opcion);
    }
    
    public void enviarDatosPac(){
        int fila = tbPacientesHosp.getSelectedRow();
        FrmHospitalizacionFormatoHC.lblID_Preventa.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 0)));
        FrmHospitalizacionFormatoHC.lblIdHc.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 1)));
        FrmHospitalizacionFormatoHC.txtActoMedico.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 2)));
        FrmHospitalizacionFormatoHC.txtNHC.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 3)));
        FrmHospitalizacionFormatoHC.txtDNI.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 4)));
        FrmHospitalizacionFormatoHC.txtPaciente.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 5)));
        FrmHospitalizacionFormatoHC.txtNroCama.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 7)));
    }

    public void enviarDatosaModificar(){
        int fila = tbFormatoHC.getSelectedRow();
        frmListarFormatoHC.dispose();
        FrmHospitalizacionFormatoHC.lblHHH_ID.setText(String.valueOf(tbFormatoHC.getValueAt(fila, 0)));
        FrmHospitalizacionFormatoHC.txtActoMedico.setText(String.valueOf(tbFormatoHC.getValueAt(fila, 1)));
        FrmHospitalizacionFormatoHC.txtNHC.setText(String.valueOf(tbFormatoHC.getValueAt(fila, 2)));
        FrmHospitalizacionFormatoHC.txtDNI.setText(String.valueOf(tbFormatoHC.getValueAt(fila, 3)));
        FrmHospitalizacionFormatoHC.txtPaciente.setText(String.valueOf(tbFormatoHC.getValueAt(fila, 4)));
        FrmHospitalizacionFormatoHC.cbxTipoAtencion.setSelectedItem(String.valueOf(tbFormatoHC.getValueAt(fila, 5)));
        FrmHospitalizacionFormatoHC.txtAnamnesis.setText(String.valueOf(tbFormatoHC.getValueAt(fila, 6)));
        FrmHospitalizacionFormatoHC.txtEnfInfancia.setText(String.valueOf(tbFormatoHC.getValueAt(fila, 7)));
        FrmHospitalizacionFormatoHC.txtEnfAdulto.setText(String.valueOf(tbFormatoHC.getValueAt(fila, 8)));
        FrmHospitalizacionFormatoHC.txtTraumaAcci.setText(String.valueOf(tbFormatoHC.getValueAt(fila, 9)));
        FrmHospitalizacionFormatoHC.txtHabitos.setText(String.valueOf(tbFormatoHC.getValueAt(fila, 10)));
        FrmHospitalizacionFormatoHC.txtTiempEnf.setText(String.valueOf(tbFormatoHC.getValueAt(fila, 11)));
        FrmHospitalizacionFormatoHC.txtMotEnfer.setText(String.valueOf(tbFormatoHC.getValueAt(fila, 12)));
        FrmHospitalizacionFormatoHC.txtSintPrincipales.setText(String.valueOf(tbFormatoHC.getValueAt(fila, 13)));
        FrmHospitalizacionFormatoHC.txtNroCama.setText(String.valueOf(tbFormatoHC.getValueAt(fila, 14)));
        habilitarCajas(false);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FrmBuscarPaciente = new javax.swing.JFrame();
        jPanel9 = new javax.swing.JPanel();
        titulo6 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbPacientesHosp = new javax.swing.JTable();
        txtBuscarPaciente = new javax.swing.JTextField();
        btnBuscarPaciente = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        frmListarFormatoHC = new javax.swing.JFrame();
        jPanel10 = new javax.swing.JPanel();
        titulo7 = new javax.swing.JLabel();
        lblUsuUsuario1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtBuscarActoMedico = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbFormatoHC = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtBuscarDatos = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        lblUsuUsuario = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbxTipoAtencion = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        txtActoMedico = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNHC = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPaciente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNroCama = new javax.swing.JTextField();
        lblID_Preventa = new javax.swing.JLabel();
        lblIdHc = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblMant = new javax.swing.JLabel();
        lblHHH_ID = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAnamnesis = new javax.swing.JEditorPane();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEnfInfancia = new javax.swing.JEditorPane();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtTraumaAcci = new javax.swing.JEditorPane();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtEnfAdulto = new javax.swing.JEditorPane();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtHabitos = new javax.swing.JEditorPane();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtTiempEnf = new javax.swing.JEditorPane();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtMotEnfer = new javax.swing.JEditorPane();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtSintPrincipales = new javax.swing.JEditorPane();
        jSeparator1 = new javax.swing.JSeparator();

        FrmBuscarPaciente.setAlwaysOnTop(true);
        FrmBuscarPaciente.setMinimumSize(new java.awt.Dimension(750, 450));

        jPanel9.setBackground(new java.awt.Color(217, 176, 86));
        jPanel9.setPreferredSize(new java.awt.Dimension(500, 65));
        jPanel9.setLayout(null);

        titulo6.setBackground(new java.awt.Color(153, 0, 51));
        titulo6.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo6.setForeground(new java.awt.Color(255, 255, 255));
        titulo6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo6.setText("Pacientes Hospitalizados");
        titulo6.setToolTipText("");
        titulo6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel9.add(titulo6);
        titulo6.setBounds(10, 10, 470, 41);

        jScrollPane9.setBorder(null);
        jScrollPane9.setOpaque(false);

        tbPacientesHosp = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbPacientesHosp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPacientesHosp.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbPacientesHosp.setSelectionBackground(new java.awt.Color(217, 176, 86));
        tbPacientesHosp.getTableHeader().setReorderingAllowed(false);
        tbPacientesHosp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPacientesHospMouseClicked(evt);
            }
        });
        tbPacientesHosp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPacientesHospKeyPressed(evt);
            }
        });
        jScrollPane9.setViewportView(tbPacientesHosp);

        jPanel9.add(jScrollPane9);
        jScrollPane9.setBounds(0, 110, 745, 315);

        txtBuscarPaciente.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarPacienteCaretUpdate(evt);
            }
        });
        txtBuscarPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarPacienteKeyPressed(evt);
            }
        });
        jPanel9.add(txtBuscarPaciente);
        txtBuscarPaciente.setBounds(10, 60, 230, 30);

        btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscarPaciente.setBorderPainted(false);
        btnBuscarPaciente.setContentAreaFilled(false);
        btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel9.add(btnBuscarPaciente);
        btnBuscarPaciente.setBounds(240, 60, 30, 30);

        jLabel16.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Acto Médico / Nº H.C. / Datos del Paciente");
        jPanel9.add(jLabel16);
        jLabel16.setBounds(10, 90, 220, 14);

        javax.swing.GroupLayout FrmBuscarPacienteLayout = new javax.swing.GroupLayout(FrmBuscarPaciente.getContentPane());
        FrmBuscarPaciente.getContentPane().setLayout(FrmBuscarPacienteLayout);
        FrmBuscarPacienteLayout.setHorizontalGroup(
            FrmBuscarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        FrmBuscarPacienteLayout.setVerticalGroup(
            FrmBuscarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        frmListarFormatoHC.setMinimumSize(new java.awt.Dimension(900, 600));
        frmListarFormatoHC.getContentPane().setLayout(null);

        jPanel10.setBackground(new java.awt.Color(217, 176, 86));
        jPanel10.setPreferredSize(new java.awt.Dimension(500, 65));
        jPanel10.setLayout(null);

        titulo7.setBackground(new java.awt.Color(153, 0, 51));
        titulo7.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo7.setForeground(new java.awt.Color(255, 255, 255));
        titulo7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo7.setText("Formatos Historia Clínica Hospitalaria");
        titulo7.setToolTipText("");
        titulo7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel10.add(titulo7);
        titulo7.setBounds(10, 10, 470, 41);

        lblUsuUsuario1.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblUsuUsuario1.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuUsuario1.setText("Silvana");
        jPanel10.add(lblUsuUsuario1);
        lblUsuUsuario1.setBounds(1190, 20, 85, 20);

        jLabel20.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
        jPanel10.add(jLabel20);
        jLabel20.setBounds(1150, 20, 32, 24);

        txtBuscarActoMedico.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarActoMedicoCaretUpdate(evt);
            }
        });
        txtBuscarActoMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActoMedicoActionPerformed(evt);
            }
        });
        txtBuscarActoMedico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarActoMedicoKeyPressed(evt);
            }
        });
        jPanel10.add(txtBuscarActoMedico);
        txtBuscarActoMedico.setBounds(100, 50, 120, 30);

        jScrollPane11.setBorder(null);
        jScrollPane11.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane11.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbFormatoHC = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbFormatoHC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbFormatoHC.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbFormatoHC.setSelectionBackground(new java.awt.Color(217, 176, 86));
        tbFormatoHC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFormatoHCKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbFormatoHCKeyReleased(evt);
            }
        });
        jScrollPane11.setViewportView(tbFormatoHC);

        jPanel10.add(jScrollPane11);
        jScrollPane11.setBounds(0, 112, 895, 460);

        jLabel18.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Acto Médico:");
        jPanel10.add(jLabel18);
        jLabel18.setBounds(10, 60, 90, 20);

        jLabel21.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Nº H.C. / DNI / Apellidos y nombres:");
        jPanel10.add(jLabel21);
        jLabel21.setBounds(10, 90, 230, 18);

        txtBuscarDatos.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarDatosCaretUpdate(evt);
            }
        });
        txtBuscarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarDatosActionPerformed(evt);
            }
        });
        txtBuscarDatos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarDatosKeyPressed(evt);
            }
        });
        jPanel10.add(txtBuscarDatos);
        txtBuscarDatos.setBounds(240, 80, 260, 30);

        frmListarFormatoHC.getContentPane().add(jPanel10);
        jPanel10.setBounds(0, 0, 900, 620);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Formato de Historia Clínica Hospitalaria");

        jPanel8.setBackground(new java.awt.Color(217, 176, 86));
        jPanel8.setPreferredSize(new java.awt.Dimension(500, 65));
        jPanel8.setLayout(null);

        titulo5.setBackground(new java.awt.Color(153, 0, 51));
        titulo5.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo5.setForeground(new java.awt.Color(255, 255, 255));
        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo5.setText("Formato Historia Clínica Hospitalaria");
        titulo5.setToolTipText("");
        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel8.add(titulo5);
        titulo5.setBounds(10, 10, 470, 41);

        lblUsuUsuario.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblUsuUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuUsuario.setText("Silvana");
        jPanel8.add(lblUsuUsuario);
        lblUsuUsuario.setBounds(1190, 20, 85, 20);

        jLabel19.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
        jPanel8.add(jLabel19);
        jLabel19.setBounds(1150, 20, 32, 24);

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
        btnNuevo.setBounds(10, 60, 24, 30);

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
        btnGuardar.setBounds(50, 60, 28, 30);

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
        btnModificar.setBounds(90, 60, 28, 30);

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
        btnEliminar.setBounds(130, 60, 28, 30);

        btnBuscar3.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscar3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBuscar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscar3.setToolTipText("Buscar (Alt + F3)");
        btnBuscar3.setContentAreaFilled(false);
        btnBuscar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar3.setFocusable(false);
        btnBuscar3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar3ActionPerformed(evt);
            }
        });
        btnBuscar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBuscar3KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnBuscar3KeyTyped(evt);
            }
        });
        jPanel8.add(btnBuscar3);
        btnBuscar3.setBounds(170, 60, 38, 32);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel1.setText("Tipo de atención:");

        cbxTipoAtencion.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        cbxTipoAtencion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Recuperativo Asistencial", "Rehabilitacion" }));
        cbxTipoAtencion.setEnabled(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-30.png"))); // NOI18N
        btnBuscar.setMnemonic('B');
        btnBuscar.setText("Buscar Paciente");
        btnBuscar.setToolTipText("Buscar Paciente (Alt + B)");
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setEnabled(false);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtActoMedico.setEditable(false);
        txtActoMedico.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel4.setText("Acto Médico:");

        txtNHC.setEditable(false);
        txtNHC.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel3.setText("Nº H.C.");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel5.setText("DNI:");

        txtDNI.setEditable(false);
        txtDNI.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel2.setText("Paciente:");

        txtPaciente.setEditable(false);
        txtPaciente.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel6.setText("Nº de Cama:");

        txtNroCama.setEditable(false);
        txtNroCama.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtNroCama.setEnabled(false);

        lblID_Preventa.setText("ID Preventa");

        lblIdHc.setText("ID HC");

        jLabel17.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel17.setText("Alt + B");

        lblMant.setText("Mantenimiento");

        lblHHH_ID.setText("HHH_ID");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtNHC, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addComponent(lblMant))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNroCama, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(178, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(lblID_Preventa, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(lblIdHc, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(lblHHH_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblID_Preventa)
                        .addComponent(lblIdHc)
                        .addComponent(jLabel17)
                        .addComponent(lblMant))
                    .addComponent(lblHHH_ID, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtNroCama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel7.setText("Anamnesis");

        txtAnamnesis.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtAnamnesis.setEnabled(false);
        jScrollPane2.setViewportView(txtAnamnesis);

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel8.setText("Antecedentes");

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel9.setText("Enfermedades de la infancia");

        txtEnfInfancia.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtEnfInfancia.setEnabled(false);
        jScrollPane1.setViewportView(txtEnfInfancia);

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel10.setText("Enfermedades de adulto");

        txtTraumaAcci.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtTraumaAcci.setEnabled(false);
        jScrollPane3.setViewportView(txtTraumaAcci);

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel11.setText("Trauma accidentes");

        txtEnfAdulto.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtEnfAdulto.setEnabled(false);
        jScrollPane4.setViewportView(txtEnfAdulto);

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel12.setText("Hábitos");

        txtHabitos.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtHabitos.setEnabled(false);
        jScrollPane5.setViewportView(txtHabitos);

        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel13.setText("Tiempo de Enfermedad");

        txtTiempEnf.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtTiempEnf.setEnabled(false);
        jScrollPane6.setViewportView(txtTiempEnf);

        jLabel14.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel14.setText("Motivo de Enfermedad");

        txtMotEnfer.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtMotEnfer.setEnabled(false);
        jScrollPane7.setViewportView(txtMotEnfer);

        jLabel15.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel15.setText("Síntomas Principales");

        txtSintPrincipales.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtSintPrincipales.setEnabled(false);
        jScrollPane8.setViewportView(txtSintPrincipales);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxTipoAtencion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane3))
                        .addGap(24, 24, 24)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane6)
                            .addComponent(jScrollPane7)
                            .addComponent(jLabel12)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                            .addComponent(jScrollPane8))
                        .addGap(21, 21, 21))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cbxTipoAtencion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addGap(1, 1, 1)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addGap(5, 5, 5)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        HospitalizacionHCHospitalaria hospHC = new HospitalizacionHCHospitalaria();
        if(hospHC.idHospitalizacionHCHospitalaria()!=0)
            lblHHH_ID.setText(String.valueOf(hospHC.idHospitalizacionHCHospitalaria()));
        else
            lblHHH_ID.setText("1");
        limpiar();
        habilitarCajas(true);
        btnGuardar.setEnabled(true);
        lblMant.setText("I");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        ImageIcon alerta=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png"));
        ImageIcon guarda=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Comprobado-60.png"));
        AdmisionEmergenciaCabecera ademer = new AdmisionEmergenciaCabecera();
        if(cbxTipoAtencion.getSelectedIndex()==0 || txtActoMedico.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un paciente \n y el tipo de atención", "Atención", WIDTH, alerta);
        } else {
            if(lblMant.getText().equals("I")){
                int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,alerta);
                HospitalizacionHCHospitalaria hospHC = new HospitalizacionHCHospitalaria();
                int hhh_id = Integer.parseInt(lblHHH_ID.getText());
                int id_preventa = Integer.parseInt(lblID_Preventa.getText());
                String tipo_atencion = cbxTipoAtencion.getSelectedItem().toString();
                String anamnesis = txtAnamnesis.getText();
                String enf_infancia = txtEnfInfancia.getText();
                String enf_adulto = txtEnfAdulto.getText();
                String trauma_acc = txtTraumaAcci.getText();
                String habitos = txtHabitos.getText();
                String tiempo_enf = txtTiempEnf.getText();
                String motivo_enf = txtMotEnfer.getText();
                String sintoma_prin = txtSintPrincipales.getText();
                String usuario = ademer.codUsuario(lblUsuUsuario.getText());
                if(guardar == 0){
                    hospHC.setHhh_id(hhh_id);
                    hospHC.setId_preventa(id_preventa);
                    hospHC.setTipo_atencion(tipo_atencion);
                    hospHC.setAnamnesis(anamnesis);
                    hospHC.setEnf_infancia(enf_infancia);
                    hospHC.setEnf_adulto(enf_adulto);
                    hospHC.setTrauma_acc(trauma_acc);
                    hospHC.setHabitos(habitos);
                    hospHC.setTiempo_enf(tiempo_enf);
                    hospHC.setMotivo_enf(motivo_enf);
                    hospHC.setSintoma_prin(sintoma_prin);
                    hospHC.setCod_usu(usuario);
                    if(hospHC.mantenimientoHospitalizacionHCHospitalaria(lblMant.getText())==true){
                        JOptionPane.showMessageDialog(this, "Registro Guardado con éxito", "Registro Guardado", WIDTH, guarda);
                        //REPORTE
                        //
                        //
                        //
                        //
                        limpiar();
                        habilitarCajas(false);
                        btnGuardar.setEnabled(false);
                    }
                }
            } else { //MODIFICAR FORMATO HC HOSPITALARIA
                int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,alerta);
                HospitalizacionHCHospitalaria hospHC = new HospitalizacionHCHospitalaria();
                int hhh_id = Integer.parseInt(lblHHH_ID.getText());
                String tipo_atencion = cbxTipoAtencion.getSelectedItem().toString();
                String anamnesis = txtAnamnesis.getText();
                String enf_infancia = txtEnfInfancia.getText();
                String enf_adulto = txtEnfAdulto.getText();
                String trauma_acc = txtTraumaAcci.getText();
                String habitos = txtHabitos.getText();
                String tiempo_enf = txtTiempEnf.getText();
                String motivo_enf = txtMotEnfer.getText();
                String sintoma_prin = txtSintPrincipales.getText();
                if(guardar == 0){
                    hospHC.setHhh_id(hhh_id);
                    hospHC.setTipo_atencion(tipo_atencion);
                    hospHC.setAnamnesis(anamnesis);
                    hospHC.setEnf_infancia(enf_infancia);
                    hospHC.setEnf_adulto(enf_adulto);
                    hospHC.setTrauma_acc(trauma_acc);
                    hospHC.setHabitos(habitos);
                    hospHC.setTiempo_enf(tiempo_enf);
                    hospHC.setMotivo_enf(motivo_enf);
                    hospHC.setSintoma_prin(sintoma_prin);
                    if(hospHC.mantenimientoHospitalizacionHCHospitalaria(lblMant.getText())==true){
                        JOptionPane.showMessageDialog(this, "Registro modificado con éxito", "Registro Modificado", WIDTH, guarda);
                        limpiar();
                        habilitarCajas(false);
                        btnGuardar.setEnabled(false);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        habilitarCajas(true);
        lblMant.setText("U");
        btnGuardar.setEnabled(true);
        btnEliminar.setEnabled(false);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        ImageIcon alerta=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png"));
        ImageIcon guarda=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Comprobado-60.png"));
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR los datos?",
                                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,alerta);
        HospitalizacionHCHospitalaria hosp = new HospitalizacionHCHospitalaria();
        if(eliminar == 0){
            hosp.setHhh_id(Integer.parseInt(lblHHH_ID.getText()));
            if(hosp.mantenimientoHospitalizacionHCHospitalaria("E")){
                JOptionPane.showMessageDialog(this, "Registro elimnado con éxito", "Registro Eliminado", WIDTH, guarda);
                limpiar();
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        FrmBuscarPaciente.setVisible(true);
        FrmBuscarPaciente.setLocationRelativeTo(null);//en el centro
        FrmBuscarPaciente.setResizable(false);
        FrmBuscarPaciente.getContentPane().setBackground(Color.WHITE);
        HospitalizacionPapeletas hosp = new HospitalizacionPapeletas();
        hosp.listarPapeleta("C", txtBuscarPaciente.getText(), tbPacientesHosp,"F");
        txtBuscarPaciente.requestFocus();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPacienteKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbPacientesHosp.getSelectionModel().setSelectionInterval(0,0);
            tbPacientesHosp.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarPacienteKeyPressed

    private void tbPacientesHospKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacientesHospKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbPacientesHosp.getSelectedRow()==0){
            tbPacientesHosp.getSelectionModel().setSelectionInterval(0,0);
            txtBuscarPaciente.requestFocus();
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            FrmBuscarPaciente.dispose();
            enviarDatosPac();
        }
    }//GEN-LAST:event_tbPacientesHospKeyPressed

    private void txtBuscarPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarPacienteCaretUpdate
        HospitalizacionPapeletas hosp = new HospitalizacionPapeletas();
        hosp.listarPapeleta("C", txtBuscarPaciente.getText(), tbPacientesHosp,"F");
    }//GEN-LAST:event_txtBuscarPacienteCaretUpdate

    private void tbPacientesHospMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesHospMouseClicked
        if(evt.getClickCount()==2){
            FrmBuscarPaciente.dispose();
            enviarDatosPac();
        }
    }//GEN-LAST:event_tbPacientesHospMouseClicked

    private void btnBuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar3ActionPerformed
        frmListarFormatoHC.setVisible(true);
        frmListarFormatoHC.setLocationRelativeTo(null);//en el centro
        frmListarFormatoHC.setResizable(false);
        frmListarFormatoHC.getContentPane().setBackground(Color.WHITE);
        HospitalizacionHCHospitalaria hosp = new HospitalizacionHCHospitalaria();
        hosp.listarFormatoHC(0, txtBuscarActoMedico.getText(), "A", tbFormatoHC);
        
    }//GEN-LAST:event_btnBuscar3ActionPerformed

    private void txtBuscarActoMedicoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarActoMedicoCaretUpdate
        if(txtBuscarActoMedico.getText().equals("")){
            txtBuscarDatos.setEnabled(true);
            HospitalizacionHCHospitalaria hosp = new HospitalizacionHCHospitalaria();
            hosp.listarFormatoHC(0, txtBuscarActoMedico.getText(), "A", tbFormatoHC);
        } else {
            HospitalizacionHCHospitalaria hosp = new HospitalizacionHCHospitalaria();
            hosp.listarFormatoHC(0, txtBuscarActoMedico.getText(), "A", tbFormatoHC);
            txtBuscarDatos.setEnabled(false);
        }
    }//GEN-LAST:event_txtBuscarActoMedicoCaretUpdate

    private void txtBuscarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarDatosActionPerformed
        
    }//GEN-LAST:event_txtBuscarDatosActionPerformed

    private void txtBuscarActoMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActoMedicoActionPerformed
       
    }//GEN-LAST:event_txtBuscarActoMedicoActionPerformed

    private void txtBuscarDatosCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarDatosCaretUpdate
        if(txtBuscarDatos.getText().equals("")){
            txtBuscarActoMedico.setEnabled(true);
            HospitalizacionHCHospitalaria hosp = new HospitalizacionHCHospitalaria();
            hosp.listarFormatoHC(0, txtBuscarDatos.getText(), "N", tbFormatoHC);
        } else {
            HospitalizacionHCHospitalaria hosp = new HospitalizacionHCHospitalaria();
            hosp.listarFormatoHC(0, txtBuscarDatos.getText(), "N", tbFormatoHC);
            txtBuscarActoMedico.setEnabled(false);
        }
    }//GEN-LAST:event_txtBuscarDatosCaretUpdate

    private void txtBuscarActoMedicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarActoMedicoKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            txtBuscarDatos.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarActoMedicoKeyPressed

    private void txtBuscarDatosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarDatosKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbFormatoHC.getSelectionModel().setSelectionInterval(0,0);
            tbFormatoHC.requestFocus();
        }
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            txtBuscarActoMedico.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarDatosKeyPressed

    private void tbFormatoHCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFormatoHCKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbFormatoHC.getSelectedRow()==0){
            tbFormatoHC.getSelectionModel().setSelectionInterval(0,0);
            txtBuscarDatos.requestFocus();
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosaModificar();
        }
    }//GEN-LAST:event_tbFormatoHCKeyPressed

    private void btnBuscar3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscar3KeyTyped
        
    }//GEN-LAST:event_btnBuscar3KeyTyped

    private void btnBuscar3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscar3KeyPressed

    }//GEN-LAST:event_btnBuscar3KeyPressed

    private void tbFormatoHCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFormatoHCKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbFormatoHCKeyReleased

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
            java.util.logging.Logger.getLogger(FrmHospitalizacionFormatoHC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionFormatoHC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionFormatoHC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionFormatoHC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHospitalizacionFormatoHC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame FrmBuscarPaciente;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar3;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    public static javax.swing.JComboBox cbxTipoAtencion;
    private javax.swing.JFrame frmListarFormatoHC;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JLabel lblHHH_ID;
    public static javax.swing.JLabel lblID_Preventa;
    public static javax.swing.JLabel lblIdHc;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblUsuUsuario;
    public static javax.swing.JLabel lblUsuUsuario1;
    private javax.swing.JTable tbFormatoHC;
    private javax.swing.JTable tbPacientesHosp;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel titulo7;
    public static javax.swing.JTextField txtActoMedico;
    public static javax.swing.JEditorPane txtAnamnesis;
    private javax.swing.JTextField txtBuscarActoMedico;
    private javax.swing.JTextField txtBuscarDatos;
    private javax.swing.JTextField txtBuscarPaciente;
    public static javax.swing.JTextField txtDNI;
    public static javax.swing.JEditorPane txtEnfAdulto;
    public static javax.swing.JEditorPane txtEnfInfancia;
    public static javax.swing.JEditorPane txtHabitos;
    public static javax.swing.JEditorPane txtMotEnfer;
    public static javax.swing.JTextField txtNHC;
    public static javax.swing.JTextField txtNroCama;
    public static javax.swing.JTextField txtPaciente;
    public static javax.swing.JEditorPane txtSintPrincipales;
    public static javax.swing.JEditorPane txtTiempEnf;
    public static javax.swing.JEditorPane txtTraumaAcci;
    // End of variables declaration//GEN-END:variables
}
