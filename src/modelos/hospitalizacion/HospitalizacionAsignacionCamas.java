/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.hospitalizacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
public class HospitalizacionAsignacionCamas {
    private int AC_ID;
    private int ID_PREVENTA;
    private int CA_ID;
    private String AC_ESTADO;
    private String FECHA_ACTU;
    private String HORA_ACTU;
    private String COD_USU;
    private String NOM_PC;
    static DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    
    public boolean mantenimientoAsignacionCama()
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_ASIGNACION_CAMA_MANTENIMIENTO ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_PREVENTA());
            cmd.setInt(2, getCA_ID());
            cmd.setString(3, getCOD_USU());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoAsignacionCama: " + ex.getMessage());
        }
        return resp;
    }
    
    public HospitalizacionAsignacionCamas()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    /**
     * @return the AC_ID
     */
    public int getAC_ID() {
        return AC_ID;
    }

    /**
     * @param AC_ID the AC_ID to set
     */
    public void setAC_ID(int AC_ID) {
        this.AC_ID = AC_ID;
    }

    /**
     * @return the ID_PREVENTA
     */
    public int getID_PREVENTA() {
        return ID_PREVENTA;
    }

    /**
     * @param ID_PREVENTA the ID_PREVENTA to set
     */
    public void setID_PREVENTA(int ID_PREVENTA) {
        this.ID_PREVENTA = ID_PREVENTA;
    }

    /**
     * @return the CA_ID
     */
    public int getCA_ID() {
        return CA_ID;
    }

    /**
     * @param CA_ID the CA_ID to set
     */
    public void setCA_ID(int CA_ID) {
        this.CA_ID = CA_ID;
    }

    /**
     * @return the AC_ESTADO
     */
    public String getAC_ESTADO() {
        return AC_ESTADO;
    }

    /**
     * @param AC_ESTADO the AC_ESTADO to set
     */
    public void setAC_ESTADO(String AC_ESTADO) {
        this.AC_ESTADO = AC_ESTADO;
    }

    /**
     * @return the FECHA_ACTU
     */
    public String getFECHA_ACTU() {
        return FECHA_ACTU;
    }

    /**
     * @param FECHA_ACTU the FECHA_ACTU to set
     */
    public void setFECHA_ACTU(String FECHA_ACTU) {
        this.FECHA_ACTU = FECHA_ACTU;
    }

    /**
     * @return the HORA_ACTU
     */
    public String getHORA_ACTU() {
        return HORA_ACTU;
    }

    /**
     * @param HORA_ACTU the HORA_ACTU to set
     */
    public void setHORA_ACTU(String HORA_ACTU) {
        this.HORA_ACTU = HORA_ACTU;
    }

    /**
     * @return the COD_USU
     */
    public String getCOD_USU() {
        return COD_USU;
    }

    /**
     * @param COD_USU the COD_USU to set
     */
    public void setCOD_USU(String COD_USU) {
        this.COD_USU = COD_USU;
    }

    /**
     * @return the NOM_PC
     */
    public String getNOM_PC() {
        return NOM_PC;
    }

    /**
     * @param NOM_PC the NOM_PC to set
     */
    public void setNOM_PC(String NOM_PC) {
        this.NOM_PC = NOM_PC;
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
