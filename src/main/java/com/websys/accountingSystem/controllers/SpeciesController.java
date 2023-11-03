package com.websys.accountingSystem.controllers;

import com.websys.accountingSystem.dto.ListItemDto;
import com.websys.accountingSystem.models.Species;
import com.websys.accountingSystem.service.SpeciesService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequestMapping("/species")
public class SpeciesController {

    @Autowired
    SpeciesService speciesService;

    @GetMapping
    public String speciesController(Model model){
        return speciesService.findAllByOrderByName(model);
    }

    @PostMapping
    public String create(@ModelAttribute("newspecies") @Valid Species species, Errors errors){
        return speciesService.create(species, errors);
    }

    @PostMapping("/{id}/edit")
    public String getUpdateSpecies(@PathVariable("id") Long id, Model model){
        return speciesService.getUpdateSpecies(id, model);
    }

    @PostMapping("/{id}")
    public String update(@Valid Species species, Errors errors){
        return speciesService.update(species, errors);
    }

    @PostMapping("/{id}/remove")
    public String delete(@PathVariable("id") Long id){
        return speciesService.delete(id);
    }

    @GetMapping("/list")                 //Запрос возвращает ответ, содержащий записи с именами, начинающимися на полученный параметр в запросе
    @ResponseBody
    public List<ListItemDto> list(@RequestParam(required = false) String firstLetters) {
        return speciesService.getAllSpeciesByNameStartingWithLetters(firstLetters);
    }

}
