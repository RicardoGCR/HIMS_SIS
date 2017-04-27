/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.annotation.XmlRootElement;
import servicios.Conexion;
import vista.ConsultorioEx.RegistroEmbarazo;

public class ConsultorioExtCarnetPerinatalCabecera implements Serializable {
    private static final long serialVersionUID = 1L;

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private String idHc;
    private String cpEstbOrigen;
    private String cpEstbAct;
    private String cpTipoSeguro;
    private String cpEdad;
    private String cpCodigoAfil;
    private String cpEstudios;
    private String cpAniosAprob;
    private String cpPadreRn;
    private String fechaActu;
    private String horaActu;
    private Character estado;
    private String nomPc;
    private String codUsu;

    public boolean mantenimientoConsultorioExtCarnetPerinatalCabecera(String tipo,String triaje)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_CABECERA ?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCpId());
            cmd.setString(2, getIdHc());
            cmd.setString(3, getCpEstbOrigen());
            cmd.setString(4, getCpEstbAct());
            cmd.setString(5, getCpTipoSeguro());
            cmd.setString(6, getCpEdad());
            cmd.setString(7, getCpCodigoAfil());
            cmd.setString(8, getCpEstudios());
            cmd.setString(9, getCpAniosAprob());
            cmd.setString(10, getCpPadreRn());
            cmd.setString(11, getCodUsu());
            cmd.setString(12, tipo);
            cmd.setString(13, triaje);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatalCabecera: " + ex.getMessage());
        }
        return resp;
    }
    
    public String perinatalCabeceraID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 CP_ID\n" +
                        "FROM CONSULTORIO_EXT_CARNET_PERINATAL_CABECERA \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY CP_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("perinatalCabeceraID: " + ex.getMessage());
        }
        return cod;
    }
    
    public String nombreEstablecimiento()
    {
        String establecimiento="";
        try
        {
            String sql = "SELECT TOP 1 UE_DESC FROM SISTEMA_UNIDAD_EJECUTORA";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               establecimiento = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("perinatalCabeceraID: " + ex.getMessage());
        }
        return establecimiento;
    }
    
    public void mostrarDatosHC(String id_hc){
        String consulta="";
        try {
            consulta="EXEC [CONSULTORIO_EXT_DATOS_HC] ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, id_hc);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                RegistroEmbarazo.lblDireccion.setText(r.getString(4)); 
                RegistroEmbarazo.lblDepartamento.setText(r.getString(5));
                RegistroEmbarazo.lblProvincia.setText(r.getString(6));
                RegistroEmbarazo.lblDistrito.setText(r.getString(7));
                RegistroEmbarazo.lblSector.setText(r.getString(8));
                RegistroEmbarazo.lblCelular.setText(r.getString(9));
                RegistroEmbarazo.lblTelefono.setText(r.getString(10));
                RegistroEmbarazo.lblEstadoCiv.setText(r.getString(11));
                RegistroEmbarazo.lblEdad.setText(r.getString(12));
                RegistroEmbarazo.lblOcupacion.setText(r.getString(13));
                int edad = Integer.parseInt(r.getString(12));
                if(edad < 15 || edad > 35)
                    RegistroEmbarazo.ChkEdad.setText("X");
                else
                    RegistroEmbarazo.ChkEdad.setText("");
            }
            //
        } catch (Exception e) {
            System.out.println("mostrarDatosHC: " + e.getMessage());
        }
    }
    
    public void formatoTablaConsultorioExControlPerinatalCabListar(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);//
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);//
        tabla.getColumnModel().getColumn(2).setPreferredWidth(250);//
        tabla.getColumnModel().getColumn(3).setPreferredWidth(120);//
        
    }
    
    public void consultorioExControlPerinatalCabListar(String busqueda,String tipo,JTable tabla,String tamano){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Edad","Padre","Fecha"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CONSULTORIO_EXT_CARNET_PERINATAL_CAB_LISTAR_POR_PACIENTE ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, busqueda);
            cmd.setString(2, tipo);
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
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaConsultorioExControlPerinatalCabListar(tabla);
            if(tamano.equals("actual"))
                tabla.setRowHeight(50);
            else
                tabla.setRowHeight(30);
        } catch (Exception e) {
            System.out.println("Error: consultorioExControlPerinatalCabListar: " + e.getMessage());
        }
    }
    
    public ConsultorioExtCarnetPerinatalCabecera() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalCabecera(int cpId) {
        this.cpId = cpId;
    }

    public int getCpId() {
        return cpId;
    }

    public void setCpId(int cpId) {
        this.cpId = cpId;
    }

    public String getCpEstbOrigen() {
        return cpEstbOrigen;
    }

    public void setCpEstbOrigen(String cpEstbOrigen) {
        this.cpEstbOrigen = cpEstbOrigen;
    }

    public String getCpEstbAct() {
        return cpEstbAct;
    }

    public void setCpEstbAct(String cpEstbAct) {
        this.cpEstbAct = cpEstbAct;
    }

    public String getCpTipoSeguro() {
        return cpTipoSeguro;
    }

    public void setCpTipoSeguro(String cpTipoSeguro) {
        this.cpTipoSeguro = cpTipoSeguro;
    }

    public String getCpEdad() {
        return cpEdad;
    }

    public void setCpEdad(String cpEdad) {
        this.cpEdad = cpEdad;
    }

    public String getCpCodigoAfil() {
        return cpCodigoAfil;
    }

    public void setCpCodigoAfil(String cpCodigoAfil) {
        this.cpCodigoAfil = cpCodigoAfil;
    }

    public String getCpEstudios() {
        return cpEstudios;
    }

    public void setCpEstudios(String cpEstudios) {
        this.cpEstudios = cpEstudios;
    }

    public String getCpAniosAprob() {
        return cpAniosAprob;
    }

    public void setCpAniosAprob(String cpAniosAprob) {
        this.cpAniosAprob = cpAniosAprob;
    }

    public String getCpPadreRn() {
        return cpPadreRn;
    }

    public void setCpPadreRn(String cpPadreRn) {
        this.cpPadreRn = cpPadreRn;
    }

    public String getFechaActu() {
        return fechaActu;
    }

    public void setFechaActu(String fechaActu) {
        this.fechaActu = fechaActu;
    }

    public String getHoraActu() {
        return horaActu;
    }

    public void setHoraActu(String horaActu) {
        this.horaActu = horaActu;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }


    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalCabecera[ cpId=" + cpId + " ]";
    }

    /**
     * @return the idHc
     */
    public String getIdHc() {
        return idHc;
    }

    /**
     * @param idHc the idHc to set
     */
    public void setIdHc(String idHc) {
        this.idHc = idHc;
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
