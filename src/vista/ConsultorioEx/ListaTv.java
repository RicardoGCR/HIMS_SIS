/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Color;
import java.beans.PropertyVetoException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.ConsultorioEx.ConsultorioExtConsultorioCabecera;
import static vista.ConsultorioEx.MensajeTv.tbMensajes;
import static vista.Principal.fechaActual;

/**
 *
 * @author PC02
 */
public class ListaTv extends javax.swing.JFrame implements Runnable{

    ConsultorioExtConsultorioCabecera consultorio = new ConsultorioExtConsultorioCabecera();
    Thread h1;
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    public ListaTv() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);//en el centro
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
//        lblFecha.setText(fechaActual());
//        consultorio.listarConsultorioTv(tbConsultorio);
        Mensaje msj =new Mensaje();
        ListaTv.pnlMensaje.add(msj);
        try {
            msj.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnEnviar.requestFocus();
        tbPacientes.setVisible(false);
        jScrollPane5.setVisible(false);
        btnEnviar.setVisible(false);
        consultorio.listarConsultorioTv(tbPacientes);
        btnEnviar.doClick();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        lblConsultorio5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lblPaciente1 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        lblNumero1 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        lblConsultorio1 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        lblPaciente2 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        lblNumero2 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        lblConsultorio2 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        lblPaciente3 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        lblNumero3 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        lblConsultorio3 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        lblPaciente4 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        lblNumero4 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        lblConsultorio4 = new javax.swing.JLabel();
        lblMedico = new javax.swing.JLabel();
        pnlMensaje = new javax.swing.JDesktopPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbPacientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            btnEnviar = new javax.swing.JButton();
            jPanel4 = new javax.swing.JPanel();
            lblConsultorio6 = new javax.swing.JLabel();
            jPanel5 = new javax.swing.JPanel();
            lblConsultorio7 = new javax.swing.JLabel();
            jPanel1 = new javax.swing.JPanel();
            jLabel3 = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            jPanel3.setBackground(new java.awt.Color(0, 204, 204));
            jPanel3.setPreferredSize(new java.awt.Dimension(180, 114));

            lblConsultorio5.setFont(new java.awt.Font("Segoe UI Light", 0, 38)); // NOI18N
            lblConsultorio5.setForeground(new java.awt.Color(255, 255, 255));
            lblConsultorio5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblConsultorio5.setText("Consultorio");

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblConsultorio5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblConsultorio5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
            );

            jPanel14.setBackground(new java.awt.Color(255, 255, 255));
            jPanel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 1, true));

            lblPaciente1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
            lblPaciente1.setForeground(new java.awt.Color(0, 204, 204));
            lblPaciente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblPaciente1.setText("Paciente");

            javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
            jPanel14.setLayout(jPanel14Layout);
            jPanel14Layout.setHorizontalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblPaciente1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel14Layout.setVerticalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblPaciente1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
            );

            jPanel15.setBackground(new java.awt.Color(153, 153, 153));
            jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
            jPanel15.setForeground(new java.awt.Color(102, 102, 102));

            lblNumero1.setFont(new java.awt.Font("Segoe UI Light", 0, 50)); // NOI18N
            lblNumero1.setForeground(new java.awt.Color(255, 255, 255));
            lblNumero1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblNumero1.setText("Nº");

            javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
            jPanel15.setLayout(jPanel15Layout);
            jPanel15Layout.setHorizontalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumero1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
            );
            jPanel15Layout.setVerticalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumero1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
            );

            jPanel16.setBackground(new java.awt.Color(0, 204, 204));
            jPanel16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 1, true));

            lblConsultorio1.setFont(new java.awt.Font("Segoe UI Light", 0, 38)); // NOI18N
            lblConsultorio1.setForeground(new java.awt.Color(255, 255, 255));
            lblConsultorio1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblConsultorio1.setText("Consultorio");

            javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
            jPanel16.setLayout(jPanel16Layout);
            jPanel16Layout.setHorizontalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblConsultorio1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
            );
            jPanel16Layout.setVerticalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblConsultorio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            jPanel17.setBackground(new java.awt.Color(255, 255, 255));
            jPanel17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 1, true));

            lblPaciente2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
            lblPaciente2.setForeground(new java.awt.Color(0, 204, 204));
            lblPaciente2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblPaciente2.setText("Paciente");

            javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
            jPanel17.setLayout(jPanel17Layout);
            jPanel17Layout.setHorizontalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblPaciente2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel17Layout.setVerticalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblPaciente2, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
            );

            jPanel18.setBackground(new java.awt.Color(153, 153, 153));
            jPanel18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
            jPanel18.setForeground(new java.awt.Color(102, 102, 102));

            lblNumero2.setFont(new java.awt.Font("Segoe UI Light", 0, 50)); // NOI18N
            lblNumero2.setForeground(new java.awt.Color(255, 255, 255));
            lblNumero2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblNumero2.setText("Nº");

            javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
            jPanel18.setLayout(jPanel18Layout);
            jPanel18Layout.setHorizontalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblNumero2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
            );
            jPanel18Layout.setVerticalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblNumero2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            jPanel19.setBackground(new java.awt.Color(0, 204, 204));
            jPanel19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 1, true));

            lblConsultorio2.setFont(new java.awt.Font("Segoe UI Light", 0, 38)); // NOI18N
            lblConsultorio2.setForeground(new java.awt.Color(255, 255, 255));
            lblConsultorio2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblConsultorio2.setText("Consultorio");

            javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
            jPanel19.setLayout(jPanel19Layout);
            jPanel19Layout.setHorizontalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblConsultorio2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
            );
            jPanel19Layout.setVerticalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblConsultorio2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            jPanel20.setBackground(new java.awt.Color(255, 255, 255));
            jPanel20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 1, true));

            lblPaciente3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
            lblPaciente3.setForeground(new java.awt.Color(0, 204, 204));
            lblPaciente3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblPaciente3.setText("Paciente");

            javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
            jPanel20.setLayout(jPanel20Layout);
            jPanel20Layout.setHorizontalGroup(
                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblPaciente3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel20Layout.setVerticalGroup(
                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblPaciente3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            jPanel21.setBackground(new java.awt.Color(153, 153, 153));
            jPanel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
            jPanel21.setForeground(new java.awt.Color(102, 102, 102));

            lblNumero3.setFont(new java.awt.Font("Segoe UI Light", 0, 50)); // NOI18N
            lblNumero3.setForeground(new java.awt.Color(255, 255, 255));
            lblNumero3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblNumero3.setText("Nº");

            javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
            jPanel21.setLayout(jPanel21Layout);
            jPanel21Layout.setHorizontalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblNumero3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel21Layout.setVerticalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblNumero3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            jPanel22.setBackground(new java.awt.Color(0, 204, 204));
            jPanel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 1, true));

            lblConsultorio3.setFont(new java.awt.Font("Segoe UI Light", 0, 38)); // NOI18N
            lblConsultorio3.setForeground(new java.awt.Color(255, 255, 255));
            lblConsultorio3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblConsultorio3.setText("Consultorio");

            javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
            jPanel22.setLayout(jPanel22Layout);
            jPanel22Layout.setHorizontalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 230, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblConsultorio3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
            );
            jPanel22Layout.setVerticalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 114, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblConsultorio3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
            );

            jPanel23.setBackground(new java.awt.Color(255, 255, 255));
            jPanel23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 1, true));

            lblPaciente4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
            lblPaciente4.setForeground(new java.awt.Color(0, 204, 204));
            lblPaciente4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblPaciente4.setText("Paciente");

            javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
            jPanel23.setLayout(jPanel23Layout);
            jPanel23Layout.setHorizontalGroup(
                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblPaciente4, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel23Layout.setVerticalGroup(
                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(lblPaciente4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(38, 38, 38))
            );

            jPanel24.setBackground(new java.awt.Color(153, 153, 153));
            jPanel24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
            jPanel24.setForeground(new java.awt.Color(102, 102, 102));

            lblNumero4.setFont(new java.awt.Font("Segoe UI Light", 0, 50)); // NOI18N
            lblNumero4.setForeground(new java.awt.Color(255, 255, 255));
            lblNumero4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblNumero4.setText("Nº");

            javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
            jPanel24.setLayout(jPanel24Layout);
            jPanel24Layout.setHorizontalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblNumero4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel24Layout.setVerticalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblNumero4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            jPanel25.setBackground(new java.awt.Color(0, 204, 204));
            jPanel25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 1, true));

            lblConsultorio4.setFont(new java.awt.Font("Segoe UI Light", 0, 38)); // NOI18N
            lblConsultorio4.setForeground(new java.awt.Color(255, 255, 255));
            lblConsultorio4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblConsultorio4.setText("Consultorio");

            javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
            jPanel25.setLayout(jPanel25Layout);
            jPanel25Layout.setHorizontalGroup(
                jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblConsultorio4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
            );
            jPanel25Layout.setVerticalGroup(
                jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 114, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblConsultorio4, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
            );

            lblMedico.setFont(new java.awt.Font("Segoe UI Light", 0, 40)); // NOI18N
            lblMedico.setForeground(new java.awt.Color(0, 204, 204));
            lblMedico.setText("Médico");

            javax.swing.GroupLayout pnlMensajeLayout = new javax.swing.GroupLayout(pnlMensaje);
            pnlMensaje.setLayout(pnlMensajeLayout);
            pnlMensajeLayout.setHorizontalGroup(
                pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 1354, Short.MAX_VALUE)
            );
            pnlMensajeLayout.setVerticalGroup(
                pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 117, Short.MAX_VALUE)
            );

            jScrollPane5.setBorder(null);

            tbPacientes.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            tbPacientes.setForeground(new java.awt.Color(102, 102, 102));
            tbPacientes.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ));
            tbPacientes.setGridColor(new java.awt.Color(255, 255, 255));
            tbPacientes.setRowHeight(25);
            tbPacientes.setSelectionBackground(new java.awt.Color(39, 174, 97));
            tbPacientes.getTableHeader().setReorderingAllowed(false);
            tbPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbPacientesMouseClicked(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    tbPacientesMousePressed(evt);
                }
            });
            tbPacientes.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbPacientesKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    tbPacientesKeyReleased(evt);
                }
            });
            jScrollPane5.setViewportView(tbPacientes);

            btnEnviar.setText("Enviar");
            btnEnviar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnEnviarActionPerformed(evt);
                }
            });

            jPanel4.setBackground(new java.awt.Color(153, 153, 153));
            jPanel4.setPreferredSize(new java.awt.Dimension(180, 114));

            lblConsultorio6.setFont(new java.awt.Font("Segoe UI Light", 0, 35)); // NOI18N
            lblConsultorio6.setForeground(new java.awt.Color(255, 255, 255));
            lblConsultorio6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblConsultorio6.setText("Nº");

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblConsultorio6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblConsultorio6, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
            );

            jPanel5.setBackground(new java.awt.Color(0, 204, 204));
            jPanel5.setPreferredSize(new java.awt.Dimension(180, 114));

            lblConsultorio7.setFont(new java.awt.Font("Segoe UI Light", 0, 38)); // NOI18N
            lblConsultorio7.setForeground(new java.awt.Color(255, 255, 255));
            lblConsultorio7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblConsultorio7.setText("Paciente");

            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblConsultorio7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblConsultorio7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
            );

            jPanel1.setBackground(new java.awt.Color(228, 226, 226));
            jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));

            jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/hospitalSanJose.png"))); // NOI18N

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addComponent(jLabel3)
                    .addContainerGap(77, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(76, 76, 76)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(78, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlMensaje)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnEnviar)))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                        .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGap(18, 18, 18)))
                    .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void tbPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesMouseClicked
        
    }//GEN-LAST:event_tbPacientesMouseClicked

    private void tbPacientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPacientesMousePressed

    private void tbPacientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacientesKeyPressed

    }//GEN-LAST:event_tbPacientesKeyPressed

    private void tbPacientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacientesKeyReleased
        
    }//GEN-LAST:event_tbPacientesKeyReleased

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
    
            lblPaciente1.setText("");
            lblPaciente2.setText("");
            lblPaciente3.setText("");
            lblPaciente4.setText("");
            lblNumero1.setText("");
            lblNumero2.setText("");
            lblNumero3.setText("");
            lblNumero4.setText("");
            lblConsultorio1.setText("");
            lblConsultorio2.setText("");
            lblConsultorio3.setText("");
            lblConsultorio4.setText("");
            try {
                if(tbPacientes.getRowCount()==1){
                lblPaciente1.setText(String.valueOf(tbPacientes.getValueAt(0, 0)));
                lblNumero1.setText(String.valueOf(tbPacientes.getValueAt(0, 1)));
                lblConsultorio1.setText(String.valueOf(tbPacientes.getValueAt(0, 2)));
                lblMedico.setText(String.valueOf(tbPacientes.getValueAt(0, 3)));
                }else
                if(tbPacientes.getRowCount()==2){
                lblPaciente1.setText(String.valueOf(tbPacientes.getValueAt(0, 0)));
                lblPaciente2.setText(String.valueOf(tbPacientes.getValueAt(1, 0)));
                lblNumero1.setText(String.valueOf(tbPacientes.getValueAt(0, 1)));
                lblNumero2.setText(String.valueOf(tbPacientes.getValueAt(1, 1)));
                lblConsultorio1.setText(String.valueOf(tbPacientes.getValueAt(0, 2)));
                lblConsultorio2.setText(String.valueOf(tbPacientes.getValueAt(1, 2)));
                lblMedico.setText(String.valueOf(tbPacientes.getValueAt(0, 3)));
                }else
                if(tbPacientes.getRowCount()==3){
                lblPaciente1.setText(String.valueOf(tbPacientes.getValueAt(0, 0)));
                lblPaciente2.setText(String.valueOf(tbPacientes.getValueAt(1, 0)));
                lblPaciente3.setText(String.valueOf(tbPacientes.getValueAt(2, 0)));
                lblNumero1.setText(String.valueOf(tbPacientes.getValueAt(0, 1)));
                lblNumero2.setText(String.valueOf(tbPacientes.getValueAt(1, 1)));
                lblNumero3.setText(String.valueOf(tbPacientes.getValueAt(2, 1)));
                lblConsultorio1.setText(String.valueOf(tbPacientes.getValueAt(0, 2)));
                lblConsultorio2.setText(String.valueOf(tbPacientes.getValueAt(1, 2)));
                lblConsultorio3.setText(String.valueOf(tbPacientes.getValueAt(2, 2)));
                lblMedico.setText(String.valueOf(tbPacientes.getValueAt(0, 3)));
                }else
                if(tbPacientes.getRowCount()==4){
                lblPaciente1.setText(String.valueOf(tbPacientes.getValueAt(0, 0)));
                lblPaciente2.setText(String.valueOf(tbPacientes.getValueAt(1, 0)));
                lblPaciente3.setText(String.valueOf(tbPacientes.getValueAt(2, 0)));
                lblPaciente4.setText(String.valueOf(tbPacientes.getValueAt(3, 0)));
                lblNumero1.setText(String.valueOf(tbPacientes.getValueAt(0, 1)));
                lblNumero2.setText(String.valueOf(tbPacientes.getValueAt(1, 1)));
                lblNumero3.setText(String.valueOf(tbPacientes.getValueAt(2, 1)));
                lblNumero4.setText(String.valueOf(tbPacientes.getValueAt(3, 1)));
                lblConsultorio1.setText(String.valueOf(tbPacientes.getValueAt(0, 2)));
                lblConsultorio2.setText(String.valueOf(tbPacientes.getValueAt(1, 2)));
                lblConsultorio3.setText(String.valueOf(tbPacientes.getValueAt(2, 2)));
                lblConsultorio4.setText(String.valueOf(tbPacientes.getValueAt(3, 2)));
                lblMedico.setText(String.valueOf(tbPacientes.getValueAt(0, 3)));
                }
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            try {
                Thread.sleep(7000);
                consultorio.listarConsultorioTv(tbPacientes);
                btnEnviar.doClick();
            } catch (InterruptedException e) {
            }
        }
    }
    
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
            java.util.logging.Logger.getLogger(ListaTv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaTv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaTv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaTv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaTv().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblConsultorio1;
    private javax.swing.JLabel lblConsultorio2;
    private javax.swing.JLabel lblConsultorio3;
    private javax.swing.JLabel lblConsultorio4;
    private javax.swing.JLabel lblConsultorio5;
    private javax.swing.JLabel lblConsultorio6;
    private javax.swing.JLabel lblConsultorio7;
    private javax.swing.JLabel lblMedico;
    private javax.swing.JLabel lblNumero1;
    private javax.swing.JLabel lblNumero2;
    private javax.swing.JLabel lblNumero3;
    private javax.swing.JLabel lblNumero4;
    public static javax.swing.JLabel lblPaciente1;
    public static javax.swing.JLabel lblPaciente2;
    private javax.swing.JLabel lblPaciente3;
    private javax.swing.JLabel lblPaciente4;
    public static javax.swing.JDesktopPane pnlMensaje;
    public static javax.swing.JTable tbPacientes;
    // End of variables declaration//GEN-END:variables
}
