package com.websys.accountingSystem.repo;

import com.websys.accountingSystem.models.HerbariumLocation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HerbariumLocationRepository  extends CrudRepository<HerbariumLocation, Long>,IFindByName<HerbariumLocation> {

}
