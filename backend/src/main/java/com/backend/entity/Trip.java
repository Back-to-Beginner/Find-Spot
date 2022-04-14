package com.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity // This tells Hibernate to make a table out of this class
public class Trip extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String text;

	// String tag; //PoC제외

	private java.sql.Date date;

	private int fullCost;


	@Override
	public String toString() {
		return "Trip{" +
				"id=" + id +
				", title='" + title + '\'' +
				", text='" + text + '\'' +
				", date=" + date +
				", fullCost=" + fullCost +
				'}';
	}
}
