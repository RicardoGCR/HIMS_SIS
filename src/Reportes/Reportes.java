/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author silvana
 */
public class Reportes {
   private static JasperReport report;
   private static JasperPrint informe;
   private static JasperViewer ventana;
    Connection conexion;
    Statement instrucion;

//METODO PARA EXPORTAR A PDF UN REPORTE   

    public void reportesPDFlista(String ruta, String archi) {
        try {
            String rutaInforme = ruta;
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream(rutaInforme), null, conexion);
            JasperExportManager.exportReportToPdfFile(informe, archi);

            
//            
//            JasperViewer ventanavisor = new JasperViewer(informe, false);
//            ventanavisor.setTitle("INFORME");
//            ventanavisor.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO"+e.getMessage());
        }
    }
   
}
