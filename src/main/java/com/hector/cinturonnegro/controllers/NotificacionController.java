package com.hector.cinturonnegro.controllers;

import com.hector.cinturonnegro.models.*;
import com.hector.cinturonnegro.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class NotificacionController {
    private final UserService userService;
    private final PublicationService publicationService;
    private final NotificacionService notificacionService;
    private final MessageService messageService;
    private final FeedbackService feedbackService;

    public NotificacionController(UserService userService, PublicationService publicationService, NotificacionService notificacionService, MessageService messageService, FeedbackService feedbackService) {
        this.userService = userService;
        this.publicationService = publicationService;
        this.notificacionService = notificacionService;
        this.messageService = messageService;
        this.feedbackService = feedbackService;
    }

    @GetMapping("/perfil/notificaciones")
    public String allNotificaciones(Model model, HttpSession session){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        model.addAttribute("notificaciones", user.getNotificacions());
        return "notificaciones.jsp";
    }

    @GetMapping("/notificacion/{idPublicacion}/{idUserLogueado}/{idUserEmisor}/{idMessage}")
    public String pushNotification(
            @PathVariable("idPublicacion") Long idPublicacion,
            @PathVariable("idUserLogueado") Long idUserLogueado,
            @PathVariable("idUserEmisor") Long idUserEmisor,
            @PathVariable("idMessage") Long idMessage,
            HttpSession session
    ){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Publication publication = publicationService.findById(idPublicacion);
        User emisor = userService.findById(idUserEmisor);
        User user = userService.findById(idUserLogueado);
        Message message = messageService.findById(idMessage);
        Notificacion notificacion = new Notificacion();
        notificacion.setUser(user);
        notificacion.setContenido("El usuario "+ emisor.getFirstName() + " "+ emisor.getLastName() + " ha escrito algo en tu publicacion "+ publication.getTitle());
        notificacion.setMessage(message);
        notificacion.setPublication(publication);
        notificacionService.update(notificacion);
        return "redirect:/publicaciones/"+idPublicacion+"#"+idMessage;
    }

    @GetMapping("/notificacion/feedback/{idPublicacion}/{idUsuarioLogueado}/{idUserEmisor}/{idFeedback}")
    public String pushFeedBackNotification(
            @PathVariable("idPublicacion") Long idPublicacion,
            @PathVariable("idUsuarioLogueado") Long idUserLogueado,
            @PathVariable("idUserEmisor") Long idUserEmisor,
            @PathVariable("idFeedback") Long idFeedback,
            HttpSession session
    ){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Publication publication = publicationService.findById(idPublicacion);
        User emisor = userService.findById(idUserEmisor);
        User user = userService.findById(idUserLogueado);
        Feedback feedback = feedbackService.findById(idFeedback);
        Notificacion notificacion = new Notificacion();
        notificacion.setUser(user);
        notificacion.setContenido("El usuario "+ emisor.getFirstName()+ " "+ emisor.getLastName()+ " ha escrito un feedback en tu publicacion:"+publication.getTitle());
        notificacion.setFeedback(feedback);
        notificacion.setPublication(publication);
        notificacionService.update(notificacion);
        return "redirect:/publicaciones/"+idPublicacion+"#feedback"+idFeedback;
    }



    @GetMapping("/notificacion/{idNotificacion}")
    public String verNotificacion(
            @PathVariable("idNotificacion") Long idNotificacion
    ){
        Notificacion notificacion = notificacionService.findById(idNotificacion);
        notificacionService.delete(notificacion.getId());
        if(notificacion.getMessage() != null){
            return "redirect:/publicaciones/"+notificacion.getPublication().getId()+"#"+notificacion.getMessage().getId();
        }else{
            return "redirect:/publicaciones/"+notificacion.getPublication().getId()+"#feedback"+notificacion.getFeedback().getId();
        }
    }














    @GetMapping("/notificaciones/leer")
    @ResponseBody
    public int leerNotificaciones(HttpSession session){
        if(session.getAttribute("userid") != null){
            Long idUser = (Long) session.getAttribute("userid");
            User user = userService.findById(idUser);
            return user.getNotificacions().size();
        }
        return 0;
    }



}
