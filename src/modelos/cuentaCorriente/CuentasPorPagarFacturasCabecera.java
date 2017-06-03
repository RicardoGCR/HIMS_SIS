
package modelos.cuentaCorriente;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import vista.cuentaCorriente.Facturador;


public class CuentasPorPagarFacturasCabecera implements Serializable {
    private static final long serialVersionUID = 1L;


    private int id;
    private String serie;
    private String correlativo;
    private String tipoOperacion;
    private String fechaEmision;
    private String tipoMoneda;
    private String documento;
    private String actoMedico;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String estado;
    private String cod_usu;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    
    public boolean mantenimientoCuentasPorPagarFacturasCabecera(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CUENTAS_POR_PAGAR_MANTENIMIENTO_FACTURAS_CABECERA ?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId());
            cmd.setString(2, getSerie());
            cmd.setString(3, getCorrelativo());
            cmd.setString(4, getTipoOperacion());
            cmd.setString(5, getFechaEmision());
            cmd.setString(6, getTipoMoneda());
            cmd.setString(7, getDocumento());
            cmd.setString(8, getActoMedico());
            cmd.setString(9, getCod_usu());
            cmd.setString(10, tipo);
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
    
    public void historiaClinicaMotivo(String busqueda){
        String consulta="";
        try {
            consulta="CAJA_EMPRESA_JERARQUIA_PAGO_BUSCAR_RUC ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, busqueda);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Facturador.txtApeNom.setText(r.getString(1)); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: Consultorios CABECERA LISTAR  " + e.getMessage());
        }
    } 
    
    public void formatoTablaVentas(JTable tabla){
//        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);//CODIGO
        TableColumn columna = tabla.getColumnModel().getColumn(0);
            columna.setMaxWidth(0);
            columna.setMinWidth(0);
            columna.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna1 = tabla.getColumnModel().getColumn(1);
            columna1.setMaxWidth(0);
            columna1.setMinWidth(0);
            columna1.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna2 = tabla.getColumnModel().getColumn(2);
            columna2.setMaxWidth(0);
            columna2.setMinWidth(0);
            columna2.setPreferredWidth(0);
            tabla.doLayout();
        TableColumn columna3 = tabla.getColumnModel().getColumn(3);
            columna3.setMaxWidth(0);
            columna3.setMinWidth(0);
            columna3.setPreferredWidth(0);
            tabla.doLayout();
        tabla.setRowHeight(25);
    }
    
    public void cargarVentas(String busqueda,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"","","","",
            "Cantidad","Código","Descripción","Precio"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_DOCUMENTO_CABECERA_FACTURADOR_BUSCAR_ACTO_MEDICO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, busqueda);
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
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaVentas(tabla);
        } catch (Exception e) {
            System.out.println("Error: cargarVentas: " + e.getMessage());
        }
    }

    public CuentasPorPagarFacturasCabecera() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "modelos.cuentaCorriente.Facturador[ id=" + id + " ]";
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
     * @return the serieCorrelativo
     */
   
    /**
     * @return the tipoOperacion
     */
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * @param tipoOperacion the tipoOperacion to set
     */
    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    /**
     * @return the fechaEmision
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * @return the tipoMoneda
     */
    public String getTipoMoneda() {
        return tipoMoneda;
    }

    /**
     * @param tipoMoneda the tipoMoneda to set
     */
    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    /**
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * @return the actoMedico
     */
    public String getActoMedico() {
        return actoMedico;
    }

    /**
     * @param actoMedico the actoMedico to set
     */
    public void setActoMedico(String actoMedico) {
        this.actoMedico = actoMedico;
    }

    /**
     * @return the fecha_actu
     */
    public String getFecha_actu() {
        return fecha_actu;
    }

    /**
     * @param fecha_actu the fecha_actu to set
     */
    public void setFecha_actu(String fecha_actu) {
        this.fecha_actu = fecha_actu;
    }

    /**
     * @return the hora_actu
     */
    public String getHora_actu() {
        return hora_actu;
    }

    /**
     * @param hora_actu the hora_actu to set
     */
    public void setHora_actu(String hora_actu) {
        this.hora_actu = hora_actu;
    }

    /**
     * @return the nom_pc
     */
    public String getNom_pc() {
        return nom_pc;
    }

    /**
     * @param nom_pc the nom_pc to set
     */
    public void setNom_pc(String nom_pc) {
        this.nom_pc = nom_pc;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
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

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the correlativo
     */
    public String getCorrelativo() {
        return correlativo;
    }

    /**
     * @param correlativo the correlativo to set
     */
    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }
    
}
