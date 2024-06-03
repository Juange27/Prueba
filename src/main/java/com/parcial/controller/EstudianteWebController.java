package com.parcial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.parcial.entidades.Estudiante;
import com.parcial.repository.EstudianteRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Estudiante")

public class EstudianteWebController {

	 @Autowired
	    private EstudianteRepository estudianteRepository;

	    @GetMapping("/agregar")
	    public String agregarEstudianteTemplate(Model model) {
	        model.addAttribute("estudiante", new Estudiante());
	        return "formulario-estudiante";
	    }

	    @GetMapping("/")
	    public String listarTodosEstudiantes(Model model) {
	        List<Estudiante> estudiantes = estudianteRepository.findAll();
	        model.addAttribute("estudiantes", estudiantes);
	        return "lista-estudiante"; // 
	    }

	    @PostMapping("/guardar")
	    public String guardarEstudiante(@ModelAttribute("estudiante") Estudiante estudiante) {
	        estudianteRepository.save(estudiante);
	        return "redirect:/Estudiante/";
	    }

	    
	    /*@GetMapping("/edit/{id}")
		public String editarJugadorTemplate(@PathVariable("id") String id, Model model) {
			model.addAttribute("Estudiante", estudianteRepository.findById(id).orElseThrow());
			return "formulario-estudiante"; 
		}
*/
	    
	    @GetMapping("/edit/{id}")
	    public String editarEstudianteTemplate(@PathVariable("id") String id, Model model) {
	        Estudiante estudiante = estudianteRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con el ID: " + id));
	        model.addAttribute("estudiante", estudiante);
	        return "formulario-estudiante";
	    }

	    
	    @GetMapping("/delete/{id}")
	    public String eliminarEstudiante(@PathVariable("id") String id) {
	        estudianteRepository.deleteById(id);
	        return "redirect:/Estudiante/";
	    }
	    
	    @GetMapping("/login")
	    public String EstudianteLoginTemplate(Model model) {
	        return "login_alumno";
	    }
	
	    
	    
	    
	    @PostMapping("/logear")
	    public String EstudianteLogearTemplate(@RequestParam String usuario, @RequestParam String contrasena, Model model, HttpSession session) {
	        
	        // Buscar al alumno por nombre de usuario en la base de datos
	        Estudiante estudiante = null;
	        for (Estudiante c : estudianteRepository.findAll()) {
	            // Verificar si el nombre de usuario no es nulo y es igual al proporcionado en la solicitud
	            if (c.getUsuario() != null && c.getUsuario().equals(usuario)) {
	                estudiante = c;
	                break;
	            }
	        }
	        
	        // Verificar si se encontró al alumno y si la contraseña es correcta
	        if (estudiante != null && estudiante.getContrasena().equals(contrasena)) {
	            
	            // Guardar el usuario logeado en la sesión
	            session.setAttribute("usuarioLogeado", estudiante);
	            
	            // Si las credenciales son correctas, redirigir a la página de inicio
	            return "redirect:/Estudiante/indexEstudiante";
	            
	        } else {
	            
	            // Si las credenciales son incorrectas, mostrar un mensaje de error y volver al formulario de login
	            model.addAttribute("error", true);
	            return "login_alumno";
	        }
	    }

	    
	    
	    @GetMapping("/indexEstudiante")
	    public String EstudianteIndexTemplate(Model model, HttpSession session) {
	    	
	        // Obtener el usuario logeado de la sesión
	    	Estudiante estudiante = (Estudiante) session.getAttribute("usuarioLogeado");
	    	model.addAttribute("estudiante", estudianteRepository.findAll());
	    	
	    	 double puntajePromedio = (estudiante.getComunE() + estudiante.getRazoC() + estudiante.getLeC() + estudiante.getCompC() + estudiante.getIngl() + estudiante.getFormProyectos() + estudiante.getPenCientifico() + estudiante.getDiseño()) / 8.0;
	    	    model.addAttribute("PuntajeGeneral", puntajePromedio);
	    	    
	    	 // Calcular el puntaje general y pasarlo como un número entero al modelo
	    	    int puntajeGeneral = (int) Math.floor((estudiante.getComunE() + estudiante.getRazoC() + estudiante.getLeC() + estudiante.getCompC() + estudiante.getIngl() + estudiante.getFormProyectos() + estudiante.getPenCientifico() + estudiante.getDiseño()) / 8.0);
	    	    model.addAttribute("PuntajeGeneral", puntajeGeneral);
	    	    

	    	 // Definir rangos de puntaje y colores correspondientes
	    	    String puntajeNivel;
	            if (puntajeGeneral >= 0 && puntajeGeneral <= 125) {
	                puntajeNivel = "Nivel 1";
	            } else if (puntajeGeneral > 126 && puntajeGeneral <= 156) {
	                puntajeNivel = "Nivel 2";
	            } else if (puntajeGeneral > 157 && puntajeGeneral <= 185) {
	                puntajeNivel = "Nivel 3";
	            } else {
	                puntajeNivel = "Nivel 4";
	            }
	            model.addAttribute("PuntajeNivel", puntajeNivel);
	    	
	        // Verificar si el usuario está logeado antes de agregarlo al modelo
	        if (estudiante != null) {
	            model.addAttribute("usuario", estudiante.getUsuario());
	            model.addAttribute("contrasena", estudiante.getContrasena());
	            model.addAttribute("Cedula", estudiante.getCc());
	            model.addAttribute("Nombre", estudiante.getNombre());
	            model.addAttribute("Apellido", estudiante.getApellido());
	            model.addAttribute("Registro", estudiante.getRegistro());
	            model.addAttribute("Correo", estudiante.getCorreo());
	            model.addAttribute("Telefono", estudiante.getTelefono());
	            model.addAttribute("ComunE", estudiante.getComunE());
	            model.addAttribute("RazonC", estudiante.getRazoC());
	            model.addAttribute("LeC", estudiante.getLeC());
	            model.addAttribute("CompC", estudiante.getCompC());
	            model.addAttribute("Ingl", estudiante.getIngl());
	            model.addAttribute("FormProyectos", estudiante.getFormProyectos());
	            model.addAttribute("PenCientifico", estudiante.getPenCientifico());
	            model.addAttribute("Diseño", estudiante.getDiseño());
	         
	        }
	        
	        return "index-estudiante";
	    }
}
