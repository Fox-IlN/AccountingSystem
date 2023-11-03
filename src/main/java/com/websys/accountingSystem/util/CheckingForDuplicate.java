package com.websys.accountingSystem.util;

import com.websys.accountingSystem.models.ComboListItem;
import com.websys.accountingSystem.models.Species;
import com.websys.accountingSystem.repo.IFindByName;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public class CheckingForDuplicate {
    //Проверка на дубликат по имени
    public static <T> boolean checkingForDuplicateName(IFindByName<T> repository, ComboListItem model){
        Optional<T> findName = repository.findByName(model.getName());
        return findName.isEmpty();
    }
}
