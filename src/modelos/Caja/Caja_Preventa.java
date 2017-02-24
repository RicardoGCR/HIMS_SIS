/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.Caja;

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
 * @author PC02
 */
public class Caja_Preventa {
    DefaultTableModel m;
    private Connection cn;
    private int id_preventa;
    private String id_hc;
    private int CA_ID;
    private int id_per_uni_org;
    private String hOS_Indicaciones;
    private String Modulo;
    private int UP_ID;
    private String fecha_actu;
    private String hora_actu;
    private String cod_usu;
    private String estado;
    private String Pc_Registro;
    private String cod_per;
    private String cod_medico;
    private int lt_minuto;
    private int AR_ID;
    private String COD_TIPO_TURNO;
    private String OBSERVACION;
    private int ACTO_MEDICO;
    private int HKD_ID;
    private String cod_nomen_caja;
    private String EMER_FORMA_LLEGADA_ID;
    private String EMER_TRAIDOPOR;
    private String EMER_PARENTESCO;
    private String EMER_OBSERVACION;
    private String ESTADO_CAJAP;
    Conexion con = new Conexion();

    public boolean mantanimientoCajaPreventaEmergencia(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CAJA_PREVENTA_MANTENIMIENTO_EMERGENCIA "
                        + "?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_preventa());
            cmd.setString(2, getId_hc());
            cmd.setString(3, getEMER_TRAIDOPOR());
            cmd.setString(4, getEMER_PARENTESCO());
            cmd.setString(5, getEMER_OBSERVACION());
            cmd.setString(6, getEMER_FORMA_LLEGADA_ID());
            cmd.setString(7, getCod_usu());
            cmd.setString(8, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("mantanimientoCajaPreventaEmergencia: " + ex.getMessage());
        }
        return resp;
    }
    
    public String codFormaLlegada(String formaLlegada)
    {
        String cod="";
        try
        {
            String sql = "SELECT EMER_FORMA_LLEGADA_ID FROM ADMISION_EMERGENCIA_FORMA_LLEGADA WHERE EMER_FORMA_LLEGADA_NOMBRE = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, formaLlegada);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_codFormaLlegada: " + ex.getMessage());
        }
        return cod;
    }
    
    public String codUsuario(String nombreUsuario)
    {
        String cod="";
        try
        {
            String sql = "SELECT USU_CODIGO FROM USUARIO WHERE Usu_Usuario = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombreUsuario);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_codUsuario: " + ex.getMessage());
        }
        return cod;
    }
    
    public void formatoTablaCargarFormatEmer(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);//id
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);//acto medico
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);//nhc
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);//dni
        tabla.getColumnModel().getColumn(4).setPreferredWidth(200);//PACIENTE
        tabla.getColumnModel().getColumn(5).setPreferredWidth(200);//TRAIDO POR
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);//PARENTESCO
        tabla.getColumnModel().getColumn(7).setPreferredWidth(150);//SERVICIO
        tabla.getColumnModel().getColumn(8).setPreferredWidth(150);//AREA
        tabla.getColumnModel().getColumn(9).setPreferredWidth(200);//MEDICO
        tabla.getColumnModel().getColumn(10).setPreferredWidth(80);//ID FL
        tabla.getColumnModel().getColumn(11).setPreferredWidth(120);//NOMBRE FL 
        tabla.getColumnModel().getColumn(12).setPreferredWidth(160);//OBSERVACION
        tabla.getColumnModel().getColumn(13).setPreferredWidth(100);//FECHA DE INGRESO
        tabla.getColumnModel().getColumn(14).setPreferredWidth(100);//HORA DE INGRESO
        tabla.setRowHeight(25);
    }
    
    public void listarDatosEmergencia(String paciente,String fechai, String fechaf,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID Prev","Acto Médico","Nº H.C","DNI","Paciente",
                "Traído por","Parentesco","Servicio","Area","Médico"," ID FL",
                "Forma de llegada", "Observación","Fecha de ingreso","Hora de ingreso"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CAJA_PREVENTA_LISTAR_EMERGENCIA ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, paciente);
            cmd.setString(2, fechai);
            cmd.setString(3, fechaf);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // nhc
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // dni
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7); // dni
                fila[7]=r.getString(8);
                fila[8]=r.getString(9);
                fila[9]=r.getString(10);
                fila[10]=r.getString(11); // dni
                fila[11]=r.getString(12);
                fila[12]=r.getString(13);
                fila[13]=r.getString(14); // dni
                fila[14]=r.getString(15); // dni
                //fila[4]=r.getString(1); // codigo de hc
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaCargarFormatEmer(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarDatosEmergencia: " + e.getMessage());
        }
    }
    
    public int idCajaPreventa(){
        int id = 0;
        try {
            String consulta = "EXEC CAJA_PREVENTA_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getInt(1);
        }
        }catch(Exception ex){
            System.out.println("Error: idCajaPreventa: " + ex.getMessage());
        }
        return id;
    }
    
    public Caja_Preventa()
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
     * @return the id_preventa
     */
    public int getId_preventa() {
        return id_preventa;
    }

    /**
     * @param id_preventa the id_preventa to set
     */
    public void setId_preventa(int id_preventa) {
        this.id_preventa = id_preventa;
    }

    /**
     * @return the id_hc
     */
    public String getId_hc() {
        return id_hc;
    }

    /**
     * @param id_hc the id_hc to set
     */
    public void setId_hc(String id_hc) {
        this.id_hc = id_hc;
    }

    /**
     * @return the CA_ID
     */
    public int getCA_ID() {
        return CA_ID;
    }

    /**
     * @param CA_ID the CA_ID to set
     */
    public void setCA_ID(int CA_ID) {
        this.CA_ID = CA_ID;
    }

    /**
     * @return the id_per_uni_org
     */
    public int getId_per_uni_org() {
        return id_per_uni_org;
    }

    /**
     * @param id_per_uni_org the id_per_uni_org to set
     */
    public void setId_per_uni_org(int id_per_uni_org) {
        this.id_per_uni_org = id_per_uni_org;
    }

    /**
     * @return the hOS_Indicaciones
     */
    public String gethOS_Indicaciones() {
        return hOS_Indicaciones;
    }

    /**
     * @param hOS_Indicaciones the hOS_Indicaciones to set
     */
    public void sethOS_Indicaciones(String hOS_Indicaciones) {
        this.hOS_Indicaciones = hOS_Indicaciones;
    }

    /**
     * @return the Modulo
     */
    public String getModulo() {
        return Modulo;
    }

    /**
     * @param Modulo the Modulo to set
     */
    public void setModulo(String Modulo) {
        this.Modulo = Modulo;
    }

    /**
     * @return the UP_ID
     */
    public int getUP_ID() {
        return UP_ID;
    }

    /**
     * @param UP_ID the UP_ID to set
     */
    public void setUP_ID(int UP_ID) {
        this.UP_ID = UP_ID;
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
     * @return the Pc_Registro
     */
    public String getPc_Registro() {
        return Pc_Registro;
    }

    /**
     * @param Pc_Registro the Pc_Registro to set
     */
    public void setPc_Registro(String Pc_Registro) {
        this.Pc_Registro = Pc_Registro;
    }

    /**
     * @return the cod_per
     */
    public String getCod_per() {
        return cod_per;
    }

    /**
     * @param cod_per the cod_per to set
     */
    public void setCod_per(String cod_per) {
        this.cod_per = cod_per;
    }

    /**
     * @return the cod_medico
     */
    public String getCod_medico() {
        return cod_medico;
    }

    /**
     * @param cod_medico the cod_medico to set
     */
    public void setCod_medico(String cod_medico) {
        this.cod_medico = cod_medico;
    }

    /**
     * @return the lt_minuto
     */
    public int getLt_minuto() {
        return lt_minuto;
    }

    /**
     * @param lt_minuto the lt_minuto to set
     */
    public void setLt_minuto(int lt_minuto) {
        this.lt_minuto = lt_minuto;
    }

    /**
     * @return the AR_ID
     */
    public int getAR_ID() {
        return AR_ID;
    }

    /**
     * @param AR_ID the AR_ID to set
     */
    public void setAR_ID(int AR_ID) {
        this.AR_ID = AR_ID;
    }

    /**
     * @return the COD_TIPO_TURNO
     */
    public String getCOD_TIPO_TURNO() {
        return COD_TIPO_TURNO;
    }

    /**
     * @param COD_TIPO_TURNO the COD_TIPO_TURNO to set
     */
    public void setCOD_TIPO_TURNO(String COD_TIPO_TURNO) {
        this.COD_TIPO_TURNO = COD_TIPO_TURNO;
    }

    /**
     * @return the OBSERVACION
     */
    public String getOBSERVACION() {
        return OBSERVACION;
    }

    /**
     * @param OBSERVACION the OBSERVACION to set
     */
    public void setOBSERVACION(String OBSERVACION) {
        this.OBSERVACION = OBSERVACION;
    }

    /**
     * @return the ACTO_MEDICO
     */
    public int getACTO_MEDICO() {
        return ACTO_MEDICO;
    }

    /**
     * @param ACTO_MEDICO the ACTO_MEDICO to set
     */
    public void setACTO_MEDICO(int ACTO_MEDICO) {
        this.ACTO_MEDICO = ACTO_MEDICO;
    }

    /**
     * @return the HKD_ID
     */
    public int getHKD_ID() {
        return HKD_ID;
    }

    /**
     * @param HKD_ID the HKD_ID to set
     */
    public void setHKD_ID(int HKD_ID) {
        this.HKD_ID = HKD_ID;
    }

    /**
     * @return the cod_nomen_caja
     */
    public String getCod_nomen_caja() {
        return cod_nomen_caja;
    }

    /**
     * @param cod_nomen_caja the cod_nomen_caja to set
     */
    public void setCod_nomen_caja(String cod_nomen_caja) {
        this.cod_nomen_caja = cod_nomen_caja;
    }

    /**
     * @return the EMER_FORMA_LLEGADA_ID
     */
    public String getEMER_FORMA_LLEGADA_ID() {
        return EMER_FORMA_LLEGADA_ID;
    }

    /**
     * @param EMER_FORMA_LLEGADA_ID the EMER_FORMA_LLEGADA_ID to set
     */
    public void setEMER_FORMA_LLEGADA_ID(String EMER_FORMA_LLEGADA_ID) {
        this.EMER_FORMA_LLEGADA_ID = EMER_FORMA_LLEGADA_ID;
    }

    /**
     * @return the EMER_TRAIDOPOR
     */
    public String getEMER_TRAIDOPOR() {
        return EMER_TRAIDOPOR;
    }

    /**
     * @param EMER_TRAIDOPOR the EMER_TRAIDOPOR to set
     */
    public void setEMER_TRAIDOPOR(String EMER_TRAIDOPOR) {
        this.EMER_TRAIDOPOR = EMER_TRAIDOPOR;
    }

    /**
     * @return the EMER_PARENTESCO
     */
    public String getEMER_PARENTESCO() {
        return EMER_PARENTESCO;
    }

    /**
     * @param EMER_PARENTESCO the EMER_PARENTESCO to set
     */
    public void setEMER_PARENTESCO(String EMER_PARENTESCO) {
        this.EMER_PARENTESCO = EMER_PARENTESCO;
    }

    /**
     * @return the EMER_OBSERVACION
     */
    public String getEMER_OBSERVACION() {
        return EMER_OBSERVACION;
    }

    /**
     * @param EMER_OBSERVACION the EMER_OBSERVACION to set
     */
    public void setEMER_OBSERVACION(String EMER_OBSERVACION) {
        this.EMER_OBSERVACION = EMER_OBSERVACION;
    }

    /**
     * @return the ESTADO_CAJAP
     */
    public String getESTADO_CAJAP() {
        return ESTADO_CAJAP;
    }

    /**
     * @param ESTADO_CAJAP the ESTADO_CAJAP to set
     */
    public void setESTADO_CAJAP(String ESTADO_CAJAP) {
        this.ESTADO_CAJAP = ESTADO_CAJAP;
    }
}
