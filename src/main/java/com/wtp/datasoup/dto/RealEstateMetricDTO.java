package com.wtp.datasoup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wtp.datasoup.repository.RealEstateDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by liga on Apr, 2020.
 */
@Getter@Setter
@Accessors(chain = true)
public class RealEstateMetricDTO {
	@JsonProperty(value = "RigaApartments")
	private List<RealEstateDAO> rigaApartments;
	@JsonProperty(value = "RigaHouses")
	private List<RealEstateDAO> rigaHouses;
	@JsonProperty(value = "RigaRegionHouses")
	private List<RealEstateDAO> rigaRegionHouses;
	@JsonProperty(value = "RigaRegionApartments")
	private List<RealEstateDAO> rigaRegionApartments;
}
