package com.tronina.proxy.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Owner {
    @JsonProperty("account_id")
    private Integer accountId;

    @JsonProperty("accept_rate")
    private Integer acceptRate;
    @JsonProperty("display_name")
    private String displayName;
    private String link;
    @JsonProperty("profile_image")
    private String profileImage;
    private Integer reputation;
    @JsonProperty("user_id")
    private Double userId;
    @JsonProperty("user_type")
    private String userType;
}
