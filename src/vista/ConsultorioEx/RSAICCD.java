/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.ConsultorioEx.ConsultorioExtRsCcd;
import static vista.ConsultorioEx.RegistroSeguimiento.jTabbedPane1;

/**
 *
 * @author MYS1
 */
public class RSAICCD extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
private Dimension DimensionBarra = null;

ConsultorioExtRsCcd adCXRSCCD = new ConsultorioExtRsCcd();
static ConsultorioExtRsCcd adCXRSCCDA = new ConsultorioExtRsCcd();
static DefaultTableModel m;
byte tg;
byte tge;
    /**
     * Creates new form RSAICCD
     */
JDateChooser fecha;
JTextField fua;
JLabel cie10;
ConsultorioExtRsCcd CCD01 = new ConsultorioExtRsCcd();
    public RSAICCD() {
        initComponents();
        QuitarLaBarraTitulo();
        jTabbedPane1.setEnabledAt(0,false);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        habilitarDatos(false);
        mensaje1.setVisible(false);
        FrmCie10.setLocationRelativeTo(null);//en el centro
        FrmCie10.getContentPane().setBackground(new Color(0,153,102));
       
    }
    public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
    }
    public void habilitarDatos(boolean opcion){
        //RECIEN NACIEDO
        FUACCDRN1.setEnabled(opcion);
        FUACCDRN2.setEnabled(opcion);
        FUACCDRN3.setEnabled(opcion);
        FUACCDRN4.setEnabled(opcion);
        
        FCCDRN1.setEnabled(opcion);
        FCCDRN2.setEnabled(opcion);
        FCCDRN3.setEnabled(opcion);
        FCCDRN4.setEnabled(opcion);
        
        DXCCDRN1.setEnabled(opcion);
        DXCCDRN2.setEnabled(opcion);
        DXCCDRN3.setEnabled(opcion);
        DXCCDRN4.setEnabled(opcion);
        
        
        //MENORES DE UN AÑO
        FCCDM1.setEnabled(opcion);
        FCCDM2.setEnabled(opcion);
        FCCDM3.setEnabled(opcion);
        FCCDM4.setEnabled(opcion);
        FCCDM5.setEnabled(opcion);
        FCCDM6.setEnabled(opcion);
        FCCDM7.setEnabled(opcion);
        FCCDM8.setEnabled(opcion);
        FCCDM9.setEnabled(opcion);
        FCCDM10.setEnabled(opcion);
        FCCDM11.setEnabled(opcion);
        
        DXCCDM1.setEnabled(opcion);
        DXCCDM2.setEnabled(opcion);
        DXCCDM3.setEnabled(opcion);
        DXCCDM4.setEnabled(opcion);
        DXCCDM5.setEnabled(opcion);
        DXCCDM6.setEnabled(opcion);
        DXCCDM7.setEnabled(opcion);
        DXCCDM8.setEnabled(opcion);
        DXCCDM9.setEnabled(opcion);
        DXCCDM10.setEnabled(opcion);
        DXCCDM11.setEnabled(opcion);
        
        FUACCDM1.setEnabled(opcion);
        FUACCDM2.setEnabled(opcion);
        FUACCDM3.setEnabled(opcion);
        FUACCDM4.setEnabled(opcion);
        FUACCDM5.setEnabled(opcion);
        FUACCDM6.setEnabled(opcion);
        FUACCDM7.setEnabled(opcion);
        FUACCDM8.setEnabled(opcion);
        FUACCDM9.setEnabled(opcion);
        FUACCDM10.setEnabled(opcion);
        FUACCDM11.setEnabled(opcion);
        //1 AÑO
        FCCD11.setEnabled(opcion);
        FCCD12.setEnabled(opcion);
        FCCD13.setEnabled(opcion);
        FCCD14.setEnabled(opcion);
        FCCD15.setEnabled(opcion);
        FCCD16.setEnabled(opcion);
        
        DXCCD11.setEnabled(opcion);
        DXCCD12.setEnabled(opcion);
        DXCCD13.setEnabled(opcion);
        DXCCD14.setEnabled(opcion);
        DXCCD15.setEnabled(opcion);
        DXCCD16.setEnabled(opcion);
        
        FUACCD11.setEnabled(opcion);
        FUACCD12.setEnabled(opcion);
        FUACCD13.setEnabled(opcion);
        FUACCD14.setEnabled(opcion);
        FUACCD15.setEnabled(opcion);
        FUACCD16.setEnabled(opcion);
        //2 AÑOS
        FCCD21.setEnabled(opcion);
        FCCD22.setEnabled(opcion);
        FCCD23.setEnabled(opcion);
        FCCD24.setEnabled(opcion);
        
        DXCCD21.setEnabled(opcion);
        DXCCD22.setEnabled(opcion);
        DXCCD23.setEnabled(opcion);
        DXCCD24.setEnabled(opcion);
        
        FUACCD21.setEnabled(opcion);
        FUACCD22.setEnabled(opcion);
        FUACCD23.setEnabled(opcion);
        FUACCD24.setEnabled(opcion);
        //3 AÑOS
        FCCD3A1.setEnabled(opcion);
        FCCD3A2.setEnabled(opcion);
        FCCD3A2.setEnabled(opcion);
        FCCD3A2.setEnabled(opcion);
        
        DXCCD3A1.setEnabled(opcion);
        DXCCD3A2.setEnabled(opcion);
        DXCCD3A3.setEnabled(opcion);
        DXCCD3A4.setEnabled(opcion);
        
        FUACCD3A1.setEnabled(opcion);
        FUACCD3A2.setEnabled(opcion);
        FUACCD3A3.setEnabled(opcion);
        FUACCD3A4.setEnabled(opcion);
        //4 AÑOS
        FCCD4A1.setEnabled(opcion);
        FCCD4A2.setEnabled(opcion);
        FCCD4A2.setEnabled(opcion);
        FCCD4A2.setEnabled(opcion);
        
        DXCCD4A1.setEnabled(opcion);
        DXCCD4A2.setEnabled(opcion);
        DXCCD4A3.setEnabled(opcion);
        DXCCD4A4.setEnabled(opcion);
        
        FUACCD4A1.setEnabled(opcion);
        FUACCD4A2.setEnabled(opcion);
        FUACCD4A3.setEnabled(opcion);
        FUACCD4A4.setEnabled(opcion);
        //5 AÑOS
        FCCD5A.setEnabled(opcion);
        DXCCD5A.setEnabled(opcion);   
        FUACCD5A.setEnabled(opcion);
        //6 AÑOS
        FCCD6A.setEnabled(opcion);
        DXCCD6A.setEnabled(opcion);   
        FUACCD6A.setEnabled(opcion);
        //7 AÑOS
        FCCD7A.setEnabled(opcion);
        DXCCD7A.setEnabled(opcion);   
        FUACCD7A.setEnabled(opcion);
        //8 AÑOS
        FCCD8A.setEnabled(opcion);
        DXCCD8A.setEnabled(opcion);   
        FUACCD8A.setEnabled(opcion);
        //9 AÑOS
        FCCD9A.setEnabled(opcion);
        DXCCD9A.setEnabled(opcion);   
        FUACCD9A.setEnabled(opcion);
        //10 AÑOS
        FCCD10A.setEnabled(opcion);
        DXCCD10A.setEnabled(opcion);   
        FUACCD10A.setEnabled(opcion);
        //11 AÑOS
        FCCD11A.setEnabled(opcion);
        DXCCD11A.setEnabled(opcion);   
        FUACCD11A.setEnabled(opcion);
    }
    public void habilitarRadio(boolean opcion){
        //RECIEN NACIDO
        Rrn1.setEnabled(opcion);  
        Rrn2.setEnabled(opcion); 
        Rrn3.setEnabled(opcion); 
        Rrn4.setEnabled(opcion); 
        //MENORES DE UN AÑO
        Rm11.setEnabled(opcion); 
        Rm12.setEnabled(opcion); 
        Rm13.setEnabled(opcion); 
        Rm14.setEnabled(opcion); 
        Rm15.setEnabled(opcion); 
        Rm16.setEnabled(opcion); 
        Rm17.setEnabled(opcion); 
        Rm18.setEnabled(opcion); 
        Rm19.setEnabled(opcion); 
        Rm110.setEnabled(opcion); 
        Rm111.setEnabled(opcion); 
        //1 AÑO
        R11.setEnabled(opcion); 
        R12.setEnabled(opcion); 
        R13.setEnabled(opcion); 
        R14.setEnabled(opcion); 
        R15.setEnabled(opcion); 
        R16.setEnabled(opcion); 
        // 2 AÑOS
        R21.setEnabled(opcion); 
        R22.setEnabled(opcion); 
        R23.setEnabled(opcion); 
        R24.setEnabled(opcion); 
        //3 AÑOS
        R31.setEnabled(opcion); 
        R32.setEnabled(opcion); 
        R33.setEnabled(opcion); 
        R34.setEnabled(opcion); 
        //4 AÑOS
        R41.setEnabled(opcion); 
        R42.setEnabled(opcion); 
        R43.setEnabled(opcion); 
        R44.setEnabled(opcion); 
        //5 AÑOS
        R5.setEnabled(opcion); 
        //6 AÑOS
        R6.setEnabled(opcion); 
        //7 AÑOS
        R7.setEnabled(opcion); 
        //8 AÑOS
        R8.setEnabled(opcion); 
        //9 AÑOS
        R9.setEnabled(opcion); 
        //10 AÑOS
        R10.setEnabled(opcion); 
        //11 AÑOS
        R11.setEnabled(opcion); 
 
    }
    public void validaRegistro(int rs_id){
        try {
            PreparedStatement cmd = CCD01.getCn().prepareStatement("SELECT RS_ID FROM CONSULTORIO_EXT_RS_VACUNAS WHERE RS_ID ='"+rs_id+"'");
            ResultSet res = cmd.executeQuery();
            if(res.next()){ // si existe
                Modificar(fecha);
            }else { // no existe
                Guardar(fecha);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: validaRegistro: " + e.toString());
        }
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
                           mensaje1.setVisible(true);
                           mensaje1.setBackground(new Color(255,91,70)); 
                           men.setText("Ingrese una fecha correcta");
                          
         }
        
        return fecha;
    }
    public void enviarDiagnosticos(){
        int fila = tbCiePresun.getSelectedRow();
        FrmCie10.dispose();
        DXCCDRN2.setText(String.valueOf(tbCiePresun.getValueAt(fila, 1)));
    }
    
    public void Guardar(JDateChooser fecha){
        if(fecha.getDate()==null){
            mensaje1.setVisible(true);
            mensaje1.setBackground(new Color(255,91,70)); 
            men.setText("Ingrese una fecha valida");
            b.setVisible(false);
            b1.setVisible(false);
        } else {
            ConsultorioExtRsCcd CXRsCCD = new ConsultorioExtRsCcd();
            ConsultorioExtRsCcd CXRsCCD2 = new ConsultorioExtRsCcd();
            try {

                    CXRsCCD.setRsId(Integer.parseInt(lblId.getText()));
                    //RECIEN NACIDO
                    if(FCCDRN1.getDate()!=null){
                        CXRsCCD.setRn1Fecha(determinarFecha(FCCDRN1)); 
                        CXRsCCD.setRn1Cie10(DXCCDRN1.getText());
                        CXRsCCD.setRn1Fua(FUACCDRN1.getText());
                    }
                    if(FCCDRN2.getDate()!=null){
                        CXRsCCD.setRn2Fecha(determinarFecha(FCCDRN2)); 
                        CXRsCCD.setRn2Cie10(DXCCDRN2.getText());
                        CXRsCCD.setRn2Fua(FUACCDRN2.getText());
                    }
                    if(FCCDRN3.getDate()!=null){
                        CXRsCCD.setRn3Fecha(determinarFecha(FCCDRN3)); 
                        CXRsCCD.setRn3Cie10(DXCCDRN3.getText());
                        CXRsCCD.setRn3Fua(FUACCDRN3.getText());
                    }
                    if(FCCDRN4.getDate()!=null){
                        CXRsCCD.setRn4Fecha(determinarFecha(FCCDRN4)); 
                        CXRsCCD.setRn4Cie10(DXCCDRN4.getText());
                        CXRsCCD.setRn4Fua(FUACCDRN4.getText());
                    }
                    /// MENORES DE UN AÑO
                    if(FCCDM1.getDate()!=null){
                        CXRsCCD.setM1Fecha(determinarFecha(FCCDM1)); 
                        CXRsCCD.setM1Cie10(DXCCDM1.getText());
                        CXRsCCD.setM1Fua(FUACCDM1.getText());
                    }
                    if(FCCDM2.getDate()!=null){
                        CXRsCCD.setM2Fecha(determinarFecha(FCCDM2)); 
                        CXRsCCD.setM2Cie10(DXCCDM2.getText());
                        CXRsCCD.setM2Fua(FUACCDM2.getText());
                    }
                    if(FCCDM3.getDate()!=null){
                        CXRsCCD.setM3Fecha(determinarFecha(FCCDM3)); 
                        CXRsCCD.setM3Cie10(DXCCDM3.getText());
                        CXRsCCD.setM3Fua(FUACCDM3.getText());
                    }
                    if(FCCDM4.getDate()!=null){
                        CXRsCCD.setM4Fecha(determinarFecha(FCCDM4)); 
                        CXRsCCD.setM4Cie10(DXCCDM4.getText());
                        CXRsCCD.setM4Fua(FUACCDM4.getText());
                    }
                    if(FCCDM5.getDate()!=null){
                        CXRsCCD.setM5Fecha(determinarFecha(FCCDM5)); 
                        CXRsCCD.setM5Cie10(DXCCDM5.getText());
                        CXRsCCD.setM5Fua(FUACCDM5.getText());
                    }
                    if(FCCDM6.getDate()!=null){
                        CXRsCCD.setM6Fecha(determinarFecha(FCCDM6)); 
                        CXRsCCD.setM6Cie10(DXCCDM6.getText());
                        CXRsCCD.setM6Fua(FUACCDM6.getText());
                    }
                    if(FCCDM7.getDate()!=null){
                        CXRsCCD.setM7Fecha(determinarFecha(FCCDM7)); 
                        CXRsCCD.setM7Cie10(DXCCDM7.getText());
                        CXRsCCD.setM7Fua(FUACCDM7.getText());
                    }
                    if(FCCDM8.getDate()!=null){
                        CXRsCCD.setM8Fecha(determinarFecha(FCCDM8)); 
                        CXRsCCD.setM8Cie10(DXCCDM8.getText());
                        CXRsCCD.setM8Fua(FUACCDM8.getText());
                    }
                    if(FCCDM9.getDate()!=null){
                        CXRsCCD.setM7Fecha(determinarFecha(FCCDM9)); 
                        CXRsCCD.setM7Cie10(DXCCDM9.getText());
                        CXRsCCD.setM7Fua(FUACCDM9.getText());
                    }
                    if(FCCDM10.getDate()!=null){
                        CXRsCCD.setM10Fecha(determinarFecha(FCCDM10)); 
                        CXRsCCD.setM10Cie10(DXCCDM10.getText());
                        CXRsCCD.setM10Fua(FUACCDM10.getText());
                    }
                    if(FCCDM11.getDate()!=null){
                        CXRsCCD.setM11Fecha(determinarFecha(FCCDM11)); 
                        CXRsCCD.setM11Cie10(DXCCDM11.getText());
                        CXRsCCD.setM11Fua(FUACCDM11.getText());
                    }
                    ////////////////////////////////////////////////////////
                    //1 AÑO
                    if(FCCD11.getDate()!=null){
                        CXRsCCD.setM11Fecha1(determinarFecha(FCCD11)); 
                        CXRsCCD.setM11Cie101(DXCCD11.getText());
                        CXRsCCD.setM11Fua1(FUACCD11.getText());
                    }
                    if(FCCD12.getDate()!=null){
                        CXRsCCD.setM12Fecha(determinarFecha(FCCD12)); 
                        CXRsCCD.setM12Cie10(DXCCD12.getText());
                        CXRsCCD.setM12Fua(FUACCD12.getText());
                    }
                    if(FCCD13.getDate()!=null){
                        CXRsCCD.setM13Fecha(determinarFecha(FCCD13)); 
                        CXRsCCD.setM13Cie10(DXCCD13.getText());
                        CXRsCCD.setM13Fua(FUACCD13.getText());
                    }
                    if(FCCD14.getDate()!=null){
                        CXRsCCD.setM14Fecha(determinarFecha(FCCD14)); 
                        CXRsCCD.setM14Cie10(DXCCD14.getText());
                        CXRsCCD.setM14Fua(FUACCD14.getText());
                    }
                    if(FCCD15.getDate()!=null){
                        CXRsCCD.setM15Fecha(determinarFecha(FCCD15)); 
                        CXRsCCD.setM15Cie10(DXCCD15.getText());
                        CXRsCCD.setM15Fua(FUACCD15.getText());
                    }
                    if(FCCD16.getDate()!=null){
                        CXRsCCD.setM16Fecha(determinarFecha(FCCD16)); 
                        CXRsCCD.setM16Cie10(DXCCD16.getText());
                        CXRsCCD.setM16Fua(FUACCD16.getText());
                    }
                    
                    //////////////////////////////////////////////////
                    //2 AÑOS
                    if(FCCD21.getDate()!=null){
                        CXRsCCD.setM21Fecha(determinarFecha(FCCD21)); 
                        CXRsCCD.setM21Cie10(DXCCD21.getText());
                        CXRsCCD.setM21Fua(FUACCD21.getText());
                    }
                    if(FCCD22.getDate()!=null){
                        CXRsCCD.setM22Fecha(determinarFecha(FCCD22)); 
                        CXRsCCD.setM22Cie10(DXCCD22.getText());
                        CXRsCCD.setM22Fua(FUACCD22.getText());
                    }
                    if(FCCD23.getDate()!=null){
                        CXRsCCD.setM23Fecha(determinarFecha(FCCD23)); 
                        CXRsCCD.setM23Cie10(DXCCD23.getText());
                        CXRsCCD.setM23Fua(FUACCD23.getText());
                    }
                    if(FCCD24.getDate()!=null){
                        CXRsCCD.setM24Fecha(determinarFecha(FCCD24)); 
                        CXRsCCD.setM24Cie10(DXCCD24.getText());
                        CXRsCCD.setM24Fua(FUACCD24.getText());
                    }
                    //////////////////////////////////////////////////
                    //3 AÑOS
                    if(FCCD3A1.getDate()!=null){
                        CXRsCCD.setM31Fecha(determinarFecha(FCCD3A1)); 
                        CXRsCCD.setM31Cie10(DXCCD3A1.getText());
                        CXRsCCD.setM31Fua(FUACCD3A1.getText());
                    }
                    if(FCCD3A2.getDate()!=null){
                        CXRsCCD.setM32Fecha(determinarFecha(FCCD3A2)); 
                        CXRsCCD.setM32Cie10(DXCCD3A2.getText());
                        CXRsCCD.setM32Fua(FUACCD3A2.getText());
                    }
                    if(FCCD3A3.getDate()!=null){
                        CXRsCCD.setM33Fecha(determinarFecha(FCCD3A3)); 
                        CXRsCCD.setM33Cie10(DXCCD3A3.getText());
                        CXRsCCD.setM33Fua(FUACCD3A3.getText());
                    }
                    if(FCCD3A4.getDate()!=null){
                        CXRsCCD.setM34Fecha(determinarFecha(FCCD3A4)); 
                        CXRsCCD.setM34Cie10(DXCCD3A4.getText());
                        CXRsCCD.setM34Fua(FUACCD3A4.getText());
                    }
                    ////////////////////////////////////////////////
                    //4 AÑOS
                    if(FCCD4A1.getDate()!=null){
                        CXRsCCD.setM41Fecha(determinarFecha(FCCD4A1)); 
                        CXRsCCD.setM41Cie10(DXCCD4A1.getText());
                        CXRsCCD.setM41Fua(FUACCD4A1.getText());
                    }
                    if(FCCD4A2.getDate()!=null){
                        CXRsCCD.setM42Fecha(determinarFecha(FCCD4A2)); 
                        CXRsCCD.setM42Cie10(DXCCD4A2.getText());
                        CXRsCCD.setM42Fua(FUACCD4A2.getText());
                    }
                    if(FCCD4A3.getDate()!=null){
                        CXRsCCD.setM43Fecha(determinarFecha(FCCD4A3)); 
                        CXRsCCD.setM43Cie10(DXCCD4A3.getText());
                        CXRsCCD.setM43Fua(FUACCD4A3.getText());
                    }
                    if(FCCD4A4.getDate()!=null){
                        CXRsCCD.setM44Fecha(determinarFecha(FCCD4A4)); 
                        CXRsCCD.setM44Cie10(DXCCD4A4.getText());
                        CXRsCCD.setM44Fua(FUACCD4A4.getText());
                    }
                    ////////////////////////////////////////////////
                    //5 AÑOS
                    if(FCCD5A.getDate()!=null){
                        CXRsCCD.setM5Fecha(determinarFecha(FCCD5A)); 
                        CXRsCCD.setM5Cie10(DXCCD5A.getText());
                        CXRsCCD.setM5Fua(FUACCD5A.getText());
                    }
                    ////////////////////////////////////////////////
                    //6 AÑOS
                    if(FCCD6A.getDate()!=null){
                        CXRsCCD.setM6Fecha(determinarFecha(FCCD6A)); 
                        CXRsCCD.setM6Cie10(DXCCD6A.getText());
                        CXRsCCD.setM6Fua(FUACCD6A.getText());
                    }
                    ////////////////////////////////////////////////
                    //7 AÑOS
                    if(FCCD7A.getDate()!=null){
                        CXRsCCD.setM7Fecha(determinarFecha(FCCD7A)); 
                        CXRsCCD.setM7Cie10(DXCCD7A.getText());
                        CXRsCCD.setM7Fua(FUACCD7A.getText());
                    }
                    ////////////////////////////////////////////////
                    //8 AÑOS
                    if(FCCD8A.getDate()!=null){
                        CXRsCCD.setM8Fecha(determinarFecha(FCCD8A)); 
                        CXRsCCD.setM8Cie10(DXCCD8A.getText());
                        CXRsCCD.setM8Fua(FUACCD8A.getText());
                    }
                    ////////////////////////////////////////////////
                    //9 AÑOS
                    if(FCCD9A.getDate()!=null){
                        CXRsCCD.setM9Fecha(determinarFecha(FCCD9A)); 
                        CXRsCCD.setM9Cie10(DXCCD9A.getText());
                        CXRsCCD.setM9Fua(FUACCD9A.getText());
                    }
                    ////////////////////////////////////////////////
                    //10 AÑOS
                    if(FCCD10A.getDate()!=null){
                        CXRsCCD.setM10Fecha1(determinarFecha(FCCD10A)); 
                        CXRsCCD.setM10Cie101(DXCCD10A.getText());
                        CXRsCCD.setM10Fua1(FUACCD10A.getText());
                    }
                    ////////////////////////////////////////////////
                    //10 AÑOS
                    if(FCCD11A.getDate()!=null){
                        CXRsCCD.setM11Fecha2(determinarFecha(FCCD11A)); 
                        CXRsCCD.setM11Cie102(DXCCD11A.getText());
                        CXRsCCD.setM11Fua2(FUACCD11A.getText());
                    }
                                    if(CXRsCCD.mantenimientoRSAICCD("I")==true){
                                        mensaje1.setVisible(true);
                                        mensaje1.setBackground(new Color(33,115,70)); 
                                        men.setText("Datos Guardados de forma correcta");
                                        b.setText("OK");
                                        b.setVisible(true);
                                        b1.setVisible(false);

                                        btnGuardar.setEnabled(false);
                                        tge=1;
                                        CXRsCCD2.ConsultoriosExtCCDListar(Integer.parseInt(lblId.getText()));
                                        habilitarDatos(false);
                                        habilitarRadio(true);

                                }else {

                                        mensaje1.setVisible(true);
                                        mensaje1.setBackground(new Color(255,91,70)); 
                                        men.setText("Ocurrio un error, Verifique");
                                        b.setVisible(false);
                                        b1.setVisible(false);
                                        tge=7;
                             }  
                                 } catch (Exception e) {
                                   System.out.println("Error: modificar " + e.getMessage());
                 }
    }
    }
    
    public void Modificar(JDateChooser fecha){
    if(fecha.getDate()==null){
        fecha.setEnabled(true);
        //fua.setEnabled(true);
        mensaje1.setVisible(true);
        mensaje1.setBackground(new Color(255,91,70)); 
        men.setText("Ingrese una fecha valida");
        b.setVisible(false);
        b1.setVisible(false);
    } else {
            ConsultorioExtRsCcd CXRsCCD = new ConsultorioExtRsCcd();
            ConsultorioExtRsCcd CXRsCCD2 = new ConsultorioExtRsCcd();
        try {
    
            CXRsCCD.setRsId(Integer.parseInt(lblId.getText()));
                    //RECIEN NACIDO
                    if(FCCDRN1.getDate()!=null){
                        CXRsCCD.setRn1Fecha(determinarFecha(FCCDRN1)); 
                        CXRsCCD.setRn1Cie10(DXCCDRN1.getText());
                        CXRsCCD.setRn1Fua(FUACCDRN1.getText());
                    }
                    if(FCCDRN2.getDate()!=null){
                        CXRsCCD.setRn2Fecha(determinarFecha(FCCDRN2)); 
                        CXRsCCD.setRn2Cie10(DXCCDRN2.getText());
                        CXRsCCD.setRn2Fua(FUACCDRN2.getText());
                    }
                    if(FCCDRN3.getDate()!=null){
                        CXRsCCD.setRn3Fecha(determinarFecha(FCCDRN3)); 
                        CXRsCCD.setRn3Cie10(DXCCDRN3.getText());
                        CXRsCCD.setRn3Fua(FUACCDRN3.getText());
                    }
                    if(FCCDRN4.getDate()!=null){
                        CXRsCCD.setRn4Fecha(determinarFecha(FCCDRN4)); 
                        CXRsCCD.setRn4Cie10(DXCCDRN4.getText());
                        CXRsCCD.setRn4Fua(FUACCDRN4.getText());
                    }
                    /// MENORES DE UN AÑO
                    if(FCCDM1.getDate()!=null){
                        CXRsCCD.setM1Fecha(determinarFecha(FCCDM1)); 
                        CXRsCCD.setM1Cie10(DXCCDM1.getText());
                        CXRsCCD.setM1Fua(FUACCDM1.getText());
                    }
                    if(FCCDM2.getDate()!=null){
                        CXRsCCD.setM2Fecha(determinarFecha(FCCDM2)); 
                        CXRsCCD.setM2Cie10(DXCCDM2.getText());
                        CXRsCCD.setM2Fua(FUACCDM2.getText());
                    }
                    if(FCCDM3.getDate()!=null){
                        CXRsCCD.setM3Fecha(determinarFecha(FCCDM3)); 
                        CXRsCCD.setM3Cie10(DXCCDM3.getText());
                        CXRsCCD.setM3Fua(FUACCDM3.getText());
                    }
                    if(FCCDM4.getDate()!=null){
                        CXRsCCD.setM4Fecha(determinarFecha(FCCDM4)); 
                        CXRsCCD.setM4Cie10(DXCCDM4.getText());
                        CXRsCCD.setM4Fua(FUACCDM4.getText());
                    }
                    if(FCCDM5.getDate()!=null){
                        CXRsCCD.setM5Fecha(determinarFecha(FCCDM5)); 
                        CXRsCCD.setM5Cie10(DXCCDM5.getText());
                        CXRsCCD.setM5Fua(FUACCDM5.getText());
                    }
                    if(FCCDM6.getDate()!=null){
                        CXRsCCD.setM6Fecha(determinarFecha(FCCDM6)); 
                        CXRsCCD.setM6Cie10(DXCCDM6.getText());
                        CXRsCCD.setM6Fua(FUACCDM6.getText());
                    }
                    if(FCCDM7.getDate()!=null){
                        CXRsCCD.setM7Fecha(determinarFecha(FCCDM7)); 
                        CXRsCCD.setM7Cie10(DXCCDM7.getText());
                        CXRsCCD.setM7Fua(FUACCDM7.getText());
                    }
                    if(FCCDM8.getDate()!=null){
                        CXRsCCD.setM8Fecha(determinarFecha(FCCDM8)); 
                        CXRsCCD.setM8Cie10(DXCCDM8.getText());
                        CXRsCCD.setM8Fua(FUACCDM8.getText());
                    }
                    if(FCCDM9.getDate()!=null){
                        CXRsCCD.setM7Fecha(determinarFecha(FCCDM9)); 
                        CXRsCCD.setM7Cie10(DXCCDM9.getText());
                        CXRsCCD.setM7Fua(FUACCDM9.getText());
                    }
                    if(FCCDM10.getDate()!=null){
                        CXRsCCD.setM10Fecha(determinarFecha(FCCDM10)); 
                        CXRsCCD.setM10Cie10(DXCCDM10.getText());
                        CXRsCCD.setM10Fua(FUACCDM10.getText());
                    }
                    if(FCCDM11.getDate()!=null){
                        CXRsCCD.setM11Fecha(determinarFecha(FCCDM11)); 
                        CXRsCCD.setM11Cie10(DXCCDM11.getText());
                        CXRsCCD.setM11Fua(FUACCDM11.getText());
                    }
                    ////////////////////////////////////////////////////////
                    //1 AÑO
                    if(FCCD11.getDate()!=null){
                        CXRsCCD.setM11Fecha1(determinarFecha(FCCD11)); 
                        CXRsCCD.setM11Cie101(DXCCD11.getText());
                        CXRsCCD.setM11Fua1(FUACCD11.getText());
                    }
                    if(FCCD12.getDate()!=null){
                        CXRsCCD.setM12Fecha(determinarFecha(FCCD12)); 
                        CXRsCCD.setM12Cie10(DXCCD12.getText());
                        CXRsCCD.setM12Fua(FUACCD12.getText());
                    }
                    if(FCCD13.getDate()!=null){
                        CXRsCCD.setM13Fecha(determinarFecha(FCCD13)); 
                        CXRsCCD.setM13Cie10(DXCCD13.getText());
                        CXRsCCD.setM13Fua(FUACCD13.getText());
                    }
                    if(FCCD14.getDate()!=null){
                        CXRsCCD.setM14Fecha(determinarFecha(FCCD14)); 
                        CXRsCCD.setM14Cie10(DXCCD14.getText());
                        CXRsCCD.setM14Fua(FUACCD14.getText());
                    }
                    if(FCCD15.getDate()!=null){
                        CXRsCCD.setM15Fecha(determinarFecha(FCCD15)); 
                        CXRsCCD.setM15Cie10(DXCCD15.getText());
                        CXRsCCD.setM15Fua(FUACCD15.getText());
                    }
                    if(FCCD16.getDate()!=null){
                        CXRsCCD.setM16Fecha(determinarFecha(FCCD16)); 
                        CXRsCCD.setM16Cie10(DXCCD16.getText());
                        CXRsCCD.setM16Fua(FUACCD16.getText());
                    }
                    
                    //////////////////////////////////////////////////
                    //2 AÑOS
                    if(FCCD21.getDate()!=null){
                        CXRsCCD.setM21Fecha(determinarFecha(FCCD21)); 
                        CXRsCCD.setM21Cie10(DXCCD21.getText());
                        CXRsCCD.setM21Fua(FUACCD21.getText());
                    }
                    if(FCCD22.getDate()!=null){
                        CXRsCCD.setM22Fecha(determinarFecha(FCCD22)); 
                        CXRsCCD.setM22Cie10(DXCCD22.getText());
                        CXRsCCD.setM22Fua(FUACCD22.getText());
                    }
                    if(FCCD23.getDate()!=null){
                        CXRsCCD.setM23Fecha(determinarFecha(FCCD23)); 
                        CXRsCCD.setM23Cie10(DXCCD23.getText());
                        CXRsCCD.setM23Fua(FUACCD23.getText());
                    }
                    if(FCCD24.getDate()!=null){
                        CXRsCCD.setM24Fecha(determinarFecha(FCCD24)); 
                        CXRsCCD.setM24Cie10(DXCCD24.getText());
                        CXRsCCD.setM24Fua(FUACCD24.getText());
                    }
                    //////////////////////////////////////////////////
                    //3 AÑOS
                    if(FCCD3A1.getDate()!=null){
                        CXRsCCD.setM31Fecha(determinarFecha(FCCD3A1)); 
                        CXRsCCD.setM31Cie10(DXCCD3A1.getText());
                        CXRsCCD.setM31Fua(FUACCD3A1.getText());
                    }
                    if(FCCD3A2.getDate()!=null){
                        CXRsCCD.setM32Fecha(determinarFecha(FCCD3A2)); 
                        CXRsCCD.setM32Cie10(DXCCD3A2.getText());
                        CXRsCCD.setM32Fua(FUACCD3A2.getText());
                    }
                    if(FCCD3A3.getDate()!=null){
                        CXRsCCD.setM33Fecha(determinarFecha(FCCD3A3)); 
                        CXRsCCD.setM33Cie10(DXCCD3A3.getText());
                        CXRsCCD.setM33Fua(FUACCD3A3.getText());
                    }
                    if(FCCD3A4.getDate()!=null){
                        CXRsCCD.setM34Fecha(determinarFecha(FCCD3A4)); 
                        CXRsCCD.setM34Cie10(DXCCD3A4.getText());
                        CXRsCCD.setM34Fua(FUACCD3A4.getText());
                    }
                    ////////////////////////////////////////////////
                    //4 AÑOS
                    if(FCCD4A1.getDate()!=null){
                        CXRsCCD.setM41Fecha(determinarFecha(FCCD4A1)); 
                        CXRsCCD.setM41Cie10(DXCCD4A1.getText());
                        CXRsCCD.setM41Fua(FUACCD4A1.getText());
                    }
                    if(FCCD4A2.getDate()!=null){
                        CXRsCCD.setM42Fecha(determinarFecha(FCCD4A2)); 
                        CXRsCCD.setM42Cie10(DXCCD4A2.getText());
                        CXRsCCD.setM42Fua(FUACCD4A2.getText());
                    }
                    if(FCCD4A3.getDate()!=null){
                        CXRsCCD.setM43Fecha(determinarFecha(FCCD4A3)); 
                        CXRsCCD.setM43Cie10(DXCCD4A3.getText());
                        CXRsCCD.setM43Fua(FUACCD4A3.getText());
                    }
                    if(FCCD4A4.getDate()!=null){
                        CXRsCCD.setM44Fecha(determinarFecha(FCCD4A4)); 
                        CXRsCCD.setM44Cie10(DXCCD4A4.getText());
                        CXRsCCD.setM44Fua(FUACCD4A4.getText());
                    }
                    ////////////////////////////////////////////////
                    //5 AÑOS
                    if(FCCD5A.getDate()!=null){
                        CXRsCCD.setM5Fecha(determinarFecha(FCCD5A)); 
                        CXRsCCD.setM5Cie10(DXCCD5A.getText());
                        CXRsCCD.setM5Fua(FUACCD5A.getText());
                    }
                    ////////////////////////////////////////////////
                    //6 AÑOS
                    if(FCCD6A.getDate()!=null){
                        CXRsCCD.setM6Fecha(determinarFecha(FCCD6A)); 
                        CXRsCCD.setM6Cie10(DXCCD6A.getText());
                        CXRsCCD.setM6Fua(FUACCD6A.getText());
                    }
                    ////////////////////////////////////////////////
                    //7 AÑOS
                    if(FCCD7A.getDate()!=null){
                        CXRsCCD.setM7Fecha(determinarFecha(FCCD7A)); 
                        CXRsCCD.setM7Cie10(DXCCD7A.getText());
                        CXRsCCD.setM7Fua(FUACCD7A.getText());
                    }
                    ////////////////////////////////////////////////
                    //8 AÑOS
                    if(FCCD8A.getDate()!=null){
                        CXRsCCD.setM8Fecha(determinarFecha(FCCD8A)); 
                        CXRsCCD.setM8Cie10(DXCCD8A.getText());
                        CXRsCCD.setM8Fua(FUACCD8A.getText());
                    }
                    ////////////////////////////////////////////////
                    //9 AÑOS
                    if(FCCD9A.getDate()!=null){
                        CXRsCCD.setM9Fecha(determinarFecha(FCCD9A)); 
                        CXRsCCD.setM9Cie10(DXCCD9A.getText());
                        CXRsCCD.setM9Fua(FUACCD9A.getText());
                    }
                    ////////////////////////////////////////////////
                    //10 AÑOS
                    if(FCCD10A.getDate()!=null){
                        CXRsCCD.setM10Fecha1(determinarFecha(FCCD10A)); 
                        CXRsCCD.setM10Cie101(DXCCD10A.getText());
                        CXRsCCD.setM10Fua1(FUACCD10A.getText());
                    }
                    ////////////////////////////////////////////////
                    //10 AÑOS
                    if(FCCD11A.getDate()!=null){
                        CXRsCCD.setM11Fecha2(determinarFecha(FCCD11A)); 
                        CXRsCCD.setM11Cie102(DXCCD11A.getText());
                        CXRsCCD.setM11Fua2(FUACCD11A.getText());
                    }
            if(CXRsCCD.mantenimientoRSAICCD("U")==true){
                mensaje1.setVisible(true);
                mensaje1.setBackground(new Color(33,115,70)); 
                men.setText("Datos Actualizados de forma correcta");
                b.setText("OK");
                b.setVisible(true);
                b1.setVisible(false);
                btnGuardar.setEnabled(false);
                tge=1;
                CXRsCCD2.ConsultoriosExtCCDListar(Integer.parseInt(lblId.getText()));
                habilitarDatos(false);
                habilitarRadio(true);
            }else {
                mensaje1.setVisible(true);
                mensaje1.setBackground(new Color(255,91,70)); 
                men.setText("Ocurrio un error, Verifique");
                b.setVisible(false);
                b1.setVisible(false);
                tge=5;
            }  

            } catch (Exception e) {
               System.out.println("Error: modificar " + e.getMessage());
            }
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FrmCie10 = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        titulo7 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        T7 = new javax.swing.JLabel();
        txtBuscarCie10 = new javax.swing.JTextField();
        jPanel32 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbCiePresun = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            buttonGroup1 = new javax.swing.ButtonGroup();
            jTabbedPane1 = new javax.swing.JTabbedPane();
            CCD = new javax.swing.JPanel();
            jPanel28 = new javax.swing.JPanel();
            jLabel17 = new javax.swing.JLabel();
            jPanel29 = new javax.swing.JPanel();
            jLabel20 = new javax.swing.JLabel();
            CCDRN = new javax.swing.JPanel();
            jPanel67 = new javax.swing.JPanel();
            jLabel58 = new javax.swing.JLabel();
            jPanel68 = new javax.swing.JPanel();
            FCCDRN1 = new com.toedter.calendar.JDateChooser();
            Rrn1 = new javax.swing.JRadioButton();
            jPanel69 = new javax.swing.JPanel();
            FCCDRN2 = new com.toedter.calendar.JDateChooser();
            Rrn2 = new javax.swing.JRadioButton();
            jPanel70 = new javax.swing.JPanel();
            FCCDRN3 = new com.toedter.calendar.JDateChooser();
            Rrn3 = new javax.swing.JRadioButton();
            FUACCDRN3 = new javax.swing.JTextField();
            FUACCDRN1 = new javax.swing.JTextField();
            FUACCDRN2 = new javax.swing.JTextField();
            jPanel71 = new javax.swing.JPanel();
            FCCDRN4 = new com.toedter.calendar.JDateChooser();
            Rrn4 = new javax.swing.JRadioButton();
            FUACCDRN4 = new javax.swing.JTextField();
            DXCCDRN1 = new javax.swing.JLabel();
            DXCCDRN2 = new javax.swing.JLabel();
            DXCCDRN3 = new javax.swing.JLabel();
            DXCCDRN4 = new javax.swing.JLabel();
            LEYENDA = new javax.swing.JPanel();
            jPanel72 = new javax.swing.JPanel();
            jPanel73 = new javax.swing.JPanel();
            jLabel64 = new javax.swing.JLabel();
            jPanel74 = new javax.swing.JPanel();
            jLabel63 = new javax.swing.JLabel();
            jPanel75 = new javax.swing.JPanel();
            jLabel65 = new javax.swing.JLabel();
            jLabel66 = new javax.swing.JLabel();
            CCDM11 = new javax.swing.JPanel();
            jPanel76 = new javax.swing.JPanel();
            jLabel67 = new javax.swing.JLabel();
            jPanel77 = new javax.swing.JPanel();
            FCCDM1 = new com.toedter.calendar.JDateChooser();
            Rm11 = new javax.swing.JRadioButton();
            jPanel78 = new javax.swing.JPanel();
            FCCDM2 = new com.toedter.calendar.JDateChooser();
            Rm12 = new javax.swing.JRadioButton();
            jPanel79 = new javax.swing.JPanel();
            FCCDM3 = new com.toedter.calendar.JDateChooser();
            Rm13 = new javax.swing.JRadioButton();
            FUACCDM3 = new javax.swing.JTextField();
            FUACCDM1 = new javax.swing.JTextField();
            FUACCDM2 = new javax.swing.JTextField();
            jPanel80 = new javax.swing.JPanel();
            FCCDM4 = new com.toedter.calendar.JDateChooser();
            Rm14 = new javax.swing.JRadioButton();
            FUACCDM4 = new javax.swing.JTextField();
            DXCCDM1 = new javax.swing.JLabel();
            DXCCDM2 = new javax.swing.JLabel();
            DXCCDM3 = new javax.swing.JLabel();
            DXCCDM4 = new javax.swing.JLabel();
            CCDM12 = new javax.swing.JPanel();
            jPanel81 = new javax.swing.JPanel();
            jLabel72 = new javax.swing.JLabel();
            jPanel82 = new javax.swing.JPanel();
            FCCDM5 = new com.toedter.calendar.JDateChooser();
            Rm15 = new javax.swing.JRadioButton();
            jPanel83 = new javax.swing.JPanel();
            FCCDM6 = new com.toedter.calendar.JDateChooser();
            Rm16 = new javax.swing.JRadioButton();
            jPanel84 = new javax.swing.JPanel();
            FCCDM7 = new com.toedter.calendar.JDateChooser();
            Rm17 = new javax.swing.JRadioButton();
            FUACCDM7 = new javax.swing.JTextField();
            FUACCDM5 = new javax.swing.JTextField();
            FUACCDM6 = new javax.swing.JTextField();
            DXCCDM5 = new javax.swing.JLabel();
            DXCCDM6 = new javax.swing.JLabel();
            DXCCDM7 = new javax.swing.JLabel();
            jPanel85 = new javax.swing.JPanel();
            FCCDM8 = new com.toedter.calendar.JDateChooser();
            Rm18 = new javax.swing.JRadioButton();
            DXCCDM8 = new javax.swing.JLabel();
            FUACCDM8 = new javax.swing.JTextField();
            jPanel86 = new javax.swing.JPanel();
            FCCDM9 = new com.toedter.calendar.JDateChooser();
            Rm19 = new javax.swing.JRadioButton();
            DXCCDM9 = new javax.swing.JLabel();
            FUACCDM9 = new javax.swing.JTextField();
            jPanel87 = new javax.swing.JPanel();
            FCCDM10 = new com.toedter.calendar.JDateChooser();
            Rm110 = new javax.swing.JRadioButton();
            DXCCDM10 = new javax.swing.JLabel();
            FUACCDM10 = new javax.swing.JTextField();
            jPanel88 = new javax.swing.JPanel();
            FCCDM11 = new com.toedter.calendar.JDateChooser();
            Rm111 = new javax.swing.JRadioButton();
            DXCCDM11 = new javax.swing.JLabel();
            FUACCDM11 = new javax.swing.JTextField();
            jPanel169 = new javax.swing.JPanel();
            jLabel107 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            lblNina = new javax.swing.JLabel();
            lblNino = new javax.swing.JLabel();
            lblId = new javax.swing.JLabel();
            CCD1 = new javax.swing.JPanel();
            CCD1A = new javax.swing.JPanel();
            jPanel89 = new javax.swing.JPanel();
            jLabel82 = new javax.swing.JLabel();
            jPanel90 = new javax.swing.JPanel();
            FCCD11 = new com.toedter.calendar.JDateChooser();
            R11 = new javax.swing.JRadioButton();
            jPanel91 = new javax.swing.JPanel();
            FCCD12 = new com.toedter.calendar.JDateChooser();
            R12 = new javax.swing.JRadioButton();
            jPanel92 = new javax.swing.JPanel();
            FCCD13 = new com.toedter.calendar.JDateChooser();
            R13 = new javax.swing.JRadioButton();
            FUACCD13 = new javax.swing.JTextField();
            FUACCD11 = new javax.swing.JTextField();
            FUACCD12 = new javax.swing.JTextField();
            DXCCD11 = new javax.swing.JLabel();
            DXCCD12 = new javax.swing.JLabel();
            DXCCD13 = new javax.swing.JLabel();
            jPanel93 = new javax.swing.JPanel();
            FCCD14 = new com.toedter.calendar.JDateChooser();
            R14 = new javax.swing.JRadioButton();
            DXCCD14 = new javax.swing.JLabel();
            FUACCD14 = new javax.swing.JTextField();
            jPanel94 = new javax.swing.JPanel();
            FCCD15 = new com.toedter.calendar.JDateChooser();
            R15 = new javax.swing.JRadioButton();
            DXCCD15 = new javax.swing.JLabel();
            FUACCD15 = new javax.swing.JTextField();
            jPanel95 = new javax.swing.JPanel();
            FCCD16 = new com.toedter.calendar.JDateChooser();
            R16 = new javax.swing.JRadioButton();
            DXCCD16 = new javax.swing.JLabel();
            FUACCD16 = new javax.swing.JTextField();
            CCDR2A = new javax.swing.JPanel();
            jPanel96 = new javax.swing.JPanel();
            jLabel89 = new javax.swing.JLabel();
            jPanel97 = new javax.swing.JPanel();
            FCCD21 = new com.toedter.calendar.JDateChooser();
            R21 = new javax.swing.JRadioButton();
            jPanel98 = new javax.swing.JPanel();
            FCCD22 = new com.toedter.calendar.JDateChooser();
            R22 = new javax.swing.JRadioButton();
            jPanel99 = new javax.swing.JPanel();
            FCCD23 = new com.toedter.calendar.JDateChooser();
            R23 = new javax.swing.JRadioButton();
            FUACCD23 = new javax.swing.JTextField();
            FUACCD21 = new javax.swing.JTextField();
            FUACCD22 = new javax.swing.JTextField();
            jPanel100 = new javax.swing.JPanel();
            FCCD24 = new com.toedter.calendar.JDateChooser();
            R24 = new javax.swing.JRadioButton();
            FUACCD24 = new javax.swing.JTextField();
            DXCCD21 = new javax.swing.JLabel();
            DXCCD22 = new javax.swing.JLabel();
            DXCCD23 = new javax.swing.JLabel();
            DXCCD24 = new javax.swing.JLabel();
            CCDR3A = new javax.swing.JPanel();
            jPanel101 = new javax.swing.JPanel();
            jLabel94 = new javax.swing.JLabel();
            jPanel102 = new javax.swing.JPanel();
            FCCD3A1 = new com.toedter.calendar.JDateChooser();
            R31 = new javax.swing.JRadioButton();
            jPanel103 = new javax.swing.JPanel();
            FCCD3A2 = new com.toedter.calendar.JDateChooser();
            R32 = new javax.swing.JRadioButton();
            jPanel104 = new javax.swing.JPanel();
            FCCD3A3 = new com.toedter.calendar.JDateChooser();
            R33 = new javax.swing.JRadioButton();
            FUACCD3A3 = new javax.swing.JTextField();
            FUACCD3A1 = new javax.swing.JTextField();
            FUACCD3A2 = new javax.swing.JTextField();
            jPanel105 = new javax.swing.JPanel();
            FCCD3A4 = new com.toedter.calendar.JDateChooser();
            R34 = new javax.swing.JRadioButton();
            FUACCD3A4 = new javax.swing.JTextField();
            DXCCD3A1 = new javax.swing.JLabel();
            DXCCD3A2 = new javax.swing.JLabel();
            DXCCD3A3 = new javax.swing.JLabel();
            DXCCD3A4 = new javax.swing.JLabel();
            jPanel106 = new javax.swing.JPanel();
            jLabel99 = new javax.swing.JLabel();
            jPanel107 = new javax.swing.JPanel();
            jLabel100 = new javax.swing.JLabel();
            CCDR3A1 = new javax.swing.JPanel();
            jPanel108 = new javax.swing.JPanel();
            jLabel101 = new javax.swing.JLabel();
            jPanel109 = new javax.swing.JPanel();
            FCCD4A1 = new com.toedter.calendar.JDateChooser();
            R41 = new javax.swing.JRadioButton();
            jPanel110 = new javax.swing.JPanel();
            FCCD4A2 = new com.toedter.calendar.JDateChooser();
            R42 = new javax.swing.JRadioButton();
            jPanel111 = new javax.swing.JPanel();
            FCCD4A3 = new com.toedter.calendar.JDateChooser();
            R43 = new javax.swing.JRadioButton();
            FUACCD4A3 = new javax.swing.JTextField();
            FUACCD4A1 = new javax.swing.JTextField();
            FUACCD4A2 = new javax.swing.JTextField();
            jPanel112 = new javax.swing.JPanel();
            FCCD4A4 = new com.toedter.calendar.JDateChooser();
            R44 = new javax.swing.JRadioButton();
            FUACCD4A4 = new javax.swing.JTextField();
            DXCCD4A1 = new javax.swing.JLabel();
            DXCCD4A2 = new javax.swing.JLabel();
            DXCCD4A3 = new javax.swing.JLabel();
            DXCCD4A4 = new javax.swing.JLabel();
            jPanel170 = new javax.swing.JPanel();
            jLabel108 = new javax.swing.JLabel();
            jLabel109 = new javax.swing.JLabel();
            DXCCDRN7 = new javax.swing.JLabel();
            DXCCDRN8 = new javax.swing.JLabel();
            CCD2 = new javax.swing.JPanel();
            jPanel113 = new javax.swing.JPanel();
            jLabel106 = new javax.swing.JLabel();
            jPanel114 = new javax.swing.JPanel();
            jLabel110 = new javax.swing.JLabel();
            CCDR5A = new javax.swing.JPanel();
            jPanel115 = new javax.swing.JPanel();
            jLabel111 = new javax.swing.JLabel();
            jPanel116 = new javax.swing.JPanel();
            FCCD5A = new com.toedter.calendar.JDateChooser();
            R5 = new javax.swing.JRadioButton();
            FUACCD5A = new javax.swing.JTextField();
            DXCCD5A = new javax.swing.JLabel();
            CCDR6A = new javax.swing.JPanel();
            jPanel117 = new javax.swing.JPanel();
            jLabel113 = new javax.swing.JLabel();
            jPanel118 = new javax.swing.JPanel();
            FCCD6A = new com.toedter.calendar.JDateChooser();
            R6 = new javax.swing.JRadioButton();
            FUACCD6A = new javax.swing.JTextField();
            DXCCD6A = new javax.swing.JLabel();
            CCDR7A = new javax.swing.JPanel();
            jPanel119 = new javax.swing.JPanel();
            jLabel115 = new javax.swing.JLabel();
            jPanel120 = new javax.swing.JPanel();
            FCCD7A = new com.toedter.calendar.JDateChooser();
            R7 = new javax.swing.JRadioButton();
            FUACCD7A = new javax.swing.JTextField();
            DXCCD7A = new javax.swing.JLabel();
            CCDR8A = new javax.swing.JPanel();
            jPanel121 = new javax.swing.JPanel();
            jLabel117 = new javax.swing.JLabel();
            jPanel122 = new javax.swing.JPanel();
            FCCD8A = new com.toedter.calendar.JDateChooser();
            R8 = new javax.swing.JRadioButton();
            FUACCD8A = new javax.swing.JTextField();
            DXCCD8A = new javax.swing.JLabel();
            CCDR9A = new javax.swing.JPanel();
            jPanel123 = new javax.swing.JPanel();
            jLabel119 = new javax.swing.JLabel();
            jPanel124 = new javax.swing.JPanel();
            FCCD9A = new com.toedter.calendar.JDateChooser();
            R9 = new javax.swing.JRadioButton();
            FUACCD9A = new javax.swing.JTextField();
            DXCCD9A = new javax.swing.JLabel();
            CCDR10A = new javax.swing.JPanel();
            jPanel125 = new javax.swing.JPanel();
            jLabel121 = new javax.swing.JLabel();
            jPanel126 = new javax.swing.JPanel();
            FCCD10A = new com.toedter.calendar.JDateChooser();
            R10 = new javax.swing.JRadioButton();
            FUACCD10A = new javax.swing.JTextField();
            DXCCD10A = new javax.swing.JLabel();
            CCDR11A = new javax.swing.JPanel();
            jPanel127 = new javax.swing.JPanel();
            jLabel123 = new javax.swing.JLabel();
            jPanel128 = new javax.swing.JPanel();
            FCCD11A = new com.toedter.calendar.JDateChooser();
            R11a = new javax.swing.JRadioButton();
            FUACCD11A = new javax.swing.JTextField();
            DXCCD11A = new javax.swing.JLabel();
            DXCCDRN9 = new javax.swing.JLabel();
            DXCCDRN10 = new javax.swing.JLabel();
            mensaje = new javax.swing.JPanel();
            jPanel31 = new javax.swing.JPanel();
            btneditar4 = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            btnCaccnelar = new javax.swing.JButton();
            mensaje1 = new javax.swing.JPanel();
            men = new javax.swing.JLabel();
            b = new javax.swing.JButton();
            b1 = new javax.swing.JButton();

            FrmCie10.setMinimumSize(new java.awt.Dimension(750, 400));
            FrmCie10.setResizable(false);

            jPanel10.setBackground(new java.awt.Color(102, 102, 102));
            jPanel10.setPreferredSize(new java.awt.Dimension(500, 65));

            titulo7.setBackground(new java.awt.Color(153, 0, 51));
            titulo7.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
            titulo7.setForeground(new java.awt.Color(255, 255, 255));
            titulo7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            titulo7.setText("CIE 10");
            titulo7.setToolTipText("");
            titulo7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

            jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            jLabel36.setForeground(new java.awt.Color(255, 255, 255));
            jLabel36.setText("Código CIE 10 / Diagnóstico Presuntivo");

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

            jPanel32.setBackground(new java.awt.Color(39, 174, 97));

            javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
            jPanel32.setLayout(jPanel32Layout);
            jPanel32Layout.setHorizontalGroup(
                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 750, Short.MAX_VALUE)
            );
            jPanel32Layout.setVerticalGroup(
                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 16, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
            jPanel10.setLayout(jPanel10Layout);
            jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titulo7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(449, 449, Short.MAX_VALUE))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(titulo7)
                    .addGap(9, 9, 9)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(9, 9, 9)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(184, Short.MAX_VALUE)))
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
            tbCiePresun.setSelectionBackground(new java.awt.Color(39, 174, 97));
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
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                .addComponent(jScrollPane4)
            );
            FrmCie10Layout.setVerticalGroup(
                FrmCie10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(FrmCie10Layout.createSequentialGroup()
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
            );

            setBorder(javax.swing.BorderFactory.createCompoundBorder());
            setVisible(true);

            jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

            CCD.setBackground(new java.awt.Color(255, 255, 255));

            jPanel28.setBackground(new java.awt.Color(45, 204, 112));
            jPanel28.setPreferredSize(new java.awt.Dimension(83, 45));

            jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel17.setForeground(new java.awt.Color(255, 255, 255));
            jLabel17.setText("CONTROL DE CRECIMIENTO Y DESARROLLO");

            jPanel29.setBackground(new java.awt.Color(39, 174, 97));

            jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel20.setForeground(new java.awt.Color(255, 255, 255));
            jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
            jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel20MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
            jPanel29.setLayout(jPanel29Layout);
            jPanel29Layout.setHorizontalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20)
                    .addContainerGap())
            );
            jPanel29Layout.setVerticalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jLabel20)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
            jPanel28.setLayout(jPanel28Layout);
            jPanel28Layout.setHorizontalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel17)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel28Layout.setVerticalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            CCDRN.setBackground(new java.awt.Color(204, 204, 204));

            jPanel67.setBackground(new java.awt.Color(102, 102, 102));

            jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel58.setForeground(new java.awt.Color(255, 255, 255));
            jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel58.setText("RECIEN NACIDO");

            javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
            jPanel67.setLayout(jPanel67Layout);
            jPanel67Layout.setHorizontalGroup(
                jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel67Layout.createSequentialGroup()
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel67Layout.setVerticalGroup(
                jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel58)
            );

            jPanel68.setBackground(new java.awt.Color(153, 153, 153));

            FCCDRN1.setBackground(new java.awt.Color(255, 255, 255));
            FCCDRN1.setDateFormatString("dd/MM/yyyy");
            FCCDRN1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            Rrn1.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(Rrn1);
            Rrn1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            Rrn1.setForeground(new java.awt.Color(255, 255, 255));
            Rrn1.setText("1");
            Rrn1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rrn1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
            jPanel68.setLayout(jPanel68Layout);
            jPanel68Layout.setHorizontalGroup(
                jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCDRN1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel68Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(Rrn1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel68Layout.setVerticalGroup(
                jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel68Layout.createSequentialGroup()
                    .addComponent(Rrn1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDRN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );

            jPanel69.setBackground(new java.awt.Color(153, 153, 153));

            FCCDRN2.setBackground(new java.awt.Color(255, 255, 255));
            FCCDRN2.setDateFormatString("dd/MM/yyyy");
            FCCDRN2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            Rrn2.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(Rrn2);
            Rrn2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            Rrn2.setForeground(new java.awt.Color(255, 255, 255));
            Rrn2.setText("2");
            Rrn2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rrn2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
            jPanel69.setLayout(jPanel69Layout);
            jPanel69Layout.setHorizontalGroup(
                jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCDRN2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel69Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(Rrn2)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel69Layout.setVerticalGroup(
                jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel69Layout.createSequentialGroup()
                    .addComponent(Rrn2)
                    .addGap(3, 3, 3)
                    .addComponent(FCCDRN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel70.setBackground(new java.awt.Color(153, 153, 153));

            FCCDRN3.setBackground(new java.awt.Color(255, 255, 255));
            FCCDRN3.setDateFormatString("dd/MM/yyyy");
            FCCDRN3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            Rrn3.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(Rrn3);
            Rrn3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            Rrn3.setForeground(new java.awt.Color(255, 255, 255));
            Rrn3.setText("3");
            Rrn3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rrn3ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
            jPanel70.setLayout(jPanel70Layout);
            jPanel70Layout.setHorizontalGroup(
                jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCDRN3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel70Layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(Rrn3)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel70Layout.setVerticalGroup(
                jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel70Layout.createSequentialGroup()
                    .addComponent(Rrn3)
                    .addGap(3, 3, 3)
                    .addComponent(FCCDRN3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            FUACCDRN3.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    FUACCDRN3CaretUpdate(evt);
                }
            });

            FUACCDRN1.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    FUACCDRN1CaretUpdate(evt);
                }
            });
            FUACCDRN1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    FUACCDRN1MouseClicked(evt);
                }
            });

            FUACCDRN2.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    FUACCDRN2CaretUpdate(evt);
                }
            });
            FUACCDRN2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    FUACCDRN2ActionPerformed(evt);
                }
            });

            jPanel71.setBackground(new java.awt.Color(153, 153, 153));

            FCCDRN4.setBackground(new java.awt.Color(255, 255, 255));
            FCCDRN4.setDateFormatString("dd/MM/yyyy");
            FCCDRN4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            Rrn4.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(Rrn4);
            Rrn4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            Rrn4.setForeground(new java.awt.Color(255, 255, 255));
            Rrn4.setText("4");
            Rrn4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rrn4ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
            jPanel71.setLayout(jPanel71Layout);
            jPanel71Layout.setHorizontalGroup(
                jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCDRN4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel71Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(Rrn4)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel71Layout.setVerticalGroup(
                jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel71Layout.createSequentialGroup()
                    .addComponent(Rrn4)
                    .addGap(3, 3, 3)
                    .addComponent(FCCDRN4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            FUACCDRN4.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    FUACCDRN4CaretUpdate(evt);
                }
            });

            DXCCDRN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDRN1.setText("                                ");
            DXCCDRN1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXCCDRN1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXCCDRN1MouseClicked(evt);
                }
            });

            DXCCDRN2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDRN2.setText("                                ");
            DXCCDRN2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXCCDRN2.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXCCDRN2MouseClicked(evt);
                }
            });

            DXCCDRN3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDRN3.setText("                                ");
            DXCCDRN3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCDRN4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDRN4.setText("                                ");
            DXCCDRN4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDRNLayout = new javax.swing.GroupLayout(CCDRN);
            CCDRN.setLayout(CCDRNLayout);
            CCDRNLayout.setHorizontalGroup(
                CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDRNLayout.createSequentialGroup()
                    .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel68, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDRN1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXCCDRN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDRN2)
                        .addComponent(DXCCDRN2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCDRN3)
                        .addComponent(jPanel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCDRN3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDRN4)
                        .addComponent(DXCCDRN4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            CCDRNLayout.setVerticalGroup(
                CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDRNLayout.createSequentialGroup()
                    .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXCCDRN1)
                        .addComponent(DXCCDRN2)
                        .addComponent(DXCCDRN3)
                        .addComponent(DXCCDRN4))
                    .addGap(0, 0, 0)
                    .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FUACCDRN3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCDRN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCDRN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCDRN4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            LEYENDA.setBackground(new java.awt.Color(204, 204, 204));

            jPanel72.setBackground(new java.awt.Color(153, 153, 153));

            javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
            jPanel72.setLayout(jPanel72Layout);
            jPanel72Layout.setHorizontalGroup(
                jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );
            jPanel72Layout.setVerticalGroup(
                jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 20, Short.MAX_VALUE)
            );

            jPanel73.setBackground(new java.awt.Color(153, 153, 153));

            jLabel64.setBackground(new java.awt.Color(153, 153, 153));
            jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel64.setForeground(new java.awt.Color(255, 255, 255));
            jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel64.setText("Nº CONRTROL");

            javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
            jPanel73.setLayout(jPanel73Layout);
            jPanel73Layout.setHorizontalGroup(
                jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel73Layout.createSequentialGroup()
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 26, Short.MAX_VALUE))
            );
            jPanel73Layout.setVerticalGroup(
                jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel73Layout.createSequentialGroup()
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel74.setBackground(new java.awt.Color(204, 204, 204));

            jLabel63.setForeground(new java.awt.Color(255, 255, 255));
            jLabel63.setText("FECHA");

            javax.swing.GroupLayout jPanel74Layout = new javax.swing.GroupLayout(jPanel74);
            jPanel74.setLayout(jPanel74Layout);
            jPanel74Layout.setHorizontalGroup(
                jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel74Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel74Layout.setVerticalGroup(
                jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel74Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel63))
            );

            jPanel75.setBackground(new java.awt.Color(153, 153, 153));
            jPanel75.setForeground(new java.awt.Color(255, 255, 255));

            jLabel65.setForeground(new java.awt.Color(255, 255, 255));
            jLabel65.setText("DX");

            javax.swing.GroupLayout jPanel75Layout = new javax.swing.GroupLayout(jPanel75);
            jPanel75.setLayout(jPanel75Layout);
            jPanel75Layout.setHorizontalGroup(
                jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel75Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel75Layout.setVerticalGroup(
                jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
            );

            jLabel66.setForeground(new java.awt.Color(255, 255, 255));
            jLabel66.setText("FUA");

            javax.swing.GroupLayout LEYENDALayout = new javax.swing.GroupLayout(LEYENDA);
            LEYENDA.setLayout(LEYENDALayout);
            LEYENDALayout.setHorizontalGroup(
                LEYENDALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(LEYENDALayout.createSequentialGroup()
                    .addGroup(LEYENDALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(LEYENDALayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            LEYENDALayout.setVerticalGroup(
                LEYENDALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LEYENDALayout.createSequentialGroup()
                    .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(jPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            CCDM11.setBackground(new java.awt.Color(204, 204, 204));

            jPanel76.setBackground(new java.awt.Color(102, 102, 102));

            jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel67.setForeground(new java.awt.Color(255, 255, 255));
            jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel67.setText("MENORES DE 1 AÑO");

            javax.swing.GroupLayout jPanel76Layout = new javax.swing.GroupLayout(jPanel76);
            jPanel76.setLayout(jPanel76Layout);
            jPanel76Layout.setHorizontalGroup(
                jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel76Layout.createSequentialGroup()
                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel76Layout.setVerticalGroup(
                jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel67)
            );

            jPanel77.setBackground(new java.awt.Color(153, 153, 153));

            FCCDM1.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM1.setDateFormatString("dd/MM/yyyy");
            FCCDM1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            Rm11.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(Rm11);
            Rm11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            Rm11.setForeground(new java.awt.Color(255, 255, 255));
            Rm11.setText("1º");
            Rm11.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rm11ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel77Layout = new javax.swing.GroupLayout(jPanel77);
            jPanel77.setLayout(jPanel77Layout);
            jPanel77Layout.setHorizontalGroup(
                jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCDM1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel77Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(Rm11)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel77Layout.setVerticalGroup(
                jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel77Layout.createSequentialGroup()
                    .addComponent(Rm11)
                    .addGap(3, 3, 3)
                    .addComponent(FCCDM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel78.setBackground(new java.awt.Color(153, 153, 153));

            FCCDM2.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM2.setDateFormatString("dd/MM/yyyy");
            FCCDM2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            Rm12.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(Rm12);
            Rm12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            Rm12.setForeground(new java.awt.Color(255, 255, 255));
            Rm12.setText("2º");
            Rm12.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rm12ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel78Layout = new javax.swing.GroupLayout(jPanel78);
            jPanel78.setLayout(jPanel78Layout);
            jPanel78Layout.setHorizontalGroup(
                jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCDM2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel78Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(Rm12)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel78Layout.setVerticalGroup(
                jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel78Layout.createSequentialGroup()
                    .addComponent(Rm12)
                    .addGap(3, 3, 3)
                    .addComponent(FCCDM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel79.setBackground(new java.awt.Color(153, 153, 153));

            FCCDM3.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM3.setDateFormatString("dd/MM/yyyy");
            FCCDM3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            Rm13.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(Rm13);
            Rm13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            Rm13.setForeground(new java.awt.Color(255, 255, 255));
            Rm13.setText("3º");
            Rm13.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rm13ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel79Layout = new javax.swing.GroupLayout(jPanel79);
            jPanel79.setLayout(jPanel79Layout);
            jPanel79Layout.setHorizontalGroup(
                jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCDM3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel79Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(Rm13)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel79Layout.setVerticalGroup(
                jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel79Layout.createSequentialGroup()
                    .addComponent(Rm13)
                    .addGap(3, 3, 3)
                    .addComponent(FCCDM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel80.setBackground(new java.awt.Color(153, 153, 153));

            FCCDM4.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM4.setDateFormatString("dd/MM/yyyy");
            FCCDM4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            Rm14.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(Rm14);
            Rm14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            Rm14.setForeground(new java.awt.Color(255, 255, 255));
            Rm14.setText("4º");
            Rm14.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rm14ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel80Layout = new javax.swing.GroupLayout(jPanel80);
            jPanel80.setLayout(jPanel80Layout);
            jPanel80Layout.setHorizontalGroup(
                jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCDM4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel80Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(Rm14)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel80Layout.setVerticalGroup(
                jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel80Layout.createSequentialGroup()
                    .addComponent(Rm14)
                    .addGap(3, 3, 3)
                    .addComponent(FCCDM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCDM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM1.setText("                                ");
            DXCCDM1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCDM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM2.setText("                                ");
            DXCCDM2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCDM3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM3.setText("                                ");
            DXCCDM3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCDM4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM4.setText("                                ");
            DXCCDM4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDM11Layout = new javax.swing.GroupLayout(CCDM11);
            CCDM11.setLayout(CCDM11Layout);
            CCDM11Layout.setHorizontalGroup(
                CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDM11Layout.createSequentialGroup()
                    .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel77, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDM1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXCCDM1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDM2)
                        .addComponent(DXCCDM2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCDM3)
                        .addComponent(jPanel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCDM3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDM4)
                        .addComponent(DXCCDM4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            CCDM11Layout.setVerticalGroup(
                CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDM11Layout.createSequentialGroup()
                    .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXCCDM1)
                        .addComponent(DXCCDM2)
                        .addComponent(DXCCDM3)
                        .addComponent(DXCCDM4))
                    .addGap(0, 0, 0)
                    .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FUACCDM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCDM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCDM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCDM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            CCDM12.setBackground(new java.awt.Color(204, 204, 204));

            jPanel81.setBackground(new java.awt.Color(102, 102, 102));

            jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel72.setForeground(new java.awt.Color(255, 255, 255));
            jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel72.setText("MENORES DE 1 AÑO");

            javax.swing.GroupLayout jPanel81Layout = new javax.swing.GroupLayout(jPanel81);
            jPanel81.setLayout(jPanel81Layout);
            jPanel81Layout.setHorizontalGroup(
                jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel81Layout.createSequentialGroup()
                    .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel81Layout.setVerticalGroup(
                jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel72)
            );

            jPanel82.setBackground(new java.awt.Color(153, 153, 153));

            FCCDM5.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM5.setDateFormatString("dd/MM/yyyy");
            FCCDM5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            Rm15.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(Rm15);
            Rm15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            Rm15.setForeground(new java.awt.Color(255, 255, 255));
            Rm15.setText("5º");
            Rm15.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rm15ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel82Layout = new javax.swing.GroupLayout(jPanel82);
            jPanel82.setLayout(jPanel82Layout);
            jPanel82Layout.setHorizontalGroup(
                jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCDM5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel82Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Rm15)
                    .addGap(39, 39, 39))
            );
            jPanel82Layout.setVerticalGroup(
                jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel82Layout.createSequentialGroup()
                    .addComponent(Rm15)
                    .addGap(3, 3, 3)
                    .addComponent(FCCDM5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel83.setBackground(new java.awt.Color(153, 153, 153));

            FCCDM6.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM6.setDateFormatString("dd/MM/yyyy");
            FCCDM6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            Rm16.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(Rm16);
            Rm16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            Rm16.setForeground(new java.awt.Color(255, 255, 255));
            Rm16.setText("6º");
            Rm16.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rm16ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel83Layout = new javax.swing.GroupLayout(jPanel83);
            jPanel83.setLayout(jPanel83Layout);
            jPanel83Layout.setHorizontalGroup(
                jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCDM6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel83Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(Rm16)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel83Layout.setVerticalGroup(
                jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel83Layout.createSequentialGroup()
                    .addComponent(Rm16)
                    .addGap(3, 3, 3)
                    .addComponent(FCCDM6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel84.setBackground(new java.awt.Color(153, 153, 153));

            FCCDM7.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM7.setDateFormatString("dd/MM/yyyy");
            FCCDM7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            Rm17.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(Rm17);
            Rm17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            Rm17.setForeground(new java.awt.Color(255, 255, 255));
            Rm17.setText("7º");
            Rm17.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rm17ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel84Layout = new javax.swing.GroupLayout(jPanel84);
            jPanel84.setLayout(jPanel84Layout);
            jPanel84Layout.setHorizontalGroup(
                jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCDM7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel84Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(Rm17)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel84Layout.setVerticalGroup(
                jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel84Layout.createSequentialGroup()
                    .addComponent(Rm17)
                    .addGap(3, 3, 3)
                    .addComponent(FCCDM7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            FUACCDM7.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    FUACCDM7CaretUpdate(evt);
                }
            });

            DXCCDM5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM5.setText("                                ");
            DXCCDM5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCDM6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM6.setText("                                ");
            DXCCDM6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCDM7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM7.setText("                                ");
            DXCCDM7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            jPanel85.setBackground(new java.awt.Color(153, 153, 153));

            FCCDM8.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM8.setDateFormatString("dd/MM/yyyy");
            FCCDM8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            Rm18.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(Rm18);
            Rm18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            Rm18.setForeground(new java.awt.Color(255, 255, 255));
            Rm18.setText("8º");
            Rm18.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rm18ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel85Layout = new javax.swing.GroupLayout(jPanel85);
            jPanel85.setLayout(jPanel85Layout);
            jPanel85Layout.setHorizontalGroup(
                jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCDM8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel85Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(Rm18)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel85Layout.setVerticalGroup(
                jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel85Layout.createSequentialGroup()
                    .addComponent(Rm18)
                    .addGap(3, 3, 3)
                    .addComponent(FCCDM8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCDM8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM8.setText("                                ");
            DXCCDM8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            jPanel86.setBackground(new java.awt.Color(153, 153, 153));

            FCCDM9.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM9.setDateFormatString("dd/MM/yyyy");
            FCCDM9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            Rm19.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(Rm19);
            Rm19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            Rm19.setForeground(new java.awt.Color(255, 255, 255));
            Rm19.setText("9º");
            Rm19.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rm19ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel86Layout = new javax.swing.GroupLayout(jPanel86);
            jPanel86.setLayout(jPanel86Layout);
            jPanel86Layout.setHorizontalGroup(
                jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCDM9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel86Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(Rm19)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel86Layout.setVerticalGroup(
                jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel86Layout.createSequentialGroup()
                    .addComponent(Rm19)
                    .addGap(3, 3, 3)
                    .addComponent(FCCDM9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCDM9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM9.setText("                                ");
            DXCCDM9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            jPanel87.setBackground(new java.awt.Color(153, 153, 153));

            FCCDM10.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM10.setDateFormatString("dd/MM/yyyy");
            FCCDM10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            Rm110.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(Rm110);
            Rm110.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            Rm110.setForeground(new java.awt.Color(255, 255, 255));
            Rm110.setText("10º");
            Rm110.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rm110ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel87Layout = new javax.swing.GroupLayout(jPanel87);
            jPanel87.setLayout(jPanel87Layout);
            jPanel87Layout.setHorizontalGroup(
                jPanel87Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCDM10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel87Layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(Rm110)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel87Layout.setVerticalGroup(
                jPanel87Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel87Layout.createSequentialGroup()
                    .addComponent(Rm110)
                    .addGap(3, 3, 3)
                    .addComponent(FCCDM10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCDM10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM10.setText("                                ");
            DXCCDM10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            jPanel88.setBackground(new java.awt.Color(153, 153, 153));

            FCCDM11.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM11.setDateFormatString("dd/MM/yyyy");
            FCCDM11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            Rm111.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(Rm111);
            Rm111.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            Rm111.setForeground(new java.awt.Color(255, 255, 255));
            Rm111.setText("11º");
            Rm111.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Rm111ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel88Layout = new javax.swing.GroupLayout(jPanel88);
            jPanel88.setLayout(jPanel88Layout);
            jPanel88Layout.setHorizontalGroup(
                jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCDM11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel88Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(Rm111)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel88Layout.setVerticalGroup(
                jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel88Layout.createSequentialGroup()
                    .addComponent(Rm111)
                    .addGap(3, 3, 3)
                    .addComponent(FCCDM11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCDM11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM11.setText("                                ");
            DXCCDM11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDM12Layout = new javax.swing.GroupLayout(CCDM12);
            CCDM12.setLayout(CCDM12Layout);
            CCDM12Layout.setHorizontalGroup(
                CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDM12Layout.createSequentialGroup()
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel82, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDM5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXCCDM5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDM6)
                        .addComponent(DXCCDM6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCDM7)
                        .addComponent(jPanel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCDM7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCDM8)
                        .addComponent(jPanel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCDM8))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCDM9)
                        .addComponent(jPanel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCDM9))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCDM10)
                        .addComponent(jPanel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCDM10))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCDM11)
                        .addComponent(jPanel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCDM11))
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            CCDM12Layout.setVerticalGroup(
                CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDM12Layout.createSequentialGroup()
                    .addComponent(jPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDM12Layout.createSequentialGroup()
                            .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jPanel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(DXCCDM5)
                                .addComponent(DXCCDM6)
                                .addComponent(DXCCDM7))
                            .addGap(0, 0, 0)
                            .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(FUACCDM7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUACCDM5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUACCDM6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(CCDM12Layout.createSequentialGroup()
                            .addComponent(jPanel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXCCDM9)
                            .addGap(0, 0, 0)
                            .addComponent(FUACCDM9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCDM12Layout.createSequentialGroup()
                            .addComponent(jPanel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXCCDM8)
                            .addGap(0, 0, 0)
                            .addComponent(FUACCDM8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCDM12Layout.createSequentialGroup()
                            .addComponent(jPanel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXCCDM10)
                            .addGap(0, 0, 0)
                            .addComponent(FUACCDM10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCDM12Layout.createSequentialGroup()
                            .addComponent(jPanel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXCCDM11)
                            .addGap(0, 0, 0)
                            .addComponent(FUACCDM11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            );

            jPanel169.setBackground(new java.awt.Color(153, 153, 153));
            jPanel169.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jPanel169MouseClicked(evt);
                }
            });

            jLabel107.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel107.setForeground(new java.awt.Color(255, 255, 255));
            jLabel107.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Derecha Filled-50.png"))); // NOI18N
            jLabel107.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel107MouseClicked(evt);
                }
            });

            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            jLabel3.setText("1, 2, 3 y 4 AÑOS");

            javax.swing.GroupLayout jPanel169Layout = new javax.swing.GroupLayout(jPanel169);
            jPanel169.setLayout(jPanel169Layout);
            jPanel169Layout.setHorizontalGroup(
                jPanel169Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel169Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel107))
            );
            jPanel169Layout.setVerticalGroup(
                jPanel169Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel169Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel169Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel169Layout.createSequentialGroup()
                            .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(21, 21, 21))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel169Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(45, 45, 45))))
            );

            lblNina.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            lblNina.setForeground(new java.awt.Color(102, 102, 102));
            lblNina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niña-48.png"))); // NOI18N
            lblNina.setText("NIÑOS");
            lblNina.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            lblNina.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            lblNina.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lblNinaMouseClicked(evt);
                }
            });

            lblNino.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            lblNino.setForeground(new java.awt.Color(102, 102, 102));
            lblNino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niño-48.png"))); // NOI18N
            lblNino.setText("NIÑOS");
            lblNino.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            lblNino.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            lblNino.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lblNinoMouseClicked(evt);
                }
            });

            lblId.setText("jLabel1");

            javax.swing.GroupLayout CCDLayout = new javax.swing.GroupLayout(CCD);
            CCD.setLayout(CCDLayout);
            CCDLayout.setHorizontalGroup(
                CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, 1277, Short.MAX_VALUE)
                .addGroup(CCDLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(CCDLayout.createSequentialGroup()
                                .addComponent(CCDM12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jPanel169, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(CCDLayout.createSequentialGroup()
                                .addComponent(LEYENDA, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(CCDRN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(CCDM11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(CCDLayout.createSequentialGroup()
                            .addComponent(lblNina, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblNino, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblId))
                    .addContainerGap(180, Short.MAX_VALUE))
            );
            CCDLayout.setVerticalGroup(
                CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDLayout.createSequentialGroup()
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNina)
                        .addComponent(lblNino))
                    .addGap(18, 18, 18)
                    .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(CCDM11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CCDRN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LEYENDA, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                    .addGap(27, 27, 27)
                    .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(CCDM12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel169, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addComponent(lblId)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("Menores de 1 año", CCD);

            CCD1.setBackground(new java.awt.Color(255, 255, 255));

            CCD1A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel89.setBackground(new java.awt.Color(102, 102, 102));

            jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel82.setForeground(new java.awt.Color(255, 255, 255));
            jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel82.setText("1 AÑO");

            javax.swing.GroupLayout jPanel89Layout = new javax.swing.GroupLayout(jPanel89);
            jPanel89.setLayout(jPanel89Layout);
            jPanel89Layout.setHorizontalGroup(
                jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel89Layout.createSequentialGroup()
                    .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel89Layout.setVerticalGroup(
                jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel82)
            );

            jPanel90.setBackground(new java.awt.Color(153, 153, 153));

            FCCD11.setBackground(new java.awt.Color(204, 204, 204));
            FCCD11.setDateFormatString("dd/MM/yyyy");
            FCCD11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R11.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R11);
            R11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R11.setForeground(new java.awt.Color(255, 255, 255));
            R11.setText("1º");
            R11.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R11ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel90Layout = new javax.swing.GroupLayout(jPanel90);
            jPanel90.setLayout(jPanel90Layout);
            jPanel90Layout.setHorizontalGroup(
                jPanel90Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel90Layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(R11)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel90Layout.setVerticalGroup(
                jPanel90Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel90Layout.createSequentialGroup()
                    .addComponent(R11)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel91.setBackground(new java.awt.Color(153, 153, 153));

            FCCD12.setBackground(new java.awt.Color(204, 204, 204));
            FCCD12.setDateFormatString("dd/MM/yyyy");
            FCCD12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R12.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R12);
            R12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R12.setForeground(new java.awt.Color(255, 255, 255));
            R12.setText("2º");
            R12.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R12ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel91Layout = new javax.swing.GroupLayout(jPanel91);
            jPanel91.setLayout(jPanel91Layout);
            jPanel91Layout.setHorizontalGroup(
                jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel91Layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(R12)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel91Layout.setVerticalGroup(
                jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel91Layout.createSequentialGroup()
                    .addComponent(R12)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel92.setBackground(new java.awt.Color(153, 153, 153));

            FCCD13.setBackground(new java.awt.Color(204, 204, 204));
            FCCD13.setDateFormatString("dd/MM/yyyy");
            FCCD13.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R13.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R13);
            R13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R13.setForeground(new java.awt.Color(255, 255, 255));
            R13.setText("3º");
            R13.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R13ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel92Layout = new javax.swing.GroupLayout(jPanel92);
            jPanel92.setLayout(jPanel92Layout);
            jPanel92Layout.setHorizontalGroup(
                jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel92Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(R13)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel92Layout.setVerticalGroup(
                jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel92Layout.createSequentialGroup()
                    .addComponent(R13)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD11.setText("                                ");
            DXCCD11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD12.setText("                                ");
            DXCCD12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD13.setText("                                ");
            DXCCD13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD13.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            jPanel93.setBackground(new java.awt.Color(153, 153, 153));

            FCCD14.setBackground(new java.awt.Color(204, 204, 204));
            FCCD14.setDateFormatString("dd/MM/yyyy");
            FCCD14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R14.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R14);
            R14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R14.setForeground(new java.awt.Color(255, 255, 255));
            R14.setText("4º");
            R14.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R14ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel93Layout = new javax.swing.GroupLayout(jPanel93);
            jPanel93.setLayout(jPanel93Layout);
            jPanel93Layout.setHorizontalGroup(
                jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel93Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(R14)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel93Layout.setVerticalGroup(
                jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel93Layout.createSequentialGroup()
                    .addComponent(R14)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD14.setText("                                ");
            DXCCD14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            jPanel94.setBackground(new java.awt.Color(153, 153, 153));

            FCCD15.setBackground(new java.awt.Color(204, 204, 204));
            FCCD15.setDateFormatString("dd/MM/yyyy");
            FCCD15.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R15.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R15);
            R15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R15.setForeground(new java.awt.Color(255, 255, 255));
            R15.setText("5º");
            R15.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R15ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel94Layout = new javax.swing.GroupLayout(jPanel94);
            jPanel94.setLayout(jPanel94Layout);
            jPanel94Layout.setHorizontalGroup(
                jPanel94Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel94Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(R15)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel94Layout.setVerticalGroup(
                jPanel94Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel94Layout.createSequentialGroup()
                    .addComponent(R15)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD15.setText("                                ");
            DXCCD15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            jPanel95.setBackground(new java.awt.Color(153, 153, 153));

            FCCD16.setBackground(new java.awt.Color(204, 204, 204));
            FCCD16.setDateFormatString("dd/MM/yyyy");
            FCCD16.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R16.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R16);
            R16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R16.setForeground(new java.awt.Color(255, 255, 255));
            R16.setText("6º");
            R16.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R16ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel95Layout = new javax.swing.GroupLayout(jPanel95);
            jPanel95.setLayout(jPanel95Layout);
            jPanel95Layout.setHorizontalGroup(
                jPanel95Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel95Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(R16)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel95Layout.setVerticalGroup(
                jPanel95Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel95Layout.createSequentialGroup()
                    .addComponent(R16)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD16.setText("                                ");
            DXCCD16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD16.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCD1ALayout = new javax.swing.GroupLayout(CCD1A);
            CCD1A.setLayout(CCD1ALayout);
            CCD1ALayout.setHorizontalGroup(
                CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCD1ALayout.createSequentialGroup()
                    .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel90, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD11, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXCCD11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD12)
                        .addComponent(DXCCD12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCD13)
                        .addComponent(jPanel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCD13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCD14)
                        .addComponent(jPanel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCD14))
                    .addGap(1, 1, 1)
                    .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCD15)
                        .addComponent(jPanel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCD15))
                    .addGap(1, 1, 1)
                    .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCD16)
                        .addComponent(jPanel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCD16)))
            );
            CCD1ALayout.setVerticalGroup(
                CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCD1ALayout.createSequentialGroup()
                    .addComponent(jPanel89, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCD1ALayout.createSequentialGroup()
                            .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jPanel90, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(DXCCD11)
                                .addComponent(DXCCD12)
                                .addComponent(DXCCD13))
                            .addGap(0, 0, 0)
                            .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(FUACCD13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUACCD11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUACCD12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(CCD1ALayout.createSequentialGroup()
                            .addComponent(jPanel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXCCD15)
                            .addGap(0, 0, 0)
                            .addComponent(FUACCD15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCD1ALayout.createSequentialGroup()
                            .addComponent(jPanel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXCCD14)
                            .addGap(0, 0, 0)
                            .addComponent(FUACCD14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCD1ALayout.createSequentialGroup()
                            .addComponent(jPanel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXCCD16)
                            .addGap(0, 0, 0)
                            .addComponent(FUACCD16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            );

            CCDR2A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel96.setBackground(new java.awt.Color(102, 102, 102));

            jLabel89.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel89.setForeground(new java.awt.Color(255, 255, 255));
            jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel89.setText("2 AÑOS");

            javax.swing.GroupLayout jPanel96Layout = new javax.swing.GroupLayout(jPanel96);
            jPanel96.setLayout(jPanel96Layout);
            jPanel96Layout.setHorizontalGroup(
                jPanel96Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel96Layout.createSequentialGroup()
                    .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel96Layout.setVerticalGroup(
                jPanel96Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel89)
            );

            jPanel97.setBackground(new java.awt.Color(153, 153, 153));

            FCCD21.setBackground(new java.awt.Color(204, 204, 204));
            FCCD21.setDateFormatString("dd/MM/yyyy");
            FCCD21.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R21.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R21);
            R21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R21.setForeground(new java.awt.Color(255, 255, 255));
            R21.setText("1º");
            R21.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R21ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel97Layout = new javax.swing.GroupLayout(jPanel97);
            jPanel97.setLayout(jPanel97Layout);
            jPanel97Layout.setHorizontalGroup(
                jPanel97Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel97Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(R21)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel97Layout.setVerticalGroup(
                jPanel97Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel97Layout.createSequentialGroup()
                    .addComponent(R21)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel98.setBackground(new java.awt.Color(153, 153, 153));

            FCCD22.setBackground(new java.awt.Color(204, 204, 204));
            FCCD22.setDateFormatString("dd/MM/yyyy");
            FCCD22.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R22.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R22);
            R22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R22.setForeground(new java.awt.Color(255, 255, 255));
            R22.setText("2º");
            R22.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R22ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel98Layout = new javax.swing.GroupLayout(jPanel98);
            jPanel98.setLayout(jPanel98Layout);
            jPanel98Layout.setHorizontalGroup(
                jPanel98Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel98Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(R22)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel98Layout.setVerticalGroup(
                jPanel98Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel98Layout.createSequentialGroup()
                    .addComponent(R22)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel99.setBackground(new java.awt.Color(153, 153, 153));

            FCCD23.setBackground(new java.awt.Color(204, 204, 204));
            FCCD23.setDateFormatString("dd/MM/yyyy");
            FCCD23.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R23.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R23);
            R23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R23.setForeground(new java.awt.Color(255, 255, 255));
            R23.setText("3º");
            R23.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R23ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel99Layout = new javax.swing.GroupLayout(jPanel99);
            jPanel99.setLayout(jPanel99Layout);
            jPanel99Layout.setHorizontalGroup(
                jPanel99Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel99Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(R23)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel99Layout.setVerticalGroup(
                jPanel99Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel99Layout.createSequentialGroup()
                    .addComponent(R23)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel100.setBackground(new java.awt.Color(153, 153, 153));

            FCCD24.setBackground(new java.awt.Color(204, 204, 204));
            FCCD24.setDateFormatString("dd/MM/yyyy");
            FCCD24.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R24.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R24);
            R24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R24.setForeground(new java.awt.Color(255, 255, 255));
            R24.setText("4º");
            R24.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R24ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel100Layout = new javax.swing.GroupLayout(jPanel100);
            jPanel100.setLayout(jPanel100Layout);
            jPanel100Layout.setHorizontalGroup(
                jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel100Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(R24)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel100Layout.setVerticalGroup(
                jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel100Layout.createSequentialGroup()
                    .addComponent(R24)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD21.setText("                                ");
            DXCCD21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD21.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD22.setText("                                ");
            DXCCD22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD22.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD23.setText("                                ");
            DXCCD23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD23.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD24.setText("                                ");
            DXCCD24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD24.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR2ALayout = new javax.swing.GroupLayout(CCDR2A);
            CCDR2A.setLayout(CCDR2ALayout);
            CCDR2ALayout.setHorizontalGroup(
                CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel96, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR2ALayout.createSequentialGroup()
                    .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel97, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD21, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXCCD21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD22)
                        .addComponent(DXCCD22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCD23)
                        .addComponent(jPanel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCD23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD24)
                        .addComponent(DXCCD24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            CCDR2ALayout.setVerticalGroup(
                CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR2ALayout.createSequentialGroup()
                    .addComponent(jPanel96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel97, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXCCD21)
                        .addComponent(DXCCD22)
                        .addComponent(DXCCD23)
                        .addComponent(DXCCD24))
                    .addGap(0, 0, 0)
                    .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FUACCD23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            CCDR3A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel101.setBackground(new java.awt.Color(102, 102, 102));

            jLabel94.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel94.setForeground(new java.awt.Color(255, 255, 255));
            jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel94.setText("3 AÑOS");

            javax.swing.GroupLayout jPanel101Layout = new javax.swing.GroupLayout(jPanel101);
            jPanel101.setLayout(jPanel101Layout);
            jPanel101Layout.setHorizontalGroup(
                jPanel101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel101Layout.createSequentialGroup()
                    .addComponent(jLabel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel101Layout.setVerticalGroup(
                jPanel101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel94)
            );

            jPanel102.setBackground(new java.awt.Color(153, 153, 153));

            FCCD3A1.setBackground(new java.awt.Color(204, 204, 204));
            FCCD3A1.setDateFormatString("dd/MM/yyyy");
            FCCD3A1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R31.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R31);
            R31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R31.setForeground(new java.awt.Color(255, 255, 255));
            R31.setText("1º");
            R31.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R31ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel102Layout = new javax.swing.GroupLayout(jPanel102);
            jPanel102.setLayout(jPanel102Layout);
            jPanel102Layout.setHorizontalGroup(
                jPanel102Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD3A1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel102Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(R31)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel102Layout.setVerticalGroup(
                jPanel102Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel102Layout.createSequentialGroup()
                    .addComponent(R31)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD3A1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel103.setBackground(new java.awt.Color(153, 153, 153));

            FCCD3A2.setBackground(new java.awt.Color(204, 204, 204));
            FCCD3A2.setDateFormatString("dd/MM/yyyy");
            FCCD3A2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R32.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R32);
            R32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R32.setForeground(new java.awt.Color(255, 255, 255));
            R32.setText("2º");
            R32.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R32ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel103Layout = new javax.swing.GroupLayout(jPanel103);
            jPanel103.setLayout(jPanel103Layout);
            jPanel103Layout.setHorizontalGroup(
                jPanel103Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD3A2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel103Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(R32)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel103Layout.setVerticalGroup(
                jPanel103Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel103Layout.createSequentialGroup()
                    .addComponent(R32)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD3A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel104.setBackground(new java.awt.Color(153, 153, 153));

            FCCD3A3.setBackground(new java.awt.Color(204, 204, 204));
            FCCD3A3.setDateFormatString("dd/MM/yyyy");
            FCCD3A3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R33.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R33);
            R33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R33.setForeground(new java.awt.Color(255, 255, 255));
            R33.setText("3º");
            R33.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R33ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel104Layout = new javax.swing.GroupLayout(jPanel104);
            jPanel104.setLayout(jPanel104Layout);
            jPanel104Layout.setHorizontalGroup(
                jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD3A3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel104Layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(R33)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel104Layout.setVerticalGroup(
                jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel104Layout.createSequentialGroup()
                    .addComponent(R33)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD3A3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel105.setBackground(new java.awt.Color(153, 153, 153));

            FCCD3A4.setBackground(new java.awt.Color(204, 204, 204));
            FCCD3A4.setDateFormatString("dd/MM/yyyy");
            FCCD3A4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R34.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R34);
            R34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R34.setForeground(new java.awt.Color(255, 255, 255));
            R34.setText("4º");
            R34.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R34ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel105Layout = new javax.swing.GroupLayout(jPanel105);
            jPanel105.setLayout(jPanel105Layout);
            jPanel105Layout.setHorizontalGroup(
                jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD3A4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel105Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(R34)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel105Layout.setVerticalGroup(
                jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel105Layout.createSequentialGroup()
                    .addComponent(R34)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD3A4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD3A1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD3A1.setText("                                ");
            DXCCD3A1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD3A1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD3A2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD3A2.setText("                                ");
            DXCCD3A2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD3A2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD3A3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD3A3.setText("                                ");
            DXCCD3A3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD3A3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD3A4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD3A4.setText("                                ");
            DXCCD3A4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD3A4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR3ALayout = new javax.swing.GroupLayout(CCDR3A);
            CCDR3A.setLayout(CCDR3ALayout);
            CCDR3ALayout.setHorizontalGroup(
                CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel101, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR3ALayout.createSequentialGroup()
                    .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel102, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD3A1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXCCD3A1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel103, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD3A2)
                        .addComponent(DXCCD3A2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCD3A3)
                        .addComponent(jPanel104, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCD3A3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD3A4)
                        .addComponent(DXCCD3A4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            CCDR3ALayout.setVerticalGroup(
                CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR3ALayout.createSequentialGroup()
                    .addComponent(jPanel101, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel104, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel103, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel102, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXCCD3A1)
                        .addComponent(DXCCD3A2)
                        .addComponent(DXCCD3A3)
                        .addComponent(DXCCD3A4))
                    .addGap(0, 0, 0)
                    .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FUACCD3A3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD3A1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD3A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD3A4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            jPanel106.setBackground(new java.awt.Color(45, 204, 112));
            jPanel106.setPreferredSize(new java.awt.Dimension(83, 45));

            jLabel99.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel99.setForeground(new java.awt.Color(255, 255, 255));
            jLabel99.setText("CONTROL DE CRECIMIENTO Y DESARROLLO");

            jPanel107.setBackground(new java.awt.Color(39, 174, 97));

            jLabel100.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel100.setForeground(new java.awt.Color(255, 255, 255));
            jLabel100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
            jLabel100.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel100MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel107Layout = new javax.swing.GroupLayout(jPanel107);
            jPanel107.setLayout(jPanel107Layout);
            jPanel107Layout.setHorizontalGroup(
                jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel107Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel100)
                    .addContainerGap())
            );
            jPanel107Layout.setVerticalGroup(
                jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel107Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jLabel100)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel106Layout = new javax.swing.GroupLayout(jPanel106);
            jPanel106.setLayout(jPanel106Layout);
            jPanel106Layout.setHorizontalGroup(
                jPanel106Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel106Layout.createSequentialGroup()
                    .addComponent(jPanel107, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel99)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel106Layout.setVerticalGroup(
                jPanel106Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel106Layout.createSequentialGroup()
                    .addGroup(jPanel106Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel107, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            CCDR3A1.setBackground(new java.awt.Color(204, 204, 204));

            jPanel108.setBackground(new java.awt.Color(102, 102, 102));

            jLabel101.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel101.setForeground(new java.awt.Color(255, 255, 255));
            jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel101.setText("4 AÑOS");

            javax.swing.GroupLayout jPanel108Layout = new javax.swing.GroupLayout(jPanel108);
            jPanel108.setLayout(jPanel108Layout);
            jPanel108Layout.setHorizontalGroup(
                jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel108Layout.createSequentialGroup()
                    .addComponent(jLabel101, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel108Layout.setVerticalGroup(
                jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel101)
            );

            jPanel109.setBackground(new java.awt.Color(153, 153, 153));

            FCCD4A1.setBackground(new java.awt.Color(204, 204, 204));
            FCCD4A1.setDateFormatString("dd/MM/yyyy");
            FCCD4A1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R41.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R41);
            R41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R41.setForeground(new java.awt.Color(255, 255, 255));
            R41.setText("1º");
            R41.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R41ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel109Layout = new javax.swing.GroupLayout(jPanel109);
            jPanel109.setLayout(jPanel109Layout);
            jPanel109Layout.setHorizontalGroup(
                jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD4A1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel109Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(R41)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel109Layout.setVerticalGroup(
                jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel109Layout.createSequentialGroup()
                    .addComponent(R41)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD4A1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel110.setBackground(new java.awt.Color(153, 153, 153));

            FCCD4A2.setBackground(new java.awt.Color(204, 204, 204));
            FCCD4A2.setDateFormatString("dd/MM/yyyy");
            FCCD4A2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R42.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R42);
            R42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R42.setForeground(new java.awt.Color(255, 255, 255));
            R42.setText("2º");
            R42.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R42ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel110Layout = new javax.swing.GroupLayout(jPanel110);
            jPanel110.setLayout(jPanel110Layout);
            jPanel110Layout.setHorizontalGroup(
                jPanel110Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD4A2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel110Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(R42)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel110Layout.setVerticalGroup(
                jPanel110Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel110Layout.createSequentialGroup()
                    .addComponent(R42)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD4A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel111.setBackground(new java.awt.Color(153, 153, 153));

            FCCD4A3.setBackground(new java.awt.Color(204, 204, 204));
            FCCD4A3.setDateFormatString("dd/MM/yyyy");
            FCCD4A3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R43.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R43);
            R43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R43.setForeground(new java.awt.Color(255, 255, 255));
            R43.setText("3º");
            R43.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R43ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel111Layout = new javax.swing.GroupLayout(jPanel111);
            jPanel111.setLayout(jPanel111Layout);
            jPanel111Layout.setHorizontalGroup(
                jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD4A3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel111Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(R43)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel111Layout.setVerticalGroup(
                jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel111Layout.createSequentialGroup()
                    .addComponent(R43)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD4A3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel112.setBackground(new java.awt.Color(153, 153, 153));

            FCCD4A4.setBackground(new java.awt.Color(204, 204, 204));
            FCCD4A4.setDateFormatString("dd/MM/yyyy");
            FCCD4A4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R44.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R44);
            R44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R44.setForeground(new java.awt.Color(255, 255, 255));
            R44.setText("4º");
            R44.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R44ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel112Layout = new javax.swing.GroupLayout(jPanel112);
            jPanel112.setLayout(jPanel112Layout);
            jPanel112Layout.setHorizontalGroup(
                jPanel112Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD4A4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel112Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(R44)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel112Layout.setVerticalGroup(
                jPanel112Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel112Layout.createSequentialGroup()
                    .addComponent(R44)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD4A4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD4A1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD4A1.setText("                                ");
            DXCCD4A1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD4A1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD4A2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD4A2.setText("                                ");
            DXCCD4A2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD4A2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD4A3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD4A3.setText("                                ");
            DXCCD4A3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD4A3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD4A4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD4A4.setText("                                ");
            DXCCD4A4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD4A4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR3A1Layout = new javax.swing.GroupLayout(CCDR3A1);
            CCDR3A1.setLayout(CCDR3A1Layout);
            CCDR3A1Layout.setHorizontalGroup(
                CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR3A1Layout.createSequentialGroup()
                    .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel109, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD4A1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXCCD4A1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel110, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD4A2)
                        .addComponent(DXCCD4A2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCD4A3)
                        .addComponent(jPanel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCD4A3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel112, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD4A4)
                        .addComponent(DXCCD4A4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            CCDR3A1Layout.setVerticalGroup(
                CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR3A1Layout.createSequentialGroup()
                    .addComponent(jPanel108, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel110, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel112, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel109, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXCCD4A1)
                        .addComponent(DXCCD4A2)
                        .addComponent(DXCCD4A3)
                        .addComponent(DXCCD4A4))
                    .addGap(0, 0, 0)
                    .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FUACCD4A3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD4A1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD4A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD4A4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            jPanel170.setBackground(new java.awt.Color(153, 153, 153));
            jPanel170.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jPanel170MouseClicked(evt);
                }
            });

            jLabel108.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel108.setForeground(new java.awt.Color(255, 255, 255));
            jLabel108.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Derecha Filled-50.png"))); // NOI18N
            jLabel108.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel108MouseClicked(evt);
                }
            });

            jLabel109.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel109.setForeground(new java.awt.Color(255, 255, 255));
            jLabel109.setText("5 - 11 AÑOS");

            javax.swing.GroupLayout jPanel170Layout = new javax.swing.GroupLayout(jPanel170);
            jPanel170.setLayout(jPanel170Layout);
            jPanel170Layout.setHorizontalGroup(
                jPanel170Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel170Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel109)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel108))
            );
            jPanel170Layout.setVerticalGroup(
                jPanel170Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel170Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel170Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel170Layout.createSequentialGroup()
                            .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(21, 21, 21))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel170Layout.createSequentialGroup()
                            .addComponent(jLabel109)
                            .addGap(45, 45, 45))))
            );

            DXCCDRN7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niña-48.png"))); // NOI18N
            DXCCDRN7.setText("NIÑOS");
            DXCCDRN7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            DXCCDRN7.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXCCDRN7MouseClicked(evt);
                }
            });

            DXCCDRN8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niño-48.png"))); // NOI18N
            DXCCDRN8.setText("NIÑOS");
            DXCCDRN8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            DXCCDRN8.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXCCDRN8MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout CCD1Layout = new javax.swing.GroupLayout(CCD1);
            CCD1.setLayout(CCD1Layout);
            CCD1Layout.setHorizontalGroup(
                CCD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel106, javax.swing.GroupLayout.DEFAULT_SIZE, 1277, Short.MAX_VALUE)
                .addGroup(CCD1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(CCD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(CCD1Layout.createSequentialGroup()
                                .addComponent(CCD1A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(CCDR2A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(CCD1Layout.createSequentialGroup()
                                .addComponent(CCDR3A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(CCDR3A1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel170, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(CCD1Layout.createSequentialGroup()
                            .addComponent(DXCCDRN7, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(DXCCDRN8, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(58, Short.MAX_VALUE))
            );
            CCD1Layout.setVerticalGroup(
                CCD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCD1Layout.createSequentialGroup()
                    .addComponent(jPanel106, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(CCD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXCCDRN7)
                        .addComponent(DXCCDRN8))
                    .addGap(18, 18, 18)
                    .addGroup(CCD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(CCD1A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CCDR2A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18, 18, Short.MAX_VALUE)
                    .addGroup(CCD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(CCDR3A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CCDR3A1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel170, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(52, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("1 a 4 años", CCD1);

            CCD2.setBackground(new java.awt.Color(255, 255, 255));

            jPanel113.setBackground(new java.awt.Color(45, 204, 112));
            jPanel113.setPreferredSize(new java.awt.Dimension(83, 45));

            jLabel106.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel106.setForeground(new java.awt.Color(255, 255, 255));
            jLabel106.setText("CONTROL DE CRECIMIENTO Y DESARROLLO");

            jPanel114.setBackground(new java.awt.Color(39, 174, 97));

            jLabel110.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel110.setForeground(new java.awt.Color(255, 255, 255));
            jLabel110.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
            jLabel110.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel110MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel114Layout = new javax.swing.GroupLayout(jPanel114);
            jPanel114.setLayout(jPanel114Layout);
            jPanel114Layout.setHorizontalGroup(
                jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel114Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel110)
                    .addContainerGap())
            );
            jPanel114Layout.setVerticalGroup(
                jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel114Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jLabel110)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel113Layout = new javax.swing.GroupLayout(jPanel113);
            jPanel113.setLayout(jPanel113Layout);
            jPanel113Layout.setHorizontalGroup(
                jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel113Layout.createSequentialGroup()
                    .addComponent(jPanel114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel106)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel113Layout.setVerticalGroup(
                jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel113Layout.createSequentialGroup()
                    .addGroup(jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel114, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            CCDR5A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel115.setBackground(new java.awt.Color(102, 102, 102));

            jLabel111.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel111.setForeground(new java.awt.Color(255, 255, 255));
            jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel111.setText("5 AÑOS");

            javax.swing.GroupLayout jPanel115Layout = new javax.swing.GroupLayout(jPanel115);
            jPanel115.setLayout(jPanel115Layout);
            jPanel115Layout.setHorizontalGroup(
                jPanel115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel115Layout.createSequentialGroup()
                    .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel115Layout.setVerticalGroup(
                jPanel115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel111)
            );

            jPanel116.setBackground(new java.awt.Color(153, 153, 153));

            FCCD5A.setBackground(new java.awt.Color(204, 204, 204));
            FCCD5A.setDateFormatString("dd/MM/yyyy");
            FCCD5A.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R5.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R5);
            R5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R5.setForeground(new java.awt.Color(255, 255, 255));
            R5.setText("1º");
            R5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R5ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel116Layout = new javax.swing.GroupLayout(jPanel116);
            jPanel116.setLayout(jPanel116Layout);
            jPanel116Layout.setHorizontalGroup(
                jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD5A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel116Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(R5)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel116Layout.setVerticalGroup(
                jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel116Layout.createSequentialGroup()
                    .addComponent(R5)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD5A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD5A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD5A.setText("                                ");
            DXCCD5A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD5A.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR5ALayout = new javax.swing.GroupLayout(CCDR5A);
            CCDR5A.setLayout(CCDR5ALayout);
            CCDR5ALayout.setHorizontalGroup(
                CCDR5ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel115, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR5ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel116, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUACCD5A, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXCCD5A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            CCDR5ALayout.setVerticalGroup(
                CCDR5ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR5ALayout.createSequentialGroup()
                    .addComponent(jPanel115, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel116, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DXCCD5A)
                    .addGap(0, 0, 0)
                    .addComponent(FUACCD5A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            CCDR6A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel117.setBackground(new java.awt.Color(102, 102, 102));

            jLabel113.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel113.setForeground(new java.awt.Color(255, 255, 255));
            jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel113.setText("6 AÑOS");

            javax.swing.GroupLayout jPanel117Layout = new javax.swing.GroupLayout(jPanel117);
            jPanel117.setLayout(jPanel117Layout);
            jPanel117Layout.setHorizontalGroup(
                jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel117Layout.createSequentialGroup()
                    .addComponent(jLabel113, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel117Layout.setVerticalGroup(
                jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel113)
            );

            jPanel118.setBackground(new java.awt.Color(153, 153, 153));

            FCCD6A.setBackground(new java.awt.Color(204, 204, 204));
            FCCD6A.setDateFormatString("dd/MM/yyyy");
            FCCD6A.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R6.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R6);
            R6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R6.setForeground(new java.awt.Color(255, 255, 255));
            R6.setText("1º");
            R6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R6ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel118Layout = new javax.swing.GroupLayout(jPanel118);
            jPanel118.setLayout(jPanel118Layout);
            jPanel118Layout.setHorizontalGroup(
                jPanel118Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD6A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel118Layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(R6)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel118Layout.setVerticalGroup(
                jPanel118Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel118Layout.createSequentialGroup()
                    .addComponent(R6)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD6A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD6A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD6A.setText("                                ");
            DXCCD6A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD6A.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR6ALayout = new javax.swing.GroupLayout(CCDR6A);
            CCDR6A.setLayout(CCDR6ALayout);
            CCDR6ALayout.setHorizontalGroup(
                CCDR6ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel117, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR6ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel118, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUACCD6A, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXCCD6A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            CCDR6ALayout.setVerticalGroup(
                CCDR6ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR6ALayout.createSequentialGroup()
                    .addComponent(jPanel117, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel118, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DXCCD6A)
                    .addGap(0, 0, 0)
                    .addComponent(FUACCD6A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            CCDR7A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel119.setBackground(new java.awt.Color(102, 102, 102));

            jLabel115.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel115.setForeground(new java.awt.Color(255, 255, 255));
            jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel115.setText("7 AÑOS");

            javax.swing.GroupLayout jPanel119Layout = new javax.swing.GroupLayout(jPanel119);
            jPanel119.setLayout(jPanel119Layout);
            jPanel119Layout.setHorizontalGroup(
                jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel119Layout.createSequentialGroup()
                    .addComponent(jLabel115, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel119Layout.setVerticalGroup(
                jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel115)
            );

            jPanel120.setBackground(new java.awt.Color(153, 153, 153));

            FCCD7A.setBackground(new java.awt.Color(204, 204, 204));
            FCCD7A.setDateFormatString("dd/MM/yyyy");
            FCCD7A.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R7.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R7);
            R7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R7.setForeground(new java.awt.Color(255, 255, 255));
            R7.setText("1º");
            R7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R7ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel120Layout = new javax.swing.GroupLayout(jPanel120);
            jPanel120.setLayout(jPanel120Layout);
            jPanel120Layout.setHorizontalGroup(
                jPanel120Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD7A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel120Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(R7)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel120Layout.setVerticalGroup(
                jPanel120Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel120Layout.createSequentialGroup()
                    .addComponent(R7)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD7A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD7A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD7A.setText("                                ");
            DXCCD7A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD7A.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR7ALayout = new javax.swing.GroupLayout(CCDR7A);
            CCDR7A.setLayout(CCDR7ALayout);
            CCDR7ALayout.setHorizontalGroup(
                CCDR7ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel119, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR7ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel120, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUACCD7A, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXCCD7A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            CCDR7ALayout.setVerticalGroup(
                CCDR7ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR7ALayout.createSequentialGroup()
                    .addComponent(jPanel119, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel120, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DXCCD7A)
                    .addGap(0, 0, 0)
                    .addComponent(FUACCD7A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            CCDR8A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel121.setBackground(new java.awt.Color(102, 102, 102));

            jLabel117.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel117.setForeground(new java.awt.Color(255, 255, 255));
            jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel117.setText("8 AÑOS");

            javax.swing.GroupLayout jPanel121Layout = new javax.swing.GroupLayout(jPanel121);
            jPanel121.setLayout(jPanel121Layout);
            jPanel121Layout.setHorizontalGroup(
                jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel121Layout.createSequentialGroup()
                    .addComponent(jLabel117, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel121Layout.setVerticalGroup(
                jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel117)
            );

            jPanel122.setBackground(new java.awt.Color(153, 153, 153));

            FCCD8A.setBackground(new java.awt.Color(204, 204, 204));
            FCCD8A.setDateFormatString("dd/MM/yyyy");
            FCCD8A.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R8.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R8);
            R8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R8.setForeground(new java.awt.Color(255, 255, 255));
            R8.setText("1º");
            R8.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R8ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel122Layout = new javax.swing.GroupLayout(jPanel122);
            jPanel122.setLayout(jPanel122Layout);
            jPanel122Layout.setHorizontalGroup(
                jPanel122Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD8A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel122Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(R8)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel122Layout.setVerticalGroup(
                jPanel122Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel122Layout.createSequentialGroup()
                    .addComponent(R8)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD8A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD8A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD8A.setText("                                ");
            DXCCD8A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD8A.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR8ALayout = new javax.swing.GroupLayout(CCDR8A);
            CCDR8A.setLayout(CCDR8ALayout);
            CCDR8ALayout.setHorizontalGroup(
                CCDR8ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel121, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR8ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel122, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUACCD8A, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXCCD8A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            CCDR8ALayout.setVerticalGroup(
                CCDR8ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR8ALayout.createSequentialGroup()
                    .addComponent(jPanel121, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel122, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DXCCD8A)
                    .addGap(0, 0, 0)
                    .addComponent(FUACCD8A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            CCDR9A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel123.setBackground(new java.awt.Color(102, 102, 102));

            jLabel119.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel119.setForeground(new java.awt.Color(255, 255, 255));
            jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel119.setText("9 AÑOS");

            javax.swing.GroupLayout jPanel123Layout = new javax.swing.GroupLayout(jPanel123);
            jPanel123.setLayout(jPanel123Layout);
            jPanel123Layout.setHorizontalGroup(
                jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel123Layout.createSequentialGroup()
                    .addComponent(jLabel119, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel123Layout.setVerticalGroup(
                jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel119)
            );

            jPanel124.setBackground(new java.awt.Color(153, 153, 153));

            FCCD9A.setBackground(new java.awt.Color(204, 204, 204));
            FCCD9A.setDateFormatString("dd/MM/yyyy");
            FCCD9A.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R9.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R9);
            R9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R9.setForeground(new java.awt.Color(255, 255, 255));
            R9.setText("1º");
            R9.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R9ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel124Layout = new javax.swing.GroupLayout(jPanel124);
            jPanel124.setLayout(jPanel124Layout);
            jPanel124Layout.setHorizontalGroup(
                jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD9A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel124Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(R9)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel124Layout.setVerticalGroup(
                jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel124Layout.createSequentialGroup()
                    .addComponent(R9)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD9A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD9A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD9A.setText("                                ");
            DXCCD9A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD9A.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR9ALayout = new javax.swing.GroupLayout(CCDR9A);
            CCDR9A.setLayout(CCDR9ALayout);
            CCDR9ALayout.setHorizontalGroup(
                CCDR9ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel123, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR9ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel124, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUACCD9A, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXCCD9A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            CCDR9ALayout.setVerticalGroup(
                CCDR9ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR9ALayout.createSequentialGroup()
                    .addComponent(jPanel123, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel124, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DXCCD9A)
                    .addGap(0, 0, 0)
                    .addComponent(FUACCD9A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            CCDR10A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel125.setBackground(new java.awt.Color(102, 102, 102));

            jLabel121.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel121.setForeground(new java.awt.Color(255, 255, 255));
            jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel121.setText("10 AÑOS");

            javax.swing.GroupLayout jPanel125Layout = new javax.swing.GroupLayout(jPanel125);
            jPanel125.setLayout(jPanel125Layout);
            jPanel125Layout.setHorizontalGroup(
                jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel125Layout.createSequentialGroup()
                    .addComponent(jLabel121, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel125Layout.setVerticalGroup(
                jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel121)
            );

            jPanel126.setBackground(new java.awt.Color(153, 153, 153));

            FCCD10A.setBackground(new java.awt.Color(204, 204, 204));
            FCCD10A.setDateFormatString("dd/MM/yyyy");
            FCCD10A.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R10.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R10);
            R10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R10.setForeground(new java.awt.Color(255, 255, 255));
            R10.setText("1º");
            R10.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R10ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel126Layout = new javax.swing.GroupLayout(jPanel126);
            jPanel126.setLayout(jPanel126Layout);
            jPanel126Layout.setHorizontalGroup(
                jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD10A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel126Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(R10)
                    .addGap(39, 39, 39))
            );
            jPanel126Layout.setVerticalGroup(
                jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel126Layout.createSequentialGroup()
                    .addComponent(R10)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD10A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD10A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD10A.setText("                                ");
            DXCCD10A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD10A.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR10ALayout = new javax.swing.GroupLayout(CCDR10A);
            CCDR10A.setLayout(CCDR10ALayout);
            CCDR10ALayout.setHorizontalGroup(
                CCDR10ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel125, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR10ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel126, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUACCD10A, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXCCD10A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            CCDR10ALayout.setVerticalGroup(
                CCDR10ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR10ALayout.createSequentialGroup()
                    .addComponent(jPanel125, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel126, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DXCCD10A)
                    .addGap(0, 0, 0)
                    .addComponent(FUACCD10A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            CCDR11A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel127.setBackground(new java.awt.Color(102, 102, 102));

            jLabel123.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel123.setForeground(new java.awt.Color(255, 255, 255));
            jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel123.setText("11 AÑOS");

            javax.swing.GroupLayout jPanel127Layout = new javax.swing.GroupLayout(jPanel127);
            jPanel127.setLayout(jPanel127Layout);
            jPanel127Layout.setHorizontalGroup(
                jPanel127Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel127Layout.createSequentialGroup()
                    .addComponent(jLabel123, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel127Layout.setVerticalGroup(
                jPanel127Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel123)
            );

            jPanel128.setBackground(new java.awt.Color(153, 153, 153));

            FCCD11A.setBackground(new java.awt.Color(204, 204, 204));
            FCCD11A.setDateFormatString("dd/MM/yyyy");
            FCCD11A.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            R11a.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(R11a);
            R11a.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            R11a.setForeground(new java.awt.Color(255, 255, 255));
            R11a.setText("1º");
            R11a.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    R11aActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel128Layout = new javax.swing.GroupLayout(jPanel128);
            jPanel128.setLayout(jPanel128Layout);
            jPanel128Layout.setHorizontalGroup(
                jPanel128Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FCCD11A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel128Layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(R11a)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel128Layout.setVerticalGroup(
                jPanel128Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel128Layout.createSequentialGroup()
                    .addComponent(R11a)
                    .addGap(3, 3, 3)
                    .addComponent(FCCD11A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD11A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD11A.setText("                                ");
            DXCCD11A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD11A.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR11ALayout = new javax.swing.GroupLayout(CCDR11A);
            CCDR11A.setLayout(CCDR11ALayout);
            CCDR11ALayout.setHorizontalGroup(
                CCDR11ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel127, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR11ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel128, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUACCD11A, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXCCD11A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            CCDR11ALayout.setVerticalGroup(
                CCDR11ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR11ALayout.createSequentialGroup()
                    .addComponent(jPanel127, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel128, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DXCCD11A)
                    .addGap(0, 0, 0)
                    .addComponent(FUACCD11A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCDRN9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niña-48.png"))); // NOI18N
            DXCCDRN9.setText("NIÑOS");
            DXCCDRN9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            DXCCDRN9.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXCCDRN9MouseClicked(evt);
                }
            });

            DXCCDRN10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niño-48.png"))); // NOI18N
            DXCCDRN10.setText("NIÑOS");
            DXCCDRN10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            DXCCDRN10.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXCCDRN10MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout CCD2Layout = new javax.swing.GroupLayout(CCD2);
            CCD2.setLayout(CCD2Layout);
            CCD2Layout.setHorizontalGroup(
                CCD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel113, javax.swing.GroupLayout.DEFAULT_SIZE, 1277, Short.MAX_VALUE)
                .addGroup(CCD2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(CCD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCD2Layout.createSequentialGroup()
                            .addComponent(CCDR5A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CCDR6A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CCDR7A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CCDR8A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CCDR9A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCD2Layout.createSequentialGroup()
                            .addComponent(CCDR10A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CCDR11A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCD2Layout.createSequentialGroup()
                            .addComponent(DXCCDRN9, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(DXCCDRN10, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(544, Short.MAX_VALUE))
            );
            CCD2Layout.setVerticalGroup(
                CCD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCD2Layout.createSequentialGroup()
                    .addComponent(jPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addGroup(CCD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXCCDRN9)
                        .addComponent(DXCCDRN10))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(CCD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(CCDR5A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CCDR6A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CCDR7A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CCDR8A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CCDR9A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(27, 27, 27)
                    .addGroup(CCD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(CCDR10A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CCDR11A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(68, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("5 a 11 años", CCD2);

            mensaje.setBackground(new java.awt.Color(102, 102, 102));

            jPanel31.setBackground(new java.awt.Color(51, 51, 51));

            btneditar4.setForeground(new java.awt.Color(240, 240, 240));
            btneditar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
            btneditar4.setMnemonic('N');
            btneditar4.setContentAreaFilled(false);
            btneditar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btneditar4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btneditar4.setIconTextGap(30);
            btneditar4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btneditar4ActionPerformed(evt);
                }
            });

            btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
            btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
            btnGuardar.setMnemonic('N');
            btnGuardar.setText("Guardar");
            btnGuardar.setContentAreaFilled(false);
            btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnGuardar.setIconTextGap(30);
            btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGuardarActionPerformed(evt);
                }
            });

            btnCaccnelar.setForeground(new java.awt.Color(240, 240, 240));
            btnCaccnelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Deshacer-30.png"))); // NOI18N
            btnCaccnelar.setMnemonic('N');
            btnCaccnelar.setContentAreaFilled(false);
            btnCaccnelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnCaccnelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnCaccnelar.setIconTextGap(30);
            btnCaccnelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnCaccnelarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
            jPanel31.setLayout(jPanel31Layout);
            jPanel31Layout.setHorizontalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                    .addContainerGap(124, Short.MAX_VALUE)
                    .addComponent(btnCaccnelar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btneditar4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(64, Short.MAX_VALUE)))
            );
            jPanel31Layout.setVerticalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel31Layout.createSequentialGroup()
                    .addComponent(btnCaccnelar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btneditar4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );

            mensaje1.setBackground(new java.awt.Color(33, 115, 70));

            men.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            men.setForeground(new java.awt.Color(255, 255, 255));
            men.setText("Desea Actualizar el Registro ?");

            b.setForeground(new java.awt.Color(240, 240, 240));
            b.setText("Si");
            b.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
            b.setContentAreaFilled(false);
            b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            b.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            b.setIconTextGap(30);
            b.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    bActionPerformed(evt);
                }
            });

            b1.setForeground(new java.awt.Color(240, 240, 240));
            b1.setText("No");
            b1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
            b1.setContentAreaFilled(false);
            b1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            b1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            b1.setIconTextGap(30);
            b1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    b1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout mensaje1Layout = new javax.swing.GroupLayout(mensaje1);
            mensaje1.setLayout(mensaje1Layout);
            mensaje1Layout.setHorizontalGroup(
                mensaje1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mensaje1Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(men)
                    .addGap(46, 46, 46)
                    .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            mensaje1Layout.setVerticalGroup(
                mensaje1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mensaje1Layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(mensaje1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(men)
                        .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout mensajeLayout = new javax.swing.GroupLayout(mensaje);
            mensaje.setLayout(mensajeLayout);
            mensajeLayout.setHorizontalGroup(
                mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mensajeLayout.createSequentialGroup()
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(mensaje1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
            );
            mensajeLayout.setVerticalGroup(
                mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mensaje1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1)
                .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jTabbedPane1)
                    .addGap(0, 0, 0)
                    .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        RegistroSeguimiento.jTabbedPane1.setSelectedIndex(0);
        this.dispose();
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel107MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel107MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel107MouseClicked

    private void jPanel169MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel169MouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jPanel169MouseClicked

    private void jLabel100MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel100MouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel100MouseClicked

    private void jLabel108MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel108MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel108MouseClicked

    private void jPanel170MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel170MouseClicked
      jTabbedPane1.setSelectedIndex(2);          
    }//GEN-LAST:event_jPanel170MouseClicked

    private void jLabel110MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel110MouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel110MouseClicked

    private void btneditar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar4ActionPerformed

    }//GEN-LAST:event_btneditar4ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
     validaRegistro(Integer.parseInt(lblId.getText()));
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void FUACCDRN2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FUACCDRN2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FUACCDRN2ActionPerformed

    private void FUACCDRN1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_FUACCDRN1CaretUpdate
      
    }//GEN-LAST:event_FUACCDRN1CaretUpdate

    private void FUACCDRN2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_FUACCDRN2CaretUpdate
       
    }//GEN-LAST:event_FUACCDRN2CaretUpdate

    private void FUACCDRN3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_FUACCDRN3CaretUpdate
     
    }//GEN-LAST:event_FUACCDRN3CaretUpdate

    private void FUACCDRN4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_FUACCDRN4CaretUpdate
        
    }//GEN-LAST:event_FUACCDRN4CaretUpdate

    private void FUACCDRN1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FUACCDRN1MouseClicked
      

    }//GEN-LAST:event_FUACCDRN1MouseClicked

    private void txtBuscarCie10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCie10CaretUpdate
        ConsultorioExtRsCcd CCDBUSCAR = new ConsultorioExtRsCcd();
        CCDBUSCAR.cargarDatosCie10(txtBuscarCie10.getText(), tbCiePresun);
    }//GEN-LAST:event_txtBuscarCie10CaretUpdate

    private void txtBuscarCie10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCie10KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbCiePresun.getSelectionModel().setSelectionInterval(0, 0);
            tbCiePresun.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarCie10KeyPressed

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T7MouseClicked

    private void DXCCDRN1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXCCDRN1MouseClicked
        ConsultorioExtRsCcd adTopico = new ConsultorioExtRsCcd();
//        adTopico.cargarDatosCie10("", tbCiePresun);
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXCCDRN1MouseClicked

    private void tbCiePresunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCiePresunMouseClicked
        // TODO add your handling code here:
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
            enviarDiagnosticos();
        }
    }//GEN-LAST:event_tbCiePresunKeyPressed

    private void lblNinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNinaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNinaMouseClicked

    private void lblNinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNinoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNinoMouseClicked

    private void DXCCDRN7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXCCDRN7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DXCCDRN7MouseClicked

    private void DXCCDRN8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXCCDRN8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DXCCDRN8MouseClicked

    private void DXCCDRN9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXCCDRN9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DXCCDRN9MouseClicked

    private void DXCCDRN10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXCCDRN10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DXCCDRN10MouseClicked

    private void FUACCDM7CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_FUACCDM7CaretUpdate
        if(FUACCDM7.getText().equals("")){
            FUACCDM7.setEnabled(true);
        }else{
            FUACCDM8.setEnabled(false);
        }
    }//GEN-LAST:event_FUACCDM7CaretUpdate

    private void Rm19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rm19ActionPerformed
        if(FCCDM9.getDate()==null){
            if(Rm19.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCDM9.setEnabled(true);
                FCCDM9.setEnabled(true);
                DXCCDM9.requestFocus();
                FUACCDM9.setEditable(true); 
                fecha=FCCDM9;
                fua = FUACCDM9;
                cie10=DXCCDM9;
            }
        } else {
            FCCDM9.setEnabled(false);
        }
    }//GEN-LAST:event_Rm19ActionPerformed

    private void R14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R14ActionPerformed
         if(FCCD14.getDate()==null){
            if(R14.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD14.setEnabled(true);
                FCCD14.setEnabled(true);
                DXCCD14.requestFocus();
                FUACCD14.setEditable(true); 
                fecha=FCCD14;
                fua = FUACCD14;
                cie10=DXCCD14;
            }
        } else {
            FCCD14.setEnabled(false);
        }
    }//GEN-LAST:event_R14ActionPerformed

    private void R24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R24ActionPerformed
         if(FCCD24.getDate()==null){
            if(R24.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD24.setEnabled(true);
                FCCD24.setEnabled(true);
                DXCCD24.requestFocus();
                FUACCD24.setEditable(true); 
                fecha=FCCD24;
                fua = FUACCD24;
                cie10=DXCCD24;
            }
        } else {
            FCCD21.setEnabled(false);
        }
    }//GEN-LAST:event_R24ActionPerformed

    private void R34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R34ActionPerformed
        if(FCCD3A4.getDate()==null){
            if(R34.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD3A4.setEnabled(true);
                FCCD3A4.setEnabled(true);
                DXCCD3A4.requestFocus();
                FUACCD3A4.setEditable(true); 
                fecha=FCCD3A4;
                fua = FUACCD3A4;
                cie10=DXCCD3A4;
            }
        } else {
            FCCD3A4.setEnabled(false);
        }
    }//GEN-LAST:event_R34ActionPerformed

    private void R44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R44ActionPerformed
        if(FCCD4A4.getDate()==null){
            if(R44.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD4A4.setEnabled(true);
                FCCD4A4.setEnabled(true);
                DXCCD4A4.requestFocus();
                FUACCD4A4.setEditable(true); 
                fecha=FCCD4A4;
                fua = FUACCD4A4;
                cie10=DXCCD4A4;
            }
        } else {
            FCCD4A4.setEnabled(false);
        }
    }//GEN-LAST:event_R44ActionPerformed

    private void btnCaccnelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaccnelarActionPerformed
       
        habilitarRadio(true);
        habilitarDatos(false);
    }//GEN-LAST:event_btnCaccnelarActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        if (tge==3 || tge==1 || tge==9){
            mensaje1.setVisible(false);

        }

        if (tge==2){
            //            Modificar();

            btneditar4.setEnabled(false);
            ;

        }

    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        mensaje1.setVisible(false);
    }//GEN-LAST:event_b1ActionPerformed

    private void R11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R11ActionPerformed
        if(FCCD11.getDate()==null){
            if(R11.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD11.setEnabled(true);
                FCCD11.setEnabled(true);
                DXCCD11.requestFocus();
                FUACCD11.setEditable(true); 
                fecha=FCCD11;
                fua = FUACCD11;
                cie10=DXCCD11;
            }
        } else {
            FCCD11.setEnabled(false);
        }
    }//GEN-LAST:event_R11ActionPerformed

    private void R12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R12ActionPerformed
         if(FCCD12.getDate()==null){
            if(R12.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD12.setEnabled(true);
                FCCD12.setEnabled(true);
                DXCCD12.requestFocus();
                FUACCD12.setEditable(true); 
                fecha=FCCD12;
                fua = FUACCD12;
                cie10=DXCCD12;
            }
        } else {
            FCCD12.setEnabled(false);
        }
    }//GEN-LAST:event_R12ActionPerformed

    private void R13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R13ActionPerformed
         if(FCCD13.getDate()==null){
            if(R13.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD13.setEnabled(true);
                FCCD13.setEnabled(true);
                DXCCD13.requestFocus();
                FUACCD13.setEditable(true); 
                fecha=FCCD13;
                fua = FUACCD13;
                cie10=DXCCD13;
            }
        } else {
            FCCD13.setEnabled(false);
        }
    }//GEN-LAST:event_R13ActionPerformed

    private void R15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R15ActionPerformed
        if(FCCD15.getDate()==null){
            if(R15.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD15.setEnabled(true);
                FCCD15.setEnabled(true);
                DXCCD15.requestFocus();
                FUACCD15.setEditable(true); 
                fecha=FCCD15;
                fua = FUACCD15;
                cie10=DXCCD15;
            }
        } else {
            FCCD15.setEnabled(false);
        }
    }//GEN-LAST:event_R15ActionPerformed

    private void R16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R16ActionPerformed
         if(FCCD16.getDate()==null){
            if(R16.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD16.setEnabled(true);
                FCCD16.setEnabled(true);
                DXCCD16.requestFocus();
                FUACCD16.setEditable(true); 
                fecha=FCCD16;
                fua = FUACCD16;
                cie10=DXCCD16;
            }
        } else {
            FCCD16.setEnabled(false);
        }
    }//GEN-LAST:event_R16ActionPerformed

    private void Rrn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rrn1ActionPerformed
        if(FCCDRN1.getDate()==null){
            if(Rrn1.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCDRN1.setEnabled(true);
                FCCDRN1.setEnabled(true);
                DXCCDRN1.requestFocus();
                FUACCDRN1.setEditable(true); 
                fecha=FCCDRN1;
                fua = FUACCD11;
                cie10=DXCCD11;
            }
        } else {
            FCCDRN1.setEnabled(false);
        }
    }//GEN-LAST:event_Rrn1ActionPerformed

    private void Rrn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rrn2ActionPerformed
        if(FCCDRN2.getDate()==null){
            if(Rrn2.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCDRN2.setEnabled(true);
                FCCDRN2.setEnabled(true);
                DXCCDRN2.requestFocus();
                FUACCDRN2.setEditable(true); 
                fecha=FCCDRN2;
                fua = FUACCDRN2;
                cie10=DXCCDRN2;
            }
        } else {
            FCCDRN2.setEnabled(false);
        }
    }//GEN-LAST:event_Rrn2ActionPerformed

    private void Rrn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rrn3ActionPerformed
        if(FCCDRN3.getDate()==null){
            if(Rrn3.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCDRN3.setEnabled(true);
                FCCDRN3.setEnabled(true);
                DXCCDRN3.requestFocus();
                FUACCDRN3.setEditable(true); 
                fecha=FCCDRN3;
                fua = FUACCDRN3;
                cie10=DXCCDRN3;
            }
        } else {
            FCCDRN3.setEnabled(false);
        }
    }//GEN-LAST:event_Rrn3ActionPerformed

    private void Rrn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rrn4ActionPerformed
         if(FCCDRN4.getDate()==null){
            if(Rrn4.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCDRN4.setEnabled(true);
                FCCDRN4.setEnabled(true);
                DXCCDRN4.requestFocus();
                FUACCDRN4.setEditable(true); 
                fecha=FCCDRN4;
                fua = FUACCDRN4;
                cie10=DXCCDRN4;
            }
        } else {
            FCCDRN4.setEnabled(false);
        }
    }//GEN-LAST:event_Rrn4ActionPerformed

    private void Rm11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rm11ActionPerformed
        if(FCCDM1.getDate()==null){
            if(Rm11.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCDM1.setEnabled(true);
                FCCDM1.setEnabled(true);
                DXCCDM1.requestFocus();
                FUACCDM1.setEditable(true); 
                fecha=FCCDM1;
                fua = FUACCDM1;
                cie10=DXCCDM1;
            }
        } else {
            FCCDM1.setEnabled(false);
        }
    }//GEN-LAST:event_Rm11ActionPerformed

    private void Rm12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rm12ActionPerformed
        if(FCCDM2.getDate()==null){
            if(Rm12.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCDM2.setEnabled(true);
                FCCDM2.setEnabled(true);
                DXCCDM2.requestFocus();
                FUACCDM2.setEditable(true); 
                fecha=FCCDM2;
                fua = FUACCDM2;
                cie10=DXCCDM2;
            }
        } else {
            FCCDM2.setEnabled(false);
        }
    }//GEN-LAST:event_Rm12ActionPerformed

    private void Rm13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rm13ActionPerformed
        if(FCCDM3.getDate()==null){
            if(Rm13.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCDM3.setEnabled(true);
                FCCDM3.setEnabled(true);
                DXCCDM3.requestFocus();
                FUACCDM3.setEditable(true); 
                fecha=FCCDM3;
                fua = FUACCDM3;
                cie10=DXCCDM3;
            }
        } else {
            FCCDM3.setEnabled(false);
        }
    }//GEN-LAST:event_Rm13ActionPerformed

    private void Rm14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rm14ActionPerformed
        if(FCCDM4.getDate()==null){
            if(Rm14.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCDM4.setEnabled(true);
                FCCDM4.setEnabled(true);
                DXCCDM4.requestFocus();
                FUACCDM4.setEditable(true); 
                fecha=FCCDM4;
                fua = FUACCDM4;
                cie10=DXCCDM4;
            }
        } else {
            FCCDM4.setEnabled(false);
        }
    }//GEN-LAST:event_Rm14ActionPerformed

    private void Rm15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rm15ActionPerformed
        if(FCCDM5.getDate()==null){
            if(Rm15.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCDM5.setEnabled(true);
                FCCDM5.setEnabled(true);
                DXCCDM5.requestFocus();
                FUACCDM5.setEditable(true); 
                fecha=FCCDM5;
                fua = FUACCDM5;
                cie10=DXCCDM5;
            }
        } else {
            FCCDM5.setEnabled(false);
        }
    }//GEN-LAST:event_Rm15ActionPerformed

    private void Rm16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rm16ActionPerformed
        if(FCCDM6.getDate()==null){
            if(Rm16.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCDM6.setEnabled(true);
                FCCDM6.setEnabled(true);
                DXCCDM6.requestFocus();
                FUACCDM6.setEditable(true); 
                fecha=FCCDM6;
                fua = FUACCDM6;
                cie10=DXCCDM6;
            }
        } else {
            FCCDM6.setEnabled(false);
        }
    }//GEN-LAST:event_Rm16ActionPerformed

    private void Rm17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rm17ActionPerformed
       if(FCCDM7.getDate()==null){
            if(Rm17.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCDM7.setEnabled(true);
                FCCDM7.setEnabled(true);
                DXCCDM7.requestFocus();
                FUACCDM7.setEditable(true); 
                fecha=FCCDM7;
                fua = FUACCDM7;
                cie10=DXCCDM7;
            }
        } else {
            FCCDM7.setEnabled(false);
        }
    }//GEN-LAST:event_Rm17ActionPerformed

    private void Rm18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rm18ActionPerformed
        if(FCCDM8.getDate()==null){
            if(Rm18.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCDM8.setEnabled(true);
                FCCDM8.setEnabled(true);
                DXCCDM8.requestFocus();
                FUACCDM8.setEditable(true); 
                fecha=FCCDM8;
                fua = FUACCDM8;
                cie10=DXCCDM8;
            }
        } else {
            FCCDM8.setEnabled(false);
        }
    }//GEN-LAST:event_Rm18ActionPerformed

    private void Rm110ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rm110ActionPerformed
        if(FCCDM10.getDate()==null){
            if(Rm110.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCDM10.setEnabled(true);
                FCCDM10.setEnabled(true);
                DXCCDM10.requestFocus();
                FUACCDM10.setEditable(true); 
                fecha=FCCDM10;
                fua = FUACCDM10;
                cie10=DXCCDM10;
            }
        } else {
            FCCDM10.setEnabled(false);
        }
    }//GEN-LAST:event_Rm110ActionPerformed

    private void Rm111ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rm111ActionPerformed
        if(FCCDM11.getDate()==null){
            if(Rm111.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCDM11.setEnabled(true);
                FCCDM11.setEnabled(true);
                DXCCDM11.requestFocus();
                FUACCDM11.setEditable(true); 
                fecha=FCCDM11;
                fua = FUACCDM11;
                cie10=DXCCDM11;
            }
        } else {
            FCCDM11.setEnabled(false);
        }
    }//GEN-LAST:event_Rm111ActionPerformed

    private void R21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R21ActionPerformed
        if(FCCD21.getDate()==null){
            if(R21.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD21.setEnabled(true);
                FCCD21.setEnabled(true);
                DXCCD21.requestFocus();
                FUACCD21.setEditable(true); 
                fecha=FCCD21;
                fua = FUACCD21;
                cie10=DXCCD21;
            }
        } else {
            FCCD21.setEnabled(false);
        }
    }//GEN-LAST:event_R21ActionPerformed

    private void R22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R22ActionPerformed
         if(FCCD22.getDate()==null){
            if(R22.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD22.setEnabled(true);
                FCCD22.setEnabled(true);
                DXCCD22.requestFocus();
                FUACCD22.setEditable(true); 
                fecha=FCCD22;
                fua = FUACCD22;
                cie10=DXCCD22;
            }
        } else {
            FCCD22.setEnabled(false);
        }
    }//GEN-LAST:event_R22ActionPerformed

    private void R23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R23ActionPerformed
         if(FCCD23.getDate()==null){
            if(R23.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD23.setEnabled(true);
                FCCD23.setEnabled(true);
                DXCCD23.requestFocus();
                FUACCD23.setEditable(true); 
                fecha=FCCD23;
                fua = FUACCD23;
                cie10=DXCCD23;
            }
        } else {
            FCCD23.setEnabled(false);
        }
    }//GEN-LAST:event_R23ActionPerformed

    private void R31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R31ActionPerformed
        if(FCCD3A1.getDate()==null){
            if(R31.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD3A1.setEnabled(true);
                FCCD3A1.setEnabled(true);
                DXCCD3A1.requestFocus();
                FUACCD3A1.setEditable(true); 
                fecha=FCCD3A1;
                fua = FUACCD3A1;
                cie10=DXCCD3A1;
            }
        } else {
            FCCD3A1.setEnabled(false);
        }
    }//GEN-LAST:event_R31ActionPerformed

    private void R32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R32ActionPerformed
        if(FCCD3A2.getDate()==null){
            if(R32.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD3A2.setEnabled(true);
                FCCD3A2.setEnabled(true);
                DXCCD3A2.requestFocus();
                FUACCD3A2.setEditable(true); 
                fecha=FCCD3A2;
                fua = FUACCD3A2;
                cie10=DXCCD3A2;
            }
        } else {
            FCCD3A2.setEnabled(false);
        }
    }//GEN-LAST:event_R32ActionPerformed

    private void R33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R33ActionPerformed
       if(FCCD3A3.getDate()==null){
            if(R33.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD3A3.setEnabled(true);
                FCCD3A3.setEnabled(true);
                DXCCD3A3.requestFocus();
                FUACCD3A3.setEditable(true); 
                fecha=FCCD3A3;
                fua = FUACCD3A3;
                cie10=DXCCD3A3;
            }
        } else {
            FCCD3A3.setEnabled(false);
        }
    }//GEN-LAST:event_R33ActionPerformed

    private void R41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R41ActionPerformed
        if(FCCD4A1.getDate()==null){
            if(R41.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD4A1.setEnabled(true);
                FCCD4A1.setEnabled(true);
                DXCCD4A1.requestFocus();
                FUACCD4A1.setEditable(true); 
                fecha=FCCD4A1;
                fua = FUACCD4A1;
                cie10=DXCCD4A1;
            }
        } else {
            FCCD4A1.setEnabled(false);
        }
    }//GEN-LAST:event_R41ActionPerformed

    private void R42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R42ActionPerformed
        if(FCCD4A2.getDate()==null){
            if(R42.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD4A2.setEnabled(true);
                FCCD4A2.setEnabled(true);
                DXCCD4A2.requestFocus();
                FUACCD4A2.setEditable(true); 
                fecha=FCCD4A2;
                fua = FUACCD4A2;
                cie10=DXCCD4A2;
            }
        } else {
            FCCD4A2.setEnabled(false);
        }
    }//GEN-LAST:event_R42ActionPerformed

    private void R43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R43ActionPerformed
        if(FCCD4A3.getDate()==null){
            if(R43.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD4A3.setEnabled(true);
                FCCD4A3.setEnabled(true);
                DXCCD4A3.requestFocus();
                FUACCD4A3.setEditable(true); 
                fecha=FCCD4A3;
                fua = FUACCD4A3;
                cie10=DXCCD4A3;
            }
        } else {
            FCCD4A3.setEnabled(false);
        }
    }//GEN-LAST:event_R43ActionPerformed

    private void R5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R5ActionPerformed
        if(FCCD5A.getDate()==null){
            if(R5.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD5A.setEnabled(true);
                FCCD5A.setEnabled(true);
                DXCCD5A.requestFocus();
                FUACCD5A.setEditable(true); 
                fecha=FCCD5A;
                fua = FUACCD5A;
                cie10=DXCCD5A;
            }
        } else {
            FCCD5A.setEnabled(false);
        }
    }//GEN-LAST:event_R5ActionPerformed

    private void R6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R6ActionPerformed
         if(FCCD6A.getDate()==null){
            if(R6.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD6A.setEnabled(true);
                FCCD6A.setEnabled(true);
                DXCCD6A.requestFocus();
                FUACCD6A.setEditable(true); 
                fecha=FCCD6A;
                fua = FUACCD6A;
                cie10=DXCCD6A;
            }
        } else {
            FCCD6A.setEnabled(false);
        }
    }//GEN-LAST:event_R6ActionPerformed

    private void R7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R7ActionPerformed
         if(FCCD7A.getDate()==null){
            if(R7.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD7A.setEnabled(true);
                FCCD7A.setEnabled(true);
                DXCCD7A.requestFocus();
                FUACCD7A.setEditable(true); 
                fecha=FCCD7A;
                fua = FUACCD7A;
                cie10=DXCCD7A;
            }
        } else {
            FCCD7A.setEnabled(false);
        }
    }//GEN-LAST:event_R7ActionPerformed

    private void R8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R8ActionPerformed
         if(FCCD8A.getDate()==null){
            if(R8.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD8A.setEnabled(true);
                FCCD8A.setEnabled(true);
                DXCCD8A.requestFocus();
                FUACCD8A.setEditable(true); 
                fecha=FCCD8A;
                fua = FUACCD8A;
                cie10=DXCCD8A;
            }
        } else {
            FCCD8A.setEnabled(false);
        }
    }//GEN-LAST:event_R8ActionPerformed

    private void R9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R9ActionPerformed
         if(FCCD9A.getDate()==null){
            if(R9.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD9A.setEnabled(true);
                FCCD9A.setEnabled(true);
                DXCCD9A.requestFocus();
                FUACCD9A.setEditable(true); 
                fecha=FCCD9A;
                fua = FUACCD9A;
                cie10=DXCCD9A;
            }
        } else {
            FCCD9A.setEnabled(false);
        }
    }//GEN-LAST:event_R9ActionPerformed

    private void R10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R10ActionPerformed
         if(FCCD10A.getDate()==null){
            if(R10.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD10A.setEnabled(true);
                FCCD10A.setEnabled(true);
                DXCCD10A.requestFocus();
                FUACCD10A.setEditable(true); 
                fecha=FCCD10A;
                fua = FUACCD10A;
                cie10=DXCCD10A;
            }
        } else {
            FCCD10A.setEnabled(false);
        }
    }//GEN-LAST:event_R10ActionPerformed

    private void R11aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R11aActionPerformed
         if(FCCD11A.getDate()==null){
            if(R11.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                FUACCD11A.setEnabled(true);
                FCCD11A.setEnabled(true);
                DXCCD11A.requestFocus();
                FUACCD11A.setEditable(true); 
                fecha=FCCD11A;
                fua = FUACCD11A;
                cie10=DXCCD11A;
            }
        } else {
            FCCD11A.setEnabled(false);
        }
    }//GEN-LAST:event_R11aActionPerformed

    private void DXCCDRN2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXCCDRN2MouseClicked
            ConsultorioExtRsCcd adTopico = new ConsultorioExtRsCcd();
//        adTopico.cargarDatosCie10("", tbCiePresun);
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXCCDRN2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CCD;
    private javax.swing.JPanel CCD1;
    private javax.swing.JPanel CCD1A;
    private javax.swing.JPanel CCD2;
    private javax.swing.JPanel CCDM11;
    private javax.swing.JPanel CCDM12;
    private javax.swing.JPanel CCDR10A;
    private javax.swing.JPanel CCDR11A;
    private javax.swing.JPanel CCDR2A;
    private javax.swing.JPanel CCDR3A;
    private javax.swing.JPanel CCDR3A1;
    private javax.swing.JPanel CCDR5A;
    private javax.swing.JPanel CCDR6A;
    private javax.swing.JPanel CCDR7A;
    private javax.swing.JPanel CCDR8A;
    private javax.swing.JPanel CCDR9A;
    private javax.swing.JPanel CCDRN;
    public static javax.swing.JLabel DXCCD10A;
    public static javax.swing.JLabel DXCCD11;
    public static javax.swing.JLabel DXCCD11A;
    public static javax.swing.JLabel DXCCD12;
    public static javax.swing.JLabel DXCCD13;
    public static javax.swing.JLabel DXCCD14;
    public static javax.swing.JLabel DXCCD15;
    public static javax.swing.JLabel DXCCD16;
    public static javax.swing.JLabel DXCCD21;
    public static javax.swing.JLabel DXCCD22;
    public static javax.swing.JLabel DXCCD23;
    public static javax.swing.JLabel DXCCD24;
    public static javax.swing.JLabel DXCCD3A1;
    public static javax.swing.JLabel DXCCD3A2;
    public static javax.swing.JLabel DXCCD3A3;
    public static javax.swing.JLabel DXCCD3A4;
    public static javax.swing.JLabel DXCCD4A1;
    public static javax.swing.JLabel DXCCD4A2;
    public static javax.swing.JLabel DXCCD4A3;
    public static javax.swing.JLabel DXCCD4A4;
    public static javax.swing.JLabel DXCCD5A;
    public static javax.swing.JLabel DXCCD6A;
    public static javax.swing.JLabel DXCCD7A;
    public static javax.swing.JLabel DXCCD8A;
    public static javax.swing.JLabel DXCCD9A;
    public static javax.swing.JLabel DXCCDM1;
    public static javax.swing.JLabel DXCCDM10;
    public static javax.swing.JLabel DXCCDM11;
    public static javax.swing.JLabel DXCCDM2;
    public static javax.swing.JLabel DXCCDM3;
    public static javax.swing.JLabel DXCCDM4;
    public static javax.swing.JLabel DXCCDM5;
    public static javax.swing.JLabel DXCCDM6;
    public static javax.swing.JLabel DXCCDM7;
    public static javax.swing.JLabel DXCCDM8;
    public static javax.swing.JLabel DXCCDM9;
    public static javax.swing.JLabel DXCCDRN1;
    private javax.swing.JLabel DXCCDRN10;
    public static javax.swing.JLabel DXCCDRN2;
    public static javax.swing.JLabel DXCCDRN3;
    public static javax.swing.JLabel DXCCDRN4;
    private javax.swing.JLabel DXCCDRN7;
    private javax.swing.JLabel DXCCDRN8;
    private javax.swing.JLabel DXCCDRN9;
    public static com.toedter.calendar.JDateChooser FCCD10A;
    public static com.toedter.calendar.JDateChooser FCCD11;
    public static com.toedter.calendar.JDateChooser FCCD11A;
    public static com.toedter.calendar.JDateChooser FCCD12;
    public static com.toedter.calendar.JDateChooser FCCD13;
    public static com.toedter.calendar.JDateChooser FCCD14;
    public static com.toedter.calendar.JDateChooser FCCD15;
    public static com.toedter.calendar.JDateChooser FCCD16;
    public static com.toedter.calendar.JDateChooser FCCD21;
    public static com.toedter.calendar.JDateChooser FCCD22;
    public static com.toedter.calendar.JDateChooser FCCD23;
    public static com.toedter.calendar.JDateChooser FCCD24;
    public static com.toedter.calendar.JDateChooser FCCD3A1;
    public static com.toedter.calendar.JDateChooser FCCD3A2;
    public static com.toedter.calendar.JDateChooser FCCD3A3;
    public static com.toedter.calendar.JDateChooser FCCD3A4;
    public static com.toedter.calendar.JDateChooser FCCD4A1;
    public static com.toedter.calendar.JDateChooser FCCD4A2;
    public static com.toedter.calendar.JDateChooser FCCD4A3;
    public static com.toedter.calendar.JDateChooser FCCD4A4;
    public static com.toedter.calendar.JDateChooser FCCD5A;
    public static com.toedter.calendar.JDateChooser FCCD6A;
    public static com.toedter.calendar.JDateChooser FCCD7A;
    public static com.toedter.calendar.JDateChooser FCCD8A;
    public static com.toedter.calendar.JDateChooser FCCD9A;
    public static com.toedter.calendar.JDateChooser FCCDM1;
    public static com.toedter.calendar.JDateChooser FCCDM10;
    public static com.toedter.calendar.JDateChooser FCCDM11;
    public static com.toedter.calendar.JDateChooser FCCDM2;
    public static com.toedter.calendar.JDateChooser FCCDM3;
    public static com.toedter.calendar.JDateChooser FCCDM4;
    public static com.toedter.calendar.JDateChooser FCCDM5;
    public static com.toedter.calendar.JDateChooser FCCDM6;
    public static com.toedter.calendar.JDateChooser FCCDM7;
    public static com.toedter.calendar.JDateChooser FCCDM8;
    public static com.toedter.calendar.JDateChooser FCCDM9;
    public static com.toedter.calendar.JDateChooser FCCDRN1;
    public static com.toedter.calendar.JDateChooser FCCDRN2;
    public static com.toedter.calendar.JDateChooser FCCDRN3;
    public static com.toedter.calendar.JDateChooser FCCDRN4;
    public static javax.swing.JTextField FUACCD10A;
    public static javax.swing.JTextField FUACCD11;
    public static javax.swing.JTextField FUACCD11A;
    public static javax.swing.JTextField FUACCD12;
    public static javax.swing.JTextField FUACCD13;
    public static javax.swing.JTextField FUACCD14;
    public static javax.swing.JTextField FUACCD15;
    public static javax.swing.JTextField FUACCD16;
    public static javax.swing.JTextField FUACCD21;
    public static javax.swing.JTextField FUACCD22;
    public static javax.swing.JTextField FUACCD23;
    public static javax.swing.JTextField FUACCD24;
    public static javax.swing.JTextField FUACCD3A1;
    public static javax.swing.JTextField FUACCD3A2;
    public static javax.swing.JTextField FUACCD3A3;
    public static javax.swing.JTextField FUACCD3A4;
    public static javax.swing.JTextField FUACCD4A1;
    public static javax.swing.JTextField FUACCD4A2;
    public static javax.swing.JTextField FUACCD4A3;
    public static javax.swing.JTextField FUACCD4A4;
    public static javax.swing.JTextField FUACCD5A;
    public static javax.swing.JTextField FUACCD6A;
    public static javax.swing.JTextField FUACCD7A;
    public static javax.swing.JTextField FUACCD8A;
    public static javax.swing.JTextField FUACCD9A;
    public static javax.swing.JTextField FUACCDM1;
    public static javax.swing.JTextField FUACCDM10;
    public static javax.swing.JTextField FUACCDM11;
    public static javax.swing.JTextField FUACCDM2;
    public static javax.swing.JTextField FUACCDM3;
    public static javax.swing.JTextField FUACCDM4;
    public static javax.swing.JTextField FUACCDM5;
    public static javax.swing.JTextField FUACCDM6;
    public static javax.swing.JTextField FUACCDM7;
    public static javax.swing.JTextField FUACCDM8;
    public static javax.swing.JTextField FUACCDM9;
    public static javax.swing.JTextField FUACCDRN1;
    public static javax.swing.JTextField FUACCDRN2;
    public static javax.swing.JTextField FUACCDRN3;
    public static javax.swing.JTextField FUACCDRN4;
    private javax.swing.JDialog FrmCie10;
    private javax.swing.JPanel LEYENDA;
    private javax.swing.JRadioButton R10;
    private javax.swing.JRadioButton R11;
    private javax.swing.JRadioButton R11a;
    private javax.swing.JRadioButton R12;
    private javax.swing.JRadioButton R13;
    private javax.swing.JRadioButton R14;
    private javax.swing.JRadioButton R15;
    private javax.swing.JRadioButton R16;
    private javax.swing.JRadioButton R21;
    private javax.swing.JRadioButton R22;
    private javax.swing.JRadioButton R23;
    private javax.swing.JRadioButton R24;
    private javax.swing.JRadioButton R31;
    private javax.swing.JRadioButton R32;
    private javax.swing.JRadioButton R33;
    private javax.swing.JRadioButton R34;
    private javax.swing.JRadioButton R41;
    private javax.swing.JRadioButton R42;
    private javax.swing.JRadioButton R43;
    private javax.swing.JRadioButton R44;
    private javax.swing.JRadioButton R5;
    private javax.swing.JRadioButton R6;
    private javax.swing.JRadioButton R7;
    private javax.swing.JRadioButton R8;
    private javax.swing.JRadioButton R9;
    private javax.swing.JRadioButton Rm11;
    private javax.swing.JRadioButton Rm110;
    private javax.swing.JRadioButton Rm111;
    private javax.swing.JRadioButton Rm12;
    private javax.swing.JRadioButton Rm13;
    private javax.swing.JRadioButton Rm14;
    private javax.swing.JRadioButton Rm15;
    private javax.swing.JRadioButton Rm16;
    private javax.swing.JRadioButton Rm17;
    private javax.swing.JRadioButton Rm18;
    private javax.swing.JRadioButton Rm19;
    private javax.swing.JRadioButton Rrn1;
    private javax.swing.JRadioButton Rrn2;
    private javax.swing.JRadioButton Rrn3;
    private javax.swing.JRadioButton Rrn4;
    private javax.swing.JLabel T7;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnCaccnelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btneditar4;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel100;
    private javax.swing.JPanel jPanel101;
    private javax.swing.JPanel jPanel102;
    private javax.swing.JPanel jPanel103;
    private javax.swing.JPanel jPanel104;
    private javax.swing.JPanel jPanel105;
    private javax.swing.JPanel jPanel106;
    private javax.swing.JPanel jPanel107;
    private javax.swing.JPanel jPanel108;
    private javax.swing.JPanel jPanel109;
    private javax.swing.JPanel jPanel110;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel112;
    private javax.swing.JPanel jPanel113;
    private javax.swing.JPanel jPanel114;
    private javax.swing.JPanel jPanel115;
    private javax.swing.JPanel jPanel116;
    private javax.swing.JPanel jPanel117;
    private javax.swing.JPanel jPanel118;
    private javax.swing.JPanel jPanel119;
    private javax.swing.JPanel jPanel120;
    private javax.swing.JPanel jPanel121;
    private javax.swing.JPanel jPanel122;
    private javax.swing.JPanel jPanel123;
    private javax.swing.JPanel jPanel124;
    private javax.swing.JPanel jPanel125;
    private javax.swing.JPanel jPanel126;
    private javax.swing.JPanel jPanel127;
    private javax.swing.JPanel jPanel128;
    private javax.swing.JPanel jPanel169;
    private javax.swing.JPanel jPanel170;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel86;
    private javax.swing.JPanel jPanel87;
    private javax.swing.JPanel jPanel88;
    private javax.swing.JPanel jPanel89;
    private javax.swing.JPanel jPanel90;
    private javax.swing.JPanel jPanel91;
    private javax.swing.JPanel jPanel92;
    private javax.swing.JPanel jPanel93;
    private javax.swing.JPanel jPanel94;
    private javax.swing.JPanel jPanel95;
    private javax.swing.JPanel jPanel96;
    private javax.swing.JPanel jPanel97;
    private javax.swing.JPanel jPanel98;
    private javax.swing.JPanel jPanel99;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JLabel lblId;
    public static javax.swing.JLabel lblNina;
    public static javax.swing.JLabel lblNino;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    private javax.swing.JPanel mensaje1;
    private javax.swing.JTable tbCiePresun;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscarCie10;
    // End of variables declaration//GEN-END:variables
}
