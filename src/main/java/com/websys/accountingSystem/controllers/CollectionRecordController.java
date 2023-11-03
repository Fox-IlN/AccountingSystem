package com.websys.accountingSystem.controllers;

import com.websys.accountingSystem.models.CollectionRecord;
import com.websys.accountingSystem.service.CollectionRecordService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/collectionRecord")
public class CollectionRecordController {

    @Autowired
    CollectionRecordService collectionRecordService;

    @GetMapping
    public String getAllcollectionRecord(Model model) {
        return collectionRecordService.getAllCollectionRecord(model);
    }

    @PostMapping
    public String create(@ModelAttribute("newCollectionRecord") @Valid CollectionRecord collectionRecord, Errors errors) {
        return collectionRecordService.create(collectionRecord, errors);
    }

    @PostMapping("/{id}/edit")
    public String getUpdateCollectionRecord(@PathVariable("id") Long id, Model model) {
        return collectionRecordService.getUpdateCollectionRecord(id, model);
    }

    @PostMapping("/{id}")
    public String update(@Valid CollectionRecord collectionRecord, Errors errors) {
        return collectionRecordService.update(collectionRecord, errors);
    }

    @PostMapping("/{id}/remove")
    public String delete(@PathVariable("id") Long id) {
        return collectionRecordService.delete(id);
    }

    @GetMapping("/searchSpecies")
    public String searchBySpecies() {
        return "searchSpecies";
    }

    @GetMapping("/searchBySpecies")
    public String searchBySpeciesList(@Param("keyword") Long keyword, Model model) {
        return collectionRecordService.searchBySpeciesList(keyword, model);
    }

    @GetMapping("/searchRegion")
    public String searchByRegion(Model model) {
        return collectionRecordService.searchByRegion(model);
    }

    @GetMapping("/searchByRegion")
    public String searchByRegionList(@Param("keyword") Long keyword, Model model) {
        return collectionRecordService.searchByRegionList(keyword, model);
    }

    @GetMapping("/searchDistrict")
    public String searchByDistrict() {
        return "searchDistrict";
    }

    @GetMapping("/searchByDistrict")
    public String searchByDistrictList(@Param("keyword") Long keyword, Model model) {
        return collectionRecordService.searchByDistrictList(keyword, model);
    }

    @GetMapping("/searchDate")
    public String searchByDate() {
        return "searchDate";
    }

    @GetMapping("/searchByDate")
    public String searchByDateList(@Param("after") Date after,
                                   @Param("before") Date before,
                                   Model model) {
        return collectionRecordService.searchByDateList(after, before, model);
    }

    @GetMapping("/searchCollector")
    public String searchByCollector() {
        return "searchCollector";
    }

    @GetMapping("/searchByCollector")
    public String searchByCollectorList(@Param("keyword") String keyword, Model model) {
        return collectionRecordService.searchByCollectorList(keyword, model);
    }

    @GetMapping("/searchSpeciesAndDate")
    public String searchBySpeciesAndDate() {
        return "searchSpeciesAndDate";
    }

    @GetMapping("/searchBySpeciesAndDate")
    public String searchBySpeciesAndDateList(@Param("id") Long id,
                                             @Param("after") Date after,
                                             @Param("before") Date before,
                                             Model model) {
        return collectionRecordService.searchBySpeciesAndDateList(id, after, before, model);
    }

    @GetMapping("/searchSpeciesAndCollector")
    public String searchBySpeciesAndCollector() {
        return "searchSpeciesAndCollector";
    }

    @GetMapping("/searchBySpeciesAndCollector")
    public String searchBySpeciesAndCollectorList(@Param("id") Long id,
                                                  @Param("keyword") String keyword,
                                                  Model model) {
        return collectionRecordService.searchBySpeciesAndCollectorList(id, keyword, model);
    }

    @GetMapping("/searchRegionAndDistrict")
    public String searchByRegionAndDistrict(Model model) {
        return collectionRecordService.searchByRegionAndDistrict(model);
    }

    @GetMapping("/searchByRegionAndDistrict")
    public String searchByRegionAndDistrictList(@Param("idRegion") Long idRegion,
                                                @Param("idDistrict") Long idDistrict,
                                                Model model) {
        return collectionRecordService.searchByRegionAndDistrictList(idRegion, idDistrict, model);
    }
}
