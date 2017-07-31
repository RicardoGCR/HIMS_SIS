/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;
import JCollantesImprimir.Ticket;
import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import static groovy.xml.Entity.gt;
import static groovy.xml.Entity.lt;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import modelos.Caja.Caja_Precio;
/**
 *
 * @author MYS1
 */
public class Caja_Precios extends javax.swing.JFrame {
DefaultTableModel m;
byte tg;
byte tge;
Caja_Precio cnn = new Caja_Precio();
Ticket ticket=new Ticket();
    /**
     * Creates new form Caja_Precios
     */
    public Caja_Precios() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
         LISTAR();
         LISTARFP();
         LISTARNom();
         formato1();
         formato();
         
         FormaP.setLocationRelativeTo(null);//en el centro
         FormaP.getContentPane().setBackground(Color.WHITE);
         Nomenclatura.setLocationRelativeTo(null);//en el centro
         Nomenclatura.getContentPane().setBackground(Color.WHITE);
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        cargareliminar.setVisible(false);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());

    }
    
    
    ////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////
    public void imprimir(){
        try{
            Date date=new Date();
            SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat hora=new SimpleDateFormat("hh:mm:ss aa");
            Ticket ticket = new Ticket();
            ticket.AddCabecera("             SANDALS RESTAURANT");
            ticket.AddCabecera(ticket.DarEspacio());
            ticket.AddCabecera("     tlf: 222222  r.u.c: 22222222222");
            ticket.AddCabecera(ticket.DarEspacio());
            ticket.AddSubCabecera(ticket.DibujarLinea(40));
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddSubCabecera("     Ticket Factura No:'003-000011'");
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddSubCabecera("        "+fecha.format(date) + " " + hora.format(date));
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddSubCabecera("         Mesa "+"1021"+" Mozo "+"1022"+" Pers "+"1023");
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddSubCabecera(ticket.DibujarLinea(40));
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddSubCabecera("cant      producto         p.u     total");
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddSubCabecera(ticket.DibujarLinea(40));
            ticket.AddSubCabecera(ticket.DarEspacio());
           for(int y=0;y<tb_Grupo1.getRowCount();y++){
               //cantidad de decimales
               NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
               DecimalFormat form = (DecimalFormat)nf;
               form.applyPattern("#,###.00");
               //cantidad
               String cantidad=tb_Grupo1.getValueAt(y,0).toString();
               if(cantidad.length()<4){
                   int cant=4-cantidad.length();String can="";
                   for(int f=0;f<cant;f++){can+=" ";}cantidad+=can;
               }
                //items
                String item=tb_Grupo1.getValueAt(y,1).toString();
                if(item.length()>17){item=item.substring(0,16)+".";}
                else{
                    int c=17-item.length();String comple="";
                    for(int y1=0;y1<c;y1++){comple+=" ";}item+=comple;
                }
                //precio
                String precio=tb_Grupo1.getValueAt(y,2).toString();
                double pre1=Double.parseDouble(precio);
                precio=form.format(pre1);
                if(precio.length()<8){
                    int p=8-precio.length();String pre="";
                    for(int y1=0;y1<p;y1++){pre+=" ";}precio=pre+precio;
                }
                //total
                String total=tb_Grupo1.getValueAt(y,3).toString();
                total=form.format(Double.parseDouble(total));
                if(total.length()<8){
                    int t=8-total.length();String tota="";
                    for(int y1=0;y1<t;y1++){tota+=" ";}total=tota+total;
                }
                //agrego los items al detalle
                ticket.AddItem(cantidad,item,precio,total);
                //ticket.AddItem("","","",ticket.DarEspacio());
            }
            ticket.AddItem(ticket.DibujarLinea(40),"","","");
            ticket.AddTotal("",ticket.DarEspacio());
            ticket.AddTotal("total                   ","200.00");
            ticket.AddTotal("",ticket.DarEspacio());
            ticket.AddTotal("Igv                     ","5.00");
            ticket.AddTotal("",ticket.DarEspacio());
            ticket.AddTotal("total venta             ","205.00");
            ticket.AddTotal("",ticket.DarEspacio());
            ticket.AddTotal("paga con                ","210.00");
            ticket.AddTotal("",ticket.DarEspacio());
            ticket.AddTotal("vuelto                  ","5.00");
            ticket.AddPieLinea(ticket.DarEspacio());
            ticket.AddPieLinea("Gracias por su Preferencia");
//            String miImpresora="HP LaserJet Professional P 1102w";
            ticket.ImprimirDocumento(true);
            System.out.println("Imprimiendo");
        }catch(Exception e){System.out.print("\nerror "+e.toString());}
    }
    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////

    
    public void LISTAR(){
    try {
             String titulos[]={"Codigo","Forma de Pago","Descripcion Forma de pago","CPT","Descripcion Nomenclatura","Precio","",""};//
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];

            Conexion obj = new Conexion();  
        String consulta="exec caja_Precios_listar";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7);
                fila[7]=r.getString(8);
      
                    m.addRow(fila);
                    c++;
            }
            tb_Grupo1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo1.setRowSorter(elQueOrdena);
            this.tb_Grupo1.setModel(m);
            formato();
            
    } catch (Exception e) {
    }
}
    
    public void LISTARFP(){
    try {
             String titulos[]={"Forma de Pago","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Conexion obj = new Conexion();  
        String consulta="exec ListarJerarquiaas";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
      
                    m.addRow(fila);
                    c++;
            }
            tb_Grupo.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo.setRowSorter(elQueOrdena);
            this.tb_Grupo.setModel(m);
            formato1();
            
    } catch (Exception e) {
    }
}
    public void LISTARNom(){
    try {
             String titulos[]={"CPT","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Conexion obj = new Conexion();  
        String consulta="exec ListarNomenclaturas";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
      
                    m.addRow(fila);
                    c++;
            }
            tb_Grupo2.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo2.setRowSorter(elQueOrdena);
            this.tb_Grupo2.setModel(m);
            formato1();
            
    } catch (Exception e) {
    }
}
    public void Buscar(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo1.setModel(new DefaultTableModel());
             String titulos[]={"Codigo","Forma de Pago","Descripcion Forma de pago","CPT","Descripcion Nomenclatura","Precio","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];

            Caja_Precio obj=new Caja_Precio();
                    consulta="exec Caja_Precios_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscartodo.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7);
                fila[7]=r.getString(8);

                m.addRow(fila);
                c++;
            }
            tb_Grupo1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo1.setRowSorter(elQueOrdena);
            this.tb_Grupo1.setModel(m);

            formato();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    public void formato(){
    tb_Grupo1.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Grupo1.getColumnModel().getColumn(1).setPreferredWidth(220);
    tb_Grupo1.getColumnModel().getColumn(2).setPreferredWidth(220);
    tb_Grupo1.getColumnModel().getColumn(3).setPreferredWidth(90);
    tb_Grupo1.getColumnModel().getColumn(4).setPreferredWidth(600);
    tb_Grupo1.getColumnModel().getColumn(5).setPreferredWidth(100);
    
    tb_Grupo1.getColumnModel().getColumn(6).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(6).setMaxWidth(0);
    
    tb_Grupo1.getColumnModel().getColumn(7).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(7).setMaxWidth(0);
    tb_Grupo1.setRowHeight(45);
    }
    
    public void BuscarFP(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo.setModel(new DefaultTableModel());
             String titulos[]={"Forma de Pago","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Precio obj=new Caja_Precio();
                    consulta="exec BuscarJerarquias ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
             

                m.addRow(fila);
                c++;
            }
            tb_Grupo.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo.setRowSorter(elQueOrdena);
            this.tb_Grupo.setModel(m);

            formato1();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    public void BuscarNom(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo2.setModel(new DefaultTableModel());
             String titulos[]={"CPT","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Precio obj=new Caja_Precio();
                    consulta="exec BuscarNomenclaturas ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar1.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
             

                m.addRow(fila);
                c++;
            }
            tb_Grupo2.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo2.setRowSorter(elQueOrdena);
            this.tb_Grupo2.setModel(m);

            formato1();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    public void formato1(){
    tb_Grupo.getColumnModel().getColumn(0).setPreferredWidth(300);
    tb_Grupo.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Grupo.getColumnModel().getColumn(2).setMaxWidth(0);

    tb_Grupo.setRowHeight(30);
    
    tb_Grupo2.getColumnModel().getColumn(0).setPreferredWidth(80);
    tb_Grupo2.getColumnModel().getColumn(1).setPreferredWidth(500);
    tb_Grupo2.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Grupo2.getColumnModel().getColumn(2).setMaxWidth(0);

    tb_Grupo2.setRowHeight(30);
    }
    public void GuardarPrecio(){

        if((txtPrecio.getText().equals(""))){
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ingrese el precio");
                                eli.setVisible(false);
                                noeli.setVisible(false);
        } else {

                
                Caja_Precio cno1 = new Caja_Precio();
                //cno1.setCod_precio(txtcodigo.getText());//
                cno1.setCod_jerar_forma_pago(fp.getText());//
                cno1.setCod_nomen_caja(nom.getText());//
                cno1.setPrecio(txtPrecio.getText());//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevoPrecio()==true){
                           cargareliminar.setVisible(true);   
                           cargareliminar.setBackground(new Color(0,153,102)); 
                           Mensaje.setText("Datos Guardados de forma correcta");
                           tge=1;
                           btnguardar.setEnabled(false);
                           btneditar.setEnabled(false);
                           btneliminar.setEnabled(false);
                           txtPrecio.setEditable(false);
                           eli.setText("OK");
                           eli.setVisible(true);
                           noeli.setVisible(false);
                           b.setVisible(false);
                           b1.setVisible(false);
                        
                           forma.setEditable(false);
                           Nomen.setEditable(false);
                            LISTAR();
                           
                       } else {
                            cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrió un error, Verifique");
                                eli.setVisible(false);
                                noeli.setVisible(false);
                                tge=1;
                       }}}

    public void Modificar(){
  
                        Caja_Precio cno = new Caja_Precio();
                        cno.setCod_precio(txtcodigo.getText());//
                        cno.setCod_jerar_forma_pago(fp.getText());//
                        cno.setCod_nomen_caja(nom.getText());//
                        cno.setPrecio(txtPrecio.getText());//
                        cno.setNom_usu(lblusu.getText());//
                        if(cno.modificarPrecio()==true){
                                   cargareliminar.setVisible(true);   
                           cargareliminar.setBackground(new Color(0,153,102)); 
                           Mensaje.setText("Datos Actualizados de forma correcta");
                           tge=1;
                           btnguardar.setEnabled(false);
                           btneditar.setEnabled(true);
                           txtPrecio.setEditable(false);
                           noeli.setText("OK");
                           eli.setVisible(false);
                           b.setVisible(false);
                           b1.setVisible(false);
                           noeli.setVisible(true);
                                tge=3;
                                
                             btnguardar.setEnabled(false);
                             btneditar.setEnabled(false);
                             btneliminar.setEnabled(false);
                             txtPrecio.setEditable(false);
                             
                       
                           forma.setEditable(false);
                           Nomen.setEditable(false);
                        } else {
                           
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrió un error, Verifique");
                                eli.setVisible(false);
                                noeli.setVisible(false);
                                tge=2;
                        }
                       
    }
    public void eliminar(){
         try{
            if(tge==6 ){
                Caja_Precio hCEl = new Caja_Precio();
                hCEl.setCod_precio(txtcodigo.getText());
                if(hCEl.eliminar()){
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Registro eliminado de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(false);
                                noeli.setText("OK");
                                noeli.setVisible(true);
                                tge=7;
                }
            } 
        }catch(Exception e){
            System.out.println("Error: " + e.toString());
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

        FormaP = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscarPaciente4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Grupo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            Nomenclatura = new javax.swing.JDialog();
            jPanel8 = new javax.swing.JPanel();
            jLabel20 = new javax.swing.JLabel();
            jPanel47 = new javax.swing.JPanel();
            txtBuscar1 = new javax.swing.JTextField();
            btnBuscarPaciente5 = new javax.swing.JButton();
            jScrollPane4 = new javax.swing.JScrollPane();
            tb_Grupo2 = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel1 = new javax.swing.JPanel();
                btnNuevo = new javax.swing.JButton();
                btneditar = new javax.swing.JButton();
                btnguardar = new javax.swing.JButton();
                btneliminar = new javax.swing.JButton();
                lblusu = new javax.swing.JLabel();
                jLabel57 = new javax.swing.JLabel();
                jPanel23 = new javax.swing.JPanel();
                buscartodo = new javax.swing.JTextField();
                btnBuscarPaciente = new javax.swing.JButton();
                lbldetalle = new javax.swing.JLabel();
                cargareliminar = new javax.swing.JPanel();
                Mensaje = new javax.swing.JLabel();
                eli = new javax.swing.JButton();
                noeli = new javax.swing.JButton();
                jPanel3 = new javax.swing.JPanel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                txtPrecio = new javax.swing.JTextField();
                fp = new javax.swing.JLabel();
                nom = new javax.swing.JLabel();
                txtcodigo = new javax.swing.JTextField();
                panelFormaPago = new javax.swing.JPanel();
                forma = new javax.swing.JTextField();
                b = new javax.swing.JButton();
                panelFormaPago1 = new javax.swing.JPanel();
                Nomen = new javax.swing.JTextField();
                b1 = new javax.swing.JButton();
                jScrollPane3 = new javax.swing.JScrollPane();
                tb_Grupo1 = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jPanel5 = new javax.swing.JPanel();
                    jLabel33 = new javax.swing.JLabel();

                    FormaP.setMinimumSize(new java.awt.Dimension(310, 441));
                    FormaP.setResizable(false);

                    jPanel7.setBackground(new java.awt.Color(41, 127, 184));
                    jPanel7.setMinimumSize(new java.awt.Dimension(310, 441));

                    jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel19.setText("Forma de Pago");

                    jPanel48.setBackground(new java.awt.Color(255, 255, 255));

                    txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    txtBuscar.setBorder(null);
                    txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtBuscarCaretUpdate(evt);
                        }
                    });
                    txtBuscar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtBuscarActionPerformed(evt);
                        }
                    });
                    txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtBuscarKeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtBuscarKeyReleased(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
                    jPanel48.setLayout(jPanel48Layout);
                    jPanel48Layout.setHorizontalGroup(
                        jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel48Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 5, Short.MAX_VALUE))
                    );
                    jPanel48Layout.setVerticalGroup(
                        jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(jLabel19)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnBuscarPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(24, Short.MAX_VALUE))
                    );
                    jPanel7Layout.setVerticalGroup(
                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addContainerGap(25, Short.MAX_VALUE)
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnBuscarPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(334, 334, 334))
                    );

                    jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                    tb_Grupo.setModel(new javax.swing.table.DefaultTableModel(
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
                    tb_Grupo.setGridColor(new java.awt.Color(255, 255, 255));
                    tb_Grupo.setRowHeight(25);
                    tb_Grupo.setSelectionBackground(new java.awt.Color(102, 102, 102));
                    tb_Grupo.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tb_GrupoMouseClicked(evt);
                        }
                    });
                    tb_Grupo.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tb_GrupoKeyPressed(evt);
                        }
                    });
                    jScrollPane2.setViewportView(tb_Grupo);

                    javax.swing.GroupLayout FormaPLayout = new javax.swing.GroupLayout(FormaP.getContentPane());
                    FormaP.getContentPane().setLayout(FormaPLayout);
                    FormaPLayout.setHorizontalGroup(
                        FormaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    );
                    FormaPLayout.setVerticalGroup(
                        FormaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(FormaPLayout.createSequentialGroup()
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                            .addContainerGap())
                    );

                    Nomenclatura.setMinimumSize(new java.awt.Dimension(612, 430));
                    Nomenclatura.setResizable(false);

                    jPanel8.setBackground(new java.awt.Color(41, 127, 184));
                    jPanel8.setMinimumSize(new java.awt.Dimension(310, 441));

                    jLabel20.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel20.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel20.setText("CPT");

                    jPanel47.setBackground(new java.awt.Color(255, 255, 255));

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
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtBuscar1KeyReleased(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
                    jPanel47.setLayout(jPanel47Layout);
                    jPanel47Layout.setHorizontalGroup(
                        jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel47Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 5, Short.MAX_VALUE))
                    );
                    jPanel47Layout.setVerticalGroup(
                        jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel47Layout.createSequentialGroup()
                            .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                    );

                    btnBuscarPaciente5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                    btnBuscarPaciente5.setContentAreaFilled(false);
                    btnBuscarPaciente5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                    jPanel8.setLayout(jPanel8Layout);
                    jPanel8Layout.setHorizontalGroup(
                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnBuscarPaciente5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(299, Short.MAX_VALUE))
                    );
                    jPanel8Layout.setVerticalGroup(
                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnBuscarPaciente5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(348, 348, 348))
                    );

                    jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                    tb_Grupo2.setModel(new javax.swing.table.DefaultTableModel(
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
                    tb_Grupo2.setGridColor(new java.awt.Color(255, 255, 255));
                    tb_Grupo2.setRowHeight(25);
                    tb_Grupo2.setSelectionBackground(new java.awt.Color(102, 102, 102));
                    tb_Grupo2.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tb_Grupo2MouseClicked(evt);
                        }
                    });
                    tb_Grupo2.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tb_Grupo2KeyPressed(evt);
                        }
                    });
                    jScrollPane4.setViewportView(tb_Grupo2);

                    javax.swing.GroupLayout NomenclaturaLayout = new javax.swing.GroupLayout(Nomenclatura.getContentPane());
                    Nomenclatura.getContentPane().setLayout(NomenclaturaLayout);
                    NomenclaturaLayout.setHorizontalGroup(
                        NomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                    );
                    NomenclaturaLayout.setVerticalGroup(
                        NomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(NomenclaturaLayout.createSequentialGroup()
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                            .addContainerGap())
                    );

                    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                    jPanel1.setBackground(new java.awt.Color(41, 127, 184));
                    jPanel1.setPreferredSize(new java.awt.Dimension(284, 714));

                    btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                    btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                    btnNuevo.setText("Nuevo");
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

                    btneditar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    btneditar.setForeground(new java.awt.Color(240, 240, 240));
                    btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
                    btneditar.setText("Editar");
                    btneditar.setContentAreaFilled(false);
                    btneditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btneditar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                    btneditar.setIconTextGap(30);
                    btneditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btneditar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btneditarActionPerformed(evt);
                        }
                    });

                    btnguardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    btnguardar.setForeground(new java.awt.Color(240, 240, 240));
                    btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
                    btnguardar.setText("Guardar");
                    btnguardar.setContentAreaFilled(false);
                    btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnguardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                    btnguardar.setIconTextGap(30);
                    btnguardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnguardar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnguardarActionPerformed(evt);
                        }
                    });

                    btneliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                    btneliminar.setForeground(new java.awt.Color(240, 240, 240));
                    btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
                    btneliminar.setText("Eliminar");
                    btneliminar.setContentAreaFilled(false);
                    btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btneliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    btneliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                    btneliminar.setIconTextGap(30);
                    btneliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btneliminar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btneliminarActionPerformed(evt);
                        }
                    });

                    lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                    lblusu.setForeground(new java.awt.Color(255, 255, 255));
                    lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                    lblusu.setText("Silvana");
                    lblusu.setFocusable(false);
                    lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                    jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                    jLabel57.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel57.setText("<html>Precios<span style=\"font-size:'14px'\"><br>Nomenclaturas</br></span></html>");

                    jPanel23.setBackground(new java.awt.Color(255, 255, 255));

                    buscartodo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                    buscartodo.setForeground(new java.awt.Color(51, 51, 51));
                    buscartodo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                    buscartodo.setBorder(null);
                    buscartodo.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            buscartodoCaretUpdate(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
                    jPanel23.setLayout(jPanel23Layout);
                    jPanel23Layout.setHorizontalGroup(
                        jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(42, 42, 42))
                    );
                    jPanel23Layout.setVerticalGroup(
                        jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel23Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                    btnBuscarPaciente.setContentAreaFilled(false);
                    btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscarPacienteActionPerformed(evt);
                        }
                    });

                    lbldetalle.setForeground(new java.awt.Color(255, 255, 255));
                    lbldetalle.setText("CPT, Descripción");

                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                    jPanel1.setLayout(jPanel1Layout);
                    jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbldetalle)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbldetalle)
                            .addGap(21, 21, 21)
                            .addComponent(btnNuevo)
                            .addGap(18, 18, 18)
                            .addComponent(btnguardar)
                            .addGap(18, 18, 18)
                            .addComponent(btneditar)
                            .addGap(18, 18, 18)
                            .addComponent(btneliminar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                            .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                    );

                    cargareliminar.setBackground(new java.awt.Color(255, 153, 51));

                    Mensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    Mensaje.setForeground(new java.awt.Color(255, 255, 255));
                    Mensaje.setText("Mensaje");

                    eli.setForeground(new java.awt.Color(240, 240, 240));
                    eli.setText("Si");
                    eli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                    eli.setContentAreaFilled(false);
                    eli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    eli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    eli.setIconTextGap(30);
                    eli.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            eliActionPerformed(evt);
                        }
                    });

                    noeli.setForeground(new java.awt.Color(240, 240, 240));
                    noeli.setText("No");
                    noeli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                    noeli.setContentAreaFilled(false);
                    noeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    noeli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    noeli.setIconTextGap(30);
                    noeli.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            noeliActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout cargareliminarLayout = new javax.swing.GroupLayout(cargareliminar);
                    cargareliminar.setLayout(cargareliminarLayout);
                    cargareliminarLayout.setHorizontalGroup(
                        cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(cargareliminarLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(Mensaje)
                            .addGap(46, 46, 46)
                            .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    cargareliminarLayout.setVerticalGroup(
                        cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(cargareliminarLayout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Mensaje)
                                .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                    jPanel3.setMinimumSize(new java.awt.Dimension(543, 156));

                    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel3.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel3.setText("Forma de Pago");

                    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel4.setText("Nomenclatura");

                    jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel5.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel5.setText("Precio");

                    txtPrecio.setEditable(false);
                    txtPrecio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    txtPrecio.setForeground(new java.awt.Color(51, 51, 51));
                    txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtPrecioKeyPressed(evt);
                        }
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtPrecioKeyTyped(evt);
                        }
                    });

                    fp.setBackground(new java.awt.Color(255, 255, 255));
                    fp.setForeground(new java.awt.Color(255, 255, 255));
                    fp.setText("jLabel8");

                    nom.setBackground(new java.awt.Color(255, 255, 255));
                    nom.setForeground(new java.awt.Color(255, 255, 255));
                    nom.setText("jLabel8");

                    txtcodigo.setEditable(false);
                    txtcodigo.setForeground(new java.awt.Color(255, 255, 255));
                    txtcodigo.setBorder(null);

                    panelFormaPago.setBackground(new java.awt.Color(255, 255, 255));
                    panelFormaPago.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    forma.setEditable(false);
                    forma.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                    forma.setForeground(new java.awt.Color(51, 51, 51));
                    forma.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                    forma.setBorder(null);
                    forma.setSelectedTextColor(new java.awt.Color(51, 51, 51));
                    forma.setSelectionColor(new java.awt.Color(255, 255, 255));
                    forma.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            formaCaretUpdate(evt);
                        }
                    });

                    b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    b.setContentAreaFilled(false);
                    b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    b.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            bActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout panelFormaPagoLayout = new javax.swing.GroupLayout(panelFormaPago);
                    panelFormaPago.setLayout(panelFormaPagoLayout);
                    panelFormaPagoLayout.setHorizontalGroup(
                        panelFormaPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelFormaPagoLayout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addComponent(forma, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3))
                    );
                    panelFormaPagoLayout.setVerticalGroup(
                        panelFormaPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelFormaPagoLayout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addGroup(panelFormaPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(forma, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    panelFormaPago1.setBackground(new java.awt.Color(255, 255, 255));
                    panelFormaPago1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    Nomen.setEditable(false);
                    Nomen.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                    Nomen.setForeground(new java.awt.Color(51, 51, 51));
                    Nomen.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                    Nomen.setBorder(null);
                    Nomen.setSelectedTextColor(new java.awt.Color(51, 51, 51));
                    Nomen.setSelectionColor(new java.awt.Color(255, 255, 255));
                    Nomen.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            NomenCaretUpdate(evt);
                        }
                    });

                    b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    b1.setContentAreaFilled(false);
                    b1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    b1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            b1ActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout panelFormaPago1Layout = new javax.swing.GroupLayout(panelFormaPago1);
                    panelFormaPago1.setLayout(panelFormaPago1Layout);
                    panelFormaPago1Layout.setHorizontalGroup(
                        panelFormaPago1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelFormaPago1Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addComponent(Nomen)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3))
                    );
                    panelFormaPago1Layout.setVerticalGroup(
                        panelFormaPago1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelFormaPago1Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addGroup(panelFormaPago1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Nomen, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                    jPanel3.setLayout(jPanel3Layout);
                    jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3))
                            .addGap(61, 61, 61)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(panelFormaPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelFormaPago1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fp)
                            .addGap(5, 5, 5)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(338, 338, 338)
                                    .addComponent(nom))
                                .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(121, 121, 121))
                    );
                    jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(fp)
                                            .addComponent(nom)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(panelFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(panelFormaPago1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                    tb_Grupo1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                    tb_Grupo1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                    tb_Grupo1.setForeground(new java.awt.Color(51, 51, 51));
                    tb_Grupo1.setModel(new javax.swing.table.DefaultTableModel(
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
                    tb_Grupo1.setGridColor(new java.awt.Color(255, 255, 255));
                    tb_Grupo1.setRowHeight(25);
                    tb_Grupo1.setSelectionBackground(new java.awt.Color(102, 102, 102));
                    tb_Grupo1.getTableHeader().setReorderingAllowed(false);
                    tb_Grupo1.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tb_Grupo1MouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                            tb_Grupo1MousePressed(evt);
                        }
                    });
                    tb_Grupo1.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tb_Grupo1KeyPressed(evt);
                        }
                    });
                    jScrollPane3.setViewportView(tb_Grupo1);

                    jPanel5.setBackground(new java.awt.Color(43, 43, 43));
                    jPanel5.setPreferredSize(new java.awt.Dimension(929, 115));

                    jLabel33.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                    jLabel33.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel33.setText("Edición");

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
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cargareliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1223, Short.MAX_VALUE)))
                    );
                    layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );

                    pack();
                }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
//        txtcodigo.setText(cnn.idPrecio());
        tg=1;
        
       
      
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        b.setVisible(true);
        b1.setVisible(true);
        txtPrecio.setText("");
        Nomen.setText("");
        forma.setText("");
        txtcodigo.setEnabled(true);
        
        cargareliminar.setVisible(false);
        FormaP.setVisible(true);
     
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
   
        b.setVisible(true);
        b1.setVisible(true);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        txtPrecio.setEditable(true);
        txtcodigo.setEnabled(true);
        forma.setEnabled(true);
        Nomen.setEnabled(true);
        txtPrecio.setEditable(true);
        tg=2;
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if(tg==1){
            GuardarPrecio();
                 LISTAR();
        }else if(tg==2){
              cargareliminar.setVisible(true);
              cargareliminar.setBackground(new Color(255,153,51)); 
              Mensaje.setText("Desea Actualizar el Registro ?");
              eli.setText("Si");
              eli.setVisible(true);
              noeli.setText("No");
              noeli.setVisible(true);
              tge=2;
             
 
        }
      

    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
              cargareliminar.setVisible(true);
              cargareliminar.setBackground(new Color(255,91,70)); 
              Mensaje.setText("Desea Eliminar este registro?");
              eli.setText("Si");
              eli.setVisible(true);
              noeli.setText("No");
              noeli.setVisible(true);
              tge=6;
    }//GEN-LAST:event_btneliminarActionPerformed

    private void tb_Grupo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MouseClicked
        //CUENTA2
        Caja_Precio cno1 = new Caja_Precio();
        int fila=tb_Grupo1.getSelectedRow();
        if(evt.getClickCount()==1){
       
            txtcodigo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            forma.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            Nomen.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
            txtPrecio.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
            
            nom.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 6)));
            fp.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 7)));
            
        }
        tg=2;
        forma.setEnabled(false);
        txtPrecio.setEditable(false);
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        b.setEnabled(false);
        b1.setEnabled(false);
        Nomen.setEnabled(true);
        forma.setEnabled(true);
        txtcodigo.setEnabled(true);
        txtPrecio.setEditable(true);
        txtPrecio.setEditable(false);
       
       
    }//GEN-LAST:event_tb_Grupo1MouseClicked

    private void tb_Grupo1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MousePressed

    }//GEN-LAST:event_tb_Grupo1MousePressed

    private void tb_Grupo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo1KeyPressed

        Caja_Precio cno1 = new Caja_Precio();  
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo1.getSelectedRow();
        
         
            txtcodigo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            forma.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            Nomen.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
            txtPrecio.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
            
            nom.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 6)));
            fp.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 7)));
            
        }
        tg=2;
        forma.setEnabled(false);
        txtPrecio.setEditable(false);
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        b.setEnabled(false);
        b1.setEnabled(false);
        Nomen.setEnabled(true);
        forma.setEnabled(true);
        txtcodigo.setEnabled(true);
        txtPrecio.setEditable(true);
        txtPrecio.setEditable(false);
     
        
    }//GEN-LAST:event_tb_Grupo1KeyPressed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        BuscarFP();
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void tb_GrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GrupoMouseClicked
        //CUENTA2
        int fila=tb_Grupo.getSelectedRow();
        if(evt.getClickCount()==2){
            FormaP.dispose();
            forma.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            fp.setText(String.valueOf(tb_Grupo.getValueAt(fila, 2)));
                          forma.setEnabled(true);
                          Nomenclatura.setVisible(true);

        }
    }//GEN-LAST:event_tb_GrupoMouseClicked

    private void tb_GrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_GrupoKeyPressed
        // CUENTA 2
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo.getSelectedRow();
            FormaP.dispose();
            forma.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            fp.setText(String.valueOf(tb_Grupo.getValueAt(fila, 2)));
              forma.setEnabled(true);
              Nomenclatura.setVisible(true);
   
        }
    }//GEN-LAST:event_tb_GrupoKeyPressed

    private void txtBuscar1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar1CaretUpdate
        BuscarNom();
    }//GEN-LAST:event_txtBuscar1CaretUpdate

    private void txtBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar1ActionPerformed

    private void tb_Grupo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo2MouseClicked
        //CUENTA2
        int fila=tb_Grupo2.getSelectedRow();
        if(evt.getClickCount()==2){
            Nomenclatura.dispose();
            Nomen.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 0)));
            nom.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 2)));
            Nomen.setEnabled(true);
             txtPrecio.setEnabled(true);
        txtPrecio.setEditable(true);
       txtPrecio.requestFocus();
        }
    }//GEN-LAST:event_tb_Grupo2MouseClicked

    private void tb_Grupo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo2KeyPressed
        // CUENTA 2
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo2.getSelectedRow();
            Nomenclatura.dispose();
            Nomen.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 0))) ;
            nom.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 2))) ;
        Nomen.setEnabled(true);
         txtPrecio.setEditable(true);
        txtPrecio.setEditable(true);
        txtPrecio.requestFocus();
        }
    }//GEN-LAST:event_tb_Grupo2KeyPressed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        if (tge==1 || tge==2){
            cargareliminar.setVisible(false);

        }
        if (tge==2){
            Modificar();
            LISTAR();

        }
         if (tge==6){
            eliminar();
             LISTAR();

        }
      

    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed
        
       if (tge==1||tge==6||tge==7){
             cargareliminar.setVisible(false);  
               }
        if (tge==2){
            cargareliminar.setVisible(true);
            cargareliminar.setBackground(new Color(255,153,51)); 
            Mensaje.setText("No se realizo ningun cambio");
            eli.setVisible(false);
            noeli.setVisible(false);

        }
           if (tge==3){
            cargareliminar.setVisible(false);

        }
                                
    }//GEN-LAST:event_noeliActionPerformed

    private void txtBuscar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyPressed
            char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_Grupo2.getSelectionModel().setSelectionInterval (0,0) ;
          tb_Grupo2.requestFocus();
          } 
    }//GEN-LAST:event_txtBuscar1KeyPressed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
           char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_Grupo.getSelectionModel().setSelectionInterval (0,0) ;
          tb_Grupo.requestFocus();
          } 
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }    
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtPrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyPressed
      char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
             if(tg==1){
                 GuardarPrecio();
                 LISTAR();
                 eli.requestFocus();
        }
        if(tg==2){
           cargareliminar.setVisible(true);
           cargareliminar.setBackground(new Color(255,153,51)); 
           Mensaje.setText("Desea Actualizar el Registro ?");
           eli.setText("Si");
           eli.setVisible(true);
           noeli.setVisible(true); 
           tge=2;
        }       
          } 
    }//GEN-LAST:event_txtPrecioKeyPressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        txtBuscar.setText(txtBuscar.getText().toUpperCase());
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyReleased
         txtBuscar1.setText(txtBuscar1.getText().toUpperCase());
    }//GEN-LAST:event_txtBuscar1KeyReleased

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        Buscar();
   
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void formaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_formaCaretUpdate

    }//GEN-LAST:event_formaCaretUpdate

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        FormaP.setVisible(true);
    }//GEN-LAST:event_bActionPerformed

    private void NomenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_NomenCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_NomenCaretUpdate

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        Nomenclatura.setVisible(true);
    }//GEN-LAST:event_b1ActionPerformed

    private void btnBuscarPaciente4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente4ActionPerformed

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
            java.util.logging.Logger.getLogger(Caja_Precios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Precios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Precios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Precios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Precios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog FormaP;
    private javax.swing.JLabel Mensaje;
    public static javax.swing.JTextField Nomen;
    private javax.swing.JDialog Nomenclatura;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarPaciente4;
    private javax.swing.JButton btnBuscarPaciente5;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    public static javax.swing.JTextField buscartodo;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JButton eli;
    public static javax.swing.JTextField forma;
    private javax.swing.JLabel fp;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JLabel nom;
    private javax.swing.JPanel panelFormaPago;
    private javax.swing.JPanel panelFormaPago1;
    private javax.swing.JTable tb_Grupo;
    private javax.swing.JTable tb_Grupo1;
    private javax.swing.JTable tb_Grupo2;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtcodigo;
    // End of variables declaration//GEN-END:variables
}
