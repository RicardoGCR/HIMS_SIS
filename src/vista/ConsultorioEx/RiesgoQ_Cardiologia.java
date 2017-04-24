/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Caja.Caja_DetallePreventa;
import modelos.ConsultorioEx.ConsultorioExRQDetalle;
import modelos.ConsultorioEx.ConsultorioExRiesgoQuirurgico;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import modelos.admisionEmergencia.AdmisionEmergenciaTriaje;
import servicios.Conexion;

/**
 *
 * @author MYS1
 */
public class RiesgoQ_Cardiologia extends javax.swing.JFrame {
    DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    ResultSet r;
    ConsultorioExRiesgoQuirurgico consul1 = new ConsultorioExRiesgoQuirurgico();
    PreparedStatement pstm;
    public RiesgoQ_Cardiologia() {
        initComponents();
        this.setLocationRelativeTo(null);//en el centro
        this.setResizable(false);//deshabilita boton maximizar
        this.getContentPane().setBackground(Color.WHITE);//color blanco del formulario
         FrmCie10.setLocationRelativeTo(null);//en el centro
         FrmCie10.getContentPane().setBackground(Color.WHITE);
         FrmNomenclaturas.setLocationRelativeTo(null);//en el centro
         FrmNomenclaturas.getContentPane().setBackground(Color.WHITE);
         cbxProcedencia.setBackground(new Color(204,204,204));
         //BOTON CERRAR
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        // BOTON ESCAPE (ESC)
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();
            }
        });
        cerrar();
        pnlMensaje.setVisible(false);
        consul1.inicializarTabla(tbDiagnostico);
        consul1.inicializarTablaNomenclatura(tbCpt);
        conexion = c.conectar();
        this.cbxProcedencia.setModel(areas());
        txtId1.setVisible(false);
        cbxMedico.setModel(medicos());
        txtMedico.setVisible(false);
        lblMant.setVisible(false);
        txtId.setVisible(false);
        lblTriaje.setVisible(false);
        lblHc.setVisible(false);
        lblPreventa.setVisible(false);
        lblArea.setVisible(false);
    }

    public void cerrar (){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    dispose();
                }
});
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void limpiar(){
        txtNhc.setText("");
        lblActoMedico.setText("_______");
        lblPaciente.setText("_________________________________________");
        lblEdad.setText("_____");
        lblOcupacion.setText("_________________________________________");
        cbxProcedencia.setSelectedIndex(0);
        cbxMedico.setSelectedIndex(0);
        DefaultTableModel modelo1 = (DefaultTableModel)tbDiagnostico.getModel(); 
        int b=tbDiagnostico.getRowCount();
        for(int j=0;j<b;j++){
                    modelo1.removeRow(0);
        }
        txtExamenFisico.setText("");
        DefaultTableModel modelo2 = (DefaultTableModel)tbCpt.getModel(); 
        int b2=tbCpt.getRowCount();
        for(int j=0;j<b2;j++){
                    modelo2.removeRow(0);
        }
        chkDisnea.setSelected(false);
        chkDm.setSelected(false);
        chkEnfRenal.setSelected(false);
        chkHta.setSelected(false);
        chkPalpitaciones.setSelected(false);
        chkTos.setSelected(false);
        cbxAlergia.setSelectedIndex(0);
        txtQxAnteriores.setText("");
        txtOtros.setText("");
        txtOtrosSintomas.setText("");
        txtRq.setText("");
        txtSugerencia.setText("");
    }
    
    public void habilitarCampos(boolean opcion){
        txtNhc.setEnabled(opcion);
        cbxProcedencia.setEnabled(opcion);
        txtExamenFisico.setEnabled(opcion);
        btnPaciente.setEnabled(opcion);
        btnAgregarCpt.setEnabled(opcion);
        btnAgregarDiagnostico.setEnabled(opcion);
        chkDisnea.setEnabled(opcion);
        chkDm.setEnabled(opcion);
        chkEnfRenal.setEnabled(opcion);
        chkHta.setEnabled(opcion);
        chkPalpitaciones.setEnabled(opcion);
        chkTos.setEnabled(opcion);
        cbxAlergia.setEnabled(opcion);
        txtQxAnteriores.setEnabled(opcion);
        txtOtros.setEnabled(opcion);
        txtOtrosSintomas.setEnabled(opcion);
        txtRq.setEnabled(opcion);
        txtSugerencia.setEnabled(opcion);
        cbxMedico.setEnabled(opcion);
        txtMedico.setEnabled(true);
    }
    
    public DefaultComboBoxModel areas(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = conexion.createStatement();
              r = st.executeQuery ("EXEC CONSULTORIO_EXT_LISTAR_SERVICIOS"); 
              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "AR_DESC" ) );                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error servicios :" + ex.getMessage() );
        }        
        return listmodel;
    }
    
    public DefaultComboBoxModel medicos(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = conexion.createStatement();
              r = st.executeQuery ("EXEC CONSULTORIO_EXT_MEDICO_TURNO_LISTAR "+ Integer.parseInt(lblArea.getText()) +""); 
              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "MEDICO" ) );                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error: medicos " + ex.getMessage() );
        }        
        return listmodel;
    }
    
    public void enviarDatosPaciente(){
        int fila = tbPacientes.getSelectedRow();
        RiesgoQ_Cardiologia.lblTriaje.setText(String.valueOf(tbPacientes.getValueAt(fila, 0)));
        RiesgoQ_Cardiologia.lblActoMedico.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
        RiesgoQ_Cardiologia.txtNhc.setText(String.valueOf(tbPacientes.getValueAt(fila, 3)));
        RiesgoQ_Cardiologia.lblPaciente.setText(String.valueOf(tbPacientes.getValueAt(fila, 4)));
        RiesgoQ_Cardiologia.lblEdad.setText(String.valueOf(tbPacientes.getValueAt(fila, 5)));
        RiesgoQ_Cardiologia.lblOcupacion.setText(String.valueOf(tbPacientes.getValueAt(fila, 6)));
        RiesgoQ_Cardiologia.lblHc.setText(String.valueOf(tbPacientes.getValueAt(fila, 13)));
        FrmPacientes.dispose();
    }
    
    public void formatotbDiagnosticos(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(550);
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public void formatoTablaNomenclatura(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(600);
//        COLUMNAS OCULTAS
//        TableColumn columna = tabla.getColumnModel().getColumn(0);
//            columna.setMaxWidth(0);
//            columna.setMinWidth(0);
//            columna.setPreferredWidth(0);
//            tabla.doLayout();
        tabla.setRowHeight(30);
    }
    
    public boolean repiteDiagImp(JTable tablaEnvia, JTable tablaRecibe){
        int filaselec=tablaEnvia.getSelectedRow();
        boolean c=false;
        for (int i = 0; i < tablaRecibe.getRowCount(); i++){    
            if(tablaRecibe.getValueAt(i, 0).toString().equalsIgnoreCase(tablaEnvia.getValueAt(filaselec, 0).toString())){
                    c=true;
            }
        }
        return c;
     }
    
    public void enviarDiagnosticos(JTable tablaEnvia, JTable tablaRecibe, JDialog dialogo){
        m = (DefaultTableModel) tablaRecibe.getModel();
        int fila = tablaEnvia.getSelectedRow();
        if(tablaRecibe.getColumnCount()==0){
            dialogo.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tablaEnvia.getValueAt(fila, 0)),
            String.valueOf(tablaEnvia.getValueAt(fila, 1)),
            String.valueOf(tablaEnvia.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbDiagnosticos(tablaRecibe);
            //btnQuitarDiag.setEnabled(true);
        } else
        if(repiteDiagImp(tablaEnvia,tablaRecibe)==true)
            JOptionPane.showMessageDialog(dialogo, "Ya existe este registro");
        else {
            dialogo.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tablaEnvia.getValueAt(fila, 0)),
            String.valueOf(tablaEnvia.getValueAt(fila, 1)),
            String.valueOf(tablaEnvia.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbDiagnosticos(tablaRecibe);
            //btnQuitarDiag.setEnabled(true);
        }
    }
    
    public void quitarRegistro(JTable tabla){
        if(tabla.getSelectedRowCount()!=0){
            m= (DefaultTableModel) tabla.getModel(); 
            m.removeRow(tabla.getSelectedRow()); 
        } else 
            JOptionPane.showMessageDialog(this, "Seleccione un registro");
    }
    
    public boolean guardarPreventa(){
        boolean retorna = false;
        try {
            AdmisionEmergenciaCabecera adEmerCab5 = new AdmisionEmergenciaCabecera();
            ConsultorioExRiesgoQuirurgico consultorio3 = new ConsultorioExRiesgoQuirurgico();
            consultorio3.setUsuario(adEmerCab5.codUsuario(lblusu.getText()));
            if(consultorio3.mantenimientoPreventa(lblMant.getText(),lblHc.getText())==true){
                //lblPreventa.setText(consultorio3.preventaID());
                retorna = true;
            } else {
                pnlMensaje.setVisible(true);
                pnlMensaje.setBackground(new Color(255,91,70));
                lblMensaje.setText("Ocurrió un error, verifique");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                retorna = false;
            }
        } catch (Exception e) {
            System.out.println("Error: guardarPreventa" + e.getMessage());
        }
        return retorna;
    }
    
    public boolean guardarDiagnostico(int id_riesgo){
        AdmisionEmergenciaCabecera adEmer2 = new AdmisionEmergenciaCabecera();
        boolean retorna = false;
        try {
            for (int i = 0; i < tbDiagnostico.getRowCount(); i++){      
                ConsultorioExRQDetalle consultorio4 = new ConsultorioExRQDetalle();
                consultorio4.setRq_id(id_riesgo);
                consultorio4.setId_cie10(Integer.parseInt(tbDiagnostico.getValueAt(i,0).toString()));
                consultorio4.setUsuario(adEmer2.codUsuario(lblusu.getText()));
                if(consultorio4.mantenimientoConsultorioExRQDetalle("I")==true)
                    retorna = true;
            }
        } catch (Exception e) {
            System.out.println("Error: guardarDiagnostico" + e.getMessage());
        }
        return retorna;
    }
    //falta
    public boolean guardarNomenclatura(int preventa){
        AdmisionEmergenciaCabecera adEmer2 = new AdmisionEmergenciaCabecera();
        boolean retorna = false;
        try {
            for (int i = 0; i < tbCpt.getRowCount(); i++){      
                Caja_DetallePreventa caja = new Caja_DetallePreventa();
                caja.setId_Preventa(preventa);
                caja.setCod_nome(tbCpt.getValueAt(i,0).toString());
                caja.setCod_usu(adEmer2.codUsuario(lblusu.getText()));
                if(caja.mantenimientoDetallePreventa("I")==true)
                    retorna = true;
            }
        } catch (Exception e) {
            System.out.println("Error: guardarNomenclatura" + e.getMessage());
        }
        return retorna;
    }
    
    public boolean guardarDatos(){
        boolean retorna = false;
        try {
            AdmisionEmergenciaCabecera adEmerCab5 = new AdmisionEmergenciaCabecera();
            ConsultorioExRiesgoQuirurgico consultorio3 = new ConsultorioExRiesgoQuirurgico();
            consultorio3.setAr_id(Integer.parseInt(lblArea.getText()));
            consultorio3.setProcedencia(Integer.parseInt(consultorio3.areaID(cbxProcedencia.getSelectedItem().toString())));
            consultorio3.setEx_fisico(txtExamenFisico.getText());
            consultorio3.setQx(txtQxAnteriores.getText());
            consultorio3.setOtros(txtOtros.getText());
            consultorio3.setSint_otros(txtOtrosSintomas.getText());
            consultorio3.setDesc(txtRq.getText());
            consultorio3.setSugerencia(txtSugerencia.getText());
            consultorio3.setUsuario(adEmerCab5.codUsuario(lblusu.getText()));
            consultorio3.setId_triaje(lblTriaje.getText());
            consultorio3.setCod_per(consultorio3.medicoID(cbxMedico.getSelectedItem().toString()));
            //
            if(chkHta.isSelected())
                consultorio3.setHta("X");
            else
                consultorio3.setHta("");
            //
            if(chkDm.isSelected())
                consultorio3.setDm("X");
            else
                consultorio3.setDm("");
            //
            if(chkEnfRenal.isSelected())
                consultorio3.setRenal("X");
            else
                consultorio3.setRenal("");
            //
            if(chkDisnea.isSelected())
                consultorio3.setDisnea("X");
            else
                consultorio3.setDisnea("");
            //
            if(chkPalpitaciones.isSelected())
                consultorio3.setPalpit("X");
            else
                consultorio3.setPalpit("");
            //
            if(chkTos.isSelected())
                consultorio3.setTos("X");
            else
                consultorio3.setTos("");
            //
            if(cbxAlergia.getSelectedItem().equals("SI"))
                consultorio3.setAlergia("X");
            else
                consultorio3.setAlergia("");
            //
            if(guardarPreventa()==true){
            int id = Integer.parseInt(consultorio3.preventaID());
            consultorio3.setPreventa(id);
                if(consultorio3.mantenimientoConsultorioExRQ(lblMant.getText())==true){
                    if(tbDiagnostico.getRowCount()!=0 && tbCpt.getRowCount()!=0){
                        if(guardarDiagnostico(Integer.parseInt(consultorio3.rqID()))&& guardarNomenclatura(id)){
                            pnlMensaje.setVisible(true);
                            lblMensaje.setText("Datos guardados de forma correcta");
                            limpiar();
                            habilitarCampos(false);
                            btnGuardar.setEnabled(false);
                            pnlMensaje.setBackground(new Color(33,115,70));
                            btnSi.setVisible(true);
                            btnSi.setText("OK");
                            btnNo.setVisible(false);
                        } else {
                            pnlMensaje.setVisible(true);
                            lblMensaje.setText("Ocurrió un error, verifique");
                            pnlMensaje.setBackground(new Color(255,91,70));
                            btnSi.setVisible(false);
                            btnNo.setVisible(false);
                        }
                    } else
                    if(tbDiagnostico.getRowCount()==0 && tbCpt.getRowCount()!=0){
                        if(guardarNomenclatura(id)){
                            pnlMensaje.setVisible(true);
                            lblMensaje.setText("Datos guardados de forma correcta");
                            limpiar();
                            habilitarCampos(false);
                            btnGuardar.setEnabled(false);
                            pnlMensaje.setBackground(new Color(33,115,70));
                            btnSi.setVisible(true);
                            btnSi.setText("OK");
                            btnNo.setVisible(false);
                        } else {
                            pnlMensaje.setVisible(true);
                            lblMensaje.setText("Ocurrió un error, verifique");
                            pnlMensaje.setBackground(new Color(255,91,70));
                            btnSi.setVisible(false);
                            btnNo.setVisible(false);
                        }
                    } else
                    if(tbDiagnostico.getRowCount()!=0 && tbCpt.getRowCount()==0){
                        if(guardarDiagnostico(Integer.parseInt(consultorio3.rqID()))){
                            pnlMensaje.setVisible(true);
                            lblMensaje.setText("Datos guardados de forma correcta");
                            limpiar();
                            habilitarCampos(false);
                            btnGuardar.setEnabled(false);
                            pnlMensaje.setBackground(new Color(33,115,70));
                            btnSi.setVisible(true);
                            btnSi.setText("OK");
                            btnNo.setVisible(false);
                        } else {
                            pnlMensaje.setVisible(true);
                            lblMensaje.setText("Ocurrió un error, verifique");
                            pnlMensaje.setBackground(new Color(255,91,70));
                            btnSi.setVisible(false);
                            btnNo.setVisible(false);
                        }
                    } else
                    if(tbDiagnostico.getRowCount()==0 && tbCpt.getRowCount()==0){
                        pnlMensaje.setVisible(true);
                        lblMensaje.setText("Datos guardados de forma correcta");
                        limpiar();
                        habilitarCampos(false);
                        btnGuardar.setEnabled(false);
                        pnlMensaje.setBackground(new Color(33,115,70));
                        btnSi.setVisible(true);
                        btnSi.setText("OK");
                        btnNo.setVisible(false);
                    } 
                }
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ocurrió un error, verifique");
                pnlMensaje.setBackground(new Color(255,91,70));
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error: guardarDatos" + e.getMessage());
        }
        return retorna;
    }
    
    public boolean modificarDatos(){
        boolean retorna = false;
        try {
            ConsultorioExRiesgoQuirurgico consultorio3 = new ConsultorioExRiesgoQuirurgico();
            consultorio3.setId(Integer.parseInt(txtId.getText()));
            consultorio3.setAr_id(Integer.parseInt(lblArea.getText()));
            consultorio3.setProcedencia(Integer.parseInt(consultorio3.areaID(cbxProcedencia.getSelectedItem().toString())));
            consultorio3.setEx_fisico(txtExamenFisico.getText());
            consultorio3.setQx(txtQxAnteriores.getText());
            consultorio3.setOtros(txtOtros.getText());
            consultorio3.setSint_otros(txtOtrosSintomas.getText());
            consultorio3.setDesc(txtRq.getText());
            consultorio3.setSugerencia(txtSugerencia.getText());
            consultorio3.setPreventa(Integer.parseInt(lblPreventa.getText()));
            //
            if(chkHta.isSelected())
                consultorio3.setHta("X");
            else
                consultorio3.setHta("");
            //
            if(chkDm.isSelected())
                consultorio3.setDm("X");
            else
                consultorio3.setDm("");
            //
            if(chkEnfRenal.isSelected())
                consultorio3.setRenal("X");
            else
                consultorio3.setRenal("");
            //
            if(chkDisnea.isSelected())
                consultorio3.setDisnea("X");
            else
                consultorio3.setDisnea("");
            //
            if(chkPalpitaciones.isSelected())
                consultorio3.setPalpit("X");
            else
                consultorio3.setPalpit("");
            //
            if(chkTos.isSelected())
                consultorio3.setTos("X");
            else
                consultorio3.setTos("");
            //
            if(cbxAlergia.getSelectedItem().equals("SI"))
                consultorio3.setAlergia("X");
            else
                consultorio3.setAlergia("");
            //
                if(consultorio3.mantenimientoConsultorioExRQ(lblMant.getText())==true){
                    pnlMensaje.setVisible(true);
                    lblMensaje.setText("Datos guardados de forma correcta");
                    if(tbDiagnostico.getRowCount()!=0 && tbCpt.getRowCount()!=0){
                        if(guardarDiagnostico(Integer.parseInt(txtId.getText()))&& guardarNomenclatura(Integer.parseInt(lblPreventa.getText()))){
                            pnlMensaje.setVisible(true);
                            lblMensaje.setText("Datos guardados de forma correcta");
                            limpiar();
                            habilitarCampos(false);
                            btnGuardar.setEnabled(false);
                            pnlMensaje.setBackground(new Color(33,115,70));
                            btnSi.setVisible(true);
                            btnSi.setText("OK");
                            btnNo.setVisible(false);
                            txtMedico.setVisible(false);
                            cbxMedico.setVisible(true);
                        } else {
                            pnlMensaje.setVisible(true);
                            lblMensaje.setText("Ocurrió un error, verifique");
                            pnlMensaje.setBackground(new Color(255,91,70));
                            btnSi.setVisible(false);
                            btnNo.setVisible(false);
                        }
                    } else
                    if(tbDiagnostico.getRowCount()==0 && tbCpt.getRowCount()!=0){
                        if(guardarNomenclatura(Integer.parseInt(lblPreventa.getText()))){
                            pnlMensaje.setVisible(true);
                            lblMensaje.setText("Datos guardados de forma correcta");
                            limpiar();
                            habilitarCampos(false);
                            btnGuardar.setEnabled(false);
                            pnlMensaje.setBackground(new Color(33,115,70));
                            btnSi.setVisible(true);
                            btnSi.setText("OK");
                            btnNo.setVisible(false);
                        } else {
                            pnlMensaje.setVisible(true);
                            lblMensaje.setText("Ocurrió un error, verifique");
                            pnlMensaje.setBackground(new Color(255,91,70));
                            btnSi.setVisible(false);
                            btnNo.setVisible(false);
                        }
                    } else
                    if(tbDiagnostico.getRowCount()!=0 && tbCpt.getRowCount()==0){
                        if(guardarDiagnostico(Integer.parseInt(txtId.getText()))){
                            pnlMensaje.setVisible(true);
                            lblMensaje.setText("Datos guardados de forma correcta");
                            limpiar();
                            habilitarCampos(false);
                            btnGuardar.setEnabled(false);
                            pnlMensaje.setBackground(new Color(33,115,70));
                            btnSi.setVisible(true);
                            btnSi.setText("OK");
                            btnNo.setVisible(false);
                        } else {
                            pnlMensaje.setVisible(true);
                            lblMensaje.setText("Ocurrió un error, verifique");
                            pnlMensaje.setBackground(new Color(255,91,70));
                            btnSi.setVisible(false);
                            btnNo.setVisible(false);
                        }
                    } else
                    if(tbDiagnostico.getRowCount()==0 && tbCpt.getRowCount()==0){
                        pnlMensaje.setVisible(true);
                        lblMensaje.setText("Datos guardados de forma correcta");
                        limpiar();
                        habilitarCampos(false);
                        btnGuardar.setEnabled(false);
                        pnlMensaje.setBackground(new Color(33,115,70));
                        btnSi.setVisible(true);
                        btnSi.setText("OK");
                        btnNo.setVisible(false);
                    } 
                } else {
                    pnlMensaje.setVisible(true);
                     lblMensaje.setText("error");
                }
        } catch (Exception e) {
            System.out.println("Error: modificarDatos" + e.getMessage());
        }
        return retorna;
    }
    
    public void eliminarDatos(){
        ConsultorioExRiesgoQuirurgico consultorio3 = new ConsultorioExRiesgoQuirurgico();
            consultorio3.setId(Integer.parseInt(txtId.getText()));
            if(consultorio3.mantenimientoConsultorioExRQ("E")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos eliminados de forma correcta");
                limpiar();
                habilitarCampos(false);
                btnGuardar.setEnabled(false);
                pnlMensaje.setBackground(new Color(33,115,70));
                btnSi.setVisible(true);
                btnSi.setText("OK");
                btnNo.setVisible(false);
            } else{
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ocurrió un error, verifique");
                pnlMensaje.setBackground(new Color(255,91,70));
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            }
    }
    
    public String determinarFecha(JDateChooser calendario){
        String fecha = "";
        int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);
        int mes = calendario.getCalendar().get(Calendar.MONTH)+1;
        int anio = calendario.getCalendar().get(Calendar.YEAR); 
        if(dia < 10 && mes < 10){
            fecha = String.valueOf("0" + dia + "/" + "0" + mes + "/" + anio);
        }else 
            if(dia < 10 || mes < 10){
                if(dia < 10 && mes >=10){
                    fecha = String.valueOf("0" + dia + "/" + mes + "/" + anio);
                } else 
                    if(dia >= 10 && mes < 10){
                        fecha = String.valueOf(dia + "/" + "0" + mes + "/" + anio);
                    } 
            } else 
                fecha = String.valueOf(dia + "/" + mes + "/" + anio);
        return fecha;
    }
    
    public void enviarDatosRQ(){
        int fila = tbRQ.getSelectedRow();
        RiesgoQ_Cardiologia.txtId.setText(String.valueOf(tbRQ.getValueAt(fila, 0)));
        RiesgoQ_Cardiologia.lblActoMedico.setText(String.valueOf(tbRQ.getValueAt(fila, 1)));
        RiesgoQ_Cardiologia.txtNhc.setText(String.valueOf(tbRQ.getValueAt(fila, 3)));
        RiesgoQ_Cardiologia.lblPaciente.setText(String.valueOf(tbRQ.getValueAt(fila, 4)));
        RiesgoQ_Cardiologia.lblEdad.setText(String.valueOf(tbRQ.getValueAt(fila, 5)));
        RiesgoQ_Cardiologia.lblOcupacion.setText(String.valueOf(tbRQ.getValueAt(fila, 6)));
        RiesgoQ_Cardiologia.cbxProcedencia.setSelectedItem(String.valueOf(tbRQ.getValueAt(fila, 7)));
        RiesgoQ_Cardiologia.txtQxAnteriores.setText(String.valueOf(tbRQ.getValueAt(fila, 12)));
        RiesgoQ_Cardiologia.txtOtros.setText(String.valueOf(tbRQ.getValueAt(fila, 13)));
        RiesgoQ_Cardiologia.txtOtrosSintomas.setText(String.valueOf(tbRQ.getValueAt(fila, 17)));
        RiesgoQ_Cardiologia.txtExamenFisico.setText(String.valueOf(tbRQ.getValueAt(fila, 18)));
        RiesgoQ_Cardiologia.txtRq.setText(String.valueOf(tbRQ.getValueAt(fila, 19)));
        RiesgoQ_Cardiologia.txtSugerencia.setText(String.valueOf(tbRQ.getValueAt(fila, 20)));
        RiesgoQ_Cardiologia.lblPreventa.setText(String.valueOf(tbRQ.getValueAt(fila, 23)));
        RiesgoQ_Cardiologia.txtMedico.setText(String.valueOf(tbRQ.getValueAt(fila, 21)));
        cbxMedico.setVisible(false);
        txtMedico.setVisible(true);
        String hta = String.valueOf(tbRQ.getValueAt(fila, 8));
        String dm = String.valueOf(tbRQ.getValueAt(fila, 9));
        String enf = String.valueOf(tbRQ.getValueAt(fila, 10));
        String alergia = String.valueOf(tbRQ.getValueAt(fila, 11));
        String disnea = String.valueOf(tbRQ.getValueAt(fila, 14));
        String palp = String.valueOf(tbRQ.getValueAt(fila, 15));
        String tos = String.valueOf(tbRQ.getValueAt(fila, 16));
        if(hta.equals("X"))
            chkHta.setSelected(true);
        else
            chkHta.setSelected(false);
        if(dm.equals("X"))
            chkDm.setSelected(true);
        else
            chkDm.setSelected(false);
        if(enf.equals("X"))
            chkEnfRenal.setSelected(true);
        else
            chkEnfRenal.setSelected(false);
        if(alergia.equals("X"))
            cbxAlergia.setSelectedItem("SI");
        else
            cbxAlergia.setSelectedItem("NO");
        if(disnea.equals("X"))
            chkDisnea.setSelected(true);
        else
            chkDisnea.setSelected(false);
        if(palp.equals("X"))
            chkPalpitaciones.setSelected(true);
        else
            chkPalpitaciones.setSelected(false);
        if(tos.equals("X"))
            chkTos.setSelected(true);
        else
            chkTos.setSelected(false);
        FrmRiesgoQuirurgico.dispose();
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FrmCie10 = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        titulo7 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        tbCie10 = new javax.swing.JTable();
        txtBuscarCie10 = new javax.swing.JTextField();
        btnBuscarCie10 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        FrmNomenclaturas = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tb_Grupo4 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            txtBuscarCpt = new javax.swing.JTextField();
            btnBuscarCie11 = new javax.swing.JButton();
            FrmPacientes = new javax.swing.JDialog();
            jPanel17 = new javax.swing.JPanel();
            titulo8 = new javax.swing.JLabel();
            jScrollPane25 = new javax.swing.JScrollPane();
            tbPacientes = new javax.swing.JTable();
            txtBuscarPaciente = new javax.swing.JTextField();
            btnBuscarPaciente = new javax.swing.JButton();
            jLabel37 = new javax.swing.JLabel();
            FrmRiesgoQuirurgico = new javax.swing.JDialog();
            jPanel6 = new javax.swing.JPanel();
            jLabel8 = new javax.swing.JLabel();
            lblusu1 = new javax.swing.JLabel();
            txtId1 = new javax.swing.JTextField();
            txtBuscarRq = new javax.swing.JTextField();
            btnBuscarRQ = new javax.swing.JButton();
            dtFechaInicio = new com.toedter.calendar.JDateChooser();
            dtFechaFin = new com.toedter.calendar.JDateChooser();
            jPanel7 = new javax.swing.JPanel();
            jScrollPane9 = new javax.swing.JScrollPane();
            tbRQ = new javax.swing.JTable();
            jScrollPane10 = new javax.swing.JScrollPane();
            tbRqCie = new javax.swing.JTable();
            jScrollPane11 = new javax.swing.JScrollPane();
            tbRqCpt = new javax.swing.JTable();
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            btnNuevo = new javax.swing.JButton();
            btnModificar = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            btnEliminar = new javax.swing.JButton();
            btnBuscar = new javax.swing.JButton();
            lblusu = new javax.swing.JLabel();
            lblMant = new javax.swing.JLabel();
            txtId = new javax.swing.JTextField();
            lblArea = new javax.swing.JLabel();
            lblTriaje = new javax.swing.JLabel();
            lblHc = new javax.swing.JLabel();
            lblPreventa = new javax.swing.JLabel();
            jPanel2 = new javax.swing.JPanel();
            jPanel29 = new javax.swing.JPanel();
            txtNhc = new javax.swing.JTextField();
            btnPaciente = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            lblPaciente = new javax.swing.JLabel();
            lblOcupacion = new javax.swing.JLabel();
            lblEdad = new javax.swing.JLabel();
            cbxProcedencia = new javax.swing.JComboBox();
            jLabel11 = new javax.swing.JLabel();
            jScrollPane3 = new javax.swing.JScrollPane();
            tbDiagnostico = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                btnAgregarDiagnostico = new javax.swing.JButton();
                jLabel14 = new javax.swing.JLabel();
                jLabel21 = new javax.swing.JLabel();
                jLabel22 = new javax.swing.JLabel();
                txtExamenFisico = new javax.swing.JTextField();
                jScrollPane5 = new javax.swing.JScrollPane();
                tbCpt = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    btnAgregarCpt = new javax.swing.JButton();
                    jLabel7 = new javax.swing.JLabel();
                    lblActoMedico = new javax.swing.JLabel();
                    jPanel5 = new javax.swing.JPanel();
                    jLabel12 = new javax.swing.JLabel();
                    jLabel13 = new javax.swing.JLabel();
                    jLabel19 = new javax.swing.JLabel();
                    jLabel15 = new javax.swing.JLabel();
                    jLabel20 = new javax.swing.JLabel();
                    jPanel3 = new javax.swing.JPanel();
                    chkHta = new javax.swing.JCheckBox();
                    chkDm = new javax.swing.JCheckBox();
                    chkEnfRenal = new javax.swing.JCheckBox();
                    jLabel16 = new javax.swing.JLabel();
                    jLabel17 = new javax.swing.JLabel();
                    jLabel18 = new javax.swing.JLabel();
                    jPanel4 = new javax.swing.JPanel();
                    chkDisnea = new javax.swing.JCheckBox();
                    chkPalpitaciones = new javax.swing.JCheckBox();
                    chkTos = new javax.swing.JCheckBox();
                    jScrollPane1 = new javax.swing.JScrollPane();
                    txtOtros = new javax.swing.JEditorPane();
                    jScrollPane2 = new javax.swing.JScrollPane();
                    txtQxAnteriores = new javax.swing.JEditorPane();
                    jScrollPane4 = new javax.swing.JScrollPane();
                    txtOtrosSintomas = new javax.swing.JEditorPane();
                    cbxAlergia = new javax.swing.JComboBox();
                    jLabel23 = new javax.swing.JLabel();
                    jScrollPane6 = new javax.swing.JScrollPane();
                    txtRq = new javax.swing.JEditorPane();
                    jLabel24 = new javax.swing.JLabel();
                    jScrollPane7 = new javax.swing.JScrollPane();
                    txtSugerencia = new javax.swing.JEditorPane();
                    jLabel10 = new javax.swing.JLabel();
                    cbxMedico = new javax.swing.JComboBox();
                    txtMedico = new javax.swing.JTextField();
                    pnlMensaje = new javax.swing.JPanel();
                    lblMensaje = new javax.swing.JLabel();
                    btnSi = new javax.swing.JButton();
                    btnNo = new javax.swing.JButton();

                    FrmCie10.setMinimumSize(new java.awt.Dimension(600, 450));
                    FrmCie10.setResizable(false);

                    jPanel10.setBackground(new java.awt.Color(0, 153, 102));
                    jPanel10.setPreferredSize(new java.awt.Dimension(500, 65));
                    jPanel10.setLayout(null);

                    titulo7.setBackground(new java.awt.Color(153, 0, 51));
                    titulo7.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    titulo7.setForeground(new java.awt.Color(255, 255, 255));
                    titulo7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    titulo7.setText("CIE 10");
                    titulo7.setToolTipText("");
                    titulo7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    jPanel10.add(titulo7);
                    titulo7.setBounds(10, 10, 180, 41);

                    jScrollPane24.setBorder(null);
                    jScrollPane24.setOpaque(false);

                    tbCie10 = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false;
                        }
                    };
                    tbCie10.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                    ));
                    tbCie10.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tbCie10.setSelectionBackground(new java.awt.Color(0, 153, 102));
                    tbCie10.getTableHeader().setReorderingAllowed(false);
                    tbCie10.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tbCie10MouseClicked(evt);
                        }
                    });
                    tbCie10.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tbCie10KeyPressed(evt);
                        }
                    });
                    jScrollPane24.setViewportView(tbCie10);

                    jPanel10.add(jScrollPane24);
                    jScrollPane24.setBounds(0, 110, 595, 312);

                    txtBuscarCie10.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtBuscarCie10CaretUpdate(evt);
                        }
                    });
                    txtBuscarCie10.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtBuscarCie10KeyPressed(evt);
                        }
                    });
                    jPanel10.add(txtBuscarCie10);
                    txtBuscarCie10.setBounds(10, 60, 230, 30);

                    btnBuscarCie10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
                    btnBuscarCie10.setBorderPainted(false);
                    btnBuscarCie10.setContentAreaFilled(false);
                    btnBuscarCie10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    jPanel10.add(btnBuscarCie10);
                    btnBuscarCie10.setBounds(240, 60, 30, 30);

                    jLabel36.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
                    jLabel36.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel36.setText("Código CIE 10 / Diagnóstico Presuntivo");
                    jPanel10.add(jLabel36);
                    jLabel36.setBounds(10, 90, 220, 14);

                    javax.swing.GroupLayout FrmCie10Layout = new javax.swing.GroupLayout(FrmCie10.getContentPane());
                    FrmCie10.getContentPane().setLayout(FrmCie10Layout);
                    FrmCie10Layout.setHorizontalGroup(
                        FrmCie10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    );
                    FrmCie10Layout.setVerticalGroup(
                        FrmCie10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    );

                    FrmNomenclaturas.setAlwaysOnTop(true);
                    FrmNomenclaturas.setMinimumSize(new java.awt.Dimension(749, 338));
                    FrmNomenclaturas.setResizable(false);
                    FrmNomenclaturas.getContentPane().setLayout(null);

                    jPanel11.setBackground(new java.awt.Color(0, 153, 102));
                    jPanel11.setMinimumSize(new java.awt.Dimension(310, 441));

                    jLabel25.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel25.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel25.setText("CPT");

                    jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));
                    jScrollPane8.setBorder(null);
                    jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                    jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                    tb_Grupo4.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                    ));
                    tb_Grupo4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tb_Grupo4.setGridColor(new java.awt.Color(255, 255, 255));
                    tb_Grupo4.setRowHeight(25);
                    tb_Grupo4.setSelectionBackground(new java.awt.Color(0, 153, 102));
                    tb_Grupo4.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tb_Grupo4MouseClicked(evt);
                        }
                    });
                    tb_Grupo4.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tb_Grupo4KeyPressed(evt);
                        }
                    });
                    jScrollPane8.setViewportView(tb_Grupo4);

                    txtBuscarCpt.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtBuscarCptCaretUpdate(evt);
                        }
                    });
                    txtBuscarCpt.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtBuscarCptKeyPressed(evt);
                        }
                    });

                    btnBuscarCie11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
                    btnBuscarCie11.setBorderPainted(false);
                    btnBuscarCie11.setContentAreaFilled(false);
                    btnBuscarCie11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                    jPanel11.setLayout(jPanel11Layout);
                    jPanel11Layout.setHorizontalGroup(
                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 87, Short.MAX_VALUE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel25)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(txtBuscarCpt, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(btnBuscarCie11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    jPanel11Layout.setVerticalGroup(
                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnBuscarCie11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(txtBuscarCpt, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(129, 129, 129))
                    );

                    FrmNomenclaturas.getContentPane().add(jPanel11);
                    jPanel11.setBounds(0, 0, 830, 400);

                    FrmPacientes.setAlwaysOnTop(true);
                    FrmPacientes.setMinimumSize(new java.awt.Dimension(739, 450));

                    jPanel17.setBackground(new java.awt.Color(0, 153, 102));
                    jPanel17.setPreferredSize(new java.awt.Dimension(500, 65));
                    jPanel17.setLayout(null);

                    titulo8.setBackground(new java.awt.Color(153, 0, 51));
                    titulo8.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    titulo8.setForeground(new java.awt.Color(255, 255, 255));
                    titulo8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    titulo8.setText("Pacientes");
                    titulo8.setToolTipText("");
                    titulo8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    jPanel17.add(titulo8);
                    titulo8.setBounds(10, 10, 180, 41);

                    jScrollPane25.setBorder(null);
                    jScrollPane25.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                    jScrollPane25.setToolTipText("");
                    jScrollPane25.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                    jScrollPane25.setOpaque(false);

                    tbPacientes = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false;
                        }
                    };
                    tbPacientes.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                    ));
                    tbPacientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tbPacientes.setSelectionBackground(new java.awt.Color(0, 153, 102));
                    tbPacientes.getTableHeader().setReorderingAllowed(false);
                    tbPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tbPacientesMouseClicked(evt);
                        }
                    });
                    tbPacientes.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tbPacientesKeyPressed(evt);
                        }
                    });
                    jScrollPane25.setViewportView(tbPacientes);

                    jPanel17.add(jScrollPane25);
                    jScrollPane25.setBounds(0, 110, 735, 313);

                    txtBuscarPaciente.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtBuscarPacienteCaretUpdate(evt);
                        }
                    });
                    txtBuscarPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtBuscarPacienteKeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtBuscarPacienteKeyReleased(evt);
                        }
                    });
                    jPanel17.add(txtBuscarPaciente);
                    txtBuscarPaciente.setBounds(10, 60, 230, 30);

                    btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
                    btnBuscarPaciente.setBorderPainted(false);
                    btnBuscarPaciente.setContentAreaFilled(false);
                    btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    jPanel17.add(btnBuscarPaciente);
                    btnBuscarPaciente.setBounds(240, 60, 30, 30);

                    jLabel37.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
                    jLabel37.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel37.setText("Acto Médico/Nº H.C. / DNI / Apellidos y Nombres");
                    jPanel17.add(jLabel37);
                    jLabel37.setBounds(10, 90, 230, 14);

                    javax.swing.GroupLayout FrmPacientesLayout = new javax.swing.GroupLayout(FrmPacientes.getContentPane());
                    FrmPacientes.getContentPane().setLayout(FrmPacientesLayout);
                    FrmPacientesLayout.setHorizontalGroup(
                        FrmPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
                    );
                    FrmPacientesLayout.setVerticalGroup(
                        FrmPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    );

                    FrmRiesgoQuirurgico.setAlwaysOnTop(true);
                    FrmRiesgoQuirurgico.setMinimumSize(new java.awt.Dimension(1110, 650));

                    jPanel6.setBackground(new java.awt.Color(0, 153, 102));

                    jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel8.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel8.setText("Riesgo Quirúrgico");

                    lblusu1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                    lblusu1.setForeground(new java.awt.Color(255, 255, 255));
                    lblusu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
                    lblusu1.setText("Silvana");
                    lblusu1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                    txtId1.setEditable(false);
                    txtId1.setEnabled(false);
                    txtId1.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtId1CaretUpdate(evt);
                        }
                    });

                    txtBuscarRq.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    txtBuscarRq.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtBuscarRqCaretUpdate(evt);
                        }
                    });

                    btnBuscarRQ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
                    btnBuscarRQ.setMnemonic('B');
                    btnBuscarRQ.setToolTipText("Buscar (Alt + B)");
                    btnBuscarRQ.setBorderPainted(false);
                    btnBuscarRQ.setContentAreaFilled(false);
                    btnBuscarRQ.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnBuscarRQ.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscarRQActionPerformed(evt);
                        }
                    });

                    dtFechaInicio.setBackground(new java.awt.Color(0, 153, 102));
                    dtFechaInicio.setDateFormatString("dd/MM/yyyy");
                    dtFechaInicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                    dtFechaFin.setBackground(new java.awt.Color(0, 153, 102));
                    dtFechaFin.setDateFormatString("dd/MM/yyyy");
                    dtFechaFin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                    jPanel6.setLayout(jPanel6Layout);
                    jPanel6Layout.setHorizontalGroup(
                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 675, Short.MAX_VALUE)
                                    .addComponent(lblusu1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(16, 16, 16))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(txtBuscarRq, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(dtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(dtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnBuscarRQ, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(191, 191, 191))))
                    );
                    jPanel6Layout.setVerticalGroup(
                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addComponent(lblusu1)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(dtFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dtFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscarRQ, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBuscarRq, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(555, 555, 555))
                    );

                    jPanel7.setBackground(new java.awt.Color(255, 255, 255));

                    jScrollPane9.setBorder(null);

                    tbRQ = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false;
                        }
                    };
                    tbRQ.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                            {null, null, null, null},
                            {null, null, null, null},
                            {null, null, null, null},
                            {null, null, null, null}
                        },
                        new String [] {
                            "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                    ));
                    tbRQ.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tbRQ.setSelectionBackground(new java.awt.Color(0, 153, 102));
                    tbRQ.getTableHeader().setReorderingAllowed(false);
                    tbRQ.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tbRQMouseClicked(evt);
                        }
                    });
                    tbRQ.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tbRQKeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            tbRQKeyReleased(evt);
                        }
                    });
                    jScrollPane9.setViewportView(tbRQ);

                    jScrollPane10.setBorder(null);

                    tbRqCie = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false;
                        }
                    };
                    tbRqCie.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                            {null, null, null, null},
                            {null, null, null, null},
                            {null, null, null, null},
                            {null, null, null, null}
                        },
                        new String [] {
                            "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                    ));
                    tbRqCie.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tbRqCie.setSelectionBackground(new java.awt.Color(33, 115, 70));
                    jScrollPane10.setViewportView(tbRqCie);

                    jScrollPane11.setBorder(null);

                    tbRqCpt = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false;
                        }
                    };
                    tbRqCpt.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                            {null, null, null, null},
                            {null, null, null, null},
                            {null, null, null, null},
                            {null, null, null, null}
                        },
                        new String [] {
                            "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                    ));
                    tbRqCpt.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tbRqCpt.setSelectionBackground(new java.awt.Color(33, 115, 70));
                    jScrollPane11.setViewportView(tbRqCpt);

                    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                    jPanel7.setLayout(jPanel7Layout);
                    jPanel7Layout.setHorizontalGroup(
                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jScrollPane9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap())
                    );
                    jPanel7Layout.setVerticalGroup(
                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE))
                            .addContainerGap())
                    );

                    javax.swing.GroupLayout FrmRiesgoQuirurgicoLayout = new javax.swing.GroupLayout(FrmRiesgoQuirurgico.getContentPane());
                    FrmRiesgoQuirurgico.getContentPane().setLayout(FrmRiesgoQuirurgicoLayout);
                    FrmRiesgoQuirurgicoLayout.setHorizontalGroup(
                        FrmRiesgoQuirurgicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );
                    FrmRiesgoQuirurgicoLayout.setVerticalGroup(
                        FrmRiesgoQuirurgicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(FrmRiesgoQuirurgicoLayout.createSequentialGroup()
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                    );

                    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                    jPanel1.setBackground(new java.awt.Color(0, 153, 102));

                    jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel1.setText("Riesgo Quirúrgico");

                    btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                    btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                    btnNuevo.setMnemonic('N');
                    btnNuevo.setContentAreaFilled(false);
                    btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnNuevo.setIconTextGap(30);
                    btnNuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnNuevoActionPerformed(evt);
                        }
                    });

                    btnModificar.setForeground(new java.awt.Color(240, 240, 240));
                    btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
                    btnModificar.setMnemonic('N');
                    btnModificar.setContentAreaFilled(false);
                    btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnModificar.setEnabled(false);
                    btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnModificar.setIconTextGap(30);
                    btnModificar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnModificar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnModificarActionPerformed(evt);
                        }
                    });

                    btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
                    btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
                    btnGuardar.setMnemonic('N');
                    btnGuardar.setContentAreaFilled(false);
                    btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnGuardar.setEnabled(false);
                    btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnGuardar.setIconTextGap(30);
                    btnGuardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnGuardarActionPerformed(evt);
                        }
                    });

                    btnEliminar.setForeground(new java.awt.Color(240, 240, 240));
                    btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
                    btnEliminar.setMnemonic('N');
                    btnEliminar.setContentAreaFilled(false);
                    btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnEliminar.setEnabled(false);
                    btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnEliminar.setIconTextGap(30);
                    btnEliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnEliminarActionPerformed(evt);
                        }
                    });

                    btnBuscar.setForeground(new java.awt.Color(240, 240, 240));
                    btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                    btnBuscar.setMnemonic('N');
                    btnBuscar.setContentAreaFilled(false);
                    btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnBuscar.setIconTextGap(30);
                    btnBuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscarActionPerformed(evt);
                        }
                    });

                    lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                    lblusu.setForeground(new java.awt.Color(255, 255, 255));
                    lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
                    lblusu.setText("Silvana");
                    lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                    lblMant.setText("Mantenimiento");

                    txtId.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtIdCaretUpdate(evt);
                        }
                    });

                    lblArea.setText("60");

                    lblTriaje.setText("Triaje");

                    lblHc.setText("HC");

                    lblPreventa.setText("Preventa");

                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                    jPanel1.setLayout(jPanel1Layout);
                    jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 917, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblArea, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(16, 16, 16))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(162, 162, 162)
                                    .addComponent(lblMant, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(72, 72, 72)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(48, 48, 48)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(lblTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblPreventa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblHc, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(40, 40, 40))))
                    );
                    jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addComponent(lblusu)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnEliminar)
                                        .addComponent(btnBuscar))
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(lblArea))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblMant)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTriaje)
                                        .addComponent(lblPreventa))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblHc)))
                            .addGap(541, 541, 541))
                    );

                    jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                    jPanel29.setBackground(new java.awt.Color(204, 204, 204));

                    txtNhc.setEditable(false);
                    txtNhc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    txtNhc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtNhc.setBorder(null);
                    txtNhc.setEnabled(false);
                    txtNhc.setSelectionColor(new java.awt.Color(0, 153, 102));
                    txtNhc.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtNhcCaretUpdate(evt);
                        }
                    });
                    txtNhc.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            txtNhcMouseClicked(evt);
                        }
                    });
                    txtNhc.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtNhcActionPerformed(evt);
                        }
                    });
                    txtNhc.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtNhcKeyPressed(evt);
                        }
                    });

                    btnPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    btnPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnPaciente.setEnabled(false);
                    btnPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            btnPacienteMouseClicked(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
                    jPanel29.setLayout(jPanel29Layout);
                    jPanel29Layout.setHorizontalGroup(
                        jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(txtNhc, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0))
                    );
                    jPanel29Layout.setVerticalGroup(
                        jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                            .addGap(0, 1, Short.MAX_VALUE)
                            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNhc))
                            .addGap(0, 0, 0))
                    );

                    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel2.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel2.setText("Paciente Nº H.C.");

                    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel3.setText("Apellidos y Nombres");

                    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel4.setText("Ocupación");

                    jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel5.setText("Edad");

                    jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel6.setText("Procedencia ");

                    lblPaciente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    lblPaciente.setForeground(new java.awt.Color(102, 102, 102));
                    lblPaciente.setText("_________________________________________");

                    lblOcupacion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    lblOcupacion.setForeground(new java.awt.Color(102, 102, 102));
                    lblOcupacion.setText("_________________________________________");

                    lblEdad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    lblEdad.setForeground(new java.awt.Color(102, 102, 102));
                    lblEdad.setText("_____");

                    cbxProcedencia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
                    cbxProcedencia.setEnabled(false);

                    jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel11.setText("Diagnóstico");

                    jScrollPane3.setBorder(null);

                    tbDiagnostico.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                    ));
                    tbDiagnostico.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tbDiagnostico.setGridColor(new java.awt.Color(255, 255, 255));
                    tbDiagnostico.setRowHeight(25);
                    tbDiagnostico.setSelectionBackground(new java.awt.Color(0, 153, 102));
                    tbDiagnostico.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tbDiagnosticoMouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                            tbDiagnosticoMousePressed(evt);
                        }
                    });
                    tbDiagnostico.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tbDiagnosticoKeyPressed(evt);
                        }
                    });
                    jScrollPane3.setViewportView(tbDiagnostico);
                    if (tbDiagnostico.getColumnModel().getColumnCount() > 0) {
                        tbDiagnostico.getColumnModel().getColumn(0).setResizable(false);
                        tbDiagnostico.getColumnModel().getColumn(1).setResizable(false);
                        tbDiagnostico.getColumnModel().getColumn(2).setResizable(false);
                    }

                    btnAgregarDiagnostico.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
                    btnAgregarDiagnostico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                    btnAgregarDiagnostico.setMnemonic('P');
                    btnAgregarDiagnostico.setText("Agregar");
                    btnAgregarDiagnostico.setToolTipText("Alt + P");
                    btnAgregarDiagnostico.setBorderPainted(false);
                    btnAgregarDiagnostico.setContentAreaFilled(false);
                    btnAgregarDiagnostico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnAgregarDiagnostico.setEnabled(false);
                    btnAgregarDiagnostico.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnAgregarDiagnosticoActionPerformed(evt);
                        }
                    });

                    jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel14.setText("CPT");

                    jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                    jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel22.setText("<html>Examen fisico<br>Referencial</br></span></html>");

                    txtExamenFisico.setEnabled(false);

                    jScrollPane5.setBorder(null);

                    tbCpt.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                    ));
                    tbCpt.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tbCpt.setGridColor(new java.awt.Color(255, 255, 255));
                    tbCpt.setRowHeight(25);
                    tbCpt.setSelectionBackground(new java.awt.Color(0, 153, 102));
                    tbCpt.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tbCptMouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                            tbCptMousePressed(evt);
                        }
                    });
                    tbCpt.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tbCptKeyPressed(evt);
                        }
                    });
                    jScrollPane5.setViewportView(tbCpt);

                    btnAgregarCpt.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
                    btnAgregarCpt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                    btnAgregarCpt.setMnemonic('P');
                    btnAgregarCpt.setText("Agregar");
                    btnAgregarCpt.setToolTipText("Alt + P");
                    btnAgregarCpt.setBorderPainted(false);
                    btnAgregarCpt.setContentAreaFilled(false);
                    btnAgregarCpt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnAgregarCpt.setEnabled(false);
                    btnAgregarCpt.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnAgregarCptActionPerformed(evt);
                        }
                    });

                    jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
                    jLabel7.setForeground(new java.awt.Color(0, 153, 102));
                    jLabel7.setText("Acto Médico");

                    lblActoMedico.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
                    lblActoMedico.setForeground(new java.awt.Color(0, 153, 102));
                    lblActoMedico.setText("_______");

                    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                    jPanel2.setLayout(jPanel2Layout);
                    jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane3)
                                        .addComponent(txtExamenFisico)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                            .addComponent(lblPaciente)
                                                            .addGap(44, 44, 44))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                            .addComponent(lblOcupacion, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(34, 34, 34)))
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                            .addComponent(jLabel5)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(lblEdad))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                            .addComponent(jLabel7)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(lblActoMedico))))
                                                .addComponent(cbxProcedencia, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jScrollPane5)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(btnAgregarDiagnostico))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel21)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAgregarCpt))))
                    );
                    jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(lblActoMedico)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5)
                                .addComponent(lblPaciente)
                                .addComponent(lblEdad))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(lblOcupacion))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(cbxProcedencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(2, 2, 2)
                            .addComponent(btnAgregarDiagnostico)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtExamenFisico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(12, 12, 12)
                                    .addComponent(jLabel14))
                                .addComponent(btnAgregarCpt, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    jPanel5.setBackground(new java.awt.Color(255, 255, 255));

                    jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel12.setText("<html>Antecendentes<br>Importantes</br></span></html>");

                    jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel13.setText("<html>Alergia<br>Medicamentosa</br></span></html>");

                    jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel19.setText("Otros");

                    jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel15.setText("<html>Síntomas<br>Importantes</br></span></html>");

                    jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel20.setText("Otros");

                    jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                    chkHta.setBackground(new java.awt.Color(255, 255, 255));
                    chkHta.setText("HTA");
                    chkHta.setEnabled(false);
                    chkHta.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                    chkHta.setInheritsPopupMenu(true);
                    chkHta.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            chkHtaActionPerformed(evt);
                        }
                    });

                    chkDm.setBackground(new java.awt.Color(255, 255, 255));
                    chkDm.setText("DM");
                    chkDm.setEnabled(false);
                    chkDm.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

                    chkEnfRenal.setBackground(new java.awt.Color(255, 255, 255));
                    chkEnfRenal.setText("ENF. RENAL");
                    chkEnfRenal.setEnabled(false);
                    chkEnfRenal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

                    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                    jPanel3.setLayout(jPanel3Layout);
                    jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(chkHta)
                            .addGap(47, 47, 47)
                            .addComponent(chkDm)
                            .addGap(67, 67, 67)
                            .addComponent(chkEnfRenal)
                            .addGap(0, 107, Short.MAX_VALUE))
                    );
                    jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(chkHta)
                                .addComponent(chkDm)
                                .addComponent(chkEnfRenal))
                            .addGap(60, 60, 60))
                    );

                    jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel16.setText("(");

                    jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel17.setText("), I.");

                    jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel18.setText("Qx Anteriores");

                    jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                    chkDisnea.setBackground(new java.awt.Color(255, 255, 255));
                    chkDisnea.setText("DISNEA");
                    chkDisnea.setEnabled(false);
                    chkDisnea.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                    chkDisnea.setInheritsPopupMenu(true);
                    chkDisnea.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            chkDisneaActionPerformed(evt);
                        }
                    });

                    chkPalpitaciones.setBackground(new java.awt.Color(255, 255, 255));
                    chkPalpitaciones.setText("PALPITACIONES");
                    chkPalpitaciones.setEnabled(false);
                    chkPalpitaciones.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

                    chkTos.setBackground(new java.awt.Color(255, 255, 255));
                    chkTos.setText("TOS");
                    chkTos.setEnabled(false);
                    chkTos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

                    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                    jPanel4.setLayout(jPanel4Layout);
                    jPanel4Layout.setHorizontalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(chkDisnea)
                            .addGap(47, 47, 47)
                            .addComponent(chkPalpitaciones)
                            .addGap(67, 67, 67)
                            .addComponent(chkTos)
                            .addContainerGap(42, Short.MAX_VALUE))
                    );
                    jPanel4Layout.setVerticalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(chkDisnea)
                                .addComponent(chkPalpitaciones)
                                .addComponent(chkTos))
                            .addGap(60, 60, 60))
                    );

                    txtOtros.setForeground(new java.awt.Color(51, 51, 51));
                    txtOtros.setEnabled(false);
                    txtOtros.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtOtrosKeyReleased(evt);
                        }
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtOtrosKeyTyped(evt);
                        }
                    });
                    jScrollPane1.setViewportView(txtOtros);

                    txtQxAnteriores.setForeground(new java.awt.Color(51, 51, 51));
                    txtQxAnteriores.setEnabled(false);
                    txtQxAnteriores.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtQxAnterioresKeyReleased(evt);
                        }
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtQxAnterioresKeyTyped(evt);
                        }
                    });
                    jScrollPane2.setViewportView(txtQxAnteriores);

                    txtOtrosSintomas.setForeground(new java.awt.Color(51, 51, 51));
                    txtOtrosSintomas.setEnabled(false);
                    txtOtrosSintomas.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtOtrosSintomasKeyReleased(evt);
                        }
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtOtrosSintomasKeyTyped(evt);
                        }
                    });
                    jScrollPane4.setViewportView(txtOtrosSintomas);

                    cbxAlergia.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
                    cbxAlergia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SI", "NO" }));
                    cbxAlergia.setEnabled(false);

                    jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel23.setText("RQ");

                    txtRq.setForeground(new java.awt.Color(51, 51, 51));
                    txtRq.setEnabled(false);
                    txtRq.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtRqKeyReleased(evt);
                        }
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtRqKeyTyped(evt);
                        }
                    });
                    jScrollPane6.setViewportView(txtRq);

                    jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel24.setText("Sugerencia");

                    txtSugerencia.setForeground(new java.awt.Color(51, 51, 51));
                    txtSugerencia.setEnabled(false);
                    txtSugerencia.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtSugerenciaKeyReleased(evt);
                        }
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtSugerenciaKeyTyped(evt);
                        }
                    });
                    jScrollPane7.setViewportView(txtSugerencia);

                    jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel10.setText("Médico");

                    cbxMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    cbxMedico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
                    cbxMedico.setEnabled(false);

                    txtMedico.setEditable(false);
                    txtMedico.setEnabled(false);

                    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                    jPanel5.setLayout(jPanel5Layout);
                    jPanel5Layout.setHorizontalGroup(
                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel19)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel20))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addGap(33, 33, 33)
                                            .addComponent(jLabel16)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbxAlergia, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel17)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel18)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addGap(43, 43, 43)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                                    .addGap(0, 10, Short.MAX_VALUE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addGap(138, 138, 138)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addComponent(cbxMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtMedico))))
                                        .addComponent(jLabel10)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel23)
                                                .addComponent(jLabel24))
                                            .addGap(74, 74, 74)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane6)
                                                .addComponent(jScrollPane7))))
                                    .addContainerGap())))
                    );
                    jPanel5Layout.setVerticalGroup(
                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(cbxMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)
                                    .addComponent(cbxAlergia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel19)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(4, 4, 4)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel20)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel23)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    pnlMensaje.setBackground(new java.awt.Color(255, 153, 51));

                    lblMensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    lblMensaje.setForeground(new java.awt.Color(255, 255, 255));
                    lblMensaje.setText("Desea Actualizar el Registro ?");

                    btnSi.setForeground(new java.awt.Color(240, 240, 240));
                    btnSi.setText("Si");
                    btnSi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                    btnSi.setContentAreaFilled(false);
                    btnSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnSi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnSi.setIconTextGap(30);
                    btnSi.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnSiActionPerformed(evt);
                        }
                    });

                    btnNo.setForeground(new java.awt.Color(240, 240, 240));
                    btnNo.setText("No");
                    btnNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                    btnNo.setContentAreaFilled(false);
                    btnNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnNo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnNo.setIconTextGap(30);
                    btnNo.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnNoActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout pnlMensajeLayout = new javax.swing.GroupLayout(pnlMensaje);
                    pnlMensaje.setLayout(pnlMensajeLayout);
                    pnlMensajeLayout.setHorizontalGroup(
                        pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlMensajeLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(lblMensaje)
                            .addGap(46, 46, 46)
                            .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    pnlMensajeLayout.setVerticalGroup(
                        pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlMensajeLayout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblMensaje)
                                .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                    getContentPane().setLayout(layout);
                    layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 21, Short.MAX_VALUE))
                        .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );
                    layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(0, 0, 0))
                    );

                    pack();
                }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            limpiar();
            habilitarCampos(true);
            btnGuardar.setEnabled(true);
            lblMant.setText("I");
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
            pnlMensaje.setVisible(false);
            txtMedico.setVisible(false);
        } catch (Exception e) {
            System.out.println("Error: btnNuevo" + e.getMessage());
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        lblMant.setText("U");
        habilitarCampos(true);
        btnGuardar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(lblMant.getText().equals("I")){
            if(txtNhc.getText().equals("") || cbxProcedencia.getSelectedIndex()==0
                    || cbxMedico.getSelectedIndex()==0){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Verifique, que los datos estén correctos");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("¿Guardar los datos?");
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                pnlMensaje.setBackground(new Color(255,153,51));
            }
        } else 
        if(lblMant.getText().equals("U")){
            if(txtNhc.getText().equals("") || cbxProcedencia.getSelectedIndex()==0){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Verifique, que los datos estén correctos");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("¿Guardar los datos?");
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                pnlMensaje.setBackground(new Color(255,153,51));
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        lblMant.setText("E");
        pnlMensaje.setVisible(true);
        lblMensaje.setText("Desea ELIMINAR el registro?");
        btnSi.setVisible(true);
        btnNo.setVisible(true);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        FrmRiesgoQuirurgico.setVisible(true);
        FrmRiesgoQuirurgico.setLocationRelativeTo(null);//en el centro
        FrmRiesgoQuirurgico.setResizable(false);
        FrmRiesgoQuirurgico.getContentPane().setBackground(Color.WHITE);
        ConsultorioExRiesgoQuirurgico consult2 = new ConsultorioExRiesgoQuirurgico();
        consult2.listarRQ("","","",tbRQ);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        limpiar();
        habilitarCampos(false);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void chkDisneaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDisneaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkDisneaActionPerformed

    private void chkHtaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHtaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHtaActionPerformed

    private void btnAgregarDiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDiagnosticoActionPerformed
       FrmCie10.setVisible(true);
       ConsultorioExRiesgoQuirurgico riesgo = new ConsultorioExRiesgoQuirurgico();
       riesgo.listarDiagnostico(txtBuscarCie10.getText(), tbCie10);
    }//GEN-LAST:event_btnAgregarDiagnosticoActionPerformed

    private void tbDiagnosticoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosticoKeyPressed
        char teclaPresionada = evt.getKeyChar(); 
            if(teclaPresionada==KeyEvent.VK_DELETE){
                if(btnAgregarDiagnostico.isEnabled())
                    quitarRegistro(tbDiagnostico);
                else
                    JOptionPane.showMessageDialog(this, "Debe habilitar la opción modificar");
            
        }
    }//GEN-LAST:event_tbDiagnosticoKeyPressed

    private void tbDiagnosticoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosticoMousePressed

    }//GEN-LAST:event_tbDiagnosticoMousePressed

    private void tbDiagnosticoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosticoMouseClicked

    }//GEN-LAST:event_tbDiagnosticoMouseClicked

    private void btnPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPacienteMouseClicked
        FrmPacientes.setVisible(true);
        FrmPacientes.setLocationRelativeTo(null);//en el centro
        FrmPacientes.setResizable(false);
        FrmPacientes.getContentPane().setBackground(Color.WHITE);
        AdmisionEmergenciaTriaje triaje1 = new AdmisionEmergenciaTriaje();
        triaje1.consultorioExListar(txtBuscarPaciente.getText(), "Q", tbPacientes);
    }//GEN-LAST:event_btnPacienteMouseClicked

    private void txtNhcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNhcKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhcKeyPressed

    private void txtNhcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNhcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhcActionPerformed

    private void txtNhcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNhcMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhcMouseClicked

    private void txtNhcCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNhcCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhcCaretUpdate

    private void txtOtrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtrosKeyReleased
//        txtOtros.setText(txtOtros.getText().toUpperCase()); PARA CONVERTIR A MAYUSCULAS
    }//GEN-LAST:event_txtOtrosKeyReleased

    private void txtOtrosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtrosKeyTyped

    }//GEN-LAST:event_txtOtrosKeyTyped

    private void txtQxAnterioresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQxAnterioresKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQxAnterioresKeyReleased

    private void txtQxAnterioresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQxAnterioresKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQxAnterioresKeyTyped

    private void txtOtrosSintomasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtrosSintomasKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrosSintomasKeyReleased

    private void txtOtrosSintomasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtrosSintomasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrosSintomasKeyTyped

    private void tbCptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCptMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCptMouseClicked

    private void tbCptMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCptMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCptMousePressed

    private void tbCptKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCptKeyPressed
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_DELETE){
            if(btnAgregarDiagnostico.isEnabled())
                quitarRegistro(tbCpt);
            else
                JOptionPane.showMessageDialog(this, "Debe habilitar la opción modificar");
        }
    }//GEN-LAST:event_tbCptKeyPressed

    private void txtRqKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRqKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRqKeyReleased

    private void txtRqKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRqKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRqKeyTyped

    private void txtSugerenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSugerenciaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSugerenciaKeyReleased

    private void txtSugerenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSugerenciaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSugerenciaKeyTyped

    private void tbCie10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCie10KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbCie10.getSelectedRow()==0){
            txtBuscarCie10.requestFocus();
            tbCie10.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDiagnosticos(tbCie10,tbDiagnostico,FrmCie10);
        }
    }//GEN-LAST:event_tbCie10KeyPressed

    private void txtBuscarCie10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCie10CaretUpdate
        ConsultorioExRiesgoQuirurgico riesgo = new ConsultorioExRiesgoQuirurgico();
        riesgo.listarDiagnostico(txtBuscarCie10.getText(), tbCie10);
    }//GEN-LAST:event_txtBuscarCie10CaretUpdate

    private void txtBuscarCie10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCie10KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbCie10.getSelectionModel().setSelectionInterval(0, 0);
            tbCie10.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarCie10KeyPressed

    private void tb_Grupo4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo4MouseClicked
        if(evt.getClickCount()==2){
            enviarDiagnosticos(tb_Grupo4,tbCpt,FrmNomenclaturas);
            formatoTablaNomenclatura(tbCpt);
        }
    }//GEN-LAST:event_tb_Grupo4MouseClicked

    private void tb_Grupo4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo4KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tb_Grupo4.getSelectedRow()==0){
            txtBuscarCpt.requestFocus();
            tb_Grupo4.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDiagnosticos(tb_Grupo4,tbCpt,FrmNomenclaturas);
            formatoTablaNomenclatura(tbCpt);
        }
    }//GEN-LAST:event_tb_Grupo4KeyPressed

    private void btnAgregarCptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCptActionPerformed
        FrmNomenclaturas.setVisible(true);
        FrmNomenclaturas.setLocationRelativeTo(null);//en el centro
        FrmNomenclaturas.setResizable(false);
        FrmNomenclaturas.getContentPane().setBackground(Color.WHITE);
        ConsultorioExRiesgoQuirurgico consultorio2 = new ConsultorioExRiesgoQuirurgico();
        consultorio2.listarDiagnosticoNomenclatura(txtBuscarCpt.getText(), tb_Grupo4);
    }//GEN-LAST:event_btnAgregarCptActionPerformed

    private void tbPacientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacientesKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbPacientes.getSelectedRow()==0){
            txtBuscarPaciente.requestFocus();
            tbPacientes.getSelectionModel().setSelectionInterval(0,0);
        }
       char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosPaciente();
        }
    }//GEN-LAST:event_tbPacientesKeyPressed

    private void txtBuscarPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarPacienteCaretUpdate
        AdmisionEmergenciaTriaje triaje1 = new AdmisionEmergenciaTriaje();
        triaje1.consultorioExListar(txtBuscarPaciente.getText(), "Q", tbPacientes);
    }//GEN-LAST:event_txtBuscarPacienteCaretUpdate

    private void txtBuscarPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPacienteKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbPacientes.getSelectionModel().setSelectionInterval(0, 0);
            tbPacientes.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarPacienteKeyPressed

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        if(btnSi.getText().equals("Si")){ // Al guardar
            if(lblMant.getText().equals("I")){
                guardarDatos();
            } else 
            if(lblMant.getText().equals("U")){
                modificarDatos();
            }
            if(lblMant.getText().equals("E")){
                eliminarDatos();
            }
        } else
        if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
            pnlMensaje.setVisible(false);
        }
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed

    }//GEN-LAST:event_btnNoActionPerformed

    private void tbPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesMouseClicked
        if(evt.getClickCount()==2){
            enviarDatosPaciente();
        }
    }//GEN-LAST:event_tbPacientesMouseClicked

    private void txtBuscarPacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPacienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarPacienteKeyReleased

    private void tbCie10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCie10MouseClicked
        if(evt.getClickCount()==2){
            enviarDiagnosticos(tbCie10,tbDiagnostico,FrmCie10);
        }
    }//GEN-LAST:event_tbCie10MouseClicked

    private void txtBuscarCptKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCptKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_Grupo4.getSelectionModel().setSelectionInterval(0, 0);
            tb_Grupo4.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarCptKeyPressed

    private void txtBuscarCptCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCptCaretUpdate
        ConsultorioExRiesgoQuirurgico consultorio2 = new ConsultorioExRiesgoQuirurgico();
        consultorio2.listarDiagnosticoNomenclatura(txtBuscarCpt.getText(), tb_Grupo4);
    }//GEN-LAST:event_txtBuscarCptCaretUpdate

    private void btnBuscarRQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarRQActionPerformed
        if(dtFechaInicio.getDate()!=null || dtFechaFin.getDate()!=null){
            ConsultorioExRiesgoQuirurgico consult2 = new ConsultorioExRiesgoQuirurgico();
            consult2.listarRQ(txtBuscarRq.getText(),determinarFecha(dtFechaInicio),determinarFecha(dtFechaFin),tbRQ);
        } else {
            ConsultorioExRiesgoQuirurgico consult2 = new ConsultorioExRiesgoQuirurgico();
            consult2.listarRQ(txtBuscarRq.getText(),"","",tbRQ);
        }
    }//GEN-LAST:event_btnBuscarRQActionPerformed

    private void txtBuscarRqCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarRqCaretUpdate
        if(dtFechaInicio.getDate()!=null || dtFechaFin.getDate()!=null){
            ConsultorioExRiesgoQuirurgico consult2 = new ConsultorioExRiesgoQuirurgico();
            consult2.listarRQ(txtBuscarRq.getText(),determinarFecha(dtFechaInicio),determinarFecha(dtFechaFin),tbRQ);
        } else {
            ConsultorioExRiesgoQuirurgico consult2 = new ConsultorioExRiesgoQuirurgico();
            consult2.listarRQ(txtBuscarRq.getText(),"","",tbRQ);
        }
    }//GEN-LAST:event_txtBuscarRqCaretUpdate

    private void tbRQKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRQKeyReleased
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            int fila = tbRQ.getSelectedRow();
            txtId1.setText(String.valueOf(tbRQ.getValueAt(fila, 0)));
        }
    }//GEN-LAST:event_tbRQKeyReleased

    private void tbRQMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRQMouseClicked
        if(evt.getClickCount()==1){
            int fila = tbRQ.getSelectedRow();
            txtId1.setText(String.valueOf(tbRQ.getValueAt(fila, 0)));
        }
        if(evt.getClickCount()==2){
            //enviarDatosRQ();
        }
    }//GEN-LAST:event_tbRQMouseClicked

    private void txtId1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtId1CaretUpdate
        ConsultorioExRQDetalle detalle1 = new ConsultorioExRQDetalle();
        detalle1.listarDiagnosticos(txtId1.getText(), tbRqCie);
        detalle1.listarNomenclatura(txtId1.getText(), tbRqCpt);
    }//GEN-LAST:event_txtId1CaretUpdate

    private void tbRQKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRQKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosRQ();
        }
    }//GEN-LAST:event_tbRQKeyPressed

    private void txtIdCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIdCaretUpdate
        ConsultorioExRQDetalle detalle1 = new ConsultorioExRQDetalle();
        detalle1.listarDiagnosticos(txtId.getText(), tbDiagnostico);
        detalle1.listarNomenclatura(txtId.getText(), tbCpt);
        formatoTablaNomenclatura(tbCpt);
    }//GEN-LAST:event_txtIdCaretUpdate

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RiesgoQ_Cardiologia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RiesgoQ_Cardiologia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RiesgoQ_Cardiologia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RiesgoQ_Cardiologia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RiesgoQ_Cardiologia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog FrmCie10;
    private javax.swing.JDialog FrmNomenclaturas;
    private javax.swing.JDialog FrmPacientes;
    private javax.swing.JDialog FrmRiesgoQuirurgico;
    private javax.swing.JButton btnAgregarCpt;
    private javax.swing.JButton btnAgregarDiagnostico;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarCie10;
    private javax.swing.JButton btnBuscarCie11;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarRQ;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel btnPaciente;
    private javax.swing.JButton btnSi;
    public static javax.swing.JComboBox cbxAlergia;
    public static javax.swing.JComboBox cbxMedico;
    public static javax.swing.JComboBox cbxProcedencia;
    public static javax.swing.JCheckBox chkDisnea;
    public static javax.swing.JCheckBox chkDm;
    public static javax.swing.JCheckBox chkEnfRenal;
    public static javax.swing.JCheckBox chkHta;
    public static javax.swing.JCheckBox chkPalpitaciones;
    public static javax.swing.JCheckBox chkTos;
    private com.toedter.calendar.JDateChooser dtFechaFin;
    private com.toedter.calendar.JDateChooser dtFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    public static javax.swing.JLabel lblActoMedico;
    private javax.swing.JLabel lblArea;
    public static javax.swing.JLabel lblEdad;
    public static javax.swing.JLabel lblHc;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMensaje;
    public static javax.swing.JLabel lblOcupacion;
    public static javax.swing.JLabel lblPaciente;
    public static javax.swing.JLabel lblPreventa;
    public static javax.swing.JLabel lblTriaje;
    private javax.swing.JLabel lblusu;
    private javax.swing.JLabel lblusu1;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JTable tbCie10;
    private javax.swing.JTable tbCpt;
    private javax.swing.JTable tbDiagnostico;
    private javax.swing.JTable tbPacientes;
    private javax.swing.JTable tbRQ;
    private javax.swing.JTable tbRqCie;
    private javax.swing.JTable tbRqCpt;
    private javax.swing.JTable tb_Grupo4;
    private javax.swing.JLabel titulo7;
    private javax.swing.JLabel titulo8;
    private javax.swing.JTextField txtBuscarCie10;
    private javax.swing.JTextField txtBuscarCpt;
    private javax.swing.JTextField txtBuscarPaciente;
    private javax.swing.JTextField txtBuscarRq;
    public static javax.swing.JTextField txtExamenFisico;
    public static javax.swing.JTextField txtId;
    private javax.swing.JTextField txtId1;
    public static javax.swing.JTextField txtMedico;
    public static javax.swing.JTextField txtNhc;
    public static javax.swing.JEditorPane txtOtros;
    public static javax.swing.JEditorPane txtOtrosSintomas;
    public static javax.swing.JEditorPane txtQxAnteriores;
    public static javax.swing.JEditorPane txtRq;
    public static javax.swing.JEditorPane txtSugerencia;
    // End of variables declaration//GEN-END:variables
}
