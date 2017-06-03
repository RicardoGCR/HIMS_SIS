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

import javax.swing.table.DefaultTableModel;
import servicios.Conexion;

public class CuentasPorPagarFacturasDetalle implements Serializable {
    private static final long serialVersionUID = 1L;

    private int cpdId;
<<<<<<< HEAD

=======
    private int cpfId;
    private String nomenclatura;
    @Column(name = "CPD_GRAV")
>>>>>>> 3cbfaf97c6f87dc0c0c4016e63261f25cb75d3a6
    private String cpdGrav;

    private String cpdCodUnidad;

    private Integer cpdCantidad;

    private String cpdCodProdSunat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    private BigDecimal cpdValorU;

    private BigDecimal cpdDescPorcen;

    private BigDecimal cpdDscto;

    private BigDecimal cpdIgv;
   
    private String cpdAfecIgv;
 
    private BigDecimal cpdIsc;

    private String cpdAfecIsc;

    private BigDecimal cpdPrecioVenta;

    private BigDecimal cpdValorVenta;

    private BigDecimal cpdDsctoGlobal;

    private BigDecimal cpdSumOtrosCargos;

    private BigDecimal cpdSumIgv;

    private BigDecimal cpdTVvInafec;

    private BigDecimal cpdTVvGrav;

    private BigDecimal cpdTDsctos;

    private BigDecimal cpdOtrosTribut;

    private BigDecimal cpdSumIsc;

    private BigDecimal cpdTVExonen;

    private BigDecimal cpdImpTotVtas;

    private String fechaActu;

    private String horaActu;

    private String nomPc;

    private Character estado;

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
