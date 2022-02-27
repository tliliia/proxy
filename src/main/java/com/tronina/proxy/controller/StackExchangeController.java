package com.tronina.proxy.controller;

import com.tronina.proxy.mapper.SearchResultMapper;
import com.tronina.proxy.model.SearchItemDto;
import com.tronina.proxy.model.SearchResult;
import com.tronina.proxy.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Рест контроллер для перенаправления запроса на стороннее апи
 */
@RestController
public class StackExchangeController {
    private final DataService stackExchangeService;
    private final SearchResultMapper searchResultMapper;

    @Autowired
    public StackExchangeController(DataService stackExchangeService, SearchResultMapper mapper) {
        this.stackExchangeService = stackExchangeService;
        this.searchResultMapper = mapper;
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam String query) {
        try {
            SearchResult result = stackExchangeService.search(query);
            List<SearchItemDto> outerResult = result.getItems()
                    .stream()
                    .map(searchResultMapper::toDto)
                    .collect(Collectors.toList());
            return new ResponseEntity(outerResult, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
