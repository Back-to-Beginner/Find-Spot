package com.backend.domain.moment.service;

import com.backend.domain.moment.domain.entity.Moment;
import com.backend.domain.moment.domain.repository.MomentRepository;
import com.backend.domain.moment.dto.MomentMapper;
import com.backend.domain.moment.dto.MomentResponseDto;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MomentService {

    private final MomentRepository repository;
    private final MomentMapper mapper;

    public List<MomentResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .parallel()
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }

    public MomentResponseDto create(Moment moment) {
        return Stream.of(moment)
                .map(repository::save)
                .map(mapper::fromEntity)
                .findAny()
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, "모먼트를 저장할 수 없습니다."));
    }

    public MomentResponseDto findByIdAsDto(Long id){
        return Stream.of(id)
                .map(this::findById)
                .map(mapper::fromEntity)
                .findAny()
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, "모먼트를 매핑할 수 없습니다."));
    }

    public Moment findById(Long id) {
        return Stream.of(id)
                .map(repository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny()
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, "모먼트를 찾을 수 없습니다."));
    }

    public Moment updateById(Long id, Moment moment) {
        return repository.save(findById(id).update(moment));
    }

    public void deleteById(Long id) {
        repository.delete(findById(id));
    }

    public List<MomentResponseDto> findAllByTripId(Long id) {
        return Stream.of(id)
                .map(repository::findAllByTripId)
                .filter(momentList -> !momentList.isEmpty())
                .findAny()
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, "해당 여행에 등록된 모먼트가 없습니다."))
                .stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }
}
