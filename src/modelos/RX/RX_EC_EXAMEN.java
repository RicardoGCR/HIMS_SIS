/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos.RX;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
/**
 *
 * @author USUARIO
 */
public class RX_EC_EXAMEN {
    private Connection cn;
    private String ID_DOCUMENTO;
    private String NUMERO_EXAMEN;
    private String COD_PERSONAL_EXAMEN;
    private String NOMBRE_PERSONAL_EXAMEN;
    private String COD_PERSONAL_EXAMEN_REG;
    private String NOMBRE_PERSONAL_EXAMEN_REG;
    private String FECHA_REG;
    private String HORA_REG;
    private String FECHA_ACTU;
    private String HORA_ACTU;
    private String NOM_PC;
    DefaultTableModel m;
    
    public RX_EC_EXAMEN()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public String RX_EC_EXAMEN_generarNum()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec RX_EC_EXAMEN_GENERAR_NUM";
        ResultSet r;
        r=cn.Listar(consulta);
        if(r.next())
            {
               cod = r.getString(1);
            }
        }catch(Exception ex)
        {
            System.out.println("Error generar numero: " + ex.getMessage());
        }
        return cod;
    }
    
    public boolean RX_EC_EXAMEN_GUARDAR(){
        boolean resp = false;
        try
        {
            String sql = "exec RX_EC_EXAMEN_CABECERA_GUARDAR ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_DOCUMENTO());
            cmd.setString(2, getNUMERO_EXAMEN());
            cmd.setString(3, getCOD_PERSONAL_EXAMEN());
            cmd.setString(4, getNOMBRE_PERSONAL_EXAMEN());
            cmd.setString(5, getCOD_PERSONAL_EXAMEN_REG());
            cmd.setString(6, getNOMBRE_PERSONAL_EXAMEN_REG());          
            cmd.setString(7, getNOM_PC());
            
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error guardar cabecera ex: " + ex.getMessage());
        }
        return resp;
    }

    public String RX_EC_ID_CAB_RX()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec RX_EC_ID_CAB_EX";
        ResultSet r;
        r=cn.Listar(consulta);
        if(r.next())
            {
               cod = r.getString(1);
            }
        }catch(Exception ex)
        {
            System.out.println("Error CODIGO: " + ex.getMessage());
        }
        return cod;
    }
    
    public void LISTA_CABECERAS(JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID_CAJAº NUM_EXA","COD_PERS","NOM_PERS","COD_PERS_1","NOM_PERS_1","PC","FP","REF"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC RX_EC_BUSCAR_CAJA_FECHAS_RX_1";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
//            cmd.setString(1, descripcion);
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
                    m.addRow(fila);
                    c++;
//                    a++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR : " + e.getMessage());
        }
    }
    
    
    public void LISTA_DETALLES(String ID,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Cod. Documento Det.","Cod. Nomenclatura","Descripción Nomenclatura",
                           "Incidencia" ,"Placas Usadas", "Medida","Producto"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[7];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC RX_EC_VER_DETALLE_EXAMEN ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ID);
            ResultSet r= cmd.executeQuery();
            int c=1, usado=0;
           
            while(r.next()){
//                        for (int i=0; i<5; i++){
                        fila[0]=r.getString(1);
                        fila[1]=r.getString(2); 
                        fila[2]=r.getString(3); 
                        fila[3]=r.getString(4);
                        
                        fila[4]=r.getString(5);
                        fila[5]=r.getString(6); 
                        fila[6]=r.getString(7);
//                      
                        
                          m.addRow(fila);
                        }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR : " + e.getMessage());
        }
    }
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getID_DOCUMENTO() {
        return ID_DOCUMENTO;
    }

    public void setID_DOCUMENTO(String ID_DOCUMENTO) {
        this.ID_DOCUMENTO = ID_DOCUMENTO;
    }

    public String getNUMERO_EXAMEN() {
        return NUMERO_EXAMEN;
    }

    public void setNUMERO_EXAMEN(String NUMERO_EXAMEN) {
        this.NUMERO_EXAMEN = NUMERO_EXAMEN;
    }

    public String getCOD_PERSONAL_EXAMEN() {
        return COD_PERSONAL_EXAMEN;
    }

    public void setCOD_PERSONAL_EXAMEN(String COD_PERSONAL_EXAMEN) {
        this.COD_PERSONAL_EXAMEN = COD_PERSONAL_EXAMEN;
    }

    public String getNOMBRE_PERSONAL_EXAMEN() {
        return NOMBRE_PERSONAL_EXAMEN;
    }

    public void setNOMBRE_PERSONAL_EXAMEN(String NOMBRE_PERSONAL_EXAMEN) {
        this.NOMBRE_PERSONAL_EXAMEN = NOMBRE_PERSONAL_EXAMEN;
    }

    public String getCOD_PERSONAL_EXAMEN_REG() {
        return COD_PERSONAL_EXAMEN_REG;
    }

    public void setCOD_PERSONAL_EXAMEN_REG(String COD_PERSONAL_EXAMEN_REG) {
        this.COD_PERSONAL_EXAMEN_REG = COD_PERSONAL_EXAMEN_REG;
    }

    public String getNOMBRE_PERSONAL_EXAMEN_REG() {
        return NOMBRE_PERSONAL_EXAMEN_REG;
    }

    public void setNOMBRE_PERSONAL_EXAMEN_REG(String NOMBRE_PERSONAL_EXAMEN_REG) {
        this.NOMBRE_PERSONAL_EXAMEN_REG = NOMBRE_PERSONAL_EXAMEN_REG;
    }

    public String getFECHA_REG() {
        return FECHA_REG;
    }

    public void setFECHA_REG(String FECHA_REG) {
        this.FECHA_REG = FECHA_REG;
    }

    public String getHORA_REG() {
        return HORA_REG;
    }

    public void setHORA_REG(String HORA_REG) {
        this.HORA_REG = HORA_REG;
    }

    public String getFECHA_ACTU() {
        return FECHA_ACTU;
    }

    public void setFECHA_ACTU(String FECHA_ACTU) {
        this.FECHA_ACTU = FECHA_ACTU;
    }

    public String getHORA_ACTU() {
        return HORA_ACTU;
    }

    public void setHORA_ACTU(String HORA_ACTU) {
        this.HORA_ACTU = HORA_ACTU;
    }

    public String getNOM_PC() {
        return NOM_PC;
    }

    public void setNOM_PC(String NOM_PC) {
        this.NOM_PC = NOM_PC;
    }
        
}
