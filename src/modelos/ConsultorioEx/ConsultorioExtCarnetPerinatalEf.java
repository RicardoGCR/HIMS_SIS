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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;
import vista.ConsultorioEx.RegistroEmbarazoEXF;
import vista.ConsultorioEx.RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V;

public class ConsultorioExtCarnetPerinatalEf implements Serializable {
    private static final long serialVersionUID = 1L;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int efId;
    private String efEc;
    private String efMamas;
    private String efCueUte;
    private String efPelvis;
    private String efOdont;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;

    public boolean mantenimientoConsultorioExtCarnetPerinatalEf(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_EF ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getEfId());
            cmd.setInt(2, getCpId());
            cmd.setString(3, getEfEc());
            cmd.setString(4, getEfMamas());
            cmd.setString(5, getEfCueUte());
            cmd.setString(6, getEfPelvis());
            cmd.setString(7, getEfOdont());
            cmd.setString(8, getCodUsu());
            cmd.setString(9, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatalEf: " + ex.getMessage());
        }
        return resp;
    }
    
    public String perinatalEfID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 EF_ID\n" +
                        "FROM CONSULTORIO_EXT_CARNET_PERINATAL_EF \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY EF_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("perinatalEfID: " + ex.getMessage());
        }
        return cod;
    }   
    
    public void ConsultoriosExtEfListar(String cp_id){
        String consulta="";
        try {
            consulta="[CONSULTORIO_EXT_LISTAR_CARNET_PERINATAL_EF] ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, cp_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                RegistroEmbarazoEXF.lblId.setText(r.getString(1)); 
                //CLINICO
                if(r.getString(2).equals("Sin examen"))
                    RegistroEmbarazoEXF.txtClinicoSinExamen.setText("X");
                if(r.getString(2).equals("Patologico"))
                    RegistroEmbarazoEXF.txtClinicoPatologico.setText("X");
                if(r.getString(2).equals("Normal"))
                    RegistroEmbarazoEXF.txtClinicoNormal.setText("X");
                
                //MAMAS
                if(r.getString(3).equals("Sin examen"))
                    RegistroEmbarazoEXF.txtMamasSinExamen.setText("X");
                if(r.getString(3).equals("Patologico"))
                    RegistroEmbarazoEXF.txtMamasPatologico.setText("X");
                if(r.getString(3).equals("Normal"))
                    RegistroEmbarazoEXF.txtMamasNormal.setText("X");
                
                //CUELLO UTERINO
                if(r.getString(4).equals("Sin examen"))
                    RegistroEmbarazoEXF.txtCUSinExamen.setText("X");
                if(r.getString(4).equals("Patologico"))
                    RegistroEmbarazoEXF.txtCuPatologico.setText("X");
                if(r.getString(4).equals("Normal"))
                    RegistroEmbarazoEXF.txtCuNormal.setText("X");
                
                //PELVIS
                if(r.getString(5).equals("Sin examen"))
                    RegistroEmbarazoEXF.txtPelvisSinExamen.setText("X");
                if(r.getString(5).equals("Patologico"))
                    RegistroEmbarazoEXF.txtPelvisPatologico.setText("X");
                if(r.getString(5).equals("Normal"))
                    RegistroEmbarazoEXF.txtPelvisNormal.setText("X");
                
                //ODONTOLOGICO
                if(r.getString(6).equals("Sin examen"))
                    RegistroEmbarazoEXF.txtOdontSinExamen.setText("X");
                if(r.getString(6).equals("Patologico"))
                    RegistroEmbarazoEXF.txtOdontPatologico.setText("X");
                if(r.getString(6).equals("Normal"))
                    RegistroEmbarazoEXF.txtOdontNormal.setText("X");
            }
            //
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtEfListar: " + e.getMessage());
        }
    }
    
    public ConsultorioExtCarnetPerinatalEf() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalEf(int efId) {
        this.efId = efId;
    }

    public int getEfId() {
        return efId;
    }

    public void setEfId(int efId) {
        this.efId = efId;
    }

    public String getEfEc() {
        return efEc;
    }

    public void setEfEc(String efEc) {
        this.efEc = efEc;
    }

    public String getEfMamas() {
        return efMamas;
    }

    public void setEfMamas(String efMamas) {
        this.efMamas = efMamas;
    }

    public String getEfCueUte() {
        return efCueUte;
    }

    public void setEfCueUte(String efCueUte) {
        this.efCueUte = efCueUte;
    }

    public String getEfPelvis() {
        return efPelvis;
    }

    public void setEfPelvis(String efPelvis) {
        this.efPelvis = efPelvis;
    }

    public String getEfOdont() {
        return efOdont;
    }

    public void setEfOdont(String efOdont) {
        this.efOdont = efOdont;
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

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalEf[ efId=" + efId + " ]";
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
     * @return the cpId
     */
    public int getCpId() {
        return cpId;
    }

    /**
     * @param cpId the cpId to set
     */
    public void setCpId(int cpId) {
        this.cpId = cpId;
    }
    
}
