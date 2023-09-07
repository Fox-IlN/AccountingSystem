package com.websys.accountingSystem.controllers;

import com.websys.accountingSystem.models.CollectionRecord;
import com.websys.accountingSystem.models.District;
import com.websys.accountingSystem.models.Region;
import com.websys.accountingSystem.models.Species;
import com.websys.accountingSystem.repo.*;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/collectionRecord")
public class CollectionRecordController {

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
    public CollectionRecordController(CollectionRecordRepository collectionRecordRepository) {
        this.collectionRecordRepository = collectionRecordRepository;}

    @GetMapping
    public String collectionRecordController(Model model) {
        model.addAttribute("collectionRecord", collectionRecordRepository.findByOrderByIdDesc());
        model.addAttribute("regionList", regionRepository.findAll());
        model.addAttribute("herbariumLocationList", herbariumLocationRepository.findAll());
        model.addAttribute("newCollectionRecord", new CollectionRecord());
        return "collectionRecord";
    }

    @PostMapping
    public String addCollectionRecord(@ModelAttribute("newCollectionRecord") @Valid CollectionRecord collectionRecord, Errors errors) {

        if (errors.hasErrors()) {
            return "collectionRecord";
        }

        Optional<CollectionRecord> findUniqueKey = collectionRecordRepository.findByUniqueKey(collectionRecord.getUniqueKey());
        if (findUniqueKey.isEmpty()) {
            collectionRecordRepository.save(collectionRecord);
        }

        return "redirect:/collectionRecord";
    }


    @PostMapping("/{id}/edit")
    public String editById(@PathVariable("id") Long id, Model model) {
        if (collectionRecordRepository.findById(id).isPresent()) {
            model.addAttribute("regionList", regionRepository.findAll());
            model.addAttribute("herbariumLocationList", herbariumLocationRepository.findAll());
            model.addAttribute("collectionRecord", collectionRecordRepository.findById(id).get());
            return "editCollectionRecord";
        }
        model.addAttribute("collectionRecord", new CollectionRecord());
        return "editCollectionRecord";
    }

    @PostMapping("/{id}")
    public String updateById(@Valid CollectionRecord collectionRecord, Errors errors) {

        if (errors.hasErrors()) {
            return "editCollectionRecord";
        }

        collectionRecordRepository.save(collectionRecord);
        return "redirect:/collectionRecord";
    }

    @PostMapping("/{id}/remove")
    public String deleteById(@PathVariable("id") Long id) {
        CollectionRecord collectionRecord = collectionRecordRepository.findById(id).orElseThrow();
        collectionRecordRepository.delete(collectionRecord);

        return "redirect:/collectionRecord";
    }

    @GetMapping("/searchSpecies")
    public String searchBySpecies() {

        return "searchSpecies";
    }

    @GetMapping("/searchBySpecies")
    public String searchBySpeciesList(@Param("keyword") Long keyword, Model model) {

        if (keyword != null) {
            Optional<Species> species = speciesRepository.findById(keyword);
            model.addAttribute("collectionRecord",
                    collectionRecordRepository.findBySpecies(species));
            model.addAttribute("collectionRecordCount", (long)
                    collectionRecordRepository.findBySpecies(species).size());
        }
        return "searchSpecies";
    }

    @GetMapping("/searchRegion")
    public String searchByRegion(Model model) {

        model.addAttribute("regionList", regionRepository.findAll());
        return "searchRegion";
    }

    @GetMapping("/searchByRegion")
    public String searchByRegionList(@Param("keyword") Long keyword, Model model) {

        if (keyword != null) {
            Optional<Region> region = regionRepository.findById(keyword);
            model.addAttribute("collectionRecord",
                    collectionRecordRepository.findByRegion(region));
            model.addAttribute("collectionRecordCount", (long)
                    collectionRecordRepository.findByRegion(region).size());
        }
        model.addAttribute("regionList", regionRepository.findAll());
        return "searchRegion";
    }

    @GetMapping("/searchDistrict")
    public String searchByDistrict() {

        return "searchDistrict";
    }

    @GetMapping("/searchByDistrict")
    public String searchByDistrictList(@Param("keyword") Long keyword, Model model) {

        if (keyword != null) {
            Optional<District> district = districtRepository.findById(keyword);
            model.addAttribute("collectionRecord",
                    collectionRecordRepository.findByDistrict(district));
            model.addAttribute("collectionRecordCount",
                    (long) collectionRecordRepository.findByDistrict(district).size());
        }
        return "searchDistrict";
    }

    @GetMapping("/searchDate")
    public String searchByDate() {

        return "searchDate";
    }

    @GetMapping("/searchByDate")
    public String searchByDateList(@Param("after") Date after, @Param("before") Date before, Model model) {

        if (after != null && before != null) {
            model.addAttribute("collectionRecord", collectionRecordRepository
                    .findByCollectionDateIsAfterAndCollectionDateIsBefore(after, before));
            model.addAttribute("collectionRecordCount", (long) collectionRecordRepository
                    .findByCollectionDateIsAfterAndCollectionDateIsBefore(after, before).size());
        }
        return "searchDate";
    }

    @GetMapping("/searchCollector")
    public String searchByCollector() {

        return "searchCollector";
    }

    @GetMapping("/searchByCollector")
    public String searchByCollectorList(@Param("keyword") String keyword, Model model) {

        if (keyword != null) {
            model.addAttribute("collectionRecord",
                    collectionRecordRepository.findByCollectorByContaining(keyword));
            model.addAttribute("collectionRecordCount",
                    (long) collectionRecordRepository.findByCollectorByContaining(keyword).size());
        }
        return "searchCollector";
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

        if (id != null && after != null && before != null) {
            Optional<Species> species = speciesRepository.findById(id);
            model.addAttribute("collectionRecord", collectionRecordRepository
                    .findBySpeciesAndCollectionDateIsAfterAndCollectionDateIsBefore(species, after, before));
            model.addAttribute("collectionRecordCount", (long) collectionRecordRepository
                    .findBySpeciesAndCollectionDateIsAfterAndCollectionDateIsBefore(species, after, before).size());
        }
        return "searchSpeciesAndDate";
    }

    @GetMapping("/searchSpeciesAndCollector")
    public String searchBySpeciesAndCollector() {

        return "searchSpeciesAndCollector";
    }

    @GetMapping("/searchBySpeciesAndCollector")
    public String searchBySpeciesAndCollectorList(@Param("id") Long id, @Param("keyword") String keyword, Model model) {

        if (id != null && keyword != null) {
            Optional<Species> species = speciesRepository.findById(id);
            model.addAttribute("collectionRecord",
                    collectionRecordRepository.findBySpeciesAndCollectorByContaining(species, keyword));
            model.addAttribute("collectionRecordCount",
                    (long) collectionRecordRepository.findBySpeciesAndCollectorByContaining(species, keyword).size());
        }
        return "searchSpeciesAndCollector";
    }

    @GetMapping("/searchRegionAndDistrict")
    public String searchByRegionAndDistrict(Model model) {

        model.addAttribute("regionList", regionRepository.findAll());
        return "searchRegionAndDistrict";
    }

    @GetMapping("/searchByRegionAndDistrict")
    public String searchByRegionAndDistrictList(@Param("idRegion") Long idRegion,
                                                @Param("idDistrict") Long idDistrict,
                                                Model model) {

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

/*    @PostMapping("/searchSpecies")
    public String searchByIdSpecies(Model model){
        model.addAttribute("searchSpeciesList", collectionRecordRepository.findAll();

        return "/searchSpecies";
    }

    @PostMapping("/search/{id}")
    public String searchBySpecies(@PathVariable("id") Species id, Model model){
        model.addAttribute("resultSearch", collectionRecordRepository.findBySpecies(id));
        return "searchSpecies";
    }*/


}
