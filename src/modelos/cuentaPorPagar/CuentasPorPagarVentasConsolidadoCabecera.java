package modelos.cuentaPorPagar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Caja.Caja_NuevaVenta;
import servicios.Conexion;
import vista.cuentaPorPagar.VentasConsolidado;
import static vista.cuentaPorPagar.VentasConsolidado.txtDni;

public class CuentasPorPagarVentasConsolidadoCabecera {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    
    public void formatoVentasConsolidadoCabecera(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(0);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getColumnModel().getColumn(5).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(200);
        tabla.getColumnModel().getColumn(12).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(13).setMinWidth(0);
        tabla.getColumnModel().getColumn(13).setMaxWidth(0);
        tabla.getColumnModel().getColumn(14).setMinWidth(0);
        tabla.getColumnModel().getColumn(14).setMaxWidth(0);
    }
    
    public void ventasConsolidadoCabecera(JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
             String titulos[]={"Documento","Nº Documento","Forma de Pago","DNI","HC","C","Estado","Descuento","SubTotal","IGV","Total","Fecha","Hora","Am","ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="CUENTAS_POR_PAGAR_VENTAS_CONSOLIDADO_CABECERA ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtDni.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);
            fila[4]=r.getString(5);
            fila[5]=r.getString(6);
            fila[6]=r.getString(7);
            fila[7]=r.getString(8);
            fila[8]=r.getString(9);
            fila[9]=r.getString(10);
            fila[10]=r.getString(11);
            fila[11]=r.getString(12);
            fila[12]=r.getString(13);
            fila[13]=r.getString(14);
            fila[14]=r.getString(15);
                m.addRow(fila);
                c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoVentasConsolidadoCabecera(tabla);
        } catch (Exception e) {
            System.out.println("Error: ventasConsolidadoCabecera" + e.getMessage());
        }
      }

     public void listarActoMedico(){
        try {
            Statement sta=cn.createStatement();
            ResultSet rs=sta.executeQuery("EXEC CUENTAS_POR_PAGAR_LISTAR_ACTO_MEDICO ''");
            VentasConsolidado.cbxActoMedico.removeAllItems();
            VentasConsolidado.cbxActoMedico.addItem("Acto Médico");
            while(rs.next()){
//                cbxActoMedico.addItem(rs.getInt("NUM_ACTOMEDICO"));
            }

            } catch (SQLException e) {
                System.out.println("error:" + e.getMessage());
            }
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
