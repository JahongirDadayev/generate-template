package com.example.springsocial.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MinistryDataResponseDto {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "data")
    private String data;

    @JsonProperty(value = "ministry")
    private MinistryResponseDto ministry;
}
