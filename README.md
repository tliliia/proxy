##Rest API
для поиска строки в заголовке вопроса в stackoverflow. Результат содержит дату вопроса, его название, кто выложил, есть ли ответ, ссылку на исходный вопрос в stackoverflow.

Пример запроса: localhost:8080/search?query=java


##Структура проекта
StackExchangeController - Рест контроллер для перенаправления запроса на стороннее апи

DataService - интерфейс сервиса для получение ответа на запрос из стороннего источника

DataServiceStackExchangeImpl - реализация DataService для взаимодецствия с api.stackexchange.com

SearchResultMapper маппер для преобразования объектов из api.stackexchange в необходимую модель ДТО

