package com.example.springsocial.service.serviceImpl;

import com.example.springsocial.entity.DbMinistry;
import com.example.springsocial.entity.DbMinistryData;
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
                .data(ministryDataRepository.findByDbMinistry_Id(ministryId).stream()
                        .map(ministryData -> MinistryDataResponseDto.builder()
                                .id(ministryData.getId())
                                .data(ministryData.getData())
                                .ministry(MinistryResponseDto.builder()
                                        .id(ministryData.getDbMinistry().getId())
                                        .name(ministryData.getDbMinistry().getName())
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
        DbMinistryData ministryData = DbMinistryData.builder()
                .data(requestDto.getData())
                .dbMinistry(DbMinistry.builder()
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
                                .id(ministryData.getDbMinistry().getId())
                                .build()
                        )
                        .build()
                )
                .success(true)
                .build();
    }

    @Override
    public ResponseDto update(MinistryDataRequestDto requestDto) {
        DbMinistryData ministryData = DbMinistryData.builder()
                .id(requestDto.getId())
                .data(requestDto.getData())
                .dbMinistry(DbMinistry.builder()
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
                                .id(ministryData.getDbMinistry().getId())
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
