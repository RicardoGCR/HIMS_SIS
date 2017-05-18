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
import modelos.ConsultorioEx.ConsultorioExtRsDiagnosticoDesarrollo;

/**
 *
 * @author MYS1
 */
public class RSAIDD extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
private Dimension DimensionBarra = null;
byte tg;
byte tge;
JDateChooser fecha;
JTextField fua;
JLabel cie10;
ConsultorioExtRsDiagnosticoDesarrollo DD01 = new ConsultorioExtRsDiagnosticoDesarrollo();
    /**
     * Creates new form RSAIDD
     */
    public RSAIDD() {
        initComponents();
        QuitarLaBarraTitulo();
        habilitarCampos(false);
        habilitarCampos(false);
        mensaje1.setVisible(false);
        FrmCie10.setLocationRelativeTo(null);//en el centro
        FrmCie10.getContentPane().setBackground(new Color(0,153,102));
        Botones(false);
        ConsultorioExtRsDiagnosticoDesarrollo CCDBUSCAR = new ConsultorioExtRsDiagnosticoDesarrollo();
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
        FDD1.setEnabled(opcion);
        FDD2.setEnabled(opcion);
        FDD3.setEnabled(opcion);
        FDD4.setEnabled(opcion);
        FDD5.setEnabled(opcion);
        FDD6.setEnabled(opcion);
        FDD7.setEnabled(opcion);
        FDD8.setEnabled(opcion);
        FDD9.setEnabled(opcion);
        FDD10.setEnabled(opcion);
        FDD11.setEnabled(opcion);
        FDD12.setEnabled(opcion);
        DXDD1.setEnabled(opcion);
        DXDD2.setEnabled(opcion);
        DXDD3.setEnabled(opcion);
        DXDD4.setEnabled(opcion);
        DXDD5.setEnabled(opcion);
        DXDD6.setEnabled(opcion);
        DXDD7.setEnabled(opcion);
        DXDD8.setEnabled(opcion);
        DXDD9.setEnabled(opcion);
        DXDD10.setEnabled(opcion);
        DXDD11.setEnabled(opcion);
        DXDD12.setEnabled(opcion);
        
        FUADD1.setEnabled(opcion);
        FUADD2.setEnabled(opcion);
        FUADD3.setEnabled(opcion);
        FUADD4.setEnabled(opcion);
        FUADD5.setEnabled(opcion);
        FUADD6.setEnabled(opcion);
        FUADD7.setEnabled(opcion);
        FUADD8.setEnabled(opcion);
        FUADD9.setEnabled(opcion);
        FUADD10.setEnabled(opcion);
        FUADD11.setEnabled(opcion);
        FUADD12.setEnabled(opcion);
    }
    
    public void habilitarRadio(boolean opcion){
      RDD1.setEnabled(opcion);  
      RDD2.setEnabled(opcion);
      RDD3.setEnabled(opcion);  
      RDD4.setEnabled(opcion);  
      RDD5.setEnabled(opcion);  
      RDD6.setEnabled(opcion);   
      RDD7.setEnabled(opcion);  
      RDD8.setEnabled(opcion);  
      RDD9.setEnabled(opcion);  
      RDD10.setEnabled(opcion); 
      RDD11.setEnabled(opcion);  
      RDD12.setEnabled(opcion);  
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
                           mensaje1.setVisible(true);
                           mensaje1.setBackground(new Color(255,91,70)); 
                           men.setText("Ingrese una fecha correcta");
                           b.setVisible(false);
                           b1.setVisible(false); 
         }
        
        return fecha;
    }
    
    public void validaRegistro(int rs_id){
        try {
            PreparedStatement cmd = DD01.getCn().prepareStatement("SELECT RS_ID FROM CONSULTORIO_EXT_RS_DIAGNOSTICO_DESARROLLO WHERE RS_ID ='"+rs_id+"'");
            ResultSet res = cmd.executeQuery();
            if(res.next()){ // si existe
                Modificar(fecha);
            }else { // no existe
                Guardar(fecha);
            }
        } catch (Exception e) {
            mensaje1.setVisible(true);
            mensaje1.setBackground(new Color(255,91,70)); 
            men.setText("Ocurrio un error, verifique");
            b.setVisible(false);
            b1.setVisible(false);
        }
    }
    
    public void Guardar(JDateChooser fecha){
        if(fecha.getDate()==null){
            mensaje1.setVisible(true);
            mensaje1.setBackground(new Color(255,91,70)); 
            men.setText("Ingrese una fecha valida");
            b.setVisible(false);
            b1.setVisible(false);
        } else {
    ConsultorioExtRsDiagnosticoDesarrollo CXRsDN  = new ConsultorioExtRsDiagnosticoDesarrollo();
    ConsultorioExtRsDiagnosticoDesarrollo CXRsDN2 = new ConsultorioExtRsDiagnosticoDesarrollo();
    try {
                 
            CXRsDN.setRs_id(Integer.parseInt(lblId.getText()));
            if(FDD1.getDate()!=null){
                CXRsDN.setDn1Fecha(determinarFecha(FDD1));  
                CXRsDN.setDn1Cie10(DXDD1.getText());
                CXRsDN.setDn1Fua(FUADD1.getText());
            }
            
            if(FDD2.getDate()!=null){
                CXRsDN.setDn2Fecha(determinarFecha(FDD2));  
                CXRsDN.setDn2Cie10(DXDD2.getText());
                CXRsDN.setDn2Fua(FUADD2.getText());
            }
             
            if(FDD3.getDate()!=null){
                CXRsDN.setDn3Fecha(determinarFecha(FDD3));  
                CXRsDN.setDn3Cie10(DXDD3.getText());
                CXRsDN.setDn3Fua(FUADD3.getText());
            }
             
            if(FDD4.getDate()!=null){
                CXRsDN.setDn4Fecha(determinarFecha(FDD4));  
                CXRsDN.setDn4Cie10(DXDD4.getText());
                CXRsDN.setDn4Fua(FUADD4.getText());
            }
              
            if(FDD5.getDate()!=null){
                CXRsDN.setDn5Fecha(determinarFecha(FDD5));  
                CXRsDN.setDn5Cie10(DXDD5.getText());
                CXRsDN.setDn5Fua(FUADD5.getText());
            }
               
            if(FDD6.getDate()!=null){
                CXRsDN.setDn6Fecha(determinarFecha(FDD6));  
                CXRsDN.setDn6Cie10(DXDD6.getText());
                CXRsDN.setDn6Fua(FUADD6.getText());
            }
                
            if(FDD7.getDate()!=null){
                CXRsDN.setDn1Fecha(determinarFecha(FDD7));  
                CXRsDN.setDn1Cie10(DXDD7.getText());
                CXRsDN.setDn1Fua(FUADD7.getText());
            }
                 
            if(FDD8.getDate()!=null){
                CXRsDN.setDn8Fecha(determinarFecha(FDD8));  
                CXRsDN.setDn8Cie10(DXDD8.getText());
                CXRsDN.setDn8Fua(FUADD8.getText());
            }
                  
            if(FDD9.getDate()!=null){
                CXRsDN.setDn9Fecha(determinarFecha(FDD9));  
                CXRsDN.setDn9Cie10(DXDD9.getText());
                CXRsDN.setDn9Fua(FUADD9.getText());
            }
                   
            if(FDD10.getDate()!=null){
                CXRsDN.setDn10Fecha(determinarFecha(FDD10));  
                CXRsDN.setDn10Cie10(DXDD10.getText());
                CXRsDN.setDn10Fua(FUADD10.getText());
            }
            if(FDD11.getDate()!=null){
                CXRsDN.setDn10Fecha(determinarFecha(FDD11));  
                CXRsDN.setDn10Cie10(DXDD11.getText());
                CXRsDN.setDn10Fua(FUADD11.getText());
            }
            if(FDD12.getDate()!=null){
                CXRsDN.setDn10Fecha(determinarFecha(FDD12));  
                CXRsDN.setDn10Cie10(DXDD12.getText());
                CXRsDN.setDn10Fua(FUADD12.getText());
            }
            if(CXRsDN.mantenimientoRSAIDD("I")==true){
                mensaje1.setVisible(true);
                mensaje1.setBackground(new Color(33,115,70)); 
                men.setText("Datos Guardados de forma correcta");
                b.setText("OK");
                b.setVisible(true);
                b1.setVisible(false);

                btnGuardar.setEnabled(false);
                tge=1;
                CXRsDN2.ConsultoriosExtDDListar(Integer.parseInt(lblId.getText()));
                CXRsDN2.porcentajeDD(Integer.parseInt(lblId.getText()));
                habilitarCampos(false);
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
            System.out.println("Error: guardar " + e.getMessage());
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
        ConsultorioExtRsDiagnosticoDesarrollo CXRsDN  = new ConsultorioExtRsDiagnosticoDesarrollo();
        ConsultorioExtRsDiagnosticoDesarrollo CXRsDN2 = new ConsultorioExtRsDiagnosticoDesarrollo();
        try {
            
            if(FDD1.getDate()!=null){
                CXRsDN.setDn1Fecha(determinarFecha(FDD1));  
                CXRsDN.setDn1Cie10(DXDD1.getText());
                CXRsDN.setDn1Fua(FUADD1.getText());
            }
            
            if(FDD2.getDate()!=null){
                CXRsDN.setDn2Fecha(determinarFecha(FDD2));  
                CXRsDN.setDn2Cie10(DXDD2.getText());
                CXRsDN.setDn2Fua(FUADD2.getText());
            }
             
            if(FDD3.getDate()!=null){
                CXRsDN.setDn3Fecha(determinarFecha(FDD3));  
                CXRsDN.setDn3Cie10(DXDD3.getText());
                CXRsDN.setDn3Fua(FUADD3.getText());
            }
             
            if(FDD4.getDate()!=null){
                CXRsDN.setDn4Fecha(determinarFecha(FDD4));  
                CXRsDN.setDn4Cie10(DXDD4.getText());
                CXRsDN.setDn4Fua(FUADD4.getText());
            }
              
            if(FDD5.getDate()!=null){
                CXRsDN.setDn5Fecha(determinarFecha(FDD5));  
                CXRsDN.setDn5Cie10(DXDD5.getText());
                CXRsDN.setDn5Fua(FUADD5.getText());
            }
               
            if(FDD6.getDate()!=null){
                CXRsDN.setDn6Fecha(determinarFecha(FDD6));  
                CXRsDN.setDn6Cie10(DXDD6.getText());
                CXRsDN.setDn6Fua(FUADD6.getText());
            }
                
            if(FDD7.getDate()!=null){
                CXRsDN.setDn1Fecha(determinarFecha(FDD7));  
                CXRsDN.setDn1Cie10(DXDD7.getText());
                CXRsDN.setDn1Fua(FUADD7.getText());
            }
                 
            if(FDD8.getDate()!=null){
                CXRsDN.setDn8Fecha(determinarFecha(FDD8));  
                CXRsDN.setDn8Cie10(DXDD8.getText());
                CXRsDN.setDn8Fua(FUADD8.getText());
            }
                  
            if(FDD9.getDate()!=null){
                CXRsDN.setDn9Fecha(determinarFecha(FDD9));  
                CXRsDN.setDn9Cie10(DXDD9.getText());
                CXRsDN.setDn9Fua(FUADD9.getText());
            }
                   
            if(FDD10.getDate()!=null){
                CXRsDN.setDn10Fecha(determinarFecha(FDD10));  
                CXRsDN.setDn10Cie10(DXDD10.getText());
                CXRsDN.setDn10Fua(FUADD10.getText());
            }
            if(FDD11.getDate()!=null){
                CXRsDN.setDn10Fecha(determinarFecha(FDD11));  
                CXRsDN.setDn10Cie10(DXDD11.getText());
                CXRsDN.setDn10Fua(FUADD11.getText());
            }
            if(FDD12.getDate()!=null){
                CXRsDN.setDn10Fecha(determinarFecha(FDD12));  
                CXRsDN.setDn10Cie10(DXDD12.getText());
                CXRsDN.setDn10Fua(FUADD12.getText());
            }
    
            CXRsDN.setRs_id(Integer.parseInt(lblId.getText()));
            if(CXRsDN.mantenimientoRSAIDD("U")==true){
                mensaje1.setVisible(true);
                mensaje1.setBackground(new Color(33,115,70)); 
                men.setText("Datos Actualizados de forma correcta");
                b.setText("OK");
                b.setVisible(true);
                b1.setVisible(false);
                btnGuardar.setEnabled(false);
                tge=1;
                CXRsDN2.ConsultoriosExtDDListar(Integer.parseInt(lblId.getText()));
                CXRsDN2.porcentajeDD(Integer.parseInt(lblId.getText()));
                habilitarCampos(false);
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

     public void enviarDiagnosticos(JLabel cie10){
        int fila = tbCiePresun.getSelectedRow();
        FrmCie10.dispose();
        cie10.setText(String.valueOf(tbCiePresun.getValueAt(fila, 1)));
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
            FDD1 = new com.toedter.calendar.JDateChooser();
            RDD1 = new javax.swing.JRadioButton();
            jPanel151 = new javax.swing.JPanel();
            FDD2 = new com.toedter.calendar.JDateChooser();
            RDD2 = new javax.swing.JRadioButton();
            jPanel152 = new javax.swing.JPanel();
            FDD3 = new com.toedter.calendar.JDateChooser();
            RDD3 = new javax.swing.JRadioButton();
            FUADD3 = new javax.swing.JTextField();
            FUADD1 = new javax.swing.JTextField();
            FUADD2 = new javax.swing.JTextField();
            DXDD1 = new javax.swing.JLabel();
            DXDD2 = new javax.swing.JLabel();
            DXDD3 = new javax.swing.JLabel();
            jPanel153 = new javax.swing.JPanel();
            FDD4 = new com.toedter.calendar.JDateChooser();
            RDD4 = new javax.swing.JRadioButton();
            DXDD4 = new javax.swing.JLabel();
            FUADD4 = new javax.swing.JTextField();
            jPanel154 = new javax.swing.JPanel();
            FDD5 = new com.toedter.calendar.JDateChooser();
            RDD5 = new javax.swing.JRadioButton();
            DXDD5 = new javax.swing.JLabel();
            FUADD5 = new javax.swing.JTextField();
            jPanel155 = new javax.swing.JPanel();
            FDD6 = new com.toedter.calendar.JDateChooser();
            RDD6 = new javax.swing.JRadioButton();
            DXDD6 = new javax.swing.JLabel();
            FUADD6 = new javax.swing.JTextField();
            CCDM15 = new javax.swing.JPanel();
            jPanel156 = new javax.swing.JPanel();
            jLabel156 = new javax.swing.JLabel();
            jPanel157 = new javax.swing.JPanel();
            FDD7 = new com.toedter.calendar.JDateChooser();
            RDD7 = new javax.swing.JRadioButton();
            jPanel158 = new javax.swing.JPanel();
            FDD8 = new com.toedter.calendar.JDateChooser();
            RDD8 = new javax.swing.JRadioButton();
            jPanel159 = new javax.swing.JPanel();
            FDD9 = new com.toedter.calendar.JDateChooser();
            RDD9 = new javax.swing.JRadioButton();
            FUADD9 = new javax.swing.JTextField();
            FUADD7 = new javax.swing.JTextField();
            FUADD8 = new javax.swing.JTextField();
            DXDD7 = new javax.swing.JLabel();
            DXDD8 = new javax.swing.JLabel();
            DXDD9 = new javax.swing.JLabel();
            jPanel160 = new javax.swing.JPanel();
            FDD10 = new com.toedter.calendar.JDateChooser();
            RDD10 = new javax.swing.JRadioButton();
            DXDD10 = new javax.swing.JLabel();
            FUADD10 = new javax.swing.JTextField();
            jPanel161 = new javax.swing.JPanel();
            FDD11 = new com.toedter.calendar.JDateChooser();
            RDD11 = new javax.swing.JRadioButton();
            DXDD11 = new javax.swing.JLabel();
            FUADD11 = new javax.swing.JTextField();
            jPanel162 = new javax.swing.JPanel();
            FDD12 = new com.toedter.calendar.JDateChooser();
            RDD12 = new javax.swing.JRadioButton();
            DXDD12 = new javax.swing.JLabel();
            FUADD12 = new javax.swing.JTextField();
            lblId = new javax.swing.JLabel();
            jPanel26 = new javax.swing.JPanel();
            jLabel10 = new javax.swing.JLabel();
            jPanel27 = new javax.swing.JPanel();
            jLabel13 = new javax.swing.JLabel();
            lblPorcentajeV = new javax.swing.JLabel();
            jPanel2 = new javax.swing.JPanel();
            lblNina = new javax.swing.JLabel();
            lblNino = new javax.swing.JLabel();
            mensaje = new javax.swing.JPanel();
            jPanel31 = new javax.swing.JPanel();
            btnEditar = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            btnCancelar = new javax.swing.JButton();
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

            jPanel32.setBackground(new java.awt.Color(141, 68, 173));

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
            tbCiePresun.setSelectionBackground(new java.awt.Color(154, 89, 181));
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
            jLabel149.setText("DIAGNOSTICO NUTRICIONAL");

            javax.swing.GroupLayout jPanel149Layout = new javax.swing.GroupLayout(jPanel149);
            jPanel149.setLayout(jPanel149Layout);
            jPanel149Layout.setHorizontalGroup(
                jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel149Layout.createSequentialGroup()
                    .addComponent(jLabel149, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel149Layout.setVerticalGroup(
                jPanel149Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel149)
            );

            jPanel150.setBackground(new java.awt.Color(153, 153, 153));

            FDD1.setBackground(new java.awt.Color(204, 204, 204));
            FDD1.setDateFormatString("dd/MM/yyyy");
            FDD1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            RDD1.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(RDD1);
            RDD1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RDD1.setForeground(new java.awt.Color(255, 255, 255));
            RDD1.setText("1º");
            RDD1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RDD1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel150Layout = new javax.swing.GroupLayout(jPanel150);
            jPanel150.setLayout(jPanel150Layout);
            jPanel150Layout.setHorizontalGroup(
                jPanel150Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDD1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel150Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(RDD1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel150Layout.setVerticalGroup(
                jPanel150Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel150Layout.createSequentialGroup()
                    .addComponent(RDD1)
                    .addGap(3, 3, 3)
                    .addComponent(FDD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel151.setBackground(new java.awt.Color(153, 153, 153));

            FDD2.setBackground(new java.awt.Color(204, 204, 204));
            FDD2.setDateFormatString("dd/MM/yyyy");
            FDD2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            RDD2.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(RDD2);
            RDD2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RDD2.setForeground(new java.awt.Color(255, 255, 255));
            RDD2.setText("2º");
            RDD2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RDD2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel151Layout = new javax.swing.GroupLayout(jPanel151);
            jPanel151.setLayout(jPanel151Layout);
            jPanel151Layout.setHorizontalGroup(
                jPanel151Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDD2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel151Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(RDD2)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel151Layout.setVerticalGroup(
                jPanel151Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel151Layout.createSequentialGroup()
                    .addComponent(RDD2)
                    .addGap(3, 3, 3)
                    .addComponent(FDD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel152.setBackground(new java.awt.Color(153, 153, 153));

            FDD3.setBackground(new java.awt.Color(204, 204, 204));
            FDD3.setDateFormatString("dd/MM/yyyy");
            FDD3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            RDD3.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(RDD3);
            RDD3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RDD3.setForeground(new java.awt.Color(255, 255, 255));
            RDD3.setText("3º");
            RDD3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RDD3ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel152Layout = new javax.swing.GroupLayout(jPanel152);
            jPanel152.setLayout(jPanel152Layout);
            jPanel152Layout.setHorizontalGroup(
                jPanel152Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDD3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel152Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(RDD3)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel152Layout.setVerticalGroup(
                jPanel152Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel152Layout.createSequentialGroup()
                    .addComponent(RDD3)
                    .addGap(3, 3, 3)
                    .addComponent(FDD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXDD1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDD1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDD1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDD1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDD1MouseClicked(evt);
                }
            });
            DXDD1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    DXDD1KeyPressed(evt);
                }
            });

            DXDD2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDD2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDD2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDD2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDD2.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDD2MouseClicked(evt);
                }
            });

            DXDD3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDD3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDD3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDD3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDD3.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDD3MouseClicked(evt);
                }
            });

            jPanel153.setBackground(new java.awt.Color(153, 153, 153));

            FDD4.setBackground(new java.awt.Color(204, 204, 204));
            FDD4.setDateFormatString("dd/MM/yyyy");
            FDD4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            RDD4.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(RDD4);
            RDD4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RDD4.setForeground(new java.awt.Color(255, 255, 255));
            RDD4.setText("4º");
            RDD4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RDD4ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel153Layout = new javax.swing.GroupLayout(jPanel153);
            jPanel153.setLayout(jPanel153Layout);
            jPanel153Layout.setHorizontalGroup(
                jPanel153Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDD4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel153Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(RDD4)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel153Layout.setVerticalGroup(
                jPanel153Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel153Layout.createSequentialGroup()
                    .addComponent(RDD4)
                    .addGap(3, 3, 3)
                    .addComponent(FDD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXDD4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDD4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDD4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDD4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDD4.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDD4MouseClicked(evt);
                }
            });

            jPanel154.setBackground(new java.awt.Color(153, 153, 153));

            FDD5.setBackground(new java.awt.Color(204, 204, 204));
            FDD5.setDateFormatString("dd/MM/yyyy");
            FDD5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            RDD5.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(RDD5);
            RDD5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RDD5.setForeground(new java.awt.Color(255, 255, 255));
            RDD5.setText("5º");
            RDD5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RDD5ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel154Layout = new javax.swing.GroupLayout(jPanel154);
            jPanel154.setLayout(jPanel154Layout);
            jPanel154Layout.setHorizontalGroup(
                jPanel154Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDD5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel154Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(RDD5)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel154Layout.setVerticalGroup(
                jPanel154Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel154Layout.createSequentialGroup()
                    .addComponent(RDD5)
                    .addGap(3, 3, 3)
                    .addComponent(FDD5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXDD5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDD5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDD5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDD5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDD5.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDD5MouseClicked(evt);
                }
            });

            jPanel155.setBackground(new java.awt.Color(153, 153, 153));

            FDD6.setBackground(new java.awt.Color(204, 204, 204));
            FDD6.setDateFormatString("dd/MM/yyyy");
            FDD6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            RDD6.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(RDD6);
            RDD6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RDD6.setForeground(new java.awt.Color(255, 255, 255));
            RDD6.setText("6º");
            RDD6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RDD6ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel155Layout = new javax.swing.GroupLayout(jPanel155);
            jPanel155.setLayout(jPanel155Layout);
            jPanel155Layout.setHorizontalGroup(
                jPanel155Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDD6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel155Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(RDD6)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel155Layout.setVerticalGroup(
                jPanel155Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel155Layout.createSequentialGroup()
                    .addComponent(RDD6)
                    .addGap(3, 3, 3)
                    .addComponent(FDD6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXDD6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDD6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDD6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDD6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDD6.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDD6MouseClicked(evt);
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
                        .addComponent(FUADD1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXDD1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel151, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUADD2)
                        .addComponent(DXDD2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUADD3)
                        .addComponent(jPanel152, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXDD3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUADD4)
                        .addComponent(jPanel153, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXDD4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUADD5)
                        .addComponent(jPanel154, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXDD5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUADD6)
                        .addComponent(jPanel155, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXDD6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                .addComponent(DXDD1)
                                .addComponent(DXDD2)
                                .addComponent(DXDD3))
                            .addGap(0, 0, 0)
                            .addGroup(CCDM14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(FUADD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUADD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUADD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(CCDM14Layout.createSequentialGroup()
                            .addComponent(jPanel154, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXDD5)
                            .addGap(0, 0, 0)
                            .addComponent(FUADD5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCDM14Layout.createSequentialGroup()
                            .addComponent(jPanel153, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXDD4)
                            .addGap(0, 0, 0)
                            .addComponent(FUADD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCDM14Layout.createSequentialGroup()
                            .addComponent(jPanel155, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXDD6)
                            .addGap(0, 0, 0)
                            .addComponent(FUADD6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            );

            CCDM15.setBackground(new java.awt.Color(204, 204, 204));

            jPanel156.setBackground(new java.awt.Color(153, 153, 153));

            jLabel156.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel156.setForeground(new java.awt.Color(255, 255, 255));
            jLabel156.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel156.setText("DIAGNOSTICO NUTRICIONAL");

            javax.swing.GroupLayout jPanel156Layout = new javax.swing.GroupLayout(jPanel156);
            jPanel156.setLayout(jPanel156Layout);
            jPanel156Layout.setHorizontalGroup(
                jPanel156Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel156Layout.createSequentialGroup()
                    .addComponent(jLabel156, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel156Layout.setVerticalGroup(
                jPanel156Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel156)
            );

            jPanel157.setBackground(new java.awt.Color(153, 153, 153));

            FDD7.setBackground(new java.awt.Color(204, 204, 204));
            FDD7.setDateFormatString("dd/MM/yyyy");
            FDD7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            RDD7.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(RDD7);
            RDD7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RDD7.setForeground(new java.awt.Color(255, 255, 255));
            RDD7.setText("7º");
            RDD7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RDD7ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel157Layout = new javax.swing.GroupLayout(jPanel157);
            jPanel157.setLayout(jPanel157Layout);
            jPanel157Layout.setHorizontalGroup(
                jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDD7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel157Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RDD7)
                    .addGap(39, 39, 39))
            );
            jPanel157Layout.setVerticalGroup(
                jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel157Layout.createSequentialGroup()
                    .addComponent(RDD7)
                    .addGap(3, 3, 3)
                    .addComponent(FDD7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel158.setBackground(new java.awt.Color(153, 153, 153));

            FDD8.setBackground(new java.awt.Color(204, 204, 204));
            FDD8.setDateFormatString("dd/MM/yyyy");
            FDD8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            RDD8.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(RDD8);
            RDD8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RDD8.setForeground(new java.awt.Color(255, 255, 255));
            RDD8.setText("8º");
            RDD8.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RDD8ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel158Layout = new javax.swing.GroupLayout(jPanel158);
            jPanel158.setLayout(jPanel158Layout);
            jPanel158Layout.setHorizontalGroup(
                jPanel158Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDD8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel158Layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(RDD8)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel158Layout.setVerticalGroup(
                jPanel158Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel158Layout.createSequentialGroup()
                    .addComponent(RDD8)
                    .addGap(3, 3, 3)
                    .addComponent(FDD8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel159.setBackground(new java.awt.Color(153, 153, 153));

            FDD9.setBackground(new java.awt.Color(204, 204, 204));
            FDD9.setDateFormatString("dd/MM/yyyy");
            FDD9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            RDD9.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(RDD9);
            RDD9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RDD9.setForeground(new java.awt.Color(255, 255, 255));
            RDD9.setText("9º");
            RDD9.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RDD9ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel159Layout = new javax.swing.GroupLayout(jPanel159);
            jPanel159.setLayout(jPanel159Layout);
            jPanel159Layout.setHorizontalGroup(
                jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDD9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel159Layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(RDD9)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel159Layout.setVerticalGroup(
                jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel159Layout.createSequentialGroup()
                    .addComponent(RDD9)
                    .addGap(3, 3, 3)
                    .addComponent(FDD9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            FUADD8.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    FUADD8ActionPerformed(evt);
                }
            });

            DXDD7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDD7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDD7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDD7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDD7.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDD7MouseClicked(evt);
                }
            });

            DXDD8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDD8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDD8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDD8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDD8.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDD8MouseClicked(evt);
                }
            });

            DXDD9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDD9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDD9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDD9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDD9.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDD9MouseClicked(evt);
                }
            });

            jPanel160.setBackground(new java.awt.Color(153, 153, 153));

            FDD10.setBackground(new java.awt.Color(204, 204, 204));
            FDD10.setDateFormatString("dd/MM/yyyy");
            FDD10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            RDD10.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(RDD10);
            RDD10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RDD10.setForeground(new java.awt.Color(255, 255, 255));
            RDD10.setText("10º");
            RDD10.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RDD10ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel160Layout = new javax.swing.GroupLayout(jPanel160);
            jPanel160.setLayout(jPanel160Layout);
            jPanel160Layout.setHorizontalGroup(
                jPanel160Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDD10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel160Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(RDD10)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel160Layout.setVerticalGroup(
                jPanel160Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel160Layout.createSequentialGroup()
                    .addComponent(RDD10)
                    .addGap(3, 3, 3)
                    .addComponent(FDD10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXDD10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDD10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDD10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDD10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDD10.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDD10MouseClicked(evt);
                }
            });

            jPanel161.setBackground(new java.awt.Color(153, 153, 153));

            FDD11.setBackground(new java.awt.Color(204, 204, 204));
            FDD11.setDateFormatString("dd/MM/yyyy");
            FDD11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            RDD11.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(RDD11);
            RDD11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RDD11.setForeground(new java.awt.Color(255, 255, 255));
            RDD11.setText("11º");
            RDD11.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RDD11ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel161Layout = new javax.swing.GroupLayout(jPanel161);
            jPanel161.setLayout(jPanel161Layout);
            jPanel161Layout.setHorizontalGroup(
                jPanel161Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDD11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel161Layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(RDD11)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel161Layout.setVerticalGroup(
                jPanel161Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel161Layout.createSequentialGroup()
                    .addComponent(RDD11)
                    .addGap(3, 3, 3)
                    .addComponent(FDD11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXDD11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDD11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDD11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDD11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDD11.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDD11MouseClicked(evt);
                }
            });

            jPanel162.setBackground(new java.awt.Color(153, 153, 153));

            FDD12.setBackground(new java.awt.Color(204, 204, 204));
            FDD12.setDateFormatString("dd/MM/yyyy");
            FDD12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            RDD12.setBackground(new java.awt.Color(153, 153, 153));
            buttonGroup1.add(RDD12);
            RDD12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            RDD12.setForeground(new java.awt.Color(255, 255, 255));
            RDD12.setText("12º");
            RDD12.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RDD12ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel162Layout = new javax.swing.GroupLayout(jPanel162);
            jPanel162.setLayout(jPanel162Layout);
            jPanel162Layout.setHorizontalGroup(
                jPanel162Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(FDD12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel162Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(RDD12)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel162Layout.setVerticalGroup(
                jPanel162Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel162Layout.createSequentialGroup()
                    .addComponent(RDD12)
                    .addGap(3, 3, 3)
                    .addComponent(FDD12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXDD12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            DXDD12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXDD12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXDD12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXDD12.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXDD12MouseClicked(evt);
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
                        .addComponent(FUADD7, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXDD7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel158, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUADD8)
                        .addComponent(DXDD8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUADD9)
                        .addComponent(jPanel159, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXDD9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUADD10)
                        .addComponent(jPanel160, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXDD10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUADD11)
                        .addComponent(jPanel161, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXDD11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUADD12)
                        .addComponent(jPanel162, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXDD12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                .addComponent(DXDD7)
                                .addComponent(DXDD8)
                                .addComponent(DXDD9))
                            .addGap(0, 0, 0)
                            .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(FUADD9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUADD7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUADD8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(CCDM15Layout.createSequentialGroup()
                            .addComponent(jPanel161, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXDD11)
                            .addGap(0, 0, 0)
                            .addComponent(FUADD11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCDM15Layout.createSequentialGroup()
                            .addComponent(jPanel160, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXDD10)
                            .addGap(0, 0, 0)
                            .addComponent(FUADD10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCDM15Layout.createSequentialGroup()
                            .addComponent(jPanel162, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXDD12)
                            .addGap(0, 0, 0)
                            .addComponent(FUADD12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            );

            lblId.setText("jLabel1");

            jPanel26.setBackground(new java.awt.Color(154, 89, 181));
            jPanel26.setPreferredSize(new java.awt.Dimension(83, 45));

            jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
            jLabel10.setForeground(new java.awt.Color(255, 255, 255));
            jLabel10.setText("Diagnóstico De Desarrollo");

            jPanel27.setBackground(new java.awt.Color(141, 68, 173));

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

            jPanel2.setBackground(new java.awt.Color(141, 68, 173));
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

            javax.swing.GroupLayout DDLayout = new javax.swing.GroupLayout(DD);
            DD.setLayout(DDLayout);
            DDLayout.setHorizontalGroup(
                DDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DDLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(DDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(DDLayout.createSequentialGroup()
                            .addComponent(LEYENDA3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(CCDM14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(DDLayout.createSequentialGroup()
                            .addComponent(CCDM15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblId)))
                    .addContainerGap(639, Short.MAX_VALUE))
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 1494, Short.MAX_VALUE)
            );
            DDLayout.setVerticalGroup(
                DDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(DDLayout.createSequentialGroup()
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(53, 53, 53)
                    .addGroup(DDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(CCDM14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LEYENDA3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(DDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(DDLayout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(CCDM15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(DDLayout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(lblId)))
                    .addContainerGap(47, Short.MAX_VALUE))
            );

            mensaje.setBackground(new java.awt.Color(102, 102, 102));

            jPanel31.setBackground(new java.awt.Color(51, 51, 51));

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

            javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
            jPanel31.setLayout(jPanel31Layout);
            jPanel31Layout.setHorizontalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                    .addContainerGap(124, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(64, Short.MAX_VALUE)))
            );
            jPanel31Layout.setVerticalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel31Layout.createSequentialGroup()
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addContainerGap(847, Short.MAX_VALUE))
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
                    .addComponent(mensaje1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 392, Short.MAX_VALUE)
                    .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(cie10.getText()!=""){
            validaRegistro(Integer.parseInt(lblId.getText()));
        } else{
            fecha.setEnabled(true);
            //fua.setEnabled(true);
            mensaje1.setVisible(true);
            mensaje1.setBackground(new Color(255,91,70)); 
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
            mensaje1.setVisible(false);

        }

        if (tge==2){
            //            Modificar();

            btnEditar.setEnabled(false);
            ;

        }
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        mensaje1.setVisible(false);
    }//GEN-LAST:event_b1ActionPerformed

    private void RDD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD1ActionPerformed
        if(FDD1.getDate()==null){
            if(RDD1.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDD1.setEnabled(true);
                FDD1.setEnabled(true);
                fecha=FDD1;
                fua = FUADD1;
                cie10=DXDD1;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RDD1.setEnabled(false);
        }
       
    }//GEN-LAST:event_RDD1ActionPerformed

    private void RDD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD2ActionPerformed
        if(FDD2.getDate()==null){
            if(RDD2.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDD2.setEnabled(true);
                FDD2.setEnabled(true);
                fecha=FDD2;
                fua = FUADD2;
                cie10=DXDD2;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RDD2.setEnabled(false);
        }
    }//GEN-LAST:event_RDD2ActionPerformed

    private void RDD3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD3ActionPerformed
       if(FDD3.getDate()==null){
            if(RDD3.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDD3.setEnabled(true);
                FDD3.setEnabled(true);
                fecha=FDD3;
                fua = FUADD3;
                cie10=DXDD3;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RDD3.setEnabled(false);
        }
    }//GEN-LAST:event_RDD3ActionPerformed

    private void RDD4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD4ActionPerformed
        if(FDD4.getDate()==null){
            if(RDD4.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDD4.setEnabled(true);
                FDD4.setEnabled(true);
                fecha=FDD4;
                fua = FUADD4;
                cie10=DXDD4;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RDD4.setEnabled(false);
        }
    }//GEN-LAST:event_RDD4ActionPerformed

    private void RDD5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD5ActionPerformed
        if(FDD5.getDate()==null){
            if(RDD5.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDD5.setEnabled(true);
                FDD5.setEnabled(true);
                fecha=FDD5;
                fua = FUADD5;
                cie10=DXDD5;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RDD5.setEnabled(false);
        }
    }//GEN-LAST:event_RDD5ActionPerformed

    private void RDD6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD6ActionPerformed
        if(FDD6.getDate()==null){
            if(RDD6.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDD6.setEnabled(true);
                FDD6.setEnabled(true);
                fecha=FDD6;
                fua = FUADD6;
                cie10=DXDD6;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RDD6.setEnabled(false);
        }
    }//GEN-LAST:event_RDD6ActionPerformed

    private void RDD7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD7ActionPerformed
        if(FDD7.getDate()==null){
            if(RDD7.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDD7.setEnabled(true);
                FDD7.setEnabled(true);
                fecha=FDD7;
                fua = FUADD7;
                cie10=DXDD7;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RDD7.setEnabled(false);
        }
    }//GEN-LAST:event_RDD7ActionPerformed

    private void RDD8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD8ActionPerformed
        if(FDD8.getDate()==null){
            if(RDD8.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDD8.setEnabled(true);
                FDD8.setEnabled(true);
                fecha=FDD8;
                fua = FUADD8;
                cie10=DXDD8;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RDD8.setEnabled(false);
        }
    }//GEN-LAST:event_RDD8ActionPerformed

    private void RDD9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD9ActionPerformed
        if(FDD9.getDate()==null){
            if(RDD9.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDD9.setEnabled(true);
                FDD9.setEnabled(true);
                fecha=FDD9;
                fua = FUADD9;
                cie10=DXDD9;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RDD9.setEnabled(false);
        }
    }//GEN-LAST:event_RDD9ActionPerformed

    private void RDD10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD10ActionPerformed
        if(FDD10.getDate()==null){
            if(RDD10.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDD10.setEnabled(true);
                FDD10.setEnabled(true);
                fecha=FDD10;
                fua = FUADD10;
                cie10=DXDD10;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RDD10.setEnabled(false);
        }
    }//GEN-LAST:event_RDD10ActionPerformed

    private void RDD11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD11ActionPerformed
       if(FDD11.getDate()==null){
            if(RDD11.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDD11.setEnabled(true);
                FDD11.setEnabled(true);
                fecha=FDD11;
                fua = FUADD11;
                cie10=DXDD11;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RDD11.setEnabled(false);
        }
    }//GEN-LAST:event_RDD11ActionPerformed

    private void RDD12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD12ActionPerformed
        if(FDD12.getDate()==null){
            if(RDD12.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDD12.setEnabled(true);
                FDD12.setEnabled(true);
                fecha=FDD12;
                fua = FUADD12;
                cie10=DXDD12;
                fua.setText(RegistroSeguimiento.lblFua.getText());
            }
        } else {
            RDD12.setEnabled(false);
        }
    }//GEN-LAST:event_RDD12ActionPerformed

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T7MouseClicked

    private void txtBuscarCie10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCie10CaretUpdate
        ConsultorioExtRsDiagnosticoDesarrollo DDBUSCAR = new ConsultorioExtRsDiagnosticoDesarrollo();
        DDBUSCAR.cargarDatosCie10(txtBuscarCie10.getText(), tbCiePresun);
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

    private void DXDD1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DXDD1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DXDD1KeyPressed

    private void DXDD1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDD1MouseClicked
       FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDD1MouseClicked

    private void DXDD2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDD2MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDD2MouseClicked

    private void DXDD3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDD3MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDD3MouseClicked

    private void DXDD4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDD4MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDD4MouseClicked

    private void DXDD5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDD5MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDD5MouseClicked

    private void DXDD6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDD6MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDD6MouseClicked

    private void DXDD7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDD7MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDD7MouseClicked

    private void DXDD8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDD8MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDD8MouseClicked

    private void DXDD9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDD9MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDD9MouseClicked

    private void DXDD10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDD10MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDD10MouseClicked

    private void DXDD11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDD11MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDD11MouseClicked

    private void DXDD12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXDD12MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXDD12MouseClicked

    private void FUADD8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FUADD8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FUADD8ActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        RegistroSeguimiento.jTabbedPane1.setSelectedIndex(0);
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CCDM14;
    private javax.swing.JPanel CCDM15;
    private javax.swing.JPanel DD;
    public static javax.swing.JLabel DXDD1;
    public static javax.swing.JLabel DXDD10;
    public static javax.swing.JLabel DXDD11;
    public static javax.swing.JLabel DXDD12;
    public static javax.swing.JLabel DXDD2;
    public static javax.swing.JLabel DXDD3;
    public static javax.swing.JLabel DXDD4;
    public static javax.swing.JLabel DXDD5;
    public static javax.swing.JLabel DXDD6;
    public static javax.swing.JLabel DXDD7;
    public static javax.swing.JLabel DXDD8;
    public static javax.swing.JLabel DXDD9;
    public static com.toedter.calendar.JDateChooser FDD1;
    public static com.toedter.calendar.JDateChooser FDD10;
    public static com.toedter.calendar.JDateChooser FDD11;
    public static com.toedter.calendar.JDateChooser FDD12;
    public static com.toedter.calendar.JDateChooser FDD2;
    public static com.toedter.calendar.JDateChooser FDD3;
    public static com.toedter.calendar.JDateChooser FDD4;
    public static com.toedter.calendar.JDateChooser FDD5;
    public static com.toedter.calendar.JDateChooser FDD6;
    public static com.toedter.calendar.JDateChooser FDD7;
    public static com.toedter.calendar.JDateChooser FDD8;
    public static com.toedter.calendar.JDateChooser FDD9;
    public static javax.swing.JTextField FUADD1;
    public static javax.swing.JTextField FUADD10;
    public static javax.swing.JTextField FUADD11;
    public static javax.swing.JTextField FUADD12;
    public static javax.swing.JTextField FUADD2;
    public static javax.swing.JTextField FUADD3;
    public static javax.swing.JTextField FUADD4;
    public static javax.swing.JTextField FUADD5;
    public static javax.swing.JTextField FUADD6;
    public static javax.swing.JTextField FUADD7;
    public static javax.swing.JTextField FUADD8;
    public static javax.swing.JTextField FUADD9;
    private javax.swing.JDialog FrmCie10;
    private javax.swing.JPanel LEYENDA3;
    private javax.swing.JRadioButton RDD1;
    private javax.swing.JRadioButton RDD10;
    private javax.swing.JRadioButton RDD11;
    private javax.swing.JRadioButton RDD12;
    private javax.swing.JRadioButton RDD2;
    private javax.swing.JRadioButton RDD3;
    private javax.swing.JRadioButton RDD4;
    private javax.swing.JRadioButton RDD5;
    private javax.swing.JRadioButton RDD6;
    private javax.swing.JRadioButton RDD7;
    private javax.swing.JRadioButton RDD8;
    private javax.swing.JRadioButton RDD9;
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
    private javax.swing.JPanel jPanel161;
    private javax.swing.JPanel jPanel162;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JLabel lblId;
    public static javax.swing.JLabel lblNina;
    public static javax.swing.JLabel lblNino;
    public static javax.swing.JLabel lblPorcentajeV;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    private javax.swing.JPanel mensaje1;
    private javax.swing.JTable tbCiePresun;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscarCie10;
    // End of variables declaration//GEN-END:variables
}
