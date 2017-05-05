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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;

    
/**
 *
 * @author MYS1
 */


public class ConsultorioExtCarnetPerinatalAlartas {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    
    
    public void listarAlertas(JTable tabla){
    String consulta="";
        try {
                tabla.setModel(new DefaultTableModel());
                String titulos[]={"AP_ID","CP_ID","DNI","Nª H.C.","Paciente","Fecha de la Cita","Atención","Telefono","Celular","Distrito","Sector","Tipo","Dirección"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                String fila[]=new String[14];
                //int index = cbxTipoBusqueda.getSelectedIndex();
                consulta="EXEC CONSULTORIO_EXT_CARNET_PERINATAL_ATENCION_PRENATAL_ALERTAS";
                PreparedStatement cmd = getCn().prepareStatement(consulta);
 
                ResultSet r= cmd.executeQuery();
                int c=1;
                while(r.next()){
                    fila[0]=r.getString(1); // id
                    fila[1]=r.getString(2); // dni
                    fila[2]=r.getString(3); // nhc
                    fila[3]=r.getString(4); // nhc
                    fila[4]=r.getString(5); // nhc
                    fila[5]=r.getString(6); // nhc
                        m.addRow(fila);
                        c++;
                }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaNomenclatura(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarNomenclatura: " + e.getMessage());
        }
    }
     public void formatoTablaNomenclatura(JTable tabla){
        tabla.getColumnModel().getColumn(2).setPreferredWidth(350);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(120);
        
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getColumnModel().getColumn(5).setMaxWidth(0);
    
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(30);
    }    
    


   public ConsultorioExtCarnetPerinatalAlartas() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public DefaultTableModel getM() {
        return m;
    }

    public void setM(DefaultTableModel m) {
        this.m = m;
    }

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
   
   
   }
    

