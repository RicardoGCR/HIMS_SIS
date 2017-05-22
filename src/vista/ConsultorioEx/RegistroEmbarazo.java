/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import campos.LimitadorDeDocumento;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalCabecera;
import modelos.ConsultorioEx.ConsultorioExtEsnitss;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;


/**
 *
 * @author MYS1
 */
public class RegistroEmbarazo extends javax.swing.JFrame {

    String estadoSeleccion = "";
    ConsultorioExtCarnetPerinatalCabecera cabecera = new ConsultorioExtCarnetPerinatalCabecera();
    public RegistroEmbarazo() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
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
        jTabbedPane1.setEnabledAt(0,false);
        jTabbedPane1.setEnabledAt(1, false);
        pnlControl.setVisible(false);
        pnlMensaje.setVisible(false);
        btnGuardar.setVisible(false);
        LimitadorDeDocumento limitCodigo = new LimitadorDeDocumento(13);
        txtCodigo.setDocument(limitCodigo);
        LimitadorDeDocumento limitAp = new LimitadorDeDocumento(2);
        ChkAnAp.setDocument(limitAp);
        LimitadorDeDocumento limitEstOrigen = new LimitadorDeDocumento(200);
        txtEstabOrigen.setDocument(limitEstOrigen);
        LimitadorDeDocumento limitPadreRN = new LimitadorDeDocumento(250);
        txtPadreRN.setDocument(limitPadreRN);
        lblMant.setVisible(false);
        txtIdHc.setVisible(false);
        lblTriaje.setVisible(false);
        lblId.setVisible(false);
        btnInicio.setVisible(false);
        btnRegistrosExistentes.setVisible(false);
        btnAtras.setVisible(false);
        btnNuevoEmbarazo.setEnabled(false);
        btnGuardar.setEnabled(false);
        cabecera.conteoAlertas();
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
        txtEstabOrigen.setText("");
        chkNoAplica.setText("");
        chkRef.setText("");
//        txtEstablecimiento.setText("");
        chkSis.setSelected(false);
        chkEssalud.setSelected(false);
        chkPrivado.setSelected(false);
        txtCodigo.setText("");
        ChkAnalf.setText("");
        Chkprim.setText("");
        ChkSec.setText("");
        ChkSup.setText("");
        ChkSupnU.setText("");
        ChkAnAp.setText("");
        txtPadreRN.setText("");
    }

    public void enviarDatosMadres(){   
        limpiar();
        int fila = tbMadres.getSelectedRow();
        lblTriaje.setText(String.valueOf(tbMadres.getValueAt(fila, 0)));
        lblActoMed.setText(String.valueOf(tbMadres.getValueAt(fila, 1)));
        lblDni.setText(String.valueOf(tbMadres.getValueAt(fila, 2)));
        txtPaciente.setText(String.valueOf(tbMadres.getValueAt(fila, 4)));
        lblHc.setText(String.valueOf(tbMadres.getValueAt(fila, 3)));
        txtIdHc.setText(String.valueOf(tbMadres.getValueAt(fila, 13)));
        pnlControl.setVisible(true);
        btnGuardar.setVisible(true);
        lblMant.setText("I");
        ConsultorioExtCarnetPerinatalCabecera consultorio1 = new ConsultorioExtCarnetPerinatalCabecera();
        consultorio1.nombreEstablecimiento();
        consultorio1.mantenimientoConsultorioExtCarnetPerinatalCabecera("T",String.valueOf(tbMadres.getValueAt(fila, 0)));//para cambiar el estado de triaje de pendiente a ya atendido
        btnGuardar.setEnabled(true);
    }

    public boolean mantenimientoRegistroEmbarazo(){
        boolean retorna = false;
        try {
            ConsultorioExtCarnetPerinatalCabecera consultorio1 = new ConsultorioExtCarnetPerinatalCabecera();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMant.getText().equals("U") || lblMant.getText().equals("E"))
                consultorio1.setCpId(Integer.parseInt(lblId.getText()));
            consultorio1.setIdHc(txtIdHc.getText());
            consultorio1.setCpEstbOrigen(txtEstabOrigen.getText());
            consultorio1.setCpAniosAprob(ChkAnAp.getText());
            consultorio1.setCpEstbAct(txtEstablecimiento.getText());
            if(chkSis.isSelected())
                consultorio1.setCpTipoSeguro("SIS");
            else
            if(chkEssalud.isSelected())
                consultorio1.setCpTipoSeguro("ESSALUD");
            else
            if(chkPrivado.isSelected())
                consultorio1.setCpTipoSeguro("PRIVADO");
            else
                consultorio1.setCpTipoSeguro("");
            
            consultorio1.setCpEdad(lblEdad.getText());
            consultorio1.setCpCodigoAfil(txtCodigo.getText());
            if(ChkAnalf.getText().equals("X"))
                consultorio1.setCpEstudios("Analfabeta");
            else
            if(Chkprim.getText().equals("X"))
                consultorio1.setCpEstudios("Primaria");
            else
            if(ChkSec.getText().equals("X"))
                consultorio1.setCpEstudios("Secundaria");
            else
            if(ChkSup.getText().equals("X"))
                consultorio1.setCpEstudios("Superior");
            else
            if(ChkSupnU.getText().equals("X"))
                consultorio1.setCpEstudios("Superior No Univ.");
            else
                consultorio1.setCpEstudios("");
            consultorio1.setCpPadreRn(txtPadreRN.getText());
            consultorio1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalCabecera(lblMant.getText(),lblTriaje.getText())==true){
                RegistroEmbarazoPrincipal GA =new RegistroEmbarazoPrincipal();
                Contenedor.add(GA);
                try {
                    GA.setMaximum(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
                jTabbedPane1.setSelectedIndex(1);
                if(lblMant.getText().equals("I")){
                    System.out.println("ID CARNET PERINATAL CABECERA: " + Integer.parseInt(consultorio1.perinatalCabeceraID()));
                    RegistroEmbarazoPrincipal.lblId.setText(consultorio1.perinatalCabeceraID());
                    RegistroEmbarazoPrincipal.lblMadre.setText(txtPaciente.getText());
                }
                if(lblMant.getText().equals("U")){
                    RegistroEmbarazoPrincipal.lblId.setText(lblId.getText());
                }
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                btnGuardar.setVisible(false);
                pnlMensaje.setBackground(new Color(33,115,70));
                btnSi.setVisible(true);
                btnSi.setText("OK");
                btnNo.setVisible(false);
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ocurrió un error, verifique");
                pnlMensaje.setBackground(new Color(255,91,70));
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error : mantenimiento Registro Embarazo" + e.getMessage());
        }
        return retorna;
    }
    
    public void enviaDatosExistentes(){
        int fila = tbEmbarazoActual.getSelectedRow();
        lblId.setText(String.valueOf(tbEmbarazoActual.getValueAt(fila, 0)));
        pnlControl.setVisible(true);
        BuscarMadres.dispose();
        btnInicio.setVisible(true);
    }
    
    public void enviarDatosCabecera(){
        ConsultorioExtCarnetPerinatalCabecera consultorio1 = new ConsultorioExtCarnetPerinatalCabecera();
        consultorio1.listarCabecera(lblId.getText());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BuscarMadres = new javax.swing.JDialog();
        jPanel28 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        T7 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        btnNuevoEmbarazo = new javax.swing.JButton();
        btnRegistrosExistentes = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbMadres = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel23 = new javax.swing.JPanel();
            jScrollPane2 = new javax.swing.JScrollPane();
            tbEmbarazoAntiguos = new javax.swing.JTable();
            jLabel12 = new javax.swing.JLabel();
            jLabel13 = new javax.swing.JLabel();
            jScrollPane1 = new javax.swing.JScrollPane();
            tbEmbarazoActual = new javax.swing.JTable();
            buttonGroup1 = new javax.swing.ButtonGroup();
            buttonGroup2 = new javax.swing.ButtonGroup();
            jPanel19 = new javax.swing.JPanel();
            jTabbedPane1 = new javax.swing.JTabbedPane();
            jPanel2 = new javax.swing.JPanel();
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            lblusu = new javax.swing.JLabel();
            btnAlertas = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            jLabel2 = new javax.swing.JLabel();
            ChkAnalf1 = new javax.swing.JTextField();
            jLabel30 = new javax.swing.JLabel();
            ChkEdad1 = new javax.swing.JTextField();
            jLabel37 = new javax.swing.JLabel();
            btnNuevo = new javax.swing.JButton();
            btnInicio = new javax.swing.JButton();
            lblAlertas = new javax.swing.JLabel();
            pnlControl = new javax.swing.JPanel();
            jLabel20 = new javax.swing.JLabel();
            jPanel8 = new javax.swing.JPanel();
            Chkprim = new javax.swing.JTextField();
            jLabel32 = new javax.swing.JLabel();
            jPanel9 = new javax.swing.JPanel();
            ChkSec = new javax.swing.JTextField();
            jLabel33 = new javax.swing.JLabel();
            jPanel11 = new javax.swing.JPanel();
            ChkSup = new javax.swing.JTextField();
            jLabel34 = new javax.swing.JLabel();
            jLabel29 = new javax.swing.JLabel();
            jPanel4 = new javax.swing.JPanel();
            jLabel28 = new javax.swing.JLabel();
            jLabel27 = new javax.swing.JLabel();
            jLabel39 = new javax.swing.JLabel();
            jLabel25 = new javax.swing.JLabel();
            lblOcupacion = new javax.swing.JLabel();
            lblEstadoCiv = new javax.swing.JLabel();
            jPanel12 = new javax.swing.JPanel();
            ChkSupnU = new javax.swing.JTextField();
            jLabel35 = new javax.swing.JLabel();
            jPanel5 = new javax.swing.JPanel();
            ChkAnalf = new javax.swing.JTextField();
            jLabel31 = new javax.swing.JLabel();
            jLabel44 = new javax.swing.JLabel();
            jPanel13 = new javax.swing.JPanel();
            ChkAnAp = new javax.swing.JTextField();
            jLabel36 = new javax.swing.JLabel();
            txtCodigo = new javax.swing.JTextField();
            jLabel23 = new javax.swing.JLabel();
            chkPrivado = new javax.swing.JCheckBox();
            chkEssalud = new javax.swing.JCheckBox();
            chkSis = new javax.swing.JCheckBox();
            jLabel22 = new javax.swing.JLabel();
            txtEstablecimiento = new javax.swing.JTextField();
            jLabel21 = new javax.swing.JLabel();
            lblCelular = new javax.swing.JLabel();
            jLabel18 = new javax.swing.JLabel();
            lblTelefono = new javax.swing.JLabel();
            jLabel8 = new javax.swing.JLabel();
            jLabel7 = new javax.swing.JLabel();
            lblDistrito = new javax.swing.JLabel();
            lblProvincia = new javax.swing.JLabel();
            jLabel14 = new javax.swing.JLabel();
            lblDepartamento = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            jLabel11 = new javax.swing.JLabel();
            lblSector = new javax.swing.JLabel();
            txtIdHc = new javax.swing.JTextField();
            lblMant = new javax.swing.JLabel();
            lblDireccion = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            lblDni = new javax.swing.JLabel();
            jLabel10 = new javax.swing.JLabel();
            jPanel21 = new javax.swing.JPanel();
            chkRef = new javax.swing.JTextField();
            jLabel47 = new javax.swing.JLabel();
            jPanel20 = new javax.swing.JPanel();
            chkNoAplica = new javax.swing.JTextField();
            jLabel46 = new javax.swing.JLabel();
            jPanel15 = new javax.swing.JPanel();
            txtEstabOrigen = new javax.swing.JTextField();
            jLabel38 = new javax.swing.JLabel();
            lblHc = new javax.swing.JLabel();
            jLabel48 = new javax.swing.JLabel();
            jPanel10 = new javax.swing.JPanel();
            txtPaciente = new javax.swing.JTextField();
            jLabel3 = new javax.swing.JLabel();
            lblActoMed = new javax.swing.JLabel();
            jLabel9 = new javax.swing.JLabel();
            jPanel7 = new javax.swing.JPanel();
            jPanel6 = new javax.swing.JPanel();
            lblEdad = new javax.swing.JTextField();
            txtPadreRN = new javax.swing.JTextField();
            lblTriaje = new javax.swing.JLabel();
            lblId = new javax.swing.JTextField();
            ChkEdad = new javax.swing.JTextField();
            pnlMensaje = new javax.swing.JPanel();
            lblMensaje = new javax.swing.JLabel();
            btnSi = new javax.swing.JButton();
            btnNo = new javax.swing.JButton();
            Contenedor = new javax.swing.JDesktopPane();
            ContenedorTablas = new javax.swing.JDesktopPane();

            BuscarMadres.setAlwaysOnTop(true);
            BuscarMadres.setMinimumSize(new java.awt.Dimension(855, 422));
            BuscarMadres.setResizable(false);
            BuscarMadres.getContentPane().setLayout(null);

            jPanel28.setBackground(new java.awt.Color(102, 102, 102));

            jLabel45.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
            jLabel45.setForeground(new java.awt.Color(255, 255, 255));
            jLabel45.setText("<html>Madre<span style=\"font-size:'15px'\"><br>del menor</br></span></html>");

            jPanel29.setBackground(new java.awt.Color(255, 255, 255));

            txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txtBuscar.setBorder(null);
            txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtBuscarCaretUpdate(evt);
                }
            });
            txtBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    txtBuscarMouseClicked(evt);
                }
            });
            txtBuscar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtBuscarActionPerformed(evt);
                }
            });
            txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtBuscarKeyPressed(evt);
                }
            });

            T7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
            T7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            T7.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    T7MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
            jPanel29.setLayout(jPanel29Layout);
            jPanel29Layout.setHorizontalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(T7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            jPanel29Layout.setVerticalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(T7)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            jPanel30.setBackground(new java.awt.Color(0, 153, 102));
            jPanel30.setPreferredSize(new java.awt.Dimension(46, 5));

            javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
            jPanel30.setLayout(jPanel30Layout);
            jPanel30Layout.setHorizontalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 46, Short.MAX_VALUE)
            );
            jPanel30Layout.setVerticalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 5, Short.MAX_VALUE)
            );

            btnNuevoEmbarazo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnNuevoEmbarazo.setForeground(new java.awt.Color(240, 240, 240));
            btnNuevoEmbarazo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Document-32.png"))); // NOI18N
            btnNuevoEmbarazo.setText("Nuevo");
            btnNuevoEmbarazo.setBorder(null);
            btnNuevoEmbarazo.setContentAreaFilled(false);
            btnNuevoEmbarazo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnNuevoEmbarazo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            btnNuevoEmbarazo.setIconTextGap(10);
            btnNuevoEmbarazo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            btnNuevoEmbarazo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnNuevoEmbarazoActionPerformed(evt);
                }
            });

            btnRegistrosExistentes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnRegistrosExistentes.setForeground(new java.awt.Color(240, 240, 240));
            btnRegistrosExistentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Bebé-32.png"))); // NOI18N
            btnRegistrosExistentes.setText("Registros Existente");
            btnRegistrosExistentes.setBorder(null);
            btnRegistrosExistentes.setContentAreaFilled(false);
            btnRegistrosExistentes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnRegistrosExistentes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            btnRegistrosExistentes.setIconTextGap(10);
            btnRegistrosExistentes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            btnRegistrosExistentes.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnRegistrosExistentesActionPerformed(evt);
                }
            });

            btnAtras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAtras.setForeground(new java.awt.Color(240, 240, 240));
            btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Bebé-32.png"))); // NOI18N
            btnAtras.setText("Atrás");
            btnAtras.setBorder(null);
            btnAtras.setContentAreaFilled(false);
            btnAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAtras.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            btnAtras.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            btnAtras.setIconTextGap(10);
            btnAtras.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            btnAtras.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAtrasActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
            jPanel28.setLayout(jPanel28Layout);
            jPanel28Layout.setHorizontalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNuevoEmbarazo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel28Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(btnAtras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel28Layout.createSequentialGroup()
                            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnRegistrosExistentes))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            jPanel28Layout.setVerticalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnNuevoEmbarazo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnRegistrosExistentes, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE))
            );

            BuscarMadres.getContentPane().add(jPanel28);
            jPanel28.setBounds(0, 0, 178, 370);

            jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);

            jPanel22.setBackground(new java.awt.Color(255, 255, 255));

            jScrollPane4.setBorder(null);
            jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

            tbMadres.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ));
            tbMadres.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tbMadres.setGridColor(new java.awt.Color(255, 255, 255));
            tbMadres.setRowHeight(25);
            tbMadres.setSelectionBackground(new java.awt.Color(39, 174, 97));
            tbMadres.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbMadresMouseClicked(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    tbMadresMousePressed(evt);
                }
            });
            tbMadres.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbMadresKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    tbMadresKeyReleased(evt);
                }
            });
            jScrollPane4.setViewportView(tbMadres);

            javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
            jPanel22.setLayout(jPanel22Layout);
            jPanel22Layout.setHorizontalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE))
            );
            jPanel22Layout.setVerticalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
            );

            jTabbedPane2.addTab("tab1", jPanel22);

            jPanel23.setBackground(new java.awt.Color(255, 255, 255));

            jScrollPane2.setBorder(null);

            tbEmbarazoAntiguos = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                tbEmbarazoAntiguos.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                tbEmbarazoAntiguos.getTableHeader().setReorderingAllowed(false);
                jScrollPane2.setViewportView(tbEmbarazoAntiguos);

                jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                jLabel12.setForeground(new java.awt.Color(51, 51, 51));
                jLabel12.setText("Embarazo Actual");

                jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                jLabel13.setForeground(new java.awt.Color(51, 51, 51));
                jLabel13.setText("Embarazos Anteriores");

                jScrollPane1.setBorder(null);
                jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

                tbEmbarazoActual = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    tbEmbarazoActual.setBackground(new java.awt.Color(232, 76, 61));
                    tbEmbarazoActual.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
                    tbEmbarazoActual.setForeground(new java.awt.Color(255, 255, 255));
                    tbEmbarazoActual.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "null", "null", "null"
                        }
                    ) {
                        boolean[] canEdit = new boolean [] {
                            false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return canEdit [columnIndex];
                        }
                    });
                    tbEmbarazoActual.setSelectionBackground(new java.awt.Color(232, 76, 61));
                    tbEmbarazoActual.getTableHeader().setReorderingAllowed(false);
                    tbEmbarazoActual.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tbEmbarazoActualMouseClicked(evt);
                        }
                    });
                    tbEmbarazoActual.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tbEmbarazoActualKeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            tbEmbarazoActualKeyReleased(evt);
                        }
                    });
                    jScrollPane1.setViewportView(tbEmbarazoActual);

                    javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
                    jPanel23.setLayout(jPanel23Layout);
                    jPanel23Layout.setHorizontalGroup(
                        jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel23Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel13))
                            .addContainerGap(525, Short.MAX_VALUE))
                        .addComponent(jScrollPane1)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                    );
                    jPanel23Layout.setVerticalGroup(
                        jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel23Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                    );

                    jTabbedPane2.addTab("tab2", jPanel23);

                    BuscarMadres.getContentPane().add(jTabbedPane2);
                    jTabbedPane2.setBounds(132, 0, 730, 370);

                    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                    setMinimumSize(new java.awt.Dimension(1282, 707));
                    getContentPane().setLayout(null);

                    jPanel19.setBackground(new java.awt.Color(255, 255, 255));

                    javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
                    jPanel19.setLayout(jPanel19Layout);
                    jPanel19Layout.setHorizontalGroup(
                        jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1280, Short.MAX_VALUE)
                    );
                    jPanel19Layout.setVerticalGroup(
                        jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 70, Short.MAX_VALUE)
                    );

                    getContentPane().add(jPanel19);
                    jPanel19.setBounds(0, 710, 1280, 70);

                    jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                    jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                    jPanel1.setBackground(new java.awt.Color(43, 43, 43));

                    jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel1.setText("<html>Carnet de<br>Control Materno <br>Perinatal <span style=\"font-size:'15px'\"></span></html>");

                    lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                    lblusu.setForeground(new java.awt.Color(255, 255, 255));
                    lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                    lblusu.setText("Silvana");
                    lblusu.setFocusable(false);
                    lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                    btnAlertas.setBackground(new java.awt.Color(232, 76, 61));
                    btnAlertas.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
                    btnAlertas.setForeground(new java.awt.Color(232, 76, 61));
                    btnAlertas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                    btnAlertas.setText("Alertas");
                    btnAlertas.setBorderPainted(false);
                    btnAlertas.setContentAreaFilled(false);
                    btnAlertas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnAlertas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    btnAlertas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                    btnAlertas.setIconTextGap(30);
                    btnAlertas.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnAlertas.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnAlertasActionPerformed(evt);
                        }
                    });

                    btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
                    btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
                    btnGuardar.setText("Guardar");
                    btnGuardar.setContentAreaFilled(false);
                    btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                    btnGuardar.setIconTextGap(30);
                    btnGuardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnGuardarActionPerformed(evt);
                        }
                    });

                    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    jLabel2.setForeground(new java.awt.Color(204, 204, 204));
                    jLabel2.setText("Leyenda");

                    ChkAnalf1.setEditable(false);
                    ChkAnalf1.setBackground(new java.awt.Color(255, 204, 51));
                    ChkAnalf1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    ChkAnalf1.setForeground(new java.awt.Color(102, 102, 102));
                    ChkAnalf1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    ChkAnalf1.setBorder(null);
                    ChkAnalf1.setPreferredSize(new java.awt.Dimension(28, 28));
                    ChkAnalf1.setSelectionColor(new java.awt.Color(255, 204, 51));
                    ChkAnalf1.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            ChkAnalf1CaretUpdate(evt);
                        }
                    });
                    ChkAnalf1.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            ChkAnalf1MouseClicked(evt);
                        }
                    });

                    jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel30.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel30.setText("ALERTA");

                    ChkEdad1.setEditable(false);
                    ChkEdad1.setBackground(new java.awt.Color(255, 51, 51));
                    ChkEdad1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    ChkEdad1.setForeground(new java.awt.Color(255, 255, 255));
                    ChkEdad1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    ChkEdad1.setBorder(null);
                    ChkEdad1.setPreferredSize(new java.awt.Dimension(28, 28));
                    ChkEdad1.setSelectionColor(new java.awt.Color(255, 51, 51));
                    ChkEdad1.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            ChkEdad1CaretUpdate(evt);
                        }
                    });
                    ChkEdad1.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            ChkEdad1MouseClicked(evt);
                        }
                    });

                    jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel37.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel37.setText("Requiere Seguimiento Continuo");

                    btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
                    btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
                    btnNuevo.setText("Nuevo Registro");
                    btnNuevo.setToolTipText("");
                    btnNuevo.setContentAreaFilled(false);
                    btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                    btnNuevo.setIconTextGap(30);
                    btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnNuevoActionPerformed(evt);
                        }
                    });

                    btnInicio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    btnInicio.setForeground(new java.awt.Color(240, 240, 240));
                    btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Libreta de ahorros-32.png"))); // NOI18N
                    btnInicio.setText("Carnet");
                    btnInicio.setContentAreaFilled(false);
                    btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    btnInicio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                    btnInicio.setIconTextGap(30);
                    btnInicio.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnInicio.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnInicioActionPerformed(evt);
                        }
                    });

                    lblAlertas.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
                    lblAlertas.setForeground(new java.awt.Color(232, 76, 61));
                    lblAlertas.setText("10");

                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                    jPanel1.setLayout(jPanel1Layout);
                    jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel2)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel30))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel37)))
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(btnAlertas, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblAlertas)))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addContainerGap())
                    );
                    jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(12, 12, 12)
                            .addComponent(btnGuardar)
                            .addGap(18, 18, 18)
                            .addComponent(btnInicio)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAlertas)
                                .addComponent(lblAlertas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(73, 73, 73)
                            .addComponent(lblusu)
                            .addGap(28, 28, 28)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel30))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel37))
                            .addGap(180, 180, 180))
                    );

                    pnlControl.setBackground(new java.awt.Color(255, 255, 255));

                    jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel20.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel20.setText("Ocupación");

                    jPanel8.setBackground(new java.awt.Color(255, 255, 255));

                    Chkprim.setEditable(false);
                    Chkprim.setBackground(new java.awt.Color(204, 204, 204));
                    Chkprim.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    Chkprim.setForeground(new java.awt.Color(102, 102, 102));
                    Chkprim.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    Chkprim.setBorder(null);
                    Chkprim.setPreferredSize(new java.awt.Dimension(28, 28));
                    Chkprim.setSelectionColor(new java.awt.Color(204, 204, 204));
                    Chkprim.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            ChkprimCaretUpdate(evt);
                        }
                    });
                    Chkprim.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            ChkprimMouseClicked(evt);
                        }
                    });

                    jLabel32.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel32.setText("Primaria");

                    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                    jPanel8.setLayout(jPanel8Layout);
                    jPanel8Layout.setHorizontalGroup(
                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel32)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                            .addComponent(Chkprim, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0))
                    );
                    jPanel8Layout.setVerticalGroup(
                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Chkprim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel32))
                            .addGap(0, 0, Short.MAX_VALUE))
                    );

                    jPanel9.setBackground(new java.awt.Color(255, 255, 255));

                    ChkSec.setEditable(false);
                    ChkSec.setBackground(new java.awt.Color(204, 204, 204));
                    ChkSec.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    ChkSec.setForeground(new java.awt.Color(102, 102, 102));
                    ChkSec.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    ChkSec.setBorder(null);
                    ChkSec.setPreferredSize(new java.awt.Dimension(28, 28));
                    ChkSec.setSelectionColor(new java.awt.Color(204, 204, 204));
                    ChkSec.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            ChkSecCaretUpdate(evt);
                        }
                    });
                    ChkSec.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            ChkSecMouseClicked(evt);
                        }
                    });

                    jLabel33.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel33.setText("Secundaria");

                    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                    jPanel9.setLayout(jPanel9Layout);
                    jPanel9Layout.setHorizontalGroup(
                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel33)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ChkSec, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0))
                    );
                    jPanel9Layout.setVerticalGroup(
                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ChkSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33))
                            .addGap(0, 0, Short.MAX_VALUE))
                    );

                    jPanel11.setBackground(new java.awt.Color(255, 255, 255));

                    ChkSup.setEditable(false);
                    ChkSup.setBackground(new java.awt.Color(204, 204, 204));
                    ChkSup.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    ChkSup.setForeground(new java.awt.Color(102, 102, 102));
                    ChkSup.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    ChkSup.setBorder(null);
                    ChkSup.setPreferredSize(new java.awt.Dimension(28, 28));
                    ChkSup.setSelectionColor(new java.awt.Color(204, 204, 204));
                    ChkSup.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            ChkSupCaretUpdate(evt);
                        }
                    });
                    ChkSup.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            ChkSupMouseClicked(evt);
                        }
                    });

                    jLabel34.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel34.setText("Superior");

                    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                    jPanel11.setLayout(jPanel11Layout);
                    jPanel11Layout.setHorizontalGroup(
                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel34)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(ChkSup, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0))
                    );
                    jPanel11Layout.setVerticalGroup(
                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ChkSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel34))
                            .addGap(0, 0, Short.MAX_VALUE))
                    );

                    jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel29.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel29.setText("Estudios");

                    jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                    jLabel28.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel28.setText(">35");

                    jLabel27.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel27.setText("<15");

                    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                    jPanel4.setLayout(jPanel4Layout);
                    jPanel4Layout.setHorizontalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel27)
                                .addComponent(jLabel28))
                            .addGap(0, 76, Short.MAX_VALUE))
                    );
                    jPanel4Layout.setVerticalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel27)
                            .addGap(0, 0, 0)
                            .addComponent(jLabel28)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel39.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel39.setText("Estado Civil");

                    jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel25.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel25.setText("Edad");

                    lblOcupacion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    lblOcupacion.setForeground(new java.awt.Color(51, 51, 51));
                    lblOcupacion.setText("___________________________________");

                    lblEstadoCiv.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    lblEstadoCiv.setForeground(new java.awt.Color(51, 51, 51));
                    lblEstadoCiv.setText("___________________________________");

                    jPanel12.setBackground(new java.awt.Color(255, 255, 255));

                    ChkSupnU.setEditable(false);
                    ChkSupnU.setBackground(new java.awt.Color(204, 204, 204));
                    ChkSupnU.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    ChkSupnU.setForeground(new java.awt.Color(102, 102, 102));
                    ChkSupnU.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    ChkSupnU.setBorder(null);
                    ChkSupnU.setPreferredSize(new java.awt.Dimension(28, 28));
                    ChkSupnU.setSelectionColor(new java.awt.Color(204, 204, 204));
                    ChkSupnU.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            ChkSupnUCaretUpdate(evt);
                        }
                    });
                    ChkSupnU.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            ChkSupnUMouseClicked(evt);
                        }
                    });

                    jLabel35.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel35.setText("Superior No Univ.");

                    javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                    jPanel12.setLayout(jPanel12Layout);
                    jPanel12Layout.setHorizontalGroup(
                        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addComponent(jLabel35)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ChkSupnU, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    );
                    jPanel12Layout.setVerticalGroup(
                        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ChkSupnU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel35))
                            .addGap(0, 0, Short.MAX_VALUE))
                    );

                    jPanel5.setBackground(new java.awt.Color(255, 255, 255));

                    ChkAnalf.setEditable(false);
                    ChkAnalf.setBackground(new java.awt.Color(255, 204, 51));
                    ChkAnalf.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    ChkAnalf.setForeground(new java.awt.Color(102, 102, 102));
                    ChkAnalf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    ChkAnalf.setBorder(null);
                    ChkAnalf.setPreferredSize(new java.awt.Dimension(28, 28));
                    ChkAnalf.setSelectionColor(new java.awt.Color(255, 204, 51));
                    ChkAnalf.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            ChkAnalfCaretUpdate(evt);
                        }
                    });
                    ChkAnalf.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            ChkAnalfMouseClicked(evt);
                        }
                    });

                    jLabel31.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel31.setText("Analfabeta");

                    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                    jPanel5.setLayout(jPanel5Layout);
                    jPanel5Layout.setHorizontalGroup(
                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel31)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ChkAnalf, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0))
                    );
                    jPanel5Layout.setVerticalGroup(
                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ChkAnalf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel31))
                            .addGap(0, 0, Short.MAX_VALUE))
                    );

                    jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel44.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel44.setText("Padre:RN:");

                    jPanel13.setBackground(new java.awt.Color(255, 255, 255));

                    ChkAnAp.setBackground(new java.awt.Color(204, 204, 204));
                    ChkAnAp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    ChkAnAp.setForeground(new java.awt.Color(102, 102, 102));
                    ChkAnAp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    ChkAnAp.setBorder(null);
                    ChkAnAp.setPreferredSize(new java.awt.Dimension(28, 28));
                    ChkAnAp.setSelectionColor(new java.awt.Color(204, 204, 204));
                    ChkAnAp.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            ChkAnApCaretUpdate(evt);
                        }
                    });
                    ChkAnAp.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            ChkAnApMouseClicked(evt);
                        }
                    });
                    ChkAnAp.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            ChkAnApKeyTyped(evt);
                        }
                    });

                    jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                    jLabel36.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel36.setText("Años Aprobados");

                    javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                    jPanel13.setLayout(jPanel13Layout);
                    jPanel13Layout.setHorizontalGroup(
                        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel36)
                            .addGap(4, 4, 4)
                            .addComponent(ChkAnAp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                    );
                    jPanel13Layout.setVerticalGroup(
                        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ChkAnAp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel36))
                            .addGap(0, 0, Short.MAX_VALUE))
                    );

                    txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                    txtCodigo.setForeground(new java.awt.Color(102, 102, 102));
                    txtCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                    txtCodigo.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtCodigoCaretUpdate(evt);
                        }
                    });
                    txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtCodigoKeyTyped(evt);
                        }
                    });

                    jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel23.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel23.setText("Código Afiliacion Seguro");

                    chkPrivado.setBackground(new java.awt.Color(255, 255, 255));
                    buttonGroup1.add(chkPrivado);
                    chkPrivado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    chkPrivado.setText("PRIVADO");

                    chkEssalud.setBackground(new java.awt.Color(255, 255, 255));
                    buttonGroup1.add(chkEssalud);
                    chkEssalud.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    chkEssalud.setText("ESSALUD");

                    chkSis.setBackground(new java.awt.Color(255, 255, 255));
                    buttonGroup1.add(chkSis);
                    chkSis.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    chkSis.setText("SIS");

                    jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel22.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel22.setText("Tipo de Seguro");

                    txtEstablecimiento.setEditable(false);
                    txtEstablecimiento.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                    txtEstablecimiento.setForeground(new java.awt.Color(102, 102, 102));
                    txtEstablecimiento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtEstablecimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                    txtEstablecimiento.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtEstablecimientoCaretUpdate(evt);
                        }
                    });

                    jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel21.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel21.setText("Establecimiento");

                    lblCelular.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    lblCelular.setForeground(new java.awt.Color(51, 51, 51));
                    lblCelular.setText("________________________");

                    jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel18.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel18.setText("Celular");

                    lblTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    lblTelefono.setForeground(new java.awt.Color(51, 51, 51));
                    lblTelefono.setText("___________________________________");

                    jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel8.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel8.setText("Teléfono");

                    jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel7.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel7.setText("Distrito");

                    lblDistrito.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    lblDistrito.setForeground(new java.awt.Color(51, 51, 51));
                    lblDistrito.setText("__________________________________________________________________________");

                    lblProvincia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    lblProvincia.setForeground(new java.awt.Color(51, 51, 51));
                    lblProvincia.setText("________________________");

                    jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel14.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel14.setText("Provincia");

                    lblDepartamento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    lblDepartamento.setForeground(new java.awt.Color(51, 51, 51));
                    lblDepartamento.setText("___________________________________");

                    jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel6.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel6.setText("Departamento");

                    jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel11.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel11.setText("Cod. Sector");

                    lblSector.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    lblSector.setForeground(new java.awt.Color(51, 51, 51));
                    lblSector.setText("________________________");

                    txtIdHc.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtIdHcCaretUpdate(evt);
                        }
                    });

                    lblMant.setText("Mantenimiento");

                    lblDireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    lblDireccion.setForeground(new java.awt.Color(51, 51, 51));
                    lblDireccion.setText("__________________________________________________________________________");

                    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel4.setText("Dirección");

                    lblDni.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    lblDni.setForeground(new java.awt.Color(39, 174, 97));

                    jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel10.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel10.setText("DNI");

                    jPanel21.setBackground(new java.awt.Color(255, 255, 255));

                    chkRef.setEditable(false);
                    chkRef.setBackground(new java.awt.Color(204, 204, 204));
                    chkRef.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    chkRef.setForeground(new java.awt.Color(102, 102, 102));
                    chkRef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    chkRef.setBorder(null);
                    chkRef.setPreferredSize(new java.awt.Dimension(28, 28));
                    chkRef.setSelectionColor(new java.awt.Color(204, 204, 204));
                    chkRef.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            chkRefCaretUpdate(evt);
                        }
                    });
                    chkRef.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            chkRefMouseClicked(evt);
                        }
                    });

                    jLabel47.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel47.setText("Referencia");

                    javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
                    jPanel21.setLayout(jPanel21Layout);
                    jPanel21Layout.setHorizontalGroup(
                        jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel47)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(chkRef, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0))
                    );
                    jPanel21Layout.setVerticalGroup(
                        jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(chkRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel47))
                            .addGap(0, 0, Short.MAX_VALUE))
                    );

                    jPanel20.setBackground(new java.awt.Color(255, 255, 255));

                    chkNoAplica.setEditable(false);
                    chkNoAplica.setBackground(new java.awt.Color(204, 204, 204));
                    chkNoAplica.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    chkNoAplica.setForeground(new java.awt.Color(102, 102, 102));
                    chkNoAplica.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    chkNoAplica.setBorder(null);
                    chkNoAplica.setPreferredSize(new java.awt.Dimension(28, 28));
                    chkNoAplica.setSelectionColor(new java.awt.Color(204, 204, 204));
                    chkNoAplica.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            chkNoAplicaCaretUpdate(evt);
                        }
                    });
                    chkNoAplica.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            chkNoAplicaMouseClicked(evt);
                        }
                    });

                    jLabel46.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel46.setText("No Aplica");

                    javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
                    jPanel20.setLayout(jPanel20Layout);
                    jPanel20Layout.setHorizontalGroup(
                        jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel46)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(chkNoAplica, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0))
                    );
                    jPanel20Layout.setVerticalGroup(
                        jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(chkNoAplica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel46))
                            .addGap(0, 0, Short.MAX_VALUE))
                    );

                    jPanel15.setBackground(new java.awt.Color(255, 255, 255));
                    jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtEstabOrigen.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                    txtEstabOrigen.setForeground(new java.awt.Color(102, 102, 102));
                    txtEstabOrigen.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtEstabOrigen.setBorder(null);
                    txtEstabOrigen.setEnabled(false);
                    txtEstabOrigen.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtEstabOrigenCaretUpdate(evt);
                        }
                    });
                    txtEstabOrigen.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtEstabOrigenKeyTyped(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                    jPanel15.setLayout(jPanel15Layout);
                    jPanel15Layout.setHorizontalGroup(
                        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtEstabOrigen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    );
                    jPanel15Layout.setVerticalGroup(
                        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addComponent(txtEstabOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel38.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel38.setText("Establecimiento Origen");

                    lblHc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    lblHc.setForeground(new java.awt.Color(39, 174, 97));
                    lblHc.setText("____________");

                    jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel48.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel48.setText("Nº. HC");

                    jPanel10.setBackground(new java.awt.Color(255, 255, 255));
                    jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtPaciente.setEditable(false);
                    txtPaciente.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                    txtPaciente.setForeground(new java.awt.Color(102, 102, 102));
                    txtPaciente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtPaciente.setBorder(null);
                    txtPaciente.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtPacienteCaretUpdate(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                    jPanel10.setLayout(jPanel10Layout);
                    jPanel10Layout.setHorizontalGroup(
                        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPaciente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                    );
                    jPanel10Layout.setVerticalGroup(
                        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel3.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel3.setText("Apellidos y Nombres");

                    lblActoMed.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    lblActoMed.setForeground(new java.awt.Color(39, 174, 97));
                    lblActoMed.setText("______________");

                    jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel9.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel9.setText("Acto Medico");

                    jPanel7.setBackground(new java.awt.Color(39, 174, 97));
                    jPanel7.setPreferredSize(new java.awt.Dimension(15, 34));

                    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                    jPanel7.setLayout(jPanel7Layout);
                    jPanel7Layout.setHorizontalGroup(
                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 13, Short.MAX_VALUE)
                    );
                    jPanel7Layout.setVerticalGroup(
                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 37, Short.MAX_VALUE)
                    );

                    jPanel6.setBackground(new java.awt.Color(39, 174, 97));
                    jPanel6.setPreferredSize(new java.awt.Dimension(15, 34));

                    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                    jPanel6.setLayout(jPanel6Layout);
                    jPanel6Layout.setHorizontalGroup(
                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 14, Short.MAX_VALUE)
                    );
                    jPanel6Layout.setVerticalGroup(
                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 37, Short.MAX_VALUE)
                    );

                    lblEdad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    lblEdad.setBorder(null);

                    txtPadreRN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    txtPadreRN.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtPadreRNKeyTyped(evt);
                        }
                    });

                    lblTriaje.setText("jLabel5");

                    lblId.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            lblIdCaretUpdate(evt);
                        }
                    });

                    ChkEdad.setEditable(false);
                    ChkEdad.setBackground(new java.awt.Color(255, 51, 51));
                    ChkEdad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    ChkEdad.setForeground(new java.awt.Color(255, 255, 255));
                    ChkEdad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    ChkEdad.setBorder(null);
                    ChkEdad.setPreferredSize(new java.awt.Dimension(28, 28));
                    ChkEdad.setSelectionColor(new java.awt.Color(255, 51, 51));
                    ChkEdad.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            ChkEdadCaretUpdate(evt);
                        }
                    });
                    ChkEdad.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            ChkEdadMouseClicked(evt);
                        }
                    });

                    javax.swing.GroupLayout pnlControlLayout = new javax.swing.GroupLayout(pnlControl);
                    pnlControl.setLayout(pnlControlLayout);
                    pnlControlLayout.setHorizontalGroup(
                        pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlControlLayout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlControlLayout.createSequentialGroup()
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel22)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel21)
                                        .addComponent(jLabel14)
                                        .addGroup(pnlControlLayout.createSequentialGroup()
                                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel29)
                                                .addComponent(jLabel39)
                                                .addComponent(jLabel44)
                                                .addComponent(jLabel25))
                                            .addGap(90, 90, 90)
                                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnlControlLayout.createSequentialGroup()
                                                    .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(10, 10, 10)
                                                    .addComponent(ChkEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(pnlControlLayout.createSequentialGroup()
                                                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(pnlControlLayout.createSequentialGroup()
                                                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(lblEstadoCiv)
                                                        .addComponent(txtPadreRN))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(pnlControlLayout.createSequentialGroup()
                                            .addComponent(jLabel23)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlControlLayout.createSequentialGroup()
                                            .addGap(160, 160, 160)
                                            .addComponent(lblOcupacion))))
                                .addGroup(pnlControlLayout.createSequentialGroup()
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlControlLayout.createSequentialGroup()
                                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel18)
                                                .addComponent(jLabel6))
                                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlControlLayout.createSequentialGroup()
                                                    .addGap(75, 75, 75)
                                                    .addComponent(lblActoMed, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel10)
                                                    .addGap(35, 35, 35)
                                                    .addComponent(lblDni, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(67, 67, 67)
                                                    .addComponent(jLabel48)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lblHc, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlControlLayout.createSequentialGroup()
                                                    .addGap(564, 564, 564)
                                                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblMant, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblTriaje)
                                                        .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(lblId, javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtIdHc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)))
                                                    .addGap(119, 119, 119))))
                                        .addGroup(pnlControlLayout.createSequentialGroup()
                                            .addComponent(jLabel38)
                                            .addGap(23, 23, 23)
                                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel9)
                                        .addGroup(pnlControlLayout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(38, 38, 38)
                                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel4)
                                        .addGroup(pnlControlLayout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addGap(91, 91, 91)
                                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblProvincia)
                                                .addComponent(lblSector)
                                                .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblDepartamento)
                                                .addComponent(lblDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblTelefono)
                                                .addComponent(lblCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(pnlControlLayout.createSequentialGroup()
                                                    .addComponent(chkSis)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(chkEssalud)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(chkPrivado)))))))
                            .addContainerGap(64, Short.MAX_VALUE))
                    );
                    pnlControlLayout.setVerticalGroup(
                        pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlControlLayout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(7, 7, 7)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9)
                                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel48)
                                        .addComponent(lblHc))
                                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblActoMed)
                                        .addComponent(jLabel10))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel38))
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(lblDireccion))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(lblSector))
                            .addGap(12, 12, 12)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblMant)
                                .addComponent(jLabel6)
                                .addComponent(lblDepartamento))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(lblProvincia)
                                .addComponent(txtIdHc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(lblDistrito))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(lblTelefono)
                                .addComponent(lblTriaje))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pnlControlLayout.createSequentialGroup()
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27))
                                .addGroup(pnlControlLayout.createSequentialGroup()
                                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(lblCelular)
                                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel22)
                                .addComponent(chkEssalud)
                                .addComponent(chkPrivado)
                                .addComponent(chkSis))
                            .addGap(6, 6, 6)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20)
                                .addComponent(lblOcupacion))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel25)
                                .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ChkEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel39)
                                .addComponent(lblEstadoCiv))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel44)
                                .addComponent(txtPadreRN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(15, Short.MAX_VALUE))
                    );

                    pnlMensaje.setBackground(new java.awt.Color(33, 115, 70));

                    lblMensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    lblMensaje.setForeground(new java.awt.Color(255, 255, 255));
                    lblMensaje.setText("Desea Actualizar el Registro ?");

                    btnSi.setForeground(new java.awt.Color(240, 240, 240));
                    btnSi.setText("Si");
                    btnSi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                    btnSi.setContentAreaFilled(false);
                    btnSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnSi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnSi.setIconTextGap(30);
                    btnSi.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnSiActionPerformed(evt);
                        }
                    });

                    btnNo.setForeground(new java.awt.Color(240, 240, 240));
                    btnNo.setText("No");
                    btnNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                    btnNo.setContentAreaFilled(false);
                    btnNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnNo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnNo.setIconTextGap(30);
                    btnNo.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnNoActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout pnlMensajeLayout = new javax.swing.GroupLayout(pnlMensaje);
                    pnlMensaje.setLayout(pnlMensajeLayout);
                    pnlMensajeLayout.setHorizontalGroup(
                        pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlMensajeLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(lblMensaje)
                            .addGap(46, 46, 46)
                            .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(819, Short.MAX_VALUE))
                    );
                    pnlMensajeLayout.setVerticalGroup(
                        pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMensajeLayout.createSequentialGroup()
                            .addContainerGap(17, Short.MAX_VALUE)
                            .addGroup(pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblMensaje)
                                .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap())
                    );

                    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                    jPanel2.setLayout(jPanel2Layout);
                    jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(83, Short.MAX_VALUE))
                    );
                    jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(pnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                            .addContainerGap())
                    );

                    jTabbedPane1.addTab("Datos Generales", jPanel2);

                    javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
                    Contenedor.setLayout(ContenedorLayout);
                    ContenedorLayout.setHorizontalGroup(
                        ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1528, Short.MAX_VALUE)
                    );
                    ContenedorLayout.setVerticalGroup(
                        ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 712, Short.MAX_VALUE)
                    );

                    jTabbedPane1.addTab("Registros", Contenedor);

                    javax.swing.GroupLayout ContenedorTablasLayout = new javax.swing.GroupLayout(ContenedorTablas);
                    ContenedorTablas.setLayout(ContenedorTablasLayout);
                    ContenedorTablasLayout.setHorizontalGroup(
                        ContenedorTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1528, Short.MAX_VALUE)
                    );
                    ContenedorTablasLayout.setVerticalGroup(
                        ContenedorTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 712, Short.MAX_VALUE)
                    );

                    jTabbedPane1.addTab("Pestañas", ContenedorTablas);

                    getContentPane().add(jTabbedPane1);
                    jTabbedPane1.setBounds(0, 0, 1533, 740);

                    pack();
                }// </editor-fold>//GEN-END:initComponents

    private void txtPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPacienteCaretUpdate

    }//GEN-LAST:event_txtPacienteCaretUpdate

    private void txtCodigoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCodigoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoCaretUpdate

    private void txtEstablecimientoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEstablecimientoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstablecimientoCaretUpdate

    private void ChkAnalfCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkAnalfCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnalfCaretUpdate

    private void ChkAnalfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkAnalfMouseClicked
        if(Chkprim.getText().equals("") && ChkSec.getText().equals("") &&
           ChkSup.getText().equals("") && ChkSupnU.getText().equals("")){
            if(ChkAnalf.getText().equals("") && evt.getClickCount()==1){
               ChkAnalf.setText("X");
            }else
            if(ChkAnalf.getText().equals("X") && evt.getClickCount()==1){
               ChkAnalf.setText(""); 
            }
        } else {
            if(evt.getClickCount()==1){
                ChkAnalf.setText(""); 
            }
        }
    }//GEN-LAST:event_ChkAnalfMouseClicked

    private void ChkEdadCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkEdadCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdadCaretUpdate

    private void ChkEdadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkEdadMouseClicked

    }//GEN-LAST:event_ChkEdadMouseClicked

    private void ChkprimCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkprimCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkprimCaretUpdate

    private void ChkprimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkprimMouseClicked
        if(ChkAnalf.getText().equals("") && ChkSec.getText().equals("") &&
           ChkSup.getText().equals("") && ChkSupnU.getText().equals("")){
            if(Chkprim.getText().equals("") && evt.getClickCount()==1){
               Chkprim.setText("X");
            }else
            if(Chkprim.getText().equals("X") && evt.getClickCount()==1){
               Chkprim.setText(""); 
            }
        } else {
            if(evt.getClickCount()==1){
                Chkprim.setText(""); 
            }
        }
    }//GEN-LAST:event_ChkprimMouseClicked

    private void ChkSecCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkSecCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkSecCaretUpdate

    private void ChkSecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkSecMouseClicked
        if(ChkAnalf.getText().equals("") && Chkprim.getText().equals("") &&
           ChkSup.getText().equals("") && ChkSupnU.getText().equals("")){
            if(ChkSec.getText().equals("") && evt.getClickCount()==1){
               ChkSec.setText("X");
            }else
            if(ChkSec.getText().equals("X") && evt.getClickCount()==1){
               ChkSec.setText(""); 
            }
        } else {
            if(evt.getClickCount()==1){
                ChkSec.setText(""); 
            }
        }
    }//GEN-LAST:event_ChkSecMouseClicked

    private void ChkSupCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkSupCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkSupCaretUpdate

    private void ChkSupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkSupMouseClicked
        if(ChkAnalf.getText().equals("") && Chkprim.getText().equals("") &&
           ChkSec.getText().equals("") && ChkSupnU.getText().equals("")){
            if(ChkSup.getText().equals("") && evt.getClickCount()==1){
               ChkSup.setText("X");
            }else
            if(ChkSup.getText().equals("X") && evt.getClickCount()==1){
               ChkSup.setText(""); 
            }
        } else {
            if(evt.getClickCount()==1){
                ChkSup.setText(""); 
            }
        }
    }//GEN-LAST:event_ChkSupMouseClicked

    private void ChkSupnUCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkSupnUCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkSupnUCaretUpdate

    private void ChkSupnUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkSupnUMouseClicked
        if(ChkAnalf.getText().equals("") && Chkprim.getText().equals("") &&
           ChkSec.getText().equals("") && ChkSup.getText().equals("")){
            if(ChkSupnU.getText().equals("") && evt.getClickCount()==1){
               ChkSupnU.setText("X");
            }else
            if(ChkSupnU.getText().equals("X") && evt.getClickCount()==1){
               ChkSupnU.setText(""); 
            }
        } else {
            if(evt.getClickCount()==1){
                ChkSupnU.setText(""); 
            }
        }
    }//GEN-LAST:event_ChkSupnUMouseClicked

    private void ChkAnApCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkAnApCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnApCaretUpdate

    private void ChkAnApMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkAnApMouseClicked
  
    }//GEN-LAST:event_ChkAnApMouseClicked

    private void txtEstabOrigenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEstabOrigenCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstabOrigenCaretUpdate

    private void chkNoAplicaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkNoAplicaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkNoAplicaCaretUpdate

    private void chkNoAplicaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkNoAplicaMouseClicked
        if(chkRef.getText().equals("")){
            if(chkNoAplica.getText().equals("") && evt.getClickCount()==1){
               chkNoAplica.setText("X");
               txtEstabOrigen.setEnabled(false);
               txtEstabOrigen.setText("");
            }else
            if(chkNoAplica.getText().equals("X") && evt.getClickCount()==1){
               chkNoAplica.setText(""); 
               txtEstabOrigen.setEnabled(false);
               txtEstabOrigen.setText("");
            }
        } else {
            if(evt.getClickCount()==1){
                chkNoAplica.setText(""); 
            }
        }
    }//GEN-LAST:event_chkNoAplicaMouseClicked

    private void chkRefCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkRefCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkRefCaretUpdate

    private void chkRefMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkRefMouseClicked
        if(chkNoAplica.getText().equals("")){
            if(chkRef.getText().equals("") && evt.getClickCount()==1){
               chkRef.setText("X");
               txtEstabOrigen.setEnabled(true);
               txtEstabOrigen.requestFocus();
            }else
            if(chkRef.getText().equals("X") && evt.getClickCount()==1){
               chkRef.setText(""); 
               txtEstabOrigen.setEnabled(false);
               txtEstabOrigen.setText("");
            }
        } else {
            if(evt.getClickCount()==1){
                chkRef.setText(""); 
            }
        }
    }//GEN-LAST:event_chkRefMouseClicked

    private void btnAlertasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertasActionPerformed
        RegistroEmabarazoAlertas alertas =new RegistroEmabarazoAlertas();
        RegistroEmbarazo.Contenedor.add(alertas);
        try {
            alertas.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnAlertasActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(lblMant.getText().equals("I")){
            if(!chkSis.isSelected() && !chkEssalud.isSelected() && !chkPrivado.isSelected()){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccine un nivel de estudios");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else
            if(ChkAnalf.getText().equals("") && Chkprim.getText().equals("") &&
                ChkSec.getText().equals("") && ChkSup.getText().equals("") &&
                ChkSupnU.getText().equals("")){
                    pnlMensaje.setVisible(true);
                    lblMensaje.setText("Seleccine un nivel de estudios");
                    btnSi.setVisible(false);
                    btnNo.setVisible(false);
                    pnlMensaje.setBackground(new Color(255,91,70));
            }else
            if(ChkAnAp.getText().equals("")){
                    pnlMensaje.setVisible(true);
                    lblMensaje.setText("Ingrese la cantidad de años aprobados");
                    btnSi.setVisible(false);
                    btnNo.setVisible(false);
                    pnlMensaje.setBackground(new Color(255,91,70));
            }else
            if(txtPadreRN.getText().equals("")){
                    pnlMensaje.setVisible(true);
                    lblMensaje.setText("Ingrese el nombre del padre");
                    btnSi.setVisible(false);
                    btnNo.setVisible(false);
                    pnlMensaje.setBackground(new Color(255,91,70));
            }
            else {
                    pnlMensaje.setVisible(true);
                    lblMensaje.setText("¿Guardar los datos?");
                    btnSi.setText("Si");
                    btnSi.setVisible(true);
                    btnNo.setVisible(true);
                    pnlMensaje.setBackground(new Color(255,153,51));
            }
        } else
        if(lblMant.getText().equals("U")){
            if(!chkSis.isSelected() && !chkEssalud.isSelected() && !chkPrivado.isSelected()){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccine un nivel de estudios");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else
            if(ChkAnalf.getText().equals("") && Chkprim.getText().equals("") &&
                ChkSec.getText().equals("") && ChkSup.getText().equals("") &&
                ChkSupnU.getText().equals("")){
                    pnlMensaje.setVisible(true);
                    lblMensaje.setText("Seleccine un nivel de estudios");
                    btnSi.setVisible(false);
                    btnNo.setVisible(false);
                    pnlMensaje.setBackground(new Color(255,91,70));
            }else
            if(ChkAnAp.getText().equals("")){
                    pnlMensaje.setVisible(true);
                    lblMensaje.setText("Ingrese la cantidad de años aprobados");
                    btnSi.setVisible(false);
                    btnNo.setVisible(false);
                    pnlMensaje.setBackground(new Color(255,91,70));
            }else
            if(txtPadreRN.getText().equals("")){
                    pnlMensaje.setVisible(true);
                    lblMensaje.setText("Ingrese el nombre del padre");
                    btnSi.setVisible(false);
                    btnNo.setVisible(false);
                    pnlMensaje.setBackground(new Color(255,91,70));
            }
            else {
                    pnlMensaje.setVisible(true);
                    lblMensaje.setText("¿Modificar los datos?");
                    btnSi.setText("Si");
                    btnSi.setVisible(true);
                    btnNo.setVisible(true);
                    pnlMensaje.setBackground(new Color(255,153,51));
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void ChkAnalf1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkAnalf1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnalf1CaretUpdate

    private void ChkAnalf1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkAnalf1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnalf1MouseClicked

    private void ChkEdad1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkEdad1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdad1CaretUpdate

    private void ChkEdad1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkEdad1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdad1MouseClicked

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        BuscarMadres.setVisible(true);
        BuscarMadres.setLocationRelativeTo(null);//en el centro
        BuscarMadres.setResizable(false);
        BuscarMadres.getContentPane().setBackground(Color.WHITE);
        tbEmbarazoActual.setVisible(true);
        jScrollPane1.setVisible(true);
        ConsultorioExtEsnitss consultorio1 = new ConsultorioExtEsnitss();
        consultorio1.consultorioExListarC(txtBuscar.getText(), "Q", tbMadres);
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        ConsultorioExtEsnitss consultorio1 = new ConsultorioExtEsnitss();
        consultorio1.consultorioExListarC(txtBuscar.getText(), "Q", tbMadres);
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbMadres.getSelectionModel().setSelectionInterval(0, 0);
            tbMadres.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T7MouseClicked

    private void tbMadresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMadresMouseClicked
        if(evt.getClickCount()==1){
//            enviarDatosMadres();
            int fila = tbMadres.getSelectedRow();
            ConsultorioExtCarnetPerinatalCabecera consultorio1 = new ConsultorioExtCarnetPerinatalCabecera();
            consultorio1.consultorioExControlPerinatalCabListar(String.valueOf(tbMadres.getValueAt(fila, 13)),"AC",tbEmbarazoActual,"actual"); //llenar tabla de registro actuales
            consultorio1.consultorioExControlPerinatalCabListar(String.valueOf(tbMadres.getValueAt(fila, 13)),"AN",tbEmbarazoAntiguos,"antigua"); //llenar tabla de registro antiguos
            if(tbEmbarazoActual.getRowCount()!=0){
                btnNuevoEmbarazo.setEnabled(false);
                tbEmbarazoActual.setVisible(true);
                btnRegistrosExistentes.setVisible(true);
                jScrollPane1.setVisible(true);
            }else{
                btnNuevoEmbarazo.setEnabled(true);
                tbEmbarazoActual.setVisible(false);
                jScrollPane1.setVisible(false);
                btnRegistrosExistentes.setVisible(false);
            }
        }
    }//GEN-LAST:event_tbMadresMouseClicked

    private void tbMadresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMadresMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMadresMousePressed

    private void tbMadresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMadresKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbMadres.getSelectedRow()==0){
            txtBuscar.requestFocus();
            tbMadres.getSelectionModel().setSelectionInterval(0,0);
        }
    }//GEN-LAST:event_tbMadresKeyPressed

    private void txtIdHcCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIdHcCaretUpdate
        ConsultorioExtCarnetPerinatalCabecera consultorio1 = new ConsultorioExtCarnetPerinatalCabecera();
        consultorio1.mostrarDatosHC(txtIdHc.getText());
    }//GEN-LAST:event_txtIdHcCaretUpdate

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        if(btnSi.getText().equals("Si")){ // Al guardar
            mantenimientoRegistroEmbarazo();
        } else
        if(btnSi.getText().equals("OK")) // Al hacer OK hacerloinvisible
            pnlMensaje.setVisible(false);

    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_btnNoActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtPadreRNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPadreRNKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtPadreRNKeyTyped

    private void txtEstabOrigenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstabOrigenKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtEstabOrigenKeyTyped

    private void ChkAnApKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ChkAnApKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_ChkAnApKeyTyped

    private void tbMadresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMadresKeyReleased
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP
           || evt.getExtendedKeyCode()==KeyEvent.VK_ENTER){
            int fila = tbMadres.getSelectedRow();
            ConsultorioExtCarnetPerinatalCabecera consultorio1 = new ConsultorioExtCarnetPerinatalCabecera();
            consultorio1.consultorioExControlPerinatalCabListar(String.valueOf(tbMadres.getValueAt(fila, 13)),"AC",tbEmbarazoActual,"actual"); //llenar tabla de registro actuales
            consultorio1.consultorioExControlPerinatalCabListar(String.valueOf(tbMadres.getValueAt(fila, 13)),"AN",tbEmbarazoAntiguos,"antigua"); //llenar tabla de registro antiguos
            if(tbEmbarazoActual.getRowCount()!=0){
                btnNuevoEmbarazo.setEnabled(false);
                tbEmbarazoActual.setVisible(true);
                btnRegistrosExistentes.setVisible(true);
                jScrollPane1.setVisible(true);
            }else{
                btnNuevoEmbarazo.setEnabled(true);
                tbEmbarazoActual.setVisible(false);
                jScrollPane1.setVisible(false);
                btnRegistrosExistentes.setVisible(false);
            }
        }
    }//GEN-LAST:event_tbMadresKeyReleased

    private void tbEmbarazoActualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbEmbarazoActualKeyPressed
        ConsultorioExtCarnetPerinatalCabecera consultorio1 = new ConsultorioExtCarnetPerinatalCabecera();
        int fila = tbEmbarazoActual.getSelectedRow();
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviaDatosExistentes();
            enviarDatosMadres();
            enviarDatosCabecera();
            lblMant.setText("U");
            btnGuardar.setEnabled(true);
        }
        char eliminar = evt.getKeyChar();
        if(eliminar==KeyEvent.VK_DELETE){
            consultorio1.setCpId(Integer.parseInt(String.valueOf(tbEmbarazoActual.getValueAt(fila, 0))));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalCabecera("E",""))
                btnNuevoEmbarazo.setEnabled(true);
                DefaultTableModel modelo4 = (DefaultTableModel)tbEmbarazoActual.getModel(); 
                int b4=tbEmbarazoActual.getRowCount();
                for(int j=0;j<b4;j++){
                            modelo4.removeRow(0);
                }
        }
    }//GEN-LAST:event_tbEmbarazoActualKeyPressed

    private void tbEmbarazoActualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEmbarazoActualMouseClicked
        if(evt.getClickCount()==2){
            enviaDatosExistentes();
            enviarDatosMadres();
            enviarDatosCabecera();
            lblMant.setText("U");
            btnGuardar.setEnabled(true);
        }
    }//GEN-LAST:event_tbEmbarazoActualMouseClicked

    private void lblIdCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_lblIdCaretUpdate

    }//GEN-LAST:event_lblIdCaretUpdate

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        RegistroEmbarazoPrincipal GA =new RegistroEmbarazoPrincipal();
        Contenedor.add(GA);
        jTabbedPane1.setSelectedIndex(1);
        RegistroEmbarazoPrincipal.lblId.setText(String.valueOf(lblId.getText()));
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnNuevoEmbarazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoEmbarazoActionPerformed
        BuscarMadres.dispose();
        enviarDatosMadres();
        limpiar();
    }//GEN-LAST:event_btnNuevoEmbarazoActionPerformed

    private void btnRegistrosExistentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrosExistentesActionPerformed
        jTabbedPane2.setSelectedIndex(1);
        btnAtras.setVisible(true);
    }//GEN-LAST:event_btnRegistrosExistentesActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        jTabbedPane2.setSelectedIndex(0);
        btnAtras.setVisible(false);
        btnRegistrosExistentes.setVisible(false);
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void tbEmbarazoActualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbEmbarazoActualKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbEmbarazoActualKeyReleased

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
            java.util.logging.Logger.getLogger(RegistroEmbarazo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroEmbarazo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroEmbarazo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroEmbarazo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroEmbarazo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BuscarMadres;
    public static javax.swing.JTextField ChkAnAp;
    public static javax.swing.JTextField ChkAnalf;
    public static javax.swing.JTextField ChkAnalf1;
    public static javax.swing.JTextField ChkEdad;
    public static javax.swing.JTextField ChkEdad1;
    public static javax.swing.JTextField ChkSec;
    public static javax.swing.JTextField ChkSup;
    public static javax.swing.JTextField ChkSupnU;
    public static javax.swing.JTextField Chkprim;
    public static javax.swing.JDesktopPane Contenedor;
    public static javax.swing.JDesktopPane ContenedorTablas;
    private javax.swing.JLabel T7;
    private javax.swing.JButton btnAlertas;
    private javax.swing.JButton btnAtras;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnInicio;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevoEmbarazo;
    private javax.swing.JButton btnRegistrosExistentes;
    private javax.swing.JButton btnSi;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    public static javax.swing.JCheckBox chkEssalud;
    public static javax.swing.JTextField chkNoAplica;
    public static javax.swing.JCheckBox chkPrivado;
    public static javax.swing.JTextField chkRef;
    public static javax.swing.JCheckBox chkSis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    public static javax.swing.JLabel lblActoMed;
    public static javax.swing.JLabel lblAlertas;
    public static javax.swing.JLabel lblCelular;
    public static javax.swing.JLabel lblDepartamento;
    public static javax.swing.JLabel lblDireccion;
    public static javax.swing.JLabel lblDistrito;
    public static javax.swing.JLabel lblDni;
    public static javax.swing.JTextField lblEdad;
    public static javax.swing.JLabel lblEstadoCiv;
    public static javax.swing.JLabel lblHc;
    private javax.swing.JTextField lblId;
    public static javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMensaje;
    public static javax.swing.JLabel lblOcupacion;
    public static javax.swing.JLabel lblProvincia;
    public static javax.swing.JLabel lblSector;
    public static javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTriaje;
    public static javax.swing.JLabel lblusu;
    public static javax.swing.JPanel pnlControl;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JTable tbEmbarazoActual;
    private javax.swing.JTable tbEmbarazoAntiguos;
    private javax.swing.JTable tbMadres;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtEstabOrigen;
    public static javax.swing.JTextField txtEstablecimiento;
    private javax.swing.JTextField txtIdHc;
    public static javax.swing.JTextField txtPaciente;
    public static javax.swing.JTextField txtPadreRN;
    // End of variables declaration//GEN-END:variables
}
