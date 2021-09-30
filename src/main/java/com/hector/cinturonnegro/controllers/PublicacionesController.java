package com.hector.cinturonnegro.controllers;

import com.hector.cinturonnegro.models.*;
import com.hector.cinturonnegro.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PublicacionesController {
    private final UserService userService;
    private final PublicationService publicationService;
    private final CategoryService categoryService;
    private final FeedbackService feedbackService;
    private final MessageService messageService;

    public PublicacionesController(UserService userService, PublicationService publicationService, CategoryService categoryService, FeedbackService feedbackService, MessageService messageService) {
        this.userService = userService;
        this.publicationService = publicationService;
        this.categoryService = categoryService;
        this.feedbackService = feedbackService;
        this.messageService = messageService;
    }

    ////tipo 1 -> Prestar servicios
    ////tipo 2 -> Buscar servicios

    @GetMapping("/publicaciones")
    public String allPublicaciones(Model model, HttpSession session){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }else{
            Long userId = (Long) session.getAttribute("userid");
            User user = userService.findById(userId);
            model.addAttribute("publicationList", user.getPublications());
            model.addAttribute("user", user);
            return "allPublicaciones.jsp";
        }
    }

    /////////////////////////////////////////////////////////
    /////////////////publicaciones por id////////////////////
    /////////////////////////////////////////////////////////

    @GetMapping("/publicaciones/{idPublicacion}")
    public String publicacionPorId(
            @PathVariable("idPublicacion") Long idPublicacion,
            HttpSession session,
            Model model,
            @ModelAttribute("message")Message message,
            @ModelAttribute("feedback")Feedback feedback
            ){
        Publication publication = publicationService.findById(idPublicacion);
        if(session.getAttribute("userid") == null){
            List<Message> messageList = publication.getMessages();
            List<Feedback> feedbacks = publication.getFeedback();
            User creatorP = publication.getUser();
            List<Publication> publicationList = creatorP.getPublications();
            int suma = 0;
            int promedio = 0;
            int contador = 0;
            for (Publication p : publicationList) {
                List<Feedback> feedbackList = p.getFeedback();
                for (Feedback f : feedbackList) {
                    int ratingF = f.getRating();
                    suma += ratingF;
                }
                contador += p.getFeedback().size();
            }
            if (suma == 0) {
                contador = 1;
            }
            promedio = suma / contador;
            model.addAttribute("ratingF", promedio);
            model.addAttribute("feedbacks", feedbacks);
            model.addAttribute("messageList", messageList);
            model.addAttribute("publication", publication);
            return "publicacionPorId.jsp";
        }
        Long userId = (Long) session.getAttribute("userid");
        User user = userService.findById(userId);
        if (publication.isEstado() == false && user.getRol() != 3) {
            return "redirect:/";
        }
        else {
            List<Message> messageList = publication.getMessages();
            List<Feedback> feedbacks = publication.getFeedback();
            User creatorP = publication.getUser();
            List<Publication> publicationList = creatorP.getPublications();
            int suma = 0;
            int promedio = 0;
            int contador = 0;
            for (Publication p : publicationList) {
                List<Feedback> feedbackList = p.getFeedback();
                for (Feedback f : feedbackList) {
                    int ratingF = f.getRating();
                    suma += ratingF;
                }
                contador += p.getFeedback().size();
            }
            if (suma == 0) {
                contador = 1;
            }
            promedio = suma / contador;
            model.addAttribute("ratingF", promedio);
            model.addAttribute("user", user);
            model.addAttribute("feedbacks", feedbacks);
            model.addAttribute("messageList", messageList);
            model.addAttribute("publication", publication);
            return "publicacionPorId.jsp";
        }
    }


    /////////////////////////////////////////////////////////
    ///////////////////Crear publicacion/////////////////////
    /////////////////////////////////////////////////////////

    @GetMapping("/publicaciones/add")
    public String publicaciones(
            @ModelAttribute("publication") Publication publication,
            HttpSession session,
            Model model
    ){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        } else{
            Long userId = (Long) session.getAttribute("userid");
            User user = userService.findById(userId);
            List<Category> categories = categoryService.allData();
            model.addAttribute("categories", categories);
            model.addAttribute("user", user);
            return "addpublicacion.jsp";
        }
    }

    @PostMapping("/publicaciones/add")
    public String addPublicacion(
            @Valid @ModelAttribute("publication") Publication publication,
            BindingResult result,
            HttpSession session,
            Model model,
            @RequestParam("file") MultipartFile file
    ){
        if(result.hasErrors()){
            Long userId = (Long) session.getAttribute("userid");
            User user = userService.findById(userId);
            List<Category> categories = categoryService.allData();
            model.addAttribute("categories", categories);
            model.addAttribute("user", user);
            return "addpublicacion.jsp";
        } else{
            Long userId = (Long) session.getAttribute("userid");
            User user = userService.findById(userId);
            int carpetaPubl = publicationService.allData().size() + 1;
            String url ="img/"+user.getId()+"/"+carpetaPubl+"/";
            try{
                publicationService.setImage(user, file, url);
                publication.setPhoto_publication(url+file.getOriginalFilename());
            }catch (Exception e){
                System.out.println(e.getMessage());
                return "redirect:/error";
            }
            publication.setUser(user);
            publication.setAddress(user.getAddress());
            publicationService.create(publication);
            return "redirect:/publicaciones";
        }
    }

    ///////////////////////////////////////////////////////////
    ///////////////////////Editar//////////////////////////////
    ///////////////////////////////////////////////////////////

    @GetMapping("/publicaciones/{idPublicacion}/edit")
    public String formPublicacion(
            @PathVariable("idPublicacion") Long idPublicacion,
            @ModelAttribute("publication") Publication publication,
            HttpSession session,
            Model model

    ){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        Publication p = publicationService.findById(idPublicacion);
        List<Category> c = categoryService.allData();
        if(user.getPublications().contains(p) == false){
            return "redirect:/";
        }else{
            model.addAttribute("p", p);
            model.addAttribute("c", c);
            model.addAttribute("user", user);
            return "editarPublicacion.jsp";
        }
    }

    @PutMapping("/publicaciones/{idPublicacion}/edit")
    public String editPublicacion(
            @PathVariable("idPublicacion") Long idPublicacion,
            @Valid @ModelAttribute("publication") Publication publication,
            BindingResult result,
            HttpSession session,
            Model model
    ){
        if(result.hasErrors()){
            Long idUser = (Long) session.getAttribute("userid");
            User user = userService.findById(idUser);
            Publication p = publicationService.findById(idPublicacion);
            List<Category> c = categoryService.allData();
            model.addAttribute("p", p);
            model.addAttribute("c", c);
            model.addAttribute("user", user);
            return "editarPublicacion.jsp";
        }else{
            Long userId = (Long) session.getAttribute("userid");
            User user = userService.findById(userId);
            Publication p = publicationService.findById(idPublicacion);
            model.addAttribute("p", p);
            p.setTitle(publication.getTitle());
            p.setDescription(publication.getDescription());
            p.setPrice(publication.getPrice());
            p.setType_publication(publication.getType_publication());
            p.setCategory(publication.getCategory());
            publicationService.update(p);
            return "redirect:/publicaciones/"+idPublicacion;
        }
    }

    /////////////////////////////////////////////////////////////
    ////////////////////Cambiar Img Publicación//////////////////
    /////////////////////////////////////////////////////////////

    @GetMapping("/publicaciones/{idPublicacion}/editFoto")
    public String cambiarFoto(            @PathVariable("idPublicacion") Long idPublicacion,
                                          @ModelAttribute("publication") Publication publication,
                                          HttpSession session,
                                          Model model

    ){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        Publication p = publicationService.findById(idPublicacion);
        if(user.getPublications().contains(p) == false){
            return "redirect:/";
        }else{
            model.addAttribute("p", p);
            model.addAttribute("user", user);
            return "cambiarFotoPublicación.jsp";
        }
    }

    @PostMapping("/publicaciones/{idPublicacion}/editFoto")
    public String editarFotoPublicación(@ModelAttribute("publication")Publication publication,
                                        @RequestParam("file")MultipartFile file, Model model,
                                        @PathVariable("idPublicacion")Long idPublicacion,
                                        HttpSession session){
        Publication p = publicationService.findById(idPublicacion);
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if (file.isEmpty()){
            String error = "Error al subir la imagen, verifique que no esté vacía";
            model.addAttribute("error", error);
            model.addAttribute("user", user);
            return "cambiarFotoPublicación.jsp";
        }else{
            String url ="img/"+user.getId()+"/"+p.getId()+"/";
            try{
                publicationService.setImage(user, file, url);
                p.setPhoto_publication(url+file.getOriginalFilename());
            }catch (Exception e){
                System.out.println(e.getMessage());
                return "redirect:/error";
            }
        }
        publicationService.update(p);
        return "redirect:/publicaciones/"+idPublicacion;
    }


    //////////////////////////////////////////////////////////
    ///////////////////"Borrar" publicacion///////////////////
    //////////////////////////////////////////////////////////

    @GetMapping("/publicaciones/{idPublicaciones}/delete")
    public String deshabilitar(@PathVariable("idPublicaciones")Long idPublicaciones, HttpSession session){
        if(session.getAttribute("userid") == null){
            return "redirect:/login";
        }else{
            Long idUser = (Long) session.getAttribute("userid");
            User user = userService.findById(idUser);
            Publication publication = publicationService.findById(idPublicaciones);
            if(user.getPublications().contains(publication) == false) {
                return "redirect:/";
            }else{
               publication.setEstado(false);
               publicationService.update(publication);
               return "redirect:/publicaciones";
            }
        }
    }
}


