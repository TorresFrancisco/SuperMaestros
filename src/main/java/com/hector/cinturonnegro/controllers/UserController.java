package com.hector.cinturonnegro.controllers;

import com.hector.cinturonnegro.models.Address;
import com.hector.cinturonnegro.models.Comuna;
import com.hector.cinturonnegro.models.User;
import com.hector.cinturonnegro.services.*;
import com.hector.cinturonnegro.validator.UserValidator;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Controller
public class UserController {
    private final UserValidator userValidator;
    private final UserService userService;
    private final ComunaService comunaService;
    private final RegionService regionService;
    private final AddressService addressService;
    private final PublicationService publicationService;

    public UserController(UserValidator userValidator, UserService userService, ComunaService comunaService, RegionService regionService, AddressService addressService, PublicationService publicationService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.comunaService = comunaService;
        this.regionService = regionService;
        this.addressService = addressService;
        this.publicationService = publicationService;
    }

    //ROL = 1 => Prestador de servicios
    //ROL = 2 => Buscador de servicios

    ///////////////////////////////////////////////////////
    ///////////////////////REGISTRATION////////////////////
    ///////////////////////////////////////////////////////

    @GetMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user, HttpSession session, Model model) {
        if (session.getAttribute("userid") != null) {
            return "redirect:/index";
        } else {
            List<User> userList = userService.allData();
            model.addAttribute("userList", userList);
            model.addAttribute("regiones", regionService.allData());
            model.addAttribute("comunas", comunaService.allData());
            return "registration.jsp";
        }
    }

    @PostMapping("/registration")
    public String registerUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            HttpSession session,
            @RequestParam("file") MultipartFile file,
            Model model
    ) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            model.addAttribute("regiones", regionService.allData());
            model.addAttribute("comunas", comunaService.allData());
            return "registration.jsp";
        }
        if (userService.emailExist(user.getEmail())) {
            FieldError error = new FieldError("email", "email", "El email " + user.getEmail() + " ya se encuentra registrado");
            result.addError(error);
            model.addAttribute("regiones", regionService.allData());
            model.addAttribute("comunas", comunaService.allData());
            return "registration.jsp";
        } else {
            Address address = new Address(null, null);
            address.setNameCalle(user.getAddress().getNameCalle());
            address.setComuna(user.getAddress().getComuna());
            addressService.update(address);
            user.setAddress(address);
            int carpetaUser = userService.allData().size() + 1;
            String url = "img/" + carpetaUser + "/perfil/";
            try {
                userService.setImage(file, url);
                user.setPhoto(url + file.getOriginalFilename());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "redirect:/error";
            }
            User u = userService.registerUser(user);
            session.setAttribute("userid", u.getId());
            return "redirect:/";
        }
    }

    ////////////////////////////////////////////////////
    /////////////////////LOGIN/////////////////////////
    ///////////////////////////////////////////////////

    @GetMapping("/login")
    public String loginForm(HttpSession session) {
        if (session.getAttribute("userid") != null) {
            return "redirect:/";
        } else {
            return "login.jsp";
        }
    }

    @PostMapping("/login")
    public String loginUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model,
            HttpSession session
    ) {
        if (userService.autenticarUsuario(email, password)) {
            User user = userService.findUserByEmail(email);
            if (user.isAvailable() == false) {
                String error = "Esta cuenta está deshabilitada";
                session.setAttribute("error", error);
                return "redirect:/login";
            } else {
                session.setAttribute("userid", user.getId());
                return "redirect:/";
            }
        } else {
            String error = "Credenciales incorrectas";
            session.setAttribute("error", error);
            return "redirect:/login";
        }
    }

    ////////////////////////////////////////////////////
    //////////////LOGOUT////////////////////////////////
    //////////////////////////////////////////////////

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


    /////////////////////////////////////////////////////
    //////////////////////PERFIL////////////////////////
    ////////////////////////////////////////////////////

    @GetMapping("/perfil/{idUser}")
    public String perfilUser(@PathVariable("idUser") Long idUser, Model model, HttpSession session) {
        if (session.getAttribute("userid") == null) {
            return "redirect:/";
        }
        Long userId = (Long) session.getAttribute("userid");
        User user = userService.findById(userId);
        if (user.getId() != idUser) {
            return "redirect:/";
        } else {
            model.addAttribute("user", user);
            return "perfilUsuario.jsp";
        }
    }

    ////////////////////EDITAR PERFIL/////////////////////
    @GetMapping("/perfil/{idUser}/editar")
    public String editUserForm(
            @PathVariable("idUser") Long idUser,
            @ModelAttribute("user") User user,
            HttpSession session,
            Model model
    ) {
        if (session.getAttribute("userid") == null) {
            return "redirect:/";
        }
        Long idUserLog = (Long) session.getAttribute("userid");
        User userLog = userService.findById(idUserLog);
        if (userLog.getId() != idUser) {
            return "redirect:/";
        } else {
            model.addAttribute("regiones", regionService.allData());
            model.addAttribute("comunas", comunaService.allData());
            model.addAttribute("user", userLog);
            return "editUser.jsp";
        }
    }

    @PutMapping("/perfil/{idUser}/editar")
    public String updateUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            @PathVariable("idUser") Long idUser,
            Model model,
            HttpSession session
    ) {
        if (result.hasErrors()) {
            Long idUserLog = (Long) session.getAttribute("userid");
            User userLog = userService.findById(idUserLog);
            model.addAttribute("regiones", regionService.allData());
            model.addAttribute("comunas", comunaService.allData());
            model.addAttribute("user", userLog);
            return "editUser.jsp";
        } else {
            User userLog = userService.findById(idUser);
            model.addAttribute("user", userLog);
            userLog.setFirstName(user.getFirstName());
            userLog.setLastName(user.getLastName());
            userLog.setRol(user.getRol());
            userLog.setPhone(user.getPhone());
            userLog.getAddress().setNameCalle(user.getAddress().getNameCalle());
            userLog.getAddress().setComuna(user.getAddress().getComuna());
            userLog.getAddress().getComuna().setRegion(user.getAddress().getComuna().getRegion());
            userService.update(userLog);
            return "redirect:/perfil/" + idUser;
        }
    }

    ///////////////////////////////////////////////////
    ////////////////Cambiar Img Perfil////////////////
    /////////////////////////////////////////////////

    @GetMapping("/perfil/{idUser}/cambiarFoto")
    public String cambiarFoto(@PathVariable("idUser") Long idUser,
                              @ModelAttribute("user") User user,
                              HttpSession session,
                              Model model
    ) {
        if (session.getAttribute("userid") == null) {
            return "redirect:/";
        }
        Long idUserLog = (Long) session.getAttribute("userid");
        User userLog = userService.findById(idUserLog);
        if (userLog.getId() != idUser) {
            return "redirect:/";
        } else {
            model.addAttribute("user", userLog);
            return "editFotoPerfil.jsp";
        }

    }

    @PostMapping("/perfil/{idUser}/cambiarFoto")
    public String cambiarFotoPerfil(@ModelAttribute("user")User user,
                                    @RequestParam("file") MultipartFile file,
                                    HttpSession session, Model model,
                                    @RequestParam(value = "fotoActual", required = false)String fotoActual) {
        Long idUserLog = (Long) session.getAttribute("userid");
        User userLog = userService.findById(idUserLog);
        if (file.isEmpty()){
            String error = "Error al subir la imagen, verifique que no esté vacía";
            userLog.setPhoto(fotoActual);
            model.addAttribute("error", error);
            model.addAttribute("user", userLog);
            userService.update(userLog);
            return "editFotoPerfil.jsp";
        }
        String url = "img/" + userLog.getId() + "/perfil/";
        try {
            userService.setImage(file, url);
            userLog.setPhoto(url + file.getOriginalFilename());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/error";
        }
        userService.update(userLog);
        return "redirect:/perfil/"+userLog.getId();
    }


    /////////////////////////////////////////////////////
    ////////////////Banear/"Eliminar" cuenta/////////////
    /////////////////////////////////////////////////////

    @GetMapping("/perfil/{idUser}/estadoCuenta")
    public String estadoCuenta(@PathVariable("idUser")Long idUser,
                               Model model, HttpSession session){
        if(session.getAttribute("userid") == null){
            return "redirect:/";
        }
        Long idUserLog = (Long) session.getAttribute("userid");
        User userLog = userService.findById(idUserLog);
        User user = userService.findById(idUser);
        if (userLog.getRol() == 3 || userLog.getId() == user.getId()){
            model.addAttribute("userLog", userLog);
            model.addAttribute("user", user);
            return "estadoCuenta.jsp";
        }else {
            return "redirect:/";
        }
    }
    @GetMapping("/{idUser}/estadoCuenta")
    public String cambiarEstadoCuenta(@PathVariable("idUser")Long idUser, HttpSession session){
        Long idUserLog = (Long) session.getAttribute("userid");
        User userLog = userService.findById(idUserLog);
        User user = userService.findById(idUser);
        if (userLog.getId() == user.getId()){
            if(user.isAvailable()){
                user.setAvailable(false);
                userService.update(user);
                return "redirect:/logout";
            }
        }
        if (userLog.getRol() == 3){
            if(user.isAvailable()){
                user.setAvailable(false);
                userService.update(user);
                return "redirect:/admin/allusers";
            }else{
                user.setAvailable(true);
                userService.update(user);
                return "redirect:/admin/allusers";
            }
        }
        else{
            return "redirect:/";
        }
    }
}

