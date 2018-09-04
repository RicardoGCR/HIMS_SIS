/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import modelos.Caja.Caja_AperturaCierre;
import modelos.Caja.Caja_PC_Registro;
import modelos.hospitalizacion.HospitalizacionPapeletas;
import servicios.Conexion;
import vista.ALMACEN.frm_Asig_Catalogo_Producto;
import vista.COSTOS.BUSCAR_ESTIMACION_COSTOS;
import vista.COSTOS.BUSCAR_NOMENCLATURA;
import vista.COSTOS.COSTOS_PRODUCTO_REFERENCIAL;
import vista.COSTOS.COSTOS_SERVICIOS_VARIOS;
import vista.COSTOS.CS_PRINCIPAL;
import vista.COSTOS.Costos_Depreciacion;
import vista.COSTOS.TipoSustentacion;
import vista.CRED.RegistroSeguimiento;
import vista.Caja.CAJA_FR_ANULADOS;
import vista.Caja.Caja_Abono;
import vista.Caja.Caja_Apertura;
import vista.Caja.Caja_Cierre;
import vista.Caja.Caja_Empresa_jerarquia;
import vista.Caja.Caja_Grupo_Nomenclaturas;
import vista.Caja.Caja_Jerarquia;
import vista.Caja.Caja_Nomenclaturas;
import vista.Caja.Caja_Pagos;
import vista.Caja.Caja_Precios;
import vista.Caja.Caja_Registro;
import vista.Caja.Caja_Transaccion;
import vista.ConsultorioEx.CEX_PRINCIPAL;
import vista.ConsultorioEx.Consultorio;
import vista.ConsultorioEx.ConsultorioAsignacion;
import vista.ConsultorioEx.ConsultorioExt;
import vista.ConsultorioEx.Triaje;
import vista.EC.EC_BUSCAR_EXAMEN_CAJA;
import vista.EC.EC_PRINCIPAL;
import vista.EC.EC_RESULTADOS;
import vista.LABORATORIO.frm_LAB_ESQUEMA_RESULTADO_INGRESO;
import vista.LABORATORIO.frm_LAB_MUESTRA_EXAMEN;
import vista.LABORATORIO.frm_LAB_VALORES_REFERENCIALES_INGRESO;
import vista.admisionCentral.FrmMovimientoHC;
import vista.admisionCentral.FrmNuevaHistoriaC;
import vista.admisionCentral.FrmSeguimientoHC;
import vista.admisionEmergencia.FrmFormaDeLlegada;
import vista.admisionEmergencia.FrmFormatoEmergencia;
import vista.admisionEmergencia.FrmListFormatoEmergencia;
import vista.hospitalizacion.FrmHospitalizacionCamas;
import vista.hospitalizacion.FrmHospitalizacionHabitaciones;
import vista.hospitalizacion.FrmHospitalizacionCajaPreventa;
import vista.hospitalizacion.FrmHospitalizacionPisos;
import vista.sectorizacion.FrmSector;
import vista.LABORATORIO.*;
import vista.PERSONAL.PERSONAL_ACTIVIDADES;
import vista.PERSONAL.PERSONAL_ASIGNACION_PERSONAL;
import vista.PERSONAL.PERSONAL_ROL;
import vista.PERSONAL.PERSONAL_TURNOS;
import vista.PERSONAL.PER_PRINCIPAL;
import vista.Programas.frm_SOLICITUD_INVESTIGACION_BACT;
import vista.RX.RX_EC_BUSCAR_EXAMEN_C;
import vista.hospitalizacion.FrmHospitalizacionEpicrisis;
import vista.hospitalizacion.FrmHospitalizacionExClinico;
import vista.hospitalizacion.FrmHospitalizacionFormatoHC;
import vista.hospitalizacion.FrmHospitalizacionListarCajaPreventa;
import vista.hospitalizacion.FrmHospitalizacionNotaEnfermeria;
import vista.RX.RX_EC_BUSCAR_EXAMEN_RESULTADO;
import vista.RX.RX_EC_BUSCAR_RESULTADOS;
import vista.RX.RX_PRINCIPAL;
import vista.admisionCentral.ADM_PRINCIPAL;
import vista.admisionEmergencia.AdmEmer_Registro;
import vista.admisionEmergencia.EME_PRINCIPAL;
import vista.admisionEmergencia.FrmFormatoEmergenciaCabecera;
import vista.admisionEmergencia.FrmFormatoEmergenciaTopico;
import vista.admisionEmergencia.FrmFormatoEmergenciaTriaje;
import vista.cuentaPorPagar.BoletaElectronica;
import vista.cuentaPorPagar.CPP_PRINCIPAL;
import vista.cuentaPorPagar.ComunicacionBaja;
import vista.cuentaPorPagar.FacturadorPagos;
import vista.cuentaPorPagar.NotasCreditoDebito;
import vista.cuentaPorPagar.ReporteNCND;
import vista.cuentaPorPagar.VentasConsolidado;

/**
 *
 * @author USUARIO
 */
public class PrincipalMDI extends javax.swing.JFrame {
static int contador =0;
int velocidad=1;
String tg="";
    Conexion con = new Conexion();
    public PrincipalMDI() {
        initComponents();
//        lblServicio.setText(mostrarServicioHosp());
//        jTabbedPane1.setBackgroundAt(0, Color.white);
         this.getContentPane().setBackground(Color.white); 
         timer.start();
         ErrorExistente.setLocationRelativeTo(null);//en el centro
         AperturaOraPC.setLocationRelativeTo(null);//en el centro
         ErrorCajaCerrada.setLocationRelativeTo(null);//en el centro
         NivelSuperior.setLocationRelativeTo(null);//en el centro
         tbAPERTURA_OTRAPC1.getTableHeader().setVisible(false);
         tbAPERTURA_OTRAPC1.setTableHeader(null);
         jScrollPane2.setVisible(false);
         jPanel138.setVisible(false);
         jTabbedPane1.setEnabled(false);
         jTabbedPane1.setEnabledAt(0,false);
         jTabbedPane1.setEnabledAt(1, false);
         jTabbedPane1.setEnabledAt(2,false);


//        cerrartodo();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //ICONO DE FORMULARIO
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
    }
    
    public void mostrarServicioHosp(){
        String desc = "";
        String id = "";
        try {
            String consulta = "EXEC HOSPITALIZACION_MOSTRAR_SERVICIO";
            ResultSet r;
            r=con.Listar(consulta);
        while(r.next()){
            //////PRO CAMBIO EN FRM PRINCIPAL
//               lblSe_ID.setText(r.getString(1));
//               lblServicio.setText(r.getString(2));
        }
        }catch(Exception ex){
            System.out.println("Error: PrincipalMDI - mostrarServicioHosp: " + ex.getMessage());
        }
    }
    
    Timer timer2 = new Timer (5000, new ActionListener (){
        public void actionPerformed(ActionEvent e){
            Imagenes3();  
    }
    });
    
    Timer timer1 = new Timer (5000, new ActionListener (){
        public void actionPerformed(ActionEvent e){
            Imagenes2();
            timer2.start();
    }
    });
    
    Timer timer = new Timer (5000, new ActionListener (){
        public void actionPerformed(ActionEvent e){
            
            Cambios();
    }
    });
    
    public void Cambios(){
        switch(contador){
        case 0:
        contador = 1;
        Imagenes1();
        break;
        case 1:
        contador = 2;
        Imagenes2();
        break;
        case 2:
        contador = 3;
        Imagenes3();
        break;
        case 3:
        contador = 4;
        Imagenes4();
        break;
            
        case 4:
        contador = 5;
        Imagenes5();
        break;
                
        case 5:
        contador = 6;
        Imagenes6();
        break;
            
        case 6:
        contador = 7;
        Imagenes7();
        break;
            
        case 7:
        contador = 0;
        Imagenes8();
        break;
                
        }
    }
    
    public void Imagenes8(){
        ImageIcon CambioF=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/Imagen/B8.png"));
        lblUsu.setIcon(CambioF);
    }
    
    public void Imagenes7(){
        ImageIcon CambioF=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/Imagen/B7.png"));
        lblUsu.setIcon(CambioF);
    }
    
    public void Imagenes6(){
        ImageIcon CambioF=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/Imagen/B6.png"));
        lblUsu.setIcon(CambioF);
    }
    public void Imagenes5(){
        ImageIcon CambioF=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/Imagen/B5.png"));
        lblUsu.setIcon(CambioF);
    }
    public void Imagenes4(){
        ImageIcon CambioF=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/Imagen/B4.png"));
        lblUsu.setIcon(CambioF);
    }
    
    
    public void Imagenes3(){
        ImageIcon CambioF=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/Imagen/B3.png"));
        lblUsu.setIcon(CambioF);
    }
    
    public void Imagenes2(){
        ImageIcon CambioF=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/Imagen/B2.png"));
        lblUsu.setIcon(CambioF);
    }
    
    public void Imagenes1(){
        ImageIcon CambioF=new ImageIcon(this.getClass().getResource("/Imagenes/Iconos/Imagen/B1.png"));
        lblUsu.setIcon(CambioF);
    }
    
//    public void cerrar(){
//        Object [] opciones ={"SI","NO"};
//        int eleccion = JOptionPane.showOptionDialog(rootPane,"¿Desea realmente salir del modulo?","Mensaje de Confirmacion",
//        JOptionPane.YES_NO_OPTION,
//        JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
//        if (eleccion == JOptionPane.YES_OPTION)
//        {
//            dispose();   
//            Principal pr= new Principal();
//            pr.setVisible(true);  
//        }else{
//        }
//    }
    
        //cerrar
//    public void cerrartodo (){
//        try {
//            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//            addWindowListener(new WindowAdapter() {
//                public void windowClosing(WindowEvent e){
//                    cerrar();
//                }
//});
//            this.setVisible(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ErrorExistente = new javax.swing.JDialog();
        jPanel134 = new javax.swing.JPanel();
        lblAd1 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jPanel135 = new javax.swing.JPanel();
        btnAlertConsulta7 = new javax.swing.JButton();
        AperturaOraPC = new javax.swing.JDialog();
        jPanel104 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbAPERTURA_OTRAPC1 = new javax.swing.JTable();
        lblAd2 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jPanel105 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jPanel141 = new javax.swing.JPanel();
        btnAnularVenta = new javax.swing.JButton();
        ErrorCajaCerrada = new javax.swing.JDialog();
        jPanel143 = new javax.swing.JPanel();
        lblAd3 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jPanel144 = new javax.swing.JPanel();
        btnAlertConsulta9 = new javax.swing.JButton();
        NivelSuperior = new javax.swing.JDialog();
        jPanel145 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        panelCPT = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        panelCPT1 = new javax.swing.JPanel();
        txtContra = new javax.swing.JPasswordField();
        jPanel146 = new javax.swing.JPanel();
        btnAlertConsulta10 = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        btnCostos = new javax.swing.JButton();
        btnCaja = new javax.swing.JButton();
        btnLaboratorio2 = new javax.swing.JButton();
        btnAdmEme = new javax.swing.JButton();
        btnPersonal = new javax.swing.JButton();
        btnRayosX = new javax.swing.JButton();
        btnLaboratorio = new javax.swing.JButton();
        btnConExt = new javax.swing.JButton();
        btnHospitalizacion = new javax.swing.JButton();
        btnAdmCentral = new javax.swing.JButton();
        btnEcografia = new javax.swing.JButton();
        btnPersonal1 = new javax.swing.JButton();
        jPanel138 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbAPERTURA = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbEME = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbSESIONES = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbAPERTURA_OTRAPC = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbULTIMA_SESION = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        panelFACTURAS = new javax.swing.JDesktopPane();
        jPanel46 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPC = new javax.swing.JTable();
        btnVentas = new javax.swing.JButton();
        btnVentas1 = new javax.swing.JButton();
        btnVentas2 = new javax.swing.JButton();
        btnVentas3 = new javax.swing.JButton();
        btnVentas5 = new javax.swing.JButton();
        btnVentas6 = new javax.swing.JButton();
        btnVentas7 = new javax.swing.JButton();
        btnVentas8 = new javax.swing.JButton();
        btnVentas9 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        lblIDSESION = new javax.swing.JLabel();
        jPanel73 = new javax.swing.JPanel();
        btnVentas4 = new javax.swing.JButton();
        btnVentas10 = new javax.swing.JButton();
        ibiIDAPERTURA = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblUsu = new javax.swing.JLabel();

        ErrorExistente.setAlwaysOnTop(true);
        ErrorExistente.setMinimumSize(new java.awt.Dimension(446, 240));
        ErrorExistente.setResizable(false);

        jPanel134.setBackground(new java.awt.Color(241, 197, 14));
        jPanel134.setMinimumSize(new java.awt.Dimension(310, 441));

        lblAd1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        lblAd1.setForeground(new java.awt.Color(51, 51, 51));
        lblAd1.setText("Error");

        jLabel119.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(51, 51, 51));
        jLabel119.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Error-80.png"))); // NOI18N
        jLabel119.setText("<html>Actualmente existe una apertura <br>de caja activa en este equipo.</html> ");

        jPanel135.setBackground(new java.awt.Color(43, 43, 43));

        btnAlertConsulta7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAlertConsulta7.setForeground(new java.awt.Color(240, 240, 240));
        btnAlertConsulta7.setText("OK");
        btnAlertConsulta7.setContentAreaFilled(false);
        btnAlertConsulta7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlertConsulta7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAlertConsulta7.setIconTextGap(30);
        btnAlertConsulta7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlertConsulta7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel135Layout = new javax.swing.GroupLayout(jPanel135);
        jPanel135.setLayout(jPanel135Layout);
        jPanel135Layout.setHorizontalGroup(
            jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel135Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAlertConsulta7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel135Layout.setVerticalGroup(
            jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel135Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAlertConsulta7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel134Layout = new javax.swing.GroupLayout(jPanel134);
        jPanel134.setLayout(jPanel134Layout);
        jPanel134Layout.setHorizontalGroup(
            jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel134Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAd1))
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel134Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel135, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel134Layout.setVerticalGroup(
            jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel134Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAd1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel135, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ErrorExistenteLayout = new javax.swing.GroupLayout(ErrorExistente.getContentPane());
        ErrorExistente.getContentPane().setLayout(ErrorExistenteLayout);
        ErrorExistenteLayout.setHorizontalGroup(
            ErrorExistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel134, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ErrorExistenteLayout.setVerticalGroup(
            ErrorExistenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel134, javax.swing.GroupLayout.PREFERRED_SIZE, 206, Short.MAX_VALUE)
        );

        AperturaOraPC.setAlwaysOnTop(true);
        AperturaOraPC.setMinimumSize(new java.awt.Dimension(522, 300));
        AperturaOraPC.setResizable(false);

        jPanel104.setBackground(new java.awt.Color(241, 197, 14));

        jScrollPane7.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tbAPERTURA_OTRAPC1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbAPERTURA_OTRAPC1.setForeground(new java.awt.Color(102, 102, 102));
        tbAPERTURA_OTRAPC1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbAPERTURA_OTRAPC1.setSelectionBackground(new java.awt.Color(43, 43, 43));
        jScrollPane7.setViewportView(tbAPERTURA_OTRAPC1);

        lblAd2.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        lblAd2.setForeground(new java.awt.Color(51, 51, 51));
        lblAd2.setText("Error");

        jLabel120.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(51, 51, 51));
        jLabel120.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Error-80.png"))); // NOI18N
        jLabel120.setText("<html>Actualmente tienes una apertura activa<br>en otro equipo.</html> ");

        jPanel105.setBackground(new java.awt.Color(102, 102, 102));
        jPanel105.setPreferredSize(new java.awt.Dimension(0, 2));

        javax.swing.GroupLayout jPanel105Layout = new javax.swing.GroupLayout(jPanel105);
        jPanel105.setLayout(jPanel105Layout);
        jPanel105Layout.setHorizontalGroup(
            jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel105Layout.setVerticalGroup(
            jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setText("Base");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setText("Terminal");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
        jLabel50.setText("Hora");

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(51, 51, 51));
        jLabel51.setText("Fecha ");

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(51, 51, 51));
        jLabel59.setText(" Serie");

        jPanel141.setBackground(new java.awt.Color(43, 43, 43));
        jPanel141.setPreferredSize(new java.awt.Dimension(125, 25));

        btnAnularVenta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAnularVenta.setForeground(new java.awt.Color(240, 240, 240));
        btnAnularVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Caja fuerte abierta-50.png"))); // NOI18N
        btnAnularVenta.setText("Cerrar Caja Aperturada en otro Equipo");
        btnAnularVenta.setContentAreaFilled(false);
        btnAnularVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnularVenta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAnularVenta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAnularVenta.setIconTextGap(30);
        btnAnularVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel141Layout = new javax.swing.GroupLayout(jPanel141);
        jPanel141.setLayout(jPanel141Layout);
        jPanel141Layout.setHorizontalGroup(
            jPanel141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel141Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAnularVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel141Layout.setVerticalGroup(
            jPanel141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAnularVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel104Layout = new javax.swing.GroupLayout(jPanel104);
        jPanel104.setLayout(jPanel104Layout);
        jPanel104Layout.setHorizontalGroup(
            jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel105, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
            .addGroup(jPanel104Layout.createSequentialGroup()
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel104Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAd2)
                    .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(190, Short.MAX_VALUE))
            .addComponent(jPanel141, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );
        jPanel104Layout.setVerticalGroup(
            jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel104Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAd2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel105, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel141, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout AperturaOraPCLayout = new javax.swing.GroupLayout(AperturaOraPC.getContentPane());
        AperturaOraPC.getContentPane().setLayout(AperturaOraPCLayout);
        AperturaOraPCLayout.setHorizontalGroup(
            AperturaOraPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel104, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AperturaOraPCLayout.setVerticalGroup(
            AperturaOraPCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel104, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ErrorCajaCerrada.setAlwaysOnTop(true);
        ErrorCajaCerrada.setMinimumSize(new java.awt.Dimension(446, 240));
        ErrorCajaCerrada.setResizable(false);

        jPanel143.setBackground(new java.awt.Color(241, 197, 14));
        jPanel143.setMinimumSize(new java.awt.Dimension(310, 441));

        lblAd3.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        lblAd3.setForeground(new java.awt.Color(51, 51, 51));
        lblAd3.setText("Error");

        jLabel121.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(51, 51, 51));
        jLabel121.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Error-80.png"))); // NOI18N
        jLabel121.setText("<html>Esta caja fue cerrada <br>desde otro equipo.</html> ");

        jPanel144.setBackground(new java.awt.Color(43, 43, 43));

        btnAlertConsulta9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAlertConsulta9.setForeground(new java.awt.Color(240, 240, 240));
        btnAlertConsulta9.setText("OK");
        btnAlertConsulta9.setContentAreaFilled(false);
        btnAlertConsulta9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlertConsulta9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAlertConsulta9.setIconTextGap(30);
        btnAlertConsulta9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlertConsulta9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel144Layout = new javax.swing.GroupLayout(jPanel144);
        jPanel144.setLayout(jPanel144Layout);
        jPanel144Layout.setHorizontalGroup(
            jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel144Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAlertConsulta9, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel144Layout.setVerticalGroup(
            jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel144Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAlertConsulta9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel143Layout = new javax.swing.GroupLayout(jPanel143);
        jPanel143.setLayout(jPanel143Layout);
        jPanel143Layout.setHorizontalGroup(
            jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel143Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAd3))
                .addContainerGap(167, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel143Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel144, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel143Layout.setVerticalGroup(
            jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel143Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAd3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel144, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ErrorCajaCerradaLayout = new javax.swing.GroupLayout(ErrorCajaCerrada.getContentPane());
        ErrorCajaCerrada.getContentPane().setLayout(ErrorCajaCerradaLayout);
        ErrorCajaCerradaLayout.setHorizontalGroup(
            ErrorCajaCerradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel143, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ErrorCajaCerradaLayout.setVerticalGroup(
            ErrorCajaCerradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel143, javax.swing.GroupLayout.PREFERRED_SIZE, 206, Short.MAX_VALUE)
        );

        NivelSuperior.setAlwaysOnTop(true);
        NivelSuperior.setMinimumSize(new java.awt.Dimension(430, 366));
        NivelSuperior.setResizable(false);

        jPanel145.setBackground(new java.awt.Color(204, 204, 204));

        jLabel64.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel64.setText("Iniciar sesión");

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(51, 51, 51));
        jLabel65.setText("Usuario");

        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(51, 51, 51));
        jLabel66.setText("Contraseña");

        panelCPT.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(51, 51, 51));
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtUsuario.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtUsuario.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtUsuarioCaretUpdate(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelCPTLayout = new javax.swing.GroupLayout(panelCPT);
        panelCPT.setLayout(panelCPTLayout);
        panelCPTLayout.setHorizontalGroup(
            panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPTLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtUsuario)
                .addContainerGap())
        );
        panelCPTLayout.setVerticalGroup(
            panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPTLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCPT1.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtContra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtContra.setForeground(new java.awt.Color(51, 51, 51));
        txtContra.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtContra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panelCPT1Layout = new javax.swing.GroupLayout(panelCPT1);
        panelCPT1.setLayout(panelCPT1Layout);
        panelCPT1Layout.setHorizontalGroup(
            panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCPT1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtContra, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelCPT1Layout.setVerticalGroup(
            panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT1Layout.createSequentialGroup()
                .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel146.setBackground(new java.awt.Color(41, 127, 184));

        btnAlertConsulta10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnAlertConsulta10.setForeground(new java.awt.Color(240, 240, 240));
        btnAlertConsulta10.setText("Iniciar Sesión");
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
            .addComponent(btnAlertConsulta10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
        );
        jPanel146Layout.setVerticalGroup(
            jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel146Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAlertConsulta10))
        );

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(102, 102, 102));
        jLabel67.setText("<html>Esta caja fue cerrada recientemente, para volver a acceder<br>  pídale a _______ que ingrese sus credenciales.</html>");

        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel68.setText("X");
        jLabel68.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel68MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel145Layout = new javax.swing.GroupLayout(jPanel145);
        jPanel145.setLayout(jPanel145Layout);
        jPanel145Layout.setHorizontalGroup(
            jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel145Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel145Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel66)
                            .addComponent(jLabel65))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelCPT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel145Layout.createSequentialGroup()
                        .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel145Layout.createSequentialGroup()
                                .addComponent(jLabel64)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel68))
                            .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel146, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel145Layout.setVerticalGroup(
            jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel145Layout.createSequentialGroup()
                .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel145Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel64))
                    .addGroup(jPanel145Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel68)))
                .addGap(44, 44, 44)
                .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelCPT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(67, 67, 67)
                .addComponent(jPanel146, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addContainerGap())
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HIMS");

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        btnCostos.setBackground(new java.awt.Color(102, 102, 102));
        btnCostos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCostos.setForeground(new java.awt.Color(102, 102, 102));
        btnCostos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Cheque de nómina-64.png"))); // NOI18N
        btnCostos.setText("Costos");
        btnCostos.setContentAreaFilled(false);
        btnCostos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCostos.setFocusPainted(false);
        btnCostos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCostos.setIconTextGap(30);
        btnCostos.setPreferredSize(new java.awt.Dimension(155, 155));
        btnCostos.setVerifyInputWhenFocusTarget(false);
        btnCostos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCostos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCostosActionPerformed(evt);
            }
        });

        btnCaja.setBackground(new java.awt.Color(102, 102, 102));
        btnCaja.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCaja.setForeground(new java.awt.Color(102, 102, 102));
        btnCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Caja fuerte-64 (1).png"))); // NOI18N
        btnCaja.setText("Caja");
        btnCaja.setContentAreaFilled(false);
        btnCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCaja.setFocusPainted(false);
        btnCaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCaja.setIconTextGap(30);
        btnCaja.setPreferredSize(new java.awt.Dimension(155, 155));
        btnCaja.setVerifyInputWhenFocusTarget(false);
        btnCaja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCajaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCajaMouseEntered(evt);
            }
        });
        btnCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCajaActionPerformed(evt);
            }
        });

        btnLaboratorio2.setBackground(new java.awt.Color(25, 88, 130));
        btnLaboratorio2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLaboratorio2.setForeground(new java.awt.Color(102, 102, 102));
        btnLaboratorio2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Money Transfer-64(1).png"))); // NOI18N
        btnLaboratorio2.setText("Facturador");
        btnLaboratorio2.setContentAreaFilled(false);
        btnLaboratorio2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLaboratorio2.setFocusPainted(false);
        btnLaboratorio2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLaboratorio2.setIconTextGap(30);
        btnLaboratorio2.setPreferredSize(new java.awt.Dimension(155, 155));
        btnLaboratorio2.setVerifyInputWhenFocusTarget(false);
        btnLaboratorio2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLaboratorio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaboratorio2ActionPerformed(evt);
            }
        });

        btnAdmEme.setBackground(new java.awt.Color(102, 102, 102));
        btnAdmEme.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAdmEme.setForeground(new java.awt.Color(102, 102, 102));
        btnAdmEme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-ambulance-64.png"))); // NOI18N
        btnAdmEme.setText("Emergencia");
        btnAdmEme.setContentAreaFilled(false);
        btnAdmEme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdmEme.setFocusPainted(false);
        btnAdmEme.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdmEme.setIconTextGap(30);
        btnAdmEme.setPreferredSize(new java.awt.Dimension(155, 155));
        btnAdmEme.setVerifyInputWhenFocusTarget(false);
        btnAdmEme.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdmEme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdmEmeMouseEntered(evt);
            }
        });
        btnAdmEme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmEmeActionPerformed(evt);
            }
        });

        btnPersonal.setBackground(new java.awt.Color(102, 102, 102));
        btnPersonal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnPersonal.setForeground(new java.awt.Color(102, 102, 102));
        btnPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Grupo de usuarios hombre hombre-64 (1).png"))); // NOI18N
        btnPersonal.setText("Personal");
        btnPersonal.setContentAreaFilled(false);
        btnPersonal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPersonal.setFocusPainted(false);
        btnPersonal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPersonal.setIconTextGap(30);
        btnPersonal.setPreferredSize(new java.awt.Dimension(155, 155));
        btnPersonal.setVerifyInputWhenFocusTarget(false);
        btnPersonal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalActionPerformed(evt);
            }
        });

        btnRayosX.setBackground(new java.awt.Color(102, 102, 102));
        btnRayosX.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnRayosX.setForeground(new java.awt.Color(51, 51, 51));
        btnRayosX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-x-ray-64.png"))); // NOI18N
        btnRayosX.setText("Rayos X");
        btnRayosX.setContentAreaFilled(false);
        btnRayosX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRayosX.setFocusPainted(false);
        btnRayosX.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRayosX.setIconTextGap(30);
        btnRayosX.setPreferredSize(new java.awt.Dimension(155, 155));
        btnRayosX.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-x-ray-64(1).png"))); // NOI18N
        btnRayosX.setVerifyInputWhenFocusTarget(false);
        btnRayosX.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRayosX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRayosXActionPerformed(evt);
            }
        });

        btnLaboratorio.setBackground(new java.awt.Color(102, 102, 102));
        btnLaboratorio.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnLaboratorio.setForeground(new java.awt.Color(51, 51, 51));
        btnLaboratorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-microscope-64.png"))); // NOI18N
        btnLaboratorio.setText("Laboratorio");
        btnLaboratorio.setContentAreaFilled(false);
        btnLaboratorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLaboratorio.setFocusPainted(false);
        btnLaboratorio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLaboratorio.setIconTextGap(30);
        btnLaboratorio.setPreferredSize(new java.awt.Dimension(155, 155));
        btnLaboratorio.setVerifyInputWhenFocusTarget(false);
        btnLaboratorio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLaboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaboratorioActionPerformed(evt);
            }
        });

        btnConExt.setBackground(new java.awt.Color(102, 102, 102));
        btnConExt.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnConExt.setForeground(new java.awt.Color(51, 51, 51));
        btnConExt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-stethoscope-64.png"))); // NOI18N
        btnConExt.setText("<HTML>Consultorios<BR>Externos<HTML>");
        btnConExt.setContentAreaFilled(false);
        btnConExt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConExt.setFocusPainted(false);
        btnConExt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConExt.setIconTextGap(30);
        btnConExt.setPreferredSize(new java.awt.Dimension(155, 155));
        btnConExt.setVerifyInputWhenFocusTarget(false);
        btnConExt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConExt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConExtActionPerformed(evt);
            }
        });

        btnHospitalizacion.setBackground(new java.awt.Color(102, 102, 102));
        btnHospitalizacion.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnHospitalizacion.setForeground(new java.awt.Color(51, 51, 51));
        btnHospitalizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-occupied-bed-64.png"))); // NOI18N
        btnHospitalizacion.setText("Hospitalización");
        btnHospitalizacion.setContentAreaFilled(false);
        btnHospitalizacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHospitalizacion.setFocusPainted(false);
        btnHospitalizacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHospitalizacion.setIconTextGap(30);
        btnHospitalizacion.setPreferredSize(new java.awt.Dimension(155, 155));
        btnHospitalizacion.setVerifyInputWhenFocusTarget(false);
        btnHospitalizacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHospitalizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHospitalizacionActionPerformed(evt);
            }
        });

        btnAdmCentral.setBackground(new java.awt.Color(102, 102, 102));
        btnAdmCentral.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnAdmCentral.setForeground(new java.awt.Color(51, 51, 51));
        btnAdmCentral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-reception-64.png"))); // NOI18N
        btnAdmCentral.setText("Admisión");
        btnAdmCentral.setContentAreaFilled(false);
        btnAdmCentral.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdmCentral.setFocusPainted(false);
        btnAdmCentral.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdmCentral.setIconTextGap(30);
        btnAdmCentral.setPreferredSize(new java.awt.Dimension(155, 155));
        btnAdmCentral.setVerifyInputWhenFocusTarget(false);
        btnAdmCentral.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdmCentral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdmCentralMouseEntered(evt);
            }
        });
        btnAdmCentral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmCentralActionPerformed(evt);
            }
        });

        btnEcografia.setBackground(new java.awt.Color(102, 102, 102));
        btnEcografia.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnEcografia.setForeground(new java.awt.Color(51, 51, 51));
        btnEcografia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-minimize-window-64.png"))); // NOI18N
        btnEcografia.setText("Ecografías");
        btnEcografia.setContentAreaFilled(false);
        btnEcografia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEcografia.setFocusPainted(false);
        btnEcografia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEcografia.setIconTextGap(30);
        btnEcografia.setPreferredSize(new java.awt.Dimension(155, 155));
        btnEcografia.setVerifyInputWhenFocusTarget(false);
        btnEcografia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEcografia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEcografiaActionPerformed(evt);
            }
        });

        btnPersonal1.setBackground(new java.awt.Color(102, 102, 102));
        btnPersonal1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnPersonal1.setForeground(new java.awt.Color(102, 102, 102));
        btnPersonal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Grupo de usuarios hombre hombre-64 (1).png"))); // NOI18N
        btnPersonal1.setText("Usuarios");
        btnPersonal1.setContentAreaFilled(false);
        btnPersonal1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPersonal1.setFocusPainted(false);
        btnPersonal1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPersonal1.setIconTextGap(30);
        btnPersonal1.setPreferredSize(new java.awt.Dimension(155, 155));
        btnPersonal1.setVerifyInputWhenFocusTarget(false);
        btnPersonal1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPersonal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonal1ActionPerformed(evt);
            }
        });

        tbAPERTURA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tbAPERTURA);

        tbEME.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tbEME);

        tbSESIONES.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tbSESIONES);

        tbAPERTURA_OTRAPC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tbAPERTURA_OTRAPC);

        tbULTIMA_SESION.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(tbULTIMA_SESION);

        javax.swing.GroupLayout jPanel138Layout = new javax.swing.GroupLayout(jPanel138);
        jPanel138.setLayout(jPanel138Layout);
        jPanel138Layout.setHorizontalGroup(
            jPanel138Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel138Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel138Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel138Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel138Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel138Layout.setVerticalGroup(
            jPanel138Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel138Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel138Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel138Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel138, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnPersonal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRayosX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCostos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnLaboratorio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAdmEme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnHospitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnConExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAdmCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEcografia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCostos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLaboratorio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdmEme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHospitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRayosX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdmCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEcografia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPersonal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel138, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 85, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inicio", jPanel2);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelFACTURASLayout = new javax.swing.GroupLayout(panelFACTURAS);
        panelFACTURAS.setLayout(panelFACTURASLayout);
        panelFACTURASLayout.setHorizontalGroup(
            panelFACTURASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1156, Short.MAX_VALUE)
        );
        panelFACTURASLayout.setVerticalGroup(
            panelFACTURASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFACTURAS)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFACTURAS)
        );

        jTabbedPane1.addTab("Rx", jPanel14);

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N

        tbPC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbPC.setForeground(new java.awt.Color(51, 51, 51));
        tbPC.setToolTipText("");
        tbPC.setRowHeight(25);
        tbPC.getTableHeader().setReorderingAllowed(false);
        tbPC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPCKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbPC);

        btnVentas.setBackground(new java.awt.Color(102, 102, 102));
        btnVentas.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnVentas.setForeground(new java.awt.Color(51, 51, 51));
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Monedas-64.png"))); // NOI18N
        btnVentas.setText("Ventas");
        btnVentas.setContentAreaFilled(false);
        btnVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas.setFocusPainted(false);
        btnVentas.setFocusable(false);
        btnVentas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas.setIconTextGap(30);
        btnVentas.setPreferredSize(new java.awt.Dimension(155, 155));
        btnVentas.setVerifyInputWhenFocusTarget(false);
        btnVentas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentasMouseEntered(evt);
            }
        });
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });

        btnVentas1.setBackground(new java.awt.Color(102, 102, 102));
        btnVentas1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnVentas1.setForeground(new java.awt.Color(51, 51, 51));
        btnVentas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Editar propiedad-64.png"))); // NOI18N
        btnVentas1.setText("CPT");
        btnVentas1.setContentAreaFilled(false);
        btnVentas1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas1.setFocusPainted(false);
        btnVentas1.setFocusable(false);
        btnVentas1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas1.setIconTextGap(30);
        btnVentas1.setPreferredSize(new java.awt.Dimension(155, 155));
        btnVentas1.setVerifyInputWhenFocusTarget(false);
        btnVentas1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentas1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentas1MouseEntered(evt);
            }
        });
        btnVentas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentas1ActionPerformed(evt);
            }
        });

        btnVentas2.setBackground(new java.awt.Color(102, 102, 102));
        btnVentas2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnVentas2.setForeground(new java.awt.Color(51, 51, 51));
        btnVentas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Price Tag-64.png"))); // NOI18N
        btnVentas2.setText("Tarifario");
        btnVentas2.setContentAreaFilled(false);
        btnVentas2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas2.setFocusPainted(false);
        btnVentas2.setFocusable(false);
        btnVentas2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas2.setIconTextGap(30);
        btnVentas2.setPreferredSize(new java.awt.Dimension(155, 155));
        btnVentas2.setVerifyInputWhenFocusTarget(false);
        btnVentas2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentas2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentas2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentas2MouseEntered(evt);
            }
        });
        btnVentas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentas2ActionPerformed(evt);
            }
        });

        btnVentas3.setBackground(new java.awt.Color(102, 102, 102));
        btnVentas3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnVentas3.setForeground(new java.awt.Color(51, 51, 51));
        btnVentas3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Comments-64.png"))); // NOI18N
        btnVentas3.setText("Grupos");
        btnVentas3.setContentAreaFilled(false);
        btnVentas3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas3.setFocusPainted(false);
        btnVentas3.setFocusable(false);
        btnVentas3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas3.setIconTextGap(30);
        btnVentas3.setPreferredSize(new java.awt.Dimension(155, 155));
        btnVentas3.setVerifyInputWhenFocusTarget(false);
        btnVentas3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentas3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentas3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentas3MouseEntered(evt);
            }
        });
        btnVentas3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentas3ActionPerformed(evt);
            }
        });

        btnVentas5.setBackground(new java.awt.Color(102, 102, 102));
        btnVentas5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnVentas5.setForeground(new java.awt.Color(51, 51, 51));
        btnVentas5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-increase-filled-64.png"))); // NOI18N
        btnVentas5.setText("Estadísticas");
        btnVentas5.setContentAreaFilled(false);
        btnVentas5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas5.setFocusPainted(false);
        btnVentas5.setFocusable(false);
        btnVentas5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas5.setIconTextGap(30);
        btnVentas5.setPreferredSize(new java.awt.Dimension(155, 155));
        btnVentas5.setVerifyInputWhenFocusTarget(false);
        btnVentas5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentas5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentas5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentas5MouseEntered(evt);
            }
        });
        btnVentas5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentas5ActionPerformed(evt);
            }
        });

        btnVentas6.setBackground(new java.awt.Color(102, 102, 102));
        btnVentas6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnVentas6.setForeground(new java.awt.Color(51, 51, 51));
        btnVentas6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-access-64.png"))); // NOI18N
        btnVentas6.setText("Cuenta Corriente");
        btnVentas6.setContentAreaFilled(false);
        btnVentas6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas6.setFocusPainted(false);
        btnVentas6.setFocusable(false);
        btnVentas6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas6.setIconTextGap(30);
        btnVentas6.setPreferredSize(new java.awt.Dimension(155, 155));
        btnVentas6.setVerifyInputWhenFocusTarget(false);
        btnVentas6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentas6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentas6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentas6MouseEntered(evt);
            }
        });
        btnVentas6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentas6ActionPerformed(evt);
            }
        });

        btnVentas7.setBackground(new java.awt.Color(102, 102, 102));
        btnVentas7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnVentas7.setForeground(new java.awt.Color(51, 51, 51));
        btnVentas7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-company-64.png"))); // NOI18N
        btnVentas7.setText("Empresas");
        btnVentas7.setContentAreaFilled(false);
        btnVentas7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas7.setFocusPainted(false);
        btnVentas7.setFocusable(false);
        btnVentas7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas7.setIconTextGap(30);
        btnVentas7.setPreferredSize(new java.awt.Dimension(155, 155));
        btnVentas7.setVerifyInputWhenFocusTarget(false);
        btnVentas7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentas7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentas7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentas7MouseEntered(evt);
            }
        });
        btnVentas7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentas7ActionPerformed(evt);
            }
        });

        btnVentas8.setBackground(new java.awt.Color(102, 102, 102));
        btnVentas8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnVentas8.setForeground(new java.awt.Color(51, 51, 51));
        btnVentas8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Accounting-64.png"))); // NOI18N
        btnVentas8.setText("Cuentas");
        btnVentas8.setContentAreaFilled(false);
        btnVentas8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas8.setFocusPainted(false);
        btnVentas8.setFocusable(false);
        btnVentas8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas8.setIconTextGap(30);
        btnVentas8.setPreferredSize(new java.awt.Dimension(155, 155));
        btnVentas8.setVerifyInputWhenFocusTarget(false);
        btnVentas8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentas8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentas8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentas8MouseEntered(evt);
            }
        });
        btnVentas8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentas8ActionPerformed(evt);
            }
        });

        btnVentas9.setBackground(new java.awt.Color(102, 102, 102));
        btnVentas9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnVentas9.setForeground(new java.awt.Color(51, 51, 51));
        btnVentas9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Money Transfer-64(1).png"))); // NOI18N
        btnVentas9.setText("Formas de Pago");
        btnVentas9.setContentAreaFilled(false);
        btnVentas9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas9.setFocusPainted(false);
        btnVentas9.setFocusable(false);
        btnVentas9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas9.setIconTextGap(30);
        btnVentas9.setPreferredSize(new java.awt.Dimension(155, 155));
        btnVentas9.setVerifyInputWhenFocusTarget(false);
        btnVentas9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentas9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentas9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentas9MouseEntered(evt);
            }
        });
        btnVentas9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentas9ActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(0, 122, 204));

        jLabel47.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("<html>Caja Aperturada<span style=\"font-size:'14px'\"><br></br></span></html>");

        lblIDSESION.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblIDSESION.setForeground(new java.awt.Color(255, 255, 255));
        lblIDSESION.setText("ID");

        jPanel73.setBackground(new java.awt.Color(255, 51, 51));

        btnVentas4.setBackground(new java.awt.Color(102, 102, 102));
        btnVentas4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnVentas4.setForeground(new java.awt.Color(255, 255, 255));
        btnVentas4.setText("Cierre de Caja");
        btnVentas4.setContentAreaFilled(false);
        btnVentas4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas4.setFocusPainted(false);
        btnVentas4.setFocusable(false);
        btnVentas4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVentas4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas4.setIconTextGap(30);
        btnVentas4.setVerifyInputWhenFocusTarget(false);
        btnVentas4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentas4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentas4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentas4MouseEntered(evt);
            }
        });
        btnVentas4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentas4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
        jPanel73.setLayout(jPanel73Layout);
        jPanel73Layout.setHorizontalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addComponent(btnVentas4, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 88, Short.MAX_VALUE))
        );
        jPanel73Layout.setVerticalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnVentas4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIDSESION, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(lblIDSESION)
                .addContainerGap())
        );

        btnVentas10.setBackground(new java.awt.Color(102, 102, 102));
        btnVentas10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnVentas10.setForeground(new java.awt.Color(51, 51, 51));
        btnVentas10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Imprimir-64.png"))); // NOI18N
        btnVentas10.setText("Impresora");
        btnVentas10.setContentAreaFilled(false);
        btnVentas10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas10.setFocusPainted(false);
        btnVentas10.setFocusable(false);
        btnVentas10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas10.setIconTextGap(30);
        btnVentas10.setPreferredSize(new java.awt.Dimension(155, 155));
        btnVentas10.setVerifyInputWhenFocusTarget(false);
        btnVentas10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentas10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentas10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentas10MouseEntered(evt);
            }
        });
        btnVentas10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentas10ActionPerformed(evt);
            }
        });

        ibiIDAPERTURA.setText("jLabel48");

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(btnVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVentas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVentas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVentas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVentas9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel46Layout.createSequentialGroup()
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addComponent(btnVentas8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                                .addComponent(ibiIDAPERTURA)
                                .addGap(134, 134, 134)))
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addComponent(btnVentas5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnVentas6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnVentas7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnVentas10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(275, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentas9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVentas8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentas5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentas6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentas7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentas10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ibiIDAPERTURA)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Caja", jPanel46);

        jPanel6.setBackground(new java.awt.Color(40, 40, 43));

        lblUsu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblUsu.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/B1.png"))); // NOI18N
        lblUsu.setText("jLabel2");
        lblUsu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblUsu.setIconTextGap(40);
        lblUsu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlertConsulta7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta7ActionPerformed
        ErrorExistente.dispose();
    }//GEN-LAST:event_btnAlertConsulta7ActionPerformed

    private void btnAnularVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularVentaActionPerformed
            Caja_Cierre frmCIERRED = new Caja_Cierre();
            frmCIERRED.lblusu.setText(PrincipalMDI.lblUsu.getText());
            frmCIERRED.setVisible(true);
            AperturaOraPC.dispose();
    }//GEN-LAST:event_btnAnularVentaActionPerformed

    private void btnAlertConsulta9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta9ActionPerformed
        ErrorCajaCerrada.dispose();
    }//GEN-LAST:event_btnAlertConsulta9ActionPerformed

    private void txtUsuarioCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtUsuarioCaretUpdate

    }//GEN-LAST:event_txtUsuarioCaretUpdate

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed

    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        txtUsuario.setText(txtUsuario.getText().toUpperCase());
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped

    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtContraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraKeyPressed

    }//GEN-LAST:event_txtContraKeyPressed

    private void btnAlertConsulta10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta10ActionPerformed
        NivelSuperior.dispose();    
        Caja_Apertura frmPCAPERTURA = new Caja_Apertura();
        frmPCAPERTURA.setVisible(true);
        frmPCAPERTURA.lblusu.setText(PrincipalMDI.lblUsu.getText());
    }//GEN-LAST:event_btnAlertConsulta10ActionPerformed

    private void jLabel68MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel68MouseClicked
        NivelSuperior.dispose();
    }//GEN-LAST:event_jLabel68MouseClicked

    private void lblUsuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsuMouseClicked
//         dispose();
//        inicioSesion i=new inicioSesion();
//        i.setVisible(true);
        jPanel6.setBackground(new Color(40,40,43)); 
        PrincipalMDI.jTabbedPane1.setSelectedIndex(0);
    switch (tg) {
        case "RX":
            RX_PRINCIPAL.jButton2.doClick();
            break;
        case "EC":
            EC_PRINCIPAL.jButton2.doClick();
            break;
        case "ADM":
            ADM_PRINCIPAL.jButton2.doClick();
            break;  
        case "LAB":
//            ADM_PRINCIPAL.jButton2.doClick();
            break;  
    }

    }//GEN-LAST:event_lblUsuMouseClicked

    private void btnVentas10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas10ActionPerformed
        Impresoras CI = new Impresoras();
        CI.setVisible(true);
        String u=PrincipalMDI.lblUsu.getText();
        CI.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas10ActionPerformed

    private void btnVentas10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas10MouseEntered

    private void btnVentas10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas10MouseClicked

    private void btnVentas4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas4ActionPerformed

        Caja_Cierre frmCIERRE = new Caja_Cierre();
        frmCIERRE.lblusu.setText(PrincipalMDI.lblUsu.getText());
        frmCIERRE.setVisible(true);
    }//GEN-LAST:event_btnVentas4ActionPerformed

    private void btnVentas4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas4MouseEntered

    private void btnVentas4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas4MouseClicked

    private void btnVentas9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas9ActionPerformed
        Caja_Jerarquia CJ = new Caja_Jerarquia();
        CJ.setVisible(true);
        String u=PrincipalMDI.lblUsu.getText();
        CJ.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas9ActionPerformed

    private void btnVentas9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas9MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas9MouseEntered

    private void btnVentas9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas9MouseClicked

    private void btnVentas8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas8ActionPerformed
        Caja_Transaccion CT = new Caja_Transaccion();
        CT.setVisible(true);
        String u=PrincipalMDI.lblUsu.getText();
        CT.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas8ActionPerformed

    private void btnVentas8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas8MouseEntered

    private void btnVentas8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas8MouseClicked

    private void btnVentas7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas7ActionPerformed
        Caja_Empresa_jerarquia CEJFP = new Caja_Empresa_jerarquia();
        CEJFP.setVisible(true);
        String u=PrincipalMDI.lblUsu.getText();
        CEJFP.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas7ActionPerformed

    private void btnVentas7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas7MouseEntered

    private void btnVentas7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas7MouseClicked

    private void btnVentas6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas6ActionPerformed
        Caja_Abono CA = new Caja_Abono();
        CA.setVisible(true);
        String u=PrincipalMDI.lblUsu.getText();
        CA.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas6ActionPerformed

    private void btnVentas6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas6MouseEntered

    private void btnVentas6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas6MouseClicked

    private void btnVentas5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas5ActionPerformed

    private void btnVentas5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas5MouseEntered

    private void btnVentas5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas5MouseClicked

    private void btnVentas3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas3ActionPerformed
        Caja_Grupo_Nomenclaturas CG = new Caja_Grupo_Nomenclaturas();
        CG.setVisible(true);
        String u=PrincipalMDI.lblUsu.getText();
        CG.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas3ActionPerformed

    private void btnVentas3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas3MouseEntered

    private void btnVentas3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas3MouseClicked

    private void btnVentas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas2ActionPerformed
        Caja_Precios CPR = new Caja_Precios();
        CPR.setVisible(true);
        String u=PrincipalMDI.lblUsu.getText();
        CPR.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas2ActionPerformed

    private void btnVentas2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas2MouseEntered

    private void btnVentas2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas2MouseClicked

    private void btnVentas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas1ActionPerformed
        Caja_Nomenclaturas CCPT = new Caja_Nomenclaturas();
        CCPT.setVisible(true);
        String u=PrincipalMDI.lblUsu.getText();
        CCPT.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas1ActionPerformed

    private void btnVentas1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas1MouseEntered

    private void btnVentas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas1MouseClicked

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        Caja_Pagos CP = new Caja_Pagos();
        CP.setVisible(true);
        String IDS=PrincipalMDI.lblIDSESION.getText();
        Caja_Pagos.lblID_SESION.setText(IDS);
        String u=PrincipalMDI.lblUsu.getText();
        Caja_Pagos.lblusu.setText(u);
        String ID=PrincipalMDI.ibiIDAPERTURA.getText();
        Caja_Pagos.lblID_APERTURA.setText(ID);
        System.out.println(" "+Caja_Pagos.lblID_SESION.getText());
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnVentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseEntered

    }//GEN-LAST:event_btnVentasMouseEntered

    private void btnVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseClicked

    }//GEN-LAST:event_btnVentasMouseClicked

    private void tbPCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPCKeyPressed

    }//GEN-LAST:event_tbPCKeyPressed

    private void btnPersonal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonal1ActionPerformed
        RegistroUsuario frm = new RegistroUsuario();
        frm.setVisible(true);
        String u=PrincipalMDI.lblUsu.getText();
        frm.lblUsuUsuario.setText(u);
    }//GEN-LAST:event_btnPersonal1ActionPerformed

    private void btnEcografiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEcografiaActionPerformed
        tg="EC";
        Caja_PC_Registro CPC = new Caja_PC_Registro();
        CPC.VerificarExistencia(PrincipalMDI.lblUsu.getText(),"EC",tbPC);
        if(tbPC.getRowCount()==0){
            Caja_Registro frmPC = new Caja_Registro();
            frmPC.setVisible(true);
            frmPC.lblUsuario.setText(PrincipalMDI.lblUsu.getText());
        }else   if(tbPC.getRowCount()>0){
            EC_PRINCIPAL f=new EC_PRINCIPAL();
            panelFACTURAS.add(f);
            //        String u=Principal.lblUsu.getText();
            f.setVisible(true);
            try {
                f.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            jPanel6.setBackground(new Color(25,188,157));
            jTabbedPane1.setSelectedIndex(1);
        }
        
    }//GEN-LAST:event_btnEcografiaActionPerformed

    private void btnAdmCentralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmCentralActionPerformed
        tg="ADM";
        Caja_PC_Registro CPC = new Caja_PC_Registro();
        CPC.VerificarExistencia(PrincipalMDI.lblUsu.getText(),"ADM",tbPC);
        if(tbPC.getRowCount()==0){
            Caja_Registro frmPC = new Caja_Registro();
            frmPC.setVisible(true);
            frmPC.lblUsuario.setText(PrincipalMDI.lblUsu.getText());
        }else   if(tbPC.getRowCount()>0){
            ADM_PRINCIPAL f=new ADM_PRINCIPAL();
            panelFACTURAS.add(f);
            //        String u=Principal.lblUsu.getText();
            f.setVisible(true);
            try {
                f.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTabbedPane1.setSelectedIndex(1);
        }
        
    }//GEN-LAST:event_btnAdmCentralActionPerformed

    private void btnAdmCentralMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmCentralMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdmCentralMouseEntered

    private void btnHospitalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHospitalizacionActionPerformed

        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_btnHospitalizacionActionPerformed

    private void btnConExtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConExtActionPerformed
        CEX_PRINCIPAL f=new CEX_PRINCIPAL();
        panelFACTURAS.add(f);
        //        String u=Principal.lblUsu.getText();
        f.setVisible(true);
        try {
            f.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnConExtActionPerformed

    private void btnLaboratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaboratorioActionPerformed
        tg="LAB";
        Caja_PC_Registro CPC = new Caja_PC_Registro();
        CPC.VerificarExistencia(PrincipalMDI.lblUsu.getText(),"LAB",tbPC);
        if(tbPC.getRowCount()==0){
            Caja_Registro frmPC = new Caja_Registro();
            frmPC.setVisible(true);
            frmPC.lblUsuario.setText(PrincipalMDI.lblUsu.getText());
        }else   if(tbPC.getRowCount()>0){
            LAB_PRINCIPAL f=new LAB_PRINCIPAL();
            panelFACTURAS.add(f);
            //        String u=Principal.lblUsu.getText();
            f.setVisible(true);
            try {
                f.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTabbedPane1.setSelectedIndex(1);
        }
        
    }//GEN-LAST:event_btnLaboratorioActionPerformed

    private void btnRayosXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRayosXActionPerformed
        tg="RX";
        Caja_PC_Registro CPC = new Caja_PC_Registro();
        CPC.VerificarExistencia(PrincipalMDI.lblUsu.getText(),"RX",tbPC);
        if(tbPC.getRowCount()==0){
            Caja_Registro frmPC = new Caja_Registro();
            frmPC.setVisible(true);
            frmPC.lblUsuario.setText(PrincipalMDI.lblUsu.getText());
        }else   if(tbPC.getRowCount()>0){
            RX_PRINCIPAL f=new RX_PRINCIPAL();
            panelFACTURAS.add(f);
            //        String u=Principal.lblUsu.getText();

            f.setVisible(true);
            f.jButton1.doClick();
            try {
                f.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
//            jPanel6.setBackground(new Color(209,52,56));
            jTabbedPane1.setSelectedIndex(1);
        }
        

    }//GEN-LAST:event_btnRayosXActionPerformed

    private void btnPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalActionPerformed
        PER_PRINCIPAL f=new PER_PRINCIPAL();
        panelFACTURAS.add(f);
        //        String u=Principal.lblUsu.getText();

        f.setVisible(true);
        try {
            f.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnPersonalActionPerformed

    private void btnAdmEmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmEmeActionPerformed
        tg="EME";
        Caja_PC_Registro CPC = new Caja_PC_Registro();
        CPC.VerificarExistencia(PrincipalMDI.lblUsu.getText(),"EME",tbEME);
        if(tbEME.getRowCount()==0){
            Caja_Registro frmPC = new Caja_Registro();
            frmPC.setVisible(true);
            frmPC.lblUsuario.setText(PrincipalMDI.lblUsu.getText());
        }else   if(tbEME.getRowCount()>0){
            //                    Caja_Pagos CP = new Caja_Pagos();
            //                    CP.setVisible(true);
            //                    String u=PrincipalMDI.lblUsu.getText();
            //                    CP.lblusu.setText(u);
            EME_PRINCIPAL f=new EME_PRINCIPAL();
        panelFACTURAS.add(f);
        //        String u=Principal.lblUsu.getText();
        f.setVisible(true);
        try {
            f.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
        }

    }//GEN-LAST:event_btnAdmEmeActionPerformed

    private void btnAdmEmeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmEmeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdmEmeMouseEntered

    private void btnLaboratorio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaboratorio2ActionPerformed
        CPP_PRINCIPAL f=new CPP_PRINCIPAL();
        panelFACTURAS.add(f);
        //        String u=Principal.lblUsu.getText();

        f.setVisible(true);
        try {
            f.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnLaboratorio2ActionPerformed

    private void btnCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCajaActionPerformed
        //        ImageIcon aperturar=new ImageIcon(this.getClass().getResource("/imagenes/iconos/icons8-Meter en caja fuerte-80.png"));
        //        ImageIcon cerrada=new ImageIcon(this.getClass().getResource("/imagenes/iconos/icons8-Caja fuerte-90.png"));

        Caja_PC_Registro CPC = new Caja_PC_Registro();
        Caja_AperturaCierre CA =new Caja_AperturaCierre();
        Caja_AperturaCierre CAID =new Caja_AperturaCierre();
        Caja_AperturaCierre CAS =new Caja_AperturaCierre();
        Caja_AperturaCierre CAO =new Caja_AperturaCierre();
        Caja_AperturaCierre CAOD =new Caja_AperturaCierre();
        Caja_AperturaCierre CAOU =new Caja_AperturaCierre();
        CPC.VerificarExistencia(PrincipalMDI.lblUsu.getText(),"CC",tbPC);
        CA.Caja_Verificar_Apertura(PrincipalMDI.lblUsu.getText(),tbAPERTURA);
        CAID.CajaID_SESION(PrincipalMDI.lblUsu.getText());
        CAS.Caja_Verificar_SESIONES(PrincipalMDI.lblUsu.getText(),tbSESIONES);
        CAO.Caja_Verificar_SESIONES_OTRA_PC(PrincipalMDI.lblUsu.getText(),tbAPERTURA_OTRAPC);
        CAOD.Caja_Verificar_SESIONES_OTRA_PC(PrincipalMDI.lblUsu.getText(),tbAPERTURA_OTRAPC1);
        CAOU.Caja_Verificar_ULTIMO_CIERRE(PrincipalMDI.lblUsu.getText(),tbULTIMA_SESION);

        if(tbPC.getRowCount()==0){
            Caja_Registro frmPC = new Caja_Registro();
            frmPC.setVisible(true);
            frmPC.lblUsuario.setText(PrincipalMDI.lblUsu.getText());
        }else   if(tbPC.getRowCount()>0){
            if(tbSESIONES.getRowCount()==0){
                if(tbULTIMA_SESION.getRowCount()==0){
                    if(tbAPERTURA_OTRAPC.getRowCount()==0){
                        if(tbAPERTURA.getRowCount()==0){
                            //                                    btnCaja.setPressedIcon(cerrada);
                            Caja_Apertura frmPCAPERTURA = new Caja_Apertura();
                            frmPCAPERTURA.setVisible(true);
                            frmPCAPERTURA.lblusu.setText(PrincipalMDI.lblUsu.getText());
                        }else if(tbAPERTURA.getRowCount()>0){
                            //                                        btnCaja.setPressedIcon(aperturar);
//                            jPanel6.setBackground(new Color(0,122,204)); 
                            jTabbedPane1.setSelectedIndex(2);
                        }
                        System.out.println("Bienvenido");
                    }else if(tbAPERTURA_OTRAPC.getRowCount()>0){
                        //                                        btnCaja.setPressedIcon(cerrada);
                        AperturaOraPC.setVisible(true);
                        tbAPERTURA_OTRAPC1.getSelectionModel().setSelectionInterval (0,0) ;
                    }
                }if(tbULTIMA_SESION.getRowCount()>0){
                    //                            btnCaja.setPressedIcon(cerrada);
                    NivelSuperior.setUndecorated(true);
                    NivelSuperior.setVisible(true);
                }
            }else if(tbSESIONES.getRowCount()>0){
                //                            btnCaja.setPressedIcon(cerrada);
                ErrorExistente.setVisible(true);
            }
        }

    }//GEN-LAST:event_btnCajaActionPerformed

    private void btnCajaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCajaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCajaMouseEntered

    private void btnCajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCajaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCajaMouseClicked

    private void btnCostosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCostosActionPerformed
tg="CST";
//        Caja_PC_Registro CPC = new Caja_PC_Registro();
//        CPC.VerificarExistencia(PrincipalMDI.lblUsu.getText(),"LAB",tbPC);
//        if(tbPC.getRowCount()==0){
//            Caja_Registro frmPC = new Caja_Registro();
//            frmPC.setVisible(true);
//            frmPC.lblUsuario.setText(PrincipalMDI.lblUsu.getText());
//        }else   if(tbPC.getRowCount()>0){
            CS_PRINCIPAL f=new CS_PRINCIPAL();
            panelFACTURAS.add(f);
            //        String u=Principal.lblUsu.getText();
            f.setVisible(true);
            try {
                f.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTabbedPane1.setSelectedIndex(1);
//        }
    }//GEN-LAST:event_btnCostosActionPerformed


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
            java.util.logging.Logger.getLogger(PrincipalMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalMDI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AperturaOraPC;
    public static javax.swing.JDialog ErrorCajaCerrada;
    private javax.swing.JDialog ErrorExistente;
    private javax.swing.JDialog NivelSuperior;
    public static javax.swing.JButton btnAdmCentral;
    public static javax.swing.JButton btnAdmEme;
    private javax.swing.JButton btnAlertConsulta10;
    private javax.swing.JButton btnAlertConsulta7;
    private javax.swing.JButton btnAlertConsulta9;
    private javax.swing.JButton btnAnularVenta;
    public static javax.swing.JButton btnCaja;
    public static javax.swing.JButton btnConExt;
    public static javax.swing.JButton btnCostos;
    public static javax.swing.JButton btnEcografia;
    public static javax.swing.JButton btnHospitalizacion;
    public static javax.swing.JButton btnLaboratorio;
    public static javax.swing.JButton btnLaboratorio2;
    public static javax.swing.JButton btnPersonal;
    public static javax.swing.JButton btnPersonal1;
    public static javax.swing.JButton btnRayosX;
    public static javax.swing.JButton btnVentas;
    public static javax.swing.JButton btnVentas1;
    public static javax.swing.JButton btnVentas10;
    public static javax.swing.JButton btnVentas2;
    public static javax.swing.JButton btnVentas3;
    public static javax.swing.JButton btnVentas4;
    public static javax.swing.JButton btnVentas5;
    public static javax.swing.JButton btnVentas6;
    public static javax.swing.JButton btnVentas7;
    public static javax.swing.JButton btnVentas8;
    public static javax.swing.JButton btnVentas9;
    public static javax.swing.JLabel ibiIDAPERTURA;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JPanel jPanel104;
    private javax.swing.JPanel jPanel105;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel134;
    private javax.swing.JPanel jPanel135;
    private javax.swing.JPanel jPanel138;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel141;
    private javax.swing.JPanel jPanel143;
    private javax.swing.JPanel jPanel144;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JScrollPane jScrollPane6;
    public static javax.swing.JScrollPane jScrollPane7;
    public static javax.swing.JScrollPane jScrollPane8;
    public static javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAd1;
    private javax.swing.JLabel lblAd2;
    private javax.swing.JLabel lblAd3;
    public static javax.swing.JLabel lblIDSESION;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JDesktopPane panelFACTURAS;
    public static javax.swing.JTable tbAPERTURA;
    public static javax.swing.JTable tbAPERTURA_OTRAPC;
    public static javax.swing.JTable tbAPERTURA_OTRAPC1;
    public static javax.swing.JTable tbEME;
    public static javax.swing.JTable tbPC;
    public static javax.swing.JTable tbSESIONES;
    public static javax.swing.JTable tbULTIMA_SESION;
    public static javax.swing.JPasswordField txtContra;
    public static javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
