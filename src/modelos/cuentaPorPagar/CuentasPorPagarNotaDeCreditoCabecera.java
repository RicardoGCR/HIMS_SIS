/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.cuentaPorPagar;

import java.sql.Connection;
import java.sql.ResultSet;
import servicios.Conexion;
import vista.cuentaPorPagar.NotasCreditoDebito;

/**
 *
 * @author Profe
 */
public class CuentasPorPagarNotaDeCreditoCabecera {
    Conexion con = new Conexion();
    private Connection cn;
    
    public void generarSerieCorrelativo(){
        try {
            String consulta = "exec CUENTAS_POR_PAGAR_NOTA_CREDITO_GENERAR_Serie_Correlativo";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               NotasCreditoDebito.txtSerieCorrelativo.setText(r.getString(0)+r.getString(1));
              
        }
        }catch(Exception ex){
            System.out.println("Error: generarSerieCorrelativo - " + ex.getMessage());
        }
    }
    
}
