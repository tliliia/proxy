package com.tronina.proxy.mapper;

import com.tronina.proxy.modelDto.SearchItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {DateMapper.class})
public interface SearchItemMapper {

    SearchItemMapper INSTANCE = Mappers.getMapper(SearchItemMapper.class);

    @Mapping(source = "item.owner.displayName", target = "owner")
    @Mapping(source = "item.creationDate", target = "date")
    SearchItemDto toDto(com.tronina.proxy.model.SearchItem item);

    List<SearchItemDto> toDto(List<com.tronina.proxy.model.SearchItem> itemsList);

}
