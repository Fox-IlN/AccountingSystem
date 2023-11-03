package com.websys.accountingSystem.service;

import com.websys.accountingSystem.models.CollectionRecord;
import com.websys.accountingSystem.models.District;
import com.websys.accountingSystem.models.Region;
import com.websys.accountingSystem.models.Species;
import com.websys.accountingSystem.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import java.util.Date;
import java.util.Optional;

@Service
public class CollectionRecordService {

    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private HerbariumLocationRepository herbariumLocationRepository;
    @Autowired
    private SpeciesRepository speciesRepository;
    @Autowired
    private DistrictRepository districtRepository;

    private final CollectionRecordRepository collectionRecordRepository;

    @Autowired
    public CollectionRecordService(CollectionRecordRepository collectionRecordRepository) {
        this.collectionRecordRepository = collectionRecordRepository;}

    public String getAllCollectionRecord(Model model) {
        model.addAttribute("collectionRecord", collectionRecordRepository.findByOrderByIdDesc());       //Возвращает все записи таблицы collection_record
        model.addAttribute("regionList", regionRepository.findAll());                                   //Возвращает все записи таблицы region
        model.addAttribute("herbariumLocationList", herbariumLocationRepository.findAll());             //Возвращает все записи таблицы  herbarium_location
        model.addAttribute("newCollectionRecord", new CollectionRecord());                              //Добавляем к модели ресурс, для новой записи
        return "collectionRecord";
    }

    public String create(CollectionRecord collectionRecord, Errors errors) {                                        //Создает запись в БД
        if (errors.hasErrors()) {                                                                                   //Ловит ошибки с клиента(валидация введенных данных)
            return "collectionRecord";
        }

        Optional<CollectionRecord> findUniqueKey = collectionRecordRepository.findByUniqueKey(collectionRecord.getUniqueKey()); //Проверка на уникальность инвентарного номера
        if (findUniqueKey.isEmpty()) {
            collectionRecordRepository.save(collectionRecord);
        }

        return "redirect:/collectionRecord";
    }

    public String getUpdateCollectionRecord(Long id, Model model) {
        if (collectionRecordRepository.findById(id).isPresent()) {
            model.addAttribute("regionList", regionRepository.findAll());                               //Возвращает все записи таблицы region
            model.addAttribute("herbariumLocationList", herbariumLocationRepository.findAll());         //Возвращает все записи таблицы  herbarium_location
            model.addAttribute("collectionRecord", collectionRecordRepository.findById(id).get());      //Возвращает данные обновляеммой записи
            return "editCollectionRecord";
        }
        model.addAttribute("collectionRecord", new CollectionRecord());
        return "editCollectionRecord";
    }

    public String update(CollectionRecord collectionRecord, Errors errors) {                                        //Обновляет запись в БД

        if (errors.hasErrors()) {                                                                                   //Ловит ошибки с клиента(валидация введенных данных)
            return "editCollectionRecord";
        }

        collectionRecordRepository.save(collectionRecord);
        return "redirect:/collectionRecord";
    }

    public String delete(Long id) {                                                                                 //Удаляет запись в БД
        CollectionRecord collectionRecord = collectionRecordRepository.findById(id).orElseThrow();
        collectionRecordRepository.delete(collectionRecord);

        return "redirect:/collectionRecord";
    }

    public String searchBySpeciesList(Long keyword, Model model) {                                                  //Возвращает записи из таблицы collection_records с указанным видом
        if (keyword != null) {
            Optional<Species> species = speciesRepository.findById(keyword);
            model.addAttribute("collectionRecord", collectionRecordRepository.findBySpecies(species));
            model.addAttribute("collectionRecordCount", (long) collectionRecordRepository.findBySpecies(species).size());
        }
        return "searchSpecies";
    }

    public String searchByRegion(Model model) {                                                                     //Возвращает записи из таблицы region
        model.addAttribute("regionList", regionRepository.findAll());
        return "searchRegion";
    }

    public String searchByRegionList(Long keyword, Model model) {                                                   //Возвращает записи из таблицы collection_records с указанным регионом
        if (keyword != null) {
            Optional<Region> region = regionRepository.findById(keyword);
            model.addAttribute("collectionRecord", collectionRecordRepository.findByRegion(region));
            model.addAttribute("collectionRecordCount", (long) collectionRecordRepository.findByRegion(region).size());
        }
        model.addAttribute("regionList", regionRepository.findAll());
        return "searchRegion";
    }

    public String searchByDistrictList(Long keyword, Model model) {                                                 //Возвращает записи из таблицы collection_records с указанным районом
        if (keyword != null) {
            Optional<District> district = districtRepository.findById(keyword);
            model.addAttribute("collectionRecord", collectionRecordRepository.findByDistrict(district));
            model.addAttribute("collectionRecordCount", (long) collectionRecordRepository.findByDistrict(district).size());
        }
        return "searchDistrict";
    }

    public String searchByDateList(Date after, Date before, Model model) {                                          //Возвращает записи из таблицы collection_records в промежутке указанной даты
        if (after != null && before != null) {
            model.addAttribute("collectionRecord", collectionRecordRepository.
                    findByCollectionDateIsAfterAndCollectionDateIsBefore(after, before));
            model.addAttribute("collectionRecordCount", (long) collectionRecordRepository.
                    findByCollectionDateIsAfterAndCollectionDateIsBefore(after, before).size());
        }
        return "searchDate";
    }

    public String searchByCollectorList(String keyword, Model model) {                                              //Возвращает записи из таблицы collection_records с указанным коллектором
        if (keyword != null) {
            model.addAttribute("collectionRecord",
                    collectionRecordRepository.findByCollectorByContaining(keyword));
            model.addAttribute("collectionRecordCount", (long) collectionRecordRepository.
                    findByCollectorByContaining(keyword).size());
        }
        return "searchCollector";
    }

    public String searchBySpeciesAndDateList(Long id, Date after, Date before, Model model) {                       //Возвращает записи из таблицы collection_records с указанными видом и промежуточной датой между указанных
        if (id != null && after != null && before != null) {
            Optional<Species> species = speciesRepository.findById(id);
            model.addAttribute("collectionRecord", collectionRecordRepository
                    .findBySpeciesAndCollectionDateIsAfterAndCollectionDateIsBefore(species, after, before));
            model.addAttribute("collectionRecordCount", (long) collectionRecordRepository
                    .findBySpeciesAndCollectionDateIsAfterAndCollectionDateIsBefore(species, after, before).size());
        }
        return "searchSpeciesAndDate";
    }

    public String searchBySpeciesAndCollectorList(Long id, String keyword, Model model) {                           //Возвращает записи из таблицы collection_records с указанными видом и коллектором
        if (id != null && keyword != null) {
            Optional<Species> species = speciesRepository.findById(id);
            model.addAttribute("collectionRecord",
                    collectionRecordRepository.findBySpeciesAndCollectorByContaining(species, keyword));
            model.addAttribute("collectionRecordCount",
                    (long) collectionRecordRepository.findBySpeciesAndCollectorByContaining(species, keyword).size());
        }
        return "searchSpeciesAndCollector";
    }

    public String searchByRegionAndDistrict(Model model) {                                                          //Возвращает записи из таблицы region
        model.addAttribute("regionList", regionRepository.findAll());
        return "searchRegionAndDistrict";
    }

    public String searchByRegionAndDistrictList(Long idRegion, Long idDistrict, Model model) {                      //Возвращает записи из таблицы collection_records с указанными регионом и районом
        if (idRegion != null && idDistrict != null) {
            Optional<Region> region = regionRepository.findById(idRegion);
            Optional<District> district = districtRepository.findById(idDistrict);
            model.addAttribute("collectionRecord",
                    collectionRecordRepository.findByRegionAndDistrict(region, district));
            model.addAttribute("collectionRecordCount", (long)
                    collectionRecordRepository.findByRegionAndDistrict(region, district).size());
        }
        model.addAttribute("regionList", regionRepository.findAll());
        return "searchRegionAndDistrict";
    }
}
