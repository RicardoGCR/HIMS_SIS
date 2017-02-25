/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.Caja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
public class Caja_DetallePreventa {
    static DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int IdDetalle_Preventa;	
    private int Id_Preventa;	
    private String cod_precio;		
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String cod_usu;
    private String id_topico;	

    public boolean cajaDetallePreventaInsertar()
        {
        boolean resp = false;
        try{
            String sql = "EXEC CAJA_PREVENTA_DETALLE_INSERTAR_EXAMEN ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_topico());
            cmd.setString(2, getCod_precio());
            cmd.setString(3, getCod_usu());
            cmd.setInt(4, getId_Preventa());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: cajaDetallePreventaInsertar: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean cajaDetallePreventaModificar()
        {
        boolean resp = false;
        try{
            String sql = "EXEC CAJA_PREVENTA_DETALLE_EXAMEN_MODIFICAR ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_topico());
            cmd.setString(2, getCod_precio());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: modificarDetalleExamen: " + ex.getMessage());
        }
        return resp;
    }
    
    public Caja_DetallePreventa()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
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

    /**
     * @return the IdDetalle_Preventa
     */
    public int getIdDetalle_Preventa() {
        return IdDetalle_Preventa;
    }

    /**
     * @param IdDetalle_Preventa the IdDetalle_Preventa to set
     */
    public void setIdDetalle_Preventa(int IdDetalle_Preventa) {
        this.IdDetalle_Preventa = IdDetalle_Preventa;
    }

    /**
     * @return the Id_Preventa
     */
    public int getId_Preventa() {
        return Id_Preventa;
    }

    /**
     * @param Id_Preventa the Id_Preventa to set
     */
    public void setId_Preventa(int Id_Preventa) {
        this.Id_Preventa = Id_Preventa;
    }

    /**
     * @return the cod_precio
     */
    public String getCod_precio() {
        return cod_precio;
    }

    /**
     * @param cod_precio the cod_precio to set
     */
    public void setCod_precio(String cod_precio) {
        this.cod_precio = cod_precio;
    }

    /**
     * @return the FECHA_ACTU
     */
    public String getFecha_actu() {
        return fecha_actu;
    }

    /**
     * @param FECHA_ACTU the FECHA_ACTU to set
     */
    public void setFecha_actu(String fecha_actu) {
        this.fecha_actu = fecha_actu;
    }

    /**
     * @return the HORA_aCTU
     */
    public String getHora_actu() {
        return hora_actu;
    }

    /**
     * @param HORA_aCTU the HORA_aCTU to set
     */
    public void setHora_actu(String hora_actu) {
        this.hora_actu = hora_actu;
    }

    /**
     * @return the NOM_PC
     */
    public String getNom_pc() {
        return nom_pc;
    }

    /**
     * @param NOM_PC the NOM_PC to set
     */
    public void setNom_pc(String nom_pc) {
        this.nom_pc = nom_pc;
    }

    /**
     * @return the COD_USU
     */
    public String getCod_usu() {
        return cod_usu;
    }

    /**
     * @param COD_USU the COD_USU to set
     */
    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    /**
     * @return the ID_TOPICO
     */
    public String getId_topico() {
        return id_topico;
    }

    /**
     * @param ID_TOPICO the ID_TOPICO to set
     */
    public void setId_topico(String id_topico) {
        this.id_topico = id_topico;
    }
		
}
