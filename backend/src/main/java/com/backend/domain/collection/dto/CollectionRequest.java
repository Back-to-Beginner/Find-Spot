package com.backend.domain.collection.dto;

import com.backend.domain.collection.domain.entity.Collection;
import com.backend.domain.post.domain.entity.Post;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CollectionRequest {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private List<Long> postIdList;

    public Collection toEntity(List<Post> postList) {
        return Collection.builder()
                .name(name)
                .description(description)
                .postList(postList)
                .build();
    }
}
