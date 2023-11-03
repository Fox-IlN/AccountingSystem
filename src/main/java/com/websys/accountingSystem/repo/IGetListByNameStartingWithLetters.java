package com.websys.accountingSystem.repo;

import java.util.List;

public interface IGetListByNameStartingWithLetters<T>{
    List<T> findByNameStartingWith(String input);
}
