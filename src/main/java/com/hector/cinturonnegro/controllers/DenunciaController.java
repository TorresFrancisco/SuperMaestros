package com.hector.cinturonnegro.controllers;

import com.hector.cinturonnegro.models.Denuncia;
import com.hector.cinturonnegro.models.Message;
import com.hector.cinturonnegro.models.User;
import com.hector.cinturonnegro.services.DenunciaService;
import com.hector.cinturonnegro.services.MessageService;
import com.hector.cinturonnegro.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class DenunciaController {
    private final DenunciaService denunciaService;
    private final MessageService messageService;
    private final UserService userService;

    public DenunciaController(DenunciaService denunciaService, MessageService messageService, UserService userService) {
        this.denunciaService = denunciaService;
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/denuncia/{idMessage}")
    public String denunciar(@ModelAttribute("denuncia")Denuncia denuncia ,@PathVariable("idMessage")Long idMessage, Model model,
                            HttpSession session){
        Long idUserLog = (Long) session.getAttribute("userid");
        User userLog = userService.findById(idUserLog);
        Message message = messageService.findById(idMessage);
        model.addAttribute("user", userLog);
        model.addAttribute("message", message);
        return "denuncia.jsp";
    }

    @PostMapping("/denuncia/{idMessage}")
    public String denuncia(@Valid @ModelAttribute("denuncia")Denuncia denuncia,
                           BindingResult result,
                           @PathVariable("idMessage")Long idMessage){
        if (result.hasErrors()){
            return "redirect:/";
        }else{
            Message message = messageService.findById(idMessage);
            denunciaService.create(denuncia);
            denuncia.setMessage(message);
            message.setNumDenuncias(message.getNumDenuncias() + 1);
            messageService.update(message);
            denunciaService.update(denuncia);
            return "redirect:/";
        }
    }
}
