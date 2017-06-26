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
    int id;
    private String serie;
    private String correlativo;
    private String fechaEmision;
    
    
    public void generarSerieCorrelativo(String serie){
        try {
            String sql = "exec CUENTAS_POR_PAGAR_GENERAR_SERIE_CORRELATIVO ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, serie);
            ResultSet r = cmd.executeQuery();
        if(r.next()){          
               lblNroCorrelativo.setText(r.getString(1));
        }
        }catch(Exception ex){
            System.out.println("Error: generarSerieCorrelativo - " + ex.getMessage());
        }
    }
    public boolean mantenimientoCuentasPorPagarFacturasCabecera(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CUENTAS_POR_PAGAR_NOTA_CREDITO  ?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId());
            cmd.setString(2, getSerie());
            cmd.setString(3, getCorrelativo());
            cmd.setString(4, getTipoOperacion());
            cmd.setString(5, getFechaEmision());
            cmd.setString(6, getTipoMoneda());
            cmd.setString(7, getDocumento());
            cmd.setInt(8, getActoMedico());
            cmd.setString(9, getCod_usu());
            cmd.setString(10, tipo);
            cmd.setString(11, getCodigoEmpresa());
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
    
    
}
