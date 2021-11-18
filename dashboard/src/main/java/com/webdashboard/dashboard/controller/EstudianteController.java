package com.webdashboard.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.webdashboard.dashboard.model.Estudiante;
import com.webdashboard.dashboard.model.Usuario;
import com.webdashboard.dashboard.repository.EstudianteRepository;
import com.webdashboard.dashboard.repository.UsuarioRepository;

import javax.validation.Valid;

@Controller
public class EstudianteController {
    private static final String INDEX ="estudiante/create"; 
    private static String MODEL_CONTACT="estudiante";
    private final EstudianteRepository studentsData;
    private final UsuarioRepository usuariosData;

    public EstudianteController(EstudianteRepository studentsData,
        UsuarioRepository usuariosData    
        ){
        this.studentsData = studentsData;
        this.usuariosData = usuariosData;
    }      

    @GetMapping("/estudiante/create")
    public String index(Model model) {
        model.addAttribute(MODEL_CONTACT, new Estudiante());
        return INDEX;
    }    

    @PostMapping("/estudiante/create")
    public String createSubmitForm(Model model, 
        @Valid Estudiante objEstudiante, BindingResult result ){
        if(result.hasFieldErrors()) {
            model.addAttribute("mensaje", "No se pudo completar el registro");
        }else{
            Usuario user = objEstudiante.getUser();
            user.setTipoUsuario("C");
            this.usuariosData.save(user);
            this.usuariosData.flush();
            this.studentsData.save(objEstudiante);
            model.addAttribute(MODEL_CONTACT, objEstudiante);
            model.addAttribute("mensaje", "Registro exitoso");
        }
        return "redirect:/";
    }
}
