/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Caja.Caja_Nomenclatura;
import modelos.ConsultorioEx.ConsultorioExConsultorio;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import servicios.Conexion;

/**
 *
 * @author MYS1
 */
public class Consultorio extends javax.swing.JFrame {
DefaultTableModel m;
    public Consultorio() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
      
        this.getContentPane().setBackground(Color.WHITE);//color blanco del formulario
        ConsultorioExConsultorio consultorio2 = new ConsultorioExConsultorio();
        consultorio2.inicializarTablaConsultorios(tbConsultorios);
        consultorio2.listarConsultorios("", tbConsultorios);
        //BOTON CERRAR
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        // BOTON ESCAPE (ESC)
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();
            }
        });
        cerrar();
        LISTAR_Unidad();
        pnlMensaje.setVisible(false);
        lblMant.setVisible(false);
        txtID.setVisible(false);
        b2.setEnabled(false);
        txtDescripcion.setEditable(false);
        Unidad.setLocationRelativeTo(null);//en el centro
        Unidad.getContentPane().setBackground(Color.WHITE); 
        tbConsultorios.getTableHeader().setVisible(false);
        tbConsultorios.setTableHeader(null);
    }
    
    public void cerrar (){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    dispose();
                }
    });
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void limpiar(){
        lblMant.setText("I");
        txtID.setText("");
        txtNumero.setText("");
        txtDescripcion.setText("");
    }
    
    public void habilitarCampos(boolean opcion){
        txtNumero.setEditable(opcion);
        txtDescripcion.setEditable(opcion);
        b2.setEnabled(opcion);
    }
        public void BuscarUnidad(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupos1.setModel(new DefaultTableModel());
             String titulos[]={"Área","","Servicio"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Nomenclatura obj=new Caja_Nomenclatura();
                    consulta="exec buscarunidad ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar1.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);

                m.addRow(fila);
                c++;
            }
            tb_Grupos1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupos1.setRowSorter(elQueOrdena);
            this.tb_Grupos1.setModel(m);

            formatoventanas1();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
            public void LISTAR_Unidad(){
    try {
             String titulos[]={"Área","","Servicio"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Conexion obj = new Conexion();  
        String consulta="exec listaruinidad";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1);
                fila[1]=r.getString(2); 
                fila[2]=r.getString(3); 

                    m.addRow(fila);
                    c++;
            }
        
            tb_Grupos1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupos1.setRowSorter(elQueOrdena);
            this.tb_Grupos1.setModel(m);
           formatoventanas1();
            
    } catch (Exception e) {
    }
}
    public void formatoventanas1(){
    tb_Grupos1.getColumnModel().getColumn(0).setPreferredWidth(200);
    tb_Grupos1.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Grupos1.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_Grupos1.getColumnModel().getColumn(2).setPreferredWidth(200);
    tb_Grupos1.setRowHeight(45);
}
    
    public void guardarConsultorio(){
        try {
            ConsultorioExConsultorio consultorio1 = new ConsultorioExConsultorio();
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
            AdmisionEmergenciaCabecera adEmerCab5 = new AdmisionEmergenciaCabecera();
            
                String numero = txtNumero.getText();
                String descripcion = txtDescripcion.getText();
                String cod_usu = adEmerCab5.codUsuario(lblusu.getText());
                int area = Integer.parseInt(unior.getText());
//                int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
//                               "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
//                if(guardar == 0){
                    consultorio1.setNumero(numero);
                    consultorio1.setDescripcion(descripcion);
                    consultorio1.setUsuario(cod_usu);
                    consultorio1.setAR_ID(area);
                    if(consultorio1.mantenimientoConsultorioExConsultorio(lblMant.getText())==true){
                        String id = consultorio1.consultorioID();
                        System.out.println("ID: " + id);
                        lblMensaje.setText("Consultorio guardado con éxito");
                        consultorio1.listarConsultorios("", tbConsultorios);
                        btnGuardar.setEnabled(false);
                        btnEliminar.setEnabled(false);
                        btnModificar.setEnabled(false);
                        limpiar();
                        habilitarCampos(false);
                        lblMant.setText("I");
                        txtID.setText("");
                    }
//                }else{
//                        JOptionPane.showMessageDialog(this, "No se realizó ninguú registro");
//                }
        } catch (Exception e) {
            System.out.println("Error: guardarConsultorio" + e.getMessage());
        }
    }
    
    public void modificarConsultorio(){
        try {
            ConsultorioExConsultorio consultorio1 = new ConsultorioExConsultorio();
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png"));
                String numero = txtNumero.getText();
                String descripcion = txtDescripcion.getText();
                    consultorio1.setId(Integer.parseInt(txtID.getText()));
                    consultorio1.setNumero(numero);
                    consultorio1.setDescripcion(descripcion);
                    if(consultorio1.mantenimientoConsultorioExConsultorio(lblMant.getText())==true){
                        String id = consultorio1.consultorioID();
                        System.out.println("ID: " + id);
                        lblMensaje.setText("Consultorio modificado con éxito");
                        consultorio1.listarConsultorios("", tbConsultorios);
                        btnGuardar.setEnabled(false);
                        btnEliminar.setEnabled(false);
                        btnModificar.setEnabled(false);
                        limpiar();
                        habilitarCampos(false);
                        lblMant.setText("I");
                        txtID.setText("");
                    }
        } catch (Exception e) {
            System.out.println("Error: modificarConsultorio" + e.getMessage());
        }
    }
    
    public void eliminarConsultorio(){
        ConsultorioExConsultorio consultorio3 = new ConsultorioExConsultorio();
            consultorio3.setId(Integer.parseInt(txtID.getText()));
            if(consultorio3.mantenimientoConsultorioExConsultorio("E")){
                lblMensaje.setText("Consultorio eliminado");
                consultorio3.listarConsultorios("", tbConsultorios);
                btnEliminar.setEnabled(false);
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(false);
                limpiar();
                habilitarCampos(false);
            } else{
                lblMensaje.setText("Ocurrió un error al eliminar");
                consultorio3.listarConsultorios("", tbConsultorios);
                btnEliminar.setEnabled(false);
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(false);
                limpiar();
                habilitarCampos(false);
            }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Unidad = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        txtBuscar1 = new javax.swing.JTextField();
        btnBuscarPaciente4 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_Grupos1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel1 = new javax.swing.JPanel();
            btnNuevo = new javax.swing.JButton();
            btnModificar = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            btnEliminar = new javax.swing.JButton();
            lblusu = new javax.swing.JLabel();
            lblMant = new javax.swing.JLabel();
            txtID = new javax.swing.JTextField();
            jLabel57 = new javax.swing.JLabel();
            pnlMensaje = new javax.swing.JPanel();
            lblMensaje = new javax.swing.JLabel();
            btnSi = new javax.swing.JButton();
            btnNo = new javax.swing.JButton();
            jPanel2 = new javax.swing.JPanel();
            jLabel4 = new javax.swing.JLabel();
            jScrollPane1 = new javax.swing.JScrollPane();
            txtDescripcion = new javax.swing.JEditorPane();
            txtNumero = new javax.swing.JTextField();
            jLabel2 = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            jPanel3 = new javax.swing.JPanel();
            jLabel3 = new javax.swing.JLabel();
            jScrollPane3 = new javax.swing.JScrollPane();
            tbConsultorios = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel35 = new javax.swing.JPanel();
                jLabel40 = new javax.swing.JLabel();
                jLabel47 = new javax.swing.JLabel();
                jLabel41 = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                panelCPT2 = new javax.swing.JPanel();
                txtuo = new javax.swing.JTextField();
                b2 = new javax.swing.JButton();
                unior = new javax.swing.JLabel();
                jPanel5 = new javax.swing.JPanel();
                jLabel33 = new javax.swing.JLabel();

                Unidad.setAlwaysOnTop(true);
                Unidad.setMinimumSize(new java.awt.Dimension(591, 419));
                Unidad.setResizable(false);

                jPanel7.setBackground(new java.awt.Color(0, 153, 102));

                jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel5.setForeground(new java.awt.Color(255, 255, 255));
                jLabel5.setText("Área");

                jPanel29.setBackground(new java.awt.Color(255, 255, 255));

                txtBuscar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtBuscar1.setBorder(null);
                txtBuscar1.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscar1CaretUpdate(evt);
                    }
                });
                txtBuscar1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtBuscar1ActionPerformed(evt);
                    }
                });
                txtBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscar1KeyPressed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
                jPanel29.setLayout(jPanel29Layout);
                jPanel29Layout.setHorizontalGroup(
                    jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 5, Short.MAX_VALUE))
                );
                jPanel29Layout.setVerticalGroup(
                    jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );

                btnBuscarPaciente4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                btnBuscarPaciente4.setContentAreaFilled(false);
                btnBuscarPaciente4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarPaciente4.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarPaciente4ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(305, Short.MAX_VALUE))
                );
                jPanel7Layout.setVerticalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarPaciente4))
                        .addGap(412, 412, 412))
                );

                jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                tb_Grupos1.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
                tb_Grupos1.setGridColor(new java.awt.Color(255, 255, 255));
                tb_Grupos1.setRowHeight(25);
                tb_Grupos1.setSelectionBackground(new java.awt.Color(102, 102, 102));
                tb_Grupos1.getTableHeader().setReorderingAllowed(false);
                tb_Grupos1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_Grupos1MouseClicked(evt);
                    }
                });
                tb_Grupos1.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_Grupos1KeyPressed(evt);
                    }
                });
                jScrollPane4.setViewportView(tb_Grupos1);

                javax.swing.GroupLayout UnidadLayout = new javax.swing.GroupLayout(Unidad.getContentPane());
                Unidad.getContentPane().setLayout(UnidadLayout);
                UnidadLayout.setHorizontalGroup(
                    UnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                );
                UnidadLayout.setVerticalGroup(
                    UnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UnidadLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(0, 153, 102));
                jPanel1.setPreferredSize(new java.awt.Dimension(292, 437));

                btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                btnNuevo.setText("Nuevo");
                btnNuevo.setToolTipText("");
                btnNuevo.setContentAreaFilled(false);
                btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnNuevo.setIconTextGap(30);
                btnNuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnNuevoActionPerformed(evt);
                    }
                });

                btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnModificar.setForeground(new java.awt.Color(240, 240, 240));
                btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
                btnModificar.setText("Editar");
                btnModificar.setContentAreaFilled(false);
                btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnModificar.setEnabled(false);
                btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnModificar.setIconTextGap(30);
                btnModificar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnModificar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnModificarActionPerformed(evt);
                    }
                });

                btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
                btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
                btnGuardar.setText("Guardar");
                btnGuardar.setContentAreaFilled(false);
                btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnGuardar.setEnabled(false);
                btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnGuardar.setIconTextGap(30);
                btnGuardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGuardarActionPerformed(evt);
                    }
                });

                btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnEliminar.setForeground(new java.awt.Color(240, 240, 240));
                btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
                btnEliminar.setText("Eliminar");
                btnEliminar.setContentAreaFilled(false);
                btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnEliminar.setEnabled(false);
                btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnEliminar.setIconTextGap(30);
                btnEliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnEliminarActionPerformed(evt);
                    }
                });

                lblusu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                lblusu.setForeground(new java.awt.Color(255, 255, 255));
                lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
                lblusu.setText("Silvana");

                lblMant.setText("Mantenimiento");

                txtID.setEnabled(false);
                txtID.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtIDCaretUpdate(evt);
                    }
                });

                jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                jLabel57.setForeground(new java.awt.Color(255, 255, 255));
                jLabel57.setText("<html>Consultorios<span style=\"font-size:'14px'\"><br>Consultorios Externos</br></span></html>");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(lblMant, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(44, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMant))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblusu)
                        .addContainerGap())
                );

                pnlMensaje.setBackground(new java.awt.Color(255, 153, 51));

                lblMensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                lblMensaje.setForeground(new java.awt.Color(255, 255, 255));
                lblMensaje.setText("Desea Actualizar el Registro ?");

                btnSi.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
                btnSi.setForeground(new java.awt.Color(240, 240, 240));
                btnSi.setText("Si");
                btnSi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                btnSi.setContentAreaFilled(false);
                btnSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnSi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnSi.setIconTextGap(30);
                btnSi.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnSiActionPerformed(evt);
                    }
                });

                btnNo.setForeground(new java.awt.Color(240, 240, 240));
                btnNo.setText("No");
                btnNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                btnNo.setContentAreaFilled(false);
                btnNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnNo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnNo.setIconTextGap(30);
                btnNo.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnNoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout pnlMensajeLayout = new javax.swing.GroupLayout(pnlMensaje);
                pnlMensaje.setLayout(pnlMensajeLayout);
                pnlMensajeLayout.setHorizontalGroup(
                    pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMensajeLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblMensaje)
                        .addGap(54, 54, 54)
                        .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                pnlMensajeLayout.setVerticalGroup(
                    pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMensajeLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMensaje)
                            .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                jLabel4.setText("Numero");

                jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                txtDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtDescripcionKeyReleased(evt);
                    }
                });
                jScrollPane1.setViewportView(txtDescripcion);

                txtNumero.setEditable(false);
                txtNumero.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtNumeroCaretUpdate(evt);
                    }
                });

                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(102, 102, 102));
                jLabel2.setText("Nuevo Registro");

                jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(51, 51, 51));
                jLabel6.setText("Descripcion");

                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(102, 102, 102));
                jLabel3.setText(" Consultorios Registrados");

                jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                tbConsultorios.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                tbConsultorios.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {

                    }
                ));
                tbConsultorios.setGridColor(new java.awt.Color(255, 255, 255));
                tbConsultorios.setRowHeight(25);
                tbConsultorios.setSelectionBackground(new java.awt.Color(102, 102, 102));
                tbConsultorios.getTableHeader().setReorderingAllowed(false);
                tbConsultorios.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbConsultoriosMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbConsultoriosMousePressed(evt);
                    }
                });
                tbConsultorios.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbConsultoriosKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        tbConsultoriosKeyReleased(evt);
                    }
                });
                jScrollPane3.setViewportView(tbConsultorios);

                jPanel35.setBackground(new java.awt.Color(0, 153, 102));
                jPanel35.setPreferredSize(new java.awt.Dimension(0, 2));

                javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
                jPanel35.setLayout(jPanel35Layout);
                jPanel35Layout.setHorizontalGroup(
                    jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 450, Short.MAX_VALUE)
                );
                jPanel35Layout.setVerticalGroup(
                    jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 2, Short.MAX_VALUE)
                );

                jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                jLabel40.setForeground(new java.awt.Color(51, 51, 51));
                jLabel40.setText("Descripción");

                jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                jLabel47.setForeground(new java.awt.Color(51, 51, 51));
                jLabel47.setText(" Nº");

                jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                jLabel41.setForeground(new java.awt.Color(51, 51, 51));
                jLabel41.setText("Área");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addContainerGap())
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)))
                );
                jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                );

                jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel7.setForeground(new java.awt.Color(51, 51, 51));
                jLabel7.setText("Área");

                panelCPT2.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtuo.setEditable(false);
                txtuo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtuo.setForeground(new java.awt.Color(51, 51, 51));
                txtuo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtuo.setBorder(null);
                txtuo.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtuoCaretUpdate(evt);
                    }
                });

                b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                b2.setContentAreaFilled(false);
                b2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                b2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        b2ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT2Layout = new javax.swing.GroupLayout(panelCPT2);
                panelCPT2.setLayout(panelCPT2Layout);
                panelCPT2Layout.setHorizontalGroup(
                    panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtuo, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                );
                panelCPT2Layout.setVerticalGroup(
                    panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT2Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtuo, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                unior.setText("jLabel1");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(unior)
                                    .addComponent(jLabel6)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel7))
                                        .addGap(38, 38, 38)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 64, Short.MAX_VALUE)))
                        .addGap(66, 66, 66))
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7))
                            .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(unior)
                        .addContainerGap(42, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                jPanel5.setBackground(new java.awt.Color(43, 43, 43));
                jPanel5.setPreferredSize(new java.awt.Dimension(929, 115));

                jLabel33.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                jLabel33.setForeground(new java.awt.Color(255, 255, 255));
                jLabel33.setText("Registro");

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel5Layout.setVerticalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(67, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
                            .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            limpiar();
            habilitarCampos(true);
            btnGuardar.setEnabled(true);
            pnlMensaje.setVisible(false);
        } catch (Exception e) {
            System.out.println("Error: btnNuevo" + e.getMessage());
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        lblMant.setText("U");
        btnGuardar.setEnabled(true);
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        habilitarCampos(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(lblMant.getText().equals("I")){
            if(txtNumero.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese un número de consultorio");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("¿Seguro que desea GUARDAR los datos?");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
            }
        }else{
            if(txtNumero.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese un número de consultorio");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("¿Seguro que desea MODIFICAR los datos?");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
            }
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        lblMant.setText("E");
        pnlMensaje.setVisible(true);
        lblMensaje.setText("Desea ELIMINAR el registro?");
        btnSi.setVisible(true);
        btnNo.setVisible(true);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtIDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIDCaretUpdate
        
    }//GEN-LAST:event_txtIDCaretUpdate

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        if(lblMant.getText().equals("I")){
            guardarConsultorio();
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        } else
        if(lblMant.getText().equals("U")){
            modificarConsultorio();
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        } else
        if(lblMant.getText().equals("E")){
            eliminarConsultorio();
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        }
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
        if(lblMant.getText().equals("I")){
            lblMensaje.setText("No se realizó ningún registro");
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        } else
        if(lblMant.getText().equals("U")){
            lblMensaje.setText("No se realizó ninguna modificación");
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        } else
        if(lblMant.getText().equals("E")){
            lblMensaje.setText("No se eliminó ningún registro");
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        }    
    }//GEN-LAST:event_btnNoActionPerformed

    private void txtNumeroCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNumeroCaretUpdate

    }//GEN-LAST:event_txtNumeroCaretUpdate

    private void txtBuscar1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar1CaretUpdate
        BuscarUnidad();
    }//GEN-LAST:event_txtBuscar1CaretUpdate

    private void txtBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar1ActionPerformed

    private void txtBuscar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
            tb_Grupos1.getSelectionModel().setSelectionInterval (0,0) ;
            tb_Grupos1.requestFocus();
        }
    }//GEN-LAST:event_txtBuscar1KeyPressed

    private void btnBuscarPaciente4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente4ActionPerformed

    private void tb_Grupos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupos1MouseClicked

        int fila=tb_Grupos1.getSelectedRow();
        if(evt.getClickCount()==2){
            Unidad.dispose();
            txtuo.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 0)));
            unior.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 1)));
            txtuo.setEditable(true);
            txtuo.setEditable(false);

        }
    }//GEN-LAST:event_tb_Grupos1MouseClicked

    private void tb_Grupos1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupos1KeyPressed

        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupos1.getSelectedRow();
            Unidad.dispose();
            txtuo.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 0)));
            unior.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 1)));
            txtuo.setEditable(true);
            txtuo.setEditable(false);
   

        }
    }//GEN-LAST:event_tb_Grupos1KeyPressed

    private void txtuoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtuoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtuoCaretUpdate

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        Unidad.setVisible(true);
    }//GEN-LAST:event_b2ActionPerformed

    private void tbConsultoriosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbConsultoriosKeyReleased
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            int fila = tbConsultorios.getSelectedRow();
            txtID.setText(String.valueOf(tbConsultorios.getValueAt(fila, 0)));
            txtNumero.setText(String.valueOf(tbConsultorios.getValueAt(fila, 1)));
            txtDescripcion.setText(String.valueOf(tbConsultorios.getValueAt(fila, 2)));
            txtuo.setText(String.valueOf(tbConsultorios.getValueAt(fila, 3)));
            unior.setText(String.valueOf(tbConsultorios.getValueAt(fila, 4)));
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(false);
        }
    }//GEN-LAST:event_tbConsultoriosKeyReleased

    private void tbConsultoriosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbConsultoriosKeyPressed

    }//GEN-LAST:event_tbConsultoriosKeyPressed

    private void tbConsultoriosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbConsultoriosMousePressed

    }//GEN-LAST:event_tbConsultoriosMousePressed

    private void tbConsultoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbConsultoriosMouseClicked
        if(evt.getClickCount()==1){
            int fila = tbConsultorios.getSelectedRow();
            txtID.setText(String.valueOf(tbConsultorios.getValueAt(fila, 0)));
            txtNumero.setText(String.valueOf(tbConsultorios.getValueAt(fila, 1)));
            txtDescripcion.setText(String.valueOf(tbConsultorios.getValueAt(fila, 2)));
            txtuo.setText(String.valueOf(tbConsultorios.getValueAt(fila, 3)));
            unior.setText(String.valueOf(tbConsultorios.getValueAt(fila, 4)));
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(false);
        }
    }//GEN-LAST:event_tbConsultoriosMouseClicked

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
       txtDescripcion.setText(txtDescripcion.getText().toUpperCase());
    }//GEN-LAST:event_txtDescripcionKeyReleased

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
            java.util.logging.Logger.getLogger(Consultorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consultorio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Unidad;
    private javax.swing.JButton b2;
    private javax.swing.JButton btnBuscarPaciente4;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSi;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel35;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblusu;
    private javax.swing.JPanel panelCPT2;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JTable tbConsultorios;
    private javax.swing.JTable tb_Grupos1;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JEditorPane txtDescripcion;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNumero;
    public static javax.swing.JTextField txtuo;
    private javax.swing.JLabel unior;
    // End of variables declaration//GEN-END:variables
}
