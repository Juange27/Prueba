package com.parcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.parcial.entidades.Coordinador;
import com.parcial.repository.CoordinadorRepository;
import com.parcial.repository.EstudianteRepository;


import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping(value = "/Coordinador")

public class CoordinadorWebController {
	
	@Autowired
    private CoordinadorRepository coordinadorRepository;
	@Autowired
	private EstudianteRepository estudiantesRepository;

	
	  @GetMapping("/index")
	    public String coordinadorIndexTemplate(Model model, HttpSession session) {
	    	
	        // Obtener el usuario logeado de la sesión
	    	Coordinador coordinador = (Coordinador) session.getAttribute("usuarioLogeado");
	    	model.addAttribute("coordinador", coordinadorRepository.findAll());
	    	
	        // Verificar si el usuario está logeado antes de agregarlo al modelo
	        if (coordinador != null) {
	            model.addAttribute("usuario", coordinador.getUsuario());
	            model.addAttribute("nombre", coordinador.getNombre());
	        }
	        
	        return "index-coordinador";
	    }
	    
	    @GetMapping("/login")
	    public String coordinadorLoginTemplate(Model model) {
	        return "login_coordinador";
	    }
	    
	    
	    
	    @PostMapping("/logear")
	    public String coordinadorLogearTemplate(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
	    	
	        // Buscar al coordinador por nombre de usuario en la base de datos
	        Coordinador coordinador = null;
	        for (Coordinador c : coordinadorRepository.findAll()) {
	        	
	            if (c.getUsuario().equals(usuario)) {
	                coordinador = c;
	                break;
	            }
	        }
	        
	        // Verificar si se encontró al coordinador y si la contraseña es correcta
	        
	        if (coordinador != null && coordinador.getContrasena().equals(contrasena)) {
	        	
	            // Guardar el usuario logeado en la sesión
	            session.setAttribute("usuarioLogeado", coordinador);
	            
	            // Si las credenciales son correctas, redirigir a la página de inicio
	            return "redirect:/Coordinador/index";
	            
	        } else {
	        	
	            // Si las credenciales son incorrectas, mostrar un mensaje de error y volver al formulario de login
	            model.addAttribute("error", true);
	            return "login_coordinador";
	        }
	    }
}
