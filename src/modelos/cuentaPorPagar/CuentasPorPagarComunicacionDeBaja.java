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
public class CuentasPorPagarComunicacionDeBaja {
    private Conexion con = new Conexion();
    private Connection cn;
    private int idFactura;
    private String descripcion;
    private String numero;
    private String cod_usu;
    
       public CuentasPorPagarComunicacionDeBaja()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public String generarNroBaja(){
        String cor="";
         try {
            String consulta = "exec CUENTAS_POR_PAGAR_COMUNICACION_BAJA_GENERAR_numero";
            ResultSet r;
            r=getCon().Listar(consulta);
        if(r.next()){
               cor=r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error: generarSerieCorrelativo - " + ex.getMessage());
        }
         return cor;
    }
    
  
    
    public boolean mantenimientoCuentasPorPagarComunicacionBaja()
        {
        boolean resp = false;
        try{
            String sql = "sp_CUENTAS_POR_PAGAR_COMUNICACION_BAJA ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getIdFactura());
            cmd.setString(2, getDescripcion());
            cmd.setString(3, getNumero());
            cmd.setString(4, getCod_usu());
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
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
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
