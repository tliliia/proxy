package com.tronina.proxy.service;

import com.tronina.proxy.modelDto.SearchItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataService {
    List<SearchItemDto> search(String query);
}
