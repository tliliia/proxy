package com.tronina.proxy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class SearchItemDto {
    @JsonProperty("question_id")
    private Double questionId;

    private Date date;

    private String title;

    private String owner;

    @JsonProperty("is_answered")
    private Boolean isAnswered;

    private String link;

}
