/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.cuentaPorPagar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;
import vista.cuentaPorPagar.NotasCreditoDebito;
import static vista.cuentaPorPagar.NotasCreditoDebito.lblNroCorrelativo;

/**
 *
 * @author Profe
 */
public class CuentasPorPagarNotaDeCreditoCabecera {
    private Conexion con = new Conexion();
    private Connection cn;
    private int idFactura;
    private String nota_credito;
    private String descripcion;
    private String serie;
    private String correlativo;
    private String fechaEmision;
    private String cod_usu;
    
       public CuentasPorPagarNotaDeCreditoCabecera()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public String generarSerieCorrelativo(String serie){
        String cor="";
        try {
             
            String sql = "exec CUENTAS_POR_PAGAR_NOTA_CREDITO_GENERAR_Serie_Correlativo ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, serie);
            ResultSet r = cmd.executeQuery();
        if(r.next()){          
            cor=r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error: generarSerieCorrelativo - " + ex.getMessage());
        }
        return cor;
    }
    public boolean mantenimientoCuentasPorPagarNotaCredito()
        {
        boolean resp = false;
        try{
            String sql = "sp_CUENTAS_POR_PAGAR_NOTA_CREDITO  ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getIdFactura());
            cmd.setString(2, getNota_credito());
            cmd.setString(3, getDescripcion());
            cmd.setString(4, getSerie());
            cmd.setString(5, getCorrelativo());
            cmd.setString(6, getCod_usu());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoCuentasPorPagarFacturasCabecera: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean CuentasPorPagarFacturaEstado(int CPF_ID,String tipo){
         boolean resp = false;
        try{
            String sql = "EXEC sp_CUENTAS_POR_PAGAR_FACTURA_CABECERA_update ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, CPF_ID);
            cmd.setString(2, tipo);
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

    /**
     * @return the con
     */
    public Conexion getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Conexion con) {
        this.con = con;
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
     * @return the idFactura
     */
    public int getIdFactura() {
        return idFactura;
    }

    /**
     * @param idFactura the idFactura to set
     */
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the correlativo
     */
    public String getCorrelativo() {
        return correlativo;
    }

    /**
     * @param correlativo the correlativo to set
     */
    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    /**
     * @return the fechaEmision
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * @return the nota_credito
     */
    public String getNota_credito() {
        return nota_credito;
    }

    /**
     * @param nota_credito the nota_credito to set
     */
    public void setNota_credito(String nota_credito) {
        this.nota_credito = nota_credito;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the cod_usu
     */
    public String getCod_usu() {
        return cod_usu;
    }

    /**
     * @param cod_usu the cod_usu to set
     */
    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }
    
    
}
