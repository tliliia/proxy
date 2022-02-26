package com.tronina.proxy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchItem {
    @JsonProperty("question_id")
    private Double questionId;

    @JsonProperty("creation_date")
    private Integer creationDate;

    private String title;

    private Owner owner;

    private String link;

    @JsonProperty("is_answered")
    private Boolean isAnswered;
}