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
import java.util.Calendar;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;
import modelos.ConsultorioEx.ConsultorioExtRsCcd;
import static vista.ConsultorioEx.RegistroSeguimiento.jTabbedPane1;

/**
 *
 * @author MYS1
 */
public class RSAICCD extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
private Dimension DimensionBarra = null;

ConsultorioExtRsCcd adCXRSCCD = new ConsultorioExtRsCcd();
static ConsultorioExtRsCcd adCXRSCCDA = new ConsultorioExtRsCcd();
static DefaultTableModel m;
byte tg;
byte tge;
    /**
     * Creates new form RSAICCD
     */
    public RSAICCD() {
        initComponents();
        QuitarLaBarraTitulo();
        jTabbedPane1.setEnabledAt(0,false);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        HC_ID.setText(RegistroSeguimiento.lblHc.getText());
//        if(){
//            
//        }
        lblnina.setText(RegistroSeguimiento.txtPaciente.getText());
        lblnino.setText(RegistroSeguimiento.txtPaciente.getText());
    }
    public void QuitarLaBarraTitulo()
{ 
Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
DimensionBarra = Barra.getPreferredSize(); 
Barra.setSize(0,0); 
Barra.setPreferredSize(new Dimension(0,0)); 
repaint(); 
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
//                           mensaje.setBackground(new Color(255,91,70)); 
//                           men.setText("Ingrese una fecha correcta");
                          
         }
        
        return fecha;
    }
    public void enviarDiagnosticos(){
        int fila = tbCiePresun.getSelectedRow();
        FrmCie10.dispose();
        CIE10_ID.setText(String.valueOf(tbCiePresun.getValueAt(fila, 0)));
        DXCCDRN1.setText(String.valueOf(tbCiePresun.getValueAt(fila, 1)));
    }
    
    public void Guardar(){

                            ConsultorioExtRsCcd GCCD= new ConsultorioExtRsCcd();
           

                            GCCD.setRsCcd(0);
                            GCCD.setRS_ID(Integer.parseInt(HC_ID.getText()));
                            GCCD.setDescripcion(TxtDes.getText());
                            GCCD.setFecha(TxtFecha.getText());
                            GCCD.setID_CIE10(Integer.parseInt(CIE10_ID.getText()));
                            GCCD.setFua(TxtFua.getText());
//                            GCCD.setRsCcd(0);
//                            GCCD.setRS_ID(txtPA.getText());
//                            GCCD.setFecha(txtFC.getText());
//                            GCCD.setID_CIE10(txtT.getText());
//                            GCCD.setFua(txtPeso.getText());

                            if(GCCD.mantenimientoCCD("I")==true){
                                mensaje.setBackground(new Color(33,115,70)); 
                                men.setText("Datos Guardados de forma correcta");
//                                b.setText("OK");
//                                b.setVisible(true);
//                                b1.setVisible(false);

                                btnGuardar.setEnabled(false);
                                tge=1;
//                                listar();
                            
                        }else {
                           
                                mensaje.setVisible(true);
                                mensaje.setBackground(new Color(255,91,70)); 
                                men.setText("Ocurrio un error, Verifique");
//                                b.setVisible(false);
//                                b1.setVisible(false);
                                tge=7;
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
            jTabbedPane1 = new javax.swing.JTabbedPane();
            CCD = new javax.swing.JPanel();
            jPanel28 = new javax.swing.JPanel();
            jLabel17 = new javax.swing.JLabel();
            jPanel29 = new javax.swing.JPanel();
            jLabel20 = new javax.swing.JLabel();
            CCDRN = new javax.swing.JPanel();
            jPanel67 = new javax.swing.JPanel();
            jLabel58 = new javax.swing.JLabel();
            jPanel68 = new javax.swing.JPanel();
            jLabel59 = new javax.swing.JLabel();
            FCCDRN1 = new com.toedter.calendar.JDateChooser();
            jPanel69 = new javax.swing.JPanel();
            jLabel60 = new javax.swing.JLabel();
            FCCDRN2 = new com.toedter.calendar.JDateChooser();
            jPanel70 = new javax.swing.JPanel();
            jLabel61 = new javax.swing.JLabel();
            FCCDRN3 = new com.toedter.calendar.JDateChooser();
            FUACCDRN3 = new javax.swing.JTextField();
            FUACCDRN1 = new javax.swing.JTextField();
            FUACCDRN2 = new javax.swing.JTextField();
            jPanel71 = new javax.swing.JPanel();
            jLabel62 = new javax.swing.JLabel();
            FCCDRN4 = new com.toedter.calendar.JDateChooser();
            FUACCDRN4 = new javax.swing.JTextField();
            DXCCDRN1 = new javax.swing.JLabel();
            DXCCDRN2 = new javax.swing.JLabel();
            DXCCDRN3 = new javax.swing.JLabel();
            DXCCDRN4 = new javax.swing.JLabel();
            LEYENDA = new javax.swing.JPanel();
            jPanel72 = new javax.swing.JPanel();
            jPanel73 = new javax.swing.JPanel();
            jLabel64 = new javax.swing.JLabel();
            jPanel74 = new javax.swing.JPanel();
            jLabel63 = new javax.swing.JLabel();
            jPanel75 = new javax.swing.JPanel();
            jLabel65 = new javax.swing.JLabel();
            jLabel66 = new javax.swing.JLabel();
            CCDM11 = new javax.swing.JPanel();
            jPanel76 = new javax.swing.JPanel();
            jLabel67 = new javax.swing.JLabel();
            jPanel77 = new javax.swing.JPanel();
            jLabel68 = new javax.swing.JLabel();
            FCCDM1 = new com.toedter.calendar.JDateChooser();
            jPanel78 = new javax.swing.JPanel();
            jLabel69 = new javax.swing.JLabel();
            FCCDM2 = new com.toedter.calendar.JDateChooser();
            jPanel79 = new javax.swing.JPanel();
            jLabel70 = new javax.swing.JLabel();
            FCCDM3 = new com.toedter.calendar.JDateChooser();
            FUACCDM3 = new javax.swing.JTextField();
            FUACCDM1 = new javax.swing.JTextField();
            FUACCDM2 = new javax.swing.JTextField();
            jPanel80 = new javax.swing.JPanel();
            jLabel71 = new javax.swing.JLabel();
            FCCDM4 = new com.toedter.calendar.JDateChooser();
            FUACCDM4 = new javax.swing.JTextField();
            DXCCDM1 = new javax.swing.JLabel();
            DXCCDM2 = new javax.swing.JLabel();
            DXCCDM3 = new javax.swing.JLabel();
            DXCCDM4 = new javax.swing.JLabel();
            CCDM12 = new javax.swing.JPanel();
            jPanel81 = new javax.swing.JPanel();
            jLabel72 = new javax.swing.JLabel();
            jPanel82 = new javax.swing.JPanel();
            jLabel73 = new javax.swing.JLabel();
            FCCDM5 = new com.toedter.calendar.JDateChooser();
            jPanel83 = new javax.swing.JPanel();
            jLabel74 = new javax.swing.JLabel();
            FCCDM6 = new com.toedter.calendar.JDateChooser();
            jPanel84 = new javax.swing.JPanel();
            jLabel75 = new javax.swing.JLabel();
            FCCDM7 = new com.toedter.calendar.JDateChooser();
            FUACCDM7 = new javax.swing.JTextField();
            FUACCDM5 = new javax.swing.JTextField();
            FUACCDM6 = new javax.swing.JTextField();
            DXCCDM5 = new javax.swing.JLabel();
            DXCCDM6 = new javax.swing.JLabel();
            DXCCDM7 = new javax.swing.JLabel();
            jPanel85 = new javax.swing.JPanel();
            jLabel76 = new javax.swing.JLabel();
            FCCDM8 = new com.toedter.calendar.JDateChooser();
            DXCCDM8 = new javax.swing.JLabel();
            FUACCDM8 = new javax.swing.JTextField();
            jPanel86 = new javax.swing.JPanel();
            jLabel77 = new javax.swing.JLabel();
            FCCDM9 = new com.toedter.calendar.JDateChooser();
            DXCCDM9 = new javax.swing.JLabel();
            FUACCDM9 = new javax.swing.JTextField();
            jPanel87 = new javax.swing.JPanel();
            jLabel78 = new javax.swing.JLabel();
            FCCDM10 = new com.toedter.calendar.JDateChooser();
            DXCCDM10 = new javax.swing.JLabel();
            FUACCDM10 = new javax.swing.JTextField();
            jPanel88 = new javax.swing.JPanel();
            jLabel79 = new javax.swing.JLabel();
            FCCDM11 = new com.toedter.calendar.JDateChooser();
            DXCCDM11 = new javax.swing.JLabel();
            FUACCDM11 = new javax.swing.JTextField();
            jPanel169 = new javax.swing.JPanel();
            jLabel107 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            CIE10_ID = new javax.swing.JLabel();
            HC_ID = new javax.swing.JLabel();
            TxtFecha = new javax.swing.JLabel();
            TxtFua = new javax.swing.JLabel();
            TxtDes = new javax.swing.JLabel();
            lblnina = new javax.swing.JLabel();
            lblnino = new javax.swing.JLabel();
            CCD1 = new javax.swing.JPanel();
            CCD1A = new javax.swing.JPanel();
            jPanel89 = new javax.swing.JPanel();
            jLabel82 = new javax.swing.JLabel();
            jPanel90 = new javax.swing.JPanel();
            jLabel83 = new javax.swing.JLabel();
            FCCD11 = new com.toedter.calendar.JDateChooser();
            jPanel91 = new javax.swing.JPanel();
            jLabel84 = new javax.swing.JLabel();
            FCCD12 = new com.toedter.calendar.JDateChooser();
            jPanel92 = new javax.swing.JPanel();
            jLabel85 = new javax.swing.JLabel();
            FCCD13 = new com.toedter.calendar.JDateChooser();
            FUACCD13 = new javax.swing.JTextField();
            FUACCD11 = new javax.swing.JTextField();
            FUACCD12 = new javax.swing.JTextField();
            DXCCD11 = new javax.swing.JLabel();
            DXCCD12 = new javax.swing.JLabel();
            DXCCD13 = new javax.swing.JLabel();
            jPanel93 = new javax.swing.JPanel();
            jLabel86 = new javax.swing.JLabel();
            FCCD14 = new com.toedter.calendar.JDateChooser();
            DXCCD14 = new javax.swing.JLabel();
            FUACCD14 = new javax.swing.JTextField();
            jPanel94 = new javax.swing.JPanel();
            jLabel87 = new javax.swing.JLabel();
            FCCD15 = new com.toedter.calendar.JDateChooser();
            DXCCD15 = new javax.swing.JLabel();
            FUACCD15 = new javax.swing.JTextField();
            jPanel95 = new javax.swing.JPanel();
            jLabel88 = new javax.swing.JLabel();
            FCCD16 = new com.toedter.calendar.JDateChooser();
            DXCCD16 = new javax.swing.JLabel();
            FUACCD16 = new javax.swing.JTextField();
            CCDR2A = new javax.swing.JPanel();
            jPanel96 = new javax.swing.JPanel();
            jLabel89 = new javax.swing.JLabel();
            jPanel97 = new javax.swing.JPanel();
            jLabel90 = new javax.swing.JLabel();
            FCCD21 = new com.toedter.calendar.JDateChooser();
            jPanel98 = new javax.swing.JPanel();
            jLabel91 = new javax.swing.JLabel();
            FCCD22 = new com.toedter.calendar.JDateChooser();
            jPanel99 = new javax.swing.JPanel();
            jLabel92 = new javax.swing.JLabel();
            FCCD23 = new com.toedter.calendar.JDateChooser();
            FUACCD23 = new javax.swing.JTextField();
            FUACCD21 = new javax.swing.JTextField();
            FUACCD22 = new javax.swing.JTextField();
            jPanel100 = new javax.swing.JPanel();
            jLabel93 = new javax.swing.JLabel();
            FCCD24 = new com.toedter.calendar.JDateChooser();
            FUACCD24 = new javax.swing.JTextField();
            DXCCD21 = new javax.swing.JLabel();
            DXCCD22 = new javax.swing.JLabel();
            DXCCD23 = new javax.swing.JLabel();
            DXCCD24 = new javax.swing.JLabel();
            CCDR3A = new javax.swing.JPanel();
            jPanel101 = new javax.swing.JPanel();
            jLabel94 = new javax.swing.JLabel();
            jPanel102 = new javax.swing.JPanel();
            jLabel95 = new javax.swing.JLabel();
            FCCD3A1 = new com.toedter.calendar.JDateChooser();
            jPanel103 = new javax.swing.JPanel();
            jLabel96 = new javax.swing.JLabel();
            FCCD3A2 = new com.toedter.calendar.JDateChooser();
            jPanel104 = new javax.swing.JPanel();
            jLabel97 = new javax.swing.JLabel();
            FCCD3A3 = new com.toedter.calendar.JDateChooser();
            FUACCD3A3 = new javax.swing.JTextField();
            FUACCD3A1 = new javax.swing.JTextField();
            FUACCD3A2 = new javax.swing.JTextField();
            jPanel105 = new javax.swing.JPanel();
            jLabel98 = new javax.swing.JLabel();
            FCCD3A4 = new com.toedter.calendar.JDateChooser();
            FUACCD3A4 = new javax.swing.JTextField();
            DXCCD3A1 = new javax.swing.JLabel();
            DXCCD3A2 = new javax.swing.JLabel();
            DXCCD3A3 = new javax.swing.JLabel();
            DXCCD3A4 = new javax.swing.JLabel();
            jPanel106 = new javax.swing.JPanel();
            jLabel99 = new javax.swing.JLabel();
            jPanel107 = new javax.swing.JPanel();
            jLabel100 = new javax.swing.JLabel();
            CCDR3A1 = new javax.swing.JPanel();
            jPanel108 = new javax.swing.JPanel();
            jLabel101 = new javax.swing.JLabel();
            jPanel109 = new javax.swing.JPanel();
            jLabel102 = new javax.swing.JLabel();
            FCCD4A1 = new com.toedter.calendar.JDateChooser();
            jPanel110 = new javax.swing.JPanel();
            jLabel103 = new javax.swing.JLabel();
            FCCD4A2 = new com.toedter.calendar.JDateChooser();
            jPanel111 = new javax.swing.JPanel();
            jLabel104 = new javax.swing.JLabel();
            FCCD4A3 = new com.toedter.calendar.JDateChooser();
            FUACCD4A3 = new javax.swing.JTextField();
            FUACCD4A1 = new javax.swing.JTextField();
            FUACCD4A2 = new javax.swing.JTextField();
            jPanel112 = new javax.swing.JPanel();
            jLabel105 = new javax.swing.JLabel();
            FCCD4A4 = new com.toedter.calendar.JDateChooser();
            FUACCD4A4 = new javax.swing.JTextField();
            DXCCD4A1 = new javax.swing.JLabel();
            DXCCD4A2 = new javax.swing.JLabel();
            DXCCD4A3 = new javax.swing.JLabel();
            DXCCD4A4 = new javax.swing.JLabel();
            jPanel170 = new javax.swing.JPanel();
            jLabel108 = new javax.swing.JLabel();
            jLabel109 = new javax.swing.JLabel();
            DXCCDRN7 = new javax.swing.JLabel();
            DXCCDRN8 = new javax.swing.JLabel();
            CCD2 = new javax.swing.JPanel();
            jPanel113 = new javax.swing.JPanel();
            jLabel106 = new javax.swing.JLabel();
            jPanel114 = new javax.swing.JPanel();
            jLabel110 = new javax.swing.JLabel();
            CCDR5A = new javax.swing.JPanel();
            jPanel115 = new javax.swing.JPanel();
            jLabel111 = new javax.swing.JLabel();
            jPanel116 = new javax.swing.JPanel();
            jLabel112 = new javax.swing.JLabel();
            FCCD5A = new com.toedter.calendar.JDateChooser();
            FUACCD5A = new javax.swing.JTextField();
            DXCCD5A = new javax.swing.JLabel();
            CCDR6A = new javax.swing.JPanel();
            jPanel117 = new javax.swing.JPanel();
            jLabel113 = new javax.swing.JLabel();
            jPanel118 = new javax.swing.JPanel();
            jLabel114 = new javax.swing.JLabel();
            FCCD6A = new com.toedter.calendar.JDateChooser();
            FUACCD6A = new javax.swing.JTextField();
            DXCCD6A = new javax.swing.JLabel();
            CCDR7A = new javax.swing.JPanel();
            jPanel119 = new javax.swing.JPanel();
            jLabel115 = new javax.swing.JLabel();
            jPanel120 = new javax.swing.JPanel();
            jLabel116 = new javax.swing.JLabel();
            FCCD7A = new com.toedter.calendar.JDateChooser();
            FUACCD7A = new javax.swing.JTextField();
            DXCCD7A = new javax.swing.JLabel();
            CCDR8A = new javax.swing.JPanel();
            jPanel121 = new javax.swing.JPanel();
            jLabel117 = new javax.swing.JLabel();
            jPanel122 = new javax.swing.JPanel();
            jLabel118 = new javax.swing.JLabel();
            FCCD8A = new com.toedter.calendar.JDateChooser();
            FUACCD8A = new javax.swing.JTextField();
            DXCCD8A = new javax.swing.JLabel();
            CCDR9A = new javax.swing.JPanel();
            jPanel123 = new javax.swing.JPanel();
            jLabel119 = new javax.swing.JLabel();
            jPanel124 = new javax.swing.JPanel();
            jLabel120 = new javax.swing.JLabel();
            FCCD9A = new com.toedter.calendar.JDateChooser();
            FUACCD9A = new javax.swing.JTextField();
            DXCCD9A = new javax.swing.JLabel();
            CCDR10A = new javax.swing.JPanel();
            jPanel125 = new javax.swing.JPanel();
            jLabel121 = new javax.swing.JLabel();
            jPanel126 = new javax.swing.JPanel();
            jLabel122 = new javax.swing.JLabel();
            FCCD10A = new com.toedter.calendar.JDateChooser();
            FUACCD10A = new javax.swing.JTextField();
            DXCCD10A = new javax.swing.JLabel();
            CCDR11A = new javax.swing.JPanel();
            jPanel127 = new javax.swing.JPanel();
            jLabel123 = new javax.swing.JLabel();
            jPanel128 = new javax.swing.JPanel();
            jLabel124 = new javax.swing.JLabel();
            FCCD11A = new com.toedter.calendar.JDateChooser();
            FUACCD11A = new javax.swing.JTextField();
            DXCCD11A = new javax.swing.JLabel();
            DXCCDRN9 = new javax.swing.JLabel();
            DXCCDRN10 = new javax.swing.JLabel();
            mensaje = new javax.swing.JPanel();
            jPanel31 = new javax.swing.JPanel();
            btneditar4 = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            men = new javax.swing.JLabel();

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

            jPanel32.setBackground(new java.awt.Color(39, 174, 97));

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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
            tbCiePresun.setSelectionBackground(new java.awt.Color(39, 174, 97));
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

            jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

            CCD.setBackground(new java.awt.Color(255, 255, 255));

            jPanel28.setBackground(new java.awt.Color(45, 204, 112));
            jPanel28.setPreferredSize(new java.awt.Dimension(83, 45));

            jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel17.setForeground(new java.awt.Color(255, 255, 255));
            jLabel17.setText("CONTROL DE CRECIMIENTO Y DESARROLLO");

            jPanel29.setBackground(new java.awt.Color(39, 174, 97));

            jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel20.setForeground(new java.awt.Color(255, 255, 255));
            jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
            jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel20MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
            jPanel29.setLayout(jPanel29Layout);
            jPanel29Layout.setHorizontalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20)
                    .addContainerGap())
            );
            jPanel29Layout.setVerticalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jLabel20)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
            jPanel28.setLayout(jPanel28Layout);
            jPanel28Layout.setHorizontalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel17)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel28Layout.setVerticalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            CCDRN.setBackground(new java.awt.Color(204, 204, 204));

            jPanel67.setBackground(new java.awt.Color(102, 102, 102));

            jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel58.setForeground(new java.awt.Color(255, 255, 255));
            jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel58.setText("RECIEN NACIDO");

            javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
            jPanel67.setLayout(jPanel67Layout);
            jPanel67Layout.setHorizontalGroup(
                jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel67Layout.createSequentialGroup()
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel67Layout.setVerticalGroup(
                jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel58)
            );

            jPanel68.setBackground(new java.awt.Color(153, 153, 153));

            jLabel59.setBackground(new java.awt.Color(153, 153, 153));
            jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel59.setForeground(new java.awt.Color(255, 255, 255));
            jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel59.setText("1");

            FCCDRN1.setBackground(new java.awt.Color(255, 255, 255));
            FCCDRN1.setDateFormatString("dd/MM/yyyy");
            FCCDRN1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
            jPanel68.setLayout(jPanel68Layout);
            jPanel68Layout.setHorizontalGroup(
                jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCDRN1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel68Layout.setVerticalGroup(
                jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel68Layout.createSequentialGroup()
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDRN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel69.setBackground(new java.awt.Color(153, 153, 153));

            jLabel60.setBackground(new java.awt.Color(153, 153, 153));
            jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel60.setForeground(new java.awt.Color(255, 255, 255));
            jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel60.setText("2");

            FCCDRN2.setBackground(new java.awt.Color(255, 255, 255));
            FCCDRN2.setDateFormatString("dd/MM/yyyy");
            FCCDRN2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
            jPanel69.setLayout(jPanel69Layout);
            jPanel69Layout.setHorizontalGroup(
                jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCDRN2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel69Layout.setVerticalGroup(
                jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel69Layout.createSequentialGroup()
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDRN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel70.setBackground(new java.awt.Color(153, 153, 153));

            jLabel61.setBackground(new java.awt.Color(153, 153, 153));
            jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel61.setForeground(new java.awt.Color(255, 255, 255));
            jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel61.setText("3");

            FCCDRN3.setBackground(new java.awt.Color(255, 255, 255));
            FCCDRN3.setDateFormatString("dd/MM/yyyy");
            FCCDRN3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
            jPanel70.setLayout(jPanel70Layout);
            jPanel70Layout.setHorizontalGroup(
                jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCDRN3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel70Layout.setVerticalGroup(
                jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel70Layout.createSequentialGroup()
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDRN3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            FUACCDRN3.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    FUACCDRN3CaretUpdate(evt);
                }
            });

            FUACCDRN1.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    FUACCDRN1CaretUpdate(evt);
                }
            });
            FUACCDRN1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    FUACCDRN1MouseClicked(evt);
                }
            });

            FUACCDRN2.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    FUACCDRN2CaretUpdate(evt);
                }
            });
            FUACCDRN2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    FUACCDRN2ActionPerformed(evt);
                }
            });

            jPanel71.setBackground(new java.awt.Color(153, 153, 153));

            jLabel62.setBackground(new java.awt.Color(153, 153, 153));
            jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel62.setForeground(new java.awt.Color(255, 255, 255));
            jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel62.setText("4");

            FCCDRN4.setBackground(new java.awt.Color(255, 255, 255));
            FCCDRN4.setDateFormatString("dd/MM/yyyy");
            FCCDRN4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
            jPanel71.setLayout(jPanel71Layout);
            jPanel71Layout.setHorizontalGroup(
                jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCDRN4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel71Layout.setVerticalGroup(
                jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel71Layout.createSequentialGroup()
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDRN4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            FUACCDRN4.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    FUACCDRN4CaretUpdate(evt);
                }
            });

            DXCCDRN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDRN1.setText("                                ");
            DXCCDRN1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            DXCCDRN1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXCCDRN1MouseClicked(evt);
                }
            });

            DXCCDRN2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDRN2.setText("                                ");
            DXCCDRN2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCDRN3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDRN3.setText("                                ");
            DXCCDRN3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCDRN4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDRN4.setText("                                ");
            DXCCDRN4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDRNLayout = new javax.swing.GroupLayout(CCDRN);
            CCDRN.setLayout(CCDRNLayout);
            CCDRNLayout.setHorizontalGroup(
                CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDRNLayout.createSequentialGroup()
                    .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel68, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDRN1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXCCDRN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDRN2)
                        .addComponent(DXCCDRN2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCDRN3)
                        .addComponent(jPanel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCDRN3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDRN4)
                        .addComponent(DXCCDRN4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            CCDRNLayout.setVerticalGroup(
                CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDRNLayout.createSequentialGroup()
                    .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 4, Short.MAX_VALUE)
                    .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXCCDRN1)
                        .addComponent(DXCCDRN2)
                        .addComponent(DXCCDRN3)
                        .addComponent(DXCCDRN4))
                    .addGap(0, 0, 0)
                    .addGroup(CCDRNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FUACCDRN3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCDRN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCDRN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCDRN4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            LEYENDA.setBackground(new java.awt.Color(204, 204, 204));

            jPanel72.setBackground(new java.awt.Color(153, 153, 153));

            javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
            jPanel72.setLayout(jPanel72Layout);
            jPanel72Layout.setHorizontalGroup(
                jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );
            jPanel72Layout.setVerticalGroup(
                jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 20, Short.MAX_VALUE)
            );

            jPanel73.setBackground(new java.awt.Color(153, 153, 153));

            jLabel64.setBackground(new java.awt.Color(153, 153, 153));
            jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel64.setForeground(new java.awt.Color(255, 255, 255));
            jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel64.setText("Nº CONRTROL");

            javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
            jPanel73.setLayout(jPanel73Layout);
            jPanel73Layout.setHorizontalGroup(
                jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel73Layout.createSequentialGroup()
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 26, Short.MAX_VALUE))
            );
            jPanel73Layout.setVerticalGroup(
                jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel73Layout.createSequentialGroup()
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel74.setBackground(new java.awt.Color(204, 204, 204));

            jLabel63.setForeground(new java.awt.Color(255, 255, 255));
            jLabel63.setText("FECHA");

            javax.swing.GroupLayout jPanel74Layout = new javax.swing.GroupLayout(jPanel74);
            jPanel74.setLayout(jPanel74Layout);
            jPanel74Layout.setHorizontalGroup(
                jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel74Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel74Layout.setVerticalGroup(
                jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel74Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel63))
            );

            jPanel75.setBackground(new java.awt.Color(153, 153, 153));
            jPanel75.setForeground(new java.awt.Color(255, 255, 255));

            jLabel65.setForeground(new java.awt.Color(255, 255, 255));
            jLabel65.setText("DX");

            javax.swing.GroupLayout jPanel75Layout = new javax.swing.GroupLayout(jPanel75);
            jPanel75.setLayout(jPanel75Layout);
            jPanel75Layout.setHorizontalGroup(
                jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel75Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel75Layout.setVerticalGroup(
                jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
            );

            jLabel66.setForeground(new java.awt.Color(255, 255, 255));
            jLabel66.setText("FUA");

            javax.swing.GroupLayout LEYENDALayout = new javax.swing.GroupLayout(LEYENDA);
            LEYENDA.setLayout(LEYENDALayout);
            LEYENDALayout.setHorizontalGroup(
                LEYENDALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(LEYENDALayout.createSequentialGroup()
                    .addGroup(LEYENDALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(LEYENDALayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            LEYENDALayout.setVerticalGroup(
                LEYENDALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LEYENDALayout.createSequentialGroup()
                    .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(jPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            CCDM11.setBackground(new java.awt.Color(204, 204, 204));

            jPanel76.setBackground(new java.awt.Color(102, 102, 102));

            jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel67.setForeground(new java.awt.Color(255, 255, 255));
            jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel67.setText("MENORES DE 1 AÑO");

            javax.swing.GroupLayout jPanel76Layout = new javax.swing.GroupLayout(jPanel76);
            jPanel76.setLayout(jPanel76Layout);
            jPanel76Layout.setHorizontalGroup(
                jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel76Layout.createSequentialGroup()
                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel76Layout.setVerticalGroup(
                jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel67)
            );

            jPanel77.setBackground(new java.awt.Color(153, 153, 153));

            jLabel68.setBackground(new java.awt.Color(153, 153, 153));
            jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel68.setForeground(new java.awt.Color(255, 255, 255));
            jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel68.setText("1º");

            FCCDM1.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM1.setDateFormatString("dd/MM/yyyy");
            FCCDM1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel77Layout = new javax.swing.GroupLayout(jPanel77);
            jPanel77.setLayout(jPanel77Layout);
            jPanel77Layout.setHorizontalGroup(
                jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel68, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCDM1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel77Layout.setVerticalGroup(
                jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel77Layout.createSequentialGroup()
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel78.setBackground(new java.awt.Color(153, 153, 153));

            jLabel69.setBackground(new java.awt.Color(153, 153, 153));
            jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel69.setForeground(new java.awt.Color(255, 255, 255));
            jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel69.setText("2º");

            FCCDM2.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM2.setDateFormatString("dd/MM/yyyy");
            FCCDM2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel78Layout = new javax.swing.GroupLayout(jPanel78);
            jPanel78.setLayout(jPanel78Layout);
            jPanel78Layout.setHorizontalGroup(
                jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCDM2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel78Layout.setVerticalGroup(
                jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel78Layout.createSequentialGroup()
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel79.setBackground(new java.awt.Color(153, 153, 153));

            jLabel70.setBackground(new java.awt.Color(153, 153, 153));
            jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel70.setForeground(new java.awt.Color(255, 255, 255));
            jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel70.setText("3º");

            FCCDM3.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM3.setDateFormatString("dd/MM/yyyy");
            FCCDM3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel79Layout = new javax.swing.GroupLayout(jPanel79);
            jPanel79.setLayout(jPanel79Layout);
            jPanel79Layout.setHorizontalGroup(
                jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCDM3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel79Layout.setVerticalGroup(
                jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel79Layout.createSequentialGroup()
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel80.setBackground(new java.awt.Color(153, 153, 153));

            jLabel71.setBackground(new java.awt.Color(153, 153, 153));
            jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel71.setForeground(new java.awt.Color(255, 255, 255));
            jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel71.setText("4º");

            FCCDM4.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM4.setDateFormatString("dd/MM/yyyy");
            FCCDM4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel80Layout = new javax.swing.GroupLayout(jPanel80);
            jPanel80.setLayout(jPanel80Layout);
            jPanel80Layout.setHorizontalGroup(
                jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCDM4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel80Layout.setVerticalGroup(
                jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel80Layout.createSequentialGroup()
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCDM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM1.setText("                                ");
            DXCCDM1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCDM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM2.setText("                                ");
            DXCCDM2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCDM3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM3.setText("                                ");
            DXCCDM3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCDM4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM4.setText("                                ");
            DXCCDM4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDM11Layout = new javax.swing.GroupLayout(CCDM11);
            CCDM11.setLayout(CCDM11Layout);
            CCDM11Layout.setHorizontalGroup(
                CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDM11Layout.createSequentialGroup()
                    .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel77, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDM1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXCCDM1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDM2)
                        .addComponent(DXCCDM2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCDM3)
                        .addComponent(jPanel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCDM3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDM4)
                        .addComponent(DXCCDM4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            CCDM11Layout.setVerticalGroup(
                CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDM11Layout.createSequentialGroup()
                    .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel77, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 4, Short.MAX_VALUE)
                    .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXCCDM1)
                        .addComponent(DXCCDM2)
                        .addComponent(DXCCDM3)
                        .addComponent(DXCCDM4))
                    .addGap(0, 0, 0)
                    .addGroup(CCDM11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FUACCDM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCDM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCDM2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCDM4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            CCDM12.setBackground(new java.awt.Color(204, 204, 204));

            jPanel81.setBackground(new java.awt.Color(102, 102, 102));

            jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel72.setForeground(new java.awt.Color(255, 255, 255));
            jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel72.setText("MENORES DE 1 AÑO");

            javax.swing.GroupLayout jPanel81Layout = new javax.swing.GroupLayout(jPanel81);
            jPanel81.setLayout(jPanel81Layout);
            jPanel81Layout.setHorizontalGroup(
                jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel81Layout.createSequentialGroup()
                    .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel81Layout.setVerticalGroup(
                jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel72)
            );

            jPanel82.setBackground(new java.awt.Color(153, 153, 153));

            jLabel73.setBackground(new java.awt.Color(153, 153, 153));
            jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel73.setForeground(new java.awt.Color(255, 255, 255));
            jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel73.setText("5º");

            FCCDM5.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM5.setDateFormatString("dd/MM/yyyy");
            FCCDM5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel82Layout = new javax.swing.GroupLayout(jPanel82);
            jPanel82.setLayout(jPanel82Layout);
            jPanel82Layout.setHorizontalGroup(
                jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCDM5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel82Layout.setVerticalGroup(
                jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel82Layout.createSequentialGroup()
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDM5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel83.setBackground(new java.awt.Color(153, 153, 153));

            jLabel74.setBackground(new java.awt.Color(153, 153, 153));
            jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel74.setForeground(new java.awt.Color(255, 255, 255));
            jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel74.setText("6º");

            FCCDM6.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM6.setDateFormatString("dd/MM/yyyy");
            FCCDM6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel83Layout = new javax.swing.GroupLayout(jPanel83);
            jPanel83.setLayout(jPanel83Layout);
            jPanel83Layout.setHorizontalGroup(
                jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel74, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCDM6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel83Layout.setVerticalGroup(
                jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel83Layout.createSequentialGroup()
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDM6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel84.setBackground(new java.awt.Color(153, 153, 153));

            jLabel75.setBackground(new java.awt.Color(153, 153, 153));
            jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel75.setForeground(new java.awt.Color(255, 255, 255));
            jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel75.setText("7º");

            FCCDM7.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM7.setDateFormatString("dd/MM/yyyy");
            FCCDM7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel84Layout = new javax.swing.GroupLayout(jPanel84);
            jPanel84.setLayout(jPanel84Layout);
            jPanel84Layout.setHorizontalGroup(
                jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCDM7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel84Layout.setVerticalGroup(
                jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel84Layout.createSequentialGroup()
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDM7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            FUACCDM7.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    FUACCDM7CaretUpdate(evt);
                }
            });

            DXCCDM5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM5.setText("                                ");
            DXCCDM5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCDM6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM6.setText("                                ");
            DXCCDM6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCDM7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM7.setText("                                ");
            DXCCDM7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            jPanel85.setBackground(new java.awt.Color(153, 153, 153));

            jLabel76.setBackground(new java.awt.Color(153, 153, 153));
            jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel76.setForeground(new java.awt.Color(255, 255, 255));
            jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel76.setText("8º");

            FCCDM8.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM8.setDateFormatString("dd/MM/yyyy");
            FCCDM8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel85Layout = new javax.swing.GroupLayout(jPanel85);
            jPanel85.setLayout(jPanel85Layout);
            jPanel85Layout.setHorizontalGroup(
                jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel76, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCDM8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel85Layout.setVerticalGroup(
                jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel85Layout.createSequentialGroup()
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDM8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCDM8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM8.setText("                                ");
            DXCCDM8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            jPanel86.setBackground(new java.awt.Color(153, 153, 153));

            jLabel77.setBackground(new java.awt.Color(153, 153, 153));
            jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel77.setForeground(new java.awt.Color(255, 255, 255));
            jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel77.setText("9º");

            FCCDM9.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM9.setDateFormatString("dd/MM/yyyy");
            FCCDM9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel86Layout = new javax.swing.GroupLayout(jPanel86);
            jPanel86.setLayout(jPanel86Layout);
            jPanel86Layout.setHorizontalGroup(
                jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel77, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCDM9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel86Layout.setVerticalGroup(
                jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel86Layout.createSequentialGroup()
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDM9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCDM9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM9.setText("                                ");
            DXCCDM9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            jPanel87.setBackground(new java.awt.Color(153, 153, 153));

            jLabel78.setBackground(new java.awt.Color(153, 153, 153));
            jLabel78.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel78.setForeground(new java.awt.Color(255, 255, 255));
            jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel78.setText("10º");

            FCCDM10.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM10.setDateFormatString("dd/MM/yyyy");
            FCCDM10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel87Layout = new javax.swing.GroupLayout(jPanel87);
            jPanel87.setLayout(jPanel87Layout);
            jPanel87Layout.setHorizontalGroup(
                jPanel87Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCDM10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel87Layout.setVerticalGroup(
                jPanel87Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel87Layout.createSequentialGroup()
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDM10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCDM10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM10.setText("                                ");
            DXCCDM10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            jPanel88.setBackground(new java.awt.Color(153, 153, 153));

            jLabel79.setBackground(new java.awt.Color(153, 153, 153));
            jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel79.setForeground(new java.awt.Color(255, 255, 255));
            jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel79.setText("11º");

            FCCDM11.setBackground(new java.awt.Color(255, 255, 255));
            FCCDM11.setDateFormatString("dd/MM/yyyy");
            FCCDM11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel88Layout = new javax.swing.GroupLayout(jPanel88);
            jPanel88.setLayout(jPanel88Layout);
            jPanel88Layout.setHorizontalGroup(
                jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel79, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCDM11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel88Layout.setVerticalGroup(
                jPanel88Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel88Layout.createSequentialGroup()
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCDM11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCDM11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCDM11.setText("                                ");
            DXCCDM11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDM11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDM12Layout = new javax.swing.GroupLayout(CCDM12);
            CCDM12.setLayout(CCDM12Layout);
            CCDM12Layout.setHorizontalGroup(
                CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDM12Layout.createSequentialGroup()
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel82, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDM5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXCCDM5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCDM6)
                        .addComponent(DXCCDM6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCDM7)
                        .addComponent(jPanel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCDM7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCDM8)
                        .addComponent(jPanel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCDM8))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCDM9)
                        .addComponent(jPanel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCDM9))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCDM10)
                        .addComponent(jPanel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCDM10))
                    .addGap(1, 1, 1)
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCDM11)
                        .addComponent(jPanel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCDM11))
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            CCDM12Layout.setVerticalGroup(
                CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDM12Layout.createSequentialGroup()
                    .addComponent(jPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDM12Layout.createSequentialGroup()
                            .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jPanel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(DXCCDM5)
                                .addComponent(DXCCDM6)
                                .addComponent(DXCCDM7))
                            .addGap(0, 0, 0)
                            .addGroup(CCDM12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(FUACCDM7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUACCDM5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUACCDM6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(CCDM12Layout.createSequentialGroup()
                            .addComponent(jPanel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXCCDM9)
                            .addGap(0, 0, 0)
                            .addComponent(FUACCDM9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCDM12Layout.createSequentialGroup()
                            .addComponent(jPanel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXCCDM8)
                            .addGap(0, 0, 0)
                            .addComponent(FUACCDM8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCDM12Layout.createSequentialGroup()
                            .addComponent(jPanel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXCCDM10)
                            .addGap(0, 0, 0)
                            .addComponent(FUACCDM10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCDM12Layout.createSequentialGroup()
                            .addComponent(jPanel88, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXCCDM11)
                            .addGap(0, 0, 0)
                            .addComponent(FUACCDM11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            );

            jPanel169.setBackground(new java.awt.Color(153, 153, 153));
            jPanel169.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jPanel169MouseClicked(evt);
                }
            });

            jLabel107.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel107.setForeground(new java.awt.Color(255, 255, 255));
            jLabel107.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Derecha Filled-50.png"))); // NOI18N
            jLabel107.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel107MouseClicked(evt);
                }
            });

            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            jLabel3.setText("1, 2, 3 y 4 AÑOS");

            javax.swing.GroupLayout jPanel169Layout = new javax.swing.GroupLayout(jPanel169);
            jPanel169.setLayout(jPanel169Layout);
            jPanel169Layout.setHorizontalGroup(
                jPanel169Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel169Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel107))
            );
            jPanel169Layout.setVerticalGroup(
                jPanel169Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel169Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel169Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel169Layout.createSequentialGroup()
                            .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(21, 21, 21))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel169Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(45, 45, 45))))
            );

            CIE10_ID.setText("jLabel1");

            HC_ID.setText("jLabel1");

            TxtFecha.setText("jLabel1");

            TxtFua.setText("jLabel4");

            TxtDes.setText("jLabel1");

            lblnina.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            lblnina.setForeground(new java.awt.Color(102, 102, 102));
            lblnina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niña-48.png"))); // NOI18N
            lblnina.setText("NIÑOS");
            lblnina.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            lblnina.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            lblnina.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lblninaMouseClicked(evt);
                }
            });

            lblnino.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            lblnino.setForeground(new java.awt.Color(102, 102, 102));
            lblnino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niño-48.png"))); // NOI18N
            lblnino.setText("NIÑOS");
            lblnino.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            lblnino.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            lblnino.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lblninoMouseClicked(evt);
                }
            });

            javax.swing.GroupLayout CCDLayout = new javax.swing.GroupLayout(CCD);
            CCD.setLayout(CCDLayout);
            CCDLayout.setHorizontalGroup(
                CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, 1277, Short.MAX_VALUE)
                .addGroup(CCDLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(CCDLayout.createSequentialGroup()
                                .addComponent(CCDM12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jPanel169, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(CCDLayout.createSequentialGroup()
                                .addComponent(LEYENDA, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(CCDRN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(CCDM11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(CCDLayout.createSequentialGroup()
                            .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CIE10_ID)
                                .addComponent(HC_ID))
                            .addGap(141, 141, 141)
                            .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TxtFecha)
                                .addComponent(TxtFua)
                                .addComponent(TxtDes)))
                        .addGroup(CCDLayout.createSequentialGroup()
                            .addComponent(lblnina, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblnino, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(180, Short.MAX_VALUE))
            );
            CCDLayout.setVerticalGroup(
                CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDLayout.createSequentialGroup()
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblnina)
                        .addComponent(lblnino))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(CCDRN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LEYENDA, javax.swing.GroupLayout.PREFERRED_SIZE, 116, Short.MAX_VALUE)
                        .addComponent(CCDM11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(27, 27, 27)
                    .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(CCDM12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel169, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(34, 34, 34)
                    .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CIE10_ID)
                        .addComponent(TxtFecha))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(CCDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(HC_ID)
                        .addComponent(TxtDes))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(TxtFua)
                    .addContainerGap(35, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("CCD", CCD);

            CCD1.setBackground(new java.awt.Color(255, 255, 255));

            CCD1A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel89.setBackground(new java.awt.Color(241, 197, 14));

            jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel82.setForeground(new java.awt.Color(255, 255, 255));
            jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel82.setText("1 AÑO");

            javax.swing.GroupLayout jPanel89Layout = new javax.swing.GroupLayout(jPanel89);
            jPanel89.setLayout(jPanel89Layout);
            jPanel89Layout.setHorizontalGroup(
                jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel89Layout.createSequentialGroup()
                    .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel89Layout.setVerticalGroup(
                jPanel89Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel82)
            );

            jPanel90.setBackground(new java.awt.Color(153, 153, 153));

            jLabel83.setBackground(new java.awt.Color(153, 153, 153));
            jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel83.setForeground(new java.awt.Color(255, 255, 255));
            jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel83.setText("1º");

            FCCD11.setBackground(new java.awt.Color(204, 204, 204));
            FCCD11.setDateFormatString("dd/MM/yyyy");
            FCCD11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel90Layout = new javax.swing.GroupLayout(jPanel90);
            jPanel90.setLayout(jPanel90Layout);
            jPanel90Layout.setHorizontalGroup(
                jPanel90Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel83, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel90Layout.setVerticalGroup(
                jPanel90Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel90Layout.createSequentialGroup()
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel91.setBackground(new java.awt.Color(153, 153, 153));

            jLabel84.setBackground(new java.awt.Color(153, 153, 153));
            jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel84.setForeground(new java.awt.Color(255, 255, 255));
            jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel84.setText("2º");

            FCCD12.setBackground(new java.awt.Color(204, 204, 204));
            FCCD12.setDateFormatString("dd/MM/yyyy");
            FCCD12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel91Layout = new javax.swing.GroupLayout(jPanel91);
            jPanel91.setLayout(jPanel91Layout);
            jPanel91Layout.setHorizontalGroup(
                jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel84, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel91Layout.setVerticalGroup(
                jPanel91Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel91Layout.createSequentialGroup()
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel92.setBackground(new java.awt.Color(153, 153, 153));

            jLabel85.setBackground(new java.awt.Color(153, 153, 153));
            jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel85.setForeground(new java.awt.Color(255, 255, 255));
            jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel85.setText("3º");

            FCCD13.setBackground(new java.awt.Color(204, 204, 204));
            FCCD13.setDateFormatString("dd/MM/yyyy");
            FCCD13.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel92Layout = new javax.swing.GroupLayout(jPanel92);
            jPanel92.setLayout(jPanel92Layout);
            jPanel92Layout.setHorizontalGroup(
                jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel85, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel92Layout.setVerticalGroup(
                jPanel92Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel92Layout.createSequentialGroup()
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD11.setText("                                ");
            DXCCD11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD12.setText("                                ");
            DXCCD12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD13.setText("                                ");
            DXCCD13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD13.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            jPanel93.setBackground(new java.awt.Color(153, 153, 153));

            jLabel86.setBackground(new java.awt.Color(153, 153, 153));
            jLabel86.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel86.setForeground(new java.awt.Color(255, 255, 255));
            jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel86.setText("4º");

            FCCD14.setBackground(new java.awt.Color(204, 204, 204));
            FCCD14.setDateFormatString("dd/MM/yyyy");
            FCCD14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel93Layout = new javax.swing.GroupLayout(jPanel93);
            jPanel93.setLayout(jPanel93Layout);
            jPanel93Layout.setHorizontalGroup(
                jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel86, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel93Layout.setVerticalGroup(
                jPanel93Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel93Layout.createSequentialGroup()
                    .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD14.setText("                                ");
            DXCCD14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            jPanel94.setBackground(new java.awt.Color(153, 153, 153));

            jLabel87.setBackground(new java.awt.Color(153, 153, 153));
            jLabel87.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel87.setForeground(new java.awt.Color(255, 255, 255));
            jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel87.setText("5º");

            FCCD15.setBackground(new java.awt.Color(204, 204, 204));
            FCCD15.setDateFormatString("dd/MM/yyyy");
            FCCD15.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel94Layout = new javax.swing.GroupLayout(jPanel94);
            jPanel94.setLayout(jPanel94Layout);
            jPanel94Layout.setHorizontalGroup(
                jPanel94Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel87, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel94Layout.setVerticalGroup(
                jPanel94Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel94Layout.createSequentialGroup()
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD15.setText("                                ");
            DXCCD15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            jPanel95.setBackground(new java.awt.Color(153, 153, 153));

            jLabel88.setBackground(new java.awt.Color(153, 153, 153));
            jLabel88.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel88.setForeground(new java.awt.Color(255, 255, 255));
            jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel88.setText("6º");

            FCCD16.setBackground(new java.awt.Color(204, 204, 204));
            FCCD16.setDateFormatString("dd/MM/yyyy");
            FCCD16.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel95Layout = new javax.swing.GroupLayout(jPanel95);
            jPanel95.setLayout(jPanel95Layout);
            jPanel95Layout.setHorizontalGroup(
                jPanel95Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel88, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel95Layout.setVerticalGroup(
                jPanel95Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel95Layout.createSequentialGroup()
                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD16.setText("                                ");
            DXCCD16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD16.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCD1ALayout = new javax.swing.GroupLayout(CCD1A);
            CCD1A.setLayout(CCD1ALayout);
            CCD1ALayout.setHorizontalGroup(
                CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCD1ALayout.createSequentialGroup()
                    .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel90, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD11, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXCCD11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD12)
                        .addComponent(DXCCD12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCD13)
                        .addComponent(jPanel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCD13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCD14)
                        .addComponent(jPanel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCD14))
                    .addGap(1, 1, 1)
                    .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCD15)
                        .addComponent(jPanel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCD15))
                    .addGap(1, 1, 1)
                    .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCD16)
                        .addComponent(jPanel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCD16)))
            );
            CCD1ALayout.setVerticalGroup(
                CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCD1ALayout.createSequentialGroup()
                    .addComponent(jPanel89, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCD1ALayout.createSequentialGroup()
                            .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jPanel90, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(DXCCD11)
                                .addComponent(DXCCD12)
                                .addComponent(DXCCD13))
                            .addGap(0, 0, 0)
                            .addGroup(CCD1ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(FUACCD13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUACCD11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FUACCD12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(CCD1ALayout.createSequentialGroup()
                            .addComponent(jPanel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXCCD15)
                            .addGap(0, 0, 0)
                            .addComponent(FUACCD15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCD1ALayout.createSequentialGroup()
                            .addComponent(jPanel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXCCD14)
                            .addGap(0, 0, 0)
                            .addComponent(FUACCD14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCD1ALayout.createSequentialGroup()
                            .addComponent(jPanel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(DXCCD16)
                            .addGap(0, 0, 0)
                            .addComponent(FUACCD16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            );

            CCDR2A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel96.setBackground(new java.awt.Color(243, 156, 17));

            jLabel89.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel89.setForeground(new java.awt.Color(255, 255, 255));
            jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel89.setText("2 AÑOS");

            javax.swing.GroupLayout jPanel96Layout = new javax.swing.GroupLayout(jPanel96);
            jPanel96.setLayout(jPanel96Layout);
            jPanel96Layout.setHorizontalGroup(
                jPanel96Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel96Layout.createSequentialGroup()
                    .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel96Layout.setVerticalGroup(
                jPanel96Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel89)
            );

            jPanel97.setBackground(new java.awt.Color(153, 153, 153));

            jLabel90.setBackground(new java.awt.Color(153, 153, 153));
            jLabel90.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel90.setForeground(new java.awt.Color(255, 255, 255));
            jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel90.setText("1º");

            FCCD21.setBackground(new java.awt.Color(204, 204, 204));
            FCCD21.setDateFormatString("dd/MM/yyyy");
            FCCD21.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel97Layout = new javax.swing.GroupLayout(jPanel97);
            jPanel97.setLayout(jPanel97Layout);
            jPanel97Layout.setHorizontalGroup(
                jPanel97Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel90, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel97Layout.setVerticalGroup(
                jPanel97Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel97Layout.createSequentialGroup()
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel98.setBackground(new java.awt.Color(153, 153, 153));

            jLabel91.setBackground(new java.awt.Color(153, 153, 153));
            jLabel91.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel91.setForeground(new java.awt.Color(255, 255, 255));
            jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel91.setText("2º");

            FCCD22.setBackground(new java.awt.Color(204, 204, 204));
            FCCD22.setDateFormatString("dd/MM/yyyy");
            FCCD22.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel98Layout = new javax.swing.GroupLayout(jPanel98);
            jPanel98.setLayout(jPanel98Layout);
            jPanel98Layout.setHorizontalGroup(
                jPanel98Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel91, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel98Layout.setVerticalGroup(
                jPanel98Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel98Layout.createSequentialGroup()
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel99.setBackground(new java.awt.Color(153, 153, 153));

            jLabel92.setBackground(new java.awt.Color(153, 153, 153));
            jLabel92.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel92.setForeground(new java.awt.Color(255, 255, 255));
            jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel92.setText("3º");

            FCCD23.setBackground(new java.awt.Color(204, 204, 204));
            FCCD23.setDateFormatString("dd/MM/yyyy");
            FCCD23.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel99Layout = new javax.swing.GroupLayout(jPanel99);
            jPanel99.setLayout(jPanel99Layout);
            jPanel99Layout.setHorizontalGroup(
                jPanel99Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel92, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel99Layout.setVerticalGroup(
                jPanel99Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel99Layout.createSequentialGroup()
                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel100.setBackground(new java.awt.Color(153, 153, 153));

            jLabel93.setBackground(new java.awt.Color(153, 153, 153));
            jLabel93.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel93.setForeground(new java.awt.Color(255, 255, 255));
            jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel93.setText("4º");

            FCCD24.setBackground(new java.awt.Color(204, 204, 204));
            FCCD24.setDateFormatString("dd/MM/yyyy");
            FCCD24.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel100Layout = new javax.swing.GroupLayout(jPanel100);
            jPanel100.setLayout(jPanel100Layout);
            jPanel100Layout.setHorizontalGroup(
                jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel93, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel100Layout.setVerticalGroup(
                jPanel100Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel100Layout.createSequentialGroup()
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD21.setText("                                ");
            DXCCD21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD21.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD22.setText("                                ");
            DXCCD22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD22.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD23.setText("                                ");
            DXCCD23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD23.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD24.setText("                                ");
            DXCCD24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD24.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR2ALayout = new javax.swing.GroupLayout(CCDR2A);
            CCDR2A.setLayout(CCDR2ALayout);
            CCDR2ALayout.setHorizontalGroup(
                CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel96, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR2ALayout.createSequentialGroup()
                    .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel97, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD21, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXCCD21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD22)
                        .addComponent(DXCCD22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCD23)
                        .addComponent(jPanel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCD23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD24)
                        .addComponent(DXCCD24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            CCDR2ALayout.setVerticalGroup(
                CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR2ALayout.createSequentialGroup()
                    .addComponent(jPanel96, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel97, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXCCD21)
                        .addComponent(DXCCD22)
                        .addComponent(DXCCD23)
                        .addComponent(DXCCD24))
                    .addGap(0, 0, 0)
                    .addGroup(CCDR2ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FUACCD23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            CCDR3A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel101.setBackground(new java.awt.Color(154, 89, 181));

            jLabel94.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel94.setForeground(new java.awt.Color(255, 255, 255));
            jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel94.setText("3 AÑOS");

            javax.swing.GroupLayout jPanel101Layout = new javax.swing.GroupLayout(jPanel101);
            jPanel101.setLayout(jPanel101Layout);
            jPanel101Layout.setHorizontalGroup(
                jPanel101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel101Layout.createSequentialGroup()
                    .addComponent(jLabel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel101Layout.setVerticalGroup(
                jPanel101Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel94)
            );

            jPanel102.setBackground(new java.awt.Color(153, 153, 153));

            jLabel95.setBackground(new java.awt.Color(153, 153, 153));
            jLabel95.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel95.setForeground(new java.awt.Color(255, 255, 255));
            jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel95.setText("1º");

            FCCD3A1.setBackground(new java.awt.Color(204, 204, 204));
            FCCD3A1.setDateFormatString("dd/MM/yyyy");
            FCCD3A1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel102Layout = new javax.swing.GroupLayout(jPanel102);
            jPanel102.setLayout(jPanel102Layout);
            jPanel102Layout.setHorizontalGroup(
                jPanel102Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel95, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD3A1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel102Layout.setVerticalGroup(
                jPanel102Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel102Layout.createSequentialGroup()
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD3A1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel103.setBackground(new java.awt.Color(153, 153, 153));

            jLabel96.setBackground(new java.awt.Color(153, 153, 153));
            jLabel96.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel96.setForeground(new java.awt.Color(255, 255, 255));
            jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel96.setText("2º");

            FCCD3A2.setBackground(new java.awt.Color(204, 204, 204));
            FCCD3A2.setDateFormatString("dd/MM/yyyy");
            FCCD3A2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel103Layout = new javax.swing.GroupLayout(jPanel103);
            jPanel103.setLayout(jPanel103Layout);
            jPanel103Layout.setHorizontalGroup(
                jPanel103Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel96, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD3A2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel103Layout.setVerticalGroup(
                jPanel103Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel103Layout.createSequentialGroup()
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD3A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel104.setBackground(new java.awt.Color(153, 153, 153));

            jLabel97.setBackground(new java.awt.Color(153, 153, 153));
            jLabel97.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel97.setForeground(new java.awt.Color(255, 255, 255));
            jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel97.setText("3º");

            FCCD3A3.setBackground(new java.awt.Color(204, 204, 204));
            FCCD3A3.setDateFormatString("dd/MM/yyyy");
            FCCD3A3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel104Layout = new javax.swing.GroupLayout(jPanel104);
            jPanel104.setLayout(jPanel104Layout);
            jPanel104Layout.setHorizontalGroup(
                jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel97, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD3A3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel104Layout.setVerticalGroup(
                jPanel104Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel104Layout.createSequentialGroup()
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD3A3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel105.setBackground(new java.awt.Color(153, 153, 153));

            jLabel98.setBackground(new java.awt.Color(153, 153, 153));
            jLabel98.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel98.setForeground(new java.awt.Color(255, 255, 255));
            jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel98.setText("4º");

            FCCD3A4.setBackground(new java.awt.Color(204, 204, 204));
            FCCD3A4.setDateFormatString("dd/MM/yyyy");
            FCCD3A4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel105Layout = new javax.swing.GroupLayout(jPanel105);
            jPanel105.setLayout(jPanel105Layout);
            jPanel105Layout.setHorizontalGroup(
                jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel98, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD3A4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel105Layout.setVerticalGroup(
                jPanel105Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel105Layout.createSequentialGroup()
                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD3A4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD3A1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD3A1.setText("                                ");
            DXCCD3A1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD3A1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD3A2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD3A2.setText("                                ");
            DXCCD3A2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD3A2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD3A3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD3A3.setText("                                ");
            DXCCD3A3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD3A3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD3A4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD3A4.setText("                                ");
            DXCCD3A4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD3A4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR3ALayout = new javax.swing.GroupLayout(CCDR3A);
            CCDR3A.setLayout(CCDR3ALayout);
            CCDR3ALayout.setHorizontalGroup(
                CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel101, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR3ALayout.createSequentialGroup()
                    .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel102, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD3A1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXCCD3A1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel103, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD3A2)
                        .addComponent(DXCCD3A2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCD3A3)
                        .addComponent(jPanel104, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCD3A3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD3A4)
                        .addComponent(DXCCD3A4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            CCDR3ALayout.setVerticalGroup(
                CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR3ALayout.createSequentialGroup()
                    .addComponent(jPanel101, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel104, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel103, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel102, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXCCD3A1)
                        .addComponent(DXCCD3A2)
                        .addComponent(DXCCD3A3)
                        .addComponent(DXCCD3A4))
                    .addGap(0, 0, 0)
                    .addGroup(CCDR3ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FUACCD3A3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD3A1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD3A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD3A4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            jPanel106.setBackground(new java.awt.Color(45, 204, 112));
            jPanel106.setPreferredSize(new java.awt.Dimension(83, 45));

            jLabel99.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel99.setForeground(new java.awt.Color(255, 255, 255));
            jLabel99.setText("CONTROL DE CRECIMIENTO Y DESARROLLO");

            jPanel107.setBackground(new java.awt.Color(39, 174, 97));

            jLabel100.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel100.setForeground(new java.awt.Color(255, 255, 255));
            jLabel100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
            jLabel100.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel100MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel107Layout = new javax.swing.GroupLayout(jPanel107);
            jPanel107.setLayout(jPanel107Layout);
            jPanel107Layout.setHorizontalGroup(
                jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel107Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel100)
                    .addContainerGap())
            );
            jPanel107Layout.setVerticalGroup(
                jPanel107Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel107Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jLabel100)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel106Layout = new javax.swing.GroupLayout(jPanel106);
            jPanel106.setLayout(jPanel106Layout);
            jPanel106Layout.setHorizontalGroup(
                jPanel106Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel106Layout.createSequentialGroup()
                    .addComponent(jPanel107, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel99)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel106Layout.setVerticalGroup(
                jPanel106Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel106Layout.createSequentialGroup()
                    .addGroup(jPanel106Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel107, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            CCDR3A1.setBackground(new java.awt.Color(204, 204, 204));

            jPanel108.setBackground(new java.awt.Color(232, 76, 61));

            jLabel101.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel101.setForeground(new java.awt.Color(255, 255, 255));
            jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel101.setText("4 AÑOS");

            javax.swing.GroupLayout jPanel108Layout = new javax.swing.GroupLayout(jPanel108);
            jPanel108.setLayout(jPanel108Layout);
            jPanel108Layout.setHorizontalGroup(
                jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel108Layout.createSequentialGroup()
                    .addComponent(jLabel101, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel108Layout.setVerticalGroup(
                jPanel108Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel101)
            );

            jPanel109.setBackground(new java.awt.Color(153, 153, 153));

            jLabel102.setBackground(new java.awt.Color(153, 153, 153));
            jLabel102.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel102.setForeground(new java.awt.Color(255, 255, 255));
            jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel102.setText("1º");

            FCCD4A1.setBackground(new java.awt.Color(204, 204, 204));
            FCCD4A1.setDateFormatString("dd/MM/yyyy");
            FCCD4A1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel109Layout = new javax.swing.GroupLayout(jPanel109);
            jPanel109.setLayout(jPanel109Layout);
            jPanel109Layout.setHorizontalGroup(
                jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel102, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD4A1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel109Layout.setVerticalGroup(
                jPanel109Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel109Layout.createSequentialGroup()
                    .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD4A1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel110.setBackground(new java.awt.Color(153, 153, 153));

            jLabel103.setBackground(new java.awt.Color(153, 153, 153));
            jLabel103.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel103.setForeground(new java.awt.Color(255, 255, 255));
            jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel103.setText("2º");

            FCCD4A2.setBackground(new java.awt.Color(204, 204, 204));
            FCCD4A2.setDateFormatString("dd/MM/yyyy");
            FCCD4A2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel110Layout = new javax.swing.GroupLayout(jPanel110);
            jPanel110.setLayout(jPanel110Layout);
            jPanel110Layout.setHorizontalGroup(
                jPanel110Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel103, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD4A2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel110Layout.setVerticalGroup(
                jPanel110Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel110Layout.createSequentialGroup()
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD4A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel111.setBackground(new java.awt.Color(153, 153, 153));

            jLabel104.setBackground(new java.awt.Color(153, 153, 153));
            jLabel104.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel104.setForeground(new java.awt.Color(255, 255, 255));
            jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel104.setText("3º");

            FCCD4A3.setBackground(new java.awt.Color(204, 204, 204));
            FCCD4A3.setDateFormatString("dd/MM/yyyy");
            FCCD4A3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel111Layout = new javax.swing.GroupLayout(jPanel111);
            jPanel111.setLayout(jPanel111Layout);
            jPanel111Layout.setHorizontalGroup(
                jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel104, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD4A3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel111Layout.setVerticalGroup(
                jPanel111Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel111Layout.createSequentialGroup()
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD4A3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel112.setBackground(new java.awt.Color(153, 153, 153));

            jLabel105.setBackground(new java.awt.Color(153, 153, 153));
            jLabel105.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel105.setForeground(new java.awt.Color(255, 255, 255));
            jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel105.setText("4º");

            FCCD4A4.setBackground(new java.awt.Color(204, 204, 204));
            FCCD4A4.setDateFormatString("dd/MM/yyyy");
            FCCD4A4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel112Layout = new javax.swing.GroupLayout(jPanel112);
            jPanel112.setLayout(jPanel112Layout);
            jPanel112Layout.setHorizontalGroup(
                jPanel112Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel105, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD4A4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel112Layout.setVerticalGroup(
                jPanel112Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel112Layout.createSequentialGroup()
                    .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD4A4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD4A1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD4A1.setText("                                ");
            DXCCD4A1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD4A1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD4A2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD4A2.setText("                                ");
            DXCCD4A2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD4A2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD4A3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD4A3.setText("                                ");
            DXCCD4A3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD4A3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            DXCCD4A4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD4A4.setText("                                ");
            DXCCD4A4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD4A4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR3A1Layout = new javax.swing.GroupLayout(CCDR3A1);
            CCDR3A1.setLayout(CCDR3A1Layout);
            CCDR3A1Layout.setHorizontalGroup(
                CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR3A1Layout.createSequentialGroup()
                    .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel109, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD4A1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(DXCCD4A1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel110, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD4A2)
                        .addComponent(DXCCD4A2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(FUACCD4A3)
                        .addComponent(jPanel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DXCCD4A3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel112, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FUACCD4A4)
                        .addComponent(DXCCD4A4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
            CCDR3A1Layout.setVerticalGroup(
                CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR3A1Layout.createSequentialGroup()
                    .addComponent(jPanel108, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel110, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel112, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel109, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXCCD4A1)
                        .addComponent(DXCCD4A2)
                        .addComponent(DXCCD4A3)
                        .addComponent(DXCCD4A4))
                    .addGap(0, 0, 0)
                    .addGroup(CCDR3A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FUACCD4A3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD4A1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD4A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FUACCD4A4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            jPanel170.setBackground(new java.awt.Color(39, 174, 97));
            jPanel170.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jPanel170MouseClicked(evt);
                }
            });

            jLabel108.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel108.setForeground(new java.awt.Color(255, 255, 255));
            jLabel108.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Derecha Filled-50.png"))); // NOI18N
            jLabel108.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel108MouseClicked(evt);
                }
            });

            jLabel109.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel109.setForeground(new java.awt.Color(255, 255, 255));
            jLabel109.setText("5 - 11 AÑOS");

            javax.swing.GroupLayout jPanel170Layout = new javax.swing.GroupLayout(jPanel170);
            jPanel170.setLayout(jPanel170Layout);
            jPanel170Layout.setHorizontalGroup(
                jPanel170Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel170Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel109)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel108))
            );
            jPanel170Layout.setVerticalGroup(
                jPanel170Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel170Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel170Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel170Layout.createSequentialGroup()
                            .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(21, 21, 21))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel170Layout.createSequentialGroup()
                            .addComponent(jLabel109)
                            .addGap(45, 45, 45))))
            );

            DXCCDRN7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niña-48.png"))); // NOI18N
            DXCCDRN7.setText("NIÑOS");
            DXCCDRN7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            DXCCDRN7.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXCCDRN7MouseClicked(evt);
                }
            });

            DXCCDRN8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niño-48.png"))); // NOI18N
            DXCCDRN8.setText("NIÑOS");
            DXCCDRN8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            DXCCDRN8.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXCCDRN8MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout CCD1Layout = new javax.swing.GroupLayout(CCD1);
            CCD1.setLayout(CCD1Layout);
            CCD1Layout.setHorizontalGroup(
                CCD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel106, javax.swing.GroupLayout.DEFAULT_SIZE, 1277, Short.MAX_VALUE)
                .addGroup(CCD1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(CCD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(CCD1Layout.createSequentialGroup()
                                .addComponent(CCD1A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(CCDR2A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(CCD1Layout.createSequentialGroup()
                                .addComponent(CCDR3A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(CCDR3A1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel170, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(CCD1Layout.createSequentialGroup()
                            .addComponent(DXCCDRN7, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(DXCCDRN8, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(58, Short.MAX_VALUE))
            );
            CCD1Layout.setVerticalGroup(
                CCD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCD1Layout.createSequentialGroup()
                    .addComponent(jPanel106, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(CCD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXCCDRN7)
                        .addComponent(DXCCDRN8))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(CCD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(CCD1A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CCDR2A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(18, 37, Short.MAX_VALUE)
                    .addGroup(CCD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(CCDR3A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CCDR3A1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel170, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(120, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("CCD", CCD1);

            CCD2.setBackground(new java.awt.Color(255, 255, 255));

            jPanel113.setBackground(new java.awt.Color(45, 204, 112));
            jPanel113.setPreferredSize(new java.awt.Dimension(83, 45));

            jLabel106.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel106.setForeground(new java.awt.Color(255, 255, 255));
            jLabel106.setText("CONTROL DE CRECIMIENTO Y DESARROLLO");

            jPanel114.setBackground(new java.awt.Color(39, 174, 97));

            jLabel110.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel110.setForeground(new java.awt.Color(255, 255, 255));
            jLabel110.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
            jLabel110.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel110MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel114Layout = new javax.swing.GroupLayout(jPanel114);
            jPanel114.setLayout(jPanel114Layout);
            jPanel114Layout.setHorizontalGroup(
                jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel114Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel110)
                    .addContainerGap())
            );
            jPanel114Layout.setVerticalGroup(
                jPanel114Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel114Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jLabel110)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel113Layout = new javax.swing.GroupLayout(jPanel113);
            jPanel113.setLayout(jPanel113Layout);
            jPanel113Layout.setHorizontalGroup(
                jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel113Layout.createSequentialGroup()
                    .addComponent(jPanel114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel106)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel113Layout.setVerticalGroup(
                jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel113Layout.createSequentialGroup()
                    .addGroup(jPanel113Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel114, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            CCDR5A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel115.setBackground(new java.awt.Color(154, 89, 181));

            jLabel111.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel111.setForeground(new java.awt.Color(255, 255, 255));
            jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel111.setText("5 AÑOS");

            javax.swing.GroupLayout jPanel115Layout = new javax.swing.GroupLayout(jPanel115);
            jPanel115.setLayout(jPanel115Layout);
            jPanel115Layout.setHorizontalGroup(
                jPanel115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel115Layout.createSequentialGroup()
                    .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel115Layout.setVerticalGroup(
                jPanel115Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel111)
            );

            jPanel116.setBackground(new java.awt.Color(153, 153, 153));

            jLabel112.setBackground(new java.awt.Color(153, 153, 153));
            jLabel112.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel112.setForeground(new java.awt.Color(255, 255, 255));
            jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel112.setText("1º");

            FCCD5A.setBackground(new java.awt.Color(204, 204, 204));
            FCCD5A.setDateFormatString("dd/MM/yyyy");
            FCCD5A.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel116Layout = new javax.swing.GroupLayout(jPanel116);
            jPanel116.setLayout(jPanel116Layout);
            jPanel116Layout.setHorizontalGroup(
                jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel112, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD5A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel116Layout.setVerticalGroup(
                jPanel116Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel116Layout.createSequentialGroup()
                    .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD5A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD5A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD5A.setText("                                ");
            DXCCD5A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD5A.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR5ALayout = new javax.swing.GroupLayout(CCDR5A);
            CCDR5A.setLayout(CCDR5ALayout);
            CCDR5ALayout.setHorizontalGroup(
                CCDR5ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel115, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR5ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel116, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUACCD5A, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXCCD5A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            CCDR5ALayout.setVerticalGroup(
                CCDR5ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR5ALayout.createSequentialGroup()
                    .addComponent(jPanel115, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(jPanel116, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DXCCD5A)
                    .addGap(0, 0, 0)
                    .addComponent(FUACCD5A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            CCDR6A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel117.setBackground(new java.awt.Color(154, 89, 181));

            jLabel113.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel113.setForeground(new java.awt.Color(255, 255, 255));
            jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel113.setText("6 AÑOS");

            javax.swing.GroupLayout jPanel117Layout = new javax.swing.GroupLayout(jPanel117);
            jPanel117.setLayout(jPanel117Layout);
            jPanel117Layout.setHorizontalGroup(
                jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel117Layout.createSequentialGroup()
                    .addComponent(jLabel113, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel117Layout.setVerticalGroup(
                jPanel117Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel113)
            );

            jPanel118.setBackground(new java.awt.Color(153, 153, 153));

            jLabel114.setBackground(new java.awt.Color(153, 153, 153));
            jLabel114.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel114.setForeground(new java.awt.Color(255, 255, 255));
            jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel114.setText("1º");

            FCCD6A.setBackground(new java.awt.Color(204, 204, 204));
            FCCD6A.setDateFormatString("dd/MM/yyyy");
            FCCD6A.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel118Layout = new javax.swing.GroupLayout(jPanel118);
            jPanel118.setLayout(jPanel118Layout);
            jPanel118Layout.setHorizontalGroup(
                jPanel118Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel114, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD6A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel118Layout.setVerticalGroup(
                jPanel118Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel118Layout.createSequentialGroup()
                    .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD6A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD6A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD6A.setText("                                ");
            DXCCD6A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD6A.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR6ALayout = new javax.swing.GroupLayout(CCDR6A);
            CCDR6A.setLayout(CCDR6ALayout);
            CCDR6ALayout.setHorizontalGroup(
                CCDR6ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel117, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR6ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel118, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUACCD6A, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXCCD6A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            CCDR6ALayout.setVerticalGroup(
                CCDR6ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR6ALayout.createSequentialGroup()
                    .addComponent(jPanel117, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(jPanel118, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DXCCD6A)
                    .addGap(0, 0, 0)
                    .addComponent(FUACCD6A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            CCDR7A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel119.setBackground(new java.awt.Color(154, 89, 181));

            jLabel115.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel115.setForeground(new java.awt.Color(255, 255, 255));
            jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel115.setText("7 AÑOS");

            javax.swing.GroupLayout jPanel119Layout = new javax.swing.GroupLayout(jPanel119);
            jPanel119.setLayout(jPanel119Layout);
            jPanel119Layout.setHorizontalGroup(
                jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel119Layout.createSequentialGroup()
                    .addComponent(jLabel115, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel119Layout.setVerticalGroup(
                jPanel119Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel115)
            );

            jPanel120.setBackground(new java.awt.Color(153, 153, 153));

            jLabel116.setBackground(new java.awt.Color(153, 153, 153));
            jLabel116.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel116.setForeground(new java.awt.Color(255, 255, 255));
            jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel116.setText("1º");

            FCCD7A.setBackground(new java.awt.Color(204, 204, 204));
            FCCD7A.setDateFormatString("dd/MM/yyyy");
            FCCD7A.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel120Layout = new javax.swing.GroupLayout(jPanel120);
            jPanel120.setLayout(jPanel120Layout);
            jPanel120Layout.setHorizontalGroup(
                jPanel120Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel116, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD7A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel120Layout.setVerticalGroup(
                jPanel120Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel120Layout.createSequentialGroup()
                    .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD7A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD7A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD7A.setText("                                ");
            DXCCD7A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD7A.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR7ALayout = new javax.swing.GroupLayout(CCDR7A);
            CCDR7A.setLayout(CCDR7ALayout);
            CCDR7ALayout.setHorizontalGroup(
                CCDR7ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel119, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR7ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel120, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUACCD7A, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXCCD7A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            CCDR7ALayout.setVerticalGroup(
                CCDR7ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR7ALayout.createSequentialGroup()
                    .addComponent(jPanel119, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(jPanel120, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DXCCD7A)
                    .addGap(0, 0, 0)
                    .addComponent(FUACCD7A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            CCDR8A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel121.setBackground(new java.awt.Color(154, 89, 181));

            jLabel117.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel117.setForeground(new java.awt.Color(255, 255, 255));
            jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel117.setText("8 AÑOS");

            javax.swing.GroupLayout jPanel121Layout = new javax.swing.GroupLayout(jPanel121);
            jPanel121.setLayout(jPanel121Layout);
            jPanel121Layout.setHorizontalGroup(
                jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel121Layout.createSequentialGroup()
                    .addComponent(jLabel117, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel121Layout.setVerticalGroup(
                jPanel121Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel117)
            );

            jPanel122.setBackground(new java.awt.Color(153, 153, 153));

            jLabel118.setBackground(new java.awt.Color(153, 153, 153));
            jLabel118.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel118.setForeground(new java.awt.Color(255, 255, 255));
            jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel118.setText("1º");

            FCCD8A.setBackground(new java.awt.Color(204, 204, 204));
            FCCD8A.setDateFormatString("dd/MM/yyyy");
            FCCD8A.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel122Layout = new javax.swing.GroupLayout(jPanel122);
            jPanel122.setLayout(jPanel122Layout);
            jPanel122Layout.setHorizontalGroup(
                jPanel122Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel118, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD8A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel122Layout.setVerticalGroup(
                jPanel122Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel122Layout.createSequentialGroup()
                    .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD8A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD8A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD8A.setText("                                ");
            DXCCD8A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD8A.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR8ALayout = new javax.swing.GroupLayout(CCDR8A);
            CCDR8A.setLayout(CCDR8ALayout);
            CCDR8ALayout.setHorizontalGroup(
                CCDR8ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel121, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR8ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel122, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUACCD8A, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXCCD8A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            CCDR8ALayout.setVerticalGroup(
                CCDR8ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR8ALayout.createSequentialGroup()
                    .addComponent(jPanel121, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(jPanel122, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DXCCD8A)
                    .addGap(0, 0, 0)
                    .addComponent(FUACCD8A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            CCDR9A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel123.setBackground(new java.awt.Color(154, 89, 181));

            jLabel119.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel119.setForeground(new java.awt.Color(255, 255, 255));
            jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel119.setText("9 AÑOS");

            javax.swing.GroupLayout jPanel123Layout = new javax.swing.GroupLayout(jPanel123);
            jPanel123.setLayout(jPanel123Layout);
            jPanel123Layout.setHorizontalGroup(
                jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel123Layout.createSequentialGroup()
                    .addComponent(jLabel119, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel123Layout.setVerticalGroup(
                jPanel123Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel119)
            );

            jPanel124.setBackground(new java.awt.Color(153, 153, 153));

            jLabel120.setBackground(new java.awt.Color(153, 153, 153));
            jLabel120.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel120.setForeground(new java.awt.Color(255, 255, 255));
            jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel120.setText("1º");

            FCCD9A.setBackground(new java.awt.Color(204, 204, 204));
            FCCD9A.setDateFormatString("dd/MM/yyyy");
            FCCD9A.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel124Layout = new javax.swing.GroupLayout(jPanel124);
            jPanel124.setLayout(jPanel124Layout);
            jPanel124Layout.setHorizontalGroup(
                jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel120, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD9A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel124Layout.setVerticalGroup(
                jPanel124Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel124Layout.createSequentialGroup()
                    .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD9A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD9A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD9A.setText("                                ");
            DXCCD9A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD9A.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR9ALayout = new javax.swing.GroupLayout(CCDR9A);
            CCDR9A.setLayout(CCDR9ALayout);
            CCDR9ALayout.setHorizontalGroup(
                CCDR9ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel123, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR9ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel124, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUACCD9A, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXCCD9A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            CCDR9ALayout.setVerticalGroup(
                CCDR9ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR9ALayout.createSequentialGroup()
                    .addComponent(jPanel123, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(jPanel124, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DXCCD9A)
                    .addGap(0, 0, 0)
                    .addComponent(FUACCD9A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            CCDR10A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel125.setBackground(new java.awt.Color(154, 89, 181));

            jLabel121.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel121.setForeground(new java.awt.Color(255, 255, 255));
            jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel121.setText("10 AÑOS");

            javax.swing.GroupLayout jPanel125Layout = new javax.swing.GroupLayout(jPanel125);
            jPanel125.setLayout(jPanel125Layout);
            jPanel125Layout.setHorizontalGroup(
                jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel125Layout.createSequentialGroup()
                    .addComponent(jLabel121, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel125Layout.setVerticalGroup(
                jPanel125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel121)
            );

            jPanel126.setBackground(new java.awt.Color(153, 153, 153));

            jLabel122.setBackground(new java.awt.Color(153, 153, 153));
            jLabel122.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel122.setForeground(new java.awt.Color(255, 255, 255));
            jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel122.setText("1º");

            FCCD10A.setBackground(new java.awt.Color(204, 204, 204));
            FCCD10A.setDateFormatString("dd/MM/yyyy");
            FCCD10A.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel126Layout = new javax.swing.GroupLayout(jPanel126);
            jPanel126.setLayout(jPanel126Layout);
            jPanel126Layout.setHorizontalGroup(
                jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel122, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD10A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel126Layout.setVerticalGroup(
                jPanel126Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel126Layout.createSequentialGroup()
                    .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD10A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD10A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD10A.setText("                                ");
            DXCCD10A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD10A.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR10ALayout = new javax.swing.GroupLayout(CCDR10A);
            CCDR10A.setLayout(CCDR10ALayout);
            CCDR10ALayout.setHorizontalGroup(
                CCDR10ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel125, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR10ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel126, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUACCD10A, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXCCD10A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            CCDR10ALayout.setVerticalGroup(
                CCDR10ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR10ALayout.createSequentialGroup()
                    .addComponent(jPanel125, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(jPanel126, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DXCCD10A)
                    .addGap(0, 0, 0)
                    .addComponent(FUACCD10A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            CCDR11A.setBackground(new java.awt.Color(204, 204, 204));

            jPanel127.setBackground(new java.awt.Color(154, 89, 181));

            jLabel123.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel123.setForeground(new java.awt.Color(255, 255, 255));
            jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel123.setText("11 AÑOS");

            javax.swing.GroupLayout jPanel127Layout = new javax.swing.GroupLayout(jPanel127);
            jPanel127.setLayout(jPanel127Layout);
            jPanel127Layout.setHorizontalGroup(
                jPanel127Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel127Layout.createSequentialGroup()
                    .addComponent(jLabel123, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel127Layout.setVerticalGroup(
                jPanel127Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel123)
            );

            jPanel128.setBackground(new java.awt.Color(153, 153, 153));

            jLabel124.setBackground(new java.awt.Color(153, 153, 153));
            jLabel124.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel124.setForeground(new java.awt.Color(255, 255, 255));
            jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel124.setText("1º");

            FCCD11A.setBackground(new java.awt.Color(204, 204, 204));
            FCCD11A.setDateFormatString("dd/MM/yyyy");
            FCCD11A.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel128Layout = new javax.swing.GroupLayout(jPanel128);
            jPanel128.setLayout(jPanel128Layout);
            jPanel128Layout.setHorizontalGroup(
                jPanel128Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel124, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FCCD11A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel128Layout.setVerticalGroup(
                jPanel128Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel128Layout.createSequentialGroup()
                    .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(FCCD11A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCD11A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
            DXCCD11A.setText("                                ");
            DXCCD11A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCD11A.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

            javax.swing.GroupLayout CCDR11ALayout = new javax.swing.GroupLayout(CCDR11A);
            CCDR11A.setLayout(CCDR11ALayout);
            CCDR11ALayout.setHorizontalGroup(
                CCDR11ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel127, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CCDR11ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel128, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUACCD11A, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXCCD11A, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            CCDR11ALayout.setVerticalGroup(
                CCDR11ALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCDR11ALayout.createSequentialGroup()
                    .addComponent(jPanel127, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(1, 1, 1)
                    .addComponent(jPanel128, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(DXCCD11A)
                    .addGap(0, 0, 0)
                    .addComponent(FUACCD11A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            DXCCDRN9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niña-48.png"))); // NOI18N
            DXCCDRN9.setText("NIÑOS");
            DXCCDRN9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            DXCCDRN9.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXCCDRN9MouseClicked(evt);
                }
            });

            DXCCDRN10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niño-48.png"))); // NOI18N
            DXCCDRN10.setText("NIÑOS");
            DXCCDRN10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            DXCCDRN10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            DXCCDRN10.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    DXCCDRN10MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout CCD2Layout = new javax.swing.GroupLayout(CCD2);
            CCD2.setLayout(CCD2Layout);
            CCD2Layout.setHorizontalGroup(
                CCD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel113, javax.swing.GroupLayout.DEFAULT_SIZE, 1277, Short.MAX_VALUE)
                .addGroup(CCD2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(CCD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CCD2Layout.createSequentialGroup()
                            .addComponent(CCDR5A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CCDR6A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CCDR7A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CCDR8A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CCDR9A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCD2Layout.createSequentialGroup()
                            .addComponent(CCDR10A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(CCDR11A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CCD2Layout.createSequentialGroup()
                            .addComponent(DXCCDRN9, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(DXCCDRN10, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(544, Short.MAX_VALUE))
            );
            CCD2Layout.setVerticalGroup(
                CCD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(CCD2Layout.createSequentialGroup()
                    .addComponent(jPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addGroup(CCD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DXCCDRN9)
                        .addComponent(DXCCDRN10))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(CCD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(CCDR5A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CCDR6A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CCDR7A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CCDR8A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CCDR9A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(27, 27, 27)
                    .addGroup(CCD2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(CCDR10A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CCDR11A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(136, Short.MAX_VALUE))
            );

            jTabbedPane1.addTab("CCD", CCD2);

            mensaje.setBackground(new java.awt.Color(102, 102, 102));

            jPanel31.setBackground(new java.awt.Color(51, 51, 51));

            btneditar4.setForeground(new java.awt.Color(240, 240, 240));
            btneditar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
            btneditar4.setMnemonic('N');
            btneditar4.setContentAreaFilled(false);
            btneditar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btneditar4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btneditar4.setIconTextGap(30);
            btneditar4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btneditar4ActionPerformed(evt);
                }
            });

            btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
            btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
            btnGuardar.setMnemonic('N');
            btnGuardar.setText("Guardar");
            btnGuardar.setContentAreaFilled(false);
            btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnGuardar.setIconTextGap(30);
            btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGuardarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
            jPanel31.setLayout(jPanel31Layout);
            jPanel31Layout.setHorizontalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 122, Short.MAX_VALUE)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btneditar4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE)))
            );
            jPanel31Layout.setVerticalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btneditar4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );

            men.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            men.setForeground(new java.awt.Color(255, 255, 255));
            men.setText("jLabel1");

            javax.swing.GroupLayout mensajeLayout = new javax.swing.GroupLayout(mensaje);
            mensaje.setLayout(mensajeLayout);
            mensajeLayout.setHorizontalGroup(
                mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mensajeLayout.createSequentialGroup()
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(men)
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            mensajeLayout.setVerticalGroup(
                mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mensajeLayout.createSequentialGroup()
                    .addComponent(men, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1)
                .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jTabbedPane1)
                    .addGap(0, 0, 0)
                    .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        RegistroSeguimiento.jTabbedPane1.setSelectedIndex(0);
        this.dispose();
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel107MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel107MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel107MouseClicked

    private void jPanel169MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel169MouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jPanel169MouseClicked

    private void jLabel100MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel100MouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel100MouseClicked

    private void jLabel108MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel108MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel108MouseClicked

    private void jPanel170MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel170MouseClicked
      jTabbedPane1.setSelectedIndex(2);          
    }//GEN-LAST:event_jPanel170MouseClicked

    private void jLabel110MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel110MouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel110MouseClicked

    private void btneditar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar4ActionPerformed

    }//GEN-LAST:event_btneditar4ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
            Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void FUACCDRN2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FUACCDRN2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FUACCDRN2ActionPerformed

    private void FUACCDRN1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_FUACCDRN1CaretUpdate
        if (FUACCDRN1.getText().equals("")){
        FUACCDRN2.setEnabled(true);
        FCCDRN2.setEnabled(true);
        DXCCDRN2.setEnabled(true);
        
        FUACCDRN1.setEnabled(true);
        FCCDRN1.setEnabled(true);
        DXCCDRN1.setEnabled(true);
        
        FUACCDRN3.setEnabled(true);
        FCCDRN3.setEnabled(true);
        DXCCDRN3.setEnabled(true);
        
        FUACCDRN4.setEnabled(true);
        FCCDRN4.setEnabled(true);
        DXCCDRN4.setEnabled(true);    
        } else {
        TxtFua.setText(FUACCDRN1.getText());
        TxtFecha.setText(determinarFecha(FCCDRN1));
        TxtDes.setText("RN1");
        
        FUACCDRN2.setEnabled(false);
        FCCDRN2.setEnabled(false);
        DXCCDRN2.setEnabled(false);
        
        FUACCDRN3.setEnabled(false);
        FCCDRN3.setEnabled(false);
        DXCCDRN3.setEnabled(false);
        
        FUACCDRN4.setEnabled(false);
        FCCDRN4.setEnabled(false);
        DXCCDRN4.setEnabled(false);   
       }
            
    }//GEN-LAST:event_FUACCDRN1CaretUpdate

    private void FUACCDRN2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_FUACCDRN2CaretUpdate
        if (FUACCDRN2.getText().equals("")){
        FUACCDRN1.setEnabled(true);
        FCCDRN1.setEnabled(true);
        DXCCDRN1.setEnabled(true);
        
        FUACCDRN2.setEnabled(true);
        FCCDRN2.setEnabled(true);
        DXCCDRN2.setEnabled(true);
        
        FUACCDRN3.setEnabled(true);
        FCCDRN3.setEnabled(true);
        DXCCDRN3.setEnabled(true);
        
        FUACCDRN4.setEnabled(true);
        FCCDRN4.setEnabled(true);
        DXCCDRN4.setEnabled(true);    
        } else {
        TxtFua.setText(FUACCDRN2.getText());
        TxtFecha.setText(determinarFecha(FCCDRN2));
        TxtDes.setText("RN2");
        
        FUACCDRN1.setEnabled(false);
        FCCDRN1.setEnabled(false);
        DXCCDRN1.setEnabled(false);
        
        FUACCDRN3.setEnabled(false);
        FCCDRN3.setEnabled(false);
        DXCCDRN3.setEnabled(false);
        
        FUACCDRN4.setEnabled(false);
        FCCDRN4.setEnabled(false);
        DXCCDRN4.setEnabled(false);   
       }
    }//GEN-LAST:event_FUACCDRN2CaretUpdate

    private void FUACCDRN3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_FUACCDRN3CaretUpdate
       if (FUACCDRN3.getText().equals("")){
           
        FUACCDRN2.setEnabled(true);
        FCCDRN2.setEnabled(true);
        DXCCDRN2.setEnabled(true);
        
        FUACCDRN3.setEnabled(true);
        FCCDRN3.setEnabled(true);
        DXCCDRN3.setEnabled(true);
        
        FUACCDRN1.setEnabled(true);
        FCCDRN1.setEnabled(true);
        DXCCDRN1.setEnabled(true);
        
        FUACCDRN4.setEnabled(true);
        FCCDRN4.setEnabled(true);
        DXCCDRN4.setEnabled(true);    
        } else {
        TxtFua.setText(FUACCDRN3.getText());
        TxtFecha.setText(determinarFecha(FCCDRN3));
        TxtDes.setText("RN3");
        
        FUACCDRN2.setEnabled(false);
        FCCDRN2.setEnabled(false);
        DXCCDRN2.setEnabled(false);
        
        FUACCDRN1.setEnabled(false);
        FCCDRN1.setEnabled(false);
        DXCCDRN1.setEnabled(false);
        
        FUACCDRN4.setEnabled(false);
        FCCDRN4.setEnabled(false);
        DXCCDRN4.setEnabled(false);   
       }
    }//GEN-LAST:event_FUACCDRN3CaretUpdate

    private void FUACCDRN4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_FUACCDRN4CaretUpdate
        if (FUACCDRN4.getText().equals("")){
        FUACCDRN2.setEnabled(true);
        FCCDRN2.setEnabled(true);
        DXCCDRN2.setEnabled(true);
        
        FUACCDRN4.setEnabled(true);
        FCCDRN4.setEnabled(true);
        DXCCDRN4.setEnabled(true);
        
        FUACCDRN3.setEnabled(true);
        FCCDRN3.setEnabled(true);
        DXCCDRN3.setEnabled(true);
        
        FUACCDRN1.setEnabled(true);
        FCCDRN1.setEnabled(true);
        DXCCDRN1.setEnabled(true);    
        } else {
        TxtFua.setText(FUACCDRN4.getText());
        TxtFecha.setText(determinarFecha(FCCDRN4));
        TxtDes.setText("RN4");
        
        FUACCDRN2.setEnabled(false);
        FCCDRN2.setEnabled(false);
        DXCCDRN2.setEnabled(false);
        
        FUACCDRN3.setEnabled(false);
        FCCDRN3.setEnabled(false);
        DXCCDRN3.setEnabled(false);
        
        FUACCDRN1.setEnabled(false);
        FCCDRN1.setEnabled(false);
        DXCCDRN1.setEnabled(false);   
       }
    }//GEN-LAST:event_FUACCDRN4CaretUpdate

    private void FUACCDRN1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FUACCDRN1MouseClicked
      

    }//GEN-LAST:event_FUACCDRN1MouseClicked

    private void txtBuscarCie10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCie10CaretUpdate
        ConsultorioExtRsCcd adTopico = new ConsultorioExtRsCcd();
        adTopico.cargarDatosCie10(txtBuscarCie10.getText(), tbCiePresun);
    }//GEN-LAST:event_txtBuscarCie10CaretUpdate

    private void txtBuscarCie10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCie10KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbCiePresun.getSelectionModel().setSelectionInterval(0, 0);
            tbCiePresun.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarCie10KeyPressed

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T7MouseClicked

    private void DXCCDRN1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXCCDRN1MouseClicked
        ConsultorioExtRsCcd adTopico = new ConsultorioExtRsCcd();
        adTopico.cargarDatosCie10("", tbCiePresun);
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_DXCCDRN1MouseClicked

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
            enviarDiagnosticos();
        }
    }//GEN-LAST:event_tbCiePresunKeyPressed

    private void lblninaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblninaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblninaMouseClicked

    private void lblninoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblninoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblninoMouseClicked

    private void DXCCDRN7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXCCDRN7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DXCCDRN7MouseClicked

    private void DXCCDRN8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXCCDRN8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DXCCDRN8MouseClicked

    private void DXCCDRN9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXCCDRN9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DXCCDRN9MouseClicked

    private void DXCCDRN10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DXCCDRN10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DXCCDRN10MouseClicked

    private void FUACCDM7CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_FUACCDM7CaretUpdate
        if(FUACCDM7.getText().equals("")){
            FUACCDM7.setEnabled(true);
        }else{
            FUACCDM8.setEnabled(false);
        }
    }//GEN-LAST:event_FUACCDM7CaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CCD;
    private javax.swing.JPanel CCD1;
    private javax.swing.JPanel CCD1A;
    private javax.swing.JPanel CCD2;
    private javax.swing.JPanel CCDM11;
    private javax.swing.JPanel CCDM12;
    private javax.swing.JPanel CCDR10A;
    private javax.swing.JPanel CCDR11A;
    private javax.swing.JPanel CCDR2A;
    private javax.swing.JPanel CCDR3A;
    private javax.swing.JPanel CCDR3A1;
    private javax.swing.JPanel CCDR5A;
    private javax.swing.JPanel CCDR6A;
    private javax.swing.JPanel CCDR7A;
    private javax.swing.JPanel CCDR8A;
    private javax.swing.JPanel CCDR9A;
    private javax.swing.JPanel CCDRN;
    private javax.swing.JLabel CIE10_ID;
    private javax.swing.JLabel DXCCD10A;
    private javax.swing.JLabel DXCCD11;
    private javax.swing.JLabel DXCCD11A;
    private javax.swing.JLabel DXCCD12;
    private javax.swing.JLabel DXCCD13;
    private javax.swing.JLabel DXCCD14;
    private javax.swing.JLabel DXCCD15;
    private javax.swing.JLabel DXCCD16;
    private javax.swing.JLabel DXCCD21;
    private javax.swing.JLabel DXCCD22;
    private javax.swing.JLabel DXCCD23;
    private javax.swing.JLabel DXCCD24;
    private javax.swing.JLabel DXCCD3A1;
    private javax.swing.JLabel DXCCD3A2;
    private javax.swing.JLabel DXCCD3A3;
    private javax.swing.JLabel DXCCD3A4;
    private javax.swing.JLabel DXCCD4A1;
    private javax.swing.JLabel DXCCD4A2;
    private javax.swing.JLabel DXCCD4A3;
    private javax.swing.JLabel DXCCD4A4;
    private javax.swing.JLabel DXCCD5A;
    private javax.swing.JLabel DXCCD6A;
    private javax.swing.JLabel DXCCD7A;
    private javax.swing.JLabel DXCCD8A;
    private javax.swing.JLabel DXCCD9A;
    private javax.swing.JLabel DXCCDM1;
    private javax.swing.JLabel DXCCDM10;
    private javax.swing.JLabel DXCCDM11;
    private javax.swing.JLabel DXCCDM2;
    private javax.swing.JLabel DXCCDM3;
    private javax.swing.JLabel DXCCDM4;
    private javax.swing.JLabel DXCCDM5;
    private javax.swing.JLabel DXCCDM6;
    private javax.swing.JLabel DXCCDM7;
    private javax.swing.JLabel DXCCDM8;
    private javax.swing.JLabel DXCCDM9;
    private javax.swing.JLabel DXCCDRN1;
    private javax.swing.JLabel DXCCDRN10;
    private javax.swing.JLabel DXCCDRN2;
    private javax.swing.JLabel DXCCDRN3;
    private javax.swing.JLabel DXCCDRN4;
    private javax.swing.JLabel DXCCDRN7;
    private javax.swing.JLabel DXCCDRN8;
    private javax.swing.JLabel DXCCDRN9;
    private com.toedter.calendar.JDateChooser FCCD10A;
    private com.toedter.calendar.JDateChooser FCCD11;
    private com.toedter.calendar.JDateChooser FCCD11A;
    private com.toedter.calendar.JDateChooser FCCD12;
    private com.toedter.calendar.JDateChooser FCCD13;
    private com.toedter.calendar.JDateChooser FCCD14;
    private com.toedter.calendar.JDateChooser FCCD15;
    private com.toedter.calendar.JDateChooser FCCD16;
    private com.toedter.calendar.JDateChooser FCCD21;
    private com.toedter.calendar.JDateChooser FCCD22;
    private com.toedter.calendar.JDateChooser FCCD23;
    private com.toedter.calendar.JDateChooser FCCD24;
    private com.toedter.calendar.JDateChooser FCCD3A1;
    private com.toedter.calendar.JDateChooser FCCD3A2;
    private com.toedter.calendar.JDateChooser FCCD3A3;
    private com.toedter.calendar.JDateChooser FCCD3A4;
    private com.toedter.calendar.JDateChooser FCCD4A1;
    private com.toedter.calendar.JDateChooser FCCD4A2;
    private com.toedter.calendar.JDateChooser FCCD4A3;
    private com.toedter.calendar.JDateChooser FCCD4A4;
    private com.toedter.calendar.JDateChooser FCCD5A;
    private com.toedter.calendar.JDateChooser FCCD6A;
    private com.toedter.calendar.JDateChooser FCCD7A;
    private com.toedter.calendar.JDateChooser FCCD8A;
    private com.toedter.calendar.JDateChooser FCCD9A;
    private com.toedter.calendar.JDateChooser FCCDM1;
    private com.toedter.calendar.JDateChooser FCCDM10;
    private com.toedter.calendar.JDateChooser FCCDM11;
    private com.toedter.calendar.JDateChooser FCCDM2;
    private com.toedter.calendar.JDateChooser FCCDM3;
    private com.toedter.calendar.JDateChooser FCCDM4;
    private com.toedter.calendar.JDateChooser FCCDM5;
    private com.toedter.calendar.JDateChooser FCCDM6;
    private com.toedter.calendar.JDateChooser FCCDM7;
    private com.toedter.calendar.JDateChooser FCCDM8;
    private com.toedter.calendar.JDateChooser FCCDM9;
    private com.toedter.calendar.JDateChooser FCCDRN1;
    private com.toedter.calendar.JDateChooser FCCDRN2;
    private com.toedter.calendar.JDateChooser FCCDRN3;
    private com.toedter.calendar.JDateChooser FCCDRN4;
    private javax.swing.JTextField FUACCD10A;
    private javax.swing.JTextField FUACCD11;
    private javax.swing.JTextField FUACCD11A;
    private javax.swing.JTextField FUACCD12;
    private javax.swing.JTextField FUACCD13;
    private javax.swing.JTextField FUACCD14;
    private javax.swing.JTextField FUACCD15;
    private javax.swing.JTextField FUACCD16;
    private javax.swing.JTextField FUACCD21;
    private javax.swing.JTextField FUACCD22;
    private javax.swing.JTextField FUACCD23;
    private javax.swing.JTextField FUACCD24;
    private javax.swing.JTextField FUACCD3A1;
    private javax.swing.JTextField FUACCD3A2;
    private javax.swing.JTextField FUACCD3A3;
    private javax.swing.JTextField FUACCD3A4;
    private javax.swing.JTextField FUACCD4A1;
    private javax.swing.JTextField FUACCD4A2;
    private javax.swing.JTextField FUACCD4A3;
    private javax.swing.JTextField FUACCD4A4;
    private javax.swing.JTextField FUACCD5A;
    private javax.swing.JTextField FUACCD6A;
    private javax.swing.JTextField FUACCD7A;
    private javax.swing.JTextField FUACCD8A;
    private javax.swing.JTextField FUACCD9A;
    private javax.swing.JTextField FUACCDM1;
    private javax.swing.JTextField FUACCDM10;
    private javax.swing.JTextField FUACCDM11;
    private javax.swing.JTextField FUACCDM2;
    private javax.swing.JTextField FUACCDM3;
    private javax.swing.JTextField FUACCDM4;
    private javax.swing.JTextField FUACCDM5;
    private javax.swing.JTextField FUACCDM6;
    private javax.swing.JTextField FUACCDM7;
    private javax.swing.JTextField FUACCDM8;
    private javax.swing.JTextField FUACCDM9;
    private javax.swing.JTextField FUACCDRN1;
    private javax.swing.JTextField FUACCDRN2;
    private javax.swing.JTextField FUACCDRN3;
    private javax.swing.JTextField FUACCDRN4;
    private javax.swing.JDialog FrmCie10;
    private javax.swing.JLabel HC_ID;
    private javax.swing.JPanel LEYENDA;
    private javax.swing.JLabel T7;
    private javax.swing.JLabel TxtDes;
    private javax.swing.JLabel TxtFecha;
    private javax.swing.JLabel TxtFua;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btneditar4;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel100;
    private javax.swing.JPanel jPanel101;
    private javax.swing.JPanel jPanel102;
    private javax.swing.JPanel jPanel103;
    private javax.swing.JPanel jPanel104;
    private javax.swing.JPanel jPanel105;
    private javax.swing.JPanel jPanel106;
    private javax.swing.JPanel jPanel107;
    private javax.swing.JPanel jPanel108;
    private javax.swing.JPanel jPanel109;
    private javax.swing.JPanel jPanel110;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel112;
    private javax.swing.JPanel jPanel113;
    private javax.swing.JPanel jPanel114;
    private javax.swing.JPanel jPanel115;
    private javax.swing.JPanel jPanel116;
    private javax.swing.JPanel jPanel117;
    private javax.swing.JPanel jPanel118;
    private javax.swing.JPanel jPanel119;
    private javax.swing.JPanel jPanel120;
    private javax.swing.JPanel jPanel121;
    private javax.swing.JPanel jPanel122;
    private javax.swing.JPanel jPanel123;
    private javax.swing.JPanel jPanel124;
    private javax.swing.JPanel jPanel125;
    private javax.swing.JPanel jPanel126;
    private javax.swing.JPanel jPanel127;
    private javax.swing.JPanel jPanel128;
    private javax.swing.JPanel jPanel169;
    private javax.swing.JPanel jPanel170;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel86;
    private javax.swing.JPanel jPanel87;
    private javax.swing.JPanel jPanel88;
    private javax.swing.JPanel jPanel89;
    private javax.swing.JPanel jPanel90;
    private javax.swing.JPanel jPanel91;
    private javax.swing.JPanel jPanel92;
    private javax.swing.JPanel jPanel93;
    private javax.swing.JPanel jPanel94;
    private javax.swing.JPanel jPanel95;
    private javax.swing.JPanel jPanel96;
    private javax.swing.JPanel jPanel97;
    private javax.swing.JPanel jPanel98;
    private javax.swing.JPanel jPanel99;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblnina;
    private javax.swing.JLabel lblnino;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    private javax.swing.JTable tbCiePresun;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscarCie10;
    // End of variables declaration//GEN-END:variables
}
