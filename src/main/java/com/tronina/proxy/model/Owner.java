package com.tronina.proxy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    private Double reputation;

    @JsonProperty("account_id")
    private Double accountId;

    @JsonProperty("user_id")
    private Double userId;

    @JsonProperty("user_type")
    private String userType;

    @JsonProperty("profile_image")
    private String profileImage;

    @JsonProperty("display_name")
    private String displayName;

    private String link;
}
