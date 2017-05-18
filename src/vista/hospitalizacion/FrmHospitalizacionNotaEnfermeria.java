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
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import modelos.hospitalizacion.HospitalizacionEvolucion;
import modelos.hospitalizacion.HospitalizacionNotaEnfermeria;
import modelos.hospitalizacion.HospitalizacionPapeletas;

/**
 *
 * @author PC02
 */
public class FrmHospitalizacionNotaEnfermeria extends javax.swing.JFrame {

    /**
     * Creates new form FrmHospitalizacionNotaEnfermeria
     */
    public FrmHospitalizacionNotaEnfermeria() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
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
        HospitalizacionEvolucion hosEv = new HospitalizacionEvolucion();
        hosEv.inicializarTabla(tbNotEnf);
        txtIDPreventa.setVisible(false);
        lblMant.setVisible(false);
        txtID.setVisible(false);
        txtIdHe.setVisible(false);
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
    
    public void enviarDatosPac(){
        int fila = tbPacientesHosp.getSelectedRow();
        FrmHospitalizacionNotaEnfermeria.txtIDPreventa.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 0)));
        txtActoMedico.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 2)));
        txtNHC.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 3)));
        txtDNI.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 4)));
        txtPaciente.setText("Paciente: " + String.valueOf(tbPacientesHosp.getValueAt(fila, 5)));
        FrmHospitalizacionNotaEnfermeria.txtNroCama.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 7)));
        txtIndicaciones.setText("");
        txtNotEnfermeria.setText("");
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardar.setEnabled(true);
        lblMant.setText("I");
    }
    
    public void habilitarDatos(boolean opcion){
        btnBuscarPac.setEnabled(opcion);
        txtActoMedico.setEnabled(opcion);
        txtDNI.setEnabled(opcion);
        txtNHC.setEnabled(opcion);
        txtNroCama.setEnabled(opcion);
        txtPaciente.setEnabled(opcion);
        txtNotEnfermeria.setEnabled(opcion);
        txtIndicaciones.setEnabled(opcion);
        spHora.setEnabled(opcion);
    }
    
    public void limpiar(){
        txtActoMedico.setText("");
        txtDNI.setText("");
        txtNHC.setText("");
        txtNroCama.setText("");
        txtPaciente.setText("");
        txtNotEnfermeria.setText("");
        txtIndicaciones.setText("");
        txtIDPreventa.setText("");
        txtIdHe.setText("");
    }
    
    public boolean guardarDatosNotaEnfermeria(){
        boolean retorna = false;
        try {
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
            AdmisionEmergenciaCabecera adEmerCab5 = new AdmisionEmergenciaCabecera();
            if(txtNotEnfermeria.getText().equals("") || txtIndicaciones.getText().equals("") || txtActoMedico.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Debe completar todos los datos porfavor");
            } else {
                //int id = Integer.parseInt(txtID.getText());
                int id_preventa = Integer.parseInt(txtIDPreventa.getText());
                String indicaciones = txtIndicaciones.getText();
                String notEnfermeria = txtNotEnfermeria.getText();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formatoHora =  new SimpleDateFormat("HH:mm:ss");
                String fecha = formatoFecha.format(spHora.getValue());
                String hora = formatoHora.format(spHora.getValue());
                String cod_usu = adEmerCab5.codUsuario(lblUsuUsuario.getText());
                HospitalizacionNotaEnfermeria hosEnf = new HospitalizacionNotaEnfermeria();
                int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                               "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                if(guardar == 0){
                    hosEnf.setNota_enfermeria(notEnfermeria);
                    hosEnf.setIndicaciones(indicaciones);
                    hosEnf.setFecha_not(fecha);
                    hosEnf.setHora_not(hora);
                    hosEnf.setPreventa(id_preventa);
                    hosEnf.setUsuario(cod_usu);
                    if(hosEnf.mantenimientoHospitalizacionNotEnfermeria(lblMant.getText())==true){
                        txtIdHe.setText(hosEnf.hospitalizacionNotaEnfID());
                        JOptionPane.showMessageDialog(this, "Nota de enfermería Guardada");
                        hosEnf.listarNotaEnf(txtIDPreventa.getText(), tbNotEnf);
                        txtNotEnfermeria.setText("");
                        txtIndicaciones.setText("");
                        lblMant.setText("I");
                        btnGuardar.setEnabled(true);
                        btnModificar.setEnabled(false);
                        btnEliminar.setEnabled(false);
                    } else
                        JOptionPane.showMessageDialog(this, "No se realizó ningun registro");
                }else{
                        JOptionPane.showMessageDialog(this, "No se realizó ningun registro");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: guardarDatosNotaEnfermeria" + e.getMessage());
        }
        return retorna;
    }
    
    public boolean modificarDatosNotEnfemeria(){
        boolean retorna = false;
        try {
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
            if(txtIndicaciones.getText().equals("") || txtNotEnfermeria.getText().equals("") || txtActoMedico.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Debe completar todos los datos porfavor");
            } else {
                int fila = tbNotEnf.getSelectedRow();
                int id_preventa = Integer.parseInt(txtIDPreventa.getText());
                String indicaciones = txtIndicaciones.getText();
                String notEnfermeria = txtNotEnfermeria.getText();
                HospitalizacionNotaEnfermeria hosEnf = new HospitalizacionNotaEnfermeria();
                int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                               "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                if(guardar == 0){
                    hosEnf.setId(Integer.parseInt(String.valueOf(tbNotEnf.getValueAt(fila, 0))));
                    hosEnf.setNota_enfermeria(notEnfermeria);
                    hosEnf.setIndicaciones(indicaciones);
                    hosEnf.setPreventa(id_preventa);
                    if(hosEnf.mantenimientoHospitalizacionNotEnfermeria(lblMant.getText())==true){
                        txtIdHe.setText(hosEnf.hospitalizacionNotaEnfID());
                        JOptionPane.showMessageDialog(this, "Nota de Enfermeria Modificada");
                        hosEnf.listarNotaEnf(txtIDPreventa.getText(), tbNotEnf);
                        txtIndicaciones.setText("");
                        txtNotEnfermeria.setText("");
                        lblMant.setText("I");
                        btnGuardar.setEnabled(true);
                        btnModificar.setEnabled(false);
                        btnEliminar.setEnabled(false);
                    } else
                        JOptionPane.showMessageDialog(this, "No se realizó ningun cambio");
                }else{
                        JOptionPane.showMessageDialog(this, "No se realizó ningun cambio");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: modificarDatosNotEnfemeria" + e.getMessage());
        }
        return retorna;
    }
    
    public void eliminarRegistroNotEnf(){
        HospitalizacionNotaEnfermeria hosEnf = new HospitalizacionNotaEnfermeria();
        int fila = tbNotEnf.getSelectedRow();
        hosEnf.setId(Integer.parseInt(String.valueOf(tbNotEnf.getValueAt(fila, 0))));
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR este registro?",
                               "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
        if(eliminar == 0){
            if(hosEnf.mantenimientoHospitalizacionNotEnfermeria("E")){
                JOptionPane.showMessageDialog(this, "Registro eliminado");
                hosEnf.listarNotaEnf(txtIDPreventa.getText(), tbNotEnf);
                txtIndicaciones.setText("");
                txtNotEnfermeria.setText("");
                habilitarDatos(true);
                btnGuardar.setEnabled(true);
                lblMant.setText("I");
                btnEliminar.setEnabled(false);
                btnModificar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "No se realizó ninguna modificación");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se realizó ninguna modificación");
        }
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
        jPanel8 = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        lblUsuUsuario = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscarPac = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtIDPreventa = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        lblMant = new javax.swing.JLabel();
        txtIdHe = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNotEnfermeria = new javax.swing.JEditorPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbNotEnf = new javax.swing.JTable();
        txtNroCama = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        spHora = new javax.swing.JSpinner();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbProcedimientos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnBuscarPac1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtIndicaciones = new javax.swing.JEditorPane();
        jPanel3 = new javax.swing.JPanel();
        txtPaciente = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtActoMedico = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNHC = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel8.setBackground(new java.awt.Color(255, 73, 7));
        jPanel8.setPreferredSize(new java.awt.Dimension(500, 65));
        jPanel8.setLayout(null);

        titulo5.setBackground(new java.awt.Color(153, 0, 51));
        titulo5.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo5.setForeground(new java.awt.Color(255, 255, 255));
        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo5.setText("Nota de Enfermeria");
        titulo5.setToolTipText("");
        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel8.add(titulo5);
        titulo5.setBounds(10, 10, 260, 41);

        lblUsuUsuario.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblUsuUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuUsuario.setText("Silvana");
        jPanel8.add(lblUsuUsuario);
        lblUsuUsuario.setBounds(20, 220, 85, 20);

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
        btnEliminar.setToolTipText("Eliminar (Delete)");
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

        btnBuscarPac.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnBuscarPac.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarPac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscarPac.setMnemonic('B');
        btnBuscarPac.setText("Buscar Paciente");
        btnBuscarPac.setToolTipText("Buscar Paciente (Alt + B)");
        btnBuscarPac.setBorderPainted(false);
        btnBuscarPac.setContentAreaFilled(false);
        btnBuscarPac.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarPac.setEnabled(false);
        btnBuscarPac.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscarPac.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarPac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPacActionPerformed(evt);
            }
        });
        jPanel8.add(btnBuscarPac);
        btnBuscarPac.setBounds(0, 100, 180, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Alt + B");
        jPanel8.add(jLabel7);
        jLabel7.setBounds(170, 110, 50, 20);

        txtIDPreventa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIDPreventaCaretUpdate(evt);
            }
        });
        jPanel8.add(txtIDPreventa);
        txtIDPreventa.setBounds(0, 200, 210, 20);
        jPanel8.add(txtID);
        txtID.setBounds(290, 10, 70, 20);

        lblMant.setText("jLabel9");
        jPanel8.add(lblMant);
        lblMant.setBounds(100, 170, 34, 14);
        jPanel8.add(txtIdHe);
        txtIdHe.setBounds(100, 240, 110, 20);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtNotEnfermeria.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtNotEnfermeria.setEnabled(false);
        jScrollPane1.setViewportView(txtNotEnfermeria);

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel1.setText("Notas de enfemería");

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel2.setText("Indicaciones");

        jScrollPane3.setBorder(null);

        tbNotEnf = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbNotEnf.setModel(new javax.swing.table.DefaultTableModel(
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
        tbNotEnf.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbNotEnf.setSelectionBackground(new java.awt.Color(218, 209, 195));
        tbNotEnf.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tbNotEnf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNotEnfMouseClicked(evt);
            }
        });
        tbNotEnf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbNotEnfKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbNotEnfKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tbNotEnf);

        txtNroCama.setEditable(false);
        txtNroCama.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtNroCama.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNroCama.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel8.setText("Nº de Cama:");

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel10.setText("Fecha y Hora:");

        spHora.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        spHora.setModel(new javax.swing.SpinnerDateModel());
        spHora.setEnabled(false);

        jScrollPane4.setBorder(null);

        tbNotEnf = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbProcedimientos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbProcedimientos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbProcedimientos.setSelectionBackground(new java.awt.Color(218, 209, 195));
        tbProcedimientos.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tbProcedimientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProcedimientosMouseClicked(evt);
            }
        });
        tbProcedimientos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbProcedimientosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbProcedimientosKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tbProcedimientos);

        jPanel4.setBackground(new java.awt.Color(255, 65, 26));

        btnBuscarPac1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBuscarPac1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarPac1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Document-32.png"))); // NOI18N
        btnBuscarPac1.setMnemonic('B');
        btnBuscarPac1.setText("<html>Agregar <br>Procedimiento</html>");
        btnBuscarPac1.setToolTipText("Buscar Paciente (Alt + B)");
        btnBuscarPac1.setBorderPainted(false);
        btnBuscarPac1.setContentAreaFilled(false);
        btnBuscarPac1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarPac1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscarPac1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarPac1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPac1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscarPac1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(btnBuscarPac1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane5.setViewportView(txtIndicaciones);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(405, 405, 405)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNroCama, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spHora, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 90, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNroCama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(spHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1022, 1022, 1022))
        );

        jPanel3.setBackground(new java.awt.Color(43, 43, 43));

        txtPaciente.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txtPaciente.setForeground(new java.awt.Color(255, 255, 255));
        txtPaciente.setText("Nombres y Apellidos");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("Acto Médico");

        txtActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtActoMedico.setForeground(new java.awt.Color(204, 204, 204));
        txtActoMedico.setText("Nº");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("DNI");

        txtDNI.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtDNI.setForeground(new java.awt.Color(204, 204, 204));
        txtDNI.setText("DNI");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("Nº H.C.");

        txtNHC.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtNHC.setForeground(new java.awt.Color(204, 204, 204));
        txtNHC.setText("H.C.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtActoMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDNI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNHC, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtPaciente)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(txtPaciente)
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtActoMedico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtDNI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNHC)
                    .addComponent(jLabel12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Document-16.png"))); // NOI18N
        jLabel42.setText("Nuevo (Alt + N)");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Save-16.png"))); // NOI18N
        jLabel43.setText("Guardar (Alt + G)");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Edit-16.png"))); // NOI18N
        jLabel36.setText("Modificar (Alt + M)");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Eliminar-16.png"))); // NOI18N
        jLabel44.setText("Eliminar (Delete)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42)
                .addGap(6, 6, 6)
                .addComponent(jLabel43)
                .addGap(6, 6, 6)
                .addComponent(jLabel36)
                .addGap(6, 6, 6)
                .addComponent(jLabel44)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jLabel36)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try { 
            limpiar();
            habilitarDatos(true);
            lblMant.setText("I");
            btnGuardar.setEnabled(true);
            btnEliminar.setEnabled(false);
            btnModificar.setEnabled(false);
        } catch (Exception e) {
            System.out.println("Error: btnNuevo" + e.getMessage());
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(lblMant.getText().equals("I")) 
            guardarDatosNotaEnfermeria();
        else
            modificarDatosNotEnfemeria();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        lblMant.setText("U");
        btnGuardar.setEnabled(true);
        txtIndicaciones.setEnabled(true);
        txtNotEnfermeria.setEnabled(true);
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(tbNotEnf.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Seleccione un registro");
        } else {
                eliminarRegistroNotEnf();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarPacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacActionPerformed
        FrmBuscarPaciente.setVisible(true);
        FrmBuscarPaciente.setLocationRelativeTo(null);//en el centro
        FrmBuscarPaciente.setResizable(false);
        FrmBuscarPaciente.getContentPane().setBackground(Color.WHITE);
        HospitalizacionPapeletas hosp = new HospitalizacionPapeletas();
        hosp.listarPapeleta("C", txtBuscarPaciente.getText(), tbPacientesHosp,"F");
        txtBuscarPaciente.requestFocus();
    }//GEN-LAST:event_btnBuscarPacActionPerformed

    private void txtIDPreventaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIDPreventaCaretUpdate
        HospitalizacionNotaEnfermeria hosEnf = new HospitalizacionNotaEnfermeria();
        hosEnf.listarNotaEnf(txtIDPreventa.getText(), tbNotEnf);
    }//GEN-LAST:event_txtIDPreventaCaretUpdate

    private void tbNotEnfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNotEnfKeyPressed
        if(tbNotEnf.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Seleccione un registro");
        } else {
            if(evt.getKeyChar()==KeyEvent.VK_DELETE){
                eliminarRegistroNotEnf();
            }
        }
    }//GEN-LAST:event_tbNotEnfKeyPressed

    private void tbPacientesHospMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesHospMouseClicked
        if(evt.getClickCount()==2){
            FrmBuscarPaciente.dispose();
            enviarDatosPac();
        }
    }//GEN-LAST:event_tbPacientesHospMouseClicked

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

    private void txtBuscarPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPacienteKeyPressed

    }//GEN-LAST:event_txtBuscarPacienteKeyPressed

    private void tbNotEnfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNotEnfKeyReleased
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            int fila = tbNotEnf.getSelectedRow();
            txtNotEnfermeria.setText(String.valueOf(tbNotEnf.getValueAt(fila, 3)));
            txtIndicaciones.setText(String.valueOf(tbNotEnf.getValueAt(fila, 4)));
            spHora.setEnabled(false);
            btnEliminar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnGuardar.setEnabled(false);
            txtIndicaciones.setEnabled(false);
            txtNotEnfermeria.setEnabled(false);
        }
    }//GEN-LAST:event_tbNotEnfKeyReleased

    private void tbNotEnfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNotEnfMouseClicked
        if(evt.getClickCount()==1){
            int fila = tbNotEnf.getSelectedRow();
            txtNotEnfermeria.setText(String.valueOf(tbNotEnf.getValueAt(fila, 3)));
            txtIndicaciones.setText(String.valueOf(tbNotEnf.getValueAt(fila, 4)));
            spHora.setEnabled(false);
            btnEliminar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnGuardar.setEnabled(false);
            txtIndicaciones.setEnabled(false);
            txtNotEnfermeria.setEnabled(false);
        }
    }//GEN-LAST:event_tbNotEnfMouseClicked

    private void tbProcedimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProcedimientosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProcedimientosMouseClicked

    private void tbProcedimientosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProcedimientosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProcedimientosKeyPressed

    private void tbProcedimientosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProcedimientosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProcedimientosKeyReleased

    private void btnBuscarPac1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPac1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPac1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmHospitalizacionNotaEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionNotaEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionNotaEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionNotaEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHospitalizacionNotaEnfermeria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame FrmBuscarPaciente;
    private javax.swing.JButton btnBuscarPac;
    private javax.swing.JButton btnBuscarPac1;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblUsuUsuario;
    public static javax.swing.JSpinner spHora;
    private javax.swing.JTable tbNotEnf;
    private javax.swing.JTable tbPacientesHosp;
    private javax.swing.JTable tbProcedimientos;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel txtActoMedico;
    private javax.swing.JTextField txtBuscarPaciente;
    private javax.swing.JLabel txtDNI;
    private javax.swing.JTextField txtID;
    public static javax.swing.JTextField txtIDPreventa;
    private javax.swing.JTextField txtIdHe;
    private javax.swing.JEditorPane txtIndicaciones;
    private javax.swing.JLabel txtNHC;
    private javax.swing.JEditorPane txtNotEnfermeria;
    public static javax.swing.JTextField txtNroCama;
    private javax.swing.JLabel txtPaciente;
    // End of variables declaration//GEN-END:variables
}
