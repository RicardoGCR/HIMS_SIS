
package modelos.hospitalizacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
public class HospitalizacionPisos {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private String piso_id;
    private int se_id;
    private int piso_numero;
    private String piso_nombre;
    private String piso_fecha_actu;
    private String piso_hora_actu;
    private String piso_nom_pc;
    private String piso_estado;
    private String cod_usu;
    
    public boolean mantenimientoHospitalizacionPisos(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_PISOS_MANTENIMIENTO ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getSe_id());
            cmd.setInt(2, getPiso_numero());
            cmd.setString(3, getPiso_nombre());
            cmd.setString(4, getCod_usu());
            cmd.setString(5, tipo);
            cmd.setString(6, getPiso_id());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoHospitalizacionPisos: " + ex.getMessage());
        }
        return resp;
    }
    
    public String codServicio(String servicio)
    {
        String cod="";
        try
        {
            String sql = "SELECT SE_ID FROM SISTEMA_SERVICIO WHERE SE_DESC = ? AND UP_ID = '02'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, servicio);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error: codServicio: " + ex.getMessage());
        }
        return cod;
    }
    
    public void formatoTablaHospitalizacionPisosListar(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);//id tabla piso
        tabla.getColumnModel().getColumn(1).setPreferredWidth(40);//nro piso
        tabla.getColumnModel().getColumn(2).setPreferredWidth(180);//nombre piso
        tabla.getColumnModel().getColumn(3).setPreferredWidth(160);//servicio desc
        tabla.getColumnModel().getColumn(4).setPreferredWidth(120);//codigo servicio
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void hospitalizacionPisosListar(JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID Piso","Nº Piso","Nombre de Piso","Servicio","Código de servicio"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[5];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC HOSPITALIZACION_PISOS_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, getPiso_nombre());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // 
                fila[4]=r.getString(5);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaHospitalizacionPisosListar(tabla);
        } catch (Exception e) {
            System.out.println("Error: hospitalizacionPisosListar: " + e.getMessage());
        }
    }
    
    public HospitalizacionPisos()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
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
     * @return the piso_id
     */
    public String getPiso_id() {
        return piso_id;
    }

    /**
     * @param piso_id the piso_id to set
     */
    public void setPiso_id(String piso_id) {
        this.piso_id = piso_id;
    }

    /**
     * @return the up_id
     */
    public int getSe_id() {
        return se_id;
    }

    /**
     * @param up_id the up_id to set
     */
    public void setSe_id(int se_id) {
        this.se_id = se_id;
    }

    /**
     * @return the piso_numero
     */
    public int getPiso_numero() {
        return piso_numero;
    }

    /**
     * @param piso_numero the piso_numero to set
     */
    public void setPiso_numero(int piso_numero) {
        this.piso_numero = piso_numero;
    }

    /**
     * @return the piso_nombre
     */
    public String getPiso_nombre() {
        return piso_nombre;
    }

    /**
     * @param piso_nombre the piso_nombre to set
     */
    public void setPiso_nombre(String piso_nombre) {
        this.piso_nombre = piso_nombre;
    }

    /**
     * @return the piso_fecha_actu
     */
    public String getPiso_fecha_actu() {
        return piso_fecha_actu;
    }

    /**
     * @param piso_fecha_actu the piso_fecha_actu to set
     */
    public void setPiso_fecha_actu(String piso_fecha_actu) {
        this.piso_fecha_actu = piso_fecha_actu;
    }

    /**
     * @return the piso_hora_actu
     */
    public String getPiso_hora_actu() {
        return piso_hora_actu;
    }

    /**
     * @param piso_hora_actu the piso_hora_actu to set
     */
    public void setPiso_hora_actu(String piso_hora_actu) {
        this.piso_hora_actu = piso_hora_actu;
    }

    /**
     * @return the piso_nom_pc
     */
    public String getPiso_nom_pc() {
        return piso_nom_pc;
    }

    /**
     * @param piso_nom_pc the piso_nom_pc to set
     */
    public void setPiso_nom_pc(String piso_nom_pc) {
        this.piso_nom_pc = piso_nom_pc;
    }

    /**
     * @return the piso_estado
     */
    public String getPiso_estado() {
        return piso_estado;
    }

    /**
     * @param piso_estado the piso_estado to set
     */
    public void setPiso_estado(String piso_estado) {
        this.piso_estado = piso_estado;
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
