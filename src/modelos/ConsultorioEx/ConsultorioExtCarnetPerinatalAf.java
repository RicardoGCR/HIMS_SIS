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
import vista.ConsultorioEx.RegistroEmbarazoAF;
public class ConsultorioExtCarnetPerinatalAf implements Serializable {
    private static final long serialVersionUID = 1L;
    
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int afId;
    private String afNinguno;
    private String afAlergias;
    private String afHipertens;
    private String afEpilepsia;
    private String afDiabetes;
    private String afEnfCongenitas;
    private String afEmbMultiple;
    private String afMalaria;
    private String afHiperArterial;
    private String afHipotiroidismo;
    private String afNeoplasica;
    private String afTbc;
    private String afOtros;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;
    
    
    
    public void ConsultoriosExtAFListar(String ap_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CARNET_PERINATAL_AF ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                
                
                RegistroEmbarazoAF.txtAf1.setText(r.getString(3)); 
                RegistroEmbarazoAF.txtAf2.setText(r.getString(4));
                RegistroEmbarazoAF.txtAf3.setText(r.getString(5));
                RegistroEmbarazoAF.txtAf4.setText(r.getString(6));
                RegistroEmbarazoAF.txtAf5.setText(r.getString(7));
                RegistroEmbarazoAF.txtAf6.setText(r.getString(8)); 
                RegistroEmbarazoAF.txtAf7.setText(r.getString(9));
                RegistroEmbarazoAF.txtAf8.setText(r.getString(10));
                RegistroEmbarazoAF.txtAf9.setText(r.getString(11));
                RegistroEmbarazoAF.txtAf10.setText(r.getString(12));
                RegistroEmbarazoAF.txtAf11.setText(r.getString(13)); 
                RegistroEmbarazoAF.txtAf12.setText(r.getString(14));
                RegistroEmbarazoAF.txtOtros.setText(r.getString(15));
                if (!RegistroEmbarazoAF.txtOtros.getText().equals("") ){
                RegistroEmbarazoAF.txtAf13.setText("X");
                }
                RegistroEmbarazoAF.lblIdAF.setText(r.getString(1)); 
                if (!RegistroEmbarazoAF.lblIdAF.getText().equals("") ){
                    RegistroEmbarazoAF.btnGuardar.setEnabled(false);
                    RegistroEmbarazoAF.btneditar.setEnabled(true);
                    RegistroEmbarazoAF.var.setText("2");
                }
                
                
            }
            //
        } catch (Exception e) {
            System.out.println("Error: LISTAR AP  " + e.getMessage());
        }
    }
    
    public boolean mantenimientoConsultorioExtAF(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "[CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_AF] ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getAfId());
            cmd.setInt(2, getCpId());
            cmd.setString(3, getAfNinguno());
            cmd.setString(4, getAfAlergias());
            cmd.setString(5, getAfHipertens());
            cmd.setString(6, getAfEpilepsia());
            cmd.setString(7, getAfDiabetes());
            cmd.setString(8, getAfEnfCongenitas());
            cmd.setString(9, getAfEmbMultiple());
            cmd.setString(10, getAfMalaria());
            cmd.setString(11, getAfHiperArterial());
            cmd.setString(12, getAfHipotiroidismo());
            cmd.setString(13, getAfNeoplasica());
            cmd.setString(14, getAfTbc());
            cmd.setString(15, getAfOtros());
            cmd.setString(16, getCodUsu());
            cmd.setString(17, tipo);
                if(!cmd.execute()){
                    resp = true;
                }
            cmd.close();
        }
            catch(Exception ex){
                System.out.println("Error: mantenimientoConsultorioExtAO: " + ex.getMessage());
            }
        return resp;
    }

    public ConsultorioExtCarnetPerinatalAf() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalAf(int afId) {
        this.afId = afId;
    }

    public int getAfId() {
        return afId;
    }

    public void setAfId(int afId) {
        this.afId = afId;
    }

    public String getAfNinguno() {
        return afNinguno;
    }

    public void setAfNinguno(String afNinguno) {
        this.afNinguno = afNinguno;
    }

    public String getAfAlergias() {
        return afAlergias;
    }

    public void setAfAlergias(String afAlergias) {
        this.afAlergias = afAlergias;
    }

    public String getAfHipertens() {
        return afHipertens;
    }

    public void setAfHipertens(String afHipertens) {
        this.afHipertens = afHipertens;
    }

    public String getAfEpilepsia() {
        return afEpilepsia;
    }

    public void setAfEpilepsia(String afEpilepsia) {
        this.afEpilepsia = afEpilepsia;
    }

    public String getAfDiabetes() {
        return afDiabetes;
    }

    public void setAfDiabetes(String afDiabetes) {
        this.afDiabetes = afDiabetes;
    }

    public String getAfEnfCongenitas() {
        return afEnfCongenitas;
    }

    public void setAfEnfCongenitas(String afEnfCongenitas) {
        this.afEnfCongenitas = afEnfCongenitas;
    }

    public String getAfEmbMultiple() {
        return afEmbMultiple;
    }

    public void setAfEmbMultiple(String afEmbMultiple) {
        this.afEmbMultiple = afEmbMultiple;
    }

    public String getAfMalaria() {
        return afMalaria;
    }

    public void setAfMalaria(String afMalaria) {
        this.afMalaria = afMalaria;
    }

    public String getAfHiperArterial() {
        return afHiperArterial;
    }

    public void setAfHiperArterial(String afHiperArterial) {
        this.afHiperArterial = afHiperArterial;
    }

    public String getAfHipotiroidismo() {
        return afHipotiroidismo;
    }

    public void setAfHipotiroidismo(String afHipotiroidismo) {
        this.afHipotiroidismo = afHipotiroidismo;
    }

    public String getAfNeoplasica() {
        return afNeoplasica;
    }

    public void setAfNeoplasica(String afNeoplasica) {
        this.afNeoplasica = afNeoplasica;
    }

    public String getAfTbc() {
        return afTbc;
    }

    public void setAfTbc(String afTbc) {
        this.afTbc = afTbc;
    }

    public String getAfOtros() {
        return afOtros;
    }

    public void setAfOtros(String afOtros) {
        this.afOtros = afOtros;
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
    
}
