package com.example.springsocial.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinistryDataRequestDto {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "data")
    private String data;

    @JsonProperty(value = "ministry")
    private MinistryRequestDto ministry;
}
