package com.tronina.proxy.service;

import com.tronina.proxy.model.SearchResult;

/***
 * Сервис получение ответа на запрос из стороннего источника
 */
public interface DataService {
    SearchResult search(String query);
}
