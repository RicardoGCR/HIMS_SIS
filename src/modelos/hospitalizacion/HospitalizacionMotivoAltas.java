/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.hospitalizacion;

import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
public class HospitalizacionMotivoAltas {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int ma_id;
    private String ma_descripcion;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String cod_usu;
}
