package com.webdashboard.dashboard.controller;

import com.webdashboard.dashboard.model.Usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private static final String HOME_INDEX = "usuario/login";
    private static final String VIEW_DASHBOARD = "dashboard";
    private static final String VIEW_USER = "user";
    private static final String VIEW_REPORTES = "table";

    @GetMapping("/")
    public String index(Model model){
        return "redirect:/" + HOME_INDEX;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        try {
            Usuario user = (Usuario)session.getAttribute("user"); 
            if (user == null) {
                return "redirect:/" + HOME_INDEX;
            }

            return VIEW_DASHBOARD;
        }
        catch (Exception e)
        {
            return "redirect:/" + HOME_INDEX;
        }        
    }

    @GetMapping("/user")
    public String user(Model model, HttpSession session) {
        try {
            Usuario user = (Usuario)session.getAttribute("user"); 
            if (user == null) {
                return "redirect:/" + HOME_INDEX;
            }
            
            return VIEW_USER;
        }
        catch (Exception e)
        {
            return "redirect:/" + HOME_INDEX;
        }
    }

    @GetMapping("/table")
    public String table(Model model, HttpSession session) {
        try {
            Usuario user = (Usuario)session.getAttribute("user"); 
            if (user == null) {
                return "redirect:/" + HOME_INDEX;
            }
            
            return VIEW_REPORTES;
        }
        catch (Exception e)
        {
            return "redirect:/" + HOME_INDEX;
        }
    }
}
