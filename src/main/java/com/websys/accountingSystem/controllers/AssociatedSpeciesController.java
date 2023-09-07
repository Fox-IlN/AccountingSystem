/*
package com.websys.accountingSystem.controllers;

import com.websys.accountingSystem.dto.ListItemDto;
import com.websys.accountingSystem.models.AssociatedSpecies;
import com.websys.accountingSystem.repo.AssociatedSpeciesRepository;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/associatedSpecies")
public class AssociatedSpeciesController {

    private final AssociatedSpeciesRepository associatedSpeciesRepository;

    @Autowired
    public AssociatedSpeciesController(AssociatedSpeciesRepository associatedSpeciesRepository){this.associatedSpeciesRepository=associatedSpeciesRepository;}


    @GetMapping
    public String associatedSpeciesController(Model model){
        model.addAttribute("associatedSpecies",associatedSpeciesRepository.findAll());
        model.addAttribute("newassociatedSpecies",new AssociatedSpecies());
        return "associatedSpecies";
    }

    @PostMapping
    public String addAssociatedSpecies(@ModelAttribute("newassociatedSpecies") @Valid AssociatedSpecies associatedSpecies, Errors errors){

        if(errors.hasErrors()){
            return "associatedSpecies";
        }

        Optional<AssociatedSpecies> findName = associatedSpeciesRepository.findByName(associatedSpecies.getName());
        if (findName.isEmpty()){ //проверки на уникальность наименования региона, прежде чем отправить запись в БД
            associatedSpeciesRepository.save(associatedSpecies);
        }

        return "redirect:/associatedSpecies";
    }

    @PostMapping("/{id}/edit")
    public String editById(@PathVariable("id") Long id, Model model){
        if(associatedSpeciesRepository.findById(id).isPresent()){
            model.addAttribute("associatedSpecies",associatedSpeciesRepository.findById(id).get());
            return "editAssociatedSpecies";
        }
        model.addAttribute("associatedSpecies", new AssociatedSpecies());
        return "editAssociatedSpecies";
    }

    @PostMapping("/{id}")
    public String updateById(@Valid AssociatedSpecies associatedSpecies, Errors errors){

        if(errors.hasErrors()){
            return "editAssociatedSpecies";
        }

        Optional<AssociatedSpecies> findName = associatedSpeciesRepository.findByName(associatedSpecies.getName());
        if (findName.isEmpty()){ //проверки на уникальность наименования региона, прежде чем отправить запись в БД
            associatedSpeciesRepository.save(associatedSpecies);
        }

        return "redirect:/associatedSpecies";
    }

    @PostMapping("/{id}/remove")
    public String deleteById(@PathVariable("id") Long id){
        AssociatedSpecies associatedSpecies = associatedSpeciesRepository.findById(id).orElseThrow();
        associatedSpeciesRepository.delete(associatedSpecies);

        return "redirect:/associatedSpecies";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<ListItemDto> list(@RequestParam(required = false) String firstLetters) {
        return associatedSpeciesRepository.findByNameStartingWith(firstLetters).stream().map(entity -> new ListItemDto(entity.getId(), entity.getName())).limit(10L)
                .collect(Collectors.toList());
    }

}
*/
