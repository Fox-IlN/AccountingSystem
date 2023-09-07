package com.websys.accountingSystem.repo;

import com.websys.accountingSystem.models.CollectionRecord;
import com.websys.accountingSystem.models.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.* ;

import java.util.Optional;

public interface SpeciesRepository  extends JpaRepository<Species, Long> {

    List<Species> findByOrderByName();
    List<Species> findByNameStartingWith(String input);

    Optional<Species> findByName(String name);
}
