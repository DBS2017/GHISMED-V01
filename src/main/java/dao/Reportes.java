package dao;

import dao.Conexion;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class Reportes extends Conexion {

//    public void exportarPDF(Map params) throws JRException, IOException, Exception {
//        try {
//            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("vistas\\Reportes\\.jasper"));
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), params, this.conectar());
//            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//            response.addHeader("Content-disposition", "attachment; filename=Reporte_Persona.pdf");
//            ServletOutputStream stream = response.getOutputStream();
//            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//            stream.flush();
//            stream.close();
//            FacesContext.getCurrentInstance().responseComplete();
//        } catch (Exception e) {
//            System.out.println("ERROR en exportarPerPDF: " + e.getMessage());
//            e.getStackTrace();
//            throw e;
//        }
//    }

    public void exportPrograma(Map parameters, String namefile, String url) throws JRException, IOException, Exception {
        try {
            this.conectar();
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("vistas/Reportes/" + url + ".jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=" + namefile);
            ServletOutputStream stream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
