package com.websys.accountingSystem.repo;

import com.websys.accountingSystem.models.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeciesRepository extends JpaRepository<Species, Long>, IGetListByNameStartingWithLetters<Species>, IFindByName<Species> {

    List<Species> findByOrderByName();
}
