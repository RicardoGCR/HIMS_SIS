/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.cuentaCorriente;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author PC02
 */
@Entity
@Table(name = "CUENTAS_POR_PAGAR_FACTURAS_DETALLE")
@NamedQueries({
    @NamedQuery(name = "CuentasPorPagarFacturasDetalle.findAll", query = "SELECT c FROM CuentasPorPagarFacturasDetalle c")})
public class CuentasPorPagarFacturasDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CPD_ID")
    private Long cpdId;
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

    public CuentasPorPagarFacturasDetalle() {
    }

    public CuentasPorPagarFacturasDetalle(Long cpdId) {
        this.cpdId = cpdId;
    }

    public Long getCpdId() {
        return cpdId;
    }

    public void setCpdId(Long cpdId) {
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
    public int hashCode() {
        int hash = 0;
        hash += (cpdId != null ? cpdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentasPorPagarFacturasDetalle)) {
            return false;
        }
        CuentasPorPagarFacturasDetalle other = (CuentasPorPagarFacturasDetalle) object;
        if ((this.cpdId == null && other.cpdId != null) || (this.cpdId != null && !this.cpdId.equals(other.cpdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.cuentaCorriente.CuentasPorPagarFacturasDetalle[ cpdId=" + cpdId + " ]";
    }
    
}
