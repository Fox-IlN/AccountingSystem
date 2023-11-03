package com.websys.accountingSystem.repo;

import com.websys.accountingSystem.models.Region;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends CrudRepository<Region, Long>, IFindByName<Region> {

}
