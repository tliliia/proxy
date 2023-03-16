package com.tronina.proxy.controller;

import com.tronina.proxy.modelDto.SearchItemDto;
import com.tronina.proxy.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StackExchangeController implements ControllerApi {
    private final DataService dataLoader;

    @Override
    public ResponseEntity<List<SearchItemDto>> search(String query) {
        List<SearchItemDto> result = dataLoader.search(query);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

}
