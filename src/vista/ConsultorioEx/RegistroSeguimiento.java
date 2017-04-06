/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.ConsultorioEx.ConsultorioExtRsCabecera;
import modelos.ConsultorioEx.ConsultorioExtRsCcd;
import modelos.ConsultorioEx.ConsultorioExtRsDiagnosticoDesarrollo;
import modelos.ConsultorioEx.ConsultorioExtRsDiagnosticoNutricional;
import modelos.ConsultorioEx.ConsultorioExtRsEstimulacionTemprana;
import modelos.ConsultorioEx.ConsultorioExtRsTamizajeAnemiaParasitosis;
import modelos.ConsultorioEx.ConsultorioExtRsTamizajeNeonatal;
import modelos.ConsultorioEx.ConsultorioExtRsTtoAntiparasitario;
import modelos.ConsultorioEx.ConsultorioExtRsVacunas;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import static modelos.hospitalizacion.HospitalizacionPapeletas.getCn;
import servicios.Conexion;
import static vista.ConsultorioEx.RSAIVacunas.dtElab;

/**
 *
 * @author MYS1
 */
public class RegistroSeguimiento extends javax.swing.JFrame {

    DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    ResultSet r;
    PreparedStatement pstm;
    ConsultorioExtRsCabecera cabecera = new ConsultorioExtRsCabecera();
    int id;
    public RegistroSeguimiento() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        jTabbedPane1.setEnabledAt(0,false);
        jTabbedPane1.setEnabledAt(1, false);
        pnlMensaje.setVisible(false);
        pnlSexo.setBackground(Color.white);
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
        conexion = c.conectar();
        lblHc.setVisible(false);
        txtPaciente.requestFocus();
        pnlPadres.setVisible(false);
        lblPadres.setVisible(false);
        pnlContenedor.setVisible(false);
        lblTriaje.setVisible(false);
        lblGenero.setVisible(false);
        lblHcMadre.setVisible(false);
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
    
    public void enviarDatosNinos(){
        int fila = tbNinos.getSelectedRow();
        RegistroSeguimiento.lblActoMedico.setText(String.valueOf(tbNinos.getValueAt(fila, 1)));
        RegistroSeguimiento.lblDni.setText(String.valueOf(tbNinos.getValueAt(fila, 2)));
        RegistroSeguimiento.lblNHC.setText(String.valueOf(tbNinos.getValueAt(fila, 3)));
        RegistroSeguimiento.txtPaciente.setText(String.valueOf(tbNinos.getValueAt(fila, 4)));
        RegistroSeguimiento.lblHc.setText(String.valueOf(tbNinos.getValueAt(fila, 0)));
        RegistroSeguimiento.lblTriaje.setText(String.valueOf(tbNinos.getValueAt(fila, 9)));
        RegistroSeguimiento.lblGenero.setText(String.valueOf(tbNinos.getValueAt(fila, 5)));
        RegistroSeguimiento.lblFecNac.setText(String.valueOf(tbNinos.getValueAt(fila, 6)));
        if(String.valueOf(tbNinos.getValueAt(fila, 5)).equals("F")){
            pnlSexo.setBackground(new Color(255,71,163));
            pnlSexo.setVisible(true);
        }   else {
            pnlSexo.setBackground(new Color(13,183,225));
            pnlSexo.setVisible(true);
        }
        BuscarNinos.dispose();
    }
    
    public void enviarDatosMadres(){
        int fila = tbMadres.getSelectedRow();
        RegistroSeguimiento.txtMadre.setText(String.valueOf(tbMadres.getValueAt(fila, 2)));
        RegistroSeguimiento.lblTelefono.setText(String.valueOf(tbMadres.getValueAt(fila, 3)));
        RegistroSeguimiento.lblDireccion.setText(String.valueOf(tbMadres.getValueAt(fila, 4)));
        RegistroSeguimiento.lblHcMadre.setText(String.valueOf(tbMadres.getValueAt(fila, 5)));
        BuscarMadres.dispose();
    }
    
    public void btnGuardar(){
        try {
            if(cbxTipoSeguro.getSelectedIndex()>0 || txtPadre.getText().equals("")){
            ConsultorioExtRsCabecera consultorio1  = new ConsultorioExtRsCabecera();
                AdmisionEmergenciaCabecera adEmerCab7 = new AdmisionEmergenciaCabecera();
                consultorio1.setHc_nino(lblHc.getText());
                consultorio1.setHc_madre(lblHcMadre.getText());
                consultorio1.setRsTipoRiesgo(txtRiesgo.getText());
                consultorio1.setRsPadre(txtPadre.getText());
                consultorio1.setRsAfilSis(txtCodigo.getText());
                consultorio1.setTriaje_id(lblTriaje.getText());
                consultorio1.setTipo_seguro(cbxTipoSeguro.getSelectedItem().toString());
                consultorio1.setUsuario(adEmerCab7.codUsuario(lblusu.getText()));
                if(consultorio1.mantenimientoConsultorioRsCabecera("I")==true){
    //              OBTENER ID DEL ULTIMO REGISTRO
                    id = cabecera.idCabecera();
                    pnlMensaje.setVisible(true);
                    lblMensaje.setText("Datos guardados de forma correcta");
                    pnlContenedor.setVisible(true);
    //                limpiar();
    //                habilitarCampos(false);
                    btnGuardar.setEnabled(false);
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
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Verifique, que los datos estén correctos");
                pnlMensaje.setBackground(new Color(255,91,70));
                btnSi.setVisible(true);
                btnNo.setVisible(false);
                btnSi.setText("OK");
            }
        } catch (Exception e) {
            System.out.println("Error: btnGuardar" + e.getMessage());
        }
    }
    
    public void datosPadres(String triaje){
        String consulta="";
        try {
            consulta="EXEC CONSULTORIO_EXT_RS_CABECERA_DATOS_PADRES ?";
            PreparedStatement cmd = cabecera.getCn().prepareStatement(consulta);
            cmd.setString(1, triaje);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                txtMadre.setText(r.getString(1)); 
                txtRiesgo.setText(r.getString(2)); 
                lblTelefono.setText(r.getString(3)); 
                lblDireccion.setText(r.getString(4)); 
                lblSector.setText(r.getString(5)); 
                cbxTipoSeguro.setSelectedItem(r.getString(6)); 
                txtPadre.setText(r.getString(7)); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: datosPadres " + e.getMessage());
        }
    }

    public void validaTriaje(String triaje){
        try {
            ConsultorioExtRsVacunas vacs = new ConsultorioExtRsVacunas();
            ConsultorioExtRsCcd ccd = new ConsultorioExtRsCcd();
            ConsultorioExtRsDiagnosticoNutricional nutr = new ConsultorioExtRsDiagnosticoNutricional();
            ConsultorioExtRsDiagnosticoDesarrollo desarrollo = new ConsultorioExtRsDiagnosticoDesarrollo();
            ConsultorioExtRsEstimulacionTemprana estiTemp = new ConsultorioExtRsEstimulacionTemprana();
            ConsultorioExtRsTamizajeNeonatal tamiNeo = new ConsultorioExtRsTamizajeNeonatal();
            ConsultorioExtRsTamizajeAnemiaParasitosis tAnemia = new ConsultorioExtRsTamizajeAnemiaParasitosis();
            PreparedStatement cmd = cabecera.getCn().prepareStatement("SELECT RS_ID FROM CONSULTORIO_EXT_RS_CABECERA WHERE TRIAJE_ID = '"+triaje+"'");
            ResultSet res = cmd.executeQuery();
            if(res.next()){
                id = Integer.parseInt(res.getString("RS_ID"));
                //Porcentajes de avance
                vacs.porcentajeVacunas(id);
                ccd.porcentajeCCD(id);
                nutr.porcentajeDN(id);
                desarrollo.porcentajeDD(id);
                estiTemp.porcentajeET(id);
                tamiNeo.porcentajeTN(id);
                tAnemia.porcentajeTAP(id);
                datosPadres(triaje);
                pnlPadres.setVisible(true);
                lblPadres.setVisible(true);
                btnGuardar.setVisible(false);
                pnlContenedor.setVisible(true);
            }else {
                limpiarDatosPadres();
                habilitarDatos(true);
                pnlContenedor.setVisible(false);
                btnGuardar.setVisible(true);
                pnlPadres.setVisible(true);
                lblPadres.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: validaTriaje: " + e.toString());
        }
    }
    
    public void limpiarDatosPadres(){
        lblTelefono.setText("_____________________");
        lblDireccion.setText("_______________________________________________________________");
        txtMadre.setText("");
        txtCodigo.setText("");
        cbxTipoSeguro.setSelectedIndex(0);
        txtPadre.setText("");
        lblSector.setText("_______________");
        txtRiesgo.setText("");
    }
    
    public void habilitarDatos(boolean opcion){
        txtRiesgo.setEditable(opcion);
        cbxTipoSeguro.setEditable(opcion);
        txtPadre.setEditable(opcion);
        lblDireccion.setEditable(opcion);
        lblTelefono.setEditable(opcion);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BuscarNinos = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        txtBuscarNino = new javax.swing.JTextField();
        T6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbNinos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            BuscarMadres = new javax.swing.JDialog();
            jPanel28 = new javax.swing.JPanel();
            jLabel31 = new javax.swing.JLabel();
            jPanel29 = new javax.swing.JPanel();
            txtBuscarMadres = new javax.swing.JTextField();
            T7 = new javax.swing.JLabel();
            jPanel30 = new javax.swing.JPanel();
            jScrollPane4 = new javax.swing.JScrollPane();
            tbMadres = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                lblusu = new javax.swing.JLabel();
                pnlMensaje = new javax.swing.JPanel();
                lblMensaje = new javax.swing.JLabel();
                btnSi = new javax.swing.JButton();
                btnNo = new javax.swing.JButton();
                jTabbedPane1 = new javax.swing.JTabbedPane();
                jPanel2 = new javax.swing.JPanel();
                jPanel4 = new javax.swing.JPanel();
                pnlContenedor = new javax.swing.JPanel();
                jPanel12 = new javax.swing.JPanel();
                jLabel4 = new javax.swing.JLabel();
                lblPorcentajeVacunas = new javax.swing.JLabel();
                jPanel13 = new javax.swing.JPanel();
                jLabel5 = new javax.swing.JLabel();
                lblPorcentajeCCD = new javax.swing.JLabel();
                jPanel15 = new javax.swing.JPanel();
                jLabel6 = new javax.swing.JLabel();
                lblPorcentajeDN = new javax.swing.JLabel();
                jPanel16 = new javax.swing.JPanel();
                lblPorcentajeDD = new javax.swing.JLabel();
                jLabel27 = new javax.swing.JLabel();
                jPanel17 = new javax.swing.JPanel();
                jLabel8 = new javax.swing.JLabel();
                lblPorcentajeET = new javax.swing.JLabel();
                jPanel18 = new javax.swing.JPanel();
                jLabel9 = new javax.swing.JLabel();
                lblPorcentajeTN = new javax.swing.JLabel();
                jPanel19 = new javax.swing.JPanel();
                jLabel19 = new javax.swing.JLabel();
                lblPorcentajeTAP = new javax.swing.JLabel();
                jPanel20 = new javax.swing.JPanel();
                jLabel11 = new javax.swing.JLabel();
                lblPorcentajeTTO = new javax.swing.JLabel();
                jPanel22 = new javax.swing.JPanel();
                jLabel18 = new javax.swing.JLabel();
                lblPorcentajeEEP = new javax.swing.JLabel();
                jPanel23 = new javax.swing.JPanel();
                jLabel14 = new javax.swing.JLabel();
                lblPorcentajeSVA = new javax.swing.JLabel();
                jPanel24 = new javax.swing.JPanel();
                jLabel15 = new javax.swing.JLabel();
                lblPorcentajeVD = new javax.swing.JLabel();
                jPanel25 = new javax.swing.JPanel();
                jLabel16 = new javax.swing.JLabel();
                jPanel21 = new javax.swing.JPanel();
                jLabel12 = new javax.swing.JLabel();
                lblPorcentajeSHM = new javax.swing.JLabel();
                txtSis = new javax.swing.JPanel();
                jPanel9 = new javax.swing.JPanel();
                txtPaciente = new javax.swing.JTextField();
                btnBuscarNino = new javax.swing.JButton();
                jLabel2 = new javax.swing.JLabel();
                pnlSexo = new javax.swing.JPanel();
                jLabel3 = new javax.swing.JLabel();
                lblDni = new javax.swing.JLabel();
                jLabel13 = new javax.swing.JLabel();
                lblFecNac = new javax.swing.JLabel();
                lblPadres = new javax.swing.JLabel();
                jLabel28 = new javax.swing.JLabel();
                lblActoMedico = new javax.swing.JLabel();
                lblHc = new javax.swing.JLabel();
                jLabel29 = new javax.swing.JLabel();
                lblNHC = new javax.swing.JLabel();
                pnlPadres = new javax.swing.JPanel();
                jLabel23 = new javax.swing.JLabel();
                txtPadre = new javax.swing.JTextField();
                jLabel22 = new javax.swing.JLabel();
                cbxTipoSeguro = new javax.swing.JComboBox();
                jLabel26 = new javax.swing.JLabel();
                jLabel10 = new javax.swing.JLabel();
                lblDireccion = new javax.swing.JTextField();
                btnGuardar = new javax.swing.JButton();
                txtCodigo = new javax.swing.JTextField();
                lblHcMadre = new javax.swing.JLabel();
                txtRiesgo = new javax.swing.JTextField();
                jLabel25 = new javax.swing.JLabel();
                lblTelefono = new javax.swing.JTextField();
                jLabel24 = new javax.swing.JLabel();
                jLabel21 = new javax.swing.JLabel();
                jPanel10 = new javax.swing.JPanel();
                txtMadre = new javax.swing.JTextField();
                btnBuscarMadres = new javax.swing.JButton();
                lblSector = new javax.swing.JLabel();
                jLabel17 = new javax.swing.JLabel();
                lblTriaje = new javax.swing.JTextField();
                lblGenero = new javax.swing.JLabel();
                jPanel3 = new javax.swing.JPanel();
                Contenedor = new javax.swing.JDesktopPane();

                BuscarNinos.setAlwaysOnTop(true);
                BuscarNinos.setMinimumSize(new java.awt.Dimension(615, 333));
                BuscarNinos.setResizable(false);

                jPanel7.setBackground(new java.awt.Color(102, 102, 102));

                jLabel30.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel30.setForeground(new java.awt.Color(255, 255, 255));
                jLabel30.setText("<html>Niños<span style=\"font-size:'15px'\"><br>menores de 11 años</br></span></html>");

                jPanel27.setBackground(new java.awt.Color(255, 255, 255));

                txtBuscarNino.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtBuscarNino.setBorder(null);
                txtBuscarNino.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscarNinoCaretUpdate(evt);
                    }
                });
                txtBuscarNino.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtBuscarNinoMouseClicked(evt);
                    }
                });
                txtBuscarNino.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtBuscarNinoActionPerformed(evt);
                    }
                });
                txtBuscarNino.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscarNinoKeyPressed(evt);
                    }
                });

                T6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                T6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                T6.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        T6MouseClicked(evt);
                    }
                });

                javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
                jPanel27.setLayout(jPanel27Layout);
                jPanel27Layout.setHorizontalGroup(
                    jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtBuscarNino, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(T6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );
                jPanel27Layout.setVerticalGroup(
                    jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(T6)
                            .addComponent(txtBuscarNino, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                );

                jPanel8.setBackground(new java.awt.Color(255, 71, 163));

                jPanel26.setBackground(new java.awt.Color(13, 183, 225));

                javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
                jPanel26.setLayout(jPanel26Layout);
                jPanel26Layout.setHorizontalGroup(
                    jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 314, Short.MAX_VALUE)
                );
                jPanel26Layout.setVerticalGroup(
                    jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 21, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel7Layout.setVerticalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(549, 549, 549))
                );

                jScrollPane3.setBorder(null);

                tbNinos.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
                tbNinos.setGridColor(new java.awt.Color(255, 255, 255));
                tbNinos.setRowHeight(25);
                tbNinos.setSelectionBackground(new java.awt.Color(102, 102, 102));
                tbNinos.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbNinosMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbNinosMousePressed(evt);
                    }
                });
                tbNinos.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbNinosKeyPressed(evt);
                    }
                });
                jScrollPane3.setViewportView(tbNinos);

                javax.swing.GroupLayout BuscarNinosLayout = new javax.swing.GroupLayout(BuscarNinos.getContentPane());
                BuscarNinos.getContentPane().setLayout(BuscarNinosLayout);
                BuscarNinosLayout.setHorizontalGroup(
                    BuscarNinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                );
                BuscarNinosLayout.setVerticalGroup(
                    BuscarNinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BuscarNinosLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                );

                BuscarMadres.setAlwaysOnTop(true);
                BuscarMadres.setMinimumSize(new java.awt.Dimension(615, 333));
                BuscarMadres.setResizable(false);

                jPanel28.setBackground(new java.awt.Color(102, 102, 102));

                jLabel31.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel31.setForeground(new java.awt.Color(255, 255, 255));
                jLabel31.setText("<html>Madre<span style=\"font-size:'15px'\"><br>del menor</br></span></html>");

                jPanel29.setBackground(new java.awt.Color(255, 255, 255));

                txtBuscarMadres.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtBuscarMadres.setBorder(null);
                txtBuscarMadres.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscarMadresCaretUpdate(evt);
                    }
                });
                txtBuscarMadres.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtBuscarMadresMouseClicked(evt);
                    }
                });
                txtBuscarMadres.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtBuscarMadresActionPerformed(evt);
                    }
                });
                txtBuscarMadres.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscarMadresKeyPressed(evt);
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
                        .addComponent(txtBuscarMadres, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(txtBuscarMadres, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addGap(0, 21, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
                jPanel28.setLayout(jPanel28Layout);
                jPanel28Layout.setHorizontalGroup(
                    jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel28Layout.setVerticalGroup(
                    jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(549, 549, 549))
                );

                jScrollPane4.setBorder(null);

                tbMadres.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
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
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(0, 153, 102));

                jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("<html>Registro de seguimiento a la atención integral <span style=\"font-size:'15px'\"><br>Niños menores de 11 años</br></span></html>");

                lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                lblusu.setForeground(new java.awt.Color(255, 255, 255));
                lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
                lblusu.setText("Silvana");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblusu))
                        .addGap(587, 587, 587))
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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                pnlMensajeLayout.setVerticalGroup(
                    pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMensajeLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMensaje)
                            .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                pnlContenedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                jPanel12.setBackground(new java.awt.Color(25, 188, 157));
                jPanel12.setPreferredSize(new java.awt.Dimension(83, 45));
                jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jPanel12MouseClicked(evt);
                    }
                });

                jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(255, 255, 255));
                jLabel4.setText("Vacunas");
                jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jLabel4MouseClicked(evt);
                    }
                });

                lblPorcentajeVacunas.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                lblPorcentajeVacunas.setForeground(new java.awt.Color(255, 255, 255));
                lblPorcentajeVacunas.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
                lblPorcentajeVacunas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Círculo sin marcar-100 (4).png"))); // NOI18N
                lblPorcentajeVacunas.setText("0 %");
                lblPorcentajeVacunas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                lblPorcentajeVacunas.setIconTextGap(-10);
                lblPorcentajeVacunas.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        lblPorcentajeVacunasMouseClicked(evt);
                    }
                });

                javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                jPanel12.setLayout(jPanel12Layout);
                jPanel12Layout.setHorizontalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblPorcentajeVacunas)
                        .addContainerGap(31, Short.MAX_VALUE))
                );
                jPanel12Layout.setVerticalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPorcentajeVacunas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel13.setBackground(new java.awt.Color(45, 204, 112));
                jPanel13.setPreferredSize(new java.awt.Dimension(298, 45));
                jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jPanel13MouseClicked(evt);
                    }
                });

                jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
                jLabel5.setForeground(new java.awt.Color(255, 255, 255));
                jLabel5.setText("<html>Control de <br>Crecimiento y<br>Desarrollo</br></span></html>");
                jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jLabel5MouseClicked(evt);
                    }
                });

                lblPorcentajeCCD.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                lblPorcentajeCCD.setForeground(new java.awt.Color(255, 255, 255));
                lblPorcentajeCCD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Círculo sin marcar-100 (3).png"))); // NOI18N
                lblPorcentajeCCD.setText("0 %");
                lblPorcentajeCCD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

                javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                jPanel13.setLayout(jPanel13Layout);
                jPanel13Layout.setHorizontalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(lblPorcentajeCCD)
                        .addGap(41, 41, 41))
                );
                jPanel13Layout.setVerticalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPorcentajeCCD, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                );

                jPanel15.setBackground(new java.awt.Color(50, 151, 219));
                jPanel15.setPreferredSize(new java.awt.Dimension(205, 45));

                jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(255, 255, 255));
                jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel6.setText("<html>Diagnostico<br>Nutricional</br></span></html>");
                jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jLabel6MouseClicked(evt);
                    }
                });

                lblPorcentajeDN.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                lblPorcentajeDN.setForeground(new java.awt.Color(255, 255, 255));
                lblPorcentajeDN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lblPorcentajeDN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Círculo sin marcar-100.png"))); // NOI18N
                lblPorcentajeDN.setText("0 %");
                lblPorcentajeDN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

                javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                jPanel15.setLayout(jPanel15Layout);
                jPanel15Layout.setHorizontalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(lblPorcentajeDN, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                jPanel15Layout.setVerticalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(19, 19, 19))
                    .addComponent(lblPorcentajeDN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                jPanel16.setBackground(new java.awt.Color(154, 89, 181));
                jPanel16.setPreferredSize(new java.awt.Dimension(222, 45));
                jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jPanel16MouseClicked(evt);
                    }
                });

                lblPorcentajeDD.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                lblPorcentajeDD.setForeground(new java.awt.Color(255, 255, 255));
                lblPorcentajeDD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Círculo sin marcar-100 (1).png"))); // NOI18N
                lblPorcentajeDD.setText("0%");
                lblPorcentajeDD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

                jLabel27.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
                jLabel27.setForeground(new java.awt.Color(255, 255, 255));
                jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel27.setText("<html>Diagnostico de<br>Desarrollo</br></span></html>");
                jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jLabel27MouseClicked(evt);
                    }
                });

                javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                jPanel16.setLayout(jPanel16Layout);
                jPanel16Layout.setHorizontalGroup(
                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(lblPorcentajeDD)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel16Layout.setVerticalGroup(
                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(lblPorcentajeDD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                jPanel17.setBackground(new java.awt.Color(67, 94, 120));
                jPanel17.setPreferredSize(new java.awt.Dimension(238, 45));
                jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jPanel17MouseClicked(evt);
                    }
                });

                jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
                jLabel8.setForeground(new java.awt.Color(255, 255, 255));
                jLabel8.setText("<html>Estimulación<br>Temprana</br></span></html>");
                jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jLabel8MouseClicked(evt);
                    }
                });

                lblPorcentajeET.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                lblPorcentajeET.setForeground(new java.awt.Color(255, 255, 255));
                lblPorcentajeET.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Círculo sin marcar-100 (5).png"))); // NOI18N
                lblPorcentajeET.setText("0 %");
                lblPorcentajeET.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

                javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                jPanel17.setLayout(jPanel17Layout);
                jPanel17Layout.setHorizontalGroup(
                    jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPorcentajeET)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel17Layout.setVerticalGroup(
                    jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPorcentajeET, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel18.setBackground(new java.awt.Color(23, 160, 134));

                jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
                jLabel9.setForeground(new java.awt.Color(255, 255, 255));
                jLabel9.setText("<html>Tamizaje<br>Neonatal</br></span></html>");
                jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jLabel9MouseClicked(evt);
                    }
                });

                lblPorcentajeTN.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                lblPorcentajeTN.setForeground(new java.awt.Color(255, 255, 255));
                lblPorcentajeTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Círculo sin marcar-100 (2).png"))); // NOI18N
                lblPorcentajeTN.setText("0 %");
                lblPorcentajeTN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

                javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
                jPanel18.setLayout(jPanel18Layout);
                jPanel18Layout.setHorizontalGroup(
                    jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPorcentajeTN)
                        .addGap(32, 32, 32))
                );
                jPanel18Layout.setVerticalGroup(
                    jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addComponent(lblPorcentajeTN, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                );

                jPanel19.setBackground(new java.awt.Color(39, 174, 97));

                jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                jLabel19.setText("<html>Tamizaje de Anemia Parasitosis<br>Y Test De Graham</br></html>");
                jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jLabel19MouseClicked(evt);
                    }
                });

                lblPorcentajeTAP.setText("jLabel32");

                javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
                jPanel19.setLayout(jPanel19Layout);
                jPanel19Layout.setHorizontalGroup(
                    jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(lblPorcentajeTAP)
                        .addContainerGap(40, Short.MAX_VALUE))
                );
                jPanel19Layout.setVerticalGroup(
                    jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPorcentajeTAP)
                        .addContainerGap())
                );

                jPanel20.setBackground(new java.awt.Color(41, 127, 184));

                jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel11.setForeground(new java.awt.Color(255, 255, 255));
                jLabel11.setText("TTO ANTIPARASITARIO");
                jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jLabel11MouseClicked(evt);
                    }
                });

                lblPorcentajeTTO.setText("jLabel27");

                javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
                jPanel20.setLayout(jPanel20Layout);
                jPanel20Layout.setHorizontalGroup(
                    jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPorcentajeTTO)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel20Layout.setVerticalGroup(
                    jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPorcentajeTTO)
                        .addContainerGap())
                );

                jPanel22.setBackground(new java.awt.Color(241, 197, 14));

                jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel18.setForeground(new java.awt.Color(255, 255, 255));
                jLabel18.setText("<html>EPISODIOS DE ENFERMEDADES<br>PREVALENTES DE LA INFANCIA</br></html>");

                lblPorcentajeEEP.setText("jLabel7");

                javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
                jPanel22.setLayout(jPanel22Layout);
                jPanel22Layout.setHorizontalGroup(
                    jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPorcentajeEEP)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel22Layout.setVerticalGroup(
                    jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addGap(0, 32, Short.MAX_VALUE)
                        .addComponent(lblPorcentajeEEP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel23.setBackground(new java.awt.Color(243, 156, 17));
                jPanel23.setPreferredSize(new java.awt.Dimension(294, 45));

                jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel14.setForeground(new java.awt.Color(255, 255, 255));
                jLabel14.setText("SUPLEMENTACIÓN CON VITAMINA A");

                lblPorcentajeSVA.setText("jLabel7");

                javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
                jPanel23.setLayout(jPanel23Layout);
                jPanel23Layout.setHorizontalGroup(
                    jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addGap(44, 44, 44)
                        .addComponent(lblPorcentajeSVA)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel23Layout.setVerticalGroup(
                    jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblPorcentajeSVA)
                        .addGap(31, 31, 31))
                );

                jPanel24.setBackground(new java.awt.Color(232, 76, 61));
                jPanel24.setPreferredSize(new java.awt.Dimension(170, 45));

                jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel15.setForeground(new java.awt.Color(255, 255, 255));
                jLabel15.setText("VISITAS DOMICILIARIAS");

                lblPorcentajeVD.setText("jLabel7");

                javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
                jPanel24.setLayout(jPanel24Layout);
                jPanel24Layout.setHorizontalGroup(
                    jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(lblPorcentajeVD)
                        .addContainerGap(40, Short.MAX_VALUE))
                );
                jPanel24Layout.setVerticalGroup(
                    jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPorcentajeVD)
                        .addGap(24, 24, 24))
                );

                jPanel25.setBackground(new java.awt.Color(126, 140, 141));
                jPanel25.setPreferredSize(new java.awt.Dimension(324, 45));

                jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel16.setForeground(new java.awt.Color(255, 255, 255));
                jLabel16.setText("REG. SEGUIMIENTO DE LA ATENCIÓN INTEGRAL ");
                jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jLabel16MouseClicked(evt);
                    }
                });

                javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
                jPanel25.setLayout(jPanel25Layout);
                jPanel25Layout.setHorizontalGroup(
                    jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(101, Short.MAX_VALUE))
                );
                jPanel25Layout.setVerticalGroup(
                    jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                jPanel21.setBackground(new java.awt.Color(141, 68, 173));

                jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel12.setForeground(new java.awt.Color(255, 255, 255));
                jLabel12.setText("SUPLEMTACIÓN DE HIERRO Y/O MICRONUTRIENTES");

                lblPorcentajeSHM.setText("jLabel7");

                javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
                jPanel21.setLayout(jPanel21Layout);
                jPanel21Layout.setHorizontalGroup(
                    jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(193, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPorcentajeSHM)
                        .addGap(91, 91, 91))
                );
                jPanel21Layout.setVerticalGroup(
                    jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPorcentajeSHM)
                        .addContainerGap())
                );

                javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
                pnlContenedor.setLayout(pnlContenedorLayout);
                pnlContenedorLayout.setHorizontalGroup(
                    pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContenedorLayout.createSequentialGroup()
                                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenedorLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnlContenedorLayout.createSequentialGroup()
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(224, 224, 224))
                );
                pnlContenedorLayout.setVerticalGroup(
                    pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)))
                );

                txtSis.setBackground(new java.awt.Color(255, 255, 255));

                jPanel9.setBackground(new java.awt.Color(255, 255, 255));
                jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtPaciente.setEditable(false);
                txtPaciente.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtPaciente.setForeground(new java.awt.Color(102, 102, 102));
                txtPaciente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPaciente.setToolTipText("");
                txtPaciente.setBorder(null);
                txtPaciente.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPacienteCaretUpdate(evt);
                    }
                });

                btnBuscarNino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarNino.setMnemonic('B');
                btnBuscarNino.setToolTipText("");
                btnBuscarNino.setBorderPainted(false);
                btnBuscarNino.setContentAreaFilled(false);
                btnBuscarNino.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarNino.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarNinoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                jPanel9.setLayout(jPanel9Layout);
                jPanel9Layout.setHorizontalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarNino, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                jPanel9Layout.setVerticalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarNino, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(51, 51, 51));
                jLabel2.setText("Nombres y apellidos del niño");

                pnlSexo.setBackground(new java.awt.Color(13, 183, 225));
                pnlSexo.setPreferredSize(new java.awt.Dimension(50, 0));

                javax.swing.GroupLayout pnlSexoLayout = new javax.swing.GroupLayout(pnlSexo);
                pnlSexo.setLayout(pnlSexoLayout);
                pnlSexoLayout.setHorizontalGroup(
                    pnlSexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 21, Short.MAX_VALUE)
                );
                pnlSexoLayout.setVerticalGroup(
                    pnlSexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 276, Short.MAX_VALUE)
                );

                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(51, 51, 51));
                jLabel3.setText("DNI");

                lblDni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblDni.setForeground(new java.awt.Color(102, 102, 102));
                lblDni.setText("_____________________");

                jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel13.setForeground(new java.awt.Color(51, 51, 51));
                jLabel13.setText("Fecha de Nacimiento");

                lblFecNac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblFecNac.setForeground(new java.awt.Color(102, 102, 102));
                lblFecNac.setText("_____________________");

                lblPadres.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                lblPadres.setForeground(new java.awt.Color(51, 51, 51));
                lblPadres.setText("Padres_________________________________________________________________________________________________________________________________________________________");

                jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel28.setForeground(new java.awt.Color(51, 51, 51));
                jLabel28.setText("Acto Médico");

                lblActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblActoMedico.setForeground(new java.awt.Color(102, 102, 102));
                lblActoMedico.setText("_____________________");

                lblHc.setText("jLabel32");

                jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel29.setForeground(new java.awt.Color(51, 51, 51));
                jLabel29.setText("Nº H.C.");

                lblNHC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblNHC.setForeground(new java.awt.Color(102, 102, 102));
                lblNHC.setText("_____________________");

                pnlPadres.setBackground(new java.awt.Color(255, 255, 255));

                jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel23.setForeground(new java.awt.Color(51, 51, 51));
                jLabel23.setText("Tipo de Riesgo Perinatal");

                txtPadre.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtPadre.setForeground(new java.awt.Color(102, 102, 102));
                txtPadre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtPadre.setToolTipText("");
                txtPadre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtPadre.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPadreCaretUpdate(evt);
                    }
                });

                jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel22.setForeground(new java.awt.Color(51, 51, 51));
                jLabel22.setText("Nombres del Padre           ");

                cbxTipoSeguro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                cbxTipoSeguro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Seguro1", "Seguro2", "Seguro2" }));
                cbxTipoSeguro.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxTipoSeguroActionPerformed(evt);
                    }
                });

                jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel26.setForeground(new java.awt.Color(51, 51, 51));
                jLabel26.setText("Código Afiliación SIS");

                jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel10.setText("Tipo de Seguro");

                lblDireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblDireccion.setForeground(new java.awt.Color(102, 102, 102));
                lblDireccion.setText("_______________________________________________________________");
                lblDireccion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                lblDireccion.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        lblDireccionActionPerformed(evt);
                    }
                });

                btnGuardar.setText("Guardar");
                btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGuardarActionPerformed(evt);
                    }
                });

                txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtCodigo.setForeground(new java.awt.Color(102, 102, 102));
                txtCodigo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtCodigo.setToolTipText("");
                txtCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtCodigo.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCodigoCaretUpdate(evt);
                    }
                });

                lblHcMadre.setText("jLabel10");

                txtRiesgo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtRiesgo.setForeground(new java.awt.Color(102, 102, 102));
                txtRiesgo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtRiesgo.setToolTipText("");
                txtRiesgo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtRiesgo.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtRiesgoCaretUpdate(evt);
                    }
                });

                jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel25.setForeground(new java.awt.Color(51, 51, 51));
                jLabel25.setText("Dirección");

                lblTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblTelefono.setForeground(new java.awt.Color(102, 102, 102));
                lblTelefono.setText("_____________________");
                lblTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                lblTelefono.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        lblTelefonoActionPerformed(evt);
                    }
                });

                jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel24.setForeground(new java.awt.Color(51, 51, 51));
                jLabel24.setText("Télefono");

                jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel21.setForeground(new java.awt.Color(51, 51, 51));
                jLabel21.setText("Nombres de la Madre           ");

                jPanel10.setBackground(new java.awt.Color(255, 255, 255));
                jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtMadre.setEditable(false);
                txtMadre.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtMadre.setForeground(new java.awt.Color(102, 102, 102));
                txtMadre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtMadre.setToolTipText("");
                txtMadre.setBorder(null);
                txtMadre.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtMadreCaretUpdate(evt);
                    }
                });

                btnBuscarMadres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarMadres.setMnemonic('B');
                btnBuscarMadres.setToolTipText("");
                btnBuscarMadres.setBorderPainted(false);
                btnBuscarMadres.setContentAreaFilled(false);
                btnBuscarMadres.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarMadres.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarMadresActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                jPanel10.setLayout(jPanel10Layout);
                jPanel10Layout.setHorizontalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtMadre, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarMadres, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                );
                jPanel10Layout.setVerticalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBuscarMadres, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtMadre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                lblSector.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblSector.setForeground(new java.awt.Color(102, 102, 102));
                lblSector.setText("_______________");

                jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel17.setText("Sector");

                javax.swing.GroupLayout pnlPadresLayout = new javax.swing.GroupLayout(pnlPadres);
                pnlPadres.setLayout(pnlPadresLayout);
                pnlPadresLayout.setHorizontalGroup(
                    pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPadresLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPadresLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlPadresLayout.createSequentialGroup()
                                .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25))
                                .addGap(41, 41, 41)
                                .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblDireccion)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPadresLayout.createSequentialGroup()
                                            .addComponent(txtRiesgo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(28, 28, 28)
                                            .addComponent(jLabel26)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(29, 29, 29)
                        .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel22)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlPadresLayout.createSequentialGroup()
                                .addComponent(lblSector)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                                .addComponent(lblHcMadre)
                                .addGap(71, 71, 71)
                                .addComponent(btnGuardar))
                            .addComponent(txtPadre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxTipoSeguro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0))
                );
                pnlPadresLayout.setVerticalGroup(
                    pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPadresLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel22)
                                .addComponent(txtPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPadresLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlPadresLayout.createSequentialGroup()
                                .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtRiesgo)
                                    .addComponent(jLabel26)
                                    .addComponent(txtCodigo)
                                    .addComponent(jLabel10)
                                    .addComponent(cbxTipoSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)))
                        .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPadresLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHcMadre)
                                    .addComponent(btnGuardar)))
                            .addGroup(pnlPadresLayout.createSequentialGroup()
                                .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlPadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSector)
                                        .addComponent(jLabel17))
                                    .addComponent(jLabel25))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                );

                lblTriaje.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        lblTriajeCaretUpdate(evt);
                    }
                });

                lblGenero.setText("jLabel17");

                javax.swing.GroupLayout txtSisLayout = new javax.swing.GroupLayout(txtSis);
                txtSis.setLayout(txtSisLayout);
                txtSisLayout.setHorizontalGroup(
                    txtSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtSisLayout.createSequentialGroup()
                        .addComponent(pnlSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(txtSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(txtSisLayout.createSequentialGroup()
                                .addGroup(txtSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(txtSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(txtSisLayout.createSequentialGroup()
                                        .addComponent(lblDni)
                                        .addGap(61, 61, 61)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblFecNac)
                                        .addGap(44, 44, 44)
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblActoMedico)
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabel29)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblNHC))
                                    .addGroup(txtSisLayout.createSequentialGroup()
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(lblHc, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblGenero))))
                            .addComponent(pnlPadres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPadres, javax.swing.GroupLayout.PREFERRED_SIZE, 1128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(209, Short.MAX_VALUE))
                );
                txtSisLayout.setVerticalGroup(
                    txtSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(txtSisLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(txtSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGroup(txtSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblHc)
                                .addComponent(lblTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblGenero)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(txtSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(txtSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel28)
                                .addComponent(lblActoMedico)
                                .addComponent(jLabel29)
                                .addComponent(lblNHC))
                            .addGroup(txtSisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(lblDni)
                                .addComponent(jLabel13)
                                .addComponent(lblFecNac)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPadres)
                        .addGap(20, 20, 20)
                        .addComponent(pnlPadres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(pnlSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                );

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 1614, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 303, Short.MAX_VALUE))
                );
                jPanel4Layout.setVerticalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(txtSis, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                );

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 1917, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 654, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jTabbedPane1.addTab("Inicio", jPanel2);

                javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
                Contenedor.setLayout(ContenedorLayout);
                ContenedorLayout.setHorizontalGroup(
                    ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 1917, Short.MAX_VALUE)
                );
                ContenedorLayout.setVerticalGroup(
                    ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 654, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Contenedor)
                );
                jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Contenedor)
                );

                jTabbedPane1.addTab("Registro de seguimiento a la atención integral", jPanel3);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTabbedPane1))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents


    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        if(btnSi.getText().equals("OK"))
            pnlMensaje.setVisible(false);
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_btnNoActionPerformed

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
       
        
    }//GEN-LAST:event_jPanel12MouseClicked

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        RSAICCD CCD =new RSAICCD();
        Contenedor.add(CCD);
        if(lblGenero.getText().equals("F")){
            CCD.lblNina.setText(txtPaciente.getText());
            CCD.lblNina.setVisible(true);
            CCD.lblNino.setVisible(false);
        } else {
            CCD.lblNino.setText(txtPaciente.getText());
            CCD.lblNino.setVisible(true);
            CCD.lblNina.setVisible(false);
        }
        RSAICCD.lblId.setText(String.valueOf(id));
        try {
            CCD.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jPanel13MouseClicked

    private void txtPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPacienteCaretUpdate

    }//GEN-LAST:event_txtPacienteCaretUpdate

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        
        RSAICCD CCD =new RSAICCD();
        Contenedor.add(CCD);
        if(lblGenero.getText().equals("F")){
            CCD.lblNina.setText(txtPaciente.getText());
            CCD.lblNina.setVisible(true);
            CCD.lblNino.setVisible(false);
        } else {
            CCD.lblNino.setText(txtPaciente.getText());
            CCD.lblNino.setVisible(true);
            CCD.lblNina.setVisible(false);
        }
        ConsultorioExtRsCcd CCDLISTAR = new ConsultorioExtRsCcd();
        CCDLISTAR.ConsultoriosExtCCDListar(id);
       
      
        RSAICCD.lblId.setText(String.valueOf(id));
        try {
            CCD.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        RSAIDN DN =new RSAIDN();
        Contenedor.add(DN);
        if(lblGenero.getText().equals("F")){
            DN.lblNina.setText(txtPaciente.getText());
            DN.lblNina.setVisible(true);
            DN.lblNino.setVisible(false);
        } else {
            DN.lblNino.setText(txtPaciente.getText());
            DN.lblNino.setVisible(true);
            DN.lblNina.setVisible(false);
        }
        ConsultorioExtRsDiagnosticoNutricional DN1 = new ConsultorioExtRsDiagnosticoNutricional();
        DN1.ConsultoriosExtDNListar(id);
        
        RSAIDN.lblId.setText(String.valueOf(id));
        try {
            DN.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        RSAIET ET12 =new RSAIET();
        Contenedor.add(ET12);
        if(lblGenero.getText().equals("F")){
            ET12.lblNina.setText(txtPaciente.getText());
            ET12.lblNina.setVisible(true);
            ET12.lblNino.setVisible(false);
        } else {
            ET12.lblNino.setText(txtPaciente.getText());
            ET12.lblNino.setVisible(true);
            ET12.lblNina.setVisible(false);
        }
        
        ConsultorioExtRsEstimulacionTemprana ET1 = new ConsultorioExtRsEstimulacionTemprana();
        ET1.ConsultoriosExtETListar(id);
       
        RSAIET.lblId.setText(String.valueOf(id));
        
        try {
            ET12.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        RSAIREGSEG REGSEG =new RSAIREGSEG();
        Contenedor.add(REGSEG);
        try {
            REGSEG.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void txtMadreCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMadreCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMadreCaretUpdate

    private void txtPadreCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPadreCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPadreCaretUpdate

    private void lblTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblTelefonoActionPerformed

    private void lblDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblDireccionActionPerformed

    private void tbNinosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNinosMouseClicked
        if(evt.getClickCount()==2){
            enviarDatosNinos();
        }
    }//GEN-LAST:event_tbNinosMouseClicked

    private void tbNinosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNinosMousePressed

    }//GEN-LAST:event_tbNinosMousePressed

    private void tbNinosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNinosKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbNinos.getSelectedRow()==0){
            txtBuscarNino.requestFocus();
            tbNinos.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosNinos();
        }
    }//GEN-LAST:event_tbNinosKeyPressed

    private void txtBuscarNinoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarNinoCaretUpdate
        ConsultorioExtRsCabecera consultorio1 = new ConsultorioExtRsCabecera();
        consultorio1.listarNinos(tbNinos, txtBuscarNino.getText());
    }//GEN-LAST:event_txtBuscarNinoCaretUpdate

    private void txtBuscarNinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarNinoMouseClicked

    }//GEN-LAST:event_txtBuscarNinoMouseClicked

    private void txtBuscarNinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarNinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarNinoActionPerformed

    private void txtBuscarNinoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarNinoKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbNinos.getSelectionModel().setSelectionInterval(0, 0);
            tbNinos.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarNinoKeyPressed

    private void T6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T6MouseClicked

    }//GEN-LAST:event_T6MouseClicked

    private void txtBuscarMadresCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarMadresCaretUpdate
        ConsultorioExtRsCabecera consultorio1 = new ConsultorioExtRsCabecera();
        consultorio1.listarMadres(tbMadres, txtBuscarMadres.getText());
    }//GEN-LAST:event_txtBuscarMadresCaretUpdate

    private void txtBuscarMadresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMadresMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMadresMouseClicked

    private void txtBuscarMadresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarMadresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMadresActionPerformed

    private void txtBuscarMadresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMadresKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbMadres.getSelectionModel().setSelectionInterval(0, 0);
            tbMadres.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarMadresKeyPressed

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T7MouseClicked

    private void tbMadresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMadresMouseClicked
        if(evt.getClickCount()==1){
            enviarDatosMadres();
        }
    }//GEN-LAST:event_tbMadresMouseClicked

    private void tbMadresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMadresMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMadresMousePressed

    private void tbMadresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMadresKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbNinos.getSelectedRow()==0){
            txtBuscarMadres.requestFocus();
            tbMadres.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosMadres();
        }
    }//GEN-LAST:event_tbMadresKeyPressed

    private void btnBuscarNinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNinoActionPerformed
        BuscarNinos.setVisible(true);
        BuscarNinos.setLocationRelativeTo(null);//en el centro
        BuscarNinos.setResizable(false);
        BuscarNinos.getContentPane().setBackground(Color.WHITE);
        ConsultorioExtRsCabecera consultorio1 = new ConsultorioExtRsCabecera();
        consultorio1.listarNinos(tbNinos, txtBuscarNino.getText());
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_btnBuscarNinoActionPerformed

    private void btnBuscarMadresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMadresActionPerformed
        BuscarMadres.setVisible(true);
        BuscarMadres.setLocationRelativeTo(null);//en el centro
        BuscarMadres.setResizable(false);
        BuscarMadres.getContentPane().setBackground(Color.WHITE);
        ConsultorioExtRsCabecera consultorio1 = new ConsultorioExtRsCabecera();
        consultorio1.listarMadres(tbMadres, txtBuscarMadres.getText());
    }//GEN-LAST:event_btnBuscarMadresActionPerformed

    private void txtCodigoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCodigoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoCaretUpdate

    private void txtRiesgoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtRiesgoCaretUpdate

    }//GEN-LAST:event_txtRiesgoCaretUpdate

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        btnGuardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cbxTipoSeguroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoSeguroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoSeguroActionPerformed

    private void lblTriajeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_lblTriajeCaretUpdate
        validaTriaje(lblTriaje.getText());
    }//GEN-LAST:event_lblTriajeCaretUpdate

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked

            
    }//GEN-LAST:event_jPanel16MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
       RSAITAPTG TAPTG =new RSAITAPTG();
        Contenedor.add(TAPTG);
        if(lblGenero.getText().equals("F")){
            RSAITAPTG.lblNina.setText(txtPaciente.getText());
            RSAITAPTG.lblNina.setVisible(true);
            RSAITAPTG.lblNino.setVisible(false);
        } else {
            RSAITAPTG.lblNino.setText(txtPaciente.getText());
            RSAITAPTG.lblNino.setVisible(true);
            RSAITAPTG.lblNina.setVisible(false);
        }
        ConsultorioExtRsTamizajeAnemiaParasitosis TAPTG1 = new ConsultorioExtRsTamizajeAnemiaParasitosis();
        TAPTG1.ConsultoriosExtTAListar(id);
       
        RSAITAPTG.lblId.setText(String.valueOf(id));
        try {
            TAPTG.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        RSAITN TN =new RSAITN();
        Contenedor.add(TN);
        if(lblGenero.getText().equals("F")){
            TN.lblNina.setText(txtPaciente.getText());
            TN.lblNina.setVisible(true);
            TN.lblNino.setVisible(false);
        } else {
            TN.lblNino.setText(txtPaciente.getText());
            TN.lblNino.setVisible(true);
            TN.lblNina.setVisible(false);
        }
        ConsultorioExtRsTamizajeNeonatal TN1 = new ConsultorioExtRsTamizajeNeonatal();
        TN1.ConsultoriosExtTNListar(id);
       
        RSAITN.lblId.setText(String.valueOf(id));
        try {
            TN.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
       
    }//GEN-LAST:event_jPanel17MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        RSAIVacunas V =new RSAIVacunas();
        Contenedor.add(V);
        if(lblGenero.getText().equals("F")){
            RSAIVacunas.lblNina.setText(txtPaciente.getText());
            RSAIVacunas.lblNina.setVisible(true);
            RSAIVacunas.lblNino.setVisible(false);
        } else {
            RSAIVacunas.lblNino.setText(txtPaciente.getText());
            RSAIVacunas.lblNino.setVisible(true);
            RSAIVacunas.lblNina.setVisible(false);
        }
        ConsultorioExtRsVacunas vacunas1 = new ConsultorioExtRsVacunas();
        vacunas1.ConsultoriosExtVacunasListar(id);
       
        RSAIVacunas.lblId.setText(String.valueOf(id));
    
        try {
            V.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void lblPorcentajeVacunasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPorcentajeVacunasMouseClicked
        RSAIVacunas V =new RSAIVacunas();
        Contenedor.add(V);
        if(lblGenero.getText().equals("F")){
            RSAIVacunas.lblNina.setText(txtPaciente.getText());
            RSAIVacunas.lblNina.setVisible(true);
            RSAIVacunas.lblNino.setVisible(false);
        } else {
            RSAIVacunas.lblNino.setText(txtPaciente.getText());
            RSAIVacunas.lblNino.setVisible(true);
            RSAIVacunas.lblNina.setVisible(false);
        }
        ConsultorioExtRsVacunas vacunas1 = new ConsultorioExtRsVacunas();
        vacunas1.ConsultoriosExtVacunasListar(id);
       
        RSAIVacunas.lblId.setText(String.valueOf(id));
    
        try {
            V.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_lblPorcentajeVacunasMouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        RSAIDD DD =new RSAIDD();
        Contenedor.add(DD);
        if(lblGenero.getText().equals("F")){
            DD.lblNina.setText(txtPaciente.getText());
            DD.lblNina.setVisible(true);
            DD.lblNino.setVisible(false);
        } else {
            DD.lblNino.setText(txtPaciente.getText());
            DD.lblNino.setVisible(true);
            DD.lblNina.setVisible(false);
        }
        ConsultorioExtRsDiagnosticoDesarrollo DD1 = new ConsultorioExtRsDiagnosticoDesarrollo();
        DD1.ConsultoriosExtDDListar(id);
        
        RSAIDD.lblId.setText(String.valueOf(id));
        try {
            DD.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        RSAITTO TTO =new RSAITTO();
        Contenedor.add(TTO);
        if(lblGenero.getText().equals("F")){
            TTO.lblNina.setText(txtPaciente.getText());
            TTO.lblNina.setVisible(true);
            TTO.lblNino.setVisible(false);
        } else {
            TTO.lblNino.setText(txtPaciente.getText());
            TTO.lblNino.setVisible(true);
            TTO.lblNina.setVisible(false);
        }
//        ConsultorioExtRsTtoAntiparasitario Tto1 = new ConsultorioExtRsTtoAntiparasitario();
//        Tto1.c(id);
       
//        RSAITN.lblId.setText(String.valueOf(id));
        try {
            TTO.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel11MouseClicked

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
            java.util.logging.Logger.getLogger(RegistroSeguimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroSeguimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroSeguimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroSeguimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroSeguimiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BuscarMadres;
    private javax.swing.JDialog BuscarNinos;
    public static javax.swing.JDesktopPane Contenedor;
    private javax.swing.JLabel T6;
    private javax.swing.JLabel T7;
    private javax.swing.JButton btnBuscarMadres;
    private javax.swing.JButton btnBuscarNino;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnSi;
    private javax.swing.JComboBox cbxTipoSeguro;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JLabel lblActoMedico;
    public static javax.swing.JTextField lblDireccion;
    public static javax.swing.JLabel lblDni;
    public static javax.swing.JLabel lblFecNac;
    public static javax.swing.JLabel lblGenero;
    public static javax.swing.JLabel lblHc;
    public static javax.swing.JLabel lblHcMadre;
    private javax.swing.JLabel lblMensaje;
    public static javax.swing.JLabel lblNHC;
    private javax.swing.JLabel lblPadres;
    public static javax.swing.JLabel lblPorcentajeCCD;
    public static javax.swing.JLabel lblPorcentajeDD;
    public static javax.swing.JLabel lblPorcentajeDN;
    public static javax.swing.JLabel lblPorcentajeEEP;
    public static javax.swing.JLabel lblPorcentajeET;
    public static javax.swing.JLabel lblPorcentajeSHM;
    public static javax.swing.JLabel lblPorcentajeSVA;
    public static javax.swing.JLabel lblPorcentajeTAP;
    public static javax.swing.JLabel lblPorcentajeTN;
    public static javax.swing.JLabel lblPorcentajeTTO;
    public static javax.swing.JLabel lblPorcentajeVD;
    public static javax.swing.JLabel lblPorcentajeVacunas;
    public static javax.swing.JLabel lblSector;
    public static javax.swing.JTextField lblTelefono;
    public static javax.swing.JTextField lblTriaje;
    private javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JPanel pnlPadres;
    private javax.swing.JPanel pnlSexo;
    private javax.swing.JTable tbMadres;
    private javax.swing.JTable tbNinos;
    private javax.swing.JTextField txtBuscarMadres;
    private javax.swing.JTextField txtBuscarNino;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtMadre;
    public static javax.swing.JTextField txtPaciente;
    public static javax.swing.JTextField txtPadre;
    public static javax.swing.JTextField txtRiesgo;
    private javax.swing.JPanel txtSis;
    // End of variables declaration//GEN-END:variables
}
