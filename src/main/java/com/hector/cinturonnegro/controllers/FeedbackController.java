package com.hector.cinturonnegro.controllers;

import com.hector.cinturonnegro.models.Feedback;
import com.hector.cinturonnegro.models.Publication;
import com.hector.cinturonnegro.models.User;
import com.hector.cinturonnegro.services.FeedbackService;
import com.hector.cinturonnegro.services.PublicationService;
import com.hector.cinturonnegro.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final UserService userService;
    private final PublicationService publicationService;

    public FeedbackController(FeedbackService feedbackService, UserService userService, PublicationService publicationService) {
        this.feedbackService = feedbackService;
        this.userService = userService;
        this.publicationService = publicationService;
    }
    
    @PostMapping("/publicaciones/{idPublicacion}/feedback")
    public String feedbackCreate(@Valid @ModelAttribute("feedback")Feedback feedback,
                           BindingResult result,
                           @PathVariable("idPublicacion")Long idPublicacion,
                           HttpSession session,
                           @RequestParam("file") MultipartFile file){
        Publication publication = publicationService.findById(idPublicacion);
        Long userId = (Long) session.getAttribute("userid");
        User user = userService.findById(userId);
        if (result.hasErrors()){
            return "publicacionPorId.jsp";
        }else{
            feedbackService.create(feedback);
            feedback.setUser(user);
            feedback.setPublication(publication);
            String url ="img/"+publication.getUser().getId()+"/"+publication.getId()+"/feedbacks/";
            try{
                feedbackService.setImage(file, url);
                feedback.setPhoto_feedback(url+file.getOriginalFilename());
            }catch (Exception e){
                System.out.println(e.getMessage());
                return "redirect:/error";
            }
            feedbackService.create(feedback);
            return "redirect:/notificacion/feedback/"+publication.getId()+"/"+publication.getUser().getId()+"/"+feedback.getUser().getId()+"/"+feedback.getId();
        }

    }
}
