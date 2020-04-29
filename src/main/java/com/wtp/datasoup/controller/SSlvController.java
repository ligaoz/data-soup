package com.wtp.datasoup.controller;

import com.wtp.datasoup.dto.RealEstateMetricDTO;
import com.wtp.datasoup.model.Apartment;
import com.wtp.datasoup.model.House;
import com.wtp.datasoup.repository.ApartmentRepository;
import com.wtp.datasoup.repository.RealEstateDAO;
import com.wtp.datasoup.repository.HouseRepository;
import com.wtp.datasoup.service.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liga on Apr, 2020.
 */
@RestController
@RequestMapping(value = "api")
public class SSlvController {

	@Autowired
	RealEstateService realEstateService;

	@Autowired
	HouseRepository houseRepository;

	@Autowired
	ApartmentRepository apartmentRepository;

	@Value("${SSLV_BASE_URL}")
	private String sslvBaseUrl;

	private final String sslvRealEstate = sslvBaseUrl + "/lv/real-estate/";

	@GetMapping("all")
	public void scrapeAllCategories(){
		realEstateService.scrapeHouseUrl(sslvRealEstate+"homes-summer-residences/riga/all/sell/");
		realEstateService.scrapeHouseUrl(sslvRealEstate+"homes-summer-residences/riga-region/all/sell/");
		realEstateService.scrapeAptUrl(sslvRealEstate+"flats/riga-region/all/sell/");
		realEstateService.scrapeAptUrl(sslvRealEstate+"flats/riga/all/sell/");
	}

	@GetMapping("/houses/riga")
	public List<House> getRigaHouses(){
		return realEstateService.scrapeHouseUrl(sslvBaseUrl+"/lv/real-estate/homes-summer-residences/riga/all/sell/");
	}
	@GetMapping("/houses/riga-region")
	public List<House> getRigaRegionHouses(){
		return realEstateService.scrapeHouseUrl(sslvBaseUrl+"/lv/real-estate/homes-summer-residences/riga-region/all/sell/");
	}

	@GetMapping("/apartments/riga-region")
	public List<Apartment> getRigaRegionApt(){
		return realEstateService.scrapeAptUrl(sslvBaseUrl+"/lv/real-estate/flats/riga-region/all/sell/");
	}
	@GetMapping("/apartments/riga")
	public List<Apartment> getRigaApt(){
		return realEstateService.scrapeAptUrl(sslvBaseUrl+"/lv/real-estate/flats/riga/all/sell/");
	}
	@GetMapping("houses/avg/perDay")
	public List<RealEstateDAO> getAvgHousePricePerDay(){
		return houseRepository.getMetricsByDate();
	}
	@GetMapping("houses/avg/test")
	public List<House> test(){
		return houseRepository.findHouseByFloorIsGreaterThanEqual(2);
	}
	@GetMapping("apartments/avg/perDay")
	public List<RealEstateDAO> getAvgAptPricePerDay(){
		return apartmentRepository.getMetricsByDate();
	}
	@GetMapping("realEstate/stats/")
	public RealEstateMetricDTO getStatsPerDayPerCategory(){
		return new RealEstateMetricDTO()
				.setRigaApartments(apartmentRepository.getMetricsByDateFilter("%/riga/%"))
				.setRigaRegionApartments(apartmentRepository.getMetricsByDateFilter("%/riga-region/%"))
				.setRigaHouses(houseRepository.getMetricsByDateFilter("%/riga%"))
				.setRigaRegionHouses(houseRepository.getMetricsByDateFilter("%riga-region%"));
	}
}
