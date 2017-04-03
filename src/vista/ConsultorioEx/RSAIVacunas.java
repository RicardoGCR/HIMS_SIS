/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Color;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 *
 * @author MYS1
 */
public class RSAIVacunas extends javax.swing.JInternalFrame {
//defino dos métodosdentro del JInternalFrame y lo instanciamos de la siguiente manera.
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
private Dimension DimensionBarra = null; 
//A continuación creamos una función dentro del mismo JInternalFrame como el ejemplo siguiente:

    /**
     * Creates new form RSAIVacunas
     */
    public RSAIVacunas() {
        initComponents();
        QuitarLaBarraTitulo();
        habilitarDatos(false);


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
      Relab.setEnabled(opcion);  
      Rejec.setEnabled(opcion);  
      Rbcg.setEnabled(opcion);  
      Rhvb.setEnabled(opcion);  
      Ripv1.setEnabled(opcion);  
      Ripv2.setEnabled(opcion);  
      Ripv3.setEnabled(opcion);  
      Rp1.setEnabled(opcion);  
      Rp2.setEnabled(opcion);  
      Rp3.setEnabled(opcion); 
      
      Rn1.setEnabled(opcion);  
      Rn2.setEnabled(opcion);  
      Rn3.setEnabled(opcion);  
      Ri1.setEnabled(opcion);  
      Ri2.setEnabled(opcion);  
      Rr1.setEnabled(opcion);  
      Rr2.setEnabled(opcion);  
      Rspr1.setEnabled(opcion);  
      Rspr2.setEnabled(opcion);  
      Rama.setEnabled(opcion);
      
      Rdpt1.setEnabled(opcion);  
      Rdpt2.setEnabled(opcion);  
      Rir1.setEnabled(opcion);  
      Rir2.setEnabled(opcion);  
      Rapo1.setEnabled(opcion);  
      Rapo2.setEnabled(opcion);
      
    }
    public void habilitarDatos(boolean opcion){
        txtFuaAmaDu.setEnabled(opcion);
        txtFuaApoR1.setEnabled(opcion);
        txtFuaApoR2.setEnabled(opcion);
        txtFuaBcg.setEnabled(opcion);
        txtFuaDpt1.setEnabled(opcion);
        txtFuaDpt2.setEnabled(opcion);
        txtFuaEjec.setEnabled(opcion);
        txtFuaElab.setEnabled(opcion);
        txtFuaHvb.setEnabled(opcion);
        txtFuaInfl1.setEnabled(opcion);
        txtFuaInfl2.setEnabled(opcion);
        txtFuaIpv1.setEnabled(opcion);
        txtFuaIpv2.setEnabled(opcion);
        txtFuaIpv3.setEnabled(opcion);
        txtFuaNeumo1.setEnabled(opcion);
        txtFuaNeumo2.setEnabled(opcion);
        txtFuaNeumo3.setEnabled(opcion);
        txtFuaPent1.setEnabled(opcion);
        txtFuaPent2.setEnabled(opcion);
        txtFuaPent3.setEnabled(opcion);
        txtFuaRot1.setEnabled(opcion);
        txtFuaRot2.setEnabled(opcion);
        txtFuaSpr1.setEnabled(opcion);
        txtFuaSpr2.setEnabled(opcion);
        txtInflR1.setEnabled(opcion);
        txtInflR2.setEnabled(opcion);
        dtAmadu.setEnabled(opcion);
        dtApoR1.setEnabled(opcion);
        dtApoR2.setEnabled(opcion);
        dtBcg.setEnabled(opcion);
        dtDpt1.setEnabled(opcion);
        dtDpt2.setEnabled(opcion);
        dtEjec.setEnabled(opcion);
        dtElab.setEnabled(opcion);
        dtHvb.setEnabled(opcion);
        dtInfl1.setEnabled(opcion);
        dtInfl2.setEnabled(opcion);
        dtInflR1.setEnabled(opcion);
        dtInflR2.setEnabled(opcion);
        dtIpv1.setEnabled(opcion);
        dtIpv2.setEnabled(opcion);
        dtIpv3.setEnabled(opcion);
        dtNeumo1.setEnabled(opcion);
        dtNeumo2.setEnabled(opcion);
        dtNeumo3.setEnabled(opcion);
        dtPent1.setEnabled(opcion);
        dtPent2.setEnabled(opcion);
        dtPent3.setEnabled(opcion);
        dtRot1.setEnabled(opcion);
        dtRot2.setEnabled(opcion);
        dtSpr1.setEnabled(opcion);
        dtSpr2.setEnabled(opcion);
        txtFuaAmaDu.setEditable(opcion);
        txtFuaApoR1.setEditable(opcion);
        txtFuaApoR2.setEditable(opcion);
        txtFuaBcg.setEditable(opcion);
        txtFuaDpt1.setEditable(opcion);
        txtFuaDpt2.setEditable(opcion);
        txtFuaEjec.setEditable(opcion);
        txtFuaElab.setEditable(opcion);
        txtFuaHvb.setEditable(opcion);
        txtFuaInfl1.setEditable(opcion);
        txtFuaInfl2.setEditable(opcion);
        txtFuaIpv1.setEditable(opcion);
        txtFuaIpv2.setEditable(opcion);
        txtFuaIpv3.setEditable(opcion);
        txtFuaNeumo1.setEditable(opcion);
        txtFuaNeumo2.setEditable(opcion);
        txtFuaNeumo3.setEditable(opcion);
        txtFuaPent1.setEditable(opcion);
        txtFuaPent2.setEditable(opcion);
        txtFuaPent3.setEditable(opcion);
        txtFuaRot1.setEditable(opcion);
        txtFuaRot2.setEditable(opcion);
        txtFuaSpr1.setEditable(opcion);
        txtFuaSpr2.setEditable(opcion);
        txtInflR1.setEditable(opcion);
        txtInflR2.setEditable(opcion);
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
        jPanel26 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        ELAB = new javax.swing.JPanel();
        dtElab = new com.toedter.calendar.JDateChooser();
        txtFuaElab = new javax.swing.JTextField();
        Relab = new javax.swing.JRadioButton();
        EJEC = new javax.swing.JPanel();
        dtEjec = new com.toedter.calendar.JDateChooser();
        txtFuaEjec = new javax.swing.JTextField();
        Rejec = new javax.swing.JRadioButton();
        RN = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        dtBcg = new com.toedter.calendar.JDateChooser();
        Rbcg = new javax.swing.JRadioButton();
        jPanel36 = new javax.swing.JPanel();
        dtHvb = new com.toedter.calendar.JDateChooser();
        Rhvb = new javax.swing.JRadioButton();
        txtFuaBcg = new javax.swing.JTextField();
        txtFuaHvb = new javax.swing.JTextField();
        NEUMOCOCO = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        dtNeumo1 = new com.toedter.calendar.JDateChooser();
        Rn1 = new javax.swing.JRadioButton();
        jPanel54 = new javax.swing.JPanel();
        dtNeumo2 = new com.toedter.calendar.JDateChooser();
        Rn2 = new javax.swing.JRadioButton();
        jPanel55 = new javax.swing.JPanel();
        dtNeumo3 = new com.toedter.calendar.JDateChooser();
        Rn3 = new javax.swing.JRadioButton();
        txtFuaNeumo3 = new javax.swing.JTextField();
        txtFuaNeumo1 = new javax.swing.JTextField();
        txtFuaNeumo2 = new javax.swing.JTextField();
        INFLUENZA = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        dtInfl1 = new com.toedter.calendar.JDateChooser();
        Ri1 = new javax.swing.JRadioButton();
        jPanel38 = new javax.swing.JPanel();
        dtInfl2 = new com.toedter.calendar.JDateChooser();
        Ri2 = new javax.swing.JRadioButton();
        txtFuaInfl1 = new javax.swing.JTextField();
        txtFuaInfl2 = new javax.swing.JTextField();
        ROTAVIRUS = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        dtRot1 = new com.toedter.calendar.JDateChooser();
        Rr1 = new javax.swing.JRadioButton();
        jPanel42 = new javax.swing.JPanel();
        dtRot2 = new com.toedter.calendar.JDateChooser();
        Rr2 = new javax.swing.JRadioButton();
        txtFuaRot1 = new javax.swing.JTextField();
        txtFuaRot2 = new javax.swing.JTextField();
        SPR = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        dtSpr1 = new com.toedter.calendar.JDateChooser();
        Rspr1 = new javax.swing.JRadioButton();
        jPanel46 = new javax.swing.JPanel();
        dtSpr2 = new com.toedter.calendar.JDateChooser();
        Rspr2 = new javax.swing.JRadioButton();
        txtFuaSpr1 = new javax.swing.JTextField();
        txtFuaSpr2 = new javax.swing.JTextField();
        AMA = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jPanel56 = new javax.swing.JPanel();
        dtAmadu = new com.toedter.calendar.JDateChooser();
        Rama = new javax.swing.JRadioButton();
        txtFuaAmaDu = new javax.swing.JTextField();
        ANTIPOLIO = new javax.swing.JPanel();
        jPanel57 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jPanel58 = new javax.swing.JPanel();
        dtIpv1 = new com.toedter.calendar.JDateChooser();
        Ripv1 = new javax.swing.JRadioButton();
        jPanel59 = new javax.swing.JPanel();
        dtIpv2 = new com.toedter.calendar.JDateChooser();
        Ripv2 = new javax.swing.JRadioButton();
        jPanel60 = new javax.swing.JPanel();
        dtIpv3 = new com.toedter.calendar.JDateChooser();
        Ripv3 = new javax.swing.JRadioButton();
        txtFuaIpv3 = new javax.swing.JTextField();
        txtFuaIpv1 = new javax.swing.JTextField();
        txtFuaIpv2 = new javax.swing.JTextField();
        PENTAVALENTE = new javax.swing.JPanel();
        jPanel61 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jPanel62 = new javax.swing.JPanel();
        dtPent1 = new com.toedter.calendar.JDateChooser();
        Rp1 = new javax.swing.JRadioButton();
        jPanel63 = new javax.swing.JPanel();
        dtPent2 = new com.toedter.calendar.JDateChooser();
        Rp2 = new javax.swing.JRadioButton();
        jPanel64 = new javax.swing.JPanel();
        dtPent3 = new com.toedter.calendar.JDateChooser();
        Rp3 = new javax.swing.JRadioButton();
        txtFuaPent3 = new javax.swing.JTextField();
        txtFuaPent1 = new javax.swing.JTextField();
        txtFuaPent2 = new javax.swing.JTextField();
        DPT = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        dtDpt1 = new com.toedter.calendar.JDateChooser();
        Rdpt1 = new javax.swing.JRadioButton();
        jPanel43 = new javax.swing.JPanel();
        dtDpt2 = new com.toedter.calendar.JDateChooser();
        Rdpt2 = new javax.swing.JRadioButton();
        txtFuaDpt1 = new javax.swing.JTextField();
        txtFuaDpt2 = new javax.swing.JTextField();
        INFLUENZA_REF = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        dtInflR1 = new com.toedter.calendar.JDateChooser();
        Rir1 = new javax.swing.JRadioButton();
        jPanel49 = new javax.swing.JPanel();
        dtInflR2 = new com.toedter.calendar.JDateChooser();
        Rir2 = new javax.swing.JRadioButton();
        txtInflR1 = new javax.swing.JTextField();
        txtInflR2 = new javax.swing.JTextField();
        INFLUENZA_REF1 = new javax.swing.JPanel();
        jPanel50 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel65 = new javax.swing.JPanel();
        dtApoR1 = new com.toedter.calendar.JDateChooser();
        Rapo1 = new javax.swing.JRadioButton();
        jPanel66 = new javax.swing.JPanel();
        dtApoR2 = new com.toedter.calendar.JDateChooser();
        Rapo2 = new javax.swing.JRadioButton();
        txtFuaApoR1 = new javax.swing.JTextField();
        txtFuaApoR2 = new javax.swing.JTextField();
        Opciones = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        btneditar = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        lblNino = new javax.swing.JLabel();
        lblNina = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setMinimumSize(new java.awt.Dimension(1381, 430));
        setVisible(true);

        VACUNAS.setBackground(new java.awt.Color(255, 255, 255));

        jPanel26.setBackground(new java.awt.Color(25, 188, 157));
        jPanel26.setPreferredSize(new java.awt.Dimension(83, 45));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("VACUNAS");

        jPanel27.setBackground(new java.awt.Color(23, 160, 134));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addContainerGap(1260, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ELAB.setBackground(new java.awt.Color(153, 153, 153));
        ELAB.setPreferredSize(new java.awt.Dimension(120, 88));

        dtElab.setBackground(new java.awt.Color(204, 204, 204));
        dtElab.setDateFormatString("dd/MM/yyyy");
        dtElab.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txtFuaElab.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaElabCaretUpdate(evt);
            }
        });
        txtFuaElab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFuaElabMouseClicked(evt);
            }
        });
        txtFuaElab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFuaElabActionPerformed(evt);
            }
        });
        txtFuaElab.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                txtFuaElabAncestorRemoved(evt);
            }
        });

        Relab.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Relab);
        Relab.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Relab.setForeground(new java.awt.Color(255, 255, 255));
        Relab.setText("ELAB");
        Relab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RelabMouseClicked(evt);
            }
        });
        Relab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RelabActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ELABLayout = new javax.swing.GroupLayout(ELAB);
        ELAB.setLayout(ELABLayout);
        ELABLayout.setHorizontalGroup(
            ELABLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtFuaElab, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(dtElab, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(ELABLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(Relab)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ELABLayout.setVerticalGroup(
            ELABLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ELABLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Relab)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dtElab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtFuaElab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        EJEC.setBackground(new java.awt.Color(153, 153, 153));
        EJEC.setPreferredSize(new java.awt.Dimension(120, 88));

        dtEjec.setBackground(new java.awt.Color(204, 204, 204));
        dtEjec.setDateFormatString("dd/MM/yyyy");
        dtEjec.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txtFuaEjec.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaEjecCaretUpdate(evt);
            }
        });
        txtFuaEjec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFuaEjecMouseClicked(evt);
            }
        });
        txtFuaEjec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFuaEjecActionPerformed(evt);
            }
        });

        Rejec.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rejec);
        Rejec.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rejec.setForeground(new java.awt.Color(255, 255, 255));
        Rejec.setText("EJEC");
        Rejec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RejecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EJECLayout = new javax.swing.GroupLayout(EJEC);
        EJEC.setLayout(EJECLayout);
        EJECLayout.setHorizontalGroup(
            EJECLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtFuaEjec, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(dtEjec, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(EJECLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Rejec)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        EJECLayout.setVerticalGroup(
            EJECLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EJECLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Rejec)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dtEjec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtFuaEjec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        RN.setBackground(new java.awt.Color(204, 204, 204));

        jPanel31.setBackground(new java.awt.Color(153, 153, 153));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("RECIEN NACIDO");

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

        dtBcg.setBackground(new java.awt.Color(204, 204, 204));
        dtBcg.setDateFormatString("dd/MM/yyyy");
        dtBcg.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rbcg.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rbcg);
        Rbcg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rbcg.setForeground(new java.awt.Color(255, 255, 255));
        Rbcg.setText("BCG");
        Rbcg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbcgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtBcg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(Rbcg)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addComponent(Rbcg)
                .addGap(3, 3, 3)
                .addComponent(dtBcg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel36.setBackground(new java.awt.Color(153, 153, 153));

        dtHvb.setBackground(new java.awt.Color(204, 204, 204));
        dtHvb.setDateFormatString("dd/MM/yyyy");
        dtHvb.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rhvb.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rhvb);
        Rhvb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rhvb.setForeground(new java.awt.Color(255, 255, 255));
        Rhvb.setText("HvB");
        Rhvb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RhvbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtHvb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(Rhvb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addComponent(Rhvb)
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

        javax.swing.GroupLayout RNLayout = new javax.swing.GroupLayout(RN);
        RN.setLayout(RNLayout);
        RNLayout.setHorizontalGroup(
            RNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(RNLayout.createSequentialGroup()
                .addGroup(RNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtFuaBcg, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(RNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaHvb)))
        );
        RNLayout.setVerticalGroup(
            RNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RNLayout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(RNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(RNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuaBcg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaHvb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        NEUMOCOCO.setBackground(new java.awt.Color(204, 204, 204));

        jPanel52.setBackground(new java.awt.Color(153, 153, 153));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("NEUMOCOCO");

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel37)
        );

        jPanel53.setBackground(new java.awt.Color(153, 153, 153));

        dtNeumo1.setBackground(new java.awt.Color(204, 204, 204));
        dtNeumo1.setDateFormatString("dd/MM/yyyy");
        dtNeumo1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rn1.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rn1);
        Rn1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rn1.setForeground(new java.awt.Color(255, 255, 255));
        Rn1.setText("1º");
        Rn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtNeumo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(Rn1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addComponent(Rn1)
                .addGap(3, 3, 3)
                .addComponent(dtNeumo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel54.setBackground(new java.awt.Color(153, 153, 153));

        dtNeumo2.setBackground(new java.awt.Color(204, 204, 204));
        dtNeumo2.setDateFormatString("dd/MM/yyyy");
        dtNeumo2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rn2.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rn2);
        Rn2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rn2.setForeground(new java.awt.Color(255, 255, 255));
        Rn2.setText("2º");
        Rn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtNeumo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(Rn2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addComponent(Rn2)
                .addGap(3, 3, 3)
                .addComponent(dtNeumo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel55.setBackground(new java.awt.Color(153, 153, 153));

        dtNeumo3.setBackground(new java.awt.Color(204, 204, 204));
        dtNeumo3.setDateFormatString("dd/MM/yyyy");
        dtNeumo3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rn3.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rn3);
        Rn3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rn3.setForeground(new java.awt.Color(255, 255, 255));
        Rn3.setText("3º");
        Rn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rn3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtNeumo3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(Rn3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addComponent(Rn3)
                .addGap(3, 3, 3)
                .addComponent(dtNeumo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtFuaNeumo3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaNeumo3CaretUpdate(evt);
            }
        });

        txtFuaNeumo1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaNeumo1CaretUpdate(evt);
            }
        });

        txtFuaNeumo2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaNeumo2CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout NEUMOCOCOLayout = new javax.swing.GroupLayout(NEUMOCOCO);
        NEUMOCOCO.setLayout(NEUMOCOCOLayout);
        NEUMOCOCOLayout.setHorizontalGroup(
            NEUMOCOCOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(NEUMOCOCOLayout.createSequentialGroup()
                .addGroup(NEUMOCOCOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NEUMOCOCOLayout.createSequentialGroup()
                        .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addComponent(txtFuaNeumo1))
                .addGroup(NEUMOCOCOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaNeumo2))
                .addGap(1, 1, 1)
                .addGroup(NEUMOCOCOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFuaNeumo3)
                    .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        NEUMOCOCOLayout.setVerticalGroup(
            NEUMOCOCOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NEUMOCOCOLayout.createSequentialGroup()
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(NEUMOCOCOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NEUMOCOCOLayout.createSequentialGroup()
                        .addGroup(NEUMOCOCOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(NEUMOCOCOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFuaNeumo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFuaNeumo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFuaNeumo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(NEUMOCOCOLayout.createSequentialGroup()
                        .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        INFLUENZA.setBackground(new java.awt.Color(204, 204, 204));

        jPanel33.setBackground(new java.awt.Color(153, 153, 153));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("INFLUENZA");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel37.setBackground(new java.awt.Color(153, 153, 153));

        dtInfl1.setBackground(new java.awt.Color(204, 204, 204));
        dtInfl1.setDateFormatString("dd/MM/yyyy");
        dtInfl1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Ri1.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Ri1);
        Ri1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Ri1.setForeground(new java.awt.Color(255, 255, 255));
        Ri1.setText("1º");
        Ri1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ri1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtInfl1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(Ri1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(Ri1)
                .addGap(3, 3, 3)
                .addComponent(dtInfl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel38.setBackground(new java.awt.Color(153, 153, 153));

        dtInfl2.setBackground(new java.awt.Color(204, 204, 204));
        dtInfl2.setDateFormatString("dd/MM/yyyy");
        dtInfl2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Ri2.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Ri2);
        Ri2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Ri2.setForeground(new java.awt.Color(255, 255, 255));
        Ri2.setText("2º");
        Ri2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ri2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtInfl2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(Ri2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addComponent(Ri2)
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

        javax.swing.GroupLayout INFLUENZALayout = new javax.swing.GroupLayout(INFLUENZA);
        INFLUENZA.setLayout(INFLUENZALayout);
        INFLUENZALayout.setHorizontalGroup(
            INFLUENZALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(INFLUENZALayout.createSequentialGroup()
                .addGroup(INFLUENZALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtFuaInfl1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(INFLUENZALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaInfl2)))
        );
        INFLUENZALayout.setVerticalGroup(
            INFLUENZALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(INFLUENZALayout.createSequentialGroup()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(INFLUENZALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(INFLUENZALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuaInfl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaInfl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        ROTAVIRUS.setBackground(new java.awt.Color(204, 204, 204));

        jPanel34.setBackground(new java.awt.Color(153, 153, 153));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("ROTAVIRUS");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel39.setBackground(new java.awt.Color(153, 153, 153));

        dtRot1.setBackground(new java.awt.Color(204, 204, 204));
        dtRot1.setDateFormatString("dd/MM/yyyy");
        dtRot1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rr1.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rr1);
        Rr1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rr1.setForeground(new java.awt.Color(255, 255, 255));
        Rr1.setText("1º");
        Rr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rr1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtRot1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(Rr1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addComponent(Rr1)
                .addGap(3, 3, 3)
                .addComponent(dtRot1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel42.setBackground(new java.awt.Color(153, 153, 153));

        dtRot2.setBackground(new java.awt.Color(204, 204, 204));
        dtRot2.setDateFormatString("dd/MM/yyyy");
        dtRot2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rr2.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rr2);
        Rr2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rr2.setForeground(new java.awt.Color(255, 255, 255));
        Rr2.setText("2º");
        Rr2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rr2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtRot2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Rr2)
                .addGap(39, 39, 39))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addComponent(Rr2)
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

        javax.swing.GroupLayout ROTAVIRUSLayout = new javax.swing.GroupLayout(ROTAVIRUS);
        ROTAVIRUS.setLayout(ROTAVIRUSLayout);
        ROTAVIRUSLayout.setHorizontalGroup(
            ROTAVIRUSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ROTAVIRUSLayout.createSequentialGroup()
                .addGroup(ROTAVIRUSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtFuaRot1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(ROTAVIRUSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaRot2)))
        );
        ROTAVIRUSLayout.setVerticalGroup(
            ROTAVIRUSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ROTAVIRUSLayout.createSequentialGroup()
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(ROTAVIRUSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(ROTAVIRUSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuaRot1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaRot2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        SPR.setBackground(new java.awt.Color(204, 204, 204));

        jPanel44.setBackground(new java.awt.Color(153, 153, 153));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("SPR");

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

        dtSpr1.setBackground(new java.awt.Color(204, 204, 204));
        dtSpr1.setDateFormatString("dd/MM/yyyy");
        dtSpr1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rspr1.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rspr1);
        Rspr1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rspr1.setForeground(new java.awt.Color(255, 255, 255));
        Rspr1.setText("1º");
        Rspr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rspr1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtSpr1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(Rspr1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addComponent(Rspr1)
                .addGap(3, 3, 3)
                .addComponent(dtSpr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel46.setBackground(new java.awt.Color(153, 153, 153));

        dtSpr2.setBackground(new java.awt.Color(204, 204, 204));
        dtSpr2.setDateFormatString("dd/MM/yyyy");
        dtSpr2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rspr2.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rspr2);
        Rspr2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rspr2.setForeground(new java.awt.Color(255, 255, 255));
        Rspr2.setText("2 REF ");
        Rspr2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rspr2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtSpr2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(Rspr2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addComponent(Rspr2)
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

        javax.swing.GroupLayout SPRLayout = new javax.swing.GroupLayout(SPR);
        SPR.setLayout(SPRLayout);
        SPRLayout.setHorizontalGroup(
            SPRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(SPRLayout.createSequentialGroup()
                .addGroup(SPRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtFuaSpr1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(SPRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaSpr2)))
        );
        SPRLayout.setVerticalGroup(
            SPRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SPRLayout.createSequentialGroup()
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(SPRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(SPRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuaSpr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaSpr2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        AMA.setBackground(new java.awt.Color(204, 204, 204));

        jPanel51.setBackground(new java.awt.Color(153, 153, 153));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("AMA");

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel56.setBackground(new java.awt.Color(153, 153, 153));

        dtAmadu.setBackground(new java.awt.Color(204, 204, 204));
        dtAmadu.setDateFormatString("dd/MM/yyyy");
        dtAmadu.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rama.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rama);
        Rama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rama.setForeground(new java.awt.Color(255, 255, 255));
        Rama.setText("DU ");
        Rama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RamaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtAmadu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(Rama)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addComponent(Rama)
                .addGap(3, 3, 3)
                .addComponent(dtAmadu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

        javax.swing.GroupLayout AMALayout = new javax.swing.GroupLayout(AMA);
        AMA.setLayout(AMALayout);
        AMALayout.setHorizontalGroup(
            AMALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(AMALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(txtFuaAmaDu, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel56, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AMALayout.setVerticalGroup(
            AMALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AMALayout.createSequentialGroup()
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(txtFuaAmaDu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        ANTIPOLIO.setBackground(new java.awt.Color(204, 204, 204));

        jPanel57.setBackground(new java.awt.Color(153, 153, 153));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("ANTIPOLIO");

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel49)
        );

        jPanel58.setBackground(new java.awt.Color(153, 153, 153));

        dtIpv1.setBackground(new java.awt.Color(204, 204, 204));
        dtIpv1.setDateFormatString("dd/MM/yyyy");
        dtIpv1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Ripv1.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Ripv1);
        Ripv1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Ripv1.setForeground(new java.awt.Color(255, 255, 255));
        Ripv1.setText("IPV 1º");
        Ripv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ripv1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtIpv1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(Ripv1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addComponent(Ripv1)
                .addGap(3, 3, 3)
                .addComponent(dtIpv1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel59.setBackground(new java.awt.Color(153, 153, 153));

        dtIpv2.setBackground(new java.awt.Color(204, 204, 204));
        dtIpv2.setDateFormatString("dd/MM/yyyy");
        dtIpv2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Ripv2.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Ripv2);
        Ripv2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Ripv2.setForeground(new java.awt.Color(255, 255, 255));
        Ripv2.setText("IPV 2º");
        Ripv2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ripv2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtIpv2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(Ripv2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addComponent(Ripv2)
                .addGap(3, 3, 3)
                .addComponent(dtIpv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel60.setBackground(new java.awt.Color(153, 153, 153));

        dtIpv3.setBackground(new java.awt.Color(204, 204, 204));
        dtIpv3.setDateFormatString("dd/MM/yyyy");
        dtIpv3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Ripv3.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Ripv3);
        Ripv3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Ripv3.setForeground(new java.awt.Color(255, 255, 255));
        Ripv3.setText("IPV 3º");
        Ripv3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ripv3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtIpv3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Ripv3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addComponent(Ripv3)
                .addGap(3, 3, 3)
                .addComponent(dtIpv3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtFuaIpv3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaIpv3CaretUpdate(evt);
            }
        });

        txtFuaIpv1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaIpv1CaretUpdate(evt);
            }
        });

        txtFuaIpv2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaIpv2CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout ANTIPOLIOLayout = new javax.swing.GroupLayout(ANTIPOLIO);
        ANTIPOLIO.setLayout(ANTIPOLIOLayout);
        ANTIPOLIOLayout.setHorizontalGroup(
            ANTIPOLIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ANTIPOLIOLayout.createSequentialGroup()
                .addGroup(ANTIPOLIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ANTIPOLIOLayout.createSequentialGroup()
                        .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addComponent(txtFuaIpv1))
                .addGroup(ANTIPOLIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaIpv2))
                .addGap(1, 1, 1)
                .addGroup(ANTIPOLIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFuaIpv3)
                    .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        ANTIPOLIOLayout.setVerticalGroup(
            ANTIPOLIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ANTIPOLIOLayout.createSequentialGroup()
                .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(ANTIPOLIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ANTIPOLIOLayout.createSequentialGroup()
                        .addGroup(ANTIPOLIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(ANTIPOLIOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFuaIpv3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFuaIpv1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFuaIpv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ANTIPOLIOLayout.createSequentialGroup()
                        .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        PENTAVALENTE.setBackground(new java.awt.Color(204, 204, 204));

        jPanel61.setBackground(new java.awt.Color(153, 153, 153));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("PENTAVALENTE");

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel53)
        );

        jPanel62.setBackground(new java.awt.Color(153, 153, 153));

        dtPent1.setBackground(new java.awt.Color(204, 204, 204));
        dtPent1.setDateFormatString("dd/MM/yyyy");
        dtPent1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rp1.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rp1);
        Rp1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rp1.setForeground(new java.awt.Color(255, 255, 255));
        Rp1.setText("1º");
        Rp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rp1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtPent1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(Rp1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addComponent(Rp1)
                .addGap(3, 3, 3)
                .addComponent(dtPent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel63.setBackground(new java.awt.Color(153, 153, 153));

        dtPent2.setBackground(new java.awt.Color(204, 204, 204));
        dtPent2.setDateFormatString("dd/MM/yyyy");
        dtPent2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rp2.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rp2);
        Rp2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rp2.setForeground(new java.awt.Color(255, 255, 255));
        Rp2.setText("2º");
        Rp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rp2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtPent2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(Rp2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addComponent(Rp2)
                .addGap(3, 3, 3)
                .addComponent(dtPent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel64.setBackground(new java.awt.Color(153, 153, 153));

        dtPent3.setBackground(new java.awt.Color(204, 204, 204));
        dtPent3.setDateFormatString("dd/MM/yyyy");
        dtPent3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rp3.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rp3);
        Rp3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rp3.setForeground(new java.awt.Color(255, 255, 255));
        Rp3.setText("3º");
        Rp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rp3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtPent3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(Rp3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel64Layout.createSequentialGroup()
                .addComponent(Rp3)
                .addGap(3, 3, 3)
                .addComponent(dtPent3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtFuaPent3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaPent3CaretUpdate(evt);
            }
        });

        txtFuaPent1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaPent1CaretUpdate(evt);
            }
        });

        txtFuaPent2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaPent2CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout PENTAVALENTELayout = new javax.swing.GroupLayout(PENTAVALENTE);
        PENTAVALENTE.setLayout(PENTAVALENTELayout);
        PENTAVALENTELayout.setHorizontalGroup(
            PENTAVALENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PENTAVALENTELayout.createSequentialGroup()
                .addGroup(PENTAVALENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PENTAVALENTELayout.createSequentialGroup()
                        .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addComponent(txtFuaPent1))
                .addGroup(PENTAVALENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaPent2))
                .addGap(1, 1, 1)
                .addGroup(PENTAVALENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFuaPent3)
                    .addComponent(jPanel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        PENTAVALENTELayout.setVerticalGroup(
            PENTAVALENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PENTAVALENTELayout.createSequentialGroup()
                .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(PENTAVALENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PENTAVALENTELayout.createSequentialGroup()
                        .addGroup(PENTAVALENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(PENTAVALENTELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFuaPent3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFuaPent1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFuaPent2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PENTAVALENTELayout.createSequentialGroup()
                        .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        DPT.setBackground(new java.awt.Color(204, 204, 204));

        jPanel40.setBackground(new java.awt.Color(153, 153, 153));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("DPT");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel41.setBackground(new java.awt.Color(153, 153, 153));

        dtDpt1.setBackground(new java.awt.Color(204, 204, 204));
        dtDpt1.setDateFormatString("dd/MM/yyyy");
        dtDpt1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rdpt1.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rdpt1);
        Rdpt1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rdpt1.setForeground(new java.awt.Color(255, 255, 255));
        Rdpt1.setText("1º REF");
        Rdpt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rdpt1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtDpt1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Rdpt1)
                .addGap(26, 26, 26))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(Rdpt1)
                .addGap(3, 3, 3)
                .addComponent(dtDpt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel43.setBackground(new java.awt.Color(153, 153, 153));

        dtDpt2.setBackground(new java.awt.Color(204, 204, 204));
        dtDpt2.setDateFormatString("dd/MM/yyyy");
        dtDpt2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rdpt2.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rdpt2);
        Rdpt2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rdpt2.setForeground(new java.awt.Color(255, 255, 255));
        Rdpt2.setText("2º REF");
        Rdpt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rdpt2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtDpt2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(Rdpt2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addComponent(Rdpt2)
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

        javax.swing.GroupLayout DPTLayout = new javax.swing.GroupLayout(DPT);
        DPT.setLayout(DPTLayout);
        DPTLayout.setHorizontalGroup(
            DPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DPTLayout.createSequentialGroup()
                .addGroup(DPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtFuaDpt1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(DPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaDpt2)))
        );
        DPTLayout.setVerticalGroup(
            DPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DPTLayout.createSequentialGroup()
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(DPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(DPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuaDpt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaDpt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        INFLUENZA_REF.setBackground(new java.awt.Color(204, 204, 204));

        jPanel47.setBackground(new java.awt.Color(153, 153, 153));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("INFLUENZA REF.");

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel48.setBackground(new java.awt.Color(153, 153, 153));

        dtInflR1.setBackground(new java.awt.Color(204, 204, 204));
        dtInflR1.setDateFormatString("dd/MM/yyyy");
        dtInflR1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rir1.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rir1);
        Rir1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rir1.setForeground(new java.awt.Color(255, 255, 255));
        Rir1.setText("1ā 8m");
        Rir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtInflR1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(Rir1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addComponent(Rir1)
                .addGap(3, 3, 3)
                .addComponent(dtInflR1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel49.setBackground(new java.awt.Color(153, 153, 153));

        dtInflR2.setBackground(new java.awt.Color(204, 204, 204));
        dtInflR2.setDateFormatString("dd/MM/yyyy");
        dtInflR2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rir2.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rir2);
        Rir2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rir2.setForeground(new java.awt.Color(255, 255, 255));
        Rir2.setText("2º REF");
        Rir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rir2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtInflR2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(Rir2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addComponent(Rir2)
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

        javax.swing.GroupLayout INFLUENZA_REFLayout = new javax.swing.GroupLayout(INFLUENZA_REF);
        INFLUENZA_REF.setLayout(INFLUENZA_REFLayout);
        INFLUENZA_REFLayout.setHorizontalGroup(
            INFLUENZA_REFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(INFLUENZA_REFLayout.createSequentialGroup()
                .addGroup(INFLUENZA_REFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtInflR1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(INFLUENZA_REFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInflR2)))
        );
        INFLUENZA_REFLayout.setVerticalGroup(
            INFLUENZA_REFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(INFLUENZA_REFLayout.createSequentialGroup()
                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(INFLUENZA_REFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(INFLUENZA_REFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInflR1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInflR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        INFLUENZA_REF1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel50.setBackground(new java.awt.Color(153, 153, 153));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("APO REF.");

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel65.setBackground(new java.awt.Color(153, 153, 153));

        dtApoR1.setBackground(new java.awt.Color(204, 204, 204));
        dtApoR1.setDateFormatString("dd/MM/yyyy");
        dtApoR1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rapo1.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rapo1);
        Rapo1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rapo1.setForeground(new java.awt.Color(255, 255, 255));
        Rapo1.setText("1º REF");
        Rapo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rapo1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtApoR1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(Rapo1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addComponent(Rapo1)
                .addGap(3, 3, 3)
                .addComponent(dtApoR1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel66.setBackground(new java.awt.Color(153, 153, 153));

        dtApoR2.setBackground(new java.awt.Color(204, 204, 204));
        dtApoR2.setDateFormatString("dd/MM/yyyy");
        dtApoR2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        Rapo2.setBackground(new java.awt.Color(153, 153, 153));
        buttonGroup1.add(Rapo2);
        Rapo2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rapo2.setForeground(new java.awt.Color(255, 255, 255));
        Rapo2.setText("2º REF");
        Rapo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rapo2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dtApoR2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(Rapo2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addComponent(Rapo2)
                .addGap(3, 3, 3)
                .addComponent(dtApoR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtFuaApoR1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaApoR1CaretUpdate(evt);
            }
        });

        txtFuaApoR2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFuaApoR2CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout INFLUENZA_REF1Layout = new javax.swing.GroupLayout(INFLUENZA_REF1);
        INFLUENZA_REF1.setLayout(INFLUENZA_REF1Layout);
        INFLUENZA_REF1Layout.setHorizontalGroup(
            INFLUENZA_REF1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(INFLUENZA_REF1Layout.createSequentialGroup()
                .addGroup(INFLUENZA_REF1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtFuaApoR1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel65, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(INFLUENZA_REF1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaApoR2)))
        );
        INFLUENZA_REF1Layout.setVerticalGroup(
            INFLUENZA_REF1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(INFLUENZA_REF1Layout.createSequentialGroup()
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(INFLUENZA_REF1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(INFLUENZA_REF1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuaApoR1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuaApoR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
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
        btnguardar.setText("Guardar");
        btnguardar.setContentAreaFilled(false);
        btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar.setIconTextGap(30);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout OpcionesLayout = new javax.swing.GroupLayout(Opciones);
        Opciones.setLayout(OpcionesLayout);
        OpcionesLayout.setHorizontalGroup(
            OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OpcionesLayout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OpcionesLayout.setVerticalGroup(
            OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lblNino.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNino.setForeground(new java.awt.Color(102, 102, 102));
        lblNino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niño-48.png"))); // NOI18N
        lblNino.setText("NIÑOS");
        lblNino.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNino.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblNina.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNina.setForeground(new java.awt.Color(102, 102, 102));
        lblNina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niña-48.png"))); // NOI18N
        lblNina.setText("NIÑOS");
        lblNina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNina.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout VACUNASLayout = new javax.swing.GroupLayout(VACUNAS);
        VACUNAS.setLayout(VACUNASLayout);
        VACUNASLayout.setHorizontalGroup(
            VACUNASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 1383, Short.MAX_VALUE)
            .addComponent(Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(VACUNASLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(VACUNASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VACUNASLayout.createSequentialGroup()
                        .addComponent(ELAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(EJEC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(RN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(ANTIPOLIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(PENTAVALENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(VACUNASLayout.createSequentialGroup()
                        .addGroup(VACUNASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(VACUNASLayout.createSequentialGroup()
                                .addComponent(NEUMOCOCO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(INFLUENZA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(ROTAVIRUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(VACUNASLayout.createSequentialGroup()
                                .addComponent(DPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(INFLUENZA_REF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(INFLUENZA_REF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1)
                        .addComponent(SPR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(AMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(VACUNASLayout.createSequentialGroup()
                        .addComponent(lblNina, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNino, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(164, Short.MAX_VALUE))
        );
        VACUNASLayout.setVerticalGroup(
            VACUNASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VACUNASLayout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(VACUNASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNino)
                    .addComponent(lblNina))
                .addGap(18, 18, 18)
                .addGroup(VACUNASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(EJEC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(PENTAVALENTE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ANTIPOLIO, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ELAB, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(VACUNASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NEUMOCOCO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(INFLUENZA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ROTAVIRUS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SPR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AMA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(VACUNASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(INFLUENZA_REF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(INFLUENZA_REF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked

        RegistroSeguimiento.jTabbedPane1.setSelectedIndex(0);
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        habilitarRadio(true);
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed

    }//GEN-LAST:event_btneditarActionPerformed

    private void txtFuaElabCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaElabCaretUpdate
      
    }//GEN-LAST:event_txtFuaElabCaretUpdate

    private void txtFuaElabAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtFuaElabAncestorRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuaElabAncestorRemoved

    private void txtFuaEjecCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaEjecCaretUpdate
      
    }//GEN-LAST:event_txtFuaEjecCaretUpdate

    private void txtFuaBcgCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaBcgCaretUpdate
      
    }//GEN-LAST:event_txtFuaBcgCaretUpdate

    private void txtFuaHvbCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaHvbCaretUpdate

    }//GEN-LAST:event_txtFuaHvbCaretUpdate

    private void txtFuaIpv1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaIpv1CaretUpdate

    }//GEN-LAST:event_txtFuaIpv1CaretUpdate

    private void txtFuaIpv2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaIpv2CaretUpdate
 
    }//GEN-LAST:event_txtFuaIpv2CaretUpdate

    private void txtFuaIpv3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaIpv3CaretUpdate

    }//GEN-LAST:event_txtFuaIpv3CaretUpdate

    private void txtFuaPent1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaPent1CaretUpdate

    }//GEN-LAST:event_txtFuaPent1CaretUpdate

    private void txtFuaPent2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaPent2CaretUpdate

    }//GEN-LAST:event_txtFuaPent2CaretUpdate

    private void txtFuaPent3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaPent3CaretUpdate

    }//GEN-LAST:event_txtFuaPent3CaretUpdate

    private void txtFuaNeumo1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaNeumo1CaretUpdate

    }//GEN-LAST:event_txtFuaNeumo1CaretUpdate

    private void txtFuaNeumo2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaNeumo2CaretUpdate
 
    }//GEN-LAST:event_txtFuaNeumo2CaretUpdate

    private void txtFuaNeumo3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaNeumo3CaretUpdate
 
    }//GEN-LAST:event_txtFuaNeumo3CaretUpdate

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

    private void txtFuaApoR1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaApoR1CaretUpdate

    }//GEN-LAST:event_txtFuaApoR1CaretUpdate

    private void txtFuaApoR2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFuaApoR2CaretUpdate

    }//GEN-LAST:event_txtFuaApoR2CaretUpdate

    private void txtFuaAmaDuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFuaAmaDuMouseClicked
       
    }//GEN-LAST:event_txtFuaAmaDuMouseClicked

    private void txtFuaElabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFuaElabActionPerformed

    }//GEN-LAST:event_txtFuaElabActionPerformed

    private void txtFuaEjecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFuaEjecActionPerformed

    }//GEN-LAST:event_txtFuaEjecActionPerformed

    private void txtFuaElabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFuaElabMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuaElabMouseClicked

    private void txtFuaEjecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFuaEjecMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuaEjecMouseClicked

    private void Rp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rp1ActionPerformed
        if(Rp1.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaPent1.setEnabled(true);
            dtPent1.setEnabled(true);
            txtFuaPent1.requestFocus();
            txtFuaPent1.setEditable(true);  
        }
    }//GEN-LAST:event_Rp1ActionPerformed

    private void Rdpt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rdpt2ActionPerformed
      if(Rdpt2.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaDpt2.setEnabled(true);
            dtDpt2.setEnabled(true);
            txtFuaDpt2.requestFocus();
            txtFuaDpt2.setEditable(true);  
        }
    }//GEN-LAST:event_Rdpt2ActionPerformed

    private void Rir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rir2ActionPerformed
        if(Rir2.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtInflR2.setEnabled(true);
            dtInflR2.setEnabled(true);
            txtInflR2.requestFocus();
            txtInflR2.setEditable(true);  
        }
    }//GEN-LAST:event_Rir2ActionPerformed

    private void Rapo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rapo2ActionPerformed
        if(Rapo2.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaApoR2.setEnabled(true);
            dtApoR2.setEnabled(true);
            txtFuaApoR2.requestFocus();
            txtFuaApoR2.setEditable(true);  
        }
    }//GEN-LAST:event_Rapo2ActionPerformed

    private void RelabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RelabMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_RelabMouseClicked

    private void RelabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RelabActionPerformed
        if(dtElab.getDate()==null){
            if(Relab.isSelected()){
                habilitarDatos(false);
                habilitarRadio(false);
                txtFuaElab.setEnabled(true);
                dtElab.setEnabled(true);
                txtFuaElab.requestFocus();
                txtFuaElab.setEditable(true); 
            }
        } else {
            Relab.setEnabled(false);
        }
    }//GEN-LAST:event_RelabActionPerformed

    private void RejecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RejecActionPerformed
         if(Rejec.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaEjec.setEnabled(true);
            dtEjec.setEnabled(true);
            txtFuaEjec.requestFocus();
            txtFuaEjec.setEditable(true); 
            
        }
    }//GEN-LAST:event_RejecActionPerformed

    private void RbcgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbcgActionPerformed
           if(Rbcg.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaBcg.setEnabled(true);
            dtBcg.setEnabled(true);
            txtFuaBcg.requestFocus();
            txtFuaBcg.setEditable(true); 
            
        }
    }//GEN-LAST:event_RbcgActionPerformed

    private void RhvbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RhvbActionPerformed
            if(Rhvb.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaHvb.setEnabled(true);
            dtHvb.setEnabled(true);
            txtFuaHvb.requestFocus();
            txtFuaHvb.setEditable(true);  
            }
    }//GEN-LAST:event_RhvbActionPerformed

    private void Rp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rp3ActionPerformed
        if(Rp3.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaPent3.setEnabled(true);
            dtPent3.setEnabled(true);
            txtFuaPent3.requestFocus();
            txtFuaPent3.setEditable(true);  
        }
    }//GEN-LAST:event_Rp3ActionPerformed

    private void Rp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rp2ActionPerformed
        if(Rp2.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaPent2.setEnabled(true);
            dtPent2.setEnabled(true);
            txtFuaPent2.requestFocus();
            txtFuaPent2.setEditable(true);  
        }
    }//GEN-LAST:event_Rp2ActionPerformed

    private void Ripv3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ripv3ActionPerformed
        if(Ripv3.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaIpv3.setEnabled(true);
            dtIpv1.setEnabled(true);
            txtFuaIpv3.requestFocus();
            txtFuaIpv3.setEditable(true);  
        }
    }//GEN-LAST:event_Ripv3ActionPerformed

    private void Ripv2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ripv2ActionPerformed
        if(Ripv2.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaIpv2.setEnabled(true);
            dtIpv2.setEnabled(true);
            txtFuaIpv2.requestFocus();
            txtFuaIpv2.setEditable(true);  
        }
    }//GEN-LAST:event_Ripv2ActionPerformed

    private void Ripv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ripv1ActionPerformed
        if(Ripv1.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaIpv1.setEnabled(true);
            dtIpv1.setEnabled(true);
            txtFuaIpv1.requestFocus();
            txtFuaIpv1.setEditable(true);  
        }
    }//GEN-LAST:event_Ripv1ActionPerformed

    private void Rn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rn1ActionPerformed
        if(Rn1.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaNeumo1.setEnabled(true);
            dtNeumo1.setEnabled(true);
            txtFuaNeumo1.requestFocus();
            txtFuaNeumo1.setEditable(true);  
        }
    }//GEN-LAST:event_Rn1ActionPerformed

    private void Rn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rn2ActionPerformed
        if(Rn2.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaNeumo2.setEnabled(true);
            dtNeumo2.setEnabled(true);
            txtFuaNeumo2.requestFocus();
            txtFuaNeumo2.setEditable(true);  
        }
    }//GEN-LAST:event_Rn2ActionPerformed

    private void Rn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rn3ActionPerformed
        if(Rn3.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaNeumo3.setEnabled(true);
            dtNeumo3.setEnabled(true);
            txtFuaNeumo3.requestFocus();
            txtFuaNeumo3.setEditable(true);  
        }
    }//GEN-LAST:event_Rn3ActionPerformed

    private void Ri1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ri1ActionPerformed
        if(Ri1.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaInfl1.setEnabled(true);
            dtInfl1.setEnabled(true);
            txtFuaInfl1.requestFocus();
            txtFuaInfl1.setEditable(true);  
        }
    }//GEN-LAST:event_Ri1ActionPerformed

    private void Ri2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ri2ActionPerformed
         if(Ri2.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaInfl2.setEnabled(true);
            dtInfl2.setEnabled(true);
            txtFuaInfl2.requestFocus();
            txtFuaInfl2.setEditable(true);  
        }
    }//GEN-LAST:event_Ri2ActionPerformed

    private void Rr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rr1ActionPerformed
         if(Rr1.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaRot1.setEnabled(true);
            dtRot1.setEnabled(true);
            txtFuaRot1.requestFocus();
            txtFuaRot1.setEditable(true);  
        }
    }//GEN-LAST:event_Rr1ActionPerformed

    private void Rr2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rr2ActionPerformed
        if(Rr2.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaRot2.setEnabled(true);
            dtRot2.setEnabled(true);
            txtFuaRot2.requestFocus();
            txtFuaRot2.setEditable(true);  
        }
    }//GEN-LAST:event_Rr2ActionPerformed

    private void Rspr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rspr1ActionPerformed
        if(Rspr1.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaSpr1.setEnabled(true);
            dtSpr1.setEnabled(true);
            txtFuaSpr1.requestFocus();
            txtFuaSpr1.setEditable(true);  
        }
    }//GEN-LAST:event_Rspr1ActionPerformed

    private void Rspr2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rspr2ActionPerformed
        if(Rspr2.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaSpr2.setEnabled(true);
            dtSpr2.setEnabled(true);
            txtFuaSpr2.requestFocus();
            txtFuaSpr2.setEditable(true);  
        }
    }//GEN-LAST:event_Rspr2ActionPerformed

    private void RamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RamaActionPerformed
        if(Rama.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaAmaDu.setEnabled(true);
            dtAmadu.setEnabled(true);
            txtFuaAmaDu.requestFocus();
            txtFuaAmaDu.setEditable(true);  
        }
    }//GEN-LAST:event_RamaActionPerformed

    private void Rdpt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rdpt1ActionPerformed
        if(Rdpt1.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaDpt1.setEnabled(true);
            dtDpt1.setEnabled(true);
            txtFuaDpt1.requestFocus();
            txtFuaDpt1.setEditable(true);  
        }
    }//GEN-LAST:event_Rdpt1ActionPerformed

    private void Rir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rir1ActionPerformed
        if(Rir1.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtInflR1.setEnabled(true);
            dtInflR1.setEnabled(true);
            txtInflR1.requestFocus();
            txtInflR1.setEditable(true);  
        }
    }//GEN-LAST:event_Rir1ActionPerformed

    private void Rapo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Rapo1ActionPerformed
        if(Rapo1.isSelected()){
            habilitarDatos(false);
            habilitarRadio(false);
            txtFuaApoR1.setEnabled(true);
            dtApoR1.setEnabled(true);
            txtFuaApoR1.requestFocus();
            txtFuaApoR1.setEditable(true);  
        }
    }//GEN-LAST:event_Rapo1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AMA;
    private javax.swing.JPanel ANTIPOLIO;
    private javax.swing.JPanel DPT;
    private javax.swing.JPanel EJEC;
    private javax.swing.JPanel ELAB;
    private javax.swing.JPanel INFLUENZA;
    private javax.swing.JPanel INFLUENZA_REF;
    private javax.swing.JPanel INFLUENZA_REF1;
    private javax.swing.JPanel NEUMOCOCO;
    private javax.swing.JPanel Opciones;
    private javax.swing.JPanel PENTAVALENTE;
    private javax.swing.JPanel RN;
    private javax.swing.JPanel ROTAVIRUS;
    private javax.swing.JRadioButton Rama;
    private javax.swing.JRadioButton Rapo1;
    private javax.swing.JRadioButton Rapo2;
    private javax.swing.JRadioButton Rbcg;
    private javax.swing.JRadioButton Rdpt1;
    private javax.swing.JRadioButton Rdpt2;
    private javax.swing.JRadioButton Rejec;
    private javax.swing.JRadioButton Relab;
    private javax.swing.JRadioButton Rhvb;
    private javax.swing.JRadioButton Ri1;
    private javax.swing.JRadioButton Ri2;
    private javax.swing.JRadioButton Ripv1;
    private javax.swing.JRadioButton Ripv2;
    private javax.swing.JRadioButton Ripv3;
    private javax.swing.JRadioButton Rir1;
    private javax.swing.JRadioButton Rir2;
    private javax.swing.JRadioButton Rn1;
    private javax.swing.JRadioButton Rn2;
    private javax.swing.JRadioButton Rn3;
    private javax.swing.JRadioButton Rp1;
    private javax.swing.JRadioButton Rp2;
    private javax.swing.JRadioButton Rp3;
    private javax.swing.JRadioButton Rr1;
    private javax.swing.JRadioButton Rr2;
    private javax.swing.JRadioButton Rspr1;
    private javax.swing.JRadioButton Rspr2;
    private javax.swing.JPanel SPR;
    private javax.swing.JPanel VACUNAS;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btnguardar;
    private javax.swing.ButtonGroup buttonGroup1;
    public static com.toedter.calendar.JDateChooser dtAmadu;
    public static com.toedter.calendar.JDateChooser dtApoR1;
    public static com.toedter.calendar.JDateChooser dtApoR2;
    public static com.toedter.calendar.JDateChooser dtBcg;
    public static com.toedter.calendar.JDateChooser dtDpt1;
    public static com.toedter.calendar.JDateChooser dtDpt2;
    public static com.toedter.calendar.JDateChooser dtEjec;
    public static com.toedter.calendar.JDateChooser dtElab;
    public static com.toedter.calendar.JDateChooser dtHvb;
    public static com.toedter.calendar.JDateChooser dtInfl1;
    public static com.toedter.calendar.JDateChooser dtInfl2;
    public static com.toedter.calendar.JDateChooser dtInflR1;
    public static com.toedter.calendar.JDateChooser dtInflR2;
    public static com.toedter.calendar.JDateChooser dtIpv1;
    public static com.toedter.calendar.JDateChooser dtIpv2;
    public static com.toedter.calendar.JDateChooser dtIpv3;
    public static com.toedter.calendar.JDateChooser dtNeumo1;
    public static com.toedter.calendar.JDateChooser dtNeumo2;
    public static com.toedter.calendar.JDateChooser dtNeumo3;
    public static com.toedter.calendar.JDateChooser dtPent1;
    public static com.toedter.calendar.JDateChooser dtPent2;
    public static com.toedter.calendar.JDateChooser dtPent3;
    public static com.toedter.calendar.JDateChooser dtRot1;
    public static com.toedter.calendar.JDateChooser dtRot2;
    public static com.toedter.calendar.JDateChooser dtSpr1;
    public static com.toedter.calendar.JDateChooser dtSpr2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
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
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    public static javax.swing.JLabel lblNina;
    public static javax.swing.JLabel lblNino;
    public static javax.swing.JTextField txtFuaAmaDu;
    public static javax.swing.JTextField txtFuaApoR1;
    public static javax.swing.JTextField txtFuaApoR2;
    public static javax.swing.JTextField txtFuaBcg;
    public static javax.swing.JTextField txtFuaDpt1;
    public static javax.swing.JTextField txtFuaDpt2;
    public static javax.swing.JTextField txtFuaEjec;
    public static javax.swing.JTextField txtFuaElab;
    public static javax.swing.JTextField txtFuaHvb;
    public static javax.swing.JTextField txtFuaInfl1;
    public static javax.swing.JTextField txtFuaInfl2;
    public static javax.swing.JTextField txtFuaIpv1;
    public static javax.swing.JTextField txtFuaIpv2;
    public static javax.swing.JTextField txtFuaIpv3;
    public static javax.swing.JTextField txtFuaNeumo1;
    public static javax.swing.JTextField txtFuaNeumo2;
    public static javax.swing.JTextField txtFuaNeumo3;
    public static javax.swing.JTextField txtFuaPent1;
    public static javax.swing.JTextField txtFuaPent2;
    public static javax.swing.JTextField txtFuaPent3;
    public static javax.swing.JTextField txtFuaRot1;
    public static javax.swing.JTextField txtFuaRot2;
    public static javax.swing.JTextField txtFuaSpr1;
    public static javax.swing.JTextField txtFuaSpr2;
    public static javax.swing.JTextField txtInflR1;
    public static javax.swing.JTextField txtInflR2;
    // End of variables declaration//GEN-END:variables
}
