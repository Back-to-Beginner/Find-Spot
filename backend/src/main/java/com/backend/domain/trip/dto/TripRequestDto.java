package com.backend.domain.trip.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Date;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class TripRequestDto {
    @NotNull
    private Long userId;

    @NotNull
    private String title;

    @Past
    @NotNull
    private Date beginDate;

    @Past
    @NotNull
    private Date endDate;

    @NotNull
    private int fullCost;

    private List<String> tagNameList;

}
