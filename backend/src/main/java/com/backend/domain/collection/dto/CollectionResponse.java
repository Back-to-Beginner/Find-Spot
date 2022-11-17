package com.backend.domain.collection.dto;

import com.backend.domain.collection.domain.entity.Collection;
import com.backend.domain.post.dto.PostResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class CollectionResponse {

    private Long id;
    private String name;
    private String description;
    private List<PostResponse> postList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CollectionResponse of(Collection collection) {
        return CollectionResponse.builder()
                .id(collection.getId())
                .name(collection.getName())
                .description(collection.getDescription())
                .postList(collection.getPostList()
                        .stream()
                        .map(PostResponse::of)
                        .collect(Collectors.toList()))
                .createdAt(collection.getCreatedAt())
                .updatedAt(collection.getUpdatedAt())
                .build();
    }
}
