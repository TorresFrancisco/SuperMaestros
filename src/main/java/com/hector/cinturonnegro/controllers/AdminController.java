package com.hector.cinturonnegro.controllers;

import com.hector.cinturonnegro.models.*;
import com.hector.cinturonnegro.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;
    private final PublicationService publicationService;
    private final FeedbackService feedbackService;
    private final ComunaService comunaService;
    private final RegionService regionService;
    private final CategoryService categoryService;
    private final AddressService addressService;
    private final MessageService messageService;

    //admin == userRol => 3


    public AdminController(UserService userService, PublicationService publicationService, FeedbackService feedbackService, ComunaService comunaService, RegionService regionService, CategoryService categoryService, AddressService addressService, MessageService messageService) {
        this.userService = userService;
        this.publicationService = publicationService;
        this.feedbackService = feedbackService;
        this.comunaService = comunaService;
        this.regionService = regionService;
        this.categoryService = categoryService;
        this.addressService = addressService;
        this.messageService = messageService;
    }

    @GetMapping("/admin")
    public String admin(HttpSession session, Model model){
        if(session.getAttribute("userid") == null) {
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            return "admin.jsp";
        }
    }

    @GetMapping("/admin/allusers")
    public String adminAllUsers(HttpSession session, Model model){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            List<User> users = userService.allData();
            model.addAttribute("data", users);
            return "adminDataAllUsuarios.jsp";
        }
    }

    @GetMapping("/admin/allpublications")
    public String adminAllPublications(HttpSession session, Model model){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            List<Publication> publications = publicationService.allData();
            model.addAttribute("data", publications);
            return "adminDataAllPublicaciones.jsp";
        }
    }


    @GetMapping("/admin/{idUsuario}/createAdmin")
    public String addAdmin(
            HttpSession session,
            @PathVariable("idUsuario") Long idUsuario
    ){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            User adminNew = userService.findById(idUsuario);
            adminNew.setRol(3);
            userService.update(adminNew);
            return "redirect:/admin";
        }
    }

    @GetMapping("/admin/{idPublicacion}/borrarpublicacion")
    public String deletePublicacion(
            HttpSession session,
            @PathVariable("idPulicacion") Long idPublicacion
    ){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            Publication publication = publicationService.findById(idPublicacion);
            publication.setCategory(null);
            publication.setUser(null);
            for (Feedback feedback : publication.getFeedback()) {
                feedbackService.delete(feedback.getId());
            }
            publicationService.delete(publication.getId());
            return "redirect:/admin";
        }

    }

    /////////////////////////////////////////////////////////////
    ////////////////////////CREAR COMUNA/////////////////////////
    /////////////////////////////////////////////////////////////

    @GetMapping("/admin/comunas")
    public String allComuna(
            HttpSession session,
            Model model
    ){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        } else{
            List<Comuna> allData = comunaService.allData();
            model.addAttribute("data", allData);
            return "adminDataComunas.jsp";
        }
    }

    @GetMapping("/admin/comunas/{idComuna}")
    public String comunaPorId(
            @PathVariable("idComuna") Long idComuna,
            HttpSession session,
            Model model
    ){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            Comuna comuna = comunaService.findById(idComuna);
            model.addAttribute("comuna", comuna);
            return "comunaPorIdAdmin.jsp";
        }
    }


    @GetMapping("/admin/comunas/new")
    public String comunaForm(
            HttpSession session,
            Model model,
            @ModelAttribute("comuna") Comuna comuna
    ){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            List<Region> regiones = regionService.allData();
            model.addAttribute("regiones", regiones);
            return "newComunaAdmin.jsp";
        }
    }

    @PostMapping("/admin/comunas/new")
    public String addComuna(
            @Valid @ModelAttribute("comuna") Comuna comuna,
            BindingResult result,
            Model model
    ){
        if(result.hasErrors()){
            return "newComunaAdmin.jsp";
        }else{
            List<Region> regiones = regionService.allData();
            model.addAttribute("regiones", regiones);
            comunaService.create(comuna);
            return "redirect:/admin/comunas";
        }
    }

    /////////////////////////////////////////////////////////////
    ////////////////////////CREAR REGION/////////////////////////
    /////////////////////////////////////////////////////////////

    @GetMapping("/admin/regiones")
    public String allRegions(
            HttpSession session,
            Model model
    ){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            List<Region> regiones = regionService.allData();
            model.addAttribute("data", regiones);
            return "adminDataRegiones.jsp";
        }
    }

    @GetMapping("/admin/regiones/{idRegion}")
    public String regionPorId(
            @PathVariable("idRegion") Long idRegion,
            HttpSession session,
            Model model
    ){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            Region region = regionService.findById(idRegion);
            model.addAttribute("region", region);
            return "regionPorIdAdmin.jsp";
        }
    }



    @GetMapping("/admin/regiones/new")
    public String formRegion(HttpSession session, Model model, @ModelAttribute("region") Region region){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            return "newRegionAdmin.jsp";
        }
    }

    @PostMapping("/admin/regiones/new")
    public String addRegion(
            @Valid @ModelAttribute("region") Region region,
            BindingResult result
    ){
        if(result.hasErrors()){
            return "newRegionAdmin.jsp";
        }else{
            regionService.create(region);
            return "redirect:/admin/regiones";
        }
    }

    /////////////////////////////////////////////////////////////
    ////////////////////////CREAR CATEGORY///////////////////////
    /////////////////////////////////////////////////////////////

    @GetMapping("/admin/categories")
    public String allCategories(HttpSession session, Model model){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            List<Category> categories = categoryService.allData();
            model.addAttribute("data", categories);
            return "adminDataCategories.jsp";
        }
    }

    @GetMapping("/admin/categories/{idCategory}")
    public String categoryPorId(@PathVariable("idCategory") Long idCategory, HttpSession session, Model model){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            Category category = categoryService.findById(idCategory);
            model.addAttribute("category", category);
            return "categoryPorIdAdmin.jsp";
        }
    }

    @GetMapping("/admin/categories/new")
    public String categoryForm(HttpSession session, @ModelAttribute("category") Category category){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            return "newCategoryAdmin.jsp";
        }
    }

    @PostMapping("/admin/categories/new")
    public String addCategory(
            @Valid @ModelAttribute("category") Category category,
            BindingResult result
    ){
        if(result.hasErrors()){
            return "newCategoryAdmin.jsp";
        }else{
            categoryService.create(category);
            return "redirect:/admin/categories";
        }
    }

    ///////////////////////////////////////////////////////
    //////////////////////DELETE X3////////////////////////
    ///////////////////////////////////////////////////////

    @GetMapping("/admin/categories/{idCategory}/delete")
    public String deleteCategories(@PathVariable("idCategory") Long idCategory, HttpSession session){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            Category category = categoryService.findById(idCategory);
            category.setPublications(new ArrayList<>());
            categoryService.delete(category.getId());
            return "redirect:/admin/categories";
        }
    }
    @GetMapping("/admin/regiones/{idRegion}/delete")
    public String deleteRegion(@PathVariable("idRegion") Long idRegion, HttpSession session){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            Region region = regionService.findById(idRegion);
            for (Comuna comuna : region.getComunas()) {
                comunaService.delete(comuna.getId());
            }
            regionService.delete(region.getId());
            return "redirect:/admin/regiones";
        }
    }
    @GetMapping("/admin/comunas/{idComuna}/delete")
    public String deleteComuna(@PathVariable("idComuna") Long idComuna, HttpSession session){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            Comuna comuna = comunaService.findById(idComuna);
            for (Address address : comuna.getUserAddress()) {
                addressService.delete(address.getId());
            }
            comunaService.delete(comuna.getId());
            return "redirect:/admin/comunas";
        }
    }

    ///////////////////////////////////////////////////////////
    /////////////////////EDIT X 3//////////////////////////////
    ///////////////////////////////////////////////////////////

    @GetMapping("/admin/comunas/{idComuna}/edit")
    public String formComunaEdit(@PathVariable("idComuna") Long idComuna, HttpSession session, @ModelAttribute("comuna") Comuna comuna, Model model){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            Comuna c = comunaService.findById(idComuna);
            List<Region> allRegions = regionService.allData();
            model.addAttribute("allRegions", allRegions);
            model.addAttribute("c", c);
            return "editComunaAdmin.jsp";
        }
    }

    @PutMapping("/admin/comunas/{idComuna}/edit")
    public String editingComuna(
            @PathVariable("idComuna") Long idComuna,
            @Valid @ModelAttribute("comuna") Comuna comuna,
            BindingResult result,
            Model model
    ){
        if(result.hasErrors()){
            List<Region> allRegions = regionService.allData();
            model.addAttribute("allRegions", allRegions);
            return "editComunaAdmin.jsp";
        }else{
            List<Region> allRegions = regionService.allData();
            model.addAttribute("allRegions", allRegions);
            Comuna c = comunaService.findById(idComuna);
            c.setNameComuna(comuna.getNameComuna());
            c.setRegion(comuna.getRegion());
            comunaService.update(c);
            return "redirect:/admin/comunas";
        }
    }


    @GetMapping("/admin/regiones/{idRegion}/edit")
    public String formRegionEdit(@PathVariable("idRegion") Long idRegion, HttpSession session, @ModelAttribute("region") Region region, Model model){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            Region r = regionService.findById(idRegion);
            model.addAttribute("r", r);
            return "editRegionAdmin.jsp";
        }
    }

    @PutMapping("/admin/regiones/{idRegion}/edit")
    public String editRegion(
        @Valid @ModelAttribute("region") Region region,
        BindingResult result,
        @PathVariable("idRegion") Long idRegion
    ){
        if(result.hasErrors()){
            return "editRegionAdmin.jsp";
        }else{
            Region r = regionService.findById(idRegion);
            r.setNameRegion(region.getNameRegion());
            regionService.update(r);
            return "redirect:/admin/regiones";
        }
    }

    @GetMapping("/admin/categories/{idCategory}/edit")
    public String formCategory(
            @PathVariable("idCategory") Long idCategory,
            HttpSession session,
            Model model,
            @ModelAttribute("category") Category category
    ){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            Category c = categoryService.findById(idCategory);
            model.addAttribute("c", c);
            return "editCategoryAdmin.jsp";
        }
    }

    @PutMapping("/admin/categories/{idCategory}/edit")
    public String editCategory(
            @PathVariable("idCategory") Long idCategory,
            @Valid @ModelAttribute("Category") Category category,
            BindingResult result,
            Model model
    ){
        if (result.hasErrors()){
            return "editCategoryAdmin.jsp";
        } else{
            Category c = categoryService.findById(idCategory);
            model.addAttribute("c", c);
            c.setName(category.getName());
            categoryService.update(c);
            return "redirect:/admin/categories";
        }
    }

    ///////////////////////////////////////////////////////////
    ////////////////////////Mensajes Denunciados//////////////////////////
    ///////////////////////////////////////////////////////////


    @GetMapping("/admin/denuncias")
    public String denuncias(HttpSession session, Model model){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            List<Publication> publications = publicationService.allData();
            List<Message> messageList = new ArrayList<>();
            for (Publication publication: publications) {
                List<Message> messages = publication.getMessages();
                for (Message message: messages) {
                    if (message.getDenuncias() != null){
                        messageList.add(message);
                    }
                }
            }
            model.addAttribute("messages", messageList);
            return "adminMensajesConDenuncias.jsp";
        }
    }

    //////////////////////////////////////////////////////////////////
    /////////////////////////Denuncias////////////////////////////////
    //////////////////////////////////////////////////////////////////

    @GetMapping("/admin/denuncias/{idMensaje}")
    public String denunciasMensaje(@PathVariable("idMensaje")Long idMensaje,
                                   HttpSession session, Model model){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUser = (Long) session.getAttribute("userid");
        User user = userService.findById(idUser);
        if(user.getRol() != 3){
            return "redirect:/publicaciones";
        }else{
            Message message = messageService.findById(idMensaje);
            model.addAttribute("message", message);
            return "adminDenunciasMensaje.jsp";
            }
        }
}
