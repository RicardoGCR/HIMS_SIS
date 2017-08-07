/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import modelos.ConsultorioEx.ConsultorioExtRsVisitasDomiciliarias;


/**
 *
 * @author MYS1
 */
public class RSAIVD extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
private Dimension DimensionBarra = null; 
byte tg;
byte tge;
JDateChooser fecha;
JTextField fua;
JLabel cie10;
ConsultorioExtRsVisitasDomiciliarias VD01 = new ConsultorioExtRsVisitasDomiciliarias();
    /**
     * Creates new form RSAITAPTG
     */
    public RSAIVD() {
        initComponents();
        QuitarLaBarraTitulo();
        habilitarDatos(false);
     
        mensaje.setVisible(false);
    }
public void QuitarLaBarraTitulo()
    { 
    Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
    DimensionBarra = Barra.getPreferredSize(); 
    Barra.setSize(0,0); 
    Barra.setPreferredSize(new Dimension(0,0)); 
    repaint(); 
    }

    public void habilitarRadio(boolean opcion){

          RDD39.setEnabled(opcion);  
          RDD37.setEnabled(opcion);  
          RDD12.setEnabled(opcion);  
          RDD14.setEnabled(opcion);  
          RDD16.setEnabled(opcion); 
          
          RDD40.setEnabled(opcion);  
          RDD38.setEnabled(opcion);  
          RDD13.setEnabled(opcion);  
          RDD15.setEnabled(opcion);  
          RDD17.setEnabled(opcion); 
          
          RDD22.setEnabled(opcion);  
          RDD24.setEnabled(opcion);  
          RDD26.setEnabled(opcion);  
          RDD45.setEnabled(opcion);  
          RDD41.setEnabled(opcion); 
          
          RDD23.setEnabled(opcion);  
          RDD25.setEnabled(opcion);  
          RDD27.setEnabled(opcion);  
          RDD46.setEnabled(opcion);  
          RDD42.setEnabled(opcion); 
          
          RDD31.setEnabled(opcion);  
          RDD33.setEnabled(opcion);  
          RDD35.setEnabled(opcion);  
          RDD47.setEnabled(opcion);  
          RDD43.setEnabled(opcion); 
          
          RDD32.setEnabled(opcion);  
          RDD34.setEnabled(opcion);  
          RDD36.setEnabled(opcion);  
          RDD48.setEnabled(opcion);  
          RDD44.setEnabled(opcion); 
    }
    
    public void habilitarDatos(boolean opcion){

        DVF1.setEnabled(opcion);
        DVF12.setEnabled(opcion);
        DVF13.setEnabled(opcion);
        DVF14.setEnabled(opcion);
        DVF15.setEnabled(opcion);
        
        DVF2.setEnabled(opcion);
        DVF22.setEnabled(opcion);
        DVF23.setEnabled(opcion);
        DVF24.setEnabled(opcion);
        DVF25.setEnabled(opcion);
        
        DVF3.setEnabled(opcion);
        DVF32.setEnabled(opcion);
        DVF33.setEnabled(opcion);
        DVF34.setEnabled(opcion);
        DVF35.setEnabled(opcion);
        
        DVF4.setEnabled(opcion);
        DVF42.setEnabled(opcion);
        DVF43.setEnabled(opcion);
        DVF44.setEnabled(opcion);
        DVF45.setEnabled(opcion);
        
        DVF5.setEnabled(opcion);
        DVF52.setEnabled(opcion);
        DVF53.setEnabled(opcion);
        DVF54.setEnabled(opcion);
        DVF55.setEnabled(opcion);
        
        DVF6.setEnabled(opcion);
        DVF62.setEnabled(opcion);
        DVF63.setEnabled(opcion);
        DVF64.setEnabled(opcion);
        DVF65.setEnabled(opcion);
        

    }
    public void Botones(boolean opcion){
        btnGuardar.setEnabled(opcion);
        btnEditar.setEnabled(opcion);
        btnCancelar.setEnabled(opcion);
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
                           mensaje.setVisible(true);
                           mensaje.setBackground(new Color(255,91,70)); 
                           men.setText("Ingrese una fecha correcta");
                           b.setVisible(false);
                           b1.setVisible(false); 
         }
        
        return fecha;
    }
    public void validaRegistro(int rs_id){
        try {
            PreparedStatement cmd = VD01.getCn().prepareStatement("SELECT RS_ID FROM CONSULTORIO_EXT_RS_VISITAS_DOMICILIARIAS WHERE RS_ID ='"+rs_id+"'");
            ResultSet res = cmd.executeQuery();
            if(res.next()){ // si existe
                Modificar(fecha);
            }else { // no existe
                Guardar(fecha);
            }
        } catch (Exception e) {
            mensaje.setVisible(true);
            mensaje.setBackground(new Color(255,91,70)); 
            men.setText("Ocurrió un error, Verifique");
            b.setVisible(false);
            b1.setVisible(false);
        }
    }
    
    public void Modificar(JDateChooser fecha){
    if(fecha.getDate()==null){
        fecha.setEnabled(true);
        //fua.setEnabled(true);
        mensaje.setVisible(true);
        mensaje.setBackground(new Color(255,91,70)); 
        men.setText("Ingrese una fecha valida");
        b.setVisible(false);
        b1.setVisible(false);
    } else {
        ConsultorioExtRsVisitasDomiciliarias CXRsVD = new ConsultorioExtRsVisitasDomiciliarias();
        ConsultorioExtRsVisitasDomiciliarias CXRsVD2 = new ConsultorioExtRsVisitasDomiciliarias();
        try {
    
            CXRsVD.setRsId(Integer.parseInt(lblId.getText()));
            ///////////////////////////////////////////////////////////////
            //1
            
            if(DVF1.getDate()!=null){
                CXRsVD.setVd1Fecha(determinarFecha(DVF1));  
            }
            
            if(DVF12.getDate()!=null){
                CXRsVD.setVd2Fecha(determinarFecha(DVF12));  
            }
            
            if(DVF13.getDate()!=null){
                CXRsVD.setVd3Fecha(determinarFecha(DVF13));  
            }
            
            if(DVF14.getDate()!=null){
                CXRsVD.setVd4Fecha(determinarFecha(DVF14));  
            }
            
            if(DVF15.getDate()!=null){
                CXRsVD.setVd5Fecha(determinarFecha(DVF15));  
            }
            
            ///////////////////////////////////////////////////////////////
            //2
            
            if(DVF2.getDate()!=null){
                CXRsVD.setVd6Fecha(determinarFecha(DVF2));  
            }
            
            if(DVF22.getDate()!=null){
                CXRsVD.setVd7Fecha(determinarFecha(DVF22));  
            }
            
            if(DVF23.getDate()!=null){
                CXRsVD.setVd8Fecha(determinarFecha(DVF23));  
            }
            
            if(DVF24.getDate()!=null){
                CXRsVD.setVd9Fecha(determinarFecha(DVF24));  
            }
            
            if(DVF25.getDate()!=null){
                CXRsVD.setVd10Fecha(determinarFecha(DVF25));  
            }
            
            ///////////////////////////////////////////////////////////////
            //3
            
            if(DVF3.getDate()!=null){
                CXRsVD.setVd11Fecha(determinarFecha(DVF3));  
            }
            
            if(DVF32.getDate()!=null){
                CXRsVD.setVd12Fecha(determinarFecha(DVF32));  
            }
            
            if(DVF33.getDate()!=null){
                CXRsVD.setVd13Fecha(determinarFecha(DVF33));  
            }
            
            if(DVF34.getDate()!=null){
                CXRsVD.setVd14Fecha(determinarFecha(DVF34));  
            }
            
            if(DVF35.getDate()!=null){
                CXRsVD.setVd15Fecha(determinarFecha(DVF35));  
            }
            
            ///////////////////////////////////////////////////////////////
            //4
            
            if(DVF4.getDate()!=null){
                CXRsVD.setVd16Fecha(determinarFecha(DVF4));  
            }
            
            if(DVF42.getDate()!=null){
                CXRsVD.setVd17Fecha(determinarFecha(DVF42));  
            }
            
            if(DVF43.getDate()!=null){
                CXRsVD.setVd18Fecha(determinarFecha(DVF43));  
            }
            
            if(DVF44.getDate()!=null){
                CXRsVD.setVd19Fecha(determinarFecha(DVF44));  
            }
            
            if(DVF45.getDate()!=null){
                CXRsVD.setVd20Fecha(determinarFecha(DVF45));  
            }
            
            ///////////////////////////////////////////////////////////////
            //5
            
            if(DVF5.getDate()!=null){
                CXRsVD.setVd21Fecha(determinarFecha(DVF5));  
            }
            
            if(DVF52.getDate()!=null){
                CXRsVD.setVd22Fecha(determinarFecha(DVF52));  
            }
            
            if(DVF53.getDate()!=null){
                CXRsVD.setVd23Fecha(determinarFecha(DVF53));  
            }
            
            if(DVF54.getDate()!=null){
                CXRsVD.setVd24Fecha(determinarFecha(DVF54));  
            }
            
            if(DVF55.getDate()!=null){
                CXRsVD.setVd25Fecha(determinarFecha(DVF55));  
            }
            
            ///////////////////////////////////////////////////////////////
            //6
            
            if(DVF6.getDate()!=null){
                CXRsVD.setVd26Fecha(determinarFecha(DVF2));  
            }
            
            if(DVF62.getDate()!=null){
                CXRsVD.setVd27Fecha(determinarFecha(DVF62));  
            }
            
            if(DVF63.getDate()!=null){
                CXRsVD.setVd28Fecha(determinarFecha(DVF63));  
            }
            
            if(DVF64.getDate()!=null){
                CXRsVD.setVd29Fecha(determinarFecha(DVF64));  
            }
            
            if(DVF65.getDate()!=null){
                CXRsVD.setVd30Fecha(determinarFecha(DVF65));  
            }
            
            if(CXRsVD.mantenimientoRSAIVD("U")==true){
                mensaje.setVisible(true);
                mensaje.setBackground(new Color(33,115,70)); 
                men.setText("Datos Actualizados de forma correcta");
                b.setText("OK");
                b.setVisible(true);
                b1.setVisible(false);
                btnGuardar.setEnabled(false);
                tge=1;
                CXRsVD2.ConsultoriosExtVDListar(Integer.parseInt(lblId.getText()));
                CXRsVD2.porcentajeVD(Integer.parseInt(lblId.getText()));
                habilitarDatos(false);
                habilitarRadio(true);
            }else {
                mensaje.setVisible(true);
                mensaje.setBackground(new Color(255,91,70)); 
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
    
    public void Guardar(JDateChooser fecha){
        if(fecha.getDate()==null){
            mensaje.setVisible(true);
            mensaje.setBackground(new Color(255,91,70)); 
            men.setText("Ingrese una fecha valida");
            b.setVisible(false);
            b1.setVisible(false);
        } else {
        ConsultorioExtRsVisitasDomiciliarias CXRsVD = new ConsultorioExtRsVisitasDomiciliarias();
        ConsultorioExtRsVisitasDomiciliarias CXRsVD2 = new ConsultorioExtRsVisitasDomiciliarias();
        try {
    
            CXRsVD.setRsId(Integer.parseInt(lblId.getText()));
            ///////////////////////////////////////////////////////////////
            //1
            
            if(DVF1.getDate()!=null){
                CXRsVD.setVd1Fecha(determinarFecha(DVF1));  
            }
            
            if(DVF12.getDate()!=null){
                CXRsVD.setVd2Fecha(determinarFecha(DVF12));  
            }
            
            if(DVF13.getDate()!=null){
                CXRsVD.setVd3Fecha(determinarFecha(DVF13));  
            }
            
            if(DVF14.getDate()!=null){
                CXRsVD.setVd4Fecha(determinarFecha(DVF14));  
            }
            
            if(DVF15.getDate()!=null){
                CXRsVD.setVd5Fecha(determinarFecha(DVF15));  
            }
            
            ///////////////////////////////////////////////////////////////
            //2
            
            if(DVF2.getDate()!=null){
                CXRsVD.setVd6Fecha(determinarFecha(DVF2));  
            }
            
            if(DVF22.getDate()!=null){
                CXRsVD.setVd7Fecha(determinarFecha(DVF22));  
            }
            
            if(DVF23.getDate()!=null){
                CXRsVD.setVd8Fecha(determinarFecha(DVF23));  
            }
            
            if(DVF24.getDate()!=null){
                CXRsVD.setVd9Fecha(determinarFecha(DVF24));  
            }
            
            if(DVF25.getDate()!=null){
                CXRsVD.setVd10Fecha(determinarFecha(DVF25));  
            }
            
            ///////////////////////////////////////////////////////////////
            //3
            
            if(DVF3.getDate()!=null){
                CXRsVD.setVd11Fecha(determinarFecha(DVF3));  
            }
            
            if(DVF32.getDate()!=null){
                CXRsVD.setVd12Fecha(determinarFecha(DVF32));  
            }
            
            if(DVF33.getDate()!=null){
                CXRsVD.setVd13Fecha(determinarFecha(DVF33));  
            }
            
            if(DVF34.getDate()!=null){
                CXRsVD.setVd14Fecha(determinarFecha(DVF34));  
            }
            
            if(DVF35.getDate()!=null){
                CXRsVD.setVd15Fecha(determinarFecha(DVF35));  
            }
            
            ///////////////////////////////////////////////////////////////
            //4
            
            if(DVF4.getDate()!=null){
                CXRsVD.setVd16Fecha(determinarFecha(DVF4));  
            }
            
            if(DVF42.getDate()!=null){
                CXRsVD.setVd17Fecha(determinarFecha(DVF42));  
            }
            
            if(DVF43.getDate()!=null){
                CXRsVD.setVd18Fecha(determinarFecha(DVF43));  
            }
            
            if(DVF44.getDate()!=null){
                CXRsVD.setVd19Fecha(determinarFecha(DVF44));  
            }
            
            if(DVF45.getDate()!=null){
                CXRsVD.setVd20Fecha(determinarFecha(DVF45));  
            }
            
            ///////////////////////////////////////////////////////////////
            //5
            
            if(DVF5.getDate()!=null){
                CXRsVD.setVd21Fecha(determinarFecha(DVF5));  
            }
            
            if(DVF52.getDate()!=null){
                CXRsVD.setVd22Fecha(determinarFecha(DVF52));  
            }
            
            if(DVF53.getDate()!=null){
                CXRsVD.setVd23Fecha(determinarFecha(DVF53));  
            }
            
            if(DVF54.getDate()!=null){
                CXRsVD.setVd24Fecha(determinarFecha(DVF54));  
            }
            
            if(DVF55.getDate()!=null){
                CXRsVD.setVd25Fecha(determinarFecha(DVF55));  
            }
            
            ///////////////////////////////////////////////////////////////
            //6
            
            if(DVF6.getDate()!=null){
                CXRsVD.setVd26Fecha(determinarFecha(DVF2));  
            }
            
            if(DVF62.getDate()!=null){
                CXRsVD.setVd27Fecha(determinarFecha(DVF62));  
            }
            
            if(DVF63.getDate()!=null){
                CXRsVD.setVd28Fecha(determinarFecha(DVF63));  
            }
            
            if(DVF64.getDate()!=null){
                CXRsVD.setVd29Fecha(determinarFecha(DVF64));  
            }
            
            if(DVF65.getDate()!=null){
                CXRsVD.setVd30Fecha(determinarFecha(DVF65));  
            }    
        if(CXRsVD.mantenimientoRSAIVD("I")==true){
                mensaje.setVisible(true);
                mensaje.setBackground(new Color(33,115,70)); 
                men.setText("Datos Guardados de forma correcta");
                CXRsVD2.porcentajeVD(Integer.parseInt(lblId.getText()));
                b.setText("OK");
                b.setVisible(true);
                b1.setVisible(false);

                btnGuardar.setEnabled(false);
                tge=1;

                CXRsVD2.ConsultoriosExtVDListar(Integer.parseInt(lblId.getText()));

                habilitarDatos(false);
                habilitarRadio(true);
            }else {

                    mensaje.setVisible(true);
                    mensaje.setBackground(new Color(255,91,70)); 
                    men.setText("Ocurrio un error, Verifique");
                    b.setVisible(false);
                    b1.setVisible(false);
                    tge=7;
            }  
         } catch (Exception e) {
            System.out.println("Error: guardar " + e.getMessage());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        CCD = new javax.swing.JPanel();
        CCDM16 = new javax.swing.JPanel();
        jPanel104 = new javax.swing.JPanel();
        DVF13 = new com.toedter.calendar.JDateChooser();
        RDD12 = new javax.swing.JRadioButton();
        jPanel105 = new javax.swing.JPanel();
        DVF23 = new com.toedter.calendar.JDateChooser();
        RDD13 = new javax.swing.JRadioButton();
        lblId = new javax.swing.JLabel();
        Opciones = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        mensaje = new javax.swing.JPanel();
        men = new javax.swing.JLabel();
        b = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        CCDM17 = new javax.swing.JPanel();
        jPanel106 = new javax.swing.JPanel();
        DVF14 = new com.toedter.calendar.JDateChooser();
        RDD14 = new javax.swing.JRadioButton();
        jPanel107 = new javax.swing.JPanel();
        DVF24 = new com.toedter.calendar.JDateChooser();
        RDD15 = new javax.swing.JRadioButton();
        CCDM18 = new javax.swing.JPanel();
        jPanel108 = new javax.swing.JPanel();
        DVF15 = new com.toedter.calendar.JDateChooser();
        RDD16 = new javax.swing.JRadioButton();
        jPanel109 = new javax.swing.JPanel();
        DVF25 = new com.toedter.calendar.JDateChooser();
        RDD17 = new javax.swing.JRadioButton();
        CCDM21 = new javax.swing.JPanel();
        jPanel114 = new javax.swing.JPanel();
        DVF3 = new com.toedter.calendar.JDateChooser();
        RDD22 = new javax.swing.JRadioButton();
        jPanel115 = new javax.swing.JPanel();
        DVF4 = new com.toedter.calendar.JDateChooser();
        RDD23 = new javax.swing.JRadioButton();
        CCDM27 = new javax.swing.JPanel();
        jPanel123 = new javax.swing.JPanel();
        DVF5 = new com.toedter.calendar.JDateChooser();
        RDD31 = new javax.swing.JRadioButton();
        jPanel124 = new javax.swing.JPanel();
        DVF6 = new com.toedter.calendar.JDateChooser();
        RDD32 = new javax.swing.JRadioButton();
        CCDM22 = new javax.swing.JPanel();
        jPanel116 = new javax.swing.JPanel();
        DVF32 = new com.toedter.calendar.JDateChooser();
        RDD24 = new javax.swing.JRadioButton();
        jPanel117 = new javax.swing.JPanel();
        DVF42 = new com.toedter.calendar.JDateChooser();
        RDD25 = new javax.swing.JRadioButton();
        CCDM28 = new javax.swing.JPanel();
        jPanel125 = new javax.swing.JPanel();
        DVF52 = new com.toedter.calendar.JDateChooser();
        RDD33 = new javax.swing.JRadioButton();
        jPanel126 = new javax.swing.JPanel();
        DVF62 = new com.toedter.calendar.JDateChooser();
        RDD34 = new javax.swing.JRadioButton();
        CCDM23 = new javax.swing.JPanel();
        jPanel118 = new javax.swing.JPanel();
        DVF33 = new com.toedter.calendar.JDateChooser();
        RDD26 = new javax.swing.JRadioButton();
        jPanel119 = new javax.swing.JPanel();
        DVF43 = new com.toedter.calendar.JDateChooser();
        RDD27 = new javax.swing.JRadioButton();
        CCDM29 = new javax.swing.JPanel();
        jPanel127 = new javax.swing.JPanel();
        DVF53 = new com.toedter.calendar.JDateChooser();
        RDD35 = new javax.swing.JRadioButton();
        jPanel128 = new javax.swing.JPanel();
        DVF63 = new com.toedter.calendar.JDateChooser();
        RDD36 = new javax.swing.JRadioButton();
        CCDM34 = new javax.swing.JPanel();
        jPanel137 = new javax.swing.JPanel();
        DVF34 = new com.toedter.calendar.JDateChooser();
        RDD45 = new javax.swing.JRadioButton();
        jPanel138 = new javax.swing.JPanel();
        DVF44 = new com.toedter.calendar.JDateChooser();
        RDD46 = new javax.swing.JRadioButton();
        CCDM35 = new javax.swing.JPanel();
        jPanel139 = new javax.swing.JPanel();
        DVF54 = new com.toedter.calendar.JDateChooser();
        RDD47 = new javax.swing.JRadioButton();
        jPanel140 = new javax.swing.JPanel();
        DVF64 = new com.toedter.calendar.JDateChooser();
        RDD48 = new javax.swing.JRadioButton();
        CCDM32 = new javax.swing.JPanel();
        jPanel133 = new javax.swing.JPanel();
        DVF35 = new com.toedter.calendar.JDateChooser();
        RDD41 = new javax.swing.JRadioButton();
        jPanel134 = new javax.swing.JPanel();
        DVF45 = new com.toedter.calendar.JDateChooser();
        RDD42 = new javax.swing.JRadioButton();
        CCDM33 = new javax.swing.JPanel();
        jPanel135 = new javax.swing.JPanel();
        DVF55 = new com.toedter.calendar.JDateChooser();
        RDD43 = new javax.swing.JRadioButton();
        jPanel136 = new javax.swing.JPanel();
        DVF65 = new com.toedter.calendar.JDateChooser();
        RDD44 = new javax.swing.JRadioButton();
        CCDM30 = new javax.swing.JPanel();
        jPanel129 = new javax.swing.JPanel();
        DVF12 = new com.toedter.calendar.JDateChooser();
        RDD37 = new javax.swing.JRadioButton();
        jPanel130 = new javax.swing.JPanel();
        DVF22 = new com.toedter.calendar.JDateChooser();
        RDD38 = new javax.swing.JRadioButton();
        CCDM31 = new javax.swing.JPanel();
        jPanel131 = new javax.swing.JPanel();
        DVF1 = new com.toedter.calendar.JDateChooser();
        RDD39 = new javax.swing.JRadioButton();
        jPanel132 = new javax.swing.JPanel();
        DVF2 = new com.toedter.calendar.JDateChooser();
        RDD40 = new javax.swing.JRadioButton();
        jPanel26 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lblPorcentajeVisitas = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblNina = new javax.swing.JLabel();
        lblNino = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        CCD.setBackground(new java.awt.Color(255, 255, 255));

        CCDM16.setBackground(new java.awt.Color(255, 255, 255));

        jPanel104.setBackground(new java.awt.Color(153, 153, 153));

        DVF13.setBackground(new java.awt.Color(255, 255, 255));
        DVF13.setDateFormatString("dd/MM/yyyy");
        DVF13.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD12.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD12);
        RDD12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD12.setForeground(new java.awt.Color(255, 255, 255));
        RDD12.setText("1º");
        RDD12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel104Layout = new javax.swing.GroupLayout(jPanel104);
        jPanel104.setLayout(jPanel104Layout);
        jPanel104Layout.setHorizontalGroup(
            jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DVF13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addGroup(jPanel104Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel104Layout.setVerticalGroup(
            jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel104Layout.createSequentialGroup()
                .addComponent(RDD12)
                .addGap(3, 3, 3)
                .addComponent(DVF13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel105.setBackground(new java.awt.Color(153, 153, 153));

        DVF23.setBackground(new java.awt.Color(255, 255, 255));
        DVF23.setDateFormatString("dd/MM/yyyy");
        DVF23.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD13.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD13);
        RDD13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD13.setForeground(new java.awt.Color(255, 255, 255));
        RDD13.setText("2º");
        RDD13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel105Layout = new javax.swing.GroupLayout(jPanel105);
        jPanel105.setLayout(jPanel105Layout);
        jPanel105Layout.setHorizontalGroup(
            jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel105Layout.createSequentialGroup()
                .addComponent(DVF23, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel105Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel105Layout.setVerticalGroup(
            jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel105Layout.createSequentialGroup()
                .addComponent(RDD13)
                .addGap(3, 3, 3)
                .addComponent(DVF23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout CCDM16Layout = new javax.swing.GroupLayout(CCDM16);
        CCDM16.setLayout(CCDM16Layout);
        CCDM16Layout.setHorizontalGroup(
            CCDM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM16Layout.createSequentialGroup()
                .addComponent(jPanel104, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel105, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        CCDM16Layout.setVerticalGroup(
            CCDM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM16Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(CCDM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel105, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel104, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        lblId.setText("jLabel1");

        Opciones.setBackground(new java.awt.Color(102, 102, 102));

        jPanel30.setBackground(new java.awt.Color(51, 51, 51));

        btnEditar.setForeground(new java.awt.Color(240, 240, 240));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
        btnEditar.setMnemonic('N');
        btnEditar.setContentAreaFilled(false);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditar.setIconTextGap(30);
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
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setIconTextGap(30);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setForeground(new java.awt.Color(240, 240, 240));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Deshacer-30.png"))); // NOI18N
        btnCancelar.setMnemonic('N');
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setIconTextGap(30);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel30Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(69, Short.MAX_VALUE)))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel30Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        mensaje.setBackground(new java.awt.Color(33, 115, 70));

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

        javax.swing.GroupLayout mensajeLayout = new javax.swing.GroupLayout(mensaje);
        mensaje.setLayout(mensajeLayout);
        mensajeLayout.setHorizontalGroup(
            mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mensajeLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(men)
                .addGap(46, 46, 46)
                .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mensajeLayout.setVerticalGroup(
            mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mensajeLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(men)
                    .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout OpcionesLayout = new javax.swing.GroupLayout(Opciones);
        Opciones.setLayout(OpcionesLayout);
        OpcionesLayout.setHorizontalGroup(
            OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OpcionesLayout.createSequentialGroup()
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        OpcionesLayout.setVerticalGroup(
            OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(OpcionesLayout.createSequentialGroup()
                .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        CCDM17.setBackground(new java.awt.Color(255, 255, 255));

        jPanel106.setBackground(new java.awt.Color(204, 204, 204));

        DVF14.setBackground(new java.awt.Color(255, 255, 255));
        DVF14.setDateFormatString("dd/MM/yyyy");
        DVF14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD14.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(RDD14);
        RDD14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD14.setForeground(new java.awt.Color(102, 102, 102));
        RDD14.setText("1º");
        RDD14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel106Layout = new javax.swing.GroupLayout(jPanel106);
        jPanel106.setLayout(jPanel106Layout);
        jPanel106Layout.setHorizontalGroup(
            jPanel106Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DVF14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addGroup(jPanel106Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel106Layout.setVerticalGroup(
            jPanel106Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel106Layout.createSequentialGroup()
                .addComponent(RDD14)
                .addGap(3, 3, 3)
                .addComponent(DVF14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel107.setBackground(new java.awt.Color(204, 204, 204));

        DVF24.setBackground(new java.awt.Color(255, 255, 255));
        DVF24.setDateFormatString("dd/MM/yyyy");
        DVF24.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD15.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(RDD15);
        RDD15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD15.setForeground(new java.awt.Color(102, 102, 102));
        RDD15.setText("2º");
        RDD15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel107Layout = new javax.swing.GroupLayout(jPanel107);
        jPanel107.setLayout(jPanel107Layout);
        jPanel107Layout.setHorizontalGroup(
            jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel107Layout.createSequentialGroup()
                .addComponent(DVF24, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel107Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel107Layout.setVerticalGroup(
            jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel107Layout.createSequentialGroup()
                .addComponent(RDD15)
                .addGap(3, 3, 3)
                .addComponent(DVF24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout CCDM17Layout = new javax.swing.GroupLayout(CCDM17);
        CCDM17.setLayout(CCDM17Layout);
        CCDM17Layout.setHorizontalGroup(
            CCDM17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM17Layout.createSequentialGroup()
                .addComponent(jPanel106, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel107, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        CCDM17Layout.setVerticalGroup(
            CCDM17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM17Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(CCDM17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel107, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel106, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        CCDM18.setBackground(new java.awt.Color(255, 255, 255));

        jPanel108.setBackground(new java.awt.Color(153, 153, 153));

        DVF15.setBackground(new java.awt.Color(255, 255, 255));
        DVF15.setDateFormatString("dd/MM/yyyy");
        DVF15.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD16.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD16);
        RDD16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD16.setForeground(new java.awt.Color(255, 255, 255));
        RDD16.setText("1º");
        RDD16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel108Layout = new javax.swing.GroupLayout(jPanel108);
        jPanel108.setLayout(jPanel108Layout);
        jPanel108Layout.setHorizontalGroup(
            jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DVF15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addGroup(jPanel108Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel108Layout.setVerticalGroup(
            jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel108Layout.createSequentialGroup()
                .addComponent(RDD16)
                .addGap(3, 3, 3)
                .addComponent(DVF15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel109.setBackground(new java.awt.Color(153, 153, 153));

        DVF25.setBackground(new java.awt.Color(255, 255, 255));
        DVF25.setDateFormatString("dd/MM/yyyy");
        DVF25.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD17.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD17);
        RDD17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD17.setForeground(new java.awt.Color(255, 255, 255));
        RDD17.setText("2º");
        RDD17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel109Layout = new javax.swing.GroupLayout(jPanel109);
        jPanel109.setLayout(jPanel109Layout);
        jPanel109Layout.setHorizontalGroup(
            jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel109Layout.createSequentialGroup()
                .addComponent(DVF25, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel109Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel109Layout.setVerticalGroup(
            jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel109Layout.createSequentialGroup()
                .addComponent(RDD17)
                .addGap(3, 3, 3)
                .addComponent(DVF25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout CCDM18Layout = new javax.swing.GroupLayout(CCDM18);
        CCDM18.setLayout(CCDM18Layout);
        CCDM18Layout.setHorizontalGroup(
            CCDM18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM18Layout.createSequentialGroup()
                .addComponent(jPanel108, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel109, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        CCDM18Layout.setVerticalGroup(
            CCDM18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM18Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(CCDM18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel109, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        CCDM21.setBackground(new java.awt.Color(255, 255, 255));

        jPanel114.setBackground(new java.awt.Color(153, 153, 153));

        DVF3.setBackground(new java.awt.Color(255, 255, 255));
        DVF3.setDateFormatString("dd/MM/yyyy");
        DVF3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD22.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD22);
        RDD22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD22.setForeground(new java.awt.Color(255, 255, 255));
        RDD22.setText("3º");
        RDD22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel114Layout = new javax.swing.GroupLayout(jPanel114);
        jPanel114.setLayout(jPanel114Layout);
        jPanel114Layout.setHorizontalGroup(
            jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DVF3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addGroup(jPanel114Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel114Layout.setVerticalGroup(
            jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel114Layout.createSequentialGroup()
                .addComponent(RDD22)
                .addGap(3, 3, 3)
                .addComponent(DVF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel115.setBackground(new java.awt.Color(153, 153, 153));

        DVF4.setBackground(new java.awt.Color(255, 255, 255));
        DVF4.setDateFormatString("dd/MM/yyyy");
        DVF4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD23.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD23);
        RDD23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD23.setForeground(new java.awt.Color(255, 255, 255));
        RDD23.setText("4º");
        RDD23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel115Layout = new javax.swing.GroupLayout(jPanel115);
        jPanel115.setLayout(jPanel115Layout);
        jPanel115Layout.setHorizontalGroup(
            jPanel115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel115Layout.createSequentialGroup()
                .addComponent(DVF4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel115Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel115Layout.setVerticalGroup(
            jPanel115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel115Layout.createSequentialGroup()
                .addComponent(RDD23)
                .addGap(3, 3, 3)
                .addComponent(DVF4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        CCDM27.setBackground(new java.awt.Color(255, 255, 255));

        jPanel123.setBackground(new java.awt.Color(153, 153, 153));
        jPanel123.setPreferredSize(new java.awt.Dimension(120, 52));

        DVF5.setBackground(new java.awt.Color(255, 255, 255));
        DVF5.setDateFormatString("dd/MM/yyyy");
        DVF5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD31.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD31);
        RDD31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD31.setForeground(new java.awt.Color(255, 255, 255));
        RDD31.setText("5º");
        RDD31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel123Layout = new javax.swing.GroupLayout(jPanel123);
        jPanel123.setLayout(jPanel123Layout);
        jPanel123Layout.setHorizontalGroup(
            jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DVF5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel123Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel123Layout.setVerticalGroup(
            jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel123Layout.createSequentialGroup()
                .addComponent(RDD31)
                .addGap(3, 3, 3)
                .addComponent(DVF5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel124.setBackground(new java.awt.Color(153, 153, 153));

        DVF6.setBackground(new java.awt.Color(255, 255, 255));
        DVF6.setDateFormatString("dd/MM/yyyy");
        DVF6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD32.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD32);
        RDD32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD32.setForeground(new java.awt.Color(255, 255, 255));
        RDD32.setText("6º");
        RDD32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD32ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel124Layout = new javax.swing.GroupLayout(jPanel124);
        jPanel124.setLayout(jPanel124Layout);
        jPanel124Layout.setHorizontalGroup(
            jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel124Layout.createSequentialGroup()
                .addComponent(DVF6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel124Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel124Layout.setVerticalGroup(
            jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel124Layout.createSequentialGroup()
                .addComponent(RDD32)
                .addGap(3, 3, 3)
                .addComponent(DVF6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout CCDM27Layout = new javax.swing.GroupLayout(CCDM27);
        CCDM27.setLayout(CCDM27Layout);
        CCDM27Layout.setHorizontalGroup(
            CCDM27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM27Layout.createSequentialGroup()
                .addComponent(jPanel123, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel124, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        CCDM27Layout.setVerticalGroup(
            CCDM27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM27Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(CCDM27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel124, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel123, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout CCDM21Layout = new javax.swing.GroupLayout(CCDM21);
        CCDM21.setLayout(CCDM21Layout);
        CCDM21Layout.setHorizontalGroup(
            CCDM21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM21Layout.createSequentialGroup()
                .addComponent(jPanel114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel115, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CCDM27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        CCDM21Layout.setVerticalGroup(
            CCDM21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM21Layout.createSequentialGroup()
                .addGroup(CCDM21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CCDM27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CCDM21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel115, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel114, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        CCDM22.setBackground(new java.awt.Color(255, 255, 255));

        jPanel116.setBackground(new java.awt.Color(204, 204, 204));

        DVF32.setBackground(new java.awt.Color(255, 255, 255));
        DVF32.setDateFormatString("dd/MM/yyyy");
        DVF32.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD24.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(RDD24);
        RDD24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD24.setForeground(new java.awt.Color(102, 102, 102));
        RDD24.setText("3º");
        RDD24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel116Layout = new javax.swing.GroupLayout(jPanel116);
        jPanel116.setLayout(jPanel116Layout);
        jPanel116Layout.setHorizontalGroup(
            jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DVF32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addGroup(jPanel116Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel116Layout.setVerticalGroup(
            jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel116Layout.createSequentialGroup()
                .addComponent(RDD24)
                .addGap(3, 3, 3)
                .addComponent(DVF32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel117.setBackground(new java.awt.Color(204, 204, 204));

        DVF42.setBackground(new java.awt.Color(255, 255, 255));
        DVF42.setDateFormatString("dd/MM/yyyy");
        DVF42.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD25.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(RDD25);
        RDD25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD25.setForeground(new java.awt.Color(102, 102, 102));
        RDD25.setText("4º");
        RDD25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel117Layout = new javax.swing.GroupLayout(jPanel117);
        jPanel117.setLayout(jPanel117Layout);
        jPanel117Layout.setHorizontalGroup(
            jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel117Layout.createSequentialGroup()
                .addComponent(DVF42, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel117Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel117Layout.setVerticalGroup(
            jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel117Layout.createSequentialGroup()
                .addComponent(RDD25)
                .addGap(3, 3, 3)
                .addComponent(DVF42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        CCDM28.setBackground(new java.awt.Color(255, 255, 255));

        jPanel125.setBackground(new java.awt.Color(204, 204, 204));
        jPanel125.setPreferredSize(new java.awt.Dimension(120, 52));

        DVF52.setBackground(new java.awt.Color(255, 255, 255));
        DVF52.setDateFormatString("dd/MM/yyyy");
        DVF52.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD33.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(RDD33);
        RDD33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD33.setForeground(new java.awt.Color(102, 102, 102));
        RDD33.setText("5º");
        RDD33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD33ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel125Layout = new javax.swing.GroupLayout(jPanel125);
        jPanel125.setLayout(jPanel125Layout);
        jPanel125Layout.setHorizontalGroup(
            jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DVF52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel125Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel125Layout.setVerticalGroup(
            jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel125Layout.createSequentialGroup()
                .addComponent(RDD33)
                .addGap(3, 3, 3)
                .addComponent(DVF52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel126.setBackground(new java.awt.Color(204, 204, 204));

        DVF62.setBackground(new java.awt.Color(255, 255, 255));
        DVF62.setDateFormatString("dd/MM/yyyy");
        DVF62.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD34.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(RDD34);
        RDD34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD34.setForeground(new java.awt.Color(102, 102, 102));
        RDD34.setText("6º");
        RDD34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD34ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel126Layout = new javax.swing.GroupLayout(jPanel126);
        jPanel126.setLayout(jPanel126Layout);
        jPanel126Layout.setHorizontalGroup(
            jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel126Layout.createSequentialGroup()
                .addComponent(DVF62, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel126Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel126Layout.setVerticalGroup(
            jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel126Layout.createSequentialGroup()
                .addComponent(RDD34)
                .addGap(3, 3, 3)
                .addComponent(DVF62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout CCDM28Layout = new javax.swing.GroupLayout(CCDM28);
        CCDM28.setLayout(CCDM28Layout);
        CCDM28Layout.setHorizontalGroup(
            CCDM28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM28Layout.createSequentialGroup()
                .addComponent(jPanel125, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel126, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        CCDM28Layout.setVerticalGroup(
            CCDM28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM28Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(CCDM28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel126, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel125, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout CCDM22Layout = new javax.swing.GroupLayout(CCDM22);
        CCDM22.setLayout(CCDM22Layout);
        CCDM22Layout.setHorizontalGroup(
            CCDM22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM22Layout.createSequentialGroup()
                .addComponent(jPanel116, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel117, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CCDM28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        CCDM22Layout.setVerticalGroup(
            CCDM22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM22Layout.createSequentialGroup()
                .addGroup(CCDM22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CCDM28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CCDM22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel117, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel116, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        CCDM23.setBackground(new java.awt.Color(255, 255, 255));

        jPanel118.setBackground(new java.awt.Color(153, 153, 153));

        DVF33.setBackground(new java.awt.Color(255, 255, 255));
        DVF33.setDateFormatString("dd/MM/yyyy");
        DVF33.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD26.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD26);
        RDD26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD26.setForeground(new java.awt.Color(255, 255, 255));
        RDD26.setText("3º");
        RDD26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD26ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel118Layout = new javax.swing.GroupLayout(jPanel118);
        jPanel118.setLayout(jPanel118Layout);
        jPanel118Layout.setHorizontalGroup(
            jPanel118Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DVF33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addGroup(jPanel118Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel118Layout.setVerticalGroup(
            jPanel118Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel118Layout.createSequentialGroup()
                .addComponent(RDD26)
                .addGap(3, 3, 3)
                .addComponent(DVF33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel119.setBackground(new java.awt.Color(153, 153, 153));

        DVF43.setBackground(new java.awt.Color(255, 255, 255));
        DVF43.setDateFormatString("dd/MM/yyyy");
        DVF43.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD27.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD27);
        RDD27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD27.setForeground(new java.awt.Color(255, 255, 255));
        RDD27.setText("4º");
        RDD27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD27ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel119Layout = new javax.swing.GroupLayout(jPanel119);
        jPanel119.setLayout(jPanel119Layout);
        jPanel119Layout.setHorizontalGroup(
            jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel119Layout.createSequentialGroup()
                .addComponent(DVF43, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel119Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel119Layout.setVerticalGroup(
            jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel119Layout.createSequentialGroup()
                .addComponent(RDD27)
                .addGap(3, 3, 3)
                .addComponent(DVF43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        CCDM29.setBackground(new java.awt.Color(255, 255, 255));

        jPanel127.setBackground(new java.awt.Color(153, 153, 153));
        jPanel127.setPreferredSize(new java.awt.Dimension(120, 52));

        DVF53.setBackground(new java.awt.Color(255, 255, 255));
        DVF53.setDateFormatString("dd/MM/yyyy");
        DVF53.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD35.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD35);
        RDD35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD35.setForeground(new java.awt.Color(255, 255, 255));
        RDD35.setText("5º");
        RDD35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD35ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel127Layout = new javax.swing.GroupLayout(jPanel127);
        jPanel127.setLayout(jPanel127Layout);
        jPanel127Layout.setHorizontalGroup(
            jPanel127Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DVF53, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel127Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel127Layout.setVerticalGroup(
            jPanel127Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel127Layout.createSequentialGroup()
                .addComponent(RDD35)
                .addGap(3, 3, 3)
                .addComponent(DVF53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel128.setBackground(new java.awt.Color(153, 153, 153));

        DVF63.setBackground(new java.awt.Color(255, 255, 255));
        DVF63.setDateFormatString("dd/MM/yyyy");
        DVF63.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD36.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD36);
        RDD36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD36.setForeground(new java.awt.Color(255, 255, 255));
        RDD36.setText("6º");
        RDD36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD36ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel128Layout = new javax.swing.GroupLayout(jPanel128);
        jPanel128.setLayout(jPanel128Layout);
        jPanel128Layout.setHorizontalGroup(
            jPanel128Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel128Layout.createSequentialGroup()
                .addComponent(DVF63, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel128Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel128Layout.setVerticalGroup(
            jPanel128Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel128Layout.createSequentialGroup()
                .addComponent(RDD36)
                .addGap(3, 3, 3)
                .addComponent(DVF63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout CCDM29Layout = new javax.swing.GroupLayout(CCDM29);
        CCDM29.setLayout(CCDM29Layout);
        CCDM29Layout.setHorizontalGroup(
            CCDM29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM29Layout.createSequentialGroup()
                .addComponent(jPanel127, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel128, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        CCDM29Layout.setVerticalGroup(
            CCDM29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM29Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(CCDM29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel128, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel127, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        CCDM34.setBackground(new java.awt.Color(255, 255, 255));

        jPanel137.setBackground(new java.awt.Color(204, 204, 204));

        DVF34.setBackground(new java.awt.Color(255, 255, 255));
        DVF34.setDateFormatString("dd/MM/yyyy");
        DVF34.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD45.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(RDD45);
        RDD45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD45.setForeground(new java.awt.Color(102, 102, 102));
        RDD45.setText("3º");
        RDD45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD45ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel137Layout = new javax.swing.GroupLayout(jPanel137);
        jPanel137.setLayout(jPanel137Layout);
        jPanel137Layout.setHorizontalGroup(
            jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DVF34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addGroup(jPanel137Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel137Layout.setVerticalGroup(
            jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel137Layout.createSequentialGroup()
                .addComponent(RDD45)
                .addGap(3, 3, 3)
                .addComponent(DVF34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel138.setBackground(new java.awt.Color(204, 204, 204));

        DVF44.setBackground(new java.awt.Color(255, 255, 255));
        DVF44.setDateFormatString("dd/MM/yyyy");
        DVF44.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD46.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(RDD46);
        RDD46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD46.setForeground(new java.awt.Color(102, 102, 102));
        RDD46.setText("4º");
        RDD46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD46ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel138Layout = new javax.swing.GroupLayout(jPanel138);
        jPanel138.setLayout(jPanel138Layout);
        jPanel138Layout.setHorizontalGroup(
            jPanel138Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel138Layout.createSequentialGroup()
                .addComponent(DVF44, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel138Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel138Layout.setVerticalGroup(
            jPanel138Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel138Layout.createSequentialGroup()
                .addComponent(RDD46)
                .addGap(3, 3, 3)
                .addComponent(DVF44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        CCDM35.setBackground(new java.awt.Color(255, 255, 255));

        jPanel139.setBackground(new java.awt.Color(204, 204, 204));
        jPanel139.setPreferredSize(new java.awt.Dimension(120, 52));

        DVF54.setBackground(new java.awt.Color(255, 255, 255));
        DVF54.setDateFormatString("dd/MM/yyyy");
        DVF54.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD47.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(RDD47);
        RDD47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD47.setForeground(new java.awt.Color(102, 102, 102));
        RDD47.setText("5º");
        RDD47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD47ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel139Layout = new javax.swing.GroupLayout(jPanel139);
        jPanel139.setLayout(jPanel139Layout);
        jPanel139Layout.setHorizontalGroup(
            jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DVF54, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel139Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel139Layout.setVerticalGroup(
            jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel139Layout.createSequentialGroup()
                .addComponent(RDD47)
                .addGap(3, 3, 3)
                .addComponent(DVF54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel140.setBackground(new java.awt.Color(204, 204, 204));

        DVF64.setBackground(new java.awt.Color(255, 255, 255));
        DVF64.setDateFormatString("dd/MM/yyyy");
        DVF64.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD48.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(RDD48);
        RDD48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD48.setForeground(new java.awt.Color(102, 102, 102));
        RDD48.setText("6º");
        RDD48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD48ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel140Layout = new javax.swing.GroupLayout(jPanel140);
        jPanel140.setLayout(jPanel140Layout);
        jPanel140Layout.setHorizontalGroup(
            jPanel140Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel140Layout.createSequentialGroup()
                .addComponent(DVF64, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel140Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel140Layout.setVerticalGroup(
            jPanel140Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel140Layout.createSequentialGroup()
                .addComponent(RDD48)
                .addGap(3, 3, 3)
                .addComponent(DVF64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout CCDM35Layout = new javax.swing.GroupLayout(CCDM35);
        CCDM35.setLayout(CCDM35Layout);
        CCDM35Layout.setHorizontalGroup(
            CCDM35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM35Layout.createSequentialGroup()
                .addComponent(jPanel139, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel140, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        CCDM35Layout.setVerticalGroup(
            CCDM35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM35Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(CCDM35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel140, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel139, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout CCDM34Layout = new javax.swing.GroupLayout(CCDM34);
        CCDM34.setLayout(CCDM34Layout);
        CCDM34Layout.setHorizontalGroup(
            CCDM34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM34Layout.createSequentialGroup()
                .addComponent(jPanel137, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel138, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CCDM35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        CCDM34Layout.setVerticalGroup(
            CCDM34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM34Layout.createSequentialGroup()
                .addGroup(CCDM34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CCDM35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CCDM34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel138, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel137, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        CCDM32.setBackground(new java.awt.Color(255, 255, 255));

        jPanel133.setBackground(new java.awt.Color(153, 153, 153));

        DVF35.setBackground(new java.awt.Color(255, 255, 255));
        DVF35.setDateFormatString("dd/MM/yyyy");
        DVF35.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD41.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD41);
        RDD41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD41.setForeground(new java.awt.Color(255, 255, 255));
        RDD41.setText("3º");
        RDD41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD41ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel133Layout = new javax.swing.GroupLayout(jPanel133);
        jPanel133.setLayout(jPanel133Layout);
        jPanel133Layout.setHorizontalGroup(
            jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DVF35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addGroup(jPanel133Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel133Layout.setVerticalGroup(
            jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel133Layout.createSequentialGroup()
                .addComponent(RDD41)
                .addGap(3, 3, 3)
                .addComponent(DVF35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel134.setBackground(new java.awt.Color(153, 153, 153));

        DVF45.setBackground(new java.awt.Color(255, 255, 255));
        DVF45.setDateFormatString("dd/MM/yyyy");
        DVF45.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD42.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD42);
        RDD42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD42.setForeground(new java.awt.Color(255, 255, 255));
        RDD42.setText("4º");
        RDD42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD42ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel134Layout = new javax.swing.GroupLayout(jPanel134);
        jPanel134.setLayout(jPanel134Layout);
        jPanel134Layout.setHorizontalGroup(
            jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel134Layout.createSequentialGroup()
                .addComponent(DVF45, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel134Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel134Layout.setVerticalGroup(
            jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel134Layout.createSequentialGroup()
                .addComponent(RDD42)
                .addGap(3, 3, 3)
                .addComponent(DVF45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        CCDM33.setBackground(new java.awt.Color(255, 255, 255));

        jPanel135.setBackground(new java.awt.Color(153, 153, 153));
        jPanel135.setPreferredSize(new java.awt.Dimension(120, 52));

        DVF55.setBackground(new java.awt.Color(255, 255, 255));
        DVF55.setDateFormatString("dd/MM/yyyy");
        DVF55.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD43.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD43);
        RDD43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD43.setForeground(new java.awt.Color(255, 255, 255));
        RDD43.setText("5º");
        RDD43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD43ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel135Layout = new javax.swing.GroupLayout(jPanel135);
        jPanel135.setLayout(jPanel135Layout);
        jPanel135Layout.setHorizontalGroup(
            jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DVF55, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel135Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel135Layout.setVerticalGroup(
            jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel135Layout.createSequentialGroup()
                .addComponent(RDD43)
                .addGap(3, 3, 3)
                .addComponent(DVF55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel136.setBackground(new java.awt.Color(153, 153, 153));

        DVF65.setBackground(new java.awt.Color(255, 255, 255));
        DVF65.setDateFormatString("dd/MM/yyyy");
        DVF65.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD44.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD44);
        RDD44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD44.setForeground(new java.awt.Color(255, 255, 255));
        RDD44.setText("6º");
        RDD44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD44ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel136Layout = new javax.swing.GroupLayout(jPanel136);
        jPanel136.setLayout(jPanel136Layout);
        jPanel136Layout.setHorizontalGroup(
            jPanel136Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel136Layout.createSequentialGroup()
                .addComponent(DVF65, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel136Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel136Layout.setVerticalGroup(
            jPanel136Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel136Layout.createSequentialGroup()
                .addComponent(RDD44)
                .addGap(3, 3, 3)
                .addComponent(DVF65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout CCDM33Layout = new javax.swing.GroupLayout(CCDM33);
        CCDM33.setLayout(CCDM33Layout);
        CCDM33Layout.setHorizontalGroup(
            CCDM33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM33Layout.createSequentialGroup()
                .addComponent(jPanel135, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel136, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        CCDM33Layout.setVerticalGroup(
            CCDM33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM33Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(CCDM33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel136, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel135, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout CCDM32Layout = new javax.swing.GroupLayout(CCDM32);
        CCDM32.setLayout(CCDM32Layout);
        CCDM32Layout.setHorizontalGroup(
            CCDM32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM32Layout.createSequentialGroup()
                .addComponent(jPanel133, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel134, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CCDM33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        CCDM32Layout.setVerticalGroup(
            CCDM32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM32Layout.createSequentialGroup()
                .addGroup(CCDM32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CCDM33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CCDM32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel134, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel133, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout CCDM23Layout = new javax.swing.GroupLayout(CCDM23);
        CCDM23.setLayout(CCDM23Layout);
        CCDM23Layout.setHorizontalGroup(
            CCDM23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM23Layout.createSequentialGroup()
                .addGroup(CCDM23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CCDM23Layout.createSequentialGroup()
                        .addComponent(jPanel118, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel119, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CCDM29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CCDM34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CCDM32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        CCDM23Layout.setVerticalGroup(
            CCDM23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM23Layout.createSequentialGroup()
                .addGroup(CCDM23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CCDM29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CCDM23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel119, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel118, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(CCDM34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(CCDM32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        CCDM30.setBackground(new java.awt.Color(255, 255, 255));

        jPanel129.setBackground(new java.awt.Color(204, 204, 204));

        DVF12.setBackground(new java.awt.Color(255, 255, 255));
        DVF12.setDateFormatString("dd/MM/yyyy");
        DVF12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD37.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(RDD37);
        RDD37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD37.setForeground(new java.awt.Color(102, 102, 102));
        RDD37.setText("1º");
        RDD37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD37ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel129Layout = new javax.swing.GroupLayout(jPanel129);
        jPanel129.setLayout(jPanel129Layout);
        jPanel129Layout.setHorizontalGroup(
            jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DVF12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addGroup(jPanel129Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel129Layout.setVerticalGroup(
            jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel129Layout.createSequentialGroup()
                .addComponent(RDD37)
                .addGap(3, 3, 3)
                .addComponent(DVF12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel130.setBackground(new java.awt.Color(204, 204, 204));

        DVF22.setBackground(new java.awt.Color(255, 255, 255));
        DVF22.setDateFormatString("dd/MM/yyyy");
        DVF22.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD38.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(RDD38);
        RDD38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD38.setForeground(new java.awt.Color(102, 102, 102));
        RDD38.setText("2º");
        RDD38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD38ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel130Layout = new javax.swing.GroupLayout(jPanel130);
        jPanel130.setLayout(jPanel130Layout);
        jPanel130Layout.setHorizontalGroup(
            jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel130Layout.createSequentialGroup()
                .addComponent(DVF22, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel130Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel130Layout.setVerticalGroup(
            jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel130Layout.createSequentialGroup()
                .addComponent(RDD38)
                .addGap(3, 3, 3)
                .addComponent(DVF22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout CCDM30Layout = new javax.swing.GroupLayout(CCDM30);
        CCDM30.setLayout(CCDM30Layout);
        CCDM30Layout.setHorizontalGroup(
            CCDM30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM30Layout.createSequentialGroup()
                .addComponent(jPanel129, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel130, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        CCDM30Layout.setVerticalGroup(
            CCDM30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM30Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(CCDM30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel130, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel129, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        CCDM31.setBackground(new java.awt.Color(255, 255, 255));

        jPanel131.setBackground(new java.awt.Color(153, 153, 153));

        DVF1.setBackground(new java.awt.Color(255, 255, 255));
        DVF1.setDateFormatString("dd/MM/yyyy");
        DVF1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD39.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD39);
        RDD39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD39.setForeground(new java.awt.Color(255, 255, 255));
        RDD39.setText("1º");
        RDD39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD39ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel131Layout = new javax.swing.GroupLayout(jPanel131);
        jPanel131.setLayout(jPanel131Layout);
        jPanel131Layout.setHorizontalGroup(
            jPanel131Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DVF1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addGroup(jPanel131Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel131Layout.setVerticalGroup(
            jPanel131Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel131Layout.createSequentialGroup()
                .addComponent(RDD39)
                .addGap(3, 3, 3)
                .addComponent(DVF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel132.setBackground(new java.awt.Color(153, 153, 153));

        DVF2.setBackground(new java.awt.Color(255, 255, 255));
        DVF2.setDateFormatString("dd/MM/yyyy");
        DVF2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD40.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD40);
        RDD40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD40.setForeground(new java.awt.Color(255, 255, 255));
        RDD40.setText("2º");
        RDD40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RDD40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD40ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel132Layout = new javax.swing.GroupLayout(jPanel132);
        jPanel132.setLayout(jPanel132Layout);
        jPanel132Layout.setHorizontalGroup(
            jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel132Layout.createSequentialGroup()
                .addComponent(DVF2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel132Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RDD40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel132Layout.setVerticalGroup(
            jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel132Layout.createSequentialGroup()
                .addComponent(RDD40)
                .addGap(3, 3, 3)
                .addComponent(DVF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout CCDM31Layout = new javax.swing.GroupLayout(CCDM31);
        CCDM31.setLayout(CCDM31Layout);
        CCDM31Layout.setHorizontalGroup(
            CCDM31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM31Layout.createSequentialGroup()
                .addComponent(jPanel131, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel132, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        CCDM31Layout.setVerticalGroup(
            CCDM31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM31Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(CCDM31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel132, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel131, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jPanel26.setBackground(new java.awt.Color(232, 76, 61));
        jPanel26.setPreferredSize(new java.awt.Dimension(83, 45));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Visitas Domiciliarias");

        jPanel27.setBackground(new java.awt.Color(193, 57, 45));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblPorcentajeVisitas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPorcentajeVisitas.setForeground(new java.awt.Color(255, 255, 255));
        lblPorcentajeVisitas.setText("100 %  Completado");
        lblPorcentajeVisitas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel2.setBackground(new java.awt.Color(109, 23, 16));
        jPanel2.setPreferredSize(new java.awt.Dimension(1, 100));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        lblNina.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        lblNina.setForeground(new java.awt.Color(255, 255, 255));
        lblNina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niña-50.png"))); // NOI18N
        lblNina.setText("NIÑOS");
        lblNina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNina.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblNino.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        lblNino.setForeground(new java.awt.Color(255, 255, 255));
        lblNino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niño-50.png"))); // NOI18N
        lblNino.setText("NIÑOS");
        lblNino.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNino.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPorcentajeVisitas)
                    .addComponent(jLabel10))
                .addGap(59, 59, 59)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lblNina, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNino, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblPorcentajeVisitas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNino)
                    .addComponent(lblNina))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CCDLayout = new javax.swing.GroupLayout(CCD);
        CCD.setLayout(CCDLayout);
        CCDLayout.setHorizontalGroup(
            CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(CCDLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CCDLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(lblId))
                    .addGroup(CCDLayout.createSequentialGroup()
                        .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CCDM31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CCDM30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CCDM16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CCDM18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CCDM17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CCDM23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CCDM22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CCDM21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(913, Short.MAX_VALUE))
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 1769, Short.MAX_VALUE)
        );
        CCDLayout.setVerticalGroup(
            CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDLayout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CCDM31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CCDM21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CCDM30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CCDM22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CCDM23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CCDLayout.createSequentialGroup()
                        .addComponent(CCDM16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(CCDM17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(CCDM18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addComponent(lblId)
                .addGap(18, 18, 18)
                .addComponent(Opciones, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1769, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(CCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(CCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RDD36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD36ActionPerformed
        if(DVF63.getDate()==null){
            if(RDD36.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF63.setEnabled(true);
                fecha=DVF63;
            }
        } else {
            RDD36.setEnabled(false);
        }
    }//GEN-LAST:event_RDD36ActionPerformed

    private void RDD35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD35ActionPerformed
        if(DVF53.getDate()==null){
            if(RDD35.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF53.setEnabled(true);
                fecha=DVF53;
            }
        } else {
            RDD35.setEnabled(false);
        }
    }//GEN-LAST:event_RDD35ActionPerformed

    private void RDD27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD27ActionPerformed
         if(DVF43.getDate()==null){
            if(RDD27.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF43.setEnabled(true);
                fecha=DVF43;
            }
        } else {
            RDD27.setEnabled(false);
        }
    }//GEN-LAST:event_RDD27ActionPerformed

    private void RDD26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD26ActionPerformed
        if(DVF33.getDate()==null){
            if(RDD26.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF33.setEnabled(true);
                fecha=DVF33;
            }
        } else {
            RDD26.setEnabled(false);
        }
    }//GEN-LAST:event_RDD26ActionPerformed

    private void RDD34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD34ActionPerformed
        if(DVF62.getDate()==null){
            if(RDD34.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF62.setEnabled(true);
                fecha=DVF62;
            }
        } else {
            RDD34.setEnabled(false);
        }
    }//GEN-LAST:event_RDD34ActionPerformed

    private void RDD33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD33ActionPerformed
        if(DVF52.getDate()==null){
            if(RDD33.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF52.setEnabled(true);
                fecha=DVF52;
            }
        } else {
            RDD33.setEnabled(false);
        }
    }//GEN-LAST:event_RDD33ActionPerformed

    private void RDD25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD25ActionPerformed
         if(DVF42.getDate()==null){
            if(RDD25.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF42.setEnabled(true);
                fecha=DVF42;
            }
        } else {
            RDD25.setEnabled(false);
        }
    }//GEN-LAST:event_RDD25ActionPerformed

    private void RDD24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD24ActionPerformed
        if(DVF32.getDate()==null){
            if(RDD24.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF32.setEnabled(true);
                fecha=DVF32;
            }
        } else {
            RDD24.setEnabled(false);
        }
    }//GEN-LAST:event_RDD24ActionPerformed

    private void RDD32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD32ActionPerformed
        if(DVF6.getDate()==null){
            if(RDD32.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF6.setEnabled(true);
                fecha=DVF6;
            }
        } else {
            RDD32.setEnabled(false);
        }
    }//GEN-LAST:event_RDD32ActionPerformed

    private void RDD31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD31ActionPerformed
        if(DVF5.getDate()==null){
            if(RDD31.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF5.setEnabled(true);
                fecha=DVF5;
            }
        } else {
            RDD31.setEnabled(false);
        }
    }//GEN-LAST:event_RDD31ActionPerformed

    private void RDD23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD23ActionPerformed
        if(DVF4.getDate()==null){
            if(RDD23.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF4.setEnabled(true);
                fecha=DVF4;
            }
        } else {
            RDD23.setEnabled(false);
        }
    }//GEN-LAST:event_RDD23ActionPerformed

    private void RDD22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD22ActionPerformed
        if(DVF3.getDate()==null){
            if(RDD22.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF3.setEnabled(true);
                fecha=DVF3;
            }
        } else {
            RDD22.setEnabled(false);
        }
    }//GEN-LAST:event_RDD22ActionPerformed

    private void RDD17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD17ActionPerformed
        if(DVF25.getDate()==null){
            if(RDD17.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF25.setEnabled(true);
                fecha=DVF25;
            }
        } else {
            RDD17.setEnabled(false);
        }
    }//GEN-LAST:event_RDD17ActionPerformed

    private void RDD16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD16ActionPerformed
        if(DVF15.getDate()==null){
            if(RDD16.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF15.setEnabled(true);
                fecha=DVF15;
            }
        } else {
            RDD16.setEnabled(false);
        }
    }//GEN-LAST:event_RDD16ActionPerformed

    private void RDD15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD15ActionPerformed
        if(DVF24.getDate()==null){
            if(RDD15.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF24.setEnabled(true);
                fecha=DVF24;
            }
        } else {
            RDD15.setEnabled(false);
        }
    }//GEN-LAST:event_RDD15ActionPerformed

    private void RDD14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD14ActionPerformed
        if(DVF14.getDate()==null){
            if(RDD14.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF14.setEnabled(true);
                fecha=DVF14;
            }
        } else {
            RDD14.setEnabled(false);
        }
    }//GEN-LAST:event_RDD14ActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        mensaje.setVisible(false);
    }//GEN-LAST:event_b1ActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        if (tge==3 || tge==1 || tge==9){
            mensaje.setVisible(false);
        }
        if (tge==2){
            //            Modificar();
            btnEditar.setEnabled(false);

        }
    }//GEN-LAST:event_bActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        habilitarRadio(true);
        habilitarDatos(false);
        Botones(false);
        fua.setText("");
        fecha.setDate(null);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
       validaRegistro(Integer.parseInt(lblId.getText()));
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

    }//GEN-LAST:event_btnEditarActionPerformed

    private void RDD13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD13ActionPerformed
      if(DVF23.getDate()==null){
            if(RDD13.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF23.setEnabled(true);
                fecha=DVF23;
            }
        } else {
            RDD13.setEnabled(false);
        }
    }//GEN-LAST:event_RDD13ActionPerformed

    private void RDD12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD12ActionPerformed
       if(DVF13.getDate()==null){
            if(RDD12.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF13.setEnabled(true);
                fecha=DVF13;
            }
        } else {
            RDD12.setEnabled(false);
        }
    }//GEN-LAST:event_RDD12ActionPerformed

    private void RDD37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD37ActionPerformed
        if(DVF12.getDate()==null){
            if(RDD37.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF12.setEnabled(true);
                fecha=DVF12;
            }
        } else {
            RDD37.setEnabled(false);
        }
    }//GEN-LAST:event_RDD37ActionPerformed

    private void RDD38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD38ActionPerformed
        if(DVF22.getDate()==null){
            if(RDD38.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF22.setEnabled(true);
                fecha=DVF22;
            }
        } else {
            RDD38.setEnabled(false);
        }
    }//GEN-LAST:event_RDD38ActionPerformed

    private void RDD39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD39ActionPerformed
        if(DVF1.getDate()==null){
            if(RDD39.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF1.setEnabled(true);
                fecha=DVF1;
            }
        } else {
            RDD39.setEnabled(false);
        }
    }//GEN-LAST:event_RDD39ActionPerformed

    private void RDD40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD40ActionPerformed
        if(DVF2.getDate()==null){
            if(RDD40.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF2.setEnabled(true);
                fecha=DVF2;
            }
        } else {
            RDD40.setEnabled(false);
        }
    }//GEN-LAST:event_RDD40ActionPerformed

    private void RDD41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD41ActionPerformed
        if(DVF35.getDate()==null){
            if(RDD41.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF35.setEnabled(true);
                fecha=DVF35;
            }
        } else {
            RDD41.setEnabled(false);
        }
    }//GEN-LAST:event_RDD41ActionPerformed

    private void RDD42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD42ActionPerformed
         if(DVF45.getDate()==null){
            if(RDD42.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF45.setEnabled(true);
                fecha=DVF45;
            }
        } else {
            RDD42.setEnabled(false);
        }
    }//GEN-LAST:event_RDD42ActionPerformed

    private void RDD43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD43ActionPerformed
        if(DVF55.getDate()==null){
            if(RDD43.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF55.setEnabled(true);
                fecha=DVF55;
            }
        } else {
            RDD43.setEnabled(false);
        }
    }//GEN-LAST:event_RDD43ActionPerformed

    private void RDD44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD44ActionPerformed
        if(DVF65.getDate()==null){
            if(RDD44.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF65.setEnabled(true);
                fecha=DVF65;
            }
        } else {
            RDD44.setEnabled(false);
        }
    }//GEN-LAST:event_RDD44ActionPerformed

    private void RDD45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD45ActionPerformed
        if(DVF34.getDate()==null){
            if(RDD45.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF34.setEnabled(true);
                fecha=DVF34;
            }
        } else {
            RDD45.setEnabled(false);
        }
    }//GEN-LAST:event_RDD45ActionPerformed

    private void RDD46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD46ActionPerformed
         if(DVF44.getDate()==null){
            if(RDD46.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF44.setEnabled(true);
                fecha=DVF44;
            }
        } else {
            RDD46.setEnabled(false);
        }
    }//GEN-LAST:event_RDD46ActionPerformed

    private void RDD47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD47ActionPerformed
        if(DVF54.getDate()==null){
            if(RDD47.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF54.setEnabled(true);
                fecha=DVF54;
            }
        } else {
            RDD47.setEnabled(false);
        }
    }//GEN-LAST:event_RDD47ActionPerformed

    private void RDD48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD48ActionPerformed
        if(DVF64.getDate()==null){
            if(RDD48.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                DVF64.setEnabled(true);
                fecha=DVF64;
            }
        } else {
            RDD48.setEnabled(false);
        }
    }//GEN-LAST:event_RDD48ActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        RegistroSeguimiento.jTabbedPane1.setSelectedIndex(2);
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CCD;
    private javax.swing.JPanel CCDM16;
    private javax.swing.JPanel CCDM17;
    private javax.swing.JPanel CCDM18;
    private javax.swing.JPanel CCDM21;
    private javax.swing.JPanel CCDM22;
    private javax.swing.JPanel CCDM23;
    private javax.swing.JPanel CCDM27;
    private javax.swing.JPanel CCDM28;
    private javax.swing.JPanel CCDM29;
    private javax.swing.JPanel CCDM30;
    private javax.swing.JPanel CCDM31;
    private javax.swing.JPanel CCDM32;
    private javax.swing.JPanel CCDM33;
    private javax.swing.JPanel CCDM34;
    private javax.swing.JPanel CCDM35;
    public static com.toedter.calendar.JDateChooser DVF1;
    public static com.toedter.calendar.JDateChooser DVF12;
    public static com.toedter.calendar.JDateChooser DVF13;
    public static com.toedter.calendar.JDateChooser DVF14;
    public static com.toedter.calendar.JDateChooser DVF15;
    public static com.toedter.calendar.JDateChooser DVF2;
    public static com.toedter.calendar.JDateChooser DVF22;
    public static com.toedter.calendar.JDateChooser DVF23;
    public static com.toedter.calendar.JDateChooser DVF24;
    public static com.toedter.calendar.JDateChooser DVF25;
    public static com.toedter.calendar.JDateChooser DVF3;
    public static com.toedter.calendar.JDateChooser DVF32;
    public static com.toedter.calendar.JDateChooser DVF33;
    public static com.toedter.calendar.JDateChooser DVF34;
    public static com.toedter.calendar.JDateChooser DVF35;
    public static com.toedter.calendar.JDateChooser DVF4;
    public static com.toedter.calendar.JDateChooser DVF42;
    public static com.toedter.calendar.JDateChooser DVF43;
    public static com.toedter.calendar.JDateChooser DVF44;
    public static com.toedter.calendar.JDateChooser DVF45;
    public static com.toedter.calendar.JDateChooser DVF5;
    public static com.toedter.calendar.JDateChooser DVF52;
    public static com.toedter.calendar.JDateChooser DVF53;
    public static com.toedter.calendar.JDateChooser DVF54;
    public static com.toedter.calendar.JDateChooser DVF55;
    public static com.toedter.calendar.JDateChooser DVF6;
    public static com.toedter.calendar.JDateChooser DVF62;
    public static com.toedter.calendar.JDateChooser DVF63;
    public static com.toedter.calendar.JDateChooser DVF64;
    public static com.toedter.calendar.JDateChooser DVF65;
    private javax.swing.JPanel Opciones;
    private javax.swing.JRadioButton RDD12;
    private javax.swing.JRadioButton RDD13;
    private javax.swing.JRadioButton RDD14;
    private javax.swing.JRadioButton RDD15;
    private javax.swing.JRadioButton RDD16;
    private javax.swing.JRadioButton RDD17;
    private javax.swing.JRadioButton RDD22;
    private javax.swing.JRadioButton RDD23;
    private javax.swing.JRadioButton RDD24;
    private javax.swing.JRadioButton RDD25;
    private javax.swing.JRadioButton RDD26;
    private javax.swing.JRadioButton RDD27;
    private javax.swing.JRadioButton RDD31;
    private javax.swing.JRadioButton RDD32;
    private javax.swing.JRadioButton RDD33;
    private javax.swing.JRadioButton RDD34;
    private javax.swing.JRadioButton RDD35;
    private javax.swing.JRadioButton RDD36;
    private javax.swing.JRadioButton RDD37;
    private javax.swing.JRadioButton RDD38;
    private javax.swing.JRadioButton RDD39;
    private javax.swing.JRadioButton RDD40;
    private javax.swing.JRadioButton RDD41;
    private javax.swing.JRadioButton RDD42;
    private javax.swing.JRadioButton RDD43;
    private javax.swing.JRadioButton RDD44;
    private javax.swing.JRadioButton RDD45;
    private javax.swing.JRadioButton RDD46;
    private javax.swing.JRadioButton RDD47;
    private javax.swing.JRadioButton RDD48;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel104;
    private javax.swing.JPanel jPanel105;
    private javax.swing.JPanel jPanel106;
    private javax.swing.JPanel jPanel107;
    private javax.swing.JPanel jPanel108;
    private javax.swing.JPanel jPanel109;
    private javax.swing.JPanel jPanel114;
    private javax.swing.JPanel jPanel115;
    private javax.swing.JPanel jPanel116;
    private javax.swing.JPanel jPanel117;
    private javax.swing.JPanel jPanel118;
    private javax.swing.JPanel jPanel119;
    private javax.swing.JPanel jPanel123;
    private javax.swing.JPanel jPanel124;
    private javax.swing.JPanel jPanel125;
    private javax.swing.JPanel jPanel126;
    private javax.swing.JPanel jPanel127;
    private javax.swing.JPanel jPanel128;
    private javax.swing.JPanel jPanel129;
    private javax.swing.JPanel jPanel130;
    private javax.swing.JPanel jPanel131;
    private javax.swing.JPanel jPanel132;
    private javax.swing.JPanel jPanel133;
    private javax.swing.JPanel jPanel134;
    private javax.swing.JPanel jPanel135;
    private javax.swing.JPanel jPanel136;
    private javax.swing.JPanel jPanel137;
    private javax.swing.JPanel jPanel138;
    private javax.swing.JPanel jPanel139;
    private javax.swing.JPanel jPanel140;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel30;
    public static javax.swing.JLabel lblId;
    public static javax.swing.JLabel lblNina;
    public static javax.swing.JLabel lblNino;
    public static javax.swing.JLabel lblPorcentajeVisitas;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    // End of variables declaration//GEN-END:variables
}
