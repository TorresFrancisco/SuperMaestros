package com.hector.cinturonnegro.controllers;

import com.hector.cinturonnegro.models.Publication;
import com.hector.cinturonnegro.models.User;
import com.hector.cinturonnegro.services.PublicationService;
import com.hector.cinturonnegro.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SOSController {

    private final PublicationService publicationService;
    private final UserService userService;

    public SOSController(PublicationService publicationService, UserService userService) {
        this.publicationService = publicationService;
        this.userService = userService;
    }

    @GetMapping("/sos")
    public String sos(
            HttpSession session,
            Model model
    ){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long userId = (Long) session.getAttribute("userid");
        User user = userService.findById(userId);
        List<Publication> publicacionesEstadotrue = publicationService.publicacionesTrue();
        List<Publication> publicacionesSosTrue = new ArrayList<>();
        for (Publication publication : publicacionesEstadotrue) {
            if(publication.isSos() == true){
                publicacionesSosTrue.add(publication);
            }
        }
        model.addAttribute("publicaciones", publicacionesSosTrue);
        model.addAttribute("user", user);
        return "buscadorMaximo.jsp";
    }

    @GetMapping("/sos/{idPublicacion}/addRemove")
    public String addSos(HttpSession session, @PathVariable("idPublicacion") Long idPublicacion){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        Publication publication = publicationService.findById(idPublicacion);
        if(publication.getUser() != user){
            return "redirect:/";
        }else{
            if(publication.isSos()== false){
                publication.setSos(true);
                publicationService.update(publication);
                return "redirect:/publicaciones/"+idPublicacion;
            }else{
                publication.setSos(false);
                publicationService.update(publication);
                return "redirect:/publicaciones/"+idPublicacion;
            }
        }
    }

}
