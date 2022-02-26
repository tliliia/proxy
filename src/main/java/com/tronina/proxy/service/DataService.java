package com.tronina.proxy.service;

import com.tronina.proxy.model.SearchResult;

public interface DataService {
    SearchResult search(String query);
}
