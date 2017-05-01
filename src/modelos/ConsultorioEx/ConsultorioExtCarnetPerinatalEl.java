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
public class ConsultorioExtCarnetPerinatalEl implements Serializable {
    private static final long serialVersionUID = 1L;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int elId;
    private String elHg1;
    private String elHg1Fecha;
    private String elHg2;
    private String elHg2Fecha;
    private String elHga;
    private String elHgaFecha;
    private String elGli1;
    private String elGli1Fecha;
    private String elGli2;
    private String elGli2Fecha;
    private String elGliTg;
    private String elGliTgFecha;
    private String elVdrl1;
    private String elVdrl1Fecha;
    private String elVdrl2;
    private String elVdrl2Fecha;
    private String elFta;
    private String elFtaFecha;
    private String elThpa;
    private String elThpaFecha;
    private String elPrs;
    private String elPrsFecha;
    private String elVpr;
    private String elVprFecha;
    private String elPr2;
    private String elPr2Fecha;
    private String elElisa;
    private String elElisaFecha;
    private String elFi;
    private String elFiFecha;
    private String elHtlvi;
    private String elHtlviFecha;
    private String elTorch;
    private String elTorchFecha;
    private String elGotaG;
    private String elGotaGFecha;
    private String elMpr;
    private String elMprFecha;
    private String elFm;
    private String elFmFecha;
    private String elEco;
    private String elEcoFecha;
    private String elLeuco;
    private String elLeucoFechar;
    private String elNitrit;
    private String elNitritFecha;
    private String elUrocultiv;
    private String elUrocultivFecha;
    private String elBk;
    private String elBkFecha;
    private String elLt;
    private String elLtFecha;
    private String elHb;
    private String elHbFecha;
    private String elPap;
    private String elPapFecha;
    private String elIvaa;
    private String elIvaaFecha;
    private String elColposcopia;
    private String elColposcopiaFecha;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;

    public boolean mantenimientoConsultorioExtCarnetPerinatalEl(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_EL ?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getElId());
            cmd.setInt(2, getCpId());
            cmd.setString(3, getElHg1());
            cmd.setString(4, getElHg1Fecha());
            cmd.setString(5, getElHg2());
            cmd.setString(6, getElHg2Fecha());
            cmd.setString(7, getElHga());
            cmd.setString(8, getElHgaFecha());
            cmd.setString(9, getElGli1());
            cmd.setString(10, getElGli1Fecha());
            cmd.setString(11, getElGli2());
            cmd.setString(12, getElGli2Fecha());
            cmd.setString(13, getElGliTg());
            cmd.setString(14, getElGliTgFecha());
            cmd.setString(15, getElVdrl1());
            cmd.setString(16, getElVdrl1Fecha());
            cmd.setString(17, getElVdrl2());
            cmd.setString(18, getElVdrl2Fecha());
            cmd.setString(19, getElFta());
            cmd.setString(20, getElFtaFecha());
            cmd.setString(21, getElThpa());
            cmd.setString(22, getElThpaFecha());
            cmd.setString(23, getElPrs());
            cmd.setString(24, getElPrsFecha());
            cmd.setString(25, getElVpr());
            cmd.setString(26, getElVprFecha());
            cmd.setString(27, getElPr2());
            cmd.setString(28, getElPr2Fecha());
            cmd.setString(29, getElElisa());
            cmd.setString(30, getElElisaFecha());
            cmd.setString(31, getElFi());
            cmd.setString(32, getElFiFecha());
            cmd.setString(33, getElHtlvi());
            cmd.setString(34, getElHtlviFecha());
            cmd.setString(35, getElTorch());
            cmd.setString(36, getElTorchFecha());
            cmd.setString(37, getElGotaG());
            cmd.setString(38, getElGotaGFecha());
            cmd.setString(39, getElMpr());
            cmd.setString(40, getElMprFecha());
            cmd.setString(41, getElFm());
            cmd.setString(42, getElFmFecha());
            cmd.setString(43, getElEco());
            cmd.setString(44, getElEcoFecha());
            cmd.setString(45, getElLeuco());
            cmd.setString(46, getElLeucoFechar());
            cmd.setString(47, getElNitrit());
            cmd.setString(48, getElNitritFecha());
            cmd.setString(49, getElUrocultiv());
            cmd.setString(50, getElUrocultivFecha());
            cmd.setString(51, getElBk());
            cmd.setString(52, getElBkFecha());
            cmd.setString(53, getElLt());
            cmd.setString(54, getElLtFecha());
            cmd.setString(55, getElHb());
            cmd.setString(56, getElHbFecha());
            cmd.setString(57, getElPap());
            cmd.setString(58, getElPapFecha());
            cmd.setString(59, getElIvaa());
            cmd.setString(60, getElIvaaFecha());
            cmd.setString(61, getElColposcopia());
            cmd.setString(62, getElColposcopiaFecha());
            cmd.setString(63, getCodUsu());
            cmd.setString(64, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatalEl: " + ex.getMessage());
        }
        return resp;
    }
    
    public String perinatalElID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 EL_ID\n" +
                        "FROM CONSULTORIO_EXT_CARNET_PERINATAL_EL \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY EL_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("perinatalElID: " + ex.getMessage());
        }
        return cod;
    }   
    
    public ConsultorioExtCarnetPerinatalEl() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalEl(int elId) {
        this.elId = elId;
    }

    public int getElId() {
        return elId;
    }

    public void setElId(int elId) {
        this.elId = elId;
    }

    public String getElHg1() {
        return elHg1;
    }

    public void setElHg1(String elHg1) {
        this.elHg1 = elHg1;
    }

    public String getElHg1Fecha() {
        return elHg1Fecha;
    }

    public void setElHg1Fecha(String elHg1Fecha) {
        this.elHg1Fecha = elHg1Fecha;
    }

    public String getElHg2() {
        return elHg2;
    }

    public void setElHg2(String elHg2) {
        this.elHg2 = elHg2;
    }

    public String getElHg2Fecha() {
        return elHg2Fecha;
    }

    public void setElHg2Fecha(String elHg2Fecha) {
        this.elHg2Fecha = elHg2Fecha;
    }

    public String getElHga() {
        return elHga;
    }

    public void setElHga(String elHga) {
        this.elHga = elHga;
    }

    public String getElHgaFecha() {
        return elHgaFecha;
    }

    public void setElHgaFecha(String elHgaFecha) {
        this.elHgaFecha = elHgaFecha;
    }

    public String getElGli1() {
        return elGli1;
    }

    public void setElGli1(String elGli1) {
        this.elGli1 = elGli1;
    }

    public String getElGli1Fecha() {
        return elGli1Fecha;
    }

    public void setElGli1Fecha(String elGli1Fecha) {
        this.elGli1Fecha = elGli1Fecha;
    }

    public String getElGli2() {
        return elGli2;
    }

    public void setElGli2(String elGli2) {
        this.elGli2 = elGli2;
    }

    public String getElGli2Fecha() {
        return elGli2Fecha;
    }

    public void setElGli2Fecha(String elGli2Fecha) {
        this.elGli2Fecha = elGli2Fecha;
    }

    public String getElGliTg() {
        return elGliTg;
    }

    public void setElGliTg(String elGliTg) {
        this.elGliTg = elGliTg;
    }

    public String getElGliTgFecha() {
        return elGliTgFecha;
    }

    public void setElGliTgFecha(String elGliTgFecha) {
        this.elGliTgFecha = elGliTgFecha;
    }

    public String getElVdrl1() {
        return elVdrl1;
    }

    public void setElVdrl1(String elVdrl1) {
        this.elVdrl1 = elVdrl1;
    }

    public String getElVdrl1Fecha() {
        return elVdrl1Fecha;
    }

    public void setElVdrl1Fecha(String elVdrl1Fecha) {
        this.elVdrl1Fecha = elVdrl1Fecha;
    }

    public String getElVdrl2() {
        return elVdrl2;
    }

    public void setElVdrl2(String elVdrl2) {
        this.elVdrl2 = elVdrl2;
    }

    public String getElVdrl2Fecha() {
        return elVdrl2Fecha;
    }

    public void setElVdrl2Fecha(String elVdrl2Fecha) {
        this.elVdrl2Fecha = elVdrl2Fecha;
    }

    public String getElFta() {
        return elFta;
    }

    public void setElFta(String elFta) {
        this.elFta = elFta;
    }

    public String getElFtaFecha() {
        return elFtaFecha;
    }

    public void setElFtaFecha(String elFtaFecha) {
        this.elFtaFecha = elFtaFecha;
    }

    public String getElThpa() {
        return elThpa;
    }

    public void setElThpa(String elThpa) {
        this.elThpa = elThpa;
    }

    public String getElThpaFecha() {
        return elThpaFecha;
    }

    public void setElThpaFecha(String elThpaFecha) {
        this.elThpaFecha = elThpaFecha;
    }

    public String getElPrs() {
        return elPrs;
    }

    public void setElPrs(String elPrs) {
        this.elPrs = elPrs;
    }

    public String getElPrsFecha() {
        return elPrsFecha;
    }

    public void setElPrsFecha(String elPrsFecha) {
        this.elPrsFecha = elPrsFecha;
    }

    public String getElVpr() {
        return elVpr;
    }

    public void setElVpr(String elVpr) {
        this.elVpr = elVpr;
    }

    public String getElVprFecha() {
        return elVprFecha;
    }

    public void setElVprFecha(String elVprFecha) {
        this.elVprFecha = elVprFecha;
    }

    public String getElPr2() {
        return elPr2;
    }

    public void setElPr2(String elPr2) {
        this.elPr2 = elPr2;
    }

    public String getElPr2Fecha() {
        return elPr2Fecha;
    }

    public void setElPr2Fecha(String elPr2Fecha) {
        this.elPr2Fecha = elPr2Fecha;
    }

    public String getElElisa() {
        return elElisa;
    }

    public void setElElisa(String elElisa) {
        this.elElisa = elElisa;
    }

    public String getElElisaFecha() {
        return elElisaFecha;
    }

    public void setElElisaFecha(String elElisaFecha) {
        this.elElisaFecha = elElisaFecha;
    }

    public String getElFi() {
        return elFi;
    }

    public void setElFi(String elFi) {
        this.elFi = elFi;
    }

    public String getElFiFecha() {
        return elFiFecha;
    }

    public void setElFiFecha(String elFiFecha) {
        this.elFiFecha = elFiFecha;
    }

    public String getElHtlvi() {
        return elHtlvi;
    }

    public void setElHtlvi(String elHtlvi) {
        this.elHtlvi = elHtlvi;
    }

    public String getElHtlviFecha() {
        return elHtlviFecha;
    }

    public void setElHtlviFecha(String elHtlviFecha) {
        this.elHtlviFecha = elHtlviFecha;
    }

    public String getElTorch() {
        return elTorch;
    }

    public void setElTorch(String elTorch) {
        this.elTorch = elTorch;
    }

    public String getElTorchFecha() {
        return elTorchFecha;
    }

    public void setElTorchFecha(String elTorchFecha) {
        this.elTorchFecha = elTorchFecha;
    }

    public String getElGotaG() {
        return elGotaG;
    }

    public void setElGotaG(String elGotaG) {
        this.elGotaG = elGotaG;
    }

    public String getElGotaGFecha() {
        return elGotaGFecha;
    }

    public void setElGotaGFecha(String elGotaGFecha) {
        this.elGotaGFecha = elGotaGFecha;
    }

    public String getElMpr() {
        return elMpr;
    }

    public void setElMpr(String elMpr) {
        this.elMpr = elMpr;
    }

    public String getElMprFecha() {
        return elMprFecha;
    }

    public void setElMprFecha(String elMprFecha) {
        this.elMprFecha = elMprFecha;
    }

    public String getElFm() {
        return elFm;
    }

    public void setElFm(String elFm) {
        this.elFm = elFm;
    }

    public String getElFmFecha() {
        return elFmFecha;
    }

    public void setElFmFecha(String elFmFecha) {
        this.elFmFecha = elFmFecha;
    }

    public String getElEco() {
        return elEco;
    }

    public void setElEco(String elEco) {
        this.elEco = elEco;
    }

    public String getElEcoFecha() {
        return elEcoFecha;
    }

    public void setElEcoFecha(String elEcoFecha) {
        this.elEcoFecha = elEcoFecha;
    }

    public String getElLeuco() {
        return elLeuco;
    }

    public void setElLeuco(String elLeuco) {
        this.elLeuco = elLeuco;
    }

    public String getElLeucoFechar() {
        return elLeucoFechar;
    }

    public void setElLeucoFechar(String elLeucoFechar) {
        this.elLeucoFechar = elLeucoFechar;
    }

    public String getElNitrit() {
        return elNitrit;
    }

    public void setElNitrit(String elNitrit) {
        this.elNitrit = elNitrit;
    }

    public String getElNitritFecha() {
        return elNitritFecha;
    }

    public void setElNitritFecha(String elNitritFecha) {
        this.elNitritFecha = elNitritFecha;
    }

    public String getElUrocultiv() {
        return elUrocultiv;
    }

    public void setElUrocultiv(String elUrocultiv) {
        this.elUrocultiv = elUrocultiv;
    }

    public String getElUrocultivFecha() {
        return elUrocultivFecha;
    }

    public void setElUrocultivFecha(String elUrocultivFecha) {
        this.elUrocultivFecha = elUrocultivFecha;
    }

    public String getElBk() {
        return elBk;
    }

    public void setElBk(String elBk) {
        this.elBk = elBk;
    }

    public String getElBkFecha() {
        return elBkFecha;
    }

    public void setElBkFecha(String elBkFecha) {
        this.elBkFecha = elBkFecha;
    }

    public String getElLt() {
        return elLt;
    }

    public void setElLt(String elLt) {
        this.elLt = elLt;
    }

    public String getElLtFecha() {
        return elLtFecha;
    }

    public void setElLtFecha(String elLtFecha) {
        this.elLtFecha = elLtFecha;
    }

    public String getElHb() {
        return elHb;
    }

    public void setElHb(String elHb) {
        this.elHb = elHb;
    }

    public String getElHbFecha() {
        return elHbFecha;
    }

    public void setElHbFecha(String elHbFecha) {
        this.elHbFecha = elHbFecha;
    }

    public String getElPap() {
        return elPap;
    }

    public void setElPap(String elPap) {
        this.elPap = elPap;
    }

    public String getElPapFecha() {
        return elPapFecha;
    }

    public void setElPapFecha(String elPapFecha) {
        this.elPapFecha = elPapFecha;
    }

    public String getElIvaa() {
        return elIvaa;
    }

    public void setElIvaa(String elIvaa) {
        this.elIvaa = elIvaa;
    }

    public String getElIvaaFecha() {
        return elIvaaFecha;
    }

    public void setElIvaaFecha(String elIvaaFecha) {
        this.elIvaaFecha = elIvaaFecha;
    }

    public String getElColposcopia() {
        return elColposcopia;
    }

    public void setElColposcopia(String elColposcopia) {
        this.elColposcopia = elColposcopia;
    }

    public String getElColposcopiaFecha() {
        return elColposcopiaFecha;
    }

    public void setElColposcopiaFecha(String elColposcopiaFecha) {
        this.elColposcopiaFecha = elColposcopiaFecha;
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
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalEl[ elId=" + elId + " ]";
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
