/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import campos.LimitadorDeDocumento;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import javax.swing.JComponent;
import javax.swing.JLabel;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAn;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalEm;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalFd;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalFu;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalHo;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalPt;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalTs;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalVg;
import modelos.ConsultorioEx.ConsultorioExtEsnitss;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;

/**
 *
 * @author MYS1
 */
public class RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    JLabel cie10;
    public static String opcionGuardar = "";
    public RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V() {
        initComponents();
        QuitarLaBarraTitulo();
        this.getContentPane().setBackground(Color.WHITE);
        pnlMensaje.setVisible(false);
        FrmCie10.setLocationRelativeTo(null);//en el centro
        FrmCie10.getContentPane().setBackground(new Color(0,153,102));
        
        FrmCie11.setLocationRelativeTo(null);//en el centro
        FrmCie11.getContentPane().setBackground(new Color(0,153,102));
        ConsultorioExtCarnetPerinatalHo CCDBUSCAR = new ConsultorioExtCarnetPerinatalHo();
        CCDBUSCAR.cargarDatosCie10("", tbCiePresun);
        
        ConsultorioExtCarnetPerinatalEm CCDBUSCAR1 = new ConsultorioExtCarnetPerinatalEm();
        CCDBUSCAR1.cargarDatosCie10("", tbCiePresun1);
    }
 public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
        //ANTITETANICA
        LimitadorDeDocumento limitDosisPrevia = new LimitadorDeDocumento(5);
        txtNDosisPrevia.setDocument(limitDosisPrevia);
        LimitadorDeDocumento limitDosis1 = new LimitadorDeDocumento(1);
        txtDosis1.setDocument(limitDosis1);
        LimitadorDeDocumento limitDosis2 = new LimitadorDeDocumento(1);
        txtDosis2.setDocument(limitDosis2);
        LimitadorDeDocumento limitEco = new LimitadorDeDocumento(2);
        txtEcografia.setDocument(limitEco);
    }
  public void enviarDiagnosticos(JLabel cie10){
        int fila = tbCiePresun.getSelectedRow();
        FrmCie10.dispose();
        lblCie10.setText(String.valueOf(tbCiePresun.getValueAt(fila, 1)));
        txtdes1.setText(String.valueOf(tbCiePresun.getValueAt(fila, 2)));
        lblIdCie10.setText(String.valueOf(tbCiePresun.getValueAt(fila, 0)));
        txtCIE10.setText(String.valueOf(tbCiePresun.getValueAt(fila, 1)));
    }
  
  public void enviarDiagnosticos1(JLabel cie10){
        int fila = tbCiePresun1.getSelectedRow();
        FrmCie11.dispose();
        lblCie10E.setText(String.valueOf(tbCiePresun1.getValueAt(fila, 1)));
        txtdesCie10E.setText(String.valueOf(tbCiePresun1.getValueAt(fila, 2)));
        lblIdCie10E.setText(String.valueOf(tbCiePresun1.getValueAt(fila, 0)));
        txtCie10E.setText(String.valueOf(tbCiePresun1.getValueAt(fila, 1)));
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
 
    public boolean mantenimientoPT(){
        boolean retorna = false;
        try {
            ConsultorioExtCarnetPerinatalPt consultorio1 = new ConsultorioExtCarnetPerinatalPt();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMant.getText().equals("U") || lblMant.getText().equals("E"))
                consultorio1.setPtId(Integer.parseInt(lblIdPeso.getText()));
            consultorio1.setCpId(Integer.parseInt(lblCpId.getText()));
            consultorio1.setPtPeso(txtPeso.getText());
            consultorio1.setPtTalla(txtTalla.getText());
            consultorio1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalPt(lblMant.getText())==true){
                if(lblMant.getText().equals("I")){
                    lblIdPeso.setText(consultorio1.perinatalPtID());
                    lblMant.setText("U");
                }
                txtPeso.setEditable(false);
                txtTalla.setEditable(false);
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
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
            System.out.println("Error: mantenimientoPT" + e.getMessage());
        }
        return retorna;
    }
    
    public boolean mantenimientoAN(){
        boolean retorna = false;
        try {
            ConsultorioExtCarnetPerinatalAn consultorio1 = new ConsultorioExtCarnetPerinatalAn();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMantAn.getText().equals("U") || lblMantAn.getText().equals("E"))
                consultorio1.setAnId(Integer.parseInt(lblIdAn.getText()));
            consultorio1.setCpId(Integer.parseInt(lblCpId.getText()));
            consultorio1.setAnDosisPrevia(txtNDosisPrevia.getText());
            consultorio1.setAn1raDosis(txtDosis1.getText());
            consultorio1.setAn2raDosis(txtDosis2.getText());
            if(txtSinDosis1.getText().equals("X"))
                consultorio1.setAn1raDAplicacion("Sin dosis");
            else
                consultorio1.setAn1raDAplicacion("No aplica");
            if(txtSinDosis2.getText().equals("X"))
                consultorio1.setAn2raDAplicacion("Sin dosis");
            else
                consultorio1.setAn2raDAplicacion("No aplica");
            consultorio1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalAn(lblMantAn.getText())==true){
                if(lblMantAn.getText().equals("I")){
                    lblIdAn.setText(consultorio1.perinatalAnID());
                    lblMantAn.setText("U");
                }
                txtNDosisPrevia.setEditable(false);
                txtDosis1.setEditable(false);
                txtDosis2.setEditable(false);
                txtSinDosis1.setEditable(false);
                txtSinDosis2.setEditable(false);
                txtNoAplica1.setEditable(false);
                txtNoAplica1.setEditable(false);
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
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
            System.out.println("Error: mantenimientoAN" + e.getMessage());
        }
        return retorna;
    }
 
    public boolean mantenimientoTS(){
        boolean retorna = false;
        try {
            ConsultorioExtCarnetPerinatalTs consultorio1 = new ConsultorioExtCarnetPerinatalTs();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMantGs.getText().equals("U") || lblMantGs.getText().equals("E"))
                consultorio1.setTsId(Integer.parseInt(lblIdGs.getText()));
            consultorio1.setCpId(Integer.parseInt(lblCpId.getText()));
            if(txtA.getText().equals("X"))
                consultorio1.setTsGrupo("A");
            if(txtB.getText().equals("X"))
                consultorio1.setTsGrupo("B");
            if(txtAB.getText().equals("X"))
                consultorio1.setTsGrupo("AB");
            if(txtO.getText().equals("X"))
                consultorio1.setTsGrupo("O");
            if(txtRhPositivo.getText().equals("X"))
                consultorio1.setTsRh("RH+");
            if(txtRhSen.getText().equals("X"))
                consultorio1.setTsRh("RH+ SEN DESC");
            if(txtNoDesc.getText().equals("X"))
                consultorio1.setTsRh("RH- NO DESC");
            if(txtRhNegativo.getText().equals("X"))
                consultorio1.setTsRh("RH- SEN");
            consultorio1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalTs(lblMantGs.getText())==true){
                if(lblMantGs.getText().equals("I")){
                    lblIdGs.setText(consultorio1.perinatalTsID());
//                    lblMantGs.setText("U");
                }
                lblMantGs.setText("");
                txtRhNegativo.setEditable(false);
                txtNoDesc.setEditable(false);
                txtRhSen.setEditable(false);
                txtRhPositivo.setEditable(false);
                txtO.setEditable(false);
                txtAB.setEditable(false);
                txtB.setEditable(false);
                txtA.setEditable(false);
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
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
            System.out.println("Error: mantenimientoTS" + e.getMessage());
        }
        return retorna;
    }
    
    public boolean mantenimientoFD(){
        boolean retorna = false;
        try {
            ConsultorioExtCarnetPerinatalFd consultorio1 = new ConsultorioExtCarnetPerinatalFd();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMantFd.getText().equals("U") || lblMantFd.getText().equals("E"))
                consultorio1.setFdId(Integer.parseInt(lblIdFd.getText()));
            consultorio1.setFdNCigarros(txtNCigarros.getText());
            consultorio1.setCpId(Integer.parseInt(lblCpId.getText()));
            if(chkFdSi.getText().equals("X"))
                consultorio1.setFdDroga("SI");
            if(chkFdNo.getText().equals("X"))
                consultorio1.setFdDroga("NO");
            consultorio1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalFd(lblMantFd.getText())==true){
                if(lblMantFd.getText().equals("I")){
                    lblIdFd.setText(consultorio1.perinatalFdID());
//                    lblMantGs.setText("U");
                }
                lblMantFd.setText("");
                txtNCigarros.setEditable(false);
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
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
            System.out.println("Error: mantenimientoFD" + e.getMessage());
        }
        return retorna;
    }
    
    public boolean mantenimientoFU(){
        boolean retorna = false;
        try {
            ConsultorioExtCarnetPerinatalFu consultorio1 = new ConsultorioExtCarnetPerinatalFu();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMantFum.getText().equals("U") || lblMantFum.getText().equals("E"))
                consultorio1.setFuId(Integer.parseInt(lblIdFum.getText()));
            consultorio1.setCpId(Integer.parseInt(lblCpId.getText()));
            consultorio1.setFuFechaUltMens(determinarFecha(dtFUM));
            if(chkDudaSi.getText().equals("X"))
                consultorio1.setFuDudaFecha("SI");
            if(chkDudaNo.getText().equals("X"))
                consultorio1.setFuDudaFecha("NO");
            consultorio1.setFuEco(txtEcografia.getText());
            consultorio1.setFuFechaEco(determinarFecha(dtFechaEco));
            consultorio1.setFuFechaPParto(determinarFecha(dtFechaProbableParto));
            consultorio1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalFu(lblMantFum.getText())==true){
                if(lblMantFum.getText().equals("I")){
                    lblIdFum.setText(consultorio1.perinatalFuID());
//                    lblMantGs.setText("U");
                }
                lblMantFum.setText("");
                dtFUM.setEnabled(false);
                dtFechaEco.setEnabled(false);
                dtFechaProbableParto.setEnabled(false);
                txtEcografia.setEditable(false);
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
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
            System.out.println("Error: mantenimientoFD" + e.getMessage());
        }
        return retorna;
    }
    
    
        public boolean mantenimientoHo(){
        boolean retorna = false;
        try {
            ConsultorioExtCarnetPerinatalHo consultorio1 = new ConsultorioExtCarnetPerinatalHo();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMantHo.getText().equals("U") || lblMantHo.getText().equals("E"))
                consultorio1.setHoId(Integer.parseInt(lblIdHos.getText()));
            consultorio1.setCpId(Integer.parseInt(lblCpId.getText()));
            consultorio1.setHoFecha(determinarFecha(fechaf3));
            if(chkHsi.getText().equals("X"))
                consultorio1.setHoHosp("SI");
            if(chkHno.getText().equals("X"))
                consultorio1.setHoHosp("NO");
            consultorio1.setId_cie10(Integer.parseInt(lblIdCie10.getText()));
            consultorio1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalFu(lblMantHo.getText())==true){
                if(lblMantHo.getText().equals("I")){
                    lblIdHos.setText(consultorio1.perinatalHoID());
//                    lblMantGs.setText("U");
                }
                lblMantHo.setText("");
                fechaf3.setEnabled(false);
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
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
            System.out.println("Error: mantenimientoFD" + e.getMessage());
        }
        return retorna;
    }
        
        public boolean mantenimientoEme(){
        boolean retorna = false;
        try {
            ConsultorioExtCarnetPerinatalEm consultorio1 = new ConsultorioExtCarnetPerinatalEm();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMantEme.getText().equals("U") || lblMantEme.getText().equals("E"))
                consultorio1.setEmId(Integer.parseInt(lblIdEme.getText()));
            consultorio1.setCpId(Integer.parseInt(lblCpId.getText()));
            consultorio1.setEmFecha(determinarFecha(fechaEmer));
            consultorio1.setId_cie10(Integer.parseInt(lblIdCie10E.getText()));
            consultorio1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalEm(lblMantEme.getText())==true){
                if(lblMantEme.getText().equals("I")){
                    lblIdEme.setText(consultorio1.perinatalEmID());
//                    lblMantGs.setText("U");
                }
                lblMantEme.setText("");
                fechaEmer.setEnabled(false);
               
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
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
            System.out.println("Error: mantenimientoFD" + e.getMessage());
        }
        return retorna;
    }
        
        public boolean mantenimientoVG(){
        boolean retorna = false;
        try {
            ConsultorioExtCarnetPerinatalVg consultorio1 = new ConsultorioExtCarnetPerinatalVg();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMantVG.getText().equals("U") || lblMantVG.getText().equals("E"))
                consultorio1.setVgId(Integer.parseInt(lblIdVG.getText()));
            consultorio1.setCpId(Integer.parseInt(lblCpId.getText()));
            consultorio1.setVgFecha(determinarFecha(FechaVG));
            if(chkTsi.getText().equals("X"))
                consultorio1.setVgFichaTamizaje("SI");
            if(chkTno.getText().equals("X"))
                consultorio1.setVgFichaTamizaje("NO");
            
            if(chkVsi.getText().equals("X"))
                consultorio1.setVgViolencia("SI");
            if(chkVno.getText().equals("X"))
                consultorio1.setVgViolencia("NO");
            
            consultorio1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalVg(lblMantVG.getText())==true){
                if(lblMantVG.getText().equals("I")){
                    lblIdVG.setText(consultorio1.perinatalVgID());
//                    lblMantGs.setText("U");
                }
                lblMantVG.setText("");
                FechaVG.setEnabled(false);
         
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
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
            System.out.println("Error: mantenimientoFD" + e.getMessage());
        }
        return retorna;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FrmCie10 = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        titulo7 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        T7 = new javax.swing.JLabel();
        txtBuscarCie10 = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbCiePresun = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            FrmCie11 = new javax.swing.JDialog();
            jPanel16 = new javax.swing.JPanel();
            titulo8 = new javax.swing.JLabel();
            jLabel49 = new javax.swing.JLabel();
            jPanel34 = new javax.swing.JPanel();
            T8 = new javax.swing.JLabel();
            txtBuscarCie11 = new javax.swing.JTextField();
            jPanel35 = new javax.swing.JPanel();
            jScrollPane5 = new javax.swing.JScrollPane();
            tbCiePresun1 = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                P1 = new javax.swing.JPanel();
                jPanel1 = new javax.swing.JPanel();
                jLabel31 = new javax.swing.JLabel();
                jLabel29 = new javax.swing.JLabel();
                txtPeso = new javax.swing.JTextField();
                txtTalla = new javax.swing.JTextField();
                jLabel40 = new javax.swing.JLabel();
                jLabel32 = new javax.swing.JLabel();
                lblIdPeso = new javax.swing.JLabel();
                P3 = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                txtNDosisPrevia = new javax.swing.JTextField();
                jLabel42 = new javax.swing.JLabel();
                jLabel43 = new javax.swing.JLabel();
                txtDosis1 = new javax.swing.JTextField();
                txtDosis2 = new javax.swing.JTextField();
                jLabel46 = new javax.swing.JLabel();
                jLabel47 = new javax.swing.JLabel();
                jLabel48 = new javax.swing.JLabel();
                txtSinDosis1 = new javax.swing.JTextField();
                txtNoAplica1 = new javax.swing.JTextField();
                txtSinDosis2 = new javax.swing.JTextField();
                txtNoAplica2 = new javax.swing.JTextField();
                jLabel44 = new javax.swing.JLabel();
                jLabel45 = new javax.swing.JLabel();
                lblIdAn = new javax.swing.JLabel();
                lblMantAn = new javax.swing.JLabel();
                P5 = new javax.swing.JPanel();
                jPanel19 = new javax.swing.JPanel();
                jLabel63 = new javax.swing.JLabel();
                chkHno = new javax.swing.JTextField();
                jLabel62 = new javax.swing.JLabel();
                chkHsi = new javax.swing.JTextField();
                jLabel64 = new javax.swing.JLabel();
                jLabel65 = new javax.swing.JLabel();
                fechaf3 = new com.toedter.calendar.JDateChooser();
                jLabel66 = new javax.swing.JLabel();
                jPanel20 = new javax.swing.JPanel();
                txtCIE10 = new javax.swing.JTextField();
                btnBuscarNino2 = new javax.swing.JButton();
                jLabel67 = new javax.swing.JLabel();
                lblCie10 = new javax.swing.JLabel();
                jScrollPane2 = new javax.swing.JScrollPane();
                txtdes1 = new javax.swing.JEditorPane();
                lblIdCie10 = new javax.swing.JLabel();
                lblIdHos = new javax.swing.JLabel();
                lblMantHo = new javax.swing.JLabel();
                P4 = new javax.swing.JPanel();
                jPanel17 = new javax.swing.JPanel();
                jLabel54 = new javax.swing.JLabel();
                dtFUM = new com.toedter.calendar.JDateChooser();
                chkDudaSi = new javax.swing.JTextField();
                chkDudaNo = new javax.swing.JTextField();
                jLabel55 = new javax.swing.JLabel();
                jLabel56 = new javax.swing.JLabel();
                jLabel57 = new javax.swing.JLabel();
                txtEcografia = new javax.swing.JTextField();
                jLabel58 = new javax.swing.JLabel();
                chkNoAplica = new javax.swing.JTextField();
                jLabel59 = new javax.swing.JLabel();
                jLabel60 = new javax.swing.JLabel();
                dtFechaEco = new com.toedter.calendar.JDateChooser();
                jLabel61 = new javax.swing.JLabel();
                dtFechaProbableParto = new com.toedter.calendar.JDateChooser();
                jLabel84 = new javax.swing.JLabel();
                P6 = new javax.swing.JPanel();
                jPanel25 = new javax.swing.JPanel();
                jLabel73 = new javax.swing.JLabel();
                chkTsi = new javax.swing.JTextField();
                jLabel74 = new javax.swing.JLabel();
                jLabel75 = new javax.swing.JLabel();
                chkTno = new javax.swing.JTextField();
                jLabel76 = new javax.swing.JLabel();
                chkVsi = new javax.swing.JTextField();
                chkVno = new javax.swing.JTextField();
                jLabel77 = new javax.swing.JLabel();
                jLabel78 = new javax.swing.JLabel();
                jLabel79 = new javax.swing.JLabel();
                FechaVG = new com.toedter.calendar.JDateChooser();
                lblMantVG = new javax.swing.JLabel();
                lblIdVG = new javax.swing.JLabel();
                P9 = new javax.swing.JPanel();
                jLabel8 = new javax.swing.JLabel();
                jPanel27 = new javax.swing.JPanel();
                jLabel80 = new javax.swing.JLabel();
                txtNCigarros = new javax.swing.JTextField();
                jPanel7 = new javax.swing.JPanel();
                lblIdFd = new javax.swing.JLabel();
                lblMantFd = new javax.swing.JLabel();
                P2 = new javax.swing.JPanel();
                jPanel3 = new javax.swing.JPanel();
                jLabel33 = new javax.swing.JLabel();
                jPanel4 = new javax.swing.JPanel();
                txtA = new javax.swing.JTextField();
                jLabel34 = new javax.swing.JLabel();
                jPanel5 = new javax.swing.JPanel();
                txtB = new javax.swing.JTextField();
                jLabel35 = new javax.swing.JLabel();
                jPanel9 = new javax.swing.JPanel();
                txtAB = new javax.swing.JTextField();
                jLabel36 = new javax.swing.JLabel();
                jPanel10 = new javax.swing.JPanel();
                txtO = new javax.swing.JTextField();
                jLabel38 = new javax.swing.JLabel();
                jLabel39 = new javax.swing.JLabel();
                jPanel11 = new javax.swing.JPanel();
                txtRhPositivo = new javax.swing.JTextField();
                jLabel50 = new javax.swing.JLabel();
                jPanel12 = new javax.swing.JPanel();
                txtRhSen = new javax.swing.JTextField();
                jLabel51 = new javax.swing.JLabel();
                jPanel13 = new javax.swing.JPanel();
                txtNoDesc = new javax.swing.JTextField();
                jLabel52 = new javax.swing.JLabel();
                jPanel14 = new javax.swing.JPanel();
                txtRhNegativo = new javax.swing.JTextField();
                jLabel53 = new javax.swing.JLabel();
                lblIdGs = new javax.swing.JLabel();
                lblMantGs = new javax.swing.JLabel();
                PEmergencia = new javax.swing.JPanel();
                jPanel22 = new javax.swing.JPanel();
                fechaEmer = new com.toedter.calendar.JDateChooser();
                jLabel69 = new javax.swing.JLabel();
                jLabel70 = new javax.swing.JLabel();
                jPanel23 = new javax.swing.JPanel();
                txtCie10E = new javax.swing.JTextField();
                btnBuscarNino3 = new javax.swing.JButton();
                jLabel71 = new javax.swing.JLabel();
                lblCie10E = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                txtdesCie10E = new javax.swing.JEditorPane();
                lblMantEme = new javax.swing.JLabel();
                lblIdEme = new javax.swing.JLabel();
                lblIdCie10E = new javax.swing.JLabel();
                P8 = new javax.swing.JPanel();
                jPanel29 = new javax.swing.JPanel();
                jPanel31 = new javax.swing.JPanel();
                chkFdSi = new javax.swing.JTextField();
                jLabel81 = new javax.swing.JLabel();
                jPanel32 = new javax.swing.JPanel();
                chkFdNo = new javax.swing.JTextField();
                jLabel82 = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                jPanel6 = new javax.swing.JPanel();
                jPanel28 = new javax.swing.JPanel();
                LblTitulo = new javax.swing.JLabel();
                lblusu = new javax.swing.JLabel();
                btnCaccnelar = new javax.swing.JButton();
                btnGuardar = new javax.swing.JButton();
                jLabel26 = new javax.swing.JLabel();
                ChkAnalf1 = new javax.swing.JTextField();
                jLabel30 = new javax.swing.JLabel();
                ChkEdad1 = new javax.swing.JTextField();
                jLabel37 = new javax.swing.JLabel();
                jPanel42 = new javax.swing.JPanel();
                jLabel83 = new javax.swing.JLabel();
                btnModificar = new javax.swing.JButton();
                lblCpId = new javax.swing.JLabel();
                pnlMensaje = new javax.swing.JPanel();
                lblMensaje = new javax.swing.JLabel();
                btnSi = new javax.swing.JButton();
                btnNo = new javax.swing.JButton();
                lblMadre = new javax.swing.JLabel();
                lblMant = new javax.swing.JLabel();
                lblMantFum = new javax.swing.JLabel();
                lblIdFum = new javax.swing.JLabel();

                FrmCie10.setMinimumSize(new java.awt.Dimension(750, 400));
                FrmCie10.setResizable(false);

                jPanel15.setBackground(new java.awt.Color(102, 102, 102));
                jPanel15.setPreferredSize(new java.awt.Dimension(500, 65));

                titulo7.setBackground(new java.awt.Color(153, 0, 51));
                titulo7.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                titulo7.setForeground(new java.awt.Color(255, 255, 255));
                titulo7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                titulo7.setText("CIE 10");
                titulo7.setToolTipText("");
                titulo7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel41.setForeground(new java.awt.Color(255, 255, 255));
                jLabel41.setText("Código CIE 10 / Diagnóstico Presuntivo");

                jPanel30.setBackground(new java.awt.Color(255, 255, 255));

                T7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                T7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                T7.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        T7MouseClicked(evt);
                    }
                });

                txtBuscarCie10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtBuscarCie10.setBorder(null);
                txtBuscarCie10.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscarCie10CaretUpdate(evt);
                    }
                });
                txtBuscarCie10.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscarCie10KeyPressed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                jPanel30.setLayout(jPanel30Layout);
                jPanel30Layout.setHorizontalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtBuscarCie10, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(T7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );
                jPanel30Layout.setVerticalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                                .addComponent(T7)
                                .addGap(4, 4, 4))
                            .addComponent(txtBuscarCie10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                );

                jPanel33.setBackground(new java.awt.Color(41, 127, 184));

                javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
                jPanel33.setLayout(jPanel33Layout);
                jPanel33Layout.setHorizontalGroup(
                    jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 750, Short.MAX_VALUE)
                );
                jPanel33Layout.setVerticalGroup(
                    jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 16, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                jPanel15.setLayout(jPanel15Layout);
                jPanel15Layout.setHorizontalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(449, 449, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel15Layout.setVerticalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(titulo7)
                        .addGap(9, 9, 9)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addGap(127, 127, 127)
                            .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                );

                jScrollPane4.setBorder(null);

                tbCiePresun.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
                tbCiePresun.setGridColor(new java.awt.Color(255, 255, 255));
                tbCiePresun.setRowHeight(25);
                tbCiePresun.setSelectionBackground(new java.awt.Color(50, 151, 219));
                tbCiePresun.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbCiePresunMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbCiePresunMousePressed(evt);
                    }
                });
                tbCiePresun.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbCiePresunKeyPressed(evt);
                    }
                });
                jScrollPane4.setViewportView(tbCiePresun);

                javax.swing.GroupLayout FrmCie10Layout = new javax.swing.GroupLayout(FrmCie10.getContentPane());
                FrmCie10.getContentPane().setLayout(FrmCie10Layout);
                FrmCie10Layout.setHorizontalGroup(
                    FrmCie10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                );
                FrmCie10Layout.setVerticalGroup(
                    FrmCie10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FrmCie10Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                );

                FrmCie11.setMinimumSize(new java.awt.Dimension(750, 400));
                FrmCie11.setResizable(false);

                jPanel16.setBackground(new java.awt.Color(102, 102, 102));
                jPanel16.setPreferredSize(new java.awt.Dimension(500, 65));

                titulo8.setBackground(new java.awt.Color(153, 0, 51));
                titulo8.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                titulo8.setForeground(new java.awt.Color(255, 255, 255));
                titulo8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                titulo8.setText("CIE 10");
                titulo8.setToolTipText("");
                titulo8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel49.setForeground(new java.awt.Color(255, 255, 255));
                jLabel49.setText("Código CIE 10 / Diagnóstico Presuntivo");

                jPanel34.setBackground(new java.awt.Color(255, 255, 255));

                T8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                T8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                T8.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        T8MouseClicked(evt);
                    }
                });

                txtBuscarCie11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtBuscarCie11.setBorder(null);
                txtBuscarCie11.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscarCie11CaretUpdate(evt);
                    }
                });
                txtBuscarCie11.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscarCie11KeyPressed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
                jPanel34.setLayout(jPanel34Layout);
                jPanel34Layout.setHorizontalGroup(
                    jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtBuscarCie11, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(T8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );
                jPanel34Layout.setVerticalGroup(
                    jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                                .addComponent(T8)
                                .addGap(4, 4, 4))
                            .addComponent(txtBuscarCie11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                );

                jPanel35.setBackground(new java.awt.Color(41, 127, 184));

                javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
                jPanel35.setLayout(jPanel35Layout);
                jPanel35Layout.setHorizontalGroup(
                    jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 750, Short.MAX_VALUE)
                );
                jPanel35Layout.setVerticalGroup(
                    jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 16, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                jPanel16.setLayout(jPanel16Layout);
                jPanel16Layout.setHorizontalGroup(
                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo8, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(449, 449, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel16Layout.setVerticalGroup(
                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(titulo8)
                        .addGap(9, 9, 9)
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel16Layout.createSequentialGroup()
                            .addGap(127, 127, 127)
                            .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(184, Short.MAX_VALUE)))
                );

                jScrollPane5.setBorder(null);

                tbCiePresun1.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
                tbCiePresun1.setGridColor(new java.awt.Color(255, 255, 255));
                tbCiePresun1.setRowHeight(25);
                tbCiePresun1.setSelectionBackground(new java.awt.Color(50, 151, 219));
                tbCiePresun1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbCiePresun1MouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbCiePresun1MousePressed(evt);
                    }
                });
                tbCiePresun1.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbCiePresun1KeyPressed(evt);
                    }
                });
                jScrollPane5.setViewportView(tbCiePresun1);

                javax.swing.GroupLayout FrmCie11Layout = new javax.swing.GroupLayout(FrmCie11.getContentPane());
                FrmCie11.getContentPane().setLayout(FrmCie11Layout);
                FrmCie11Layout.setHorizontalGroup(
                    FrmCie11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addComponent(jScrollPane5)
                );
                FrmCie11Layout.setVerticalGroup(
                    FrmCie11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FrmCie11Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                );

                setBackground(new java.awt.Color(255, 255, 255));
                setBorder(javax.swing.BorderFactory.createCompoundBorder());
                setVisible(true);

                P1.setBackground(new java.awt.Color(255, 255, 255));

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));

                jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel31.setForeground(new java.awt.Color(51, 51, 51));
                jLabel31.setText("Kg");

                jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel29.setForeground(new java.awt.Color(51, 51, 51));
                jLabel29.setText("Peso Habitual");

                txtPeso.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtPeso.setForeground(new java.awt.Color(102, 102, 102));
                txtPeso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPeso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtPeso.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPesoCaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel31)
                        .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addContainerGap(31, Short.MAX_VALUE))
                );

                txtTalla.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtTalla.setForeground(new java.awt.Color(102, 102, 102));
                txtTalla.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtTalla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtTalla.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtTallaCaretUpdate(evt);
                    }
                });

                jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel40.setForeground(new java.awt.Color(51, 51, 51));
                jLabel40.setText("cm");

                jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel32.setForeground(new java.awt.Color(51, 51, 51));
                jLabel32.setText("Talla");

                javax.swing.GroupLayout P1Layout = new javax.swing.GroupLayout(P1);
                P1.setLayout(P1Layout);
                P1Layout.setHorizontalGroup(
                    P1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P1Layout.createSequentialGroup()
                        .addGroup(P1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(P1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(jLabel32)
                                .addGap(18, 18, 18)
                                .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel40))
                            .addGroup(P1Layout.createSequentialGroup()
                                .addGap(216, 216, 216)
                                .addComponent(lblIdPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(161, Short.MAX_VALUE))
                );
                P1Layout.setVerticalGroup(
                    P1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P1Layout.createSequentialGroup()
                        .addGroup(P1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(P1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(P1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel40)))
                            .addGroup(P1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIdPeso, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                );

                P3.setBackground(new java.awt.Color(255, 255, 255));

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                txtNDosisPrevia.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtNDosisPrevia.setForeground(new java.awt.Color(102, 102, 102));
                txtNDosisPrevia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtNDosisPrevia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtNDosisPrevia.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtNDosisPreviaCaretUpdate(evt);
                    }
                });
                txtNDosisPrevia.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtNDosisPreviaKeyTyped(evt);
                    }
                });

                jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel42.setForeground(new java.awt.Color(51, 51, 51));
                jLabel42.setText("<html>Nº Dosis<br>Previa</br></span></html>");

                jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel43.setForeground(new java.awt.Color(51, 51, 51));
                jLabel43.setText("1ª");

                txtDosis1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtDosis1.setForeground(new java.awt.Color(102, 102, 102));
                txtDosis1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDosis1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtDosis1.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDosis1CaretUpdate(evt);
                    }
                });
                txtDosis1.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtDosis1KeyTyped(evt);
                    }
                });

                txtDosis2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtDosis2.setForeground(new java.awt.Color(102, 102, 102));
                txtDosis2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDosis2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtDosis2.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDosis2CaretUpdate(evt);
                    }
                });
                txtDosis2.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtDosis2KeyTyped(evt);
                    }
                });

                jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel46.setForeground(new java.awt.Color(51, 51, 51));
                jLabel46.setText("2ª");

                jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel47.setForeground(new java.awt.Color(51, 51, 51));
                jLabel47.setText("Dosis");

                jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel48.setForeground(new java.awt.Color(51, 51, 51));
                jLabel48.setText("mes de gestación");

                txtSinDosis1.setEditable(false);
                txtSinDosis1.setBackground(new java.awt.Color(255, 204, 51));
                txtSinDosis1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtSinDosis1.setForeground(new java.awt.Color(102, 102, 102));
                txtSinDosis1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtSinDosis1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtSinDosis1.setPreferredSize(new java.awt.Dimension(18, 18));
                txtSinDosis1.setSelectedTextColor(new java.awt.Color(109, 109, 109));
                txtSinDosis1.setSelectionColor(new java.awt.Color(255, 204, 51));
                txtSinDosis1.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtSinDosis1CaretUpdate(evt);
                    }
                });
                txtSinDosis1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtSinDosis1MouseClicked(evt);
                    }
                });

                txtNoAplica1.setEditable(false);
                txtNoAplica1.setBackground(new java.awt.Color(255, 255, 255));
                txtNoAplica1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtNoAplica1.setForeground(new java.awt.Color(102, 102, 102));
                txtNoAplica1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtNoAplica1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtNoAplica1.setPreferredSize(new java.awt.Dimension(18, 18));
                txtNoAplica1.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                txtNoAplica1.setSelectionColor(new java.awt.Color(255, 255, 255));
                txtNoAplica1.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtNoAplica1CaretUpdate(evt);
                    }
                });
                txtNoAplica1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtNoAplica1MouseClicked(evt);
                    }
                });

                txtSinDosis2.setEditable(false);
                txtSinDosis2.setBackground(new java.awt.Color(255, 204, 51));
                txtSinDosis2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtSinDosis2.setForeground(new java.awt.Color(102, 102, 102));
                txtSinDosis2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtSinDosis2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtSinDosis2.setPreferredSize(new java.awt.Dimension(18, 18));
                txtSinDosis2.setSelectedTextColor(new java.awt.Color(109, 109, 109));
                txtSinDosis2.setSelectionColor(new java.awt.Color(255, 204, 51));
                txtSinDosis2.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtSinDosis2CaretUpdate(evt);
                    }
                });
                txtSinDosis2.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtSinDosis2MouseClicked(evt);
                    }
                });

                txtNoAplica2.setEditable(false);
                txtNoAplica2.setBackground(new java.awt.Color(255, 255, 255));
                txtNoAplica2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtNoAplica2.setForeground(new java.awt.Color(102, 102, 102));
                txtNoAplica2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtNoAplica2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtNoAplica2.setPreferredSize(new java.awt.Dimension(18, 18));
                txtNoAplica2.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                txtNoAplica2.setSelectionColor(new java.awt.Color(255, 255, 255));
                txtNoAplica2.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtNoAplica2CaretUpdate(evt);
                    }
                });
                txtNoAplica2.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtNoAplica2MouseClicked(evt);
                    }
                });

                jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel44.setForeground(new java.awt.Color(51, 51, 51));
                jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel44.setText("<html>Sin<br>dosis</br></span></html>");

                jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel45.setForeground(new java.awt.Color(51, 51, 51));
                jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel45.setText("<html>No<br>aplica</br></span></html>");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtNDosisPrevia, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblIdAn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMantAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDosis2)
                                    .addComponent(txtDosis1)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel47)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtSinDosis1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(txtNoAplica1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtSinDosis2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(txtNoAplica2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33))
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel47))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtNoAplica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSinDosis1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDosis1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel43)))
                                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNoAplica2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSinDosis2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDosis2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel46)
                                    .addComponent(txtNDosisPrevia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(lblIdAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMantAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );

                javax.swing.GroupLayout P3Layout = new javax.swing.GroupLayout(P3);
                P3.setLayout(P3Layout);
                P3Layout.setHorizontalGroup(
                    P3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                P3Layout.setVerticalGroup(
                    P3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P3Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                P5.setBackground(new java.awt.Color(255, 255, 255));

                jPanel19.setBackground(new java.awt.Color(255, 255, 255));

                jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel63.setForeground(new java.awt.Color(51, 51, 51));
                jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel63.setText("Si");

                chkHno.setEditable(false);
                chkHno.setBackground(new java.awt.Color(255, 255, 255));
                chkHno.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                chkHno.setForeground(new java.awt.Color(102, 102, 102));
                chkHno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                chkHno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                chkHno.setPreferredSize(new java.awt.Dimension(18, 18));
                chkHno.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                chkHno.setSelectionColor(new java.awt.Color(255, 255, 255));
                chkHno.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        chkHnoCaretUpdate(evt);
                    }
                });
                chkHno.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chkHnoMouseClicked(evt);
                    }
                });

                jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel62.setForeground(new java.awt.Color(51, 51, 51));
                jLabel62.setText("Hospitalización");

                chkHsi.setEditable(false);
                chkHsi.setBackground(new java.awt.Color(255, 204, 51));
                chkHsi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                chkHsi.setForeground(new java.awt.Color(102, 102, 102));
                chkHsi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                chkHsi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                chkHsi.setPreferredSize(new java.awt.Dimension(18, 18));
                chkHsi.setSelectedTextColor(new java.awt.Color(109, 109, 109));
                chkHsi.setSelectionColor(new java.awt.Color(255, 204, 51));
                chkHsi.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        chkHsiCaretUpdate(evt);
                    }
                });
                chkHsi.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chkHsiMouseClicked(evt);
                    }
                });

                jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel64.setForeground(new java.awt.Color(51, 51, 51));
                jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel64.setText("No");

                jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel65.setForeground(new java.awt.Color(51, 51, 51));
                jLabel65.setText("Fecha");

                fechaf3.setBackground(new java.awt.Color(255, 255, 255));
                fechaf3.setDateFormatString("dd-MM-yyyy");

                jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel66.setForeground(new java.awt.Color(51, 51, 51));
                jLabel66.setText("Diagnóstico");

                jPanel20.setBackground(new java.awt.Color(255, 255, 255));
                jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtCIE10.setEditable(false);
                txtCIE10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                txtCIE10.setForeground(new java.awt.Color(102, 102, 102));
                txtCIE10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtCIE10.setBorder(null);
                txtCIE10.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCIE10CaretUpdate(evt);
                    }
                });

                btnBuscarNino2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarNino2.setMnemonic('B');
                btnBuscarNino2.setToolTipText("");
                btnBuscarNino2.setBorderPainted(false);
                btnBuscarNino2.setContentAreaFilled(false);
                btnBuscarNino2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarNino2.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        btnBuscarNino2MouseClicked(evt);
                    }
                });
                btnBuscarNino2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarNino2ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
                jPanel20.setLayout(jPanel20Layout);
                jPanel20Layout.setHorizontalGroup(
                    jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtCIE10, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarNino2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                jPanel20Layout.setVerticalGroup(
                    jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarNino2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtCIE10, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel67.setForeground(new java.awt.Color(51, 51, 51));
                jLabel67.setText("CIE 10");

                lblCie10.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
                lblCie10.setForeground(new java.awt.Color(102, 102, 102));
                lblCie10.setText("________________");

                txtdes1.setForeground(new java.awt.Color(51, 51, 51));
                txtdes1.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtdes1KeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtdes1KeyTyped(evt);
                    }
                });
                jScrollPane2.setViewportView(txtdes1);

                lblIdCie10.setText("jLabel1");

                lblMantHo.setText("I");

                javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
                jPanel19.setLayout(jPanel19Layout);
                jPanel19Layout.setHorizontalGroup(
                    jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel62)
                                    .addComponent(jLabel65)
                                    .addComponent(jLabel66))
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(chkHsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chkHno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(fechaf3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblCie10)
                                .addGap(41, 41, 41)
                                .addComponent(lblIdCie10)
                                .addGap(36, 36, 36)
                                .addComponent(lblIdHos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblMantHo)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel19Layout.setVerticalGroup(
                    jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblMantHo))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chkHno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chkHsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel62)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel64))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel65)
                                    .addComponent(fechaf3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel66))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel67)
                                            .addComponent(lblCie10)
                                            .addComponent(lblIdCie10))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(lblIdHos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())
                );

                javax.swing.GroupLayout P5Layout = new javax.swing.GroupLayout(P5);
                P5.setLayout(P5Layout);
                P5Layout.setHorizontalGroup(
                    P5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
                );
                P5Layout.setVerticalGroup(
                    P5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P5Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                P4.setBackground(new java.awt.Color(255, 255, 255));

                jPanel17.setBackground(new java.awt.Color(255, 255, 255));

                jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel54.setForeground(new java.awt.Color(51, 51, 51));
                jLabel54.setText("FUM");

                dtFUM.setBackground(new java.awt.Color(255, 255, 255));
                dtFUM.setDateFormatString("dd/MM/yyyy");
                dtFUM.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

                chkDudaSi.setEditable(false);
                chkDudaSi.setBackground(new java.awt.Color(255, 204, 51));
                chkDudaSi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                chkDudaSi.setForeground(new java.awt.Color(102, 102, 102));
                chkDudaSi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                chkDudaSi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                chkDudaSi.setPreferredSize(new java.awt.Dimension(18, 18));
                chkDudaSi.setSelectedTextColor(new java.awt.Color(109, 109, 109));
                chkDudaSi.setSelectionColor(new java.awt.Color(255, 204, 51));
                chkDudaSi.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        chkDudaSiCaretUpdate(evt);
                    }
                });
                chkDudaSi.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chkDudaSiMouseClicked(evt);
                    }
                });

                chkDudaNo.setEditable(false);
                chkDudaNo.setBackground(new java.awt.Color(255, 255, 255));
                chkDudaNo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                chkDudaNo.setForeground(new java.awt.Color(102, 102, 102));
                chkDudaNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                chkDudaNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                chkDudaNo.setPreferredSize(new java.awt.Dimension(18, 18));
                chkDudaNo.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                chkDudaNo.setSelectionColor(new java.awt.Color(255, 255, 255));
                chkDudaNo.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        chkDudaNoCaretUpdate(evt);
                    }
                });
                chkDudaNo.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chkDudaNoMouseClicked(evt);
                    }
                });

                jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel55.setForeground(new java.awt.Color(51, 51, 51));
                jLabel55.setText("Duda");

                jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel56.setForeground(new java.awt.Color(51, 51, 51));
                jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel56.setText("Si");

                jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel57.setForeground(new java.awt.Color(51, 51, 51));
                jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel57.setText("No");

                txtEcografia.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtEcografia.setForeground(new java.awt.Color(102, 102, 102));
                txtEcografia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtEcografia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtEcografia.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtEcografiaCaretUpdate(evt);
                    }
                });
                txtEcografia.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtEcografiaKeyTyped(evt);
                    }
                });

                jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel58.setForeground(new java.awt.Color(51, 51, 51));
                jLabel58.setText("EG. (Ecografía)");

                chkNoAplica.setEditable(false);
                chkNoAplica.setBackground(new java.awt.Color(255, 255, 255));
                chkNoAplica.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                chkNoAplica.setForeground(new java.awt.Color(102, 102, 102));
                chkNoAplica.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                chkNoAplica.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                chkNoAplica.setPreferredSize(new java.awt.Dimension(18, 18));
                chkNoAplica.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                chkNoAplica.setSelectionColor(new java.awt.Color(255, 255, 255));
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

                jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel59.setForeground(new java.awt.Color(51, 51, 51));
                jLabel59.setText("No Aplica");

                jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel60.setForeground(new java.awt.Color(51, 51, 51));
                jLabel60.setText("semana");

                dtFechaEco.setBackground(new java.awt.Color(255, 255, 255));
                dtFechaEco.setDateFormatString("dd/MM/yyyy");
                dtFechaEco.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

                jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                jLabel61.setForeground(new java.awt.Color(102, 102, 102));
                jLabel61.setText("Fecha Probable de Parto");

                dtFechaProbableParto.setBackground(new java.awt.Color(255, 255, 255));
                dtFechaProbableParto.setDateFormatString("dd/MM/yyyy");
                dtFechaProbableParto.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

                jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel84.setForeground(new java.awt.Color(51, 51, 51));
                jLabel84.setText("Fecha");

                javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                jPanel17.setLayout(jPanel17Layout);
                jPanel17Layout.setHorizontalGroup(
                    jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel54)
                                    .addComponent(jLabel58)
                                    .addComponent(jLabel59))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(txtEcografia, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel60)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                                        .addComponent(jLabel84)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dtFechaEco, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkNoAplica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel17Layout.createSequentialGroup()
                                                .addComponent(dtFUM, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel55)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(chkDudaSi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(chkDudaNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(35, 35, 35))))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel61)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dtFechaProbableParto, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(15, Short.MAX_VALUE))
                );
                jPanel17Layout.setVerticalGroup(
                    jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(dtFUM, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel54))
                                .addGap(48, 48, 48))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel55)
                                    .addComponent(chkDudaSi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chkDudaNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel57)
                                    .addComponent(jLabel56))
                                .addGap(51, 51, 51)))
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtFechaEco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtEcografia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel58)
                                .addComponent(jLabel60)
                                .addComponent(jLabel84)))
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chkNoAplica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel59))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                .addComponent(jLabel61))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(dtFechaProbableParto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                );

                javax.swing.GroupLayout P4Layout = new javax.swing.GroupLayout(P4);
                P4.setLayout(P4Layout);
                P4Layout.setHorizontalGroup(
                    P4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P4Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 23, Short.MAX_VALUE))
                );
                P4Layout.setVerticalGroup(
                    P4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P4Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                P6.setBackground(new java.awt.Color(255, 255, 255));

                jPanel25.setBackground(new java.awt.Color(255, 255, 255));

                jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel73.setForeground(new java.awt.Color(51, 51, 51));
                jLabel73.setText("Ficha Tamizaje");

                chkTsi.setEditable(false);
                chkTsi.setBackground(new java.awt.Color(255, 255, 255));
                chkTsi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                chkTsi.setForeground(new java.awt.Color(102, 102, 102));
                chkTsi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                chkTsi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                chkTsi.setPreferredSize(new java.awt.Dimension(18, 18));
                chkTsi.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                chkTsi.setSelectionColor(new java.awt.Color(255, 255, 255));
                chkTsi.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        chkTsiCaretUpdate(evt);
                    }
                });
                chkTsi.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chkTsiMouseClicked(evt);
                    }
                });

                jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel74.setForeground(new java.awt.Color(51, 51, 51));
                jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel74.setText("Si");

                jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel75.setForeground(new java.awt.Color(51, 51, 51));
                jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel75.setText("No");

                chkTno.setEditable(false);
                chkTno.setBackground(new java.awt.Color(255, 255, 255));
                chkTno.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                chkTno.setForeground(new java.awt.Color(102, 102, 102));
                chkTno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                chkTno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                chkTno.setPreferredSize(new java.awt.Dimension(18, 18));
                chkTno.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                chkTno.setSelectionColor(new java.awt.Color(255, 255, 255));
                chkTno.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        chkTnoCaretUpdate(evt);
                    }
                });
                chkTno.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chkTnoMouseClicked(evt);
                    }
                });

                jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel76.setForeground(new java.awt.Color(51, 51, 51));
                jLabel76.setText("Violencia");

                chkVsi.setEditable(false);
                chkVsi.setBackground(new java.awt.Color(255, 255, 255));
                chkVsi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                chkVsi.setForeground(new java.awt.Color(102, 102, 102));
                chkVsi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                chkVsi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                chkVsi.setPreferredSize(new java.awt.Dimension(18, 18));
                chkVsi.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                chkVsi.setSelectionColor(new java.awt.Color(255, 255, 255));
                chkVsi.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        chkVsiCaretUpdate(evt);
                    }
                });
                chkVsi.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chkVsiMouseClicked(evt);
                    }
                });

                chkVno.setEditable(false);
                chkVno.setBackground(new java.awt.Color(255, 255, 255));
                chkVno.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                chkVno.setForeground(new java.awt.Color(102, 102, 102));
                chkVno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                chkVno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                chkVno.setPreferredSize(new java.awt.Dimension(18, 18));
                chkVno.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                chkVno.setSelectionColor(new java.awt.Color(255, 255, 255));
                chkVno.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        chkVnoCaretUpdate(evt);
                    }
                });
                chkVno.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chkVnoMouseClicked(evt);
                    }
                });

                jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel77.setForeground(new java.awt.Color(51, 51, 51));
                jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel77.setText("Si");

                jLabel78.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel78.setForeground(new java.awt.Color(51, 51, 51));
                jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel78.setText("No");

                jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel79.setForeground(new java.awt.Color(51, 51, 51));
                jLabel79.setText("Fecha");

                FechaVG.setBackground(new java.awt.Color(255, 255, 255));
                FechaVG.setDateFormatString("dd-MM-yyyy");

                lblMantVG.setText("I");

                javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
                jPanel25.setLayout(jPanel25Layout);
                jPanel25Layout.setHorizontalGroup(
                    jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel76)
                                    .addComponent(jLabel73))
                                .addGap(57, 57, 57)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                        .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(chkTsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel25Layout.createSequentialGroup()
                                        .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(chkVsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                    .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel79)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(FechaVG, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkTno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkVno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(lblMantVG)
                                .addGap(18, 18, 18)
                                .addComponent(lblIdVG, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                );
                jPanel25Layout.setVerticalGroup(
                    jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkTsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel73)
                            .addComponent(jLabel74)
                            .addComponent(chkTno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel75))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkVsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel76)
                            .addComponent(chkVno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel78)
                            .addComponent(jLabel77))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(FechaVG, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel79)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblMantVG)
                                .addComponent(lblIdVG, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(47, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout P6Layout = new javax.swing.GroupLayout(P6);
                P6.setLayout(P6Layout);
                P6Layout.setHorizontalGroup(
                    P6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                P6Layout.setVerticalGroup(
                    P6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                P9.setBackground(new java.awt.Color(255, 255, 255));

                jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jLabel8.setForeground(new java.awt.Color(102, 102, 102));
                jLabel8.setText("Fuma");

                jPanel27.setBackground(new java.awt.Color(255, 255, 255));

                jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel80.setForeground(new java.awt.Color(51, 51, 51));
                jLabel80.setText("Nº de Cigarrillos");

                txtNCigarros.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtNCigarros.setForeground(new java.awt.Color(102, 102, 102));
                txtNCigarros.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtNCigarros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtNCigarros.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtNCigarrosCaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
                jPanel27.setLayout(jPanel27Layout);
                jPanel27Layout.setHorizontalGroup(
                    jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNCigarros, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                );
                jPanel27Layout.setVerticalGroup(
                    jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel80)
                            .addComponent(txtNCigarros, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                );

                jPanel7.setBackground(new java.awt.Color(39, 174, 97));
                jPanel7.setPreferredSize(new java.awt.Dimension(15, 34));

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 15, Short.MAX_VALUE)
                );
                jPanel7Layout.setVerticalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 37, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout P9Layout = new javax.swing.GroupLayout(P9);
                P9.setLayout(P9Layout);
                P9Layout.setHorizontalGroup(
                    P9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P9Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(217, 217, 217)
                        .addComponent(lblIdFd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMantFd, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(P9Layout.createSequentialGroup()
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                );
                P9Layout.setVerticalGroup(
                    P9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P9Layout.createSequentialGroup()
                        .addGroup(P9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(P9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblIdFd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMantFd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                P2.setBackground(new java.awt.Color(255, 255, 255));

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel33.setForeground(new java.awt.Color(51, 51, 51));
                jLabel33.setText("Grupo:");

                jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                txtA.setEditable(false);
                txtA.setBackground(new java.awt.Color(255, 255, 255));
                txtA.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtA.setForeground(new java.awt.Color(102, 102, 102));
                txtA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtA.setPreferredSize(new java.awt.Dimension(18, 18));
                txtA.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                txtA.setSelectionColor(new java.awt.Color(255, 255, 255));
                txtA.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtACaretUpdate(evt);
                    }
                });
                txtA.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtAMouseClicked(evt);
                    }
                });

                jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel34.setForeground(new java.awt.Color(51, 51, 51));
                jLabel34.setText("A");

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtA, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(25, Short.MAX_VALUE))
                );
                jPanel4Layout.setVerticalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34))
                );

                jPanel5.setBackground(new java.awt.Color(255, 255, 255));

                txtB.setEditable(false);
                txtB.setBackground(new java.awt.Color(255, 255, 255));
                txtB.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtB.setForeground(new java.awt.Color(102, 102, 102));
                txtB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtB.setPreferredSize(new java.awt.Dimension(18, 18));
                txtB.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                txtB.setSelectionColor(new java.awt.Color(255, 255, 255));
                txtB.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBCaretUpdate(evt);
                    }
                });
                txtB.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtBMouseClicked(evt);
                    }
                });

                jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel35.setForeground(new java.awt.Color(51, 51, 51));
                jLabel35.setText("B");

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtB, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(25, Short.MAX_VALUE))
                );
                jPanel5Layout.setVerticalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35))
                );

                jPanel9.setBackground(new java.awt.Color(255, 255, 255));

                txtAB.setEditable(false);
                txtAB.setBackground(new java.awt.Color(255, 255, 255));
                txtAB.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtAB.setForeground(new java.awt.Color(102, 102, 102));
                txtAB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtAB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtAB.setPreferredSize(new java.awt.Dimension(18, 18));
                txtAB.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                txtAB.setSelectionColor(new java.awt.Color(255, 255, 255));
                txtAB.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtABCaretUpdate(evt);
                    }
                });
                txtAB.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtABMouseClicked(evt);
                    }
                });

                jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel36.setForeground(new java.awt.Color(51, 51, 51));
                jLabel36.setText("AB");

                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                jPanel9.setLayout(jPanel9Layout);
                jPanel9Layout.setHorizontalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAB, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(25, Short.MAX_VALUE))
                );
                jPanel9Layout.setVerticalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36))
                );

                jPanel10.setBackground(new java.awt.Color(255, 255, 255));

                txtO.setEditable(false);
                txtO.setBackground(new java.awt.Color(255, 255, 255));
                txtO.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtO.setForeground(new java.awt.Color(102, 102, 102));
                txtO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtO.setPreferredSize(new java.awt.Dimension(18, 18));
                txtO.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                txtO.setSelectionColor(new java.awt.Color(255, 255, 255));
                txtO.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtOCaretUpdate(evt);
                    }
                });
                txtO.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtOMouseClicked(evt);
                    }
                });

                jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel38.setForeground(new java.awt.Color(51, 51, 51));
                jLabel38.setText("O");

                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                jPanel10.setLayout(jPanel10Layout);
                jPanel10Layout.setHorizontalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtO, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(35, Short.MAX_VALUE))
                );
                jPanel10Layout.setVerticalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel38))
                );

                jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel39.setForeground(new java.awt.Color(51, 51, 51));
                jLabel39.setText("Rh:");

                jPanel11.setBackground(new java.awt.Color(255, 255, 255));

                txtRhPositivo.setEditable(false);
                txtRhPositivo.setBackground(new java.awt.Color(255, 255, 255));
                txtRhPositivo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtRhPositivo.setForeground(new java.awt.Color(102, 102, 102));
                txtRhPositivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtRhPositivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtRhPositivo.setPreferredSize(new java.awt.Dimension(18, 18));
                txtRhPositivo.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                txtRhPositivo.setSelectionColor(new java.awt.Color(255, 255, 255));
                txtRhPositivo.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtRhPositivoCaretUpdate(evt);
                    }
                });
                txtRhPositivo.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtRhPositivoMouseClicked(evt);
                    }
                });

                jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel50.setForeground(new java.awt.Color(51, 51, 51));
                jLabel50.setText("Rh (+)");

                javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                jPanel11.setLayout(jPanel11Layout);
                jPanel11Layout.setHorizontalGroup(
                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRhPositivo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel11Layout.setVerticalGroup(
                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRhPositivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel50))
                );

                jPanel12.setBackground(new java.awt.Color(255, 255, 255));

                txtRhSen.setEditable(false);
                txtRhSen.setBackground(new java.awt.Color(255, 204, 51));
                txtRhSen.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtRhSen.setForeground(new java.awt.Color(102, 102, 102));
                txtRhSen.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtRhSen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtRhSen.setPreferredSize(new java.awt.Dimension(18, 18));
                txtRhSen.setSelectedTextColor(new java.awt.Color(109, 109, 109));
                txtRhSen.setSelectionColor(new java.awt.Color(255, 204, 51));
                txtRhSen.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtRhSenCaretUpdate(evt);
                    }
                });
                txtRhSen.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtRhSenMouseClicked(evt);
                    }
                });

                jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel51.setForeground(new java.awt.Color(51, 51, 51));
                jLabel51.setText("Rh (+) Sen Desc");

                javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                jPanel12.setLayout(jPanel12Layout);
                jPanel12Layout.setHorizontalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRhSen, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel12Layout.setVerticalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRhSen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel51))
                );

                jPanel13.setBackground(new java.awt.Color(255, 255, 255));

                txtNoDesc.setEditable(false);
                txtNoDesc.setBackground(new java.awt.Color(255, 204, 51));
                txtNoDesc.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtNoDesc.setForeground(new java.awt.Color(102, 102, 102));
                txtNoDesc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtNoDesc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtNoDesc.setPreferredSize(new java.awt.Dimension(18, 18));
                txtNoDesc.setSelectedTextColor(new java.awt.Color(109, 109, 109));
                txtNoDesc.setSelectionColor(new java.awt.Color(255, 204, 51));
                txtNoDesc.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtNoDescCaretUpdate(evt);
                    }
                });
                txtNoDesc.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtNoDescMouseClicked(evt);
                    }
                });

                jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel52.setForeground(new java.awt.Color(51, 51, 51));
                jLabel52.setText("Rh (-) No Desc");

                javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                jPanel13.setLayout(jPanel13Layout);
                jPanel13Layout.setHorizontalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNoDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel13Layout.setVerticalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNoDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel52))
                );

                jPanel14.setBackground(new java.awt.Color(255, 255, 255));

                txtRhNegativo.setEditable(false);
                txtRhNegativo.setBackground(new java.awt.Color(255, 51, 51));
                txtRhNegativo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtRhNegativo.setForeground(new java.awt.Color(255, 255, 255));
                txtRhNegativo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtRhNegativo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtRhNegativo.setPreferredSize(new java.awt.Dimension(18, 18));
                txtRhNegativo.setSelectionColor(new java.awt.Color(255, 51, 51));
                txtRhNegativo.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtRhNegativoCaretUpdate(evt);
                    }
                });
                txtRhNegativo.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtRhNegativoMouseClicked(evt);
                    }
                });

                jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel53.setForeground(new java.awt.Color(51, 51, 51));
                jLabel53.setText("Rh (-) Sen");

                javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                jPanel14.setLayout(jPanel14Layout);
                jPanel14Layout.setHorizontalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRhNegativo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel14Layout.setVerticalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRhNegativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel53))
                );

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel39))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(lblIdGs, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblMantGs, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                );
                jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblIdGs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMantGs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                );

                javax.swing.GroupLayout P2Layout = new javax.swing.GroupLayout(P2);
                P2.setLayout(P2Layout);
                P2Layout.setHorizontalGroup(
                    P2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(194, Short.MAX_VALUE))
                );
                P2Layout.setVerticalGroup(
                    P2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                PEmergencia.setBackground(new java.awt.Color(255, 255, 255));

                jPanel22.setBackground(new java.awt.Color(255, 255, 255));

                fechaEmer.setBackground(new java.awt.Color(255, 255, 255));
                fechaEmer.setDateFormatString("dd-MM-yyyy");

                jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel69.setForeground(new java.awt.Color(51, 51, 51));
                jLabel69.setText("Fecha");

                jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel70.setForeground(new java.awt.Color(51, 51, 51));
                jLabel70.setText("Diagnóstico");

                jPanel23.setBackground(new java.awt.Color(255, 255, 255));
                jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtCie10E.setEditable(false);
                txtCie10E.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                txtCie10E.setForeground(new java.awt.Color(102, 102, 102));
                txtCie10E.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtCie10E.setBorder(null);
                txtCie10E.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCie10ECaretUpdate(evt);
                    }
                });

                btnBuscarNino3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarNino3.setMnemonic('B');
                btnBuscarNino3.setToolTipText("");
                btnBuscarNino3.setBorderPainted(false);
                btnBuscarNino3.setContentAreaFilled(false);
                btnBuscarNino3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarNino3.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        btnBuscarNino3MouseClicked(evt);
                    }
                });
                btnBuscarNino3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarNino3ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
                jPanel23.setLayout(jPanel23Layout);
                jPanel23Layout.setHorizontalGroup(
                    jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtCie10E, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarNino3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                jPanel23Layout.setVerticalGroup(
                    jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarNino3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtCie10E, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel71.setForeground(new java.awt.Color(51, 51, 51));
                jLabel71.setText("CIE 10");

                lblCie10E.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblCie10E.setForeground(new java.awt.Color(51, 51, 51));
                lblCie10E.setText("________________");

                txtdesCie10E.setForeground(new java.awt.Color(51, 51, 51));
                txtdesCie10E.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtdesCie10EKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtdesCie10EKeyTyped(evt);
                    }
                });
                jScrollPane1.setViewportView(txtdesCie10E);

                lblMantEme.setText("I");

                lblIdCie10E.setText("jLabel1");

                javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
                jPanel22.setLayout(jPanel22Layout);
                jPanel22Layout.setHorizontalGroup(
                    jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel71)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblCie10E)
                                .addGap(88, 88, 88)
                                .addComponent(lblIdEme, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblMantEme)
                                .addGap(35, 35, 35)
                                .addComponent(lblIdCie10E))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel70)
                                    .addComponent(jLabel69))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fechaEmer, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel22Layout.setVerticalGroup(
                    jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fechaEmer, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel70)
                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblMantEme)
                                    .addComponent(lblIdCie10E)))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel71)
                                        .addComponent(lblCie10E))
                                    .addComponent(lblIdEme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout PEmergenciaLayout = new javax.swing.GroupLayout(PEmergencia);
                PEmergencia.setLayout(PEmergenciaLayout);
                PEmergenciaLayout.setHorizontalGroup(
                    PEmergenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PEmergenciaLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                PEmergenciaLayout.setVerticalGroup(
                    PEmergenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PEmergenciaLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                P8.setBackground(new java.awt.Color(255, 255, 255));

                jPanel29.setBackground(new java.awt.Color(255, 255, 255));

                jPanel31.setBackground(new java.awt.Color(255, 255, 255));

                chkFdSi.setEditable(false);
                chkFdSi.setBackground(new java.awt.Color(255, 204, 51));
                chkFdSi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                chkFdSi.setForeground(new java.awt.Color(102, 102, 102));
                chkFdSi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                chkFdSi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                chkFdSi.setPreferredSize(new java.awt.Dimension(18, 18));
                chkFdSi.setSelectedTextColor(new java.awt.Color(109, 109, 109));
                chkFdSi.setSelectionColor(new java.awt.Color(255, 204, 51));
                chkFdSi.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        chkFdSiCaretUpdate(evt);
                    }
                });
                chkFdSi.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chkFdSiMouseClicked(evt);
                    }
                });

                jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel81.setForeground(new java.awt.Color(51, 51, 51));
                jLabel81.setText("Si");

                javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
                jPanel31.setLayout(jPanel31Layout);
                jPanel31Layout.setHorizontalGroup(
                    jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel81)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkFdSi, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel31Layout.setVerticalGroup(
                    jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkFdSi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel81))
                );

                jPanel32.setBackground(new java.awt.Color(255, 255, 255));

                chkFdNo.setEditable(false);
                chkFdNo.setBackground(new java.awt.Color(255, 255, 255));
                chkFdNo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                chkFdNo.setForeground(new java.awt.Color(102, 102, 102));
                chkFdNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                chkFdNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                chkFdNo.setPreferredSize(new java.awt.Dimension(18, 18));
                chkFdNo.setSelectedTextColor(new java.awt.Color(102, 102, 102));
                chkFdNo.setSelectionColor(new java.awt.Color(255, 255, 255));
                chkFdNo.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        chkFdNoCaretUpdate(evt);
                    }
                });
                chkFdNo.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chkFdNoMouseClicked(evt);
                    }
                });

                jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel82.setForeground(new java.awt.Color(51, 51, 51));
                jLabel82.setText("No");

                javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
                jPanel32.setLayout(jPanel32Layout);
                jPanel32Layout.setHorizontalGroup(
                    jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabel82)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkFdNo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel32Layout.setVerticalGroup(
                    jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkFdNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel82))
                );

                javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
                jPanel29.setLayout(jPanel29Layout);
                jPanel29Layout.setHorizontalGroup(
                    jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 237, Short.MAX_VALUE)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel29Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                );
                jPanel29Layout.setVerticalGroup(
                    jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 42, Short.MAX_VALUE)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel29Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                );

                jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jLabel9.setForeground(new java.awt.Color(102, 102, 102));
                jLabel9.setText("Droga");

                jPanel6.setBackground(new java.awt.Color(39, 174, 97));
                jPanel6.setPreferredSize(new java.awt.Dimension(15, 34));

                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                jPanel6.setLayout(jPanel6Layout);
                jPanel6Layout.setHorizontalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 15, Short.MAX_VALUE)
                );
                jPanel6Layout.setVerticalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 37, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout P8Layout = new javax.swing.GroupLayout(P8);
                P8.setLayout(P8Layout);
                P8Layout.setHorizontalGroup(
                    P8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P8Layout.createSequentialGroup()
                        .addGroup(P8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(P8Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addGroup(P8Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                P8Layout.setVerticalGroup(
                    P8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(P8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel28.setBackground(new java.awt.Color(51, 51, 51));

                LblTitulo.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                LblTitulo.setForeground(new java.awt.Color(255, 255, 255));
                LblTitulo.setText("<html>Antecedentes <br>Obstétricos</html>");

                lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                lblusu.setForeground(new java.awt.Color(255, 255, 255));
                lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                lblusu.setText("Silvana");
                lblusu.setFocusable(false);
                lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                btnCaccnelar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnCaccnelar.setForeground(new java.awt.Color(255, 255, 255));
                btnCaccnelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Casa-32.png"))); // NOI18N
                btnCaccnelar.setText("Detalles");
                btnCaccnelar.setToolTipText("");
                btnCaccnelar.setContentAreaFilled(false);
                btnCaccnelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnCaccnelar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnCaccnelar.setIconTextGap(30);
                btnCaccnelar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnCaccnelarActionPerformed(evt);
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

                jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jLabel26.setForeground(new java.awt.Color(204, 204, 204));
                jLabel26.setText("Leyenda");

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

                jPanel42.setBackground(new java.awt.Color(39, 174, 97));

                jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel83.setForeground(new java.awt.Color(255, 255, 255));
                jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
                jLabel83.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jLabel83MouseClicked(evt);
                    }
                });

                javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
                jPanel42.setLayout(jPanel42Layout);
                jPanel42Layout.setHorizontalGroup(
                    jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel83)
                );
                jPanel42Layout.setVerticalGroup(
                    jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                        .addContainerGap())
                );

                btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnModificar.setForeground(new java.awt.Color(240, 240, 240));
                btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
                btnModificar.setMnemonic('N');
                btnModificar.setText("Modificar");
                btnModificar.setContentAreaFilled(false);
                btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnModificar.setIconTextGap(30);
                btnModificar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnModificar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnModificarActionPerformed(evt);
                    }
                });

                lblCpId.setText("jLabel1");

                javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
                jPanel28.setLayout(jPanel28Layout);
                jPanel28Layout.setHorizontalGroup(
                    jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                            .addComponent(btnCaccnelar)
                            .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel26)
                                    .addGroup(jPanel28Layout.createSequentialGroup()
                                        .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel30))
                                    .addGroup(jPanel28Layout.createSequentialGroup()
                                        .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel37))
                                    .addComponent(lblCpId))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel28Layout.setVerticalGroup(
                    jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCaccnelar, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCpId)
                        .addGap(65, 65, 65)
                        .addComponent(lblusu)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addGap(249, 249, 249))
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
                        .addContainerGap(17, Short.MAX_VALUE))
                );

                lblMadre.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
                lblMadre.setForeground(new java.awt.Color(12, 97, 81));
                lblMadre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Embarazada Filled-60.png"))); // NOI18N
                lblMadre.setText("Martha Arias Torres");
                lblMadre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lblMadre.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(P6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(P3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(PEmergencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(P4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(466, 466, 466)
                                                .addComponent(lblMantFum, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblIdFum, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(P8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(P9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(P5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(P1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(P2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblMant, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap())))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblMadre)
                                    .addComponent(lblMant, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(P1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(P5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(P3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(P4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblIdFum, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblMantFum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(6, 6, 6)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PEmergencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(P8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(P9, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(P6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(P2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void txtPesoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPesoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoCaretUpdate

    private void txtTallaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTallaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTallaCaretUpdate

    private void txtNDosisPreviaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNDosisPreviaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNDosisPreviaCaretUpdate

    private void txtDosis1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDosis1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis1CaretUpdate

    private void txtDosis2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDosis2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis2CaretUpdate

    private void txtSinDosis1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSinDosis1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSinDosis1CaretUpdate

    private void txtNoAplica1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNoAplica1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoAplica1CaretUpdate

    private void txtSinDosis2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSinDosis2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSinDosis2CaretUpdate

    private void txtNoAplica2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNoAplica2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoAplica2CaretUpdate

    private void txtACaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtACaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtACaretUpdate

    private void txtBCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBCaretUpdate

    private void txtABCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtABCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtABCaretUpdate

    private void txtOCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtOCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOCaretUpdate

    private void txtRhPositivoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtRhPositivoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRhPositivoCaretUpdate

    private void txtRhSenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtRhSenCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRhSenCaretUpdate

    private void txtNoDescCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNoDescCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoDescCaretUpdate

    private void txtRhNegativoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtRhNegativoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRhNegativoCaretUpdate

    private void chkDudaSiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkDudaSiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkDudaSiCaretUpdate

    private void chkDudaNoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkDudaNoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkDudaNoCaretUpdate

    private void txtEcografiaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEcografiaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEcografiaCaretUpdate

    private void chkNoAplicaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkNoAplicaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkNoAplicaCaretUpdate

    private void chkHsiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkHsiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHsiCaretUpdate

    private void chkHnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkHnoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHnoCaretUpdate

    private void txtCIE10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCIE10CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCIE10CaretUpdate

    private void btnBuscarNino2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNino2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarNino2ActionPerformed

    private void txtCie10ECaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCie10ECaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCie10ECaretUpdate

    private void btnBuscarNino3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNino3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarNino3ActionPerformed

    private void txtNCigarrosCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNCigarrosCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNCigarrosCaretUpdate

    private void chkFdSiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkFdSiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkFdSiCaretUpdate

    private void chkFdNoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkFdNoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkFdNoCaretUpdate

    private void btnCaccnelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaccnelarActionPerformed

    }//GEN-LAST:event_btnCaccnelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(opcionGuardar.equals("peso")){ //PARA EL FORMULARIO DE PESO Y TALLA
            if(txtPeso.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese el peso");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else 
            if(txtTalla.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese la talla");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            }
            pnlMensaje.setVisible(true);
            btnSi.setText("Si");
            btnSi.setVisible(true);
            btnNo.setVisible(true);
            pnlMensaje.setBackground(new Color(255,153,51));
            if(lblMant.getText().equals("I")){
                lblMensaje.setText("¿Guardar los datos?");
            } else
            if(lblMant.getText().equals("U"))
                lblMensaje.setText("¿Modificar los datos?");
        } // FIN DE FORMULARIO PESO Y TALLA
        
        if(opcionGuardar.equals("antitetanica")){ //PARA EL FORMULARIO DE ANTITETANICA
            pnlMensaje.setVisible(true);
            btnSi.setText("Si");
            btnSi.setVisible(true);
            btnNo.setVisible(true);
            pnlMensaje.setBackground(new Color(255,153,51));
            if(lblMantAn.getText().equals("I")){
                lblMensaje.setText("¿Guardar los datos?");
            } else
            if(lblMantAn.getText().equals("U"))
                lblMensaje.setText("¿Modificar los datos?");
        } // FIN DE FORMULARIO DE ANTITETANICA
        
        if(opcionGuardar.equals("tipoSangre")){ //PARA EL FORMULARIO DE TIPO DE SANGRE
            if(txtA.getText().equals("") && txtB.getText().equals("") &&
               txtAB.getText().equals("") && txtO.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione un grupo de sangre");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else 
            if(txtRhNegativo.getText().equals("") && txtRhPositivo.getText().equals("") &&
               txtRhSen.getText().equals("") && txtNoDesc.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione el Rh");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else
            if(lblMantGs.getText().equals("I")){
                lblMensaje.setText("¿Guardar los datos?");
                pnlMensaje.setVisible(true);
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                pnlMensaje.setBackground(new Color(255,153,51));
            } else
            if(lblMantGs.getText().equals("U")){
                lblMensaje.setText("¿Modificar los datos?");
                pnlMensaje.setVisible(true);
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                pnlMensaje.setBackground(new Color(255,153,51));
            }
        }
        
        if(opcionGuardar.equals("fumaDroga")){ //PARA EL FORMULARIO DE FUMA DROGA
            if(chkFdSi.getText().equals("") && chkFdNo.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione si se droga o no");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else 
            if(lblMantFd.getText().equals("I")){
                lblMensaje.setText("¿Guardar los datos?");
                pnlMensaje.setVisible(true);
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                pnlMensaje.setBackground(new Color(255,153,51));
            } else
            if(lblMantFd.getText().equals("U")){
                lblMensaje.setText("¿Modificar los datos?");
                pnlMensaje.setVisible(true);
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                pnlMensaje.setBackground(new Color(255,153,51));
            }
        }
        
        if(opcionGuardar.equals("fum")){ //PARA EL FORMULARIO DE FECHA ULTIMA DE MENSTRUACION
            if(dtFUM.getDate()==null){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese la fecha última de menstruación");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else 
            if(chkDudaSi.getText().equals("") && chkDudaNo.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione la opción de duda");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else
            if(lblMantFum.getText().equals("I")){
                lblMensaje.setText("¿Guardar los datos?");
                pnlMensaje.setVisible(true);
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                pnlMensaje.setBackground(new Color(255,153,51));
            } else
            if(lblMantFum.getText().equals("U")){
                lblMensaje.setText("¿Modificar los datos?");
                pnlMensaje.setVisible(true);
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                pnlMensaje.setBackground(new Color(255,153,51));
            }
        }
        
        if(opcionGuardar.equals("hos")){ //PARA EL FORMULARIO HOSPITALIZACION
            if(lblCie10.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese un CIE10");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } 
            pnlMensaje.setVisible(true);
            btnSi.setText("Si");
            btnSi.setVisible(true);
            btnNo.setVisible(true);
            pnlMensaje.setBackground(new Color(255,153,51));
            if(lblMantHo.getText().equals("I")){
                lblMensaje.setText("¿Guardar los datos?");
            } else
            if(lblMantHo.getText().equals("U"))
                lblMensaje.setText("¿Actualizar los datos?");
        } // FIN DE FORMULARIO HOSPITALIZACION
        
        if(opcionGuardar.equals("eme")){ //PARA EL FORMULARIO EMERGENCIA
            if(lblCie10E.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese un CIE10");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } 
            pnlMensaje.setVisible(true);
            btnSi.setText("Si");
            btnSi.setVisible(true);
            btnNo.setVisible(true);
            pnlMensaje.setBackground(new Color(255,153,51));
            if(lblMantEme.getText().equals("I")){
                lblMensaje.setText("¿Guardar los datos?");
            } else
            if(lblMantEme.getText().equals("U"))
                lblMensaje.setText("¿Actualizar los datos?");
        } // FIN DE FORMULARIO EMERGENCIA
        
        if(opcionGuardar.equals("vg")){ //PARA EL FORMULARIO VIOLENCIA DE GENERO
            if(FechaVG.getDate()==null){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese la fecha ");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } 
            pnlMensaje.setVisible(true);
            btnSi.setText("Si");
            btnSi.setVisible(true);
            btnNo.setVisible(true);
            pnlMensaje.setBackground(new Color(255,153,51));
            if(lblMantVG.getText().equals("I")){
                lblMensaje.setText("¿Guardar los datos?");
            } else
            if(lblMantVG.getText().equals("U"))
                lblMensaje.setText("¿Actualizar los datos?");
        } // FIN DE FORMULARIO VIOLENCIA DE GENERO
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

    private void jLabel83MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel83MouseClicked
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(1);
        this.dispose();
    }//GEN-LAST:event_jLabel83MouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(opcionGuardar.equals("peso")){ // PARA EL FORMULARIO DE PESO
            txtPeso.setEditable(true);
            txtTalla.setEditable(true);
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(false);
        } else
        if(opcionGuardar.equals("antitetanica")){ // PARA EL FORMULARIO DE ANTITETANICA
            txtNDosisPrevia.setEditable(true);
            txtDosis1.setEditable(true);
            txtDosis2.setEditable(true);
            txtSinDosis1.setEnabled(true);
            txtSinDosis2.setEnabled(true);
            txtNoAplica1.setEnabled(true);
            txtNoAplica2.setEnabled(true);
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(false);
            lblMantAn.setText("U");
        } else
        if(opcionGuardar.equals("tipoSangre")){ // PARA EL FORMULARIO DE ANTITETANICA
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(false);
            lblMantGs.setText("U");
        } else
        if(opcionGuardar.equals("fumaDroga")){ // PARA EL FORMULARIO DE ANTITETANICA
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(false);
            lblMantFd.setText("U");
            txtNCigarros.setEditable(true);
        } else 
        if(opcionGuardar.equals("fum")){ // PARA EL FORMULARIO DE FUM
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(false);
            lblMantFum.setText("U");
            txtEcografia.setEditable(true);
            dtFUM.setEnabled(true);
            dtFechaEco.setEnabled(true);
            dtFechaProbableParto.setEnabled(true);
        }else 
        if(opcionGuardar.equals("hos")){ // PARA EL FORMULARIO DE HOSPITALIZACION
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(false);
            lblMantHo.setText("U");
            fechaf3.setEnabled(true);

        }else 
        if(opcionGuardar.equals("eme")){ // PARA EL FORMULARIO DE EMERGENCIA
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(false);
            lblMantEme.setText("U");
            fechaEmer.setEnabled(true);

        }else 
        if(opcionGuardar.equals("vg")){ // PARA EL FORMULARIO DE VIOLENCIA DE GENERO
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(false);
            lblMantVG.setText("U");
            FechaVG.setEnabled(true);

        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        if(opcionGuardar.equals("peso")){ // formulario de peso y talla
            if(btnSi.getText().equals("Si")){ // Al guardar
                mantenimientoPT();
            } else
            if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
                pnlMensaje.setVisible(false);
            }
        }
        
        if(opcionGuardar.equals("antitetanica")){ // formulario de antitetanica
            if(btnSi.getText().equals("Si")){ // Al guardar
                mantenimientoAN();
            } else
            if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
                pnlMensaje.setVisible(false);
            }
        }
        
        if(opcionGuardar.equals("tipoSangre")){ // formulario de Tipo Sangre
            if(btnSi.getText().equals("Si")){ // Al guardar
                mantenimientoTS();
            } else
            if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
                pnlMensaje.setVisible(false);
            }
        }
        
        if(opcionGuardar.equals("fumaDroga")){ // formulario de Tipo Sangre
            if(btnSi.getText().equals("Si")){ // Al guardar
                mantenimientoFD();
            } else
            if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
                pnlMensaje.setVisible(false);
            }
        }
        
        if(opcionGuardar.equals("fum")){ // formulario de fecha ultima de menstruacion
            if(btnSi.getText().equals("Si")){ // Al guardar
                mantenimientoFU();
            } else
            if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
                pnlMensaje.setVisible(false);
            }
        }
        
        if(opcionGuardar.equals("hos")){ // formulario de Hospitalizacion
            if(btnSi.getText().equals("Si")){ // Al guardar
                mantenimientoHo();
            } else
            if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
                pnlMensaje.setVisible(false);
            }
        }
        
        if(opcionGuardar.equals("eme")){ // formulario de Emergencia
            if(btnSi.getText().equals("Si")){ // Al guardar
                mantenimientoEme();
            } else
            if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
                pnlMensaje.setVisible(false);
            }
        }
        
        if(opcionGuardar.equals("vg")){ // formulario de Emergencia
            if(btnSi.getText().equals("Si")){ // Al guardar
                mantenimientoVG();
            } else
            if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
                pnlMensaje.setVisible(false);
            }
        }
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_btnNoActionPerformed

    private void chkVnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkVnoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkVnoCaretUpdate

    private void chkVsiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkVsiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkVsiCaretUpdate

    private void chkTnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkTnoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkTnoCaretUpdate

    private void chkTsiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkTsiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkTsiCaretUpdate

    private void txtdesCie10EKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesCie10EKeyReleased
        txtdesCie10E.setText(txtdesCie10E.getText().toUpperCase());

    }//GEN-LAST:event_txtdesCie10EKeyReleased

    private void txtdesCie10EKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesCie10EKeyTyped

    }//GEN-LAST:event_txtdesCie10EKeyTyped

    private void txtdes1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdes1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdes1KeyReleased

    private void txtdes1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdes1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdes1KeyTyped

    private void txtSinDosis1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSinDosis1MouseClicked
        if(lblIdAn.getText().equals("") || lblMantAn.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtSinDosis1.setText("X");
                txtNoAplica1.setText("");
            }
        }
    }//GEN-LAST:event_txtSinDosis1MouseClicked

    private void txtSinDosis2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSinDosis2MouseClicked
        if(lblIdAn.getText().equals("") || lblMantAn.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtSinDosis2.setText("X");
                txtNoAplica2.setText("");
            }
        }
    }//GEN-LAST:event_txtSinDosis2MouseClicked

    private void txtNoAplica1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNoAplica1MouseClicked
        if(lblIdAn.getText().equals("") || lblMantAn.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtSinDosis1.setText("");
                txtNoAplica1.setText("X");
            }
        }
    }//GEN-LAST:event_txtNoAplica1MouseClicked

    private void txtNoAplica2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNoAplica2MouseClicked
        if(lblIdAn.getText().equals("") || lblMantAn.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtSinDosis2.setText("");
                txtNoAplica2.setText("X");
            }
        }
    }//GEN-LAST:event_txtNoAplica2MouseClicked

    private void txtNDosisPreviaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNDosisPreviaKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtNDosisPreviaKeyTyped

    private void txtDosis1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosis1KeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtDosis1KeyTyped

    private void txtDosis2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosis2KeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtDosis2KeyTyped

    private void txtAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtA.setText("X");
                txtB.setText("");
                txtAB.setText("");
                txtO.setText("");
            }
        }
    }//GEN-LAST:event_txtAMouseClicked

    private void txtBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtA.setText("");
                txtB.setText("X");
                txtAB.setText("");
                txtO.setText("");
            }
        }
    }//GEN-LAST:event_txtBMouseClicked

    private void txtABMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtABMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtA.setText("");
                txtB.setText("");
                txtAB.setText("X");
                txtO.setText("");
            }
        }
    }//GEN-LAST:event_txtABMouseClicked

    private void txtOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtOMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtA.setText("");
                txtB.setText("");
                txtAB.setText("");
                txtO.setText("X");
            }
        }
    }//GEN-LAST:event_txtOMouseClicked

    private void txtRhPositivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRhPositivoMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtRhPositivo.setText("X");
                txtRhSen.setText("");
                txtNoDesc.setText("");
                txtRhNegativo.setText("");
            }
        }
    }//GEN-LAST:event_txtRhPositivoMouseClicked

    private void txtRhSenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRhSenMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtRhPositivo.setText("");
                txtRhSen.setText("X");
                txtNoDesc.setText("");
                txtRhNegativo.setText("");
            }
        }
    }//GEN-LAST:event_txtRhSenMouseClicked

    private void txtNoDescMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNoDescMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtRhPositivo.setText("");
                txtRhSen.setText("");
                txtNoDesc.setText("X");
                txtRhNegativo.setText("");
            }
        }
    }//GEN-LAST:event_txtNoDescMouseClicked

    private void txtRhNegativoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRhNegativoMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtRhPositivo.setText("");
                txtRhSen.setText("");
                txtNoDesc.setText("");
                txtRhNegativo.setText("X");
            }
        }
    }//GEN-LAST:event_txtRhNegativoMouseClicked

    private void chkFdSiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkFdSiMouseClicked
        if(lblIdFd.getText().equals("") || lblMantFd.getText().equals("U")){
            if(evt.getClickCount()==1){
                chkFdSi.setText("X");
                chkFdNo.setText("");
            }
        }
    }//GEN-LAST:event_chkFdSiMouseClicked

    private void chkFdNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkFdNoMouseClicked
        if(lblIdFd.getText().equals("") || lblMantFd.getText().equals("U")){
            if(evt.getClickCount()==1){
                chkFdSi.setText("");
                chkFdNo.setText("X");
            }
        }
    }//GEN-LAST:event_chkFdNoMouseClicked

    private void chkDudaSiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkDudaSiMouseClicked
        if(lblIdFum.getText().equals("") || lblMantFum.getText().equals("U")){
            if(evt.getClickCount()==1){
                chkDudaSi.setText("X");
                chkDudaNo.setText("");
            }
        }
    }//GEN-LAST:event_chkDudaSiMouseClicked

    private void chkDudaNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkDudaNoMouseClicked
        if(lblIdFum.getText().equals("") || lblMantFum.getText().equals("U")){
            if(evt.getClickCount()==1){
                chkDudaSi.setText("");
                chkDudaNo.setText("X");
            }
        }
    }//GEN-LAST:event_chkDudaNoMouseClicked

    private void chkNoAplicaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkNoAplicaMouseClicked
        if(lblIdFum.getText().equals("") || lblMantFum.getText().equals("U")){
            if(evt.getClickCount()==1 && chkNoAplica.getText().equals("")){
                chkNoAplica.setText("X");
                dtFechaEco.setEnabled(false);
                txtEcografia.setEnabled(false);
                dtFechaEco.setDate(null);
                txtEcografia.setText("");
            } else{
                chkNoAplica.setText("");
                dtFechaEco.setEnabled(true);
                txtEcografia.setEnabled(true);
            }
        }
    }//GEN-LAST:event_chkNoAplicaMouseClicked

    private void txtEcografiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEcografiaKeyTyped
       char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtEcografiaKeyTyped

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T7MouseClicked

    private void txtBuscarCie10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCie10CaretUpdate
        ConsultorioExtCarnetPerinatalHo ETBUSCAR = new ConsultorioExtCarnetPerinatalHo();
        ETBUSCAR.cargarDatosCie10(txtBuscarCie10.getText(), tbCiePresun);
    }//GEN-LAST:event_txtBuscarCie10CaretUpdate

    private void txtBuscarCie10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCie10KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbCiePresun.getSelectionModel().setSelectionInterval(0, 0);
            tbCiePresun.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarCie10KeyPressed

    private void tbCiePresunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCiePresunMouseClicked
         if(evt.getClickCount()==2){
            enviarDiagnosticos(cie10); 
         }
    }//GEN-LAST:event_tbCiePresunMouseClicked

    private void tbCiePresunMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCiePresunMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCiePresunMousePressed

    private void tbCiePresunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCiePresunKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbCiePresun.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBuscarCie10.requestFocus();
            tbCiePresun.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDiagnosticos(cie10);
        }
        
    }//GEN-LAST:event_tbCiePresunKeyPressed

    private void btnBuscarNino2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarNino2MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_btnBuscarNino2MouseClicked

    private void chkHsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkHsiMouseClicked
        if(lblIdHos.getText().equals("") || lblMantHo.getText().equals("U")){
            if(evt.getClickCount()==1){
                chkHsi.setText("X");
                chkHno.setText("");
            }
        }
    }//GEN-LAST:event_chkHsiMouseClicked

    private void chkHnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkHnoMouseClicked
       if(lblIdHos.getText().equals("") || lblMantHo.getText().equals("U")){
            if(evt.getClickCount()==1){
                chkHsi.setText("");
                chkHno.setText("X");
            }
        }
    }//GEN-LAST:event_chkHnoMouseClicked

    private void T8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T8MouseClicked

    private void txtBuscarCie11CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCie11CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCie11CaretUpdate

    private void txtBuscarCie11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCie11KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCie11KeyPressed

    private void tbCiePresun1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCiePresun1MouseClicked
         if(evt.getClickCount()==2){
            enviarDiagnosticos1(cie10); 
         }
    }//GEN-LAST:event_tbCiePresun1MouseClicked

    private void tbCiePresun1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCiePresun1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCiePresun1MousePressed

    private void tbCiePresun1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCiePresun1KeyPressed
          if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbCiePresun.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBuscarCie10.requestFocus();
            tbCiePresun.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDiagnosticos(cie10);
        }
    }//GEN-LAST:event_tbCiePresun1KeyPressed

    private void btnBuscarNino3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarNino3MouseClicked
         FrmCie11.setVisible(true);
    }//GEN-LAST:event_btnBuscarNino3MouseClicked

    private void chkTsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkTsiMouseClicked
       if(lblIdVG.getText().equals("") || lblMantVG.getText().equals("U")){
            if(evt.getClickCount()==1){
                chkTsi.setText("X");
                chkTno.setText("");
            }
        }
    }//GEN-LAST:event_chkTsiMouseClicked

    private void chkTnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkTnoMouseClicked
       if(lblIdVG.getText().equals("") || lblMantVG.getText().equals("U")){
            if(evt.getClickCount()==1){
                chkTsi.setText("");
                chkTno.setText("X");
            }
        }
    }//GEN-LAST:event_chkTnoMouseClicked

    private void chkVsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkVsiMouseClicked
         if(lblIdVG.getText().equals("") || lblMantVG.getText().equals("U")){
            if(evt.getClickCount()==1){
                chkVsi.setText("X");
                chkVno.setText("");
            }
        }
    }//GEN-LAST:event_chkVsiMouseClicked

    private void chkVnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkVnoMouseClicked
        if(lblIdVG.getText().equals("") || lblMantVG.getText().equals("U")){
            if(evt.getClickCount()==1){
                chkVsi.setText("");
                chkVno.setText("X");
            }
        }
    }//GEN-LAST:event_chkVnoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField ChkAnalf1;
    public static javax.swing.JTextField ChkEdad1;
    public static com.toedter.calendar.JDateChooser FechaVG;
    private javax.swing.JDialog FrmCie10;
    private javax.swing.JDialog FrmCie11;
    public static javax.swing.JLabel LblTitulo;
    public static javax.swing.JPanel P1;
    public static javax.swing.JPanel P2;
    public static javax.swing.JPanel P3;
    public static javax.swing.JPanel P4;
    public static javax.swing.JPanel P5;
    public static javax.swing.JPanel P6;
    public static javax.swing.JPanel P8;
    public static javax.swing.JPanel P9;
    public static javax.swing.JPanel PEmergencia;
    private javax.swing.JLabel T7;
    private javax.swing.JLabel T8;
    private javax.swing.JButton btnBuscarNino2;
    private javax.swing.JButton btnBuscarNino3;
    private javax.swing.JButton btnCaccnelar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnSi;
    public static javax.swing.JTextField chkDudaNo;
    public static javax.swing.JTextField chkDudaSi;
    public static javax.swing.JTextField chkFdNo;
    public static javax.swing.JTextField chkFdSi;
    public static javax.swing.JTextField chkHno;
    public static javax.swing.JTextField chkHsi;
    public static javax.swing.JTextField chkNoAplica;
    public static javax.swing.JTextField chkTno;
    public static javax.swing.JTextField chkTsi;
    public static javax.swing.JTextField chkVno;
    public static javax.swing.JTextField chkVsi;
    public static com.toedter.calendar.JDateChooser dtFUM;
    public static com.toedter.calendar.JDateChooser dtFechaEco;
    public static com.toedter.calendar.JDateChooser dtFechaProbableParto;
    public static com.toedter.calendar.JDateChooser fechaEmer;
    public static com.toedter.calendar.JDateChooser fechaf3;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
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
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
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
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JLabel lblCie10;
    public static javax.swing.JLabel lblCie10E;
    public static javax.swing.JLabel lblCpId;
    public static javax.swing.JLabel lblIdAn;
    public static javax.swing.JLabel lblIdCie10;
    public static javax.swing.JLabel lblIdCie10E;
    public static javax.swing.JLabel lblIdEme;
    public static javax.swing.JLabel lblIdFd;
    public static javax.swing.JLabel lblIdFum;
    public static javax.swing.JLabel lblIdGs;
    public static javax.swing.JLabel lblIdHos;
    public static javax.swing.JLabel lblIdPeso;
    public static javax.swing.JLabel lblIdVG;
    public static javax.swing.JLabel lblMadre;
    public static javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblMantAn;
    public static javax.swing.JLabel lblMantEme;
    public static javax.swing.JLabel lblMantFd;
    public static javax.swing.JLabel lblMantFum;
    public static javax.swing.JLabel lblMantGs;
    public static javax.swing.JLabel lblMantHo;
    public static javax.swing.JLabel lblMantVG;
    private javax.swing.JLabel lblMensaje;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JTable tbCiePresun;
    private javax.swing.JTable tbCiePresun1;
    private javax.swing.JLabel titulo7;
    private javax.swing.JLabel titulo8;
    public static javax.swing.JTextField txtA;
    public static javax.swing.JTextField txtAB;
    public static javax.swing.JTextField txtB;
    private javax.swing.JTextField txtBuscarCie10;
    private javax.swing.JTextField txtBuscarCie11;
    public static javax.swing.JTextField txtCIE10;
    public static javax.swing.JTextField txtCie10E;
    public static javax.swing.JTextField txtDosis1;
    public static javax.swing.JTextField txtDosis2;
    public static javax.swing.JTextField txtEcografia;
    public static javax.swing.JTextField txtNCigarros;
    public static javax.swing.JTextField txtNDosisPrevia;
    public static javax.swing.JTextField txtNoAplica1;
    public static javax.swing.JTextField txtNoAplica2;
    public static javax.swing.JTextField txtNoDesc;
    public static javax.swing.JTextField txtO;
    public static javax.swing.JTextField txtPeso;
    public static javax.swing.JTextField txtRhNegativo;
    public static javax.swing.JTextField txtRhPositivo;
    public static javax.swing.JTextField txtRhSen;
    public static javax.swing.JTextField txtSinDosis1;
    public static javax.swing.JTextField txtSinDosis2;
    public static javax.swing.JTextField txtTalla;
    public static javax.swing.JEditorPane txtdes1;
    public static javax.swing.JEditorPane txtdesCie10E;
    // End of variables declaration//GEN-END:variables
}
