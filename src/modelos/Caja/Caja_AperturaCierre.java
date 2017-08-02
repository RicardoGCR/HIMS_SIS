/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.Caja;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import servicios.Conexion;
import vista.Caja.Caja_Apertura;
import vista.Caja.Caja_Cierre;
import vista.Caja.Caja_Pagos;
import vista.PrincipalMDI;
/**
/**
 *
 * @author Ricardo
 */
public class Caja_AperturaCierre {
private Connection cn;
private String Cajero ;
private String Caja ;
private String BASE ;

/////////PARA EL CIERRE
private int Id_Apertura;
private String CAJERO_CIERRE;
private int TERMINAL;
private String MONTO_CIERRE_C;
private String MONTO_CIERRE_O;
private String MONTO_CIERRE_A;


        
Conexion con = new Conexion();
static DefaultTableModel m;
public boolean NUEVO()
        {
        boolean resp = false;
        try{
            String sql = "EXEC CAJA_APERTURA_CAJA ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            
            cmd.setString(1, getCajero());
            cmd.setString(2, getCaja());
            cmd.setString(3, getBASE());
            cmd.setInt(4, getId_Apertura());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error : " + ex.getMessage());
        }
        return resp;
    }
public String PreventaID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 idApertura FROM CAJA_APERTURA WHERE pc = HOST_NAME() and estado='A' ORDER BY idApertura DESC";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               Caja_Apertura.lblID.setText(rs.getString(1));
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: PreventaID: " + ex.getMessage());
        }
        return cod;
    }
public void reporteAperura(int id,String Usu) {
        try {
            Map parametros = new HashMap();
            parametros.put("id",id);
            parametros.put("Usu",Usu);
           JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/cajaCentral/Apertura.jasper"), parametros, con.conectar());   
            JasperPrintManager.printReport(informe, false);
            } catch (Exception e) {
                System.out.println("ERROR AL IMPRIMIR");
                
            }
    } 

public void reporteCierre(int SESION) {
        try {
            Map parametros = new HashMap();
            parametros.put("SESION",SESION);
           JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/cajaCentral/ReporteCierre.jasper"), parametros, con.conectar());   
            JasperPrintManager.printReport(informe, false);
            } catch (Exception e) {
                System.out.println("ERROR AL IMPRIMIR");
                
            }
    } 


public boolean CIERRE(){
        boolean resp = false;
        try
        {
            String sql = "Exec CAJA_CIERRE_CAJA ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_Apertura());
            cmd.setString(2, getCAJERO_CIERRE());
            cmd.setInt(3, getTERMINAL());
            cmd.setString(4, getMONTO_CIERRE_C());
            cmd.setString(5, getMONTO_CIERRE_O());
            cmd.setString(6, getMONTO_CIERRE_A());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error " + ex.getMessage());
        }
        return resp;
    }

public String Apertura(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec CAJA_Apertura";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error " + ex.getMessage());
        }
        return id;
    }

public void CajaPC_NRO(String usu){
        String consulta="";
        try {
            consulta="CAJA_PC_SERIE_APERTURA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Apertura.lblCorrelativo.setText(r.getString(2)); 
                Caja_Apertura.lblTerminal.setText(r.getString(1)); 
                System.out.println("COREE   "+r.getString(2));
                System.out.println("PC   "+r.getString(1));


                }
            //
        } catch (Exception e) {
            System.out.println("Error: PC: " + e.getMessage());
        }
    }
public void CajaPC_NRO_CIERRE(String usu){
        String consulta="";
        try {
            consulta="CAJA_PC_SERIE_APERTURA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Cierre.lblCorrelativo.setText(r.getString(2)); 
                Caja_Cierre.lblTerminal.setText(r.getString(1)); 

                }
            //
        } catch (Exception e) {
            System.out.println("Error: PC: " + e.getMessage());
        }
    }

public void CajaID_SESION(String usu){
        String consulta="";
        try {
            consulta="CAJA_ID_SESION_APERTURA_CAJA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                PrincipalMDI.lblIDSESION.setText(r.getString(1)); 
                PrincipalMDI.ibiIDAPERTURA.setText(r.getString(2)); 

                }
            //
        } catch (Exception e) {
            System.out.println("Error: ID_SESION: " + e.getMessage());
        }
    }

public void Caja_Verificar_Apertura(String usu,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"id","cajero","conta","Serie",
                "Fecha Apertura","Hora Apertura","Terminal","Base","Estado"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_VERIFICAR_APERTURA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); // 
                fila[7]=r.getString(8); // 
                fila[8]=r.getString(9);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaAperturas(tabla);
        } catch (Exception e) {
            System.out.println("Error: admisionEmergenciaTriajeListarReporte: " + e.getMessage());
        }
    }

public void Caja_Verificar_Apertura_C(String usu,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"id","cajero","conta","Serie",
                "Fecha Apertura","Hora Apertura","Terminal","Base","Estado","ID SESION"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_VERIFICAR_APERTURA_CIERRE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); // 
                fila[7]=r.getString(8); // 
                fila[8]=r.getString(9);
                fila[9]=r.getString(10);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaAperturasS(tabla);
        } catch (Exception e) {
            System.out.println("Error: CARGAR SESION DEL USUARIO: " + e.getMessage());
        }
    }

public void Caja_Verificar_SESIONES(String usu,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"id","cajero","conta","Serie",
                "Fecha Apertura","Hora Apertura","Terminal","Base"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_VERIFICAR_SESION_APERTURADA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); // 
                fila[7]=r.getString(8); // 
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
        } catch (Exception e) {
            System.out.println("Error: CONSULTAR SESIONES: " + e.getMessage());
        }
    }

    public void Caja_Verificar_SESIONES_OTRA_PC(String usu,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"id","cajero","conta","Serie",
                "Fecha Apertura","Hora Apertura","Terminal","Base"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_CERRAR_SESION_DISTINTA_PC ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, usu);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // 
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); // 
                fila[7]=r.getString(8); // 
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaAperturasP(tabla);
        } catch (Exception e) {
            System.out.println("Error: CONSULTAR SESIONES: " + e.getMessage());
        }
    }

    public void formatoTablaAperturasS(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(50);//dni
        tabla.getColumnModel().getColumn(4).setPreferredWidth(100);//paciente
        tabla.getColumnModel().getColumn(5).setPreferredWidth(80);//fecha de ingreso
        tabla.getColumnModel().getColumn(6).setPreferredWidth(80);//hora de ingreso 
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);//traido por
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0);

        TableColumn columna = tabla.getColumnModel().getColumn(0);//
            columna.setMaxWidth(1);
            columna.setMinWidth(1);
            columna.setPreferredWidth(1);
            tabla.doLayout();
        tabla.setRowHeight(38);
    }
    public void formatoTablaAperturasP(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(50);//dni
        tabla.getColumnModel().getColumn(4).setPreferredWidth(100);//paciente
        tabla.getColumnModel().getColumn(5).setPreferredWidth(80);//fecha de ingreso
        tabla.getColumnModel().getColumn(6).setPreferredWidth(80);//hora de ingreso 
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);//traido por


        TableColumn columna = tabla.getColumnModel().getColumn(0);//
            columna.setMaxWidth(1);
            columna.setMinWidth(1);
            columna.setPreferredWidth(1);
            tabla.doLayout();
        tabla.setRowHeight(38);
    }
    public void formatoTablaAperturas(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(50);//dni
        tabla.getColumnModel().getColumn(4).setPreferredWidth(100);//paciente
        tabla.getColumnModel().getColumn(5).setPreferredWidth(80);//fecha de ingreso
        tabla.getColumnModel().getColumn(6).setPreferredWidth(80);//hora de ingreso 
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);//traido por
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);

        TableColumn columna = tabla.getColumnModel().getColumn(0);//
            columna.setMaxWidth(1);
            columna.setMinWidth(1);
            columna.setPreferredWidth(1);
            tabla.doLayout();
        tabla.setRowHeight(38);
    }
 public Caja_AperturaCierre(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getCajero() {
        return Cajero;
    }

    public void setCajero(String Cajero) {
        this.Cajero = Cajero;
    }

    public String getCaja() {
        return Caja;
    }

    public void setCaja(String Caja) {
        this.Caja = Caja;
    }

    public String getBASE() {
        return BASE;
    }

    public void setBASE(String BASE) {
        this.BASE = BASE;
    }

    public int getId_Apertura() {
        return Id_Apertura;
    }

    public void setId_Apertura(int Id_Apertura) {
        this.Id_Apertura = Id_Apertura;
    }

    public String getCAJERO_CIERRE() {
        return CAJERO_CIERRE;
    }

    public void setCAJERO_CIERRE(String CAJERO_CIERRE) {
        this.CAJERO_CIERRE = CAJERO_CIERRE;
    }

    public int getTERMINAL() {
        return TERMINAL;
    }

    public void setTERMINAL(int TERMINAL) {
        this.TERMINAL = TERMINAL;
    }

    public String getMONTO_CIERRE_C() {
        return MONTO_CIERRE_C;
    }

    public void setMONTO_CIERRE_C(String MONTO_CIERRE_C) {
        this.MONTO_CIERRE_C = MONTO_CIERRE_C;
    }

    public String getMONTO_CIERRE_O() {
        return MONTO_CIERRE_O;
    }

    public void setMONTO_CIERRE_O(String MONTO_CIERRE_O) {
        this.MONTO_CIERRE_O = MONTO_CIERRE_O;
    }

    public String getMONTO_CIERRE_A() {
        return MONTO_CIERRE_A;
    }

    public void setMONTO_CIERRE_A(String MONTO_CIERRE_A) {
        this.MONTO_CIERRE_A = MONTO_CIERRE_A;
    }

    

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }
 
 
    
}
