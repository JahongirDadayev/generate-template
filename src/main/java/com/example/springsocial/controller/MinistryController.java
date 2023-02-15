package com.example.springsocial.controller;

import com.example.springsocial.model.request.MinistryRequestDto;
import com.example.springsocial.model.response.ResponseDto;
import com.example.springsocial.service.MinistryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/ministry")
@RequiredArgsConstructor
public class MinistryController {
    private final MinistryService ministryService;

    @GetMapping
    public ResponseEntity<ResponseDto> getAll() {
        ResponseDto responseDto = ministryService.getAll();
        return (responseDto.getSuccess()) ? ResponseEntity.status(HttpStatus.OK).body(responseDto) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody MinistryRequestDto requestDto){
        ResponseDto responseDto = ministryService.create(requestDto);
        return (responseDto.getSuccess())?ResponseEntity.status(HttpStatus.CREATED).body(responseDto):ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody MinistryRequestDto requestDto){
        ResponseDto responseDto = ministryService.update(requestDto);
        return (responseDto.getSuccess())?ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto):ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> delete(@RequestParam(name = "id") Long id) {
        ResponseDto responseDto = ministryService.delete(id);
        return (responseDto.getSuccess()) ? ResponseEntity.status(HttpStatus.OK).body(responseDto) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
}
