package com.example.springsocial.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordRequestDto {
    @JsonProperty(value = "ministry")
    private MinistryRequestDto ministry;

    @JsonProperty(value = "ministry_data")
    private MinistryDataRequestDto ministryData;

    @JsonProperty(value = "date")
    private String date;

    @JsonProperty(value = "number")
    private String number;
}
