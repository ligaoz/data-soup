package com.wtp.datasoup.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by liga on Apr, 2020.
 */
@Accessors(chain = true)
@Setter@Getter
@Table(name = "houses", schema = "datasoup")
@Entity
public class House implements Serializable {

	public House(){
		this.date = LocalDate.now();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String originId;
	private String location;
	private Integer floor;
	private Float landArea;
	private Float area;
	private Float price;
	private LocalDate date;
	private String source;

	@Override
	public String toString() {
		return "House{" +
				"location='" + location + '\'' +
				", floor=" + floor +
				", landArea=" + landArea +
				", area=" + area +
				", price=" + price +
				'}';
	}
}
