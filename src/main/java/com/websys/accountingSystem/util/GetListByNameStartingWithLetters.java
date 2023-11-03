package com.websys.accountingSystem.util;

import com.websys.accountingSystem.dto.ListItemDto;
import com.websys.accountingSystem.models.ComboListItem;
import com.websys.accountingSystem.repo.IGetListByNameStartingWithLetters;

import java.util.List;
import java.util.stream.Collectors;

public class GetListByNameStartingWithLetters {
    //Возвращает список, содержащий все записи, начинающиеся на полученную строку
    public static <T extends ComboListItem> List<ListItemDto> getList(IGetListByNameStartingWithLetters<T> repository, String firstLetters){
        return repository.findByNameStartingWith(firstLetters).stream().map(
                entity -> new ListItemDto(entity.getId(), entity.getName())).limit(10L)
                .collect(Collectors.toList());
    }
}