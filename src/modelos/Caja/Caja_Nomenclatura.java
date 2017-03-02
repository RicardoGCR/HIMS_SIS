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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;

/**
 *
 * @author Ricardo
 */
public class Caja_Nomenclatura {
DefaultTableModel m;
private Connection cn;
private String cod_nomen_caja;  
private String cod_grupo_nomen_aten;
private int ar_id;  
private String id_cuenta;  
private String nomen_caja;  
private String descripcion_nomen_tipo;  
private String nom_usu;  

private String vis_admi;   
private String vis_aten;  
Conexion con = new Conexion(); 
public String idNomen(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec Caja_Nomenclatura_ID";
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

public boolean nuevaNomenclatura(){
        boolean resp = false;
        try{
            String sql = "exec Caja_nomenclatura_NUEVO "
                        + "?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_nomen_caja());
            cmd.setString(2, getCod_grupo_nomen_aten());
            cmd.setInt(3, getAr_id());
            cmd.setString(4, getId_cuenta());
            cmd.setString(5, getNomen_caja());
            cmd.setString(6, getDescripcion_nomen_tipo());
            cmd.setString(7, getNom_usu());

            cmd.setString(8, getVis_admi());
            cmd.setString(9, getVis_aten());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  " + ex.getMessage());
        }
        return resp;
    }
     
     
    public boolean modificarNomenclatura(){
        boolean resp = false;
        try
        {
            String sql = "Caja_nomenclatura_MODIFICAR ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_nomen_caja());
            cmd.setString(2, getCod_grupo_nomen_aten());
            cmd.setInt(3, getAr_id());
            cmd.setString(4, getId_cuenta());
            cmd.setString(5, getNomen_caja());
            cmd.setString(6, getDescripcion_nomen_tipo());
            cmd.setString(7, getNom_usu());

            cmd.setString(8, getVis_admi());
            cmd.setString(9, getVis_aten());  
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
    
    public boolean eliminarCajaNomenclatura(){
        boolean resp = false;
        try
        {
            String sql = "EXEC Caja_nomenclatura_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_nomen_caja());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error_eliminar: " + ex.getMessage());
        }
        return resp;
    }
    
    public void formatoNomenclaturaEmer(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(90);//cpt
        tabla.getColumnModel().getColumn(1).setPreferredWidth(555);//descripcion
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);//id
        tabla.setRowHeight(25);
    }
    
    public void cajaNomenclaturaListarEmergencia(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Código CPT","Descripción de CPT","ID CPT"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[13];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_NOMENCLATURA_CAJA_LISTAR_EMER ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); //codigo cpt 
                fila[1]=r.getString(2); //descripcion
                fila[2]=r.getString(3); //id
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoNomenclaturaEmer(tabla);
        } catch (Exception e) {
            System.out.println("Error: cajaNomenclaturaListarEmergencia: " + e.getMessage());
        }
    }
         
      public Caja_Nomenclatura()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
     public String getCod_nomen_caja() {
        return cod_nomen_caja;
    }

    public void setCod_nomen_caja(String cod_nomen_caja) {
        this.cod_nomen_caja = cod_nomen_caja;
    }

    public String getCod_grupo_nomen_aten() {
        return cod_grupo_nomen_aten;
    }

    public void setCod_grupo_nomen_aten(String cod_grupo_nomen_aten) {
        this.cod_grupo_nomen_aten = cod_grupo_nomen_aten;
    }

    public int getAr_id() {
        return ar_id;
    }

    public void setAr_id(int ar_id) {
        this.ar_id = ar_id;
    }



    public String getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(String id_cuenta) {
        this.id_cuenta = id_cuenta;
    }



    public String getNomen_caja() {
        return nomen_caja;
    }

    public void setNomen_caja(String nomen_caja) {
        this.nomen_caja = nomen_caja;
    }

    public String getDescripcion_nomen_tipo() {
        return descripcion_nomen_tipo;
    }

    public void setDescripcion_nomen_tipo(String descripcion_nomen_tipo) {
        this.descripcion_nomen_tipo = descripcion_nomen_tipo;
    }

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

 

    public String getVis_admi() {
        return vis_admi;
    }

    public void setVis_admi(String vis_admi) {
        this.vis_admi = vis_admi;
    }

   

    public String getVis_aten() {
        return vis_aten;
    }

    public void setVis_aten(String vis_aten) {
        this.vis_aten = vis_aten;
    }
}
