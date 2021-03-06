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
import vista.ConsultorioEx.RegistroEmbarazoAO;
import vista.ConsultorioEx.RegistroEmbarazoPrincipal;
import static vista.ConsultorioEx.RegistroEmbarazoPrincipal.lblActoMedico;
import static vista.ConsultorioEx.RegistroEmbarazoPrincipal.lblIdActoMedico;
/**
 *
 * @author MYS1
 */
public class ConsultorioExtCarnetPerinatalAO {
        DefaultTableModel m;
        Conexion con = new Conexion();
        private Connection cn;    
        private int AO_ID;
	private int CP_ID ;
	private String AO_GESTAS;
	private String AO_ABORTOS;  
	private String AO_VAGINALES;  
	private String AO_NAC_VIVOS;  
	private String AO_VIVEN;  
	private String AO_PARTOS;  
	private String AO_CESAREAS;  
	private String AO_NAC_MUERTOS;  
	private String AO_MUERTO_P_SEM;  
	private String AO_MUERTO_D_PSEM;  
	private String AO_PARTO_0;  
	private String AO_PARTO_2500;  
	private String AO_PARTO_MULT;  
	private String AO_PARTO_37_SEM;  
	private String AO_RN_MAYOR_PESO;  
	private String ESTADO;  
	private String COD_USU;  
        private int idActoMedico;
        
 public void ConsultoriosExtAOListar(String rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CARNET_PERINATAL_AO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                
                RegistroEmbarazoAO.txtGestas.setText(r.getString(3)); 
                RegistroEmbarazoAO.txtAborto.setText(r.getString(4));
                RegistroEmbarazoAO.txtPartos.setText(r.getString(8));
                RegistroEmbarazoAO.txtVaginales.setText(r.getString(5));
                RegistroEmbarazoAO.txtCesareas.setText(r.getString(9));
                RegistroEmbarazoAO.txtRN.setText(r.getString(6));
                RegistroEmbarazoAO.txtNacidos.setText(r.getString(10));
                RegistroEmbarazoAO.txtViven.setText(r.getString(7));
                RegistroEmbarazoAO.txtMuerto1.setText(r.getString(11));
                RegistroEmbarazoAO.txtDespues.setText(r.getString(12));
                RegistroEmbarazoAO.chk1.setText(r.getString(13));
                RegistroEmbarazoAO.chk2.setText(r.getString(14));
                RegistroEmbarazoAO.chk3.setText(r.getString(15));
                RegistroEmbarazoAO.chk4.setText(r.getString(16));
                RegistroEmbarazoAO.txtRNmayor.setText(r.getString(17));
                RegistroEmbarazoAO.lblIdAO.setText(r.getString(1));
                if (!RegistroEmbarazoAO.lblIdAO.getText().equals("") ){
                    RegistroEmbarazoAO.btnGuardar.setEnabled(false);
                    RegistroEmbarazoAO.btneditar.setEnabled(true);
                    RegistroEmbarazoAO.var.setText("2");
                    RegistroEmbarazoAO.txtGestas.setEditable(false);
                    RegistroEmbarazoAO.txtAborto.setEditable(false);
                    RegistroEmbarazoAO.txtPartos.setEditable(false);
                    RegistroEmbarazoAO.txtVaginales.setEditable(false);
                    RegistroEmbarazoAO.txtCesareas.setEditable(false);
                    RegistroEmbarazoAO.txtRN.setEditable(false);
                    RegistroEmbarazoAO.txtNacidos.setEditable(false);
                    RegistroEmbarazoAO.txtViven.setEditable(false);
                    RegistroEmbarazoAO.txtMuerto1.setEditable(false);
                    RegistroEmbarazoAO.txtDespues.setEditable(false);
                    RegistroEmbarazoAO.txtRNmayor.setEditable(false);
                    RegistroEmbarazoAO.lblIdActoMedico.setText(r.getString(23));
                    RegistroEmbarazoAO.lblActoMedico.setText(r.getString(24));
                }

                }
            //
        } catch (Exception e) {
            System.out.println("Error: Consultorios AO  " + e.getMessage());
        }
    }
        
    public boolean mantenimientoConsultorioExtAO(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_AO ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getAO_ID());
            cmd.setInt(2, getCP_ID());
            cmd.setString(3, getAO_GESTAS());
            cmd.setString(4, getAO_ABORTOS());
            cmd.setString(5, getAO_VAGINALES());
            cmd.setString(6, getAO_NAC_VIVOS());
            cmd.setString(7, getAO_VIVEN());
            cmd.setString(8, getAO_PARTOS());
            cmd.setString(9, getAO_CESAREAS());
            cmd.setString(10, getAO_NAC_MUERTOS());
            cmd.setString(11, getAO_MUERTO_P_SEM());
            cmd.setString(12, getAO_MUERTO_D_PSEM());
            cmd.setString(13, getAO_PARTO_0());
            cmd.setString(14, getAO_PARTO_2500());
            cmd.setString(15, getAO_PARTO_MULT());
            cmd.setString(16, getAO_PARTO_37_SEM());
            cmd.setString(17, getAO_RN_MAYOR_PESO());
            cmd.setString(18, getCOD_USU());
            cmd.setString(19, tipo);
            cmd.setInt(20, getIdActoMedico());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtAO: " + ex.getMessage());
        }
        return resp;
    }

        
    public ConsultorioExtCarnetPerinatalAO() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public DefaultTableModel getM() {
        return m;
    }

    public void setM(DefaultTableModel m) {
        this.m = m;
    }

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getAO_ID() {
        return AO_ID;
    }

    public void setAO_ID(int AO_ID) {
        this.AO_ID = AO_ID;
    }

    public int getCP_ID() {
        return CP_ID;
    }

    public void setCP_ID(int CP_ID) {
        this.CP_ID = CP_ID;
    }

    public String getAO_GESTAS() {
        return AO_GESTAS;
    }

    public void setAO_GESTAS(String AO_GESTAS) {
        this.AO_GESTAS = AO_GESTAS;
    }

    public String getAO_ABORTOS() {
        return AO_ABORTOS;
    }

    public void setAO_ABORTOS(String AO_ABORTOS) {
        this.AO_ABORTOS = AO_ABORTOS;
    }

    public String getAO_VAGINALES() {
        return AO_VAGINALES;
    }

    public void setAO_VAGINALES(String AO_VAGINALES) {
        this.AO_VAGINALES = AO_VAGINALES;
    }

    public String getAO_NAC_VIVOS() {
        return AO_NAC_VIVOS;
    }

    public void setAO_NAC_VIVOS(String AO_NAC_VIVOS) {
        this.AO_NAC_VIVOS = AO_NAC_VIVOS;
    }

    public String getAO_VIVEN() {
        return AO_VIVEN;
    }

    public void setAO_VIVEN(String AO_VIVEN) {
        this.AO_VIVEN = AO_VIVEN;
    }

    public String getAO_PARTOS() {
        return AO_PARTOS;
    }

    public void setAO_PARTOS(String AO_PARTOS) {
        this.AO_PARTOS = AO_PARTOS;
    }

    public String getAO_CESAREAS() {
        return AO_CESAREAS;
    }

    public void setAO_CESAREAS(String AO_CESAREAS) {
        this.AO_CESAREAS = AO_CESAREAS;
    }

    public String getAO_NAC_MUERTOS() {
        return AO_NAC_MUERTOS;
    }

    public void setAO_NAC_MUERTOS(String AO_NAC_MUERTOS) {
        this.AO_NAC_MUERTOS = AO_NAC_MUERTOS;
    }

    public String getAO_MUERTO_P_SEM() {
        return AO_MUERTO_P_SEM;
    }

    public void setAO_MUERTO_P_SEM(String AO_MUERTO_P_SEM) {
        this.AO_MUERTO_P_SEM = AO_MUERTO_P_SEM;
    }

    public String getAO_MUERTO_D_PSEM() {
        return AO_MUERTO_D_PSEM;
    }

    public void setAO_MUERTO_D_PSEM(String AO_MUERTO_D_PSEM) {
        this.AO_MUERTO_D_PSEM = AO_MUERTO_D_PSEM;
    }

    public String getAO_PARTO_0() {
        return AO_PARTO_0;
    }

    public void setAO_PARTO_0(String AO_PARTO_0) {
        this.AO_PARTO_0 = AO_PARTO_0;
    }

    public String getAO_PARTO_2500() {
        return AO_PARTO_2500;
    }

    public void setAO_PARTO_2500(String AO_PARTO_2500) {
        this.AO_PARTO_2500 = AO_PARTO_2500;
    }

    public String getAO_PARTO_MULT() {
        return AO_PARTO_MULT;
    }

    public void setAO_PARTO_MULT(String AO_PARTO_MULT) {
        this.AO_PARTO_MULT = AO_PARTO_MULT;
    }

    public String getAO_PARTO_37_SEM() {
        return AO_PARTO_37_SEM;
    }

    public void setAO_PARTO_37_SEM(String AO_PARTO_37_SEM) {
        this.AO_PARTO_37_SEM = AO_PARTO_37_SEM;
    }

    public String getAO_RN_MAYOR_PESO() {
        return AO_RN_MAYOR_PESO;
    }

    public void setAO_RN_MAYOR_PESO(String AO_RN_MAYOR_PESO) {
        this.AO_RN_MAYOR_PESO = AO_RN_MAYOR_PESO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getCOD_USU() {
        return COD_USU;
    }

    public void setCOD_USU(String COD_USU) {
        this.COD_USU = COD_USU;
    }

    /**
     * @return the idActoMedico
     */
    public int getIdActoMedico() {
        return idActoMedico;
    }

    /**
     * @param idActoMedico the idActoMedico to set
     */
    public void setIdActoMedico(int idActoMedico) {
        this.idActoMedico = idActoMedico;
    }
    
}
