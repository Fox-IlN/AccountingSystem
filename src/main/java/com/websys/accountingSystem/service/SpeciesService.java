package com.websys.accountingSystem.service;

import com.websys.accountingSystem.dto.ListItemDto;
import com.websys.accountingSystem.models.Species;
import com.websys.accountingSystem.repo.SpeciesRepository;
import com.websys.accountingSystem.util.CheckingForDuplicate;
import com.websys.accountingSystem.util.GetListByNameStartingWithLetters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import java.util.List;

@Service
public class SpeciesService {

    private final SpeciesRepository speciesRepository;

    @Autowired
    public SpeciesService(SpeciesRepository speciesRepository){this.speciesRepository=speciesRepository;}

    public String findAllByOrderByName (Model model){
        model.addAttribute("species",speciesRepository.findByOrderByName());    //Возвращает все записи, сортированные по имени
        model.addAttribute("newspecies",new Species());                         //Добавляем к модели ресурс, для новой записи
        return "species";
    }

    public String create(Species species, Errors errors){                                   //Создает запись в БД
        if(errors.hasErrors()){                                                             //Ловит ошибки с клиента(валидация введенных данных)
            return "species";
        }

        if (CheckingForDuplicate.checkingForDuplicateName(speciesRepository, species)){     //Проверка имени на дубликат
            speciesRepository.save(species);
        }

        return "redirect:/species";
    }

    public String getUpdateSpecies(Long id, Model model){                                   //Возвращает данные обновляеммой записи
        if(speciesRepository.findById(id).isPresent()){
            model.addAttribute("species",speciesRepository.findById(id).get());
            return "editSpecies";
        }
        model.addAttribute("species", new Species());
        return "editSpecies";
    }

    public String update(Species species, Errors errors){                                   //Обновляет запись в БД
        if(errors.hasErrors()){                                                             //Ловит ошибки с клиента(валидация введенных данных)
            return "editSpecies";
        }

        if (CheckingForDuplicate.checkingForDuplicateName(speciesRepository, species)){     //Проверка на дубликат
            speciesRepository.save(species);
        }

        return "redirect:/species";
    }

    public String delete(Long id){                                                          //Удаляет запись в БД
        Species species = speciesRepository.findById(id).orElseThrow();
        speciesRepository.delete(species);

        return "redirect:/species";
    }

    public List<ListItemDto> getAllSpeciesByNameStartingWithLetters (String firstLetters){  //Возвращает список записей, чьи имена начинаются на полученную строку
        return GetListByNameStartingWithLetters.getList(speciesRepository, firstLetters);
    }
}
