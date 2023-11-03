package com.websys.accountingSystem.service;

import com.websys.accountingSystem.models.Region;
import com.websys.accountingSystem.repo.RegionRepository;
import com.websys.accountingSystem.util.CheckingForDuplicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository){this.regionRepository=regionRepository;}

    public String findAllRegion(Model model) {
        model.addAttribute("regions", regionRepository.findAll());              //Возвращает все записи
        model.addAttribute("newregions", new Region());                         //Добавляем к модели ресурс, для новой записи
        return "region";
    }

    public String create(Region region, Errors errors) {                                    //Создает запись в БД
        if (errors.hasErrors()) {                                                           //Ловит ошибки с клиента(валидация введенных данных)
            return "region";
        }

        if (CheckingForDuplicate.checkingForDuplicateName(regionRepository, region)) {      //Проверка имени на дубликат
            regionRepository.save(region);
        }

        return "redirect:/region";
    }

    public String getUpdateRegion(Long id, Model model) {                                   //Возвращает данные обновляеммой записи
        if (regionRepository.findById(id).isPresent()) {
            model.addAttribute("region", regionRepository.findById(id).get());
            return "editRegion";
        }
        model.addAttribute("region", new Region());
        return "editRegion";
    }

    public String update(Region region, Errors errors) {                                    //Обновляет запись в БД
        if (errors.hasErrors()) {                                                           //Ловит ошибки с клиента(валидация введенных данных)
            return "editRegion";
        }

        if (CheckingForDuplicate.checkingForDuplicateName(regionRepository, region)) {      //Проверка на дубликат
            regionRepository.save(region);
        }

        return "redirect:/region";
    }

    public String delete(Long id) {                                                         //Удаляет запись в БД
        Region region = regionRepository.findById(id).orElseThrow();
        regionRepository.delete(region);

        return "redirect:/region";
    }
}
