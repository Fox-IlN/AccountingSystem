package com.websys.accountingSystem.controllers;

import com.websys.accountingSystem.models.HerbariumLocation;
import com.websys.accountingSystem.repo.HerbariumLocationRepository;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/herbariumLocation")
public class HerbariumLocationController {

    private final HerbariumLocationRepository herbariumLocationRepository;

    @Autowired
    public HerbariumLocationController(HerbariumLocationRepository herbariumLocationRepository){this.herbariumLocationRepository=herbariumLocationRepository;}


    @GetMapping
    public String herbariumLocationController(Model model){
        model.addAttribute("herbariumLocation",herbariumLocationRepository.findAll());
        model.addAttribute("newherbariumLocation",new HerbariumLocation());
        return "herbariumLocation";
    }

    @PostMapping
    public String addHerbariumLocation(@ModelAttribute("newherbariumLocation") @Valid HerbariumLocation herbariumLocation, Errors errors){

        if(errors.hasErrors()){
            return "herbariumLocation";
        }

        Optional<HerbariumLocation> findName = herbariumLocationRepository.findByName(herbariumLocation.getName());
        if (findName.isEmpty()){ //проверки на уникальность наименования региона, прежде чем отправить запись в БД
            herbariumLocationRepository.save(herbariumLocation);
        }

        return "redirect:/herbariumLocation";
    }

    @PostMapping("/{id}/edit")
    public String editById(@PathVariable("id") Long id, Model model){
        if(herbariumLocationRepository.findById(id).isPresent()){
            model.addAttribute("herbariumLocation",herbariumLocationRepository.findById(id).get());
            return "editHerbariumLocation";
        }
        model.addAttribute("herbariumLocation", new HerbariumLocation());
        return "editHerbariumLocation";
    }

    @PostMapping("/{id}")
    public String updateById(@Valid HerbariumLocation herbariumLocation, Errors errors){

        if(errors.hasErrors()){
            return "editHerbariumLocation";
        }

        Optional<HerbariumLocation> findName = herbariumLocationRepository.findByName(herbariumLocation.getName());
        if (findName.isEmpty()){ //проверки на уникальность наименования региона, прежде чем отправить запись в БД
            herbariumLocationRepository.save(herbariumLocation);
        }

        return "redirect:/herbariumLocation";
    }

    @PostMapping("/{id}/remove")
    public String deleteById(@PathVariable("id") Long id){
        HerbariumLocation herbariumLocation = herbariumLocationRepository.findById(id).orElseThrow();
        herbariumLocationRepository.delete(herbariumLocation);

        return "redirect:/herbariumLocation";
    }

}
