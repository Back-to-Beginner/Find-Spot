package com.backend.domain.location.domain.entity;

import javax.persistence.*;

import com.backend.global.domain.basetime.domain.entity.BaseTimeEntity;
import lombok.*;

@Getter
@RequiredArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class Location extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String region;

	private String name;

	private double latitude;
	private double longitude;

	@Column(name = "is_deleted")
	private boolean isDeleted = false;

	public void deleteLocation() {
		this.isDeleted = true;
	}

	@Builder
	public Location(String region, String name, int cost, double latitude, double longitude) {
		this.region =region;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
