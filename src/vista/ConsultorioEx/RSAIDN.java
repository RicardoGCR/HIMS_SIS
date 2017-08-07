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
import modelos.ConsultorioEx.ConsultorioExtRsCcd;
import modelos.ConsultorioEx.ConsultorioExtRsDiagnosticoNutricional;
import static vista.ConsultorioEx.RSAICCD.lblId;

/**
 *
 * @author MYS1
 */
public class RSAIDN extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
private Dimension DimensionBarra = null;
byte tg;
byte tge;
JDateChooser fecha;
JTextField fua;
JLabel cie10;
ConsultorioExtRsDiagnosticoNutricional DN01 = new ConsultorioExtRsDiagnosticoNutricional();
    /**
     * Creates new form RSAIDN
     */
    public RSAIDN() {
        initComponents();
        QuitarLaBarraTitulo();
        habilitarCampos(false);
        habilitarCampos(false);
        mensaje.setVisible(false);
        FrmCie10.setLocationRelativeTo(null);//en el centro
        FrmCie10.getContentPane().setBackground(new Color(0,153,102));
        Botones(false);
        ConsultorioExtRsCcd CCDBUSCAR = new ConsultorioExtRsCcd();
        CCDBUSCAR.cargarDatosCie10("", tbCiePresun);
    }
    public void QuitarLaBarraTitulo() // ocultar borde de fomrulario interno
    { 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
    }
    
    public void habilitarCampos(boolean opcion){
        FDN1.setEnabled(opcion);
        FDN2.setEnabled(opcion);
        FDN3.setEnabled(opcion);
        FDN4.setEnabled(opcion);
        FDN5.setEnabled(opcion);
        FDN6.setEnabled(opcion);
        FDN7.setEnabled(opcion);
        FDN8.setEnabled(opcion);
        FDN9.setEnabled(opcion);
        FDN10.setEnabled(opcion);
        DXDN1.setEnabled(opcion);
        DXDN2.setEnabled(opcion);
        DXDN3.setEnabled(opcion);
        DXDN4.setEnabled(opcion);
        DXDN5.setEnabled(opcion);
        DXDN6.setEnabled(opcion);
        DXDN7.setEnabled(opcion);
        DXDN8.setEnabled(opcion);
        DXDN9.setEnabled(opcion);
        DXDN10.setEnabled(opcion);
        
        FUADN1.setEnabled(opcion);
        FUADN2.setEnabled(opcion);
        FUADN3.setEnabled(opcion);
        FUADN4.setEnabled(opcion);
        FUADN5.setEnabled(opcion);
        FUADN6.setEnabled(opcion);
        FUADN7.setEnabled(opcion);
        FUADN8.setEnabled(opcion);
        FUADN9.setEnabled(opcion);
        FUADN10.setEnabled(opcion);
    }
    
    public void habilitarRadio(boolean opcion){
      rbt1.setEnabled(opcion);  
      rbt2.setEnabled(opcion);
      rbt3.setEnabled(opcion);  
      rbt4.setEnabled(opcion);  
      rbt5.setEnabled(opcion);  
      rbt6.setEnabled(opcion);   
      rbt7.setEnabled(opcion);  
      rbt8.setEnabled(opcion);  
      rbt9.setEnabled(opcion);  
      rbt10.setEnabled(opcion);  
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
            PreparedStatement cmd = DN01.getCn().prepareStatement("SELECT RS_ID FROM CONSULTORIO_EXT_RS_DIAGNOSTICO_NUTRICIONAL WHERE RS_ID ='"+rs_id+"'");
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
    
    public void Guardar(JDateChooser fecha){
        if(fecha.getDate()==null){
            mensaje.setVisible(true);
            mensaje.setBackground(new Color(255,91,70)); 
            men.setText("Ingrese una fecha valida");
            b.setVisible(false);
            b1.setVisible(false);
        } else {
    ConsultorioExtRsDiagnosticoNutricional CXRsDN = new ConsultorioExtRsDiagnosticoNutricional();
    ConsultorioExtRsDiagnosticoNutricional CXRsDN2 = new ConsultorioExtRsDiagnosticoNutricional();
    try {
                 
            CXRsDN.setRsId(Integer.parseInt(lblId.getText()));
            if(FDN1.getDate()!=null){
                CXRsDN.setDn1Fecha(determinarFecha(FDN1));  
                CXRsDN.setDn1Cie10(DXDN1.getText());
                CXRsDN.setDn1Fua(FUADN1.getText());
            }
            
            if(FDN2.getDate()!=null){
                CXRsDN.setDn2Fecha(determinarFecha(FDN2));  
                CXRsDN.setDn2Cie10(DXDN2.getText());
                CXRsDN.setDn2Fua(FUADN2.getText());
            }
             
            if(FDN3.getDate()!=null){
                CXRsDN.setDn3Fecha(determinarFecha(FDN3));  
                CXRsDN.setDn3Cie10(DXDN3.getText());
                CXRsDN.setDn3Fua(FUADN3.getText());
            }
             
            if(FDN4.getDate()!=null){
                CXRsDN.setDn4Fecha(determinarFecha(FDN4));  
                CXRsDN.setDn4Cie10(DXDN4.getText());
                CXRsDN.setDn4Fua(FUADN4.getText());
            }
              
            if(FDN5.getDate()!=null){
                CXRsDN.setDn5Fecha(determinarFecha(FDN5));  
                CXRsDN.setDn5Cie10(DXDN5.getText());
                CXRsDN.setDn5Fua(FUADN5.getText());
            }
               
            if(FDN6.getDate()!=null){
                CXRsDN.setDn6Fecha(determinarFecha(FDN6));  
                CXRsDN.setDn6Cie10(DXDN6.getText());
                CXRsDN.setDn6Fua(FUADN6.getText());
            }
                
            if(FDN7.getDate()!=null){
                CXRsDN.setDn1Fecha(determinarFecha(FDN7));  
                CXRsDN.setDn1Cie10(DXDN7.getText());
                CXRsDN.setDn1Fua(FUADN7.getText());
            }
                 
            if(FDN8.getDate()!=null){
                CXRsDN.setDn8Fecha(determinarFecha(FDN8));  
                CXRsDN.setDn8Cie10(DXDN8.getText());
                CXRsDN.setDn8Fua(FUADN8.getText());
            }
                  
            if(FDN9.getDate()!=null){
                CXRsDN.setDn9Fecha(determinarFecha(FDN9));  
                CXRsDN.setDn9Cie10(DXDN9.getText());
                CXRsDN.setDn9Fua(FUADN9.getText());
            }
                   
            if(FDN10.getDate()!=null){
                CXRsDN.setDn10Fecha(determinarFecha(FDN10));  
                CXRsDN.setDn10Cie10(DXDN10.getText());
                CXRsDN.setDn10Fua(FUADN10.getText());
            }
            if(CXRsDN.mantenimientoRSAIDN("I")==true){
                mensaje.setVisible(true);
                mensaje.setBackground(new Color(33,115,70)); 
                men.setText("Datos Guardados de forma correcta");
                b.setText("OK");
                b.setVisible(true);
                b1.setVisible(false);

                btnGuardar.setEnabled(false);
                tge=1;
                CXRsDN2.ConsultoriosExtDNListar(Integer.parseInt(lblId.getText()));
                CXRsDN2.porcentajeDN(Integer.parseInt(lblId.getText()));
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
        ConsultorioExtRsDiagnosticoNutricional CXRsDN = new ConsultorioExtRsDiagnosticoNutricional();
        ConsultorioExtRsDiagnosticoNutricional CXRsDN2 = new ConsultorioExtRsDiagnosticoNutricional();
        try {
    
            CXRsDN.setRsId(Integer.parseInt(lblId.getText()));
            if(FDN1.getDate()!=null){
                CXRsDN.setDn1Fecha(determinarFecha(FDN1));  
                CXRsDN.setDn1Cie10(DXDN1.getText());
                CXRsDN.setDn1Fua(FUADN1.getText());
            }
            
            if(FDN2.getDate()!=null){
                CXRsDN.setDn2Fecha(determinarFecha(FDN2));  
                CXRsDN.setDn2Cie10(DXDN2.getText());
                CXRsDN.setDn2Fua(FUADN2.getText());
            }
             
            if(FDN3.getDate()!=null){
                CXRsDN.setDn3Fecha(determinarFecha(FDN3));  
                CXRsDN.setDn3Cie10(DXDN3.getText());
                CXRsDN.setDn3Fua(FUADN3.getText());
            }
             
            if(FDN4.getDate()!=null){
                CXRsDN.setDn4Fecha(determinarFecha(FDN4));  
                CXRsDN.setDn4Cie10(DXDN4.getText());
                CXRsDN.setDn4Fua(FUADN4.getText());
            }
              
            if(FDN5.getDate()!=null){
                CXRsDN.setDn5Fecha(determinarFecha(FDN5));  
                CXRsDN.setDn5Cie10(DXDN5.getText());
                CXRsDN.setDn5Fua(FUADN5.getText());
            }
               
            if(FDN6.getDate()!=null){
                CXRsDN.setDn6Fecha(determinarFecha(FDN6));  
                CXRsDN.setDn6Cie10(DXDN6.getText());
                CXRsDN.setDn6Fua(FUADN6.getText());
            }
                
            if(FDN7.getDate()!=null){
                CXRsDN.setDn7Fecha(determinarFecha(FDN7));  
                CXRsDN.setDn7Cie10(DXDN7.getText());
                CXRsDN.setDn7Fua(FUADN7.getText());
            }
                 
            if(FDN8.getDate()!=null){
                CXRsDN.setDn8Fecha(determinarFecha(FDN8));  
                CXRsDN.setDn8Cie10(DXDN8.getText());
                CXRsDN.setDn8Fua(FUADN8.getText());
            }
                  
            if(FDN9.getDate()!=null){
                CXRsDN.setDn9Fecha(determinarFecha(FDN9));  
                CXRsDN.setDn9Cie10(DXDN9.getText());
                CXRsDN.setDn9Fua(FUADN9.getText());
            }
                   
            if(FDN10.getDate()!=null){
                CXRsDN.setDn10Fecha(determinarFecha(FDN10));  
                CXRsDN.setDn10Cie10(DXDN10.getText());
                CXRsDN.setDn10Fua(FUADN10.getText());
            }
            
            if(CXRsDN.mantenimientoRSAIDN("U")==true){
                mensaje.setVisible(true);
                mensaje.setBackground(new Color(33,115,70)); 
                men.setText("Datos Actualizados de forma correcta");
                b.setText("OK");
                b.setVisible(true);
                b1.setVisible(false);
                btnGuardar.setEnabled(false);
                tge=1;
                CXRsDN2.ConsultoriosExtDNListar(Integer.parseInt(lblId.getText()));
                CXRsDN2.porcentajeDN(Integer.parseInt(lblId.getText()));
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
     public void enviarDiagnosticos(JLabel cie10){
        int fila = tbCiePresun.getSelectedRow();
        FrmCie10.dispose();
        cie10.setText(String.valueOf(tbCiePresun.getValueAt(fila, 1)));
    }
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
            DN = new javax.swing.JPanel();
            CCDM13 = new javax.swing.JPanel();
            jPanel133 = new javax.swing.JPanel();
            jLabel129 = new javax.swing.JLabel();
            jPanel134 = new javax.swing.JPanel();
            FDN1 = new com.toedter.calendar.JDateChooser();
            rbt1 = new javax.swing.JRadioButton();
            jPanel135 = new javax.swing.JPanel();
            FDN2 = new com.toedter.calendar.JDateChooser();
            rbt2 = new javax.swing.JRadioButton();
            jPanel136 = new javax.swing.JPanel();
            FDN3 = new com.toedter.calendar.JDateChooser();
            rbt3 = new javax.swing.JRadioButton();
            FUADN3 = new javax.swing.JTextField();
            FUADN1 = new javax.swing.JTextField();
            FUADN2 = new javax.swing.JTextField();
            DXDN1 = new javax.swing.JLabel();
            DXDN2 = new javax.swing.JLabel();
            DXDN3 = new javax.swing.JLabel();
            jPanel137 = new javax.swing.JPanel();
            FDN4 = new com.toedter.calendar.JDateChooser();
            rbt4 = new javax.swing.JRadioButton();
            DXDN4 = new javax.swing.JLabel();
            FUADN4 = new javax.swing.JTextField();
            jPanel138 = new javax.swing.JPanel();
            FDN5 = new com.toedter.calendar.JDateChooser();
            rbt5 = new javax.swing.JRadioButton();
            DXDN5 = new javax.swing.JLabel();
            FUADN5 = new javax.swing.JTextField();
            jPanel139 = new javax.swing.JPanel();
            FDN6 = new com.toedter.calendar.JDateChooser();
            rbt6 = new javax.swing.JRadioButton();
            DXDN6 = new javax.swing.JLabel();
            FUADN6 = new javax.swing.JTextField();
            LEYENDA2 = new javax.swing.JPanel();
            jPanel141 = new javax.swing.JPanel();
            jPanel142 = new javax.swing.JPanel();
            jLabel137 = new javax.swing.JLabel();
            jPanel143 = new javax.swing.JPanel();
            jLabel138 = new javax.swing.JLabel();
            jPanel144 = new javax.swing.JPanel();
            jLabel139 = new javax.swing.JLabel();
            jLabel140 = new javax.swing.JLabel();
            CCDR3A2 = new javax.swing.JPanel();
            jPanel129 = new javax.swing.JPanel();
            jLabel125 = new javax.swing.JLabel();
            jPanel130 = new javax.swing.JPanel();
            FDN7 = new com.toedter.calendar.JDateChooser();
            rbt7 = new javax.swing.JRadioButton();
            jPanel131 = new javax.swing.JPanel();
            FDN8 = new com.toedter.calendar.JDateChooser();
            rbt8 = new javax.swing.JRadioButton();
            jPanel132 = new javax.swing.JPanel();
            FDN9 = new com.toedter.calendar.JDateChooser();
            rbt9 = new javax.swing.JRadioButton();
            FUADN9 = new javax.swing.JTextField();
            FUADN7 = new javax.swing.JTextField();
            FUADN8 = new javax.swing.JTextField();
            jPanel140 = new javax.swing.JPanel();
            FDN10 = new com.toedter.calendar.JDateChooser();
            rbt10 = new javax.swing.JRadioButton();
            FUADN10 = new javax.swing.JTextField();
            DXDN7 = new javax.swing.JLabel();
            DXDN8 = new javax.swing.JLabel();
            DXDN9 = new javax.swing.JLabel();
            DXDN10 = new javax.swing.JLabel();
            lblId = new javax.swing.JLabel();
            Opciones = new javax.swing.JPanel();
            jPanel28 = new javax.swing.JPanel();
            btnEditar = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            btnCancelar = new javax.swing.JButton();
            mensaje = new javax.swing.JPanel();
            men = new javax.swing.JLabel();
            b = new javax.swing.JButton();
            b1 = new javax.swing.JButton();
            jPanel26 = new javax.swing.JPanel();
            jLabel10 = new javax.swing.JLabel();
            jPanel27 = new javax.swing.JPanel();
            jLabel13 = new javax.swing.JLabel();
            lblPorcentajeV = new javax.swing.JLabel();
            jPanel2 = new javax.swing.JPanel();
            lblNina = new javax.swing.JLabel();
            lblNino = new javax.swing.JLabel();

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

            DN.setBackground(new java.awt.Color(255, 255, 255));

            CCDM13.setBackground(new java.awt.Color(204, 204, 204));

            jPanel133.setBackground(new java.awt.Color(102, 102, 102));

            jLabel129.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel129.setForeground(new java.awt.Color(255, 255, 255));
            jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel129.setText("DIAGNOSTICO NUTRICIONAL");

            javax.swing.GroupLayout jPanel133Layout = new javax.swing.GroupLayout(jPanel133);
            jPanel133.setLayout(jPanel133Layout);
            jPanel133Layout.setHorizontalGroup(
                jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel133Layout.createSequentialGroup()
                    .addComponent(jLabel129, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel133Layout.setVerticalGroup(
                jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel129)
            );

            jPanel134.setBackground(new java.awt.Color(153, 153, 153));

            FDN1.setBackground(new java.awt.Color(204, 204, 204));
            FDN1.setDateFormatString("dd/MM/yyyy");
            FDN1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(rbt1);
            rbt1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            rbt1.setForeground(new java.awt.Color(255, 255, 255));
            rbt1.setText("1º");
            rbt1.setContentAreaFilled(false);
            rbt1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            rbt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            rbt1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    rbt1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel134Layout = new javax.swing.GroupLayout(jPanel134);
            jPanel134.setLayout(jPanel134Layout);
            jPanel134Layout.setHorizontalGroup(
                jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel134Layout.setVerticalGroup(
                jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel134Layout.createSequentialGroup()
                    .addComponent(rbt1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FDN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel135.setBackground(new java.awt.Color(153, 153, 153));

            FDN2.setBackground(new java.awt.Color(204, 204, 204));
            FDN2.setDateFormatString("dd/MM/yyyy");
            FDN2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(rbt2);
            rbt2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            rbt2.setForeground(new java.awt.Color(255, 255, 255));
            rbt2.setText("2º");
            rbt2.setContentAreaFilled(false);
            rbt2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            rbt2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            rbt2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    rbt2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel135Layout = new javax.swing.GroupLayout(jPanel135);
            jPanel135.setLayout(jPanel135Layout);
            jPanel135Layout.setHorizontalGroup(
                jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel135Layout.setVerticalGroup(
                jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel135Layout.createSequentialGroup()
                    .addComponent(rbt2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FDN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel136.setBackground(new java.awt.Color(153, 153, 153));

            FDN3.setBackground(new java.awt.Color(204, 204, 204));
            FDN3.setDateFormatString("dd/MM/yyyy");
            FDN3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(rbt3);
            rbt3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            rbt3.setForeground(new java.awt.Color(255, 255, 255));
            rbt3.setText("3º");
            rbt3.setContentAreaFilled(false);
            rbt3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            rbt3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            rbt3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    rbt3ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel136Layout = new javax.swing.GroupLayout(jPanel136);
            jPanel136.setLayout(jPanel136Layout);
            jPanel136Layout.setHorizontalGroup(
                jPanel136Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDN3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbt3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel136Layout.setVerticalGroup(
                jPanel136Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel136Layout.createSequentialGroup()
                    .addComponent(rbt3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FDN3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXDN1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDN1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDN1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDN1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDN1MouseClicked(evt);
                }
            });

            DXDN2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDN2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDN2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDN2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDN2.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDN2MouseClicked(evt);
                }
            });

            DXDN3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDN3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDN3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDN3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDN3.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDN3MouseClicked(evt);
                }
            });

            jPanel137.setBackground(new java.awt.Color(153, 153, 153));

            FDN4.setBackground(new java.awt.Color(204, 204, 204));
            FDN4.setDateFormatString("dd/MM/yyyy");
            FDN4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(rbt4);
            rbt4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            rbt4.setForeground(new java.awt.Color(255, 255, 255));
            rbt4.setText("4º");
            rbt4.setContentAreaFilled(false);
            rbt4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            rbt4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            rbt4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    rbt4ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel137Layout = new javax.swing.GroupLayout(jPanel137);
            jPanel137.setLayout(jPanel137Layout);
            jPanel137Layout.setHorizontalGroup(
                jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDN4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbt4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel137Layout.setVerticalGroup(
                jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel137Layout.createSequentialGroup()
                    .addComponent(rbt4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FDN4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXDN4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDN4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDN4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDN4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDN4.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDN4MouseClicked(evt);
                }
            });

            jPanel138.setBackground(new java.awt.Color(153, 153, 153));

            FDN5.setBackground(new java.awt.Color(204, 204, 204));
            FDN5.setDateFormatString("dd/MM/yyyy");
            FDN5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(rbt5);
            rbt5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            rbt5.setForeground(new java.awt.Color(255, 255, 255));
            rbt5.setText("5º");
            rbt5.setContentAreaFilled(false);
            rbt5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            rbt5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            rbt5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    rbt5ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel138Layout = new javax.swing.GroupLayout(jPanel138);
            jPanel138.setLayout(jPanel138Layout);
            jPanel138Layout.setHorizontalGroup(
                jPanel138Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDN5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbt5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel138Layout.setVerticalGroup(
                jPanel138Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel138Layout.createSequentialGroup()
                    .addComponent(rbt5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FDN5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXDN5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDN5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDN5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDN5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDN5.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDN5MouseClicked(evt);
                }
            });

            jPanel139.setBackground(new java.awt.Color(153, 153, 153));

            FDN6.setBackground(new java.awt.Color(204, 204, 204));
            FDN6.setDateFormatString("dd/MM/yyyy");
            FDN6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(rbt6);
            rbt6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            rbt6.setForeground(new java.awt.Color(255, 255, 255));
            rbt6.setText("6º");
            rbt6.setContentAreaFilled(false);
            rbt6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            rbt6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            rbt6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    rbt6ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel139Layout = new javax.swing.GroupLayout(jPanel139);
            jPanel139.setLayout(jPanel139Layout);
            jPanel139Layout.setHorizontalGroup(
                jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDN6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbt6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel139Layout.setVerticalGroup(
                jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel139Layout.createSequentialGroup()
                    .addComponent(rbt6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FDN6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXDN6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDN6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDN6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDN6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDN6.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDN6MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout CCDM13Layout = new javax.swing.GroupLayout(CCDM13);
            CCDM13.setLayout(CCDM13Layout);
            CCDM13Layout.setHorizontalGroup(
                CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel133, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDM13Layout.createSequentialGroup()
                    .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel134, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUADN1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXDN1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel135, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUADN2)
                        .addComponent(DXDN2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUADN3)
                        .addComponent(jPanel136, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXDN3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUADN4)
                        .addComponent(jPanel137, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXDN4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUADN5)
                        .addComponent(jPanel138, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXDN5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUADN6)
                        .addComponent(jPanel139, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXDN6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );
            CCDM13Layout.setVerticalGroup(
                CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDM13Layout.createSequentialGroup()
                    .addComponent(jPanel133, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDM13Layout.createSequentialGroup()
                            .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel136, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel135, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jPanel134, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(DXDN1)
                                .addComponent(DXDN2)
                                .addComponent(DXDN3))
                            .addGap(0, 0, 0)
                            .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(FUADN3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUADN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUADN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(CCDM13Layout.createSequentialGroup()
                            .addComponent(jPanel138, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXDN5)
                            .addGap(0, 0, 0)
                            .addComponent(FUADN5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCDM13Layout.createSequentialGroup()
                            .addComponent(jPanel137, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXDN4)
                            .addGap(0, 0, 0)
                            .addComponent(FUADN4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCDM13Layout.createSequentialGroup()
                            .addComponent(jPanel139, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXDN6)
                            .addGap(0, 0, 0)
                            .addComponent(FUADN6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            );

            LEYENDA2.setBackground(new java.awt.Color(204, 204, 204));

            jPanel141.setBackground(new java.awt.Color(153, 153, 153));

            javax.swing.GroupLayout jPanel141Layout = new javax.swing.GroupLayout(jPanel141);
            jPanel141.setLayout(jPanel141Layout);
            jPanel141Layout.setHorizontalGroup(
                jPanel141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );
            jPanel141Layout.setVerticalGroup(
                jPanel141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 20, Short.MAX_VALUE)
            );

            jPanel142.setBackground(new java.awt.Color(153, 153, 153));

            jLabel137.setBackground(new java.awt.Color(153, 153, 153));
            jLabel137.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel137.setForeground(new java.awt.Color(255, 255, 255));
            jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel137.setText("Nº CONRTROL");

            javax.swing.GroupLayout jPanel142Layout = new javax.swing.GroupLayout(jPanel142);
            jPanel142.setLayout(jPanel142Layout);
            jPanel142Layout.setHorizontalGroup(
                jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel142Layout.createSequentialGroup()
                    .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 26, Short.MAX_VALUE))
            );
            jPanel142Layout.setVerticalGroup(
                jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel142Layout.createSequentialGroup()
                    .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel143.setBackground(new java.awt.Color(204, 204, 204));

            jLabel138.setForeground(new java.awt.Color(255, 255, 255));
            jLabel138.setText("FECHA");

            javax.swing.GroupLayout jPanel143Layout = new javax.swing.GroupLayout(jPanel143);
            jPanel143.setLayout(jPanel143Layout);
            jPanel143Layout.setHorizontalGroup(
                jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel143Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel143Layout.setVerticalGroup(
                jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel143Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel138))
            );

            jPanel144.setBackground(new java.awt.Color(153, 153, 153));

            jLabel139.setForeground(new java.awt.Color(255, 255, 255));
            jLabel139.setText("DX");

            javax.swing.GroupLayout jPanel144Layout = new javax.swing.GroupLayout(jPanel144);
            jPanel144.setLayout(jPanel144Layout);
            jPanel144Layout.setHorizontalGroup(
                jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel144Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel144Layout.setVerticalGroup(
                jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel139, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
            );

            jLabel140.setForeground(new java.awt.Color(255, 255, 255));
            jLabel140.setText("FUA");

            javax.swing.GroupLayout LEYENDA2Layout = new javax.swing.GroupLayout(LEYENDA2);
            LEYENDA2.setLayout(LEYENDA2Layout);
            LEYENDA2Layout.setHorizontalGroup(
                LEYENDA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel141, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(LEYENDA2Layout.createSequentialGroup()
                    .addGroup(LEYENDA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel142, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel143, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel144, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(LEYENDA2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            LEYENDA2Layout.setVerticalGroup(
                LEYENDA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LEYENDA2Layout.createSequentialGroup()
                    .addComponent(jPanel141, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel142, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel143, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(jPanel144, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            CCDR3A2.setBackground(new java.awt.Color(204, 204, 204));

            jPanel129.setBackground(new java.awt.Color(102, 102, 102));

            jLabel125.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel125.setForeground(new java.awt.Color(255, 255, 255));
            jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel125.setText("DIAGNOSTICO NUTRICIONAL");

            javax.swing.GroupLayout jPanel129Layout = new javax.swing.GroupLayout(jPanel129);
            jPanel129.setLayout(jPanel129Layout);
            jPanel129Layout.setHorizontalGroup(
                jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel129Layout.createSequentialGroup()
                    .addComponent(jLabel125, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel129Layout.setVerticalGroup(
                jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel125)
            );

            jPanel130.setBackground(new java.awt.Color(153, 153, 153));

            FDN7.setBackground(new java.awt.Color(204, 204, 204));
            FDN7.setDateFormatString("dd/MM/yyyy");
            FDN7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(rbt7);
            rbt7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            rbt7.setForeground(new java.awt.Color(255, 255, 255));
            rbt7.setText("7º");
            rbt7.setContentAreaFilled(false);
            rbt7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            rbt7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            rbt7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    rbt7ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel130Layout = new javax.swing.GroupLayout(jPanel130);
            jPanel130.setLayout(jPanel130Layout);
            jPanel130Layout.setHorizontalGroup(
                jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDN7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbt7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel130Layout.setVerticalGroup(
                jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel130Layout.createSequentialGroup()
                    .addComponent(rbt7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FDN7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel131.setBackground(new java.awt.Color(153, 153, 153));

            FDN8.setBackground(new java.awt.Color(204, 204, 204));
            FDN8.setDateFormatString("dd/MM/yyyy");
            FDN8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(rbt8);
            rbt8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            rbt8.setForeground(new java.awt.Color(255, 255, 255));
            rbt8.setText("8º");
            rbt8.setContentAreaFilled(false);
            rbt8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            rbt8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            rbt8.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    rbt8ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel131Layout = new javax.swing.GroupLayout(jPanel131);
            jPanel131.setLayout(jPanel131Layout);
            jPanel131Layout.setHorizontalGroup(
                jPanel131Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDN8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbt8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel131Layout.setVerticalGroup(
                jPanel131Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel131Layout.createSequentialGroup()
                    .addComponent(rbt8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FDN8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel132.setBackground(new java.awt.Color(153, 153, 153));

            FDN9.setBackground(new java.awt.Color(204, 204, 204));
            FDN9.setDateFormatString("dd/MM/yyyy");
            FDN9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(rbt9);
            rbt9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            rbt9.setForeground(new java.awt.Color(255, 255, 255));
            rbt9.setText("9º");
            rbt9.setContentAreaFilled(false);
            rbt9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            rbt9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            rbt9.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    rbt9ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel132Layout = new javax.swing.GroupLayout(jPanel132);
            jPanel132.setLayout(jPanel132Layout);
            jPanel132Layout.setHorizontalGroup(
                jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDN9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbt9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel132Layout.setVerticalGroup(
                jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel132Layout.createSequentialGroup()
                    .addComponent(rbt9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FDN9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel140.setBackground(new java.awt.Color(153, 153, 153));

            FDN10.setBackground(new java.awt.Color(204, 204, 204));
            FDN10.setDateFormatString("dd/MM/yyyy");
            FDN10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            buttonGroup1.add(rbt10);
            rbt10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            rbt10.setForeground(new java.awt.Color(255, 255, 255));
            rbt10.setText("10º");
            rbt10.setContentAreaFilled(false);
            rbt10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            rbt10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            rbt10.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    rbt10ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel140Layout = new javax.swing.GroupLayout(jPanel140);
            jPanel140.setLayout(jPanel140Layout);
            jPanel140Layout.setHorizontalGroup(
                jPanel140Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDN10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbt10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel140Layout.setVerticalGroup(
                jPanel140Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel140Layout.createSequentialGroup()
                    .addComponent(rbt10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FDN10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXDN7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDN7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDN7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDN7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDN7.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDN7MouseClicked(evt);
                }
            });

            DXDN8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDN8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDN8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDN8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDN8.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDN8MouseClicked(evt);
                }
            });

            DXDN9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDN9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDN9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDN9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDN9.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDN9MouseClicked(evt);
                }
            });

            DXDN10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDN10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDN10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDN10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDN10.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDN10MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout CCDR3A2Layout = new javax.swing.GroupLayout(CCDR3A2);
            CCDR3A2.setLayout(CCDR3A2Layout);
            CCDR3A2Layout.setHorizontalGroup(
                CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel129, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR3A2Layout.createSequentialGroup()
                    .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel130, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUADN7, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXDN7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel131, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUADN8)
                        .addComponent(DXDN8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUADN9)
                        .addComponent(jPanel132, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXDN9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel140, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUADN10)
                        .addComponent(DXDN10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );
            CCDR3A2Layout.setVerticalGroup(
                CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR3A2Layout.createSequentialGroup()
                    .addComponent(jPanel129, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel132, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel131, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel140, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel130, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXDN7)
                        .addComponent(DXDN8)
                        .addComponent(DXDN9)
                        .addComponent(DXDN10))
                    .addGap(0, 0, 0)
                    .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FUADN9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUADN7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUADN8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUADN10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            lblId.setText("jLabel1");

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
                    .addContainerGap(895, Short.MAX_VALUE))
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
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            OpcionesLayout.setVerticalGroup(
                OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(OpcionesLayout.createSequentialGroup()
                    .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );

            jPanel26.setBackground(new java.awt.Color(50, 151, 219));
            jPanel26.setPreferredSize(new java.awt.Dimension(83, 45));

            jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
            jLabel10.setForeground(new java.awt.Color(255, 255, 255));
            jLabel10.setText("Diagnóstico Nutricional");

            jPanel27.setBackground(new java.awt.Color(41, 127, 184));

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
            lblPorcentajeV.setForeground(new java.awt.Color(255, 255, 255));
            lblPorcentajeV.setText("100 %  Completado");
            lblPorcentajeV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

            jPanel2.setBackground(new java.awt.Color(41, 127, 184));
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

            javax.swing.GroupLayout DNLayout = new javax.swing.GroupLayout(DN);
            DN.setLayout(DNLayout);
            DNLayout.setHorizontalGroup(
                DNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DNLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addGroup(DNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(DNLayout.createSequentialGroup()
                            .addComponent(LEYENDA2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(CCDM13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(DNLayout.createSequentialGroup()
                            .addComponent(CCDR3A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblId)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 1423, Short.MAX_VALUE)
            );
            DNLayout.setVerticalGroup(
                DNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DNLayout.createSequentialGroup()
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(50, 50, 50)
                    .addGroup(DNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(CCDM13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LEYENDA2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addGroup(DNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(CCDR3A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblId))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Opciones, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 1423, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 445, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void rbt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt1ActionPerformed
        if(FDN1.getDate()==null){
            if(rbt1.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDN1.setEnabled(true);
                FDN1.setEnabled(true);
                fecha=FDN1;
                fua = FUADN1;
                cie10=DXDN1;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            rbt1.setEnabled(false);
        }
    }//GEN-LAST:event_rbt1ActionPerformed

    private void rbt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt2ActionPerformed
        if(FDN2.getDate()==null){
            if(rbt2.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDN2.setEnabled(true);
                FDN2.setEnabled(true);
                fecha=FDN2;
                fua = FUADN2;
                cie10=DXDN2;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            rbt2.setEnabled(false);
        }
    }//GEN-LAST:event_rbt2ActionPerformed

    private void rbt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt3ActionPerformed
        if(FDN3.getDate()==null){
            if(rbt3.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDN3.setEnabled(true);
                FDN3.setEnabled(true);
                fecha=FDN3;
                fua = FUADN3;
                cie10=DXDN3;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            rbt3.setEnabled(false);
        }
    }//GEN-LAST:event_rbt3ActionPerformed

    private void rbt4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt4ActionPerformed
        if(FDN4.getDate()==null){
            if(rbt4.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDN4.setEnabled(true);
                FDN4.setEnabled(true);
                fecha=FDN4;
                fua = FUADN4;
                cie10=DXDN4;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            rbt4.setEnabled(false);
        }
    }//GEN-LAST:event_rbt4ActionPerformed

    private void rbt5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt5ActionPerformed
        if(FDN5.getDate()==null){
            if(rbt5.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDN5.setEnabled(true);
                FDN5.setEnabled(true);
                fecha=FDN5;
                fua = FUADN5;
                cie10=DXDN5;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            rbt5.setEnabled(false);
        }
    }//GEN-LAST:event_rbt5ActionPerformed

    private void rbt6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt6ActionPerformed
        if(FDN6.getDate()==null){
            if(rbt6.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDN6.setEnabled(true);
                FDN6.setEnabled(true);
                fecha=FDN6;
                fua = FUADN6;
                cie10=DXDN6;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            rbt6.setEnabled(false);
        }
    }//GEN-LAST:event_rbt6ActionPerformed

    private void rbt8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt8ActionPerformed
        if(FDN8.getDate()==null){
            if(rbt8.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDN8.setEnabled(true);
                FDN8.setEnabled(true);
                fecha=FDN8;
                fua = FUADN8;
                cie10=DXDN8;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            rbt8.setEnabled(false);
        }
    }//GEN-LAST:event_rbt8ActionPerformed

    private void rbt9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt9ActionPerformed
        if(FDN9.getDate()==null){
            if(rbt9.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDN9.setEnabled(true);
                FDN9.setEnabled(true);
                fecha=FDN9;
                fua = FUADN9;
                cie10=DXDN9;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            rbt9.setEnabled(false);
        }
    }//GEN-LAST:event_rbt9ActionPerformed

    private void rbt10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt10ActionPerformed
       if(FDN10.getDate()==null){
            if(rbt10.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDN10.setEnabled(true);
                FDN10.setEnabled(true);
                fecha=FDN10;
                fua = FUADN10;
                cie10=DXDN10;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            rbt1.setEnabled(false);
        }
    }//GEN-LAST:event_rbt10ActionPerformed

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
        fua.setText("");
        cie10.setText("");
        fecha.setDate(null);
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
        ConsultorioExtRsDiagnosticoNutricional DNBUSCAR = new ConsultorioExtRsDiagnosticoNutricional();
        DNBUSCAR.cargarDatosCie10(txtBuscarCie10.getText(), tbCiePresun);
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

    private void DXDN1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDN1MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDN1MouseClicked

    private void DXDN2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDN2MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDN2MouseClicked

    private void DXDN3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDN3MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDN3MouseClicked

    private void DXDN4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDN4MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDN4MouseClicked

    private void DXDN5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDN5MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDN5MouseClicked

    private void DXDN6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDN6MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDN6MouseClicked

    private void DXDN7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDN7MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDN7MouseClicked

    private void DXDN8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDN8MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDN8MouseClicked

    private void DXDN9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDN9MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDN9MouseClicked

    private void DXDN10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDN10MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDN10MouseClicked

    private void rbt7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt7ActionPerformed
        if(FDN7.getDate()==null){
            if(rbt7.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDN7.setEnabled(true);
                FDN7.setEnabled(true);
                fecha=FDN7;
                fua = FUADN7;
                cie10=DXDN7;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            rbt7.setEnabled(false);
        }
    }//GEN-LAST:event_rbt7ActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        RegistroSeguimiento.jTabbedPane1.setSelectedIndex(2);
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CCDM13;
    private javax.swing.JPanel CCDR3A2;
    private javax.swing.JPanel DN;
    public static javax.swing.JLabel DXDN1;
    public static javax.swing.JLabel DXDN10;
    public static javax.swing.JLabel DXDN2;
    public static javax.swing.JLabel DXDN3;
    public static javax.swing.JLabel DXDN4;
    public static javax.swing.JLabel DXDN5;
    public static javax.swing.JLabel DXDN6;
    public static javax.swing.JLabel DXDN7;
    public static javax.swing.JLabel DXDN8;
    public static javax.swing.JLabel DXDN9;
    public static com.toedter.calendar.JDateChooser FDN1;
    public static com.toedter.calendar.JDateChooser FDN10;
    public static com.toedter.calendar.JDateChooser FDN2;
    public static com.toedter.calendar.JDateChooser FDN3;
    public static com.toedter.calendar.JDateChooser FDN4;
    public static com.toedter.calendar.JDateChooser FDN5;
    public static com.toedter.calendar.JDateChooser FDN6;
    public static com.toedter.calendar.JDateChooser FDN7;
    public static com.toedter.calendar.JDateChooser FDN8;
    public static com.toedter.calendar.JDateChooser FDN9;
    public static javax.swing.JTextField FUADN1;
    public static javax.swing.JTextField FUADN10;
    public static javax.swing.JTextField FUADN2;
    public static javax.swing.JTextField FUADN3;
    public static javax.swing.JTextField FUADN4;
    public static javax.swing.JTextField FUADN5;
    public static javax.swing.JTextField FUADN6;
    public static javax.swing.JTextField FUADN7;
    public static javax.swing.JTextField FUADN8;
    public static javax.swing.JTextField FUADN9;
    private javax.swing.JDialog FrmCie10;
    private javax.swing.JPanel LEYENDA2;
    private javax.swing.JPanel Opciones;
    private javax.swing.JLabel T7;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel10;
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
    private javax.swing.JPanel jPanel141;
    private javax.swing.JPanel jPanel142;
    private javax.swing.JPanel jPanel143;
    private javax.swing.JPanel jPanel144;
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
    private javax.swing.JRadioButton rbt1;
    private javax.swing.JRadioButton rbt10;
    private javax.swing.JRadioButton rbt2;
    private javax.swing.JRadioButton rbt3;
    private javax.swing.JRadioButton rbt4;
    private javax.swing.JRadioButton rbt5;
    private javax.swing.JRadioButton rbt6;
    private javax.swing.JRadioButton rbt7;
    private javax.swing.JRadioButton rbt8;
    private javax.swing.JRadioButton rbt9;
    private javax.swing.JTable tbCiePresun;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscarCie10;
    // End of variables declaration//GEN-END:variables
}
