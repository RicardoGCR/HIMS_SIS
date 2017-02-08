/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Caja.Caja_cuentas;
import modelos.Caja.Caja_Cta2;
import modelos.Caja.Caja_Cta3;
import modelos.Caja.Caja_Cta4;
import modelos.Caja.Caja_Cta5;
import modelos.Caja.Caja_Cta6;
import servicios.Conexion;

/**
 *
 * @author Ricardo
 */
public class Caja_Transaccion extends javax.swing.JFrame {
DefaultTableModel m;
byte tg;

    /**
     * Creates new form Caja_Transaccion
     */
Caja_cuentas cnn = new Caja_cuentas();
Caja_Cta2 cn2 = new Caja_Cta2();
Caja_Cta3 cn3 = new Caja_Cta3();
Caja_Cta4 cn4 = new Caja_Cta4();
Caja_Cta5 cn5 = new Caja_Cta5();
Caja_Cta6 cn6 = new Caja_Cta6();

    public Caja_Transaccion() {
       initComponents();
        this.getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);//en el centro
        
        LlenarTransCT2.setLocationRelativeTo(null);//en el centro
        LlenarTransCT2.getContentPane().setBackground(Color.WHITE); 
        LlenarGenericaCT3.setLocationRelativeTo(null);//en el centro
        LlenarGenericaCT3.getContentPane().setBackground(Color.WHITE); 
        LlenarSubGenericaCT4.setLocationRelativeTo(null);//en el centro
        LlenarSubGenericaCT4.getContentPane().setBackground(Color.WHITE); 
        LlenarSubGenericaDetalleCT5.setLocationRelativeTo(null);//en el centro
        LlenarSubGenericaDetalleCT5.getContentPane().setBackground(Color.WHITE); 
        LlenarEspecifica.setLocationRelativeTo(null);//en el centro
        LlenarEspecifica.getContentPane().setBackground(Color.WHITE); 
        
        
        LlenarTransCT1EDITAR.setLocationRelativeTo(null);//en el centro
        LlenarTransCT1EDITAR.getContentPane().setBackground(Color.WHITE); 
        LlenarGenericaCT2EDITAR.setLocationRelativeTo(null);//en el centro
        LlenarGenericaCT2EDITAR.getContentPane().setBackground(Color.WHITE);
        LlenarSubGenericaEDITAR.setLocationRelativeTo(null);//en el centro
        LlenarSubGenericaEDITAR.getContentPane().setBackground(Color.WHITE);
        SubGenericaDetalleEDITAR.setLocationRelativeTo(null);//en el centro
        SubGenericaDetalleEDITAR.getContentPane().setBackground(Color.WHITE);
        EspecificaEditar.setLocationRelativeTo(null);//en el centro
        EspecificaEditar.getContentPane().setBackground(Color.WHITE);
        EspecificaDetalleEDITAR.setLocationRelativeTo(null);//en el centro
        EspecificaDetalleEDITAR.getContentPane().setBackground(Color.WHITE);
        
        
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);

        bc.setBackground(java.awt.Color.gray);
        LISTAR_cta1_CT2();
        formatoTipoCt1_CT2();
        LISTAR_cta2_CT3();
        formatoTipoCt2_CT3();
        LISTAR_cta3_CT4();
        formatoTipoCt3_CT4();
        LISTAR_cta4_CT5();
        formatoTipoCt4_CT5();
        LISTAR_cta5_CT6();
        formatoTipoCt5_CT6();
        LISTAR_CT6();
        formatoTipoCT6();
 
 
    }
    
    //BUSCAR CUENTA 1 EN CUENTA 2
    public void BuscarCta1_CTA2(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_cuentas obj=new Caja_cuentas();
                    consulta="exec Caja_Cta1_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);

                m.addRow(fila);
                c++;
            }
            tb_Grupo.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo.setRowSorter(elQueOrdena);
            this.tb_Grupo.setModel(m);

            formatoTipoCt1_CT2();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
     //BUSCAR CUENTA 1 EN CUENTA 2
    public void BuscarCta1_CTA2EDITAR(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo5.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_cuentas obj=new Caja_cuentas();
                    consulta="exec Caja_Cta1_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar5.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);

                m.addRow(fila);
                c++;
            }
            tb_Grupo5.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo5.setRowSorter(elQueOrdena);
            this.tb_Grupo5.setModel(m);

            formatoTipoCt1_CT2();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    //BUSCAR CUENTA 2 EN CUENTA 3
    public void BuscarCta2_CTA3(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo1.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta2 obj=new Caja_Cta2();
                    consulta="exec Caja_Cta2_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar1.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
            tb_Grupo1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo1.setRowSorter(elQueOrdena);
            this.tb_Grupo1.setModel(m);
            formatoTipoCt2_CT3();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
  
    public void BuscarCta2EDITAR(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo6.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta2 obj=new Caja_Cta2();
                    consulta="exec Caja_Cta2_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar6.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
            tb_Grupo6.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo6.setRowSorter(elQueOrdena);
            this.tb_Grupo6.setModel(m);
            formatoTipoCt2_CT3();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    //BUSCAR CUENTA 3 EN CUENTA 4
    public void BuscarCta3_CTA4(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo2.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta3 obj=new Caja_Cta3();
                    consulta="exec Caja_Cta3_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar2.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
               formatoTipoCt3_CT4();
            tb_Grupo2.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo2.setRowSorter(elQueOrdena);
            this.tb_Grupo2.setModel(m);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    //BUSCAR CUENTA 3 EN CUENTA 4
    public void BuscarCta3EDITAR(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo7.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta3 obj=new Caja_Cta3();
                    consulta="exec Caja_Cta3_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar7.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
           
            tb_Grupo7.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo7.setRowSorter(elQueOrdena);
            this.tb_Grupo7.setModel(m);
          formatoTipoCt3_CT4();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
  
    //BUSCAR CUENTA 4 EN CUENTA 5
    public void BuscarCta4_CTA5(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo3.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta4 obj=new Caja_Cta4();
                    consulta="exec Caja_Cta4_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar3.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
            tb_Grupo3.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo3.setRowSorter(elQueOrdena);
            this.tb_Grupo3.setModel(m);
            formatoTipoCt4_CT5();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    //BUSCAR CUENTA 4 EN CUENTA 5
    public void BuscarCta4EDITAR(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo8.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta4 obj=new Caja_Cta4();
                    consulta="exec Caja_Cta4_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar8.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
            tb_Grupo8.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo8.setRowSorter(elQueOrdena);
            this.tb_Grupo8.setModel(m);
            formatoTipoCt4_CT5();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    //BUSCAR CUENTA 5 EN CUENTA 6
    public void BuscarCta5_CTA6(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo4.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta5 obj=new Caja_Cta5();
                    consulta="exec Caja_Cta5_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar4.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
            tb_Grupo4.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo4.setRowSorter(elQueOrdena);
            this.tb_Grupo4.setModel(m);
            formatoTipoCt5_CT6();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    //BUSCAR CUENTA 5 EN CUENTA 6
    public void BuscarCta5EDITAR(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo9.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta5 obj=new Caja_Cta5();
                    consulta="exec Caja_Cta5_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar9.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
            tb_Grupo9.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo9.setRowSorter(elQueOrdena);
            this.tb_Grupo9.setModel(m);
            formatoTipoCt5_CT6();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    //BUSCAR CUENTA 4 EN CUENTA 5
    public void BuscarCta6EDITAR(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo10.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Cta6 obj=new Caja_Cta6();
                    consulta="exec Caja_Cta6_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar10.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
            tb_Grupo10.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo10.setRowSorter(elQueOrdena);
            this.tb_Grupo10.setModel(m);
            formatoTipoCT6();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    public void LISTAR_cta1_CT2(){
    try {
             String titulos[]={"Cuenta","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Cta1_LISTAR";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3); // dni
  
                    m.addRow(fila);
                    c++;
            }
            tb_Grupo.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo.setRowSorter(elQueOrdena);
            this.tb_Grupo.setModel(m);
            
            tb_Grupo5.setModel(m);
            tb_Grupo5.setRowSorter(elQueOrdena);
            this.tb_Grupo5.setModel(m);
    } catch (Exception e) {
    }
}
    
    public void LISTAR_cta2_CT3(){
    try {
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Cta2_LISTAR";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3); // dni
                fila[3]=r.getString(4);
  
                    m.addRow(fila);
                    c++;
            }
          formatoTipoCt2_CT3();
            tb_Grupo1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo1.setRowSorter(elQueOrdena);
            this.tb_Grupo1.setModel(m);
            
            tb_Grupo6.setModel(m);
            tb_Grupo6.setRowSorter(elQueOrdena);
            this.tb_Grupo6.setModel(m);
    } catch (Exception e) {
    }
}
    
    public void LISTAR_cta3_CT4(){
    try {
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Cta3_LISTAR";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3); // dni
                fila[3]=r.getString(4); // dni
  
                    m.addRow(fila);
                    c++;
            }
            formatoTipoCt3_CT4();
            tb_Grupo2.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo2.setRowSorter(elQueOrdena);
            this.tb_Grupo2.setModel(m);
         
            tb_Grupo7.setModel(m);
            tb_Grupo7.setRowSorter(elQueOrdena);
            this.tb_Grupo7.setModel(m);
    } catch (Exception e) {
    }
}
    
    public void LISTAR_cta4_CT5(){
    try {
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Cta4_LISTAR";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3); // dni
                fila[3]=r.getString(4); // dni
  
                    m.addRow(fila);
                    c++;
            }
            tb_Grupo3.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo3.setRowSorter(elQueOrdena);
            this.tb_Grupo3.setModel(m);
            
            tb_Grupo8.setModel(m);

            tb_Grupo8.setRowSorter(elQueOrdena);
            this.tb_Grupo8.setModel(m);
            formatoTipoCt4_CT5();
    } catch (Exception e) {
    }
}
    
    public void LISTAR_cta5_CT6(){
    try {
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Cta5_LISTAR";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3); // dni
                fila[3]=r.getString(4); // dni
  
                    m.addRow(fila);
                    c++;
            }
            tb_Grupo4.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo4.setRowSorter(elQueOrdena);
            this.tb_Grupo4.setModel(m);
            
            tb_Grupo9.setModel(m);
            
            tb_Grupo9.setRowSorter(elQueOrdena);
            this.tb_Grupo9.setModel(m);
            
            formatoTipoCt5_CT6();
    } catch (Exception e) {
    }
}
    
    public void LISTAR_CT6(){
    try {
             String titulos[]={"Cuenta","Descripcion","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Cta6_LISTAR";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3); // dni
                fila[3]=r.getString(4); // dni
  
                    m.addRow(fila);
                    c++;
            }
            tb_Grupo10.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo10.setRowSorter(elQueOrdena);
            this.tb_Grupo10.setModel(m);

            formatoTipoCT6();
    } catch (Exception e) {
    }
}
  
    public void formatoTipoCt1_CT2(){
    tb_Grupo.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Grupo.getColumnModel().getColumn(1).setPreferredWidth(400);
    tb_Grupo.getColumnModel().getColumn(2).setPreferredWidth(1);
    
    tb_Grupo5.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Grupo5.getColumnModel().getColumn(1).setPreferredWidth(400);
    tb_Grupo5.getColumnModel().getColumn(2).setPreferredWidth(1);
}
    
    public void formatoTipoCt2_CT3(){
    tb_Grupo1.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo1.getColumnModel().getColumn(2).setPreferredWidth(1);
    tb_Grupo1.getColumnModel().getColumn(3).setPreferredWidth(1);
    
    tb_Grupo6.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Grupo6.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo6.getColumnModel().getColumn(2).setPreferredWidth(1);
    tb_Grupo6.getColumnModel().getColumn(3).setPreferredWidth(1);
}
    
    public void formatoTipoCt3_CT4(){
    tb_Grupo2.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Grupo2.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo2.getColumnModel().getColumn(2).setPreferredWidth(1);
    tb_Grupo2.getColumnModel().getColumn(3).setPreferredWidth(1);
    
    tb_Grupo7.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Grupo7.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo7.getColumnModel().getColumn(2).setPreferredWidth(1);
    tb_Grupo7.getColumnModel().getColumn(3).setPreferredWidth(1);
}
    
    public void formatoTipoCt4_CT5(){
    tb_Grupo3.getColumnModel().getColumn(0).setPreferredWidth(300);
    tb_Grupo3.getColumnModel().getColumn(1).setPreferredWidth(2000);
    tb_Grupo3.getColumnModel().getColumn(2).setPreferredWidth(200);
    
    tb_Grupo8.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Grupo8.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo8.getColumnModel().getColumn(2).setPreferredWidth(1);
    tb_Grupo8.getColumnModel().getColumn(3).setPreferredWidth(1);
}
    
    public void formatoTipoCt5_CT6(){
    tb_Grupo4.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Grupo4.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo4.getColumnModel().getColumn(2).setPreferredWidth(1);
    tb_Grupo4.getColumnModel().getColumn(3).setPreferredWidth(1);
    
    tb_Grupo9.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Grupo9.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo9.getColumnModel().getColumn(2).setPreferredWidth(1);
    tb_Grupo9.getColumnModel().getColumn(3).setPreferredWidth(1);
}
    
    public void formatoTipoCT6(){
    tb_Grupo10.getColumnModel().getColumn(0).setPreferredWidth(150);
    tb_Grupo10.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo10.getColumnModel().getColumn(2).setPreferredWidth(1);
    tb_Grupo10.getColumnModel().getColumn(3).setPreferredWidth(1);

}
    
    public void GuardarCta1(){

        if((txttipoT.getText().equals("")) ||  txtdesT.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos");
        } else {
        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(guardar == 0 ){
                
                Caja_cuentas cno1 = new Caja_cuentas();
                cno1.setId_cuenta1(txtcodT.getText());//
                cno1.setCuenta_1(txttipoT.getText());//
                cno1.setDescripcion_1(txtdesT.getText());//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevaCTA1()==true){
                           JOptionPane.showMessageDialog(this, "Datos Guardados");
                           tg=11;
                           btnguardar.setEnabled(false);
                            btneditar.setEnabled(true);
                           txttipoT.setEnabled(false);
                           txtdesT.setEnabled(false);
                           
                       } else {
                           JOptionPane.showMessageDialog(this, "Error al guardar");
                       }}}}
    
    public void GuardarCta2(){

        if((txtgen.getText().equals("")) ||  txtdesG.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos");
        } else {
        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(guardar == 0 ){
                
                Caja_Cta2 cno1 = new Caja_Cta2();
                cno1.setId_cuenta1(lblCT1.getText());//
                cno1.setId_cuenta2(txtcodG.getText());//
                cno1.setCuenta_2(txttrans1.getText()+"."+ txtgen.getText());//
                cno1.setDescripcion(txtdesG.getText());//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevaCTA2()==true){
                           JOptionPane.showMessageDialog(this, "Datos Guardados");
                           tg=22;
                           btnguardar.setEnabled(false);
                             btneditar.setEnabled(true);
                             txtgen.setEnabled(false);
                             txtdesG.setEnabled(false);
                       } else {
                           JOptionPane.showMessageDialog(this, "Error al guardar");
                       }}}}
    
    public void GuardarCta3(){

        if((txtsub.getText().equals("")) ||  txtdesSubG.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos");
        } else {
        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(guardar == 0 ){
                
                Caja_Cta3 cno1 = new Caja_Cta3();
                cno1.setId_cuenta3(txtcodsubgen.getText());//
                cno1.setId_cuenta2(lblCT2.getText());//CODIGO NO LO MUESTRA EL FORMULARIO
                cno1.setCuenta_3(txtgen3.getText()+"."+ txtsub.getText());//
                cno1.setDescripcion(txtdesSubG.getText());//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevaCTA3()==true){
                           JOptionPane.showMessageDialog(this, "Datos Guardados");
                           tg=33;
                              btnguardar.setEnabled(false);
                             btneditar.setEnabled(true);
                             txtsub.setEnabled(false);
                             txtdesSubG.setEnabled(false);
                           
                       } else {
                           JOptionPane.showMessageDialog(this, "Error al guardar");
                       }}}}
    
    public void GuardarCta4(){

        if((txtgend.getText().equals("")) ||  txtdesGd.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos");
        } else {
        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(guardar == 0 ){
                
                Caja_Cta4 cno1 = new Caja_Cta4();
                cno1.setId_cuenta4(txtcodGD.getText());//
                cno1.setId_cuenta3(lblCT3.getText());//CODIGO NO LO MUESTRA EL FORMULARIO
                cno1.setCuenta_4(txtsuggend.getText()+"."+ txtgend.getText());//
                cno1.setDescripcion(txtdesGd.getText());//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevaCTA4()==true){
                           JOptionPane.showMessageDialog(this, "Datos Guardados");
                           tg=44;
                            btnguardar.setEnabled(false);
                             btneditar.setEnabled(true);
                             txtgend.setEnabled(false);
                             txtdesGd.setEnabled(false);
                       } else {
                           JOptionPane.showMessageDialog(this, "Error al guardar");
                       }}}}
    
    public void GuardarCta5(){

        if((txtespe.getText().equals("")) ||  txtdesE.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos");
        } else {
        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(guardar == 0 ){
                
                Caja_Cta5 cno1 = new Caja_Cta5();
                cno1.setId_cuenta5(txtcodE.getText());//
                cno1.setId_cuenta4(lblCT4.getText());//CODIGO NO LO MUESTRA EL FORMULARIO
                cno1.setCuenta_5(txtsgd.getText()+"."+ txtespe.getText());//
                cno1.setDescripcion(txtdesE.getText());//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevaCTA5()==true){
                           JOptionPane.showMessageDialog(this, "Datos Guardados");
                           tg=55;
                           btnguardar.setEnabled(false);
                             btneditar.setEnabled(true);
                             txtespe.setEnabled(false);
                             txtdesE.setEnabled(false);
                       } else {
                           JOptionPane.showMessageDialog(this, "Error al guardar");
                       }}}}
        
    public void GuardarCta6(){

        if((txtespedet.getText().equals("")) ||  txtdesEd.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos");
        } else {
        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(guardar == 0 ){
                
                Caja_Cta6 cno1 = new Caja_Cta6();
                cno1.setId_cuenta6(txtcodEd.getText());//
                cno1.setId_cuenta5(lblCT5.getText());//CODIGO NO LO MUESTRA EL FORMULARIO
                cno1.setCuenta_6(txted1.getText()+"."+ txtespedet.getText());//
                cno1.setDescripcion(txtdesEd.getText());//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevaCTA6()==true){
                           JOptionPane.showMessageDialog(this, "Datos Guardados");
                           tg=66;
                             btnguardar.setEnabled(false);
                             btneditar.setEnabled(true);
                             txtespedet.setEnabled(false);
                             txtdesEd.setEnabled(false);
                       } else {
                           JOptionPane.showMessageDialog(this, "Error al guardar");
                       }}}}
        
    public void ModificarCta1(){
            int modificar = JOptionPane.showConfirmDialog(this, "¿Desea ACTUALIZAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(modificar == 0 ){
                        Caja_cuentas cno = new Caja_cuentas();
                        cno.setId_cuenta1(txtcodT.getText());//
                        cno.setCuenta_1(txttipoT.getText());//
                        cno.setDescripcion_1(txtdesT.getText());//
                        cno.setNom_usu(lblusu.getText());
                        if(cno.modificarCta1()==true){
                             JOptionPane.showMessageDialog(this, "Ejecucion Correcta");
                             btnguardar.setEnabled(false);
                              btneditar.setEnabled(true);
                             txttipoT.setEnabled(false);
                             txtdesT.setEnabled(false);
                        } else {
                           
                             JOptionPane.showMessageDialog(this, "Error al guardar");
                        }
                    } else {
                    JOptionPane.showMessageDialog(this, "No ha hecho ninguna modificación");
                    }       
    }
        
    public void ModificarCta2(){
       
            int modificar = JOptionPane.showConfirmDialog(this, "¿Desea ACTUALIZAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(modificar == 0 ){
                        Caja_Cta2 cno = new Caja_Cta2();
                        cno.setId_cuenta2(txtcodG.getText());//
                        cno.setId_cuenta1(lblCT1.getText());//
                        cno.setCuenta_2(txttrans1.getText()+"."+ txtgen.getText());//
                        cno.setDescripcion(txtdesG.getText());//
                        cno.setNom_usu(lblusu.getText());
                        if(cno.modificarCta2()==true){
                             JOptionPane.showMessageDialog(this, "Ejecucion Correcta");
                             btnguardar.setEnabled(false);
                             btneditar.setEnabled(true);
                             txtgen.setEnabled(false);
                             txtdesG.setEnabled(false);
                        } else {
                           
                             JOptionPane.showMessageDialog(this, "Error al guardar");
                        }
                    } else {
                    JOptionPane.showMessageDialog(this, "No ha hecho ninguna modificación");
                    }       
    }    
    
    public void ModificarCta3(){
       
            int modificar = JOptionPane.showConfirmDialog(this, "¿Desea ACTUALIZAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(modificar == 0 ){
                        Caja_Cta3 cno = new Caja_Cta3();
                        cno.setId_cuenta3(txtcodsubgen.getText());//
                        cno.setId_cuenta2(lblCT2.getText());//
                        cno.setCuenta_3(txtgen3.getText()+"."+ txtsub.getText());//
                        cno.setDescripcion(txtdesSubG.getText());//
                        cno.setNom_usu(lblusu.getText());
                        if(cno.modificarCta3()==true){
                             JOptionPane.showMessageDialog(this, "Ejecucion Correcta");
                             btnguardar.setEnabled(false);
                             btneditar.setEnabled(true);
                             txtsub.setEnabled(false);
                             txtdesSubG.setEnabled(false);
                        } else {
                           
                             JOptionPane.showMessageDialog(this, "Error al guardar");
                        }
                    } else {
                    JOptionPane.showMessageDialog(this, "No ha hecho ninguna modificación");
                    }       
    }    
    
    public void ModificarCta4(){
       
            int modificar = JOptionPane.showConfirmDialog(this, "¿Desea ACTUALIZAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(modificar == 0 ){
                        Caja_Cta4 cno = new Caja_Cta4();
                        cno.setId_cuenta4(txtcodGD.getText());//
                        cno.setId_cuenta3(lblCT3.getText());//
                        cno.setCuenta_4(txtsuggend.getText()+"."+ txtgend.getText());//
                        cno.setDescripcion(txtdesGd.getText());//
                        cno.setNom_usu(lblusu.getText());
                        if(cno.modificarCta4()==true){
                             JOptionPane.showMessageDialog(this, "Ejecucion Correcta");
                             btnguardar.setEnabled(false);
                             btneditar.setEnabled(true);
                             txtgend.setEnabled(false);
                             txtdesGd.setEnabled(false);
                        } else {
                           
                             JOptionPane.showMessageDialog(this, "Error al guardar");
                        }
                    } else {
                    JOptionPane.showMessageDialog(this, "No ha hecho ninguna modificación");
                    }       
    }    
    
    public void ModificarCta5(){
       
            int modificar = JOptionPane.showConfirmDialog(this, "¿Desea ACTUALIZAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(modificar == 0 ){
                        Caja_Cta5 cno = new Caja_Cta5();
                        cno.setId_cuenta5(txtcodE.getText());//
                        cno.setId_cuenta4(lblCT4.getText());//
                        cno.setCuenta_5(txtsgd.getText()+"."+ txtespe.getText());//
                        cno.setDescripcion(txtdesE.getText());//
                        cno.setNom_usu(lblusu.getText());
                        if(cno.modificarCta5()==true){
                             JOptionPane.showMessageDialog(this, "Ejecucion Correcta");
                             tg=55;
                             btnguardar.setEnabled(false);
                             btneditar.setEnabled(true);
                             txtespe.setEnabled(false);
                             txtdesE.setEnabled(false);
                        } else {
                           
                             JOptionPane.showMessageDialog(this, "Error al guardar");
                        }
                    } else {
                    JOptionPane.showMessageDialog(this, "No ha hecho ninguna modificación");
                    }       
    }
    
    public void ModificarCta6(){
       
            int modificar = JOptionPane.showConfirmDialog(this, "¿Desea ACTUALIZAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(modificar == 0 ){
                        Caja_Cta6 cno = new Caja_Cta6();
                        cno.setId_cuenta6(txtcodEd.getText());//
                        cno.setId_cuenta5(lblCT5.getText());//
                        cno.setCuenta_6(txted1.getText()+"."+ txtespedet.getText());//
                        cno.setDescripcion(txtdesEd.getText());//
                        cno.setNom_usu(lblusu.getText());
                        if(cno.modificarCta6()==true){
                             JOptionPane.showMessageDialog(this, "Ejecucion Correcta");
                             tg=66;
                             btnguardar.setEnabled(false);
                             btneditar.setEnabled(true);
                             txtespedet.setEnabled(false);
                             txtdesEd.setEnabled(false);
                        } else {
                           
                             JOptionPane.showMessageDialog(this, "Error al guardar");
                        }
                    } else {
                    JOptionPane.showMessageDialog(this, "No ha hecho ninguna modificación");
                    }       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LlenarTransCT2 = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        btnbuscar1 = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Grupo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            LlenarGenericaCT3 = new javax.swing.JDialog();
            jPanel8 = new javax.swing.JPanel();
            jLabel21 = new javax.swing.JLabel();
            btnbuscar2 = new javax.swing.JButton();
            txtBuscar1 = new javax.swing.JTextField();
            jScrollPane3 = new javax.swing.JScrollPane();
            tb_Grupo1 = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                LlenarSubGenericaCT4 = new javax.swing.JDialog();
                jPanel9 = new javax.swing.JPanel();
                jLabel29 = new javax.swing.JLabel();
                btnbuscar3 = new javax.swing.JButton();
                txtBuscar2 = new javax.swing.JTextField();
                jScrollPane4 = new javax.swing.JScrollPane();
                tb_Grupo2 = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    LlenarSubGenericaDetalleCT5 = new javax.swing.JDialog();
                    jPanel15 = new javax.swing.JPanel();
                    jLabel39 = new javax.swing.JLabel();
                    btnbuscar4 = new javax.swing.JButton();
                    txtBuscar3 = new javax.swing.JTextField();
                    jScrollPane5 = new javax.swing.JScrollPane();
                    tb_Grupo3 = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        LlenarEspecifica = new javax.swing.JDialog();
                        jPanel17 = new javax.swing.JPanel();
                        jLabel53 = new javax.swing.JLabel();
                        btnbuscar5 = new javax.swing.JButton();
                        txtBuscar4 = new javax.swing.JTextField();
                        jScrollPane6 = new javax.swing.JScrollPane();
                        tb_Grupo4 = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            LlenarTransCT1EDITAR = new javax.swing.JDialog();
                            jPanel18 = new javax.swing.JPanel();
                            jLabel54 = new javax.swing.JLabel();
                            btnbuscar6 = new javax.swing.JButton();
                            txtBuscar5 = new javax.swing.JTextField();
                            jScrollPane7 = new javax.swing.JScrollPane();
                            tb_Grupo5 = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false; //Disallow the editing of any cell
                                }};
                                LlenarGenericaCT2EDITAR = new javax.swing.JDialog();
                                jPanel19 = new javax.swing.JPanel();
                                jLabel55 = new javax.swing.JLabel();
                                btnbuscar7 = new javax.swing.JButton();
                                txtBuscar6 = new javax.swing.JTextField();
                                jScrollPane8 = new javax.swing.JScrollPane();
                                tb_Grupo6 = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false; //Disallow the editing of any cell
                                    }};
                                    LlenarSubGenericaEDITAR = new javax.swing.JDialog();
                                    jPanel20 = new javax.swing.JPanel();
                                    jLabel56 = new javax.swing.JLabel();
                                    btnbuscar8 = new javax.swing.JButton();
                                    txtBuscar7 = new javax.swing.JTextField();
                                    jScrollPane9 = new javax.swing.JScrollPane();
                                    tb_Grupo7 = new javax.swing.JTable(){
                                        public boolean isCellEditable(int rowIndex, int colIndex){
                                            return false; //Disallow the editing of any cell
                                        }};
                                        SubGenericaDetalleEDITAR = new javax.swing.JDialog();
                                        jPanel21 = new javax.swing.JPanel();
                                        jLabel57 = new javax.swing.JLabel();
                                        btnbuscar9 = new javax.swing.JButton();
                                        txtBuscar8 = new javax.swing.JTextField();
                                        jScrollPane10 = new javax.swing.JScrollPane();
                                        tb_Grupo8 = new javax.swing.JTable(){
                                            public boolean isCellEditable(int rowIndex, int colIndex){
                                                return false; //Disallow the editing of any cell
                                            }};
                                            EspecificaEditar = new javax.swing.JDialog();
                                            jPanel22 = new javax.swing.JPanel();
                                            jLabel58 = new javax.swing.JLabel();
                                            btnbuscar10 = new javax.swing.JButton();
                                            txtBuscar9 = new javax.swing.JTextField();
                                            jScrollPane11 = new javax.swing.JScrollPane();
                                            tb_Grupo9 = new javax.swing.JTable(){
                                                public boolean isCellEditable(int rowIndex, int colIndex){
                                                    return false; //Disallow the editing of any cell
                                                }};
                                                EspecificaDetalleEDITAR = new javax.swing.JDialog();
                                                jPanel23 = new javax.swing.JPanel();
                                                jLabel59 = new javax.swing.JLabel();
                                                btnbuscar11 = new javax.swing.JButton();
                                                txtBuscar10 = new javax.swing.JTextField();
                                                jScrollPane12 = new javax.swing.JScrollPane();
                                                tb_Grupo10 = new javax.swing.JTable(){
                                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                                        return false; //Disallow the editing of any cell
                                                    }};
                                                    jPanel1 = new javax.swing.JPanel();
                                                    jLabel1 = new javax.swing.JLabel();
                                                    btnNuevo = new javax.swing.JButton();
                                                    btneditar = new javax.swing.JButton();
                                                    btnguardar = new javax.swing.JButton();
                                                    btneliminar = new javax.swing.JButton();
                                                    btnbuscar = new javax.swing.JButton();
                                                    lblusu = new javax.swing.JLabel();
                                                    jLabel6 = new javax.swing.JLabel();
                                                    jLabel7 = new javax.swing.JLabel();
                                                    btneliminar1 = new javax.swing.JButton();
                                                    jPanel5 = new javax.swing.JPanel();
                                                    jLabel13 = new javax.swing.JLabel();
                                                    jLabel23 = new javax.swing.JLabel();
                                                    jLabel33 = new javax.swing.JLabel();
                                                    jLabel34 = new javax.swing.JLabel();
                                                    jLabel35 = new javax.swing.JLabel();
                                                    jPanel10 = new javax.swing.JPanel();
                                                    jTabbedPane1 = new javax.swing.JTabbedPane();
                                                    jPanel3 = new javax.swing.JPanel();
                                                    bc = new javax.swing.JButton();
                                                    bc1 = new javax.swing.JButton();
                                                    bc2 = new javax.swing.JButton();
                                                    bc3 = new javax.swing.JButton();
                                                    bc4 = new javax.swing.JButton();
                                                    bc5 = new javax.swing.JButton();
                                                    jLabel51 = new javax.swing.JLabel();
                                                    jPanel4 = new javax.swing.JPanel();
                                                    jLabel8 = new javax.swing.JLabel();
                                                    jLabel9 = new javax.swing.JLabel();
                                                    jLabel10 = new javax.swing.JLabel();
                                                    jLabel11 = new javax.swing.JLabel();
                                                    txtcodT = new javax.swing.JTextField();
                                                    txttipoT = new javax.swing.JTextField();
                                                    txtdesT = new javax.swing.JTextField();
                                                    jPanel11 = new javax.swing.JPanel();
                                                    jLabel12 = new javax.swing.JLabel();
                                                    jLabel14 = new javax.swing.JLabel();
                                                    jLabel15 = new javax.swing.JLabel();
                                                    jLabel16 = new javax.swing.JLabel();
                                                    txtcodG = new javax.swing.JTextField();
                                                    txtdesG = new javax.swing.JTextField();
                                                    txttrans = new javax.swing.JTextField();
                                                    jLabel17 = new javax.swing.JLabel();
                                                    txttrans1 = new javax.swing.JTextField();
                                                    jLabel18 = new javax.swing.JLabel();
                                                    txtgen = new javax.swing.JTextField();
                                                    B1 = new javax.swing.JButton();
                                                    lblCT1 = new javax.swing.JLabel();
                                                    cuentagenerica = new javax.swing.JLabel();
                                                    jPanel12 = new javax.swing.JPanel();
                                                    jLabel20 = new javax.swing.JLabel();
                                                    jLabel41 = new javax.swing.JLabel();
                                                    jLabel42 = new javax.swing.JLabel();
                                                    jLabel43 = new javax.swing.JLabel();
                                                    txtcodsubgen = new javax.swing.JTextField();
                                                    txtdesSubG = new javax.swing.JTextField();
                                                    txtgen2 = new javax.swing.JTextField();
                                                    jLabel44 = new javax.swing.JLabel();
                                                    txtgen3 = new javax.swing.JTextField();
                                                    jLabel45 = new javax.swing.JLabel();
                                                    txtsub = new javax.swing.JTextField();
                                                    b2 = new javax.swing.JButton();
                                                    lblCT2 = new javax.swing.JLabel();
                                                    sugenericacadena = new javax.swing.JLabel();
                                                    jPanel13 = new javax.swing.JPanel();
                                                    jLabel22 = new javax.swing.JLabel();
                                                    jLabel24 = new javax.swing.JLabel();
                                                    jLabel25 = new javax.swing.JLabel();
                                                    jLabel26 = new javax.swing.JLabel();
                                                    txtcodGD = new javax.swing.JTextField();
                                                    txtdesGd = new javax.swing.JTextField();
                                                    txtsubgen = new javax.swing.JTextField();
                                                    jLabel27 = new javax.swing.JLabel();
                                                    txtsuggend = new javax.swing.JTextField();
                                                    jLabel28 = new javax.swing.JLabel();
                                                    txtgend = new javax.swing.JTextField();
                                                    B3 = new javax.swing.JButton();
                                                    lblCT3 = new javax.swing.JLabel();
                                                    Subgenericadetalle = new javax.swing.JLabel();
                                                    jPanel14 = new javax.swing.JPanel();
                                                    jLabel30 = new javax.swing.JLabel();
                                                    jLabel31 = new javax.swing.JLabel();
                                                    jLabel32 = new javax.swing.JLabel();
                                                    jLabel36 = new javax.swing.JLabel();
                                                    txtcodE = new javax.swing.JTextField();
                                                    txtdesE = new javax.swing.JTextField();
                                                    txtsubgd = new javax.swing.JTextField();
                                                    jLabel37 = new javax.swing.JLabel();
                                                    txtsgd = new javax.swing.JTextField();
                                                    jLabel38 = new javax.swing.JLabel();
                                                    txtespe = new javax.swing.JTextField();
                                                    b4 = new javax.swing.JButton();
                                                    lblCT4 = new javax.swing.JLabel();
                                                    Especifica = new javax.swing.JLabel();
                                                    jPanel16 = new javax.swing.JPanel();
                                                    jLabel40 = new javax.swing.JLabel();
                                                    jLabel46 = new javax.swing.JLabel();
                                                    jLabel47 = new javax.swing.JLabel();
                                                    jLabel48 = new javax.swing.JLabel();
                                                    txtcodEd = new javax.swing.JTextField();
                                                    txtdesEd = new javax.swing.JTextField();
                                                    txtespe2 = new javax.swing.JTextField();
                                                    jLabel49 = new javax.swing.JLabel();
                                                    txted1 = new javax.swing.JTextField();
                                                    jLabel50 = new javax.swing.JLabel();
                                                    txtespedet = new javax.swing.JTextField();
                                                    b6 = new javax.swing.JButton();
                                                    lblCT5 = new javax.swing.JLabel();
                                                    especificadetalle = new javax.swing.JLabel();
                                                    jPanel6 = new javax.swing.JPanel();
                                                    bc6 = new javax.swing.JButton();
                                                    bc7 = new javax.swing.JButton();
                                                    bc8 = new javax.swing.JButton();
                                                    bc9 = new javax.swing.JButton();
                                                    bc10 = new javax.swing.JButton();
                                                    bc11 = new javax.swing.JButton();
                                                    jLabel52 = new javax.swing.JLabel();

                                                    LlenarTransCT2.setAlwaysOnTop(true);
                                                    LlenarTransCT2.setMinimumSize(new java.awt.Dimension(310, 441));

                                                    jPanel7.setBackground(new java.awt.Color(0, 153, 153));

                                                    jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                    jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                                                    jLabel19.setText("Transacciones");

                                                    btnbuscar1.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                                                    btnbuscar1.setMnemonic('N');
                                                    btnbuscar1.setToolTipText("");
                                                    btnbuscar1.setContentAreaFilled(false);
                                                    btnbuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar1.setIconTextGap(30);
                                                    btnbuscar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar1.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar1ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscarCaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscarActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                                                    jPanel7.setLayout(jPanel7Layout);
                                                    jPanel7Layout.setHorizontalGroup(
                                                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel19)
                                                                .addGroup(jPanel7Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(30, Short.MAX_VALUE))
                                                    );
                                                    jPanel7Layout.setVerticalGroup(
                                                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel19)
                                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel7Layout.createSequentialGroup()
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel7Layout.createSequentialGroup()
                                                                    .addGap(3, 3, 3)
                                                                    .addComponent(btnbuscar1)))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    tb_Grupo.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo.setRowHeight(25);
                                                    tb_Grupo.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                    tb_Grupo.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_GrupoMouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Grupo.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_GrupoKeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane2.setViewportView(tb_Grupo);

                                                    javax.swing.GroupLayout LlenarTransCT2Layout = new javax.swing.GroupLayout(LlenarTransCT2.getContentPane());
                                                    LlenarTransCT2.getContentPane().setLayout(LlenarTransCT2Layout);
                                                    LlenarTransCT2Layout.setHorizontalGroup(
                                                        LlenarTransCT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(LlenarTransCT2Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );
                                                    LlenarTransCT2Layout.setVerticalGroup(
                                                        LlenarTransCT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(LlenarTransCT2Layout.createSequentialGroup()
                                                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );

                                                    LlenarGenericaCT3.setAlwaysOnTop(true);
                                                    LlenarGenericaCT3.setMinimumSize(new java.awt.Dimension(310, 441));

                                                    jPanel8.setBackground(new java.awt.Color(0, 153, 153));

                                                    jLabel21.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                    jLabel21.setForeground(new java.awt.Color(255, 255, 255));
                                                    jLabel21.setText("Generica");

                                                    btnbuscar2.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                                                    btnbuscar2.setMnemonic('N');
                                                    btnbuscar2.setToolTipText("");
                                                    btnbuscar2.setContentAreaFilled(false);
                                                    btnbuscar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar2.setIconTextGap(30);
                                                    btnbuscar2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar2.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar2ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar1.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscar1CaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar1.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscar1ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                                                    jPanel8.setLayout(jPanel8Layout);
                                                    jPanel8Layout.setHorizontalGroup(
                                                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel21)
                                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );
                                                    jPanel8Layout.setVerticalGroup(
                                                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel21)
                                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                                    .addGap(3, 3, 3)
                                                                    .addComponent(btnbuscar2)))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    tb_Grupo1.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo1.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo1.setRowHeight(25);
                                                    tb_Grupo1.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                    tb_Grupo1.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Grupo1MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Grupo1.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Grupo1KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane3.setViewportView(tb_Grupo1);

                                                    javax.swing.GroupLayout LlenarGenericaCT3Layout = new javax.swing.GroupLayout(LlenarGenericaCT3.getContentPane());
                                                    LlenarGenericaCT3.getContentPane().setLayout(LlenarGenericaCT3Layout);
                                                    LlenarGenericaCT3Layout.setHorizontalGroup(
                                                        LlenarGenericaCT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(LlenarGenericaCT3Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );
                                                    LlenarGenericaCT3Layout.setVerticalGroup(
                                                        LlenarGenericaCT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(LlenarGenericaCT3Layout.createSequentialGroup()
                                                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );

                                                    LlenarSubGenericaCT4.setAlwaysOnTop(true);
                                                    LlenarSubGenericaCT4.setMinimumSize(new java.awt.Dimension(310, 441));

                                                    jPanel9.setBackground(new java.awt.Color(0, 153, 153));

                                                    jLabel29.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                    jLabel29.setForeground(new java.awt.Color(255, 255, 255));
                                                    jLabel29.setText("Sub Generica");

                                                    btnbuscar3.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                                                    btnbuscar3.setMnemonic('N');
                                                    btnbuscar3.setToolTipText("");
                                                    btnbuscar3.setContentAreaFilled(false);
                                                    btnbuscar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar3.setIconTextGap(30);
                                                    btnbuscar3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar3.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar3ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar2.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscar2CaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar2.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscar2ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                                                    jPanel9.setLayout(jPanel9Layout);
                                                    jPanel9Layout.setHorizontalGroup(
                                                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel29)
                                                                .addGroup(jPanel9Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(30, Short.MAX_VALUE))
                                                    );
                                                    jPanel9Layout.setVerticalGroup(
                                                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel29)
                                                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel9Layout.createSequentialGroup()
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel9Layout.createSequentialGroup()
                                                                    .addGap(3, 3, 3)
                                                                    .addComponent(btnbuscar3)))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    tb_Grupo2.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo2.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo2.setRowHeight(25);
                                                    tb_Grupo2.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                    tb_Grupo2.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Grupo2MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Grupo2.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Grupo2KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane4.setViewportView(tb_Grupo2);

                                                    javax.swing.GroupLayout LlenarSubGenericaCT4Layout = new javax.swing.GroupLayout(LlenarSubGenericaCT4.getContentPane());
                                                    LlenarSubGenericaCT4.getContentPane().setLayout(LlenarSubGenericaCT4Layout);
                                                    LlenarSubGenericaCT4Layout.setHorizontalGroup(
                                                        LlenarSubGenericaCT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LlenarSubGenericaCT4Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );
                                                    LlenarSubGenericaCT4Layout.setVerticalGroup(
                                                        LlenarSubGenericaCT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(LlenarSubGenericaCT4Layout.createSequentialGroup()
                                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );

                                                    LlenarSubGenericaDetalleCT5.setAlwaysOnTop(true);
                                                    LlenarSubGenericaDetalleCT5.setMinimumSize(new java.awt.Dimension(310, 441));

                                                    jPanel15.setBackground(new java.awt.Color(0, 153, 153));

                                                    jLabel39.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                    jLabel39.setForeground(new java.awt.Color(255, 255, 255));
                                                    jLabel39.setText("Sub Generica Detalle");

                                                    btnbuscar4.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                                                    btnbuscar4.setMnemonic('N');
                                                    btnbuscar4.setToolTipText("");
                                                    btnbuscar4.setContentAreaFilled(false);
                                                    btnbuscar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar4.setIconTextGap(30);
                                                    btnbuscar4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar4.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar4ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar3.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscar3CaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar3.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscar3ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                                                    jPanel15.setLayout(jPanel15Layout);
                                                    jPanel15Layout.setHorizontalGroup(
                                                        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel15Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel39)
                                                                .addGroup(jPanel15Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(30, Short.MAX_VALUE))
                                                    );
                                                    jPanel15Layout.setVerticalGroup(
                                                        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel39)
                                                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel15Layout.createSequentialGroup()
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtBuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel15Layout.createSequentialGroup()
                                                                    .addGap(3, 3, 3)
                                                                    .addComponent(btnbuscar4)))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    tb_Grupo3.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo3.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo3.setRowHeight(25);
                                                    tb_Grupo3.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                    tb_Grupo3.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Grupo3MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Grupo3.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Grupo3KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane5.setViewportView(tb_Grupo3);

                                                    javax.swing.GroupLayout LlenarSubGenericaDetalleCT5Layout = new javax.swing.GroupLayout(LlenarSubGenericaDetalleCT5.getContentPane());
                                                    LlenarSubGenericaDetalleCT5.getContentPane().setLayout(LlenarSubGenericaDetalleCT5Layout);
                                                    LlenarSubGenericaDetalleCT5Layout.setHorizontalGroup(
                                                        LlenarSubGenericaDetalleCT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(LlenarSubGenericaDetalleCT5Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );
                                                    LlenarSubGenericaDetalleCT5Layout.setVerticalGroup(
                                                        LlenarSubGenericaDetalleCT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(LlenarSubGenericaDetalleCT5Layout.createSequentialGroup()
                                                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                                            .addGap(9, 9, 9))
                                                    );

                                                    LlenarEspecifica.setAlwaysOnTop(true);
                                                    LlenarEspecifica.setMinimumSize(new java.awt.Dimension(310, 441));

                                                    jPanel17.setBackground(new java.awt.Color(0, 153, 153));

                                                    jLabel53.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                    jLabel53.setForeground(new java.awt.Color(255, 255, 255));
                                                    jLabel53.setText("Especifica");

                                                    btnbuscar5.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                                                    btnbuscar5.setMnemonic('N');
                                                    btnbuscar5.setToolTipText("");
                                                    btnbuscar5.setContentAreaFilled(false);
                                                    btnbuscar5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar5.setIconTextGap(30);
                                                    btnbuscar5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar5.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar5ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar4.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscar4CaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar4.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscar4ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                                                    jPanel17.setLayout(jPanel17Layout);
                                                    jPanel17Layout.setHorizontalGroup(
                                                        jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel17Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel53)
                                                                .addGroup(jPanel17Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(30, Short.MAX_VALUE))
                                                    );
                                                    jPanel17Layout.setVerticalGroup(
                                                        jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel53)
                                                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel17Layout.createSequentialGroup()
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtBuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel17Layout.createSequentialGroup()
                                                                    .addGap(3, 3, 3)
                                                                    .addComponent(btnbuscar5)))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    tb_Grupo4.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo4.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo4.setRowHeight(25);
                                                    tb_Grupo4.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                    tb_Grupo4.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Grupo4MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Grupo4.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Grupo4KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane6.setViewportView(tb_Grupo4);

                                                    javax.swing.GroupLayout LlenarEspecificaLayout = new javax.swing.GroupLayout(LlenarEspecifica.getContentPane());
                                                    LlenarEspecifica.getContentPane().setLayout(LlenarEspecificaLayout);
                                                    LlenarEspecificaLayout.setHorizontalGroup(
                                                        LlenarEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(LlenarEspecificaLayout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );
                                                    LlenarEspecificaLayout.setVerticalGroup(
                                                        LlenarEspecificaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(LlenarEspecificaLayout.createSequentialGroup()
                                                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );

                                                    LlenarTransCT1EDITAR.setAlwaysOnTop(true);
                                                    LlenarTransCT1EDITAR.setMinimumSize(new java.awt.Dimension(310, 441));

                                                    jPanel18.setBackground(new java.awt.Color(0, 153, 153));

                                                    jLabel54.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                    jLabel54.setForeground(new java.awt.Color(255, 255, 255));
                                                    jLabel54.setText("Tipo de Transacciones");

                                                    btnbuscar6.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                                                    btnbuscar6.setMnemonic('N');
                                                    btnbuscar6.setToolTipText("");
                                                    btnbuscar6.setContentAreaFilled(false);
                                                    btnbuscar6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar6.setIconTextGap(30);
                                                    btnbuscar6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar6.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar6ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar5.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscar5CaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar5.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscar5ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
                                                    jPanel18.setLayout(jPanel18Layout);
                                                    jPanel18Layout.setHorizontalGroup(
                                                        jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel18Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel54)
                                                                .addGroup(jPanel18Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar5, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(22, Short.MAX_VALUE))
                                                    );
                                                    jPanel18Layout.setVerticalGroup(
                                                        jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel54)
                                                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel18Layout.createSequentialGroup()
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtBuscar5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel18Layout.createSequentialGroup()
                                                                    .addGap(3, 3, 3)
                                                                    .addComponent(btnbuscar6)))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    tb_Grupo5.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo5.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo5.setRowHeight(25);
                                                    tb_Grupo5.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                    tb_Grupo5.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Grupo5MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Grupo5.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Grupo5KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane7.setViewportView(tb_Grupo5);

                                                    javax.swing.GroupLayout LlenarTransCT1EDITARLayout = new javax.swing.GroupLayout(LlenarTransCT1EDITAR.getContentPane());
                                                    LlenarTransCT1EDITAR.getContentPane().setLayout(LlenarTransCT1EDITARLayout);
                                                    LlenarTransCT1EDITARLayout.setHorizontalGroup(
                                                        LlenarTransCT1EDITARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(LlenarTransCT1EDITARLayout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );
                                                    LlenarTransCT1EDITARLayout.setVerticalGroup(
                                                        LlenarTransCT1EDITARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(LlenarTransCT1EDITARLayout.createSequentialGroup()
                                                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );

                                                    LlenarGenericaCT2EDITAR.setAlwaysOnTop(true);
                                                    LlenarGenericaCT2EDITAR.setMinimumSize(new java.awt.Dimension(310, 441));

                                                    jPanel19.setBackground(new java.awt.Color(0, 153, 153));

                                                    jLabel55.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                    jLabel55.setForeground(new java.awt.Color(255, 255, 255));
                                                    jLabel55.setText("Generica");

                                                    btnbuscar7.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                                                    btnbuscar7.setMnemonic('N');
                                                    btnbuscar7.setToolTipText("");
                                                    btnbuscar7.setContentAreaFilled(false);
                                                    btnbuscar7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar7.setIconTextGap(30);
                                                    btnbuscar7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar7.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar7ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar6.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar6.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscar6CaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar6.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscar6ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
                                                    jPanel19.setLayout(jPanel19Layout);
                                                    jPanel19Layout.setHorizontalGroup(
                                                        jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel19Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel55)
                                                                .addGroup(jPanel19Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar6, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );
                                                    jPanel19Layout.setVerticalGroup(
                                                        jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel55)
                                                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel19Layout.createSequentialGroup()
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtBuscar6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel19Layout.createSequentialGroup()
                                                                    .addGap(3, 3, 3)
                                                                    .addComponent(btnbuscar7)))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    tb_Grupo6.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo6.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo6.setRowHeight(25);
                                                    tb_Grupo6.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                    tb_Grupo6.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Grupo6MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Grupo6.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Grupo6KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane8.setViewportView(tb_Grupo6);

                                                    javax.swing.GroupLayout LlenarGenericaCT2EDITARLayout = new javax.swing.GroupLayout(LlenarGenericaCT2EDITAR.getContentPane());
                                                    LlenarGenericaCT2EDITAR.getContentPane().setLayout(LlenarGenericaCT2EDITARLayout);
                                                    LlenarGenericaCT2EDITARLayout.setHorizontalGroup(
                                                        LlenarGenericaCT2EDITARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(LlenarGenericaCT2EDITARLayout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );
                                                    LlenarGenericaCT2EDITARLayout.setVerticalGroup(
                                                        LlenarGenericaCT2EDITARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(LlenarGenericaCT2EDITARLayout.createSequentialGroup()
                                                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );

                                                    LlenarSubGenericaEDITAR.setAlwaysOnTop(true);
                                                    LlenarSubGenericaEDITAR.setMinimumSize(new java.awt.Dimension(310, 441));

                                                    jPanel20.setBackground(new java.awt.Color(0, 153, 153));

                                                    jLabel56.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                    jLabel56.setForeground(new java.awt.Color(255, 255, 255));
                                                    jLabel56.setText("Sub Generica");

                                                    btnbuscar8.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                                                    btnbuscar8.setMnemonic('N');
                                                    btnbuscar8.setToolTipText("");
                                                    btnbuscar8.setContentAreaFilled(false);
                                                    btnbuscar8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar8.setIconTextGap(30);
                                                    btnbuscar8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar8.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar8ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar7.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar7.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscar7CaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar7.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscar7ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
                                                    jPanel20.setLayout(jPanel20Layout);
                                                    jPanel20Layout.setHorizontalGroup(
                                                        jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel20Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel56)
                                                                .addGroup(jPanel20Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar7, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(30, Short.MAX_VALUE))
                                                    );
                                                    jPanel20Layout.setVerticalGroup(
                                                        jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel56)
                                                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel20Layout.createSequentialGroup()
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtBuscar7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel20Layout.createSequentialGroup()
                                                                    .addGap(3, 3, 3)
                                                                    .addComponent(btnbuscar8)))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    tb_Grupo7.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo7.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo7.setRowHeight(25);
                                                    tb_Grupo7.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                    tb_Grupo7.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Grupo7MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Grupo7.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Grupo7KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane9.setViewportView(tb_Grupo7);

                                                    javax.swing.GroupLayout LlenarSubGenericaEDITARLayout = new javax.swing.GroupLayout(LlenarSubGenericaEDITAR.getContentPane());
                                                    LlenarSubGenericaEDITAR.getContentPane().setLayout(LlenarSubGenericaEDITARLayout);
                                                    LlenarSubGenericaEDITARLayout.setHorizontalGroup(
                                                        LlenarSubGenericaEDITARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LlenarSubGenericaEDITARLayout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );
                                                    LlenarSubGenericaEDITARLayout.setVerticalGroup(
                                                        LlenarSubGenericaEDITARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(LlenarSubGenericaEDITARLayout.createSequentialGroup()
                                                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );

                                                    SubGenericaDetalleEDITAR.setAlwaysOnTop(true);
                                                    SubGenericaDetalleEDITAR.setMinimumSize(new java.awt.Dimension(310, 441));

                                                    jPanel21.setBackground(new java.awt.Color(0, 153, 153));

                                                    jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                    jLabel57.setForeground(new java.awt.Color(255, 255, 255));
                                                    jLabel57.setText("Sub Generica Detalle");

                                                    btnbuscar9.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                                                    btnbuscar9.setMnemonic('N');
                                                    btnbuscar9.setToolTipText("");
                                                    btnbuscar9.setContentAreaFilled(false);
                                                    btnbuscar9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar9.setIconTextGap(30);
                                                    btnbuscar9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar9.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar9ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar8.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar8.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscar8CaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar8.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscar8ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
                                                    jPanel21.setLayout(jPanel21Layout);
                                                    jPanel21Layout.setHorizontalGroup(
                                                        jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel21Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel57)
                                                                .addGroup(jPanel21Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar8, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(30, Short.MAX_VALUE))
                                                    );
                                                    jPanel21Layout.setVerticalGroup(
                                                        jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel57)
                                                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel21Layout.createSequentialGroup()
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtBuscar8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel21Layout.createSequentialGroup()
                                                                    .addGap(3, 3, 3)
                                                                    .addComponent(btnbuscar9)))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    tb_Grupo8.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo8.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo8.setRowHeight(25);
                                                    tb_Grupo8.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                    tb_Grupo8.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Grupo8MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Grupo8.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Grupo8KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane10.setViewportView(tb_Grupo8);

                                                    javax.swing.GroupLayout SubGenericaDetalleEDITARLayout = new javax.swing.GroupLayout(SubGenericaDetalleEDITAR.getContentPane());
                                                    SubGenericaDetalleEDITAR.getContentPane().setLayout(SubGenericaDetalleEDITARLayout);
                                                    SubGenericaDetalleEDITARLayout.setHorizontalGroup(
                                                        SubGenericaDetalleEDITARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(SubGenericaDetalleEDITARLayout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );
                                                    SubGenericaDetalleEDITARLayout.setVerticalGroup(
                                                        SubGenericaDetalleEDITARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(SubGenericaDetalleEDITARLayout.createSequentialGroup()
                                                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                                            .addGap(9, 9, 9))
                                                    );

                                                    EspecificaEditar.setAlwaysOnTop(true);
                                                    EspecificaEditar.setMinimumSize(new java.awt.Dimension(310, 441));

                                                    jPanel22.setBackground(new java.awt.Color(0, 153, 153));

                                                    jLabel58.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                    jLabel58.setForeground(new java.awt.Color(255, 255, 255));
                                                    jLabel58.setText("Especifica");

                                                    btnbuscar10.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                                                    btnbuscar10.setMnemonic('N');
                                                    btnbuscar10.setToolTipText("");
                                                    btnbuscar10.setContentAreaFilled(false);
                                                    btnbuscar10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar10.setIconTextGap(30);
                                                    btnbuscar10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar10.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar10ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar9.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar9.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscar9CaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar9.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscar9ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
                                                    jPanel22.setLayout(jPanel22Layout);
                                                    jPanel22Layout.setHorizontalGroup(
                                                        jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel22Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel58)
                                                                .addGroup(jPanel22Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar9, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(30, Short.MAX_VALUE))
                                                    );
                                                    jPanel22Layout.setVerticalGroup(
                                                        jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel58)
                                                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel22Layout.createSequentialGroup()
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtBuscar9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel22Layout.createSequentialGroup()
                                                                    .addGap(3, 3, 3)
                                                                    .addComponent(btnbuscar10)))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    tb_Grupo9.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo9.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo9.setRowHeight(25);
                                                    tb_Grupo9.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                    tb_Grupo9.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Grupo9MouseClicked(evt);
                                                        }
                                                    });
                                                    tb_Grupo9.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Grupo9KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane11.setViewportView(tb_Grupo9);

                                                    javax.swing.GroupLayout EspecificaEditarLayout = new javax.swing.GroupLayout(EspecificaEditar.getContentPane());
                                                    EspecificaEditar.getContentPane().setLayout(EspecificaEditarLayout);
                                                    EspecificaEditarLayout.setHorizontalGroup(
                                                        EspecificaEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EspecificaEditarLayout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );
                                                    EspecificaEditarLayout.setVerticalGroup(
                                                        EspecificaEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(EspecificaEditarLayout.createSequentialGroup()
                                                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );

                                                    EspecificaDetalleEDITAR.setMinimumSize(new java.awt.Dimension(310, 441));

                                                    jPanel23.setBackground(new java.awt.Color(0, 153, 153));

                                                    jLabel59.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                    jLabel59.setForeground(new java.awt.Color(255, 255, 255));
                                                    jLabel59.setText("Especifica de Detalle");

                                                    btnbuscar11.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                                                    btnbuscar11.setMnemonic('N');
                                                    btnbuscar11.setToolTipText("");
                                                    btnbuscar11.setContentAreaFilled(false);
                                                    btnbuscar11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar11.setIconTextGap(30);
                                                    btnbuscar11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar11.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscar11ActionPerformed(evt);
                                                        }
                                                    });

                                                    txtBuscar10.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    txtBuscar10.addCaretListener(new javax.swing.event.CaretListener() {
                                                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                            txtBuscar10CaretUpdate(evt);
                                                        }
                                                    });
                                                    txtBuscar10.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtBuscar10ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
                                                    jPanel23.setLayout(jPanel23Layout);
                                                    jPanel23Layout.setHorizontalGroup(
                                                        jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel23Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel59)
                                                                .addGroup(jPanel23Layout.createSequentialGroup()
                                                                    .addComponent(txtBuscar10, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(btnbuscar11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addContainerGap(30, Short.MAX_VALUE))
                                                    );
                                                    jPanel23Layout.setVerticalGroup(
                                                        jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabel59)
                                                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel23Layout.createSequentialGroup()
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(txtBuscar10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel23Layout.createSequentialGroup()
                                                                    .addGap(3, 3, 3)
                                                                    .addComponent(btnbuscar11)))
                                                            .addGap(408, 408, 408))
                                                    );

                                                    tb_Grupo10.setModel(new javax.swing.table.DefaultTableModel(
                                                        new Object [][] {
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null},
                                                            {null, null, null, null}
                                                        },
                                                        new String [] {
                                                            "Title 1", "Title 2", "Title 3", "Title 4"
                                                        }
                                                    ));
                                                    tb_Grupo10.setGridColor(new java.awt.Color(255, 255, 255));
                                                    tb_Grupo10.setRowHeight(25);
                                                    tb_Grupo10.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                                    tb_Grupo10.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            tb_Grupo10MouseClicked(evt);
                                                        }
                                                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                                            tb_Grupo10MousePressed(evt);
                                                        }
                                                    });
                                                    tb_Grupo10.addKeyListener(new java.awt.event.KeyAdapter() {
                                                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                                            tb_Grupo10KeyPressed(evt);
                                                        }
                                                    });
                                                    jScrollPane12.setViewportView(tb_Grupo10);

                                                    javax.swing.GroupLayout EspecificaDetalleEDITARLayout = new javax.swing.GroupLayout(EspecificaDetalleEDITAR.getContentPane());
                                                    EspecificaDetalleEDITAR.getContentPane().setLayout(EspecificaDetalleEDITARLayout);
                                                    EspecificaDetalleEDITARLayout.setHorizontalGroup(
                                                        EspecificaDetalleEDITARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(EspecificaDetalleEDITARLayout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );
                                                    EspecificaDetalleEDITARLayout.setVerticalGroup(
                                                        EspecificaDetalleEDITARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(EspecificaDetalleEDITARLayout.createSequentialGroup()
                                                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                                            .addContainerGap())
                                                    );

                                                    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                                                    setMinimumSize(new java.awt.Dimension(749, 473));
                                                    getContentPane().setLayout(null);

                                                    jPanel1.setBackground(new java.awt.Color(0, 153, 153));

                                                    jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                                    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                                                    jLabel1.setText("Cuentas Contables");

                                                    btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                                                    btnNuevo.setMnemonic('N');
                                                    btnNuevo.setContentAreaFilled(false);
                                                    btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnNuevo.setIconTextGap(30);
                                                    btnNuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnNuevoActionPerformed(evt);
                                                        }
                                                    });

                                                    btneditar.setForeground(new java.awt.Color(240, 240, 240));
                                                    btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
                                                    btneditar.setMnemonic('N');
                                                    btneditar.setContentAreaFilled(false);
                                                    btneditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btneditar.setIconTextGap(30);
                                                    btneditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btneditar.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btneditarActionPerformed(evt);
                                                        }
                                                    });

                                                    btnguardar.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
                                                    btnguardar.setMnemonic('N');
                                                    btnguardar.setContentAreaFilled(false);
                                                    btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnguardar.setIconTextGap(30);
                                                    btnguardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnguardar.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnguardarActionPerformed(evt);
                                                        }
                                                    });

                                                    btneliminar.setForeground(new java.awt.Color(240, 240, 240));
                                                    btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
                                                    btneliminar.setMnemonic('N');
                                                    btneliminar.setContentAreaFilled(false);
                                                    btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btneliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btneliminar.setIconTextGap(30);
                                                    btneliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btneliminar.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btneliminarActionPerformed(evt);
                                                        }
                                                    });

                                                    btnbuscar.setForeground(new java.awt.Color(240, 240, 240));
                                                    btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                                                    btnbuscar.setMnemonic('N');
                                                    btnbuscar.setContentAreaFilled(false);
                                                    btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btnbuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btnbuscar.setIconTextGap(30);
                                                    btnbuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btnbuscar.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btnbuscarActionPerformed(evt);
                                                        }
                                                    });

                                                    lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    lblusu.setForeground(new java.awt.Color(255, 255, 255));
                                                    lblusu.setText("Ricardo Chumpitaz");

                                                    jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    jLabel6.setForeground(new java.awt.Color(255, 255, 255));
                                                    jLabel6.setText("Fecha");

                                                    jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    jLabel7.setForeground(new java.awt.Color(255, 255, 255));
                                                    jLabel7.setText("Hora");

                                                    btneliminar1.setForeground(new java.awt.Color(240, 240, 240));
                                                    btneliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
                                                    btneliminar1.setMnemonic('N');
                                                    btneliminar1.setToolTipText("");
                                                    btneliminar1.setContentAreaFilled(false);
                                                    btneliminar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    btneliminar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                                    btneliminar1.setIconTextGap(30);
                                                    btneliminar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                                    btneliminar1.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            btneliminar1ActionPerformed(evt);
                                                        }
                                                    });

                                                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                                                    jPanel1.setLayout(jPanel1Layout);
                                                    jPanel1Layout.setHorizontalGroup(
                                                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel1)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(10, 10, 10)
                                                                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 324, Short.MAX_VALUE)
                                                            .addComponent(btneliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(lblusu)
                                                                .addComponent(jLabel6)
                                                                .addComponent(jLabel7))
                                                            .addGap(34, 34, 34))
                                                    );
                                                    jPanel1Layout.setVerticalGroup(
                                                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                            .addGap(20, 20, 20)
                                                            .addComponent(lblusu)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(jLabel6)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jLabel7)
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(btneliminar1)
                                                                .addComponent(jLabel1))
                                                            .addGap(18, 18, 18)
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(btneditar, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(btneliminar)
                                                                    .addComponent(btnbuscar))
                                                                .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.TRAILING))
                                                            .addGap(552, 552, 552))
                                                    );

                                                    getContentPane().add(jPanel1);
                                                    jPanel1.setBounds(0, 0, 760, 120);

                                                    jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Exit-16.png"))); // NOI18N
                                                    jLabel13.setText("Salir (ESC)");

                                                    jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Document-16.png"))); // NOI18N
                                                    jLabel23.setText("Nuevo (Alt+N)");

                                                    jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Edit-16.png"))); // NOI18N
                                                    jLabel33.setText("Modificar (Alt+M)");

                                                    jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Save-16.png"))); // NOI18N
                                                    jLabel34.setText("Guardar (Alt+G)");

                                                    jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                                                    jLabel35.setText("Buscar (Alt+F3)");

                                                    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                                                    jPanel5.setLayout(jPanel5Layout);
                                                    jPanel5Layout.setHorizontalGroup(
                                                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                            .addGap(27, 27, 27)
                                                            .addComponent(jLabel23)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jLabel33)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jLabel34)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jLabel35)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jLabel13)
                                                            .addContainerGap(193, Short.MAX_VALUE))
                                                    );
                                                    jPanel5Layout.setVerticalGroup(
                                                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel13)
                                                                .addComponent(jLabel23)
                                                                .addComponent(jLabel33)
                                                                .addComponent(jLabel34)
                                                                .addComponent(jLabel35))
                                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    );

                                                    getContentPane().add(jPanel5);
                                                    jPanel5.setBounds(0, 435, 749, 0);

                                                    jPanel10.setBackground(new java.awt.Color(255, 255, 255));

                                                    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                                                    jPanel10.setLayout(jPanel10Layout);
                                                    jPanel10Layout.setHorizontalGroup(
                                                        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 740, Short.MAX_VALUE)
                                                    );
                                                    jPanel10Layout.setVerticalGroup(
                                                        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGap(0, 40, Short.MAX_VALUE)
                                                    );

                                                    getContentPane().add(jPanel10);
                                                    jPanel10.setBounds(0, 120, 740, 40);

                                                    jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
                                                    jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
                                                    jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            jTabbedPane1MouseClicked(evt);
                                                        }
                                                    });

                                                    jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                                                    bc.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    bc.setForeground(new java.awt.Color(102, 102, 102));
                                                    bc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Page Overview 2-50.png"))); // NOI18N
                                                    bc.setMnemonic('N');
                                                    bc.setText("Tipo de Transaccion");
                                                    bc.setContentAreaFilled(false);
                                                    bc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc.setFocusPainted(false);
                                                    bc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc.setIconTextGap(30);
                                                    bc.setVerifyInputWhenFocusTarget(false);
                                                    bc.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bcActionPerformed(evt);
                                                        }
                                                    });

                                                    bc1.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    bc1.setForeground(new java.awt.Color(102, 102, 102));
                                                    bc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Generic Book File Type-50.png"))); // NOI18N
                                                    bc1.setMnemonic('N');
                                                    bc1.setText("Generica                ");
                                                    bc1.setContentAreaFilled(false);
                                                    bc1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc1.setFocusPainted(false);
                                                    bc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc1.setIconTextGap(30);
                                                    bc1.setVerifyInputWhenFocusTarget(false);
                                                    bc1.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc1ActionPerformed(evt);
                                                        }
                                                    });

                                                    bc2.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    bc2.setForeground(new java.awt.Color(102, 102, 102));
                                                    bc2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Page Overview 4-50.png"))); // NOI18N
                                                    bc2.setMnemonic('N');
                                                    bc2.setText("Sub Generica Detalle");
                                                    bc2.setContentAreaFilled(false);
                                                    bc2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc2.setFocusPainted(false);
                                                    bc2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc2.setIconTextGap(30);
                                                    bc2.setVerifyInputWhenFocusTarget(false);
                                                    bc2.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc2ActionPerformed(evt);
                                                        }
                                                    });

                                                    bc3.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    bc3.setForeground(new java.awt.Color(102, 102, 102));
                                                    bc3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documents-50.png"))); // NOI18N
                                                    bc3.setMnemonic('N');
                                                    bc3.setText("Sub Generica            ");
                                                    bc3.setContentAreaFilled(false);
                                                    bc3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc3.setFocusPainted(false);
                                                    bc3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc3.setIconTextGap(30);
                                                    bc3.setVerifyInputWhenFocusTarget(false);
                                                    bc3.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc3ActionPerformed(evt);
                                                        }
                                                    });

                                                    bc4.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    bc4.setForeground(new java.awt.Color(102, 102, 102));
                                                    bc4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Page Overview 3-50.png"))); // NOI18N
                                                    bc4.setMnemonic('N');
                                                    bc4.setText("Especifica                  ");
                                                    bc4.setContentAreaFilled(false);
                                                    bc4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc4.setFocusPainted(false);
                                                    bc4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc4.setIconTextGap(30);
                                                    bc4.setVerifyInputWhenFocusTarget(false);
                                                    bc4.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc4ActionPerformed(evt);
                                                        }
                                                    });

                                                    bc5.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    bc5.setForeground(new java.awt.Color(102, 102, 102));
                                                    bc5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Align Right Filled-50.png"))); // NOI18N
                                                    bc5.setMnemonic('N');
                                                    bc5.setText("Especifica de Detalle");
                                                    bc5.setContentAreaFilled(false);
                                                    bc5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc5.setFocusPainted(false);
                                                    bc5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc5.setIconTextGap(30);
                                                    bc5.setVerifyInputWhenFocusTarget(false);
                                                    bc5.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc5ActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel51.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                                    jLabel51.setForeground(new java.awt.Color(102, 102, 102));
                                                    jLabel51.setText("Agregar una nueva cuenta");

                                                    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                                                    jPanel3.setLayout(jPanel3Layout);
                                                    jPanel3Layout.setHorizontalGroup(
                                                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                            .addComponent(bc, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(bc1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                            .addComponent(bc2)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(bc4, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(bc5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(bc3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                    .addComponent(jLabel51)
                                                                    .addGap(0, 0, Short.MAX_VALUE))))
                                                    );
                                                    jPanel3Layout.setVerticalGroup(
                                                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                            .addContainerGap(20, Short.MAX_VALUE)
                                                            .addComponent(jLabel51)
                                                            .addGap(18, 18, 18)
                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(bc)
                                                                .addComponent(bc1)
                                                                .addComponent(bc3))
                                                            .addGap(19, 19, 19)
                                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(bc2)
                                                                .addComponent(bc4)
                                                                .addComponent(bc5))
                                                            .addGap(34, 34, 34))
                                                    );

                                                    jTabbedPane1.addTab("B", jPanel3);

                                                    jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                                                    jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                                    jLabel8.setForeground(new java.awt.Color(102, 102, 102));
                                                    jLabel8.setText("Tipo de Transaccion");

                                                    jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel9.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel9.setText("Codigo");

                                                    jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel10.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel10.setText("Tipo");

                                                    jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel11.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel11.setText("Descripcion");

                                                    txtcodT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtcodT.setEnabled(false);
                                                    txtcodT.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtcodTActionPerformed(evt);
                                                        }
                                                    });

                                                    txttipoT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txttipoT.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txttipoTActionPerformed(evt);
                                                        }
                                                    });

                                                    txtdesT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

                                                    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                                                    jPanel4.setLayout(jPanel4Layout);
                                                    jPanel4Layout.setHorizontalGroup(
                                                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel8)
                                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel9)
                                                                        .addComponent(jLabel10)
                                                                        .addComponent(jLabel11))
                                                                    .addGap(33, 33, 33)
                                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(txtdesT, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txttipoT, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txtcodT, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                            .addContainerGap(216, Short.MAX_VALUE))
                                                    );
                                                    jPanel4Layout.setVerticalGroup(
                                                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addComponent(jLabel8)
                                                            .addGap(18, 18, 18)
                                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel9)
                                                                .addComponent(txtcodT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(20, 20, 20)
                                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel10)
                                                                .addComponent(txttipoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(18, 18, 18)
                                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel11)
                                                                .addComponent(txtdesT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addContainerGap(48, Short.MAX_VALUE))
                                                    );

                                                    jTabbedPane1.addTab("C1", jPanel4);

                                                    jPanel11.setBackground(new java.awt.Color(255, 255, 255));

                                                    jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                                    jLabel12.setForeground(new java.awt.Color(102, 102, 102));
                                                    jLabel12.setText("Generica");

                                                    jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel14.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel14.setText("Codigo");

                                                    jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel15.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel15.setText("Transaccion");

                                                    jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel16.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel16.setText("Descripcion");

                                                    txtcodG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtcodG.setEnabled(false);
                                                    txtcodG.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtcodGActionPerformed(evt);
                                                        }
                                                    });

                                                    txtdesG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

                                                    txttrans.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txttrans.setEnabled(false);
                                                    txttrans.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            txttransMouseClicked(evt);
                                                        }
                                                    });
                                                    txttrans.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txttransActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel17.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel17.setText("Generica");

                                                    txttrans1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txttrans1.setEnabled(false);
                                                    txttrans1.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txttrans1ActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel18.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel18.setText(".");

                                                    txtgen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtgen.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtgenActionPerformed(evt);
                                                        }
                                                    });

                                                    B1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                                                    B1.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            B1ActionPerformed(evt);
                                                        }
                                                    });

                                                    lblCT1.setBackground(new java.awt.Color(255, 255, 255));
                                                    lblCT1.setForeground(new java.awt.Color(255, 255, 255));
                                                    lblCT1.setText("jLabel2");

                                                    cuentagenerica.setBackground(new java.awt.Color(255, 255, 255));
                                                    cuentagenerica.setForeground(new java.awt.Color(255, 255, 255));
                                                    cuentagenerica.setText("000");

                                                    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                                                    jPanel11.setLayout(jPanel11Layout);
                                                    jPanel11Layout.setHorizontalGroup(
                                                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                            .addGap(10, 10, 10)
                                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel12)
                                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                                    .addComponent(jLabel16)
                                                                    .addGap(39, 39, 39)
                                                                    .addComponent(txtdesG, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                                            .addComponent(jLabel14)
                                                                            .addGap(63, 63, 63)
                                                                            .addComponent(txtcodG))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel15)
                                                                                .addComponent(jLabel17))
                                                                            .addGap(36, 36, 36)
                                                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                                                    .addComponent(txttrans1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(jLabel18)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(txtgen, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(txttrans))))
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(B1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(122, 122, 122)
                                                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(lblCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(cuentagenerica, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                            .addGap(210, 210, 210))
                                                    );
                                                    jPanel11Layout.setVerticalGroup(
                                                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                            .addGap(11, 11, 11)
                                                            .addComponent(jLabel12)
                                                            .addGap(14, 14, 14)
                                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(txtcodG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel14)
                                                                        .addComponent(cuentagenerica))
                                                                    .addGap(18, 18, 18)
                                                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(B1)
                                                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(txttrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(jLabel15))))
                                                                .addComponent(lblCT1))
                                                            .addGap(18, 18, 18)
                                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel17)
                                                                .addComponent(txttrans1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel18)
                                                                .addComponent(txtgen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(18, 18, 18)
                                                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel11Layout.createSequentialGroup()
                                                                    .addComponent(jLabel16)
                                                                    .addGap(0, 35, Short.MAX_VALUE))
                                                                .addComponent(txtdesG))
                                                            .addContainerGap())
                                                    );

                                                    jTabbedPane1.addTab("C2", jPanel11);

                                                    jPanel12.setBackground(new java.awt.Color(255, 255, 255));

                                                    jLabel20.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                                    jLabel20.setForeground(new java.awt.Color(102, 102, 102));
                                                    jLabel20.setText("Sub Generica");

                                                    jLabel41.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel41.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel41.setText("Codigo");

                                                    jLabel42.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel42.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel42.setText("Generica");

                                                    jLabel43.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel43.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel43.setText("Descripcion");

                                                    txtcodsubgen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtcodsubgen.setEnabled(false);
                                                    txtcodsubgen.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtcodsubgenActionPerformed(evt);
                                                        }
                                                    });

                                                    txtdesSubG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

                                                    txtgen2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtgen2.setEnabled(false);
                                                    txtgen2.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            txtgen2MouseClicked(evt);
                                                        }
                                                    });
                                                    txtgen2.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtgen2ActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel44.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel44.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel44.setText("Sub Generica");

                                                    txtgen3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtgen3.setEnabled(false);
                                                    txtgen3.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtgen3ActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel45.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel45.setText(".");

                                                    txtsub.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtsub.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtsubActionPerformed(evt);
                                                        }
                                                    });

                                                    b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                                                    b2.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            b2ActionPerformed(evt);
                                                        }
                                                    });

                                                    lblCT2.setForeground(new java.awt.Color(255, 255, 255));
                                                    lblCT2.setText("jLabel2");

                                                    sugenericacadena.setForeground(new java.awt.Color(255, 255, 255));
                                                    sugenericacadena.setText("00000");

                                                    javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                                                    jPanel12.setLayout(jPanel12Layout);
                                                    jPanel12Layout.setHorizontalGroup(
                                                        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel12Layout.createSequentialGroup()
                                                                    .addGap(10, 10, 10)
                                                                    .addComponent(jLabel44)
                                                                    .addGap(30, 30, 30)
                                                                    .addComponent(txtgen3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(4, 4, 4)
                                                                    .addComponent(jLabel45)
                                                                    .addGap(4, 4, 4)
                                                                    .addComponent(txtsub, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel12Layout.createSequentialGroup()
                                                                    .addGap(10, 10, 10)
                                                                    .addComponent(jLabel43)
                                                                    .addGap(41, 41, 41)
                                                                    .addComponent(txtdesSubG, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel12Layout.createSequentialGroup()
                                                                    .addGap(10, 10, 10)
                                                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel42)
                                                                        .addComponent(jLabel20)
                                                                        .addComponent(jLabel41))
                                                                    .addGap(1, 1, 1)
                                                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(txtcodsubgen, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                                                            .addComponent(txtgen2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addGroup(jPanel12Layout.createSequentialGroup()
                                                                    .addGap(508, 508, 508)
                                                                    .addComponent(lblCT2)
                                                                    .addGap(48, 48, 48)
                                                                    .addComponent(sugenericacadena)))
                                                            .addContainerGap(115, Short.MAX_VALUE))
                                                    );
                                                    jPanel12Layout.setVerticalGroup(
                                                        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                                            .addGap(11, 11, 11)
                                                            .addComponent(jLabel20)
                                                            .addGap(14, 14, 14)
                                                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel41)
                                                                .addComponent(txtcodsubgen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(18, 18, 18)
                                                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel12Layout.createSequentialGroup()
                                                                    .addGap(3, 3, 3)
                                                                    .addComponent(jLabel42))
                                                                .addComponent(txtgen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(b2))
                                                            .addGap(16, 16, 16)
                                                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(txtgen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txtsub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel12Layout.createSequentialGroup()
                                                                    .addGap(3, 3, 3)
                                                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel44)
                                                                        .addComponent(jLabel45))))
                                                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel12Layout.createSequentialGroup()
                                                                    .addGap(21, 21, 21)
                                                                    .addComponent(jLabel43))
                                                                .addGroup(jPanel12Layout.createSequentialGroup()
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(txtdesSubG, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(lblCT2)
                                                                .addComponent(sugenericacadena)))
                                                    );

                                                    jTabbedPane1.addTab("C2", jPanel12);

                                                    jPanel13.setBackground(new java.awt.Color(255, 255, 255));

                                                    jLabel22.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                                    jLabel22.setForeground(new java.awt.Color(102, 102, 102));
                                                    jLabel22.setText("Sub Generica Detalle");

                                                    jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel24.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel24.setText("Codigo");

                                                    jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel25.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel25.setText("Sub Generica");

                                                    jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel26.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel26.setText("Descripcion");

                                                    txtcodGD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtcodGD.setEnabled(false);
                                                    txtcodGD.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtcodGDActionPerformed(evt);
                                                        }
                                                    });

                                                    txtdesGd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

                                                    txtsubgen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtsubgen.setEnabled(false);
                                                    txtsubgen.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            txtsubgenMouseClicked(evt);
                                                        }
                                                    });
                                                    txtsubgen.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtsubgenActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel27.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel27.setText("Sub Generica Detalle");

                                                    txtsuggend.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtsuggend.setEnabled(false);
                                                    txtsuggend.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtsuggendActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel28.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel28.setText(".");

                                                    txtgend.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtgend.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtgendActionPerformed(evt);
                                                        }
                                                    });

                                                    B3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                                                    B3.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            B3ActionPerformed(evt);
                                                        }
                                                    });

                                                    lblCT3.setForeground(new java.awt.Color(255, 255, 255));
                                                    lblCT3.setText("jLabel2");

                                                    Subgenericadetalle.setForeground(new java.awt.Color(255, 255, 255));
                                                    Subgenericadetalle.setText("0000000");

                                                    javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                                                    jPanel13.setLayout(jPanel13Layout);
                                                    jPanel13Layout.setHorizontalGroup(
                                                        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                            .addGap(10, 10, 10)
                                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel13Layout.createSequentialGroup()
                                                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel25)
                                                                        .addComponent(jLabel27)
                                                                        .addComponent(jLabel24))
                                                                    .addGap(36, 36, 36)
                                                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(txtcodGD)
                                                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                                            .addComponent(txtsuggend, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(jLabel28)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(txtgend, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                                                                        .addComponent(txtsubgen))
                                                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(B3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addGap(110, 110, 110)
                                                                            .addComponent(lblCT3))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                                                            .addGap(227, 227, 227)
                                                                            .addComponent(Subgenericadetalle)
                                                                            .addGap(200, 200, 200))))
                                                                .addGroup(jPanel13Layout.createSequentialGroup()
                                                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jLabel22)
                                                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                                            .addComponent(jLabel26)
                                                                            .addGap(88, 88, 88)
                                                                            .addComponent(txtdesGd, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                    .addGap(161, 161, 161))))
                                                    );
                                                    jPanel13Layout.setVerticalGroup(
                                                        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                                            .addGap(11, 11, 11)
                                                            .addComponent(jLabel22)
                                                            .addGap(14, 14, 14)
                                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addGroup(jPanel13Layout.createSequentialGroup()
                                                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(txtcodGD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel24))
                                                                    .addGap(18, 18, 18)
                                                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(B3, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                            .addComponent(txtsubgen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(jLabel25))))
                                                                .addGroup(jPanel13Layout.createSequentialGroup()
                                                                    .addComponent(Subgenericadetalle)
                                                                    .addGap(23, 23, 23)
                                                                    .addComponent(lblCT3)))
                                                            .addGap(18, 18, 18)
                                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel27)
                                                                .addComponent(txtsuggend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel28)
                                                                .addComponent(txtgend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(18, 18, 18)
                                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel13Layout.createSequentialGroup()
                                                                    .addComponent(jLabel26)
                                                                    .addGap(0, 35, Short.MAX_VALUE))
                                                                .addComponent(txtdesGd))
                                                            .addContainerGap())
                                                    );

                                                    jTabbedPane1.addTab("C2", jPanel13);

                                                    jPanel14.setBackground(new java.awt.Color(255, 255, 255));

                                                    jLabel30.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                                    jLabel30.setForeground(new java.awt.Color(102, 102, 102));
                                                    jLabel30.setText("Especifica");

                                                    jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel31.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel31.setText("Codigo");

                                                    jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel32.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel32.setText("Sub Generica Detalle");

                                                    jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel36.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel36.setText("Descripcion");

                                                    txtcodE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtcodE.setEnabled(false);
                                                    txtcodE.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtcodEActionPerformed(evt);
                                                        }
                                                    });

                                                    txtdesE.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

                                                    txtsubgd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtsubgd.setEnabled(false);
                                                    txtsubgd.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            txtsubgdMouseClicked(evt);
                                                        }
                                                    });
                                                    txtsubgd.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtsubgdActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel37.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel37.setText("Especifica");

                                                    txtsgd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtsgd.setEnabled(false);
                                                    txtsgd.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtsgdActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel38.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel38.setText(".");

                                                    txtespe.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtespe.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtespeActionPerformed(evt);
                                                        }
                                                    });

                                                    b4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                                                    b4.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            b4ActionPerformed(evt);
                                                        }
                                                    });

                                                    lblCT4.setForeground(new java.awt.Color(255, 255, 255));
                                                    lblCT4.setText("jLabel2");

                                                    Especifica.setForeground(new java.awt.Color(255, 255, 255));
                                                    Especifica.setText("0000000");

                                                    javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                                                    jPanel14.setLayout(jPanel14Layout);
                                                    jPanel14Layout.setHorizontalGroup(
                                                        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel14Layout.createSequentialGroup()
                                                            .addGap(10, 10, 10)
                                                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel30)
                                                                .addGroup(jPanel14Layout.createSequentialGroup()
                                                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel32)
                                                                        .addComponent(jLabel37)
                                                                        .addComponent(jLabel31))
                                                                    .addGap(36, 36, 36)
                                                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(txtsubgd)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                                                                            .addComponent(txtsgd, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(jLabel38)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(txtespe, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(txtcodE))
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(110, 110, 110)
                                                                    .addComponent(lblCT4)
                                                                    .addGap(63, 63, 63)
                                                                    .addComponent(Especifica))
                                                                .addGroup(jPanel14Layout.createSequentialGroup()
                                                                    .addComponent(jLabel36)
                                                                    .addGap(88, 88, 88)
                                                                    .addComponent(txtdesE, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addGap(161, 161, 161))
                                                    );
                                                    jPanel14Layout.setVerticalGroup(
                                                        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel14Layout.createSequentialGroup()
                                                            .addGap(11, 11, 11)
                                                            .addComponent(jLabel30)
                                                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel14Layout.createSequentialGroup()
                                                                    .addGap(14, 14, 14)
                                                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jPanel14Layout.createSequentialGroup()
                                                                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(txtcodE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel31))
                                                                            .addGap(18, 18, 18)
                                                                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(txtsubgd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addComponent(jLabel32))
                                                                                .addComponent(b4)))
                                                                        .addComponent(lblCT4))
                                                                    .addGap(18, 18, 18)
                                                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel37)
                                                                        .addComponent(txtsgd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel38)
                                                                        .addComponent(txtespe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addGap(18, 18, 18)
                                                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel14Layout.createSequentialGroup()
                                                                            .addComponent(jLabel36)
                                                                            .addGap(0, 0, Short.MAX_VALUE))
                                                                        .addComponent(txtdesE, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)))
                                                                .addGroup(jPanel14Layout.createSequentialGroup()
                                                                    .addGap(72, 72, 72)
                                                                    .addComponent(Especifica)))
                                                            .addContainerGap())
                                                    );

                                                    jTabbedPane1.addTab("C2", jPanel14);

                                                    jPanel16.setBackground(new java.awt.Color(255, 255, 255));

                                                    jLabel40.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                                    jLabel40.setForeground(new java.awt.Color(102, 102, 102));
                                                    jLabel40.setText("Especifica de Detalle");

                                                    jLabel46.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel46.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel46.setText("Codigo");

                                                    jLabel47.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel47.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel47.setText("Especifica");

                                                    jLabel48.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel48.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel48.setText("Descripcion");

                                                    txtcodEd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtcodEd.setEnabled(false);
                                                    txtcodEd.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtcodEdActionPerformed(evt);
                                                        }
                                                    });

                                                    txtdesEd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

                                                    txtespe2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtespe2.setEnabled(false);
                                                    txtespe2.addMouseListener(new java.awt.event.MouseAdapter() {
                                                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                            txtespe2MouseClicked(evt);
                                                        }
                                                    });
                                                    txtespe2.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtespe2ActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel49.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel49.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel49.setText("Especifica de Detalle");

                                                    txted1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txted1.setEnabled(false);
                                                    txted1.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txted1ActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel50.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    jLabel50.setForeground(new java.awt.Color(51, 51, 51));
                                                    jLabel50.setText(".");

                                                    txtespedet.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                                    txtespedet.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            txtespedetActionPerformed(evt);
                                                        }
                                                    });

                                                    b6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                                                    b6.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            b6ActionPerformed(evt);
                                                        }
                                                    });

                                                    lblCT5.setForeground(new java.awt.Color(255, 255, 255));
                                                    lblCT5.setText("jLabel2");

                                                    especificadetalle.setForeground(new java.awt.Color(255, 255, 255));
                                                    especificadetalle.setText("jLabel2");

                                                    javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                                                    jPanel16.setLayout(jPanel16Layout);
                                                    jPanel16Layout.setHorizontalGroup(
                                                        jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel16Layout.createSequentialGroup()
                                                            .addGap(10, 10, 10)
                                                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel40)
                                                                .addGroup(jPanel16Layout.createSequentialGroup()
                                                                    .addComponent(jLabel48)
                                                                    .addGap(86, 86, 86)
                                                                    .addComponent(txtdesEd, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel16Layout.createSequentialGroup()
                                                                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel47)
                                                                        .addComponent(jLabel49)
                                                                        .addComponent(jLabel46))
                                                                    .addGap(36, 36, 36)
                                                                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(txtespe2)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                                                            .addComponent(txted1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(jLabel50)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                            .addComponent(txtespedet, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(txtcodEd))
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(110, 110, 110)
                                                                    .addComponent(lblCT5)
                                                                    .addGap(47, 47, 47)
                                                                    .addComponent(especificadetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addGap(143, 143, 143))
                                                    );
                                                    jPanel16Layout.setVerticalGroup(
                                                        jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel16Layout.createSequentialGroup()
                                                            .addGap(11, 11, 11)
                                                            .addComponent(jLabel40)
                                                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel16Layout.createSequentialGroup()
                                                                    .addGap(14, 14, 14)
                                                                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jPanel16Layout.createSequentialGroup()
                                                                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(txtcodEd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel46))
                                                                            .addGap(18, 18, 18)
                                                                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                    .addComponent(txtespe2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                    .addComponent(jLabel47))
                                                                                .addComponent(b6)))
                                                                        .addComponent(lblCT5))
                                                                    .addGap(18, 18, 18)
                                                                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel49)
                                                                        .addComponent(txted1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel50)
                                                                        .addComponent(txtespedet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addGap(18, 18, 18)
                                                                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel16Layout.createSequentialGroup()
                                                                            .addComponent(jLabel48)
                                                                            .addGap(0, 0, Short.MAX_VALUE))
                                                                        .addComponent(txtdesEd, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)))
                                                                .addGroup(jPanel16Layout.createSequentialGroup()
                                                                    .addGap(73, 73, 73)
                                                                    .addComponent(especificadetalle)))
                                                            .addContainerGap())
                                                    );

                                                    jTabbedPane1.addTab("C2", jPanel16);

                                                    jPanel6.setBackground(new java.awt.Color(255, 255, 255));

                                                    bc6.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc6.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    bc6.setForeground(new java.awt.Color(102, 102, 102));
                                                    bc6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Page Overview 2-50.png"))); // NOI18N
                                                    bc6.setMnemonic('N');
                                                    bc6.setText("Tipo de Transaccion");
                                                    bc6.setContentAreaFilled(false);
                                                    bc6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc6.setFocusPainted(false);
                                                    bc6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc6.setIconTextGap(30);
                                                    bc6.setVerifyInputWhenFocusTarget(false);
                                                    bc6.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc6ActionPerformed(evt);
                                                        }
                                                    });

                                                    bc7.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc7.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    bc7.setForeground(new java.awt.Color(102, 102, 102));
                                                    bc7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Generic Book File Type-50.png"))); // NOI18N
                                                    bc7.setMnemonic('N');
                                                    bc7.setText("Generica                ");
                                                    bc7.setContentAreaFilled(false);
                                                    bc7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc7.setFocusPainted(false);
                                                    bc7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc7.setIconTextGap(30);
                                                    bc7.setVerifyInputWhenFocusTarget(false);
                                                    bc7.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc7ActionPerformed(evt);
                                                        }
                                                    });

                                                    bc8.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc8.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    bc8.setForeground(new java.awt.Color(102, 102, 102));
                                                    bc8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Page Overview 4-50.png"))); // NOI18N
                                                    bc8.setMnemonic('N');
                                                    bc8.setText("Sub Generica Detalle");
                                                    bc8.setContentAreaFilled(false);
                                                    bc8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc8.setFocusPainted(false);
                                                    bc8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc8.setIconTextGap(30);
                                                    bc8.setVerifyInputWhenFocusTarget(false);
                                                    bc8.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc8ActionPerformed(evt);
                                                        }
                                                    });

                                                    bc9.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc9.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    bc9.setForeground(new java.awt.Color(102, 102, 102));
                                                    bc9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documents-50.png"))); // NOI18N
                                                    bc9.setMnemonic('N');
                                                    bc9.setText("Sub Generica            ");
                                                    bc9.setContentAreaFilled(false);
                                                    bc9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc9.setFocusPainted(false);
                                                    bc9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc9.setIconTextGap(30);
                                                    bc9.setVerifyInputWhenFocusTarget(false);
                                                    bc9.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc9ActionPerformed(evt);
                                                        }
                                                    });

                                                    bc10.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc10.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    bc10.setForeground(new java.awt.Color(102, 102, 102));
                                                    bc10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Page Overview 3-50.png"))); // NOI18N
                                                    bc10.setMnemonic('N');
                                                    bc10.setText("Especifica                  ");
                                                    bc10.setContentAreaFilled(false);
                                                    bc10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc10.setFocusPainted(false);
                                                    bc10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc10.setIconTextGap(30);
                                                    bc10.setVerifyInputWhenFocusTarget(false);
                                                    bc10.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc10ActionPerformed(evt);
                                                        }
                                                    });

                                                    bc11.setBackground(new java.awt.Color(102, 102, 102));
                                                    bc11.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                                                    bc11.setForeground(new java.awt.Color(102, 102, 102));
                                                    bc11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Align Right Filled-50.png"))); // NOI18N
                                                    bc11.setMnemonic('N');
                                                    bc11.setText("Especifica de Detalle");
                                                    bc11.setContentAreaFilled(false);
                                                    bc11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                                    bc11.setFocusPainted(false);
                                                    bc11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                                    bc11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                                    bc11.setIconTextGap(30);
                                                    bc11.setVerifyInputWhenFocusTarget(false);
                                                    bc11.addActionListener(new java.awt.event.ActionListener() {
                                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                            bc11ActionPerformed(evt);
                                                        }
                                                    });

                                                    jLabel52.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                                    jLabel52.setForeground(new java.awt.Color(102, 102, 102));
                                                    jLabel52.setText("Buscar cuentas");

                                                    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                                                    jPanel6.setLayout(jPanel6Layout);
                                                    jPanel6Layout.setHorizontalGroup(
                                                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                            .addContainerGap()
                                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                                            .addComponent(bc6, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(bc7, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                                            .addComponent(bc8)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(bc10, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(bc11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(bc9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                                .addGroup(jPanel6Layout.createSequentialGroup()
                                                                    .addComponent(jLabel52)
                                                                    .addGap(0, 0, Short.MAX_VALUE))))
                                                    );
                                                    jPanel6Layout.setVerticalGroup(
                                                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                            .addContainerGap(20, Short.MAX_VALUE)
                                                            .addComponent(jLabel52)
                                                            .addGap(18, 18, 18)
                                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(bc6)
                                                                .addComponent(bc7)
                                                                .addComponent(bc9))
                                                            .addGap(19, 19, 19)
                                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(bc8)
                                                                .addComponent(bc10)
                                                                .addComponent(bc11))
                                                            .addGap(34, 34, 34))
                                                    );

                                                    jTabbedPane1.addTab("B", jPanel6);

                                                    getContentPane().add(jTabbedPane1);
                                                    jTabbedPane1.setBounds(0, 126, 740, 260);

                                                    pack();
                                                }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        jTabbedPane1.setSelectedIndex(0);
         btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
       //CUENTA 1
        txttipoT.setEnabled(true);
        txtdesT.setEnabled(true); 
        txtdesT.setRequestFocusEnabled(true);
        //CUENTA2
        txtgen.setEnabled(true);
        txtdesG.setEnabled(true); 
        txtgen.setRequestFocusEnabled(true);
        //CUENTA 3
        txtsub.setEnabled(true);
        txtdesSubG.setEnabled(true);
        txtsub.setRequestFocusEnabled(true);
       //CUENTA 4
        txtgend.setEnabled(true);
        txtdesGd.setEnabled(true);
        //CUENTA 5
        txtespe.setEnabled(true);
        txtdesE.setEnabled(true);
        //Cuenta 6
        txtespedet.setEnabled(true);
        txtdesEd.setEnabled(true);        
        
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
 
        
        //CUENTA 1    
        if(tg==1){ 
            GuardarCta1(); 
        }else if(tg==11){
            ModificarCta1();  
        } 
        
        //CUENTA 2
        if(tg==2){
             GuardarCta2(); 
        }else   if(tg==22){
            ModificarCta2();  
            
        } 
        
        //CUENTA 3
        if(tg==3){
             GuardarCta3(); 
        }else   if(tg==33){
            ModificarCta3(); 
        }
        
        //CUENTA 4    
        if(tg==4){ 
            GuardarCta4();
        }else   if(tg==44){
            ModificarCta4(); 
            
        } 
        
        //CUENTA 5
        if(tg==5){
            GuardarCta5();
        }else   if(tg==55){
            ModificarCta5();
        } 
        
        //CUENTA 6
        if(tg==6){
            GuardarCta6();
        }else   if(tg==66){
            ModificarCta6();
            
        }
        
       
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed

    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
       jTabbedPane1.setSelectedIndex(7);
         btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);   
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btneliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneliminar1ActionPerformed

    private void btnbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1ActionPerformed
        BuscarCta1_CTA2();
    }//GEN-LAST:event_btnbuscar1ActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        BuscarCta1_CTA2();
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void tb_GrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GrupoMouseClicked
      //CUENTA2
        int fila=tb_Grupo.getSelectedRow();
        if(evt.getClickCount()==2){
            LlenarTransCT2.dispose();
            txttrans1.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            txttrans.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            lblCT1.setText(String.valueOf(tb_Grupo.getValueAt(fila, 2)));
        }
        txttrans.setEnabled(false);
        txttrans1.setEnabled(false);
        txtgen.requestFocus();
    }//GEN-LAST:event_tb_GrupoMouseClicked

    private void tb_GrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_GrupoKeyPressed
        // CUENTA 2
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo.getSelectedRow();
            LlenarTransCT2.dispose();
            txttrans1.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            txttrans.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            lblCT1.setText(String.valueOf(tb_Grupo.getValueAt(fila, 2)));
        }
        txtgen.requestFocus();
    }//GEN-LAST:event_tb_GrupoKeyPressed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B1ActionPerformed
     
        LlenarTransCT2.setVisible(true);
    }//GEN-LAST:event_B1ActionPerformed

    private void txtgenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgenActionPerformed

    private void txttrans1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttrans1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttrans1ActionPerformed

    private void txttransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttransActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttransActionPerformed

    private void txttransMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttransMouseClicked
       
     
    }//GEN-LAST:event_txttransMouseClicked

    private void txtcodGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodGActionPerformed

    private void txttipoTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttipoTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttipoTActionPerformed

    private void txtcodTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodTActionPerformed

    private void bc5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc5ActionPerformed
        tg=6;
        jTabbedPane1.setSelectedIndex(6);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        txtcodEd.setText(cn6.idCTA6());
        txtespedet.setEnabled(true);
        txtdesEd.setEnabled(true); 
        b6.setVisible(true);
     
        txtespe2.setText("");
        txted1.setText("");
        txtespedet.setText("");
        txtdesEd.setText("");

    }//GEN-LAST:event_bc5ActionPerformed

    private void bc4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc4ActionPerformed
        tg=5;
        jTabbedPane1.setSelectedIndex(5);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        txtcodE.setText(cn5.idCTA5());
        //CUENTA 5
        txtespe.setEnabled(true);
        txtdesE.setEnabled(true);
        b4.setVisible(true);
        txtsubgd.setText("");
        txtsgd.setText("");
        txtespe.setText("");
        txtdesE.setText("");
    }//GEN-LAST:event_bc4ActionPerformed

    private void bc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc3ActionPerformed
        tg=3;
        jTabbedPane1.setSelectedIndex(3);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        txtsub.setEnabled(true);
        txtdesSubG.setEnabled(true);
        b2.setVisible(true);
        txtcodsubgen.setText(cn3.idCTA3());
        
        txtgen2.setText("");
        txtgen3.setText("");
        txtsub.setText("");
        txtdesSubG.setText("");
    }//GEN-LAST:event_bc3ActionPerformed

    private void bc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc2ActionPerformed
        tg=4;
        jTabbedPane1.setSelectedIndex(4);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        txtgend.setEnabled(true);
        txtdesGd.setEnabled(true);
        B3.setVisible(true);
        txtcodGD.setText(cn4.idCTA4());
        
        txtsubgen.setText("");
        txtsuggend.setText("");
        txtgend.setText("");
        txtdesGd.setText("");
    }//GEN-LAST:event_bc2ActionPerformed

    private void bc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc1ActionPerformed
        tg=2;
        jTabbedPane1.setSelectedIndex(2);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        B1.setVisible(true);
        txtcodG.setText(cn2.idCTA2());
        
        txttrans.setText("");
        txttrans1.setText("");
        txtgen.setText("");
        txtdesG.setText("");

    }//GEN-LAST:event_bc1ActionPerformed

    private void bcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bcActionPerformed
        tg=1;
        jTabbedPane1.setSelectedIndex(1);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        txtcodT.setText(cnn.idCTA1());
        txttipoT.setEnabled(true);
        txtdesT.setEnabled(true);

      
        txttipoT.requestFocus();
   
        txttipoT.setText("");
        txtdesT.setText("");
    }//GEN-LAST:event_bcActionPerformed

    private void txtcodsubgenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodsubgenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodsubgenActionPerformed

    private void txtgen2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtgen2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgen2MouseClicked

    private void txtgen2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgen2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgen2ActionPerformed

    private void txtgen3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgen3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgen3ActionPerformed

    private void txtsubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsubActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed

     
        LlenarGenericaCT3.setVisible(true);         
                
    }//GEN-LAST:event_b2ActionPerformed

    private void btnbuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar2ActionPerformed

    private void txtBuscar1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar1CaretUpdate
          BuscarCta2_CTA3();
    }//GEN-LAST:event_txtBuscar1CaretUpdate

    private void txtBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar1ActionPerformed

    private void tb_Grupo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MouseClicked
       //CUENTA3
        int fila=tb_Grupo1.getSelectedRow();
        if(evt.getClickCount()==2){
            LlenarGenericaCT3.dispose();
            txtgen2.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            txtgen3.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            lblCT2.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
        }
        txtgen2.setEnabled(false);
        txtgen3.setEnabled(false);
        txtsub.requestFocus();
    }//GEN-LAST:event_tb_Grupo1MouseClicked

    private void tb_Grupo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo1KeyPressed
       // CUENTA 3
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo1.getSelectedRow();

            LlenarGenericaCT3.dispose();
            txtgen2.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            txtgen3.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            lblCT2.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
        }
        txtgen2.setEnabled(false);
        txtgen3.setEnabled(false);
        txtsub.requestFocus();
    }//GEN-LAST:event_tb_Grupo1KeyPressed

    private void txtcodGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodGDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodGDActionPerformed

    private void txtsubgenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsubgenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubgenMouseClicked

    private void txtsubgenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsubgenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubgenActionPerformed

    private void txtsuggendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsuggendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsuggendActionPerformed

    private void txtgendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgendActionPerformed

    private void B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B3ActionPerformed
        LlenarSubGenericaCT4.setVisible(true); 
    }//GEN-LAST:event_B3ActionPerformed

    private void btnbuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar3ActionPerformed

    private void txtBuscar2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar2CaretUpdate
       BuscarCta3_CTA4();
    }//GEN-LAST:event_txtBuscar2CaretUpdate

    private void txtBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar2ActionPerformed

    private void tb_Grupo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo2MouseClicked
          //CUENTA3
        int fila=tb_Grupo2.getSelectedRow();
        if(evt.getClickCount()==2){
            LlenarSubGenericaCT4.dispose();
            txtsubgen.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 0)));
            txtsuggend.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 0)));
            lblCT3.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 2)));
        }
        txtsubgen.setEnabled(false);
        txtsuggend.setEnabled(false);
        txtgend.requestFocus();
    }//GEN-LAST:event_tb_Grupo2MouseClicked

    private void tb_Grupo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo2KeyPressed
     // CUENTA 3
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo2.getSelectedRow();

            LlenarSubGenericaCT4.dispose();
            txtsubgen.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 0)));
            txtsuggend.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 0)));
            lblCT3.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 2)));
        }
        txtsubgen.setEnabled(false);
        txtsuggend.setEnabled(false);
        txtgend.requestFocus();
    }//GEN-LAST:event_tb_Grupo2KeyPressed

    private void txtcodEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodEActionPerformed

    private void txtsubgdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsubgdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubgdMouseClicked

    private void txtsubgdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsubgdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubgdActionPerformed

    private void txtsgdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsgdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsgdActionPerformed

    private void txtespeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtespeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtespeActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
       LlenarSubGenericaDetalleCT5.setVisible(true); 
    }//GEN-LAST:event_b4ActionPerformed

    private void btnbuscar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar4ActionPerformed

    private void txtBuscar3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar3CaretUpdate
        BuscarCta4_CTA5();
    }//GEN-LAST:event_txtBuscar3CaretUpdate

    private void txtBuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar3ActionPerformed

    private void tb_Grupo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo3MouseClicked
           //CUENTA3
        int fila=tb_Grupo3.getSelectedRow();
        if(evt.getClickCount()==2){
  
            LlenarSubGenericaDetalleCT5.dispose();
            txtsubgd.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 0)));
            txtsgd.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 0)));
            lblCT4.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 2)));
        }
        txtdesE.requestFocus();
    }//GEN-LAST:event_tb_Grupo3MouseClicked

    private void tb_Grupo3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo3KeyPressed
      // CUENTA 4
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo3.getSelectedRow();

            LlenarSubGenericaDetalleCT5.dispose();
            txtsubgd.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 0)));
            txtsgd.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 0)));
            lblCT4.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 2)));
        }
        txtdesE.requestFocus();
    }//GEN-LAST:event_tb_Grupo3KeyPressed

    private void txtcodEdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodEdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodEdActionPerformed

    private void txtespe2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtespe2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtespe2MouseClicked

    private void txtespe2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtespe2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtespe2ActionPerformed

    private void txted1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txted1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txted1ActionPerformed

    private void txtespedetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtespedetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtespedetActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
          LlenarEspecifica.setVisible(true); 
    }//GEN-LAST:event_b6ActionPerformed

    private void bc6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc6ActionPerformed
        tg=1;
       
        LlenarTransCT1EDITAR.setVisible(true); 
          
    }//GEN-LAST:event_bc6ActionPerformed

    private void bc7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc7ActionPerformed
        LlenarGenericaCT2EDITAR.setVisible(true);
       
    }//GEN-LAST:event_bc7ActionPerformed

    private void bc8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc8ActionPerformed
       SubGenericaDetalleEDITAR.setVisible(true);
    }//GEN-LAST:event_bc8ActionPerformed

    private void bc9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc9ActionPerformed
        LlenarSubGenericaEDITAR.setVisible(true);
    }//GEN-LAST:event_bc9ActionPerformed

    private void bc10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc10ActionPerformed
          EspecificaEditar.setVisible(true);
    }//GEN-LAST:event_bc10ActionPerformed

    private void bc11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bc11ActionPerformed
      EspecificaDetalleEDITAR.setVisible(true);
    }//GEN-LAST:event_bc11ActionPerformed

    private void btnbuscar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar5ActionPerformed

    private void txtBuscar4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar4CaretUpdate
         BuscarCta5_CTA6();
    }//GEN-LAST:event_txtBuscar4CaretUpdate

    private void txtBuscar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar4ActionPerformed

    private void tb_Grupo4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo4MouseClicked
       //CUENTA5
        int fila=tb_Grupo4.getSelectedRow();
        if(evt.getClickCount()==2){
         LlenarEspecifica.dispose();
            txtespe2.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 0)));
            txted1.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 0)));
            lblCT5.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 2)));
        }
        
        txtespedet.requestFocus();
    }//GEN-LAST:event_tb_Grupo4MouseClicked

    private void tb_Grupo4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo4KeyPressed
            // CUENTA 5
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo4.getSelectedRow();

            LlenarEspecifica.dispose();
            txtespe2.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 0)));
            txted1.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 0)));
            lblCT5.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 2)));
        }
        
        txtespedet.requestFocus();
    }//GEN-LAST:event_tb_Grupo4KeyPressed

    private void btnbuscar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar6ActionPerformed

    private void txtBuscar5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar5CaretUpdate
      BuscarCta1_CTA2EDITAR();
    }//GEN-LAST:event_txtBuscar5CaretUpdate

    private void txtBuscar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar5ActionPerformed

    private void tb_Grupo5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo5MouseClicked
         //CUENTA1 EDITAR
        int fila=tb_Grupo5.getSelectedRow();
        if(evt.getClickCount()==2){
            LlenarTransCT1EDITAR.dispose();
            txtcodT.setText(String.valueOf(tb_Grupo5.getValueAt(fila, 2)));
      
            txttipoT.setText(String.valueOf(tb_Grupo5.getValueAt(fila, 0)));
            txtdesT.setText(String.valueOf(tb_Grupo5.getValueAt(fila, 1)));
        }
         
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
         jTabbedPane1.setSelectedIndex(1);  
         tg=11;
         txttipoT.setEnabled(false);
         txtdesT.setEnabled(false);
    }//GEN-LAST:event_tb_Grupo5MouseClicked

    private void tb_Grupo5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo5KeyPressed
        // CUENTA 1 EDITAR
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo5.getSelectedRow();

            LlenarTransCT1EDITAR.dispose();
            txtcodT.setText(String.valueOf(tb_Grupo5.getValueAt(fila, 2)));
         
            txttipoT.setText(String.valueOf(tb_Grupo5.getValueAt(fila, 0)));
            txtdesT.setText(String.valueOf(tb_Grupo5.getValueAt(fila, 1)));
        }
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        
         jTabbedPane1.setSelectedIndex(1);  
         tg=11;
         txttipoT.setEnabled(false);
         txtdesT.setEnabled(false);
       
    }//GEN-LAST:event_tb_Grupo5KeyPressed

    private void btnbuscar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar7ActionPerformed

    private void txtBuscar6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar6CaretUpdate
        BuscarCta2EDITAR();
    }//GEN-LAST:event_txtBuscar6CaretUpdate

    private void txtBuscar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar6ActionPerformed

    private void tb_Grupo6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo6MouseClicked
            //CUENTA2 EDITAR
        int fila=tb_Grupo6.getSelectedRow();
        if(evt.getClickCount()==2){
        LlenarGenericaCT2EDITAR.dispose();
            txtcodG.setText(String.valueOf(tb_Grupo6.getValueAt(fila, 2)));
            cuentagenerica.setText(String.valueOf(tb_Grupo6.getValueAt(fila, 0)));
            txtdesG.setText(String.valueOf(tb_Grupo6.getValueAt(fila, 1)));
            lblCT1.setText(String.valueOf(tb_Grupo6.getValueAt(fila, 3)));
        }
          try {
            String cuenta2=cuentagenerica.getText();
            String palabra=String.valueOf(cuenta2.charAt(2));
            
            String cuenta1=cuentagenerica.getText();
            String palabra2=String.valueOf(cuenta2.charAt(0));
 
            txtgen.setText(palabra);
            txttrans.setText(palabra2);
            txttrans1.setText(palabra2);
            
        } catch (Exception e) {
        }
          
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        
         jTabbedPane1.setSelectedIndex(2);  
         tg=22;
         txtgen.setEnabled(false);
         txtdesG.setEnabled(false);
         B1.setVisible(false);
    }//GEN-LAST:event_tb_Grupo6MouseClicked

    private void tb_Grupo6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo6KeyPressed
        // CUENTA 2 EDITAR
        
        
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo6.getSelectedRow();

            LlenarGenericaCT2EDITAR.dispose();
            txtcodG.setText(String.valueOf(tb_Grupo6.getValueAt(fila, 2)));
            cuentagenerica.setText(String.valueOf(tb_Grupo6.getValueAt(fila, 0)));
            txtdesG.setText(String.valueOf(tb_Grupo6.getValueAt(fila, 1)));
            lblCT1.setText(String.valueOf(tb_Grupo6.getValueAt(fila, 3)));
        }
        try {
            String cuenta2=cuentagenerica.getText();
            String palabra=String.valueOf(cuenta2.charAt(2));
            
            String cuenta1=cuentagenerica.getText();
            String palabra2=String.valueOf(cuenta2.charAt(0));
 
            txtgen.setText(palabra);
            txttrans.setText(palabra2);
            txttrans1.setText(palabra2);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        
         jTabbedPane1.setSelectedIndex(2);  
         tg=22;
         txtgen.setEnabled(false);
         txtdesG.setEnabled(false);
         B1.setVisible(false);
    }//GEN-LAST:event_tb_Grupo6KeyPressed

    private void btnbuscar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar8ActionPerformed

    private void txtBuscar7CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar7CaretUpdate
       BuscarCta3EDITAR();
    }//GEN-LAST:event_txtBuscar7CaretUpdate

    private void txtBuscar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar7ActionPerformed

    private void tb_Grupo7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo7MouseClicked
               //CUENTA3 EDITAR
        int fila=tb_Grupo7.getSelectedRow();
        if(evt.getClickCount()==2){
        LlenarSubGenericaEDITAR.dispose();
            txtcodsubgen.setText(String.valueOf(tb_Grupo7.getValueAt(fila, 2)));
            sugenericacadena.setText(String.valueOf(tb_Grupo7.getValueAt(fila, 0)));
            txtdesSubG.setText(String.valueOf(tb_Grupo7.getValueAt(fila, 1)));
            lblCT2.setText(String.valueOf(tb_Grupo7.getValueAt(fila, 3)));
        }
        try {
            String cuenta3=sugenericacadena.getText();
            String palabra=String.valueOf(cuenta3.charAt(4));

            String palabra3=String.valueOf(cuenta3.charAt(0)+""+cuenta3.charAt(1)+""+cuenta3.charAt(2));
 
            txtsub.setText(palabra);
            txtgen2.setText(palabra3);
            txtgen3.setText(palabra3);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        
         jTabbedPane1.setSelectedIndex(3);  
         tg=33;
         txtsub.setEnabled(false);
         txtdesSubG.setEnabled(false);
         b2.setVisible(false);
    }//GEN-LAST:event_tb_Grupo7MouseClicked

    private void tb_Grupo7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo7KeyPressed
          // CUENTA 3 EDITAR
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo7.getSelectedRow();

         LlenarSubGenericaEDITAR.dispose();
            txtcodsubgen.setText(String.valueOf(tb_Grupo7.getValueAt(fila, 2)));
            sugenericacadena.setText(String.valueOf(tb_Grupo7.getValueAt(fila, 0)));
            txtdesSubG.setText(String.valueOf(tb_Grupo7.getValueAt(fila, 1)));
            lblCT2.setText(String.valueOf(tb_Grupo7.getValueAt(fila, 3)));
        }
        try {
            String cuenta3=sugenericacadena.getText();
            String palabra=String.valueOf(cuenta3.charAt(4));
            
            String palabra2=String.valueOf(cuenta3.charAt(0)+""+cuenta3.charAt(1)+""+cuenta3.charAt(2));
 
            txtsub.setText(palabra);
            txtgen2.setText(palabra2);
            txtgen3.setText(palabra2);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        
         jTabbedPane1.setSelectedIndex(3);  
         tg=33;
         txtsub.setEnabled(false);
         txtdesSubG.setEnabled(false);
         b2.setVisible(false);
    }//GEN-LAST:event_tb_Grupo7KeyPressed

    private void btnbuscar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar9ActionPerformed

    private void txtBuscar8CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar8CaretUpdate
        BuscarCta4EDITAR();
    }//GEN-LAST:event_txtBuscar8CaretUpdate

    private void txtBuscar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar8ActionPerformed

    private void tb_Grupo8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo8MouseClicked
              //CUENTA4 EDITAR
        int fila=tb_Grupo8.getSelectedRow();
        if(evt.getClickCount()==2){
          SubGenericaDetalleEDITAR.dispose();
            txtcodGD.setText(String.valueOf(tb_Grupo8.getValueAt(fila, 2)));
            Subgenericadetalle.setText(String.valueOf(tb_Grupo8.getValueAt(fila, 0)));
            txtdesGd.setText(String.valueOf(tb_Grupo8.getValueAt(fila, 1)));
            lblCT3.setText(String.valueOf(tb_Grupo8.getValueAt(fila, 3)));
        }
        try {
            String cuenta4=Subgenericadetalle.getText();
            String palabra=String.valueOf(cuenta4.charAt(6));
            String palabra4=String.valueOf(cuenta4.charAt(0)+""+cuenta4.charAt(1)+""+cuenta4.charAt(2)+""+cuenta4.charAt(3)+""+cuenta4.charAt(4));
 
            txtgend.setText(palabra);
            txtsubgen.setText(palabra4);
            txtsuggend.setText(palabra4);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        
         jTabbedPane1.setSelectedIndex(4);  
         tg=44;
         txtgend.setEnabled(false);
         txtdesGd.setEnabled(false);
         B3.setVisible(false);
    }//GEN-LAST:event_tb_Grupo8MouseClicked

    private void tb_Grupo8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo8KeyPressed
        // CUENTA 4 EDITAR
        
        
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo8.getSelectedRow();

            SubGenericaDetalleEDITAR.dispose();
            txtcodGD.setText(String.valueOf(tb_Grupo8.getValueAt(fila, 2)));
            Subgenericadetalle.setText(String.valueOf(tb_Grupo8.getValueAt(fila, 0)));
            txtdesGd.setText(String.valueOf(tb_Grupo8.getValueAt(fila, 1)));
            lblCT3.setText(String.valueOf(tb_Grupo8.getValueAt(fila, 3)));
        }
        try {
            String cuenta4=Subgenericadetalle.getText();
            String palabra=String.valueOf(cuenta4.charAt(6));
            String palabra4=String.valueOf(cuenta4.charAt(0)+""+cuenta4.charAt(1)+""+cuenta4.charAt(2)+""+cuenta4.charAt(3)+""+cuenta4.charAt(4));
 
            txtgend.setText(palabra);
            txtsubgen.setText(palabra4);
            txtsuggend.setText(palabra4);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        
         jTabbedPane1.setSelectedIndex(4);  
         tg=44;
         txtgend.setEnabled(false);
         txtdesGd.setEnabled(false);
         B3.setVisible(false);
    }//GEN-LAST:event_tb_Grupo8KeyPressed

    private void btnbuscar10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar10ActionPerformed

    private void txtBuscar9CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar9CaretUpdate
       BuscarCta5EDITAR();
    }//GEN-LAST:event_txtBuscar9CaretUpdate

    private void txtBuscar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar9ActionPerformed

    private void tb_Grupo9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo9MouseClicked
                //CUENTA5 EDITAR
        int fila=tb_Grupo9.getSelectedRow();
        if(evt.getClickCount()==2){
            EspecificaEditar.dispose();
            txtcodE.setText(String.valueOf(tb_Grupo9.getValueAt(fila, 2)));
            Especifica.setText(String.valueOf(tb_Grupo9.getValueAt(fila, 0)));
            txtdesE.setText(String.valueOf(tb_Grupo9.getValueAt(fila, 1)));
            lblCT4.setText(String.valueOf(tb_Grupo9.getValueAt(fila, 3)));
        }
        try {
            String cuenta5=Especifica.getText();
            String palabr51=String.valueOf(cuenta5.charAt(8));
            String palabra5=String.valueOf(cuenta5.charAt(0)+""+cuenta5.charAt(1)+""+cuenta5.charAt(2)+""+cuenta5.charAt(3)+""+cuenta5.charAt(4)+""+cuenta5.charAt(5)+""+cuenta5.charAt(6));
 
            txtespe.setText(palabr51);
            txtsgd.setText(palabra5);
            txtsubgd.setText(palabra5);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        
         jTabbedPane1.setSelectedIndex(5);  
         tg=55;
         txtespe.setEnabled(false);
         txtdesE.setEnabled(false);
         b4.setVisible(false);
    }//GEN-LAST:event_tb_Grupo9MouseClicked

    private void tb_Grupo9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo9KeyPressed
           // CUENTA 5 EDITAR
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo9.getSelectedRow();

            EspecificaEditar.dispose();
            txtcodE.setText(String.valueOf(tb_Grupo9.getValueAt(fila, 2)));
            Especifica.setText(String.valueOf(tb_Grupo9.getValueAt(fila, 0)));
            txtdesE.setText(String.valueOf(tb_Grupo9.getValueAt(fila, 1)));
            lblCT4.setText(String.valueOf(tb_Grupo9.getValueAt(fila, 3)));
        }
        try {
            String cuenta5=Especifica.getText();
            String palabr51=String.valueOf(cuenta5.charAt(8));
            String palabra5=String.valueOf(cuenta5.charAt(0)+""+cuenta5.charAt(1)+""+cuenta5.charAt(2)+""+cuenta5.charAt(3)+""+cuenta5.charAt(4)+""+cuenta5.charAt(5)+""+cuenta5.charAt(6));
 
            txtespe.setText(palabr51);
            txtsgd.setText(palabra5);
            txtsubgd.setText(palabra5);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        
         jTabbedPane1.setSelectedIndex(5);  
         tg=55;
         txtespe.setEnabled(false);
         txtdesE.setEnabled(false);
         b4.setVisible(false);
    }//GEN-LAST:event_tb_Grupo9KeyPressed

    private void btnbuscar11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar11ActionPerformed

    private void txtBuscar10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar10CaretUpdate
        BuscarCta6EDITAR();
    }//GEN-LAST:event_txtBuscar10CaretUpdate

    private void txtBuscar10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar10ActionPerformed

    private void tb_Grupo10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo10MouseClicked
                //CUENTA6 EDITAR
        int fila=tb_Grupo10.getSelectedRow();
        if(evt.getClickCount()==2){
           EspecificaDetalleEDITAR.dispose();
            txtcodEd.setText(String.valueOf(tb_Grupo10.getValueAt(fila, 2)));
            especificadetalle.setText(String.valueOf(tb_Grupo10.getValueAt(fila, 0)));
            txtdesEd.setText(String.valueOf(tb_Grupo10.getValueAt(fila, 1)));
            lblCT5.setText(String.valueOf(tb_Grupo10.getValueAt(fila, 3)));
        }
        try {
            String cuenta6=especificadetalle.getText();
            String palabra61=String.valueOf(cuenta6.charAt(10));
            String palabra6=String.valueOf(cuenta6.charAt(0)+""+cuenta6.charAt(1)+""+cuenta6.charAt(2)+""+cuenta6.charAt(3)+""+cuenta6.charAt(4)+""+cuenta6.charAt(5)+""+cuenta6.charAt(6)+""+cuenta6.charAt(7)+""+cuenta6.charAt(8));
 
            txtespe2.setText(palabra6);
            txted1.setText(palabra6);
            txtespedet.setText(palabra61);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        
        
         tg=66;
         txtespedet.setEnabled(false);
         txtdesEd.setEnabled(false);
         b6.setVisible(false);
          jTabbedPane1.setSelectedIndex(6);  
    }//GEN-LAST:event_tb_Grupo10MouseClicked

    private void tb_Grupo10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo10KeyPressed
           // CUENTA 6 EDITAR
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo10.getSelectedRow();

            EspecificaDetalleEDITAR.dispose();
            txtcodEd.setText(String.valueOf(tb_Grupo10.getValueAt(fila, 2)));
            especificadetalle.setText(String.valueOf(tb_Grupo10.getValueAt(fila, 0)));
            txtdesEd.setText(String.valueOf(tb_Grupo10.getValueAt(fila, 1)));
            lblCT5.setText(String.valueOf(tb_Grupo10.getValueAt(fila, 3)));
        }
        try {
            String cuenta6=especificadetalle.getText();
            String palabra61=String.valueOf(cuenta6.charAt(10));
            String palabra6=String.valueOf(cuenta6.charAt(0)+""+cuenta6.charAt(1)+""+cuenta6.charAt(2)+""+cuenta6.charAt(3)+""+cuenta6.charAt(4)+""+cuenta6.charAt(5)+""+cuenta6.charAt(6)+""+cuenta6.charAt(7)+""+cuenta6.charAt(8));
 
            txtespe2.setText(palabra6);
            txted1.setText(palabra6);
            txtespedet.setText(palabra61);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        
         
         tg=66;
         txtespedet.setEnabled(false);
         txtdesEd.setEnabled(false);
         b6.setVisible(false);
         jTabbedPane1.setSelectedIndex(6);  
    }//GEN-LAST:event_tb_Grupo10KeyPressed

    private void tb_Grupo10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo10MousePressed
                  //CUENTA6 EDITAR
        int fila=tb_Grupo10.getSelectedRow();
        if(evt.getClickCount()==2){
           EspecificaDetalleEDITAR.dispose();
            txtcodEd.setText(String.valueOf(tb_Grupo10.getValueAt(fila, 2)));
            especificadetalle.setText(String.valueOf(tb_Grupo10.getValueAt(fila, 0)));
            txtdesEd.setText(String.valueOf(tb_Grupo10.getValueAt(fila, 1)));
            lblCT5.setText(String.valueOf(tb_Grupo10.getValueAt(fila, 3)));
        }
        try {
            String cuenta6=especificadetalle.getText();
            String palabra61=String.valueOf(cuenta6.charAt(10));
            String palabra6=String.valueOf(cuenta6.charAt(0)+""+cuenta6.charAt(1)+""+cuenta6.charAt(2)+""+cuenta6.charAt(3)+""+cuenta6.charAt(4)+""+cuenta6.charAt(5)+""+cuenta6.charAt(6)+""+cuenta6.charAt(7)+""+cuenta6.charAt(8));
 
            txtespe2.setText(palabra6);
            txted1.setText(palabra6);
            txtespedet.setText(palabra61);
            
        } catch (Exception e) {
        } 
           
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        
        
         tg=66;
         txtespedet.setEnabled(false);
         txtdesEd.setEnabled(false);
         b6.setVisible(false);
    }//GEN-LAST:event_tb_Grupo10MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Caja_Transaccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Transaccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Transaccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Transaccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Transaccion().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B1;
    private javax.swing.JButton B3;
    private javax.swing.JLabel Especifica;
    private javax.swing.JDialog EspecificaDetalleEDITAR;
    private javax.swing.JDialog EspecificaEditar;
    private javax.swing.JDialog LlenarEspecifica;
    private javax.swing.JDialog LlenarGenericaCT2EDITAR;
    private javax.swing.JDialog LlenarGenericaCT3;
    private javax.swing.JDialog LlenarSubGenericaCT4;
    private javax.swing.JDialog LlenarSubGenericaDetalleCT5;
    private javax.swing.JDialog LlenarSubGenericaEDITAR;
    private javax.swing.JDialog LlenarTransCT1EDITAR;
    private javax.swing.JDialog LlenarTransCT2;
    private javax.swing.JDialog SubGenericaDetalleEDITAR;
    private javax.swing.JLabel Subgenericadetalle;
    private javax.swing.JButton b2;
    private javax.swing.JButton b4;
    private javax.swing.JButton b6;
    private javax.swing.JButton bc;
    private javax.swing.JButton bc1;
    private javax.swing.JButton bc10;
    private javax.swing.JButton bc11;
    private javax.swing.JButton bc2;
    private javax.swing.JButton bc3;
    private javax.swing.JButton bc4;
    private javax.swing.JButton bc5;
    private javax.swing.JButton bc6;
    private javax.swing.JButton bc7;
    private javax.swing.JButton bc8;
    private javax.swing.JButton bc9;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnbuscar1;
    private javax.swing.JButton btnbuscar10;
    private javax.swing.JButton btnbuscar11;
    private javax.swing.JButton btnbuscar2;
    private javax.swing.JButton btnbuscar3;
    private javax.swing.JButton btnbuscar4;
    private javax.swing.JButton btnbuscar5;
    private javax.swing.JButton btnbuscar6;
    private javax.swing.JButton btnbuscar7;
    private javax.swing.JButton btnbuscar8;
    private javax.swing.JButton btnbuscar9;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btneliminar1;
    private javax.swing.JButton btnguardar;
    private javax.swing.JLabel cuentagenerica;
    private javax.swing.JLabel especificadetalle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCT1;
    private javax.swing.JLabel lblCT2;
    private javax.swing.JLabel lblCT3;
    private javax.swing.JLabel lblCT4;
    private javax.swing.JLabel lblCT5;
    private javax.swing.JLabel lblusu;
    private javax.swing.JLabel sugenericacadena;
    private javax.swing.JTable tb_Grupo;
    private javax.swing.JTable tb_Grupo1;
    private javax.swing.JTable tb_Grupo10;
    private javax.swing.JTable tb_Grupo2;
    private javax.swing.JTable tb_Grupo3;
    private javax.swing.JTable tb_Grupo4;
    private javax.swing.JTable tb_Grupo5;
    private javax.swing.JTable tb_Grupo6;
    private javax.swing.JTable tb_Grupo7;
    private javax.swing.JTable tb_Grupo8;
    private javax.swing.JTable tb_Grupo9;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtBuscar10;
    private javax.swing.JTextField txtBuscar2;
    private javax.swing.JTextField txtBuscar3;
    private javax.swing.JTextField txtBuscar4;
    private javax.swing.JTextField txtBuscar5;
    private javax.swing.JTextField txtBuscar6;
    private javax.swing.JTextField txtBuscar7;
    private javax.swing.JTextField txtBuscar8;
    private javax.swing.JTextField txtBuscar9;
    private javax.swing.JTextField txtcodE;
    private javax.swing.JTextField txtcodEd;
    private javax.swing.JTextField txtcodG;
    private javax.swing.JTextField txtcodGD;
    private javax.swing.JTextField txtcodT;
    private javax.swing.JTextField txtcodsubgen;
    private javax.swing.JTextField txtdesE;
    private javax.swing.JTextField txtdesEd;
    private javax.swing.JTextField txtdesG;
    private javax.swing.JTextField txtdesGd;
    private javax.swing.JTextField txtdesSubG;
    private javax.swing.JTextField txtdesT;
    private javax.swing.JTextField txted1;
    private javax.swing.JTextField txtespe;
    private javax.swing.JTextField txtespe2;
    private javax.swing.JTextField txtespedet;
    private javax.swing.JTextField txtgen;
    private javax.swing.JTextField txtgen2;
    private javax.swing.JTextField txtgen3;
    private javax.swing.JTextField txtgend;
    private javax.swing.JTextField txtsgd;
    private javax.swing.JTextField txtsub;
    private javax.swing.JTextField txtsubgd;
    private javax.swing.JTextField txtsubgen;
    private javax.swing.JTextField txtsuggend;
    private javax.swing.JTextField txttipoT;
    private javax.swing.JTextField txttrans;
    private javax.swing.JTextField txttrans1;
    // End of variables declaration//GEN-END:variables
}
