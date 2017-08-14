/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.CRED;

import campos.LimitadorDeDocumento;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelos.ConsultorioEx.ConsultorioExtRsTtoAntiparasitario;
import static vista.ConsultorioEx.ConsultorioAsignacion.txtMedico;
import static vista.admisionEmergencia.FrmFormatoEmergencia.pnlEObservación;

/**
 *
 * @author MYS1
 */
public class RSAITTO extends javax.swing.JInternalFrame {
//defino dos métodosdentro del JInternalFrame y lo instanciamos de la siguiente manera.
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    byte tg;
    byte tge;
    JDateChooser fecha;
    JTextField fua;
    ConsultorioExtRsTtoAntiparasitario TTO26 = new ConsultorioExtRsTtoAntiparasitario();
    public RSAITTO() {
        initComponents();
        QuitarLaBarraTitulo();
        habilitarDatos(false);
        mensaje.setVisible(false);
    }
    public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
    }
    
     public void habilitarRadio(boolean opcion){
      RB1.setEnabled(opcion);  
      RB2.setEnabled(opcion);  
      RB3.setEnabled(opcion);  
      RB4.setEnabled(opcion);  
      RB5.setEnabled(opcion);  
      RB6.setEnabled(opcion);  
      RB7.setEnabled(opcion);  
      RB8.setEnabled(opcion);  
      RB9.setEnabled(opcion);  
      RB10.setEnabled(opcion); 
      RB11.setEnabled(opcion);  
      RB12.setEnabled(opcion);  
      RB13.setEnabled(opcion);  
      RB14.setEnabled(opcion);  
      RB15.setEnabled(opcion);  
      RB16.setEnabled(opcion);  
      RB17.setEnabled(opcion);  
     }

     public void habilitarDatos(boolean opcion){
        txtFuaAmaDu.setEnabled(opcion);
        txtFuaBcg.setEnabled(opcion);
        txtFuaDpt1.setEnabled(opcion);
        txtFuaDpt2.setEnabled(opcion);
        txtFuaHvb.setEnabled(opcion);
        txtFuaInfl1.setEnabled(opcion);
        txtFuaInfl2.setEnabled(opcion);
        txtFuaRot1.setEnabled(opcion);
        txtFuaRot2.setEnabled(opcion);
        txtFuaSpr1.setEnabled(opcion);
        txtFuaSpr2.setEnabled(opcion);
        txtInflR1.setEnabled(opcion);
        txtInflR2.setEnabled(opcion);
        dtAmadu.setEnabled(opcion);
        dtAmadu1.setEnabled(opcion);
        dtAmadu2.setEnabled(opcion);
        dtAmadu3.setEnabled(opcion);
        dtAmadu4.setEnabled(opcion);
        dtBcg.setEnabled(opcion);
        dtDpt1.setEnabled(opcion);
        dtDpt2.setEnabled(opcion);
        dtHvb.setEnabled(opcion);
        dtInfl1.setEnabled(opcion);
        dtInfl2.setEnabled(opcion);
        dtInflR1.setEnabled(opcion);
        dtInflR2.setEnabled(opcion);
        dtRot1.setEnabled(opcion);
        dtRot2.setEnabled(opcion);
        dtSpr1.setEnabled(opcion);
        dtSpr2.setEnabled(opcion);
        txtFuaAmaDu.setEnabled(opcion);
        txtFuaAmaDu1.setEnabled(opcion);
        txtFuaAmaDu2.setEnabled(opcion);
        txtFuaAmaDu3.setEnabled(opcion);
        txtFuaAmaDu4.setEnabled(opcion);
        txtFuaBcg.setEnabled(opcion);
        txtFuaDpt1.setEnabled(opcion);
        txtFuaDpt2.setEnabled(opcion);
        txtFuaHvb.setEnabled(opcion);
        txtFuaInfl1.setEnabled(opcion);
        txtFuaInfl2.setEnabled(opcion);
        txtFuaRot1.setEnabled(opcion);
        txtFuaRot2.setEnabled(opcion);
        txtFuaSpr1.setEnabled(opcion);
        txtFuaSpr2.setEnabled(opcion);
        txtInflR1.setEnabled(opcion);
        txtInflR2.setEnabled(opcion);
    }
     
    public void Botones(boolean opcion){
        btnguardar.setEnabled(opcion);
        btneditar.setEnabled(opcion);
        btnCaccnelar.setEnabled(opcion);
    }
    
    public void validaRegistro(int rs_id){
        try {
            PreparedStatement cmd = TTO26.getCn().prepareStatement("SELECT RS_ID FROM CONSULTORIO_EXT_RS_TTO_ANTIPARASITARIO WHERE RS_ID ='"+rs_id+"'");
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
                           mensaje.setVisible(true);
                           mensaje.setBackground(new Color(255,91,70)); 
                           men.setText("Ingrese una fecha correcta");
                           b.setVisible(false);
                           b1.setVisible(false); 
         }
        
        return fecha;
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
        ConsultorioExtRsTtoAntiparasitario CXRsTTO = new ConsultorioExtRsTtoAntiparasitario();
        ConsultorioExtRsTtoAntiparasitario CXRsTTO2 = new ConsultorioExtRsTtoAntiparasitario();
        try {
    
            CXRsTTO.setRsId(Integer.parseInt(lblId.getText()));
            //1 AÑO
            if(dtBcg.getDate()!=null){
                CXRsTTO.setM11Fecha(determinarFecha(dtBcg));  
                CXRsTTO.setM11Fua(txtFuaBcg.getText());
            }
            
            if(dtHvb.getDate()!=null){
                CXRsTTO.setM12Fecha(determinarFecha(dtHvb));  
                CXRsTTO.setM12Fua(txtFuaHvb.getText());
            }
            //2 AÑOS
            if(dtInfl1.getDate()!=null){
                CXRsTTO.setM21Fecha(determinarFecha(dtInfl1));  
                CXRsTTO.setM21Fua(txtFuaInfl1.getText());
            }
            
            if(dtInfl2.getDate()!=null){
                CXRsTTO.setM22Fecha(determinarFecha(dtInfl2));  
                CXRsTTO.setM22Fua(txtFuaInfl2.getText());
            }
            //3 AÑOS
            if(dtRot1.getDate()!=null){
                CXRsTTO.setM31Fecha(determinarFecha(dtRot1));  
                CXRsTTO.setM31Fua(txtFuaRot1.getText());
            }
            
            if(dtRot2.getDate()!=null){
                CXRsTTO.setM32Fecha(determinarFecha(dtRot2));  
                CXRsTTO.setM32Fua(txtFuaRot2.getText());
            }
            //4 AÑOS
            if(dtSpr1.getDate()!=null){
                CXRsTTO.setM41Fecha(determinarFecha(dtSpr1));  
                CXRsTTO.setM41Fua(txtFuaSpr1.getText());
            }
            
            if(dtSpr2.getDate()!=null){
                CXRsTTO.setM42Fecha(determinarFecha(dtSpr2));  
                CXRsTTO.setM42Fua(txtFuaSpr2.getText());
            }
            //5 AÑOS
            if(dtDpt1.getDate()!=null){
                CXRsTTO.setM51Fecha(determinarFecha(dtDpt1));  
                CXRsTTO.setM51Fua(txtFuaDpt1.getText());
            }
            
            if(dtDpt2.getDate()!=null){
                CXRsTTO.setM52Fecha(determinarFecha(dtDpt2));  
                CXRsTTO.setM52Fua(txtFuaDpt2.getText());
            }
            
            //6 AÑOS
            if(dtInflR1.getDate()!=null){
                CXRsTTO.setM61Fecha(determinarFecha(dtInflR1));  
                CXRsTTO.setM61Fua(txtInflR1.getText());
            }
            
            if(dtInflR2.getDate()!=null){
                CXRsTTO.setM62Fecha(determinarFecha(dtInflR2));  
                CXRsTTO.setM62Fua(txtInflR2.getText());
            }
            //7 AÑOS
            if(dtAmadu.getDate()!=null){
                CXRsTTO.setM71Fecha(determinarFecha(dtAmadu));  
                CXRsTTO.setM71Fua(txtFuaAmaDu.getText());
            }
            
            //8 AÑOS
            if(dtAmadu1.getDate()!=null){
                CXRsTTO.setM81Fecha(determinarFecha(dtAmadu1));  
                CXRsTTO.setM81Fua(txtFuaAmaDu1.getText());
            }
            
            //9 AÑOS
            if(dtAmadu2.getDate()!=null){
                CXRsTTO.setM91Fecha(determinarFecha(dtAmadu2));  
                CXRsTTO.setM91Fua(txtFuaAmaDu2.getText());
            }
            
            //10 AÑOS
            if(dtAmadu3.getDate()!=null){
                CXRsTTO.setM101Fecha(determinarFecha(dtAmadu3));  
                CXRsTTO.setM101Fua(txtFuaAmaDu3.getText());
            }
            
            //11 AÑOS
            if(dtAmadu4.getDate()!=null){
                CXRsTTO.setM111Fecha(determinarFecha(dtAmadu4));  
                CXRsTTO.setM111Fua(txtFuaAmaDu4.getText());
            }
            
            if(CXRsTTO.mantenimientoRSAITTO("U")==true){
                mensaje.setVisible(true);
                mensaje.setBackground(new Color(33,115,70)); 
                men.setText("Datos Actualizados de forma correcta");
                b.setText("OK");
                b.setVisible(true);
                b1.setVisible(false);
                btnguardar.setEnabled(false);
                tge=1;
                CXRsTTO2.ConsultoriosExtTTOListar(Integer.parseInt(lblId.getText()));
                CXRsTTO2.porcentajeTTO(Integer.parseInt(lblId.getText()));
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
        ConsultorioExtRsTtoAntiparasitario CXRsTTO = new ConsultorioExtRsTtoAntiparasitario();
        ConsultorioExtRsTtoAntiparasitario CXRsTTO2 = new ConsultorioExtRsTtoAntiparasitario();
        try {
    
            CXRsTTO.setRsId(Integer.parseInt(lblId.getText()));
            //1 AÑO
            if(dtBcg.getDate()!=null){
                CXRsTTO.setM11Fecha(determinarFecha(dtBcg));  
                CXRsTTO.setM11Fua(txtFuaBcg.getText());
            }
            
            if(dtHvb.getDate()!=null){
                CXRsTTO.setM12Fecha(determinarFecha(dtHvb));  
                CXRsTTO.setM12Fua(txtFuaHvb.getText());
            }
            //2 AÑOS
            if(dtInfl1.getDate()!=null){
                CXRsTTO.setM21Fecha(determinarFecha(dtInfl1));  
                CXRsTTO.setM21Fua(txtFuaInfl1.getText());
            }
            
            if(dtInfl2.getDate()!=null){
                CXRsTTO.setM22Fecha(determinarFecha(dtInfl2));  
                CXRsTTO.setM22Fua(txtFuaInfl2.getText());
            }
            //3 AÑOS
            if(dtRot1.getDate()!=null){
                CXRsTTO.setM31Fecha(determinarFecha(dtRot1));  
                CXRsTTO.setM31Fua(txtFuaRot1.getText());
            }
            
            if(dtRot2.getDate()!=null){
                CXRsTTO.setM32Fecha(determinarFecha(dtRot2));  
                CXRsTTO.setM32Fua(txtFuaRot2.getText());
            }
            //4 AÑOS
            if(dtSpr1.getDate()!=null){
                CXRsTTO.setM41Fecha(determinarFecha(dtSpr1));  
                CXRsTTO.setM41Fua(txtFuaSpr1.getText());
            }
            
            if(dtSpr2.getDate()!=null){
                CXRsTTO.setM42Fecha(determinarFecha(dtSpr2));  
                CXRsTTO.setM42Fua(txtFuaSpr2.getText());
            }
            //5 AÑOS
            if(dtDpt1.getDate()!=null){
                CXRsTTO.setM51Fecha(determinarFecha(dtDpt1));  
                CXRsTTO.setM51Fua(txtFuaDpt1.getText());
            }
            
            if(dtDpt2.getDate()!=null){
                CXRsTTO.setM52Fecha(determinarFecha(dtDpt2));  
                CXRsTTO.setM52Fua(txtFuaDpt2.getText());
            }
            
            //6 AÑOS
            if(dtInflR1.getDate()!=null){
                CXRsTTO.setM61Fecha(determinarFecha(dtInflR1));  
                CXRsTTO.setM61Fua(txtInflR1.getText());
            }
            
            if(dtInflR2.getDate()!=null){
                CXRsTTO.setM62Fecha(determinarFecha(dtInflR2));  
                CXRsTTO.setM62Fua(txtInflR2.getText());
            }
            //7 AÑOS
            if(dtAmadu.getDate()!=null){
                CXRsTTO.setM71Fecha(determinarFecha(dtAmadu));  
                CXRsTTO.setM71Fua(txtFuaAmaDu.getText());
            }
            
            //8 AÑOS
            if(dtAmadu1.getDate()!=null){
                CXRsTTO.setM81Fecha(determinarFecha(dtAmadu1));  
                CXRsTTO.setM81Fua(txtFuaAmaDu1.getText());
            }
            
            //9 AÑOS
            if(dtAmadu2.getDate()!=null){
                CXRsTTO.setM91Fecha(determinarFecha(dtAmadu2));  
                CXRsTTO.setM91Fua(txtFuaAmaDu2.getText());
            }
            
            //10 AÑOS
            if(dtAmadu3.getDate()!=null){
                CXRsTTO.setM101Fecha(determinarFecha(dtAmadu3));  
                CXRsTTO.setM101Fua(txtFuaAmaDu3.getText());
            }
            
            //11 AÑOS
            if(dtAmadu4.getDate()!=null){
                CXRsTTO.setM111Fecha(determinarFecha(dtAmadu4));  
                CXRsTTO.setM111Fua(txtFuaAmaDu4.getText());
            }
               
        if(CXRsTTO.mantenimientoRSAITTO("I")==true){
                mensaje.setVisible(true);
                mensaje.setBackground(new Color(33,115,70)); 
                men.setText("Datos Guardados de forma correcta");
                b.setText("OK");
                b.setVisible(true);
                b1.setVisible(false);
                btnguardar.setEnabled(false);
                tge=1;
                CXRsTTO2.ConsultoriosExtTTOListar(Integer.parseInt(lblId.getText()));
                CXRsTTO2.porcentajeTTO(Integer.parseInt(lblId.getText()));
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
        VACUNAS = new javax.swing.JPanel();
        ANIOS_1 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        dtBcg = new com.toedter.calendar.JDateChooser();
        RB1 = new javax.swing.JRadioButton();
        jPanel36 = new javax.swing.JPanel();
        dtHvb = new com.toedter.calendar.JDateChooser();
        RB2 = new javax.swing.JRadioButton();
        txtFuaBcg = new javax.swing.JTextField();
        txtFuaHvb = new javax.swing.JTextField();
        ANIOS_2 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        dtInfl1 = new com.toedter.calendar.JDateChooser();
        RB3 = new javax.swing.JRadioButton();
        jPanel38 = new javax.swing.JPanel();
        dtInfl2 = new com.toedter.calendar.JDateChooser();
        RB4 = new javax.swing.JRadioButton();
        txtFuaInfl1 = new javax.swing.JTextField();
        txtFuaInfl2 = new javax.swing.JTextField();
        ANIOS_3 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        dtRot1 = new com.toedter.calendar.JDateChooser();
        RB5 = new javax.swing.JRadioButton();
        jPanel42 = new javax.swing.JPanel();
        dtRot2 = new com.toedter.calendar.JDateChooser();
        RB6 = new javax.swing.JRadioButton();
        txtFuaRot1 = new javax.swing.JTextField();
        txtFuaRot2 = new javax.swing.JTextField();
        ANIOS_4 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        dtSpr1 = new com.toedter.calendar.JDateChooser();
        RB7 = new javax.swing.JRadioButton();
        jPanel46 = new javax.swing.JPanel();
        dtSpr2 = new com.toedter.calendar.JDateChooser();
        RB8 = new javax.swing.JRadioButton();
        txtFuaSpr1 = new javax.swing.JTextField();
        txtFuaSpr2 = new javax.swing.JTextField();
        ANIOS_5 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        dtDpt1 = new com.toedter.calendar.JDateChooser();
        RB9 = new javax.swing.JRadioButton();
        jPanel43 = new javax.swing.JPanel();
        dtDpt2 = new com.toedter.calendar.JDateChooser();
        RB10 = new javax.swing.JRadioButton();
        txtFuaDpt1 = new javax.swing.JTextField();
        txtFuaDpt2 = new javax.swing.JTextField();
        ANIOS_6 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        dtInflR1 = new com.toedter.calendar.JDateChooser();
        RB11 = new javax.swing.JRadioButton();
        jPanel49 = new javax.swing.JPanel();
        dtInflR2 = new com.toedter.calendar.JDateChooser();
        RB12 = new javax.swing.JRadioButton();
        txtInflR1 = new javax.swing.JTextField();
        txtInflR2 = new javax.swing.JTextField();
        Opciones = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        btneditar = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnCaccnelar = new javax.swing.JButton();
        mensaje = new javax.swing.JPanel();
        men = new javax.swing.JLabel();
        b = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        lblId = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lblPorcentajeTto = new javax.swing.JLabel();
        lblNina = new javax.swing.JLabel();
        lblNino = new javax.swing.JLabel();
        jPanel56 = new javax.swing.JPanel();
        dtAmadu = new com.toedter.calendar.JDateChooser();
        RB13 = new javax.swing.JRadioButton();
        txtFuaAmaDu = new javax.swing.JTextField();
        jPanel58 = new javax.swing.JPanel();
        dtAmadu1 = new com.toedter.calendar.JDateChooser();
        RB14 = new javax.swing.JRadioButton();
        txtFuaAmaDu1 = new javax.swing.JTextField();
        jPanel57 = new javax.swing.JPanel();
        dtAmadu2 = new com.toedter.calendar.JDateChooser();
        RB15 = new javax.swing.JRadioButton();
        txtFuaAmaDu2 = new javax.swing.JTextField();
        jPanel59 = new javax.swing.JPanel();
        dtAmadu3 = new com.toedter.calendar.JDateChooser();
        RB16 = new javax.swing.JRadioButton();
        txtFuaAmaDu3 = new javax.swing.JTextField();
        jPanel60 = new javax.swing.JPanel();
        dtAmadu4 = new com.toedter.calendar.JDateChooser();
        RB17 = new javax.swing.JRadioButton();
        txtFuaAmaDu4 = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setMinimumSize(new java.awt.Dimension(1381, 430));
        setVisible(true);

        VACUNAS.setBackground(new java.awt.Color(255, 255, 255));

        ANIOS_1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel31.setBackground(new java.awt.Color(153, 153, 153));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("1 AÑO");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel35.setBackground(new java.awt.Color(153, 153, 153));

        dtBcg.setBackground(new java.awt.Color(255, 255, 255));
        dtBcg.setDateFormatString("dd/MM/yyyy");
        dtBcg.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB1.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB1);
        RB1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB1.setForeground(new java.awt.Color(255, 255, 255));
        RB1.setText("1º");
        RB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtBcg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(RB1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addComponent(RB1)
                .addGap(3, 3, 3)
                .addComponent(dtBcg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel36.setBackground(new java.awt.Color(153, 153, 153));

        dtHvb.setBackground(new java.awt.Color(255, 255, 255));
        dtHvb.setDateFormatString("dd/MM/yyyy");
        dtHvb.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB2.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB2);
        RB2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB2.setForeground(new java.awt.Color(255, 255, 255));
        RB2.setText("2º");
        RB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtHvb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(RB2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addComponent(RB2)
                .addGap(3, 3, 3)
                .addComponent(dtHvb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtFuaBcg.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaBcgCaretUpdate(evt);
            }
        });

        txtFuaHvb.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaHvbCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout ANIOS_1Layout = new javax.swing.GroupLayout(ANIOS_1);
        ANIOS_1.setLayout(ANIOS_1Layout);
        ANIOS_1Layout.setHorizontalGroup(
            ANIOS_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ANIOS_1Layout.createSequentialGroup()
                .addGroup(ANIOS_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtFuaBcg, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(ANIOS_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaHvb)))
        );
        ANIOS_1Layout.setVerticalGroup(
            ANIOS_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ANIOS_1Layout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(ANIOS_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(ANIOS_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuaBcg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaHvb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        ANIOS_2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel33.setBackground(new java.awt.Color(153, 153, 153));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("2 AÑOS");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel37.setBackground(new java.awt.Color(153, 153, 153));

        dtInfl1.setBackground(new java.awt.Color(255, 255, 255));
        dtInfl1.setDateFormatString("dd/MM/yyyy");
        dtInfl1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB3.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB3);
        RB3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB3.setForeground(new java.awt.Color(255, 255, 255));
        RB3.setText("1º");
        RB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtInfl1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(RB3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(RB3)
                .addGap(3, 3, 3)
                .addComponent(dtInfl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel38.setBackground(new java.awt.Color(153, 153, 153));

        dtInfl2.setBackground(new java.awt.Color(255, 255, 255));
        dtInfl2.setDateFormatString("dd/MM/yyyy");
        dtInfl2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB4.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB4);
        RB4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB4.setForeground(new java.awt.Color(255, 255, 255));
        RB4.setText("2º");
        RB4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtInfl2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(RB4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addComponent(RB4)
                .addGap(3, 3, 3)
                .addComponent(dtInfl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtFuaInfl1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaInfl1CaretUpdate(evt);
            }
        });

        txtFuaInfl2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaInfl2CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout ANIOS_2Layout = new javax.swing.GroupLayout(ANIOS_2);
        ANIOS_2.setLayout(ANIOS_2Layout);
        ANIOS_2Layout.setHorizontalGroup(
            ANIOS_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ANIOS_2Layout.createSequentialGroup()
                .addGroup(ANIOS_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtFuaInfl1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(ANIOS_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaInfl2)))
        );
        ANIOS_2Layout.setVerticalGroup(
            ANIOS_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ANIOS_2Layout.createSequentialGroup()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(ANIOS_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(ANIOS_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuaInfl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaInfl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        ANIOS_3.setBackground(new java.awt.Color(204, 204, 204));

        jPanel34.setBackground(new java.awt.Color(153, 153, 153));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("3 AÑOS");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel39.setBackground(new java.awt.Color(153, 153, 153));

        dtRot1.setBackground(new java.awt.Color(255, 255, 255));
        dtRot1.setDateFormatString("dd/MM/yyyy");
        dtRot1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB5.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB5);
        RB5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB5.setForeground(new java.awt.Color(255, 255, 255));
        RB5.setText("1º");
        RB5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtRot1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(RB5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addComponent(RB5)
                .addGap(3, 3, 3)
                .addComponent(dtRot1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel42.setBackground(new java.awt.Color(153, 153, 153));

        dtRot2.setBackground(new java.awt.Color(255, 255, 255));
        dtRot2.setDateFormatString("dd/MM/yyyy");
        dtRot2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB6.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB6);
        RB6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB6.setForeground(new java.awt.Color(255, 255, 255));
        RB6.setText("2º");
        RB6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtRot2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RB6)
                .addGap(39, 39, 39))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addComponent(RB6)
                .addGap(3, 3, 3)
                .addComponent(dtRot2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtFuaRot1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaRot1CaretUpdate(evt);
            }
        });

        txtFuaRot2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaRot2CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout ANIOS_3Layout = new javax.swing.GroupLayout(ANIOS_3);
        ANIOS_3.setLayout(ANIOS_3Layout);
        ANIOS_3Layout.setHorizontalGroup(
            ANIOS_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ANIOS_3Layout.createSequentialGroup()
                .addGroup(ANIOS_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtFuaRot1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(ANIOS_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaRot2)))
        );
        ANIOS_3Layout.setVerticalGroup(
            ANIOS_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ANIOS_3Layout.createSequentialGroup()
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(ANIOS_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(ANIOS_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuaRot1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaRot2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        ANIOS_4.setBackground(new java.awt.Color(204, 204, 204));

        jPanel44.setBackground(new java.awt.Color(153, 153, 153));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("4 AÑOS");

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel45.setBackground(new java.awt.Color(153, 153, 153));

        dtSpr1.setBackground(new java.awt.Color(255, 255, 255));
        dtSpr1.setDateFormatString("dd/MM/yyyy");
        dtSpr1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB7.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB7);
        RB7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB7.setForeground(new java.awt.Color(255, 255, 255));
        RB7.setText("1º");
        RB7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtSpr1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(RB7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addComponent(RB7)
                .addGap(3, 3, 3)
                .addComponent(dtSpr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel46.setBackground(new java.awt.Color(153, 153, 153));

        dtSpr2.setBackground(new java.awt.Color(255, 255, 255));
        dtSpr2.setDateFormatString("dd/MM/yyyy");
        dtSpr2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB8.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB8);
        RB8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB8.setForeground(new java.awt.Color(255, 255, 255));
        RB8.setText("2º");
        RB8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RB8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RB8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtSpr2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addComponent(RB8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addComponent(RB8)
                .addGap(3, 3, 3)
                .addComponent(dtSpr2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtFuaSpr1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaSpr1CaretUpdate(evt);
            }
        });

        txtFuaSpr2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaSpr2CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout ANIOS_4Layout = new javax.swing.GroupLayout(ANIOS_4);
        ANIOS_4.setLayout(ANIOS_4Layout);
        ANIOS_4Layout.setHorizontalGroup(
            ANIOS_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ANIOS_4Layout.createSequentialGroup()
                .addGroup(ANIOS_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtFuaSpr1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(ANIOS_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaSpr2)))
        );
        ANIOS_4Layout.setVerticalGroup(
            ANIOS_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ANIOS_4Layout.createSequentialGroup()
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(ANIOS_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(ANIOS_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuaSpr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaSpr2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        ANIOS_5.setBackground(new java.awt.Color(204, 204, 204));

        jPanel40.setBackground(new java.awt.Color(153, 153, 153));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("5 AÑOS");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel41.setBackground(new java.awt.Color(153, 153, 153));

        dtDpt1.setBackground(new java.awt.Color(255, 255, 255));
        dtDpt1.setDateFormatString("dd/MM/yyyy");
        dtDpt1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB9.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB9);
        RB9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB9.setForeground(new java.awt.Color(255, 255, 255));
        RB9.setText("1º");
        RB9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RB9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtDpt1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addComponent(RB9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(RB9)
                .addGap(3, 3, 3)
                .addComponent(dtDpt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel43.setBackground(new java.awt.Color(153, 153, 153));

        dtDpt2.setBackground(new java.awt.Color(255, 255, 255));
        dtDpt2.setDateFormatString("dd/MM/yyyy");
        dtDpt2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB10.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB10);
        RB10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB10.setForeground(new java.awt.Color(255, 255, 255));
        RB10.setText("2º");
        RB10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RB10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtDpt2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addComponent(RB10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addComponent(RB10)
                .addGap(3, 3, 3)
                .addComponent(dtDpt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtFuaDpt1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaDpt1CaretUpdate(evt);
            }
        });

        txtFuaDpt2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaDpt2CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout ANIOS_5Layout = new javax.swing.GroupLayout(ANIOS_5);
        ANIOS_5.setLayout(ANIOS_5Layout);
        ANIOS_5Layout.setHorizontalGroup(
            ANIOS_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ANIOS_5Layout.createSequentialGroup()
                .addGroup(ANIOS_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtFuaDpt1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(ANIOS_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaDpt2)))
            .addComponent(jPanel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ANIOS_5Layout.setVerticalGroup(
            ANIOS_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ANIOS_5Layout.createSequentialGroup()
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(ANIOS_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(ANIOS_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuaDpt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaDpt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        ANIOS_6.setBackground(new java.awt.Color(204, 204, 204));

        jPanel47.setBackground(new java.awt.Color(153, 153, 153));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("6 AÑOS");

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel48.setBackground(new java.awt.Color(153, 153, 153));

        dtInflR1.setBackground(new java.awt.Color(255, 255, 255));
        dtInflR1.setDateFormatString("dd/MM/yyyy");
        dtInflR1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB11.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB11);
        RB11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB11.setForeground(new java.awt.Color(255, 255, 255));
        RB11.setText("1º");
        RB11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtInflR1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(RB11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addComponent(RB11)
                .addGap(3, 3, 3)
                .addComponent(dtInflR1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel49.setBackground(new java.awt.Color(153, 153, 153));

        dtInflR2.setBackground(new java.awt.Color(255, 255, 255));
        dtInflR2.setDateFormatString("dd/MM/yyyy");
        dtInflR2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB12.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB12);
        RB12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB12.setForeground(new java.awt.Color(255, 255, 255));
        RB12.setText("2º");
        RB12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtInflR2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RB12)
                .addGap(38, 38, 38))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addComponent(RB12)
                .addGap(3, 3, 3)
                .addComponent(dtInflR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtInflR1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtInflR1CaretUpdate(evt);
            }
        });

        txtInflR2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtInflR2CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout ANIOS_6Layout = new javax.swing.GroupLayout(ANIOS_6);
        ANIOS_6.setLayout(ANIOS_6Layout);
        ANIOS_6Layout.setHorizontalGroup(
            ANIOS_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ANIOS_6Layout.createSequentialGroup()
                .addGroup(ANIOS_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtInflR1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(ANIOS_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInflR2)))
            .addComponent(jPanel47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ANIOS_6Layout.setVerticalGroup(
            ANIOS_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ANIOS_6Layout.createSequentialGroup()
                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(ANIOS_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(ANIOS_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInflR1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInflR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        Opciones.setBackground(new java.awt.Color(102, 102, 102));

        jPanel28.setBackground(new java.awt.Color(51, 51, 51));

        btneditar.setForeground(new java.awt.Color(240, 240, 240));
        btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
        btneditar.setMnemonic('N');
        btneditar.setContentAreaFilled(false);
        btneditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar.setIconTextGap(30);
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        btnguardar.setForeground(new java.awt.Color(240, 240, 240));
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
        btnguardar.setMnemonic('N');
        btnguardar.setContentAreaFilled(false);
        btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar.setIconTextGap(30);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addComponent(btnCaccnelar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(69, Short.MAX_VALUE)))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(btnCaccnelar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(1288, Short.MAX_VALUE))
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
                .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        OpcionesLayout.setVerticalGroup(
            OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(OpcionesLayout.createSequentialGroup()
                .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblId.setForeground(new java.awt.Color(255, 255, 255));
        lblId.setText("jLabel1");

        jPanel29.setBackground(new java.awt.Color(41, 127, 184));
        jPanel29.setPreferredSize(new java.awt.Dimension(83, 45));

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("TTO Antiparasitario");

        jPanel30.setBackground(new java.awt.Color(50, 151, 219));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblPorcentajeTto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPorcentajeTto.setForeground(new java.awt.Color(255, 255, 255));
        lblPorcentajeTto.setText("100 %  Completado");
        lblPorcentajeTto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

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

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPorcentajeTto)
                    .addComponent(jLabel11))
                .addGap(91, 91, 91)
                .addComponent(lblNina, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNino, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 441, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblPorcentajeTto, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNino)
                    .addComponent(lblNina))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel56.setBackground(new java.awt.Color(153, 153, 153));

        dtAmadu.setBackground(new java.awt.Color(255, 255, 255));
        dtAmadu.setDateFormatString("dd/MM/yyyy");
        dtAmadu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB13.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB13);
        RB13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB13.setForeground(new java.awt.Color(255, 255, 255));
        RB13.setText("7 AÑOS");
        RB13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB13ActionPerformed(evt);
            }
        });

        txtFuaAmaDu.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaAmaDuCaretUpdate(evt);
            }
        });
        txtFuaAmaDu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFuaAmaDuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtAmadu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(RB13)
                .addGap(25, 25, 25))
            .addComponent(txtFuaAmaDu)
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addComponent(RB13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtAmadu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtFuaAmaDu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel58.setBackground(new java.awt.Color(153, 153, 153));

        dtAmadu1.setBackground(new java.awt.Color(255, 255, 255));
        dtAmadu1.setDateFormatString("dd/MM/yyyy");
        dtAmadu1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB14.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB14);
        RB14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB14.setForeground(new java.awt.Color(255, 255, 255));
        RB14.setText("8 AÑOS");
        RB14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB14ActionPerformed(evt);
            }
        });

        txtFuaAmaDu1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaAmaDu1CaretUpdate(evt);
            }
        });
        txtFuaAmaDu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFuaAmaDu1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtAmadu1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel58Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(RB14)
                .addGap(25, 25, 25))
            .addComponent(txtFuaAmaDu1)
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addComponent(RB14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtAmadu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtFuaAmaDu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel57.setBackground(new java.awt.Color(153, 153, 153));

        dtAmadu2.setBackground(new java.awt.Color(255, 255, 255));
        dtAmadu2.setDateFormatString("dd/MM/yyyy");
        dtAmadu2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB15.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB15);
        RB15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB15.setForeground(new java.awt.Color(255, 255, 255));
        RB15.setText("9 AÑOS");
        RB15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB15ActionPerformed(evt);
            }
        });

        txtFuaAmaDu2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaAmaDu2CaretUpdate(evt);
            }
        });
        txtFuaAmaDu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFuaAmaDu2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtAmadu2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(RB15)
                .addGap(25, 25, 25))
            .addComponent(txtFuaAmaDu2)
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addComponent(RB15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtAmadu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtFuaAmaDu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel59.setBackground(new java.awt.Color(153, 153, 153));

        dtAmadu3.setBackground(new java.awt.Color(255, 255, 255));
        dtAmadu3.setDateFormatString("dd/MM/yyyy");
        dtAmadu3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB16.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB16);
        RB16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB16.setForeground(new java.awt.Color(255, 255, 255));
        RB16.setText("10 AÑOS");
        RB16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RB16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB16ActionPerformed(evt);
            }
        });

        txtFuaAmaDu3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaAmaDu3CaretUpdate(evt);
            }
        });
        txtFuaAmaDu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFuaAmaDu3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtAmadu3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addComponent(txtFuaAmaDu3)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RB16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addComponent(RB16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtAmadu3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtFuaAmaDu3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel60.setBackground(new java.awt.Color(153, 153, 153));

        dtAmadu4.setBackground(new java.awt.Color(255, 255, 255));
        dtAmadu4.setDateFormatString("dd/MM/yyyy");
        dtAmadu4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RB17.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RB17);
        RB17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RB17.setForeground(new java.awt.Color(255, 255, 255));
        RB17.setText("8 AÑOS");
        RB17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RB17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB17ActionPerformed(evt);
            }
        });

        txtFuaAmaDu4.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaAmaDu4CaretUpdate(evt);
            }
        });
        txtFuaAmaDu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFuaAmaDu4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtAmadu4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addComponent(txtFuaAmaDu4)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RB17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addComponent(RB17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtAmadu4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtFuaAmaDu4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout VACUNASLayout = new javax.swing.GroupLayout(VACUNAS);
        VACUNAS.setLayout(VACUNASLayout);
        VACUNASLayout.setHorizontalGroup(
            VACUNASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, 1800, Short.MAX_VALUE)
            .addGroup(VACUNASLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VACUNASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VACUNASLayout.createSequentialGroup()
                        .addComponent(ANIOS_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(ANIOS_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(ANIOS_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(ANIOS_4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblId)
                    .addGroup(VACUNASLayout.createSequentialGroup()
                        .addComponent(ANIOS_5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(ANIOS_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(703, Short.MAX_VALUE))
        );
        VACUNASLayout.setVerticalGroup(
            VACUNASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VACUNASLayout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(VACUNASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ANIOS_1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ANIOS_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ANIOS_3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ANIOS_4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(VACUNASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ANIOS_6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ANIOS_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel60, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(54, 54, 54)
                .addComponent(lblId)
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(Opciones, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VACUNAS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VACUNAS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        validaRegistro(Integer.parseInt(lblId.getText()));
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed

    }//GEN-LAST:event_btneditarActionPerformed

    private void txtFuaBcgCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaBcgCaretUpdate
      
    }//GEN-LAST:event_txtFuaBcgCaretUpdate

    private void txtFuaHvbCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaHvbCaretUpdate

    }//GEN-LAST:event_txtFuaHvbCaretUpdate

    private void txtFuaInfl1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaInfl1CaretUpdate

    }//GEN-LAST:event_txtFuaInfl1CaretUpdate

    private void txtFuaInfl2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaInfl2CaretUpdate
 
    }//GEN-LAST:event_txtFuaInfl2CaretUpdate

    private void txtFuaRot1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaRot1CaretUpdate

    }//GEN-LAST:event_txtFuaRot1CaretUpdate

    private void txtFuaRot2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaRot2CaretUpdate

    }//GEN-LAST:event_txtFuaRot2CaretUpdate

    private void txtFuaSpr1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaSpr1CaretUpdate

    }//GEN-LAST:event_txtFuaSpr1CaretUpdate

    private void txtFuaSpr2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaSpr2CaretUpdate

    }//GEN-LAST:event_txtFuaSpr2CaretUpdate

    private void txtFuaAmaDuCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaAmaDuCaretUpdate

    }//GEN-LAST:event_txtFuaAmaDuCaretUpdate

    private void txtFuaDpt1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaDpt1CaretUpdate

    }//GEN-LAST:event_txtFuaDpt1CaretUpdate

    private void txtFuaDpt2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaDpt2CaretUpdate

    }//GEN-LAST:event_txtFuaDpt2CaretUpdate

    private void txtInflR1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtInflR1CaretUpdate

    }//GEN-LAST:event_txtInflR1CaretUpdate

    private void txtInflR2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtInflR2CaretUpdate

    }//GEN-LAST:event_txtInflR2CaretUpdate

    private void txtFuaAmaDuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFuaAmaDuMouseClicked
       
    }//GEN-LAST:event_txtFuaAmaDuMouseClicked

    private void RB10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB10ActionPerformed
        if(dtDpt2.getDate()==null){
            if(RB10.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtFuaDpt2.setEnabled(true);
                dtDpt2.setEnabled(true);
                txtFuaDpt2.requestFocus();
                txtFuaDpt2.setEnabled(false); 
                fecha=dtDpt2;
                fua = txtFuaDpt2;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB10.setEnabled(false);
        }
    }//GEN-LAST:event_RB10ActionPerformed

    private void RB12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB12ActionPerformed
        if(dtInflR2.getDate()==null){
            if(RB12.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtInflR2.setEnabled(true);
                dtInflR2.setEnabled(true);
                txtInflR2.requestFocus();
                txtInflR2.setEnabled(false);  
                fecha=dtInflR2;
                fua = txtInflR2;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB12.setEnabled(false);
        }
    }//GEN-LAST:event_RB12ActionPerformed

    private void RB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB1ActionPerformed
        if(dtBcg.getDate()==null){
            if(RB1.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtFuaBcg.setEnabled(true);
                dtBcg.setEnabled(true);
                txtFuaBcg.requestFocus();
                txtFuaBcg.setEnabled(false); 
                fecha=dtBcg;
                fua = txtFuaBcg;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB1.setEnabled(false);
        }
    }//GEN-LAST:event_RB1ActionPerformed

    private void RB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB2ActionPerformed
        if(dtHvb.getDate()==null){
            if(RB2.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtFuaHvb.setEnabled(true);
                dtHvb.setEnabled(true);
                txtFuaHvb.requestFocus();
                txtFuaHvb.setEnabled(false);  
                fecha=dtHvb;
                fua = txtFuaHvb;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB2.setEnabled(false);
        }
    }//GEN-LAST:event_RB2ActionPerformed

    private void RB3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB3ActionPerformed
         if(dtInfl1.getDate()==null){
            if(RB3.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtFuaInfl1.setEnabled(true);
                dtInfl1.setEnabled(true);
                txtFuaInfl1.requestFocus();
                txtFuaInfl1.setEnabled(false);  
                fecha=dtInfl1;
                fua = txtFuaInfl1;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB3.setEnabled(false);
        }
    }//GEN-LAST:event_RB3ActionPerformed

    private void RB4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB4ActionPerformed
        if(dtInfl2.getDate()==null){ 
            if(RB4.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtFuaInfl2.setEnabled(true);
                dtInfl2.setEnabled(true);
                txtFuaInfl2.requestFocus();
                txtFuaInfl2.setEnabled(false); 
                fecha=dtInfl2;
                fua = txtFuaInfl2;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB4.setEnabled(false);
        }
    }//GEN-LAST:event_RB4ActionPerformed

    private void RB5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB5ActionPerformed
        if(dtRot1.getDate()==null){
            if(RB5.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtFuaRot1.setEnabled(true);
                dtRot1.setEnabled(true);
                txtFuaRot1.requestFocus();
                txtFuaRot1.setEnabled(false);  
                fecha=dtRot1;
                fua = txtFuaRot1;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB5.setEnabled(false);
        }
    }//GEN-LAST:event_RB5ActionPerformed

    private void RB6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB6ActionPerformed
        if(dtRot2.getDate()==null){
            if(RB6.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtFuaRot2.setEnabled(true);
                dtRot2.setEnabled(true);
                txtFuaRot2.requestFocus();
                txtFuaRot2.setEnabled(false); 
                fecha=dtRot2;
                fua = txtFuaRot2;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB6.setEnabled(false);
        }
    }//GEN-LAST:event_RB6ActionPerformed

    private void RB7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB7ActionPerformed
        if(dtSpr1.getDate()==null){
            if(RB7.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtFuaSpr1.setEnabled(true);
                dtSpr1.setEnabled(true);
                txtFuaSpr1.requestFocus();
                txtFuaSpr1.setEnabled(false); 
                fecha=dtSpr1;
                fua = txtFuaSpr1;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB7.setEnabled(false);
        }
    }//GEN-LAST:event_RB7ActionPerformed

    private void RB8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB8ActionPerformed
        if(dtSpr2.getDate()==null){
            if(RB8.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtFuaSpr2.setEnabled(true);
                dtSpr2.setEnabled(true);
                txtFuaSpr2.requestFocus();
                txtFuaSpr2.setEnabled(false);  
                fecha=dtSpr2;
                fua = txtFuaSpr2;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB8.setEnabled(false);
        }
    }//GEN-LAST:event_RB8ActionPerformed

    private void RB13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB13ActionPerformed
        if(dtAmadu.getDate()==null){
            if(RB13.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtFuaAmaDu.setEnabled(true);
                dtAmadu.setEnabled(true);
                txtFuaAmaDu.requestFocus();
                txtFuaAmaDu.setEnabled(false); 
                fecha=dtAmadu;
                fua = txtFuaAmaDu;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB13.setEnabled(false);
        }
    }//GEN-LAST:event_RB13ActionPerformed

    private void RB9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB9ActionPerformed
        if(dtDpt1.getDate()==null){
            if(RB9.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtFuaDpt1.setEnabled(true);
                dtDpt1.setEnabled(true);
                txtFuaDpt1.requestFocus();
                txtFuaDpt1.setEnabled(false);  
                fecha=dtDpt1;
                fua = txtFuaDpt1;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB9.setEnabled(false);
        }
    }//GEN-LAST:event_RB9ActionPerformed

    private void RB11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB11ActionPerformed
        if(dtInflR1.getDate()==null){
            if(RB11.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtInflR1.setEnabled(true);
                dtInflR1.setEnabled(true);
                txtInflR1.requestFocus();
                txtInflR1.setEnabled(false);  
                fecha=dtInflR1;
                fua = txtInflR1;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB11.setEnabled(false);
        }
    }//GEN-LAST:event_RB11ActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        if (tge==3 || tge==1 || tge==9){
            mensaje.setVisible(false);

        }

        if (tge==2){
//            Modificar();

            btneditar.setEnabled(false);
           ;

        }
      
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        mensaje.setVisible(false);
    }//GEN-LAST:event_b1ActionPerformed

    private void btnCaccnelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaccnelarActionPerformed
        habilitarRadio(true);
        habilitarDatos(false);
        Botones(false);
        fua.setText("");
        fecha.setDate(null);
    }//GEN-LAST:event_btnCaccnelarActionPerformed

    private void RB14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB14ActionPerformed
        if(dtAmadu1.getDate()==null){
            if(RB14.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtFuaAmaDu1.setEnabled(true);
                dtAmadu1.setEnabled(true);
                txtFuaAmaDu1.requestFocus();
                txtFuaAmaDu1.setEnabled(false); 
                fecha=dtAmadu1;
                fua = txtFuaAmaDu1;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB14.setEnabled(false);
        }
    }//GEN-LAST:event_RB14ActionPerformed

    private void txtFuaAmaDu1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaAmaDu1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuaAmaDu1CaretUpdate

    private void txtFuaAmaDu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFuaAmaDu1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuaAmaDu1MouseClicked

    private void RB15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB15ActionPerformed
        if(dtAmadu2.getDate()==null){
            if(RB15.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtFuaAmaDu2.setEnabled(true);
                dtAmadu2.setEnabled(true);
                txtFuaAmaDu2.requestFocus();
                txtFuaAmaDu2.setEnabled(false); 
                fecha=dtAmadu2;
                fua = txtFuaAmaDu2;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB15.setEnabled(false);
        }
    }//GEN-LAST:event_RB15ActionPerformed

    private void txtFuaAmaDu2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaAmaDu2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuaAmaDu2CaretUpdate

    private void txtFuaAmaDu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFuaAmaDu2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuaAmaDu2MouseClicked

    private void RB16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB16ActionPerformed
        if(dtAmadu3.getDate()==null){
            if(RB16.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtFuaAmaDu3.setEnabled(true);
                dtAmadu3.setEnabled(true);
                txtFuaAmaDu3.requestFocus();
                txtFuaAmaDu3.setEnabled(false); 
                fecha=dtAmadu3;
                fua = txtFuaAmaDu3;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB16.setEnabled(false);
        }
    }//GEN-LAST:event_RB16ActionPerformed

    private void txtFuaAmaDu3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaAmaDu3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuaAmaDu3CaretUpdate

    private void txtFuaAmaDu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFuaAmaDu3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuaAmaDu3MouseClicked

    private void RB17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB17ActionPerformed
        if(dtAmadu4.getDate()==null){
            if(RB17.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                txtFuaAmaDu4.setEnabled(true);
                dtAmadu4.setEnabled(true);
                txtFuaAmaDu4.requestFocus();
                txtFuaAmaDu4.setEnabled(false); 
                fecha=dtAmadu4;
                fua = txtFuaAmaDu4;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RB17.setEnabled(false);
        }
    }//GEN-LAST:event_RB17ActionPerformed

    private void txtFuaAmaDu4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaAmaDu4CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuaAmaDu4CaretUpdate

    private void txtFuaAmaDu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFuaAmaDu4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuaAmaDu4MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        RegistroSeguimiento.jTabbedPane1.setSelectedIndex(2);
        this.dispose();
    }//GEN-LAST:event_jLabel14MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ANIOS_1;
    private javax.swing.JPanel ANIOS_2;
    private javax.swing.JPanel ANIOS_3;
    private javax.swing.JPanel ANIOS_4;
    private javax.swing.JPanel ANIOS_5;
    private javax.swing.JPanel ANIOS_6;
    private javax.swing.JPanel Opciones;
    private javax.swing.JRadioButton RB1;
    private javax.swing.JRadioButton RB10;
    private javax.swing.JRadioButton RB11;
    private javax.swing.JRadioButton RB12;
    private javax.swing.JRadioButton RB13;
    private javax.swing.JRadioButton RB14;
    private javax.swing.JRadioButton RB15;
    private javax.swing.JRadioButton RB16;
    private javax.swing.JRadioButton RB17;
    private javax.swing.JRadioButton RB2;
    private javax.swing.JRadioButton RB3;
    private javax.swing.JRadioButton RB4;
    private javax.swing.JRadioButton RB5;
    private javax.swing.JRadioButton RB6;
    private javax.swing.JRadioButton RB7;
    private javax.swing.JRadioButton RB8;
    private javax.swing.JRadioButton RB9;
    private javax.swing.JPanel VACUNAS;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnCaccnelar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btnguardar;
    private javax.swing.ButtonGroup buttonGroup1;
    public static com.toedter.calendar.JDateChooser dtAmadu;
    public static com.toedter.calendar.JDateChooser dtAmadu1;
    public static com.toedter.calendar.JDateChooser dtAmadu2;
    public static com.toedter.calendar.JDateChooser dtAmadu3;
    public static com.toedter.calendar.JDateChooser dtAmadu4;
    public static com.toedter.calendar.JDateChooser dtBcg;
    public static com.toedter.calendar.JDateChooser dtDpt1;
    public static com.toedter.calendar.JDateChooser dtDpt2;
    public static com.toedter.calendar.JDateChooser dtHvb;
    public static com.toedter.calendar.JDateChooser dtInfl1;
    public static com.toedter.calendar.JDateChooser dtInfl2;
    public static com.toedter.calendar.JDateChooser dtInflR1;
    public static com.toedter.calendar.JDateChooser dtInflR2;
    public static com.toedter.calendar.JDateChooser dtRot1;
    public static com.toedter.calendar.JDateChooser dtRot2;
    public static com.toedter.calendar.JDateChooser dtSpr1;
    public static com.toedter.calendar.JDateChooser dtSpr2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel60;
    public static javax.swing.JLabel lblId;
    public static javax.swing.JLabel lblNina;
    public static javax.swing.JLabel lblNino;
    public static javax.swing.JLabel lblPorcentajeTto;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    public static javax.swing.JTextField txtFuaAmaDu;
    public static javax.swing.JTextField txtFuaAmaDu1;
    public static javax.swing.JTextField txtFuaAmaDu2;
    public static javax.swing.JTextField txtFuaAmaDu3;
    public static javax.swing.JTextField txtFuaAmaDu4;
    public static javax.swing.JTextField txtFuaBcg;
    public static javax.swing.JTextField txtFuaDpt1;
    public static javax.swing.JTextField txtFuaDpt2;
    public static javax.swing.JTextField txtFuaHvb;
    public static javax.swing.JTextField txtFuaInfl1;
    public static javax.swing.JTextField txtFuaInfl2;
    public static javax.swing.JTextField txtFuaRot1;
    public static javax.swing.JTextField txtFuaRot2;
    public static javax.swing.JTextField txtFuaSpr1;
    public static javax.swing.JTextField txtFuaSpr2;
    public static javax.swing.JTextField txtInflR1;
    public static javax.swing.JTextField txtInflR2;
    // End of variables declaration//GEN-END:variables
}
