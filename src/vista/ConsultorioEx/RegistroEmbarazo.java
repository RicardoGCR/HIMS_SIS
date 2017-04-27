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
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
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
    public RegistroEmbarazo() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
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
    }
    
    public void limpiar(){
        txtEstabOrigen.setText("");
        chkNoAplica.setText("");
        chkRef.setText("");
        txtEstablecimiento.setText("");
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
        if(estadoSeleccion.equals("enter")){
            lblTriaje.setText(String.valueOf(tbMadres.getValueAt(fila-1, 0)));
            lblActoMed.setText(String.valueOf(tbMadres.getValueAt(fila-1, 1)));
            lblDni.setText(String.valueOf(tbMadres.getValueAt(fila-1, 2)));
            txtPaciente.setText(String.valueOf(tbMadres.getValueAt(fila-1, 4)));
            lblHc.setText(String.valueOf(tbMadres.getValueAt(fila-1, 3)));
            txtIdHc.setText(String.valueOf(tbMadres.getValueAt(fila-1, 13)));
            pnlControl.setVisible(true);
            btnGuardar.setVisible(true);
            lblMant.setText("I");
            ConsultorioExtCarnetPerinatalCabecera consultorio1 = new ConsultorioExtCarnetPerinatalCabecera();
            txtEstablecimiento.setText(consultorio1.nombreEstablecimiento());
            consultorio1.mantenimientoConsultorioExtCarnetPerinatalCabecera("T",String.valueOf(tbMadres.getValueAt(fila-1, 0)));//para cambiar el estado de triaje de pendiente a ya atendido
        } else {
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
            txtEstablecimiento.setText(consultorio1.nombreEstablecimiento());
            consultorio1.mantenimientoConsultorioExtCarnetPerinatalCabecera("T",String.valueOf(tbMadres.getValueAt(fila, 0)));//para cambiar el estado de triaje de pendiente a ya atendido
        }
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
                System.out.println("ID CARNET PERINATAL CABECERA: " + Integer.parseInt(consultorio1.perinatalCabeceraID()));
                RegistroEmbarazoPrincipal GA =new RegistroEmbarazoPrincipal();
                Contenedor.add(GA);
                RegistroEmbarazoPrincipal.lblId.setText(consultorio1.perinatalCabeceraID());
                RegistroEmbarazoPrincipal.lblNina.setText(txtPaciente.getText());
                try {
                    GA.setMaximum(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
                jTabbedPane1.setSelectedIndex(1);
                RegistroEmbarazoPrincipal.lblId.setText(consultorio1.perinatalCabeceraID());
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
            System.out.println("Error ULTIMO ULTIMO: mantenimiento Registro Embarazo" + e.getMessage());
        }
        return retorna;
    }
    
//    public void validaSiEx(String triaje){
//        try {
//            ConsultorioExtRsVacunas vacs = new ConsultorioExtRsVacunas();
//            ConsultorioExtRsCcd ccd = new ConsultorioExtRsCcd();
//            ConsultorioExtRsDiagnosticoNutricional nutr = new ConsultorioExtRsDiagnosticoNutricional();
//            ConsultorioExtRsDiagnosticoDesarrollo desarrollo = new ConsultorioExtRsDiagnosticoDesarrollo();
//            ConsultorioExtRsEstimulacionTemprana estiTemp = new ConsultorioExtRsEstimulacionTemprana();
//            ConsultorioExtRsTamizajeNeonatal tamiNeo = new ConsultorioExtRsTamizajeNeonatal();
//            ConsultorioExtRsTamizajeAnemiaParasitosis tAnemia = new ConsultorioExtRsTamizajeAnemiaParasitosis();
//            ConsultorioExtRsTtoAntiparasitario tto = new ConsultorioExtRsTtoAntiparasitario();
//            ConsultorioExtRsSuplementacionHierro spHi = new ConsultorioExtRsSuplementacionHierro(); 
//            ConsultorioExtRsEpisodiosEnfermedadesPrev prev = new ConsultorioExtRsEpisodiosEnfermedadesPrev();
//            ConsultorioExtRsSuplementacionVitaminaA supV = new ConsultorioExtRsSuplementacionVitaminaA();
//            ConsultorioExtRsVisitasDomiciliarias vis = new ConsultorioExtRsVisitasDomiciliarias();
//            PreparedStatement cmd = cabecera.getCn().prepareStatement("SELECT        CONSULTORIO_EXT_RS_CABECERA.RS_ID\n" +
//"\n" +
//"FROM            CONSULTORIO_EXT_RS_CABECERA INNER JOIN\n" +
//"                         ADMISION_EMERGENCIA_TRIAJE ON CONSULTORIO_EXT_RS_CABECERA.TRIAJE_ID = ADMISION_EMERGENCIA_TRIAJE.TRIAJE_ID INNER JOIN\n" +
//"                         CAJA_DOCUMENTO_CABECERA ON ADMISION_EMERGENCIA_TRIAJE.id_documento = CAJA_DOCUMENTO_CABECERA.id_documento\n" +
//"WHERE CAJA_DOCUMENTO_CABECERA.id_hc = '"+triaje+"'");
//            ResultSet res = cmd.executeQuery();
//            if(res.next()){
//                id = Integer.parseInt(res.getString("RS_ID"));
//                //Porcentajes de avance
//                vacs.porcentajeVacunas(id);
//                ccd.porcentajeCCD(id);
//                nutr.porcentajeDN(id);
//                desarrollo.porcentajeDD(id);
//                estiTemp.porcentajeET(id);
//                tamiNeo.porcentajeTN(id);
//                tAnemia.porcentajeTAP(id);
//                tto.porcentajeTTO(id);
//                spHi.porcentajeSHM(id);
//                prev.porcentajeEET(id);
//                supV.porcentajeSVA(id);
//                vis.porcentajeVD(id);
//                datosPadres(triaje);
//                pnlPadres.setVisible(true);
//                btnGuardar.setVisible(false);
//                pnlContenedor.setVisible(true);
//            btnBuscarMadres.setEnabled(false);
//            }else {
//                limpiarDatosPadres();
//                habilitarDatos(true);
//                pnlContenedor.setVisible(false);
//                btnGuardar.setVisible(true);
//                pnlPadres.setVisible(true);
//                btnBuscarMadres.setEnabled(true);
//      
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error: validaTriaje: " + e.toString());
//        }
//    }
    
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
        jScrollPane4 = new javax.swing.JScrollPane();
        tbMadres = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            buttonGroup1 = new javax.swing.ButtonGroup();
            buttonGroup2 = new javax.swing.ButtonGroup();
            Confirmacion = new javax.swing.JDialog();
            jPanel3 = new javax.swing.JPanel();
            jLabel26 = new javax.swing.JLabel();
            ChkAnalf2 = new javax.swing.JTextField();
            jLabel40 = new javax.swing.JLabel();
            ChkEdad2 = new javax.swing.JTextField();
            jLabel41 = new javax.swing.JLabel();
            jLabel42 = new javax.swing.JLabel();
            lblPaciente = new javax.swing.JLabel();
            jPanel14 = new javax.swing.JPanel();
            jScrollPane5 = new javax.swing.JScrollPane();
            tbActual = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel16 = new javax.swing.JPanel();
                btnNuevoRegistro = new javax.swing.JButton();
                jPanel17 = new javax.swing.JPanel();
                jPanel18 = new javax.swing.JPanel();
                jScrollPane6 = new javax.swing.JScrollPane();
                tbAntigua = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jTabbedPane1 = new javax.swing.JTabbedPane();
                    jPanel2 = new javax.swing.JPanel();
                    jPanel1 = new javax.swing.JPanel();
                    jLabel1 = new javax.swing.JLabel();
                    lblusu = new javax.swing.JLabel();
                    btnBuscar = new javax.swing.JButton();
                    btnGuardar = new javax.swing.JButton();
                    jLabel2 = new javax.swing.JLabel();
                    ChkAnalf1 = new javax.swing.JTextField();
                    jLabel30 = new javax.swing.JLabel();
                    ChkEdad1 = new javax.swing.JTextField();
                    jLabel37 = new javax.swing.JLabel();
                    btnNuevo = new javax.swing.JButton();
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
                    ChkEdad = new javax.swing.JTextField();
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
                    lblId = new javax.swing.JLabel();
                    lblTriaje = new javax.swing.JLabel();
                    pnlMensaje = new javax.swing.JPanel();
                    lblMensaje = new javax.swing.JLabel();
                    btnSi = new javax.swing.JButton();
                    btnNo = new javax.swing.JButton();
                    Contenedor = new javax.swing.JDesktopPane();
                    ContenedorTablas = new javax.swing.JDesktopPane();

                    BuscarMadres.setAlwaysOnTop(true);
                    BuscarMadres.setMinimumSize(new java.awt.Dimension(615, 333));
                    BuscarMadres.setResizable(false);

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
                            .addGap(2, 2, 2)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(T7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0))
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

                    javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                    jPanel30.setLayout(jPanel30Layout);
                    jPanel30Layout.setHorizontalGroup(
                        jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 615, Short.MAX_VALUE)
                    );
                    jPanel30Layout.setVerticalGroup(
                        jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 9, Short.MAX_VALUE)
                    );

                    javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
                    jPanel28.setLayout(jPanel28Layout);
                    jPanel28Layout.setHorizontalGroup(
                        jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel28Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );
                    jPanel28Layout.setVerticalGroup(
                        jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                            .addContainerGap(17, Short.MAX_VALUE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0))
                    );

                    jScrollPane4.setBorder(null);

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
                    });
                    jScrollPane4.setViewportView(tbMadres);

                    javax.swing.GroupLayout BuscarMadresLayout = new javax.swing.GroupLayout(BuscarMadres.getContentPane());
                    BuscarMadres.getContentPane().setLayout(BuscarMadresLayout);
                    BuscarMadresLayout.setHorizontalGroup(
                        BuscarMadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                    );
                    BuscarMadresLayout.setVerticalGroup(
                        BuscarMadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BuscarMadresLayout.createSequentialGroup()
                            .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addGap(0, 0, 0))
                    );

                    Confirmacion.setAlwaysOnTop(true);
                    Confirmacion.setMinimumSize(new java.awt.Dimension(883, 227));

                    jPanel3.setBackground(new java.awt.Color(102, 102, 102));

                    jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    jLabel26.setForeground(new java.awt.Color(204, 204, 204));
                    jLabel26.setText("Leyenda");

                    ChkAnalf2.setEditable(false);
                    ChkAnalf2.setBackground(new java.awt.Color(232, 76, 61));
                    ChkAnalf2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    ChkAnalf2.setForeground(new java.awt.Color(102, 102, 102));
                    ChkAnalf2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    ChkAnalf2.setBorder(null);
                    ChkAnalf2.setPreferredSize(new java.awt.Dimension(28, 28));
                    ChkAnalf2.setSelectionColor(new java.awt.Color(255, 204, 51));
                    ChkAnalf2.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            ChkAnalf2CaretUpdate(evt);
                        }
                    });
                    ChkAnalf2.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            ChkAnalf2MouseClicked(evt);
                        }
                    });

                    jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel40.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel40.setText("Nueva Gestación");

                    ChkEdad2.setEditable(false);
                    ChkEdad2.setBackground(new java.awt.Color(39, 174, 97));
                    ChkEdad2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    ChkEdad2.setForeground(new java.awt.Color(255, 255, 255));
                    ChkEdad2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    ChkEdad2.setBorder(null);
                    ChkEdad2.setPreferredSize(new java.awt.Dimension(28, 28));
                    ChkEdad2.setSelectionColor(new java.awt.Color(255, 51, 51));
                    ChkEdad2.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            ChkEdad2CaretUpdate(evt);
                        }
                    });
                    ChkEdad2.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            ChkEdad2MouseClicked(evt);
                        }
                    });

                    jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel41.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel41.setText("Gestaciones Anteriores");

                    jLabel42.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                    jLabel42.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel42.setText("<html>Historial Obstétrico</html>");

                    lblPaciente.setText("jLabel5");

                    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                    jPanel3.setLayout(jPanel3Layout);
                    jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 3, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel26)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(ChkAnalf2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel40))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(ChkEdad2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel41))
                                .addComponent(lblPaciente))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblPaciente)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ChkAnalf2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel40))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ChkEdad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel41))
                            .addGap(37, 37, 37))
                    );

                    jPanel14.setBackground(new java.awt.Color(232, 76, 61));

                    jScrollPane5.setBorder(null);

                    tbActual.setBackground(new java.awt.Color(232, 76, 61));
                    tbActual.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    tbActual.setForeground(new java.awt.Color(255, 255, 255));
                    tbActual.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {

                        }
                    ));
                    tbActual.setGridColor(new java.awt.Color(232, 76, 61));
                    tbActual.setRowHeight(25);
                    tbActual.setSelectionBackground(new java.awt.Color(232, 76, 61));
                    tbActual.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tbActualMouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                            tbActualMousePressed(evt);
                        }
                    });
                    tbActual.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tbActualKeyPressed(evt);
                        }
                    });
                    jScrollPane5.setViewportView(tbActual);

                    jPanel16.setBackground(new java.awt.Color(232, 76, 61));

                    javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                    jPanel16.setLayout(jPanel16Layout);
                    jPanel16Layout.setHorizontalGroup(
                        jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 16, Short.MAX_VALUE)
                    );
                    jPanel16Layout.setVerticalGroup(
                        jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 72, Short.MAX_VALUE)
                    );

                    btnNuevoRegistro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    btnNuevoRegistro.setForeground(new java.awt.Color(240, 240, 240));
                    btnNuevoRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Document-32.png"))); // NOI18N
                    btnNuevoRegistro.setText("Nuevo");
                    btnNuevoRegistro.setBorder(null);
                    btnNuevoRegistro.setContentAreaFilled(false);
                    btnNuevoRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnNuevoRegistro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnNuevoRegistro.setIconTextGap(10);
                    btnNuevoRegistro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                    btnNuevoRegistro.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnNuevoRegistroActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                    jPanel14.setLayout(jPanel14Layout);
                    jPanel14Layout.setHorizontalGroup(
                        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnNuevoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                    );
                    jPanel14Layout.setVerticalGroup(
                        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(btnNuevoRegistro))
                            .addGap(0, 0, Short.MAX_VALUE))
                    );

                    jPanel17.setBackground(new java.awt.Color(255, 255, 255));

                    jPanel18.setBackground(new java.awt.Color(39, 174, 97));

                    javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
                    jPanel18.setLayout(jPanel18Layout);
                    jPanel18Layout.setHorizontalGroup(
                        jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 16, Short.MAX_VALUE)
                    );
                    jPanel18Layout.setVerticalGroup(
                        jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
                    );

                    jScrollPane6.setBorder(null);

                    tbAntigua.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                    ));
                    tbAntigua.setGridColor(new java.awt.Color(255, 255, 255));
                    tbAntigua.setRowHeight(25);
                    tbAntigua.setSelectionBackground(new java.awt.Color(39, 174, 97));
                    tbAntigua.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tbAntiguaMouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                            tbAntiguaMousePressed(evt);
                        }
                    });
                    tbAntigua.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tbAntiguaKeyPressed(evt);
                        }
                    });
                    jScrollPane6.setViewportView(tbAntigua);

                    javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                    jPanel17.setLayout(jPanel17Layout);
                    jPanel17Layout.setHorizontalGroup(
                        jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane6))
                    );
                    jPanel17Layout.setVerticalGroup(
                        jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel17Layout.createSequentialGroup()
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                    );

                    javax.swing.GroupLayout ConfirmacionLayout = new javax.swing.GroupLayout(Confirmacion.getContentPane());
                    Confirmacion.getContentPane().setLayout(ConfirmacionLayout);
                    ConfirmacionLayout.setHorizontalGroup(
                        ConfirmacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ConfirmacionLayout.createSequentialGroup()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addGroup(ConfirmacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    );
                    ConfirmacionLayout.setVerticalGroup(
                        ConfirmacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ConfirmacionLayout.createSequentialGroup()
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0))
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );

                    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                    getContentPane().setLayout(null);

                    jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                    jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                    jPanel1.setBackground(new java.awt.Color(51, 51, 51));

                    jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel1.setText("<html>Carnet de<br>Control Materno <br>Perinatal <span style=\"font-size:'15px'\"></span></html>");

                    lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                    lblusu.setForeground(new java.awt.Color(255, 255, 255));
                    lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                    lblusu.setText("Silvana");
                    lblusu.setFocusable(false);
                    lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                    btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    btnBuscar.setForeground(new java.awt.Color(240, 240, 240));
                    btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                    btnBuscar.setText("Buscar");
                    btnBuscar.setContentAreaFilled(false);
                    btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnBuscar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                    btnBuscar.setIconTextGap(30);
                    btnBuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscarActionPerformed(evt);
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

                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                    jPanel1.setLayout(jPanel1Layout);
                    jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                            .addGap(12, 12, 12)
                            .addComponent(btnBuscar)
                            .addGap(137, 137, 137)
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

                    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                    jPanel4.setLayout(jPanel4Layout);
                    jPanel4Layout.setHorizontalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addComponent(ChkEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel28)
                                .addComponent(jLabel27))
                            .addGap(0, 46, Short.MAX_VALUE))
                    );
                    jPanel4Layout.setVerticalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel27)
                                    .addGap(0, 0, 0)
                                    .addComponent(jLabel28))
                                .addComponent(ChkEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    txtCodigo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
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
                    txtEstablecimiento.setHorizontalAlignment(javax.swing.JTextField.LEFT);
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

                    lblDni.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
                    lblDni.setForeground(new java.awt.Color(243, 156, 17));

                    jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
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

                    lblHc.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
                    lblHc.setForeground(new java.awt.Color(243, 156, 17));
                    lblHc.setText("____________");

                    jLabel48.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
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

                    lblActoMed.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    lblActoMed.setForeground(new java.awt.Color(243, 156, 17));
                    lblActoMed.setText("______________");

                    jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
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

                    lblId.setText("jLabel12");

                    lblTriaje.setText("jLabel5");

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
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                                            .addGap(73, 73, 73)
                                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnlControlLayout.createSequentialGroup()
                                                    .addComponent(chkSis)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(chkEssalud)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(chkPrivado))
                                                .addComponent(txtEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(lblCelular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(pnlControlLayout.createSequentialGroup()
                                                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(pnlControlLayout.createSequentialGroup()
                                                            .addComponent(jLabel10)
                                                            .addGap(35, 35, 35)
                                                            .addComponent(lblDni, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(67, 67, 67)
                                                            .addComponent(jLabel48)
                                                            .addGap(93, 93, 93))
                                                        .addGroup(pnlControlLayout.createSequentialGroup()
                                                            .addComponent(lblDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(47, 47, 47)
                                                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(lblMant, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblId)
                                                                .addComponent(txtIdHc, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblTriaje))))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblHc, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(pnlControlLayout.createSequentialGroup()
                                            .addComponent(jLabel38)
                                            .addGap(23, 23, 23)
                                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlControlLayout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addGap(63, 63, 63)
                                            .addComponent(lblActoMed, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                                .addComponent(lblDepartamento))))
                                    .addGap(39, 39, 39)))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                    .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblActoMed)
                                        .addComponent(jLabel10)
                                        .addComponent(lblHc))))
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
                                .addComponent(lblDistrito)
                                .addComponent(lblId))
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
                                        .addComponent(lblCelular))
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
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addContainerGap(898, Short.MAX_VALUE))
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
                                .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(pnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, 986, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addContainerGap())
                    );
                    jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(pnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
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

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

    }//GEN-LAST:event_btnBuscarActionPerformed

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
        ConsultorioExtEsnitss consultorio1 = new ConsultorioExtEsnitss();
        consultorio1.consultorioExListarC(txtBuscar.getText(), "Q", tbMadres);
        //        pnlMensaje.setVisible(false);
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
        if(evt.getClickCount()==2){
//            enviarDatosMadres();
            Confirmacion.setVisible(true);
            Confirmacion.setLocationRelativeTo(null);//en el centro
            Confirmacion.setResizable(false);
            Confirmacion.getContentPane().setBackground(Color.WHITE);
            int fila = tbMadres.getSelectedRow();
            ConsultorioExtCarnetPerinatalCabecera consultorio1 = new ConsultorioExtCarnetPerinatalCabecera();
            consultorio1.consultorioExControlPerinatalCabListar(String.valueOf(tbMadres.getValueAt(fila, 13)),"AC",tbActual,"actual"); //llenar tabla de registro actuales
            consultorio1.consultorioExControlPerinatalCabListar(String.valueOf(tbMadres.getValueAt(fila, 13)),"AN",tbAntigua,"antigua"); //llenar tabla de registro antiguos
            lblPaciente.setText(String.valueOf(tbMadres.getValueAt(fila, 4)));
            if(tbActual.getRowCount()!=0)
                btnNuevoRegistro.setEnabled(false);
            else
                btnNuevoRegistro.setEnabled(true);
            BuscarMadres.dispose();
            estadoSeleccion = "click";
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
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
//            enviarDatosMadres();
            Confirmacion.setVisible(true);
            Confirmacion.setLocationRelativeTo(null);//en el centro
            Confirmacion.setResizable(false);
            Confirmacion.getContentPane().setBackground(Color.WHITE);
            int fila = tbMadres.getSelectedRow();
            ConsultorioExtCarnetPerinatalCabecera consultorio1 = new ConsultorioExtCarnetPerinatalCabecera();
            consultorio1.consultorioExControlPerinatalCabListar(String.valueOf(tbMadres.getValueAt(fila, 13)),"AC",tbActual,"actual"); //llenar tabla de registro actuales
            consultorio1.consultorioExControlPerinatalCabListar(String.valueOf(tbMadres.getValueAt(fila, 13)),"AN",tbAntigua,"antigua"); //llenar tabla de registro antiguos
            lblPaciente.setText(String.valueOf(tbMadres.getValueAt(fila, 4)));
            if(tbActual.getRowCount()!=0)
                btnNuevoRegistro.setEnabled(false);
            else
                btnNuevoRegistro.setEnabled(true);
            BuscarMadres.dispose();
            estadoSeleccion = "enter";
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

    private void tbActualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbActualMouseClicked
        if(evt.getClickCount()==2){
            int fila = tbActual.getSelectedRow();
            RegistroEmbarazoPrincipal GA =new RegistroEmbarazoPrincipal();
            Contenedor.add(GA);
            RegistroEmbarazoPrincipal.lblNina.setText(lblPaciente.getText());
            try {
                GA.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTabbedPane1.setSelectedIndex(1);
            RegistroEmbarazoPrincipal.lblId.setText(String.valueOf(tbActual.getValueAt(fila, 0)));
            Confirmacion.dispose();
            BuscarMadres.dispose();
        }
    }//GEN-LAST:event_tbActualMouseClicked

    private void tbActualMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbActualMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbActualMousePressed

    private void tbActualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbActualKeyPressed
        ConsultorioExtCarnetPerinatalCabecera consultorio1 = new ConsultorioExtCarnetPerinatalCabecera();
        int fila = tbActual.getSelectedRow();
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            RegistroEmbarazoPrincipal GA =new RegistroEmbarazoPrincipal();
            Contenedor.add(GA);
            RegistroEmbarazoPrincipal.lblNina.setText(lblPaciente.getText());
            try {
                GA.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTabbedPane1.setSelectedIndex(1);
            RegistroEmbarazoPrincipal.lblId.setText(String.valueOf(tbActual.getValueAt(fila, 0)));
            Confirmacion.dispose();
            BuscarMadres.dispose();
        }
        char eliminar = evt.getKeyChar();
        if(eliminar==KeyEvent.VK_DELETE){
            consultorio1.setCpId(Integer.parseInt(String.valueOf(tbActual.getValueAt(fila, 0))));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalCabecera("E",""))
                btnNuevoRegistro.setEnabled(true);
                DefaultTableModel modelo4 = (DefaultTableModel)tbActual.getModel(); 
                int b4=tbActual.getRowCount();
                for(int j=0;j<b4;j++){
                            modelo4.removeRow(0);
                }
        }
    }//GEN-LAST:event_tbActualKeyPressed

    private void tbAntiguaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAntiguaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbAntiguaMouseClicked

    private void tbAntiguaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAntiguaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbAntiguaMousePressed

    private void tbAntiguaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAntiguaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbAntiguaKeyPressed

    private void ChkAnalf2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkAnalf2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnalf2CaretUpdate

    private void ChkAnalf2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkAnalf2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnalf2MouseClicked

    private void ChkEdad2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkEdad2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdad2CaretUpdate

    private void ChkEdad2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkEdad2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdad2MouseClicked

    private void btnNuevoRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoRegistroActionPerformed
        BuscarMadres.dispose();
        Confirmacion.dispose();
        enviarDatosMadres();
    }//GEN-LAST:event_btnNuevoRegistroActionPerformed

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
    public static javax.swing.JTextField ChkAnalf2;
    public static javax.swing.JTextField ChkEdad;
    public static javax.swing.JTextField ChkEdad1;
    public static javax.swing.JTextField ChkEdad2;
    public static javax.swing.JTextField ChkSec;
    public static javax.swing.JTextField ChkSup;
    public static javax.swing.JTextField ChkSupnU;
    public static javax.swing.JTextField Chkprim;
    private javax.swing.JDialog Confirmacion;
    private javax.swing.JDesktopPane Contenedor;
    public static javax.swing.JDesktopPane ContenedorTablas;
    private javax.swing.JLabel T7;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevoRegistro;
    private javax.swing.JButton btnSi;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox chkEssalud;
    public static javax.swing.JTextField chkNoAplica;
    private javax.swing.JCheckBox chkPrivado;
    public static javax.swing.JTextField chkRef;
    private javax.swing.JCheckBox chkSis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
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
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
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
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    public static javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblActoMed;
    public static javax.swing.JLabel lblCelular;
    public static javax.swing.JLabel lblDepartamento;
    public static javax.swing.JLabel lblDireccion;
    public static javax.swing.JLabel lblDistrito;
    private javax.swing.JLabel lblDni;
    public static javax.swing.JTextField lblEdad;
    public static javax.swing.JLabel lblEstadoCiv;
    private javax.swing.JLabel lblHc;
    private javax.swing.JLabel lblId;
    public static javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMensaje;
    public static javax.swing.JLabel lblOcupacion;
    private javax.swing.JLabel lblPaciente;
    public static javax.swing.JLabel lblProvincia;
    public static javax.swing.JLabel lblSector;
    public static javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTriaje;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlControl;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JTable tbActual;
    private javax.swing.JTable tbAntigua;
    private javax.swing.JTable tbMadres;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtEstabOrigen;
    public static javax.swing.JTextField txtEstablecimiento;
    private javax.swing.JTextField txtIdHc;
    public static javax.swing.JTextField txtPaciente;
    private javax.swing.JTextField txtPadreRN;
    // End of variables declaration//GEN-END:variables
}
