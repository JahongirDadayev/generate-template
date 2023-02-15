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
public class ResponseDto {
    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "data")
    private Object data;

    @JsonProperty(value = "success")
    private Boolean success;
}
