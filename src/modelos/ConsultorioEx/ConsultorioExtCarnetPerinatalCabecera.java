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
import javax.xml.bind.annotation.XmlRootElement;
import servicios.Conexion;
import vista.ConsultorioEx.RegistroEmbarazo;

/**
 *
 * @author PC02
 */
@Entity
@Table(name = "CONSULTORIO_EXT_CARNET_PERINATAL_CABECERA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalCabecera.findAll", query = "SELECT c FROM ConsultorioExtCarnetPerinatalCabecera c"),
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalCabecera.findByCpId", query = "SELECT c FROM ConsultorioExtCarnetPerinatalCabecera c WHERE c.cpId = :cpId"),
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalCabecera.findByCpEstbOrigen", query = "SELECT c FROM ConsultorioExtCarnetPerinatalCabecera c WHERE c.cpEstbOrigen = :cpEstbOrigen"),
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalCabecera.findByCpEstbAct", query = "SELECT c FROM ConsultorioExtCarnetPerinatalCabecera c WHERE c.cpEstbAct = :cpEstbAct"),
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalCabecera.findByCpTipoSeguro", query = "SELECT c FROM ConsultorioExtCarnetPerinatalCabecera c WHERE c.cpTipoSeguro = :cpTipoSeguro"),
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalCabecera.findByCpEdad", query = "SELECT c FROM ConsultorioExtCarnetPerinatalCabecera c WHERE c.cpEdad = :cpEdad"),
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalCabecera.findByCpCodigoAfil", query = "SELECT c FROM ConsultorioExtCarnetPerinatalCabecera c WHERE c.cpCodigoAfil = :cpCodigoAfil"),
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalCabecera.findByCpEstudios", query = "SELECT c FROM ConsultorioExtCarnetPerinatalCabecera c WHERE c.cpEstudios = :cpEstudios"),
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalCabecera.findByCpAniosAprob", query = "SELECT c FROM ConsultorioExtCarnetPerinatalCabecera c WHERE c.cpAniosAprob = :cpAniosAprob"),
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalCabecera.findByCpPadreRn", query = "SELECT c FROM ConsultorioExtCarnetPerinatalCabecera c WHERE c.cpPadreRn = :cpPadreRn"),
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalCabecera.findByFechaActu", query = "SELECT c FROM ConsultorioExtCarnetPerinatalCabecera c WHERE c.fechaActu = :fechaActu"),
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalCabecera.findByHoraActu", query = "SELECT c FROM ConsultorioExtCarnetPerinatalCabecera c WHERE c.horaActu = :horaActu"),
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalCabecera.findByEstado", query = "SELECT c FROM ConsultorioExtCarnetPerinatalCabecera c WHERE c.estado = :estado"),
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalCabecera.findByNomPc", query = "SELECT c FROM ConsultorioExtCarnetPerinatalCabecera c WHERE c.nomPc = :nomPc"),
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalCabecera.findByCodUsu", query = "SELECT c FROM ConsultorioExtCarnetPerinatalCabecera c WHERE c.codUsu = :codUsu")})
public class ConsultorioExtCarnetPerinatalCabecera implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CP_ID")
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private String idHc;
    @Column(name = "CP_ESTB_ORIGEN")
    private String cpEstbOrigen;
    @Column(name = "CP_ESTB_ACT")
    private String cpEstbAct;
    @Column(name = "CP_TIPO_SEGURO")
    private String cpTipoSeguro;
    @Column(name = "CP_EDAD")
    private String cpEdad;
    @Column(name = "CP_CODIGO_AFIL")
    private String cpCodigoAfil;
    @Column(name = "CP_ESTUDIOS")
    private String cpEstudios;
    @Column(name = "CP_ANIOS_APROB")
    private String cpAniosAprob;
    @Column(name = "CP_PADRE_RN")
    private String cpPadreRn;
    @Column(name = "FECHA_ACTU")
    private String fechaActu;
    @Column(name = "HORA_ACTU")
    private String horaActu;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "NOM_PC")
    private String nomPc;
    @Column(name = "COD_USU")
    private String codUsu;

    public boolean mantenimientoConsultorioExtCarnetPerinatalCabecera(String tipo,String triaje)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_CABECERA ?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCpId());
            cmd.setString(2, getIdHc());
            cmd.setString(3, getCpEstbOrigen());
            cmd.setString(4, getCpEstbAct());
            cmd.setString(5, getCpTipoSeguro());
            cmd.setString(6, getCpEdad());
            cmd.setString(7, getCpCodigoAfil());
            cmd.setString(8, getCpEstudios());
            cmd.setString(9, getCpAniosAprob());
            cmd.setString(10, getCpPadreRn());
            cmd.setString(11, getCodUsu());
            cmd.setString(12, tipo);
            cmd.setString(13, triaje);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatalCabecera: " + ex.getMessage());
        }
        return resp;
    }
    
    public String perinatalCabeceraID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 CP_ID\n" +
                        "FROM CONSULTORIO_EXT_CARNET_PERINATAL_CABECERA \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY CP_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("perinatalCabeceraID: " + ex.getMessage());
        }
        return cod;
    }
    
    public void mostrarDatosHC(String id_hc){
        String consulta="";
        try {
            consulta="EXEC [CONSULTORIO_EXT_DATOS_HC] ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, id_hc);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                RegistroEmbarazo.lblDireccion.setText(r.getString(4)); 
                RegistroEmbarazo.lblDepartamento.setText(r.getString(5));
                RegistroEmbarazo.lblProvincia.setText(r.getString(6));
                RegistroEmbarazo.lblDistrito.setText(r.getString(7));
                RegistroEmbarazo.lblSector.setText(r.getString(8));
                RegistroEmbarazo.lblCelular.setText(r.getString(9));
                RegistroEmbarazo.lblTelefono.setText(r.getString(10));
                RegistroEmbarazo.lblEstadoCiv.setText(r.getString(11));
                RegistroEmbarazo.lblEdad.setText(r.getString(12));
                RegistroEmbarazo.lblOcupacion.setText(r.getString(13));
                int edad = Integer.parseInt(r.getString(12));
                if(edad < 15 || edad > 35)
                    RegistroEmbarazo.ChkEdad.setText("X");
                else
                    RegistroEmbarazo.ChkEdad.setText("");
            }
            //
        } catch (Exception e) {
            System.out.println("mostrarDatosHC: " + e.getMessage());
        }
    }
    
    public ConsultorioExtCarnetPerinatalCabecera() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalCabecera(int cpId) {
        this.cpId = cpId;
    }

    public int getCpId() {
        return cpId;
    }

    public void setCpId(int cpId) {
        this.cpId = cpId;
    }

    public String getCpEstbOrigen() {
        return cpEstbOrigen;
    }

    public void setCpEstbOrigen(String cpEstbOrigen) {
        this.cpEstbOrigen = cpEstbOrigen;
    }

    public String getCpEstbAct() {
        return cpEstbAct;
    }

    public void setCpEstbAct(String cpEstbAct) {
        this.cpEstbAct = cpEstbAct;
    }

    public String getCpTipoSeguro() {
        return cpTipoSeguro;
    }

    public void setCpTipoSeguro(String cpTipoSeguro) {
        this.cpTipoSeguro = cpTipoSeguro;
    }

    public String getCpEdad() {
        return cpEdad;
    }

    public void setCpEdad(String cpEdad) {
        this.cpEdad = cpEdad;
    }

    public String getCpCodigoAfil() {
        return cpCodigoAfil;
    }

    public void setCpCodigoAfil(String cpCodigoAfil) {
        this.cpCodigoAfil = cpCodigoAfil;
    }

    public String getCpEstudios() {
        return cpEstudios;
    }

    public void setCpEstudios(String cpEstudios) {
        this.cpEstudios = cpEstudios;
    }

    public String getCpAniosAprob() {
        return cpAniosAprob;
    }

    public void setCpAniosAprob(String cpAniosAprob) {
        this.cpAniosAprob = cpAniosAprob;
    }

    public String getCpPadreRn() {
        return cpPadreRn;
    }

    public void setCpPadreRn(String cpPadreRn) {
        this.cpPadreRn = cpPadreRn;
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

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }


    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalCabecera[ cpId=" + cpId + " ]";
    }

    /**
     * @return the idHc
     */
    public String getIdHc() {
        return idHc;
    }

    /**
     * @param idHc the idHc to set
     */
    public void setIdHc(String idHc) {
        this.idHc = idHc;
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
    
}
