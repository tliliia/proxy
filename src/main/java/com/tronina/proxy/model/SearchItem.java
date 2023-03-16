package com.tronina.proxy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchItem {

    private List<String> tags;
    private Owner owner;

    @JsonProperty("answer_count")
    private Integer answerCount;
    @JsonProperty("content_license")
    private String contentLicense;
    @JsonProperty("creation_date")
    private Integer creationDate;// unix epoch time
    @JsonProperty("is_answered")
    private Boolean isAnswered;
    @JsonProperty("last_activity_date")
    private Double lastActivityDate;
    private String link;
    @JsonProperty("question_id")
    private Double questionId;
    private Double score;
    private String title;
    @JsonProperty("view_count")
    private Double viewCount;

    @JsonProperty("accepted_answer_id")
    private Integer acceptedAnswerId;
    @JsonProperty("bounty_amount")
    private Integer bountyAmount;
    @JsonProperty("bounty_closes_date")
    private Double bountyClosesDate;
    @JsonProperty("closed_date")
    private Double closedDate;
    @JsonProperty("community_owned_date")
    private Double communityOwnedDate;
    @JsonProperty("last_edit_date")
    private Double lastEditDate;
    @JsonProperty("locked_date")
    private Double lockedDate;
    @JsonProperty("migrated_from")
    private String migratedFrom;
    @JsonProperty("protected_date")
    private String protectedDate;

}
