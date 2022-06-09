package com.backend.domain.trip.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
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
    private LocalDate beginDate;

    @Past
    @NotNull
    private LocalDate endDate;

    @NotNull
    private int fullCost;

    private List<String> tagNameList;

}
