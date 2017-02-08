/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;

import com.lowagie.text.Table;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import modelos.Caja.Caja_Jerarquias;

/**
 *
 * @author MYS1
 */
public class Caja_Jerarquias2 extends javax.swing.JFrame {
DefaultTableModel m;
byte tg;
Caja_Jerarquias cnn = new Caja_Jerarquias();



    /**
     * Creates new form Caja_Jerarquias2
     */
    public Caja_Jerarquias2() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        
        LISTAR0();
        Nivel0();
        L1.setVisible(true);
        L2.setVisible(false);
        L3.setVisible(false);
        L4.setVisible(false);
        L5.setVisible(false);
        L6.setVisible(false);
        L7.setVisible(false);

        CODN0.setText(cnn.codTipo(T1.getText()));
        Buscar0();
        Nivel1();
        Nivel1dETALLE();
        
        if (T7.getText()=="jLabel8"){
            
            T7.setVisible(false);
        
            
        }else{
            T7.setVisible(true);

        }
        Buscar11();
     
        visible();
        
        Buscar21();
        Buscar22();
        Buscar23();
        Buscar24(); 
        Buscar25();
        Buscar26();
           
           
        Nivel21();
        Nivel22();
        Nivel23();
        Nivel24();
        Nivel25();
        Nivel26();
        visibleNivel();
    
     
}
    
    public void LIMPIAR(){
         ID1.setText("a");
         ID2.setText("a");
         ID3.setText("a");
         ID4.setText("a");
         ID5.setText("a");
         ID6.setText("a");
         ID7.setText("a");
    }
    public void Nivel0(){
        try {
            
            T1.setText(String.valueOf(NIVEL0.getValueAt(0, 0)));
            T2.setText(String.valueOf(NIVEL0.getValueAt(1, 0)));
            T3.setText(String.valueOf(NIVEL0.getValueAt(2, 0)));
            T4.setText(String.valueOf(NIVEL0.getValueAt(3, 0)));
            T5.setText(String.valueOf(NIVEL0.getValueAt(4, 0)));
            
            T6.setText(String.valueOf(NIVEL0.getValueAt(5, 0)));
            T7.setText(String.valueOf(NIVEL0.getValueAt(6, 0)));
  
        } catch (Exception e) {
        }
    }
    
    public void Nivel1(){
        try {
            
            n111.setText("jLabel11");
            n112.setText("jLabel11");
            n113.setText("jLabel11");
            n114.setText("jLabel11");
            n115.setText("jLabel11");
            n116.setText("jLabel11");
            
            n111.setText(String.valueOf(NIVEL1.getValueAt(0, 0)));
            n112.setText(String.valueOf(NIVEL1.getValueAt(1, 0)));
            n113.setText(String.valueOf(NIVEL1.getValueAt(2, 0)));
            n114.setText(String.valueOf(NIVEL1.getValueAt(3, 0)));
            n115.setText(String.valueOf(NIVEL1.getValueAt(4, 0)));
            n116.setText(String.valueOf(NIVEL1.getValueAt(5, 0)));
           // n117.setText(String.valueOf(tnivel11.getValueAt(6, 0)));
            
        

        } catch (Exception e) {
        }
    }
    
    public void Nivel21(){
        try {
  
            N21.setText("jLabel2");
            N22.setText("jLabel2");
            N23.setText("jLabel2");
            N24.setText("jLabel2");
            N25.setText("jLabel2");
            N39.setText("jLabel2");
            N27.setText("jLabel2");
            N26.setText("jLabel2");
            N40.setText("jLabel2");
            N41.setText("jLabel2");
            N42.setText("jLabel2");
            
            N21.setText(String.valueOf(NIVEL21.getValueAt(0, 0)));
            N22.setText(String.valueOf(NIVEL21.getValueAt(1, 0)));
            N23.setText(String.valueOf(NIVEL21.getValueAt(2, 0)));
            N24.setText(String.valueOf(NIVEL21.getValueAt(3, 0)));
            N25.setText(String.valueOf(NIVEL21.getValueAt(4, 0)));
            N39.setText(String.valueOf(NIVEL21.getValueAt(5, 0)));
            N27.setText(String.valueOf(NIVEL21.getValueAt(6, 0)));
            N26.setText(String.valueOf(NIVEL21.getValueAt(7, 0)));
            N40.setText(String.valueOf(NIVEL21.getValueAt(8, 0)));
            N41.setText(String.valueOf(NIVEL21.getValueAt(9, 0)));
            N42.setText(String.valueOf(NIVEL21.getValueAt(10, 0)));

        } catch (Exception e) {
        }
    }
    
    public void Nivel22(){
        try {

            N28.setText("jLabel2");
            N29.setText("jLabel2");
            N30.setText("jLabel2");
            N31.setText("jLabel2");
            N32.setText("jLabel2");
            N33.setText("jLabel2");
            N34.setText("jLabel2");
            N35.setText("jLabel2");
            N36.setText("jLabel2");
            N37.setText("jLabel2");
            N38.setText("jLabel2");
            
            N28.setText(String.valueOf(NIVEL22.getValueAt(0, 0)));
            N29.setText(String.valueOf(NIVEL22.getValueAt(1, 0)));
            N30.setText(String.valueOf(NIVEL22.getValueAt(2, 0)));
            N31.setText(String.valueOf(NIVEL22.getValueAt(3, 0)));
            N32.setText(String.valueOf(NIVEL22.getValueAt(4, 0)));
            
            N33.setText(String.valueOf(NIVEL22.getValueAt(5, 0)));
            N34.setText(String.valueOf(NIVEL22.getValueAt(6, 0)));
            N35.setText(String.valueOf(NIVEL22.getValueAt(7, 0)));
            N36.setText(String.valueOf(NIVEL22.getValueAt(8, 0)));
            N37.setText(String.valueOf(NIVEL22.getValueAt(9, 0)));
            N38.setText(String.valueOf(NIVEL22.getValueAt(10, 0)));
            
            

        } catch (Exception e) {
        }
    }
        
    public void Nivel23(){
        try {

            N54.setText("jLabel2");
            N55.setText("jLabel2");
            N56.setText("jLabel2");
            N57.setText("jLabel2");
            N58.setText("jLabel2");
            N59.setText("jLabel2");
            N60.setText("jLabel2");
            N61.setText("jLabel2");
            N62.setText("jLabel2");
            N63.setText("jLabel2");
            N64.setText("jLabel2");
            
            N54.setText(String.valueOf(NIVEL23.getValueAt(0, 0)));
            N55.setText(String.valueOf(NIVEL23.getValueAt(1, 0)));
            N56.setText(String.valueOf(NIVEL23.getValueAt(2, 0)));
            N57.setText(String.valueOf(NIVEL23.getValueAt(3, 0)));
            N58.setText(String.valueOf(NIVEL23.getValueAt(4, 0))); 
            N59.setText(String.valueOf(NIVEL23.getValueAt(5, 0)));
            N60.setText(String.valueOf(NIVEL23.getValueAt(6, 0)));
            N61.setText(String.valueOf(NIVEL23.getValueAt(7, 0)));
            N62.setText(String.valueOf(NIVEL23.getValueAt(8, 0)));
            N63.setText(String.valueOf(NIVEL23.getValueAt(9, 0)));
            N64.setText(String.valueOf(NIVEL23.getValueAt(9, 0)));

        } catch (Exception e) {
        }
    }
    
    public void Nivel24(){
        try {
            
            N65.setText("jLabel2");
            N66.setText("jLabel2");
            N67.setText("jLabel2");
            N68.setText("jLabel2");
            N69.setText("jLabel2");
            N70.setText("jLabel2");
            N71.setText("jLabel2");
            N72.setText("jLabel2");
            N73.setText("jLabel2");
            N74.setText("jLabel2");
            N75.setText("jLabel2");

            N65.setText(String.valueOf(NIVEL24.getValueAt(0, 0)));
            N66.setText(String.valueOf(NIVEL24.getValueAt(1, 0)));
            N67.setText(String.valueOf(NIVEL24.getValueAt(2, 0)));
            N68.setText(String.valueOf(NIVEL24.getValueAt(3, 0)));
            N69.setText(String.valueOf(NIVEL24.getValueAt(4, 0)));
            
            N70.setText(String.valueOf(NIVEL24.getValueAt(5, 0)));
            N71.setText(String.valueOf(NIVEL24.getValueAt(6, 0)));
            N72.setText(String.valueOf(NIVEL24.getValueAt(7, 0)));
            N73.setText(String.valueOf(NIVEL24.getValueAt(8, 0)));
            N74.setText(String.valueOf(NIVEL24.getValueAt(9, 0)));
            N75.setText(String.valueOf(NIVEL24.getValueAt(10, 0)));

        } catch (Exception e) {
        }
    }
     
    public void Nivel25(){
        try {

            
            N76.setText("jLabel2");
            N77.setText("jLabel2");
            N78.setText("jLabel2");
            N79.setText("jLabel2");
            N80.setText("jLabel2");
            N81.setText("jLabel2");
            N82.setText("jLabel2");
            N83.setText("jLabel2");
            N84.setText("jLabel2");
            N85.setText("jLabel2");
            N86.setText("jLabel2");
            
            N76.setText(String.valueOf(NIVEL25.getValueAt(0, 0)));
            N77.setText(String.valueOf(NIVEL25.getValueAt(1, 0)));
            N78.setText(String.valueOf(NIVEL25.getValueAt(2, 0)));
            N79.setText(String.valueOf(NIVEL25.getValueAt(3, 0)));
            N80.setText(String.valueOf(NIVEL25.getValueAt(4, 0)));
            
            N81.setText(String.valueOf(NIVEL25.getValueAt(5, 0)));
            N82.setText(String.valueOf(NIVEL25.getValueAt(6, 0)));
            N83.setText(String.valueOf(NIVEL25.getValueAt(7, 0)));
            N84.setText(String.valueOf(NIVEL25.getValueAt(8, 0)));
            N85.setText(String.valueOf(NIVEL25.getValueAt(9, 0)));
            N86.setText(String.valueOf(NIVEL25.getValueAt(10, 0)));

        } catch (Exception e) {
        }
    }
    
    public void Nivel26(){
        try {
            
            N87.setText("jLabel2");
            N88.setText("jLabel2");
            N89.setText("jLabel2");
            N90.setText("jLabel2");
            N91.setText("jLabel2");
            N91.setText("jLabel2");
            N93.setText("jLabel2");
            N94.setText("jLabel2");
            N95.setText("jLabel2");
            N96.setText("jLabel2");
            N97.setText("jLabel2");

            N87.setText(String.valueOf(NIVEL26.getValueAt(0, 0)));
            N88.setText(String.valueOf(NIVEL26.getValueAt(1, 0)));
            N89.setText(String.valueOf(NIVEL26.getValueAt(2, 0)));
            N90.setText(String.valueOf(NIVEL26.getValueAt(3, 0)));
            N91.setText(String.valueOf(NIVEL26.getValueAt(4, 0)));
            N92.setText(String.valueOf(NIVEL26.getValueAt(5, 0)));
            N93.setText(String.valueOf(NIVEL26.getValueAt(6, 0)));
            N94.setText(String.valueOf(NIVEL26.getValueAt(7, 0)));
            N95.setText(String.valueOf(NIVEL26.getValueAt(8, 0)));
            N96.setText(String.valueOf(NIVEL26.getValueAt(9, 0)));
            N97.setText(String.valueOf(NIVEL26.getValueAt(10, 0)));

        } catch (Exception e) {
        }
    }
    
 
    public void visible(){
        try {
                    //NIVEL111
        if (n111.getText()=="jLabel11" ){
            
            n111.setText(" ");
            n29.setVisible(false);
        }else{
            n111.setVisible(true);  
            n29.setVisible(true);
        }
        
        if (n112.getText()=="jLabel11" ){
            
            n112.setText(" ");
             n30.setVisible(false);
        }else{
            n112.setVisible(true); 
            n30.setVisible(true);
        }
        
        if (n113.getText()=="jLabel11" ){
            
            n113.setText(" ");
            n32.setVisible(false);
        }else{
            n113.setVisible(true);   
            n32.setVisible(true);
        }
        
        if (n114.getText()=="jLabel11" ){
            
            n114.setText(" ");
            n33.setVisible(false);
        }else{
            n114.setVisible(true);  
            n33.setVisible(true);
        }
        
        if (n115.getText()=="jLabel11" ){
            
            n115.setText(" ");
            n34.setVisible(false);
        }else{
            n115.setVisible(true);
            n34.setVisible(true);
        }
         if (n116.getText()=="jLabel11" ){
            
            n116.setText(" ");
            n35.setVisible(false);
        }else{
            n116.setVisible(true);
            n35.setVisible(true);
        }
        
            
            
  
        } catch (Exception e) {
        }
    }
    
    public void visibleNivel(){
        try {
        /////////////////////////////////
        //NIVEL111
        if (N21.getText()=="jLabel2" ){
            
            N21.setVisible(false);
        }else{
            N21.setVisible(true);   
        }
        
        if (N22.getText()=="jLabel2" ){
            
            N22.setVisible(false);
        }else{
            N22.setVisible(true);   
        }
        
        if (N23.getText()=="jLabel2" ){
            
            N23.setVisible(false);
        }else{
            N23.setVisible(true);   
        }
        
        if (N24.getText()=="jLabel2" ){
            
            N24.setVisible(false);
        }else{
            N24.setVisible(true);   
        }
        
        if (N25.getText()=="jLabel2" ){
            
            N25.setVisible(false);
        }else{
            N25.setVisible(true);   
        }
         if (N39.getText()=="jLabel2" ){
            
            N39.setVisible(false);
        }else{
            N39.setVisible(true);   
        }
         
        if (N27.getText()=="jLabel2" ){
            
            N27.setVisible(false);
        }else{
            N27.setVisible(true);   
        }
         if (N26.getText()=="jLabel2" ){
            
            N26.setVisible(false);
        }else{
            N26.setVisible(true);   
        }
         
         
         if (N40.getText()=="jLabel2" ){
            
            N40.setVisible(false);
        }else{
            N40.setVisible(true);   
        }
         if (N41.getText()=="jLabel2" ){
            
            N41.setVisible(false);
        }else{
            N41.setVisible(true);   
        }
         
        if (N42.getText()=="jLabel2" ){
            
            N42.setVisible(false);
        }else{
            N42.setVisible(true);   
        }
        
        /////////////////////////////////
        //NIVEL12
        if (N28.getText()=="jLabel2" ){
            
            N28.setVisible(false);
        }else{
            N28.setVisible(true);   
        }
        
        if (N29.getText()=="jLabel2" ){
            
            N29.setVisible(false);
        }else{
            N29.setVisible(true);   
        }
        
        if (N30.getText()=="jLabel2" ){
            
            N30.setVisible(false);
        }else{
            N30.setVisible(true);   
        }
        
        if (N31.getText()=="jLabel2" ){
            
            N31.setVisible(false);
        }else{
            N31.setVisible(true);   
        }
        
        if (N32.getText()=="jLabel2" ){
            
            N32.setVisible(false);
        }else{
            N32.setVisible(true);   
        }
         if (N33.getText()=="jLabel2" ){
            
            N33.setVisible(false);
        }else{
            N33.setVisible(true);   
        }
         
        if (N34.getText()=="jLabel2" ){
            
            N34.setVisible(false);
        }else{
            N34.setVisible(true);   
        }
         if (N35.getText()=="jLabel2" ){
            
            N35.setVisible(false);
        }else{
            N35.setVisible(true);   
        }
         
         
         if (N36.getText()=="jLabel2" ){
            
            N36.setVisible(false);
        }else{
            N36.setVisible(true);   
        }
         if (N37.getText()=="jLabel2" ){
            
            N37.setVisible(false);
        }else{
            N37.setVisible(true);   
        }
         
        if (N38.getText()=="jLabel2" ){
            
            N38.setVisible(false);
        }else{
            N38.setVisible(true);   
        }
        
         /////////////////////////////////
        //NIVEL13
        if (N54.getText()=="jLabel2" ){
            
            N54.setVisible(false);
        }else{
            N54.setVisible(true);   
        }
        
        if (N55.getText()=="jLabel2" ){
            
            N55.setVisible(false);
        }else{
            N55.setVisible(true);   
        }
        
        if (N56.getText()=="jLabel2" ){
            
            N56.setVisible(false);
        }else{
            N56.setVisible(true);   
        }
        
        if (N57.getText()=="jLabel2" ){
            
            N57.setVisible(false);
        }else{
            N57.setVisible(true);   
        }
        
        if (N58.getText()=="jLabel2" ){
            
            N58.setVisible(false);
        }else{
            N58.setVisible(true);   
        }
         if (N59.getText()=="jLabel2" ){
            
            N59.setVisible(false);
        }else{
            N59.setVisible(true);   
        }
         
        if (N60.getText()=="jLabel2" ){
            
            N60.setVisible(false);
        }else{
            N60.setVisible(true);   
        }
         if (N61.getText()=="jLabel2" ){
            
            N61.setVisible(false);
        }else{
            N61.setVisible(true);   
        }
         
         
         if (N62.getText()=="jLabel2" ){
            
            N62.setVisible(false);
        }else{
            N62.setVisible(true);   
        }
         if (N63.getText()=="jLabel2" ){
            
            N63.setVisible(false);
        }else{
            N63.setVisible(true);   
        }
         
        if (N64.getText()=="jLabel2" ){
            
            N64.setVisible(false);
        }else{
            N64.setVisible(true);   
        }
        
         /////////////////////////////////
        //NIVEL14
        if (N65.getText()=="jLabel2" ){
            
            N65.setVisible(false);
        }else{
            N65.setVisible(true);   
        }
        
        if (N66.getText()=="jLabel2" ){
            
            N66.setVisible(false);
        }else{
            N66.setVisible(true);   
        }
        
        if (N67.getText()=="jLabel2" ){
            
            N67.setVisible(false);
        }else{
            N67.setVisible(true);   
        }
        
        if (N68.getText()=="jLabel2" ){
            
            N68.setVisible(false);
        }else{
            N68.setVisible(true);   
        }
        
        if (N69.getText()=="jLabel2" ){
            
            N69.setVisible(false);
        }else{
            N69.setVisible(true);   
        }
         if (N70.getText()=="jLabel2" ){
            
            N70.setVisible(false);
        }else{
            N70.setVisible(true);   
        }
         
        if (N71.getText()=="jLabel2" ){
            
            N71.setVisible(false);
        }else{
            N71.setVisible(true);   
        }
         if (N72.getText()=="jLabel2" ){
            
            N72.setVisible(false);
        }else{
            N72.setVisible(true);   
        }
         
         
         if (N73.getText()=="jLabel2" ){
            
            N73.setVisible(false);
        }else{
            N73.setVisible(true);   
        }
         if (N74.getText()=="jLabel2" ){
            
            N74.setVisible(false);
        }else{
            N74.setVisible(true);   
        }
         
        if (N75.getText()=="jLabel2" ){
            
            N75.setVisible(false);
        }else{
            N75.setVisible(true);   
        }
        
        //NIVEL14
        if (N76.getText()=="jLabel2" ){
            
            N76.setVisible(false);
        }else{
            N76.setVisible(true);   
        }
        
        if (N77.getText()=="jLabel2" ){
            
            N77.setVisible(false);
        }else{
            N77.setVisible(true);   
        }
        
        if (N78.getText()=="jLabel2" ){
            
            N78.setVisible(false);
        }else{
            N78.setVisible(true);   
        }
        
        if (N79.getText()=="jLabel2" ){
            
            N79.setVisible(false);
        }else{
            N79.setVisible(true);   
        }
        
        if (N80.getText()=="jLabel2" ){
            
            N80.setVisible(false);
        }else{
            N80.setVisible(true);   
        }
         if (N81.getText()=="jLabel2" ){
            
            N81.setVisible(false);
        }else{
            N81.setVisible(true);   
        }
         
        if (N82.getText()=="jLabel2" ){
            
            N82.setVisible(false);
        }else{
            N82.setVisible(true);   
        }
         if (N83.getText()=="jLabel2" ){
            
            N83.setVisible(false);
        }else{
            N83.setVisible(true);   
        }
         
         
         if (N84.getText()=="jLabel2" ){
            
            N84.setVisible(false);
        }else{
            N84.setVisible(true);   
        }
         if (N85.getText()=="jLabel2" ){
            
            N85.setVisible(false);
        }else{
            N85.setVisible(true);   
        }
         
        if (N86.getText()=="jLabel2" ){
            
            N86.setVisible(false);
        }else{
            N86.setVisible(true);   
        }
        
         //NIVEL14
        if (N87.getText()=="jLabel2" ){
            
            N87.setVisible(false);
        }else{
            N87.setVisible(true);   
        }
        
        if (N88.getText()=="jLabel2" ){
            
            N88.setVisible(false);
        }else{
            N88.setVisible(true);   
        }
        
        if (N89.getText()=="jLabel2" ){
            
            N89.setVisible(false);
        }else{
            N89.setVisible(true);   
        }
        
        if (N90.getText()=="jLabel2" ){
            
            N90.setVisible(false);
        }else{
            N90.setVisible(true);   
        }
        
        if (N91.getText()=="jLabel2" ){
            
            N91.setVisible(false);
        }else{
            N91.setVisible(true);   
        }
         if (N92.getText()=="jLabel2" ){
            
            N92.setVisible(false);
        }else{
            N92.setVisible(true);   
        }
         
        if (N93.getText()=="jLabel2" ){
            
            N93.setVisible(false);
        }else{
            N93.setVisible(true);   
        }
         if (N94.getText()=="jLabel2" ){
            
            N94.setVisible(false);
        }else{
            N94.setVisible(true);   
        }
         
         
         if (N95.getText()=="jLabel2" ){
            
            N95.setVisible(false);
        }else{
            N95.setVisible(true);   
        }
         if (N96.getText()=="jLabel2" ){
            
            N96.setVisible(false);
        }else{
            N96.setVisible(true);   
        }
         
        if (N97.getText()=="jLabel2" ){
            
            N97.setVisible(false);
        }else{
            N97.setVisible(true);   
        }
        

        } catch (Exception e) {
        }
    }
    
    
    public void Nivel1dETALLE(){
        try {
            
        
            ID1.setText(String.valueOf(NIVEL1.getValueAt(0, 2)));
            ID2.setText(String.valueOf(NIVEL1.getValueAt(1, 2)));
            ID3.setText(String.valueOf(NIVEL1.getValueAt(2, 2)));
            ID4.setText(String.valueOf(NIVEL1.getValueAt(3, 2)));
            ID5.setText(String.valueOf(NIVEL1.getValueAt(4, 2)));
            
            ID6.setText(String.valueOf(NIVEL1.getValueAt(5, 2)));
           // n117.setText(String.valueOf(tnivel11.getValueAt(6, 0)));
            
                  } catch (Exception e) {
        }
    }
    
    public void LISTAR0(){
    try {
             String titulos[]={"nivel","descripcion","codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_JerarquiasNivel0";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3); // 
      
                    m.addRow(fila);
                    c++;
            }
            NIVEL0.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            NIVEL0.setRowSorter(elQueOrdena);
            this.NIVEL0.setModel(m);
            
    } catch (Exception e) {
    }
}
    
    public void Buscar0(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            NIVEL1.setModel(new DefaultTableModel());
            String titulos[]={"nivel","descripcion","codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Jerarquias obj=new Caja_Jerarquias();
                    consulta="exec Caja_Jerarquia_NIVEL1 ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, CODN0.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
        

                m.addRow(fila);
                c++;
            }
            NIVEL1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            NIVEL1.setRowSorter(elQueOrdena);
            this.NIVEL1.setModel(m);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    public void Buscar1(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            NIVEL1.setModel(new DefaultTableModel());
             String titulos[]={"nivel","descripcion","codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Jerarquias obj=new Caja_Jerarquias();
                    consulta="exec Caja_Jerarquia_NIVEL1 ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, CODN1.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
        

                m.addRow(fila);
                c++;
            }
            NIVEL1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            NIVEL1.setRowSorter(elQueOrdena);
            this.NIVEL1.setModel(m);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    public void Buscar11(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            NIVEL1.setModel(new DefaultTableModel());
             String titulos[]={"nivel","descripcion","codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Jerarquias obj=new Caja_Jerarquias();
                    consulta="exec Caja_Jerarquia_NIVEL1 ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, CODN2.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
        

                m.addRow(fila);
                c++;
            }
            NIVEL1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            NIVEL1.setRowSorter(elQueOrdena);
            this.NIVEL1.setModel(m);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
     
    public void Buscar12(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            NIVEL1.setModel(new DefaultTableModel());
             String titulos[]={"nivel","descripcion","codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Jerarquias obj=new Caja_Jerarquias();
                    consulta="exec Caja_Jerarquia_NIVEL1 ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, CODN3.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
        

                m.addRow(fila);
                c++;
            }
            NIVEL1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            NIVEL1.setRowSorter(elQueOrdena);
            this.NIVEL1.setModel(m);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    public void Buscar13(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            NIVEL1.setModel(new DefaultTableModel());
             String titulos[]={"nivel","descripcion","codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Jerarquias obj=new Caja_Jerarquias();
                    consulta="exec Caja_Jerarquia_NIVEL1 ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, CODN4.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
        

                m.addRow(fila);
                c++;
            }
            NIVEL1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            NIVEL1.setRowSorter(elQueOrdena);
            this.NIVEL1.setModel(m);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    public void Buscar14(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            NIVEL1.setModel(new DefaultTableModel());
             String titulos[]={"nivel","descripcion","codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Jerarquias obj=new Caja_Jerarquias();
                    consulta="exec Caja_Jerarquia_NIVEL1 ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, CODN5.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
        

                m.addRow(fila);
                c++;
            }
            NIVEL1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            NIVEL1.setRowSorter(elQueOrdena);
            this.NIVEL1.setModel(m);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    public void Buscar15(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            NIVEL1.setModel(new DefaultTableModel());
             String titulos[]={"nivel","descripcion","codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Jerarquias obj=new Caja_Jerarquias();
                    consulta="exec Caja_Jerarquia_NIVEL1 ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, CODN6.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
        

                m.addRow(fila);
                c++;
            }
            NIVEL1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            NIVEL1.setRowSorter(elQueOrdena);
            this.NIVEL1.setModel(m);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    public void Buscar21(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            NIVEL21.setModel(new DefaultTableModel());
             String titulos[]={"nivel","descripcion","codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Jerarquias obj=new Caja_Jerarquias();
                    consulta="exec Caja_Jerarquia_NIVEL12 ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, ID1.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
        

                m.addRow(fila);
                c++;
            }
            NIVEL21.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            NIVEL21.setRowSorter(elQueOrdena);
            this.NIVEL21.setModel(m);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    public void Buscar22(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            NIVEL22.setModel(new DefaultTableModel());
             String titulos[]={"nivel","descripcion","codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Jerarquias obj=new Caja_Jerarquias();
                    consulta="exec Caja_Jerarquia_NIVEL12 ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, ID2.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
        

                m.addRow(fila);
                c++;
            }
            NIVEL22.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            NIVEL22.setRowSorter(elQueOrdena);
            this.NIVEL22.setModel(m);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    public void Buscar23(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            NIVEL23.setModel(new DefaultTableModel());
             String titulos[]={"nivel","descripcion","codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Jerarquias obj=new Caja_Jerarquias();
                    consulta="exec Caja_Jerarquia_NIVEL12 ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, ID3.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
        

                m.addRow(fila);
                c++;
            }
            NIVEL23.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            NIVEL23.setRowSorter(elQueOrdena);
            this.NIVEL23.setModel(m);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    public void Buscar24(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            NIVEL24.setModel(new DefaultTableModel());
             String titulos[]={"nivel","descripcion","codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Jerarquias obj=new Caja_Jerarquias();
                    consulta="exec Caja_Jerarquia_NIVEL12 ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, ID4.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
        

                m.addRow(fila);
                c++;
            }
            NIVEL24.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            NIVEL24.setRowSorter(elQueOrdena);
            this.NIVEL24.setModel(m);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    public void Buscar25(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            NIVEL25.setModel(new DefaultTableModel());
             String titulos[]={"nivel","descripcion","codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Jerarquias obj=new Caja_Jerarquias();
                    consulta="exec Caja_Jerarquia_NIVEL12 ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, ID5.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
        

                m.addRow(fila);
                c++;
            }
            NIVEL25.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            NIVEL25.setRowSorter(elQueOrdena);
            this.NIVEL25.setModel(m);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    public void Buscar26(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            NIVEL26.setModel(new DefaultTableModel());
             String titulos[]={"nivel","descripcion","codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Jerarquias obj=new Caja_Jerarquias();
                    consulta="exec Caja_Jerarquia_NIVEL12 ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, ID6.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
        

                m.addRow(fila);
                c++;
            }
            NIVEL26.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            NIVEL26.setRowSorter(elQueOrdena);
            this.NIVEL26.setModel(m);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    
    public void GuardarN2(){

        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(guardar == 0 ){
                
                Caja_Jerarquias cno1 = new Caja_Jerarquias();
                cno1.setCod_jerar_forma_pago(codigo.getText());//
                cno1.setNom_forma_pago(EP.getText());//
                cno1.setDescri_forma_pago(des.getText());//
                cno1.setRelacion_forma_pago(ID1.getText());//
                cno1.setNivel_forma_pago("2");//
                cno1.setTipo_estado_pago("P(Pendiente)");//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevaJerarquia()==true){
                           JOptionPane.showMessageDialog(this, "Datos Guardados");
                           tg=2;
                          
                           
                       } else {
                           JOptionPane.showMessageDialog(this, "Error al guardar");
                       }}}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        G21 = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        EP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        des = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnguardar1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        lblusu = new javax.swing.JLabel();
        btneliminar1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        T1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        T2 = new javax.swing.JLabel();
        CODN0 = new javax.swing.JLabel();
        T3 = new javax.swing.JLabel();
        T4 = new javax.swing.JLabel();
        T5 = new javax.swing.JLabel();
        T6 = new javax.swing.JLabel();
        T7 = new javax.swing.JLabel();
        CODN1 = new javax.swing.JLabel();
        CODN2 = new javax.swing.JLabel();
        CODN3 = new javax.swing.JLabel();
        CODN4 = new javax.swing.JLabel();
        CODN5 = new javax.swing.JLabel();
        CODN6 = new javax.swing.JLabel();
        n27 = new javax.swing.JPanel();
        n29 = new javax.swing.JPanel();
        N21 = new javax.swing.JLabel();
        N22 = new javax.swing.JLabel();
        N23 = new javax.swing.JLabel();
        N24 = new javax.swing.JLabel();
        N25 = new javax.swing.JLabel();
        N26 = new javax.swing.JLabel();
        N27 = new javax.swing.JLabel();
        N39 = new javax.swing.JLabel();
        N40 = new javax.swing.JLabel();
        N41 = new javax.swing.JLabel();
        N42 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        n111 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnNuevo2 = new javax.swing.JButton();
        N43 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        L1 = new javax.swing.JPanel();
        L2 = new javax.swing.JPanel();
        L3 = new javax.swing.JPanel();
        L5 = new javax.swing.JPanel();
        L6 = new javax.swing.JPanel();
        L7 = new javax.swing.JPanel();
        ID1 = new javax.swing.JLabel();
        ID2 = new javax.swing.JLabel();
        ID3 = new javax.swing.JLabel();
        ID4 = new javax.swing.JLabel();
        ID5 = new javax.swing.JLabel();
        ID6 = new javax.swing.JLabel();
        ID7 = new javax.swing.JLabel();
        n30 = new javax.swing.JPanel();
        N28 = new javax.swing.JLabel();
        N29 = new javax.swing.JLabel();
        N30 = new javax.swing.JLabel();
        N31 = new javax.swing.JLabel();
        N32 = new javax.swing.JLabel();
        N33 = new javax.swing.JLabel();
        N34 = new javax.swing.JLabel();
        N35 = new javax.swing.JLabel();
        N36 = new javax.swing.JLabel();
        N37 = new javax.swing.JLabel();
        N38 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        n112 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnNuevo5 = new javax.swing.JButton();
        N46 = new javax.swing.JLabel();
        n32 = new javax.swing.JPanel();
        N54 = new javax.swing.JLabel();
        N55 = new javax.swing.JLabel();
        N56 = new javax.swing.JLabel();
        N57 = new javax.swing.JLabel();
        N58 = new javax.swing.JLabel();
        N59 = new javax.swing.JLabel();
        N60 = new javax.swing.JLabel();
        N61 = new javax.swing.JLabel();
        N62 = new javax.swing.JLabel();
        N63 = new javax.swing.JLabel();
        N64 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        n113 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btnNuevo6 = new javax.swing.JButton();
        N47 = new javax.swing.JLabel();
        n33 = new javax.swing.JPanel();
        N65 = new javax.swing.JLabel();
        N66 = new javax.swing.JLabel();
        N67 = new javax.swing.JLabel();
        N68 = new javax.swing.JLabel();
        N69 = new javax.swing.JLabel();
        N70 = new javax.swing.JLabel();
        N71 = new javax.swing.JLabel();
        N72 = new javax.swing.JLabel();
        N73 = new javax.swing.JLabel();
        N74 = new javax.swing.JLabel();
        N75 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        n114 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btnNuevo7 = new javax.swing.JButton();
        N48 = new javax.swing.JLabel();
        n34 = new javax.swing.JPanel();
        N76 = new javax.swing.JLabel();
        N77 = new javax.swing.JLabel();
        N78 = new javax.swing.JLabel();
        N79 = new javax.swing.JLabel();
        N80 = new javax.swing.JLabel();
        N81 = new javax.swing.JLabel();
        N82 = new javax.swing.JLabel();
        N83 = new javax.swing.JLabel();
        N84 = new javax.swing.JLabel();
        N85 = new javax.swing.JLabel();
        N86 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        n115 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        btnNuevo8 = new javax.swing.JButton();
        N49 = new javax.swing.JLabel();
        L4 = new javax.swing.JPanel();
        n35 = new javax.swing.JPanel();
        N87 = new javax.swing.JLabel();
        N88 = new javax.swing.JLabel();
        N89 = new javax.swing.JLabel();
        N90 = new javax.swing.JLabel();
        N91 = new javax.swing.JLabel();
        N92 = new javax.swing.JLabel();
        N93 = new javax.swing.JLabel();
        N94 = new javax.swing.JLabel();
        N95 = new javax.swing.JLabel();
        N96 = new javax.swing.JLabel();
        N97 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        n116 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btnNuevo9 = new javax.swing.JButton();
        N50 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        n117 = new javax.swing.JLabel();
        codigo = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        NIVEL0 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jScrollPane6 = new javax.swing.JScrollPane();
            NIVEL1 = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jScrollPane7 = new javax.swing.JScrollPane();
                NIVEL21 = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jScrollPane8 = new javax.swing.JScrollPane();
                    NIVEL22 = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        jScrollPane9 = new javax.swing.JScrollPane();
                        NIVEL23 = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            jScrollPane10 = new javax.swing.JScrollPane();
                            NIVEL24 = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false; //Disallow the editing of any cell
                                }};
                                jScrollPane11 = new javax.swing.JScrollPane();
                                NIVEL25 = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false; //Disallow the editing of any cell
                                    }};
                                    jScrollPane12 = new javax.swing.JScrollPane();
                                    NIVEL26 = new javax.swing.JTable(){
                                        public boolean isCellEditable(int rowIndex, int colIndex){
                                            return false; //Disallow the editing of any cell
                                        }};

                                        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

                                        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                        jLabel3.setText("Nombre");

                                        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                        jLabel4.setText("Nombre");

                                        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                                        jLabel5.setText("Nombre");

                                        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                                        jLabel6.setText("Descripcion");

                                        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                                        jPanel7.setLayout(jPanel7Layout);
                                        jPanel7Layout.setHorizontalGroup(
                                            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(jLabel5))
                                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                                        .addGap(34, 34, 34)
                                                        .addComponent(jLabel4)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                                .addContainerGap(70, Short.MAX_VALUE)
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel3))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(EP, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(des, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(16, 16, 16))
                                        );
                                        jPanel7Layout.setVerticalGroup(
                                            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(jLabel5)
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(EP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel3))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel6)
                                                    .addComponent(des, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(25, Short.MAX_VALUE))
                                        );

                                        jPanel13.setBackground(new java.awt.Color(0, 153, 153));

                                        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
                                        jLabel2.setText("Empresa de pago");

                                        btnguardar1.setForeground(new java.awt.Color(240, 240, 240));
                                        btnguardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
                                        btnguardar1.setMnemonic('N');
                                        btnguardar1.setContentAreaFilled(false);
                                        btnguardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        btnguardar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                        btnguardar1.setIconTextGap(30);
                                        btnguardar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                        btnguardar1.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                btnguardar1ActionPerformed(evt);
                                            }
                                        });

                                        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                                        jPanel13.setLayout(jPanel13Layout);
                                        jPanel13Layout.setHorizontalGroup(
                                            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(btnguardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );
                                        jPanel13Layout.setVerticalGroup(
                                            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnguardar1)
                                                .addGap(559, 559, 559))
                                        );

                                        javax.swing.GroupLayout G21Layout = new javax.swing.GroupLayout(G21.getContentPane());
                                        G21.getContentPane().setLayout(G21Layout);
                                        G21Layout.setHorizontalGroup(
                                            G21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        );
                                        G21Layout.setVerticalGroup(
                                            G21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, G21Layout.createSequentialGroup()
                                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );

                                        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                                        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

                                        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                                        jLabel1.setText("Jerarquias");

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
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(10, 10, 10)
                                                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel1)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btneliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblusu)
                                                .addGap(16, 16, 16))
                                        );
                                        jPanel1Layout.setVerticalGroup(
                                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(btneliminar1)
                                                            .addComponent(jLabel1))
                                                        .addGap(18, 18, 18))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(22, 22, 22)
                                                        .addComponent(lblusu)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btneditar, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btneliminar)
                                                        .addComponent(btnbuscar))
                                                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(552, 552, 552))
                                        );

                                        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                                        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
                                        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                                        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                                        T1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Libro de salud-30.png"))); // NOI18N
                                        T1.setText("jLabel8");
                                        T1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        T1.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                T1MouseClicked(evt);
                                            }
                                        });

                                        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
                                        jLabel9.setText("TIPO______________________________________________________________________________________________________________________________________________");

                                        T2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Libro de salud-30.png"))); // NOI18N
                                        T2.setText("jLabel8");
                                        T2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        T2.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                T2MouseClicked(evt);
                                            }
                                        });

                                        CODN0.setBackground(new java.awt.Color(255, 255, 255));
                                        CODN0.setForeground(new java.awt.Color(255, 255, 255));
                                        CODN0.setText("jLabel8");

                                        T3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Libro de salud-30.png"))); // NOI18N
                                        T3.setText("jLabel8");
                                        T3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        T3.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                T3MouseClicked(evt);
                                            }
                                        });

                                        T4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Libro de salud-30.png"))); // NOI18N
                                        T4.setText("jLabel8");
                                        T4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        T4.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                T4MouseClicked(evt);
                                            }
                                        });

                                        T5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Libro de salud-30.png"))); // NOI18N
                                        T5.setText("jLabel8");
                                        T5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        T5.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                T5MouseClicked(evt);
                                            }
                                        });

                                        T6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Libro de salud-30.png"))); // NOI18N
                                        T6.setText("jLabel8");
                                        T6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        T6.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                T6MouseClicked(evt);
                                            }
                                        });

                                        T7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Libro de salud-30.png"))); // NOI18N
                                        T7.setText("jLabel8");
                                        T7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        T7.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                T7MouseClicked(evt);
                                            }
                                        });

                                        CODN1.setBackground(new java.awt.Color(255, 255, 255));
                                        CODN1.setForeground(new java.awt.Color(255, 255, 255));
                                        CODN1.setText("jLabel8");

                                        CODN2.setBackground(new java.awt.Color(255, 255, 255));
                                        CODN2.setForeground(new java.awt.Color(255, 255, 255));
                                        CODN2.setText("jLabel8");

                                        CODN3.setBackground(new java.awt.Color(255, 255, 255));
                                        CODN3.setForeground(new java.awt.Color(255, 255, 255));
                                        CODN3.setText("jLabel8");

                                        CODN4.setBackground(new java.awt.Color(255, 255, 255));
                                        CODN4.setForeground(new java.awt.Color(255, 255, 255));
                                        CODN4.setText("jLabel8");

                                        CODN5.setBackground(new java.awt.Color(255, 255, 255));
                                        CODN5.setForeground(new java.awt.Color(255, 255, 255));
                                        CODN5.setText("jLabel8");

                                        CODN6.setBackground(new java.awt.Color(255, 255, 255));
                                        CODN6.setForeground(new java.awt.Color(255, 255, 255));
                                        CODN6.setText("jLabel8");

                                        n27.setBackground(new java.awt.Color(255, 255, 255));
                                        n27.setForeground(new java.awt.Color(255, 255, 255));

                                        javax.swing.GroupLayout n27Layout = new javax.swing.GroupLayout(n27);
                                        n27.setLayout(n27Layout);
                                        n27Layout.setHorizontalGroup(
                                            n27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 84, Short.MAX_VALUE)
                                        );
                                        n27Layout.setVerticalGroup(
                                            n27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 371, Short.MAX_VALUE)
                                        );

                                        n29.setBackground(new java.awt.Color(241, 240, 240));
                                        n29.setForeground(new java.awt.Color(230, 230, 230));

                                        N21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N21.setText("jLabel2");

                                        N22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N22.setText("jLabel3");

                                        N23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N23.setText("jLabel2");

                                        N24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N24.setText("jLabel2");

                                        N25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N25.setText("jLabel2");

                                        N26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N26.setText("jLabel2");

                                        N27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N27.setText("jLabel2");

                                        N39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N39.setText("jLabel2");

                                        N40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N40.setText("jLabel2");

                                        N41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N41.setText("jLabel2");

                                        N42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N42.setText("jLabel2");

                                        jPanel27.setBackground(new java.awt.Color(0, 153, 153));

                                        n111.setForeground(new java.awt.Color(255, 255, 255));
                                        n111.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Tipo de archivo de libro genérico-20 (2).png"))); // NOI18N
                                        n111.setText("jLabel11");

                                        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
                                        jPanel27.setLayout(jPanel27Layout);
                                        jPanel27Layout.setHorizontalGroup(
                                            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel27Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(n111)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );
                                        jPanel27Layout.setVerticalGroup(
                                            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                                .addContainerGap(18, Short.MAX_VALUE)
                                                .addComponent(n111)
                                                .addContainerGap())
                                        );

                                        jPanel4.setBackground(new java.awt.Color(153, 153, 153));

                                        btnNuevo2.setBackground(new java.awt.Color(204, 204, 204));
                                        btnNuevo2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                        btnNuevo2.setForeground(new java.awt.Color(102, 102, 102));
                                        btnNuevo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Add Column-30.png"))); // NOI18N
                                        btnNuevo2.setMnemonic('N');
                                        btnNuevo2.setContentAreaFilled(false);
                                        btnNuevo2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        btnNuevo2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                        btnNuevo2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                        btnNuevo2.setIconTextGap(30);
                                        btnNuevo2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                        btnNuevo2.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                btnNuevo2ActionPerformed(evt);
                                            }
                                        });

                                        N43.setForeground(new java.awt.Color(255, 255, 255));
                                        N43.setText("Agregar              ");

                                        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                                        jPanel4.setLayout(jPanel4Layout);
                                        jPanel4Layout.setHorizontalGroup(
                                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(btnNuevo2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(N43)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        );
                                        jPanel4Layout.setVerticalGroup(
                                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnNuevo2))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(N43)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );

                                        javax.swing.GroupLayout n29Layout = new javax.swing.GroupLayout(n29);
                                        n29.setLayout(n29Layout);
                                        n29Layout.setHorizontalGroup(
                                            n29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(n29Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(n29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(N21)
                                                    .addComponent(N22)
                                                    .addComponent(N23)
                                                    .addComponent(N24)
                                                    .addComponent(N25)
                                                    .addComponent(N39)
                                                    .addComponent(N27)
                                                    .addComponent(N26)
                                                    .addComponent(N40)
                                                    .addComponent(N41)
                                                    .addComponent(N42))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        );
                                        n29Layout.setVerticalGroup(
                                            n29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(n29Layout.createSequentialGroup()
                                                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(N21)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N22)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N23)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N24)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N25)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N39)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N27)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N26)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N40)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N41)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N42)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        );

                                        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
                                        jLabel13.setText("FORMA ---> EMPRESA DE PAGO___________________________________________________________________________________");

                                        L1.setBackground(new java.awt.Color(255, 153, 51));

                                        javax.swing.GroupLayout L1Layout = new javax.swing.GroupLayout(L1);
                                        L1.setLayout(L1Layout);
                                        L1Layout.setHorizontalGroup(
                                            L1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 0, Short.MAX_VALUE)
                                        );
                                        L1Layout.setVerticalGroup(
                                            L1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 8, Short.MAX_VALUE)
                                        );

                                        L2.setBackground(new java.awt.Color(255, 153, 51));

                                        javax.swing.GroupLayout L2Layout = new javax.swing.GroupLayout(L2);
                                        L2.setLayout(L2Layout);
                                        L2Layout.setHorizontalGroup(
                                            L2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 0, Short.MAX_VALUE)
                                        );
                                        L2Layout.setVerticalGroup(
                                            L2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 8, Short.MAX_VALUE)
                                        );

                                        L3.setBackground(new java.awt.Color(255, 153, 51));

                                        javax.swing.GroupLayout L3Layout = new javax.swing.GroupLayout(L3);
                                        L3.setLayout(L3Layout);
                                        L3Layout.setHorizontalGroup(
                                            L3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 0, Short.MAX_VALUE)
                                        );
                                        L3Layout.setVerticalGroup(
                                            L3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 8, Short.MAX_VALUE)
                                        );

                                        L5.setBackground(new java.awt.Color(255, 153, 51));

                                        javax.swing.GroupLayout L5Layout = new javax.swing.GroupLayout(L5);
                                        L5.setLayout(L5Layout);
                                        L5Layout.setHorizontalGroup(
                                            L5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 0, Short.MAX_VALUE)
                                        );
                                        L5Layout.setVerticalGroup(
                                            L5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 8, Short.MAX_VALUE)
                                        );

                                        L6.setBackground(new java.awt.Color(255, 153, 51));

                                        javax.swing.GroupLayout L6Layout = new javax.swing.GroupLayout(L6);
                                        L6.setLayout(L6Layout);
                                        L6Layout.setHorizontalGroup(
                                            L6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 0, Short.MAX_VALUE)
                                        );
                                        L6Layout.setVerticalGroup(
                                            L6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 8, Short.MAX_VALUE)
                                        );

                                        L7.setBackground(new java.awt.Color(255, 153, 51));

                                        javax.swing.GroupLayout L7Layout = new javax.swing.GroupLayout(L7);
                                        L7.setLayout(L7Layout);
                                        L7Layout.setHorizontalGroup(
                                            L7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 0, Short.MAX_VALUE)
                                        );
                                        L7Layout.setVerticalGroup(
                                            L7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 8, Short.MAX_VALUE)
                                        );

                                        ID1.setBackground(new java.awt.Color(255, 255, 255));
                                        ID1.setForeground(new java.awt.Color(255, 255, 255));
                                        ID1.setText("jLabel8");

                                        ID2.setBackground(new java.awt.Color(255, 255, 255));
                                        ID2.setForeground(new java.awt.Color(255, 255, 255));
                                        ID2.setText("jLabel8");

                                        ID3.setBackground(new java.awt.Color(255, 255, 255));
                                        ID3.setForeground(new java.awt.Color(255, 255, 255));
                                        ID3.setText("jLabel8");

                                        ID4.setBackground(new java.awt.Color(255, 255, 255));
                                        ID4.setForeground(new java.awt.Color(255, 255, 255));
                                        ID4.setText("jLabel8");

                                        ID5.setBackground(new java.awt.Color(255, 255, 255));
                                        ID5.setForeground(new java.awt.Color(255, 255, 255));
                                        ID5.setText("jLabel8");

                                        ID6.setBackground(new java.awt.Color(255, 255, 255));
                                        ID6.setForeground(new java.awt.Color(255, 255, 255));
                                        ID6.setText("jLabel8");

                                        ID7.setBackground(new java.awt.Color(255, 255, 255));
                                        ID7.setForeground(new java.awt.Color(255, 255, 255));
                                        ID7.setText("jLabel8");

                                        n30.setBackground(new java.awt.Color(241, 240, 240));
                                        n30.setForeground(new java.awt.Color(230, 230, 230));

                                        N28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N28.setText("jLabel2");

                                        N29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N29.setText("jLabel2");

                                        N30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N30.setText("jLabel2");

                                        N31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N31.setText("jLabel2");

                                        N32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N32.setText("jLabel2");

                                        N33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N33.setText("jLabel2");

                                        N34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N34.setText("jLabel2");

                                        N35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N35.setText("jLabel2");

                                        N36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N36.setText("jLabel2");

                                        N37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N37.setText("jLabel2");

                                        N38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N38.setText("jLabel2");

                                        jPanel29.setBackground(new java.awt.Color(0, 153, 153));

                                        n112.setForeground(new java.awt.Color(255, 255, 255));
                                        n112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Tipo de archivo de libro genérico-20 (2).png"))); // NOI18N
                                        n112.setText("jLabel11");

                                        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
                                        jPanel29.setLayout(jPanel29Layout);
                                        jPanel29Layout.setHorizontalGroup(
                                            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel29Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(n112)
                                                .addContainerGap(48, Short.MAX_VALUE))
                                        );
                                        jPanel29Layout.setVerticalGroup(
                                            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                                .addContainerGap(18, Short.MAX_VALUE)
                                                .addComponent(n112)
                                                .addContainerGap())
                                        );

                                        jPanel8.setBackground(new java.awt.Color(153, 153, 153));

                                        btnNuevo5.setBackground(new java.awt.Color(204, 204, 204));
                                        btnNuevo5.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                        btnNuevo5.setForeground(new java.awt.Color(102, 102, 102));
                                        btnNuevo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Add Column-30.png"))); // NOI18N
                                        btnNuevo5.setMnemonic('N');
                                        btnNuevo5.setContentAreaFilled(false);
                                        btnNuevo5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        btnNuevo5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                        btnNuevo5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                        btnNuevo5.setIconTextGap(30);
                                        btnNuevo5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                        btnNuevo5.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                btnNuevo5ActionPerformed(evt);
                                            }
                                        });

                                        N46.setForeground(new java.awt.Color(255, 255, 255));
                                        N46.setText("Agregar");

                                        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                                        jPanel8.setLayout(jPanel8Layout);
                                        jPanel8Layout.setHorizontalGroup(
                                            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(btnNuevo5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(N46)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        );
                                        jPanel8Layout.setVerticalGroup(
                                            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnNuevo5))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(N46)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );

                                        javax.swing.GroupLayout n30Layout = new javax.swing.GroupLayout(n30);
                                        n30.setLayout(n30Layout);
                                        n30Layout.setHorizontalGroup(
                                            n30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(n30Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(n30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(n30Layout.createSequentialGroup()
                                                        .addGroup(n30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(N28)
                                                            .addComponent(N33)
                                                            .addComponent(N35)
                                                            .addComponent(N36))
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                    .addComponent(N38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(n30Layout.createSequentialGroup()
                                                        .addGroup(n30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(N29)
                                                            .addComponent(N30)
                                                            .addComponent(N31)
                                                            .addComponent(N32)
                                                            .addComponent(N37)
                                                            .addComponent(N34))
                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        );
                                        n30Layout.setVerticalGroup(
                                            n30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, n30Layout.createSequentialGroup()
                                                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(N28)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N29)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N30)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N31)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N32)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N33)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N34)
                                                .addGap(12, 12, 12)
                                                .addComponent(N35)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N36)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N37)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N38)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        );

                                        n32.setBackground(new java.awt.Color(241, 240, 240));
                                        n32.setForeground(new java.awt.Color(230, 230, 230));

                                        N54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N54.setText("jLabel2");

                                        N55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N55.setText("jLabel2");

                                        N56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N56.setText("jLabel2");

                                        N57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N57.setText("jLabel2");

                                        N58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N58.setText("jLabel2");

                                        N59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N59.setText("jLabel2");

                                        N60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N60.setText("jLabel2");

                                        N61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N61.setText("jLabel2");

                                        N62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N62.setText("jLabel2");

                                        N63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N63.setText("jLabel2");

                                        N64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N64.setText("jLabel2");

                                        jPanel30.setBackground(new java.awt.Color(0, 153, 153));

                                        n113.setForeground(new java.awt.Color(255, 255, 255));
                                        n113.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Tipo de archivo de libro genérico-20 (2).png"))); // NOI18N
                                        n113.setText("jLabel11");

                                        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                                        jPanel30.setLayout(jPanel30Layout);
                                        jPanel30Layout.setHorizontalGroup(
                                            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel30Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(n113)
                                                .addContainerGap(47, Short.MAX_VALUE))
                                        );
                                        jPanel30Layout.setVerticalGroup(
                                            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                                                .addContainerGap(18, Short.MAX_VALUE)
                                                .addComponent(n113)
                                                .addContainerGap())
                                        );

                                        jPanel9.setBackground(new java.awt.Color(153, 153, 153));

                                        btnNuevo6.setBackground(new java.awt.Color(204, 204, 204));
                                        btnNuevo6.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                        btnNuevo6.setForeground(new java.awt.Color(102, 102, 102));
                                        btnNuevo6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Add Column-30.png"))); // NOI18N
                                        btnNuevo6.setMnemonic('N');
                                        btnNuevo6.setContentAreaFilled(false);
                                        btnNuevo6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        btnNuevo6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                        btnNuevo6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                        btnNuevo6.setIconTextGap(30);
                                        btnNuevo6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                        btnNuevo6.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                btnNuevo6ActionPerformed(evt);
                                            }
                                        });

                                        N47.setForeground(new java.awt.Color(255, 255, 255));
                                        N47.setText("Agregar");

                                        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                                        jPanel9.setLayout(jPanel9Layout);
                                        jPanel9Layout.setHorizontalGroup(
                                            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addComponent(btnNuevo6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(N47)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        );
                                        jPanel9Layout.setVerticalGroup(
                                            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnNuevo6))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(N47)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );

                                        javax.swing.GroupLayout n32Layout = new javax.swing.GroupLayout(n32);
                                        n32.setLayout(n32Layout);
                                        n32Layout.setHorizontalGroup(
                                            n32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(n32Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(n32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(N64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(n32Layout.createSequentialGroup()
                                                        .addGroup(n32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(N61)
                                                            .addComponent(N57)
                                                            .addComponent(N60)
                                                            .addComponent(N56)
                                                            .addComponent(N58)
                                                            .addComponent(N54))
                                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGroup(n32Layout.createSequentialGroup()
                                                        .addGroup(n32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(N59)
                                                            .addComponent(N63)
                                                            .addComponent(N55)
                                                            .addComponent(N62))
                                                        .addGap(0, 0, Short.MAX_VALUE))))
                                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        );
                                        n32Layout.setVerticalGroup(
                                            n32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(n32Layout.createSequentialGroup()
                                                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(N54)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N55)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N56)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N57)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N58)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N59)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N60)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N61)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N62)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N63)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N64)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        );

                                        n33.setBackground(new java.awt.Color(241, 240, 240));
                                        n33.setForeground(new java.awt.Color(230, 230, 230));

                                        N65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N65.setText("jLabel2");

                                        N66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N66.setText("jLabel2");

                                        N67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N67.setText("jLabel2");

                                        N68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N68.setText("jLabel2");

                                        N69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N69.setText("jLabel2");

                                        N70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N70.setText("jLabel2");

                                        N71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N71.setText("jLabel2");

                                        N72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N72.setText("jLabel2");

                                        N73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N73.setText("jLabel2");

                                        N74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N74.setText("jLabel2");

                                        N75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N75.setText("jLabel2");

                                        jPanel31.setBackground(new java.awt.Color(0, 153, 153));

                                        n114.setForeground(new java.awt.Color(255, 255, 255));
                                        n114.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Tipo de archivo de libro genérico-20 (2).png"))); // NOI18N
                                        n114.setText("jLabel11");

                                        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
                                        jPanel31.setLayout(jPanel31Layout);
                                        jPanel31Layout.setHorizontalGroup(
                                            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel31Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(n114)
                                                .addContainerGap(46, Short.MAX_VALUE))
                                        );
                                        jPanel31Layout.setVerticalGroup(
                                            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                                .addContainerGap(18, Short.MAX_VALUE)
                                                .addComponent(n114)
                                                .addContainerGap())
                                        );

                                        jPanel10.setBackground(new java.awt.Color(153, 153, 153));

                                        btnNuevo7.setBackground(new java.awt.Color(204, 204, 204));
                                        btnNuevo7.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                        btnNuevo7.setForeground(new java.awt.Color(102, 102, 102));
                                        btnNuevo7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Add Column-30.png"))); // NOI18N
                                        btnNuevo7.setMnemonic('N');
                                        btnNuevo7.setContentAreaFilled(false);
                                        btnNuevo7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        btnNuevo7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                        btnNuevo7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                        btnNuevo7.setIconTextGap(30);
                                        btnNuevo7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                        btnNuevo7.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                btnNuevo7ActionPerformed(evt);
                                            }
                                        });

                                        N48.setForeground(new java.awt.Color(255, 255, 255));
                                        N48.setText("Agregar");

                                        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                                        jPanel10.setLayout(jPanel10Layout);
                                        jPanel10Layout.setHorizontalGroup(
                                            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(btnNuevo7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(N48)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        );
                                        jPanel10Layout.setVerticalGroup(
                                            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnNuevo7))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(N48)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );

                                        javax.swing.GroupLayout n33Layout = new javax.swing.GroupLayout(n33);
                                        n33.setLayout(n33Layout);
                                        n33Layout.setHorizontalGroup(
                                            n33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(n33Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(n33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(n33Layout.createSequentialGroup()
                                                        .addGroup(n33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(n33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(N70)
                                                                .addGroup(n33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(N65)
                                                                    .addComponent(N66)
                                                                    .addComponent(N67)
                                                                    .addComponent(N68)
                                                                    .addComponent(N69)))
                                                            .addComponent(N71)
                                                            .addComponent(N72)
                                                            .addComponent(N73)
                                                            .addComponent(N74))
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                    .addComponent(N75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap())
                                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        );
                                        n33Layout.setVerticalGroup(
                                            n33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, n33Layout.createSequentialGroup()
                                                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(N65)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N66)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N67)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N68)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N69)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N70)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N71)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N72)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N73)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N74)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N75)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        );

                                        n34.setBackground(new java.awt.Color(241, 240, 240));
                                        n34.setForeground(new java.awt.Color(230, 230, 230));

                                        N76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N76.setText("jLabel2");

                                        N77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N77.setText("jLabel2");

                                        N78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N78.setText("jLabel2");

                                        N79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N79.setText("jLabel2");

                                        N80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N80.setText("jLabel2");

                                        N81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N81.setText("jLabel2");

                                        N82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N82.setText("jLabel2");

                                        N83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N83.setText("jLabel2");

                                        N84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N84.setText("jLabel2");

                                        N85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N85.setText("jLabel2");

                                        N86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N86.setText("jLabel2");

                                        jPanel33.setBackground(new java.awt.Color(0, 153, 153));

                                        n115.setForeground(new java.awt.Color(255, 255, 255));
                                        n115.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Tipo de archivo de libro genérico-20 (2).png"))); // NOI18N
                                        n115.setText("jLabel11");

                                        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
                                        jPanel33.setLayout(jPanel33Layout);
                                        jPanel33Layout.setHorizontalGroup(
                                            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel33Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(n115)
                                                .addContainerGap(47, Short.MAX_VALUE))
                                        );
                                        jPanel33Layout.setVerticalGroup(
                                            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                                                .addContainerGap(18, Short.MAX_VALUE)
                                                .addComponent(n115)
                                                .addContainerGap())
                                        );

                                        jPanel11.setBackground(new java.awt.Color(153, 153, 153));

                                        btnNuevo8.setBackground(new java.awt.Color(204, 204, 204));
                                        btnNuevo8.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                        btnNuevo8.setForeground(new java.awt.Color(102, 102, 102));
                                        btnNuevo8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Add Column-30.png"))); // NOI18N
                                        btnNuevo8.setMnemonic('N');
                                        btnNuevo8.setContentAreaFilled(false);
                                        btnNuevo8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        btnNuevo8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                        btnNuevo8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                        btnNuevo8.setIconTextGap(30);
                                        btnNuevo8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                        btnNuevo8.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                btnNuevo8ActionPerformed(evt);
                                            }
                                        });

                                        N49.setForeground(new java.awt.Color(255, 255, 255));
                                        N49.setText("Agregar");

                                        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                                        jPanel11.setLayout(jPanel11Layout);
                                        jPanel11Layout.setHorizontalGroup(
                                            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(btnNuevo8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(N49)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        );
                                        jPanel11Layout.setVerticalGroup(
                                            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnNuevo8))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(N49)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );

                                        javax.swing.GroupLayout n34Layout = new javax.swing.GroupLayout(n34);
                                        n34.setLayout(n34Layout);
                                        n34Layout.setHorizontalGroup(
                                            n34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, n34Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(n34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(n34Layout.createSequentialGroup()
                                                        .addGroup(n34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(N77)
                                                            .addComponent(N78)
                                                            .addComponent(N79)
                                                            .addComponent(N80)
                                                            .addComponent(N81)
                                                            .addComponent(N76)
                                                            .addComponent(N82)
                                                            .addComponent(N83)
                                                            .addComponent(N84)
                                                            .addComponent(N85))
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                    .addComponent(N86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap())
                                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        );
                                        n34Layout.setVerticalGroup(
                                            n34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(n34Layout.createSequentialGroup()
                                                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(N76)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N77)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N78)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N79)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N80)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N81)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N82)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N83)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N84)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N85)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N86)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        );

                                        L4.setBackground(new java.awt.Color(255, 153, 51));

                                        javax.swing.GroupLayout L4Layout = new javax.swing.GroupLayout(L4);
                                        L4.setLayout(L4Layout);
                                        L4Layout.setHorizontalGroup(
                                            L4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 0, Short.MAX_VALUE)
                                        );
                                        L4Layout.setVerticalGroup(
                                            L4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGap(0, 8, Short.MAX_VALUE)
                                        );

                                        n35.setBackground(new java.awt.Color(241, 240, 240));
                                        n35.setForeground(new java.awt.Color(230, 230, 230));

                                        N87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N87.setText("jLabel2");

                                        N88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N88.setText("jLabel2");

                                        N89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N89.setText("jLabel2");

                                        N90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N90.setText("jLabel2");

                                        N91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N91.setText("jLabel2");

                                        N92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N92.setText("jLabel2");

                                        N93.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N93.setText("jLabel2");

                                        N94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N94.setText("jLabel2");

                                        N95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N95.setText("jLabel2");

                                        N96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N96.setText("jLabel2");

                                        N97.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-16.png"))); // NOI18N
                                        N97.setText("jLabel2");

                                        jPanel34.setBackground(new java.awt.Color(0, 153, 153));

                                        n116.setForeground(new java.awt.Color(255, 255, 255));
                                        n116.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Tipo de archivo de libro genérico-20 (2).png"))); // NOI18N
                                        n116.setText("jLabel11");

                                        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
                                        jPanel34.setLayout(jPanel34Layout);
                                        jPanel34Layout.setHorizontalGroup(
                                            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel34Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(n116)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );
                                        jPanel34Layout.setVerticalGroup(
                                            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                                                .addContainerGap(18, Short.MAX_VALUE)
                                                .addComponent(n116)
                                                .addContainerGap())
                                        );

                                        jPanel12.setBackground(new java.awt.Color(153, 153, 153));

                                        btnNuevo9.setBackground(new java.awt.Color(204, 204, 204));
                                        btnNuevo9.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                                        btnNuevo9.setForeground(new java.awt.Color(102, 102, 102));
                                        btnNuevo9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Add Column-30.png"))); // NOI18N
                                        btnNuevo9.setMnemonic('N');
                                        btnNuevo9.setContentAreaFilled(false);
                                        btnNuevo9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        btnNuevo9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                        btnNuevo9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                        btnNuevo9.setIconTextGap(30);
                                        btnNuevo9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                        btnNuevo9.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                btnNuevo9ActionPerformed(evt);
                                            }
                                        });

                                        N50.setForeground(new java.awt.Color(255, 255, 255));
                                        N50.setText("Agregar");

                                        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                                        jPanel12.setLayout(jPanel12Layout);
                                        jPanel12Layout.setHorizontalGroup(
                                            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addComponent(btnNuevo9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(N50)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        );
                                        jPanel12Layout.setVerticalGroup(
                                            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnNuevo9))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(N50)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );

                                        javax.swing.GroupLayout n35Layout = new javax.swing.GroupLayout(n35);
                                        n35.setLayout(n35Layout);
                                        n35Layout.setHorizontalGroup(
                                            n35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, n35Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(n35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(N91, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(N92, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(N93, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, n35Layout.createSequentialGroup()
                                                        .addGroup(n35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(N90)
                                                            .addComponent(N89)
                                                            .addComponent(N88)
                                                            .addComponent(N87))
                                                        .addGap(46, 46, 46)
                                                        .addGroup(n35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(N97, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, n35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(N96)
                                                                .addComponent(N95)
                                                                .addComponent(N94, javax.swing.GroupLayout.Alignment.LEADING)))))
                                                .addGap(25, 25, 25))
                                            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        );
                                        n35Layout.setVerticalGroup(
                                            n35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(n35Layout.createSequentialGroup()
                                                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(40, 40, 40)
                                                .addGroup(n35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(n35Layout.createSequentialGroup()
                                                        .addComponent(N87)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(N88)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(N89)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(N90))
                                                    .addGroup(n35Layout.createSequentialGroup()
                                                        .addComponent(N94)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(N95)
                                                        .addGap(6, 6, 6)
                                                        .addComponent(N96)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(N97)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N91)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(N92)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(N93)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        );

                                        jPanel35.setBackground(new java.awt.Color(0, 153, 153));
                                        jPanel35.setPreferredSize(new java.awt.Dimension(84, 49));

                                        n117.setForeground(new java.awt.Color(255, 255, 255));
                                        n117.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Add Column-30.png"))); // NOI18N
                                        n117.setText("Agregar Forma");

                                        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
                                        jPanel35.setLayout(jPanel35Layout);
                                        jPanel35Layout.setHorizontalGroup(
                                            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel35Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(n117)
                                                .addContainerGap(29, Short.MAX_VALUE))
                                        );
                                        jPanel35Layout.setVerticalGroup(
                                            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(n117)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );

                                        codigo.setText("jLabel7");

                                        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                                        jPanel3.setLayout(jPanel3Layout);
                                        jPanel3Layout.setHorizontalGroup(
                                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(ID1)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(ID2)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(ID3)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(ID4)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(ID5)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(ID6)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(ID7))
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                    .addComponent(L1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(T1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(T2, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                                                    .addComponent(L2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(T3, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                                                    .addComponent(L3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(T4, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                                                    .addComponent(L4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(T5, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                                            .addComponent(L5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(T6, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                                            .addComponent(L6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(T7, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(L7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(CODN0)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(CODN1)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(CODN2)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(CODN3)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(CODN4)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(CODN5)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(CODN6))
                                                            .addComponent(jLabel9)
                                                            .addComponent(jLabel13)
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addComponent(n29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(n30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(n32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(n33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(n34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(n35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addComponent(codigo))
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addGap(107, 107, 107)
                                                                        .addComponent(n27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                        .addGap(0, 78, Short.MAX_VALUE)))
                                                .addContainerGap())
                                        );
                                        jPanel3Layout.setVerticalGroup(
                                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(CODN0)
                                                    .addComponent(CODN1)
                                                    .addComponent(CODN2)
                                                    .addComponent(CODN3)
                                                    .addComponent(CODN4)
                                                    .addComponent(CODN5)
                                                    .addComponent(CODN6))
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(T4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(T1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(T2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(T5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(T6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(T7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(L1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(L4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(ID1)
                                                    .addComponent(ID2)
                                                    .addComponent(ID3)
                                                    .addComponent(ID4)
                                                    .addComponent(ID5)
                                                    .addComponent(ID6)
                                                    .addComponent(ID7))
                                                .addGap(1, 1, 1)
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(183, 183, 183)
                                                        .addComponent(n27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(n35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(n34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(n33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(n32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(n30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(n29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(codigo)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        );

                                        jTabbedPane1.addTab("Listado", jPanel3);

                                        NIVEL0.setModel(new javax.swing.table.DefaultTableModel(
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
                                        NIVEL0.setGridColor(new java.awt.Color(255, 255, 255));
                                        NIVEL0.setRowHeight(25);
                                        NIVEL0.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                        NIVEL0.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                NIVEL0MouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                NIVEL0MousePressed(evt);
                                            }
                                        });
                                        NIVEL0.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                NIVEL0KeyPressed(evt);
                                            }
                                        });
                                        jScrollPane3.setViewportView(NIVEL0);

                                        NIVEL1.setModel(new javax.swing.table.DefaultTableModel(
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
                                        NIVEL1.setGridColor(new java.awt.Color(255, 255, 255));
                                        NIVEL1.setRowHeight(25);
                                        NIVEL1.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                        NIVEL1.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                NIVEL1MouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                NIVEL1MousePressed(evt);
                                            }
                                        });
                                        NIVEL1.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                NIVEL1KeyPressed(evt);
                                            }
                                        });
                                        jScrollPane6.setViewportView(NIVEL1);

                                        NIVEL21.setModel(new javax.swing.table.DefaultTableModel(
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
                                        NIVEL21.setGridColor(new java.awt.Color(255, 255, 255));
                                        NIVEL21.setRowHeight(25);
                                        NIVEL21.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                        NIVEL21.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                NIVEL21MouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                NIVEL21MousePressed(evt);
                                            }
                                        });
                                        NIVEL21.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                NIVEL21KeyPressed(evt);
                                            }
                                        });
                                        jScrollPane7.setViewportView(NIVEL21);

                                        NIVEL22.setModel(new javax.swing.table.DefaultTableModel(
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
                                        NIVEL22.setGridColor(new java.awt.Color(255, 255, 255));
                                        NIVEL22.setRowHeight(25);
                                        NIVEL22.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                        NIVEL22.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                NIVEL22MouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                NIVEL22MousePressed(evt);
                                            }
                                        });
                                        NIVEL22.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                NIVEL22KeyPressed(evt);
                                            }
                                        });
                                        jScrollPane8.setViewportView(NIVEL22);

                                        NIVEL23.setModel(new javax.swing.table.DefaultTableModel(
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
                                        NIVEL23.setGridColor(new java.awt.Color(255, 255, 255));
                                        NIVEL23.setRowHeight(25);
                                        NIVEL23.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                        NIVEL23.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                NIVEL23MouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                NIVEL23MousePressed(evt);
                                            }
                                        });
                                        NIVEL23.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                NIVEL23KeyPressed(evt);
                                            }
                                        });
                                        jScrollPane9.setViewportView(NIVEL23);

                                        NIVEL24.setModel(new javax.swing.table.DefaultTableModel(
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
                                        NIVEL24.setGridColor(new java.awt.Color(255, 255, 255));
                                        NIVEL24.setRowHeight(25);
                                        NIVEL24.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                        NIVEL24.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                NIVEL24MouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                NIVEL24MousePressed(evt);
                                            }
                                        });
                                        NIVEL24.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                NIVEL24KeyPressed(evt);
                                            }
                                        });
                                        jScrollPane10.setViewportView(NIVEL24);

                                        NIVEL25.setModel(new javax.swing.table.DefaultTableModel(
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
                                        NIVEL25.setGridColor(new java.awt.Color(255, 255, 255));
                                        NIVEL25.setRowHeight(25);
                                        NIVEL25.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                        NIVEL25.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                NIVEL25MouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                NIVEL25MousePressed(evt);
                                            }
                                        });
                                        NIVEL25.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                NIVEL25KeyPressed(evt);
                                            }
                                        });
                                        jScrollPane11.setViewportView(NIVEL25);

                                        NIVEL26.setModel(new javax.swing.table.DefaultTableModel(
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
                                        NIVEL26.setGridColor(new java.awt.Color(255, 255, 255));
                                        NIVEL26.setRowHeight(25);
                                        NIVEL26.setSelectionBackground(new java.awt.Color(0, 153, 153));
                                        NIVEL26.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                NIVEL26MouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                NIVEL26MousePressed(evt);
                                            }
                                        });
                                        NIVEL26.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                NIVEL26KeyPressed(evt);
                                            }
                                        });
                                        jScrollPane12.setViewportView(NIVEL26);

                                        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                                        jPanel5.setLayout(jPanel5Layout);
                                        jPanel5Layout.setHorizontalGroup(
                                            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                                .addContainerGap())
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE)
                                                    .addContainerGap()))
                                        );
                                        jPanel5Layout.setVerticalGroup(
                                            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addContainerGap(333, Short.MAX_VALUE)
                                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap())
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addGap(13, 13, 13)
                                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addContainerGap(635, Short.MAX_VALUE)))
                                        );

                                        jTabbedPane1.addTab("tab3", jPanel5);

                                        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                                        jPanel2.setLayout(jPanel2Layout);
                                        jPanel2Layout.setHorizontalGroup(
                                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );
                                        jPanel2Layout.setVerticalGroup(
                                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jTabbedPane1)
                                                .addContainerGap())
                                        );

                                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                        getContentPane().setLayout(layout);
                                        layout.setHorizontalGroup(
                                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        );
                                        layout.setVerticalGroup(
                                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );

                                        pack();
                                    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
      
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
       
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
      
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        jTabbedPane1.setSelectedIndex(0);
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btneliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneliminar1ActionPerformed

    private void NIVEL1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIVEL1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL1KeyPressed

    private void NIVEL1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL1MousePressed

    private void NIVEL1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL1MouseClicked

    private void NIVEL0KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIVEL0KeyPressed

    }//GEN-LAST:event_NIVEL0KeyPressed

    private void NIVEL0MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL0MousePressed

    }//GEN-LAST:event_NIVEL0MousePressed

    private void NIVEL0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL0MouseClicked

    }//GEN-LAST:event_NIVEL0MouseClicked

    private void NIVEL21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL21MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL21MouseClicked

    private void NIVEL21MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL21MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL21MousePressed

    private void NIVEL21KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIVEL21KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL21KeyPressed

    private void NIVEL22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL22MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL22MouseClicked

    private void NIVEL22MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL22MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL22MousePressed

    private void NIVEL22KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIVEL22KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL22KeyPressed

    private void NIVEL23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL23MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL23MouseClicked

    private void NIVEL23MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL23MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL23MousePressed

    private void NIVEL23KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIVEL23KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL23KeyPressed

    private void NIVEL24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL24MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL24MouseClicked

    private void NIVEL24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL24MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL24MousePressed

    private void NIVEL24KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIVEL24KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL24KeyPressed

    private void NIVEL25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL25MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL25MouseClicked

    private void NIVEL25MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL25MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL25MousePressed

    private void NIVEL25KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIVEL25KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL25KeyPressed

    private void NIVEL26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL26MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL26MouseClicked

    private void NIVEL26MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NIVEL26MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL26MousePressed

    private void NIVEL26KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIVEL26KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NIVEL26KeyPressed

    private void btnNuevo9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo9ActionPerformed

    private void btnNuevo8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo8ActionPerformed

    private void btnNuevo7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo7ActionPerformed

    private void btnNuevo6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo6ActionPerformed

    private void btnNuevo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo5ActionPerformed

    private void btnNuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo2ActionPerformed
          codigo.setText(cnn.idCJ());
          
           G21.setVisible(true);
     
    }//GEN-LAST:event_btnNuevo2ActionPerformed

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        LIMPIAR();
        L1.setVisible(false);
        L2.setVisible(false);
        L3.setVisible(false);
        L4.setVisible(false);
        L5.setVisible(false);
        L6.setVisible(false);
        L7.setVisible(true);

        CODN6.setText(cnn.codTipo(T7.getText()));
        Buscar15();
        Nivel1();
        Nivel1dETALLE();
        visible();
        Buscar21();
        Buscar22();
        Buscar23();
        Buscar24();
        Buscar25();
        Buscar26();

        Nivel21();
        Nivel22();
        Nivel23();
        Nivel24();
        Nivel25();
        Nivel26();
        visibleNivel();
    }//GEN-LAST:event_T7MouseClicked

    private void T6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T6MouseClicked
        LIMPIAR();
        L1.setVisible(false);
        L2.setVisible(false);
        L3.setVisible(false);
        L4.setVisible(false);
        L5.setVisible(false);
        L6.setVisible(true);
        L7.setVisible(false);
        CODN5.setText(cnn.codTipo(T6.getText()));
        Buscar14();
        Nivel1();
        Nivel1dETALLE();
        visible();
        Buscar21();
        Buscar22();
        Buscar23();
        Buscar24();
        Buscar25();
        Buscar26();

        Nivel21();
        Nivel22();
        Nivel23();
        Nivel24();
        Nivel25();
        Nivel26();
        visibleNivel();
    }//GEN-LAST:event_T6MouseClicked

    private void T5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T5MouseClicked
        LIMPIAR();
        L1.setVisible(false);
        L2.setVisible(false);
        L3.setVisible(false);
        L4.setVisible(false);
        L5.setVisible(true);
        L6.setVisible(false);
        L7.setVisible(false);
        CODN4.setText(cnn.codTipo(T5.getText()));
        Buscar13();
        Nivel1();
        Nivel1dETALLE();
        visible();
        Buscar21();
        Buscar22();
        Buscar23();
        Buscar24();
        Buscar25();
        Buscar26();

        Nivel21();
        Nivel22();
        Nivel23();
        Nivel24();
        Nivel25();
        Nivel26();
        visibleNivel();
    }//GEN-LAST:event_T5MouseClicked

    private void T4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T4MouseClicked
        LIMPIAR();
        L1.setVisible(false);
        L2.setVisible(false);
        L3.setVisible(false);
        L4.setVisible(true);
        L5.setVisible(false);
        L6.setVisible(false);
        L7.setVisible(false);
        CODN3.setText(cnn.codTipo(T4.getText()));
        Buscar12();
        Nivel1();
        Nivel1dETALLE();
        visible();
        Buscar21();
        Buscar22();
        Buscar23();
        Buscar24();
        Buscar25();
        Buscar26();

        Nivel21();
        Nivel22();
        Nivel23();
        Nivel24();
        Nivel25();
        Nivel26();
        visibleNivel();
    }//GEN-LAST:event_T4MouseClicked

    private void T3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T3MouseClicked
        LIMPIAR();
        L1.setVisible(false);
        L2.setVisible(false);
        L3.setVisible(true);
        L4.setVisible(false);
        L5.setVisible(false);
        L6.setVisible(false);
        L7.setVisible(false);
        CODN2.setText(cnn.codTipo(T3.getText()));
        Buscar11();
        Nivel1();
        Nivel1dETALLE();
        visible();

        Buscar21();
        Buscar22();
        Buscar23();
        Buscar24();
        Buscar25();
        Buscar26();

        Nivel21();
        Nivel22();
        Nivel23();
        Nivel24();
        Nivel25();
        Nivel26();
        visibleNivel();
    }//GEN-LAST:event_T3MouseClicked

    private void T2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T2MouseClicked
        LIMPIAR();
        L1.setVisible(false);
        L2.setVisible(true);
        L3.setVisible(false);
        L4.setVisible(false);
        L5.setVisible(false);
        L6.setVisible(false);
        L7.setVisible(false);
        CODN1.setText(cnn.codTipo(T2.getText()));
        Buscar1();
        Nivel1();

        Nivel1dETALLE();
        visible();

        Buscar21();
        Buscar22();
        Buscar23();
        Buscar24();
        Buscar25();
        Buscar26();

        Nivel21();
        Nivel22();
        Nivel23();
        Nivel24();
        Nivel25();
        Nivel26();
        visibleNivel();

    }//GEN-LAST:event_T2MouseClicked

    private void T1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T1MouseClicked
        LIMPIAR();
        L1.setVisible(true);
        L2.setVisible(false);
        L3.setVisible(false);
        L4.setVisible(false);
        L5.setVisible(false);
        L6.setVisible(false);
        L7.setVisible(false);

        CODN0.setText(cnn.codTipo(T1.getText()));
        Buscar0();
        Nivel1();
        Nivel1dETALLE();
        visible();

        Buscar21();
        Buscar22();
        Buscar23();
        Buscar24();
        Buscar25();
        Buscar26();

        Nivel21();
        Nivel22();
        Nivel23();
        Nivel24();
        Nivel25();
        Nivel26();

        visibleNivel();

    }//GEN-LAST:event_T1MouseClicked

    private void btnguardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar1ActionPerformed
    
            GuardarN2();
                   Buscar0();
        Nivel1();
        Nivel1dETALLE();
        visible();

        Buscar21();
        Buscar22();
        Buscar23();
        Buscar24();
        Buscar25();
        Buscar26();

        Nivel21();
        Nivel22();
        Nivel23();
        Nivel24();
        Nivel25();
        Nivel26();

        visibleNivel();
     
    }//GEN-LAST:event_btnguardar1ActionPerformed

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
            java.util.logging.Logger.getLogger(Caja_Jerarquias2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Jerarquias2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Jerarquias2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Jerarquias2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Jerarquias2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CODN0;
    private javax.swing.JLabel CODN1;
    private javax.swing.JLabel CODN2;
    private javax.swing.JLabel CODN3;
    private javax.swing.JLabel CODN4;
    private javax.swing.JLabel CODN5;
    private javax.swing.JLabel CODN6;
    private javax.swing.JTextField EP;
    private javax.swing.JDialog G21;
    private javax.swing.JLabel ID1;
    private javax.swing.JLabel ID2;
    private javax.swing.JLabel ID3;
    private javax.swing.JLabel ID4;
    private javax.swing.JLabel ID5;
    private javax.swing.JLabel ID6;
    private javax.swing.JLabel ID7;
    private javax.swing.JPanel L1;
    private javax.swing.JPanel L2;
    private javax.swing.JPanel L3;
    private javax.swing.JPanel L4;
    private javax.swing.JPanel L5;
    private javax.swing.JPanel L6;
    private javax.swing.JPanel L7;
    private javax.swing.JLabel N21;
    private javax.swing.JLabel N22;
    private javax.swing.JLabel N23;
    private javax.swing.JLabel N24;
    private javax.swing.JLabel N25;
    private javax.swing.JLabel N26;
    private javax.swing.JLabel N27;
    private javax.swing.JLabel N28;
    private javax.swing.JLabel N29;
    private javax.swing.JLabel N30;
    private javax.swing.JLabel N31;
    private javax.swing.JLabel N32;
    private javax.swing.JLabel N33;
    private javax.swing.JLabel N34;
    private javax.swing.JLabel N35;
    private javax.swing.JLabel N36;
    private javax.swing.JLabel N37;
    private javax.swing.JLabel N38;
    private javax.swing.JLabel N39;
    private javax.swing.JLabel N40;
    private javax.swing.JLabel N41;
    private javax.swing.JLabel N42;
    private javax.swing.JLabel N43;
    private javax.swing.JLabel N44;
    private javax.swing.JLabel N46;
    private javax.swing.JLabel N47;
    private javax.swing.JLabel N48;
    private javax.swing.JLabel N49;
    private javax.swing.JLabel N50;
    private javax.swing.JLabel N54;
    private javax.swing.JLabel N55;
    private javax.swing.JLabel N56;
    private javax.swing.JLabel N57;
    private javax.swing.JLabel N58;
    private javax.swing.JLabel N59;
    private javax.swing.JLabel N60;
    private javax.swing.JLabel N61;
    private javax.swing.JLabel N62;
    private javax.swing.JLabel N63;
    private javax.swing.JLabel N64;
    private javax.swing.JLabel N65;
    private javax.swing.JLabel N66;
    private javax.swing.JLabel N67;
    private javax.swing.JLabel N68;
    private javax.swing.JLabel N69;
    private javax.swing.JLabel N70;
    private javax.swing.JLabel N71;
    private javax.swing.JLabel N72;
    private javax.swing.JLabel N73;
    private javax.swing.JLabel N74;
    private javax.swing.JLabel N75;
    private javax.swing.JLabel N76;
    private javax.swing.JLabel N77;
    private javax.swing.JLabel N78;
    private javax.swing.JLabel N79;
    private javax.swing.JLabel N80;
    private javax.swing.JLabel N81;
    private javax.swing.JLabel N82;
    private javax.swing.JLabel N83;
    private javax.swing.JLabel N84;
    private javax.swing.JLabel N85;
    private javax.swing.JLabel N86;
    private javax.swing.JLabel N87;
    private javax.swing.JLabel N88;
    private javax.swing.JLabel N89;
    private javax.swing.JLabel N90;
    private javax.swing.JLabel N91;
    private javax.swing.JLabel N92;
    private javax.swing.JLabel N93;
    private javax.swing.JLabel N94;
    private javax.swing.JLabel N95;
    private javax.swing.JLabel N96;
    private javax.swing.JLabel N97;
    private javax.swing.JTable NIVEL0;
    private javax.swing.JTable NIVEL1;
    private javax.swing.JTable NIVEL21;
    private javax.swing.JTable NIVEL22;
    private javax.swing.JTable NIVEL23;
    private javax.swing.JTable NIVEL24;
    private javax.swing.JTable NIVEL25;
    private javax.swing.JTable NIVEL26;
    private javax.swing.JLabel T1;
    private javax.swing.JLabel T2;
    private javax.swing.JLabel T3;
    private javax.swing.JLabel T4;
    private javax.swing.JLabel T5;
    private javax.swing.JLabel T6;
    private javax.swing.JLabel T7;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo2;
    private javax.swing.JButton btnNuevo3;
    private javax.swing.JButton btnNuevo5;
    private javax.swing.JButton btnNuevo6;
    private javax.swing.JButton btnNuevo7;
    private javax.swing.JButton btnNuevo8;
    private javax.swing.JButton btnNuevo9;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btneliminar1;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnguardar1;
    private javax.swing.JLabel codigo;
    private javax.swing.JTextField des;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblusu;
    private javax.swing.JLabel n111;
    private javax.swing.JLabel n112;
    private javax.swing.JLabel n113;
    private javax.swing.JLabel n114;
    private javax.swing.JLabel n115;
    private javax.swing.JLabel n116;
    private javax.swing.JLabel n117;
    private javax.swing.JPanel n27;
    private javax.swing.JPanel n29;
    private javax.swing.JPanel n30;
    private javax.swing.JPanel n32;
    private javax.swing.JPanel n33;
    private javax.swing.JPanel n34;
    private javax.swing.JPanel n35;
    // End of variables declaration//GEN-END:variables
}
