package com.example.springsocial.service;

import com.example.springsocial.model.request.MinistryRequestDto;
import com.example.springsocial.model.response.ResponseDto;

public interface MinistryService {
    ResponseDto getAll();

    ResponseDto create(MinistryRequestDto requestDto);

    ResponseDto update(MinistryRequestDto requestDto);

    ResponseDto delete(Long id);
}
