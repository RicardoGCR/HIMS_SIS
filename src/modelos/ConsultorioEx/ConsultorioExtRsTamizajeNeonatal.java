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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.annotation.XmlRootElement;
import servicios.Conexion;
import vista.CRED.RSAITN;
import static vista.CRED.RegistroSeguimiento.lblPorcentajeDD;
import static vista.CRED.RegistroSeguimiento.lblPorcentajeTN;

public class ConsultorioExtRsTamizajeNeonatal implements Serializable {
    private static final long serialVersionUID = 1L;

    Conexion con = new Conexion();
    private Connection cn;
    private int rs_id;
    private Long tnId;
    private String tshFecha;
    private String tshFua;
    private String fcFecha;
    private String fcFua;
    private String foFecha;
    private String foFua;
    private String hsrFecha;
    private String hsrFua;

     public void ConsultoriosExtTNListar(int rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_RS_TAMIZAJE_NEONATAL_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
 
                try {
                    if(r.getString(3).equals("")){
                        RSAITN.FDD7.setDate(null);
                    } else {
                        String fechaSeleccionadaD1 = (String)(r.getString(3));
                        DateFormat dfoD1 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD1 = dfoD1.parse(fechaSeleccionadaD1);
                        RSAITN.FDD7.setDate(fechaD1);
                        RSAITN.FUADD7.setText(r.getString(4));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(5).equals("")){
                        RSAITN.FDD8.setDate(null);
                    } else {
                        String fechaSeleccionadaD2 = (String)(r.getString(5));
                        DateFormat dfoD2 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD2 = dfoD2.parse(fechaSeleccionadaD2);
                        RSAITN.FDD8.setDate(fechaD2);
                        RSAITN.FUADD8.setText(r.getString(6));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(7).equals("")){
                        RSAITN.FDD9.setDate(null);
                    } else {
                        String fechaSeleccionadaD3 = (String)(r.getString(7));
                        DateFormat dfoD3 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD3 = dfoD3.parse(fechaSeleccionadaD3);
                        RSAITN.FDD9.setDate(fechaD3);
                        RSAITN.FUADD9.setText(r.getString(8));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(9).equals("")){
                        RSAITN.FDD10.setDate(null);
                    } else {
                        String fechaSeleccionadaD4 = (String)(r.getString(9));
                        DateFormat dfoD4 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD4 = dfoD4.parse(fechaSeleccionadaD4);
                        RSAITN.FDD10.setDate(fechaD4);
                        RSAITN.FUADD10.setText(r.getString(4));
                    }
                } catch (Exception e) {
                }
            }
            //
        } catch (Exception e) {
            System.out.println("Error: Consultorios tam neo " + e.getMessage());
        }
    }
     
     public boolean mantenimientoRSAITN(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CONSULTORIO_EXT_MANTENIMIENTO_RS_TAMIZAJE_NEONATAL ?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRs_id());
            
            cmd.setString(2, getTshFecha());
            cmd.setString(3, getTshFua());
            
            cmd.setString(4, getFcFecha());
            cmd.setString(5, getFcFua());
            
            cmd.setString(6, getFoFecha());
            cmd.setString(7, getFoFua());
            
            cmd.setString(8, getHsrFecha());
            cmd.setString(9, getHsrFua());

            cmd.setString(10, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimiento Vacunas " + ex.getMessage());
        }
        return resp;
    }
     
     public void porcentajeTN(int rs_id){
        String consulta="";
        try {
            consulta="EXEC CONSULTORIO_EXT_RS_TAMIZAJE_NEONATAL_PORCENTAJE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblPorcentajeTN.setText(r.getString(1) + " %"); 
                RSAITN.lblPorcentajeTn.setText(r.getString(1) + " % Completado"); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: porcentajeTN " + e.getMessage());
        }
    }  
     
    public ConsultorioExtRsTamizajeNeonatal() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsTamizajeNeonatal(Long tnId) {
        this.tnId = tnId;
    }

    public Long getTnId() {
        return tnId;
    }

    public void setTnId(Long tnId) {
        this.tnId = tnId;
    }

    public String getTshFecha() {
        return tshFecha;
    }

    public void setTshFecha(String tshFecha) {
        this.tshFecha = tshFecha;
    }

    public String getTshFua() {
        return tshFua;
    }

    public void setTshFua(String tshFua) {
        this.tshFua = tshFua;
    }

    public String getFcFecha() {
        return fcFecha;
    }

    public void setFcFecha(String fcFecha) {
        this.fcFecha = fcFecha;
    }

    public String getFcFua() {
        return fcFua;
    }

    public void setFcFua(String fcFua) {
        this.fcFua = fcFua;
    }

    public String getFoFecha() {
        return foFecha;
    }

    public void setFoFecha(String foFecha) {
        this.foFecha = foFecha;
    }

    public String getFoFua() {
        return foFua;
    }

    public void setFoFua(String foFua) {
        this.foFua = foFua;
    }

    public String getHsrFecha() {
        return hsrFecha;
    }

    public void setHsrFecha(String hsrFecha) {
        this.hsrFecha = hsrFecha;
    }

    public String getHsrFua() {
        return hsrFua;
    }

    public void setHsrFua(String hsrFua) {
        this.hsrFua = hsrFua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tnId != null ? tnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsTamizajeNeonatal)) {
            return false;
        }
        ConsultorioExtRsTamizajeNeonatal other = (ConsultorioExtRsTamizajeNeonatal) object;
        if ((this.tnId == null && other.tnId != null) || (this.tnId != null && !this.tnId.equals(other.tnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsTamizajeNeonatal[ tnId=" + tnId + " ]";
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
     * @return the rs_id
     */
    public int getRs_id() {
        return rs_id;
    }

    /**
     * @param rs_id the rs_id to set
     */
    public void setRs_id(int rs_id) {
        this.rs_id = rs_id;
    }
    
}
