/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.cuentaCorriente;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;

@Entity
@Table(name = "CUENTAS_POR_PAGAR_FACTURAS_DETALLE")
@NamedQueries({
    @NamedQuery(name = "CuentasPorPagarFacturasDetalle.findAll", query = "SELECT c FROM CuentasPorPagarFacturasDetalle c")})
public class CuentasPorPagarFacturasDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CPD_ID")
    private int cpdId;
    private int cpfId;
    private String nomenclatura;
    @Column(name = "CPD_GRAV")
    private String cpdGrav;
    @Column(name = "CPD_COD_UNIDAD")
    private String cpdCodUnidad;
    @Column(name = "CPD_CANTIDAD")
    private Integer cpdCantidad;
    @Column(name = "CPD_COD_PROD_SUNAT")
    private String cpdCodProdSunat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CPD_VALOR_U")
    private BigDecimal cpdValorU;
    @Column(name = "CPD_DESC_PORCEN")
    private BigDecimal cpdDescPorcen;
    @Column(name = "CPD_DSCTO")
    private BigDecimal cpdDscto;
    @Column(name = "CPD_IGV")
    private BigDecimal cpdIgv;
    @Column(name = "CPD_AFEC_IGV")
    private String cpdAfecIgv;
    @Column(name = "CPD_ISC")
    private BigDecimal cpdIsc;
    @Column(name = "CPD_AFEC_ISC")
    private String cpdAfecIsc;
    @Column(name = "CPD_PRECIO_VENTA")
    private BigDecimal cpdPrecioVenta;
    @Column(name = "CPD_VALOR_VENTA")
    private BigDecimal cpdValorVenta;
    @Column(name = "CPD_DSCTO_GLOBAL")
    private BigDecimal cpdDsctoGlobal;
    @Column(name = "CPD_SUM_OTROS_CARGOS")
    private BigDecimal cpdSumOtrosCargos;
    @Column(name = "CPD_SUM_IGV")
    private BigDecimal cpdSumIgv;
    @Column(name = "CPD_T_VV_INAFEC")
    private BigDecimal cpdTVvInafec;
    @Column(name = "CPD_T_VV_GRAV")
    private BigDecimal cpdTVvGrav;
    @Column(name = "CPD_T_DSCTOS")
    private BigDecimal cpdTDsctos;
    @Column(name = "CPD_OTROS_TRIBUT")
    private BigDecimal cpdOtrosTribut;
    @Column(name = "CPD_SUM_ISC")
    private BigDecimal cpdSumIsc;
    @Column(name = "CPD_T_V_EXONEN")
    private BigDecimal cpdTVExonen;
    @Column(name = "CPD_IMP_TOT_VTAS")
    private BigDecimal cpdImpTotVtas;
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
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;

    public boolean mantenimientoCuentasPorPagarFacturasDetalle(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "[CUENTAS_POR_PAGAR_MANTENIMIENTO_FACTURAS_DETALLE] ?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCpdId());
            cmd.setInt(2, getCpfId());
            cmd.setString(3, getCpdGrav());
            cmd.setString(4, getCpdCodUnidad());
            cmd.setInt(5, getCpdCantidad());
            cmd.setString(6, getNomenclatura());
            cmd.setString(7, getCpdCodProdSunat());
            cmd.setBigDecimal(8, getCpdValorU());
            cmd.setBigDecimal(9, getCpdDescPorcen());
            cmd.setBigDecimal(10, getCpdDscto());
            cmd.setBigDecimal(11, getCpdIgv());
            cmd.setString(12, getCpdAfecIgv());
            cmd.setBigDecimal(13, getCpdIsc());
            cmd.setString(14, getCpdAfecIsc());
            cmd.setBigDecimal(15, getCpdPrecioVenta());
            cmd.setBigDecimal(16, getCpdValorVenta());
            cmd.setBigDecimal(17, getCpdDsctoGlobal());
            cmd.setBigDecimal(18, getCpdSumOtrosCargos());
            cmd.setBigDecimal(19, getCpdSumIgv());
            cmd.setBigDecimal(20, getCpdTVvInafec());
            cmd.setBigDecimal(21, getCpdTVvGrav());
            cmd.setBigDecimal(22, getCpdTDsctos());
            cmd.setBigDecimal(23, getCpdOtrosTribut());
            cmd.setBigDecimal(24, getCpdSumIsc());
            cmd.setBigDecimal(25, getCpdTVExonen());
            cmd.setBigDecimal(26, getCpdImpTotVtas());
            cmd.setString(27, getCodUsu());
            cmd.setString(28, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoCuentasPorPagarFacturasDetalle: " + ex.getMessage());
        }
        return resp;
    }
    
    public CuentasPorPagarFacturasDetalle() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public CuentasPorPagarFacturasDetalle(int cpdId) {
        this.cpdId = cpdId;
    }

    public int getCpdId() {
        return cpdId;
    }

    public void setCpdId(int cpdId) {
        this.cpdId = cpdId;
    }

    public String getCpdGrav() {
        return cpdGrav;
    }

    public void setCpdGrav(String cpdGrav) {
        this.cpdGrav = cpdGrav;
    }

    public String getCpdCodUnidad() {
        return cpdCodUnidad;
    }

    public void setCpdCodUnidad(String cpdCodUnidad) {
        this.cpdCodUnidad = cpdCodUnidad;
    }

    public Integer getCpdCantidad() {
        return cpdCantidad;
    }

    public void setCpdCantidad(Integer cpdCantidad) {
        this.cpdCantidad = cpdCantidad;
    }

    public String getCpdCodProdSunat() {
        return cpdCodProdSunat;
    }

    public void setCpdCodProdSunat(String cpdCodProdSunat) {
        this.cpdCodProdSunat = cpdCodProdSunat;
    }

    public BigDecimal getCpdValorU() {
        return cpdValorU;
    }

    public void setCpdValorU(BigDecimal cpdValorU) {
        this.cpdValorU = cpdValorU;
    }

    public BigDecimal getCpdDescPorcen() {
        return cpdDescPorcen;
    }

    public void setCpdDescPorcen(BigDecimal cpdDescPorcen) {
        this.cpdDescPorcen = cpdDescPorcen;
    }

    public BigDecimal getCpdDscto() {
        return cpdDscto;
    }

    public void setCpdDscto(BigDecimal cpdDscto) {
        this.cpdDscto = cpdDscto;
    }

    public BigDecimal getCpdIgv() {
        return cpdIgv;
    }

    public void setCpdIgv(BigDecimal cpdIgv) {
        this.cpdIgv = cpdIgv;
    }

    public String getCpdAfecIgv() {
        return cpdAfecIgv;
    }

    public void setCpdAfecIgv(String cpdAfecIgv) {
        this.cpdAfecIgv = cpdAfecIgv;
    }

    public BigDecimal getCpdIsc() {
        return cpdIsc;
    }

    public void setCpdIsc(BigDecimal cpdIsc) {
        this.cpdIsc = cpdIsc;
    }

    public String getCpdAfecIsc() {
        return cpdAfecIsc;
    }

    public void setCpdAfecIsc(String cpdAfecIsc) {
        this.cpdAfecIsc = cpdAfecIsc;
    }

    public BigDecimal getCpdPrecioVenta() {
        return cpdPrecioVenta;
    }

    public void setCpdPrecioVenta(BigDecimal cpdPrecioVenta) {
        this.cpdPrecioVenta = cpdPrecioVenta;
    }

    public BigDecimal getCpdValorVenta() {
        return cpdValorVenta;
    }

    public void setCpdValorVenta(BigDecimal cpdValorVenta) {
        this.cpdValorVenta = cpdValorVenta;
    }

    public BigDecimal getCpdDsctoGlobal() {
        return cpdDsctoGlobal;
    }

    public void setCpdDsctoGlobal(BigDecimal cpdDsctoGlobal) {
        this.cpdDsctoGlobal = cpdDsctoGlobal;
    }

    public BigDecimal getCpdSumOtrosCargos() {
        return cpdSumOtrosCargos;
    }

    public void setCpdSumOtrosCargos(BigDecimal cpdSumOtrosCargos) {
        this.cpdSumOtrosCargos = cpdSumOtrosCargos;
    }

    public BigDecimal getCpdSumIgv() {
        return cpdSumIgv;
    }

    public void setCpdSumIgv(BigDecimal cpdSumIgv) {
        this.cpdSumIgv = cpdSumIgv;
    }

    public BigDecimal getCpdTVvInafec() {
        return cpdTVvInafec;
    }

    public void setCpdTVvInafec(BigDecimal cpdTVvInafec) {
        this.cpdTVvInafec = cpdTVvInafec;
    }

    public BigDecimal getCpdTVvGrav() {
        return cpdTVvGrav;
    }

    public void setCpdTVvGrav(BigDecimal cpdTVvGrav) {
        this.cpdTVvGrav = cpdTVvGrav;
    }

    public BigDecimal getCpdTDsctos() {
        return cpdTDsctos;
    }

    public void setCpdTDsctos(BigDecimal cpdTDsctos) {
        this.cpdTDsctos = cpdTDsctos;
    }

    public BigDecimal getCpdOtrosTribut() {
        return cpdOtrosTribut;
    }

    public void setCpdOtrosTribut(BigDecimal cpdOtrosTribut) {
        this.cpdOtrosTribut = cpdOtrosTribut;
    }

    public BigDecimal getCpdSumIsc() {
        return cpdSumIsc;
    }

    public void setCpdSumIsc(BigDecimal cpdSumIsc) {
        this.cpdSumIsc = cpdSumIsc;
    }

    public BigDecimal getCpdTVExonen() {
        return cpdTVExonen;
    }

    public void setCpdTVExonen(BigDecimal cpdTVExonen) {
        this.cpdTVExonen = cpdTVExonen;
    }

    public BigDecimal getCpdImpTotVtas() {
        return cpdImpTotVtas;
    }

    public void setCpdImpTotVtas(BigDecimal cpdImpTotVtas) {
        this.cpdImpTotVtas = cpdImpTotVtas;
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
        return "modelos.cuentaCorriente.CuentasPorPagarFacturasDetalle[ cpdId=" + cpdId + " ]";
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
     * @return the cpfId
     */
    public int getCpfId() {
        return cpfId;
    }

    /**
     * @param cpfId the cpfId to set
     */
    public void setCpfId(int cpfId) {
        this.cpfId = cpfId;
    }

    /**
     * @return the nomenclatura
     */
    public String getNomenclatura() {
        return nomenclatura;
    }

    /**
     * @param nomenclatura the nomenclatura to set
     */
    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }
    
}
