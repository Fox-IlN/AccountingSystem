package com.websys.accountingSystem.controllers;

import com.websys.accountingSystem.dto.ListItemDto;
import com.websys.accountingSystem.models.District;
import com.websys.accountingSystem.repo.DistrictRepository;
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
@RequestMapping("/district")
public class DistrictController {

    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictController(DistrictRepository districtRepository){this.districtRepository=districtRepository;}


    @GetMapping
    public String districtController(Model model){
        model.addAttribute("districts",districtRepository.findAll());
        model.addAttribute("newdistricts",new District());
        return "district";
    }

    @PostMapping
    public String addDistrict(@ModelAttribute("newdistricts") @Valid District district, Errors errors){

        if(errors.hasErrors()){
            return "district";
        }

        Optional<District> findName = districtRepository.findByName(district.getName());
        if (findName.isEmpty()){ //проверки на уникальность наименования региона, прежде чем отправить запись в БД
            districtRepository.save(district);
        }

        return "redirect:/district";
    }

    @PostMapping("/{id}/edit")
    public String editById(@PathVariable("id") Long id, Model model){
        if(districtRepository.findById(id).isPresent()){
            model.addAttribute("district",districtRepository.findById(id).get());
            return "editDistrict";
        }
        model.addAttribute("district", new District());
        return "editDistrict";
    }

    @PostMapping("/{id}")
    public String updateById(@Valid District district, Errors errors){

        if(errors.hasErrors()){
            return "editDistrict";
        }

        Optional<District> findName = districtRepository.findByName(district.getName());
        if (findName.isEmpty()){ //проверки на уникальность наименования региона, прежде чем отправить запись в БД
            districtRepository.save(district);
        }

        return "redirect:/district";
    }

    @PostMapping("/{id}/remove")
    public String deleteById(@PathVariable("id") Long id){
        District district = districtRepository.findById(id).orElseThrow();
        districtRepository.delete(district);

        return "redirect:/district";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<ListItemDto> list(@RequestParam(required = false) String firstLetters) {
        return districtRepository.findByNameStartingWith(firstLetters).stream().map(entity -> new ListItemDto(entity.getId(), entity.getName())).limit(10L)
                .collect(Collectors.toList());
    }

}
