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
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;
import vista.ConsultorioEx.RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V;

public class ConsultorioExtCarnetPerinatalAn implements Serializable {
    private static final long serialVersionUID = 1L;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int anId;
    private String anDosisPrevia;
    private String an1raDosis;
    private String an1raDAplicacion;
    private String an2raDosis;
    private String an2raDAplicacion;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;
    private int idActoMedico;

    public boolean mantenimientoConsultorioExtCarnetPerinatalAn(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "[CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_AN] ?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getAnId());
            cmd.setInt(2, getCpId());
            cmd.setString(3, getAnDosisPrevia());
            cmd.setString(4, getAn1raDosis());
            cmd.setString(5, getAn1raDAplicacion());
            cmd.setString(6, getAn2raDosis());
            cmd.setString(7, getAn2raDAplicacion());
            cmd.setString(8, getCodUsu());
            cmd.setString(9, tipo);
            cmd.setInt(10, getIdActoMedico());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatalAn: " + ex.getMessage());
        }
        return resp;
    }
    
    public String perinatalAnID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 AN_ID\n" +
                        "FROM CONSULTORIO_EXT_CARNET_PERINATAL_AN \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY AN_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("perinatalAnID: " + ex.getMessage());
        }
        return cod;
    }   
    
    public void ConsultoriosExtANListar(String cp_id){
        String consulta="";
        try {
            consulta="[CONSULTORIO_EXT_LISTAR_CARNET_PERINATAL_AN] ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, cp_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdAn.setText(r.getString(1)); 
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtNDosisPrevia.setText(r.getString(2)); 
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtDosis1.setText(r.getString(4));
                if(r.getString(5).equals("Sin dosis ")){
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtSinDosis1.setText("X");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtNoAplica1.setText("");
                }
                if(r.getString(5).equals("No aplica ")){
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtNoAplica1.setText("X");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtSinDosis1.setText("");
                }
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtDosis2.setText(r.getString(6));
                if(r.getString(7).equals("Sin dosis ")){
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtSinDosis2.setText("X");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtNoAplica2.setText("");
                }
                if(r.getString(7).equals("No aplica ")){
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtNoAplica2.setText("X");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtSinDosis2.setText("");
                }
                
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdActoMedico.setText(r.getString(8));
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblActoMedico.setText("Acto Médico de registro " + r.getString(9));
                }
            //
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtANListar: " + e.getMessage());
        }
    }
    
    public ConsultorioExtCarnetPerinatalAn() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalAn(int anId) {
        this.anId = anId;
    }

    public int getAnId() {
        return anId;
    }

    public void setAnId(int anId) {
        this.anId = anId;
    }

    public String getAnDosisPrevia() {
        return anDosisPrevia;
    }

    public void setAnDosisPrevia(String anDosisPrevia) {
        this.anDosisPrevia = anDosisPrevia;
    }

    public String getAn1raDosis() {
        return an1raDosis;
    }

    public void setAn1raDosis(String an1raDosis) {
        this.an1raDosis = an1raDosis;
    }

    public String getAn1raDAplicacion() {
        return an1raDAplicacion;
    }

    public void setAn1raDAplicacion(String an1raDAplicacion) {
        this.an1raDAplicacion = an1raDAplicacion;
    }

    public String getAn2raDosis() {
        return an2raDosis;
    }

    public void setAn2raDosis(String an2raDosis) {
        this.an2raDosis = an2raDosis;
    }

    public String getAn2raDAplicacion() {
        return an2raDAplicacion;
    }

    public void setAn2raDAplicacion(String an2raDAplicacion) {
        this.an2raDAplicacion = an2raDAplicacion;
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
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAn[ anId=" + anId + " ]";
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
