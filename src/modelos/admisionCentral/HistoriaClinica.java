package modelos.admisionCentral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import servicios.Conexion;
import vista.admisionCentral.FrmNuevaHistoriaC;

public class HistoriaClinica {

    private Connection cn;
    private String id_hc;
    private String dni;
    private String cod_hc;
    private String ape_pat;
    private String ape_mat;
    private String nombre1;
    private String nombre2;
    private String nombre3;
    private String fec_nac;
    private String sexo;
    private String dep_act;
    private String pro_act;
    private String dis_act;
    private String cod_dis;
    private String tipo_dir_nom;
    private String nom_dir;
    private String cod_dis_nac;
    private String dis_nac;
    private String pro_nac;
    private String dep_nac;
    private String ocupacion;
    private String estado_civil;
    private String grupo_sang;
    private String religion;
    private String telefono;
    private String celular;
    private String grado_inst;
    private String nacionalidad;
    private String fecha_actu;
    private String hora_actu;
    private String nom_usu;
    private String nom_pc;
    private String estado_hc_uso;
    private String ultima_atencion;
    private String estado_hc;
    private String se_cod;
    private String num;
    private String lote;
    private String cod_huella;
    private String cod_barra;
    private String riesgo;
    Conexion con = new Conexion();
    public boolean nuevaHistoriaClinica()
        {
        boolean resp = false;
        try{
            String sql = "EXEC SP_ADMISION_HISTORIACLINICA_INSERTAR "
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_hc());
            cmd.setString(2, getDni());
            cmd.setString(3, getCod_hc());
            cmd.setString(4, getApe_pat());
            cmd.setString(5, getApe_mat());
            cmd.setString(6, getNombre1());
            cmd.setString(7, getNombre2());
            cmd.setString(8, getNombre3());
            cmd.setString(9, getFec_nac());
            cmd.setString(10, getSexo());
            cmd.setString(11, getDep_act());
            cmd.setString(12, getPro_act());
            cmd.setString(13, getDis_act());
            cmd.setString(14, getCod_dis());
            cmd.setString(15, getTipo_dir_nom());
            cmd.setString(16, getNom_dir());
            cmd.setString(17, getCod_dis_nac());
            cmd.setString(18, getDis_nac());
            cmd.setString(19, getPro_nac());
            cmd.setString(20, getDep_nac());
            cmd.setString(21, getOcupacion());
            cmd.setString(22, getEstado_civil());
            cmd.setString(23, getGrupo_sang());
            cmd.setString(24, getReligion());
            cmd.setString(25, getTelefono());
            cmd.setString(26, getCelular());
            cmd.setString(27, getGrado_inst());
            cmd.setString(28, getNacionalidad());
            cmd.setString(29, getNom_usu());
            cmd.setString(30, getSe_cod());
            cmd.setString(31, getNum());
            cmd.setString(32, getLote());
            cmd.setString(33, getRiesgo());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error_nuevaHistoriaClinica: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean modificarHistoriaClinica()
    {
        boolean resp = false;
        try
        {
            String sql = "EXEC SP_ADMISION_HISTORIACLINICA_MODIFICAR ?,?,?,?,?,\n" +
"			 ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_hc());
            cmd.setString(2, getDni());
            cmd.setString(3, getApe_pat());
            cmd.setString(4, getApe_mat());
            cmd.setString(5, getNombre1());
            cmd.setString(6, getNombre2());
            cmd.setString(7, getNombre3());
            cmd.setString(8, getFec_nac());
            cmd.setString(9, getSexo());
            cmd.setString(10, getDep_act());
            cmd.setString(11, getPro_act());
            cmd.setString(12, getDis_act());
            cmd.setString(13, getCod_dis());
            cmd.setString(14, getTipo_dir_nom());
            cmd.setString(15, getNom_dir());
            cmd.setString(16, getCod_dis_nac());
            cmd.setString(17, getDis_nac());
            cmd.setString(18, getPro_nac());
            cmd.setString(19, getDep_nac());
            cmd.setString(20, getOcupacion());
            cmd.setString(21, getEstado_civil());
            cmd.setString(22, getGrupo_sang());
            cmd.setString(23, getReligion());
            cmd.setString(24, getTelefono());
            cmd.setString(25, getCelular());
            cmd.setString(26, getGrado_inst());
            cmd.setString(27, getNacionalidad());
            cmd.setString(28, getNom_usu());
            cmd.setString(29, getEstado_hc_uso());
            cmd.setString(30, getSe_cod());
            cmd.setString(31, getNum());
            cmd.setString(32, getLote());
            cmd.setString(33, getRiesgo());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error_modificarHistoriaClinica: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean eliminarHistoriaClinica()
    {
        boolean resp = false;
        try
        {
            String sql = "EXEC SP_ADMISION_HISTORIACLINICA_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_hc());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error_eliminar: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean reasignarCodHC()
    {
        boolean resp = false;
        try
        {
            String sql = "EXEC SP_ADMISION_HISTORIACLINICA_REASIGNAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_hc());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error_reasignarCodHC: " + ex.getMessage());
        }
        return resp;
    }
    
    public String obtenerIDReasignar(){
        String cod = "";
        try {
            String consulta = "EXEC SP_ADMISION_HISTORIACLINICA_SELECCION_ID_HC_R";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               cod = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error_obtenerIDReasignar: " + ex.getMessage());
        }
        return cod;
    }
    
    public String estadoInactivo(){
        String cod = "";
        try {
            String consulta = "EXEC SP_ADMISION_HISTORIA_CLINICA_ESTADO_INACTIVO";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               cod = r.getString(1);
        }
        }catch(Exception ex){
        }
        return cod;
    }
    
    public String obtenerCODReasignar(){
        String cod = "";
        try {
            String consulta = "EXEC SP_ADMISION_HISTORIACLINICA_SELECCION_COD_HC_R";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               cod = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error_obtenerCODReasignar: " + ex.getMessage());
        }
        return cod;
    }
    
    public boolean guardarDatosReasignar()
    {
        boolean resp = false;
        try
        {
            String sql = "EXEC SP_ADMISION_HISTORIACLINICA_GUARDAR_DATOS_REASIGNAR ?,?,?,?,?,?,?,?,?,?,?,?,?,\n" +
                         "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_hc());
            cmd.setString(2, getCod_hc());
            cmd.setString(3, getDni());
            cmd.setString(4, getApe_pat());
            cmd.setString(5, getApe_mat());
            cmd.setString(6, getNombre1());
            cmd.setString(7, getNombre2());
            cmd.setString(8, getNombre3());
            cmd.setString(9, getFec_nac());
            cmd.setString(10, getSexo());
            cmd.setString(11, getDep_act());
            cmd.setString(12, getPro_act());
            cmd.setString(13, getDis_act());
            cmd.setString(14, getCod_dis());
            cmd.setString(15, getTipo_dir_nom());
            cmd.setString(16, getNom_dir());
            cmd.setString(17, getCod_dis_nac());
            cmd.setString(18, getDis_nac());
            cmd.setString(19, getPro_nac());
            cmd.setString(20, getDep_nac());
            cmd.setString(21, getOcupacion());
            cmd.setString(22, getEstado_civil());
            cmd.setString(23, getGrupo_sang());
            cmd.setString(24, getReligion());
            cmd.setString(25, getTelefono());
            cmd.setString(26, getCelular());
            cmd.setString(27, getGrado_inst());
            cmd.setString(28, getNacionalidad());
            cmd.setString(29, getNom_usu());
            cmd.setString(30, getSe_cod());
            cmd.setString(31, getNum());
            cmd.setString(32, getLote());
            cmd.setString(33, getRiesgo());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error_guardarDatosReasignar: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean restaurarHistoriaClinica()
    {
        boolean resp = false;
        try
        {
            String sql = "EXEC SP_ADMISION_HISTORIACLINICA_RESTAURAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_hc());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error_restaurarHistoriaClinica: " + ex.getMessage());
        }
        return resp;
    }
    
    public String idHistoriaClinica(){
        String id = "";
        try {
            String consulta = "exec SP_ADMISION_HISTORIACLINICA_GENERAR_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error_idHistoriaClinica: " + ex.getMessage());
        }
        return id;
    }
    
    public String codHistoriaClinica(){
        String cod = "";
        try {
            String consulta = "exec SP_ADMISION_HISTORIACLINICA_GENERAR_NUMERO";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               cod = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error_codHistoriaClinica: " + ex.getMessage());
        }
        return cod;
    }
    
    public int ver_usuario(String usu)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM Usuario where usu_usuario=? ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, usu);
            ResultSet rs = cmd.executeQuery();
            for (int i=0; rs.next (); i++)
            {
               resultado++;
            }
            
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error_verUsuario: " + ex.getMessage());
        }
        return resultado;
    }
 
    public String codDistrito(String distrito)
    {
        String cod="";
        try
        {
            String sql = "SELECT COD_DIS \n" +
                        "FROM SISTEMA_DISTRITO\n" +
                        "WHERE NOMBRE_DISTRITO = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, distrito);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_codDistrito: " + ex.getMessage());
        }
        return cod;
    }
    
    public HistoriaClinica()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    // GETTER Y SETTER
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
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the cod_hc
     */
    public String getCod_hc() {
        return cod_hc;
    }

    /**
     * @param cod_hc the cod_hc to set
     */
    public void setCod_hc(String cod_hc) {
        this.cod_hc = cod_hc;
    }

    /**
     * @return the ape_pat
     */
    public String getApe_pat() {
        return ape_pat;
    }

    /**
     * @param ape_pat the ape_pat to set
     */
    public void setApe_pat(String ape_pat) {
        this.ape_pat = ape_pat;
    }

    /**
     * @return the ape_mat
     */
    public String getApe_mat() {
        return ape_mat;
    }

    /**
     * @param ape_mat the ape_mat to set
     */
    public void setApe_mat(String ape_mat) {
        this.ape_mat = ape_mat;
    }

    /**
     * @return the nombre1
     */
    public String getNombre1() {
        return nombre1;
    }

    /**
     * @param nombre1 the nombre1 to set
     */
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    /**
     * @return the nombre2
     */
    public String getNombre2() {
        return nombre2;
    }

    /**
     * @param nombre2 the nombre2 to set
     */
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    /**
     * @return the nombre3
     */
    public String getNombre3() {
        return nombre3;
    }

    /**
     * @param nombre3 the nombre3 to set
     */
    public void setNombre3(String nombre3) {
        this.nombre3 = nombre3;
    }

    /**
     * @return the fec_nac
     */
    public String getFec_nac() {
        return fec_nac;
    }

    /**
     * @param fec_nac the fec_nac to set
     */
    public void setFec_nac(String fec_nac) {
        this.fec_nac = fec_nac;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    /**
     * @return the cod_dis
     */
    public String getCod_dis() {
        return cod_dis;
    }

    /**
     * @param cod_dis the cod_dis to set
     */
    public void setCod_dis(String cod_dis) {
        this.cod_dis = cod_dis;
    }

    /**
     * @return the tipo_dir_nom
     */
    public String getTipo_dir_nom() {
        return tipo_dir_nom;
    }

    /**
     * @param tipo_dir_nom the tipo_dir_nom to set
     */
    public void setTipo_dir_nom(String tipo_dir_nom) {
        this.tipo_dir_nom = tipo_dir_nom;
    }

    /**
     * @return the nom_dir
     */
    public String getNom_dir() {
        return nom_dir;
    }

    /**
     * @param nom_dir the nom_dir to set
     */
    public void setNom_dir(String nom_dir) {
        this.nom_dir = nom_dir;
    }

    /**
     * @return the cod_dis_nac
     */
    public String getCod_dis_nac() {
        return cod_dis_nac;
    }

    /**
     * @param cod_dis_nac the cod_dis_nac to set
     */
    public void setCod_dis_nac(String cod_dis_nac) {
        this.cod_dis_nac = cod_dis_nac;
    }

    /**
     * @return the dis_nac
     */
    public String getDis_nac() {
        return dis_nac;
    }

    /**
     * @param dis_nac the dis_nac to set
     */
    public void setDis_nac(String dis_nac) {
        this.dis_nac = dis_nac;
    }

    /**
     * @return the pro_nac
     */
    public String getPro_nac() {
        return pro_nac;
    }

    /**
     * @param pro_nac the pro_nac to set
     */
    public void setPro_nac(String pro_nac) {
        this.pro_nac = pro_nac;
    }

    /**
     * @return the dep_nac
     */
    public String getDep_nac() {
        return dep_nac;
    }

    /**
     * @param dep_nac the dep_nac to set
     */
    public void setDep_nac(String dep_nac) {
        this.dep_nac = dep_nac;
    }

    /**
     * @return the ocupacion
     */
    public String getOcupacion() {
        return ocupacion;
    }

    /**
     * @param ocupacion the ocupacion to set
     */
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    /**
     * @return the estado_civil
     */
    public String getEstado_civil() {
        return estado_civil;
    }

    /**
     * @param estado_civil the estado_civil to set
     */
    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    /**
     * @return the grupo_sang
     */
    public String getGrupo_sang() {
        return grupo_sang;
    }

    /**
     * @param grupo_sang the grupo_sang to set
     */
    public void setGrupo_sang(String grupo_sang) {
        this.grupo_sang = grupo_sang;
    }

    /**
     * @return the religion
     */
    public String getReligion() {
        return religion;
    }

    /**
     * @param religion the religion to set
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the grado_inst
     */
    public String getGrado_inst() {
        return grado_inst;
    }

    /**
     * @param grado_inst the grado_inst to set
     */
    public void setGrado_inst(String grado_inst) {
        this.grado_inst = grado_inst;
    }

    /**
     * @return the nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
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
     * @return the nom_usu
     */
    public String getNom_usu() {
        return nom_usu;
    }

    /**
     * @param nom_usu the nom_usu to set
     */
    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
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
     * @return the estado_hc_uso
     */
    public String getEstado_hc_uso() {
        return estado_hc_uso;
    }

    /**
     * @param estado_hc_uso the estado_hc_uso to set
     */
    public void setEstado_hc_uso(String estado_hc_uso) {
        this.estado_hc_uso = estado_hc_uso;
    }

    /**
     * @return the ultima_atencion
     */
    public String getUltima_atencion() {
        return ultima_atencion;
    }

    /**
     * @param ultima_atencion the ultima_atencion to set
     */
    public void setUltima_atencion(String ultima_atencion) {
        this.ultima_atencion = ultima_atencion;
    }

    /**
     * @return the estado_hc
     */
    public String getEstado_hc() {
        return estado_hc;
    }

    /**
     * @param estado_hc the estado_hc to set
     */
    public void setEstado_hc(String estado_hc) {
        this.estado_hc = estado_hc;
    }

    /**
     * @return the dep_act
     */
    public String getDep_act() {
        return dep_act;
    }

    /**
     * @param dep_act the dep_act to set
     */
    public void setDep_act(String dep_act) {
        this.dep_act = dep_act;
    }

    /**
     * @return the pro_act
     */
    public String getPro_act() {
        return pro_act;
    }

    /**
     * @param pro_act the pro_act to set
     */
    public void setPro_act(String pro_act) {
        this.pro_act = pro_act;
    }

    /**
     * @return the dis_act
     */
    public String getDis_act() {
        return dis_act;
    }

    /**
     * @param dis_act the dis_act to set
     */
    public void setDis_act(String dis_act) {
        this.dis_act = dis_act;
    }
    
    /**
     * @return the se_cod
     */
    public String getSe_cod() {
        return se_cod;
    }

    /**
     * @param se_cod the se_cod to set
     */
    public void setSe_cod(String se_cod) {
        this.se_cod = se_cod;
    }

    /**
     * @return the num
     */
    public String getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * @return the lote
     */
    public String getLote() {
        return lote;
    }

    /**
     * @param lote the lote to set
     */
    public void setLote(String lote) {
        this.lote = lote;
    }

    /**
     * @return the cod_huella
     */
    public String getCod_huella() {
        return cod_huella;
    }

    /**
     * @param cod_huella the cod_huella to set
     */
    public void setCod_huella(String cod_huella) {
        this.cod_huella = cod_huella;
    }

    /**
     * @return the cod_barra
     */
    public String getCod_barra() {
        return cod_barra;
    }

    /**
     * @param cod_barra the cod_barra to set
     */
    public void setCod_barra(String cod_barra) {
        this.cod_barra = cod_barra;
    }

    /**
     * @return the riesgo
     */
    public String getRiesgo() {
        return riesgo;
    }

    /**
     * @param riesgo the riesgo to set
     */
    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }
 
}
