package com.backend.domain.group.dto;

import com.backend.domain.group.domain.entity.Group;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupRequest {

    @NotNull
    private String name;
    @NotNull
    private String info;

    public Group toEntity() {
        return Group.builder()
                .name(name)
                .info(info)
                .build();
    }
}
