package com.websys.accountingSystem.service;

import com.websys.accountingSystem.dto.ListItemDto;
import com.websys.accountingSystem.models.District;
import com.websys.accountingSystem.repo.DistrictRepository;
import com.websys.accountingSystem.util.CheckingForDuplicate;
import com.websys.accountingSystem.util.GetListByNameStartingWithLetters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import java.util.List;

@Service
public class DistrictService {

    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictService(DistrictRepository districtRepository){this.districtRepository=districtRepository;}

    public String getAllDistrict(Model model){
        model.addAttribute("districts",districtRepository.findAll());                       //Возвращает все записи
        model.addAttribute("newdistricts",new District());                                  //Добавляем к модели ресурс, для новой записи
        return "district";
    }

    public String create(District district, Errors errors){                                             //Создает запись в БД
        if(errors.hasErrors()){                                                                         //Ловит ошибки с клиента(валидация введенных данных)
            return "district";
        }

        if (CheckingForDuplicate.checkingForDuplicateName(districtRepository,district)){                //Проверка имени на дубликат
            districtRepository.save(district);
        }

        return "redirect:/district";
    }

    public String getUpdateDistrict(Long id, Model model){                                              //Возвращает данные обновляеммой записи
        if(districtRepository.findById(id).isPresent()){
            model.addAttribute("district",districtRepository.findById(id).get());
            return "editDistrict";
        }
        model.addAttribute("district", new District());
        return "editDistrict";
    }

    public String update(District district, Errors errors){                                             //Обновляет запись в БД
        if(errors.hasErrors()){                                                                         //Ловит ошибки с клиента(валидация введенных данных)
            return "editDistrict";
        }

        if (CheckingForDuplicate.checkingForDuplicateName(districtRepository, district)){               //Проверка на дубликат
            districtRepository.save(district);
        }

        return "redirect:/district";
    }

    public String delete(Long id){                                                                      //Удаляет запись в БД
        District district = districtRepository.findById(id).orElseThrow();
        districtRepository.delete(district);

        return "redirect:/district";
    }

    public List<ListItemDto> getAllDistrictByNameStartingWithLetters(String firstLetters) {             //Возвращает список записей, чьи имена начинаются на полученную строку
        return GetListByNameStartingWithLetters.getList(districtRepository, firstLetters);
    }
}
