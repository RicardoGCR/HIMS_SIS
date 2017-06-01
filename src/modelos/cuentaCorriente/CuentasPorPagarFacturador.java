
package modelos.cuentaCorriente;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import vista.cuentaCorriente.Facturador;

@Entity
public class CuentasPorPagarFacturador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    
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

    public CuentasPorPagarFacturador() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentasPorPagarFacturador)) {
            return false;
        }
        CuentasPorPagarFacturador other = (CuentasPorPagarFacturador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
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
    
}
