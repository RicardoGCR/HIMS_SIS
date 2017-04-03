/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import servicios.Conexion;

/**
 *
 * @author PC02
 */

public class ConsultorioExtRsCcd implements Serializable {
    private static final long serialVersionUID = 1L;
     static DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int rsCcd;
    private String Descripcion;
    private String fecha;
    private String fua;
    private int ID_CIE10;
    private int RS_ID;
    private String estado;

    
    public boolean mantenimientoCCD(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CONSULTORIO_EXT_RS_CCD_MANTANIMIENTO ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRsCcd());
            cmd.setInt(2, getRS_ID());
            cmd.setString(3, getDescripcion());
            cmd.setString(4, getFecha());
            cmd.setInt(5, getID_CIE10());
            cmd.setString(6, getFua());
 
            cmd.setString(7, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error CCD  : " + ex.getMessage());
        }
        return resp;
    }
    
     public void cargarDatosCie10(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Nro","Código","Diagnóstico"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CIE10_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // clasificacion
                fila[1]=r.getString(2); //codigo
                fila[2]=r.getString(3); //codigo
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaCargarCie10(tabla);
        } catch (Exception e) {
            System.out.println("Error_cargarDatosCie10: " + e.getMessage());
        }
    }
     
    public void formatoTablaCargarCie10(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);//CODIGO
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);//CODIGO
        tabla.getColumnModel().getColumn(2).setPreferredWidth(600);//CODIGO
        tabla.setRowHeight(30);
    }
    
    public ConsultorioExtRsCcd() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public int getRsCcd() {
        return rsCcd;
    }

    public void setRsCcd(int rsCcd) {
        this.rsCcd = rsCcd;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFua() {
        return fua;
    }

    public void setFua(String fua) {
        this.fua = fua;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public int getID_CIE10() {
        return ID_CIE10;
    }

    public void setID_CIE10(int ID_CIE10) {
        this.ID_CIE10 = ID_CIE10;
    }
    

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getRS_ID() {
        return RS_ID;
    }

    public void setRS_ID(int RS_ID) {
        this.RS_ID = RS_ID;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    


}
