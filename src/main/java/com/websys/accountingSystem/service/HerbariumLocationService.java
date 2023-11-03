package com.websys.accountingSystem.service;

import com.websys.accountingSystem.models.HerbariumLocation;
import com.websys.accountingSystem.repo.HerbariumLocationRepository;
import com.websys.accountingSystem.util.CheckingForDuplicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

@Service
public class HerbariumLocationService {

    private final HerbariumLocationRepository herbariumLocationRepository;

    @Autowired
    public HerbariumLocationService(HerbariumLocationRepository herbariumLocationRepository){this.herbariumLocationRepository=herbariumLocationRepository;}

    public String getAllHerbariumLocation(Model model){
        model.addAttribute("herbariumLocation",herbariumLocationRepository.findAll());      //Возвращает все записи
        model.addAttribute("newherbariumLocation",new HerbariumLocation());                 //Добавляем к модели ресурс, для новой записи
        return "herbariumLocation";
    }

    public String create(HerbariumLocation herbariumLocation, Errors errors){                           //Создает запись в БД
        if(errors.hasErrors()){                                                                         //Ловит ошибки с клиента(валидация введенных данных)
            return "herbariumLocation";
        }

        if (CheckingForDuplicate.checkingForDuplicateName(herbariumLocationRepository, herbariumLocation)){ //Проверка имени на дубликат
            herbariumLocationRepository.save(herbariumLocation);
        }

        return "redirect:/herbariumLocation";
    }

    public String getUpdateHerbariumLocation(Long id, Model model){                                     //Возвращает данные обновляеммой записи
        if(herbariumLocationRepository.findById(id).isPresent()){
            model.addAttribute("herbariumLocation",herbariumLocationRepository.findById(id).get());
            return "editHerbariumLocation";
        }
        model.addAttribute("herbariumLocation", new HerbariumLocation());
        return "editHerbariumLocation";
    }

    public String update(HerbariumLocation herbariumLocation, Errors errors){                           //Обновляет запись в БД
        if(errors.hasErrors()){                                                                         //Ловит ошибки с клиента(валидация введенных данных)
            return "editHerbariumLocation";
        }

        if (CheckingForDuplicate.checkingForDuplicateName(herbariumLocationRepository, herbariumLocation)){ //Проверка на дубликат
            herbariumLocationRepository.save(herbariumLocation);
        }

        return "redirect:/herbariumLocation";
    }

    public String delete(Long id){                                                                      //Удаляет запись в БД
        HerbariumLocation herbariumLocation = herbariumLocationRepository.findById(id).orElseThrow();
        herbariumLocationRepository.delete(herbariumLocation);

        return "redirect:/herbariumLocation";
    }
}
