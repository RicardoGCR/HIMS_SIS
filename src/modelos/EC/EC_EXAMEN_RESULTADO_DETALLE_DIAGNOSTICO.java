/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.EC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;
import vista.EC.EC_EXAMEN_CAB;
import static vista.EC.EC_EXAMEN_CAB.btnGuardarCabeceraRes1;

/**
 *
 * @author MYS3
 */
public class EC_EXAMEN_RESULTADO_DETALLE_DIAGNOSTICO {
    private Connection cn;
    private int ID_DETALLE_RESULTADO_EC;
    private int ID_CIE10;
    private String NOM_USU;
    private String ID_DOCUMENTO;
    private int FUA;
    private String SERVICIO;
    private int ID_DETALLE;
    
    
    public EC_EXAMEN_RESULTADO_DETALLE_DIAGNOSTICO()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public boolean RX_EC_INFORME_DETALLE_DIAGNOSTICO_GUARDAR(){
        boolean resp = false;
        try
        {
            String sql = "exec RX_EC_INFORME_RESULTADO_DETALLE_DIAGNOSTICO_EC ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_DETALLE_RESULTADO_EC());
            cmd.setInt(2, getID_CIE10());
            cmd.setString(3, getNOM_USU());
                        
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error detalle diagnostico: " + ex.getMessage());
        }
        return resp;
    }
    
        public boolean EC_FUA()
        {
        boolean resp = false;
        try{
            String sql = "EXEC EC_GENERAR_FUA_REF ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_DOCUMENTO());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error : " + ex.getMessage());
        }
        return resp;
    }
        
        
    public boolean CEX_FUA()
        {
        boolean resp = false;
        try{
            String sql = "EXEC CEX_GENERAR_FUA_REF ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_DOCUMENTO());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error : " + ex.getMessage());
        }
        return resp;
    }    
        
     public void mostrar_FP_EC(String cod){
        String consulta="";
        try {
            String sql = "EXEC RX_EC_FP ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, cod);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                EC_EXAMEN_CAB.lblFP_EC.setText(r.getString(1));
                EC_EXAMEN_CAB.lblFUA_EC.setText(r.getString(2));
                String a = "";
                  String b = "";
                  a=String.valueOf(EC_EXAMEN_CAB.lblFP_EC.getText().charAt(0));
                  b=String.valueOf(EC_EXAMEN_CAB.lblFP_EC.getText().charAt(1));
                  a=(String.valueOf(a)+String.valueOf(b));
                  System.out.println("aa"+a);

                  if(a.equals("SI")){
                      EC_EXAMEN_CAB.btnGuardarCabeceraRes1.setEnabled(true);
                  }else{
                      EC_EXAMEN_CAB.btnGuardarCabeceraRes1.setEnabled(false);
                  }
                  if(r.getString(2).equals("             ")){
                      EC_EXAMEN_CAB.panelFormaPago1.setVisible(true);
                  }else{
                      EC_EXAMEN_CAB.panelFormaPago1.setVisible(false);
                  }
            }
            
        }catch (Exception e) {
            System.out.println("Error carga cod cabecera: " + e.getMessage());
        }
    }   
    
        
    public boolean EC_FUA_LOCAL()
        {
        boolean resp = false;
        try{
            String sql = "EXEC EC_GENERAR_FUA_LOCAL ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_DETALLE());
            cmd.setInt(2, getFUA());
            cmd.setString(3, getSERVICIO());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error INSERTAR FUA: " + ex.getMessage());
        }
        return resp;
    }
        
    public int getID_DETALLE_RESULTADO_EC() {
        return ID_DETALLE_RESULTADO_EC;
    }

    public void setID_DETALLE_RESULTADO_EC(int ID_DETALLE_RESULTADO_EC) {
        this.ID_DETALLE_RESULTADO_EC = ID_DETALLE_RESULTADO_EC;
    }

    public int getID_CIE10() {
        return ID_CIE10;
    }

    public void setID_CIE10(int ID_CIE10) {
        this.ID_CIE10 = ID_CIE10;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
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

    public int getFUA() {
        return FUA;
    }

    public void setFUA(int FUA) {
        this.FUA = FUA;
    }

    public String getSERVICIO() {
        return SERVICIO;
    }

    public void setSERVICIO(String SERVICIO) {
        this.SERVICIO = SERVICIO;
    }

    public int getID_DETALLE() {
        return ID_DETALLE;
    }

    public void setID_DETALLE(int ID_DETALLE) {
        this.ID_DETALLE = ID_DETALLE;
    }
    
    
    
}
