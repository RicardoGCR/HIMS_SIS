
package modelos.PERSONAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;

    
public class CLS_PERSONAL_ASIGNACION {
     private Connection cn;
     DefaultTableModel m;
    private String DNI_per;
    private String ape_par_per;
    private String ape_mat_per;
    private String nombres_per;
    private String fec_nac_per;
    private String sexo;
    private String cod_nivel;
    private String cod_tipo_trabajador;
    private String cod_regimen_lab;
    private String SE_ID;
    private String AR_ID;
    private String cod_usu;    
    private String Correo;
    private String Cel;  
    private String cargo;
    private String servicio;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getCel() {
        return Cel;
    }

    public void setCel(String Cel) {
        this.Cel = Cel;
    }

    
     public CLS_PERSONAL_ASIGNACION()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
     
     public boolean INSERTAR_PERSONAL_ASIGNACION(String opc){
      boolean resp = false;
        try
        {
            String sql = "exec [INSERTAR_PERSONAL_PERSONAL] ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getDNI_per());
            cmd.setString(2, getApe_par_per());
            cmd.setString(3, getApe_mat_per());
            cmd.setString(4, getNombres_per());
            cmd.setString(5, getFec_nac_per());
            cmd.setString(6, getSexo());
            cmd.setString(7, getCod_nivel());     
            cmd.setString(8, getCargo());
            cmd.setString(9, getServicio()); 
            cmd.setString(10, getCod_tipo_trabajador());
            cmd.setString(11, getCod_regimen_lab());       
            cmd.setString(12, getAR_ID());
            cmd.setString(13,getCorreo()) ;  
            cmd.setString(14,getCel()) ;
            cmd.setString(15,getCod_usu()) ;  
            cmd.setString(16,opc );

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error guardar personal asigancion: " + ex.getMessage());
        }
        return resp;
     }
         public void LISTAR(JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Nº","ID UO","Cod. Personal","Apellido Paterno","Apellido Materno","Nombres","Cargo",
            "AR_ID", "Area", "Servicio", "SE_ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[11];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC PERSONAL_LISTAR_PER_UO";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
//            cmd.setString(1, paciente);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(0); // nhc
                fila[2]=r.getString(1);
                fila[3]=r.getString(2);
                fila[4]=r.getString(3); // dni
                fila[5]=r.getString(4);
                fila[6]=r.getString(5);
                fila[7]=r.getString(6); // dni
                fila[8]=r.getString(7);
                fila[9]=r.getString(8);

                m.addRow(fila);
                c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
//            formatoTablaCargarFormatEmer(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarDatos: " + e.getMessage());
        }
    }
public boolean ELIMINAR_PERSONAL()
    {
        boolean resp = false;
        try
        {
            String sql = "exec [ELIMINAR_PERSONAL_ASIGNACION] ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getDNI_per());
            if(!cmd.execute()){
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
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getDNI_per() {
        return DNI_per;
    }

    public void setDNI_per(String DNI_per) {
        this.DNI_per = DNI_per;
    }

    public String getApe_par_per() {
        return ape_par_per;
    }

    public void setApe_par_per(String ape_par_per) {
        this.ape_par_per = ape_par_per;
    }

    public String getApe_mat_per() {
        return ape_mat_per;
    }

    public void setApe_mat_per(String ape_mat_per) {
        this.ape_mat_per = ape_mat_per;
    }

    public String getNombres_per() {
        return nombres_per;
    }

    public void setNombres_per(String nombres_per) {
        this.nombres_per = nombres_per;
    }

    public String getFec_nac_per() {
        return fec_nac_per;
    }

    public void setFec_nac_per(String fec_nac_per) {
        this.fec_nac_per = fec_nac_per;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCod_nivel() {
        return cod_nivel;
    }

    public void setCod_nivel(String cod_nivel) {
        this.cod_nivel = cod_nivel;
    }

    public String getCod_tipo_trabajador() {
        return cod_tipo_trabajador;
    }

    public void setCod_tipo_trabajador(String cod_tipo_trabajador) {
        this.cod_tipo_trabajador = cod_tipo_trabajador;
    }

    public String getCod_regimen_lab() {
        return cod_regimen_lab;
    }

    public void setCod_regimen_lab(String cod_regimen_lab) {
        this.cod_regimen_lab = cod_regimen_lab;
    }

    public String getSE_ID() {
        return SE_ID;
    }

    public void setSE_ID(String SE_ID) {
        this.SE_ID = SE_ID;
    }

    public String getAR_ID() {
        return AR_ID;
    }

    public void setAR_ID(String AR_ID) {
        this.AR_ID = AR_ID;
    }

    public String getCod_usu() {
        return cod_usu;
    }

    public void setCod_uso(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    

    
    
}
