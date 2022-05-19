package com.backend.domain.trip.service;

import com.backend.domain.tag.service.TagService;
import com.backend.domain.trip.domain.entity.Trip;
import com.backend.domain.trip.domain.repository.JpaTripRepository;
import com.backend.domain.trip.dto.TripMapper;
import com.backend.domain.trip.dto.TripRequestDto;
import com.backend.domain.trip.dto.TripResponse;
import com.backend.global.error.ErrorCode;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.backend.global.error.ErrorCode.BAD_REQUEST;
import static com.backend.global.error.ErrorCode.NOT_FOUND;

@RequiredArgsConstructor
@Transactional
@Service
public class TripService {
    private final JpaTripRepository repository;

    private final TripMapper mapper;
    private final TagService tagService;

    public TripResponse save(TripRequestDto trip) {
        return Stream.of(trip)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::fromEntity)
                .findAny()
                .orElseThrow(() -> new NotFoundException(BAD_REQUEST, "태그를 저장할 수 없습니다."));
    }

    public List<TripResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }

    public TripResponse findById(long id) {
        return Stream.of(id)
                .map(repository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(mapper::fromEntity)
                .findAny()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND, "트립을 조회할 수 없습니다."));
    }

    @Transactional
    public Trip getById(long id) {
        return Stream.of(id)
                .map(repository::getById)
                .findAny()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND, "트립을 조회할 수 없습니다."));
    }

    public void deleteById(long id) {
        Stream.of(id)
                .map(this::getById)
                .forEach(repository::delete);
    }

    public TripResponse updateById(long id, TripRequestDto request) {
        return Stream.of(id)
                .map(this::getById)
                .map(trip -> trip.update(mapper.toEntity(request)))
                .map(mapper::fromEntity)
                .findAny()
                .orElseThrow(() -> new NotFoundException(BAD_REQUEST, "코드를 갱신할 수 없습니다."));
    }

    public List<TripResponse> findByTagName(String tagName) {
        return Stream.of(tagName)
                .map(tagService::findByName)
                .map(repository::findAllByTagSetContaining)
                .findAny()
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, "태그에 해당하는 트립을 찾을 수 없습니다."))
                .stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }
}
