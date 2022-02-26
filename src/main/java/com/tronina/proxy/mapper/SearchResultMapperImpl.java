package com.tronina.proxy.mapper;

import com.tronina.proxy.model.SearchItem;
import com.tronina.proxy.model.SearchItemDto;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class SearchResultMapperImpl implements SearchResultMapper {

    @Override
    public SearchItemDto toDto(SearchItem searchItem) {
        return SearchItemDto.builder()
                .questionId(searchItem.getQuestionId())
                .date(fromTimestamp(searchItem.getCreationDate()))
                .title(searchItem.getTitle())
                .owner(searchItem.getOwner().getDisplayName())
                .isAnswered(searchItem.getIsAnswered())
                .link(searchItem.getLink())
                .build();
    }

    private Date fromTimestamp(Integer timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return calendar.getTime();
    }
}
