package com.example.springsocial.service.serviceImpl;

import com.example.springsocial.entity.DbMinistry;
import com.example.springsocial.model.request.MinistryRequestDto;
import com.example.springsocial.model.response.MinistryResponseDto;
import com.example.springsocial.model.response.ResponseDto;
import com.example.springsocial.repository.MinistryRepository;
import com.example.springsocial.service.MinistryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MinistryServiceImpl implements MinistryService {
    private final MinistryRepository ministryRepository;

    @Override
    public ResponseDto getAll() {
        return ResponseDto.builder()
                .data(ministryRepository.findAll().stream()
                        .map(ministry -> MinistryResponseDto.builder()
                                .id(ministry.getId())
                                .name(ministry.getName())
                                .build()
                        )
                )
                .success(true)
                .build();
    }

    @Override
    public ResponseDto create(MinistryRequestDto requestDto) {
        DbMinistry ministry = DbMinistry.builder()
                .name(requestDto.getName())
                .build();
        ministryRepository.save(ministry);
        return ResponseDto.builder()
                .data(MinistryResponseDto.builder()
                        .id(ministry.getId())
                        .name(ministry.getName())
                        .build()
                )
                .success(true)
                .build();
    }

    @Override
    public ResponseDto update(MinistryRequestDto requestDto) {
        DbMinistry ministry = DbMinistry.builder()
                .id(requestDto.getId())
                .name(requestDto.getName())
                .build();
        ministryRepository.save(ministry);
        return ResponseDto.builder()
                .data(MinistryResponseDto.builder()
                        .id(ministry.getId())
                        .name(ministry.getName())
                        .build()
                )
                .success(true)
                .build();
    }

    @Override
    public ResponseDto delete(Long id) {
        ministryRepository.deleteById(id);
        return ResponseDto.builder()
                .data(null)
                .success(true)
                .build();
    }
}
