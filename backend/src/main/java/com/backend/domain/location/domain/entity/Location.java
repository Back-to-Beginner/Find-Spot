package com.backend.domain.location.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.backend.domain.trip.domain.entity.Trip;
import com.backend.global.BaseTimeEntity;
import lombok.*;

@Getter
@RequiredArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class Location extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "trip_id")
	private Trip trip;

	private String region;

	private String name;

	private String review;

	private int cost = 0;

	private double latitude;
	private double longitude;

	private boolean is_deleted = false;

	public void deleteLocation() {
		this.is_deleted = true;
	}

	@Builder
	public Location(Trip trip, String region, String name, String review, int cost, double latitude, double longitude) {
		this.trip = trip;
		this.region =region;
		this.name = name;
		this.review = review;
		this.cost = cost;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
