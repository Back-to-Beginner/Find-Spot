package com.backend.domain.image.domain.entity;

import javax.persistence.*;

import com.backend.global.BaseTimeEntity;
import com.backend.domain.location.domain.entity.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Builder
@Getter
@RequiredArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class Image extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(length = 300)
    private String path;

    private boolean is_deleted = false;

    public void deleteImage() {
        this.is_deleted = true;
    }

    @Builder
    public Image(Location location, String path) {
        this.location = location;
        this.path = path;
    }
}
