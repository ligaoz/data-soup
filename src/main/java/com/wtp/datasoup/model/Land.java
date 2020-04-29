package com.wtp.datasoup.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by liga on Apr, 2020.
 */
@Getter@Setter
@Accessors(chain = true)
@Entity
@Table(name = "lands")
public class Land {

	public Land(){
		this.date = LocalDate.now();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String originId;
	private String location;
	private Float area;
	private Double price;
	private LocalDate date;
	private String source;
}
