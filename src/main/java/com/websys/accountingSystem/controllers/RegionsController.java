package com.websys.accountingSystem.controllers;

import com.websys.accountingSystem.dto.ListItemDto;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.websys.accountingSystem.models.Region;
import com.websys.accountingSystem.repo.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/region")
public class RegionsController {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionsController(RegionRepository regionRepository){this.regionRepository=regionRepository;}


    @GetMapping
    public String regionController(Model model){
        model.addAttribute("regions",regionRepository.findAll());
        model.addAttribute("newregions",new Region());
        return "region";
    }

    @PostMapping
    public String addRegion(@ModelAttribute("newregions")  @Valid Region region, Errors errors){

        if(errors.hasErrors()){
            return "region";
        }

        Optional<Region> findName = regionRepository.findByName(region.getName());
        if (findName.isEmpty()){ //проверки на уникальность наименования региона, прежде чем отправить запись в БД
            regionRepository.save(region);
        }

        return "redirect:/region";
    }

    @PostMapping("/{id}/edit")
    public String editById(@PathVariable("id") Long id, Model model){
        if(regionRepository.findById(id).isPresent()){
            model.addAttribute("region",regionRepository.findById(id).get());
            return "editRegion";
        }
        model.addAttribute("region", new Region());
        return "editRegion";
    }

    @PostMapping("/{id}")
    public String updateById(@Valid Region region, Errors errors){

        if(errors.hasErrors()){
            return "editRegion";
        }

        Optional<Region> findName = regionRepository.findByName(region.getName());
        if (findName.isEmpty()){ //проверки на уникальность наименования региона, прежде чем отправить запись в БД
            regionRepository.save(region);
        }

        return "redirect:/region";
    }

    @PostMapping("/{id}/remove")
    public String deleteById(@PathVariable("id") Long id){
        Region region = regionRepository.findById(id).orElseThrow();
        regionRepository.delete(region);

        return "redirect:/region";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<ListItemDto> list(@RequestParam(required = false) String firstLetters) {
        return regionRepository.findByNameStartingWith(firstLetters).stream().map(entity -> new ListItemDto(entity.getId(), entity.getName())).limit(10L)
                .collect(Collectors.toList());
    }

    /*@GetMapping("/region/{id}/show")
    public String showById(@PathVariable("id")Long id, Model model){
       if (regionRepository.findById(id).isPresent()){
           model.addAttribute("region", regionRepository.findById(id).get());
           return "show";
       }
       model.addAttribute("region", new Region());
       return "show";
    }*/
}
