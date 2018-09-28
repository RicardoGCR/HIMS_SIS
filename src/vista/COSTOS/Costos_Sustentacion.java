/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista.COSTOS;


import vista.COSTOS.BUSCAR_NOMENCLATURA;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.COSTOS.CTipoSustentacion;
import modelos.COSTOS.CostosSustentacion;
import modelos.COSTOS.FP_Herramientas;
import modelos.COSTOS.FP_Infraestructura;
import modelos.COSTOS.FP_Insumos;
import modelos.COSTOS.FP_Personal;
import modelos.COSTOS.FP_SBasicos;
import modelos.COSTOS.FP_Servicios;
import modelos.Usuario;
import modelos.COSTOS.referencialCabecera;
import modelos.COSTOS.referencialDetalle;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import static vista.Principal.fechaActual;
import servicios.Conexion;
import vista.*;

/**
 *
 * @author USUARIO
 */
public class Costos_Sustentacion extends javax.swing.JFrame implements Runnable {
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    DefaultTableModel mbuscar,m,m1,m2,m3,msa,msg,msi,msb,mi,cs,modelo4,modelo5;
    Conexion conectar=new Conexion();
    Connection con;
    CallableStatement cst;
    ResultSet r;
    Statement st;
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Creates new form COSTOS_DETALLE
     */
    public Costos_Sustentacion() {
        initComponents();
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        con=conectar.conectar();
        inicializar_tabla();
       
        //ICONO DE FORMULARIO
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
    
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnBuscarCPT.setVisible(false);
        BuscarNomenclatura.getContentPane().setBackground(Color.white);  
        Costos_Sustentacion.getContentPane().setBackground(Color.white);  
        
        txtNomenclatura.setLineWrap(true);
        txtNomenclatura.setWrapStyleWord(true);
        txtNomenclatura.setAlignmentX(CENTER_ALIGNMENT);
        txtNomenclatura.setAlignmentY(CENTER_ALIGNMENT);
        //Busqueda
        //BuscarNomenclatura.setVisible(true);
       // setVisible(false);
        //Hora y fecha
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance();          
        lblFechaPersonal.setText(fechaActual()); 
        txtCodigoSustento.setVisible(false);
        txtCodigoPrecio.setVisible(false);
        txtGuarModif.setVisible(false);
        txtSumatoriaEnergiaBA.setVisible(false);
        txtSumatoriaAguaBA.setVisible(false);
        txtConsumo_MA.setVisible(false);
        txtConsumo_ME.setVisible(false);
        CostosSustentacion cs=new CostosSustentacion();
        txtCodigoSustento.setText(cs.codCostosSustentacion());
        
        if(txtCodigoSustento.getText().equalsIgnoreCase("")){
        txtCodigoSustento.setText("CS000000000000000001");
        txtCodigo6.setText("000001");
        }else{
            txtCodigo6.setText(cs.codCostosSustentacion().substring(14, 20));
        }
        txtGanancia.setVisible(false);
        BuscarNomenclatura.setLocationRelativeTo(null);
        Costos_Sustentacion.setLocationRelativeTo(null);
        cargarPrecios_Nomenclatura();
        cargarCostoSustentacion();
        //Ocultar Totales
        txtTotalPersonal.setVisible(false);
        txtTotalInsumos.setVisible(false);
        txtTotalSBasicos.setVisible(false);
        txtTotalHerramienta.setVisible(false);
        txtTotalInfraes.setVisible(false);
        txtTotalSAdminis.setVisible(false);
        txtTotalSGenerales.setVisible(false);
        txtTotalSIntermedios.setVisible(false);
        btnNuevo.setVisible(false);
        //para limpiar el txt al darle click
 txtBuscar.addFocusListener(new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
  txtBuscar.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
} );
 //salir presionando escape
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();

            }
        });
}
      protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        g2.rotate(1/2 * Math.PI, cx, cy);
        txtNomenclatura.paintComponents(g2);
    }    

    public void inicializar_tabla(){
     try{
            //Personal
            String titulosp[]={"Cod_tipoSust","Cod_Sueldo","Tipo Sustento","Nombre del Detalle","Hora","Min","Horas al Mes","Sueldo","Total Sustento"};
            m3=new DefaultTableModel(null,titulosp);
            JTable pp=new JTable(m3);
            String filap[]=new String[9];
            tbPersonal.setModel(m3);
            TableRowSorter<TableModel> elQueOrdenap=new TableRowSorter<TableModel>(m3);
            tbPersonal.setRowSorter(elQueOrdenap);
            this.tbPersonal.setModel(m3);
            
            //Insumos
            String titulos[]={"Cod_tipoSust","Cod_Prod_Refe","Consumible","Tipo Sustento","Nombre del Producto","Cantidad Sustento","Rendimiento","UM","Cantidad UM","Precio Sustento","Total Sustento"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[11];
            tbInsumo.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbInsumo.setRowSorter(elQueOrdena);
            this.tbInsumo.setModel(m);
            
            //Servicios Basicos
            String titulosb[]={"Código_Factor de Prod.","Cod_Servicio","Factores de Producción","Servicio","Área",
            "Ponderación de Energía","Base de Asignacion Energía","Sumatoria Base Asig. Energía",
            "Coeficiente de Consumo Energía","Consumo de Energía","Importe Energía Electrica","Importe por Consulta - Energía",
            "Ponderación de Consumo Agua","Base de Asig. Consumo Agua","Sumatoria Base Asig. Agua",
            "Coeficiente de Consumo Agua","Consumo de Agua","Importe Consumo de Agua","Importe por Consulta - Agua",
            "Total de Consultas","Costo Estandar"};
            msb=new DefaultTableModel(null,titulosb);
            JTable psb=new JTable(msb);
            String filasb[]=new String[20];
            tbServiciosBasicos.setModel(msb);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(msb);
            tbServiciosBasicos.setRowSorter(elQueOrdenasb);
            this.tbServiciosBasicos.setModel(msb);
            
            //Herramientas
            String titulos1[]={"Cod_tipoSust","Cod_Depre","Tipo Sustento","Nombre del Detalle","Codigo Patrimonial","Año de Compra","Valor de Compra",
            "Fecha Fin a Depreciar","Vida Util","Depreciacion Mensual","Total de Meses a Depreciar","Depreciacion Acumulada","Valor Neto","Depreciacion Diaria"};
            m1=new DefaultTableModel(null,titulos1);
            JTable p1=new JTable(m1);
            String fila1[]=new String[14];
            tbHerramienta.setModel(m1);
            TableRowSorter<TableModel> elQueOrdena1=new TableRowSorter<TableModel>(m1);
            tbHerramienta.setRowSorter(elQueOrdena1);
            this.tbHerramienta.setModel(m1);
            
            
            //Infraestructura cod_tipo,cod_servicio,tipo_Sust,nom_servicio,requ,valor_uni,costo_cons,tiempo,depreciacion,tiempoH,tiempoM,costo_estandar
            String titulosii[]={"Cod_tipoSust","Cod_Servicio","Tipo Sustento","Nombre del Detalle","Area Total","Costo Total","Requerimiento mínimo de Area","Valor Unitario Depr.",
                "Costo de Construccion","Tiempo de Rendimiento(min)","Depreciación de Infraestructura","Tiempo(Hora)","Tiempo(min)","Costo Estándar"};
            mi=new DefaultTableModel(null,titulosii);
            JTable pii=new JTable(mi);
            String filasi[]=new String[14];
            tbInfraestructura.setModel(mi);
            TableRowSorter<TableModel> elQueOrdenasi=new TableRowSorter<TableModel>(mi);
            tbInfraestructura.setRowSorter(elQueOrdenasi);
            this.tbInfraestructura.setModel(mi);
            
            
            //Servicios Administrativos
            String tituloss[]={"Cod_tipoSust","Cod_Servicio","Tipo Sustento","Nombre del Detalle","Cantidad Sustento","Precio Sustento","Total Sustento"};
            msa=new DefaultTableModel(null,tituloss);
            JTable ps=new JTable(msa);
            String filas[]=new String[8];
            tbServiciosAdministr.setModel(msa);
            TableRowSorter<TableModel> elQueOrdenas=new TableRowSorter<TableModel>(msa);
            tbServiciosAdministr.setRowSorter(elQueOrdenas);
            this.tbServiciosAdministr.setModel(msa);
            
            //Servicios Generales
            String titulosg[]={"Cod_tipoSust","Cod_Servicio","Tipo Sustento","Nombre del Detalle","Cantidad Sustento","Precio Sustento","Total Sustento"};
            msg=new DefaultTableModel(null,titulosg);
            JTable psg=new JTable(msg);
            String filasg[]=new String[6];
            tbServiciosGenerales.setModel(msg);
            TableRowSorter<TableModel> elQueOrdenasg=new TableRowSorter<TableModel>(msg);
            tbServiciosGenerales.setRowSorter(elQueOrdenasg);
            this.tbServiciosGenerales.setModel(msg);
            
            //Servicios Intermedios
            String titulossi[]={"Cod_tipoSust","Cod_Servicio","Tipo Sustento","Nombre del Detalle","Cantidad Sustento","Precio Sustento","Total Sustento"};
            msi=new DefaultTableModel(null,titulossi);
            JTable psi=new JTable(msi);
            String filassi[]=new String[6];
            tbServiciosIntermedios.setModel(msi);
            TableRowSorter<TableModel> elQueOrdenassi=new TableRowSorter<TableModel>(msi);
            tbServiciosIntermedios.setRowSorter(elQueOrdenassi);
            this.tbServiciosIntermedios.setModel(msi);
            
            //Resumen
            String titulos2[]={"Tipo Sustentación","Valor Total"};
            m2=new DefaultTableModel(null,titulos2);
            JTable p2=new JTable(m2);
            String filar[]=new String[2];
            for (int i=0; i<8; i++){
                if(i==0){
                filar[0]="RECURSO HUMANO";
                filar[1]="0.00";
                }
                else if(i==1){
                filar[0]="INSUMOS";
                fila[1]="0.00";
                } 
                else if(i==2){
                filar[0]="SERVICIOS BÁSICOS";
                filar[1]="0.00";
                } 
                else if(i==3){
                filar[0]="EQUIPAMIENTO BÁSICO";
                filar[1]="0.00";
                } 
                else if(i==4){
                filar[0]="INFRAESTRUCTURA";
                filar[1]="0.00";
                } 
                else if(i==5){
                filar[0]="SERVICIOS ADMINISTRATIVOS";
                filar[1]="0.00";
                } 
                else if(i==6){
                filar[0]="SERVICIOS GENERALES";
                filar[1]="0.00";
                } 
                else if(i==7){
                filar[0]="GASTOS INTERMEDIOS";
                filar[1]="0.00";
                } 
                m2.addRow(filar);
                }
            tbResumenCostos.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena2=new TableRowSorter<TableModel>(m2);
            tbResumenCostos.setRowSorter(elQueOrdena2);
            this.tbResumenCostos.setModel(m2);
            
            formatoInicializarTabla();
            
            
             } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
    }
    public static void formatoInicializarTabla(){
    //Personal
    tbPersonal.getColumnModel().getColumn(2).setPreferredWidth(150);
    tbPersonal.getColumnModel().getColumn(3).setPreferredWidth(180);
    tbPersonal.getColumnModel().getColumn(4).setPreferredWidth(50);
    tbPersonal.getColumnModel().getColumn(5).setPreferredWidth(50);
    tbPersonal.getColumnModel().getColumn(6).setPreferredWidth(120);
    tbPersonal.getColumnModel().getColumn(7).setPreferredWidth(120);
    tbPersonal.getColumnModel().getColumn(8).setPreferredWidth(120);
    //Ocultar
    tbPersonal.getColumnModel().getColumn(0).setMinWidth(0);
    tbPersonal.getColumnModel().getColumn(0).setMaxWidth(0);
    tbPersonal.getColumnModel().getColumn(1).setMinWidth(0);
    tbPersonal.getColumnModel().getColumn(1).setMaxWidth(0);
    
    
    //Insumos
    tbInsumo.getColumnModel().getColumn(3).setPreferredWidth(150);
    tbInsumo.getColumnModel().getColumn(4).setPreferredWidth(160);
    tbInsumo.getColumnModel().getColumn(5).setPreferredWidth(120);
    tbInsumo.getColumnModel().getColumn(6).setPreferredWidth(120);
    tbInsumo.getColumnModel().getColumn(7).setPreferredWidth(120);
    tbInsumo.getColumnModel().getColumn(8).setPreferredWidth(120);
    tbInsumo.getColumnModel().getColumn(9).setPreferredWidth(120);
    tbInsumo.getColumnModel().getColumn(10).setPreferredWidth(120);
    //Ocultar Insumos
    tbInsumo.getColumnModel().getColumn(0).setMinWidth(0);
    tbInsumo.getColumnModel().getColumn(0).setMaxWidth(0);
    tbInsumo.getColumnModel().getColumn(1).setMinWidth(0);
    tbInsumo.getColumnModel().getColumn(1).setMaxWidth(0);
    tbInsumo.getColumnModel().getColumn(2).setMinWidth(0);
    tbInsumo.getColumnModel().getColumn(2).setMaxWidth(0);
    
    //Servicios Basicos
    tbServiciosBasicos.getColumnModel().getColumn(0).setPreferredWidth(180);
    tbServiciosBasicos.getColumnModel().getColumn(1).setPreferredWidth(180);
    tbServiciosBasicos.getColumnModel().getColumn(2).setPreferredWidth(180);
    tbServiciosBasicos.getColumnModel().getColumn(3).setPreferredWidth(150);
    tbServiciosBasicos.getColumnModel().getColumn(4).setPreferredWidth(120);
    tbServiciosBasicos.getColumnModel().getColumn(5).setPreferredWidth(180);
    tbServiciosBasicos.getColumnModel().getColumn(6).setPreferredWidth(180);
    tbServiciosBasicos.getColumnModel().getColumn(7).setPreferredWidth(180);
    tbServiciosBasicos.getColumnModel().getColumn(8).setPreferredWidth(200);
    tbServiciosBasicos.getColumnModel().getColumn(9).setPreferredWidth(180);
    tbServiciosBasicos.getColumnModel().getColumn(10).setPreferredWidth(200);
    tbServiciosBasicos.getColumnModel().getColumn(11).setPreferredWidth(200);
    tbServiciosBasicos.getColumnModel().getColumn(12).setPreferredWidth(200);
    tbServiciosBasicos.getColumnModel().getColumn(13).setPreferredWidth(200);
    tbServiciosBasicos.getColumnModel().getColumn(14).setPreferredWidth(180);
    tbServiciosBasicos.getColumnModel().getColumn(15).setPreferredWidth(180);
    tbServiciosBasicos.getColumnModel().getColumn(16).setPreferredWidth(180);
    tbServiciosBasicos.getColumnModel().getColumn(17).setPreferredWidth(180);
    tbServiciosBasicos.getColumnModel().getColumn(18).setPreferredWidth(200);
    tbServiciosBasicos.getColumnModel().getColumn(19).setPreferredWidth(150);
    tbServiciosBasicos.getColumnModel().getColumn(20).setPreferredWidth(150);
    //Servicios Basicos - Ocultar
    tbServiciosBasicos.getColumnModel().getColumn(0).setMinWidth(0);
    tbServiciosBasicos.getColumnModel().getColumn(0).setMaxWidth(0);
    tbServiciosBasicos.getColumnModel().getColumn(1).setMinWidth(0);
    tbServiciosBasicos.getColumnModel().getColumn(1).setMaxWidth(0);
    
    
    //Herramienta
    tbHerramienta.getColumnModel().getColumn(2).setPreferredWidth(150);
    tbHerramienta.getColumnModel().getColumn(3).setPreferredWidth(150);
    tbHerramienta.getColumnModel().getColumn(4).setPreferredWidth(120);
    tbHerramienta.getColumnModel().getColumn(5).setPreferredWidth(120);
    tbHerramienta.getColumnModel().getColumn(6).setPreferredWidth(120);
    tbHerramienta.getColumnModel().getColumn(7).setPreferredWidth(120);
    tbHerramienta.getColumnModel().getColumn(8).setPreferredWidth(120);
    tbHerramienta.getColumnModel().getColumn(9).setPreferredWidth(120);
    tbHerramienta.getColumnModel().getColumn(10).setPreferredWidth(120);
    tbHerramienta.getColumnModel().getColumn(11).setPreferredWidth(120);
    tbHerramienta.getColumnModel().getColumn(12).setPreferredWidth(120);
    tbHerramienta.getColumnModel().getColumn(13).setPreferredWidth(120);
    //Herramienta- Ocultar    
    tbHerramienta.getColumnModel().getColumn(0).setMinWidth(0);
    tbHerramienta.getColumnModel().getColumn(0).setMaxWidth(0);
    tbHerramienta.getColumnModel().getColumn(1).setMinWidth(0);
    tbHerramienta.getColumnModel().getColumn(1).setMaxWidth(0);
  
    
    //Infraes
    tbInfraestructura.getColumnModel().getColumn(0).setPreferredWidth(150);
    tbInfraestructura.getColumnModel().getColumn(1).setPreferredWidth(180);
    tbInfraestructura.getColumnModel().getColumn(2).setPreferredWidth(120);
    tbInfraestructura.getColumnModel().getColumn(3).setPreferredWidth(120);
    tbInfraestructura.getColumnModel().getColumn(4).setPreferredWidth(120);
    tbInfraestructura.getColumnModel().getColumn(5).setPreferredWidth(150);
    tbInfraestructura.getColumnModel().getColumn(6).setPreferredWidth(180);
    tbInfraestructura.getColumnModel().getColumn(7).setPreferredWidth(135);
    tbInfraestructura.getColumnModel().getColumn(8).setPreferredWidth(135);
    tbInfraestructura.getColumnModel().getColumn(9).setPreferredWidth(135);
    tbInfraestructura.getColumnModel().getColumn(10).setPreferredWidth(135);
    tbInfraestructura.getColumnModel().getColumn(11).setPreferredWidth(120);
    tbInfraestructura.getColumnModel().getColumn(12).setPreferredWidth(120);
    tbInfraestructura.getColumnModel().getColumn(13).setPreferredWidth(120);
    //Infraestructura- Ocultar    
    tbInfraestructura.getColumnModel().getColumn(0).setMinWidth(0);
    tbInfraestructura.getColumnModel().getColumn(0).setMaxWidth(0);
    tbInfraestructura.getColumnModel().getColumn(1).setMinWidth(0);
    tbInfraestructura.getColumnModel().getColumn(1).setMaxWidth(0);
    
    //Servicios Adminis
    tbServiciosAdministr.getColumnModel().getColumn(2).setPreferredWidth(120);
    tbServiciosAdministr.getColumnModel().getColumn(3).setPreferredWidth(120);
    tbServiciosAdministr.getColumnModel().getColumn(4).setPreferredWidth(120);
    tbServiciosAdministr.getColumnModel().getColumn(5).setPreferredWidth(120);
    tbServiciosAdministr.getColumnModel().getColumn(6).setPreferredWidth(120);
    // Servicios Admini- Ocultar    
    tbServiciosAdministr.getColumnModel().getColumn(0).setMinWidth(0);
    tbServiciosAdministr.getColumnModel().getColumn(0).setMaxWidth(0);
    tbServiciosAdministr.getColumnModel().getColumn(1).setMinWidth(0);
    tbServiciosAdministr.getColumnModel().getColumn(1).setMaxWidth(0);
    
    
    //Servicios Generales

    tbServiciosGenerales.getColumnModel().getColumn(2).setPreferredWidth(120);
    tbServiciosGenerales.getColumnModel().getColumn(3).setPreferredWidth(120);
    tbServiciosGenerales.getColumnModel().getColumn(4).setPreferredWidth(120);
    tbServiciosGenerales.getColumnModel().getColumn(5).setPreferredWidth(120);
    tbServiciosGenerales.getColumnModel().getColumn(6).setPreferredWidth(120);
    // Gener- Ocultar    
    tbServiciosGenerales.getColumnModel().getColumn(0).setMinWidth(0);
    tbServiciosGenerales.getColumnModel().getColumn(0).setMaxWidth(0);
    tbServiciosGenerales.getColumnModel().getColumn(1).setMinWidth(0);
    tbServiciosGenerales.getColumnModel().getColumn(1).setMaxWidth(0);
    
    //Servicios Intermedios
    tbServiciosIntermedios.getColumnModel().getColumn(2).setPreferredWidth(120);
    tbServiciosIntermedios.getColumnModel().getColumn(3).setPreferredWidth(120);
    tbServiciosIntermedios.getColumnModel().getColumn(4).setPreferredWidth(120);
    tbServiciosIntermedios.getColumnModel().getColumn(5).setPreferredWidth(120);
    tbServiciosIntermedios.getColumnModel().getColumn(6).setPreferredWidth(120);
    // Interme- Ocultar    
    tbServiciosIntermedios.getColumnModel().getColumn(0).setMinWidth(0);
    tbServiciosIntermedios.getColumnModel().getColumn(0).setMaxWidth(0);
    tbServiciosIntermedios.getColumnModel().getColumn(1).setMinWidth(0);
    tbServiciosIntermedios.getColumnModel().getColumn(1).setMaxWidth(0);
    
    //Resumen
    tbResumenCostos.getColumnModel().getColumn(0).setPreferredWidth(200);
    tbResumenCostos.getColumnModel().getColumn(1).setPreferredWidth(120);
    
// 
//    txtTotalPersonal.setText("0.00");
//        txtTotalInsumos.setText("0.00");
//        txtTotalSBasicos.setText("0.00");
//        txtSumatoriaEnergiaBA.setText("0.00");
//        txtSumatoriaAguaBA.setText("0.00");
//        txtTotalHerramienta.setText("0.00");
//        txtTotalInfraes.setText("0.00");
//        txtTotalSAdminis.setText("0.00");
//        txtTotalSGenerales.setText("0.00");
//        txtTotalSIntermedios.setText("0.00");
//        txtPrecio1.setText("0.00");
//        txtPrecioTotal.setText("0.00");
//        txtGanancia.setText("0.00");
//        txtGananciaPerdida.setText("0.00");
     }
     

     
    public void cargarPrecios_Nomenclatura(){
        
       mbuscar= new DefaultTableModel();
       try{
       mbuscar.addColumn("Codigo de Precio");
       mbuscar.addColumn("Forma de Pago");
       mbuscar.addColumn("Precio");
       mbuscar.addColumn("Codigo CPT");  
       mbuscar.addColumn("Servicio");
       mbuscar.addColumn("Área");
       mbuscar.addColumn("Nomenclatura");
       
       cst=con.prepareCall("{call COSTOS_COSTOS_SUSTENTACION_caja_precios_listarSustentacion}");
       r=cst.executeQuery();
       while (r.next()){
       Object dato[]=new  Object[7];
       for (int i=0; i<7; i++){
           dato[i]=r.getString(i+1);
       }
       mbuscar.addRow(dato);
       }
       this.tbNomenclatura.setModel(mbuscar);
       //formato de la tabla
       formato();
       }catch (Exception e){}
      
    }
    
     public void cargarCostoSustentacion(){
        try {
             String titulos[]={"Codigo","Cod Precio","Codigo CPT","Servicio","Área",
                 "Forma de Pago","Precio","Tiempo(h)","Tiempo(min)","Saldo","Nomenclatura"};
            cs=new DefaultTableModel(null,titulos);
            JTable p=new JTable(cs);
            String fila[]=new String[12];
        String consulta="exec COSTOS_COSTOS_SUSTENTACION_listar";
        ResultSet r;
        r=conectar.Listar(consulta);
        while(r.next()){
            for (int i=0; i<11; i++){
           fila[i]=r.getString(i+1);
       }
                cs.addRow(fila);
            }
        
            tbCostoSusten.setModel(cs);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(cs);
            tbCostoSusten.setRowSorter(elQueOrdena);
            this.tbCostoSusten.setModel(cs);
            formatoCostoSustentacion();
    } catch (Exception e) {
    }
}
    public void formatoCostoSustentacion(){
    tbCostoSusten.getColumnModel().getColumn(0).setPreferredWidth(155);
    tbCostoSusten.getColumnModel().getColumn(1).setPreferredWidth(120);
    tbCostoSusten.getColumnModel().getColumn(2).setPreferredWidth(120);
    tbCostoSusten.getColumnModel().getColumn(3).setPreferredWidth(120);
    tbCostoSusten.getColumnModel().getColumn(4).setPreferredWidth(120);
    tbCostoSusten.getColumnModel().getColumn(5).setPreferredWidth(120);
    tbCostoSusten.getColumnModel().getColumn(6).setPreferredWidth(80);
    tbCostoSusten.getColumnModel().getColumn(7).setPreferredWidth(80);
    tbCostoSusten.getColumnModel().getColumn(8).setPreferredWidth(90);
    tbCostoSusten.getColumnModel().getColumn(9).setPreferredWidth(90);
    tbCostoSusten.getColumnModel().getColumn(10).setPreferredWidth(160);
    //Ocultar    
    tbCostoSusten.getColumnModel().getColumn(1).setMinWidth(0);
    tbCostoSusten.getColumnModel().getColumn(1).setMaxWidth(0);
}
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BuscarNomenclatura = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbNomenclatura = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        Costos_Sustentacion = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        txtBuscarCosto = new javax.swing.JTextField();
        btnBuscarCosto = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        tbCostoSusten = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            lblFechaPersonal = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            lblhPersonal = new javax.swing.JLabel();
            lblUsu = new javax.swing.JLabel();
            btnNuevo = new javax.swing.JButton();
            btnGrabar = new javax.swing.JButton();
            btnModificar = new javax.swing.JButton();
            btnBuscarTodo = new javax.swing.JButton();
            btnEliminar = new javax.swing.JButton();
            pnlCPT = new javax.swing.JPanel();
            jLabel8 = new javax.swing.JLabel();
            txtCodigoCPT = new javax.swing.JTextField();
            jLabel10 = new javax.swing.JLabel();
            txtCodigoSustento = new javax.swing.JTextField();
            btnBuscarCPT = new javax.swing.JButton();
            txtCodigo6 = new javax.swing.JTextField();
            jLabel7 = new javax.swing.JLabel();
            txtPrecio = new javax.swing.JTextField();
            jLabel9 = new javax.swing.JLabel();
            jLabel11 = new javax.swing.JLabel();
            jLabel12 = new javax.swing.JLabel();
            jLabel14 = new javax.swing.JLabel();
            txtServicio = new javax.swing.JTextField();
            jLabel16 = new javax.swing.JLabel();
            txtSubServicio = new javax.swing.JTextField();
            spHora = new javax.swing.JSpinner();
            spMin = new javax.swing.JSpinner();
            jLabel18 = new javax.swing.JLabel();
            txtFormadePago = new javax.swing.JTextField();
            txtCodigoPrecio = new javax.swing.JTextField();
            txtGuarModif = new javax.swing.JTextField();
            jScrollPane9 = new javax.swing.JScrollPane();
            jPanel3 = new javax.swing.JPanel();
            jScrollPane1 = new javax.swing.JScrollPane();
            tbInsumo = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                btnAgregarInsumo = new javax.swing.JButton();
                btnQuitarInsumo = new javax.swing.JButton();
                jScrollPane2 = new javax.swing.JScrollPane();
                tbHerramienta = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    btnAgregarHerramientas = new javax.swing.JButton();
                    btnQuitarHerramienta = new javax.swing.JButton();
                    jScrollPane4 = new javax.swing.JScrollPane();
                    tbResumenCostos = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        jLabel13 = new javax.swing.JLabel();
                        jScrollPane5 = new javax.swing.JScrollPane();
                        tbPersonal = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            btnAgregarPersonal = new javax.swing.JButton();
                            btnQuitarPersonal = new javax.swing.JButton();
                            btnAgregarSAdminis = new javax.swing.JButton();
                            btnQuitarSAdminis = new javax.swing.JButton();
                            jPanel4 = new javax.swing.JPanel();
                            jLabel15 = new javax.swing.JLabel();
                            lblGananciaPer = new javax.swing.JLabel();
                            txtPrecioTotal = new javax.swing.JTextField();
                            txtGananciaPerdida = new javax.swing.JTextField();
                            jLabel19 = new javax.swing.JLabel();
                            txtPrecio1 = new javax.swing.JTextField();
                            txtGanancia = new javax.swing.JTextField();
                            txtTotalPersonal = new javax.swing.JTextField();
                            txtTotalInsumos = new javax.swing.JTextField();
                            txtTotalHerramienta = new javax.swing.JTextField();
                            jScrollPane8 = new javax.swing.JScrollPane();
                            jTextArea2 = new javax.swing.JTextArea();
                            jScrollPane10 = new javax.swing.JScrollPane();
                            jTextArea3 = new javax.swing.JTextArea();
                            jScrollPane11 = new javax.swing.JScrollPane();
                            jTextArea4 = new javax.swing.JTextArea();
                            btnAgregarServiciosGenerales = new javax.swing.JButton();
                            btnQuitarServiciosGenerales = new javax.swing.JButton();
                            jScrollPane15 = new javax.swing.JScrollPane();
                            tbServiciosAdministr = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false; //Disallow the editing of any cell
                                }};
                                jScrollPane16 = new javax.swing.JScrollPane();
                                tbServiciosGenerales = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false; //Disallow the editing of any cell
                                    }};
                                    jScrollPane17 = new javax.swing.JScrollPane();
                                    jTextArea6 = new javax.swing.JTextArea();
                                    jScrollPane18 = new javax.swing.JScrollPane();
                                    tbServiciosIntermedios = new javax.swing.JTable(){
                                        public boolean isCellEditable(int rowIndex, int colIndex){
                                            return false; //Disallow the editing of any cell
                                        }};
                                        jScrollPane12 = new javax.swing.JScrollPane();
                                        jTextArea5 = new javax.swing.JTextArea();
                                        jScrollPane7 = new javax.swing.JScrollPane();
                                        tbServiciosBasicos = new javax.swing.JTable(){
                                            public boolean isCellEditable(int rowIndex, int colIndex){
                                                return false; //Disallow the editing of any cell
                                            }};
                                            btnAgregarHerramientas1 = new javax.swing.JButton();
                                            btnQuitarHerramienta1 = new javax.swing.JButton();
                                            btnAgregarSBasicos = new javax.swing.JButton();
                                            btnQuitarSBasicos = new javax.swing.JButton();
                                            txtTotalSBasicos = new javax.swing.JTextField();
                                            jScrollPane13 = new javax.swing.JScrollPane();
                                            jTextArea7 = new javax.swing.JTextArea();
                                            jScrollPane6 = new javax.swing.JScrollPane();
                                            tbInfraestructura = new javax.swing.JTable(){
                                                public boolean isCellEditable(int rowIndex, int colIndex){
                                                    return false; //Disallow the editing of any cell
                                                }};
                                                btnAgregarInfraestruc = new javax.swing.JButton();
                                                btnQuitarInfraes = new javax.swing.JButton();
                                                txtTotalInfraes = new javax.swing.JTextField();
                                                txtTotalSAdminis = new javax.swing.JTextField();
                                                txtTotalSGenerales = new javax.swing.JTextField();
                                                btnAgregarServiciosInterm = new javax.swing.JButton();
                                                btnQuitarServiciosInterm = new javax.swing.JButton();
                                                txtTotalSIntermedios = new javax.swing.JTextField();
                                                jScrollPane14 = new javax.swing.JScrollPane();
                                                jTextArea8 = new javax.swing.JTextArea();
                                                jScrollPane19 = new javax.swing.JScrollPane();
                                                jTextArea9 = new javax.swing.JTextArea();
                                                txtSumatoriaEnergiaBA = new javax.swing.JTextField();
                                                txtConsumo_ME = new javax.swing.JTextField();
                                                txtSumatoriaAguaBA = new javax.swing.JTextField();
                                                txtConsumo_MA = new javax.swing.JTextField();
                                                jPanel2 = new javax.swing.JPanel();
                                                jLabel17 = new javax.swing.JLabel();
                                                jLabel20 = new javax.swing.JLabel();
                                                jLabel21 = new javax.swing.JLabel();
                                                jLabel22 = new javax.swing.JLabel();
                                                jLabel23 = new javax.swing.JLabel();
                                                jLabel24 = new javax.swing.JLabel();
                                                jScrollPane21 = new javax.swing.JScrollPane();
                                                txtNomenclatura = new javax.swing.JTextArea();

                                                BuscarNomenclatura.setAlwaysOnTop(true);
                                                BuscarNomenclatura.setMinimumSize(new java.awt.Dimension(589, 450));

                                                tbNomenclatura = new javax.swing.JTable(){
                                                    public boolean isCellEditable(int rowIndex, int colIndex) {
                                                        return false; //Disallow the editing of any cell
                                                    }
                                                };
                                                tbNomenclatura.setModel(new javax.swing.table.DefaultTableModel(
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
                                                tbNomenclatura.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                                tbNomenclatura.addKeyListener(new java.awt.event.KeyAdapter() {
                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                        tbNomenclaturaKeyPressed(evt);
                                                    }
                                                });
                                                jScrollPane3.setViewportView(tbNomenclatura);

                                                jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                                                jLabel4.setText("Búsqueda ");

                                                txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
                                                txtBuscar.setText("Ingresar Nomenclatura ");
                                                txtBuscar.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        txtBuscarActionPerformed(evt);
                                                    }
                                                });
                                                txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
                                                    public void keyTyped(java.awt.event.KeyEvent evt) {
                                                        txtBuscarKeyTyped(evt);
                                                    }
                                                });

                                                btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                                                btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnBuscarActionPerformed(evt);
                                                    }
                                                });

                                                javax.swing.GroupLayout BuscarNomenclaturaLayout = new javax.swing.GroupLayout(BuscarNomenclatura.getContentPane());
                                                BuscarNomenclatura.getContentPane().setLayout(BuscarNomenclaturaLayout);
                                                BuscarNomenclaturaLayout.setHorizontalGroup(
                                                    BuscarNomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(BuscarNomenclaturaLayout.createSequentialGroup()
                                                        .addGroup(BuscarNomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(BuscarNomenclaturaLayout.createSequentialGroup()
                                                                .addGap(172, 172, 172)
                                                                .addGroup(BuscarNomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BuscarNomenclaturaLayout.createSequentialGroup()
                                                                        .addGap(57, 57, 57)
                                                                        .addComponent(jLabel4)
                                                                        .addGap(88, 88, 88))
                                                                    .addGroup(BuscarNomenclaturaLayout.createSequentialGroup()
                                                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                            .addGroup(BuscarNomenclaturaLayout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addContainerGap(12, Short.MAX_VALUE))
                                                );
                                                BuscarNomenclaturaLayout.setVerticalGroup(
                                                    BuscarNomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BuscarNomenclaturaLayout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(jLabel4)
                                                        .addGap(18, 18, 18)
                                                        .addGroup(BuscarNomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addContainerGap())
                                                );

                                                Costos_Sustentacion.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                                                Costos_Sustentacion.setTitle("HIMS .::. Búsqueda por Nomenclatura");
                                                Costos_Sustentacion.setAlwaysOnTop(true);
                                                Costos_Sustentacion.setAutoRequestFocus(false);
                                                Costos_Sustentacion.setMinimumSize(new java.awt.Dimension(726, 570));

                                                jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                                                jLabel5.setText("Búsqueda por Nomenclatura");

                                                txtBuscarCosto.setForeground(new java.awt.Color(0, 51, 51));
                                                txtBuscarCosto.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        txtBuscarCostoActionPerformed(evt);
                                                    }
                                                });
                                                txtBuscarCosto.addKeyListener(new java.awt.event.KeyAdapter() {
                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                        txtBuscarCostoKeyPressed(evt);
                                                    }
                                                    public void keyTyped(java.awt.event.KeyEvent evt) {
                                                        txtBuscarCostoKeyTyped(evt);
                                                    }
                                                });

                                                btnBuscarCosto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                                                btnBuscarCosto.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnBuscarCostoActionPerformed(evt);
                                                    }
                                                });

                                                tbCostoSusten.setModel(new javax.swing.table.DefaultTableModel(
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
                                                tbCostoSusten.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                                tbCostoSusten.setRowHeight(25);
                                                tbCostoSusten.addMouseListener(new java.awt.event.MouseAdapter() {
                                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                        tbCostoSustenMouseClicked(evt);
                                                    }
                                                });
                                                tbCostoSusten.addKeyListener(new java.awt.event.KeyAdapter() {
                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                        tbCostoSustenKeyPressed(evt);
                                                    }
                                                    public void keyTyped(java.awt.event.KeyEvent evt) {
                                                        tbCostoSustenKeyTyped(evt);
                                                    }
                                                });
                                                jScrollPane20.setViewportView(tbCostoSusten);

                                                javax.swing.GroupLayout Costos_SustentacionLayout = new javax.swing.GroupLayout(Costos_Sustentacion.getContentPane());
                                                Costos_Sustentacion.getContentPane().setLayout(Costos_SustentacionLayout);
                                                Costos_SustentacionLayout.setHorizontalGroup(
                                                    Costos_SustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(Costos_SustentacionLayout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addGroup(Costos_SustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(Costos_SustentacionLayout.createSequentialGroup()
                                                                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                                                                .addContainerGap())
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Costos_SustentacionLayout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addGroup(Costos_SustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Costos_SustentacionLayout.createSequentialGroup()
                                                                        .addComponent(txtBuscarCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(btnBuscarCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(225, 225, 225))
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Costos_SustentacionLayout.createSequentialGroup()
                                                                        .addComponent(jLabel5)
                                                                        .addGap(198, 198, 198))))))
                                                );
                                                Costos_SustentacionLayout.setVerticalGroup(
                                                    Costos_SustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(Costos_SustentacionLayout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(jLabel5)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(Costos_SustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtBuscarCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(btnBuscarCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(22, 22, 22)
                                                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addContainerGap(49, Short.MAX_VALUE))
                                                );

                                                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                                                setTitle("FACTORES DE PRODUCCIÓN");
                                                setResizable(false);

                                                jPanel1.setBackground(new java.awt.Color(102, 102, 102));

                                                jLabel1.setBackground(new java.awt.Color(255, 255, 255));
                                                jLabel1.setFont(new java.awt.Font("Palatino Linotype", 1, 30)); // NOI18N
                                                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                                                jLabel1.setText("Factores de Producción");

                                                jLabel2.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
                                                jLabel2.setForeground(new java.awt.Color(255, 255, 255));
                                                jLabel2.setText("Fecha:");

                                                lblFechaPersonal.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
                                                lblFechaPersonal.setForeground(new java.awt.Color(255, 255, 255));
                                                lblFechaPersonal.setText("jLabel4");

                                                jLabel3.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
                                                jLabel3.setForeground(new java.awt.Color(255, 255, 255));
                                                jLabel3.setText("Hora:");

                                                lblhPersonal.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
                                                lblhPersonal.setForeground(new java.awt.Color(255, 255, 255));
                                                lblhPersonal.setText("jLabel4");

                                                lblUsu.setBackground(new java.awt.Color(255, 255, 255));
                                                lblUsu.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
                                                lblUsu.setForeground(new java.awt.Color(255, 255, 255));
                                                lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
                                                lblUsu.setText("Usuario");

                                                btnNuevo.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
                                                btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
                                                btnNuevo.setMnemonic('N');
                                                btnNuevo.setToolTipText("Nuevo (Alt+N)");
                                                btnNuevo.setContentAreaFilled(false);
                                                btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnNuevoActionPerformed(evt);
                                                    }
                                                });

                                                btnGrabar.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
                                                btnGrabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
                                                btnGrabar.setMnemonic('G');
                                                btnGrabar.setToolTipText("Guardar (Alt+G)");
                                                btnGrabar.setContentAreaFilled(false);
                                                btnGrabar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                btnGrabar.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnGrabarActionPerformed(evt);
                                                    }
                                                });

                                                btnModificar.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
                                                btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
                                                btnModificar.setMnemonic('M');
                                                btnModificar.setToolTipText("Modificar (Alt+M)");
                                                btnModificar.setContentAreaFilled(false);
                                                btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                btnModificar.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnModificarActionPerformed(evt);
                                                    }
                                                });

                                                btnBuscarTodo.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
                                                btnBuscarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
                                                btnBuscarTodo.setMnemonic('B');
                                                btnBuscarTodo.setToolTipText("Buscar (Alt+B)");
                                                btnBuscarTodo.setContentAreaFilled(false);
                                                btnBuscarTodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                btnBuscarTodo.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnBuscarTodoActionPerformed(evt);
                                                    }
                                                });

                                                btnEliminar.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
                                                btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
                                                btnEliminar.setMnemonic('E');
                                                btnEliminar.setToolTipText("Eliminar (Alt+E)");
                                                btnEliminar.setContentAreaFilled(false);
                                                btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnEliminarActionPerformed(evt);
                                                    }
                                                });

                                                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                                                jPanel1.setLayout(jPanel1Layout);
                                                jPanel1Layout.setHorizontalGroup(
                                                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel1)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(54, 54, 54)
                                                                .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btnBuscarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(88, 88, 88)
                                                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 865, Short.MAX_VALUE)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(lblUsu)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel2)
                                                                    .addComponent(jLabel3))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(lblhPersonal)
                                                                    .addComponent(lblFechaPersonal))))
                                                        .addContainerGap())
                                                );
                                                jPanel1Layout.setVerticalGroup(
                                                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(jLabel2)
                                                                    .addComponent(lblFechaPersonal))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(jLabel3)
                                                                    .addComponent(lblhPersonal))
                                                                .addGap(7, 7, 7)
                                                                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(btnBuscarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                                                );

                                                pnlCPT.setBackground(new java.awt.Color(255, 255, 255));
                                                pnlCPT.setBorder(javax.swing.BorderFactory.createEtchedBorder());

                                                jLabel8.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
                                                jLabel8.setText("Código CPT:");

                                                txtCodigoCPT.setEditable(false);
                                                txtCodigoCPT.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                                                txtCodigoCPT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                txtCodigoCPT.setAlignmentX(10.0F);

                                                jLabel10.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
                                                jLabel10.setText("Registro de Costos:");

                                                txtCodigoSustento.setEditable(false);

                                                btnBuscarCPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                                                btnBuscarCPT.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnBuscarCPTActionPerformed(evt);
                                                    }
                                                });

                                                txtCodigo6.setEditable(false);
                                                txtCodigo6.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                                                txtCodigo6.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                                                jLabel7.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
                                                jLabel7.setText("Tiempo:");

                                                txtPrecio.setEditable(false);
                                                txtPrecio.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                                                txtPrecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                txtPrecio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                                                jLabel9.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
                                                jLabel9.setText("Precio:");

                                                jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                jLabel11.setText("hora");

                                                jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                jLabel12.setText("min");

                                                jLabel14.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
                                                jLabel14.setText("Servicio:");

                                                txtServicio.setEditable(false);
                                                txtServicio.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                                                txtServicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                                                jLabel16.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
                                                jLabel16.setText("Área:");

                                                txtSubServicio.setEditable(false);
                                                txtSubServicio.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
                                                txtSubServicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                                                spHora.setModel(new javax.swing.SpinnerNumberModel(0, 0, 24, 1));
                                                spHora.addChangeListener(new javax.swing.event.ChangeListener() {
                                                    public void stateChanged(javax.swing.event.ChangeEvent evt) {
                                                        spHoraStateChanged(evt);
                                                    }
                                                });

                                                spMin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 60, 1));
                                                spMin.addChangeListener(new javax.swing.event.ChangeListener() {
                                                    public void stateChanged(javax.swing.event.ChangeEvent evt) {
                                                        spMinStateChanged(evt);
                                                    }
                                                });
                                                spMin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                                                    public void propertyChange(java.beans.PropertyChangeEvent evt) {
                                                        spMinPropertyChange(evt);
                                                    }
                                                });

                                                jLabel18.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
                                                jLabel18.setText("Forma de Pago:");

                                                txtFormadePago.setEditable(false);
                                                txtFormadePago.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                                                txtFormadePago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                                txtFormadePago.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                                                txtCodigoPrecio.setEditable(false);

                                                txtGuarModif.setText("G");

                                                javax.swing.GroupLayout pnlCPTLayout = new javax.swing.GroupLayout(pnlCPT);
                                                pnlCPT.setLayout(pnlCPTLayout);
                                                pnlCPTLayout.setHorizontalGroup(
                                                    pnlCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pnlCPTLayout.createSequentialGroup()
                                                        .addGap(26, 26, 26)
                                                        .addGroup(pnlCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(spHora, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                                            .addComponent(spMin))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(pnlCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel12)
                                                            .addComponent(jLabel11))
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                    .addGroup(pnlCPTLayout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addGroup(pnlCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtFormadePago, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addContainerGap())
                                                    .addGroup(pnlCPTLayout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addGroup(pnlCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(pnlCPTLayout.createSequentialGroup()
                                                                .addComponent(jLabel7)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                            .addGroup(pnlCPTLayout.createSequentialGroup()
                                                                .addGroup(pnlCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(pnlCPTLayout.createSequentialGroup()
                                                                        .addGroup(pnlCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addGroup(pnlCPTLayout.createSequentialGroup()
                                                                                .addComponent(jLabel8)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addComponent(txtCodigo6)
                                                                            .addComponent(txtCodigoCPT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                            .addComponent(txtServicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCPTLayout.createSequentialGroup()
                                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                                        .addGroup(pnlCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCPTLayout.createSequentialGroup()
                                                                                .addComponent(txtCodigoSustento, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addGroup(pnlCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                    .addComponent(txtGuarModif, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                                                                    .addComponent(txtCodigoPrecio)))
                                                                            .addComponent(txtSubServicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addContainerGap())))
                                                );
                                                pnlCPTLayout.setVerticalGroup(
                                                    pnlCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCPTLayout.createSequentialGroup()
                                                        .addGroup(pnlCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(txtCodigoSustento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtCodigoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtGuarModif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel10)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtCodigo6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(24, 24, 24)
                                                        .addGroup(pnlCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(btnBuscarCPT)
                                                            .addComponent(jLabel8))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtCodigoCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(24, 24, 24)
                                                        .addComponent(jLabel14)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(25, 25, 25)
                                                        .addComponent(jLabel16)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtSubServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(24, 24, 24)
                                                        .addComponent(jLabel18)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtFormadePago, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(24, 24, 24)
                                                        .addComponent(jLabel9)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel7)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(pnlCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(spHora)
                                                            .addComponent(jLabel11))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(pnlCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(spMin)
                                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(13, 13, 13))
                                                );

                                                jScrollPane9.setPreferredSize(new java.awt.Dimension(1185, 900));

                                                jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                                                jPanel3.setPreferredSize(new java.awt.Dimension(1100, 900));

                                                tbInsumo.setModel(new javax.swing.table.DefaultTableModel(
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
                                                tbInsumo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                                jScrollPane1.setViewportView(tbInsumo);

                                                btnAgregarInsumo.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnAgregarInsumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
                                                btnAgregarInsumo.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnAgregarInsumoActionPerformed(evt);
                                                    }
                                                });

                                                btnQuitarInsumo.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnQuitarInsumo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
                                                btnQuitarInsumo.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnQuitarInsumoActionPerformed(evt);
                                                    }
                                                });

                                                tbHerramienta.setModel(new javax.swing.table.DefaultTableModel(
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
                                                tbHerramienta.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                                tbHerramienta.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                                                    public void propertyChange(java.beans.PropertyChangeEvent evt) {
                                                        tbHerramientaPropertyChange(evt);
                                                    }
                                                });
                                                jScrollPane2.setViewportView(tbHerramienta);

                                                btnAgregarHerramientas.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnAgregarHerramientas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
                                                btnAgregarHerramientas.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnAgregarHerramientasActionPerformed(evt);
                                                    }
                                                });

                                                btnQuitarHerramienta.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnQuitarHerramienta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
                                                btnQuitarHerramienta.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnQuitarHerramientaActionPerformed(evt);
                                                    }
                                                });

                                                tbResumenCostos.setModel(new javax.swing.table.DefaultTableModel(
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
                                                tbResumenCostos.setRowHeight(22);
                                                jScrollPane4.setViewportView(tbResumenCostos);

                                                jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                                                jLabel13.setText("RESUMEN COSTOS");

                                                tbPersonal.setModel(new javax.swing.table.DefaultTableModel(
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
                                                tbPersonal.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                                tbPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
                                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                                        tbPersonalKeyPressed(evt);
                                                    }
                                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                                        tbPersonalKeyReleased(evt);
                                                    }
                                                });
                                                jScrollPane5.setViewportView(tbPersonal);

                                                btnAgregarPersonal.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnAgregarPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
                                                btnAgregarPersonal.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnAgregarPersonalActionPerformed(evt);
                                                    }
                                                });

                                                btnQuitarPersonal.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnQuitarPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
                                                btnQuitarPersonal.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnQuitarPersonalActionPerformed(evt);
                                                    }
                                                });

                                                btnAgregarSAdminis.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnAgregarSAdminis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
                                                btnAgregarSAdminis.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnAgregarSAdminisActionPerformed(evt);
                                                    }
                                                });

                                                btnQuitarSAdminis.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnQuitarSAdminis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
                                                btnQuitarSAdminis.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnQuitarSAdminisActionPerformed(evt);
                                                    }
                                                });

                                                jPanel4.setBackground(new java.awt.Color(255, 255, 255));
                                                jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumen"));

                                                jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                                                jLabel15.setText("Estudio del Costo:");

                                                lblGananciaPer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                                                lblGananciaPer.setText("Ganancia Total:");

                                                txtPrecioTotal.setEditable(false);
                                                txtPrecioTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                                                txtPrecioTotal.setForeground(new java.awt.Color(0, 0, 51));
                                                txtPrecioTotal.setText("0");
                                                txtPrecioTotal.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        txtPrecioTotalActionPerformed(evt);
                                                    }
                                                });

                                                txtGananciaPerdida.setEditable(false);
                                                txtGananciaPerdida.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                                                txtGananciaPerdida.setForeground(new java.awt.Color(0, 0, 51));
                                                txtGananciaPerdida.setText("0");
                                                txtGananciaPerdida.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        txtGananciaPerdidaActionPerformed(evt);
                                                    }
                                                });

                                                jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                                                jLabel19.setText("Se Cobra:");

                                                txtPrecio1.setEditable(false);
                                                txtPrecio1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                                                txtPrecio1.setForeground(new java.awt.Color(0, 0, 51));
                                                txtPrecio1.setText("0");
                                                txtPrecio1.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        txtPrecio1ActionPerformed(evt);
                                                    }
                                                });

                                                txtGanancia.setEditable(false);
                                                txtGanancia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
                                                txtGanancia.setForeground(new java.awt.Color(0, 0, 51));
                                                txtGanancia.setText("0");
                                                txtGanancia.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        txtGananciaActionPerformed(evt);
                                                    }
                                                });

                                                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                                                jPanel4.setLayout(jPanel4Layout);
                                                jPanel4Layout.setHorizontalGroup(
                                                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addGap(29, 29, 29)
                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(lblGananciaPer)
                                                            .addComponent(jLabel15)
                                                            .addComponent(jLabel19))
                                                        .addGap(33, 33, 33)
                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(txtPrecio1)
                                                            .addComponent(txtGananciaPerdida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                                            .addComponent(txtPrecioTotal, javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtGanancia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                                                        .addContainerGap(34, Short.MAX_VALUE))
                                                );
                                                jPanel4Layout.setVerticalGroup(
                                                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jLabel19)
                                                            .addComponent(txtPrecio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(21, 21, 21)
                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jLabel15)
                                                            .addComponent(txtPrecioTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(21, 21, 21)
                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(lblGananciaPer)
                                                            .addComponent(txtGananciaPerdida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                                        .addComponent(txtGanancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                );

                                                txtTotalHerramienta.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        txtTotalHerramientaActionPerformed(evt);
                                                    }
                                                });

                                                jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                                                jTextArea2.setColumns(20);
                                                jTextArea2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                                                jTextArea2.setRows(5);
                                                jTextArea2.setText("\n\nEQUIPA-\nMIENTO\nBÁSICO");
                                                jTextArea2.setWrapStyleWord(true);
                                                jTextArea2.setAlignmentX(0.0F);
                                                jTextArea2.setAlignmentY(0.0F);
                                                jTextArea2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                                                jTextArea2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
                                                jTextArea2.setEnabled(false);
                                                jScrollPane8.setViewportView(jTextArea2);

                                                jScrollPane10.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                                                jTextArea3.setColumns(20);
                                                jTextArea3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                                                jTextArea3.setRows(5);
                                                jTextArea3.setText("\n\nSERVICIOS \nADMINIS-\nTRATIVOS");
                                                jTextArea3.setWrapStyleWord(true);
                                                jTextArea3.setAlignmentX(0.0F);
                                                jTextArea3.setAlignmentY(0.0F);
                                                jTextArea3.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                                                jTextArea3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
                                                jTextArea3.setEnabled(false);
                                                jScrollPane10.setViewportView(jTextArea3);

                                                jScrollPane11.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                                                jTextArea4.setColumns(20);
                                                jTextArea4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                                                jTextArea4.setRows(5);
                                                jTextArea4.setText("\n\nSERVICIOS \nGENERALES");
                                                jTextArea4.setWrapStyleWord(true);
                                                jTextArea4.setAlignmentX(0.0F);
                                                jTextArea4.setAlignmentY(0.0F);
                                                jTextArea4.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                                                jTextArea4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
                                                jTextArea4.setEnabled(false);
                                                jScrollPane11.setViewportView(jTextArea4);

                                                btnAgregarServiciosGenerales.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnAgregarServiciosGenerales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
                                                btnAgregarServiciosGenerales.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnAgregarServiciosGeneralesActionPerformed(evt);
                                                    }
                                                });

                                                btnQuitarServiciosGenerales.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnQuitarServiciosGenerales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
                                                btnQuitarServiciosGenerales.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnQuitarServiciosGeneralesActionPerformed(evt);
                                                    }
                                                });

                                                tbServiciosAdministr.setModel(new javax.swing.table.DefaultTableModel(
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
                                                tbServiciosAdministr.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                                tbServiciosAdministr.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                                                    public void propertyChange(java.beans.PropertyChangeEvent evt) {
                                                        tbServiciosAdministrPropertyChange(evt);
                                                    }
                                                });
                                                jScrollPane15.setViewportView(tbServiciosAdministr);

                                                tbServiciosGenerales.setModel(new javax.swing.table.DefaultTableModel(
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
                                                tbServiciosGenerales.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                                tbServiciosGenerales.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                                                    public void propertyChange(java.beans.PropertyChangeEvent evt) {
                                                        tbServiciosGeneralesPropertyChange(evt);
                                                    }
                                                });
                                                jScrollPane16.setViewportView(tbServiciosGenerales);

                                                jScrollPane17.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                                                jTextArea6.setColumns(20);
                                                jTextArea6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                                                jTextArea6.setRows(5);
                                                jTextArea6.setText("\n\nSERVICIOS \nINTERME-\nDIOS");
                                                jTextArea6.setWrapStyleWord(true);
                                                jTextArea6.setAlignmentX(0.0F);
                                                jTextArea6.setAlignmentY(0.0F);
                                                jTextArea6.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                                                jTextArea6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
                                                jTextArea6.setEnabled(false);
                                                jScrollPane17.setViewportView(jTextArea6);

                                                tbServiciosIntermedios.setModel(new javax.swing.table.DefaultTableModel(
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
                                                tbServiciosIntermedios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                                tbServiciosIntermedios.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                                                    public void propertyChange(java.beans.PropertyChangeEvent evt) {
                                                        tbServiciosIntermediosPropertyChange(evt);
                                                    }
                                                });
                                                jScrollPane18.setViewportView(tbServiciosIntermedios);

                                                jScrollPane12.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                                                jTextArea5.setColumns(20);
                                                jTextArea5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                                                jTextArea5.setRows(5);
                                                jTextArea5.setText("\n\nSERVICIOS\nBÁSICOS");
                                                jTextArea5.setWrapStyleWord(true);
                                                jTextArea5.setAlignmentX(0.0F);
                                                jTextArea5.setAlignmentY(0.0F);
                                                jTextArea5.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                                                jTextArea5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
                                                jTextArea5.setEnabled(false);
                                                jScrollPane12.setViewportView(jTextArea5);

                                                tbServiciosBasicos.setModel(new javax.swing.table.DefaultTableModel(
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
                                                tbServiciosBasicos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                                jScrollPane7.setViewportView(tbServiciosBasicos);

                                                btnAgregarHerramientas1.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnAgregarHerramientas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
                                                btnAgregarHerramientas1.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnAgregarHerramientas1ActionPerformed(evt);
                                                    }
                                                });

                                                btnQuitarHerramienta1.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnQuitarHerramienta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
                                                btnQuitarHerramienta1.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnQuitarHerramienta1ActionPerformed(evt);
                                                    }
                                                });

                                                btnAgregarSBasicos.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnAgregarSBasicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
                                                btnAgregarSBasicos.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnAgregarSBasicosActionPerformed(evt);
                                                    }
                                                });

                                                btnQuitarSBasicos.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnQuitarSBasicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
                                                btnQuitarSBasicos.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnQuitarSBasicosActionPerformed(evt);
                                                    }
                                                });

                                                jScrollPane13.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                                                jTextArea7.setColumns(20);
                                                jTextArea7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                                                jTextArea7.setRows(5);
                                                jTextArea7.setText("\n\nINFRAES-\nTRUCTURA");
                                                jTextArea7.setWrapStyleWord(true);
                                                jTextArea7.setAlignmentX(0.0F);
                                                jTextArea7.setAlignmentY(0.0F);
                                                jTextArea7.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                                                jTextArea7.setDisabledTextColor(new java.awt.Color(0, 0, 0));
                                                jTextArea7.setEnabled(false);
                                                jScrollPane13.setViewportView(jTextArea7);

                                                tbInfraestructura.setModel(new javax.swing.table.DefaultTableModel(
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
                                                tbInfraestructura.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                                tbInfraestructura.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                                                    public void propertyChange(java.beans.PropertyChangeEvent evt) {
                                                        tbInfraestructuraPropertyChange(evt);
                                                    }
                                                });
                                                jScrollPane6.setViewportView(tbInfraestructura);

                                                btnAgregarInfraestruc.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnAgregarInfraestruc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
                                                btnAgregarInfraestruc.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnAgregarInfraestrucActionPerformed(evt);
                                                    }
                                                });

                                                btnQuitarInfraes.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnQuitarInfraes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
                                                btnQuitarInfraes.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnQuitarInfraesActionPerformed(evt);
                                                    }
                                                });

                                                txtTotalInfraes.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        txtTotalInfraesActionPerformed(evt);
                                                    }
                                                });

                                                txtTotalSAdminis.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        txtTotalSAdminisActionPerformed(evt);
                                                    }
                                                });

                                                txtTotalSGenerales.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        txtTotalSGeneralesActionPerformed(evt);
                                                    }
                                                });

                                                btnAgregarServiciosInterm.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnAgregarServiciosInterm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
                                                btnAgregarServiciosInterm.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnAgregarServiciosIntermActionPerformed(evt);
                                                    }
                                                });

                                                btnQuitarServiciosInterm.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
                                                btnQuitarServiciosInterm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
                                                btnQuitarServiciosInterm.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        btnQuitarServiciosIntermActionPerformed(evt);
                                                    }
                                                });

                                                txtTotalSIntermedios.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        txtTotalSIntermediosActionPerformed(evt);
                                                    }
                                                });

                                                jScrollPane14.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                                                jTextArea8.setColumns(20);
                                                jTextArea8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                                                jTextArea8.setRows(5);
                                                jTextArea8.setText("\n\nRECURSOS\nHUMANOS");
                                                jTextArea8.setWrapStyleWord(true);
                                                jTextArea8.setAlignmentX(0.0F);
                                                jTextArea8.setAlignmentY(0.0F);
                                                jTextArea8.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                                                jTextArea8.setDisabledTextColor(new java.awt.Color(0, 0, 0));
                                                jTextArea8.setEnabled(false);
                                                jScrollPane14.setViewportView(jTextArea8);

                                                jScrollPane19.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

                                                jTextArea9.setColumns(20);
                                                jTextArea9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                                                jTextArea9.setRows(5);
                                                jTextArea9.setText("\n\n\nINSUMOS");
                                                jTextArea9.setWrapStyleWord(true);
                                                jTextArea9.setAlignmentX(0.0F);
                                                jTextArea9.setAlignmentY(0.0F);
                                                jTextArea9.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                                                jTextArea9.setDisabledTextColor(new java.awt.Color(0, 0, 0));
                                                jTextArea9.setEnabled(false);
                                                jScrollPane19.setViewportView(jTextArea9);

                                                txtSumatoriaEnergiaBA.setText("0");

                                                txtConsumo_ME.addActionListener(new java.awt.event.ActionListener() {
                                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                        txtConsumo_MEActionPerformed(evt);
                                                    }
                                                });

                                                txtSumatoriaAguaBA.setText("0");

                                                jPanel2.setBackground(new java.awt.Color(204, 204, 204));

                                                jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
                                                jLabel17.setText("Salir (Esc)");

                                                jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Document-16.png"))); // NOI18N
                                                jLabel20.setText("Nuevo (Alt+N)");

                                                jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/guardar16x16.png"))); // NOI18N
                                                jLabel21.setText("Guardar (Alt+G)");

                                                jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/editar.png"))); // NOI18N
                                                jLabel22.setText("Modificar (Alt+M)");

                                                jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/eliminar16x16.png"))); // NOI18N
                                                jLabel23.setText("Eliminar (Alt+E)");

                                                jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                                jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                                                jLabel24.setText("Buscar (Alt+B)");

                                                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                                                jPanel2.setLayout(jPanel2Layout);
                                                jPanel2Layout.setHorizontalGroup(
                                                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel20)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel21)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel22)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel23)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel24)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel17)
                                                        .addContainerGap())
                                                );
                                                jPanel2Layout.setVerticalGroup(
                                                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                        .addGap(5, 5, 5)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jLabel17)
                                                            .addComponent(jLabel20)
                                                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGap(14, 14, 14))
                                                );

                                                txtNomenclatura.setColumns(20);
                                                txtNomenclatura.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                                txtNomenclatura.setRows(5);
                                                txtNomenclatura.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                                                txtNomenclatura.setEnabled(false);
                                                jScrollPane21.setViewportView(txtNomenclatura);

                                                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                                                jPanel3.setLayout(jPanel3Layout);
                                                jPanel3Layout.setHorizontalGroup(
                                                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, 0)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                                                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                                                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                                                                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                                                                    .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                                                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                                                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)))
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                            .addComponent(btnAgregarInfraestruc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE)
                                                                            .addComponent(btnQuitarInfraes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                                            .addComponent(btnQuitarPersonal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                            .addComponent(btnAgregarServiciosGenerales, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                                            .addComponent(btnQuitarServiciosGenerales, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(btnQuitarSAdminis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(btnAgregarSAdminis, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                            .addComponent(btnAgregarServiciosInterm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                                            .addComponent(btnQuitarServiciosInterm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addComponent(btnQuitarHerramienta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(btnAgregarHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                    .addComponent(btnAgregarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(txtTotalHerramienta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(txtTotalInfraes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(txtTotalSAdminis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(txtTotalPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(txtTotalSGenerales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(txtTotalSIntermedios, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(btnAgregarSBasicos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtTotalSBasicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(btnQuitarSBasicos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addComponent(txtSumatoriaEnergiaBA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(txtConsumo_ME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addComponent(txtSumatoriaAguaBA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(txtConsumo_MA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                    .addComponent(btnQuitarInsumo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE)
                                                                    .addComponent(btnAgregarInsumo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(txtTotalInsumos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                                .addGap(58, 58, 58)
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                                        .addComponent(jLabel13)
                                                                        .addGap(150, 150, 150))
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(43, 43, 43))))
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGap(56, 56, 56)
                                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(23, 23, 23))))
                                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                            .addContainerGap(709, Short.MAX_VALUE)
                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(btnQuitarHerramienta1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                                .addComponent(btnAgregarHerramientas1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                            .addGap(459, 459, 459)))
                                                );
                                                jPanel3Layout.setVerticalGroup(
                                                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                    .addGap(25, 25, 25)
                                                                    .addComponent(jLabel13)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(54, 54, 54)
                                                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                            .addComponent(btnAgregarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(5, 5, 5)
                                                                            .addComponent(btnQuitarPersonal))
                                                                        .addComponent(txtTotalPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                            .addComponent(btnAgregarInsumo)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(btnQuitarInsumo))
                                                                        .addComponent(txtTotalInsumos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addGap(0, 0, 0)
                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                    .addGap(110, 110, 110)
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(btnAgregarInfraestruc)
                                                                                        .addComponent(txtTotalInfraes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(btnQuitarInfraes)
                                                                                    .addGap(40, 40, 40)
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(btnAgregarSAdminis)
                                                                                        .addComponent(txtTotalSAdminis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(btnQuitarSAdminis)
                                                                                    .addGap(31, 31, 31)
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(btnAgregarServiciosGenerales)
                                                                                        .addComponent(txtTotalSGenerales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(btnQuitarServiciosGenerales)
                                                                                    .addGap(38, 38, 38)
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(btnAgregarServiciosInterm)
                                                                                        .addComponent(txtTotalSIntermedios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(btnQuitarServiciosInterm))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(btnAgregarHerramientas)
                                                                                                .addComponent(txtTotalHerramienta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                            .addComponent(btnQuitarHerramienta))
                                                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                    .addGap(0, 0, 0)
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                    .addGap(0, 0, 0)
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addComponent(txtTotalSBasicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(7, 7, 7)
                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(txtSumatoriaEnergiaBA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addComponent(txtConsumo_ME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(txtSumatoriaAguaBA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addComponent(txtConsumo_MA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                                .addComponent(btnAgregarSBasicos)
                                                                                .addGap(7, 7, 7)
                                                                                .addComponent(btnQuitarSBasicos))))))
                                                            .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                            .addGap(326, 326, 326)
                                                            .addComponent(btnAgregarHerramientas1)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(btnQuitarHerramienta1)
                                                            .addContainerGap(525, Short.MAX_VALUE)))
                                                );

                                                jScrollPane9.setViewportView(jPanel3);

                                                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                                getContentPane().setLayout(layout);
                                                layout.setHorizontalGroup(
                                                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(4, 4, 4)
                                                        .addComponent(pnlCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1187, Short.MAX_VALUE)
                                                        .addContainerGap())
                                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                );
                                                layout.setVerticalGroup(
                                                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
                                                                .addGap(56, 56, 56))
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addComponent(pnlCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                );

                                                getAccessibleContext().setAccessibleName("MÓDULO DE COSTOS");

                                                pack();
                                            }// </editor-fold>//GEN-END:initComponents

    
    private void tbNomenclaturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNomenclaturaKeyPressed
       char teclaPresionada = evt.getKeyChar();
       if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tbNomenclatura.getSelectedRow();
              /*TipoSustentacion TS = new TipoSustentacion();
                TS.setVisible(true);
                dispose();*/
             txtCodigoPrecio.setText(String.valueOf(tbNomenclatura.getValueAt(fila, 0))); 
             txtCodigoCPT.setText(String.valueOf(tbNomenclatura.getValueAt(fila, 3))); 
             txtServicio.setText(String.valueOf(tbNomenclatura.getValueAt(fila, 4)));
             txtSubServicio.setText(String.valueOf(tbNomenclatura.getValueAt(fila, 5)));
             txtFormadePago.setText(String.valueOf(tbNomenclatura.getValueAt(fila, 1)));
             txtPrecio.setText(String.valueOf(tbNomenclatura.getValueAt(fila, 2)));
             txtNomenclatura.setText(String.valueOf(tbNomenclatura.getValueAt(fila, 6)));
             BuscarNomenclatura.setVisible(false);
       } 
       
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNomenclaturaKeyPressed

    private void txtGananciaPerdidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGananciaPerdidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGananciaPerdidaActionPerformed

    private void txtPrecioTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioTotalActionPerformed

    private void btnQuitarSAdminisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarSAdminisActionPerformed
        // TODO add your handling code here:
        try{
            int filaselec=tbServiciosAdministr.getSelectedRow();
            if( filaselec>=0){
                int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea QUITAR el Servicio?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(eliminar == 0 ){
                    quitarResumenCostoSAdmin(filaselec);
                    DefaultTableModel modelo = (DefaultTableModel)tbServiciosAdministr.getModel();
                    modelo.removeRow(filaselec);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione el Servicio Administrativo a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Detalle a eliminar");
        }
    }//GEN-LAST:event_btnQuitarSAdminisActionPerformed

    private void btnAgregarSAdminisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSAdminisActionPerformed
        // TODO add your handling code here:
        if(txtCodigoCPT.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Seleccione una Nomenclatura");
        }else if(spHora.getValue().equals(0) && spMin.getValue().equals(0)){
            JOptionPane.showMessageDialog(this, "Ingrese el Tiempo el Tiempo de la Nomenclatura.");
        }
        else{
        Costos_Sustentacion_Detalle_Servicios_Adminis CS = new Costos_Sustentacion_Detalle_Servicios_Adminis();
        CS.setVisible(true);
        String u=lblUsu.getText();
        Costos_Sustentacion_Detalle_Servicios_Adminis.lblUsu.setText(u);
        }
    }//GEN-LAST:event_btnAgregarSAdminisActionPerformed

    private void btnQuitarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarPersonalActionPerformed
        // TODO add your handling code here:
        try{
            int filaselec=tbPersonal.getSelectedRow();
            if( filaselec>=0){
                int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea QUITAR al Personal?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(eliminar == 0 ){
                    quitarResumenCostoPersonal(filaselec);
                    DefaultTableModel modelo = (DefaultTableModel)tbPersonal.getModel();
                    modelo.removeRow(filaselec);
                    
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione el Personal a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Detalle a eliminar");
        }
    }//GEN-LAST:event_btnQuitarPersonalActionPerformed

    private void btnAgregarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPersonalActionPerformed
        // TODO add your handling code here:
        if(txtCodigoCPT.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Seleccione una Nomenclatura");
        }
       else if(spHora.getValue().equals(0) && spMin.getValue().equals(0)){
            JOptionPane.showMessageDialog(this, "Ingrese el Tiempo de la Nomenclatura.");
        }
        else{
        Costos_Sustentacion_Detalle_Personal CS = new Costos_Sustentacion_Detalle_Personal();
        CS.setVisible(true);
        String u=lblUsu.getText();
        Costos_Sustentacion_Detalle_Personal.lblUsu.setText(u);
        }
    }//GEN-LAST:event_btnAgregarPersonalActionPerformed

    private void tbPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPersonalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPersonalKeyReleased

    private void btnQuitarHerramientaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarHerramientaActionPerformed
        // TODO add your handling code here:
        try{
            int filaselec=tbHerramienta.getSelectedRow();
            if( filaselec>=0){
                int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea QUITAR el Producto?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(eliminar == 0 ){
                    quitarResumenCostoHerramienta(filaselec);
                    DefaultTableModel modelo = (DefaultTableModel)tbHerramienta.getModel();
                    modelo.removeRow(filaselec);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione el Producto a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Detalle a eliminar");
        }
    }//GEN-LAST:event_btnQuitarHerramientaActionPerformed

    private void btnAgregarHerramientasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarHerramientasActionPerformed
if(txtCodigoCPT.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Seleccione una Nomenclatura");
        }else if(spHora.getValue().equals(0) && spMin.getValue().equals(0)){
            JOptionPane.showMessageDialog(this, "Ingrese el Tiempo de la Nomenclatura.");
        }
        else{
        Costos_Sustentacion_Detalle_Herramientas csd=new Costos_Sustentacion_Detalle_Herramientas();
        csd.setVisible(true);
        String u=lblUsu.getText();
        Costos_Sustentacion_Detalle_Herramientas.lblUsu.setText(u);
}
    }//GEN-LAST:event_btnAgregarHerramientasActionPerformed

    private void tbHerramientaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbHerramientaPropertyChange
        // TODO add your handling code here:
        if(tbHerramienta.getRowCount()>1 && tbHerramienta.getSelectedRow()!=-1){
            JOptionPane.showMessageDialog(rootPane, "dgf");
        }
    }//GEN-LAST:event_tbHerramientaPropertyChange

    private void btnQuitarInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarInsumoActionPerformed
        // TODO add your handling code here:
        try{
            int filaselec=tbInsumo.getSelectedRow();
            if( filaselec>=0){
                int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea QUITAR el Producto?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(eliminar == 0 ){
                    quitarResumenCostoInsumo(filaselec);
                    DefaultTableModel modelo = (DefaultTableModel)tbInsumo.getModel();
                    modelo.removeRow(filaselec);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione el Insumo a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Detalle a eliminar");
        }
    }//GEN-LAST:event_btnQuitarInsumoActionPerformed

    private void btnAgregarInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarInsumoActionPerformed
if(txtCodigoCPT.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Seleccione una Nomenclatura");
        }else if(spHora.getValue().equals(0) && spMin.getValue().equals(0)){
            JOptionPane.showMessageDialog(this, "Ingrese el Tiempo de la Nomenclatura.");
        }
        else{
        Costos_Sustentacion_Detalle_Insumo CS = new Costos_Sustentacion_Detalle_Insumo();
        CS.setVisible(true);
        String u=lblUsu.getText();
        Costos_Sustentacion_Detalle_Insumo.lblUsu.setText(u);
}
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarInsumoActionPerformed

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
    ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png")); 

      try{        
          if(txtGuarModif.getText().equalsIgnoreCase("G")){
            if(txtCodigoCPT.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Busque el Código CPT");
         }  
          else{
              int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
              if(guardar == 0 ){
                   CostosSustentacion rcGUARDAR=new CostosSustentacion();
                   
                   CostosSustentacion cs=new CostosSustentacion();
                   txtCodigoSustento.setText(cs.codCostosSustentacion());
            if(txtCodigoSustento.getText().equalsIgnoreCase("")){
            txtCodigoSustento.setText("CS000000000000000001");
            }
        
        
                  rcGUARDAR.setCod_sustento_costo(txtCodigoSustento.getText());
                  rcGUARDAR.setCod_precio(txtCodigoPrecio.getText());
                  rcGUARDAR.setTiempo_hora(Integer.parseInt(spHora.getValue().toString()));
                  rcGUARDAR.setTiempo_min(Integer.parseInt(spMin.getValue().toString()));
                  rcGUARDAR.setSaldo_ganancia_perdida_total(Double.parseDouble(txtGanancia.getText()));
                  rcGUARDAR.setNom_usu(lblUsu.getText());
  
                  if(rcGUARDAR.guardarCostosSustentacion()){
                      guardarDetallePersonal();
                      guardarDetalleInsumo();
                      guardarDetalleServiciosBasicos();
                      guardarDetalleHerramienta();
                      guardarDetalleInfra();
                      guardarDetalleServiciosAdmin();
                      guardarDetalleServiciosGener();
                      guardarDetalleServiciosInter();
                      limpiar();
                      btnModificar.setEnabled(false);
                      JOptionPane.showMessageDialog(this, "Datos Guardados");
                      dispose();

                  }
                  else{
                      JOptionPane.showMessageDialog(this, "El Producto ya se encuentra registrado\nIntente nuevamente");   
                 }
                       
                
          }}}else{
              if(txtCodigoCPT.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Busque el Código CPT");
              }  
              else{
              int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
              if(modificar == 0 ){
                  CostosSustentacion r=new CostosSustentacion();
                  r.setCod_sustento_costo(txtCodigoSustento.getText());
                  r.setCod_precio(txtCodigoPrecio.getText());
                  r.setTiempo_hora(Integer.parseInt(spHora.getValue().toString()));
                  r.setTiempo_min(Integer.parseInt(spMin.getValue().toString()));
                  r.setSaldo_ganancia_perdida_total(Double.parseDouble(txtGanancia.getText()));
                  r.setNom_usu(lblUsu.getText());

                  FP_Personal costoper=new FP_Personal();
                  costoper.setCod_sustento_costo(txtCodigoSustento.getText());
                  
                  FP_Insumos costoin=new FP_Insumos();
                  costoin.setCod_sustento_costo(txtCodigoSustento.getText());
                  
                  FP_SBasicos costosb=new FP_SBasicos();
                  costosb.setCod_sustento_costo(txtCodigoSustento.getText());
                  
                  FP_Herramientas costohe=new FP_Herramientas();
                  costohe.setCod_sustento_costo(txtCodigoSustento.getText());
                  
                  FP_Infraestructura costoinf=new FP_Infraestructura();
                  costoinf.setCod_sustento_costo(txtCodigoSustento.getText());
                  
                  FP_Servicios costose=new FP_Servicios();
                  costose.setCod_sustento_costo(txtCodigoSustento.getText());
                  
                  if(r.modificarCostosSustentacion() && costoper.eliminarFP_Personal()
                          && costoin.eliminarFP_Insumo()&& costosb.eliminarServiciosBasicos()&&
                   costohe.eliminarFP_Herramienta() && costoinf.eliminarFP_Infraestructura() && costose.eliminarFP_Servicios()){
                      guardarDetallePersonal();
                      guardarDetalleInsumo();
                      guardarDetalleServiciosBasicos();
                      guardarDetalleHerramienta();
                      guardarDetalleInfra();
                      guardarDetalleServiciosAdmin();
                      guardarDetalleServiciosGener();
                      guardarDetalleServiciosInter();
                      
                      limpiar();
                      JOptionPane.showMessageDialog(this, "Datos Modificados");
                      btnModificar.setEnabled(false);
                      dispose();
                  }
                  else{
                      JOptionPane.showMessageDialog(this, "El Servicio ya se encuentra registrado\nIntente nuevamente");

                      
                  }}
                  }}
          
            }catch(Exception e) {
              JOptionPane.showMessageDialog(this, e.getMessage());
              
          }
      
        
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       btnGrabar.setEnabled(true);
       
       limpiar();
       habilitar();
       dispose();
        BUSCAR_NOMENCLATURA cs=new BUSCAR_NOMENCLATURA();
        cs.setVisible(true);
       
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtTotalHerramientaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalHerramientaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalHerramientaActionPerformed

    private void btnAgregarServiciosGeneralesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarServiciosGeneralesActionPerformed
        // TODO add your handling code here:
        if(txtCodigoCPT.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Seleccione una Nomenclatura");
        }else if(spHora.getValue().equals(0) && spMin.getValue().equals(0)){
            JOptionPane.showMessageDialog(this, "Ingrese el Tiempo el Tiempo de la Nomenclatura.");
        }
        else{
        Costos_Sustentacion_Detalle_Servicios_Generales CS = new Costos_Sustentacion_Detalle_Servicios_Generales();
        CS.setVisible(true);
        String u=lblUsu.getText();
        Costos_Sustentacion_Detalle_Servicios_Generales.lblUsu.setText(u);
        }
    }//GEN-LAST:event_btnAgregarServiciosGeneralesActionPerformed

    private void btnQuitarServiciosGeneralesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarServiciosGeneralesActionPerformed
        // TODO add your handling code here:
        try{
            int filaselec=tbServiciosGenerales.getSelectedRow();
            if( filaselec>=0){
                int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea QUITAR el Servicio?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(eliminar == 0 ){
                    quitarResumenCostoSGenerales(filaselec);
                    DefaultTableModel modelo = (DefaultTableModel)tbServiciosGenerales.getModel();
                    modelo.removeRow(filaselec);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione el Servicio General a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Detalle a eliminar");
        }
    }//GEN-LAST:event_btnQuitarServiciosGeneralesActionPerformed

    private void tbServiciosAdministrPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbServiciosAdministrPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tbServiciosAdministrPropertyChange

    private void tbServiciosGeneralesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbServiciosGeneralesPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tbServiciosGeneralesPropertyChange

    private void tbServiciosIntermediosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbServiciosIntermediosPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tbServiciosIntermediosPropertyChange

    private void btnAgregarHerramientas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarHerramientas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarHerramientas1ActionPerformed

    private void btnQuitarHerramienta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarHerramienta1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuitarHerramienta1ActionPerformed

    private void btnAgregarSBasicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSBasicosActionPerformed
        // TODO add your handling code here:
         

        if(txtCodigoCPT.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Seleccione una Nomenclatura");
        }else if(spHora.getValue().equals(0) && spMin.getValue().equals(0)){
            JOptionPane.showMessageDialog(this, "Ingrese el Tiempo de la Nomenclatura.");
        }
        else{       
        Costos_Sustentacion_Detalle_Servicio_Basicos SB = new Costos_Sustentacion_Detalle_Servicio_Basicos();
        SB.setVisible(true);
        
        Costos_Sustentacion_Detalle_Servicio_Basicos.btnBuscarSustentacion.setEnabled(true);
        
        cargar_tbprincipal_tbserviciosBasicos();
        
        String u=lblUsu.getText();
        Costos_Sustentacion_Detalle_Servicio_Basicos.lblUsu.setText(u);
        
        //Cargar sumatoria de energia y agua en el formulario de servicios basicos
        double BA1 = Double.parseDouble(txtSumatoriaEnergiaBA.getText());
        double BA2 = Double.parseDouble(txtSumatoriaAguaBA.getText());
        Costos_Sustentacion_Detalle_Servicio_Basicos.txtSumatoriaEnergiaBA1.setText(""+BA1);
        Costos_Sustentacion_Detalle_Servicio_Basicos.txtSumatoriaAguaBA1.setText(""+BA2);
        
        //Cargar consumo mensual de luz y agua en el formulario de servicios basicos
        double CME = Double.parseDouble(txtConsumo_ME.getText());
        double CMA = Double.parseDouble(txtConsumo_MA.getText());
        Costos_Sustentacion_Detalle_Servicio_Basicos.txtConsumoMensual.setText(String.valueOf(CME));
        Costos_Sustentacion_Detalle_Servicio_Basicos.txtConsumoMensualAgua.setText(String.valueOf(CMA));

        //Cargar sumatorias en el formulario servicios basicos
        double SPE = Double.parseDouble(txtSumatoriaEnergiaBA.getText());
        double SPA = Double.parseDouble(txtSumatoriaAguaBA.getText());
        Costos_Sustentacion_Detalle_Servicio_Basicos.txtSumatoria_Principal_E.setText(String.valueOf(SPE));
        Costos_Sustentacion_Detalle_Servicio_Basicos.txtSumatoria_Principal_A.setText(String.valueOf(SPA));
        }
     
     
    }//GEN-LAST:event_btnAgregarSBasicosActionPerformed
public void cargar_tbprincipal_tbserviciosBasicos(){
    
        modelo4 = (DefaultTableModel) tbServiciosBasicos.getModel();
 
        try {
            for (int i=0;i<modelo4.getRowCount(); i++){
            String cod_sustentacion, cod_servicio, sustentacion, servicio, area, ponderacion_energia,base_energia,
            ponderacion_agua,base_agua, total_consulta;

            cod_sustentacion = tbServiciosBasicos.getValueAt(i, 0).toString();
            cod_servicio = tbServiciosBasicos.getValueAt(i, 1).toString();
            sustentacion = tbServiciosBasicos.getValueAt(i, 2).toString();
            servicio = tbServiciosBasicos.getValueAt(i, 3).toString();
            area = tbServiciosBasicos.getValueAt(i, 4).toString();
            ponderacion_energia = tbServiciosBasicos.getValueAt(i, 5).toString();
            base_energia = tbServiciosBasicos.getValueAt(i, 6).toString();
            ponderacion_agua = tbServiciosBasicos.getValueAt(i, 12).toString();
            base_agua = tbServiciosBasicos.getValueAt(i, 13).toString();
            total_consulta = tbServiciosBasicos.getValueAt(i, 19).toString();
            
            //Cargar los datos a la otra tabla 
            modelo5 = (DefaultTableModel) Costos_Sustentacion_Detalle_Servicio_Basicos.tablaServiciosBasicosEA.getModel();
            
            String filaelemento[] = {cod_sustentacion, cod_servicio, sustentacion, servicio,area,ponderacion_energia,
            base_energia,ponderacion_agua, base_agua,total_consulta};
            
            modelo5.addRow(filaelemento);

            }
        } catch (Exception e) {
        }
    }

     
    private void btnQuitarSBasicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarSBasicosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuitarSBasicosActionPerformed

    private void tbInfraestructuraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbInfraestructuraPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tbInfraestructuraPropertyChange

    private void btnAgregarInfraestrucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarInfraestrucActionPerformed
        if(txtCodigoCPT.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Seleccione una Nomenclatura");
        }
         else if(spHora.getValue().equals(0) && spMin.getValue().equals(0)){
            JOptionPane.showMessageDialog(this, "Ingrese el Tiempo el Tiempo de la Nomenclatura.");
        }
        else{
            Costos_Sustentacion_Detalle_Infraestructura CS = new Costos_Sustentacion_Detalle_Infraestructura();
        CS.setVisible(true);
        String u=lblUsu.getText();
        Costos_Sustentacion_Detalle_Infraestructura.lblUsu.setText(u);
        }
    }//GEN-LAST:event_btnAgregarInfraestrucActionPerformed

    private void btnQuitarInfraesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarInfraesActionPerformed
        try{
            int filaselec=tbInfraestructura.getSelectedRow();
            if( filaselec>=0){
                int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea QUITAR El Servicio?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(eliminar == 0 ){
                    quitarResumenCostoInfra(filaselec);
                    DefaultTableModel modelo = (DefaultTableModel)tbInfraestructura.getModel();
                    modelo.removeRow(filaselec);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione el Insumo a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Detalle a eliminar");
        }
    }//GEN-LAST:event_btnQuitarInfraesActionPerformed

    private void txtTotalInfraesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalInfraesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalInfraesActionPerformed

    private void txtTotalSAdminisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalSAdminisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalSAdminisActionPerformed

    private void txtTotalSGeneralesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalSGeneralesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalSGeneralesActionPerformed

    private void btnAgregarServiciosIntermActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarServiciosIntermActionPerformed
        // TODO add your handling code here:
        if(txtCodigoCPT.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Seleccione una Nomenclatura");
        }else if(spHora.getValue().equals(0) && spMin.getValue().equals(0)){
            JOptionPane.showMessageDialog(this, "Ingrese el Tiempo el Tiempo de la Nomenclatura.");
        }
        else{
        Costos_Sustentacion_Detalle_Servicios_Intermedios CS = new Costos_Sustentacion_Detalle_Servicios_Intermedios();
        CS.setVisible(true);
        String u=lblUsu.getText();
        Costos_Sustentacion_Detalle_Servicios_Intermedios.lblUsu.setText(u);
        }
    }//GEN-LAST:event_btnAgregarServiciosIntermActionPerformed

    private void btnQuitarServiciosIntermActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarServiciosIntermActionPerformed
        // TODO add your handling code here:
        try{
            int filaselec=tbServiciosIntermedios.getSelectedRow();
            if( filaselec>=0){
                int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea QUITAR el Servicio?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(eliminar == 0 ){
                    quitarResumenCostoSIntermedios(filaselec);
                    DefaultTableModel modelo = (DefaultTableModel)tbServiciosIntermedios.getModel();
                    modelo.removeRow(filaselec);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione el Servicio Intermedio a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Detalle a eliminar");
        }
    }//GEN-LAST:event_btnQuitarServiciosIntermActionPerformed

    private void txtTotalSIntermediosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalSIntermediosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalSIntermediosActionPerformed

    private void tbPersonalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPersonalKeyPressed
        // TODO add your handling code here:
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_DELETE){
                    btnQuitarPersonal.doClick();
                }
    }//GEN-LAST:event_tbPersonalKeyPressed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped

    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {
            tbNomenclatura.setModel(new DefaultTableModel());
            String titulosnomen[]={"Codigo de Precio","Forma de Pago","Precio","Codigo CPT","Servicio","Área","Descripcion"};
            mbuscar=new DefaultTableModel(null,titulosnomen);
            JTable pnomen=new JTable(mbuscar);
            String filanomen[]=new String[7];

            Usuario obj=new Usuario();
            consulta="exec COSTOS_COSTOS_SUSTENTACION_caja_precios_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            ResultSet r= cmd.executeQuery();
            while(r.next()){
                filanomen[0]=r.getString(1);
                filanomen[1]=r.getString(2);
                filanomen[2]=r.getString(3);
                filanomen[3]=r.getString(4);
                filanomen[4]=r.getString(5);
                filanomen[5]=r.getString(6);
                filanomen[6]=r.getString(7);
                mbuscar.addRow(filanomen);
            }
            tbNomenclatura.setModel(mbuscar);
            TableRowSorter<TableModel> elQueOrdenanomen=new TableRowSorter<TableModel>(mbuscar);
            tbNomenclatura.setRowSorter(elQueOrdenanomen);
            this.tbNomenclatura.setModel(mbuscar);
            formato();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCostoActionPerformed

    private void txtBuscarCostoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCostoKeyPressed
        // TODO add your handling code here:
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscarCosto.doClick();
        }
    }//GEN-LAST:event_txtBuscarCostoKeyPressed

    private void txtBuscarCostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCostoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCostoKeyTyped

    private void btnBuscarCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCostoActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {
            tbCostoSusten.setModel(new DefaultTableModel());
            String titulos[]={"Codigo","Cod Precio","Codigo CPT","Servicio","Área",
                 "Forma de Pago","Precio","Tiempo(h)","Tiempo(min)","Saldo","Nomenclatura"};
            cs=new DefaultTableModel(null,titulos);
            JTable p=new JTable(cs);
            String fila[]=new String[11];
            referencialCabecera obj=new referencialCabecera();
            consulta="exec COSTOS_COSTOS_SUSTENTACION_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarCosto.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
            for (int i=0; i<11; i++){
            fila[i]=r.getString(i+1);
            }
                cs.addRow(fila);
            }
            tbCostoSusten.setModel(cs);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(cs);
            tbCostoSusten.setRowSorter(elQueOrdena);
            tbCostoSusten.setModel(cs);
            formatoCostoSustentacion();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }//GEN-LAST:event_btnBuscarCostoActionPerformed

    private void tbCostoSustenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCostoSustenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCostoSustenMouseClicked

    private void tbCostoSustenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCostoSustenKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            Costos_Sustentacion.setVisible(false);
              mostrarCabecerayDetalle();
            btnBuscarCPT.setEnabled(false);
            btnGrabar.setEnabled(false);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
            spHora.setEnabled(false);
            spMin.setEnabled(false);
            //Personal
            tbPersonal.setEnabled(false);
            tbPersonal.setBackground(Color.lightGray);
            btnAgregarPersonal.setEnabled(false);
            btnQuitarPersonal.setEnabled(false);
            //Insumos
            tbInsumo.setEnabled(false);
            tbInsumo.setBackground(Color.lightGray);
            btnAgregarInsumo.setEnabled(false);
            btnQuitarInsumo.setEnabled(false);
            //Servicios Basicos
            tbServiciosBasicos.setEnabled(false);
            tbServiciosBasicos.setBackground(Color.lightGray);
            btnAgregarSBasicos.setEnabled(false);
            btnQuitarSBasicos.setEnabled(false);
            //Herramienta
            tbHerramienta.setEnabled(false);
            tbHerramienta.setBackground(Color.lightGray);
            btnAgregarHerramientas.setEnabled(false);
            btnQuitarHerramienta.setEnabled(false);
            //Infra
            tbInfraestructura.setEnabled(false);
            tbInfraestructura.setBackground(Color.lightGray);
            btnAgregarInfraestruc.setEnabled(false);
            btnQuitarInfraes.setEnabled(false);
            //S Administ
            tbServiciosAdministr.setEnabled(false);
            tbServiciosAdministr.setBackground(Color.lightGray);
            btnAgregarSAdminis.setEnabled(false);
            btnQuitarSAdminis.setEnabled(false);
            //S Generales
            tbServiciosGenerales.setEnabled(false);
            tbServiciosGenerales.setBackground(Color.lightGray);
            btnAgregarServiciosGenerales.setEnabled(false);
            btnQuitarServiciosGenerales.setEnabled(false);
            //S Intermedios
            tbServiciosIntermedios.setEnabled(false);
            tbServiciosIntermedios.setBackground(Color.lightGray);
            btnAgregarServiciosInterm.setEnabled(false);
            btnQuitarServiciosInterm.setEnabled(false);
        }

    }//GEN-LAST:event_tbCostoSustenKeyPressed

    private void tbCostoSustenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCostoSustenKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCostoSustenKeyTyped

    private void btnBuscarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTodoActionPerformed
        // TODO add your handling code here:
        Costos_Sustentacion.setVisible(true);
        cargarCostoSustentacion();
        tbCostoSusten.getSelectionModel().setSelectionInterval(0, 0);
        tbCostoSusten.requestFocus();
       
        
    }//GEN-LAST:event_btnBuscarTodoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
            habilitar();
            btnEliminar.setEnabled(false);
            btnBuscarCPT.setEnabled(false);
            txtGuarModif.setText("M");
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/eliminar16x16.png")); 
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
        try{
            if(eliminar == 0 )
            {
                CostosSustentacion costo=new CostosSustentacion();
                  costo.setCod_sustento_costo(txtCodigoSustento.getText());
                  
                  FP_Personal costoper=new FP_Personal();
                  costoper.setCod_sustento_costo(txtCodigoSustento.getText());
                  
                  FP_Insumos costoin=new FP_Insumos();
                  costoin.setCod_sustento_costo(txtCodigoSustento.getText());
                  
                  FP_SBasicos costosb=new FP_SBasicos();
                  costosb.setCod_sustento_costo(txtCodigoSustento.getText());
                  
                  FP_Herramientas costohe=new FP_Herramientas();
                  costohe.setCod_sustento_costo(txtCodigoSustento.getText());
                  
                  FP_Infraestructura costoi=new FP_Infraestructura();
                  costoi.setCod_sustento_costo(txtCodigoSustento.getText());
                  
                  FP_Servicios costose=new FP_Servicios();
                  costose.setCod_sustento_costo(txtCodigoSustento.getText());
                  
                if(costo.eliminarCostosSustentacion() && costoper.eliminarFP_Personal()
                        && costoin.eliminarFP_Insumo()&& costosb.eliminarServiciosBasicos()&&
                   costohe.eliminarFP_Herramienta() && costoi.eliminarFP_Infraestructura() && costose.eliminarFP_Servicios()    ){
                    JOptionPane.showMessageDialog(this, "Datos Eliminados");
                       btnGrabar.setEnabled(true);
                       btnModificar.setEnabled(false);
                       btnEliminar.setEnabled(false);
                    limpiar();
                    habilitar();
                    dispose();
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Personal a eliminar");
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtConsumo_MEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsumo_MEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsumo_MEActionPerformed

    private void txtPrecio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecio1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecio1ActionPerformed

    private void txtGananciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGananciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGananciaActionPerformed

    private void spMinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spMinPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_spMinPropertyChange

    private void spMinStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spMinStateChanged
        // TODO add your handling code here:
        if(tbPersonal.getRowCount()>0 ){
            if(tbPersonal.getValueAt(0, 5).toString().equalsIgnoreCase(spMin.getValue().toString())){
            }
            else{
                //   JOptionPane.showMessageDialog(rootPane,spMin.getValue().toString() );
                //   JOptionPane.showMessageDialog(rootPane,tbPersonal.getValueAt(0, 5).toString());
                tbPersonal.setModel(new DefaultTableModel());
                //Personal
                String titulosp[]={"Cod_tipoSust","Cod_Sueldo","Tipo Sustento","Nombre del Detalle","Hora","Min","Horas al Mes","Sueldo","Total Sustento"};
                m3=new DefaultTableModel(null,titulosp);
                JTable pp=new JTable(m3);
                String filap[]=new String[9];
                tbPersonal.setModel(m3);
                TableRowSorter<TableModel> elQueOrdenap=new TableRowSorter<TableModel>(m3);
                tbPersonal.setRowSorter(elQueOrdenap);
                this.tbPersonal.setModel(m3);
                //Personal
                tbPersonal.getColumnModel().getColumn(2).setPreferredWidth(150);
                tbPersonal.getColumnModel().getColumn(3).setPreferredWidth(180);
                tbPersonal.getColumnModel().getColumn(4).setPreferredWidth(50);
                tbPersonal.getColumnModel().getColumn(5).setPreferredWidth(50);
                tbPersonal.getColumnModel().getColumn(6).setPreferredWidth(120);
                tbPersonal.getColumnModel().getColumn(7).setPreferredWidth(120);
                tbPersonal.getColumnModel().getColumn(8).setPreferredWidth(120);
                //Ocultar
                tbPersonal.getColumnModel().getColumn(0).setMinWidth(0);
                tbPersonal.getColumnModel().getColumn(0).setMaxWidth(0);
                tbPersonal.getColumnModel().getColumn(1).setMinWidth(0);
                tbPersonal.getColumnModel().getColumn(1).setMaxWidth(0);
            }}
    }//GEN-LAST:event_spMinStateChanged

    private void spHoraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spHoraStateChanged
        // TODO add your handling code here:
        if(tbPersonal.getRowCount()>0 ){
            if(tbPersonal.getValueAt(0, 4).toString().equalsIgnoreCase(spHora.getValue().toString())){
            }
            else{
                //   JOptionPane.showMessageDialog(rootPane,spMin.getValue().toString() );
                //   JOptionPane.showMessageDialog(rootPane,tbPersonal.getValueAt(0, 5).toString());
                tbPersonal.setModel(new DefaultTableModel());
                //Personal
                String titulosp[]={"Cod_tipoSust","Cod_Sueldo","Tipo Sustento","Nombre del Detalle","Hora","Min","Horas al Mes","Sueldo","Total Sustento"};
                m3=new DefaultTableModel(null,titulosp);
                JTable pp=new JTable(m3);
                String filap[]=new String[9];
                tbPersonal.setModel(m3);
                TableRowSorter<TableModel> elQueOrdenap=new TableRowSorter<TableModel>(m3);
                tbPersonal.setRowSorter(elQueOrdenap);
                this.tbPersonal.setModel(m3);
                //Personal
                tbPersonal.getColumnModel().getColumn(2).setPreferredWidth(150);
                tbPersonal.getColumnModel().getColumn(3).setPreferredWidth(180);
                tbPersonal.getColumnModel().getColumn(4).setPreferredWidth(50);
                tbPersonal.getColumnModel().getColumn(5).setPreferredWidth(50);
                tbPersonal.getColumnModel().getColumn(6).setPreferredWidth(120);
                tbPersonal.getColumnModel().getColumn(7).setPreferredWidth(120);
                tbPersonal.getColumnModel().getColumn(8).setPreferredWidth(120);
                //Ocultar
                tbPersonal.getColumnModel().getColumn(0).setMinWidth(0);
                tbPersonal.getColumnModel().getColumn(0).setMaxWidth(0);
                tbPersonal.getColumnModel().getColumn(1).setMinWidth(0);
                tbPersonal.getColumnModel().getColumn(1).setMaxWidth(0);
            }}
    }//GEN-LAST:event_spHoraStateChanged

    private void btnBuscarCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPTActionPerformed
        BuscarNomenclatura.setVisible(true);
        //Desplazarce con teclas direccionales
        tbNomenclatura.getSelectionModel().setSelectionInterval(0, 0);
        tbNomenclatura.requestFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarCPTActionPerformed
    public void quitarResumenCostoPersonal( int filaselec){
        try{
        double total=0;
            tbResumenCostos.setModel(new DefaultTableModel());
            String titulos2[]={"Tipo Sustentación","Valor Total"};
            m2=new DefaultTableModel(null,titulos2);
            JTable p2=new JTable(m2);
            //total
            total=Double.parseDouble(txtTotalPersonal.getText())-Double.parseDouble(tbPersonal.getValueAt(filaselec, 8).toString());
            BigDecimal totalp = new BigDecimal(total);
                       totalp = totalp.setScale(2, BigDecimal.ROUND_HALF_UP);
            String fila[]=new String[2];
            for (int i=0; i<8; i++){
                if(i==0){
                fila[0]="RECURSO HUMANO";
                fila[1]=String.valueOf(totalp);
                }
                else if(i==1){
                fila[0]="INSUMOS";
                fila[1]=txtTotalInsumos.getText();;
                } 
                else if(i==2){
                fila[0]="SERVICIOS BÁSICOS";
                fila[1]=txtTotalSBasicos.getText();;
                } 
                else if(i==3){
                fila[0]="EQUIPAMIENTO BÁSICO";
                fila[1]=txtTotalHerramienta.getText();
                } 
                else if(i==4){
                fila[0]="INFRAESTRUCTURA";
                fila[1]=txtTotalInfraes.getText();;
                } 
                else if(i==5){
                fila[0]="SERVICIOS ADMINISTRATIVOS";
                fila[1]=txtTotalSAdminis.getText();;
                } 
                else if(i==6){
                fila[0]="SERVICIOS GENERALES";
                fila[1]=txtTotalSGenerales.getText();;
                } 
                else if(i==7){
                fila[0]="GASTOS INTERMEDIOS";
                fila[1]=txtTotalSIntermedios.getText();;
                } 
                m2.addRow(fila);
                }
            tbResumenCostos.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena2=new TableRowSorter<TableModel>(m2);
            tbResumenCostos.setRowSorter(elQueOrdena2);
            tbResumenCostos.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbResumenCostos.getColumnModel().getColumn(1).setPreferredWidth(120);
    
            txtTotalPersonal.setText(String.valueOf(totalp));
            //Total
            Double insumo,he,sb,inf,sg,sa,si;
            he=Double.parseDouble(txtTotalHerramienta.getText());
            insumo=Double.parseDouble(txtTotalInsumos.getText());
            sb=Double.parseDouble(txtTotalSBasicos.getText());
            inf=Double.parseDouble(txtTotalInfraes.getText());
            sa=Double.parseDouble(txtTotalSAdminis.getText());
            sg=Double.parseDouble(txtTotalSGenerales.getText());
            si=Double.parseDouble(txtTotalSIntermedios.getText());
            Double precioTotal=total+he+insumo+sb+inf+sa+sg+si;
            BigDecimal ptotal = new BigDecimal(precioTotal);
                        ptotal = ptotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtPrecioTotal.setText(String.valueOf(ptotal));
            //Ganancia
            double gan=Double.parseDouble(txtPrecio.getText())-precioTotal;
            BigDecimal gtotal = new BigDecimal(gan);
                       gtotal = gtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
                       txtGanancia.setText(String.valueOf(gtotal));
            if(gan>0){
                lblGananciaPer.setForeground(Color.black);
                txtGananciaPerdida.setForeground(Color.black);
                       lblGananciaPer.setText("Ganancia Total");
            txtGananciaPerdida.setText(String.valueOf(gtotal));
            }else{
                double g=-gan;
                BigDecimal gt = new BigDecimal(g);
                       gt = gt.setScale(2, BigDecimal.ROUND_HALF_UP);
                       lblGananciaPer.setForeground(Color.red);
                txtGananciaPerdida.setForeground(Color.red);
                 lblGananciaPer.setText("Pérdida Total");
            txtGananciaPerdida.setText(String.valueOf(gt));
            }
                }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
                }
            }
    //INSUMO
    public void quitarResumenCostoInsumo( int filaselec){
        try{
        double total=0;
            tbResumenCostos.setModel(new DefaultTableModel());
            String titulos2[]={"Tipo Sustentación","Valor Total"};
            m2=new DefaultTableModel(null,titulos2);
            JTable p2=new JTable(m2);
            //total
            total=Double.parseDouble(txtTotalInsumos.getText())-Double.parseDouble(tbInsumo.getValueAt(filaselec, 10).toString());
            BigDecimal totalp = new BigDecimal(total);
                       totalp = totalp.setScale(2, BigDecimal.ROUND_HALF_UP);
            String fila[]=new String[2];
            for (int i=0; i<8; i++){
                if(i==0){
                fila[0]="RECURSO HUMANO";
                fila[1]=txtTotalPersonal.getText();
                }
                else if(i==1){
                fila[0]="INSUMOS";
                fila[1]=String.valueOf(totalp);
                } 
                else if(i==2){
                fila[0]="SERVICIOS BÁSICOS";
                fila[1]=txtTotalSBasicos.getText();
                } 
                else if(i==3){
                fila[0]="EQUIPAMIENTO BÁSICO";
                fila[1]=txtTotalHerramienta.getText();
                } 
                else if(i==4){
                fila[0]="INFRAESTRUCTURA";
                fila[1]=txtTotalInfraes.getText();
                } 
                else if(i==5){
                fila[0]="SERVICIOS ADMINISTRATIVOS";
                fila[1]=txtTotalSAdminis.getText();
                } 
                else if(i==6){
                fila[0]="SERVICIOS GENERALES";
                fila[1]=txtTotalSGenerales.getText();
                } 
                else if(i==7){
                fila[0]="GASTOS INTERMEDIOS";
                fila[1]=txtTotalSIntermedios.getText();
                } 
                m2.addRow(fila);
                }
            tbResumenCostos.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena2=new TableRowSorter<TableModel>(m2);
            tbResumenCostos.setRowSorter(elQueOrdena2);
            tbResumenCostos.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbResumenCostos.getColumnModel().getColumn(1).setPreferredWidth(120);
    
            txtTotalInsumos.setText(String.valueOf(totalp));
            //Total
            Double per,he,sb,inf,sg,sa,si;
            per=Double.parseDouble(txtTotalPersonal.getText());
            he=Double.parseDouble(txtTotalHerramienta.getText());
            sb=Double.parseDouble(txtTotalSBasicos.getText());
            inf=Double.parseDouble(txtTotalInfraes.getText());
            sa=Double.parseDouble(txtTotalSAdminis.getText());
            sg=Double.parseDouble(txtTotalSGenerales.getText());
            si=Double.parseDouble(txtTotalSIntermedios.getText());
            Double precioTotal=total+he+per+sb+inf+sa+sg+si;
            BigDecimal ptotal = new BigDecimal(precioTotal);
                        ptotal = ptotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtPrecioTotal.setText(String.valueOf(ptotal));
            //Ganancia
            double gan=Double.parseDouble(txtPrecio.getText())-precioTotal;
            BigDecimal gtotal = new BigDecimal(gan);
                       gtotal = gtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
                       txtGanancia.setText(String.valueOf(gtotal));
            if(gan>0){
                lblGananciaPer.setForeground(Color.black);
                txtGananciaPerdida.setForeground(Color.black);
                       lblGananciaPer.setText("Ganancia Total");
            txtGananciaPerdida.setText(String.valueOf(gtotal));
            }else{
                double g=-gan;
                BigDecimal gt = new BigDecimal(g);
                       gt = gt.setScale(2, BigDecimal.ROUND_HALF_UP);
                       lblGananciaPer.setForeground(Color.red);
                txtGananciaPerdida.setForeground(Color.red);
                 lblGananciaPer.setText("Pérdida Total");
            txtGananciaPerdida.setText(String.valueOf(gt));
            }
                }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
                }
            }
    //HERRAMIENTAS
    public void quitarResumenCostoHerramienta( int filaselech){
        try{
        double total=0;
            tbResumenCostos.setModel(new DefaultTableModel());
            String titulos2[]={"Tipo Sustentación","Valor Total"};
            m2=new DefaultTableModel(null,titulos2);
            JTable p2=new JTable(m2);
            //total
            total=Double.parseDouble(txtTotalHerramienta.getText())-Double.parseDouble(tbHerramienta.getValueAt(filaselech, 13).toString());
            BigDecimal totalh = new BigDecimal(total);
                       totalh = totalh.setScale(2, BigDecimal.ROUND_HALF_UP);
            String fila[]=new String[2];
            for (int i=0; i<8; i++){
                if(i==0){
                fila[0]="RECURSO HUMANO";
                fila[1]=txtTotalPersonal.getText();
                }
                else if(i==1){
                fila[0]="INSUMOS";
                fila[1]=txtTotalInsumos.getText();
                } 
                else if(i==2){
                fila[0]="SERVICIOS BÁSICOS";
                fila[1]=txtTotalSBasicos.getText();
                } 
                else if(i==3){
                fila[0]="EQUIPAMIENTO BÁSICO";
                fila[1]=String.valueOf(totalh);
                } 
                else if(i==4){
                fila[0]="INFRAESTRUCTURA";
                fila[1]=txtTotalInfraes.getText();
                } 
                else if(i==5){
                fila[0]="SERVICIOS ADMINISTRATIVOS";
                fila[1]=txtTotalSAdminis.getText();
                } 
                else if(i==6){
                fila[0]="SERVICIOS GENERALES";
                fila[1]=txtTotalSGenerales.getText();
                } 
                else if(i==7){
                fila[0]="GASTOS INTERMEDIOS";
                fila[1]=txtTotalSIntermedios.getText();
                } 
                m2.addRow(fila);
                }
            tbResumenCostos.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena2=new TableRowSorter<TableModel>(m2);
            tbResumenCostos.setRowSorter(elQueOrdena2);
            tbResumenCostos.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbResumenCostos.getColumnModel().getColumn(1).setPreferredWidth(120);
    
            txtTotalHerramienta.setText(String.valueOf(totalh));
            //Total
            Double insumo,pe,sb,inf,sg,sa,si;
            pe=Double.parseDouble(txtTotalPersonal.getText());
            insumo=Double.parseDouble(txtTotalInsumos.getText());
            sb=Double.parseDouble(txtTotalSBasicos.getText());
            inf=Double.parseDouble(txtTotalInfraes.getText());
            sa=Double.parseDouble(txtTotalSAdminis.getText());
            sg=Double.parseDouble(txtTotalSGenerales.getText());
            si=Double.parseDouble(txtTotalSIntermedios.getText());
            Double precioTotal=total+pe+insumo+sb+inf+sa+sg+si;
            BigDecimal ptotal = new BigDecimal(precioTotal);
                       ptotal = ptotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtPrecioTotal.setText(String.valueOf(ptotal));
            //Ganancia
            double gan=Double.parseDouble(txtPrecio.getText())-precioTotal;
            BigDecimal gtotal = new BigDecimal(gan);
                       gtotal = gtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
                       txtGanancia.setText(String.valueOf(gtotal));
            if(gan>0){
                lblGananciaPer.setForeground(Color.black);
                txtGananciaPerdida.setForeground(Color.black);
                       lblGananciaPer.setText("Ganancia Total");
            txtGananciaPerdida.setText(String.valueOf(gtotal));
            }else{
                double g=-gan;
                BigDecimal gt = new BigDecimal(g);
                       gt = gt.setScale(2, BigDecimal.ROUND_HALF_UP);
                       lblGananciaPer.setForeground(Color.red);
                txtGananciaPerdida.setForeground(Color.red);
                 lblGananciaPer.setText("Pérdida Total");
            txtGananciaPerdida.setText(String.valueOf(gt));
            }
                }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
                }
            }
     //HERRAMIENTAS
    public void quitarResumenCostoInfra( int filaselech){
        try{
        double total=0;
            tbResumenCostos.setModel(new DefaultTableModel());
            String titulos2[]={"Tipo Sustentación","Valor Total"};
            m2=new DefaultTableModel(null,titulos2);
            JTable p2=new JTable(m2);
            //total
            total=Double.parseDouble(txtTotalInfraes.getText())-Double.parseDouble(tbInfraestructura.getValueAt(filaselech, 13).toString());
            BigDecimal totali = new BigDecimal(total);
                       totali = totali.setScale(2, BigDecimal.ROUND_HALF_UP);
            String fila[]=new String[2];
            for (int i=0; i<8; i++){
                if(i==0){
                fila[0]="RECURSO HUMANO";
                fila[1]=txtTotalPersonal.getText();
                }
                else if(i==1){
                fila[0]="INSUMOS";
                fila[1]=txtTotalInsumos.getText();
                } 
                else if(i==2){
                fila[0]="SERVICIOS BÁSICOS";
                fila[1]=txtTotalSBasicos.getText();
                } 
                else if(i==3){
                fila[0]="EQUIPAMIENTO BÁSICO";
                fila[1]=txtTotalHerramienta.getText();
                } 
                else if(i==4){
                fila[0]="INFRAESTRUCTURA";
                fila[1]=String.valueOf(totali);
                } 
                else if(i==5){
                fila[0]="SERVICIOS ADMINISTRATIVOS";
                fila[1]=txtTotalSAdminis.getText();
                } 
                else if(i==6){
                fila[0]="SERVICIOS GENERALES";
                fila[1]=txtTotalSGenerales.getText();
                } 
                else if(i==7){
                fila[0]="GASTOS INTERMEDIOS";
                fila[1]=txtTotalSIntermedios.getText();
                } 
                m2.addRow(fila);
                }
            tbResumenCostos.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena2=new TableRowSorter<TableModel>(m2);
            tbResumenCostos.setRowSorter(elQueOrdena2);
            tbResumenCostos.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbResumenCostos.getColumnModel().getColumn(1).setPreferredWidth(120);
    
            txtTotalInfraes.setText(String.valueOf(totali));
            //Total
            Double insumo,pe,sb,he,sg,sa,si;
            pe=Double.parseDouble(txtTotalPersonal.getText());
            insumo=Double.parseDouble(txtTotalInsumos.getText());
            sb=Double.parseDouble(txtTotalSBasicos.getText());
            he=Double.parseDouble(txtTotalHerramienta.getText());
            sa=Double.parseDouble(txtTotalSAdminis.getText());
            sg=Double.parseDouble(txtTotalSGenerales.getText());
            si=Double.parseDouble(txtTotalSIntermedios.getText());
            Double precioTotal=total+pe+insumo+sb+he+sa+sg+si;
            BigDecimal ptotal = new BigDecimal(precioTotal);
                       ptotal = ptotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtPrecioTotal.setText(String.valueOf(ptotal));
            //Ganancia
            double gan=Double.parseDouble(txtPrecio.getText())-precioTotal;
            BigDecimal gtotal = new BigDecimal(gan);
                       gtotal = gtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
                       txtGanancia.setText(String.valueOf(gtotal));
            if(gan>0){
                lblGananciaPer.setForeground(Color.black);
                txtGananciaPerdida.setForeground(Color.black);
                       lblGananciaPer.setText("Ganancia Total");
            txtGananciaPerdida.setText(String.valueOf(gtotal));
            }else{
                double g=-gan;
                BigDecimal gt = new BigDecimal(g);
                       gt = gt.setScale(2, BigDecimal.ROUND_HALF_UP);
                       lblGananciaPer.setForeground(Color.red);
                txtGananciaPerdida.setForeground(Color.red);
                 lblGananciaPer.setText("Pérdida Total");
            txtGananciaPerdida.setText(String.valueOf(gt));
            }
                }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
                }
            }
    public void quitarResumenCostoSAdmin( int filaselech){
        try{
        double total=0;
            tbResumenCostos.setModel(new DefaultTableModel());
            String titulos2[]={"Tipo Sustentación","Valor Total"};
            m2=new DefaultTableModel(null,titulos2);
            JTable p2=new JTable(m2);
            //total
            total=Double.parseDouble(txtTotalSAdminis.getText())-Double.parseDouble(tbServiciosAdministr.getValueAt(filaselech, 6).toString());
            BigDecimal totalsa = new BigDecimal(total);
                       totalsa = totalsa.setScale(2, BigDecimal.ROUND_HALF_UP);
            String fila[]=new String[2];
            for (int i=0; i<8; i++){
                if(i==0){
                fila[0]="RECURSO HUMANO";
                fila[1]=txtTotalPersonal.getText();
                }
                else if(i==1){
                fila[0]="INSUMOS";
                fila[1]=txtTotalInsumos.getText();
                } 
                else if(i==2){
                fila[0]="SERVICIOS BÁSICOS";
                fila[1]=txtTotalSBasicos.getText();
                } 
                else if(i==3){
                fila[0]="EQUIPAMIENTO BÁSICO";
                fila[1]=txtTotalHerramienta.getText();
                } 
                else if(i==4){
                fila[0]="INFRAESTRUCTURA";
                fila[1]=txtTotalInfraes.getText();
                } 
                else if(i==5){
                fila[0]="SERVICIOS ADMINISTRATIVOS";
                fila[1]=String.valueOf(totalsa);
                } 
                else if(i==6){
                fila[0]="SERVICIOS GENERALES";
                fila[1]=txtTotalSGenerales.getText();
                } 
                else if(i==7){
                fila[0]="GASTOS INTERMEDIOS";
                fila[1]=txtTotalSIntermedios.getText();
                } 
                m2.addRow(fila);
                }
            tbResumenCostos.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena2=new TableRowSorter<TableModel>(m2);
            tbResumenCostos.setRowSorter(elQueOrdena2);
            tbResumenCostos.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbResumenCostos.getColumnModel().getColumn(1).setPreferredWidth(120);
    
            txtTotalSAdminis.setText(String.valueOf(totalsa));
            //Total
            Double insumo,pe,sb,inf,sg,he,si;
            pe=Double.parseDouble(txtTotalPersonal.getText());
            he=Double.parseDouble(txtTotalHerramienta.getText());
            insumo=Double.parseDouble(txtTotalInsumos.getText());
            sb=Double.parseDouble(txtTotalSBasicos.getText());
            inf=Double.parseDouble(txtTotalInfraes.getText());
            sg=Double.parseDouble(txtTotalSGenerales.getText());
            si=Double.parseDouble(txtTotalSIntermedios.getText());
            Double precioTotal=total+pe+insumo+sb+inf+he+sg+si;
            BigDecimal ptotal = new BigDecimal(precioTotal);
                       ptotal = ptotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtPrecioTotal.setText(String.valueOf(ptotal));
            //Ganancia
            double gan=Double.parseDouble(txtPrecio.getText())-precioTotal;
            BigDecimal gtotal = new BigDecimal(gan);
                       gtotal = gtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
                       txtGanancia.setText(String.valueOf(gtotal));
            if(gan>0){
                lblGananciaPer.setForeground(Color.black);
                txtGananciaPerdida.setForeground(Color.black);
                       lblGananciaPer.setText("Ganancia Total");
            txtGananciaPerdida.setText(String.valueOf(gtotal));
            }else{
                double g=-gan;
                BigDecimal gt = new BigDecimal(g);
                       gt = gt.setScale(2, BigDecimal.ROUND_HALF_UP);
                       lblGananciaPer.setForeground(Color.red);
                txtGananciaPerdida.setForeground(Color.red);
                 lblGananciaPer.setText("Pérdida Total");
            txtGananciaPerdida.setText(String.valueOf(gt));
            }
                }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
                }
            }
        public void quitarResumenCostoSGenerales( int filaselech){
        try{
        double total=0;
            tbResumenCostos.setModel(new DefaultTableModel());
            String titulos2[]={"Tipo Sustentación","Valor Total"};
            m2=new DefaultTableModel(null,titulos2);
            JTable p2=new JTable(m2);
            //total
            total=Double.parseDouble(txtTotalSGenerales.getText())-Double.parseDouble(tbServiciosGenerales.getValueAt(filaselech, 6).toString());
            BigDecimal totalsg = new BigDecimal(total);
                       totalsg = totalsg.setScale(2, BigDecimal.ROUND_HALF_UP);
            String fila[]=new String[2];
            for (int i=0; i<8; i++){
                if(i==0){
                fila[0]="RECURSO HUMANO";
                fila[1]=txtTotalPersonal.getText();
                }
                else if(i==1){
                fila[0]="INSUMOS";
                fila[1]=txtTotalInsumos.getText();
                } 
                else if(i==2){
                fila[0]="SERVICIOS BÁSICOS";
                fila[1]=txtTotalSBasicos.getText();
                } 
                else if(i==3){
                fila[0]="EQUIPAMIENTO BÁSICO";
                fila[1]=txtTotalHerramienta.getText();
                } 
                else if(i==4){
                fila[0]="INFRAESTRUCTURA";
                fila[1]=txtTotalInfraes.getText();
                } 
                else if(i==5){
                fila[0]="SERVICIOS ADMINISTRATIVOS";
                fila[1]=txtTotalSAdminis.getText();
                } 
                else if(i==6){
                fila[0]="SERVICIOS GENERALES";
                fila[1]=String.valueOf(totalsg);
                } 
                else if(i==7){
                fila[0]="GASTOS INTERMEDIOS";
                fila[1]=txtTotalSIntermedios.getText();
                } 
                m2.addRow(fila);
                }
            tbResumenCostos.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena2=new TableRowSorter<TableModel>(m2);
            tbResumenCostos.setRowSorter(elQueOrdena2);
            tbResumenCostos.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbResumenCostos.getColumnModel().getColumn(1).setPreferredWidth(120);
    
            txtTotalSGenerales.setText(String.valueOf(totalsg));
            //Total
            Double insumo,pe,sb,inf,he,sa,si;
            pe=Double.parseDouble(txtTotalPersonal.getText());
            he=Double.parseDouble(txtTotalHerramienta.getText());
            insumo=Double.parseDouble(txtTotalInsumos.getText());
            sb=Double.parseDouble(txtTotalSBasicos.getText());
            inf=Double.parseDouble(txtTotalInfraes.getText());
            sa=Double.parseDouble(txtTotalSAdminis.getText());
            si=Double.parseDouble(txtTotalSIntermedios.getText());
            Double precioTotal=total+pe+insumo+sb+inf+sa+he+si;
            BigDecimal ptotal = new BigDecimal(precioTotal);
                       ptotal = ptotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtPrecioTotal.setText(String.valueOf(ptotal));
            //Ganancia
            double gan=Double.parseDouble(txtPrecio.getText())-precioTotal;
            BigDecimal gtotal = new BigDecimal(gan);
                       gtotal = gtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
                       txtGanancia.setText(String.valueOf(gtotal));
            if(gan>0){
                lblGananciaPer.setForeground(Color.black);
                txtGananciaPerdida.setForeground(Color.black);
                       lblGananciaPer.setText("Ganancia Total");
            txtGananciaPerdida.setText(String.valueOf(gtotal));
            }else{
                double g=-gan;
                BigDecimal gt = new BigDecimal(g);
                       gt = gt.setScale(2, BigDecimal.ROUND_HALF_UP);
                       lblGananciaPer.setForeground(Color.red);
                txtGananciaPerdida.setForeground(Color.red);
                 lblGananciaPer.setText("Pérdida Total");
            txtGananciaPerdida.setText(String.valueOf(gt));
            }
                }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
                }
            }
        
        public void quitarResumenCostoSIntermedios( int filaselech){
        try{
        double total=0;
            tbResumenCostos.setModel(new DefaultTableModel());
            String titulos2[]={"Tipo Sustentación","Valor Total"};
            m2=new DefaultTableModel(null,titulos2);
            JTable p2=new JTable(m2);
            //total
            total=Double.parseDouble(txtTotalSIntermedios.getText())-Double.parseDouble(tbServiciosIntermedios.getValueAt(filaselech, 6).toString());
            BigDecimal totalsi = new BigDecimal(total);
                       totalsi = totalsi.setScale(2, BigDecimal.ROUND_HALF_UP);
            String fila[]=new String[2];
            for (int i=0; i<8; i++){
                if(i==0){
                fila[0]="RECURSO HUMANO";
                fila[1]=txtTotalPersonal.getText();
                }
                else if(i==1){
                fila[0]="INSUMOS";
                fila[1]=txtTotalInsumos.getText();
                } 
                else if(i==2){
                fila[0]="SERVICIOS BÁSICOS";
                fila[1]=txtTotalSBasicos.getText();
                } 
                else if(i==3){
                fila[0]="EQUIPAMIENTO BÁSICO";
                fila[1]=txtTotalHerramienta.getText();
                } 
                else if(i==4){
                fila[0]="INFRAESTRUCTURA";
                fila[1]=txtTotalInfraes.getText();
                } 
                else if(i==5){
                fila[0]="SERVICIOS ADMINISTRATIVOS";
                fila[1]=txtTotalSAdminis.getText();
                } 
                else if(i==6){
                fila[0]="SERVICIOS GENERALES";
                fila[1]=txtTotalSGenerales.getText();
                } 
                else if(i==7){
                fila[0]="GASTOS INTERMEDIOS";
                fila[1]=String.valueOf(totalsi);
                } 
                m2.addRow(fila);
                }
            tbResumenCostos.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena2=new TableRowSorter<TableModel>(m2);
            tbResumenCostos.setRowSorter(elQueOrdena2);
            tbResumenCostos.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbResumenCostos.getColumnModel().getColumn(1).setPreferredWidth(120);
    
            txtTotalSIntermedios.setText(String.valueOf(totalsi));
            //Total
            Double insumo,pe,sb,inf,sg,sa,he;
            pe=Double.parseDouble(txtTotalPersonal.getText());
            he=Double.parseDouble(txtTotalHerramienta.getText());
            insumo=Double.parseDouble(txtTotalInsumos.getText());
            sb=Double.parseDouble(txtTotalSBasicos.getText());
            inf=Double.parseDouble(txtTotalInfraes.getText());
            sa=Double.parseDouble(txtTotalSAdminis.getText());
            sg=Double.parseDouble(txtTotalSGenerales.getText());
            Double precioTotal=total+pe+insumo+sb+inf+sa+sg+he;
            BigDecimal ptotal = new BigDecimal(precioTotal);
                       ptotal = ptotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtPrecioTotal.setText(String.valueOf(ptotal));
            //Ganancia
            double gan=Double.parseDouble(txtPrecio.getText())-precioTotal;
            BigDecimal gtotal = new BigDecimal(gan);
                       gtotal = gtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
                       txtGanancia.setText(String.valueOf(gtotal));
            if(gan>0){
                  lblGananciaPer.setForeground(Color.black);
                txtGananciaPerdida.setForeground(Color.black);
                       lblGananciaPer.setText("Ganancia Total");
            txtGananciaPerdida.setText(String.valueOf(gtotal));
            }else{
                double g=-gan;
                BigDecimal gt = new BigDecimal(g);
                       gt = gt.setScale(2, BigDecimal.ROUND_HALF_UP);
                       lblGananciaPer.setForeground(Color.red);
                txtGananciaPerdida.setForeground(Color.red);
                 lblGananciaPer.setText("Pérdida Total");
            txtGananciaPerdida.setText(String.valueOf(gt));
            }
                }
        catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
                }
            }
        public void limpiar()
    {
        CostosSustentacion v=new CostosSustentacion();
        txtCodigoSustento.setText(v.codCostosSustentacion());
        
        if(txtCodigoSustento.getText().equalsIgnoreCase("")){
        txtCodigoSustento.setText("CS000000000000000001");
        txtCodigo6.setText("000001");
        }else{
            txtCodigo6.setText(v.codCostosSustentacion().substring(14, 20));
        }
        tbPersonal.setModel(new DefaultTableModel());
        tbInsumo.setModel(new DefaultTableModel());
        tbServiciosBasicos.setModel(new DefaultTableModel());
        tbInfraestructura.setModel(new DefaultTableModel());
        tbServiciosAdministr.setModel(new DefaultTableModel());
        tbServiciosGenerales.setModel(new DefaultTableModel());
        tbServiciosIntermedios.setModel(new DefaultTableModel());
        btnBuscarCPT.setEnabled(true);
        inicializar_tabla();
        txtGuarModif.setText("G");
        txtCodigoCPT.setText("");
        txtServicio.setText("");
        txtSubServicio.setText("");
        txtFormadePago.setText("");
        txtPrecio.setText("");
        txtNomenclatura.setText("");
        spHora.setValue(0);
        spMin.setValue(0);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        lblGananciaPer.setForeground(Color.black);
                txtGananciaPerdida.setForeground(Color.black);
                lblGananciaPer.setText("Ganancia Total");
         
        }
        public void habilitar(){
            btnBuscarCPT.setEnabled(true);
            btnGrabar.setEnabled(true);
            
            spHora.setEnabled(true);
            spMin.setEnabled(true);
            //Personal
            tbPersonal.setEnabled(true);
            tbPersonal.setBackground(Color.white);
            btnAgregarPersonal.setEnabled(true);
            btnQuitarPersonal.setEnabled(true);
            //Insumos
            tbInsumo.setEnabled(true);
            tbInsumo.setBackground(Color.white);
            btnAgregarInsumo.setEnabled(true);
            btnQuitarInsumo.setEnabled(true);
            //Servicios Basicos
            tbServiciosBasicos.setEnabled(true);
            tbServiciosBasicos.setBackground(Color.white);
            btnAgregarSBasicos.setEnabled(true);
            btnQuitarSBasicos.setEnabled(true);
            //Herramienta
            tbHerramienta.setEnabled(true);
            tbHerramienta.setBackground(Color.white);
            btnAgregarHerramientas.setEnabled(true);
            btnQuitarHerramienta.setEnabled(true);
            //Infra
            tbInfraestructura.setEnabled(true);
            tbInfraestructura.setBackground(Color.white);
            btnAgregarInfraestruc.setEnabled(true);
            btnQuitarInfraes.setEnabled(true);
            //S Administ
            tbServiciosAdministr.setEnabled(true);
            tbServiciosAdministr.setBackground(Color.white);
            btnAgregarSAdminis.setEnabled(true);
            btnQuitarSAdminis.setEnabled(true);
            //S Generales
            tbServiciosGenerales.setEnabled(true);
            tbServiciosGenerales.setBackground(Color.white);
            btnAgregarServiciosGenerales.setEnabled(true);
            btnQuitarServiciosGenerales.setEnabled(true);
            //S Intermedios
            tbServiciosIntermedios.setEnabled(true);
            tbServiciosIntermedios.setBackground(Color.white);
            btnAgregarServiciosInterm.setEnabled(true);
            btnQuitarServiciosInterm.setEnabled(true);
        }
        
    public void guardarDetallePersonal(){
        if(tbPersonal.getRowCount()==0){
               FP_Personal rp0=new FP_Personal();
               rp0.setCod_sustento_costo(txtCodigoSustento.getText());
               CTipoSustentacion ct=new CTipoSustentacion();
               rp0.setCod_tipo_susten(ct.obtenerCodTipo("RECURSOS HUMANOS"));
               rp0.setCod_sueldo_bru("");
               rp0.setTiempo_hora_susten(0);
               rp0.setTiempo_min_susten(0);
               rp0.setHora_mes_susten(0);
               rp0.setSueldo_susten(0);
               rp0.setTotal_susten(0);
               rp0.setNom_usu(lblUsu.getText());
                rp0.guardarFP_Personal();
                
        }
        else{
         for (int i = 0; i < tbPersonal.getRowCount(); i++){      
               FP_Personal rp=new FP_Personal();
               rp.setCod_sustento_costo(txtCodigoSustento.getText());
               rp.setCod_tipo_susten(tbPersonal.getValueAt(i, 0).toString());
               rp.setCod_sueldo_bru(tbPersonal.getValueAt(i, 1).toString());
               rp.setTiempo_hora_susten(Integer.parseInt(tbPersonal.getValueAt(i, 4).toString()));
               rp.setTiempo_min_susten(Integer.parseInt(tbPersonal.getValueAt(i, 5).toString()));
               rp.setHora_mes_susten(Integer.parseInt(tbPersonal.getValueAt(i, 6).toString()));
               rp.setSueldo_susten(Double.parseDouble(tbPersonal.getValueAt(i, 7).toString()));
               rp.setTotal_susten(Double.parseDouble(tbPersonal.getValueAt(i, 8).toString()));
               rp.setNom_usu(lblUsu.getText());
                rp.guardarFP_Personal();
                
         }}}
    public void guardarDetalleInsumo(){
        if(tbInsumo.getRowCount()==0){
               FP_Insumos ri0=new FP_Insumos();
               ri0.setCod_sustento_costo(txtCodigoSustento.getText());
               CTipoSustentacion ct=new CTipoSustentacion();
               ri0.setCod_tipo_susten(ct.obtenerCodTipo("INSUMOS"));
               ri0.setCod_prod_refe("");
               ri0.setConsumible("N");
               ri0.setCantidad_susten(0.0);
               ri0.setRendimiento_um(0.0);
               ri0.setUm("");
               ri0.setCantidad_um(0.0);
               ri0.setPrecio_susten(0.0);
               ri0.setTotal_susten(0.0);
               ri0.setNom_usu(lblUsu.getText());
               ri0.guardarFP_Insumo();
                
        }
        else{
         for (int i = 0; i < tbInsumo.getRowCount(); i++){      
               FP_Insumos ri=new FP_Insumos();
               ri.setCod_sustento_costo(txtCodigoSustento.getText());
               ri.setCod_tipo_susten(tbInsumo.getValueAt(i, 0).toString());
               ri.setCod_prod_refe(tbInsumo.getValueAt(i, 1).toString());
               ri.setConsumible(tbInsumo.getValueAt(i, 2).toString());
               ri.setCantidad_susten(Double.parseDouble(tbInsumo.getValueAt(i, 5).toString()));
               ri.setRendimiento_um(Double.parseDouble(tbInsumo.getValueAt(i, 6).toString()));
               ri.setUm(tbInsumo.getValueAt(i, 7).toString());
               ri.setCantidad_um(Double.parseDouble(tbInsumo.getValueAt(i, 8).toString()));
               ri.setPrecio_susten(Double.parseDouble(tbInsumo.getValueAt(i, 9).toString()));
               ri.setTotal_susten(Double.parseDouble(tbInsumo.getValueAt(i, 10).toString()));
               ri.setNom_usu(lblUsu.getText());
                ri.guardarFP_Insumo();
         }}}
    
    public void guardarDetalleServiciosBasicos(){
        if(tbServiciosBasicos.getRowCount()==0){
               FP_SBasicos rsb0=new FP_SBasicos();
               rsb0.setCod_sustento_costo(txtCodigoSustento.getText());
               rsb0.setCod_tipo_susten("");
               rsb0.setCod_servi_va("");
               rsb0.setArea(0.0);
               rsb0.setTotal_consultas(0.0);
               rsb0.setPonderacion_cons_energia(0.0);
               rsb0.setBase_asig_cons_energia(0.0);
               rsb0.setSum_base_asig_cons_energia(0.0);
               rsb0.setCoefic_cons_energia(0.0);
               rsb0.setConsumo_energia(0.0);
               rsb0.setCosto_estandar_energia(0.0);
               rsb0.setPonderacion_cons_agua(0.0);
               rsb0.setBase_asig_cons_agua(0.0);
               rsb0.setSum_base_asig_cons_agua(0.0);
               rsb0.setCoefic_cons_agua(0.0);
               rsb0.setConsumo_agua(0.0);
               rsb0.setCosto_estandar_agua(0.0);
               rsb0.setCosto_estandar_total(0.0);
               rsb0.setNom_usu(lblUsu.getText());
               rsb0.guardarCostosServiciosBasicos();
                
        }
        else{
         for (int i = 0; i < tbServiciosBasicos.getRowCount(); i++){      
               FP_SBasicos rsb=new FP_SBasicos();
               rsb.setCod_sustento_costo(txtCodigoSustento.getText());
               rsb.setCod_tipo_susten(tbServiciosBasicos.getValueAt(i, 0).toString());
               rsb.setCod_servi_va(tbServiciosBasicos.getValueAt(i, 1).toString());
               rsb.setArea(Double.parseDouble(tbServiciosBasicos.getValueAt(i, 4).toString()));
               rsb.setTotal_consultas(Double.parseDouble(tbServiciosBasicos.getValueAt(i, 19).toString()));
               rsb.setPonderacion_cons_energia(Double.parseDouble(tbServiciosBasicos.getValueAt(i, 5).toString()));
               rsb.setBase_asig_cons_energia(Double.parseDouble(tbServiciosBasicos.getValueAt(i, 6).toString()));
               rsb.setSum_base_asig_cons_energia(Double.parseDouble(tbServiciosBasicos.getValueAt(i, 7).toString()));
               rsb.setCoefic_cons_energia(Double.parseDouble(tbServiciosBasicos.getValueAt(i, 8).toString()));
               rsb.setConsumo_energia(Double.parseDouble(tbServiciosBasicos.getValueAt(i, 9).toString()));
               rsb.setCosto_estandar_energia(Double.parseDouble(tbServiciosBasicos.getValueAt(i, 11).toString()));
               rsb.setPonderacion_cons_agua(Double.parseDouble(tbServiciosBasicos.getValueAt(i, 12).toString()));
               rsb.setBase_asig_cons_agua(Double.parseDouble(tbServiciosBasicos.getValueAt(i, 13).toString()));
               rsb.setSum_base_asig_cons_agua(Double.parseDouble(tbServiciosBasicos.getValueAt(i, 14).toString()));
               rsb.setCoefic_cons_agua(Double.parseDouble(tbServiciosBasicos.getValueAt(i, 15).toString()));
               rsb.setConsumo_agua(Double.parseDouble(tbServiciosBasicos.getValueAt(i, 16).toString()));
               rsb.setCosto_estandar_agua(Double.parseDouble(tbServiciosBasicos.getValueAt(i, 18).toString()));
               rsb.setCosto_estandar_total(Double.parseDouble(tbServiciosBasicos.getValueAt(i, 20).toString()));
               rsb.setNom_usu(lblUsu.getText());
                rsb.guardarCostosServiciosBasicos();
         }}}
    
    
    public void guardarDetalleHerramienta(){
        if(tbHerramienta.getRowCount()==0){
               FP_Herramientas rh0=new FP_Herramientas();
               rh0.setCod_sustento_costo(txtCodigoSustento.getText());
               CTipoSustentacion ct=new CTipoSustentacion();
               rh0.setCod_tipo_susten(ct.obtenerCodTipo("EQUIPAMIENTO BASICO"));
               rh0.setCod_depreciacion("");
               rh0.setNom_usu(lblUsu.getText());
               rh0.guardarFP_Herramienta();
                
        }
        else{
         for (int i = 0; i < tbHerramienta.getRowCount(); i++){      
               FP_Herramientas rh=new FP_Herramientas();
               rh.setCod_sustento_costo(txtCodigoSustento.getText());
               rh.setCod_tipo_susten(tbHerramienta.getValueAt(i, 0).toString());
               rh.setCod_depreciacion(tbHerramienta.getValueAt(i, 1).toString());
               rh.setNom_usu(lblUsu.getText());
               rh.guardarFP_Herramienta();
         }}}
    
    public void guardarDetalleInfra(){
        try{
        if(tbInfraestructura.getRowCount()==0){
               FP_Infraestructura rin0=new FP_Infraestructura();
               rin0.setCod_sustento_costo(txtCodigoSustento.getText());
               CTipoSustentacion ct=new CTipoSustentacion();
               rin0.setCod_tipo_susten(ct.obtenerCodTipo("INFRAESTRUCTURA"));
               rin0.setCod_servi_va("");
               rin0.setTiempo_vida_util(0);
               rin0.setArea_total(0.0);
               rin0.setCosto_total(0.0);
               rin0.setRequerimiento_area(0.0);
               rin0.setValor_unitario_dep(0.0);
               rin0.setCosto_construccion(0.0);
               rin0.setDep_infra_min(0.0);
               rin0.setTiempo_hora_proc(0);
               rin0.setTiempo_min_proc(0);
               rin0.setCosto_estandar(0.0);
               rin0.setNom_usu(lblUsu.getText());
               rin0.guardarFP_Infraestructura();
                
        }
        else{
         for (int i = 0; i < tbInfraestructura.getRowCount(); i++){      
               FP_Infraestructura rin=new FP_Infraestructura();
               rin.setCod_sustento_costo(txtCodigoSustento.getText());
               rin.setCod_tipo_susten(tbInfraestructura.getValueAt(i, 0).toString());
               rin.setCod_servi_va(tbInfraestructura.getValueAt(i, 1).toString());
               rin.setTiempo_vida_util(Integer.parseInt(tbInfraestructura.getValueAt(i, 9).toString()));
               rin.setArea_total(Double.parseDouble(tbInfraestructura.getValueAt(i, 4).toString()));
               rin.setCosto_total(Double.parseDouble(tbInfraestructura.getValueAt(i, 5).toString()));
               rin.setRequerimiento_area(Double.parseDouble(tbInfraestructura.getValueAt(i, 6).toString()));
               rin.setValor_unitario_dep(Double.parseDouble(tbInfraestructura.getValueAt(i, 7).toString()));
                
               rin.setCosto_construccion(Double.parseDouble(tbInfraestructura.getValueAt(i, 8).toString()));
               rin.setDep_infra_min(Double.parseDouble(tbInfraestructura.getValueAt(i, 10).toString()));
               rin.setTiempo_hora_proc(Integer.parseInt(tbInfraestructura.getValueAt(i, 11).toString()));
               rin.setTiempo_min_proc(Integer.parseInt(tbInfraestructura.getValueAt(i, 12).toString()));
               rin.setCosto_estandar(Double.parseDouble(tbInfraestructura.getValueAt(i, 13).toString()));
               rin.setNom_usu(lblUsu.getText());
               rin.guardarFP_Infraestructura();
            
         }}
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "inf "+e.getMessage());
        }
        }
    
    public void guardarDetalleServiciosAdmin(){
        if(tbServiciosAdministr.getRowCount()==0){
               FP_Servicios rsa0=new FP_Servicios();
               rsa0.setCod_sustento_costo(txtCodigoSustento.getText());
               CTipoSustentacion ct=new CTipoSustentacion();
               rsa0.setCod_tipo_susten(ct.obtenerCodTipo("SERVICIOS ADMINISTRATIVOS"));
               rsa0.setCod_servi_va("");
               rsa0.setCantidad_susten(0.0);
               rsa0.setPrecio_susten(0.0);
               rsa0.setTotal_susten(0.0);
               rsa0.setNom_usu(lblUsu.getText());
               rsa0.guardarFP_Servicios();
                
        }
        else{
         for (int i = 0; i < tbServiciosAdministr.getRowCount(); i++){      
               FP_Servicios rsa=new FP_Servicios();
               rsa.setCod_sustento_costo(txtCodigoSustento.getText());
               rsa.setCod_tipo_susten(tbServiciosAdministr.getValueAt(i, 0).toString());
               rsa.setCod_servi_va(tbServiciosAdministr.getValueAt(i, 1).toString());
               rsa.setCantidad_susten(Double.parseDouble(tbServiciosAdministr.getValueAt(i, 4).toString()));
               rsa.setPrecio_susten(Double.parseDouble(tbServiciosAdministr.getValueAt(i, 5).toString()));
               rsa.setTotal_susten(Double.parseDouble(tbServiciosAdministr.getValueAt(i, 6).toString()));
               rsa.setNom_usu(lblUsu.getText());
               rsa.guardarFP_Servicios();
        }}}
    public void guardarDetalleServiciosGener(){
        if(tbServiciosGenerales.getRowCount()==0){
               FP_Servicios rsg0=new FP_Servicios();
               rsg0.setCod_sustento_costo(txtCodigoSustento.getText());
               CTipoSustentacion ct=new CTipoSustentacion();
               rsg0.setCod_tipo_susten(ct.obtenerCodTipo("SERVICIOS GENERALES"));
               rsg0.setCod_servi_va("");
               rsg0.setCantidad_susten(0.0);
               rsg0.setPrecio_susten(0.0);
               rsg0.setTotal_susten(0.0);
               rsg0.setNom_usu(lblUsu.getText());
               rsg0.guardarFP_Servicios();
                
        }
        else{
         for (int i = 0; i < tbServiciosGenerales.getRowCount(); i++){      
               FP_Servicios rsg=new FP_Servicios();
               rsg.setCod_sustento_costo(txtCodigoSustento.getText());
               rsg.setCod_tipo_susten(tbServiciosGenerales.getValueAt(i, 0).toString());
               rsg.setCod_servi_va(tbServiciosGenerales.getValueAt(i, 1).toString());
               rsg.setCantidad_susten(Double.parseDouble(tbServiciosGenerales.getValueAt(i, 4).toString()));
               rsg.setPrecio_susten(Double.parseDouble(tbServiciosGenerales.getValueAt(i, 5).toString()));
               rsg.setTotal_susten(Double.parseDouble(tbServiciosGenerales.getValueAt(i, 6).toString()));
               rsg.setNom_usu(lblUsu.getText());
               rsg.guardarFP_Servicios();
        }}}
    public void guardarDetalleServiciosInter(){
        if(tbServiciosIntermedios.getRowCount()==0){
               FP_Servicios rsi0=new FP_Servicios();
               rsi0.setCod_sustento_costo(txtCodigoSustento.getText());
               CTipoSustentacion ct=new CTipoSustentacion();
               rsi0.setCod_tipo_susten(ct.obtenerCodTipo("SERVICIOS INTERMEDIOS"));
               rsi0.setCod_servi_va("");
               rsi0.setCantidad_susten(0.0);
               rsi0.setPrecio_susten(0.0);
               rsi0.setTotal_susten(0.0);
               rsi0.setNom_usu(lblUsu.getText());
               rsi0.guardarFP_Servicios();
                
        }
        else{
         for (int i = 0; i < tbServiciosIntermedios.getRowCount(); i++){      
               FP_Servicios rsi=new FP_Servicios();
               rsi.setCod_sustento_costo(txtCodigoSustento.getText());
               rsi.setCod_tipo_susten(tbServiciosIntermedios.getValueAt(i, 0).toString());
               rsi.setCod_servi_va(tbServiciosIntermedios.getValueAt(i, 1).toString());
               rsi.setCantidad_susten(Double.parseDouble(tbServiciosIntermedios.getValueAt(i, 4).toString()));
               rsi.setPrecio_susten(Double.parseDouble(tbServiciosIntermedios.getValueAt(i, 5).toString()));
               rsi.setTotal_susten(Double.parseDouble(tbServiciosIntermedios.getValueAt(i, 6).toString()));
               rsi.setNom_usu(lblUsu.getText());
               rsi.guardarFP_Servicios();
        }}}
    
    public void mostrarCabecerayDetalle(){
        try {
            int filaselec=tbCostoSusten.getSelectedRow();
            //PERSONAL
            String consulta="";
            tbPersonal.setModel(new DefaultTableModel());
            String titulos[]={"Cod_tipoSust","Cod_Sueldo","Tipo Sustento","Nombre del Detalle","Hora","Min","Horas al Mes","Sueldo","Total Sustento"};
            m3=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m3);
            String fila[]=new String[9];
            Usuario obj=new Usuario();
            consulta="exec COSTOS_COSTOS_SUSTENTACION_DET_PERSONAL_BuscarTodo ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, tbCostoSusten.getValueAt(filaselec, 0).toString());
            ResultSet r= cmd.executeQuery();
            while(r.next()){
            for (int i=0; i<9; i++){
           fila[i]=r.getString(i+1);
       }
                m3.addRow(fila);
            }
            tbPersonal.setModel(m3);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m3);
            tbPersonal.setRowSorter(elQueOrdena);
            this.tbPersonal.setModel(m3);
            
            //INSUMOS
            String consultai="";
            tbInsumo.setModel(new DefaultTableModel());
            String titulosi[]={"Cod_tipoSust","Cod_Prod_Refe","Consumible","Tipo Sustento","Nombre del Producto","Cantidad Sustento","Rendimiento","UM","Cantidad UM","Precio Sustento","Total Sustento"};
            m=new DefaultTableModel(null,titulosi);
            JTable pi=new JTable(m);
            String filai[]=new String[11];
            consultai="exec COSTOS_COSTOS_SUSTENTACION_DET_PROD_INSU_buscar ?";
            PreparedStatement cmdi = obj.getCn().prepareStatement(consultai);
            cmdi.setString(1, tbCostoSusten.getValueAt(filaselec, 0).toString());
            ResultSet ri= cmdi.executeQuery();
            while(ri.next()){
            for (int i=0; i<11; i++){
           filai[i]=ri.getString(i+1);
       }
                m.addRow(filai);
            }
            tbInsumo.setModel(m);
            TableRowSorter<TableModel> elQueOrdenai=new TableRowSorter<TableModel>(m);
            tbInsumo.setRowSorter(elQueOrdenai);
            this.tbInsumo.setModel(m);
            
            //SERVICIOS BASICOS
            String consultasb="";
            tbServiciosBasicos.setModel(new DefaultTableModel());
            String titulosb[]={"Código_Factor de Prod.","Cod_Servicio","Factores de Producción","Servicio","Área",
            "Ponderación de Energía","Base de Asignacion Energía","Sumatoria Base Asig. Energía",
            "Coeficiente de Consumo Energía","Consumo de Energía","Importe Energía Electrica","Importe por Consulta - Energía",
            "Ponderación de Consumo Agua","Base de Asig. Consumo Agua","Sumatoria Base Asig. Agua",
            "Coeficiente de Consumo Agua","Consumo de Agua","Importe Consumo de Agua","Importe por Consulta - Agua",
            "Total de Consultas","Costo Estandar"};
            msb=new DefaultTableModel(null,titulosb);
            JTable psb=new JTable(msb);
            String filasb[]=new String[21];
            consultasb="exec COSTOS_COSTOS_SUSTENTACION_DET_SERVICIOS_BASICOS_Buscar ?";
            PreparedStatement cmdsb = obj.getCn().prepareStatement(consultasb);
            cmdsb.setString(1, tbCostoSusten.getValueAt(filaselec, 0).toString());
            ResultSet rsb= cmdsb.executeQuery();
            while(rsb.next()){
            for (int i=0; i<21; i++){
           filasb[i]=rsb.getString(i+1);
       }
                msb.addRow(filasb);
            }
            tbServiciosBasicos.setModel(msb);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(msb);
            tbServiciosBasicos.setRowSorter(elQueOrdenasb);
            this.tbServiciosBasicos.setModel(msb);
            
            //HERRAMIENTAS
            String consultah="";
            tbHerramienta.setModel(new DefaultTableModel());
            String titulosh[]={"Cod_tipoSust","Cod_Depre","Tipo Sustento","Nombre del Detalle","Codigo Patrimonial","Año de Compra","Valor de Compra",
            "Fecha Fin a Depreciar","Vida Util(meses)","Depreciacion Mensual","Total de Meses a Depreciar","Depreciacion Acumulada","Valor Neto","Depreciacion Diaria"};
            m1=new DefaultTableModel(null,titulosh);
            JTable ph=new JTable(m1);
            String filah[]=new String[14];
            consultah="exec COSTOS_COSTOS_SUSTENTACION_MAQUI_HERRA_buscar ?";
            PreparedStatement cmdh = obj.getCn().prepareStatement(consultah);
            cmdh.setString(1, tbCostoSusten.getValueAt(filaselec, 0).toString());
            ResultSet rh= cmdh.executeQuery();
            while(rh.next()){
            for (int i=0; i<14; i++){
           filah[i]=rh.getString(i+1);
       }
                m1.addRow(filah);
            }
            tbHerramienta.setModel(m1);
            TableRowSorter<TableModel> elQueOrdenah=new TableRowSorter<TableModel>(m1);
            tbHerramienta.setRowSorter(elQueOrdenah);
            this.tbHerramienta.setModel(m1);
            
            //INFRAESTRUCTURA
            String consultaii="";
            tbInfraestructura.setModel(new DefaultTableModel());
            String titulosii[]={"Cod_tipoSust","Cod_Servicio","Tipo Sustento","Nombre del Detalle","Area Total","Costo Total","Requerimiento mínimo de Area","Valor Unitario Depr.",
                "Costo de Construccion","Tiempo de Rendimiento(min)","Depreciación de Infraestructura","Tiempo(Hora)","Tiempo(min)","Costo Estándar"};
            mi=new DefaultTableModel(null,titulosii);
            JTable pii=new JTable(mi);
            String filaii[]=new String[14];
            consultaii="exec COSTOS_COSTOS_SUSTENTACION_DET_INFRAESTRUCTURA_buscar ?";
            PreparedStatement cmdii = obj.getCn().prepareStatement(consultaii);
            cmdii.setString(1, tbCostoSusten.getValueAt(filaselec, 0).toString());
            ResultSet rii= cmdii.executeQuery();
            while(rii.next()){
            for (int i=0; i<14; i++){
           filaii[i]=rii.getString(i+1);
       }
                mi.addRow(filaii);
            }
            tbInfraestructura.setModel(mi);
            TableRowSorter<TableModel> elQueOrdenaii=new TableRowSorter<TableModel>(mi);
            tbInfraestructura.setRowSorter(elQueOrdenaii);
            this.tbInfraestructura.setModel(mi);
            
            //SERVICIOS ADMINISTRATIVOS
            String consultasa="";
            tbServiciosAdministr.setModel(new DefaultTableModel());
            String titulossa[]={"Cod_tipoSust","Cod_Servicio","Tipo Sustento","Nombre del Detalle","Cantidad Sustento","Precio Sustento","Total Sustento"};
            msa=new DefaultTableModel(null,titulossa);
            JTable psa=new JTable(msa);
            String filasa[]=new String[7];
            consultasa="exec COSTOS_COSTOS_SUSTENTACION_DET_SERVICIO_buscarSAdmin ?";
            PreparedStatement cmdsa = obj.getCn().prepareStatement(consultasa);
            cmdsa.setString(1, tbCostoSusten.getValueAt(filaselec, 0).toString());
            ResultSet rsa= cmdsa.executeQuery();
            while(rsa.next()){
            for (int i=0; i<7; i++){
           filasa[i]=rsa.getString(i+1);
       }
                msa.addRow(filasa);
            }
            tbServiciosAdministr.setModel(msa);
            TableRowSorter<TableModel> elQueOrdenasa=new TableRowSorter<TableModel>(msa);
            tbServiciosAdministr.setRowSorter(elQueOrdenasa);
            this.tbServiciosAdministr.setModel(msa);
            
            
            //SERVICIOS GENERALES
            String consultasg="";
            tbServiciosGenerales.setModel(new DefaultTableModel());
            String titulossg[]={"Cod_tipoSust","Cod_Servicio","Tipo Sustento","Nombre del Detalle","Cantidad Sustento","Precio Sustento","Total Sustento"};
            msg=new DefaultTableModel(null,titulossg);
            JTable psg=new JTable(msg);
            String filasg[]=new String[7];
            consultasg="exec COSTOS_COSTOS_SUSTENTACION_DET_SERVICIO_buscarSGen ?";
            PreparedStatement cmdsg = obj.getCn().prepareStatement(consultasg);
            cmdsg.setString(1, tbCostoSusten.getValueAt(filaselec, 0).toString());
            ResultSet rsg= cmdsg.executeQuery();
            while(rsg.next()){
            for (int i=0; i<7; i++){
           filasg[i]=rsg.getString(i+1);
       }
                msg.addRow(filasg);
            }
            tbServiciosGenerales.setModel(msg);
            TableRowSorter<TableModel> elQueOrdenasg=new TableRowSorter<TableModel>(msg);
            tbServiciosGenerales.setRowSorter(elQueOrdenasg);
            this.tbServiciosGenerales.setModel(msg);
            
            //SERVICIOS INTERMEDIOS
            String consultasi="";
            tbServiciosIntermedios.setModel(new DefaultTableModel());
            String titulossi[]={"Cod_tipoSust","Cod_Servicio","Tipo Sustento","Nombre del Detalle","Cantidad Sustento","Precio Sustento","Total Sustento"};
            msi=new DefaultTableModel(null,titulossi);
            JTable psi=new JTable(msi);
            String filasi[]=new String[7];
            consultasi="exec COSTOS_COSTOS_SUSTENTACION_DET_SERVICIO_buscarSInter ?";
            PreparedStatement cmdsi = obj.getCn().prepareStatement(consultasi);
            cmdsi.setString(1, tbCostoSusten.getValueAt(filaselec, 0).toString());
            ResultSet rsi= cmdsi.executeQuery();
            while(rsi.next()){
            for (int i=0; i<7; i++){
           filasi[i]=rsi.getString(i+1);
       }
                msi.addRow(filasi);
            }
            tbServiciosIntermedios.setModel(msi);
            TableRowSorter<TableModel> elQueOrdenasi=new TableRowSorter<TableModel>(msi);
            tbServiciosIntermedios.setRowSorter(elQueOrdenasi);
            this.tbServiciosIntermedios.setModel(msi);
            
            formatoInicializarTabla();
            
            
            
            //PERSONAL Sumar Subtotales
            double totalper=0;
            for (int i = 0; i < tbPersonal.getRowCount(); i++){    
                    totalper=totalper+Double.parseDouble(tbPersonal.getValueAt(i, 8).toString());
                }
                BigDecimal totalperson = new BigDecimal(totalper);
                       totalperson = totalperson.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            txtTotalPersonal.setText(String.valueOf(totalperson));

            //INSUMOS Sumar Subtotales
            double totalin=0;
            for (int i = 0; i < tbInsumo.getRowCount(); i++){    
                    totalin=totalin+Double.parseDouble(tbInsumo.getValueAt(i, 10).toString());
                }
                BigDecimal totalinsumo = new BigDecimal(totalin);
                       totalinsumo = totalinsumo.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            txtTotalInsumos.setText(String.valueOf(totalinsumo));
            
            //SERVICIOS BASICOS Sumar Subtotales
            double totalsB=0,SE=0,SA=0;
            for (int i = 0; i < tbServiciosBasicos.getRowCount(); i++){    
                    totalsB=totalsB+Double.parseDouble(tbServiciosBasicos.getValueAt(i, 20).toString());
                    SE=SE+Double.parseDouble(tbServiciosBasicos.getValueAt(i, 7).toString());
                    SA=SA+Double.parseDouble(tbServiciosBasicos.getValueAt(i, 14).toString());
                }
                BigDecimal totalsbas = new BigDecimal(totalsB);
                       totalsbas = totalsbas.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtTotalSBasicos.setText(String.valueOf(totalsbas));
            BigDecimal BSE = new BigDecimal(SE);
                       BSE = BSE.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtSumatoriaEnergiaBA.setText(String.valueOf(BSE));
            BigDecimal BSA = new BigDecimal(SA);
                       BSA = BSA.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtSumatoriaAguaBA.setText(String.valueOf(BSA));
            
            //HERRAMIENTAS Sumar Subtotales
            double totalher=0;
            for (int i = 0; i < tbHerramienta.getRowCount(); i++){    
                    totalher=totalher+Double.parseDouble(tbHerramienta.getValueAt(i, 13).toString());
                }
                BigDecimal totalherra = new BigDecimal(totalher);
                       totalherra = totalherra.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            txtTotalHerramienta.setText(String.valueOf(totalherra));
               
            //INFRAESTRUCTURA Sumar Subtotales
            double totalinf=0;
            for (int i = 0; i < tbInfraestructura.getRowCount(); i++){    
                    totalinf=totalinf+Double.parseDouble(tbInfraestructura.getValueAt(i, 13).toString());
                }
                BigDecimal totalinfra = new BigDecimal(totalinf);
                       totalinfra = totalinfra.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            txtTotalInfraes.setText(String.valueOf(totalinfra));
            
            //SERVICIOS ADMINISTRATIVOS Sumar Subtotales
            double totalsa=0;
            for (int i = 0; i < tbServiciosAdministr.getRowCount(); i++){    
                    totalsa=totalsa+Double.parseDouble(tbServiciosAdministr.getValueAt(i, 6).toString());
                }
                BigDecimal totalsadmin = new BigDecimal(totalsa);
                       totalsadmin = totalsadmin.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            txtTotalSAdminis.setText(String.valueOf(totalsadmin));
            //SERVICIOS GENERALES Sumar Subtotales
            double totalsg=0;
            for (int i = 0; i < tbServiciosGenerales.getRowCount(); i++){    
                    totalsg=totalsg+Double.parseDouble(tbServiciosGenerales.getValueAt(i, 6).toString());
                }
                BigDecimal totalsgen = new BigDecimal(totalsg);
                       totalsgen = totalsgen.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            txtTotalSGenerales.setText(String.valueOf(totalsgen));
            
            //SERVICIOS INTERMEDIOS Sumar Subtotales
            double totalsi=0;
            for (int i = 0; i < tbServiciosIntermedios.getRowCount(); i++){    
                    totalsi=totalsi+Double.parseDouble(tbServiciosIntermedios.getValueAt(i, 6).toString());
                }
                BigDecimal totalsinter = new BigDecimal(totalsi);
                       totalsinter = totalsinter.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            txtTotalSIntermedios.setText(String.valueOf(totalsinter));
            
             //Resumen
            tbResumenCostos.setModel(new DefaultTableModel());
            String titulos2[]={"Tipo Sustentación","Valor Total"};
            m2=new DefaultTableModel(null,titulos2);
            JTable p2=new JTable(m2);
            String filar[]=new String[2];
            for (int i=0; i<8; i++){
                if(i==0){
                filar[0]="RECURSO HUMANO";
                filar[1]=txtTotalPersonal.getText();
                }
                else if(i==1){
                filar[0]="INSUMOS";
                filar[1]=txtTotalInsumos.getText();
                } 
                else if(i==2){
                filar[0]="SERVICIOS BÁSICOS";
                filar[1]=txtTotalSBasicos.getText();
                } 
                else if(i==3){
                filar[0]="EQUIPAMIENTO BÁSICO";
                filar[1]=txtTotalHerramienta.getText();
                } 
                else if(i==4){
                filar[0]="INFRAESTRUCTURA";
                filar[1]=txtTotalInfraes.getText();
                } 
                else if(i==5){
                filar[0]="SERVICIOS ADMINISTRATIVOS";
                filar[1]=txtTotalSAdminis.getText();
                } 
                else if(i==6){
                filar[0]="SERVICIOS GENERALES";
                filar[1]=txtTotalSGenerales.getText();
                } 
                else if(i==7){
                filar[0]="GASTOS INTERMEDIOS";
                filar[1]=txtTotalSIntermedios.getText();
                } 
                m2.addRow(filar);
                }
                
                tbResumenCostos.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena2=new TableRowSorter<TableModel>(m2);
            tbResumenCostos.setRowSorter(elQueOrdena2);
            tbResumenCostos.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbResumenCostos.getColumnModel().getColumn(1).setPreferredWidth(120);
            //Total
            Double insumo,pe,he,sb,inf,sg,sa,si;
            pe=Double.parseDouble(txtTotalPersonal.getText());
            he=Double.parseDouble(txtTotalHerramienta.getText());
            insumo=Double.parseDouble(txtTotalInsumos.getText());
            sb=Double.parseDouble(txtTotalSBasicos.getText());
            inf=Double.parseDouble(txtTotalInfraes.getText());
            sa=Double.parseDouble(txtTotalSAdminis.getText());
            sg=Double.parseDouble(txtTotalSGenerales.getText());
            si=Double.parseDouble(txtTotalSIntermedios.getText());
            Double precioTotal=pe+he+insumo+sb+inf+sa+sg+si;
            BigDecimal ptotal = new BigDecimal(precioTotal);
                        ptotal = ptotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtPrecioTotal.setText(String.valueOf(ptotal));
            /*Ganancia
            double gan=Double.parseDouble(txtPrecio.getText())-precioTotal;
            BigDecimal gtotal = new BigDecimal(gan);
                       gtotal = gtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtGanancia.setText(String.valueOf(gtotal));
            */
            //Todo
            String codigo=tbCostoSusten.getValueAt(filaselec, 0).toString();
            txtCodigoSustento.setText(codigo);
            txtCodigo6.setText(codigo.substring(14, 20));
            txtCodigoPrecio.setText(tbCostoSusten.getValueAt(filaselec, 1).toString());
            txtCodigoCPT.setText(tbCostoSusten.getValueAt(filaselec, 2).toString());
            txtServicio.setText(tbCostoSusten.getValueAt(filaselec, 3).toString());
            txtSubServicio.setText(tbCostoSusten.getValueAt(filaselec,4).toString());
            txtFormadePago.setText(tbCostoSusten.getValueAt(filaselec,5).toString());
            txtPrecio.setText(tbCostoSusten.getValueAt(filaselec,6).toString());
            txtPrecio1.setText(tbCostoSusten.getValueAt(filaselec,6).toString());
            spHora.setValue(Integer.parseInt(tbCostoSusten.getValueAt(filaselec,7).toString()));
            spMin.setValue(Integer.parseInt(tbCostoSusten.getValueAt(filaselec,8).toString()));
            txtGanancia.setText(tbCostoSusten.getValueAt(filaselec,9).toString());
            if(Double.parseDouble(tbCostoSusten.getValueAt(filaselec,9).toString())>0){
                lblGananciaPer.setForeground(Color.black);
                txtGananciaPerdida.setForeground(Color.black);
                lblGananciaPer.setText("Ganancia Total");
            txtGananciaPerdida.setText(tbCostoSusten.getValueAt(filaselec,9).toString());
            }else{
                lblGananciaPer.setText("Pérdida Total");
                double ganper=-Double.parseDouble(tbCostoSusten.getValueAt(filaselec,9).toString());
                lblGananciaPer.setForeground(Color.red);
                txtGananciaPerdida.setForeground(Color.red);
            txtGananciaPerdida.setText(String.valueOf(ganper));
            }
            txtNomenclatura.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+tbCostoSusten.getValueAt(filaselec,10).toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
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
    
    
    public void formato(){
       tbNomenclatura.getColumnModel().getColumn(0).setPreferredWidth(150);
       tbNomenclatura.getColumnModel().getColumn(1).setPreferredWidth(150);
       tbNomenclatura.getColumnModel().getColumn(2).setPreferredWidth(100);
       tbNomenclatura.getColumnModel().getColumn(3).setPreferredWidth(150);
       tbNomenclatura.getColumnModel().getColumn(4).setPreferredWidth(150);
       tbNomenclatura.getColumnModel().getColumn(5).setPreferredWidth(200);
       tbNomenclatura.getColumnModel().getColumn(6).setPreferredWidth(200);
    }
 
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
            java.util.logging.Logger.getLogger(Costos_Sustentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Costos_Sustentacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BuscarNomenclatura;
    public static javax.swing.JDialog Costos_Sustentacion;
    public static javax.swing.JButton btnAgregarHerramientas;
    private javax.swing.JButton btnAgregarHerramientas1;
    public static javax.swing.JButton btnAgregarInfraestruc;
    public static javax.swing.JButton btnAgregarInsumo;
    public static javax.swing.JButton btnAgregarPersonal;
    public static javax.swing.JButton btnAgregarSAdminis;
    public static javax.swing.JButton btnAgregarSBasicos;
    public static javax.swing.JButton btnAgregarServiciosGenerales;
    public static javax.swing.JButton btnAgregarServiciosInterm;
    private javax.swing.JButton btnBuscar;
    public static javax.swing.JButton btnBuscarCPT;
    private javax.swing.JButton btnBuscarCosto;
    public static javax.swing.JButton btnBuscarTodo;
    public static javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnGrabar;
    public static javax.swing.JButton btnModificar;
    public static javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btnQuitarHerramienta;
    private javax.swing.JButton btnQuitarHerramienta1;
    public static javax.swing.JButton btnQuitarInfraes;
    public static javax.swing.JButton btnQuitarInsumo;
    public static javax.swing.JButton btnQuitarPersonal;
    public static javax.swing.JButton btnQuitarSAdminis;
    public static javax.swing.JButton btnQuitarSBasicos;
    public static javax.swing.JButton btnQuitarServiciosGenerales;
    public static javax.swing.JButton btnQuitarServiciosInterm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JTextArea jTextArea8;
    private javax.swing.JTextArea jTextArea9;
    private javax.swing.JLabel lblFechaPersonal;
    public static javax.swing.JLabel lblGananciaPer;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JLabel lblhPersonal;
    public static javax.swing.JPanel pnlCPT;
    public static javax.swing.JSpinner spHora;
    public static javax.swing.JSpinner spMin;
    public static javax.swing.JTable tbCostoSusten;
    public static javax.swing.JTable tbHerramienta;
    public static javax.swing.JTable tbInfraestructura;
    public static javax.swing.JTable tbInsumo;
    public static javax.swing.JTable tbNomenclatura;
    public static javax.swing.JTable tbPersonal;
    public static javax.swing.JTable tbResumenCostos;
    public static javax.swing.JTable tbServiciosAdministr;
    public static javax.swing.JTable tbServiciosBasicos;
    public static javax.swing.JTable tbServiciosGenerales;
    public static javax.swing.JTable tbServiciosIntermedios;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarCosto;
    public static javax.swing.JTextField txtCodigo6;
    public static javax.swing.JTextField txtCodigoCPT;
    public static javax.swing.JTextField txtCodigoPrecio;
    public static javax.swing.JTextField txtCodigoSustento;
    public static javax.swing.JTextField txtConsumo_MA;
    public static javax.swing.JTextField txtConsumo_ME;
    public static javax.swing.JTextField txtFormadePago;
    public static javax.swing.JTextField txtGanancia;
    public static javax.swing.JTextField txtGananciaPerdida;
    private javax.swing.JTextField txtGuarModif;
    public static javax.swing.JTextArea txtNomenclatura;
    public static javax.swing.JTextField txtPrecio;
    public static javax.swing.JTextField txtPrecio1;
    public static javax.swing.JTextField txtPrecioTotal;
    public static javax.swing.JTextField txtServicio;
    public static javax.swing.JTextField txtSubServicio;
    public static javax.swing.JTextField txtSumatoriaAguaBA;
    public static javax.swing.JTextField txtSumatoriaEnergiaBA;
    public static javax.swing.JTextField txtTotalHerramienta;
    public static javax.swing.JTextField txtTotalInfraes;
    public static javax.swing.JTextField txtTotalInsumos;
    public static javax.swing.JTextField txtTotalPersonal;
    public static javax.swing.JTextField txtTotalSAdminis;
    public static javax.swing.JTextField txtTotalSBasicos;
    public static javax.swing.JTextField txtTotalSGenerales;
    public static javax.swing.JTextField txtTotalSIntermedios;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            lblhPersonal.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
          } 
    }
}
