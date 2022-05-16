package com.backend.domain.tag.service;

import com.backend.domain.tag.domain.entity.Tag;
import com.backend.domain.tag.domain.repository.JpaTagRepository;
import com.backend.domain.tag.dto.TagListRequestDto;
import com.backend.domain.tag.dto.TagMapper;
import com.backend.domain.tag.dto.TagRequestDto;
import com.backend.domain.tag.dto.TagResponseDto;
import com.backend.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.backend.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class TagService {

    private final JpaTagRepository repository;

    private final TagMapper mapper;

    public TagResponseDto save(TagRequestDto request) {
        return Stream.of(request)
                .map(mapper::toEntity)
                .map(repository::save)
                .map(mapper::fromEntity)
                .findAny()
                .orElseThrow(() -> new NotFoundException(BAD_REQUEST, "태그를 저장할 수 없습니다."));
    }

    public List<TagResponseDto> saveList(TagListRequestDto listRequest) {
        return listRequest.getNameList()
                .stream()
                .filter(name -> !repository.existsByName(name))
                .map(Tag::new)
                .map(repository::save)
                .map(mapper::fromEntity)
                .collect(Collectors.toList());

    }

    public List<TagResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }

    public TagResponseDto findById(long id) {
        return Stream.of(id)
                .map(repository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(mapper::fromEntity)
                .findAny()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND, "태그를 조회할 수 없습니다."));
    }

    @Transactional
    public Tag getById(long id) {
        return Stream.of(id)
                .map(repository::getById)
                .findAny()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND, "태그를 조회할 수 없습니다."));
    }

    public TagResponseDto updateById(long id, TagRequestDto request) {
        return Stream.of(id)
                .map(this::getById)
                .map(tag -> tag.update(request))
                .map(mapper::fromEntity)
                .findAny()
                .orElseThrow(() -> new NotFoundException(BAD_REQUEST, "태그를 갱신할 수 없습니다."));
    }

    public void deleteById(long id) {
        Stream.of(id)
                .map(this::getById)
                .forEach(repository::delete);
    }

    public List<TagResponseDto> findByNameLike(String name) {
        return Stream.of(name)
                .map(repository::findAllByNameLike)
                .findAny()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND, "태그를 조회할 수 없습니다."))
                .stream()
                .map(mapper::fromEntity)
                .collect(Collectors.toList());
    }

    public Tag findByName(String name) {
        return Stream.of(name)
                .map(repository::findByName)
                .findAny()
                .orElseThrow(() -> new NotFoundException(NOT_FOUND, "태그를 조회할 수 없습니다."));
    }

}
