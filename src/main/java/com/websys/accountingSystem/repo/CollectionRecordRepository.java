package com.websys.accountingSystem.repo;

import com.websys.accountingSystem.models.CollectionRecord;
import com.websys.accountingSystem.models.District;
import com.websys.accountingSystem.models.Region;
import com.websys.accountingSystem.models.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface CollectionRecordRepository extends JpaRepository<CollectionRecord, Long> {


    List<CollectionRecord> findByOrderByIdDesc();
    List<CollectionRecord> findByRegionAndDistrict (Optional<Region> region, Optional<District> district);
    List<CollectionRecord> findBySpeciesAndCollectorByContaining(Optional<Species> species, String collector);
    List<CollectionRecord> findBySpeciesAndCollectionDateIsAfterAndCollectionDateIsBefore (Optional<Species> species, Date after, Date before);
    List<CollectionRecord> findByCollectorByContaining(String collector);
    List<CollectionRecord> findByCollectionDateIsAfterAndCollectionDateIsBefore(Date after, Date before);
    List<CollectionRecord> findByDistrict (Optional<District> district);
    List<CollectionRecord> findByRegion (Optional<Region> region);
    List<CollectionRecord> findBySpecies (Optional<Species> species);
    Optional<CollectionRecord> findByUniqueKey(String uniqueKey);
}
