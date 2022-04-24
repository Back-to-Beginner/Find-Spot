package com.backend.entity;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String text;

    private String tag; //PoC제외

    private java.sql.Date begin_date;

    private java.sql.Date end_date;

    private int fullCost;

	@Override
	public String toString() {
		return "Trip{" +
				"id=" + id +
				", user=" + user +
				", title='" + title + '\'' +
				", text='" + text + '\'' +
				", tag='" + tag + '\'' +
				", begin_date=" + begin_date +
				", end_date=" + end_date +
				", fullCost=" + fullCost +
				'}';
	}
}
