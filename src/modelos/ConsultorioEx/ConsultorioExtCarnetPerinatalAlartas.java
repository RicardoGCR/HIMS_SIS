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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;

    
/**
 *
 * @author MYS1
 */


public class ConsultorioExtCarnetPerinatalAlartas {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    


   public ConsultorioExtCarnetPerinatalAlartas() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
   }
    

