package com.tronina.proxy.modelDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Question object")
public class SearchItemDto {

    @JsonProperty("question_id")
    private Double questionId;

    @Schema(description = "created date")
    private Date date;

    @Schema(description = "title")
    private String title;

    @Schema(description = "author")
    private String owner;

    @JsonProperty("is_answered")
    @Schema(description = "flag specifies the question has an answer")
    private Boolean isAnswered;

    @Schema(description = "link to original question")
    private String link;

}
