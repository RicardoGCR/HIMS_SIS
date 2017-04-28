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

public class ConsultorioExtCarnetPerinatalAp implements Serializable {
    private static final long serialVersionUID = 1L;

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int apId;
    private String ap1;
    private String ap2;
    private String ap3;
    private String ap4;
    private String ap5;
    private String ap6;
    private String ap7;
    private String ap8;
    private String ap9;
    private String ap10;
    private String ap11;
    private String ap12;
    private String ap13;
    private String ap14;
    private String ap15;
    private String ap16;
    private String ap17;
    private String ap18;
    private String ap19;
    private String ap20;
    private String ap21;
    private String ap22;
    private String ap23;
    private String ap24;
    private String ap25;
    private String ap26;
    private String ap27;
    private String ap28;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private String estado;
    private String codUsu;

    public void ConsultoriosExtAPListar(String ap_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CARNET_PERINATAL_AP ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                
            /////////Referida
                
                RegistroEmbarazoAP.txtAp1.setText(r.getString(3)); 
                RegistroEmbarazoAP.txtAp2.setText(r.getString(4));
                RegistroEmbarazoAP.txtAp3.setText(r.getString(5));
                RegistroEmbarazoAP.txtAp4.setText(r.getString(6));
                RegistroEmbarazoAP.txtAp5.setText(r.getString(7));
                RegistroEmbarazoAP.txtAp6.setText(r.getString(8)); 
                RegistroEmbarazoAP.txtAp7.setText(r.getString(9));
                RegistroEmbarazoAP.txtAp8.setText(r.getString(10));
                RegistroEmbarazoAP.txtAp9.setText(r.getString(11));
                RegistroEmbarazoAP.txtAp10.setText(r.getString(12));
                RegistroEmbarazoAP.txtAp11.setText(r.getString(13)); 
                RegistroEmbarazoAP.txtAp12.setText(r.getString(14));
                RegistroEmbarazoAP.txtAp13.setText(r.getString(15));
                RegistroEmbarazoAP.txtAp14.setText(r.getString(16));
                RegistroEmbarazoAP.txtAp15.setText(r.getString(17));
                RegistroEmbarazoAP.txtAp16.setText(r.getString(18)); 
                RegistroEmbarazoAP.txtAp17.setText(r.getString(19));
                RegistroEmbarazoAP.txtAp18.setText(r.getString(20));
                RegistroEmbarazoAP.txtAp19.setText(r.getString(21));
                RegistroEmbarazoAP.txtAp20.setText(r.getString(22));
                RegistroEmbarazoAP.txtAp21.setText(r.getString(23)); 
                RegistroEmbarazoAP.txtAp22.setText(r.getString(24));
                RegistroEmbarazoAP.txtAp23.setText(r.getString(25));
                RegistroEmbarazoAP.txtAp24.setText(r.getString(26));
                RegistroEmbarazoAP.txtAp25.setText(r.getString(27));
                RegistroEmbarazoAP.txtAp26.setText(r.getString(28)); 
                RegistroEmbarazoAP.txtAp27.setText(r.getString(29));
                
                

                RegistroEmbarazoAP.txtOtros.setText(r.getString(30));
                if (!RegistroEmbarazoAP.txtOtros.getText().equals("") ){
                RegistroEmbarazoAP.txtAp28.setText("X");
                }
               

                RegistroEmbarazoAP.lblIdAP.setText(r.getString(1)); 
                if (!RegistroEmbarazoAP.lblIdAP.getText().equals("") ){
                    RegistroEmbarazoAP.btnGuardar.setEnabled(false);
                    RegistroEmbarazoAP.btneditar.setEnabled(true);
                    RegistroEmbarazoAP.var.setText("2");
                }
                
                
            }
            //
        } catch (Exception e) {
            System.out.println("Error: LISTAR AP  " + e.getMessage());
        }
    }
    
    public boolean mantenimientoConsultorioExtAP(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_AP ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getApId());
            cmd.setInt(2, getCpId());
            cmd.setString(3, getAp1());
            cmd.setString(4, getAp2());
            cmd.setString(5, getAp3());
            cmd.setString(6, getAp4());
            cmd.setString(7, getAp5());
            cmd.setString(8, getAp6());
            cmd.setString(9, getAp7());
            cmd.setString(10, getAp8());
            cmd.setString(11, getAp9());
            cmd.setString(12, getAp10());
            cmd.setString(13, getAp11());
            cmd.setString(14, getAp12());
            cmd.setString(15, getAp13());
            cmd.setString(16, getAp14());
            cmd.setString(17, getAp15());
            cmd.setString(18, getAp16());
            cmd.setString(19, getAp17());
            cmd.setString(20, getAp18());
            cmd.setString(21, getAp19());
            cmd.setString(22, getAp20());
            cmd.setString(23, getAp21());
            cmd.setString(24, getAp22());
            cmd.setString(25, getAp23());
            cmd.setString(26, getAp24());
            cmd.setString(27, getAp25());
            cmd.setString(28, getAp26());
            cmd.setString(29, getAp27());
            cmd.setString(30, getAp28());
            cmd.setString(31, getCodUsu());
            cmd.setString(32, tipo);
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
    
    
    public ConsultorioExtCarnetPerinatalAp() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalAp(int apId) {
        this.apId = apId;
    }

    public int getApId() {
        return apId;
    }

    public void setApId(int apId) {
        this.apId = apId;
    }

    public String getAp1() {
        return ap1;
    }

    public void setAp1(String ap1) {
        this.ap1 = ap1;
    }

    public String getAp2() {
        return ap2;
    }

    public void setAp2(String ap2) {
        this.ap2 = ap2;
    }

    public String getAp3() {
        return ap3;
    }

    public void setAp3(String ap3) {
        this.ap3 = ap3;
    }

    public String getAp4() {
        return ap4;
    }

    public void setAp4(String ap4) {
        this.ap4 = ap4;
    }

    public String getAp5() {
        return ap5;
    }

    public void setAp5(String ap5) {
        this.ap5 = ap5;
    }

    public String getAp6() {
        return ap6;
    }

    public void setAp6(String ap6) {
        this.ap6 = ap6;
    }

    public String getAp7() {
        return ap7;
    }

    public void setAp7(String ap7) {
        this.ap7 = ap7;
    }

    public String getAp8() {
        return ap8;
    }

    public void setAp8(String ap8) {
        this.ap8 = ap8;
    }

    public String getAp9() {
        return ap9;
    }

    public void setAp9(String ap9) {
        this.ap9 = ap9;
    }

    public String getAp10() {
        return ap10;
    }

    public void setAp10(String ap10) {
        this.ap10 = ap10;
    }

    public String getAp11() {
        return ap11;
    }

    public void setAp11(String ap11) {
        this.ap11 = ap11;
    }

    public String getAp12() {
        return ap12;
    }

    public void setAp12(String ap12) {
        this.ap12 = ap12;
    }

    public String getAp13() {
        return ap13;
    }

    public void setAp13(String ap13) {
        this.ap13 = ap13;
    }

    public String getAp14() {
        return ap14;
    }

    public void setAp14(String ap14) {
        this.ap14 = ap14;
    }

    public String getAp15() {
        return ap15;
    }

    public void setAp15(String ap15) {
        this.ap15 = ap15;
    }

    public String getAp16() {
        return ap16;
    }

    public void setAp16(String ap16) {
        this.ap16 = ap16;
    }

    public String getAp17() {
        return ap17;
    }

    public void setAp17(String ap17) {
        this.ap17 = ap17;
    }

    public String getAp18() {
        return ap18;
    }

    public void setAp18(String ap18) {
        this.ap18 = ap18;
    }

    public String getAp19() {
        return ap19;
    }

    public void setAp19(String ap19) {
        this.ap19 = ap19;
    }

    public String getAp20() {
        return ap20;
    }

    public void setAp20(String ap20) {
        this.ap20 = ap20;
    }

    public String getAp21() {
        return ap21;
    }

    public void setAp21(String ap21) {
        this.ap21 = ap21;
    }

    public String getAp22() {
        return ap22;
    }

    public void setAp22(String ap22) {
        this.ap22 = ap22;
    }

    public String getAp23() {
        return ap23;
    }

    public void setAp23(String ap23) {
        this.ap23 = ap23;
    }

    public String getAp24() {
        return ap24;
    }

    public void setAp24(String ap24) {
        this.ap24 = ap24;
    }

    public String getAp25() {
        return ap25;
    }

    public void setAp25(String ap25) {
        this.ap25 = ap25;
    }

    public String getAp26() {
        return ap26;
    }

    public void setAp26(String ap26) {
        this.ap26 = ap26;
    }

    public String getAp27() {
        return ap27;
    }

    public void setAp27(String ap27) {
        this.ap27 = ap27;
    }

    public String getAp28() {
        return ap28;
    }

    public void setAp28(String ap28) {
        this.ap28 = ap28;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAp[ apId=" + apId + " ]";
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
