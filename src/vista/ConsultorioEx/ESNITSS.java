package vista.ConsultorioEx;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.ConsultorioEx.ConsultorioExtEsnitss;
import modelos.ConsultorioEx.ConsultorioExtOrdenpcr;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import modelos.admisionEmergencia.AdmisionEmergenciaTriaje;
import servicios.Conexion;

/**
 *
 * @author MYS1
 */
public class ESNITSS extends javax.swing.JFrame {
    DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    ResultSet r;
    PreparedStatement pstm;
    String check = "H";
    public ESNITSS() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
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
        conexion = c.conectar();
        cerrar();
        pnlMensaje.setVisible(false);
        habilitarDatos(false);
        Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        lblFecha.setText(fechaActual());
        lblTriaje.setVisible(false);
        lblIdHc.setVisible(false);
        lblMant.setVisible(false);
        lblId.setVisible(false);
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
    
    public static String fechaActual(){
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        return date.format(now);
    }
    
    public void limpiar(){
        lblHc.setText("________________");
        lblDni.setText("________________");
        lblActoMedico.setText("");
        lblIdHc.setText("");
        txtAbacavir.setText("0");
        txtAtazanavir.setText("0");
        txtCotrimoxazol.setText("0");
        txtDuovir.setText("0");
        txtDuovirN.setText("0");
        txtEfavirenz.setText("0");
        txtInh.setText("0");
        txtKelatra.setText("0");
        txtLamivudina.setText("0");
        txtNevirapina.setText("0");
        txtPaciente.setText("");
        txtRatelgravir.setText("0");
        txtRitonavir.setText("0");
        txtTenofovir.setText("0");
        txtZidobudina.setText("0");
    }
    
    public void habilitarDatos(boolean opcion){
        txtAbacavir.setEnabled(opcion);
        txtAtazanavir.setEnabled(opcion);
        txtCotrimoxazol.setEnabled(opcion);
        txtDuovir.setEnabled(opcion);
        txtDuovirN.setEnabled(opcion);
        txtEfavirenz.setEnabled(opcion);
        txtInh.setEnabled(opcion);
        txtKelatra.setEnabled(opcion);
        txtLamivudina.setEnabled(opcion);
        txtNevirapina.setEnabled(opcion);
        txtRatelgravir.setEnabled(opcion);
        txtRitonavir.setEnabled(opcion);
        txtTenofovir.setEnabled(opcion);
        txtZidobudina.setEnabled(opcion);
        lblFecha.setEnabled(opcion);
    }
    
    public boolean mantenimientoEsnitss(){
        boolean retorna = false;
        try {
            ConsultorioExtEsnitss esnitss1 = new ConsultorioExtEsnitss();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMant.getText().equals("U") || lblMant.getText().equals("E"))
                esnitss1.setEsId(Integer.parseInt(lblId.getText()));
            esnitss1.setIdHc(lblIdHc.getText());
            esnitss1.setEsCodigoPac(txtPaciente.getText());
            esnitss1.setEsAbacavir(txtAbacavir.getText());
            esnitss1.setEsAtazanavir(txtAbacavir.getText());
            esnitss1.setEsCotrimoxazol(txtCotrimoxazol.getText());
            esnitss1.setEsDuobir(txtDuovir.getText());
            esnitss1.setEsDuovirN(txtDuovirN.getText());
            esnitss1.setEsEfavirenz(txtEfavirenz.getText());
            esnitss1.setEsKelatra(txtKelatra.getText());
            esnitss1.setEsLamivudina(txtLamivudina.getText());
            esnitss1.setEsNevirapina(txtNevirapina.getText());
            esnitss1.setEsRateigravir(txtRatelgravir.getText());
            esnitss1.setEsRitonavir(txtRitonavir.getText());
            esnitss1.setEsTenofovir(txtTenofovir.getText());
            esnitss1.setEsZidobudina(txtZidobudina.getText());
            esnitss1.setEsInh(txtInh.getText());
            esnitss1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(esnitss1.mantenimientoConsultorioExtEsnitss(lblMant.getText(),lblTriaje.getText())==true){
                System.out.println("ID ESNITSS: " + Integer.parseInt(esnitss1.esnitssID()));
                esnitss1.consultorioExtEsnitssListar(txtBuscar.getText(), "H","","",tbEsnitss);
                chkHoy.setSelected(true);
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                limpiar();
                habilitarDatos(false);
                btnGuardar.setEnabled(false);
                pnlMensaje.setBackground(new Color(33,115,70));
                btnSi.setVisible(true);
                btnSi.setText("OK");
                btnNo.setVisible(false);
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ocurrió un error, verifique");
                pnlMensaje.setBackground(new Color(255,91,70));
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error: guardarDatos" + e.getMessage());
        }
        return retorna;
    }
    
    public void enviarDatosPacientes(){
        int fila = tbPacientes.getSelectedRow();
        lblTriaje.setText(String.valueOf(tbPacientes.getValueAt(fila, 0)));
        txtPaciente.setText(String.valueOf(tbPacientes.getValueAt(fila, 15)));
        lblIdHc.setText(String.valueOf(tbPacientes.getValueAt(fila, 13)));
        lblHc.setText(String.valueOf(tbPacientes.getValueAt(fila, 3)));
        lblDni.setText(String.valueOf(tbPacientes.getValueAt(fila, 2)));
        lblActoMedico.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
        BuscarPacientes.dispose();
    }
    
    public void enviarDatosEsnitss(){
        int fila = tbEsnitss.getSelectedRow();
        lblActoMedico.setVisible(false);
        lblAM.setVisible(false);
        lblId.setText(String.valueOf(tbEsnitss.getValueAt(fila, 0)));
        txtPaciente.setText(String.valueOf(tbEsnitss.getValueAt(fila, 1)));
        lblHc.setText(String.valueOf(tbEsnitss.getValueAt(fila, 2)));
        lblDni.setText(String.valueOf(tbEsnitss.getValueAt(fila, 3)));
        txtTenofovir.setText(String.valueOf(tbEsnitss.getValueAt(fila, 4)));
        txtZidobudina.setText(String.valueOf(tbEsnitss.getValueAt(fila, 5)));
        txtAbacavir.setText(String.valueOf(tbEsnitss.getValueAt(fila, 6)));
        txtLamivudina.setText(String.valueOf(tbEsnitss.getValueAt(fila, 7)));
        txtEfavirenz.setText(String.valueOf(tbEsnitss.getValueAt(fila, 8)));
        txtNevirapina.setText(String.valueOf(tbEsnitss.getValueAt(fila, 9)));
        txtRitonavir.setText(String.valueOf(tbEsnitss.getValueAt(fila, 10)));
        txtAtazanavir.setText(String.valueOf(tbEsnitss.getValueAt(fila, 11)));
        txtRatelgravir.setText(String.valueOf(tbEsnitss.getValueAt(fila, 12)));
        txtDuovir.setText(String.valueOf(tbEsnitss.getValueAt(fila, 13)));
        txtDuovirN.setText(String.valueOf(tbEsnitss.getValueAt(fila, 14)));
        txtKelatra.setText(String.valueOf(tbEsnitss.getValueAt(fila, 15)));
        txtCotrimoxazol.setText(String.valueOf(tbEsnitss.getValueAt(fila, 16)));
        txtInh.setText(String.valueOf(tbEsnitss.getValueAt(fila, 17)));
        lblFecha.setText(String.valueOf(tbEsnitss.getValueAt(fila, 18)));
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        lblMant.setText("U");
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
                           pnlMensaje.setVisible(true);
                           pnlMensaje.setBackground(new Color(255,91,70)); 
                           lblMensaje.setText("Ingrese una fecha correcta");
                          
         }
        
        return fecha;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BuscarPacientes = new javax.swing.JDialog();
        jPanel16 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        txtBuscarPaciente = new javax.swing.JTextField();
        T6 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbPacientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            btnNuevo = new javax.swing.JButton();
            btnModificar = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            btnEliminar = new javax.swing.JButton();
            lblusu = new javax.swing.JLabel();
            btneliminar1 = new javax.swing.JButton();
            btnBuscar = new javax.swing.JButton();
            lblMant = new javax.swing.JLabel();
            lblIdHc = new javax.swing.JLabel();
            lblId = new javax.swing.JLabel();
            lblTriaje = new javax.swing.JLabel();
            pnlMensaje = new javax.swing.JPanel();
            lblMensaje = new javax.swing.JLabel();
            btnSi = new javax.swing.JButton();
            btnNo = new javax.swing.JButton();
            tpEsnitss = new javax.swing.JTabbedPane();
            jPanel2 = new javax.swing.JPanel();
            jLabel2 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            lblHc = new javax.swing.JLabel();
            jPanel9 = new javax.swing.JPanel();
            txtPaciente = new javax.swing.JTextField();
            btnBuscarPaciente = new javax.swing.JButton();
            lblHc1 = new javax.swing.JLabel();
            lblHc2 = new javax.swing.JLabel();
            lblHc5 = new javax.swing.JLabel();
            lblHc8 = new javax.swing.JLabel();
            lblHc9 = new javax.swing.JLabel();
            lblHc14 = new javax.swing.JLabel();
            lblHc17 = new javax.swing.JLabel();
            lblHc20 = new javax.swing.JLabel();
            lblHc23 = new javax.swing.JLabel();
            lblHc26 = new javax.swing.JLabel();
            lblHc27 = new javax.swing.JLabel();
            lblHc24 = new javax.swing.JLabel();
            lblHc21 = new javax.swing.JLabel();
            lblHc18 = new javax.swing.JLabel();
            lblHc15 = new javax.swing.JLabel();
            lblHc12 = new javax.swing.JLabel();
            lblHc10 = new javax.swing.JLabel();
            lblHc6 = new javax.swing.JLabel();
            lblHc3 = new javax.swing.JLabel();
            lblHc4 = new javax.swing.JLabel();
            lblHc7 = new javax.swing.JLabel();
            lblHc11 = new javax.swing.JLabel();
            lblHc13 = new javax.swing.JLabel();
            lblHc16 = new javax.swing.JLabel();
            lblHc19 = new javax.swing.JLabel();
            lblHc22 = new javax.swing.JLabel();
            lblHc25 = new javax.swing.JLabel();
            lblHc28 = new javax.swing.JLabel();
            txtRatelgravir = new javax.swing.JTextField();
            txtAtazanavir = new javax.swing.JTextField();
            txtRitonavir = new javax.swing.JTextField();
            txtNevirapina = new javax.swing.JTextField();
            txtEfavirenz = new javax.swing.JTextField();
            txtLamivudina = new javax.swing.JTextField();
            txtAbacavir = new javax.swing.JTextField();
            txtZidobudina = new javax.swing.JTextField();
            txtTenofovir = new javax.swing.JTextField();
            jPanel3 = new javax.swing.JPanel();
            lblHc29 = new javax.swing.JLabel();
            jPanel5 = new javax.swing.JPanel();
            lblHc31 = new javax.swing.JLabel();
            lblHc33 = new javax.swing.JLabel();
            lblHc35 = new javax.swing.JLabel();
            lblHc34 = new javax.swing.JLabel();
            lblHc36 = new javax.swing.JLabel();
            lblHc32 = new javax.swing.JLabel();
            jPanel4 = new javax.swing.JPanel();
            lblHc37 = new javax.swing.JLabel();
            txtDuovir = new javax.swing.JTextField();
            jPanel6 = new javax.swing.JPanel();
            lblHc38 = new javax.swing.JLabel();
            lblHc39 = new javax.swing.JLabel();
            lblHc40 = new javax.swing.JLabel();
            lblHc41 = new javax.swing.JLabel();
            lblHc42 = new javax.swing.JLabel();
            lblHc43 = new javax.swing.JLabel();
            jPanel7 = new javax.swing.JPanel();
            lblHc44 = new javax.swing.JLabel();
            txtDuovirN = new javax.swing.JTextField();
            lblHc50 = new javax.swing.JLabel();
            lblHc30 = new javax.swing.JLabel();
            lblHc45 = new javax.swing.JLabel();
            lblHc46 = new javax.swing.JLabel();
            jPanel8 = new javax.swing.JPanel();
            lblHc47 = new javax.swing.JLabel();
            lblHc48 = new javax.swing.JLabel();
            lblHc49 = new javax.swing.JLabel();
            lblHc51 = new javax.swing.JLabel();
            lblHc52 = new javax.swing.JLabel();
            jPanel10 = new javax.swing.JPanel();
            lblHc53 = new javax.swing.JLabel();
            txtKelatra = new javax.swing.JTextField();
            jPanel11 = new javax.swing.JPanel();
            lblHc54 = new javax.swing.JLabel();
            lblHc55 = new javax.swing.JLabel();
            lblHc56 = new javax.swing.JLabel();
            lblHc57 = new javax.swing.JLabel();
            lblHc59 = new javax.swing.JLabel();
            jPanel12 = new javax.swing.JPanel();
            lblHc60 = new javax.swing.JLabel();
            txtCotrimoxazol = new javax.swing.JTextField();
            jPanel13 = new javax.swing.JPanel();
            lblHc58 = new javax.swing.JLabel();
            lblHc61 = new javax.swing.JLabel();
            lblHc62 = new javax.swing.JLabel();
            jPanel14 = new javax.swing.JPanel();
            lblHc65 = new javax.swing.JLabel();
            txtInh = new javax.swing.JTextField();
            jLabel4 = new javax.swing.JLabel();
            lblAM = new javax.swing.JLabel();
            lblActoMedico = new javax.swing.JLabel();
            jLabel7 = new javax.swing.JLabel();
            lblDni = new javax.swing.JLabel();
            lblFecha = new javax.swing.JLabel();
            jPanel15 = new javax.swing.JPanel();
            jScrollPane3 = new javax.swing.JScrollPane();
            tbEsnitss = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel31 = new javax.swing.JPanel();
                btnBuscarE = new javax.swing.JLabel();
                txtBuscar = new javax.swing.JTextField();
                chkHoy = new javax.swing.JCheckBox();
                dtFechaI = new com.toedter.calendar.JDateChooser();
                dtFechaF = new com.toedter.calendar.JDateChooser();
                jLabel6 = new javax.swing.JLabel();

                BuscarPacientes.setAlwaysOnTop(true);
                BuscarPacientes.setMinimumSize(new java.awt.Dimension(615, 333));
                BuscarPacientes.setResizable(false);

                jPanel16.setBackground(new java.awt.Color(102, 102, 102));

                jLabel30.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel30.setForeground(new java.awt.Color(255, 255, 255));
                jLabel30.setText("Pacientes");

                jPanel27.setBackground(new java.awt.Color(255, 255, 255));

                txtBuscarPaciente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtBuscarPaciente.setBorder(null);
                txtBuscarPaciente.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscarPacienteCaretUpdate(evt);
                    }
                });
                txtBuscarPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtBuscarPacienteMouseClicked(evt);
                    }
                });
                txtBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtBuscarPacienteActionPerformed(evt);
                    }
                });
                txtBuscarPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscarPacienteKeyPressed(evt);
                    }
                });

                T6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                T6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                T6.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        T6MouseClicked(evt);
                    }
                });

                javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
                jPanel27.setLayout(jPanel27Layout);
                jPanel27Layout.setHorizontalGroup(
                    jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(T6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );
                jPanel27Layout.setVerticalGroup(
                    jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(T6)
                            .addComponent(txtBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                );

                jPanel17.setBackground(new java.awt.Color(0, 153, 102));

                javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                jPanel17.setLayout(jPanel17Layout);
                jPanel17Layout.setHorizontalGroup(
                    jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 615, Short.MAX_VALUE)
                );
                jPanel17Layout.setVerticalGroup(
                    jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 12, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                jPanel16.setLayout(jPanel16Layout);
                jPanel16Layout.setHorizontalGroup(
                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel16Layout.setVerticalGroup(
                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addContainerGap(17, Short.MAX_VALUE)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );

                jScrollPane4.setBorder(null);

                tbPacientes.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
                tbPacientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                tbPacientes.setGridColor(new java.awt.Color(255, 255, 255));
                tbPacientes.setRowHeight(25);
                tbPacientes.setSelectionBackground(new java.awt.Color(102, 102, 102));
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
                });
                jScrollPane4.setViewportView(tbPacientes);

                javax.swing.GroupLayout BuscarPacientesLayout = new javax.swing.GroupLayout(BuscarPacientes.getContentPane());
                BuscarPacientes.getContentPane().setLayout(BuscarPacientesLayout);
                BuscarPacientesLayout.setHorizontalGroup(
                    BuscarPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                );
                BuscarPacientesLayout.setVerticalGroup(
                    BuscarPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BuscarPacientesLayout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                        .addContainerGap())
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(0, 153, 102));

                jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("ESNITSS");

                btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                btnNuevo.setMnemonic('N');
                btnNuevo.setContentAreaFilled(false);
                btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnNuevo.setIconTextGap(30);
                btnNuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnNuevoActionPerformed(evt);
                    }
                });

                btnModificar.setForeground(new java.awt.Color(240, 240, 240));
                btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
                btnModificar.setMnemonic('N');
                btnModificar.setContentAreaFilled(false);
                btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnModificar.setEnabled(false);
                btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnModificar.setIconTextGap(30);
                btnModificar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnModificar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnModificarActionPerformed(evt);
                    }
                });

                btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
                btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
                btnGuardar.setMnemonic('N');
                btnGuardar.setContentAreaFilled(false);
                btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnGuardar.setEnabled(false);
                btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnGuardar.setIconTextGap(30);
                btnGuardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGuardarActionPerformed(evt);
                    }
                });

                btnEliminar.setForeground(new java.awt.Color(240, 240, 240));
                btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
                btnEliminar.setMnemonic('N');
                btnEliminar.setContentAreaFilled(false);
                btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnEliminar.setEnabled(false);
                btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnEliminar.setIconTextGap(30);
                btnEliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnEliminarActionPerformed(evt);
                    }
                });

                lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                lblusu.setForeground(new java.awt.Color(255, 255, 255));
                lblusu.setText("Silvana");

                btneliminar1.setForeground(new java.awt.Color(240, 240, 240));
                btneliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
                btneliminar1.setMnemonic('N');
                btneliminar1.setToolTipText("");
                btneliminar1.setContentAreaFilled(false);
                btneliminar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btneliminar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btneliminar1.setIconTextGap(30);
                btneliminar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btneliminar1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btneliminar1ActionPerformed(evt);
                    }
                });

                btnBuscar.setForeground(new java.awt.Color(240, 240, 240));
                btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                btnBuscar.setMnemonic('N');
                btnBuscar.setContentAreaFilled(false);
                btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnBuscar.setIconTextGap(30);
                btnBuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarActionPerformed(evt);
                    }
                });

                lblMant.setText("Mantenimiento");

                lblIdHc.setText("jLabel6");

                lblId.setText("jLabel6");

                lblTriaje.setText("jLabel6");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btneliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblusu)
                                .addGap(16, 16, 16))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(135, 135, 135)
                                .addComponent(lblMant, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblIdHc)
                                .addGap(31, 31, 31)
                                .addComponent(lblId)
                                .addGap(36, 36, 36)
                                .addComponent(lblTriaje)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblMant)
                                    .addComponent(lblIdHc)
                                    .addComponent(lblId)
                                    .addComponent(lblTriaje)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btneliminar1)
                                            .addComponent(jLabel1))
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(lblusu)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnEliminar))
                                        .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(btnBuscar))))
                        .addGap(552, 552, 552))
                );

                pnlMensaje.setBackground(new java.awt.Color(33, 115, 70));

                lblMensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                lblMensaje.setForeground(new java.awt.Color(255, 255, 255));
                lblMensaje.setText("Desea Actualizar el Registro ?");

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
                        .addGap(46, 46, 46)
                        .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                pnlMensajeLayout.setVerticalGroup(
                    pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMensajeLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMensaje)
                            .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                tpEsnitss.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
                tpEsnitss.setEnabled(false);

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(51, 51, 51));
                jLabel2.setText("Paciente");

                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(51, 51, 51));
                jLabel3.setText("Nº Hístoria Clínica");

                lblHc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc.setForeground(new java.awt.Color(51, 51, 51));
                lblHc.setText("________________");

                jPanel9.setBackground(new java.awt.Color(255, 255, 255));
                jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtPaciente.setEditable(false);
                txtPaciente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtPaciente.setForeground(new java.awt.Color(51, 51, 51));
                txtPaciente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPaciente.setToolTipText("");
                txtPaciente.setBorder(null);
                txtPaciente.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPacienteCaretUpdate(evt);
                    }
                });

                btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarPaciente.setToolTipText("");
                btnBuscarPaciente.setContentAreaFilled(false);
                btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarPaciente.setEnabled(false);
                btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarPacienteActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                jPanel9.setLayout(jPanel9Layout);
                jPanel9Layout.setHorizontalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                );
                jPanel9Layout.setVerticalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                lblHc1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc1.setForeground(new java.awt.Color(51, 51, 51));
                lblHc1.setText("Nombre Genérico                            Dosis            Cantidad");

                lblHc2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc2.setForeground(new java.awt.Color(51, 51, 51));
                lblHc2.setText("Tenofovir");

                lblHc5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc5.setForeground(new java.awt.Color(51, 51, 51));
                lblHc5.setText("Zidobudina");

                lblHc8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc8.setForeground(new java.awt.Color(51, 51, 51));
                lblHc8.setText("Abacavir");

                lblHc9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc9.setForeground(new java.awt.Color(51, 51, 51));
                lblHc9.setText("Lamivudina");

                lblHc14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc14.setForeground(new java.awt.Color(51, 51, 51));
                lblHc14.setText("Efavirenz");

                lblHc17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc17.setForeground(new java.awt.Color(51, 51, 51));
                lblHc17.setText("Nevirapina");

                lblHc20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc20.setForeground(new java.awt.Color(51, 51, 51));
                lblHc20.setText("Ritonavir");

                lblHc23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc23.setForeground(new java.awt.Color(51, 51, 51));
                lblHc23.setText("Atazanavir");

                lblHc26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc26.setForeground(new java.awt.Color(51, 51, 51));
                lblHc26.setText("Ratelgravir");

                lblHc27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc27.setForeground(new java.awt.Color(51, 51, 51));
                lblHc27.setText("RAL");

                lblHc24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc24.setForeground(new java.awt.Color(51, 51, 51));
                lblHc24.setText("ATV");

                lblHc21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc21.setForeground(new java.awt.Color(51, 51, 51));
                lblHc21.setText("RTV");

                lblHc18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc18.setForeground(new java.awt.Color(51, 51, 51));
                lblHc18.setText("NVP");

                lblHc15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc15.setForeground(new java.awt.Color(51, 51, 51));
                lblHc15.setText("EFV");

                lblHc12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc12.setForeground(new java.awt.Color(51, 51, 51));
                lblHc12.setText("3TC");

                lblHc10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc10.setForeground(new java.awt.Color(51, 51, 51));
                lblHc10.setText("ABC");

                lblHc6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc6.setForeground(new java.awt.Color(51, 51, 51));
                lblHc6.setText("AZT");

                lblHc3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc3.setForeground(new java.awt.Color(51, 51, 51));
                lblHc3.setText("TDF");

                lblHc4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc4.setForeground(new java.awt.Color(51, 51, 51));
                lblHc4.setText("300mg");

                lblHc7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc7.setForeground(new java.awt.Color(51, 51, 51));
                lblHc7.setText("300mg");

                lblHc11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc11.setForeground(new java.awt.Color(51, 51, 51));
                lblHc11.setText("300mg");

                lblHc13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc13.setForeground(new java.awt.Color(51, 51, 51));
                lblHc13.setText("150mg");

                lblHc16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc16.setForeground(new java.awt.Color(51, 51, 51));
                lblHc16.setText("600mg");

                lblHc19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc19.setForeground(new java.awt.Color(51, 51, 51));
                lblHc19.setText("200mg");

                lblHc22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc22.setForeground(new java.awt.Color(51, 51, 51));
                lblHc22.setText("100mg");

                lblHc25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc25.setForeground(new java.awt.Color(51, 51, 51));
                lblHc25.setText("300mg");

                lblHc28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc28.setForeground(new java.awt.Color(51, 51, 51));
                lblHc28.setText("300mg");

                txtRatelgravir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtRatelgravir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtRatelgravir.setText("0");
                txtRatelgravir.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtRatelgravirMouseClicked(evt);
                    }
                });
                txtRatelgravir.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtRatelgravirActionPerformed(evt);
                    }
                });
                txtRatelgravir.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtRatelgravirKeyTyped(evt);
                    }
                });

                txtAtazanavir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtAtazanavir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtAtazanavir.setText("0");
                txtAtazanavir.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtAtazanavirMouseClicked(evt);
                    }
                });
                txtAtazanavir.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtAtazanavirActionPerformed(evt);
                    }
                });
                txtAtazanavir.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtAtazanavirKeyTyped(evt);
                    }
                });

                txtRitonavir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtRitonavir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtRitonavir.setText("0");
                txtRitonavir.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtRitonavirMouseClicked(evt);
                    }
                });
                txtRitonavir.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtRitonavirActionPerformed(evt);
                    }
                });
                txtRitonavir.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtRitonavirKeyTyped(evt);
                    }
                });

                txtNevirapina.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtNevirapina.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtNevirapina.setText("0");
                txtNevirapina.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtNevirapinaMouseClicked(evt);
                    }
                });
                txtNevirapina.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtNevirapinaActionPerformed(evt);
                    }
                });
                txtNevirapina.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtNevirapinaKeyTyped(evt);
                    }
                });

                txtEfavirenz.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtEfavirenz.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtEfavirenz.setText("0");
                txtEfavirenz.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtEfavirenzMouseClicked(evt);
                    }
                });
                txtEfavirenz.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtEfavirenzActionPerformed(evt);
                    }
                });
                txtEfavirenz.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtEfavirenzKeyTyped(evt);
                    }
                });

                txtLamivudina.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtLamivudina.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtLamivudina.setText("0");
                txtLamivudina.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtLamivudinaMouseClicked(evt);
                    }
                });
                txtLamivudina.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtLamivudinaActionPerformed(evt);
                    }
                });
                txtLamivudina.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtLamivudinaKeyPressed(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtLamivudinaKeyTyped(evt);
                    }
                });

                txtAbacavir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtAbacavir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtAbacavir.setText("0");
                txtAbacavir.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtAbacavirMouseClicked(evt);
                    }
                });
                txtAbacavir.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtAbacavirActionPerformed(evt);
                    }
                });
                txtAbacavir.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtAbacavirKeyTyped(evt);
                    }
                });

                txtZidobudina.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtZidobudina.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtZidobudina.setText("0");
                txtZidobudina.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtZidobudinaMouseClicked(evt);
                    }
                });
                txtZidobudina.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtZidobudinaActionPerformed(evt);
                    }
                });
                txtZidobudina.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtZidobudinaKeyTyped(evt);
                    }
                });

                txtTenofovir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtTenofovir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtTenofovir.setText("0");
                txtTenofovir.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtTenofovirMouseClicked(evt);
                    }
                });
                txtTenofovir.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtTenofovirActionPerformed(evt);
                    }
                });
                txtTenofovir.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtTenofovirKeyTyped(evt);
                    }
                });

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                lblHc29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc29.setForeground(new java.awt.Color(51, 51, 51));
                lblHc29.setText("COMBINACIONES DE ANTIRETROVIRALES");

                jPanel5.setBackground(new java.awt.Color(204, 204, 204));

                lblHc31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc31.setForeground(new java.awt.Color(102, 102, 102));
                lblHc31.setText("Zidobudina");

                lblHc33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc33.setForeground(new java.awt.Color(102, 102, 102));
                lblHc33.setText("AZT");

                lblHc35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc35.setForeground(new java.awt.Color(102, 102, 102));
                lblHc35.setText("300 mg");

                lblHc34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc34.setForeground(new java.awt.Color(102, 102, 102));
                lblHc34.setText("3TC");

                lblHc36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc36.setForeground(new java.awt.Color(102, 102, 102));
                lblHc36.setText("150 mg");

                lblHc32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc32.setForeground(new java.awt.Color(102, 102, 102));
                lblHc32.setText("Lamivudina");

                jPanel4.setBackground(new java.awt.Color(204, 204, 204));

                lblHc37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc37.setForeground(new java.awt.Color(102, 102, 102));
                lblHc37.setText("Duovir");

                txtDuovir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtDuovir.setForeground(new java.awt.Color(102, 102, 102));
                txtDuovir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDuovir.setText("0");
                txtDuovir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtDuovir.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDuovirCaretUpdate(evt);
                    }
                });
                txtDuovir.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtDuovirMouseClicked(evt);
                    }
                });
                txtDuovir.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtDuovirActionPerformed(evt);
                    }
                });
                txtDuovir.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtDuovirKeyTyped(evt);
                    }
                });

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(lblHc37, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDuovir, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );
                jPanel4Layout.setVerticalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHc37)
                            .addComponent(txtDuovir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHc32, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHc31, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHc33)
                            .addComponent(lblHc34))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblHc36)
                            .addComponent(lblHc35))
                        .addGap(48, 48, 48)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel5Layout.setVerticalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblHc33)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(lblHc35)
                                        .addGap(2, 2, 2)
                                        .addComponent(lblHc36))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(lblHc31)
                                        .addGap(2, 2, 2)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblHc32)
                                            .addComponent(lblHc34))))))
                        .addGap(0, 0, 0))
                );

                jPanel6.setBackground(new java.awt.Color(153, 153, 153));

                lblHc38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc38.setForeground(new java.awt.Color(255, 255, 255));
                lblHc38.setText("Zidobudina");

                lblHc39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc39.setForeground(new java.awt.Color(255, 255, 255));
                lblHc39.setText("AZT");

                lblHc40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc40.setForeground(new java.awt.Color(255, 255, 255));
                lblHc40.setText("300 mg");

                lblHc41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc41.setForeground(new java.awt.Color(255, 255, 255));
                lblHc41.setText("3TC");

                lblHc42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc42.setForeground(new java.awt.Color(255, 255, 255));
                lblHc42.setText("150 mg");

                lblHc43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc43.setForeground(new java.awt.Color(255, 255, 255));
                lblHc43.setText("Lamivudina");

                jPanel7.setBackground(new java.awt.Color(153, 153, 153));

                lblHc44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc44.setForeground(new java.awt.Color(255, 255, 255));
                lblHc44.setText("Duovir-N");

                txtDuovirN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtDuovirN.setForeground(new java.awt.Color(102, 102, 102));
                txtDuovirN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDuovirN.setText("0");
                txtDuovirN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtDuovirN.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDuovirNCaretUpdate(evt);
                    }
                });
                txtDuovirN.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtDuovirNMouseClicked(evt);
                    }
                });
                txtDuovirN.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtDuovirNActionPerformed(evt);
                    }
                });
                txtDuovirN.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtDuovirNKeyTyped(evt);
                    }
                });

                lblHc50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc50.setForeground(new java.awt.Color(255, 255, 255));
                lblHc50.setText("Zidolam-N");

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHc44, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHc50, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDuovirN, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );
                jPanel7Layout.setVerticalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDuovirN, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(lblHc44)
                                .addGap(2, 2, 2)
                                .addComponent(lblHc50)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                lblHc30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc30.setForeground(new java.awt.Color(255, 255, 255));
                lblHc30.setText("Nevirapina");

                lblHc45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc45.setForeground(new java.awt.Color(255, 255, 255));
                lblHc45.setText("NVP");

                lblHc46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc46.setForeground(new java.awt.Color(255, 255, 255));
                lblHc46.setText("200 mg");

                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                jPanel6.setLayout(jPanel6Layout);
                jPanel6Layout.setHorizontalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHc43, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblHc38, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHc39)
                                    .addComponent(lblHc41))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblHc42)
                                    .addComponent(lblHc40)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(lblHc30, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(lblHc45)
                                .addGap(27, 27, 27)
                                .addComponent(lblHc46)))
                        .addGap(48, 48, 48)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                );
                jPanel6Layout.setVerticalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblHc39)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(lblHc40)
                                    .addGap(2, 2, 2)
                                    .addComponent(lblHc42))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(lblHc38)
                                    .addGap(2, 2, 2)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblHc43)
                                        .addComponent(lblHc41)))))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHc30)
                            .addComponent(lblHc45)
                            .addComponent(lblHc46)))
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                );

                jPanel8.setBackground(new java.awt.Color(204, 204, 204));

                lblHc47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc47.setForeground(new java.awt.Color(102, 102, 102));
                lblHc47.setText("Lopinavir");

                lblHc48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc48.setForeground(new java.awt.Color(102, 102, 102));
                lblHc48.setText("LPV/r");

                lblHc49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc49.setForeground(new java.awt.Color(102, 102, 102));
                lblHc49.setText("300 mg");

                lblHc51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc51.setForeground(new java.awt.Color(102, 102, 102));
                lblHc51.setText("150 mg");

                lblHc52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc52.setForeground(new java.awt.Color(102, 102, 102));
                lblHc52.setText("Ritonavir");

                jPanel10.setBackground(new java.awt.Color(204, 204, 204));

                lblHc53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc53.setForeground(new java.awt.Color(102, 102, 102));
                lblHc53.setText("Kelatra");

                txtKelatra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtKelatra.setForeground(new java.awt.Color(102, 102, 102));
                txtKelatra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtKelatra.setText("0");
                txtKelatra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtKelatra.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtKelatraCaretUpdate(evt);
                    }
                });
                txtKelatra.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtKelatraMouseClicked(evt);
                    }
                });
                txtKelatra.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtKelatraActionPerformed(evt);
                    }
                });
                txtKelatra.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtKelatraKeyTyped(evt);
                    }
                });

                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                jPanel10.setLayout(jPanel10Layout);
                jPanel10Layout.setHorizontalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(lblHc53, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtKelatra, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );
                jPanel10Layout.setVerticalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHc53)
                            .addComponent(txtKelatra, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHc52, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHc47, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(lblHc48)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblHc51)
                            .addComponent(lblHc49))
                        .addGap(48, 48, 48)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                );
                jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblHc48, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(lblHc49)
                                    .addGap(2, 2, 2)
                                    .addComponent(lblHc51))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(lblHc47)
                                    .addGap(2, 2, 2)
                                    .addComponent(lblHc52))))
                        .addGap(0, 0, 0))
                );

                jPanel11.setBackground(new java.awt.Color(153, 153, 153));

                lblHc54.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc54.setForeground(new java.awt.Color(255, 255, 255));
                lblHc54.setText("Trimetropin");

                lblHc55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc55.setForeground(new java.awt.Color(255, 255, 255));
                lblHc55.setText("TMP");

                lblHc56.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc56.setForeground(new java.awt.Color(255, 255, 255));
                lblHc56.setText("800/160 mg");

                lblHc57.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc57.setForeground(new java.awt.Color(255, 255, 255));
                lblHc57.setText("SMX");

                lblHc59.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc59.setForeground(new java.awt.Color(255, 255, 255));
                lblHc59.setText("Sulfametaxazol");

                jPanel12.setBackground(new java.awt.Color(153, 153, 153));

                lblHc60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc60.setForeground(new java.awt.Color(255, 255, 255));
                lblHc60.setText("Cotrimoxazol");

                txtCotrimoxazol.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtCotrimoxazol.setForeground(new java.awt.Color(102, 102, 102));
                txtCotrimoxazol.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtCotrimoxazol.setText("0");
                txtCotrimoxazol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtCotrimoxazol.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCotrimoxazolCaretUpdate(evt);
                    }
                });
                txtCotrimoxazol.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtCotrimoxazolMouseClicked(evt);
                    }
                });
                txtCotrimoxazol.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtCotrimoxazolActionPerformed(evt);
                    }
                });
                txtCotrimoxazol.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtCotrimoxazolKeyTyped(evt);
                    }
                });

                javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                jPanel12.setLayout(jPanel12Layout);
                jPanel12Layout.setHorizontalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(lblHc60, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCotrimoxazol, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );
                jPanel12Layout.setVerticalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHc60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCotrimoxazol, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                );

                javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                jPanel11.setLayout(jPanel11Layout);
                jPanel11Layout.setHorizontalGroup(
                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHc54, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHc59, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHc55)
                            .addComponent(lblHc57))
                        .addGap(30, 30, 30)
                        .addComponent(lblHc56, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                );
                jPanel11Layout.setVerticalGroup(
                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblHc56, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblHc55, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                    .addComponent(lblHc54)
                                    .addGap(2, 2, 2)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblHc59)
                                        .addComponent(lblHc57)))))
                        .addGap(0, 0, 0))
                );

                jPanel13.setBackground(new java.awt.Color(204, 204, 204));

                lblHc58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc58.setForeground(new java.awt.Color(102, 102, 102));
                lblHc58.setText("Isoniazida");

                lblHc61.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc61.setForeground(new java.awt.Color(102, 102, 102));
                lblHc61.setText("IMN");

                lblHc62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc62.setForeground(new java.awt.Color(102, 102, 102));
                lblHc62.setText("100 mg");

                jPanel14.setBackground(new java.awt.Color(204, 204, 204));

                lblHc65.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblHc65.setForeground(new java.awt.Color(102, 102, 102));
                lblHc65.setText("INH");

                txtInh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtInh.setForeground(new java.awt.Color(102, 102, 102));
                txtInh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtInh.setText("0");
                txtInh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtInh.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtInhCaretUpdate(evt);
                    }
                });
                txtInh.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtInhMouseClicked(evt);
                    }
                });
                txtInh.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtInhActionPerformed(evt);
                    }
                });
                txtInh.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtInhKeyTyped(evt);
                    }
                });

                javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                jPanel14.setLayout(jPanel14Layout);
                jPanel14Layout.setHorizontalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(lblHc65, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addComponent(txtInh, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );
                jPanel14Layout.setVerticalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHc65)
                            .addComponent(txtInh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                jPanel13.setLayout(jPanel13Layout);
                jPanel13Layout.setHorizontalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblHc58, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(lblHc61)
                        .addGap(30, 30, 30)
                        .addComponent(lblHc62)
                        .addGap(48, 48, 48)
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                );
                jPanel13Layout.setVerticalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblHc62, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHc58, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblHc61, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0))
                );

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblHc29, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(lblHc29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );

                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                jLabel4.setText("Fecha");

                lblAM.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                lblAM.setForeground(new java.awt.Color(0, 153, 102));
                lblAM.setText("Acto Médico");

                lblActoMedico.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                lblActoMedico.setForeground(new java.awt.Color(0, 153, 102));

                jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel7.setForeground(new java.awt.Color(51, 51, 51));
                jLabel7.setText("DNI");

                lblDni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblDni.setForeground(new java.awt.Color(51, 51, 51));
                lblDni.setText("________________");

                lblFecha.setText("jLabel6");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblHc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7)
                                        .addGap(20, 20, 20)
                                        .addComponent(lblDni))
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(lblAM)
                                .addGap(40, 40, 40)
                                .addComponent(lblActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblHc1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblHc2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblHc8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblHc9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblHc14, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblHc17, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblHc20, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblHc23, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblHc26, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(49, 49, 49)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblHc3)
                                                    .addComponent(lblHc6)
                                                    .addComponent(lblHc10)
                                                    .addComponent(lblHc12)
                                                    .addComponent(lblHc15)
                                                    .addComponent(lblHc18)
                                                    .addComponent(lblHc21)
                                                    .addComponent(lblHc24)
                                                    .addComponent(lblHc27))
                                                .addGap(65, 65, 65)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(lblHc28)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtRatelgravir, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(lblHc25)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtAtazanavir, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(lblHc22)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtRitonavir, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(lblHc19)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtNevirapina, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(lblHc16)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtEfavirenz, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(lblHc13)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtLamivudina, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(lblHc11)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtAbacavir, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                        .addComponent(lblHc7)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtZidobudina, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                        .addComponent(lblHc4)
                                                        .addGap(40, 40, 40)
                                                        .addComponent(txtTenofovir, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addComponent(lblHc5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel4)
                                        .addGap(64, 64, 64)
                                        .addComponent(lblFecha)))
                                .addGap(33, 33, 33)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(53, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblAM)
                                .addComponent(lblActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(lblDni))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(lblHc)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblHc1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHc2)
                                    .addComponent(lblHc3)
                                    .addComponent(lblHc4)
                                    .addComponent(txtTenofovir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHc5)
                                    .addComponent(lblHc6)
                                    .addComponent(lblHc7)
                                    .addComponent(txtZidobudina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHc8)
                                    .addComponent(lblHc10)
                                    .addComponent(lblHc11)
                                    .addComponent(txtAbacavir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHc9)
                                    .addComponent(lblHc12)
                                    .addComponent(lblHc13)
                                    .addComponent(txtLamivudina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHc14)
                                    .addComponent(lblHc15)
                                    .addComponent(lblHc16)
                                    .addComponent(txtEfavirenz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHc17)
                                    .addComponent(lblHc18)
                                    .addComponent(lblHc19)
                                    .addComponent(txtNevirapina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHc20)
                                    .addComponent(lblHc21)
                                    .addComponent(lblHc22)
                                    .addComponent(txtRitonavir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHc23)
                                    .addComponent(lblHc24)
                                    .addComponent(lblHc25)
                                    .addComponent(txtAtazanavir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHc26)
                                    .addComponent(lblHc27)
                                    .addComponent(lblHc28)
                                    .addComponent(txtRatelgravir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(lblFecha))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                );

                tpEsnitss.addTab("Nueva Orden", jPanel2);

                jPanel15.setBackground(new java.awt.Color(255, 255, 255));

                jScrollPane3.setBorder(null);

                tbEsnitss.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
                tbEsnitss.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                tbEsnitss.setGridColor(new java.awt.Color(255, 255, 255));
                tbEsnitss.setRowHeight(25);
                tbEsnitss.setSelectionBackground(new java.awt.Color(102, 102, 102));
                tbEsnitss.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbEsnitssMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbEsnitssMousePressed(evt);
                    }
                });
                tbEsnitss.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbEsnitssKeyPressed(evt);
                    }
                });
                jScrollPane3.setViewportView(tbEsnitss);

                jPanel31.setBackground(new java.awt.Color(204, 204, 204));

                btnBuscarE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarE.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        btnBuscarEMouseClicked(evt);
                    }
                });

                txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscarCaretUpdate(evt);
                    }
                });
                txtBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtBuscarMouseClicked(evt);
                    }
                });

                chkHoy.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                chkHoy.setText("Hoy");
                chkHoy.setContentAreaFilled(false);
                chkHoy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                chkHoy.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        chkHoyActionPerformed(evt);
                    }
                });

                dtFechaI.setBackground(new java.awt.Color(204, 204, 204));
                dtFechaI.setDateFormatString("dd/MM/yyyy");
                dtFechaI.setEnabled(false);
                dtFechaI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                dtFechaF.setBackground(new java.awt.Color(204, 204, 204));
                dtFechaF.setDateFormatString("dd/MM/yyyy");
                dtFechaF.setEnabled(false);
                dtFechaF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
                jPanel31.setLayout(jPanel31Layout);
                jPanel31Layout.setHorizontalGroup(
                    jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkHoy)
                        .addGap(18, 18, 18)
                        .addComponent(dtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(dtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarE, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(350, Short.MAX_VALUE))
                );
                jPanel31Layout.setVerticalGroup(
                    jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(txtBuscar)
                    .addComponent(chkHoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtFechaF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtFechaI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(102, 102, 102));
                jLabel6.setText("Código del Paciente / DNI / Nº H.C.");

                javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                jPanel15.setLayout(jPanel15Layout);
                jPanel15Layout.setHorizontalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                );
                jPanel15Layout.setVerticalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel6)
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
                );

                tpEsnitss.addTab("Consultar", jPanel15);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tpEsnitss)
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tpEsnitss))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            limpiar();
            habilitarDatos(true);
            lblMant.setText("I");
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnBuscarPaciente.setEnabled(true);
            lblFecha.setText(fechaActual());
            tpEsnitss.setSelectedIndex(0);
            dtFechaI.setDate(null);
            dtFechaF.setDate(null);
            dtFechaI.setEnabled(false);
            dtFechaF.setEnabled(false);
            btnBuscarE.setVisible(false);
            pnlMensaje.setVisible(false);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(lblMant.getText().equals("I")){
            if(txtPaciente.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione un paciente");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else 
            if(txtAbacavir.getText().equals("0") && txtAtazanavir.getText().equals("0") && 
            txtCotrimoxazol.getText().equals("0") && txtDuovir.getText().equals("0") &&
            txtDuovirN.getText().equals("0") && txtEfavirenz.getText().equals("0") &&
            txtInh.getText().equals("0") && txtKelatra.getText().equals("0") &&
            txtLamivudina.getText().equals("0") && txtNevirapina.getText().equals("0") &&
            txtRatelgravir.getText().equals("0") && txtRitonavir.getText().equals("0") &&
            txtTenofovir.getText().equals("0") && txtZidobudina.getText().equals("0") ||
            txtAbacavir.getText().equals("") && txtAtazanavir.getText().equals("") && 
            txtCotrimoxazol.getText().equals("") && txtDuovir.getText().equals("") &&
            txtDuovirN.getText().equals("") && txtEfavirenz.getText().equals("") &&
            txtInh.getText().equals("") && txtKelatra.getText().equals("") &&
            txtLamivudina.getText().equals("") && txtNevirapina.getText().equals("") &&
            txtRatelgravir.getText().equals("") && txtRitonavir.getText().equals("") &&
            txtTenofovir.getText().equals("") && txtZidobudina.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese las cantidades");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            }else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("¿Guardar los datos?");
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                pnlMensaje.setBackground(new Color(255,153,51));
            }
        } else 
        if(lblMant.getText().equals("U")){
            if(txtPaciente.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione un paciente");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else 
            if(txtAbacavir.getText().equals("0") && txtAtazanavir.getText().equals("0") && 
            txtCotrimoxazol.getText().equals("0") && txtDuovir.getText().equals("0") &&
            txtDuovirN.getText().equals("0") && txtEfavirenz.getText().equals("0") &&
            txtInh.getText().equals("0") && txtKelatra.getText().equals("0") &&
            txtLamivudina.getText().equals("0") && txtNevirapina.getText().equals("0") &&
            txtRatelgravir.getText().equals("0") && txtRitonavir.getText().equals("0") &&
            txtTenofovir.getText().equals("0") && txtZidobudina.getText().equals("0") ||
            txtAbacavir.getText().equals("") && txtAtazanavir.getText().equals("") && 
            txtCotrimoxazol.getText().equals("") && txtDuovir.getText().equals("") &&
            txtDuovirN.getText().equals("") && txtEfavirenz.getText().equals("") &&
            txtInh.getText().equals("") && txtKelatra.getText().equals("") &&
            txtLamivudina.getText().equals("") && txtNevirapina.getText().equals("") &&
            txtRatelgravir.getText().equals("") && txtRitonavir.getText().equals("") &&
            txtTenofovir.getText().equals("") && txtZidobudina.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese las cantidades");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            }else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("¿Modificar los datos?");
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                pnlMensaje.setBackground(new Color(255,153,51));
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btneliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneliminar1ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        tpEsnitss.setSelectedIndex(1);
        chkHoy.setSelected(true);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);        
        txtBuscar.setText("");
        limpiar();
        habilitarDatos(false);
        ConsultorioExtEsnitss consultorio1 = new ConsultorioExtEsnitss();
        consultorio1.consultorioExtEsnitssListar(txtBuscar.getText(), "H","","", tbEsnitss);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        if(btnSi.getText().equals("Si")){ // Al guardar
                mantenimientoEsnitss();
        } else
        if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
            if(tpEsnitss.getSelectedIndex()==1)
                txtBuscar.setEnabled(true);
            pnlMensaje.setVisible(false);
        }
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_btnNoActionPerformed

    private void txtPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPacienteCaretUpdate

    }//GEN-LAST:event_txtPacienteCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed
        BuscarPacientes.setVisible(true);
        BuscarPacientes.setLocationRelativeTo(null);//en el centro
        BuscarPacientes.setResizable(false);
        BuscarPacientes.getContentPane().setBackground(Color.WHITE);
        ConsultorioExtEsnitss consultorio1 = new ConsultorioExtEsnitss();
        consultorio1.consultorioExListarC(txtBuscarPaciente.getText(), "Q", tbPacientes);
    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void txtDuovirCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDuovirCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDuovirCaretUpdate

    private void txtKelatraCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtKelatraCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKelatraCaretUpdate

    private void txtDuovirNCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDuovirNCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDuovirNCaretUpdate

    private void txtCotrimoxazolCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCotrimoxazolCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCotrimoxazolCaretUpdate

    private void txtInhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtInhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInhCaretUpdate

    private void tbEsnitssMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEsnitssMouseClicked

    }//GEN-LAST:event_tbEsnitssMouseClicked

    private void tbEsnitssMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEsnitssMousePressed

    }//GEN-LAST:event_tbEsnitssMousePressed

    private void tbEsnitssKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbEsnitssKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbPacientes.getSelectedRow()==0){
            txtBuscarPaciente.requestFocus();
            tbPacientes.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosEsnitss();
            tpEsnitss.setSelectedIndex(0);
        }
        
    }//GEN-LAST:event_tbEsnitssKeyPressed

    private void btnBuscarEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarEMouseClicked
        ConsultorioExtEsnitss consultorio1 = new ConsultorioExtEsnitss();
        consultorio1.consultorioExtEsnitssListar(txtBuscar.getText(), check,determinarFecha(dtFechaI),determinarFecha(dtFechaF), tbEsnitss);
        txtBuscar.setEnabled(true);
    }//GEN-LAST:event_btnBuscarEMouseClicked

    private void chkHoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHoyActionPerformed
        if(chkHoy.isSelected()){
            check = "H";
            txtBuscar.setEnabled(false);
            dtFechaI.setEnabled(false);
            dtFechaF.setEnabled(false);
            btnBuscarE.setVisible(false);
            txtBuscar.setEnabled(true);
            dtFechaI.setDate(null);
            dtFechaF.setDate(null);
            ConsultorioExtEsnitss consultorio1 = new ConsultorioExtEsnitss();
            consultorio1.consultorioExtEsnitssListar(txtBuscar.getText(), check,"","", tbEsnitss);
        
        }else{
            check = "F";
            dtFechaI.setEnabled(true);
            dtFechaF.setEnabled(true);
            btnBuscarE.setVisible(true);
            txtBuscar.setEnabled(false);
        }
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_chkHoyActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        lblMant.setText("E");
        pnlMensaje.setVisible(true);
        lblMensaje.setText("¿Eliminar los datos?");
        btnSi.setText("Si");
        btnSi.setVisible(true);
        btnNo.setVisible(true);
        pnlMensaje.setBackground(new Color(255,153,51));
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        btnGuardar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        habilitarDatos(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtBuscarPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarPacienteCaretUpdate
        ConsultorioExtEsnitss consultorio1 = new ConsultorioExtEsnitss();
        consultorio1.consultorioExListarC(txtBuscarPaciente.getText(), "Q", tbPacientes);
    }//GEN-LAST:event_txtBuscarPacienteCaretUpdate

    private void txtBuscarPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarPacienteMouseClicked

    }//GEN-LAST:event_txtBuscarPacienteMouseClicked

    private void txtBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarPacienteActionPerformed

    private void txtBuscarPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPacienteKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbEsnitss.getSelectionModel().setSelectionInterval(0, 0);
            tbEsnitss.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarPacienteKeyPressed

    private void T6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T6MouseClicked

    }//GEN-LAST:event_T6MouseClicked

    private void tbPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesMouseClicked
        if(evt.getClickCount()==2){
            enviarDatosPacientes();
        }
    }//GEN-LAST:event_tbPacientesMouseClicked

    private void tbPacientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesMousePressed

    }//GEN-LAST:event_tbPacientesMousePressed

    private void tbPacientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacientesKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbPacientes.getSelectedRow()==0){
            txtBuscarPaciente.requestFocus();
            tbPacientes.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosPacientes();
        }
    }//GEN-LAST:event_tbPacientesKeyPressed

    private void txtLamivudinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLamivudinaActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtLamivudinaActionPerformed

    private void txtNevirapinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNevirapinaActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtNevirapinaActionPerformed

    private void txtInhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInhActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtInhActionPerformed

    private void txtTenofovirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenofovirKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtTenofovirKeyTyped

    private void txtZidobudinaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtZidobudinaKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtZidobudinaKeyTyped

    private void txtAbacavirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbacavirKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtAbacavirKeyTyped

    private void txtLamivudinaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLamivudinaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLamivudinaKeyPressed

    private void txtLamivudinaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLamivudinaKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtLamivudinaKeyTyped

    private void txtEfavirenzKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfavirenzKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtEfavirenzKeyTyped

    private void txtNevirapinaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNevirapinaKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtNevirapinaKeyTyped

    private void txtRitonavirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRitonavirKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtRitonavirKeyTyped

    private void txtAtazanavirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAtazanavirKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtAtazanavirKeyTyped

    private void txtRatelgravirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRatelgravirKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtRatelgravirKeyTyped

    private void txtDuovirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDuovirKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtDuovirKeyTyped

    private void txtDuovirNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDuovirNKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtDuovirNKeyTyped

    private void txtKelatraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKelatraKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtKelatraKeyTyped

    private void txtCotrimoxazolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCotrimoxazolKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtCotrimoxazolKeyTyped

    private void txtInhKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInhKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtInhKeyTyped

    private void txtTenofovirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenofovirActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtTenofovirActionPerformed

    private void txtZidobudinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtZidobudinaActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtZidobudinaActionPerformed

    private void txtAbacavirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbacavirActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtAbacavirActionPerformed

    private void txtEfavirenzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEfavirenzActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtEfavirenzActionPerformed

    private void txtRitonavirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRitonavirActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtRitonavirActionPerformed

    private void txtAtazanavirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAtazanavirActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtAtazanavirActionPerformed

    private void txtRatelgravirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRatelgravirActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtRatelgravirActionPerformed

    private void txtDuovirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDuovirActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtDuovirActionPerformed

    private void txtDuovirNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDuovirNActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtDuovirNActionPerformed

    private void txtKelatraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKelatraActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtKelatraActionPerformed

    private void txtCotrimoxazolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCotrimoxazolActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtCotrimoxazolActionPerformed

    private void txtTenofovirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenofovirMouseClicked
        if(evt.getClickCount()==1)
             pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtTenofovirMouseClicked

    private void txtZidobudinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtZidobudinaMouseClicked
        if(evt.getClickCount()==1)
             pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtZidobudinaMouseClicked

    private void txtAbacavirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAbacavirMouseClicked
        if(evt.getClickCount()==1)
             pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtAbacavirMouseClicked

    private void txtLamivudinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLamivudinaMouseClicked
        if(evt.getClickCount()==1)
             pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtLamivudinaMouseClicked

    private void txtEfavirenzMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEfavirenzMouseClicked
        if(evt.getClickCount()==1)
             pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtEfavirenzMouseClicked

    private void txtNevirapinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNevirapinaMouseClicked
        if(evt.getClickCount()==1)
             pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtNevirapinaMouseClicked

    private void txtRitonavirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRitonavirMouseClicked
        if(evt.getClickCount()==1)
             pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtRitonavirMouseClicked

    private void txtAtazanavirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAtazanavirMouseClicked
        if(evt.getClickCount()==1)
             pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtAtazanavirMouseClicked

    private void txtRatelgravirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRatelgravirMouseClicked
        if(evt.getClickCount()==1)
             pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtRatelgravirMouseClicked

    private void txtDuovirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDuovirMouseClicked
        if(evt.getClickCount()==1)
             pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtDuovirMouseClicked

    private void txtDuovirNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDuovirNMouseClicked
        if(evt.getClickCount()==1)
             pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtDuovirNMouseClicked

    private void txtKelatraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtKelatraMouseClicked
        if(evt.getClickCount()==1)
             pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtKelatraMouseClicked

    private void txtCotrimoxazolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCotrimoxazolMouseClicked
        if(evt.getClickCount()==1)
             pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtCotrimoxazolMouseClicked

    private void txtInhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtInhMouseClicked
        if(evt.getClickCount()==1)
             pnlMensaje.setVisible(false);
    }//GEN-LAST:event_txtInhMouseClicked

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        if(!chkHoy.isSelected() && dtFechaI.getDate()==null && dtFechaF.getDate()==null){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione un rango de fechas");
                txtBuscar.setEnabled(false);
                btnSi.setVisible(true);
                btnNo.setVisible(false);
                btnSi.setText("OK");
                pnlMensaje.setBackground(new Color(255,91,70));
        } else
        if(!chkHoy.isSelected() && dtFechaI.getDate()!=null && dtFechaF.getDate()!=null){
            ConsultorioExtEsnitss consultorio1 = new ConsultorioExtEsnitss();
            consultorio1.consultorioExtEsnitssListar(txtBuscar.getText(), check,determinarFecha(dtFechaI),determinarFecha(dtFechaF), tbEsnitss);
        }else {
            ConsultorioExtEsnitss consultorio1 = new ConsultorioExtEsnitss();
            consultorio1.consultorioExtEsnitssListar(txtBuscar.getText(), check,"","", tbEsnitss);
        }
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMouseClicked
        if(evt.getClickCount()==1){
            if(!chkHoy.isSelected() && dtFechaI.getDate()==null && dtFechaF.getDate()==null){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione un rango de fechas");
                txtBuscar.setEnabled(false);
                btnSi.setVisible(true);
                btnNo.setVisible(false);
                btnSi.setText("OK");
                pnlMensaje.setBackground(new Color(255,91,70));
            } 
        }
    }//GEN-LAST:event_txtBuscarMouseClicked

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
            java.util.logging.Logger.getLogger(ESNITSS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ESNITSS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ESNITSS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ESNITSS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ESNITSS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BuscarPacientes;
    private javax.swing.JLabel T6;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel btnBuscarE;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSi;
    private javax.swing.JButton btneliminar1;
    private javax.swing.JCheckBox chkHoy;
    private com.toedter.calendar.JDateChooser dtFechaF;
    private com.toedter.calendar.JDateChooser dtFechaI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblAM;
    private javax.swing.JLabel lblActoMedico;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHc;
    private javax.swing.JLabel lblHc1;
    private javax.swing.JLabel lblHc10;
    private javax.swing.JLabel lblHc11;
    private javax.swing.JLabel lblHc12;
    private javax.swing.JLabel lblHc13;
    private javax.swing.JLabel lblHc14;
    private javax.swing.JLabel lblHc15;
    private javax.swing.JLabel lblHc16;
    private javax.swing.JLabel lblHc17;
    private javax.swing.JLabel lblHc18;
    private javax.swing.JLabel lblHc19;
    private javax.swing.JLabel lblHc2;
    private javax.swing.JLabel lblHc20;
    private javax.swing.JLabel lblHc21;
    private javax.swing.JLabel lblHc22;
    private javax.swing.JLabel lblHc23;
    private javax.swing.JLabel lblHc24;
    private javax.swing.JLabel lblHc25;
    private javax.swing.JLabel lblHc26;
    private javax.swing.JLabel lblHc27;
    private javax.swing.JLabel lblHc28;
    private javax.swing.JLabel lblHc29;
    private javax.swing.JLabel lblHc3;
    private javax.swing.JLabel lblHc30;
    private javax.swing.JLabel lblHc31;
    private javax.swing.JLabel lblHc32;
    private javax.swing.JLabel lblHc33;
    private javax.swing.JLabel lblHc34;
    private javax.swing.JLabel lblHc35;
    private javax.swing.JLabel lblHc36;
    private javax.swing.JLabel lblHc37;
    private javax.swing.JLabel lblHc38;
    private javax.swing.JLabel lblHc39;
    private javax.swing.JLabel lblHc4;
    private javax.swing.JLabel lblHc40;
    private javax.swing.JLabel lblHc41;
    private javax.swing.JLabel lblHc42;
    private javax.swing.JLabel lblHc43;
    private javax.swing.JLabel lblHc44;
    private javax.swing.JLabel lblHc45;
    private javax.swing.JLabel lblHc46;
    private javax.swing.JLabel lblHc47;
    private javax.swing.JLabel lblHc48;
    private javax.swing.JLabel lblHc49;
    private javax.swing.JLabel lblHc5;
    private javax.swing.JLabel lblHc50;
    private javax.swing.JLabel lblHc51;
    private javax.swing.JLabel lblHc52;
    private javax.swing.JLabel lblHc53;
    private javax.swing.JLabel lblHc54;
    private javax.swing.JLabel lblHc55;
    private javax.swing.JLabel lblHc56;
    private javax.swing.JLabel lblHc57;
    private javax.swing.JLabel lblHc58;
    private javax.swing.JLabel lblHc59;
    private javax.swing.JLabel lblHc6;
    private javax.swing.JLabel lblHc60;
    private javax.swing.JLabel lblHc61;
    private javax.swing.JLabel lblHc62;
    private javax.swing.JLabel lblHc65;
    private javax.swing.JLabel lblHc7;
    private javax.swing.JLabel lblHc8;
    private javax.swing.JLabel lblHc9;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdHc;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblTriaje;
    private javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JTable tbEsnitss;
    private javax.swing.JTable tbPacientes;
    private javax.swing.JTabbedPane tpEsnitss;
    private javax.swing.JTextField txtAbacavir;
    private javax.swing.JTextField txtAtazanavir;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarPaciente;
    public static javax.swing.JTextField txtCotrimoxazol;
    public static javax.swing.JTextField txtDuovir;
    public static javax.swing.JTextField txtDuovirN;
    private javax.swing.JTextField txtEfavirenz;
    public static javax.swing.JTextField txtInh;
    public static javax.swing.JTextField txtKelatra;
    private javax.swing.JTextField txtLamivudina;
    private javax.swing.JTextField txtNevirapina;
    public static javax.swing.JTextField txtPaciente;
    private javax.swing.JTextField txtRatelgravir;
    private javax.swing.JTextField txtRitonavir;
    private javax.swing.JTextField txtTenofovir;
    private javax.swing.JTextField txtZidobudina;
    // End of variables declaration//GEN-END:variables
}
