/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.Programas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author PC-SISTEMA
 */
public class LAB_Solicitud_Inv_Bact {
      private Connection cn;
      private int codigo;
       private String cod_precio;
       private String cod_per;
       private String id_hc;
    private String hospServicio;
    private String caDesc;
    private String dir_referencia;
    private String codMuestraParaExa;
    private String antecTratamiento;
    private String diagnostico;
    private int mes_control_tratamiento;
    private String controlTratamiento;
    private Integer exNsolicBacil;
    private String exSolicBacil;
    private String tipoPruebaSens;
    private String pruebaSens;
    private String factoresriesgoTB;
    private String fechaSoli;
    private String horaSoli;
    private String idHcSolicita;
    private String nombres_pac_solicita;
    private String fechaObtencionMuestra;
    private String horaObtencionMuestra;
    private String calidadMuestra;
    private String observaciones;
    private String nomUsuSol;
    private String nomUsuRecp;
    private Character estadoInvBac;
      
       public LAB_Solicitud_Inv_Bact(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
       public boolean LAB_Solicitud_Inv_Bact_guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec sp_SOLICITUD_INV_BACT_insertar ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_precio());
            cmd.setString(2, getCod_per());
            cmd.setString(3, getId_hc());
            cmd.setString(4, getHospServicio());
            cmd.setString(5, getCaDesc());
            cmd.setString(6, getDir_referencia());
            cmd.setString(7, getCodMuestraParaExa());
            cmd.setString(8, getAntecTratamiento());
            cmd.setString(9, getDiagnostico());
            cmd.setInt(10, getMes_control_tratamiento());
            cmd.setString(11, getControlTratamiento());
            cmd.setInt(12, getExNsolicBacil());
            cmd.setString(13, getExSolicBacil());
            cmd.setString(14, getTipoPruebaSens());
            cmd.setString(15, getPruebaSens());
            cmd.setString(16, getFactoresriesgoTB());
            cmd.setString(17, getNomUsu());
 
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
       
        public boolean LAB_Solicitud_Inv_Bact_eliminar()
    {
        boolean resp = false;
        try{
            String sql = "exec sp_SOLICITUD_INV_BACT_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCodigo());
            if(!cmd.execute()){
                resp = true;
            }
            cmd.close();
            getCn().close();  
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
        
        
        public String LAB_DISA_SALUD(int cont){
        String cod="";
        try
        {
            String sql = " SELECT TOP 1 I.DESC_IPRESS,UE.UE_DESC FROM SISTEMA_IPRESS I, SISTEMA_UNIDAD_EJECUTORA UE "
                    + "WHERE I.ID_IPRESS=UE.ID_IPRESS ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(cont);
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }
        
        public String LAB_Cod_Muestra(){
        String cod="";
        try
        {
            String sql = " exec sp_LAB_MUESTRA_EXAMEN_buscar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, "Esputo");
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
        }
        
        public String codUsuario(String nombreUsuario)
    {
        String cod="";
        try
        {
            String sql = "SELECT USU_CODIGO FROM USUARIO WHERE Usu_Usuario = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombreUsuario);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_codUsuario: " + ex.getMessage());
        }
        return cod;
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
     * @return the hospServicio
     */
    public String getHospServicio() {
        return hospServicio;
    }

    /**
     * @param hospServicio the hospServicio to set
     */
    public void setHospServicio(String hospServicio) {
        this.hospServicio = hospServicio;
    }

    /**
     * @return the caDesc
     */
    public String getCaDesc() {
        return caDesc;
    }

    /**
     * @param caDesc the caDesc to set
     */
    public void setCaDesc(String caDesc) {
        this.caDesc = caDesc;
    }

    /**
     * @return the codMuestraParaExa
     */
    public String getCodMuestraParaExa() {
        return codMuestraParaExa;
    }

    /**
     * @param codMuestraParaExa the codMuestraParaExa to set
     */
    public void setCodMuestraParaExa(String codMuestraParaExa) {
        this.codMuestraParaExa = codMuestraParaExa;
    }

    /**
     * @return the antecTratamiento
     */
    public String getAntecTratamiento() {
        return antecTratamiento;
    }

    /**
     * @param antecTratamiento the antecTratamiento to set
     */
    public void setAntecTratamiento(String antecTratamiento) {
        this.antecTratamiento = antecTratamiento;
    }

    /**
     * @return the diagnostico
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * @param diagnostico the diagnostico to set
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }


    /**
     * @return the controlTratamiento
     */
    public String getControlTratamiento() {
        return controlTratamiento;
    }

    /**
     * @param controlTratamiento the controlTratamiento to set
     */
    public void setControlTratamiento(String controlTratamiento) {
        this.controlTratamiento = controlTratamiento;
    }

    /**
     * @return the exNsolicBacil
     */
    public Integer getExNsolicBacil() {
        return exNsolicBacil;
    }

    /**
     * @param exNsolicBacil the exNsolicBacil to set
     */
    public void setExNsolicBacil(Integer exNsolicBacil) {
        this.exNsolicBacil = exNsolicBacil;
    }

    /**
     * @return the exSolicBacil
     */
    public String getExSolicBacil() {
        return exSolicBacil;
    }

    /**
     * @param exSolicBacil the exSolicBacil to set
     */
    public void setExSolicBacil(String exSolicBacil) {
        this.exSolicBacil = exSolicBacil;
    }

    /**
     * @return the tipoPruebaSens
     */
    public String getTipoPruebaSens() {
        return tipoPruebaSens;
    }

    /**
     * @param tipoPruebaSens the tipoPruebaSens to set
     */
    public void setTipoPruebaSens(String tipoPruebaSens) {
        this.tipoPruebaSens = tipoPruebaSens;
    }

    /**
     * @return the pruebaSens
     */
    public String getPruebaSens() {
        return pruebaSens;
    }

    /**
     * @param pruebaSens the pruebaSens to set
     */
    public void setPruebaSens(String pruebaSens) {
        this.pruebaSens = pruebaSens;
    }

    /**
     * @return the factoresriesgoTB
     */
    public String getFactoresriesgoTB() {
        return factoresriesgoTB;
    }

    /**
     * @param factoresriesgoTB the factoresriesgoTB to set
     */
    public void setFactoresriesgoTB(String factoresriesgoTB) {
        this.factoresriesgoTB = factoresriesgoTB;
    }

    /**
     * @return the fechaSoli
     */
    public String getFechaSoli() {
        return fechaSoli;
    }

    /**
     * @param fechaSoli the fechaSoli to set
     */
    public void setFechaSoli(String fechaSoli) {
        this.fechaSoli = fechaSoli;
    }

    /**
     * @return the horaSoli
     */
    public String getHoraSoli() {
        return horaSoli;
    }

    /**
     * @param horaSoli the horaSoli to set
     */
    public void setHoraSoli(String horaSoli) {
        this.horaSoli = horaSoli;
    }

    /**
     * @return the idHcSolicita
     */
    public String getIdHcSolicita() {
        return idHcSolicita;
    }

    /**
     * @param idHcSolicita the idHcSolicita to set
     */
    public void setIdHcSolicita(String idHcSolicita) {
        this.idHcSolicita = idHcSolicita;
    }

    /**
     * @return the fechaObtencionMuestra
     */
    public String getFechaObtencionMuestra() {
        return fechaObtencionMuestra;
    }

    /**
     * @param fechaObtencionMuestra the fechaObtencionMuestra to set
     */
    public void setFechaObtencionMuestra(String fechaObtencionMuestra) {
        this.fechaObtencionMuestra = fechaObtencionMuestra;
    }

    /**
     * @return the horaObtencionMuestra
     */
    public String getHoraObtencionMuestra() {
        return horaObtencionMuestra;
    }

    /**
     * @param horaObtencionMuestra the horaObtencionMuestra to set
     */
    public void setHoraObtencionMuestra(String horaObtencionMuestra) {
        this.horaObtencionMuestra = horaObtencionMuestra;
    }

    /**
     * @return the calidadMuestra
     */
    public String getCalidadMuestra() {
        return calidadMuestra;
    }

    /**
     * @param calidadMuestra the calidadMuestra to set
     */
    public void setCalidadMuestra(String calidadMuestra) {
        this.calidadMuestra = calidadMuestra;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the nomUsu
     */
    public String getNomUsu() {
        return getNomUsuSol();
    }

    /**
     * @param nomUsu the nomUsu to set
     */
    public void setNomUsu(String nomUsu) {
        this.setNomUsuSol(nomUsu);
    }

    /**
     * @return the estadoInvBac
     */
    public Character getEstadoInvBac() {
        return estadoInvBac;
    }

    /**
     * @param estadoInvBac the estadoInvBac to set
     */
    public void setEstadoInvBac(Character estadoInvBac) {
        this.estadoInvBac = estadoInvBac;
    }

    /**
     * @return the cod_precio
     */
    public String getCod_precio() {
        return cod_precio;
    }

    /**
     * @param cod_precio the cod_precio to set
     */
    public void setCod_precio(String cod_precio) {
        this.cod_precio = cod_precio;
    }

    /**
     * @return the id_hc
     */
    public String getId_hc() {
        return id_hc;
    }

    /**
     * @param id_hc the id_hc to set
     */
    public void setId_hc(String id_hc) {
        this.id_hc = id_hc;
    }

    /**
     * @return the nomUsuSol
     */
    public String getNomUsuSol() {
        return nomUsuSol;
    }

    /**
     * @param nomUsuSol the nomUsuSol to set
     */
    public void setNomUsuSol(String nomUsuSol) {
        this.nomUsuSol = nomUsuSol;
    }

    /**
     * @return the nomUsuRecp
     */
    public String getNomUsuRecp() {
        return nomUsuRecp;
    }

    /**
     * @param nomUsuRecp the nomUsuRecp to set
     */
    public void setNomUsuRecp(String nomUsuRecp) {
        this.nomUsuRecp = nomUsuRecp;
    }

    /**
     * @return the cod_per
     */
    public String getCod_per() {
        return cod_per;
    }

    /**
     * @param cod_per the cod_per to set
     */
    public void setCod_per(String cod_per) {
        this.cod_per = cod_per;
    }

    /**
     * @return the dir_referencia
     */
    public String getDir_referencia() {
        return dir_referencia;
    }

    /**
     * @param dir_referencia the dir_referencia to set
     */
    public void setDir_referencia(String dir_referencia) {
        this.dir_referencia = dir_referencia;
    }

    /**
     * @return the mes_control_tratamiento
     */
    public int getMes_control_tratamiento() {
        return mes_control_tratamiento;
    }

    /**
     * @param mes_control_tratamiento the mes_control_tratamiento to set
     */
    public void setMes_control_tratamiento(int mes_control_tratamiento) {
        this.mes_control_tratamiento = mes_control_tratamiento;
    }

    /**
     * @return the nombres_pac_solicita
     */
    public String getNombres_pac_solicita() {
        return nombres_pac_solicita;
    }

    /**
     * @param nombres_pac_solicita the nombres_pac_solicita to set
     */
    public void setNombres_pac_solicita(String nombres_pac_solicita) {
        this.nombres_pac_solicita = nombres_pac_solicita;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
       
       
}
