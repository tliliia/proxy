package com.tronina.proxy.mapper;

import com.tronina.proxy.model.SearchItem;
import com.tronina.proxy.model.SearchItemDto;

/**
 *  Маппер для преобразования объектов из api.stackexchange в необходимую модель ДТО
 */
public interface SearchResultMapper {
    SearchItemDto toDto(SearchItem searchItem);
}
