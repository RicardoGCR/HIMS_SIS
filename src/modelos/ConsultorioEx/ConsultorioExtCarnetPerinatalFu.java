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
import vista.ConsultorioEx.RSAIVacunas;
import vista.ConsultorioEx.RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V;
public class ConsultorioExtCarnetPerinatalFu implements Serializable {
    private static final long serialVersionUID = 1L;

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int fuId;
    private String fuFechaUltMens;
    private String fuDudaFecha;
    private String fuEco;
    private String fuFechaEco;
    private String fuFechaPParto;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;

    public boolean mantenimientoConsultorioExtCarnetPerinatalFu(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_FU ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getFuId());
            cmd.setInt(2, getCpId());
            cmd.setString(3, getFuFechaUltMens());
            cmd.setString(4, getFuDudaFecha());
            cmd.setString(5, getFuEco());
            cmd.setString(6, getFuFechaEco());
            cmd.setString(7, getFuFechaPParto());
            cmd.setString(8, getCodUsu());
            cmd.setString(9, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatalFu: " + ex.getMessage());
        }
        return resp;
    }
    
    public String perinatalFuID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 FU_ID\n" +
                        "FROM CONSULTORIO_EXT_CARNET_PERINATAL_FU \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY FU_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("perinatalFuID: " + ex.getMessage());
        }
        return cod;
    }   
    
    public void ConsultoriosExtFuListar(String cp_id){
        String consulta="";
        try {
            consulta="[CONSULTORIO_EXT_LISTAR_CARNET_PERINATAL_FU] ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, cp_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdFum.setText(r.getString(1)); 
                try { // llenar el campo fecha FUM
                  if(r.getString(2).equals("")){
                  RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.dtFUM.setDate(null);
                } else {
                    String fechaFUM = (String)(r.getString(2));
                    DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha = dfo.parse(fechaFUM);
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.dtFUM.setDate(fecha);
                }
                } catch (Exception e) {
                }
                if(r.getString(3).equals("SI"))
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.chkDudaSi.setText("X");
                if(r.getString(3).equals("NO"))
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.chkDudaNo.setText("X");
                if(r.getString(4).equals(""))
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.chkNoAplica.setText("");
                else{
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.chkNoAplica.setText("");
                    RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtEcografia.setText(r.getString(4));
                    try { // llenar el campo fecha FUM
                        if(r.getString(5).equals("")){
                        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.dtFechaEco.setDate(null);
                      } else {
                          String fechaEco = (String)(r.getString(5));
                          DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
                          Date fecha = dfo.parse(fechaEco);
                          RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.dtFechaEco.setDate(fecha);
                      }
                    } catch (Exception e) {
                    }
                }
                
                try { // fecha parto
                        if(r.getString(6).equals("")){
                        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.dtFechaProbableParto.setDate(null);
                      } else {
                          String fechaParto = (String)(r.getString(6));
                          DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
                          Date fecha = dfo.parse(fechaParto);
                          RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.dtFechaProbableParto.setDate(fecha);
                      }
                    } catch (Exception e) {
                    }
                    
            }
            //
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtFuListar: " + e.getMessage());
        }
    }
    
    public ConsultorioExtCarnetPerinatalFu() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalFu(int fuId) {
        this.fuId = fuId;
    }

    public int getFuId() {
        return fuId;
    }

    public void setFuId(int fuId) {
        this.fuId = fuId;
    }

    public String getFuFechaUltMens() {
        return fuFechaUltMens;
    }

    public void setFuFechaUltMens(String fuFechaUltMens) {
        this.fuFechaUltMens = fuFechaUltMens;
    }

    public String getFuDudaFecha() {
        return fuDudaFecha;
    }

    public void setFuDudaFecha(String fuDudaFecha) {
        this.fuDudaFecha = fuDudaFecha;
    }

    public String getFuEco() {
        return fuEco;
    }

    public void setFuEco(String fuEco) {
        this.fuEco = fuEco;
    }

    public String getFuFechaEco() {
        return fuFechaEco;
    }

    public void setFuFechaEco(String fuFechaEco) {
        this.fuFechaEco = fuFechaEco;
    }

    public String getFuFechaPParto() {
        return fuFechaPParto;
    }

    public void setFuFechaPParto(String fuFechaPParto) {
        this.fuFechaPParto = fuFechaPParto;
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
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalFu[ fuId=" + fuId + " ]";
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
