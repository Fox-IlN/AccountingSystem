package com.websys.accountingSystem.controllers;

import com.websys.accountingSystem.dto.ListItemDto;
import com.websys.accountingSystem.models.Species;
import com.websys.accountingSystem.repo.SpeciesRepository;
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
@RequestMapping("/species")
public class SpeciesController {

    private final SpeciesRepository speciesRepository;

    @Autowired
    public SpeciesController(SpeciesRepository speciesRepository){this.speciesRepository=speciesRepository;}


    @GetMapping
    public String speciesController(Model model){
        model.addAttribute("species",speciesRepository.findByOrderByName());
        model.addAttribute("newspecies",new Species());
        return "species";
    }

    @PostMapping
    public String addSpecies(@ModelAttribute("newspecies") @Valid Species species, Errors errors){

        if(errors.hasErrors()){
            return "species";
        }

        Optional<Species> findName = speciesRepository.findByName(species.getName());
        if (findName.isEmpty()){
            speciesRepository.save(species);
        }

        return "redirect:/species";
    }

    @PostMapping("/{id}/edit")
    public String editById(@PathVariable("id") Long id, Model model){
        if(speciesRepository.findById(id).isPresent()){
            model.addAttribute("species",speciesRepository.findById(id).get());
            return "editSpecies";
        }
        model.addAttribute("species", new Species());
        return "editSpecies";
    }

    @PostMapping("/{id}")
    public String updateById(@Valid Species species, Errors errors){

        if(errors.hasErrors()){
            return "editSpecies";
        }

        Optional<Species> findName = speciesRepository.findByName(species.getName());
        if (findName.isEmpty()){
            speciesRepository.save(species);
        }

        return "redirect:/species";
    }

    @PostMapping("/{id}/remove")
    public String deleteById(@PathVariable("id") Long id){
        Species species = speciesRepository.findById(id).orElseThrow();
        speciesRepository.delete(species);

        return "redirect:/species";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<ListItemDto> list(@RequestParam(required = false) String firstLetters) {
        return speciesRepository.findByNameStartingWith(firstLetters).stream().map(entity ->
                        new ListItemDto(entity.getId(), entity.getName())).limit(10L)
                .collect(Collectors.toList());
    }

/*    @PostMapping("/searchSpecies")
    public String searchById(Model model){
        model.addAttribute("searchSpeciesList", speciesRepository.findAll();

        return "/searchSpecies";
    }*/

    /*@GetMapping("/list")
    public String list(Model model){
        model.addAttribute("speciesList", speciesRepository.findAll().stream().map(entity -> new ListItemDto(entity.getId(), entity.getName()))
                .collect(Collectors.toList()));
        return "species";
    }*/


}
