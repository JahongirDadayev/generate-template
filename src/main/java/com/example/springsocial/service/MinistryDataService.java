package com.example.springsocial.service;

import com.example.springsocial.model.request.MinistryDataRequestDto;
import com.example.springsocial.model.response.ResponseDto;

public interface MinistryDataService {
    ResponseDto getViaMinistryId(Long ministryId);

    ResponseDto create(MinistryDataRequestDto requestDto);

    ResponseDto update(MinistryDataRequestDto requestDto);

    ResponseDto delete(Long id);
}
