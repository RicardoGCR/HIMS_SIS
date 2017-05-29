/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.Timer;
import modelos.Usuario;
import servicios.Conexion;

import static vista.Principal.fechaActual;

/**
 *
 * @author silvana
 */
public class inicioSesion extends javax.swing.JFrame implements Runnable{
    
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    String usuario;
 
    Conexion cn= new Conexion();
    Connection cnn=cn.conectar();
    
    Conexion cn1= new Conexion();
    Connection cnn1=cn1.conectar();
    
    Conexion cn2= new Conexion();
    Connection cnn2=cn2.conectar();
   
    
    private Timer tiempo,tiempo1;
    int cont,cont1,contadmin=1,filtroModulo;
    public final static int TWO_SECOND=5;
    public final static int TWO_SECOND1=5;
 
    /**
     * Creates new form inicioSesion
     */
    public inicioSesion() {
        
        initComponents();
        lblFecha.setText(fechaActual());
        txtUsuario.requestFocus();
        this.getContentPane().setBackground(Color.lightGray);
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        panelRecuperar.setVisible(false);
        h1 = new Thread(this);
        h1.start();
        barra.setBackground(new Color(155,155,155));
        barra.setForeground(new Color(0,0,0));
//       setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/hospital32x32.png")).getImage());
    }

    public void calcula() {
        Calendar calendario = new GregorianCalendar();
        java.util.Date fechaHoraActual = new java.util.Date();

        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";

        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }
    
    void acceder(String usu,String pass){
     try {
         if(txtUsuario.getText().equalsIgnoreCase("")||txtContra.getText().equalsIgnoreCase("")){
             JOptionPane.showMessageDialog(this, "Debe llenar todos los Campos");
         }else{
            

            String cap="";
            String sql="select * from usuario where Usu_Usuario='"+usu+"' and dbo.fnLeeClave(usu_contrasena)='"+pass+"'";
            PreparedStatement st=cnn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
            cap=rs.getString("tipoUsu_Codigo");
            }
            st.close();
            Usuario u=new Usuario();
            if(cap.equalsIgnoreCase("")){
           JOptionPane.showMessageDialog(this, "Usuario y/o Contraseña Incorrectos");
           txtContra.setText("");
       }else if(cap.equalsIgnoreCase(codAdmin("ADMINISTRACION"))){//Para editar tipo de usuario
                
        //p.pack();
         cont=-1;
        barra.setValue(0);
        barra.setStringPainted(true);
        tiempo=new Timer(TWO_SECOND, new TimerListener());
        activar();
      }else if(cap.equalsIgnoreCase(codAdmin("ADMISION CENTRAL"))){//Para editar tipo de usuario

        cont1=-1;
        barra.setValue(0);
        barra.setStringPainted(true);
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=1;
      }else if(cap.equalsIgnoreCase(codAdmin("ADMISION EMERGENCIA"))){//Para editar tipo de usuario

        cont1=-1;
        barra.setValue(0);
        barra.setStringPainted(true);
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=2;
      }else if(cap.equalsIgnoreCase(codAdmin("CAJA"))){//Para editar tipo de usuario

        cont1=-1;
        barra.setValue(0);
        barra.setStringPainted(true);
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=3;
      }else if(cap.equalsIgnoreCase(codAdmin("COSTOS"))){//Para editar tipo de usuario

        cont1=-1;
        barra.setValue(0);
        barra.setStringPainted(true);
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=4;
      }else if(cap.equalsIgnoreCase(codAdmin("HOSPITALIZACION"))){//Para editar tipo de usuario

        cont1=-1;
        barra.setValue(0);
        barra.setStringPainted(true);
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=5;
      }else if(cap.equalsIgnoreCase(codAdmin("LABORATORIO"))){//Para editar tipo de usuario

        cont1=-1;
        barra.setValue(0);
        barra.setStringPainted(true);
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=6;
      }else if(cap.equalsIgnoreCase(codAdmin("RAYOS X"))){//Para editar tipo de usuario

        cont1=-1;
        barra.setValue(0);
        barra.setStringPainted(true);
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=7;
      }else if(cap.equalsIgnoreCase(codAdmin("CONSULTORIOS EXTERNOS"))){//Para editar tipo de usuario

        cont1=-1;
        barra.setValue(0);
        barra.setStringPainted(true);
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=8;
      }else if(cap.equalsIgnoreCase(codAdmin("INVESTIGACION BACTEREOLOGICA"))){//Para editar tipo de usuario

        cont1=-1;
        barra.setValue(0);
        barra.setStringPainted(true);
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=9;
      }else if(cap.equalsIgnoreCase(codAdmin("CONFIGURACION"))){//Para editar tipo de usuario

        cont1=-1;
        barra.setValue(0);
        barra.setStringPainted(true);
        tiempo1=new Timer(TWO_SECOND1, new TimerListener1());
        activar1();
        filtroModulo=10;
      }else{
          JOptionPane.showMessageDialog(this, "NO PERTENECE A NINGÚN MÓDULO DEL SISTEMA");
      }
         }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "catch---Usuario y/o Contraseña Incorrectos");
    }
}
    
    
    public String codAdmin(String codTipo)
    {
       Usuario u=new  Usuario();
        String cod="";
        try
        {
             
            String sql = "SELECT tipoUsu_Codigo FROM tipo_Usuario where tipoUsu_Tipo=?";
            PreparedStatement cmd = u.getCn().prepareStatement(sql);
            cmd.setString(1, codTipo);
            ResultSet rs = cmd.executeQuery();
           // Statement cmd = cnn1.createStatement();
          //  ResultSet rs = cmd.executeQuery(sql);
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
           
        }
        catch(Exception ex)        {
          
            System.out.println("Error en Admin: " + ex.getMessage());
        }
        return cod;
    }

   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnIniciarSesion = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        txtContra = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        panelRecuperar = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnRecuperar = new javax.swing.JButton();
        txtRespuesta = new javax.swing.JTextField();
        txtPregunta = new javax.swing.JTextField();
        barra = new javax.swing.JProgressBar();
        lblFecha = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISGESH .::. Inicio de Sesión");
        setMinimumSize(new java.awt.Dimension(915, 467));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Iniciar sesión");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Usuario");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Contraseña");

        btnIniciarSesion.setBackground(new java.awt.Color(102, 102, 102));
        btnIniciarSesion.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setText("Iniciar Sesión");
        btnIniciarSesion.setBorder(null);
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        txtContra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("He olvidado mi contraseña");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        panelRecuperar.setBackground(new java.awt.Color(0, 153, 153));
        panelRecuperar.setForeground(new java.awt.Color(0, 153, 153));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Pregunta");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Respuesta");

        btnRecuperar.setBackground(new java.awt.Color(255, 255, 255));
        btnRecuperar.setForeground(new java.awt.Color(102, 102, 102));
        btnRecuperar.setText("Recuperar Contraseña");
        btnRecuperar.setToolTipText("");
        btnRecuperar.setActionCommand("Recuperar \nContraseña");
        btnRecuperar.setBorder(null);
        btnRecuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRecuperarLayout = new javax.swing.GroupLayout(panelRecuperar);
        panelRecuperar.setLayout(panelRecuperarLayout);
        panelRecuperarLayout.setHorizontalGroup(
            panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecuperarLayout.createSequentialGroup()
                .addGroup(panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRecuperarLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRecuperar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRecuperarLayout.createSequentialGroup()
                                .addGroup(panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPregunta, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                    .addComponent(txtRespuesta)))))
                    .addGroup(panelRecuperarLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(barra, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRecuperarLayout.setVerticalGroup(
            panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecuperarLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(barra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(panelRecuperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addComponent(btnRecuperar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblFecha.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(102, 102, 102));
        lblFecha.setText("00/00/00");

        jLabel2.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Fecha");

        jLabel5.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Hora");

        lblHora.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblHora.setForeground(new java.awt.Color(102, 102, 102));
        lblHora.setText("00:00:00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRecuperar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel10))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtContra, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(txtUsuario)))
                                    .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHora)))
                .addGap(51, 51, 51))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRecuperar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 60)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Captura2 - copia - copia.PNG"))); // NOI18N
        jLabel7.setText("SISGESH");
        jLabel7.setFocusable(false);
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        Usuario u=new Usuario();
        Usuario u1=new Usuario();
        if(txtUsuario.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Ingrese su Nombre de Usuario");     
            panelRecuperar.setVisible(false);
        } else if(u.ver_usuario(txtUsuario.getText())==0){
            JOptionPane.showMessageDialog(this, "El usuario no existe en el Sistema");     
            panelRecuperar.setVisible(false);
        }
        else{
            panelRecuperar.setVisible(true);
            txtPregunta.setEditable(false);
            txtPregunta.setText(u1.Pregunta(txtUsuario.getText()));
        }
        
        
    }//GEN-LAST:event_jLabel3MouseClicked

    private void btnRecuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperarActionPerformed
        // TODO add your handling code here:
        Usuario u=new Usuario();
        Usuario us=new Usuario();
        if(txtUsuario.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Ingrese su Nombre de Usuario");
        } else if(txtRespuesta.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane, "Escriba su Respuesta");
        }else if(u.Respuesta(txtUsuario.getText(), txtPregunta.getText()).equalsIgnoreCase(txtRespuesta.getText())){
            JOptionPane.showMessageDialog(rootPane, "Su Contraseña es : "+us.Contrasena(txtUsuario.getText(), txtPregunta.getText()));
            panelRecuperar.setVisible(false);
            txtPregunta.setText("");
            txtRespuesta.setText("");
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "La Respuesta es Incorrecta");
            txtRespuesta.setText("");
        }
    }//GEN-LAST:event_btnRecuperarActionPerformed

    private void txtContraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraKeyPressed
        // TODO add your handling code here:
        
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    btnIniciarSesion.doClick();
                }
    }//GEN-LAST:event_txtContraKeyPressed

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
             // TODO add your handling code here:
        usuario=txtUsuario.getText();
        String contra=String.valueOf(txtContra.getPassword());
        acceder(usuario, contra);
    }//GEN-LAST:event_btnIniciarSesionActionPerformed
  class TimerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           cont++;
           barra.setValue(cont);
           if(cont==101){
               tiempo.stop();
               esconder();
               
                PrincipalMDI pmdi = new PrincipalMDI();
                PrincipalMDI.lblUsu.setText(usuario);
                pmdi.setVisible(true);
           }
        }
    }
   class TimerListener1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           cont1++;
           barra.setValue(cont1);
           if(cont1==101){
               tiempo1.stop();
               esconder();
        PrincipalMDI pmdi = new PrincipalMDI();
                PrincipalMDI.lblUsu.setText(usuario);
                pmdi.setVisible(true);
                
                PrincipalMDI.btnAdmCentral.setEnabled(false);
                PrincipalMDI.btnAdmCentral.setForeground(new Color(155,155,155));
                PrincipalMDI.btnAdmEme.setEnabled(false);
                PrincipalMDI.btnAdmEme.setForeground(new Color(155,155,155));
                PrincipalMDI.btnCaja.setEnabled(false);
                PrincipalMDI.btnCaja.setForeground(new Color(155,155,155));
                PrincipalMDI.btnCostos.setEnabled(false);
                PrincipalMDI.btnCostos.setForeground(new Color(155,155,155));
                PrincipalMDI.btnHospitalizacion.setEnabled(false);
                PrincipalMDI.btnHospitalizacion.setForeground(new Color(155,155,155));
                PrincipalMDI.btnLaboratorio.setEnabled(false);
                PrincipalMDI.btnLaboratorio.setForeground(new Color(155,155,155));
                PrincipalMDI.btnRayosX.setEnabled(false);
                PrincipalMDI.btnRayosX.setForeground(new Color(155,155,155));
                PrincipalMDI.btnConExt.setEnabled(false);
                PrincipalMDI.btnConExt.setForeground(new Color(155,155,155));
                PrincipalMDI.btnInvBact.setEnabled(false);
                PrincipalMDI.btnInvBact.setForeground(new Color(155,155,155));
                PrincipalMDI.btnConfiguracion.setEnabled(false);
                PrincipalMDI.btnConfiguracion.setForeground(new Color(155,155,155));
                if(filtroModulo==1){
                  PrincipalMDI.btnAdmCentral.setEnabled(true);
                  PrincipalMDI.btnAdmCentral.setForeground(new Color(255,255,255));
                }else if(filtroModulo==2){
                PrincipalMDI.btnAdmEme.setEnabled(true);
                 PrincipalMDI.btnAdmEme.setForeground(new Color(255,255,255));
                }else if(filtroModulo==3){
                 PrincipalMDI.btnCaja.setEnabled(true);
                 PrincipalMDI.btnCaja.setForeground(new Color(255,255,255));
                }else if(filtroModulo==4){
                PrincipalMDI.btnCostos.setEnabled(true);
                 PrincipalMDI.btnCostos.setForeground(new Color(255,255,255));
                }else if(filtroModulo==5){
                PrincipalMDI.btnHospitalizacion.setEnabled(true);
                 PrincipalMDI.btnHospitalizacion.setForeground(new Color(255,255,255));
                }else if(filtroModulo==6){
                PrincipalMDI.btnLaboratorio.setEnabled(true);
                 PrincipalMDI.btnLaboratorio.setForeground(new Color(255,255,255));
                }else if(filtroModulo==7){
                 PrincipalMDI.btnRayosX.setEnabled(true);
                 PrincipalMDI.btnRayosX.setForeground(new Color(255,255,255));
                }else if(filtroModulo==8){
                 PrincipalMDI.btnConExt.setEnabled(true);
                 PrincipalMDI.btnConExt.setForeground(new Color(255,255,255));
                }else if(filtroModulo==9){
                 PrincipalMDI.btnInvBact.setEnabled(true);
                 PrincipalMDI.btnInvBact.setForeground(new Color(255,255,255));
                }else if(filtroModulo==10){
                 PrincipalMDI.btnConfiguracion.setEnabled(true);
                 PrincipalMDI.btnConfiguracion.setForeground(new Color(255,255,255));
                }
           }
        }
    }
    public void esconder(){
        this.setVisible(false);
    }
    public void activar(){
        tiempo.start();
        
    }
    public void activar1(){
        tiempo1.start();
        
    }


    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            lblHora.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
           /*
    String a="sql";
            int intIndex = a.indexOf("lenguaje sql");
          if(intIndex == - 1){
             System.out.println("palabra encontrada");
          }else{
             System.out.println("palabra no encontrada"
             + intIndex);
          }
    */
    
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
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barra;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnRecuperar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JPanel panelRecuperar;
    public static javax.swing.JPasswordField txtContra;
    private javax.swing.JTextField txtPregunta;
    private javax.swing.JTextField txtRespuesta;
    public static javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
