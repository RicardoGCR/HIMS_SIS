/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.admisionEmergencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static modelos.admisionEmergencia.AdmisionEmergenciaCabecera.m;
import servicios.Conexion;

/**
 *
 * @author Yamila Rocca Ruiz
 */
public class AdmisionEmergenciaFormaDeLlegada {
    private String llegada_id;
    private String llegada_nombre;
    private String llegada_Descripcion;
    private String fecha_Actu;
    private String hora_actu;
    private String cod_usu;
    private String nom_pc;
    private String estado;
    private Connection cn;
    DefaultTableModel m;
    Conexion con = new Conexion();
    
    public boolean admisionFormaDeLlegadaMantenimiento(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC ADMISION_EMERGENCIA_FORMA_LLEGADA_IAE ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, tipo);
            cmd.setString(2, getLlegada_id());
            cmd.setString(3, getLlegada_nombre());
            cmd.setString(4, getLlegada_Descripcion());
            cmd.setString(5, getCod_usu());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error_insertarModificarAdmisionEmergenciaTopico: " + ex.getMessage());
        }
        return resp;
    }
    
    public String idAdmisionEmergenciaFormaDeLlegada(){
        String id = "";
        try {
            String consulta = "EXEC ADMISION_EMERGENCIA_FORMA_LLEGADA_GENERAR_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error: idAdmisionEmergenciaFormaDeLlegada: " + ex.getMessage());
        }
        return id;
    }
    
     public void formatoCargarFormaLlegada(JTable tabla){

        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);//apellidos
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);//nombres
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
    
        tabla.setRowHeight(45);
    }
    
    public void cargarFormatEmer(JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Nombre","Descripción"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[7];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC ADMDISION_EMERGENCIA_FORMA_LLEGADA_BUSCAR";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id
                fila[1]=r.getString(2); // nombre
                fila[2]=r.getString(3); // descripcion
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoCargarFormaLlegada(tabla);
        } catch (Exception e) {
            System.out.println("Error_buscar_HC: " + e.getMessage());
        }
    }
    
    public AdmisionEmergenciaFormaDeLlegada()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    /**
     * @return the llegada_id
     */
    public String getLlegada_id() {
        return llegada_id;
    }

    /**
     * @param llegada_id the llegada_id to set
     */
    public void setLlegada_id(String llegada_id) {
        this.llegada_id = llegada_id;
    }

    /**
     * @return the llegada_nombre
     */
    public String getLlegada_nombre() {
        return llegada_nombre;
    }

    /**
     * @param llegada_nombre the llegada_nombre to set
     */
    public void setLlegada_nombre(String llegada_nombre) {
        this.llegada_nombre = llegada_nombre;
    }

    /**
     * @return the llegada_Descripcion
     */
    public String getLlegada_Descripcion() {
        return llegada_Descripcion;
    }

    /**
     * @param llegada_Descripcion the llegada_Descripcion to set
     */
    public void setLlegada_Descripcion(String llegada_Descripcion) {
        this.llegada_Descripcion = llegada_Descripcion;
    }

    /**
     * @return the fecha_Actu
     */
    public String getFecha_Actu() {
        return fecha_Actu;
    }

    /**
     * @param fecha_Actu the fecha_Actu to set
     */
    public void setFecha_Actu(String fecha_Actu) {
        this.fecha_Actu = fecha_Actu;
    }

    /**
     * @return the hora_actu
     */
    public String getHora_actu() {
        return hora_actu;
    }

    /**
     * @param hora_actu the hora_actu to set
     */
    public void setHora_actu(String hora_actu) {
        this.hora_actu = hora_actu;
    }

    /**
     * @return the cod_usu
     */
    public String getCod_usu() {
        return cod_usu;
    }

    /**
     * @param cod_usu the cod_usu to set
     */
    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    /**
     * @return the nom_pc
     */
    public String getNom_pc() {
        return nom_pc;
    }

    /**
     * @param nom_pc the nom_pc to set
     */
    public void setNom_pc(String nom_pc) {
        this.nom_pc = nom_pc;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the cn
     */
    public Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }
}
