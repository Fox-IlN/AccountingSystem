package com.websys.accountingSystem.repo;

import java.util.Optional;

public interface IFindByName<T> {
    Optional<T> findByName(String name);
}
