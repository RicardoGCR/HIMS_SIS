/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos.RX;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;

/**
 *
 * @author USUARIO
 */
public class RX_EC_RESULTADO_CAB {
    DefaultTableModel m;
    private Connection cn;
    private int COD_EXAMEN_CAB;
    private String NUMERO_RESULTADO;
    private String COD_PERSONAL_RESULTADO;
    private String NOMBRE_PERSONAL_RESULTADO;
    private String COD_PERSONAL_RESULTADO_REG;
    private String NOMBRE_PERSONAL_RESULTADO_REG;
    private String NOM_USU;
    
    private int SIS;
    private int OTROS;
    private String FECHA;
    private int ID;
    
    public RX_EC_RESULTADO_CAB()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public String RX_EC_resultado_generarNum()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec RX_EC_RESULTADO_GENERAR_NUM";
        ResultSet r;
        r=cn.Listar(consulta);
        if(r.next())
            {
               cod = r.getString(1);
            }
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }
    
    public boolean RX_EC_RESULTADO_GUARDAR(){
        boolean resp = false;
        try
        {
            String sql = "exec RX_EC_RESULTADO_CABECERA_GUARDAR ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCOD_EXAMEN_CAB());
            cmd.setString(2, getNUMERO_RESULTADO());
            cmd.setString(3, getCOD_PERSONAL_RESULTADO());
            cmd.setString(4, getNOMBRE_PERSONAL_RESULTADO());
            cmd.setString(5, getCOD_PERSONAL_RESULTADO_REG());
            cmd.setString(6, getNOMBRE_PERSONAL_RESULTADO_REG());          
            cmd.setString(7, getNOM_USU());
            
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    
    
    public boolean RX_CAJA_NUEVO(){
        boolean resp = false;
        try
        {
            String sql = "exec RX_CAJA_INSERTAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getSIS());
            cmd.setInt(2, getOTROS());
            cmd.setString(3, getFECHA());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean EC_CAJA_NUEVO(){
        boolean resp = false;
        try
        {
            String sql = "exec EC_CAJA_INSERTAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getSIS());
            cmd.setInt(2, getOTROS());
            cmd.setString(3, getFECHA());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean RX_CAJA_ELIMINAR(){
        boolean resp = false;
        try
        {
            String sql = "exec RX_CAJA_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    
    public boolean EC_CAJA_ELIMINAR(){
        boolean resp = false;
        try
        {
            String sql = "exec EC_CAJA_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean RX_CAJA_MODIFICAR(){
        boolean resp = false;
        try
        {
            String sql = "exec RX_CAJA_ACTUALIZAR ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getSIS());
            cmd.setInt(2, getOTROS());
            cmd.setString(3, getFECHA());
            cmd.setInt(4, getID());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean EC_CAJA_MODIFICAR(){
        boolean resp = false;
        try
        {
            String sql = "exec EC_CAJA_ACTUALIZAR ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getSIS());
            cmd.setInt(2, getOTROS());
            cmd.setString(3, getFECHA());
            cmd.setInt(4, getID());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    
    public int CPT_verif(String DIA,String MES){
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM RX_CAJA_PROGRAMACION where DAY(FECHA)=? AND MONTH(FECHA) = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, DIA);
            cmd.setString(2, MES);
            ResultSet rs = cmd.executeQuery();
            for (int i=0; rs.next (); i++)
            {
               resultado++;
            }
            
            cmd.close();
            //getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error verificacion repetidos: " + ex.getMessage());
        }
        return resultado;
    }
    
    public int EC_CPT_verif(String DIA,String MES){
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM EC_CAJA_PROGRAMACION where DAY(FECHA)=? AND MONTH(FECHA) = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, DIA);
            cmd.setString(2, MES);
            ResultSet rs = cmd.executeQuery();
            for (int i=0; rs.next (); i++)
            {
               resultado++;
            }
            
            cmd.close();
            //getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error verificacion repetidos: " + ex.getMessage());
        }
        return resultado;
    }
    
    

    public String RX_EC_ID_CAB_RES()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec RX_EC_ID_CAB_RES_EX";
        ResultSet r;
        r=cn.Listar(consulta);
        if(r.next())
            {
               cod = r.getString(1);
            }
        }catch(Exception ex)
        {
            System.out.println("Error CODIGO: " + ex.getMessage());
        }
        return cod;
    }
    
     public void RX_CAJA_LISTA(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Día","SIS","Otras Formas de Pago","ID","FECHA","SIS","Otras Formas de Pago"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[7];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC RX_CAJA_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            Formato(tabla);
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR : " + e.getMessage());
        }
    }
     
     public void EC_CAJA_LISTA(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Día","SIS","Otras Formas de Pago","ID","FECHA","SIS","Otras Formas de Pago"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[7];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC EC_CAJA_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            Formato(tabla);
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR : " + e.getMessage());
        }
    }
    
    public void Formato(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(600);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(0);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(600);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(600);

        tabla.setRowHeight(40);
    }
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getCOD_EXAMEN_CAB() {
        return COD_EXAMEN_CAB;
    }

    public void setCOD_EXAMEN_CAB(int COD_EXAMEN_CAB) {
        this.COD_EXAMEN_CAB = COD_EXAMEN_CAB;
    }

    public String getNUMERO_RESULTADO() {
        return NUMERO_RESULTADO;
    }

    public void setNUMERO_RESULTADO(String NUMERO_RESULTADO) {
        this.NUMERO_RESULTADO = NUMERO_RESULTADO;
    }

    public String getCOD_PERSONAL_RESULTADO() {
        return COD_PERSONAL_RESULTADO;
    }

    public void setCOD_PERSONAL_RESULTADO(String COD_PERSONAL_RESULTADO) {
        this.COD_PERSONAL_RESULTADO = COD_PERSONAL_RESULTADO;
    }

    public String getNOMBRE_PERSONAL_RESULTADO() {
        return NOMBRE_PERSONAL_RESULTADO;
    }

    public void setNOMBRE_PERSONAL_RESULTADO(String NOMBRE_PERSONAL_RESULTADO) {
        this.NOMBRE_PERSONAL_RESULTADO = NOMBRE_PERSONAL_RESULTADO;
    }

    public String getCOD_PERSONAL_RESULTADO_REG() {
        return COD_PERSONAL_RESULTADO_REG;
    }

    public void setCOD_PERSONAL_RESULTADO_REG(String COD_PERSONAL_RESULTADO_REG) {
        this.COD_PERSONAL_RESULTADO_REG = COD_PERSONAL_RESULTADO_REG;
    }

    public String getNOMBRE_PERSONAL_RESULTADO_REG() {
        return NOMBRE_PERSONAL_RESULTADO_REG;
    }

    public void setNOMBRE_PERSONAL_RESULTADO_REG(String NOMBRE_PERSONAL_RESULTADO_REG) {
        this.NOMBRE_PERSONAL_RESULTADO_REG = NOMBRE_PERSONAL_RESULTADO_REG;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }

    public int getSIS() {
        return SIS;
    }

    public void setSIS(int SIS) {
        this.SIS = SIS;
    }

    public int getOTROS() {
        return OTROS;
    }

    public void setOTROS(int OTROS) {
        this.OTROS = OTROS;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    
}
