package com.websys.accountingSystem.controllers;

import com.websys.accountingSystem.models.HerbariumLocation;
import com.websys.accountingSystem.service.HerbariumLocationService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/herbariumLocation")
public class HerbariumLocationController {

    @Autowired
    HerbariumLocationService herbariumLocationService;

    @GetMapping
    public String herbariumLocationController(Model model){
        return herbariumLocationService.getAllHerbariumLocation(model);
    }

    @PostMapping
    public String create(@ModelAttribute("newherbariumLocation") @Valid HerbariumLocation herbariumLocation, Errors errors){
        return herbariumLocationService.create(herbariumLocation, errors);
    }

    @PostMapping("/{id}/edit")
    public String getUpdateHerbariumLocation(@PathVariable("id") Long id, Model model){
        return herbariumLocationService.getUpdateHerbariumLocation(id, model);
    }

    @PostMapping("/{id}")
    public String update(@Valid HerbariumLocation herbariumLocation, Errors errors){
        return herbariumLocationService.update(herbariumLocation, errors);
    }

    @PostMapping("/{id}/remove")
    public String delete(@PathVariable("id") Long id){
        return herbariumLocationService.delete(id);
    }

}
