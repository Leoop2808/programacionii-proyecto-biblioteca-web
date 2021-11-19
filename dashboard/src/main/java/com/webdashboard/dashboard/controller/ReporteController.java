package com.webdashboard.dashboard.controller;

import java.io.OutputStream;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webdashboard.dashboard.model.Usuario;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import net.sf.jasperreports.engine.*;

@Controller
public class ReporteController {

    private JdbcTemplate jdbcTemplate;
    private ResourceLoader resourceLoader;
    private static final String HOME_INDEX = "usuario/login";
    
    public ReporteController(JdbcTemplate jdbcTemplate, ResourceLoader resourceLoader){
        this.jdbcTemplate = jdbcTemplate;
        this.resourceLoader = resourceLoader;
    }
    
    @GetMapping("/jasper/repprestamos")
    public void generateReporteVentas(HttpServletResponse response, HttpSession session)  {
        Usuario user = (Usuario)session.getAttribute("user"); 
        if (user != null) {
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"ReportePrestamos.pdf\""));
            response.setContentType("application/pdf"); 

            try {
                OutputStream out = response.getOutputStream();
                Resource resource = resourceLoader.getResource("classpath:./reports/REPORTE_PRESTAMOS.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(resource.getInputStream());
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, jdbcTemplate.getDataSource().getConnection());
                JasperExportManager.exportReportToPdfStream(jasperPrint, out);
    
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
