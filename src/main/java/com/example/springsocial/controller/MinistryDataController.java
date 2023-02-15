package com.example.springsocial.controller;

import com.example.springsocial.model.request.MinistryDataRequestDto;
import com.example.springsocial.model.response.ResponseDto;
import com.example.springsocial.service.MinistryDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/ministry-data")
@RequiredArgsConstructor
public class MinistryDataController {
    private final MinistryDataService ministryDataService;

    @GetMapping
    public ResponseEntity<ResponseDto> getViaMinistryId(@RequestParam(name = "ministry-id") Long ministryId) {
        ResponseDto responseDto = ministryDataService.getViaMinistryId(ministryId);
        return (responseDto.getSuccess()) ? ResponseEntity.status(HttpStatus.OK).body(responseDto) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody MinistryDataRequestDto requestDto) {
        ResponseDto responseDto = ministryDataService.create(requestDto);
        return (responseDto.getSuccess()) ? ResponseEntity.status(HttpStatus.CREATED).body(responseDto) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody MinistryDataRequestDto requestDto) {
        ResponseDto responseDto = ministryDataService.update(requestDto);
        return (responseDto.getSuccess()) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> delete(@RequestParam(name = "id") Long id) {
        ResponseDto responseDto = ministryDataService.delete(id);
        return (responseDto.getSuccess()) ? ResponseEntity.status(HttpStatus.OK).body(responseDto) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
}
