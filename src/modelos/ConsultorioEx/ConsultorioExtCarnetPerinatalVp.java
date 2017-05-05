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
import vista.ConsultorioEx.RegistroEmbarazoAP;
import vista.ConsultorioEx.RegistroEmbarazoVP;

public class ConsultorioExtCarnetPerinatalVp implements Serializable {
    private static final long serialVersionUID = 1L;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int vpId;
    private String vpRubeola;
    private String vpHepatitis;
    private String vpPapiloma;
    private String vpFiebre;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;
    private int idActoMedico;

     public void ConsultoriosExtVPListar(String rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CARNET_PERINATAL_VP ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                ////////////////////RUBEOLA
                if ((r.getString(3)).equals("S") ){
                RegistroEmbarazoVP.txtRS.setText("X");
                RegistroEmbarazoVP.txtRN.setText("");

                }
                if ((r.getString(3)).equals("N") ){
                RegistroEmbarazoVP.txtRN.setText("X");
                RegistroEmbarazoVP.txtRS.setText("");
                }
                //////////////HEPATITIS
                if ((r.getString(4)).equals("S") ){
                RegistroEmbarazoVP.txtHS.setText("X");
                RegistroEmbarazoVP.txtHN.setText("");

                }
                if ((r.getString(4)).equals("N") ){
                RegistroEmbarazoVP.txtHN.setText("X");
                RegistroEmbarazoVP.txtHS.setText("");
                }
                //////////////PAPILOMA
                if ((r.getString(5)).equals("S") ){
                RegistroEmbarazoVP.txtPS.setText("X");
                RegistroEmbarazoVP.txtPN.setText("");

                }
                if ((r.getString(5)).equals("N") ){
                RegistroEmbarazoVP.txtPN.setText("X");
                RegistroEmbarazoVP.txtPS.setText("");
                }
                //////////////FIEBRE AMARILLA
                if ((r.getString(6)).equals("S") ){
                RegistroEmbarazoVP.txtFS.setText("X");
                RegistroEmbarazoVP.txtFN.setText("");

                }
                if ((r.getString(6)).equals("N") ){
                RegistroEmbarazoVP.txtFN.setText("X");
                RegistroEmbarazoVP.txtFS.setText("");
                }
                RegistroEmbarazoVP.lblIdVP.setText(r.getString(1)); 
                if (!RegistroEmbarazoVP.lblIdVP.getText().equals("") ){
                    RegistroEmbarazoVP.btnGuardar.setEnabled(false);
                    RegistroEmbarazoVP.btneditar.setEnabled(true);
                    RegistroEmbarazoVP.var.setText("2");
                    RegistroEmbarazoVP.lblIdActoMedico.setText(r.getString(12)); 
                    RegistroEmbarazoVP.lblActoMedico.setText("Acto Médico de registro " + r.getString(13)); 
                }  
             }
            //
        } catch (Exception e) {
            System.out.println("Error: Consultorios AO  " + e.getMessage());
        }
    }  
     
    public boolean mantenimientoConsultorioExtCarnetPerinatalVp(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_VP ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getVpId());
            cmd.setInt(2, getCpId());
            cmd.setString(3, getVpRubeola());
            cmd.setString(4, getVpHepatitis());
            cmd.setString(5, getVpPapiloma());
            cmd.setString(6, getVpFiebre());
            cmd.setString(7, getCodUsu());
            cmd.setString(8, tipo);
            cmd.setInt(9, getIdActoMedico());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatalVp: " + ex.getMessage());
        }
        return resp;
    }
    
    public ConsultorioExtCarnetPerinatalVp() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalVp(int vpId) {
        this.vpId = vpId;
    }

    public int getVpId() {
        return vpId;
    }

    public void setVpId(int vpId) {
        this.vpId = vpId;
    }

    public String getVpRubeola() {
        return vpRubeola;
    }

    public void setVpRubeola(String vpRubeola) {
        this.vpRubeola = vpRubeola;
    }

    public String getVpHepatitis() {
        return vpHepatitis;
    }

    public void setVpHepatitis(String vpHepatitis) {
        this.vpHepatitis = vpHepatitis;
    }

    public String getVpPapiloma() {
        return vpPapiloma;
    }

    public void setVpPapiloma(String vpPapiloma) {
        this.vpPapiloma = vpPapiloma;
    }

    public String getVpFiebre() {
        return vpFiebre;
    }

    public void setVpFiebre(String vpFiebre) {
        this.vpFiebre = vpFiebre;
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
