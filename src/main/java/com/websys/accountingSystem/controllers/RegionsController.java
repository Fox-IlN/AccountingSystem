package com.websys.accountingSystem.controllers;

import com.websys.accountingSystem.service.RegionService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.websys.accountingSystem.models.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/region")
public class RegionsController {

    @Autowired
    RegionService regionService;

    @GetMapping
    public String regionController(Model model){
        return regionService.findAllRegion(model);
    }

    @PostMapping
    public String create(@ModelAttribute("newregions")  @Valid Region region, Errors errors){
        return regionService.create(region, errors);
    }

    @PostMapping("/{id}/edit")
    public String getUpdateRegion(@PathVariable("id") Long id, Model model){
        return regionService.getUpdateRegion(id, model);
    }

    @PostMapping("/{id}")
    public String update(@Valid Region region, Errors errors){
        return regionService.update(region, errors);
    }

    @PostMapping("/{id}/remove")
    public String delete(@PathVariable("id") Long id){
        return regionService.delete(id);
    }
}
