/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import vista.Caja.Caja_Pagos;

/**
 *
 * @author Administrator
 */
public class Impresora {
DefaultTableModel m;
private Connection cn;  
private String PRINT;
private String HC;
private String TIPO;
private String FECHA;
private String ID_CAB;


    public boolean IMPRESORA_N(){
        boolean resp = false;
        try{
            String sql = "exec SISTEMA_ACTUALIZAR_IMPRESORA ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getPRINT());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean LIMPIAR_FR_ANULADO(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_FARMACIA_LLIMPIAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_CAB());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  " + ex.getMessage());
        }
        return resp;
    }
    
    
    
    public boolean NUEVAGESTANTE(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_SIS_INSERTAR_GESTANTE ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getHC());
            cmd.setString(2, getTIPO());
            cmd.setString(3, getFECHA());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  " + ex.getMessage());
        }
        return resp;
    }


    public void LISTA(JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Código","Impresora"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC SISTEMA_LISTA_IMPRESORAS ";
            PreparedStatement cmd = getCn().prepareStatement(consulta);

            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); 
                fila[1]=r.getString(2);

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaMedico(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar PREVENTA CEX: " + e.getMessage());
        }
    }
    public void formatoTablaMedico(JTable tabla){
        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0); 
        tabla.getColumnModel().getColumn(1).setPreferredWidth(350);   
//        
  
        tabla.setRowHeight(40);
    }
    
    public void LISTA_FR_ANULADOS(JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Serie - Correlativo SISMED","Forma Pago","DNI","Paciente","Fecha","Total"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_FARMACIA_ANULADOS ";
            PreparedStatement cmd = getCn().prepareStatement(consulta);

            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3); 
                fila[3]=r.getString(4);
                fila[4]=r.getString(5); 
                fila[5]=r.getString(6); 

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaMedico_FR(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar PREVENTA CEX: " + e.getMessage());
        }
    }
    
    public void LISTA_FR(JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Serie - Correlativo SISMED","Forma Pago","DNI","Paciente","Fecha","Total"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_FARMACIA_CANCELADOS ";
            PreparedStatement cmd = getCn().prepareStatement(consulta);

            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3); 
                fila[3]=r.getString(4);
                fila[4]=r.getString(5); 
                fila[5]=r.getString(6); 

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaMedico_FR(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar PREVENTA CEX: " + e.getMessage());
        }
    }
    
        public void formatoTablaMedico_FR(JTable tabla){

        tabla.getColumnModel().getColumn(0).setPreferredWidth(200); 
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(350);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(200);
//        
  
        tabla.setRowHeight(40);
    }
        
        public void LISTA_FR_TODO(JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Serie - Correlativo SISMED","Forma Pago","DNI","Paciente","Fecha","Total"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_FARMACIA_CANCELADOS_LIMIPAR ";
            PreparedStatement cmd = getCn().prepareStatement(consulta);

            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3); 
                fila[3]=r.getString(4);
                fila[4]=r.getString(5); 
                fila[5]=r.getString(6); 

                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaMedico_FR(tabla);
        } catch (Exception e) {
            System.out.println("Error: listar PREVENTA CEX: " + e.getMessage());
        }
    }
    
    public void CajaPC_Listar(String usuario){
        String consulta="";
        try {
            consulta="EXEC SISTEMA_LISTA_IMPRESORAS ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
//            cmd.setString(1, cp_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Pagos.lblIMPRESORA.setText(r.getString(2)); 

                }
            //
        } catch (Exception e) {
            System.out.println("Error: PRINT " + e.getMessage());
        }
    }
    public Impresora(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getPRINT() {
        return PRINT;
    }

    public void setPRINT(String PRINT) {
        this.PRINT = PRINT;
    }

    public String getHC() {
        return HC;
    }

    public void setHC(String HC) {
        this.HC = HC;
    }

    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public String getID_CAB() {
        return ID_CAB;
    }

    public void setID_CAB(String ID_CAB) {
        this.ID_CAB = ID_CAB;
    }
    
    
    
    
}
