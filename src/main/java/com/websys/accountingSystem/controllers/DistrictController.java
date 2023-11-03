package com.websys.accountingSystem.controllers;

import com.websys.accountingSystem.dto.ListItemDto;
import com.websys.accountingSystem.models.District;
import com.websys.accountingSystem.service.DistrictService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    public DistrictService districtService;


    @GetMapping
    public String getAllDistrict(Model model){
        return districtService.getAllDistrict(model);
    }

    @PostMapping
    public String create(@ModelAttribute("newdistricts") @Valid District district, Errors errors){
        return districtService.create(district, errors);
    }

    @PostMapping("/{id}/edit")
    public String getUpdateDistrict(@PathVariable("id") Long id, Model model){
        return districtService.getUpdateDistrict(id, model);
    }

    @PostMapping("/{id}")
    public String update(@Valid District district, Errors errors){
        return districtService.update(district, errors);
    }

    @PostMapping("/{id}/remove")
    public String delete(@PathVariable("id") Long id){
        return districtService.delete(id);
    }

    @GetMapping("/list") //Запрос возвращает ответ, содержащий записи с именами, начинающимися на полученный параметр в запросе
    @ResponseBody
    public List<ListItemDto> list(@RequestParam(required = false) String firstLetters) {
        return districtService.getAllDistrictByNameStartingWithLetters(firstLetters);
    }

}
