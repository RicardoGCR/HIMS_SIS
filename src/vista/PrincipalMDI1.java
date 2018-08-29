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
import vista.ConsultorioEx.Consultorio;
import vista.ConsultorioEx.ConsultorioAsignacion;
import vista.ConsultorioEx.ConsultorioExt;
import vista.ConsultorioEx.Triaje;
import vista.EC.EC_BUSCAR_EXAMEN_CAJA;
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
import vista.admisionEmergencia.AdmEmer_Registro;
import vista.admisionEmergencia.FrmFormatoEmergenciaCabecera;
import vista.admisionEmergencia.FrmFormatoEmergenciaTopico;
import vista.admisionEmergencia.FrmFormatoEmergenciaTriaje;
import vista.cuentaPorPagar.BoletaElectronica;
import vista.cuentaPorPagar.ComunicacionBaja;
import vista.cuentaPorPagar.FacturadorPagos;
import vista.cuentaPorPagar.NotasCreditoDebito;
import vista.cuentaPorPagar.ReporteNCND;
import vista.cuentaPorPagar.VentasConsolidado;

/**
 *
 * @author USUARIO
 */
public class PrincipalMDI1 extends javax.swing.JFrame {
static int contador =0;
int velocidad=1;
    Conexion con = new Conexion();
    public PrincipalMDI1() {
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
         jTabbedPane1.setEnabledAt(3, false);
         jTabbedPane1.setEnabledAt(4,false);
         jTabbedPane1.setEnabledAt(5, false);
         jTabbedPane1.setEnabledAt(6,false);
         jTabbedPane1.setEnabledAt(7, false);
         jTabbedPane1.setEnabledAt(8,false);
         jTabbedPane1.setEnabledAt(9, false);
         jTabbedPane1.setEnabledAt(10,false);
         jTabbedPane1.setEnabledAt(11, false);
         jTabbedPane1.setEnabledAt(12, false);
         jTabbedPane1.setEnabledAt(13,false);
         jTabbedPane1.setEnabledAt(14, false);
         jTabbedPane1.setEnabledAt(15,false);
//         jTabbedPane1.setEnabledAt(16, false);

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
               lblSe_ID.setText(r.getString(1));
               lblServicio.setText(r.getString(2));
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
        btnEcografia1 = new javax.swing.JButton();
        btnRayosX = new javax.swing.JButton();
        btnLaboratorio = new javax.swing.JButton();
        btnConExt = new javax.swing.JButton();
        btnInvBact = new javax.swing.JButton();
        btnHospitalizacion = new javax.swing.JButton();
        btnAdmCentral = new javax.swing.JButton();
        btnEcografia = new javax.swing.JButton();
        btnEcografia2 = new javax.swing.JButton();
        btnPersonal1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btneditar5 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        btneditar6 = new javax.swing.JButton();
        jPanel77 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        btneditar8 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        btneditar9 = new javax.swing.JButton();
        jPanel76 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        btnguardar8 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        btnguardar9 = new javax.swing.JButton();
        jPanel68 = new javax.swing.JPanel();
        btneditar4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel72 = new javax.swing.JPanel();
        btneditar7 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        lblUsu6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnPaciente = new javax.swing.JButton();
        btnSectorizacion = new javax.swing.JButton();
        btnReportMovinientoHC = new javax.swing.JButton();
        btnReportSeguimientoHC = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        btneditar11 = new javax.swing.JButton();
        jPanel39 = new javax.swing.JPanel();
        btnMuestras = new javax.swing.JButton();
        jPanel41 = new javax.swing.JPanel();
        btnEsquema = new javax.swing.JButton();
        jPanel43 = new javax.swing.JPanel();
        btnValores = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        btnUnidadMedi = new javax.swing.JButton();
        jPanel40 = new javax.swing.JPanel();
        btnAnalisis = new javax.swing.JButton();
        jPanel42 = new javax.swing.JPanel();
        btnClasificacion = new javax.swing.JButton();
        jPanel44 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        btnContenedor = new javax.swing.JButton();
        jPanel75 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jPanel78 = new javax.swing.JPanel();
        btnTM = new javax.swing.JButton();
        jPanel79 = new javax.swing.JPanel();
        btnTMBusqueda = new javax.swing.JButton();
        jPanel80 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jPanel81 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        btnResultado = new javax.swing.JButton();
        jPanel82 = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        btnResultadoBusq = new javax.swing.JButton();
        jPanel83 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        btnEntrega = new javax.swing.JButton();
        jPanel84 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        btnConversion = new javax.swing.JButton();
        jPanel85 = new javax.swing.JPanel();
        btnSolicitudRecep = new javax.swing.JButton();
        jPanel46 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPC = new javax.swing.JTable();
        ibiIDAPERTURA = new javax.swing.JLabel();
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
        lblUsu2 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        lblIDSESION = new javax.swing.JLabel();
        jPanel73 = new javax.swing.JPanel();
        btnVentas4 = new javax.swing.JButton();
        btnVentas10 = new javax.swing.JButton();
        btnVentas11 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel57 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jPanel60 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        btnguardar24 = new javax.swing.JButton();
        jPanel62 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        btnguardar26 = new javax.swing.JButton();
        jPanel63 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        btnguardar27 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lblServicio = new javax.swing.JLabel();
        lblSe_ID = new javax.swing.JLabel();
        jPanel65 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jPanel88 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jPanel95 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jPanel96 = new javax.swing.JPanel();
        jPanel58 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel67 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jPanel97 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel98 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel99 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel100 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jPanel101 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jPanel102 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanel66 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        lblUsu4 = new javax.swing.JLabel();
        btnReportCabecera1 = new javax.swing.JButton();
        btnReporTopico1 = new javax.swing.JButton();
        btnReportTriaje1 = new javax.swing.JButton();
        btnMantLlegada2 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        btnSolicitudInv = new javax.swing.JButton();
        jPanel86 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        lblUsu5 = new javax.swing.JLabel();
        btnTriaje = new javax.swing.JButton();
        btnConsultorio = new javax.swing.JButton();
        btnAdmEme2 = new javax.swing.JButton();
        btnAdmEme1 = new javax.swing.JButton();
        btnConsultorio1 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        panelFACTURAS = new javax.swing.JDesktopPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel136 = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        jPanel137 = new javax.swing.JPanel();
        jPanel140 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        jPanel139 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel132 = new javax.swing.JPanel();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jPanel133 = new javax.swing.JPanel();
        jPanel147 = new javax.swing.JPanel();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jPanel148 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        lblUsu3 = new javax.swing.JLabel();
        jPanel149 = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        lblusu10 = new javax.swing.JLabel();
        jPanel111 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jPanel126 = new javax.swing.JPanel();
        jPanel112 = new javax.swing.JPanel();
        jButton19 = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jButton20 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel121 = new javax.swing.JPanel();
        jPanel122 = new javax.swing.JPanel();
        btneditar21 = new javax.swing.JButton();
        btneditar22 = new javax.swing.JButton();
        btneditar23 = new javax.swing.JButton();
        btneditar25 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        lblUsu1 = new javax.swing.JLabel();
        btneditar26 = new javax.swing.JButton();
        btneditar27 = new javax.swing.JButton();
        btneditar28 = new javax.swing.JButton();
        jPanel142 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
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
        setTitle("Sistema de Gestion Hospitalaria - Modulo Administracion del Sistema");

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
        btnPersonal.setVerifyInputWhenFocusTarget(false);
        btnPersonal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalActionPerformed(evt);
            }
        });

        btnEcografia1.setBackground(new java.awt.Color(102, 102, 102));
        btnEcografia1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnEcografia1.setForeground(new java.awt.Color(51, 51, 51));
        btnEcografia1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-microorganisms-64.png"))); // NOI18N
        btnEcografia1.setText("<HTML>Bacteriología<BR> TB <HTML>");
        btnEcografia1.setContentAreaFilled(false);
        btnEcografia1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEcografia1.setFocusPainted(false);
        btnEcografia1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEcografia1.setIconTextGap(30);
        btnEcografia1.setPreferredSize(new java.awt.Dimension(155, 155));
        btnEcografia1.setVerifyInputWhenFocusTarget(false);
        btnEcografia1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEcografia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEcografia1ActionPerformed(evt);
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
        btnConExt.setVerifyInputWhenFocusTarget(false);
        btnConExt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConExt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConExtActionPerformed(evt);
            }
        });

        btnInvBact.setBackground(new java.awt.Color(102, 102, 102));
        btnInvBact.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnInvBact.setForeground(new java.awt.Color(51, 51, 51));
        btnInvBact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-molecule-64.png"))); // NOI18N
        btnInvBact.setText("<HTML>Investigación<BR>Bactereologica<HTML>");
        btnInvBact.setContentAreaFilled(false);
        btnInvBact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInvBact.setFocusPainted(false);
        btnInvBact.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnInvBact.setIconTextGap(30);
        btnInvBact.setVerifyInputWhenFocusTarget(false);
        btnInvBact.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnInvBact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInvBactActionPerformed(evt);
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
        btnEcografia.setVerifyInputWhenFocusTarget(false);
        btnEcografia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEcografia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEcografiaActionPerformed(evt);
            }
        });

        btnEcografia2.setBackground(new java.awt.Color(102, 102, 102));
        btnEcografia2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnEcografia2.setForeground(new java.awt.Color(51, 51, 51));
        btnEcografia2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-test-tube-64.png"))); // NOI18N
        btnEcografia2.setText("Programas PPR ");
        btnEcografia2.setContentAreaFilled(false);
        btnEcografia2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEcografia2.setFocusPainted(false);
        btnEcografia2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEcografia2.setIconTextGap(30);
        btnEcografia2.setVerifyInputWhenFocusTarget(false);
        btnEcografia2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEcografia2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEcografia2ActionPerformed(evt);
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
        btnPersonal1.setVerifyInputWhenFocusTarget(false);
        btnPersonal1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPersonal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonal1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnEcografia1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRayosX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnLaboratorio2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAdmEme, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnConExt, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnInvBact, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnHospitalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdmCentral, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEcografia2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEcografia, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPersonal1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 655, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLaboratorio2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdmEme, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnEcografia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRayosX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConExt, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInvBact, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnEcografia2, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addComponent(btnHospitalizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addComponent(btnAdmCentral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEcografia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnPersonal1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 355, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inicio", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(95, 106, 112));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Depreciación");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        btneditar5.setForeground(new java.awt.Color(240, 240, 240));
        btneditar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Dinámica negativa-100.png"))); // NOI18N
        btneditar5.setContentAreaFilled(false);
        btneditar5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar5.setIconTextGap(30);
        btneditar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btneditar5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btneditar5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(95, 106, 112));
        jPanel12.setForeground(new java.awt.Color(255, 153, 0));

        btneditar6.setForeground(new java.awt.Color(240, 240, 240));
        btneditar6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Servicios-100.png"))); // NOI18N
        btneditar6.setContentAreaFilled(false);
        btneditar6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar6.setIconTextGap(30);
        btneditar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar6ActionPerformed(evt);
            }
        });

        jPanel77.setBackground(new java.awt.Color(243, 112, 66));

        javax.swing.GroupLayout jPanel77Layout = new javax.swing.GroupLayout(jPanel77);
        jPanel77.setLayout(jPanel77Layout);
        jPanel77Layout.setHorizontalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel77Layout.setVerticalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Subservicios");
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btneditar6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btneditar6, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
            .addComponent(jPanel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel18.setBackground(new java.awt.Color(95, 106, 112));
        jPanel18.setForeground(new java.awt.Color(255, 102, 0));

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Estimacion de costos");

        btneditar8.setForeground(new java.awt.Color(240, 240, 240));
        btneditar8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Dinero-100.png"))); // NOI18N
        btneditar8.setContentAreaFilled(false);
        btneditar8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar8.setIconTextGap(30);
        btneditar8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btneditar8MouseClicked(evt);
            }
        });
        btneditar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
            .addComponent(btneditar8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btneditar8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(95, 106, 112));
        jPanel21.setForeground(new java.awt.Color(255, 153, 0));

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Factores de Produccion");
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });

        btneditar9.setBackground(new java.awt.Color(255, 255, 153));
        btneditar9.setForeground(new java.awt.Color(255, 255, 102));
        btneditar9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Fábrica-100.png"))); // NOI18N
        btneditar9.setContentAreaFilled(false);
        btneditar9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar9.setIconTextGap(30);
        btneditar9.setVerifyInputWhenFocusTarget(false);
        btneditar9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar9ActionPerformed(evt);
            }
        });

        jPanel76.setBackground(new java.awt.Color(243, 112, 66));

        javax.swing.GroupLayout jPanel76Layout = new javax.swing.GroupLayout(jPanel76);
        jPanel76.setLayout(jPanel76Layout);
        jPanel76Layout.setHorizontalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel76Layout.setVerticalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Sub Servicios");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btneditar9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btneditar9, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(243, 112, 66));

        btnguardar8.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnguardar8.setForeground(new java.awt.Color(240, 240, 240));
        btnguardar8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-47.png"))); // NOI18N
        btnguardar8.setText("<html>Buscar estimacion de costos</html>");
        btnguardar8.setContentAreaFilled(false);
        btnguardar8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnguardar8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnguardar8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnguardar8.setIconTextGap(30);
        btnguardar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnguardar8)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnguardar8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel20.setBackground(new java.awt.Color(243, 112, 66));

        btnguardar9.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnguardar9.setForeground(new java.awt.Color(240, 240, 240));
        btnguardar9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Ahorro de libro-50.png"))); // NOI18N
        btnguardar9.setText("Costos de Nomenclaturas");
        btnguardar9.setContentAreaFilled(false);
        btnguardar9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnguardar9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnguardar9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnguardar9.setIconTextGap(30);
        btnguardar9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnguardar9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnguardar9, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel68.setBackground(new java.awt.Color(95, 106, 112));
        jPanel68.setForeground(new java.awt.Color(255, 153, 0));

        btneditar4.setForeground(new java.awt.Color(240, 240, 240));
        btneditar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Producto-100.png"))); // NOI18N
        btneditar4.setContentAreaFilled(false);
        btneditar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar4.setIconTextGap(30);
        btneditar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar4ActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Producto Referencial");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btneditar4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btneditar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel72.setBackground(new java.awt.Color(95, 106, 112));
        jPanel72.setForeground(new java.awt.Color(255, 153, 0));

        btneditar7.setForeground(new java.awt.Color(240, 240, 240));
        btneditar7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Regla-100.png"))); // NOI18N
        btneditar7.setContentAreaFilled(false);
        btneditar7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar7.setIconTextGap(30);
        btneditar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar7ActionPerformed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Unidad de Medida");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
        jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btneditar7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel72Layout.setVerticalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btneditar7, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Costos", jPanel3);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        jPanel24.setBackground(new java.awt.Color(23, 160, 134));

        lblUsu6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        lblUsu6.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/B9.png"))); // NOI18N
        lblUsu6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblUsu6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblUsu6.setIconTextGap(40);
        lblUsu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsu6MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Admisión");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(lblUsu6)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(0, 624, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblUsu6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnPaciente.setBackground(new java.awt.Color(255, 255, 153));
        btnPaciente.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnPaciente.setForeground(new java.awt.Color(51, 51, 51));
        btnPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-wheelchair-64.png"))); // NOI18N
        btnPaciente.setText("Pacientes");
        btnPaciente.setToolTipText("");
        btnPaciente.setContentAreaFilled(false);
        btnPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPaciente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPaciente.setIconTextGap(30);
        btnPaciente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacienteActionPerformed(evt);
            }
        });

        btnSectorizacion.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnSectorizacion.setForeground(new java.awt.Color(51, 51, 51));
        btnSectorizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-map-64.png"))); // NOI18N
        btnSectorizacion.setText("Sectorización");
        btnSectorizacion.setToolTipText("");
        btnSectorizacion.setContentAreaFilled(false);
        btnSectorizacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSectorizacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSectorizacion.setIconTextGap(30);
        btnSectorizacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSectorizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSectorizacionActionPerformed(evt);
            }
        });

        btnReportMovinientoHC.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnReportMovinientoHC.setForeground(new java.awt.Color(51, 51, 51));
        btnReportMovinientoHC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-book-64.png"))); // NOI18N
        btnReportMovinientoHC.setText("<html>Movimiento de<br> Historias</html>");
        btnReportMovinientoHC.setContentAreaFilled(false);
        btnReportMovinientoHC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportMovinientoHC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReportMovinientoHC.setIconTextGap(30);
        btnReportMovinientoHC.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnReportMovinientoHC.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReportMovinientoHC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportMovinientoHCActionPerformed(evt);
            }
        });

        btnReportSeguimientoHC.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnReportSeguimientoHC.setForeground(new java.awt.Color(51, 51, 51));
        btnReportSeguimientoHC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-view-64.png"))); // NOI18N
        btnReportSeguimientoHC.setText("<html>Seguimiento de<br> Historias</html>");
        btnReportSeguimientoHC.setContentAreaFilled(false);
        btnReportSeguimientoHC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportSeguimientoHC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReportSeguimientoHC.setIconTextGap(30);
        btnReportSeguimientoHC.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReportSeguimientoHC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportSeguimientoHCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(btnPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSectorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReportMovinientoHC, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReportSeguimientoHC, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSectorizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(706, 706, 706))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnReportMovinientoHC)
                            .addComponent(btnReportSeguimientoHC))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Adm", jPanel22);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        jPanel30.setBackground(new java.awt.Color(95, 106, 112));
        jPanel30.setForeground(new java.awt.Color(255, 102, 0));

        btneditar11.setFont(new java.awt.Font("Segoe UI Light", 0, 26)); // NOI18N
        btneditar11.setForeground(new java.awt.Color(240, 240, 240));
        btneditar11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Mantenimiento-70.png"))); // NOI18N
        btneditar11.setMnemonic('N');
        btneditar11.setText("Mantenimientos");
        btneditar11.setToolTipText("");
        btneditar11.setContentAreaFilled(false);
        btneditar11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btneditar11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar11.setIconTextGap(30);
        btneditar11.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btneditar11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar11ActionPerformed(evt);
            }
        });

        jPanel39.setBackground(new java.awt.Color(0, 119, 178));

        btnMuestras.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        btnMuestras.setForeground(new java.awt.Color(240, 240, 240));
        btnMuestras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/probeta (2).png"))); // NOI18N
        btnMuestras.setText("Muestras");
        btnMuestras.setToolTipText("");
        btnMuestras.setContentAreaFilled(false);
        btnMuestras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMuestras.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMuestras.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnMuestras.setIconTextGap(15);
        btnMuestras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuestrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMuestras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMuestras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
        );

        jPanel41.setBackground(new java.awt.Color(0, 119, 178));

        btnEsquema.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        btnEsquema.setForeground(new java.awt.Color(240, 240, 240));
        btnEsquema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Vista general 3-50.png"))); // NOI18N
        btnEsquema.setText("<html>Esquema de<br>Resultados</html>");
        btnEsquema.setToolTipText("");
        btnEsquema.setContentAreaFilled(false);
        btnEsquema.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEsquema.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEsquema.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEsquema.setIconTextGap(30);
        btnEsquema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsquemaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEsquema, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEsquema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel43.setBackground(new java.awt.Color(0, 119, 178));

        btnValores.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        btnValores.setForeground(new java.awt.Color(240, 240, 240));
        btnValores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Alinear a la derecha-50.png"))); // NOI18N
        btnValores.setText("<html>Valores<br>Referenciales</html>");
        btnValores.setToolTipText("");
        btnValores.setContentAreaFilled(false);
        btnValores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnValores.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnValores.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnValores.setIconTextGap(30);
        btnValores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnValores, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnValores, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel32.setBackground(new java.awt.Color(0, 119, 178));

        btnUnidadMedi.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        btnUnidadMedi.setForeground(new java.awt.Color(240, 240, 240));
        btnUnidadMedi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Vista general 2-50.png"))); // NOI18N
        btnUnidadMedi.setText(" Unidad de Medida");
        btnUnidadMedi.setToolTipText("");
        btnUnidadMedi.setContentAreaFilled(false);
        btnUnidadMedi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUnidadMedi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUnidadMedi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnUnidadMedi.setIconTextGap(5);
        btnUnidadMedi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnidadMediActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnUnidadMedi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnUnidadMedi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel40.setBackground(new java.awt.Color(0, 119, 178));

        btnAnalisis.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        btnAnalisis.setForeground(new java.awt.Color(240, 240, 240));
        btnAnalisis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Tipo de archivo de libro genérico-50.png"))); // NOI18N
        btnAnalisis.setText(" Análisis - Examen");
        btnAnalisis.setToolTipText("");
        btnAnalisis.setContentAreaFilled(false);
        btnAnalisis.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnalisis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAnalisis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAnalisis.setIconTextGap(10);
        btnAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalisisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addComponent(btnAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel42.setBackground(new java.awt.Color(0, 119, 178));

        btnClasificacion.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        btnClasificacion.setForeground(new java.awt.Color(240, 240, 240));
        btnClasificacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documentos-50.png"))); // NOI18N
        btnClasificacion.setText(" Clasificación");
        btnClasificacion.setToolTipText("");
        btnClasificacion.setContentAreaFilled(false);
        btnClasificacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClasificacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClasificacion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnClasificacion.setIconTextGap(15);
        btnClasificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClasificacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClasificacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClasificacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel44.setBackground(new java.awt.Color(0, 119, 178));

        jLabel57.setBackground(new java.awt.Color(255, 255, 255));
        jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 19)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText(" Contenedor");
        jLabel57.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel57MouseClicked(evt);
            }
        });

        btnContenedor.setForeground(new java.awt.Color(240, 240, 240));
        btnContenedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/contenedor.png"))); // NOI18N
        btnContenedor.setMnemonic('N');
        btnContenedor.setToolTipText("");
        btnContenedor.setContentAreaFilled(false);
        btnContenedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnContenedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnContenedor.setIconTextGap(30);
        btnContenedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContenedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
            .addComponent(btnContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btneditar11, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel30Layout.createSequentialGroup()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btneditar11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel75.setBackground(new java.awt.Color(95, 106, 112));
        jPanel75.setForeground(new java.awt.Color(255, 153, 0));

        jLabel88.setBackground(new java.awt.Color(255, 255, 255));
        jLabel88.setFont(new java.awt.Font("Segoe UI Light", 0, 25)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Bacteria Filled-100.png"))); // NOI18N
        jLabel88.setText("Toma de Muestra");
        jLabel88.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel88.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel88.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jPanel78.setBackground(new java.awt.Color(0, 119, 178));

        btnTM.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        btnTM.setForeground(new java.awt.Color(240, 240, 240));
        btnTM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/probeta (2).png"))); // NOI18N
        btnTM.setText(" Toma de Muestra");
        btnTM.setToolTipText("");
        btnTM.setContentAreaFilled(false);
        btnTM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTM.setIconTextGap(15);
        btnTM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel78Layout = new javax.swing.GroupLayout(jPanel78);
        jPanel78.setLayout(jPanel78Layout);
        jPanel78Layout.setHorizontalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel78Layout.setVerticalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel78Layout.createSequentialGroup()
                .addComponent(btnTM, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel79.setBackground(new java.awt.Color(0, 119, 178));

        btnTMBusqueda.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        btnTMBusqueda.setForeground(new java.awt.Color(240, 240, 240));
        btnTMBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-47.png"))); // NOI18N
        btnTMBusqueda.setText("<html> Búsqueda de Toma de<br>Muestras</html>");
        btnTMBusqueda.setToolTipText("");
        btnTMBusqueda.setContentAreaFilled(false);
        btnTMBusqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTMBusqueda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTMBusqueda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTMBusqueda.setIconTextGap(15);
        btnTMBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTMBusquedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel79Layout = new javax.swing.GroupLayout(jPanel79);
        jPanel79.setLayout(jPanel79Layout);
        jPanel79Layout.setHorizontalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTMBusqueda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
        );
        jPanel79Layout.setVerticalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTMBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel75Layout = new javax.swing.GroupLayout(jPanel75);
        jPanel75.setLayout(jPanel75Layout);
        jPanel75Layout.setHorizontalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel75Layout.createSequentialGroup()
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel75Layout.setVerticalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel75Layout.createSequentialGroup()
                .addComponent(jPanel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(jPanel79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel80.setBackground(new java.awt.Color(95, 106, 112));
        jPanel80.setForeground(new java.awt.Color(255, 153, 0));

        jLabel95.setBackground(new java.awt.Color(255, 255, 255));
        jLabel95.setFont(new java.awt.Font("Segoe UI Light", 0, 25)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Microscopio-100.png"))); // NOI18N
        jLabel95.setText("Resultados");
        jLabel95.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel95.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jPanel81.setBackground(new java.awt.Color(0, 119, 178));

        jLabel96.setBackground(new java.awt.Color(255, 255, 255));
        jLabel96.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(255, 255, 255));
        jLabel96.setText(" Resultados");
        jLabel96.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel96.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel96MouseClicked(evt);
            }
        });

        btnResultado.setForeground(new java.awt.Color(240, 240, 240));
        btnResultado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/ExamenFisico-50.png"))); // NOI18N
        btnResultado.setMnemonic('N');
        btnResultado.setToolTipText("");
        btnResultado.setContentAreaFilled(false);
        btnResultado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnResultado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnResultado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnResultado.setIconTextGap(30);
        btnResultado.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResultadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel81Layout = new javax.swing.GroupLayout(jPanel81);
        jPanel81.setLayout(jPanel81Layout);
        jPanel81Layout.setHorizontalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel96, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnResultado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel81Layout.setVerticalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel81Layout.createSequentialGroup()
                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel82.setBackground(new java.awt.Color(0, 119, 178));

        jLabel97.setBackground(new java.awt.Color(255, 255, 255));
        jLabel97.setFont(new java.awt.Font("Segoe UI Light", 0, 19)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setText(" Búsqueda de Resultados");
        jLabel97.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel97.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel97MouseClicked(evt);
            }
        });

        btnResultadoBusq.setForeground(new java.awt.Color(240, 240, 240));
        btnResultadoBusq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-47.png"))); // NOI18N
        btnResultadoBusq.setMnemonic('N');
        btnResultadoBusq.setToolTipText("");
        btnResultadoBusq.setContentAreaFilled(false);
        btnResultadoBusq.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnResultadoBusq.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnResultadoBusq.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnResultadoBusq.setIconTextGap(30);
        btnResultadoBusq.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnResultadoBusq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResultadoBusqActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel82Layout = new javax.swing.GroupLayout(jPanel82);
        jPanel82.setLayout(jPanel82Layout);
        jPanel82Layout.setHorizontalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnResultadoBusq, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel97, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
        );
        jPanel82Layout.setVerticalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel82Layout.createSequentialGroup()
                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnResultadoBusq, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel83.setBackground(new java.awt.Color(0, 119, 178));

        jLabel98.setBackground(new java.awt.Color(255, 255, 255));
        jLabel98.setFont(new java.awt.Font("Segoe UI Light", 0, 19)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(255, 255, 255));
        jLabel98.setText(" Entrega de Resultados");
        jLabel98.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel98.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel98MouseClicked(evt);
            }
        });

        btnEntrega.setForeground(new java.awt.Color(240, 240, 240));
        btnEntrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Vista general 2-50.png"))); // NOI18N
        btnEntrega.setMnemonic('N');
        btnEntrega.setToolTipText("");
        btnEntrega.setContentAreaFilled(false);
        btnEntrega.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntrega.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnEntrega.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEntrega.setIconTextGap(30);
        btnEntrega.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel83Layout = new javax.swing.GroupLayout(jPanel83);
        jPanel83.setLayout(jPanel83Layout);
        jPanel83Layout.setHorizontalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEntrega, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel98, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel83Layout.setVerticalGroup(
            jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel83Layout.createSequentialGroup()
                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel80Layout = new javax.swing.GroupLayout(jPanel80);
        jPanel80.setLayout(jPanel80Layout);
        jPanel80Layout.setHorizontalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel82, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel81, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel80Layout.setVerticalGroup(
            jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel80Layout.createSequentialGroup()
                .addComponent(jPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel82, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(jPanel83, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel95, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel84.setBackground(new java.awt.Color(95, 106, 112));
        jPanel84.setForeground(new java.awt.Color(255, 153, 0));

        jLabel99.setBackground(new java.awt.Color(255, 255, 255));
        jLabel99.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel99.setText("Conversión");
        jLabel99.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel99MouseClicked(evt);
            }
        });

        btnConversion.setForeground(new java.awt.Color(240, 240, 240));
        btnConversion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Tubo de ensayo-100.png"))); // NOI18N
        btnConversion.setMnemonic('N');
        btnConversion.setToolTipText("");
        btnConversion.setContentAreaFilled(false);
        btnConversion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConversion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConversion.setIconTextGap(30);
        btnConversion.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnConversion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConversionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel84Layout = new javax.swing.GroupLayout(jPanel84);
        jPanel84.setLayout(jPanel84Layout);
        jPanel84Layout.setHorizontalGroup(
            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel84Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConversion)
                .addContainerGap())
        );
        jPanel84Layout.setVerticalGroup(
            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel84Layout.createSequentialGroup()
                .addComponent(btnConversion, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel85.setBackground(new java.awt.Color(95, 106, 112));
        jPanel85.setForeground(new java.awt.Color(255, 153, 0));

        btnSolicitudRecep.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        btnSolicitudRecep.setForeground(new java.awt.Color(240, 240, 240));
        btnSolicitudRecep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/recepcion.png"))); // NOI18N
        btnSolicitudRecep.setText("<html>Solicitud de investigación<br>Bacteriológica</html>");
        btnSolicitudRecep.setContentAreaFilled(false);
        btnSolicitudRecep.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSolicitudRecep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSolicitudRecep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSolicitudRecep.setIconTextGap(15);
        btnSolicitudRecep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitudRecepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel85Layout = new javax.swing.GroupLayout(jPanel85);
        jPanel85.setLayout(jPanel85Layout);
        jPanel85Layout.setHorizontalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSolicitudRecep, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel85Layout.setVerticalGroup(
            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel85Layout.createSequentialGroup()
                .addComponent(btnSolicitudRecep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                                .addComponent(jPanel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2)
                                .addComponent(jPanel85, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jPanel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel80, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(273, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lab", jPanel28);

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

        ibiIDAPERTURA.setForeground(new java.awt.Color(255, 255, 255));
        ibiIDAPERTURA.setText("jLabel48");

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

        jPanel11.setBackground(new java.awt.Color(41, 127, 184));

        lblUsu2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        lblUsu2.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/B2.png"))); // NOI18N
        lblUsu2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblUsu2.setIconTextGap(40);
        lblUsu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsu2MouseClicked(evt);
            }
        });

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
                .addContainerGap()
                .addComponent(btnVentas4, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanel73Layout.setVerticalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVentas4)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(lblUsu2)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIDSESION, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIDSESION, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblUsu2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
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

        btnVentas11.setBackground(new java.awt.Color(102, 102, 102));
        btnVentas11.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnVentas11.setForeground(new java.awt.Color(51, 51, 51));
        btnVentas11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Doctors Bag-64.png"))); // NOI18N
        btnVentas11.setText("Farmacia");
        btnVentas11.setContentAreaFilled(false);
        btnVentas11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVentas11.setFocusPainted(false);
        btnVentas11.setFocusable(false);
        btnVentas11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas11.setIconTextGap(30);
        btnVentas11.setVerifyInputWhenFocusTarget(false);
        btnVentas11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentas11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVentas11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVentas11MouseEntered(evt);
            }
        });
        btnVentas11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentas11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnVentas8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVentas9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnVentas5, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(btnVentas1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(btnVentas10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnVentas2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVentas6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVentas11, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addComponent(btnVentas3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(ibiIDAPERTURA))
                            .addComponent(btnVentas7, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(717, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnVentas1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnVentas2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnVentas3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnVentas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(ibiIDAPERTURA)))
                        .addGap(18, 18, 18)
                        .addComponent(btnVentas7, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVentas5, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVentas8, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVentas6, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVentas9, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVentas10, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVentas11, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Caja", jPanel46);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel57.setBackground(new java.awt.Color(43, 43, 43));
        jPanel57.setForeground(new java.awt.Color(255, 102, 0));

        jLabel75.setBackground(new java.awt.Color(255, 255, 255));
        jLabel75.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Mantenimientos");

        jPanel60.setBackground(new java.awt.Color(255, 119, 0));
        jPanel60.setPreferredSize(new java.awt.Dimension(160, 95));
        jPanel60.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel60MouseClicked(evt);
            }
        });

        jLabel80.setBackground(new java.awt.Color(255, 255, 255));
        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("Camas");

        btnguardar24.setForeground(new java.awt.Color(240, 240, 240));
        btnguardar24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Cama-50.png"))); // NOI18N
        btnguardar24.setMnemonic('N');
        btnguardar24.setToolTipText("");
        btnguardar24.setContentAreaFilled(false);
        btnguardar24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnguardar24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar24.setIconTextGap(30);
        btnguardar24.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnguardar24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnguardar24, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnguardar24, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel62.setBackground(new java.awt.Color(255, 119, 0));
        jPanel62.setPreferredSize(new java.awt.Dimension(160, 95));
        jPanel62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel62MouseClicked(evt);
            }
        });

        jLabel83.setBackground(new java.awt.Color(255, 255, 255));
        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("Pisos");
        jLabel83.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel83MouseClicked(evt);
            }
        });

        btnguardar26.setForeground(new java.awt.Color(240, 240, 240));
        btnguardar26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Edificio-50.png"))); // NOI18N
        btnguardar26.setMnemonic('N');
        btnguardar26.setToolTipText("");
        btnguardar26.setContentAreaFilled(false);
        btnguardar26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnguardar26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar26.setIconTextGap(30);
        btnguardar26.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnguardar26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnguardar26, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
            .addComponent(jLabel83, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnguardar26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel63.setBackground(new java.awt.Color(255, 119, 0));
        jPanel63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel63MouseClicked(evt);
            }
        });

        jLabel84.setBackground(new java.awt.Color(255, 255, 255));
        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("Habitaciones");

        btnguardar27.setForeground(new java.awt.Color(240, 240, 240));
        btnguardar27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Habitacion-50.png"))); // NOI18N
        btnguardar27.setMnemonic('N');
        btnguardar27.setToolTipText("");
        btnguardar27.setContentAreaFilled(false);
        btnguardar27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnguardar27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar27.setIconTextGap(30);
        btnguardar27.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnguardar27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar27ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnguardar27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel84, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnguardar27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
            .addComponent(jPanel62, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel75)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel6.setText("Servicio de:");

        lblServicio.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblServicio.setText("jLabel7");

        lblSe_ID.setForeground(new java.awt.Color(255, 255, 255));
        lblSe_ID.setText("jLabel7");

        jPanel65.setBackground(new java.awt.Color(61, 57, 57));

        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("<html>Examenes <br>Complementarios</html>");
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton9.setIconTextGap(6);

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel65Layout.createSequentialGroup()
                .addComponent(jButton9)
                .addContainerGap())
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
        );

        jPanel88.setBackground(new java.awt.Color(255, 119, 0));

        jButton10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Subir-32.png"))); // NOI18N
        jButton10.setText("Epicrisis");
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton10.setIconTextGap(30);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel88Layout = new javax.swing.GroupLayout(jPanel88);
        jPanel88.setLayout(jPanel88Layout);
        jPanel88Layout.setHorizontalGroup(
            jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel88Layout.createSequentialGroup()
                .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addGap(113, 113, 113))
        );
        jPanel88Layout.setVerticalGroup(
            jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel95.setBackground(new java.awt.Color(255, 119, 0));
        jPanel95.setForeground(new java.awt.Color(255, 153, 0));

        jLabel102.setBackground(new java.awt.Color(255, 255, 255));
        jLabel102.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Monedas-80.png"))); // NOI18N
        jLabel102.setText("<html>Papeleta de<br>Hospitalización</html>");
        jLabel102.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel102MouseClicked(evt);
            }
        });
        jLabel102.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel102KeyPressed(evt);
            }
        });

        jPanel96.setBackground(new java.awt.Color(255, 73, 7));

        javax.swing.GroupLayout jPanel96Layout = new javax.swing.GroupLayout(jPanel96);
        jPanel96.setLayout(jPanel96Layout);
        jPanel96Layout.setHorizontalGroup(
            jPanel96Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel96Layout.setVerticalGroup(
            jPanel96Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel95Layout = new javax.swing.GroupLayout(jPanel95);
        jPanel95.setLayout(jPanel95Layout);
        jPanel95Layout.setHorizontalGroup(
            jPanel95Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel95Layout.createSequentialGroup()
                .addComponent(jPanel96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
        jPanel95Layout.setVerticalGroup(
            jPanel95Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel102, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
            .addComponent(jPanel96, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel58.setBackground(new java.awt.Color(43, 43, 43));
        jPanel58.setForeground(new java.awt.Color(255, 153, 0));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Vista general 2-50.png"))); // NOI18N
        jButton4.setText("<html>Examen <br>Clínico</html>");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton4.setIconTextGap(30);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton4)
        );

        jPanel67.setBackground(new java.awt.Color(43, 43, 43));
        jPanel67.setForeground(new java.awt.Color(255, 153, 0));

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Jeringa-40 (2).png"))); // NOI18N
        jButton5.setText("<html>Controles <br>Vitales</html>");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton5.setIconTextGap(30);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(600, Short.MAX_VALUE))
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton5)
        );

        jPanel97.setBackground(new java.awt.Color(43, 43, 43));
        jPanel97.setForeground(new java.awt.Color(255, 153, 0));

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/FormatoHC-80.png"))); // NOI18N
        jButton3.setText("<html>Formato de <br>Historia Clínica</html>");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setHideActionText(true);
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.setIconTextGap(35);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel97Layout = new javax.swing.GroupLayout(jPanel97);
        jPanel97.setLayout(jPanel97Layout);
        jPanel97Layout.setHorizontalGroup(
            jPanel97Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel97Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel97Layout.setVerticalGroup(
            jPanel97Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
        );

        jPanel98.setBackground(new java.awt.Color(43, 43, 43));
        jPanel98.setForeground(new java.awt.Color(255, 153, 0));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Time-30.png"))); // NOI18N
        jButton1.setText("Pendientes");
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.setIconTextGap(10);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel98Layout = new javax.swing.GroupLayout(jPanel98);
        jPanel98.setLayout(jPanel98Layout);
        jPanel98Layout.setHorizontalGroup(
            jPanel98Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel98Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel98Layout.setVerticalGroup(
            jPanel98Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel98Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel99.setBackground(new java.awt.Color(43, 43, 43));
        jPanel99.setForeground(new java.awt.Color(255, 153, 0));

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Cama-30.png"))); // NOI18N
        jButton2.setText("Hospitalizados");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel99Layout = new javax.swing.GroupLayout(jPanel99);
        jPanel99.setLayout(jPanel99Layout);
        jPanel99Layout.setHorizontalGroup(
            jPanel99Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel99Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(625, Short.MAX_VALUE))
        );
        jPanel99Layout.setVerticalGroup(
            jPanel99Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel99Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );

        jPanel100.setBackground(new java.awt.Color(43, 43, 43));
        jPanel100.setForeground(new java.awt.Color(255, 153, 0));

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("<html>Hoja de Evolucion</html>");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton6.setIconTextGap(45);

        javax.swing.GroupLayout jPanel100Layout = new javax.swing.GroupLayout(jPanel100);
        jPanel100.setLayout(jPanel100Layout);
        jPanel100Layout.setHorizontalGroup(
            jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel100Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addGap(124, 124, 124))
        );
        jPanel100Layout.setVerticalGroup(
            jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel100Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton6)
                .addGap(23, 23, 23))
        );

        jPanel101.setBackground(new java.awt.Color(43, 43, 43));
        jPanel101.setForeground(new java.awt.Color(255, 153, 0));

        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("<html>Notas de <br>Enfermeria</html>");
        jButton7.setToolTipText("");
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton7.setIconTextGap(35);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel101Layout = new javax.swing.GroupLayout(jPanel101);
        jPanel101.setLayout(jPanel101Layout);
        jPanel101Layout.setHorizontalGroup(
            jPanel101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel101Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addGap(104, 104, 104))
        );
        jPanel101Layout.setVerticalGroup(
            jPanel101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel101Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel102.setBackground(new java.awt.Color(43, 43, 43));
        jPanel102.setForeground(new java.awt.Color(255, 153, 0));

        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("<html>Formato de <br>Interconsulta</html>");
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton8.setIconTextGap(30);

        javax.swing.GroupLayout jPanel102Layout = new javax.swing.GroupLayout(jPanel102);
        jPanel102.setLayout(jPanel102Layout);
        jPanel102Layout.setHorizontalGroup(
            jPanel102Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel102Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                .addGap(140, 140, 140))
        );
        jPanel102Layout.setVerticalGroup(
            jPanel102Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel102Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton8)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel95, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel98, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblSe_ID))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jPanel97, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)
                                    .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel88, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel101, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel102, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblServicio)
                    .addComponent(lblSe_ID))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel97, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel101, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel100, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel102, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Hosp", jPanel5);

        jPanel66.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setBackground(new java.awt.Color(232, 76, 61));

        lblUsu4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        lblUsu4.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/B5.png"))); // NOI18N
        lblUsu4.setText("Emergencia");
        lblUsu4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblUsu4.setIconTextGap(40);
        lblUsu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsu4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(lblUsu4, javax.swing.GroupLayout.PREFERRED_SIZE, 1099, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 439, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(lblUsu4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnReportCabecera1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnReportCabecera1.setForeground(new java.awt.Color(51, 51, 51));
        btnReportCabecera1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Document-64.png"))); // NOI18N
        btnReportCabecera1.setText("Triaje");
        btnReportCabecera1.setContentAreaFilled(false);
        btnReportCabecera1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportCabecera1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReportCabecera1.setIconTextGap(30);
        btnReportCabecera1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReportCabecera1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportCabecera1ActionPerformed(evt);
            }
        });

        btnReporTopico1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnReporTopico1.setForeground(new java.awt.Color(51, 51, 51));
        btnReporTopico1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-borrow-book-64.png"))); // NOI18N
        btnReporTopico1.setText("Cabecera");
        btnReporTopico1.setContentAreaFilled(false);
        btnReporTopico1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReporTopico1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReporTopico1.setIconTextGap(30);
        btnReporTopico1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReporTopico1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporTopico1ActionPerformed(evt);
            }
        });

        btnReportTriaje1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnReportTriaje1.setForeground(new java.awt.Color(51, 51, 51));
        btnReportTriaje1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-book-64.png"))); // NOI18N
        btnReportTriaje1.setText("Tópicos");
        btnReportTriaje1.setContentAreaFilled(false);
        btnReportTriaje1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportTriaje1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReportTriaje1.setIconTextGap(30);
        btnReportTriaje1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReportTriaje1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportTriaje1ActionPerformed(evt);
            }
        });

        btnMantLlegada2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnMantLlegada2.setForeground(new java.awt.Color(51, 51, 51));
        btnMantLlegada2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-ambulance-64.png"))); // NOI18N
        btnMantLlegada2.setText("<html>Forma de<br> LLegada</html>");
        btnMantLlegada2.setContentAreaFilled(false);
        btnMantLlegada2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMantLlegada2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMantLlegada2.setIconTextGap(30);
        btnMantLlegada2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMantLlegada2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMantLlegada2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btnReportCabecera1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReporTopico1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReportTriaje1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMantLlegada2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReportCabecera1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(btnReporTopico1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(btnReportTriaje1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(btnMantLlegada2, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                .addContainerGap(507, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("AdEM", jPanel66);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        jPanel45.setBackground(new java.awt.Color(95, 106, 112));
        jPanel45.setForeground(new java.awt.Color(255, 153, 0));

        jLabel58.setBackground(new java.awt.Color(255, 255, 255));
        jLabel58.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("<html>Solicitud de<br> Investigación<br>Bacteriológica</html>");
        jLabel58.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel58MouseClicked(evt);
            }
        });

        btnSolicitudInv.setBackground(new java.awt.Color(255, 255, 153));
        btnSolicitudInv.setForeground(new java.awt.Color(255, 255, 102));
        btnSolicitudInv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/solicitud.png"))); // NOI18N
        btnSolicitudInv.setMnemonic('N');
        btnSolicitudInv.setToolTipText("");
        btnSolicitudInv.setContentAreaFilled(false);
        btnSolicitudInv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSolicitudInv.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSolicitudInv.setIconTextGap(30);
        btnSolicitudInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitudInvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSolicitudInv, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSolicitudInv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1190, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(683, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("InvBac", jPanel29);

        jPanel86.setBackground(new java.awt.Color(255, 255, 255));

        jPanel23.setBackground(new java.awt.Color(0, 153, 102));

        lblUsu5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        lblUsu5.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/B3.png"))); // NOI18N
        lblUsu5.setText("<HTML>Consultorios<br>Externos</html>");
        lblUsu5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblUsu5.setIconTextGap(40);
        lblUsu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsu5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(lblUsu5, javax.swing.GroupLayout.PREFERRED_SIZE, 1099, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(lblUsu5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnTriaje.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnTriaje.setForeground(new java.awt.Color(51, 51, 51));
        btnTriaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-book-64.png"))); // NOI18N
        btnTriaje.setText("Triaje");
        btnTriaje.setToolTipText("");
        btnTriaje.setContentAreaFilled(false);
        btnTriaje.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTriaje.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTriaje.setIconTextGap(30);
        btnTriaje.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTriaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTriajeActionPerformed(evt);
            }
        });

        btnConsultorio.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnConsultorio.setForeground(new java.awt.Color(51, 51, 51));
        btnConsultorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-check-file-64.png"))); // NOI18N
        btnConsultorio.setText("Asignación");
        btnConsultorio.setToolTipText("");
        btnConsultorio.setContentAreaFilled(false);
        btnConsultorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsultorio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConsultorio.setIconTextGap(30);
        btnConsultorio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConsultorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultorioActionPerformed(evt);
            }
        });

        btnAdmEme2.setBackground(new java.awt.Color(102, 102, 102));
        btnAdmEme2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnAdmEme2.setForeground(new java.awt.Color(51, 51, 51));
        btnAdmEme2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-pregnant-64.png"))); // NOI18N
        btnAdmEme2.setText("<HTML>Carnet <BR>perinatal<HTML>");
        btnAdmEme2.setContentAreaFilled(false);
        btnAdmEme2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdmEme2.setFocusPainted(false);
        btnAdmEme2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdmEme2.setIconTextGap(30);
        btnAdmEme2.setVerifyInputWhenFocusTarget(false);
        btnAdmEme2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdmEme2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdmEme2MouseEntered(evt);
            }
        });
        btnAdmEme2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmEme2ActionPerformed(evt);
            }
        });

        btnAdmEme1.setBackground(new java.awt.Color(102, 102, 102));
        btnAdmEme1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAdmEme1.setForeground(new java.awt.Color(102, 102, 102));
        btnAdmEme1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-children-64.png"))); // NOI18N
        btnAdmEme1.setText("CRED");
        btnAdmEme1.setContentAreaFilled(false);
        btnAdmEme1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdmEme1.setFocusPainted(false);
        btnAdmEme1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdmEme1.setIconTextGap(30);
        btnAdmEme1.setVerifyInputWhenFocusTarget(false);
        btnAdmEme1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdmEme1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdmEme1MouseEntered(evt);
            }
        });
        btnAdmEme1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmEme1ActionPerformed(evt);
            }
        });

        btnConsultorio1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnConsultorio1.setForeground(new java.awt.Color(51, 51, 51));
        btnConsultorio1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-check-file-64.png"))); // NOI18N
        btnConsultorio1.setText("Asignación");
        btnConsultorio1.setToolTipText("");
        btnConsultorio1.setContentAreaFilled(false);
        btnConsultorio1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsultorio1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConsultorio1.setIconTextGap(30);
        btnConsultorio1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConsultorio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultorio1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel86Layout = new javax.swing.GroupLayout(jPanel86);
        jPanel86.setLayout(jPanel86Layout);
        jPanel86Layout.setHorizontalGroup(
            jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel86Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdmEme1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdmEme2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConsultorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(693, 693, 693))
        );
        jPanel86Layout.setVerticalGroup(
            jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel86Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel86Layout.createSequentialGroup()
                        .addGroup(jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTriaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnConsultorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(659, 659, 659))
                    .addGroup(jPanel86Layout.createSequentialGroup()
                        .addGroup(jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdmEme2)
                            .addComponent(btnAdmEme1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConsultorio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("CEX", jPanel86);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelFACTURASLayout = new javax.swing.GroupLayout(panelFACTURAS);
        panelFACTURAS.setLayout(panelFACTURASLayout);
        panelFACTURASLayout.setHorizontalGroup(
            panelFACTURASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1538, Short.MAX_VALUE)
        );
        panelFACTURASLayout.setVerticalGroup(
            panelFACTURASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 861, Short.MAX_VALUE)
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

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1538, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 861, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Almac", jPanel8);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jPanel136.setBackground(new java.awt.Color(43, 43, 43));
        jPanel136.setForeground(new java.awt.Color(255, 153, 0));

        jLabel109.setBackground(new java.awt.Color(255, 255, 255));
        jLabel109.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(255, 255, 255));
        jLabel109.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Monedas-80.png"))); // NOI18N
        jLabel109.setText("CAJA - sesiones activas");
        jLabel109.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel109.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel109MouseClicked(evt);
            }
        });
        jLabel109.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel109KeyPressed(evt);
            }
        });

        jPanel137.setBackground(new java.awt.Color(50, 151, 219));

        javax.swing.GroupLayout jPanel137Layout = new javax.swing.GroupLayout(jPanel137);
        jPanel137.setLayout(jPanel137Layout);
        jPanel137Layout.setHorizontalGroup(
            jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel137Layout.setVerticalGroup(
            jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel136Layout = new javax.swing.GroupLayout(jPanel136);
        jPanel136.setLayout(jPanel136Layout);
        jPanel136Layout.setHorizontalGroup(
            jPanel136Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel136Layout.createSequentialGroup()
                .addComponent(jPanel137, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 656, Short.MAX_VALUE))
        );
        jPanel136Layout.setVerticalGroup(
            jPanel136Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
            .addComponent(jPanel137, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel140.setBackground(new java.awt.Color(43, 43, 43));
        jPanel140.setForeground(new java.awt.Color(255, 153, 0));

        jLabel110.setBackground(new java.awt.Color(255, 255, 255));
        jLabel110.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(255, 255, 255));
        jLabel110.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Settings-60.png"))); // NOI18N
        jLabel110.setText("   Configuración PC");
        jLabel110.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel110.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel110MouseClicked(evt);
            }
        });
        jLabel110.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel110KeyPressed(evt);
            }
        });

        jPanel139.setBackground(new java.awt.Color(223, 0, 78));

        javax.swing.GroupLayout jPanel139Layout = new javax.swing.GroupLayout(jPanel139);
        jPanel139.setLayout(jPanel139Layout);
        jPanel139Layout.setHorizontalGroup(
            jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel139Layout.setVerticalGroup(
            jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel140Layout = new javax.swing.GroupLayout(jPanel140);
        jPanel140.setLayout(jPanel140Layout);
        jPanel140Layout.setHorizontalGroup(
            jPanel140Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel140Layout.createSequentialGroup()
                .addComponent(jPanel139, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 656, Short.MAX_VALUE))
        );
        jPanel140Layout.setVerticalGroup(
            jPanel140Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel110, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
            .addComponent(jPanel139, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel140, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel136, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel136, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel140, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(611, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Conf", jPanel16);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel132.setBackground(new java.awt.Color(122, 77, 135));

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-2005 Filled-70.png"))); // NOI18N
        jButton21.setToolTipText("");
        jButton21.setContentAreaFilled(false);
        jButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setText("Rol de Actividades");
        jButton22.setContentAreaFilled(false);
        jButton22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton22.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jPanel133.setBackground(new java.awt.Color(80, 35, 93));

        javax.swing.GroupLayout jPanel133Layout = new javax.swing.GroupLayout(jPanel133);
        jPanel133.setLayout(jPanel133Layout);
        jPanel133Layout.setHorizontalGroup(
            jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel133Layout.setVerticalGroup(
            jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel132Layout = new javax.swing.GroupLayout(jPanel132);
        jPanel132.setLayout(jPanel132Layout);
        jPanel132Layout.setHorizontalGroup(
            jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel132Layout.createSequentialGroup()
                .addComponent(jPanel133, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 1077, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );
        jPanel132Layout.setVerticalGroup(
            jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
            .addComponent(jPanel133, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel147.setBackground(new java.awt.Color(95, 106, 112));

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-2005 Filled-70.png"))); // NOI18N
        jButton23.setToolTipText("");
        jButton23.setContentAreaFilled(false);
        jButton23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setText("Actividades");
        jButton24.setContentAreaFilled(false);
        jButton24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton24.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel147Layout = new javax.swing.GroupLayout(jPanel147);
        jPanel147.setLayout(jPanel147Layout);
        jPanel147Layout.setHorizontalGroup(
            jPanel147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel147Layout.createSequentialGroup()
                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(692, 692, 692))
        );
        jPanel147Layout.setVerticalGroup(
            jPanel147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
            .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel148.setBackground(new java.awt.Color(95, 106, 112));

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-2005 Filled-70.png"))); // NOI18N
        jButton25.setToolTipText("");
        jButton25.setContentAreaFilled(false);
        jButton25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setText("Turnos");
        jButton26.setContentAreaFilled(false);
        jButton26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton26.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel148Layout = new javax.swing.GroupLayout(jPanel148);
        jPanel148.setLayout(jPanel148Layout);
        jPanel148Layout.setHorizontalGroup(
            jPanel148Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel148Layout.createSequentialGroup()
                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(689, 689, 689))
        );
        jPanel148Layout.setVerticalGroup(
            jPanel148Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
            .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(122, 77, 135));

        lblUsu3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        lblUsu3.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/B6.png"))); // NOI18N
        lblUsu3.setText("Personal");
        lblUsu3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblUsu3.setIconTextGap(40);
        lblUsu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsu3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(lblUsu3, javax.swing.GroupLayout.PREFERRED_SIZE, 1099, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(lblUsu3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel149.setBackground(new java.awt.Color(95, 106, 112));

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-2005 Filled-70.png"))); // NOI18N
        jButton27.setToolTipText("");
        jButton27.setContentAreaFilled(false);
        jButton27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jButton28.setForeground(new java.awt.Color(255, 255, 255));
        jButton28.setText("Actividades");
        jButton28.setContentAreaFilled(false);
        jButton28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton28.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel149Layout = new javax.swing.GroupLayout(jPanel149);
        jPanel149.setLayout(jPanel149Layout);
        jPanel149Layout.setHorizontalGroup(
            jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel149Layout.createSequentialGroup()
                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(692, 692, 692))
        );
        jPanel149Layout.setVerticalGroup(
            jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
            .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel149, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel132, javax.swing.GroupLayout.PREFERRED_SIZE, 1256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jPanel147, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel148, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(264, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jPanel132, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel147, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel148, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel149, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(280, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Person", jPanel10);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        lblusu10.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        lblusu10.setForeground(new java.awt.Color(102, 102, 102));
        lblusu10.setText("Diagnóstico por Imágenes EC");

        jPanel111.setBackground(new java.awt.Color(43, 43, 43));

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/file 64.png"))); // NOI18N
        jButton16.setContentAreaFilled(false);
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setText("Busqueda de Examenes a Realizar");
        jButton18.setContentAreaFilled(false);
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton18.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jPanel126.setBackground(new java.awt.Color(34, 133, 179));

        javax.swing.GroupLayout jPanel126Layout = new javax.swing.GroupLayout(jPanel126);
        jPanel126.setLayout(jPanel126Layout);
        jPanel126Layout.setHorizontalGroup(
            jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel126Layout.setVerticalGroup(
            jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel111Layout = new javax.swing.GroupLayout(jPanel111);
        jPanel111.setLayout(jPanel111Layout);
        jPanel111Layout.setHorizontalGroup(
            jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel111Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel126, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, 1166, Short.MAX_VALUE))
        );
        jPanel111Layout.setVerticalGroup(
            jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
            .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel126, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel112.setBackground(new java.awt.Color(43, 43, 43));

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/file 64.png"))); // NOI18N
        jButton19.setContentAreaFilled(false);
        jButton19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jPanel31.setBackground(new java.awt.Color(34, 133, 179));

        jButton20.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("Busqueda de Resultados");
        jButton20.setContentAreaFilled(false);
        jButton20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton20.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel112Layout = new javax.swing.GroupLayout(jPanel112);
        jPanel112.setLayout(jPanel112Layout);
        jPanel112Layout.setHorizontalGroup(
            jPanel112Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel112Layout.createSequentialGroup()
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel112Layout.setVerticalGroup(
            jPanel112Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblusu10)
                    .addComponent(jPanel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel112, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lblusu10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel111, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel112, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(530, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("EC", jPanel17);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel121.setBackground(new java.awt.Color(25, 88, 130));
        jPanel121.setForeground(new java.awt.Color(255, 153, 0));

        jPanel122.setBackground(new java.awt.Color(50, 151, 219));

        javax.swing.GroupLayout jPanel122Layout = new javax.swing.GroupLayout(jPanel122);
        jPanel122.setLayout(jPanel122Layout);
        jPanel122Layout.setHorizontalGroup(
            jPanel122Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel122Layout.setVerticalGroup(
            jPanel122Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel121Layout = new javax.swing.GroupLayout(jPanel121);
        jPanel121.setLayout(jPanel121Layout);
        jPanel121Layout.setHorizontalGroup(
            jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel121Layout.createSequentialGroup()
                .addComponent(jPanel122, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1262, Short.MAX_VALUE))
        );
        jPanel121Layout.setVerticalGroup(
            jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel122, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btneditar21.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btneditar21.setForeground(new java.awt.Color(51, 51, 51));
        btneditar21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Cheque de nómina-64.png"))); // NOI18N
        btneditar21.setText("Nota de Crédito");
        btneditar21.setContentAreaFilled(false);
        btneditar21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar21.setIconTextGap(30);
        btneditar21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btneditar21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btneditar21MouseClicked(evt);
            }
        });
        btneditar21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar21ActionPerformed(evt);
            }
        });

        btneditar22.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btneditar22.setForeground(new java.awt.Color(51, 51, 51));
        btneditar22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Mejoras económicas-64 (1).png"))); // NOI18N
        btneditar22.setText("Nota de Débito");
        btneditar22.setContentAreaFilled(false);
        btneditar22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar22.setIconTextGap(30);
        btneditar22.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btneditar22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar22ActionPerformed(evt);
            }
        });

        btneditar23.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btneditar23.setForeground(new java.awt.Color(51, 51, 51));
        btneditar23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Solicitud de dinero-64.png"))); // NOI18N
        btneditar23.setText("<HTML>Comunicación <BR>de Baja<HTML>");
        btneditar23.setContentAreaFilled(false);
        btneditar23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar23.setIconTextGap(30);
        btneditar23.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btneditar23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar23ActionPerformed(evt);
            }
        });

        btneditar25.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btneditar25.setForeground(new java.awt.Color(51, 51, 51));
        btneditar25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Document-64(1).png"))); // NOI18N
        btneditar25.setText("<html>Reportes<br><span style=\"font-size:'13px'\">Notas </span></html>");
        btneditar25.setContentAreaFilled(false);
        btneditar25.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar25.setIconTextGap(30);
        btneditar25.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btneditar25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar25ActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(135, 183, 50));

        lblUsu1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        lblUsu1.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/B4.png"))); // NOI18N
        lblUsu1.setText("<HTML>Comprobantes de<BR>Pago Electrónicos<HTML>");
        lblUsu1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblUsu1.setIconTextGap(40);
        lblUsu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsu1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(lblUsu1, javax.swing.GroupLayout.PREFERRED_SIZE, 1099, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(lblUsu1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btneditar26.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btneditar26.setForeground(new java.awt.Color(51, 51, 51));
        btneditar26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-borrow-book-64.png"))); // NOI18N
        btneditar26.setText("<HTML>Libro de <br>Compras</html>");
        btneditar26.setContentAreaFilled(false);
        btneditar26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar26.setIconTextGap(30);
        btneditar26.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btneditar26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar26ActionPerformed(evt);
            }
        });

        btneditar27.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btneditar27.setForeground(new java.awt.Color(51, 51, 51));
        btneditar27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-book-64.png"))); // NOI18N
        btneditar27.setText("<HTML>Libro de <br>Ventas</html>");
        btneditar27.setContentAreaFilled(false);
        btneditar27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar27.setIconTextGap(30);
        btneditar27.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btneditar27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar27ActionPerformed(evt);
            }
        });

        btneditar28.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btneditar28.setForeground(new java.awt.Color(51, 51, 51));
        btneditar28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-Document-64(1).png"))); // NOI18N
        btneditar28.setText("<HTML>Consolidado de <BR>Ventas<HTML>");
        btneditar28.setContentAreaFilled(false);
        btneditar28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar28.setIconTextGap(30);
        btneditar28.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btneditar28.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btneditar28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btneditar25, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btneditar26, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btneditar27, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btneditar28, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btneditar21, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btneditar22, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(19, 19, 19)
                                        .addComponent(btneditar23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel121, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 230, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btneditar22, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addComponent(btneditar23)
                        .addComponent(btneditar21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btneditar28, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jPanel121, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(675, 675, 675))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btneditar27)
                            .addComponent(btneditar26)
                            .addComponent(btneditar25))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("CCP", jPanel1);

        jPanel142.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel142Layout = new javax.swing.GroupLayout(jPanel142);
        jPanel142.setLayout(jPanel142Layout);
        jPanel142Layout.setHorizontalGroup(
            jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1538, Short.MAX_VALUE)
        );
        jPanel142Layout.setVerticalGroup(
            jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 861, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("H", jPanel142);

        jPanel9.setBackground(new java.awt.Color(40, 40, 43));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("H I M S");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIconTextGap(15);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel138, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 471, Short.MAX_VALUE)
                .addComponent(jPanel138, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 466, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));

        lblUsu.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
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
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 1167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLaboratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaboratorioActionPerformed

         jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_btnLaboratorioActionPerformed

    private void btneditar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar5ActionPerformed
        Costos_Depreciacion frm = new Costos_Depreciacion();
        frm.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        frm.lblUsu.setText(u);
    }//GEN-LAST:event_btneditar5ActionPerformed

    private void btneditar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar4ActionPerformed
        COSTOS_PRODUCTO_REFERENCIAL frm = new COSTOS_PRODUCTO_REFERENCIAL();
        frm.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        frm.lblUsu.setText(u);
    }//GEN-LAST:event_btneditar4ActionPerformed

    private void btneditar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar6ActionPerformed
        COSTOS_SERVICIOS_VARIOS frm = new COSTOS_SERVICIOS_VARIOS();
        frm.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        frm.lblUsu.setText(u);
    }//GEN-LAST:event_btneditar6ActionPerformed

    private void btneditar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar8ActionPerformed

    private void btnguardar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar8ActionPerformed
        BUSCAR_ESTIMACION_COSTOS frm = new BUSCAR_ESTIMACION_COSTOS();
        frm.setVisible(true);
        BUSCAR_ESTIMACION_COSTOS.tb_Buscar_Estimacion.getSelectionModel().setSelectionInterval(0, 0);
        BUSCAR_ESTIMACION_COSTOS.tb_Buscar_Estimacion.requestFocus();
    }//GEN-LAST:event_btnguardar8ActionPerformed

    private void btnguardar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar9ActionPerformed
        BUSCAR_NOMENCLATURA frm = new BUSCAR_NOMENCLATURA();
        frm.setVisible(true);
        
    }//GEN-LAST:event_btnguardar9ActionPerformed

    private void btneditar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar9ActionPerformed
        TipoSustentacion frm = new TipoSustentacion();
        frm.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        frm.lblUsu.setText(u);
    }//GEN-LAST:event_btneditar9ActionPerformed

    private void btnSectorizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSectorizacionActionPerformed
        FrmSector frmSector = new FrmSector();
        frmSector.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        frmSector.lblUsuUsuario.setText(u);
    }//GEN-LAST:event_btnSectorizacionActionPerformed

    private void btnReportMovinientoHCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportMovinientoHCActionPerformed
        FrmMovimientoHC frmMov = new FrmMovimientoHC();
        frmMov.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
    }//GEN-LAST:event_btnReportMovinientoHCActionPerformed

    private void btnReportSeguimientoHCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportSeguimientoHCActionPerformed
        FrmSeguimientoHC frmSeg = new FrmSeguimientoHC();
        frmSeg.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        frmSeg.lblUsuUsuario.setText(u);
    }//GEN-LAST:event_btnReportSeguimientoHCActionPerformed

    private void btnPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacienteActionPerformed
        FrmNuevaHistoriaC HC = new FrmNuevaHistoriaC();
        HC.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        HC.lblUsuUsuario.setText(u);
    }//GEN-LAST:event_btnPacienteActionPerformed

    private void btnCostosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCostosActionPerformed

         jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnCostosActionPerformed

    private void btnRayosXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRayosXActionPerformed
        RX_PRINCIPAL f=new RX_PRINCIPAL();
        panelFACTURAS.add(f);
//        String u=Principal.lblUsu.getText();
        
           f.setVisible(true);
        try {
            f.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(9);

         
    }//GEN-LAST:event_btnRayosXActionPerformed

    private void btnInvBactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInvBactActionPerformed

        jTabbedPane1.setSelectedIndex(7);
    }//GEN-LAST:event_btnInvBactActionPerformed

    private void btnguardar24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardar24ActionPerformed

    private void btnguardar26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar26ActionPerformed
        FrmHospitalizacionPisos frmHosp = new FrmHospitalizacionPisos();
        frmHosp.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        frmHosp.lblUsuUsuario.setText(u);
    }//GEN-LAST:event_btnguardar26ActionPerformed

    private void btnguardar27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardar27ActionPerformed

    private void jPanel63MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel63MouseClicked
        if(evt.getClickCount()==1){
            FrmHospitalizacionHabitaciones frmHosp = new FrmHospitalizacionHabitaciones();
            frmHosp.setVisible(true);
            String u=PrincipalMDI1.lblUsu.getText();
            frmHosp.lblUsuUsuario.setText(u);
        }
    }//GEN-LAST:event_jPanel63MouseClicked

    private void jPanel60MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel60MouseClicked
        if(evt.getClickCount()==1){
            FrmHospitalizacionCamas frmHosp = new FrmHospitalizacionCamas();
            frmHosp.setVisible(true);
            String u=PrincipalMDI1.lblUsu.getText();
            frmHosp.lblUsuUsuario.setText(u);
        }
    }//GEN-LAST:event_jPanel60MouseClicked

    private void jPanel62MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel62MouseClicked
        if(evt.getClickCount()==1){
            FrmHospitalizacionPisos frmHosp = new FrmHospitalizacionPisos();
            frmHosp.setVisible(true);
            String u=PrincipalMDI1.lblUsu.getText();
            frmHosp.lblUsuUsuario.setText(u);
        }
    }//GEN-LAST:event_jPanel62MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FrmHospitalizacionListarCajaPreventa frmHospCP=new  FrmHospitalizacionListarCajaPreventa();
        frmHospCP.setVisible(true);
        String u=lblUsu.getText();
        FrmHospitalizacionListarCajaPreventa.lblUsuUsuario.setText(u);
        FrmHospitalizacionListarCajaPreventa.rbtP.setSelected(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        FrmHospitalizacionListarCajaPreventa frmHospCP=new  FrmHospitalizacionListarCajaPreventa();
        HospitalizacionPapeletas hosP = new HospitalizacionPapeletas();
        frmHospCP.setVisible(true);
        String u=lblUsu.getText();
        FrmHospitalizacionListarCajaPreventa.lblUsuUsuario.setText(u);
        FrmHospitalizacionListarCajaPreventa.rbtH.setSelected(true);
        hosP.listarPapeleta("C", FrmHospitalizacionListarCajaPreventa.txtBuscarr.getText(), FrmHospitalizacionListarCajaPreventa.tbListarPapeleta,"C");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        FrmHospitalizacionFormatoHC frmFormat =new  FrmHospitalizacionFormatoHC();
        frmFormat.setVisible(true);
        String u=lblUsu.getText();
        FrmHospitalizacionFormatoHC.lblUsuUsuario.setText(u);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnReportCabecera1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportCabecera1ActionPerformed
        FrmFormatoEmergenciaTriaje frmEmerT = new FrmFormatoEmergenciaTriaje();
        frmEmerT.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        frmEmerT.lblusu.setText(u);
    }//GEN-LAST:event_btnReportCabecera1ActionPerformed

    private void btnReporTopico1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporTopico1ActionPerformed
        FrmFormatoEmergenciaCabecera frmEmerC = new FrmFormatoEmergenciaCabecera();
        frmEmerC.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        frmEmerC.lblusu.setText(u);
    }//GEN-LAST:event_btnReporTopico1ActionPerformed

    private void btnReportTriaje1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportTriaje1ActionPerformed
        FrmFormatoEmergenciaTopico frmEmerT = new FrmFormatoEmergenciaTopico();
        frmEmerT.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        frmEmerT.lblusu.setText(u);
    }//GEN-LAST:event_btnReportTriaje1ActionPerformed

    private void btnMantLlegada2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMantLlegada2ActionPerformed
        FrmFormaDeLlegada frmEmerFL = new FrmFormaDeLlegada();
        frmEmerFL.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        frmEmerFL.lblUsuario.setText(u);
    }//GEN-LAST:event_btnMantLlegada2ActionPerformed

    private void btnAdmEmeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmEmeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdmEmeMouseEntered

    private void btnAdmEmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmEmeActionPerformed
        Caja_PC_Registro CPC = new Caja_PC_Registro();
        CPC.VerificarExistencia(PrincipalMDI1.lblUsu.getText(),"",tbEME);
        if(tbEME.getRowCount()==0){
            AdmEmer_Registro frmPC = new AdmEmer_Registro();
            frmPC.setVisible(true);
            frmPC.lblUsuario.setText(PrincipalMDI1.lblUsu.getText());
        }else   if(tbEME.getRowCount()>0){
//                    Caja_Pagos CP = new Caja_Pagos();
//                    CP.setVisible(true);
//                    String u=PrincipalMDI.lblUsu.getText();
//                    CP.lblusu.setText(u);
            jTabbedPane1.setSelectedIndex(6);
                }
            
        
        
    }//GEN-LAST:event_btnAdmEmeActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        FrmHospitalizacionExClinico frmExamen =new  FrmHospitalizacionExClinico();
        frmExamen.setVisible(true);
        String u=lblUsu.getText();
        FrmHospitalizacionExClinico.lblUsuUsuario.setText(u);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btneditar11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar11ActionPerformed

    private void btnMuestrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuestrasActionPerformed
        frm_LAB_MUESTRA_EXAMEN lme=new  frm_LAB_MUESTRA_EXAMEN();
        lme.setVisible(true);
        String u=lblUsu.getText();
        frm_LAB_MUESTRA_EXAMEN.lblUsu.setText(u);
    }//GEN-LAST:event_btnMuestrasActionPerformed

    private void btnEsquemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsquemaActionPerformed
        frm_LAB_ESQUEMA_RESULTADO_INGRESO lme=new  frm_LAB_ESQUEMA_RESULTADO_INGRESO();
        lme.setVisible(true);
        String u=lblUsu.getText();
        frm_LAB_ESQUEMA_RESULTADO_INGRESO.lblUsu.setText(u);
    }//GEN-LAST:event_btnEsquemaActionPerformed

    private void btnValoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValoresActionPerformed
        frm_LAB_VALORES_REFERENCIALES_INGRESO lme=new   frm_LAB_VALORES_REFERENCIALES_INGRESO();
        lme.setVisible(true);
        String u=lblUsu.getText();
        frm_LAB_VALORES_REFERENCIALES_INGRESO.lblUsu.setText(u);
    }//GEN-LAST:event_btnValoresActionPerformed

    private void btnUnidadMediActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnidadMediActionPerformed
        frm_LAB_UNIDAD_MEDIDA_RESULTADO lme=new  frm_LAB_UNIDAD_MEDIDA_RESULTADO();
        lme.setVisible(true);
        String u=lblUsu.getText();
        frm_LAB_UNIDAD_MEDIDA_RESULTADO.lblUsu.setText(u);
    }//GEN-LAST:event_btnUnidadMediActionPerformed

    private void btnAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalisisActionPerformed
        frm_LAB_ANALISIS_EXAMEN lme=new  frm_LAB_ANALISIS_EXAMEN();
        lme.setVisible(true);
        String u=lblUsu.getText();
        frm_LAB_ANALISIS_EXAMEN.lblUsu.setText(u);
    }//GEN-LAST:event_btnAnalisisActionPerformed

    private void btnClasificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClasificacionActionPerformed
        frm_LAB_CLASIFICACION_EXAMEN lme=new  frm_LAB_CLASIFICACION_EXAMEN();
        lme.setVisible(true);
        String u=lblUsu.getText();
        frm_LAB_CLASIFICACION_EXAMEN.lblUsu.setText(u);
    }//GEN-LAST:event_btnClasificacionActionPerformed

    private void btnTMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTMActionPerformed
        frm_LAB_TOMA_MUESTRA_INGRESO lme=new  frm_LAB_TOMA_MUESTRA_INGRESO();
        lme.setVisible(true);
        String u=lblUsu.getText();
        frm_LAB_TOMA_MUESTRA_INGRESO.lblUsu.setText(u);
    }//GEN-LAST:event_btnTMActionPerformed

    private void btnTMBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTMBusquedaActionPerformed
       frm_LAB_BUSCAR_TOMA_MUESTRA lme=new  frm_LAB_BUSCAR_TOMA_MUESTRA();
        lme.setVisible(true);
        String u=lblUsu.getText();
        frm_LAB_BUSCAR_TOMA_MUESTRA.lblUsu.setText(u);
    }//GEN-LAST:event_btnTMBusquedaActionPerformed

    private void jLabel96MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel96MouseClicked
        btnResultado.doClick();
    }//GEN-LAST:event_jLabel96MouseClicked

    private void btnResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResultadoActionPerformed
       frm_LAB_RESULTADOS_MUESTRA_INGRESO lme=new  frm_LAB_RESULTADOS_MUESTRA_INGRESO();
        lme.setVisible(true);
        String u=lblUsu.getText();
        frm_LAB_RESULTADOS_MUESTRA_INGRESO.lblUsu.setText(u);
    }//GEN-LAST:event_btnResultadoActionPerformed

    private void jLabel97MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel97MouseClicked
        btnResultadoBusq.doClick();
    }//GEN-LAST:event_jLabel97MouseClicked

    private void btnResultadoBusqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResultadoBusqActionPerformed
        frm_LAB_BUSCAR_RESULTADO lme=new  frm_LAB_BUSCAR_RESULTADO();
        lme.setVisible(true);
        String u=lblUsu.getText();
        frm_LAB_BUSCAR_RESULTADO.lblUsu.setText(u);
    }//GEN-LAST:event_btnResultadoBusqActionPerformed

    private void jLabel98MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel98MouseClicked
        btnEntrega.doClick();
    }//GEN-LAST:event_jLabel98MouseClicked

    private void btnEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregaActionPerformed
       frm_LAB_ENTREGA_RESULTADO lme=new  frm_LAB_ENTREGA_RESULTADO();
        lme.setVisible(true);
        String u=lblUsu.getText();
        frm_LAB_ENTREGA_RESULTADO.lblUsu.setText(u);
    }//GEN-LAST:event_btnEntregaActionPerformed

    private void btnConversionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConversionActionPerformed
        frm_LAB_CONVERSION lme=new  frm_LAB_CONVERSION();
        lme.setVisible(true);
        String u=lblUsu.getText();
        frm_LAB_CONVERSION.lblUsu.setText(u);
    }//GEN-LAST:event_btnConversionActionPerformed

    private void btnSolicitudRecepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitudRecepActionPerformed
        frm_RECEP_SOLICITUDES lme=new  frm_RECEP_SOLICITUDES();
        lme.setVisible(true);
        
    }//GEN-LAST:event_btnSolicitudRecepActionPerformed

    private void jLabel99MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel99MouseClicked
        btnConversion.doClick();
    }//GEN-LAST:event_jLabel99MouseClicked

    private void jLabel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseClicked
        btnContenedor.doClick();
    }//GEN-LAST:event_jLabel57MouseClicked

    private void btnContenedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContenedorActionPerformed
        frm_LAB_CONTENEDOR_MUESTRA lme=new  frm_LAB_CONTENEDOR_MUESTRA();
        lme.setVisible(true);
        String u=lblUsu.getText();
        frm_LAB_CONTENEDOR_MUESTRA.lblUsu.setText(u);
    }//GEN-LAST:event_btnContenedorActionPerformed

    private void jLabel83MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel83MouseClicked
        if(evt.getClickCount()==1){
            FrmHospitalizacionPisos frmHosp = new FrmHospitalizacionPisos();
            frmHosp.setVisible(true);
            String u=PrincipalMDI1.lblUsu.getText();
            frmHosp.lblUsuUsuario.setText(u);
        }
    }//GEN-LAST:event_jLabel83MouseClicked

    private void btnSolicitudInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitudInvActionPerformed
       frm_SOLICITUD_INVESTIGACION_BACT sol=new frm_SOLICITUD_INVESTIGACION_BACT();
       sol.setVisible(true);
       String u=lblUsu.getText();
       frm_SOLICITUD_INVESTIGACION_BACT.lblUsu.setText(u);
    }//GEN-LAST:event_btnSolicitudInvActionPerformed

    private void jLabel58MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel58MouseClicked
        btnSolicitudInv.doClick();
    }//GEN-LAST:event_jLabel58MouseClicked

    private void btnConsultorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultorioActionPerformed
        ConsultorioAsignacion consultorio = new ConsultorioAsignacion();
        consultorio.setVisible(true);
        String u=lblUsu.getText();
        ConsultorioAsignacion.lblUsu.setText(u);
    }//GEN-LAST:event_btnConsultorioActionPerformed

    private void btnTriajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTriajeActionPerformed
        Triaje triaje = new Triaje();
        triaje.setVisible(true);
        String u=lblUsu.getText();
        Triaje.lblUsu.setText(u);
    }//GEN-LAST:event_btnTriajeActionPerformed

    private void btnConExtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConExtActionPerformed

        jTabbedPane1.setSelectedIndex(8);
    }//GEN-LAST:event_btnConExtActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        FrmHospitalizacionNotaEnfermeria notaEnfermeria = new FrmHospitalizacionNotaEnfermeria();
        notaEnfermeria.setVisible(true);
        String u=lblUsu.getText();
        FrmHospitalizacionNotaEnfermeria.lblUsu.setText(u);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        FrmHospitalizacionEpicrisis epicrisis = new FrmHospitalizacionEpicrisis();
        epicrisis.setVisible(true);
        String u=lblUsu.getText();
        FrmHospitalizacionEpicrisis.lblUsu.setText(u);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jLabel102MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel102MouseClicked
         FrmHospitalizacionCajaPreventa frmHosp = new FrmHospitalizacionCajaPreventa();
            frmHosp.setVisible(true);
            String u=PrincipalMDI1.lblUsu.getText();
            String servicio = PrincipalMDI1.lblServicio.getText();
            frmHosp.lblUsuUsuario.setText(u);
            //DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();
            frmHosp.cbxServicio.addItem(servicio);
            frmHosp.cbxServicio.setSelectedItem(servicio);
            frmHosp.lblServicio.setText(servicio);
    }//GEN-LAST:event_jLabel102MouseClicked

    private void jLabel102KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel102KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel102KeyPressed

    private void btnPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalActionPerformed

        jTabbedPane1.setSelectedIndex(12);
    }//GEN-LAST:event_btnPersonalActionPerformed

    private void btnEcografiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEcografiaActionPerformed

         
         jTabbedPane1.setSelectedIndex(13);
    }//GEN-LAST:event_btnEcografiaActionPerformed

    private void btneditar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar7ActionPerformed
          frm_Asig_Catalogo_Producto m=new frm_Asig_Catalogo_Producto();
        m.setVisible(true);
    }//GEN-LAST:event_btneditar7ActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        if(evt.getClickCount()==1){
            btneditar4.doClick();
        }
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        if(evt.getClickCount()==1){
            btneditar7.doClick();
        }
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        if(evt.getClickCount()==1){
            btneditar5.doClick();
        }
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        if(evt.getClickCount()==1){
            btneditar9.doClick();
        }
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        if(evt.getClickCount()==1){
            btneditar6.doClick();
        }
    }//GEN-LAST:event_jLabel33MouseClicked

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        EC_BUSCAR_EXAMEN_CAJA examenEC = new EC_BUSCAR_EXAMEN_CAJA();
        examenEC.setVisible(true);
        String u = lblUsu.getText();
        EC_BUSCAR_EXAMEN_CAJA.lblUsu.setText(u);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        EC_BUSCAR_EXAMEN_CAJA examenEC = new EC_BUSCAR_EXAMEN_CAJA();
        examenEC.setVisible(true);
        String u = lblUsu.getText();
        EC_BUSCAR_EXAMEN_CAJA.lblUsu.setText(u);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        EC_RESULTADOS examenEC_res = new EC_RESULTADOS();
        examenEC_res.setVisible(true);
        String u = lblUsu.getText();
        EC_RESULTADOS.lblUsu.setText(u);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        EC_RESULTADOS examenEC_res = new EC_RESULTADOS();
        examenEC_res.setVisible(true);
        String u = lblUsu.getText();
        EC_RESULTADOS.lblUsu.setText(u);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void tbPCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPCKeyPressed

    }//GEN-LAST:event_tbPCKeyPressed

    private void btnCajaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCajaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCajaMouseEntered

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
        CPC.VerificarExistencia(PrincipalMDI1.lblUsu.getText(),"",tbPC);
        CA.Caja_Verificar_Apertura(PrincipalMDI1.lblUsu.getText(),tbAPERTURA);
        CAID.CajaID_SESION(PrincipalMDI1.lblUsu.getText());
        CAS.Caja_Verificar_SESIONES(PrincipalMDI1.lblUsu.getText(),tbSESIONES);
        CAO.Caja_Verificar_SESIONES_OTRA_PC(PrincipalMDI1.lblUsu.getText(),tbAPERTURA_OTRAPC);
        CAOD.Caja_Verificar_SESIONES_OTRA_PC(PrincipalMDI1.lblUsu.getText(),tbAPERTURA_OTRAPC1);
        CAOU.Caja_Verificar_ULTIMO_CIERRE(PrincipalMDI1.lblUsu.getText(),tbULTIMA_SESION);
        
        if(tbPC.getRowCount()==0){
            Caja_Registro frmPC = new Caja_Registro();
            frmPC.setVisible(true);
            frmPC.lblUsuario.setText(PrincipalMDI1.lblUsu.getText());
        }else   if(tbPC.getRowCount()>0){
                    if(tbSESIONES.getRowCount()==0){
                        if(tbULTIMA_SESION.getRowCount()==0){
                            if(tbAPERTURA_OTRAPC.getRowCount()==0){
                                if(tbAPERTURA.getRowCount()==0){
//                                    btnCaja.setPressedIcon(cerrada);
                                    Caja_Apertura frmPCAPERTURA = new Caja_Apertura();
                                    frmPCAPERTURA.setVisible(true);
                                    frmPCAPERTURA.lblusu.setText(PrincipalMDI1.lblUsu.getText());
                                }else if(tbAPERTURA.getRowCount()>0){
//                                        btnCaja.setPressedIcon(aperturar);
                                        jTabbedPane1.setSelectedIndex(4);
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

    private void btnAdmCentralMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmCentralMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdmCentralMouseEntered

    private void btnAdmCentralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmCentralActionPerformed
         jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnAdmCentralActionPerformed

    private void btnHospitalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHospitalizacionActionPerformed

        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_btnHospitalizacionActionPerformed

    private void btnAdmEme1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmEme1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdmEme1MouseEntered

    private void btnAdmEme1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmEme1ActionPerformed
        RegistroSeguimiento frmCRED = new RegistroSeguimiento();
        frmCRED.setVisible(true);
    }//GEN-LAST:event_btnAdmEme1ActionPerformed

    private void btnAdmEme2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmEme2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdmEme2MouseEntered

    private void btnAdmEme2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmEme2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdmEme2ActionPerformed

    private void btnLaboratorio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaboratorio2ActionPerformed
        jTabbedPane1.setSelectedIndex(14);
    }//GEN-LAST:event_btnLaboratorio2ActionPerformed

    private void btneditar21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneditar21MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar21MouseClicked

    private void btneditar21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar21ActionPerformed
       NotasCreditoDebito nc=new NotasCreditoDebito();
       nc.setVisible(true);
       String u=PrincipalMDI1.lblUsu.getText();
        NotasCreditoDebito.lblUsu.setText(u);
    }//GEN-LAST:event_btneditar21ActionPerformed

    private void btneditar22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar22ActionPerformed
        NotasCreditoDebito nc=new NotasCreditoDebito();
       nc.setVisible(true);
       nc.tab.setSelectedIndex(1);
       
    }//GEN-LAST:event_btneditar22ActionPerformed

    private void btneditar23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar23ActionPerformed
        ComunicacionBaja cb=new ComunicacionBaja();
        cb.setVisible(true);
    }//GEN-LAST:event_btneditar23ActionPerformed

    private void btneditar25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar25ActionPerformed
        ReporteNCND NCND=new ReporteNCND();
        NCND.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        NotasCreditoDebito.lblUsu.setText(u);
    }//GEN-LAST:event_btneditar25ActionPerformed

    private void btnAlertConsulta7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta7ActionPerformed
        ErrorExistente.dispose();
    }//GEN-LAST:event_btnAlertConsulta7ActionPerformed

    private void jLabel109MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel109MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel109MouseClicked

    private void jLabel109KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel109KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel109KeyPressed

    private void jLabel110MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel110MouseClicked
        ConfRegistroPC pc=new ConfRegistroPC();
        pc.setVisible(true);
        ConfRegistroPC.lblUsuario.setText(lblUsu.getText());
    }//GEN-LAST:event_jLabel110MouseClicked

    private void jLabel110KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel110KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel110KeyPressed

    private void btnAnularVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularVentaActionPerformed
            Caja_Cierre frmCIERRED = new Caja_Cierre();
            frmCIERRED.lblusu.setText(PrincipalMDI1.lblUsu.getText());
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
        frmPCAPERTURA.lblusu.setText(PrincipalMDI1.lblUsu.getText());
    }//GEN-LAST:event_btnAlertConsulta10ActionPerformed

    private void jLabel68MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel68MouseClicked
        NivelSuperior.dispose();
    }//GEN-LAST:event_jLabel68MouseClicked

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        PERSONAL_ROL PERSONAL_r = new PERSONAL_ROL();
        PERSONAL_r.setVisible(true);
        String u = lblUsu.getText();
        PERSONAL_ROL.lblusu.setText(u);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        PERSONAL_ROL PERSONAL = new PERSONAL_ROL();
        PERSONAL.setVisible(true);
        String u = lblUsu.getText();
        PERSONAL_ROL.lblusu.setText(u);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        PERSONAL_ACTIVIDADES PERSONAL_A = new PERSONAL_ACTIVIDADES();
        PERSONAL_A.setVisible(true);
        String u = lblUsu.getText();
        PERSONAL_ACTIVIDADES.lblusu.setText(u);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        PERSONAL_ACTIVIDADES PERSONAL_A = new PERSONAL_ACTIVIDADES();
        PERSONAL_A.setVisible(true);
        String u = lblUsu.getText();
        PERSONAL_ACTIVIDADES.lblusu.setText(u);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        PERSONAL_TURNOS PERSONAL_T = new PERSONAL_TURNOS();
        PERSONAL_T.setVisible(true);
        String u = lblUsu.getText();
        PERSONAL_TURNOS.lblusu.setText(u);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        PERSONAL_TURNOS PERSONAL_T = new PERSONAL_TURNOS();
        PERSONAL_T.setVisible(true);
        String u = lblUsu.getText();
        PERSONAL_TURNOS.lblusu.setText(u);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void btnEcografia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEcografia1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEcografia1ActionPerformed

    private void btnEcografia2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEcografia2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEcografia2ActionPerformed

    private void btneditar8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneditar8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar8MouseClicked

    private void btnCajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCajaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCajaMouseClicked

    private void btneditar26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar26ActionPerformed

    private void btneditar27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneditar27ActionPerformed

    private void btnVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseClicked

    }//GEN-LAST:event_btnVentasMouseClicked

    private void btnVentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentasMouseEntered
 
    }//GEN-LAST:event_btnVentasMouseEntered

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
                    Caja_Pagos CP = new Caja_Pagos();
                    CP.setVisible(true);
                    String IDS=PrincipalMDI1.lblIDSESION.getText();
                    CP.lblID_SESION.setText(IDS);
                    String u=PrincipalMDI1.lblUsu.getText();
                    CP.lblusu.setText(u);
                    String ID=PrincipalMDI1.ibiIDAPERTURA.getText();
                    CP.lblID_APERTURA.setText(ID);
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnVentas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas1MouseClicked

    private void btnVentas1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas1MouseEntered

    private void btnVentas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas1ActionPerformed
        Caja_Nomenclaturas CCPT = new Caja_Nomenclaturas();
        CCPT.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        CCPT.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas1ActionPerformed

    private void btnVentas2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas2MouseClicked

    private void btnVentas2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas2MouseEntered

    private void btnVentas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas2ActionPerformed
         Caja_Precios CPR = new Caja_Precios();
        CPR.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        CPR.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas2ActionPerformed

    private void btnVentas3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas3MouseClicked

    private void btnVentas3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas3MouseEntered

    private void btnVentas3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas3ActionPerformed
        Caja_Grupo_Nomenclaturas CG = new Caja_Grupo_Nomenclaturas();
        CG.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        CG.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas3ActionPerformed

    private void btnVentas4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas4MouseClicked

    private void btnVentas4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas4MouseEntered

    private void btnVentas4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas4ActionPerformed
        
        Caja_Cierre frmCIERRE = new Caja_Cierre();
            frmCIERRE.lblusu.setText(PrincipalMDI1.lblUsu.getText());
            frmCIERRE.setVisible(true);
    }//GEN-LAST:event_btnVentas4ActionPerformed

    private void btnVentas5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas5MouseClicked

    private void btnVentas5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas5MouseEntered

    private void btnVentas5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas5ActionPerformed

    private void btnVentas6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas6MouseClicked

    private void btnVentas6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas6MouseEntered

    private void btnVentas6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas6ActionPerformed
         Caja_Abono CA = new Caja_Abono();
        CA.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        CA.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas6ActionPerformed

    private void btnVentas7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas7MouseClicked

    private void btnVentas7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas7MouseEntered

    private void btnVentas7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas7ActionPerformed
         Caja_Empresa_jerarquia CEJFP = new Caja_Empresa_jerarquia();
        CEJFP.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        CEJFP.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas7ActionPerformed

    private void btnVentas8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas8MouseClicked

    private void btnVentas8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas8MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas8MouseEntered

    private void btnVentas8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas8ActionPerformed
        Caja_Transaccion CT = new Caja_Transaccion();
        CT.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        CT.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas8ActionPerformed

    private void btnVentas9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas9MouseClicked

    private void btnVentas9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas9MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas9MouseEntered

    private void btnVentas9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas9ActionPerformed
        Caja_Jerarquia CJ = new Caja_Jerarquia();
        CJ.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        CJ.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas9ActionPerformed

    private void lblUsuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsuMouseClicked
//         dispose();
//        inicioSesion i=new inicioSesion();
//        i.setVisible(true);
    }//GEN-LAST:event_lblUsuMouseClicked

    private void lblUsu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsu1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUsu1MouseClicked

    private void btneditar28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar28ActionPerformed
        VentasConsolidado vc=new VentasConsolidado();
       vc.setVisible(true);
    }//GEN-LAST:event_btneditar28ActionPerformed

    private void lblUsu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsu2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUsu2MouseClicked

    private void lblUsu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsu3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUsu3MouseClicked

    private void lblUsu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsu4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUsu4MouseClicked

    private void lblUsu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsu5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUsu5MouseClicked

    private void lblUsu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsu6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUsu6MouseClicked

    private void btnPersonal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonal1ActionPerformed
        RegistroUsuario frm = new RegistroUsuario();
        frm.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        frm.lblUsuUsuario.setText(u);
    }//GEN-LAST:event_btnPersonal1ActionPerformed

    private void btnVentas10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas10MouseClicked

    private void btnVentas10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas10MouseEntered

    private void btnVentas10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas10ActionPerformed
        Impresoras CI = new Impresoras();
        CI.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        CI.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas10ActionPerformed

    private void btnVentas11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas11MouseClicked

    private void btnVentas11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVentas11MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentas11MouseEntered

    private void btnVentas11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentas11ActionPerformed
        CAJA_FR_ANULADOS CFR = new CAJA_FR_ANULADOS();
        CFR.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        CFR.lblusu.setText(u);
    }//GEN-LAST:event_btnVentas11ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        PERSONAL_ASIGNACION_PERSONAL CFR = new PERSONAL_ASIGNACION_PERSONAL();
        CFR.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        CFR.lblusu.setText(u);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void btnConsultorio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultorio1ActionPerformed
       Consultorio CFR = new Consultorio();
        CFR.setVisible(true);
        String u=PrincipalMDI1.lblUsu.getText();
        //CFR.lblusu.setText(u);
    }//GEN-LAST:event_btnConsultorio1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel1MouseClicked


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
            java.util.logging.Logger.getLogger(PrincipalMDI1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalMDI1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalMDI1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalMDI1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalMDI1().setVisible(true);
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
    public static javax.swing.JButton btnAdmEme1;
    public static javax.swing.JButton btnAdmEme2;
    private javax.swing.JButton btnAlertConsulta10;
    private javax.swing.JButton btnAlertConsulta7;
    private javax.swing.JButton btnAlertConsulta9;
    private javax.swing.JButton btnAnalisis;
    private javax.swing.JButton btnAnularVenta;
    public static javax.swing.JButton btnCaja;
    private javax.swing.JButton btnClasificacion;
    public static javax.swing.JButton btnConExt;
    private javax.swing.JButton btnConsultorio;
    private javax.swing.JButton btnConsultorio1;
    private javax.swing.JButton btnContenedor;
    private javax.swing.JButton btnConversion;
    public static javax.swing.JButton btnCostos;
    public static javax.swing.JButton btnEcografia;
    public static javax.swing.JButton btnEcografia1;
    public static javax.swing.JButton btnEcografia2;
    private javax.swing.JButton btnEntrega;
    private javax.swing.JButton btnEsquema;
    public static javax.swing.JButton btnHospitalizacion;
    public static javax.swing.JButton btnInvBact;
    public static javax.swing.JButton btnLaboratorio;
    public static javax.swing.JButton btnLaboratorio2;
    private javax.swing.JButton btnMantLlegada2;
    private javax.swing.JButton btnMuestras;
    private javax.swing.JButton btnPaciente;
    public static javax.swing.JButton btnPersonal;
    public static javax.swing.JButton btnPersonal1;
    public static javax.swing.JButton btnRayosX;
    private javax.swing.JButton btnReporTopico1;
    private javax.swing.JButton btnReportCabecera1;
    private javax.swing.JButton btnReportMovinientoHC;
    private javax.swing.JButton btnReportSeguimientoHC;
    private javax.swing.JButton btnReportTriaje1;
    private javax.swing.JButton btnResultado;
    private javax.swing.JButton btnResultadoBusq;
    private javax.swing.JButton btnSectorizacion;
    private javax.swing.JButton btnSolicitudInv;
    private javax.swing.JButton btnSolicitudRecep;
    private javax.swing.JButton btnTM;
    private javax.swing.JButton btnTMBusqueda;
    private javax.swing.JButton btnTriaje;
    private javax.swing.JButton btnUnidadMedi;
    private javax.swing.JButton btnValores;
    public static javax.swing.JButton btnVentas;
    public static javax.swing.JButton btnVentas1;
    public static javax.swing.JButton btnVentas10;
    public static javax.swing.JButton btnVentas11;
    public static javax.swing.JButton btnVentas2;
    public static javax.swing.JButton btnVentas3;
    public static javax.swing.JButton btnVentas4;
    public static javax.swing.JButton btnVentas5;
    public static javax.swing.JButton btnVentas6;
    public static javax.swing.JButton btnVentas7;
    public static javax.swing.JButton btnVentas8;
    public static javax.swing.JButton btnVentas9;
    private javax.swing.JButton btneditar11;
    private javax.swing.JButton btneditar21;
    private javax.swing.JButton btneditar22;
    private javax.swing.JButton btneditar23;
    private javax.swing.JButton btneditar25;
    private javax.swing.JButton btneditar26;
    private javax.swing.JButton btneditar27;
    private javax.swing.JButton btneditar28;
    private javax.swing.JButton btneditar4;
    private javax.swing.JButton btneditar5;
    private javax.swing.JButton btneditar6;
    private javax.swing.JButton btneditar7;
    private javax.swing.JButton btneditar8;
    private javax.swing.JButton btneditar9;
    private javax.swing.JButton btnguardar24;
    private javax.swing.JButton btnguardar26;
    private javax.swing.JButton btnguardar27;
    private javax.swing.JButton btnguardar8;
    private javax.swing.JButton btnguardar9;
    public static javax.swing.JLabel ibiIDAPERTURA;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel100;
    private javax.swing.JPanel jPanel101;
    private javax.swing.JPanel jPanel102;
    private javax.swing.JPanel jPanel104;
    private javax.swing.JPanel jPanel105;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel112;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel121;
    private javax.swing.JPanel jPanel122;
    private javax.swing.JPanel jPanel126;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel132;
    private javax.swing.JPanel jPanel133;
    private javax.swing.JPanel jPanel134;
    private javax.swing.JPanel jPanel135;
    private javax.swing.JPanel jPanel136;
    private javax.swing.JPanel jPanel137;
    private javax.swing.JPanel jPanel138;
    private javax.swing.JPanel jPanel139;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel140;
    private javax.swing.JPanel jPanel141;
    private javax.swing.JPanel jPanel142;
    private javax.swing.JPanel jPanel143;
    private javax.swing.JPanel jPanel144;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel147;
    private javax.swing.JPanel jPanel148;
    private javax.swing.JPanel jPanel149;
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
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel86;
    private javax.swing.JPanel jPanel88;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel95;
    private javax.swing.JPanel jPanel96;
    private javax.swing.JPanel jPanel97;
    private javax.swing.JPanel jPanel98;
    private javax.swing.JPanel jPanel99;
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
    public static javax.swing.JLabel lblSe_ID;
    public static javax.swing.JLabel lblServicio;
    public static javax.swing.JLabel lblUsu;
    public static javax.swing.JLabel lblUsu1;
    public static javax.swing.JLabel lblUsu2;
    public static javax.swing.JLabel lblUsu3;
    public static javax.swing.JLabel lblUsu4;
    public static javax.swing.JLabel lblUsu5;
    public static javax.swing.JLabel lblUsu6;
    private javax.swing.JLabel lblusu10;
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
