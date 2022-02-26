package com.tronina.proxy.mapper;

import com.tronina.proxy.model.SearchItem;
import com.tronina.proxy.model.SearchItemDto;

public interface SearchResultMapper {
    SearchItemDto toDto(SearchItem searchItem);
}
