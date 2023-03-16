package com.tronina.proxy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SearchResult {
    @JsonProperty("items")
    private List<SearchItem> items;

    @JsonProperty("has_more")
    private boolean hasMore;
}
