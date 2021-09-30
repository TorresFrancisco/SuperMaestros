package com.hector.cinturonnegro.controllers;

import com.hector.cinturonnegro.models.Comuna;
import com.hector.cinturonnegro.models.Notificacion;
import com.hector.cinturonnegro.models.Region;
import com.hector.cinturonnegro.services.RegionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ApiController {
    private final RegionService regionService;

    public ApiController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping(value = "/cargarComunas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Comuna> comunas(@RequestBody Region region){
        List<Comuna> comunas = regionService.findById(region.getId()).getComunas();
        if(comunas == null){
            return new ArrayList<Comuna>();
        }
        else{
            return comunas;
        }
    }
}
