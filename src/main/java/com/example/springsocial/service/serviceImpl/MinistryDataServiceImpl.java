package com.example.springsocial.service.serviceImpl;

import com.example.springsocial.entity.Ministry;
import com.example.springsocial.entity.MinistryData;
import com.example.springsocial.model.request.MinistryDataRequestDto;
import com.example.springsocial.model.response.MinistryDataResponseDto;
import com.example.springsocial.model.response.MinistryResponseDto;
import com.example.springsocial.model.response.ResponseDto;
import com.example.springsocial.repository.MinistryDataRepository;
import com.example.springsocial.service.MinistryDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MinistryDataServiceImpl implements MinistryDataService {
    private final MinistryDataRepository ministryDataRepository;

    @Override
    public ResponseDto getViaMinistryId(Long ministryId) {
        return ResponseDto.builder()
                .data(ministryDataRepository.findByMinistry_Id(ministryId).stream()
                        .map(ministryData -> MinistryDataResponseDto.builder()
                                .id(ministryData.getId())
                                .data(ministryData.getData())
                                .ministry(MinistryResponseDto.builder()
                                        .id(ministryData.getMinistry().getId())
                                        .name(ministryData.getMinistry().getName())
                                        .build()
                                )
                                .build()
                        )
                )
                .success(true)
                .build();
    }

    @Override
    public ResponseDto create(MinistryDataRequestDto requestDto) {
        MinistryData ministryData = MinistryData.builder()
                .data(requestDto.getData())
                .ministry(Ministry.builder()
                        .id(requestDto.getMinistry().getId())
                        .build()
                )
                .build();
        ministryDataRepository.save(ministryData);
        return ResponseDto.builder()
                .data(MinistryDataResponseDto.builder()
                        .id(ministryData.getId())
                        .data(ministryData.getData())
                        .ministry(MinistryResponseDto.builder()
                                .id(ministryData.getMinistry().getId())
                                .build()
                        )
                        .build()
                )
                .success(true)
                .build();
    }

    @Override
    public ResponseDto update(MinistryDataRequestDto requestDto) {
        MinistryData ministryData = MinistryData.builder()
                .id(requestDto.getId())
                .data(requestDto.getData())
                .ministry(Ministry.builder()
                        .id(requestDto.getMinistry().getId())
                        .build()
                )
                .build();
        ministryDataRepository.save(ministryData);
        return ResponseDto.builder()
                .data(MinistryDataResponseDto.builder()
                        .id(ministryData.getId())
                        .data(ministryData.getData())
                        .ministry(MinistryResponseDto.builder()
                                .id(ministryData.getMinistry().getId())
                                .build()
                        )
                        .build()
                )
                .success(true)
                .build();
    }

    @Override
    public ResponseDto delete(Long id) {
        ministryDataRepository.deleteById(id);
        return ResponseDto.builder()
                .data(null)
                .success(true)
                .build();
    }
}
