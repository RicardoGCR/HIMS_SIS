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
import javax.swing.JTextField;
import modelos.ConsultorioEx.ConsultorioExtRsDiagnosticoNutricional;
import modelos.ConsultorioEx.ConsultorioExtRsEstimulacionTemprana;

/**
 *
 * @author MYS1
 */
public class RSAIET extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
private Dimension DimensionBarra = null;
byte tg;
byte tge;
JDateChooser fecha;
JTextField fua;
JLabel cie10;
ConsultorioExtRsEstimulacionTemprana ET01 = new ConsultorioExtRsEstimulacionTemprana();
    /**
     * Creates new form RSAIET
     */
    public RSAIET() {
        initComponents();
        QuitarLaBarraTitulo();
        habilitarCampos(false);
        habilitarCampos(false);
        mensaje.setVisible(false);
        FrmCie10.setLocationRelativeTo(null);//en el centro
        FrmCie10.getContentPane().setBackground(new Color(0,153,102));
        Botones(false);
        ConsultorioExtRsEstimulacionTemprana CCDBUSCAR = new ConsultorioExtRsEstimulacionTemprana();
        CCDBUSCAR.cargarDatosCie10("", tbCiePresun);
    }
    
    public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
    }
    
     public void habilitarCampos(boolean opcion){
        FETM1.setEnabled(opcion);
        FETM2.setEnabled(opcion);
        FETM3.setEnabled(opcion);
        FETM4.setEnabled(opcion);
        FETM5.setEnabled(opcion);
        FETM6.setEnabled(opcion);
        FET11.setEnabled(opcion);
        FET12.setEnabled(opcion);
        FET13.setEnabled(opcion);
        FET14.setEnabled(opcion);
        FET21.setEnabled(opcion);
        FET22.setEnabled(opcion);
        FET23.setEnabled(opcion);
        
        DXETM1.setEnabled(opcion);
        DXETM2.setEnabled(opcion);
        DXETM3.setEnabled(opcion);
        DXETM4.setEnabled(opcion);
        DXETM5.setEnabled(opcion);
        DXETM6.setEnabled(opcion);
        DXET11.setEnabled(opcion);
        DXET12.setEnabled(opcion);
        DXET13.setEnabled(opcion);
        DXET14.setEnabled(opcion);
        DXET21.setEnabled(opcion);
        DXET22.setEnabled(opcion);
        DXET23.setEnabled(opcion);
        
        FUAETM1.setEnabled(opcion);
        FUAETM2.setEnabled(opcion);
        FUAETM3.setEnabled(opcion);
        FUAETM4.setEnabled(opcion);
        FUAETM5.setEnabled(opcion);
        FUAETM6.setEnabled(opcion);
        DXET11.setEnabled(opcion);
        DXET12.setEnabled(opcion);
        DXET13.setEnabled(opcion);
        DXET14.setEnabled(opcion);
        DXET21.setEnabled(opcion);
        DXET22.setEnabled(opcion);
        DXET23.setEnabled(opcion);
  
     }
     
     public void habilitarRadio(boolean opcion){
      RM1.setEnabled(opcion);  
      RM2.setEnabled(opcion);
      RM3.setEnabled(opcion);  
      RM4.setEnabled(opcion);  
      RM5.setEnabled(opcion);  
      RM6.setEnabled(opcion);   
      RM7.setEnabled(opcion);  
      RM8.setEnabled(opcion);  
      RM9.setEnabled(opcion);  
      RM10.setEnabled(opcion);  
      RM11.setEnabled(opcion);   
      RM12.setEnabled(opcion);  
      RM13.setEnabled(opcion);  
 
    }
     
    public void Botones(boolean opcion){
        btnGuardar.setEnabled(opcion);
        btnEditar.setEnabled(opcion);
        btnCancelar.setEnabled(opcion);
    }
     
     public void validaRegistro(int rs_id){
        try {
            PreparedStatement cmd = ET01.getCn().prepareStatement("SELECT RS_ID FROM CONSULTORIO_EXT_RS_ESTIMULACION_TEMPRANA WHERE RS_ID ='"+rs_id+"'");
            ResultSet res = cmd.executeQuery();
            if(res.next()){ // si existe
                Modificar(fecha);
            }else { // no existe
                Guardar(fecha);
            }
        } catch (Exception e) {
            mensaje.setVisible(true);
            mensaje.setBackground(new Color(255,91,70)); 
            men.setText("Ocurrio un error, verifique");
            b.setVisible(false);
            b1.setVisible(false);
        }
    }
     
    public void enviarDiagnosticos(JLabel cie10){
        int fila = tbCiePresun.getSelectedRow();
        FrmCie10.dispose();
        cie10.setText(String.valueOf(tbCiePresun.getValueAt(fila, 1)));
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
    
    public void Guardar(JDateChooser fecha){
        if(fecha.getDate()==null){
            mensaje.setVisible(true);
            mensaje.setBackground(new Color(255,91,70)); 
            men.setText("Ingrese una fecha valida");
            b.setVisible(false);
            b1.setVisible(false);
        } else {
    ConsultorioExtRsEstimulacionTemprana CXRsET = new ConsultorioExtRsEstimulacionTemprana();
    ConsultorioExtRsEstimulacionTemprana CXRsET2 = new ConsultorioExtRsEstimulacionTemprana();
    try {
                 
            CXRsET.setRs_id(Integer.parseInt(lblId.getText()));
            ///////////////////////////////////////////////////////////
            //MENORES DE UN AÑO
            if(FETM1.getDate()!=null){
                CXRsET.setM1Fecha(determinarFecha(FETM1));  
                CXRsET.setM1Cie10(DXETM1.getText());
                CXRsET.setM1Fua(FUAETM1.getText());
            }
            
            if(FETM2.getDate()!=null){
                CXRsET.setM2Fecha(determinarFecha(FETM2));  
                CXRsET.setM2Cie10(DXETM2.getText());
                CXRsET.setM2Fua(FUAETM2.getText());
            }
            
            if(FETM3.getDate()!=null){
                CXRsET.setM3Fecha(determinarFecha(FETM3));  
                CXRsET.setM3Cie10(DXETM3.getText());
                CXRsET.setM3Fua(FUAETM3.getText());
            }
            
            if(FETM4.getDate()!=null){
                CXRsET.setM4Fecha(determinarFecha(FETM4));  
                CXRsET.setM4Cie10(DXETM4.getText());
                CXRsET.setM4Fua(FUAETM4.getText());
            }
            
            if(FETM5.getDate()!=null){
                CXRsET.setM5Fecha(determinarFecha(FETM5));  
                CXRsET.setM5Cie10(DXETM5.getText());
                CXRsET.setM5Fua(FUAETM5.getText());
            }
            
            if(FETM6.getDate()!=null){
                CXRsET.setM6Fecha(determinarFecha(FETM6));  
                CXRsET.setM6Cie10(DXETM6.getText());
                CXRsET.setM6Fua(FUAETM6.getText());
            }
            
            /////////////////////////////////////////////////////////
            //1 AÑO
            if(FET11.getDate()!=null){
                CXRsET.setM7Fecha(determinarFecha(FET11));  
                CXRsET.setM7Cie10(DXET11.getText());
                CXRsET.setM7Fua(FUAET11.getText());
            }
            
            if(FET12.getDate()!=null){
                CXRsET.setM8Fecha(determinarFecha(FET12));  
                CXRsET.setM8Cie10(DXET12.getText());
                CXRsET.setM8Fua(FUAET12.getText());
            }
            
            if(FET13.getDate()!=null){
                CXRsET.setM9Fecha(determinarFecha(FET13));  
                CXRsET.setM9Cie10(DXET13.getText());
                CXRsET.setM9Fua(FUAET13.getText());
            }
            
            if(FET14.getDate()!=null){
                CXRsET.setM10Fecha(determinarFecha(FET14));  
                CXRsET.setM10Cie10(DXET14.getText());
                CXRsET.setM10Fua(FUAET14.getText());
            }
            ////////////////////////////////////////////////
            //2 AÑOS
            if(FET21.getDate()!=null){
                CXRsET.setM11Fecha(determinarFecha(FET21));  
                CXRsET.setM11Cie10(DXET21.getText());
                CXRsET.setM11Fua(FUAET21.getText());
            }
            
            if(FET22.getDate()!=null){
                CXRsET.setM12Fecha(determinarFecha(FET22));  
                CXRsET.setM12Cie10(DXET22.getText());
                CXRsET.setM12Fua(FUAET22.getText());
            }
            ////////////////////////////////////////////////////
            //3 AÑOS
            if(FET23.getDate()!=null){
                CXRsET.setM13Fecha(determinarFecha(FET23));  
                CXRsET.setM13Cie10(DXET23.getText());
                CXRsET.setM13Fua(FUAET23.getText());
            }
          
            
            
            
            if(CXRsET.mantenimientoRSAIET("I")==true){
                mensaje.setVisible(true);
                mensaje.setBackground(new Color(33,115,70)); 
                men.setText("Datos Guardados de forma correcta");
                b.setText("OK");
                b.setVisible(true);
                b1.setVisible(false);
                btnGuardar.setEnabled(false);
                tge=1;
                CXRsET2.ConsultoriosExtETListar(Integer.parseInt(lblId.getText()));
                CXRsET2.porcentajeET(Integer.parseInt(lblId.getText()));
                habilitarCampos(false);
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
       ConsultorioExtRsEstimulacionTemprana CXRsET = new ConsultorioExtRsEstimulacionTemprana();
    ConsultorioExtRsEstimulacionTemprana CXRsET2 = new ConsultorioExtRsEstimulacionTemprana();
    try {
                 
            CXRsET.setRs_id(Integer.parseInt(lblId.getText()));
            ///////////////////////////////////////////////////////////
            //MENORES DE UN AÑO
            if(FETM1.getDate()!=null){
                CXRsET.setM1Fecha(determinarFecha(FETM1));  
                CXRsET.setM1Cie10(DXETM1.getText());
                CXRsET.setM1Fua(FUAETM1.getText());
            }
            
            if(FETM2.getDate()!=null){
                CXRsET.setM2Fecha(determinarFecha(FETM2));  
                CXRsET.setM2Cie10(DXETM2.getText());
                CXRsET.setM2Fua(FUAETM2.getText());
            }
            
            if(FETM3.getDate()!=null){
                CXRsET.setM3Fecha(determinarFecha(FETM3));  
                CXRsET.setM3Cie10(DXETM3.getText());
                CXRsET.setM3Fua(FUAETM3.getText());
            }
            
            if(FETM4.getDate()!=null){
                CXRsET.setM4Fecha(determinarFecha(FETM4));  
                CXRsET.setM4Cie10(DXETM4.getText());
                CXRsET.setM4Fua(FUAETM4.getText());
            }
            
            if(FETM5.getDate()!=null){
                CXRsET.setM5Fecha(determinarFecha(FETM5));  
                CXRsET.setM5Cie10(DXETM5.getText());
                CXRsET.setM5Fua(FUAETM5.getText());
            }
            
            if(FETM6.getDate()!=null){
                CXRsET.setM6Fecha(determinarFecha(FETM6));  
                CXRsET.setM6Cie10(DXETM6.getText());
                CXRsET.setM6Fua(FUAETM6.getText());
            }
            
            /////////////////////////////////////////////////////////
            //1 AÑO
            if(FET11.getDate()!=null){
                CXRsET.setM7Fecha(determinarFecha(FET11));  
                CXRsET.setM7Cie10(DXET11.getText());
                CXRsET.setM7Fua(FUAET11.getText());
            }
            
            if(FET12.getDate()!=null){
                CXRsET.setM8Fecha(determinarFecha(FET12));  
                CXRsET.setM8Cie10(DXET12.getText());
                CXRsET.setM8Fua(FUAET12.getText());
            }
            
            if(FET13.getDate()!=null){
                CXRsET.setM9Fecha(determinarFecha(FET13));  
                CXRsET.setM9Cie10(DXET13.getText());
                CXRsET.setM9Fua(FUAET13.getText());
            }
            
            if(FET14.getDate()!=null){
                CXRsET.setM10Fecha(determinarFecha(FET14));  
                CXRsET.setM10Cie10(DXET14.getText());
                CXRsET.setM10Fua(FUAET14.getText());
            }
            ////////////////////////////////////////////////
            //2 AÑOS
            if(FET21.getDate()!=null){
                CXRsET.setM11Fecha(determinarFecha(FET21));  
                CXRsET.setM11Cie10(DXET21.getText());
                CXRsET.setM11Fua(FUAET21.getText());
            }
            
            if(FET22.getDate()!=null){
                CXRsET.setM12Fecha(determinarFecha(FET22));  
                CXRsET.setM12Cie10(DXET22.getText());
                CXRsET.setM12Fua(FUAET22.getText());
            }
            ////////////////////////////////////////////////////
            //3 AÑOS
            if(FET23.getDate()!=null){
                CXRsET.setM13Fecha(determinarFecha(FET23));  
                CXRsET.setM13Cie10(DXET23.getText());
                CXRsET.setM13Fua(FUAET23.getText());
            }
          
            
        if(CXRsET.mantenimientoRSAIET("U")==true){
                mensaje.setVisible(true);
                mensaje.setBackground(new Color(33,115,70)); 
                men.setText("Datos Actualizados de forma correcta");
                b.setText("OK");
                b.setVisible(true);
                b1.setVisible(false);
                btnGuardar.setEnabled(false);
                tge=1;
                CXRsET2.ConsultoriosExtETListar(Integer.parseInt(lblId.getText()));
                CXRsET2.porcentajeET(Integer.parseInt(lblId.getText()));
                habilitarCampos(false);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
            DD = new javax.swing.JPanel();
            LEYENDA3 = new javax.swing.JPanel();
            jPanel145 = new javax.swing.JPanel();
            jPanel146 = new javax.swing.JPanel();
            jLabel143 = new javax.swing.JLabel();
            jPanel147 = new javax.swing.JPanel();
            jLabel146 = new javax.swing.JLabel();
            jPanel148 = new javax.swing.JPanel();
            jLabel147 = new javax.swing.JLabel();
            jLabel148 = new javax.swing.JLabel();
            CCDM14 = new javax.swing.JPanel();
            jPanel149 = new javax.swing.JPanel();
            jLabel149 = new javax.swing.JLabel();
            jPanel150 = new javax.swing.JPanel();
            FETM1 = new com.toedter.calendar.JDateChooser();
            RM1 = new javax.swing.JRadioButton();
            jPanel151 = new javax.swing.JPanel();
            FETM2 = new com.toedter.calendar.JDateChooser();
            RM2 = new javax.swing.JRadioButton();
            jPanel152 = new javax.swing.JPanel();
            FETM3 = new com.toedter.calendar.JDateChooser();
            RM3 = new javax.swing.JRadioButton();
            FUAETM3 = new javax.swing.JTextField();
            FUAETM1 = new javax.swing.JTextField();
            FUAETM2 = new javax.swing.JTextField();
            DXETM1 = new javax.swing.JLabel();
            DXETM2 = new javax.swing.JLabel();
            DXETM3 = new javax.swing.JLabel();
            jPanel153 = new javax.swing.JPanel();
            FETM4 = new com.toedter.calendar.JDateChooser();
            RM4 = new javax.swing.JRadioButton();
            DXETM4 = new javax.swing.JLabel();
            FUAETM4 = new javax.swing.JTextField();
            jPanel154 = new javax.swing.JPanel();
            FETM5 = new com.toedter.calendar.JDateChooser();
            RM5 = new javax.swing.JRadioButton();
            DXETM5 = new javax.swing.JLabel();
            FUAETM5 = new javax.swing.JTextField();
            jPanel155 = new javax.swing.JPanel();
            FETM6 = new com.toedter.calendar.JDateChooser();
            RM6 = new javax.swing.JRadioButton();
            DXETM6 = new javax.swing.JLabel();
            FUAETM6 = new javax.swing.JTextField();
            CCDM15 = new javax.swing.JPanel();
            jPanel156 = new javax.swing.JPanel();
            jLabel156 = new javax.swing.JLabel();
            jPanel157 = new javax.swing.JPanel();
            FET11 = new com.toedter.calendar.JDateChooser();
            RM7 = new javax.swing.JRadioButton();
            jPanel158 = new javax.swing.JPanel();
            FET12 = new com.toedter.calendar.JDateChooser();
            RM8 = new javax.swing.JRadioButton();
            jPanel159 = new javax.swing.JPanel();
            FET13 = new com.toedter.calendar.JDateChooser();
            RM9 = new javax.swing.JRadioButton();
            FUAET13 = new javax.swing.JTextField();
            FUAET11 = new javax.swing.JTextField();
            FUAET12 = new javax.swing.JTextField();
            DXET11 = new javax.swing.JLabel();
            DXET12 = new javax.swing.JLabel();
            DXET13 = new javax.swing.JLabel();
            jPanel160 = new javax.swing.JPanel();
            FET14 = new com.toedter.calendar.JDateChooser();
            RM10 = new javax.swing.JRadioButton();
            DXET14 = new javax.swing.JLabel();
            FUAET14 = new javax.swing.JTextField();
            CCDM16 = new javax.swing.JPanel();
            jPanel163 = new javax.swing.JPanel();
            jLabel163 = new javax.swing.JLabel();
            jPanel164 = new javax.swing.JPanel();
            FET21 = new com.toedter.calendar.JDateChooser();
            RM11 = new javax.swing.JRadioButton();
            jPanel165 = new javax.swing.JPanel();
            FET22 = new com.toedter.calendar.JDateChooser();
            RM12 = new javax.swing.JRadioButton();
            FUAET21 = new javax.swing.JTextField();
            FUAET22 = new javax.swing.JTextField();
            DXET21 = new javax.swing.JLabel();
            DXET22 = new javax.swing.JLabel();
            CCDM17 = new javax.swing.JPanel();
            jPanel166 = new javax.swing.JPanel();
            jLabel166 = new javax.swing.JLabel();
            jPanel167 = new javax.swing.JPanel();
            FET23 = new com.toedter.calendar.JDateChooser();
            RM13 = new javax.swing.JRadioButton();
            FUAET23 = new javax.swing.JTextField();
            DXET23 = new javax.swing.JLabel();
            lblId = new javax.swing.JLabel();
            jPanel26 = new javax.swing.JPanel();
            jLabel10 = new javax.swing.JLabel();
            jPanel27 = new javax.swing.JPanel();
            jLabel13 = new javax.swing.JLabel();
            lblPorcentajeV = new javax.swing.JLabel();
            jPanel2 = new javax.swing.JPanel();
            lblNina = new javax.swing.JLabel();
            lblNino = new javax.swing.JLabel();
            Opciones = new javax.swing.JPanel();
            jPanel28 = new javax.swing.JPanel();
            btnEditar = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            btnCancelar = new javax.swing.JButton();
            mensaje = new javax.swing.JPanel();
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

            jPanel32.setBackground(new java.awt.Color(41, 127, 184));

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

            DD.setBackground(new java.awt.Color(255, 255, 255));

            LEYENDA3.setBackground(new java.awt.Color(204, 204, 204));

            jPanel145.setBackground(new java.awt.Color(153, 153, 153));

            javax.swing.GroupLayout jPanel145Layout = new javax.swing.GroupLayout(jPanel145);
            jPanel145.setLayout(jPanel145Layout);
            jPanel145Layout.setHorizontalGroup(
                jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );
            jPanel145Layout.setVerticalGroup(
                jPanel145Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 20, Short.MAX_VALUE)
            );

            jPanel146.setBackground(new java.awt.Color(153, 153, 153));

            jLabel143.setBackground(new java.awt.Color(153, 153, 153));
            jLabel143.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel143.setForeground(new java.awt.Color(255, 255, 255));
            jLabel143.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel143.setText("Nº CONRTROL");

            javax.swing.GroupLayout jPanel146Layout = new javax.swing.GroupLayout(jPanel146);
            jPanel146.setLayout(jPanel146Layout);
            jPanel146Layout.setHorizontalGroup(
                jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel146Layout.createSequentialGroup()
                    .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 26, Short.MAX_VALUE))
            );
            jPanel146Layout.setVerticalGroup(
                jPanel146Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel146Layout.createSequentialGroup()
                    .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel147.setBackground(new java.awt.Color(204, 204, 204));

            jLabel146.setForeground(new java.awt.Color(255, 255, 255));
            jLabel146.setText("FECHA");

            javax.swing.GroupLayout jPanel147Layout = new javax.swing.GroupLayout(jPanel147);
            jPanel147.setLayout(jPanel147Layout);
            jPanel147Layout.setHorizontalGroup(
                jPanel147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel147Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel147Layout.setVerticalGroup(
                jPanel147Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel147Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel146))
            );

            jPanel148.setBackground(new java.awt.Color(153, 153, 153));

            jLabel147.setForeground(new java.awt.Color(255, 255, 255));
            jLabel147.setText("DX");

            javax.swing.GroupLayout jPanel148Layout = new javax.swing.GroupLayout(jPanel148);
            jPanel148.setLayout(jPanel148Layout);
            jPanel148Layout.setHorizontalGroup(
                jPanel148Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel148Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel148Layout.setVerticalGroup(
                jPanel148Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel147, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
            );

            jLabel148.setForeground(new java.awt.Color(255, 255, 255));
            jLabel148.setText("FUA");

            javax.swing.GroupLayout LEYENDA3Layout = new javax.swing.GroupLayout(LEYENDA3);
            LEYENDA3.setLayout(LEYENDA3Layout);
            LEYENDA3Layout.setHorizontalGroup(
                LEYENDA3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(LEYENDA3Layout.createSequentialGroup()
                    .addGroup(LEYENDA3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel146, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel147, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel148, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(LEYENDA3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel148, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            LEYENDA3Layout.setVerticalGroup(
                LEYENDA3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LEYENDA3Layout.createSequentialGroup()
                    .addComponent(jPanel145, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel146, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel147, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(jPanel148, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel148, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            CCDM14.setBackground(new java.awt.Color(204, 204, 204));

            jPanel149.setBackground(new java.awt.Color(153, 153, 153));

            jLabel149.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel149.setForeground(new java.awt.Color(255, 255, 255));
            jLabel149.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel149.setText("MENOR DE 1 AÑO");

            javax.swing.GroupLayout jPanel149Layout = new javax.swing.GroupLayout(jPanel149);
            jPanel149.setLayout(jPanel149Layout);
            jPanel149Layout.setHorizontalGroup(
                jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel149Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel149, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel149Layout.setVerticalGroup(
                jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel149)
            );

            jPanel150.setBackground(new java.awt.Color(153, 153, 153));

            FETM1.setBackground(new java.awt.Color(255, 255, 255));
            FETM1.setDateFormatString("dd/MM/yyyy");
            FETM1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(RM1);
            RM1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RM1.setForeground(new java.awt.Color(255, 255, 255));
            RM1.setText("1º (1m)");
            RM1.setContentAreaFilled(false);
            RM1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            RM1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            RM1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RM1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel150Layout = new javax.swing.GroupLayout(jPanel150);
            jPanel150.setLayout(jPanel150Layout);
            jPanel150Layout.setHorizontalGroup(
                jPanel150Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FETM1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel150Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RM1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel150Layout.setVerticalGroup(
                jPanel150Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel150Layout.createSequentialGroup()
                    .addComponent(RM1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FETM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel151.setBackground(new java.awt.Color(153, 153, 153));

            FETM2.setBackground(new java.awt.Color(255, 255, 255));
            FETM2.setDateFormatString("dd/MM/yyyy");
            FETM2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(RM2);
            RM2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RM2.setForeground(new java.awt.Color(255, 255, 255));
            RM2.setText("2º (2m)");
            RM2.setContentAreaFilled(false);
            RM2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            RM2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            RM2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RM2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel151Layout = new javax.swing.GroupLayout(jPanel151);
            jPanel151.setLayout(jPanel151Layout);
            jPanel151Layout.setHorizontalGroup(
                jPanel151Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FETM2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel151Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RM2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel151Layout.setVerticalGroup(
                jPanel151Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel151Layout.createSequentialGroup()
                    .addComponent(RM2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FETM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel152.setBackground(new java.awt.Color(153, 153, 153));

            FETM3.setBackground(new java.awt.Color(255, 255, 255));
            FETM3.setDateFormatString("dd/MM/yyyy");
            FETM3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(RM3);
            RM3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RM3.setForeground(new java.awt.Color(255, 255, 255));
            RM3.setText("3º (4m)");
            RM3.setContentAreaFilled(false);
            RM3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            RM3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            RM3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RM3ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel152Layout = new javax.swing.GroupLayout(jPanel152);
            jPanel152.setLayout(jPanel152Layout);
            jPanel152Layout.setHorizontalGroup(
                jPanel152Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FETM3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel152Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RM3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel152Layout.setVerticalGroup(
                jPanel152Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel152Layout.createSequentialGroup()
                    .addComponent(RM3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FETM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXETM1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXETM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXETM1.setToolTipText("");
            DXETM1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXETM1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXETM1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXETM1MouseClicked(evt);
                }
            });

            DXETM2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXETM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXETM2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXETM2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXETM2.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXETM2MouseClicked(evt);
                }
            });

            DXETM3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXETM3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXETM3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXETM3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXETM3.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXETM3MouseClicked(evt);
                }
            });

            jPanel153.setBackground(new java.awt.Color(153, 153, 153));

            FETM4.setBackground(new java.awt.Color(255, 255, 255));
            FETM4.setDateFormatString("dd/MM/yyyy");
            FETM4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(RM4);
            RM4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RM4.setForeground(new java.awt.Color(255, 255, 255));
            RM4.setText("4º (6m)");
            RM4.setContentAreaFilled(false);
            RM4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            RM4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            RM4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RM4ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel153Layout = new javax.swing.GroupLayout(jPanel153);
            jPanel153.setLayout(jPanel153Layout);
            jPanel153Layout.setHorizontalGroup(
                jPanel153Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FETM4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel153Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RM4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel153Layout.setVerticalGroup(
                jPanel153Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel153Layout.createSequentialGroup()
                    .addComponent(RM4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FETM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXETM4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXETM4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXETM4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXETM4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXETM4.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXETM4MouseClicked(evt);
                }
            });

            jPanel154.setBackground(new java.awt.Color(153, 153, 153));

            FETM5.setBackground(new java.awt.Color(255, 255, 255));
            FETM5.setDateFormatString("dd/MM/yyyy");
            FETM5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(RM5);
            RM5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RM5.setForeground(new java.awt.Color(255, 255, 255));
            RM5.setText("5º (7m)");
            RM5.setContentAreaFilled(false);
            RM5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            RM5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            RM5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RM5ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel154Layout = new javax.swing.GroupLayout(jPanel154);
            jPanel154.setLayout(jPanel154Layout);
            jPanel154Layout.setHorizontalGroup(
                jPanel154Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FETM5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel154Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RM5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel154Layout.setVerticalGroup(
                jPanel154Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel154Layout.createSequentialGroup()
                    .addComponent(RM5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FETM5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXETM5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXETM5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXETM5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXETM5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXETM5.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXETM5MouseClicked(evt);
                }
            });

            jPanel155.setBackground(new java.awt.Color(153, 153, 153));

            FETM6.setBackground(new java.awt.Color(255, 255, 255));
            FETM6.setDateFormatString("dd/MM/yyyy");
            FETM6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(RM6);
            RM6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RM6.setForeground(new java.awt.Color(255, 255, 255));
            RM6.setText("6º (9m)");
            RM6.setContentAreaFilled(false);
            RM6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            RM6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            RM6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RM6ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel155Layout = new javax.swing.GroupLayout(jPanel155);
            jPanel155.setLayout(jPanel155Layout);
            jPanel155Layout.setHorizontalGroup(
                jPanel155Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FETM6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel155Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RM6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel155Layout.setVerticalGroup(
                jPanel155Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel155Layout.createSequentialGroup()
                    .addComponent(RM6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FETM6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXETM6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXETM6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXETM6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXETM6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXETM6.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXETM6MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout CCDM14Layout = new javax.swing.GroupLayout(CCDM14);
            CCDM14.setLayout(CCDM14Layout);
            CCDM14Layout.setHorizontalGroup(
                CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel149, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDM14Layout.createSequentialGroup()
                    .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel150, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUAETM1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXETM1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel151, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUAETM2)
                        .addComponent(DXETM2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUAETM3)
                        .addComponent(jPanel152, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXETM3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUAETM4)
                        .addComponent(jPanel153, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXETM4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUAETM5)
                        .addComponent(jPanel154, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXETM5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUAETM6)
                        .addComponent(jPanel155, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXETM6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );
            CCDM14Layout.setVerticalGroup(
                CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDM14Layout.createSequentialGroup()
                    .addComponent(jPanel149, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDM14Layout.createSequentialGroup()
                            .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel152, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel151, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jPanel150, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(DXETM1)
                                .addComponent(DXETM2)
                                .addComponent(DXETM3))
                            .addGap(0, 0, 0)
                            .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(FUAETM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUAETM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUAETM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(CCDM14Layout.createSequentialGroup()
                            .addComponent(jPanel154, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXETM5)
                            .addGap(0, 0, 0)
                            .addComponent(FUAETM5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCDM14Layout.createSequentialGroup()
                            .addComponent(jPanel153, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXETM4)
                            .addGap(0, 0, 0)
                            .addComponent(FUAETM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCDM14Layout.createSequentialGroup()
                            .addComponent(jPanel155, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXETM6)
                            .addGap(0, 0, 0)
                            .addComponent(FUAETM6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            );

            CCDM15.setBackground(new java.awt.Color(204, 204, 204));

            jPanel156.setBackground(new java.awt.Color(153, 153, 153));

            jLabel156.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel156.setForeground(new java.awt.Color(255, 255, 255));
            jLabel156.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel156.setText("1 AÑO");

            javax.swing.GroupLayout jPanel156Layout = new javax.swing.GroupLayout(jPanel156);
            jPanel156.setLayout(jPanel156Layout);
            jPanel156Layout.setHorizontalGroup(
                jPanel156Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel156Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel156, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel156Layout.setVerticalGroup(
                jPanel156Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel156)
            );

            jPanel157.setBackground(new java.awt.Color(153, 153, 153));

            FET11.setBackground(new java.awt.Color(255, 255, 255));
            FET11.setDateFormatString("dd/MM/yyyy");
            FET11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(RM7);
            RM7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RM7.setForeground(new java.awt.Color(255, 255, 255));
            RM7.setText("1º (1a)");
            RM7.setContentAreaFilled(false);
            RM7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            RM7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            RM7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RM7ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel157Layout = new javax.swing.GroupLayout(jPanel157);
            jPanel157.setLayout(jPanel157Layout);
            jPanel157Layout.setHorizontalGroup(
                jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FET11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel157Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RM7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel157Layout.setVerticalGroup(
                jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel157Layout.createSequentialGroup()
                    .addComponent(RM7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FET11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel158.setBackground(new java.awt.Color(153, 153, 153));

            FET12.setBackground(new java.awt.Color(255, 255, 255));
            FET12.setDateFormatString("dd/MM/yyyy");
            FET12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(RM8);
            RM8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RM8.setForeground(new java.awt.Color(255, 255, 255));
            RM8.setText("2º (1a6m)");
            RM8.setContentAreaFilled(false);
            RM8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            RM8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            RM8.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RM8ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel158Layout = new javax.swing.GroupLayout(jPanel158);
            jPanel158.setLayout(jPanel158Layout);
            jPanel158Layout.setHorizontalGroup(
                jPanel158Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FET12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel158Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RM8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel158Layout.setVerticalGroup(
                jPanel158Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel158Layout.createSequentialGroup()
                    .addComponent(RM8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FET12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel159.setBackground(new java.awt.Color(153, 153, 153));

            FET13.setBackground(new java.awt.Color(255, 255, 255));
            FET13.setDateFormatString("dd/MM/yyyy");
            FET13.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(RM9);
            RM9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RM9.setForeground(new java.awt.Color(255, 255, 255));
            RM9.setText("3º (1a6m)");
            RM9.setContentAreaFilled(false);
            RM9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            RM9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            RM9.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RM9ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel159Layout = new javax.swing.GroupLayout(jPanel159);
            jPanel159.setLayout(jPanel159Layout);
            jPanel159Layout.setHorizontalGroup(
                jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FET13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel159Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RM9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel159Layout.setVerticalGroup(
                jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel159Layout.createSequentialGroup()
                    .addComponent(RM9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FET13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXET11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXET11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXET11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXET11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXET11.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXET11MouseClicked(evt);
                }
            });

            DXET12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXET12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXET12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXET12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXET12.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXET12MouseClicked(evt);
                }
            });

            DXET13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXET13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXET13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXET13.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXET13.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXET13MouseClicked(evt);
                }
            });

            jPanel160.setBackground(new java.awt.Color(153, 153, 153));

            FET14.setBackground(new java.awt.Color(255, 255, 255));
            FET14.setDateFormatString("dd/MM/yyyy");
            FET14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(RM10);
            RM10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RM10.setForeground(new java.awt.Color(255, 255, 255));
            RM10.setText("4º (1a9m)");
            RM10.setContentAreaFilled(false);
            RM10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            RM10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            RM10.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RM10ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel160Layout = new javax.swing.GroupLayout(jPanel160);
            jPanel160.setLayout(jPanel160Layout);
            jPanel160Layout.setHorizontalGroup(
                jPanel160Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FET14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel160Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RM10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel160Layout.setVerticalGroup(
                jPanel160Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel160Layout.createSequentialGroup()
                    .addComponent(RM10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FET14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXET14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXET14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXET14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXET14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXET14.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXET14MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout CCDM15Layout = new javax.swing.GroupLayout(CCDM15);
            CCDM15.setLayout(CCDM15Layout);
            CCDM15Layout.setHorizontalGroup(
                CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel156, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDM15Layout.createSequentialGroup()
                    .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel157, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUAET11, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXET11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel158, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUAET12)
                        .addComponent(DXET12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUAET13)
                        .addComponent(jPanel159, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXET13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUAET14)
                        .addComponent(jPanel160, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXET14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );
            CCDM15Layout.setVerticalGroup(
                CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDM15Layout.createSequentialGroup()
                    .addComponent(jPanel156, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDM15Layout.createSequentialGroup()
                            .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel159, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel158, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jPanel157, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(DXET11)
                                .addComponent(DXET12)
                                .addComponent(DXET13))
                            .addGap(0, 0, 0)
                            .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(FUAET13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUAET11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUAET12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(CCDM15Layout.createSequentialGroup()
                            .addComponent(jPanel160, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXET14)
                            .addGap(0, 0, 0)
                            .addComponent(FUAET14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            );

            CCDM16.setBackground(new java.awt.Color(204, 204, 204));

            jPanel163.setBackground(new java.awt.Color(153, 153, 153));

            jLabel163.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel163.setForeground(new java.awt.Color(255, 255, 255));
            jLabel163.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel163.setText("2 AÑOS");

            javax.swing.GroupLayout jPanel163Layout = new javax.swing.GroupLayout(jPanel163);
            jPanel163.setLayout(jPanel163Layout);
            jPanel163Layout.setHorizontalGroup(
                jPanel163Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel163, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel163Layout.setVerticalGroup(
                jPanel163Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel163)
            );

            jPanel164.setBackground(new java.awt.Color(153, 153, 153));

            FET21.setBackground(new java.awt.Color(255, 255, 255));
            FET21.setDateFormatString("dd/MM/yyyy");
            FET21.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(RM11);
            RM11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RM11.setForeground(new java.awt.Color(255, 255, 255));
            RM11.setText("1º (2a)");
            RM11.setContentAreaFilled(false);
            RM11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            RM11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            RM11.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RM11ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel164Layout = new javax.swing.GroupLayout(jPanel164);
            jPanel164.setLayout(jPanel164Layout);
            jPanel164Layout.setHorizontalGroup(
                jPanel164Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FET21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel164Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RM11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel164Layout.setVerticalGroup(
                jPanel164Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel164Layout.createSequentialGroup()
                    .addComponent(RM11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FET21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel165.setBackground(new java.awt.Color(153, 153, 153));

            FET22.setBackground(new java.awt.Color(255, 255, 255));
            FET22.setDateFormatString("dd/MM/yyyy");
            FET22.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(RM12);
            RM12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RM12.setForeground(new java.awt.Color(255, 255, 255));
            RM12.setText("2º (2a6m)");
            RM12.setContentAreaFilled(false);
            RM12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            RM12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            RM12.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RM12ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel165Layout = new javax.swing.GroupLayout(jPanel165);
            jPanel165.setLayout(jPanel165Layout);
            jPanel165Layout.setHorizontalGroup(
                jPanel165Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FET22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel165Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RM12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel165Layout.setVerticalGroup(
                jPanel165Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel165Layout.createSequentialGroup()
                    .addComponent(RM12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FET22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXET21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXET21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXET21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXET21.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXET21.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXET21MouseClicked(evt);
                }
            });

            DXET22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXET22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXET22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXET22.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXET22.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXET22MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout CCDM16Layout = new javax.swing.GroupLayout(CCDM16);
            CCDM16.setLayout(CCDM16Layout);
            CCDM16Layout.setHorizontalGroup(
                CCDM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel163, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDM16Layout.createSequentialGroup()
                    .addGroup(CCDM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel164, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUAET21, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXET21, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel165, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUAET22)
                        .addComponent(DXET22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );
            CCDM16Layout.setVerticalGroup(
                CCDM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDM16Layout.createSequentialGroup()
                    .addComponent(jPanel163, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(CCDM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel165, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel164, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(CCDM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXET21)
                        .addComponent(DXET22))
                    .addGap(0, 0, 0)
                    .addGroup(CCDM16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FUAET21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUAET22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            CCDM17.setBackground(new java.awt.Color(204, 204, 204));

            jPanel166.setBackground(new java.awt.Color(153, 153, 153));

            jLabel166.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel166.setForeground(new java.awt.Color(255, 255, 255));
            jLabel166.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel166.setText("3 AÑOS");

            javax.swing.GroupLayout jPanel166Layout = new javax.swing.GroupLayout(jPanel166);
            jPanel166.setLayout(jPanel166Layout);
            jPanel166Layout.setHorizontalGroup(
                jPanel166Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel166, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel166Layout.setVerticalGroup(
                jPanel166Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel166)
            );

            jPanel167.setBackground(new java.awt.Color(153, 153, 153));

            FET23.setBackground(new java.awt.Color(255, 255, 255));
            FET23.setDateFormatString("dd/MM/yyyy");
            FET23.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(RM13);
            RM13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RM13.setForeground(new java.awt.Color(255, 255, 255));
            RM13.setText("1º (3a)");
            RM13.setContentAreaFilled(false);
            RM13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            RM13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            RM13.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RM13ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel167Layout = new javax.swing.GroupLayout(jPanel167);
            jPanel167.setLayout(jPanel167Layout);
            jPanel167Layout.setHorizontalGroup(
                jPanel167Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FET23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel167Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(RM13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel167Layout.setVerticalGroup(
                jPanel167Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel167Layout.createSequentialGroup()
                    .addComponent(RM13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FET23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXET23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXET23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXET23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXET23.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXET23.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXET23MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout CCDM17Layout = new javax.swing.GroupLayout(CCDM17);
            CCDM17.setLayout(CCDM17Layout);
            CCDM17Layout.setHorizontalGroup(
                CCDM17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel166, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDM17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel167, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUAET23, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXET23, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            CCDM17Layout.setVerticalGroup(
                CCDM17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDM17Layout.createSequentialGroup()
                    .addComponent(jPanel166, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel167, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DXET23)
                    .addGap(0, 0, 0)
                    .addComponent(FUAET23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            lblId.setText("jLabel1");

            jPanel26.setBackground(new java.awt.Color(67, 94, 120));
            jPanel26.setPreferredSize(new java.awt.Dimension(83, 45));

            jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
            jLabel10.setForeground(new java.awt.Color(255, 255, 255));
            jLabel10.setText("Estimulación Temprana");

            jPanel27.setBackground(new java.awt.Color(22, 48, 74));

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

            lblPorcentajeV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            lblPorcentajeV.setForeground(new java.awt.Color(12, 30, 47));
            lblPorcentajeV.setText("100 %  Completado");
            lblPorcentajeV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

            jPanel2.setBackground(new java.awt.Color(22, 48, 74));
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
            lblNina.setForeground(new java.awt.Color(12, 30, 47));
            lblNina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niña-50.png"))); // NOI18N
            lblNina.setText("NIÑOS");
            lblNina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            lblNina.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

            lblNino.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
            lblNino.setForeground(new java.awt.Color(12, 30, 47));
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
                        .addComponent(lblPorcentajeV)
                        .addComponent(jLabel10))
                    .addGap(31, 31, 31)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31)
                    .addComponent(lblNina, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblNino, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel26Layout.setVerticalGroup(
                jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createSequentialGroup()
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(lblPorcentajeV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel26Layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNina)
                        .addComponent(lblNino))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout DDLayout = new javax.swing.GroupLayout(DD);
            DD.setLayout(DDLayout);
            DDLayout.setHorizontalGroup(
                DDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DDLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(DDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(DDLayout.createSequentialGroup()
                            .addComponent(CCDM15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(1, 1, 1)
                            .addComponent(CCDM16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(1, 1, 1)
                            .addComponent(CCDM17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(DDLayout.createSequentialGroup()
                            .addComponent(LEYENDA3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(CCDM14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addComponent(lblId)
                    .addContainerGap(535, Short.MAX_VALUE))
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 1443, Short.MAX_VALUE)
            );
            DDLayout.setVerticalGroup(
                DDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DDLayout.createSequentialGroup()
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(50, 50, 50)
                    .addGroup(DDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(CCDM14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LEYENDA3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGap(27, 27, 27)
                    .addGroup(DDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(CCDM15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CCDM16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CCDM17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblId))
                    .addGap(0, 59, Short.MAX_VALUE))
            );

            Opciones.setBackground(new java.awt.Color(102, 102, 102));

            jPanel28.setBackground(new java.awt.Color(51, 51, 51));

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

            javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
            jPanel28.setLayout(jPanel28Layout);
            jPanel28Layout.setHorizontalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                    .addContainerGap(129, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(69, Short.MAX_VALUE)))
            );
            jPanel28Layout.setVerticalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addContainerGap(933, Short.MAX_VALUE))
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
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            OpcionesLayout.setVerticalGroup(
                OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(OpcionesLayout.createSequentialGroup()
                    .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 401, Short.MAX_VALUE)
                    .addComponent(Opciones, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void RM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM1ActionPerformed
        if(FETM1.getDate()==null){
            if(RM1.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXETM1.setEnabled(true);
                FETM1.setEnabled(true);
                fecha=FETM1;
                fua = FUAETM1;
                cie10=DXETM1;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RM1.setEnabled(false);
        }
    }//GEN-LAST:event_RM1ActionPerformed

    private void RM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM2ActionPerformed
       if(FETM2.getDate()==null){
            if(RM2.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXETM2.setEnabled(true);
                FETM2.setEnabled(true);
                fecha=FETM2;
                fua = FUAETM2;
                cie10=DXETM2;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RM2.setEnabled(false);
        }
    }//GEN-LAST:event_RM2ActionPerformed

    private void RM3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM3ActionPerformed
        if(FETM3.getDate()==null){
            if(RM3.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXETM3.setEnabled(true);
                FETM3.setEnabled(true);
                fecha=FETM3;
                fua = FUAETM3;
                cie10=DXETM3;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RM3.setEnabled(false);
        }
    }//GEN-LAST:event_RM3ActionPerformed

    private void RM4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM4ActionPerformed
        if(FETM4.getDate()==null){
            if(RM4.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXETM4.setEnabled(true);
                FETM4.setEnabled(true);
                fecha=FETM4;
                fua = FUAETM4;
                cie10=DXETM4;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RM4.setEnabled(false);
        }
    }//GEN-LAST:event_RM4ActionPerformed

    private void RM5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM5ActionPerformed
        if(FETM5.getDate()==null){
            if(RM5.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXETM5.setEnabled(true);
                FETM5.setEnabled(true);
                fecha=FETM5;
                fua = FUAETM5;
                cie10=DXETM5;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RM5.setEnabled(false);
        }
    }//GEN-LAST:event_RM5ActionPerformed

    private void RM6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM6ActionPerformed
        if(FETM6.getDate()==null){
            if(RM6.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXETM6.setEnabled(true);
                FETM6.setEnabled(true);
                fecha=FETM6;
                fua = FUAETM6;
                cie10=DXETM6;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RM6.setEnabled(false);
        }
    }//GEN-LAST:event_RM6ActionPerformed

    private void RM7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM7ActionPerformed
        if(FET11.getDate()==null){
            if(RM7.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXET11.setEnabled(true);
                FET11.setEnabled(true);
                fecha=FET11;
                fua = FUAET11;
                cie10=DXET11;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RM7.setEnabled(false);
        }
    }//GEN-LAST:event_RM7ActionPerformed

    private void RM8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM8ActionPerformed
         if(FET12.getDate()==null){
            if(RM8.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXET12.setEnabled(true);
                FET12.setEnabled(true);
                fecha=FET12;
                fua = FUAET12;
                cie10=DXET12;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RM8.setEnabled(false);
        }
    }//GEN-LAST:event_RM8ActionPerformed

    private void RM9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM9ActionPerformed
         if(FET13.getDate()==null){
            if(RM9.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXET13.setEnabled(true);
                FET13.setEnabled(true);
                fecha=FET13;
                fua = FUAET13;
                cie10=DXET13;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RM9.setEnabled(false);
        }
    }//GEN-LAST:event_RM9ActionPerformed

    private void RM10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM10ActionPerformed
         if(FET14.getDate()==null){
            if(RM10.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXET14.setEnabled(true);
                FET14.setEnabled(true);
                fecha=FET14;
                fua = FUAET14;
                cie10=DXET14;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RM10.setEnabled(false);
        }
    }//GEN-LAST:event_RM10ActionPerformed

    private void RM11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM11ActionPerformed
         if(FET21.getDate()==null){
            if(RM11.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXET21.setEnabled(true);
                FET21.setEnabled(true);
                fecha=FET21;
                fua = FUAET21;
                cie10=DXET21;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RM11.setEnabled(false);
        }
    }//GEN-LAST:event_RM11ActionPerformed

    private void RM12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM12ActionPerformed
         if(FET22.getDate()==null){
            if(RM12.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXET22.setEnabled(true);
                FET22.setEnabled(true);
                fecha=FET22;
                fua = FUAET22;
                cie10=DXET22;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RM12.setEnabled(false);
        }
    }//GEN-LAST:event_RM12ActionPerformed

    private void RM13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RM13ActionPerformed
         if(FET23.getDate()==null){
            if(RM13.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXET23.setEnabled(true);
                FET23.setEnabled(true);
                fecha=FET23;
                fua = FUAET23;
                cie10=DXET23;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RM13.setEnabled(false);
        }
    }//GEN-LAST:event_RM13ActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(cie10.getText()!=""){
            validaRegistro(Integer.parseInt(lblId.getText()));
        } else{
            fecha.setEnabled(true);
            //fua.setEnabled(true);
            mensaje.setVisible(true);
            mensaje.setBackground(new Color(255,91,70)); 
            men.setText("Seleccione el diagnóstico");
            b.setVisible(false);
            b1.setVisible(false);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        habilitarRadio(true);
        habilitarCampos(false);
        Botones(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        if (tge==3 || tge==1 || tge==9){
            mensaje.setVisible(false);

        }

        if (tge==2){
            //            Modificar();

            btnEditar.setEnabled(false);
            ;

        }
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        mensaje.setVisible(false);
    }//GEN-LAST:event_b1ActionPerformed

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T7MouseClicked

    private void txtBuscarCie10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCie10CaretUpdate
        ConsultorioExtRsEstimulacionTemprana ETBUSCAR = new ConsultorioExtRsEstimulacionTemprana();
        ETBUSCAR.cargarDatosCie10(txtBuscarCie10.getText(), tbCiePresun);
    }//GEN-LAST:event_txtBuscarCie10CaretUpdate

    private void txtBuscarCie10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCie10KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbCiePresun.getSelectionModel().setSelectionInterval(0, 0);
            tbCiePresun.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarCie10KeyPressed

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
            enviarDiagnosticos(cie10);
        }
    }//GEN-LAST:event_tbCiePresunKeyPressed

    private void DXETM1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXETM1MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXETM1MouseClicked

    private void DXETM2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXETM2MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXETM2MouseClicked

    private void DXETM3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXETM3MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXETM3MouseClicked

    private void DXETM4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXETM4MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXETM4MouseClicked

    private void DXETM5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXETM5MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXETM5MouseClicked

    private void DXETM6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXETM6MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXETM6MouseClicked

    private void DXET11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXET11MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXET11MouseClicked

    private void DXET12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXET12MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXET12MouseClicked

    private void DXET13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXET13MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXET13MouseClicked

    private void DXET14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXET14MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXET14MouseClicked

    private void DXET21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXET21MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXET21MouseClicked

    private void DXET22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXET22MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXET22MouseClicked

    private void DXET23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXET23MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXET23MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        RegistroSeguimiento.jTabbedPane1.setSelectedIndex(0);
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CCDM14;
    private javax.swing.JPanel CCDM15;
    private javax.swing.JPanel CCDM16;
    private javax.swing.JPanel CCDM17;
    private javax.swing.JPanel DD;
    public static javax.swing.JLabel DXET11;
    public static javax.swing.JLabel DXET12;
    public static javax.swing.JLabel DXET13;
    public static javax.swing.JLabel DXET14;
    public static javax.swing.JLabel DXET21;
    public static javax.swing.JLabel DXET22;
    public static javax.swing.JLabel DXET23;
    public static javax.swing.JLabel DXETM1;
    public static javax.swing.JLabel DXETM2;
    public static javax.swing.JLabel DXETM3;
    public static javax.swing.JLabel DXETM4;
    public static javax.swing.JLabel DXETM5;
    public static javax.swing.JLabel DXETM6;
    public static com.toedter.calendar.JDateChooser FET11;
    public static com.toedter.calendar.JDateChooser FET12;
    public static com.toedter.calendar.JDateChooser FET13;
    public static com.toedter.calendar.JDateChooser FET14;
    public static com.toedter.calendar.JDateChooser FET21;
    public static com.toedter.calendar.JDateChooser FET22;
    public static com.toedter.calendar.JDateChooser FET23;
    public static com.toedter.calendar.JDateChooser FETM1;
    public static com.toedter.calendar.JDateChooser FETM2;
    public static com.toedter.calendar.JDateChooser FETM3;
    public static com.toedter.calendar.JDateChooser FETM4;
    public static com.toedter.calendar.JDateChooser FETM5;
    public static com.toedter.calendar.JDateChooser FETM6;
    public static javax.swing.JTextField FUAET11;
    public static javax.swing.JTextField FUAET12;
    public static javax.swing.JTextField FUAET13;
    public static javax.swing.JTextField FUAET14;
    public static javax.swing.JTextField FUAET21;
    public static javax.swing.JTextField FUAET22;
    public static javax.swing.JTextField FUAET23;
    public static javax.swing.JTextField FUAETM1;
    public static javax.swing.JTextField FUAETM2;
    public static javax.swing.JTextField FUAETM3;
    public static javax.swing.JTextField FUAETM4;
    public static javax.swing.JTextField FUAETM5;
    public static javax.swing.JTextField FUAETM6;
    private javax.swing.JDialog FrmCie10;
    private javax.swing.JPanel LEYENDA3;
    private javax.swing.JPanel Opciones;
    private javax.swing.JRadioButton RM1;
    private javax.swing.JRadioButton RM10;
    private javax.swing.JRadioButton RM11;
    private javax.swing.JRadioButton RM12;
    private javax.swing.JRadioButton RM13;
    private javax.swing.JRadioButton RM2;
    private javax.swing.JRadioButton RM3;
    private javax.swing.JRadioButton RM4;
    private javax.swing.JRadioButton RM5;
    private javax.swing.JRadioButton RM6;
    private javax.swing.JRadioButton RM7;
    private javax.swing.JRadioButton RM8;
    private javax.swing.JRadioButton RM9;
    private javax.swing.JLabel T7;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel145;
    private javax.swing.JPanel jPanel146;
    private javax.swing.JPanel jPanel147;
    private javax.swing.JPanel jPanel148;
    private javax.swing.JPanel jPanel149;
    private javax.swing.JPanel jPanel150;
    private javax.swing.JPanel jPanel151;
    private javax.swing.JPanel jPanel152;
    private javax.swing.JPanel jPanel153;
    private javax.swing.JPanel jPanel154;
    private javax.swing.JPanel jPanel155;
    private javax.swing.JPanel jPanel156;
    private javax.swing.JPanel jPanel157;
    private javax.swing.JPanel jPanel158;
    private javax.swing.JPanel jPanel159;
    private javax.swing.JPanel jPanel160;
    private javax.swing.JPanel jPanel163;
    private javax.swing.JPanel jPanel164;
    private javax.swing.JPanel jPanel165;
    private javax.swing.JPanel jPanel166;
    private javax.swing.JPanel jPanel167;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JLabel lblId;
    public static javax.swing.JLabel lblNina;
    public static javax.swing.JLabel lblNino;
    public static javax.swing.JLabel lblPorcentajeV;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    private javax.swing.JTable tbCiePresun;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscarCie10;
    // End of variables declaration//GEN-END:variables
}
