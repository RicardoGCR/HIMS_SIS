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
import servicios.Conexion;
import vista.ConsultorioEx.RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V;

public class ConsultorioExtCarnetPerinatalVg implements Serializable {
    private static final long serialVersionUID = 1L;

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int vgId;
    private String vgFichaTamizaje;
    private String vgViolencia;
    private String vgFecha;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;
    private int idActoMedico;

    public boolean mantenimientoConsultorioExtCarnetPerinatalVg(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_VG ?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getVgId());
            cmd.setInt(2, getCpId());
            cmd.setString(3, getVgFichaTamizaje());
            cmd.setString(4, getVgViolencia());
            cmd.setString(5, getVgFecha());
            cmd.setString(6, getCodUsu());
            cmd.setString(7, tipo);
            cmd.setInt(8, getIdActoMedico());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatalVg: " + ex.getMessage());
        }
        return resp;
    }
    
    public String perinatalVgID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 VG_ID\n" +
                        "FROM CONSULTORIO_EXT_CARNET_PERINATAL_VG \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY VG_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("perinatalVgID: " + ex.getMessage());
        }
        return cod;
    }  
    
    public void ConsultoriosExtVgListar(String cp_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CARNET_PERINATAL_VG ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, cp_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdVG.setText(r.getString(1)); 
                try { // llenar el campo fecha FUM
                  if(r.getString(5).equals("")){
                  RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.FechaVG.setDate(null);
                } else {
                    String fechaFUM = (String)(r.getString(5));
                    DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha = dfo.parse(fechaFUM);
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.FechaVG.setDate(fecha);
                }
                } catch (Exception e) {
                }
                if(r.getString(3).equals("SI"))
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.chkTsi.setText("X");
                if(r.getString(3).equals("NO"))
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.chkTno.setText("X");
                
                if(r.getString(4).equals("SI"))
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.chkVsi.setText("X");
                if(r.getString(4).equals("NO"))
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.chkVno.setText("X");
                
                
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdActoMedico.setText(r.getString(11)); 
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblActoMedico.setText("Acto Médico de registro " + r.getString(12)); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtFuListar: " + e.getMessage());
        }
    }
    
    public ConsultorioExtCarnetPerinatalVg() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalVg(int vgId) {
        this.vgId = vgId;
    }

    public int getVgId() {
        return vgId;
    }

    public void setVgId(int vgId) {
        this.vgId = vgId;
    }

    public String getVgFichaTamizaje() {
        return vgFichaTamizaje;
    }

    public void setVgFichaTamizaje(String vgFichaTamizaje) {
        this.vgFichaTamizaje = vgFichaTamizaje;
    }

    public String getVgViolencia() {
        return vgViolencia;
    }

    public void setVgViolencia(String vgViolencia) {
        this.vgViolencia = vgViolencia;
    }

    public String getVgFecha() {
        return vgFecha;
    }

    public void setVgFecha(String vgFecha) {
        this.vgFecha = vgFecha;
    }

    public String getFechaActu() {
        return fechaActu;
    }

    public void setFechaActu(String fechaActu) {
        this.fechaActu = fechaActu;
    }

    public String getHoraActu() {
        return horaActu;
    }

    public void setHoraActu(String horaActu) {
        this.horaActu = horaActu;
    }

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalVg[ vgId=" + vgId + " ]";
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
     * @return the cpId
     */
    public int getCpId() {
        return cpId;
    }

    /**
     * @param cpId the cpId to set
     */
    public void setCpId(int cpId) {
        this.cpId = cpId;
    }

    /**
     * @return the idActoMedico
     */
    public int getIdActoMedico() {
        return idActoMedico;
    }

    /**
     * @param idActoMedico the idActoMedico to set
     */
    public void setIdActoMedico(int idActoMedico) {
        this.idActoMedico = idActoMedico;
    }
    
}
