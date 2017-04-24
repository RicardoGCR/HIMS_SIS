/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.ConsultorioEx.ConsultorioExtOrdenpcr;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import static vista.ConsultorioEx.REGSEGMONITOREO.tbMonitoreo;

/**
 *
 * @author MYS1
 */
public class OrdenPCR extends javax.swing.JFrame {

    ConsultorioExtOrdenpcr consultorio = new ConsultorioExtOrdenpcr();
    String check = "T";
    public OrdenPCR() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        pnlMensaje.setVisible(false);
        habilitarDatos(false);
        consultorio.listarOrdenPcr(txtBuscar.getText(), "T",tbOrdenpcr);
        lblMant.setVisible(false);
        lblId.setVisible(false);
        lblHc.setVisible(false);
        txtIdHc.setVisible(false);
        chkHoy.setSelected(false);
    }

    public void limpiar(){
        dtFechaSolicitud.setDate(null);
        txtMadre.setText("");
        txtDniMadre.setText("");
        txtNino.setText("");
        lblDniNino.setText("________________");
        lblFechaNac.setText("________________");
        lblGenero.setText("________________");
        chkST.setSelected(false);
        chkTPF.setSelected(false);
        dtPcr1.setDate(null);
        dtPcr2.setDate(null);
        dtPcr3.setDate(null);
        txtIdHc.setText("");
        lblHc.setText("");
    }
    
    public void habilitarDatos(boolean opcion){
        dtFechaSolicitud.setEnabled(opcion);
        txtMadre.setEnabled(opcion);
        txtDniMadre.setEnabled(opcion);
        txtNino.setEnabled(opcion);
        lblDniNino.setEnabled(opcion);
        lblFechaNac.setEnabled(opcion);
        lblGenero.setEnabled(opcion);
        chkST.setEnabled(opcion);
        chkTPF.setEnabled(opcion);
        dtPcr1.setEnabled(opcion);
        dtPcr2.setEnabled(opcion);
        dtPcr3.setEnabled(opcion);
        btnBuscarNino.setEnabled(opcion);
    }
    
    public String determinarFecha(JDateChooser calendario){
        String fecha = "";
        try {
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
         } catch (Exception e) {
                           pnlMensaje.setVisible(true);
                           pnlMensaje.setBackground(new Color(255,91,70)); 
                           lblMensaje.setText("Ingrese una fecha correcta");
                           btnSi.setVisible(false);
                           btnNo.setVisible(false); 
         }
        
        return fecha;
    }
    
    public void enviarDatosNinos(){
        int fila = tbNinos.getSelectedRow();
        OrdenPCR.lblHc.setText(String.valueOf(tbNinos.getValueAt(fila, 0)));
        OrdenPCR.txtNino.setText(String.valueOf(tbNinos.getValueAt(fila, 4)));
        OrdenPCR.lblDniNino.setText(String.valueOf(tbNinos.getValueAt(fila, 2)));
        OrdenPCR.lblFechaNac.setText(String.valueOf(tbNinos.getValueAt(fila, 6)));
        OrdenPCR.lblGenero.setText(String.valueOf(tbNinos.getValueAt(fila, 5)));
        validaHC(String.valueOf(tbNinos.getValueAt(fila, 0)));
        BuscarNinos.dispose();
    }
    
    public void enviarDatosOrdenPcr(){
        lblHc.setText(txtIdHc.getText());
        lblId.setText(String.valueOf(tbOrdenpcr.getValueAt(0, 0)));
        txtMadre.setText(String.valueOf(tbOrdenpcr.getValueAt(0, 1)));
        txtDniMadre.setText(String.valueOf(tbOrdenpcr.getValueAt(0, 2)));
        if(String.valueOf(tbOrdenpcr.getValueAt(0, 7)).equals("X"))
            chkST.setSelected(true);
        else
            chkST.setSelected(false);
        if(String.valueOf(tbOrdenpcr.getValueAt(0, 8)).equals("X"))
            chkTPF.setSelected(true);
        else
            chkTPF.setSelected(false);
        String fechaSolicitud = (String) tbOrdenpcr.getModel().getValueAt(0, 12);
        try {
            DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = dfo.parse(fechaSolicitud);
            dtFechaSolicitud.setDate(fecha);
        } catch (Exception e) {
        }
        String pcr1 = (String) tbOrdenpcr.getModel().getValueAt(0, 9);
            try {
                DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha = dfo.parse(pcr1);
                dtPcr1.setDate(fecha);
            } catch (Exception e) {
            }
        String pcr2 = (String) tbOrdenpcr.getModel().getValueAt(0, 10);
            try {
                DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha = dfo.parse(pcr2);
                dtPcr2.setDate(fecha);
            } catch (Exception e) {
            }
        String pcr3 = (String) tbOrdenpcr.getModel().getValueAt(0, 11);
            try {
                DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha = dfo.parse(pcr3);
                dtPcr3.setDate(fecha);
            } catch (Exception e) {
            }
    }
    
    public boolean guardarDatos(){
        boolean retorna = false;
        try {
            ConsultorioExtOrdenpcr ordenpcr1 = new ConsultorioExtOrdenpcr();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMant.getText().equals("U"))
                ordenpcr1.setCeoId(Integer.parseInt(lblId.getText()));
            ordenpcr1.setCeoFechSolicitud(determinarFecha(dtFechaSolicitud));
            ordenpcr1.setCeoMadre(txtMadre.getText());
            ordenpcr1.setCeoDniMadre(txtDniMadre.getText());
            ordenpcr1.setHcNino(lblHc.getText());
            ordenpcr1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(chkST.isSelected()==true)
                ordenpcr1.setCeoTipSangt("X");
            else
                ordenpcr1.setCeoTipSangt("");
            if(chkTPF.isSelected()==true)
                ordenpcr1.setCeoTipTpf("X");
            else
                ordenpcr1.setCeoTipTpf("");
            if(dtPcr1.getDate()==null)
                ordenpcr1.setCeoPcr1("");
            else
                ordenpcr1.setCeoPcr1(determinarFecha(dtPcr1));
            if(dtPcr2.getDate()==null)
                ordenpcr1.setCeoPcr2("");
            else
                ordenpcr1.setCeoPcr2(determinarFecha(dtPcr2));
            if(dtPcr3.getDate()==null)
                ordenpcr1.setCeoPcr3("");
            else
                ordenpcr1.setCeoPcr3(determinarFecha(dtPcr3));
            if(ordenpcr1.mantenimientoConsultorioOrdenpcr(lblMant.getText())==true){
                System.out.println("ID Orden PCR: " + Integer.parseInt(ordenpcr1.ordenpcrID()));
                ordenpcr1.listarOrdenPcr(txtBuscar.getText(), "T",tbOrdenpcr);
                chkHoy.setSelected(false);
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                limpiar();
                habilitarDatos(false);
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
        } catch (Exception e) {
            System.out.println("Error: guardarDatos" + e.getMessage());
        }
        return retorna;
    }
    
    public void validaHC(String hClinica){
        try {
            PreparedStatement cmd = consultorio.getCn().prepareStatement("SELECT CEO_ID FROM CONSULTORIO_EXT_ORDENPCR WHERE HC_NINO = '"+hClinica+"'");
            ResultSet res = cmd.executeQuery();
            if(res.next()){
                txtIdHc.setText(lblHc.getText());
                lblMant.setText("U");
                txtBuscar.setEnabled(false);
                chkHoy.setEnabled(false);
            }else {
                txtIdHc.setText("");
                txtMadre.setText("");
                txtDniMadre.setText("");
                lblMant.setText("I");
                txtBuscar.setEnabled(true);
                chkHoy.setEnabled(true);
                dtPcr1.setDate(null);
                dtPcr2.setDate(null);
                dtPcr3.setDate(null);
                chkST.setSelected(false);
                chkTPF.setSelected(false);
                dtFechaSolicitud.setDate(null);
            }
        } catch (Exception e) {
            System.out.println("Error: validaHC: " + e.toString());
        }
    }
    
//    public void eliminarDatos(){
//        int fila = tbPendientes.getSelectedRow();
//        Caja_Preventa cajaE = new Caja_Preventa();
//        cajaE.setId_preventa(Integer.parseInt(String.valueOf(tbPendientes.getValueAt(fila, 0))));
//        if(cajaE.mantanimientoCajaPreventaCExDepSangre("E")){
//            pnlMensaje1.setVisible(true);
//            lblMensaje1.setText("Datos eliminados de forma correcta");
//            pnlMensaje1.setBackground(new Color(33,115,70));
//            btnSi1.setVisible(true);
//            btnSi1.setText("OK");
//            btnNo1.setVisible(false);
//        } else{
//            pnlMensaje1.setVisible(true);
//            lblMensaje1.setText("Ocurrió un error, verifique");
//            pnlMensaje1.setBackground(new Color(255,91,70));
//            btnSi1.setVisible(false);
//            btnNo1.setVisible(false);
//        }
//    }
    
    
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
        jScrollPane4 = new javax.swing.JScrollPane();
        tbNinos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            btnNuevo = new javax.swing.JButton();
            btnEditar = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            btnEliminar = new javax.swing.JButton();
            lblusu = new javax.swing.JLabel();
            pnlMensaje = new javax.swing.JPanel();
            lblMensaje = new javax.swing.JLabel();
            btnSi = new javax.swing.JButton();
            btnNo = new javax.swing.JButton();
            jPanel9 = new javax.swing.JPanel();
            txtMadre = new javax.swing.JTextField();
            jLabel2 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            dtFechaSolicitud = new com.toedter.calendar.JDateChooser();
            jLabel5 = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            jPanel10 = new javax.swing.JPanel();
            txtNino = new javax.swing.JTextField();
            btnBuscarNino = new javax.swing.JButton();
            jLabel7 = new javax.swing.JLabel();
            lblFechaNac = new javax.swing.JLabel();
            lblDniNino = new javax.swing.JLabel();
            Sexo = new javax.swing.JLabel();
            lblGenero = new javax.swing.JLabel();
            Sexo1 = new javax.swing.JLabel();
            chkST = new javax.swing.JCheckBox();
            chkTPF = new javax.swing.JCheckBox();
            Sexo2 = new javax.swing.JLabel();
            lblGenero1 = new javax.swing.JLabel();
            Sexo3 = new javax.swing.JLabel();
            Sexo4 = new javax.swing.JLabel();
            Sexo5 = new javax.swing.JLabel();
            Sexo6 = new javax.swing.JLabel();
            dtPcr1 = new com.toedter.calendar.JDateChooser();
            dtPcr2 = new com.toedter.calendar.JDateChooser();
            dtPcr3 = new com.toedter.calendar.JDateChooser();
            jPanel31 = new javax.swing.JPanel();
            T7 = new javax.swing.JLabel();
            txtBuscar = new javax.swing.JTextField();
            chkHoy = new javax.swing.JCheckBox();
            jScrollPane3 = new javax.swing.JScrollPane();
            tbOrdenpcr = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                txtDniMadre = new javax.swing.JTextField();
                lblMant = new javax.swing.JLabel();
                txtIdHc = new javax.swing.JTextField();
                lblHc = new javax.swing.JLabel();
                lblId = new javax.swing.JLabel();
                jLabel8 = new javax.swing.JLabel();

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

                jScrollPane4.setBorder(null);

                tbNinos.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
                tbNinos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
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
                jScrollPane4.setViewportView(tbNinos);

                javax.swing.GroupLayout BuscarNinosLayout = new javax.swing.GroupLayout(BuscarNinos.getContentPane());
                BuscarNinos.getContentPane().setLayout(BuscarNinosLayout);
                BuscarNinosLayout.setHorizontalGroup(
                    BuscarNinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                );
                BuscarNinosLayout.setVerticalGroup(
                    BuscarNinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BuscarNinosLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setResizable(false);

                jPanel1.setBackground(new java.awt.Color(0, 153, 102));

                jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("<html>Orden de Laboratorio <span style=\"font-size:'15px'\"><br>Pruebas PCR para niños expuestos al VIH</span></html>");

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

                btnEditar.setForeground(new java.awt.Color(240, 240, 240));
                btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
                btnEditar.setMnemonic('N');
                btnEditar.setContentAreaFilled(false);
                btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnEditar.setEnabled(false);
                btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnEditar.setIconTextGap(30);
                btnEditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnEditar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnEditarActionPerformed(evt);
                    }
                });

                btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
                btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
                btnGuardar.setMnemonic('N');
                btnGuardar.setContentAreaFilled(false);
                btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnGuardar.setEnabled(false);
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
                btnEliminar.setEnabled(false);
                btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnEliminar.setIconTextGap(30);
                btnEliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnEliminarActionPerformed(evt);
                    }
                });

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblusu)
                                .addGap(47, 47, 47))))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblusu, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnEliminar))
                            .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(552, 552, 552))
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

                jPanel9.setBackground(new java.awt.Color(255, 255, 255));
                jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtMadre.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtMadre.setForeground(new java.awt.Color(51, 51, 51));
                txtMadre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtMadre.setToolTipText("");
                txtMadre.setBorder(null);
                txtMadre.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtMadreCaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                jPanel9.setLayout(jPanel9Layout);
                jPanel9Layout.setHorizontalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMadre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                );
                jPanel9Layout.setVerticalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(51, 51, 51));
                jLabel2.setText("Nombre Completo de la Madre");

                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(51, 51, 51));
                jLabel3.setText("DNI De La Madre");

                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                jLabel4.setText("Fecha de Solicitud");

                dtFechaSolicitud.setBackground(new java.awt.Color(255, 255, 255));
                dtFechaSolicitud.setDateFormatString("dd/MM/yyyy");

                jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel5.setForeground(new java.awt.Color(51, 51, 51));
                jLabel5.setText("Nombre Completo del Niño Expuesto");

                jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(51, 51, 51));
                jLabel6.setText("DNI del Niño Expuesto");

                jPanel10.setBackground(new java.awt.Color(255, 255, 255));
                jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtNino.setEditable(false);
                txtNino.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtNino.setForeground(new java.awt.Color(51, 51, 51));
                txtNino.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtNino.setToolTipText("");
                txtNino.setBorder(null);
                txtNino.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtNinoCaretUpdate(evt);
                    }
                });

                btnBuscarNino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarNino.setToolTipText("");
                btnBuscarNino.setContentAreaFilled(false);
                btnBuscarNino.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarNino.setEnabled(false);
                btnBuscarNino.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarNinoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                jPanel10.setLayout(jPanel10Layout);
                jPanel10Layout.setHorizontalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtNino, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarNino, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                );
                jPanel10Layout.setVerticalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNino, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(btnBuscarNino, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel7.setForeground(new java.awt.Color(51, 51, 51));
                jLabel7.setText("Fecha de Nacimiento");

                lblFechaNac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblFechaNac.setForeground(new java.awt.Color(51, 51, 51));
                lblFechaNac.setText("________________");

                lblDniNino.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblDniNino.setForeground(new java.awt.Color(51, 51, 51));
                lblDniNino.setText("________________");

                Sexo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                Sexo.setForeground(new java.awt.Color(51, 51, 51));
                Sexo.setText("Sexo");

                lblGenero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblGenero.setForeground(new java.awt.Color(51, 51, 51));
                lblGenero.setText("________________");

                Sexo1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                Sexo1.setForeground(new java.awt.Color(51, 51, 51));
                Sexo1.setText("Tipo de Muestra");

                chkST.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                chkST.setText("Sangre Total");
                chkST.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

                chkTPF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                chkTPF.setText("Técnica Papel Filtro");
                chkTPF.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

                Sexo2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                Sexo2.setForeground(new java.awt.Color(51, 51, 51));
                Sexo2.setText("Nº Prueba");

                lblGenero1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblGenero1.setForeground(new java.awt.Color(51, 51, 51));
                lblGenero1.setText("___________________________________________________________________________________");

                Sexo3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                Sexo3.setForeground(new java.awt.Color(51, 51, 51));
                Sexo3.setText("Fecha de Obtención de Muestra ");

                Sexo4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                Sexo4.setForeground(new java.awt.Color(102, 102, 102));
                Sexo4.setText("1º PCR DNA");

                Sexo5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                Sexo5.setForeground(new java.awt.Color(102, 102, 102));
                Sexo5.setText("2º PCR DNA");

                Sexo6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                Sexo6.setForeground(new java.awt.Color(102, 102, 102));
                Sexo6.setText("3º PCR DNA");

                dtPcr1.setBackground(new java.awt.Color(255, 255, 255));
                dtPcr1.setDateFormatString("dd/MM/yyyy");

                dtPcr2.setBackground(new java.awt.Color(255, 255, 255));
                dtPcr2.setDateFormatString("dd/MM/yyyy");

                dtPcr3.setBackground(new java.awt.Color(255, 255, 255));
                dtPcr3.setDateFormatString("dd/MM/yyyy");

                jPanel31.setBackground(new java.awt.Color(204, 204, 204));

                T7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                T7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                T7.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        T7MouseClicked(evt);
                    }
                });

                txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscarCaretUpdate(evt);
                    }
                });

                chkHoy.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                chkHoy.setText("Hoy");
                chkHoy.setContentAreaFilled(false);
                chkHoy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                chkHoy.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        chkHoyActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
                jPanel31.setLayout(jPanel31Layout);
                jPanel31Layout.setHorizontalGroup(
                    jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkHoy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(T7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );
                jPanel31Layout.setVerticalGroup(
                    jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(T7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(txtBuscar)
                    .addComponent(chkHoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                jScrollPane3.setBorder(null);

                tbOrdenpcr.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
                tbOrdenpcr.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                tbOrdenpcr.setGridColor(new java.awt.Color(255, 255, 255));
                tbOrdenpcr.setRowHeight(25);
                tbOrdenpcr.setSelectionBackground(new java.awt.Color(102, 102, 102));
                tbOrdenpcr.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbOrdenpcrMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbOrdenpcrMousePressed(evt);
                    }
                });
                tbOrdenpcr.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbOrdenpcrKeyPressed(evt);
                    }
                });
                jScrollPane3.setViewportView(tbOrdenpcr);

                txtDniMadre.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtDniMadre.setForeground(new java.awt.Color(102, 102, 102));
                txtDniMadre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDniMadre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtDniMadre.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDniMadreCaretUpdate(evt);
                    }
                });
                txtDniMadre.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtDniMadreActionPerformed(evt);
                    }
                });

                lblMant.setText("jLabel8");

                txtIdHc.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
                txtIdHc.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtIdHcCaretUpdate(evt);
                    }
                });

                lblHc.setText("jLabel8");

                lblId.setText("jLabel8");

                jLabel8.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
                jLabel8.setForeground(new java.awt.Color(102, 102, 102));
                jLabel8.setText("Apellidos y Nombres / DNI del niño");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Sexo6)
                                    .addComponent(Sexo5))
                                .addGap(108, 108, 108)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dtPcr3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dtPcr2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(528, 833, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(Sexo)
                                                .addComponent(Sexo1))
                                            .addGap(148, 148, 148)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblGenero)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(chkST)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(chkTPF))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel7))
                                            .addGap(20, 20, 20)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblFechaNac)
                                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(dtFechaSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lblMant)
                                                    .addGap(38, 38, 38)
                                                    .addComponent(txtIdHc, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblDniNino)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(txtDniMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(102, 102, 102)
                                                    .addComponent(lblHc)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblId)
                                                    .addGap(32, 32, 32)))))
                                    .addComponent(lblGenero1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Sexo2)
                                            .addComponent(Sexo4))
                                        .addGap(108, 108, 108)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dtPcr1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Sexo3))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane3)
                                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dtFechaSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMant)
                            .addComponent(txtIdHc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txtDniMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblHc)
                                        .addComponent(lblId)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(lblDniNino))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(lblFechaNac))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Sexo)
                                    .addComponent(lblGenero))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chkST)
                                    .addComponent(Sexo1)
                                    .addComponent(chkTPF))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblGenero1)
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Sexo3)
                                    .addComponent(Sexo2))
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dtPcr1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Sexo4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dtPcr2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Sexo5))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dtPcr3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Sexo6))
                                .addContainerGap(27, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            limpiar();
            habilitarDatos(true);
            lblMant.setText("I");
            btnGuardar.setEnabled(true);
            txtBuscar.setEnabled(true);
            chkHoy.setEnabled(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
      
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(lblMant.getText().equals("I")){
            if(dtFechaSolicitud.getDate()==null){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione la fecha de solicitud");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else
            if(txtMadre.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese el nombre de la madre");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else
            if(txtDniMadre.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese el DNI de la madre");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else
            if(txtNino.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione el niño");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else
            if(!chkST.isSelected() && !chkTPF.isSelected()){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Marce el tipo de muestra");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else
            if(dtPcr1.getDate()==null && dtPcr2.getDate()==null && dtPcr3.getDate()==null){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese la fecha del PCR");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            }else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("¿Guardar los datos?");
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                pnlMensaje.setBackground(new Color(255,153,51));
            }
        } else 
        if(lblMant.getText().equals("U")){
            if(dtFechaSolicitud.getDate()==null){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione la fecha de solicitud");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else
            if(txtMadre.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese el nombre de la madre");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else
            if(txtDniMadre.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese el DNI de la madre");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else
            if(txtNino.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione el niño");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else
            if(!chkST.isSelected() && !chkTPF.isSelected()){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Marce el tipo de muestra");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else
            if(dtPcr1.getDate()==null && dtPcr2.getDate()==null && dtPcr3.getDate()==null){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese la fecha del PCR");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            }else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("¿Modificar los datos?");
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                pnlMensaje.setBackground(new Color(255,153,51));
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        if(btnSi.getText().equals("Si")){ // Al guardar
            if(lblMant.getText().equals("I") || lblMant.getText().equals("U")){
                guardarDatos();
            }
        } else
        if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
            pnlMensaje.setVisible(false);
        }
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_btnNoActionPerformed

    private void txtMadreCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMadreCaretUpdate

    }//GEN-LAST:event_txtMadreCaretUpdate

    private void txtNinoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNinoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNinoCaretUpdate

    private void btnBuscarNinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNinoActionPerformed
        BuscarNinos.setVisible(true);
        BuscarNinos.setLocationRelativeTo(null);//en el centro
        BuscarNinos.setResizable(false);
        BuscarNinos.getContentPane().setBackground(Color.WHITE);
        ConsultorioExtOrdenpcr consultorio1 = new ConsultorioExtOrdenpcr();
        consultorio1.listarNinos(txtBuscarNino.getText(),tbNinos);
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_btnBuscarNinoActionPerformed

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked

    }//GEN-LAST:event_T7MouseClicked

    private void chkHoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHoyActionPerformed
        if(chkHoy.isSelected()){
            check = "H";
            ConsultorioExtOrdenpcr consultorio1 = new ConsultorioExtOrdenpcr();
            consultorio1.listarOrdenPcr(txtBuscar.getText(), check, tbOrdenpcr);
        }else{
            check = "T";
            ConsultorioExtOrdenpcr consultorio1 = new ConsultorioExtOrdenpcr();
            consultorio1.listarOrdenPcr(txtBuscar.getText(), check, tbOrdenpcr);
        }
    }//GEN-LAST:event_chkHoyActionPerformed

    private void tbOrdenpcrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbOrdenpcrMouseClicked
       
    }//GEN-LAST:event_tbOrdenpcrMouseClicked

    private void tbOrdenpcrMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbOrdenpcrMousePressed

    }//GEN-LAST:event_tbOrdenpcrMousePressed

    private void tbOrdenpcrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbOrdenpcrKeyPressed
       
    }//GEN-LAST:event_tbOrdenpcrKeyPressed

    private void txtDniMadreCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDniMadreCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniMadreCaretUpdate

    private void txtDniMadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniMadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniMadreActionPerformed

    private void txtBuscarNinoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarNinoCaretUpdate
        ConsultorioExtOrdenpcr consultorio1 = new ConsultorioExtOrdenpcr();
        consultorio1.listarNinos(txtBuscarNino.getText(),tbNinos);
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

    private void txtIdHcCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIdHcCaretUpdate
        if(!txtIdHc.getText().equals("")){
            ConsultorioExtOrdenpcr consultorio1 = new ConsultorioExtOrdenpcr();
            consultorio1.listarOrdenPcr(txtIdHc.getText(), "A", tbOrdenpcr);
            enviarDatosOrdenPcr();
        } else {
           consultorio.listarOrdenPcr(txtBuscar.getText(), "T",tbOrdenpcr); 
           chkHoy.setSelected(false);
        }
    }//GEN-LAST:event_txtIdHcCaretUpdate

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        ConsultorioExtOrdenpcr consultorio1 = new ConsultorioExtOrdenpcr();
        consultorio1.listarOrdenPcr(txtBuscar.getText(), check, tbOrdenpcr);
    }//GEN-LAST:event_txtBuscarCaretUpdate

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
            java.util.logging.Logger.getLogger(OrdenPCR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrdenPCR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrdenPCR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrdenPCR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrdenPCR().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BuscarNinos;
    private javax.swing.JLabel Sexo;
    private javax.swing.JLabel Sexo1;
    private javax.swing.JLabel Sexo2;
    private javax.swing.JLabel Sexo3;
    private javax.swing.JLabel Sexo4;
    private javax.swing.JLabel Sexo5;
    private javax.swing.JLabel Sexo6;
    private javax.swing.JLabel T6;
    private javax.swing.JLabel T7;
    private javax.swing.JButton btnBuscarNino;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSi;
    private javax.swing.JCheckBox chkHoy;
    private javax.swing.JCheckBox chkST;
    private javax.swing.JCheckBox chkTPF;
    private com.toedter.calendar.JDateChooser dtFechaSolicitud;
    private com.toedter.calendar.JDateChooser dtPcr1;
    private com.toedter.calendar.JDateChooser dtPcr2;
    private com.toedter.calendar.JDateChooser dtPcr3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JLabel lblDniNino;
    public static javax.swing.JLabel lblFechaNac;
    public static javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblGenero1;
    public static javax.swing.JLabel lblHc;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMensaje;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JTable tbNinos;
    private javax.swing.JTable tbOrdenpcr;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarNino;
    public static javax.swing.JTextField txtDniMadre;
    public static javax.swing.JTextField txtIdHc;
    public static javax.swing.JTextField txtMadre;
    public static javax.swing.JTextField txtNino;
    // End of variables declaration//GEN-END:variables
}
