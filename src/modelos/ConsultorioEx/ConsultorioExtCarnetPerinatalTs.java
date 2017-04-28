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
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;
import vista.ConsultorioEx.RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V;

/**
 *
 * @author PC02
 */
@Entity
@Table(name = "CONSULTORIO_EXT_CARNET_PERINATAL_TS")
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalTs.findAll", query = "SELECT c FROM ConsultorioExtCarnetPerinatalTs c")})
public class ConsultorioExtCarnetPerinatalTs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TS_ID")
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int tsId;
    @Column(name = "TS_GRUPO")
    private String tsGrupo;
    @Column(name = "TS_RH")
    private String tsRh;
    @Column(name = "FECHA_ACTU")
    private String fechaActu;
    @Column(name = "HORA_ACTU")
    private String horaActu;
    @Column(name = "NOM_PC")
    private String nomPc;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "COD_USU")
    private String codUsu;

    public boolean mantenimientoConsultorioExtCarnetPerinatalTs(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "[CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_TS] ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getTsId());
            cmd.setInt(2, getCpId());
            cmd.setString(3, getTsGrupo());
            cmd.setString(4, getTsRh());
            cmd.setString(5, getCodUsu());
            cmd.setString(6, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatalTs: " + ex.getMessage());
        }
        return resp;
    }
    
    public String perinatalTsID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 TS_ID\n" +
                        "FROM CONSULTORIO_EXT_CARNET_PERINATAL_TS \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY TS_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("perinatalTsID: " + ex.getMessage());
        }
        return cod;
    }   
    
    public void ConsultoriosExtTsListar(String cp_id){
        String consulta="";
        try {
            consulta="[CONSULTORIO_EXT_LISTAR_CARNET_PERINATAL_TS] ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, cp_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdGs.setText(r.getString(1)); 
                if(r.getString(3).equals("A")){
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtA.setText("X");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtB.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtAB.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtO.setText("");
                }
                if(r.getString(3).equals("B")){
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtA.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtB.setText("X");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtAB.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtO.setText("");
                }
                if(r.getString(3).equals("AB")){
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtA.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtB.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtAB.setText("X");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtO.setText("");
                }
                if(r.getString(3).equals("O")){
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtA.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtB.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtAB.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtO.setText("X");
                }
                if(r.getString(4).equals("RH+")){
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtRhPositivo.setText("X");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtRhNegativo.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtRhSen.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtNoDesc.setText("");
                }
                if(r.getString(4).equals("RH+ SEN DESC")){
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtRhPositivo.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtRhNegativo.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtRhSen.setText("X");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtNoDesc.setText("");
                }
                if(r.getString(4).equals("RH- NO DESC")){
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtRhPositivo.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtRhNegativo.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtRhSen.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtNoDesc.setText("X");
                }
                if(r.getString(4).equals("RH- SEN")){
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtRhPositivo.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtRhNegativo.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtRhSen.setText("X");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtNoDesc.setText("");
                }
                }
            //
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtTsListar: " + e.getMessage());
        }
    }
    
    public ConsultorioExtCarnetPerinatalTs() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalTs(int tsId) {
        this.tsId = tsId;
    }

    public int getTsId() {
        return tsId;
    }

    public void setTsId(int tsId) {
        this.tsId = tsId;
    }

    public String getTsGrupo() {
        return tsGrupo;
    }

    public void setTsGrupo(String tsGrupo) {
        this.tsGrupo = tsGrupo;
    }

    public String getTsRh() {
        return tsRh;
    }

    public void setTsRh(String tsRh) {
        this.tsRh = tsRh;
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
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalTs[ tsId=" + tsId + " ]";
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
    
}
