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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelos.ConsultorioEx.ConsultorioExtRsTamizajeNeonatal;


/**
 *
 * @author MYS1
 */
public class RSAITN extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
private Dimension DimensionBarra = null; 
byte tg;
    byte tge;
    JDateChooser fecha;
    JTextField fua;
    ConsultorioExtRsTamizajeNeonatal TN01 = new ConsultorioExtRsTamizajeNeonatal();
    /**
     * Creates new form RSAITN
     */
    public RSAITN() {
        initComponents();
        QuitarLaBarraTitulo();
        habilitarDatos(false);
        mensaje1.setVisible(false);
        Botones(false);
    }
    public void QuitarLaBarraTitulo()
    { 
    Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
    DimensionBarra = Barra.getPreferredSize(); 
    Barra.setSize(0,0); 
    Barra.setPreferredSize(new Dimension(0,0)); 
    repaint(); 
    }
    
    public void habilitarDatos(boolean opcion){
        FDD7.setEnabled(opcion);
        FDD8.setEnabled(opcion);
        FDD9.setEnabled(opcion);
        FDD10.setEnabled(opcion);
        
        FUADD7.setEnabled(opcion);
        FUADD8.setEnabled(opcion);
        FUADD9.setEnabled(opcion);
        FUADD10.setEnabled(opcion);
    }
     
    public void habilitarRadio(boolean opcion){
        RDD1.setEnabled(opcion);  
        RDD2.setEnabled(opcion);  
        RDD3.setEnabled(opcion);  
        RDD4.setEnabled(opcion);  
    }
      
    public void Botones(boolean opcion){
        btnGuardar.setEnabled(opcion);
        btnEditar.setEnabled(opcion);
        btnCancelar.setEnabled(opcion);
    }
    
    public void validaRegistro(int rs_id){
        try {
            PreparedStatement cmd = TN01.getCn().prepareStatement("SELECT RS_ID FROM CONSULTORIO_EXT_RS_TAMIZAJE_NEONATAL WHERE RS_ID ='"+rs_id+"'");
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
        mensaje1.setVisible(true);
        mensaje1.setBackground(new Color(255,91,70)); 
        men.setText("Ingrese una fecha valida");
        b.setVisible(false);
        b1.setVisible(false);
    } else {
        ConsultorioExtRsTamizajeNeonatal CXRsTN = new ConsultorioExtRsTamizajeNeonatal();
        ConsultorioExtRsTamizajeNeonatal CXRsTN2 = new ConsultorioExtRsTamizajeNeonatal();
        try {
    
            CXRsTN.setRs_id(Integer.parseInt(lblId.getText()));
            
            if(FDD7.getDate()!=null){
                CXRsTN.setTshFecha(determinarFecha(FDD7));  
                CXRsTN.setTshFua(FUADD7.getText());
             }
            
            if(FDD8.getDate()!=null){
                CXRsTN.setFcFecha(determinarFecha(FDD8));  
                CXRsTN.setFcFua(FUADD8.getText());
             }
            
            if(FDD9.getDate()!=null){
                CXRsTN.setFoFecha(determinarFecha(FDD9));  
                CXRsTN.setFoFua(FUADD9.getText());
             }
            
            if(FDD10.getDate()!=null){
                CXRsTN.setHsrFecha(determinarFecha(FDD10));  
                CXRsTN.setHsrFua(FUADD10.getText());
             }
            
            if(CXRsTN.mantenimientoRSAITN("U")==true){
                mensaje1.setVisible(true);
                mensaje1.setBackground(new Color(33,115,70)); 
                men.setText("Datos Actualizados de forma correcta");
                b.setText("OK");
                b.setVisible(true);
                b1.setVisible(false);
                btnGuardar.setEnabled(false);
                tge=1;
                CXRsTN2.ConsultoriosExtTNListar(Integer.parseInt(lblId.getText()));
                CXRsTN2.porcentajeTN(Integer.parseInt(lblId.getText()));
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
    public void Guardar(JDateChooser fecha){
        if(fecha.getDate()==null){
            mensaje1.setVisible(true);
            mensaje1.setBackground(new Color(255,91,70)); 
            men.setText("Ingrese una fecha valida");
            b.setVisible(false);
            b1.setVisible(false);
        } else {
        ConsultorioExtRsTamizajeNeonatal CXRsTN = new ConsultorioExtRsTamizajeNeonatal();
        ConsultorioExtRsTamizajeNeonatal CXRsTN2 = new ConsultorioExtRsTamizajeNeonatal();
        try {
    
            CXRsTN.setRs_id(Integer.parseInt(lblId.getText()));
            
            if(FDD7.getDate()!=null){
                CXRsTN.setTshFecha(determinarFecha(FDD7));  
                CXRsTN.setTshFua(FUADD7.getText());
             }
            
            if(FDD8.getDate()!=null){
                CXRsTN.setFcFecha(determinarFecha(FDD8));  
                CXRsTN.setFcFua(FUADD8.getText());
             }
            
            if(FDD9.getDate()!=null){
                CXRsTN.setFoFecha(determinarFecha(FDD9));  
                CXRsTN.setFoFua(FUADD9.getText());
             }
            
            if(FDD10.getDate()!=null){
                CXRsTN.setHsrFecha(determinarFecha(FDD10));  
                CXRsTN.setHsrFua(FUADD10.getText());
             }    
            
        if(CXRsTN.mantenimientoRSAITN("I")==true){
                mensaje1.setVisible(true);
                mensaje1.setBackground(new Color(33,115,70)); 
                men.setText("Datos Guardados de forma correcta");
                b.setText("OK");
                b.setVisible(true);
                b1.setVisible(false);

                btnGuardar.setEnabled(false);
                tge=1;

                CXRsTN2.ConsultoriosExtTNListar(Integer.parseInt(lblId.getText()));
                CXRsTN2.porcentajeTN(Integer.parseInt(lblId.getText()));
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
        jPanel299 = new javax.swing.JPanel();
        CCDM15 = new javax.swing.JPanel();
        jPanel157 = new javax.swing.JPanel();
        FDD7 = new com.toedter.calendar.JDateChooser();
        RDD1 = new javax.swing.JRadioButton();
        jPanel158 = new javax.swing.JPanel();
        FDD8 = new com.toedter.calendar.JDateChooser();
        RDD2 = new javax.swing.JRadioButton();
        jPanel159 = new javax.swing.JPanel();
        FDD9 = new com.toedter.calendar.JDateChooser();
        RDD3 = new javax.swing.JRadioButton();
        FUADD9 = new javax.swing.JTextField();
        FUADD7 = new javax.swing.JTextField();
        FUADD8 = new javax.swing.JTextField();
        jPanel160 = new javax.swing.JPanel();
        FDD10 = new com.toedter.calendar.JDateChooser();
        RDD4 = new javax.swing.JRadioButton();
        FUADD10 = new javax.swing.JTextField();
        mensaje = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        mensaje1 = new javax.swing.JPanel();
        men = new javax.swing.JLabel();
        b = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        lblId = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lblPorcentajeTn = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblNina = new javax.swing.JLabel();
        lblNino = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        jPanel299.setBackground(new java.awt.Color(255, 255, 255));

        CCDM15.setBackground(new java.awt.Color(204, 204, 204));

        jPanel157.setBackground(new java.awt.Color(153, 153, 153));

        FDD7.setBackground(new java.awt.Color(255, 255, 255));
        FDD7.setDateFormatString("dd/MM/yyyy");
        FDD7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD1.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(RDD1);
        RDD1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD1.setForeground(new java.awt.Color(255, 255, 255));
        RDD1.setText("TSH");
        RDD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel157Layout = new javax.swing.GroupLayout(jPanel157);
        jPanel157.setLayout(jPanel157Layout);
        jPanel157Layout.setHorizontalGroup(
            jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDD7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
            .addGroup(jPanel157Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(RDD1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel157Layout.setVerticalGroup(
            jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel157Layout.createSequentialGroup()
                .addComponent(RDD1)
                .addGap(3, 3, 3)
                .addComponent(FDD7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel158.setBackground(new java.awt.Color(153, 153, 153));

        FDD8.setBackground(new java.awt.Color(255, 255, 255));
        FDD8.setDateFormatString("dd/MM/yyyy");
        FDD8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD2.setBackground(new java.awt.Color(153, 153, 153));
        RDD2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD2.setForeground(new java.awt.Color(255, 255, 255));
        RDD2.setText("FC");
        RDD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel158Layout = new javax.swing.GroupLayout(jPanel158);
        jPanel158.setLayout(jPanel158Layout);
        jPanel158Layout.setHorizontalGroup(
            jPanel158Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDD8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
            .addGroup(jPanel158Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(RDD2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel158Layout.setVerticalGroup(
            jPanel158Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel158Layout.createSequentialGroup()
                .addComponent(RDD2)
                .addGap(3, 3, 3)
                .addComponent(FDD8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel159.setBackground(new java.awt.Color(153, 153, 153));

        FDD9.setBackground(new java.awt.Color(255, 255, 255));
        FDD9.setDateFormatString("dd/MM/yyyy");
        FDD9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD3.setBackground(new java.awt.Color(153, 153, 153));
        RDD3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD3.setForeground(new java.awt.Color(255, 255, 255));
        RDD3.setText("FO");
        RDD3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel159Layout = new javax.swing.GroupLayout(jPanel159);
        jPanel159.setLayout(jPanel159Layout);
        jPanel159Layout.setHorizontalGroup(
            jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDD9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel159Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RDD3)
                .addGap(39, 39, 39))
        );
        jPanel159Layout.setVerticalGroup(
            jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel159Layout.createSequentialGroup()
                .addComponent(RDD3)
                .addGap(3, 3, 3)
                .addComponent(FDD9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel160.setBackground(new java.awt.Color(153, 153, 153));

        FDD10.setBackground(new java.awt.Color(255, 255, 255));
        FDD10.setDateFormatString("dd/MM/yyyy");
        FDD10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD4.setBackground(new java.awt.Color(153, 153, 153));
        RDD4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD4.setForeground(new java.awt.Color(255, 255, 255));
        RDD4.setText("HSR");
        RDD4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel160Layout = new javax.swing.GroupLayout(jPanel160);
        jPanel160.setLayout(jPanel160Layout);
        jPanel160Layout.setHorizontalGroup(
            jPanel160Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDD10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
            .addGroup(jPanel160Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(RDD4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel160Layout.setVerticalGroup(
            jPanel160Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel160Layout.createSequentialGroup()
                .addComponent(RDD4)
                .addGap(3, 3, 3)
                .addComponent(FDD10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout CCDM15Layout = new javax.swing.GroupLayout(CCDM15);
        CCDM15.setLayout(CCDM15Layout);
        CCDM15Layout.setHorizontalGroup(
            CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM15Layout.createSequentialGroup()
                .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel157, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FUADD7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel158, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FUADD8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel159, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FUADD9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel160, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUADD10)))
        );
        CCDM15Layout.setVerticalGroup(
            CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM15Layout.createSequentialGroup()
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
                            .addComponent(FUADD9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FUADD7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FUADD8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(CCDM15Layout.createSequentialGroup()
                        .addComponent(jPanel160, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(FUADD10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addContainerGap(841, Short.MAX_VALUE))
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
                .addComponent(mensaje1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        mensajeLayout.setVerticalGroup(
            mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mensaje1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lblId.setText("jLabel1");

        jPanel26.setBackground(new java.awt.Color(23, 160, 134));
        jPanel26.setPreferredSize(new java.awt.Dimension(83, 45));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tamizaje Neonatal");

        jPanel27.setBackground(new java.awt.Color(25, 188, 157));

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

        lblPorcentajeTn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPorcentajeTn.setForeground(new java.awt.Color(124, 223, 204));
        lblPorcentajeTn.setText("100 %  Completado");
        lblPorcentajeTn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel2.setBackground(new java.awt.Color(25, 188, 157));
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
        lblNina.setForeground(new java.awt.Color(124, 223, 204));
        lblNina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niña-50.png"))); // NOI18N
        lblNina.setText("NIÑOS");
        lblNina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNina.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblNino.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        lblNino.setForeground(new java.awt.Color(124, 223, 204));
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
                    .addComponent(lblPorcentajeTn)
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
                .addComponent(lblPorcentajeTn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel299Layout = new javax.swing.GroupLayout(jPanel299);
        jPanel299.setLayout(jPanel299Layout);
        jPanel299Layout.setHorizontalGroup(
            jPanel299Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel299Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel299Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblId)
                    .addComponent(CCDM15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(892, Short.MAX_VALUE))
            .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 1392, Short.MAX_VALUE)
        );
        jPanel299Layout.setVerticalGroup(
            jPanel299Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel299Layout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(CCDM15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel299, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel299, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        validaRegistro(Integer.parseInt(lblId.getText()));
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        habilitarRadio(true);
        habilitarDatos(false);
        Botones(false);
        fua.setText("");
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
        if(FDD7.getDate()==null){
            if(RDD1.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                FDD7.setEnabled(true);
                fecha=FDD7;
                fua = FUADD7;
            }
        } else {
            RDD1.setEnabled(false);
        }

    }//GEN-LAST:event_RDD1ActionPerformed

    private void RDD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD2ActionPerformed
        if(FDD8.getDate()==null){
            if(RDD2.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                FDD8.setEnabled(true);
                fecha=FDD8;
                fua = FUADD8;
            }
        } else {
            RDD2.setEnabled(false);
        }
    }//GEN-LAST:event_RDD2ActionPerformed

    private void RDD3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD3ActionPerformed
        if(FDD9.getDate()==null){
            if(RDD3.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                FDD9.setEnabled(true);
                fecha=FDD9;
                fua = FUADD9;
            }
        } else {
            RDD3.setEnabled(false);
        }
    }//GEN-LAST:event_RDD3ActionPerformed

    private void RDD4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD4ActionPerformed
      if(FDD10.getDate()==null){
            if(RDD4.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                Botones(true);
                FDD10.setEnabled(true);
                fecha=FDD10;
                fua = FUADD10;
            }
        } else {
            RDD4.setEnabled(false);
        }
    }//GEN-LAST:event_RDD4ActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        RegistroSeguimiento.jTabbedPane1.setSelectedIndex(0);
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CCDM15;
    public static com.toedter.calendar.JDateChooser FDD10;
    public static com.toedter.calendar.JDateChooser FDD7;
    public static com.toedter.calendar.JDateChooser FDD8;
    public static com.toedter.calendar.JDateChooser FDD9;
    public static javax.swing.JTextField FUADD10;
    public static javax.swing.JTextField FUADD7;
    public static javax.swing.JTextField FUADD8;
    public static javax.swing.JTextField FUADD9;
    private javax.swing.JRadioButton RDD1;
    private javax.swing.JRadioButton RDD2;
    private javax.swing.JRadioButton RDD3;
    private javax.swing.JRadioButton RDD4;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel157;
    private javax.swing.JPanel jPanel158;
    private javax.swing.JPanel jPanel159;
    private javax.swing.JPanel jPanel160;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel299;
    private javax.swing.JPanel jPanel31;
    public static javax.swing.JLabel lblId;
    public static javax.swing.JLabel lblNina;
    public static javax.swing.JLabel lblNino;
    public static javax.swing.JLabel lblPorcentajeTn;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    private javax.swing.JPanel mensaje1;
    // End of variables declaration//GEN-END:variables
}
