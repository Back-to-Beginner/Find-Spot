package com.backend.domain.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CardResponse {

    private Long id;

    private Character type;

    private String content;

    private Long parentPostId;

    private Long userId;

    private String userName;

    private String imagePath;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @QueryProjection
    public CardResponse(
            Long id,
            Character type,
            String content,
            Long parentPostId,
            Long userId,
            String userName,
            String imagePath,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.parentPostId = parentPostId;
        this.userId = userId;
        this.userName = userName;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
