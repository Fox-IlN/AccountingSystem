package com.websys.accountingSystem.repo;

import com.websys.accountingSystem.models.District;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository  extends CrudRepository<District, Long>, IGetListByNameStartingWithLetters<District>, IFindByName<District> {

}
