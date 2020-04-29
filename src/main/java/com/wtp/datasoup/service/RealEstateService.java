package com.wtp.datasoup.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.wtp.datasoup.converter.UnitConverter;
import com.wtp.datasoup.exceptions.PageNotScrapedException;
import com.wtp.datasoup.model.Apartment;
import com.wtp.datasoup.model.House;
import com.wtp.datasoup.repository.ApartmentRepository;
import com.wtp.datasoup.repository.HouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liga on Apr, 2020.
 */
@Service
@Slf4j
public class RealEstateService {

	@Autowired
	HouseRepository houseRepository;

	@Autowired
	ApartmentRepository apartmentRepository;

	@Value("${SSLV_BASE_URL}")
	private String sslvBaseUrl;

	public List<Apartment> scrapeAptUrl(String url){
		return parseApartmentElements(scrapeAllPages(getJsoupDocument(url), new Elements()));
	}

	public List<House> scrapeHouseUrl(String url) {
		return parseHouseElements(scrapeAllPages(getJsoupDocument(url), new Elements()));
	}

	private Document getJsoupDocument(String path) {
		try (
				WebClient webClient = new WebClient();
		) {
			HtmlPage myPage = webClient.getPage(path);
			return Jsoup.parse(myPage.asXml());
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new PageNotScrapedException(path);
		}
	}

	private Elements extractHouseElements(Document htmlDoc) {
		return htmlDoc.getElementsByAttributeValueStarting("id", "tr_4");
	}

	private Elements scrapeAllPages(Document htlmDoc, Elements elements) {
		String nextPagePath = htlmDoc.getElementsByClass("navi").last().attr("href");
		log.info("Scraped page link: {}", nextPagePath);
		if (nextPagePath.endsWith("sell/")) {
			elements.addAll(extractHouseElements(htlmDoc));
			return elements;
		} else {
			elements.addAll(extractHouseElements(htlmDoc));
			return scrapeAllPages(getJsoupDocument(sslvBaseUrl + nextPagePath), elements);
		}
	}

	private List<House> parseHouseElements(Elements elements) {
		List<House> realEstatesList = new ArrayList<>();
		log.info("House listings collected: {}", elements.size());
		elements.forEach(e -> {
			House house = new House().setOriginId(e.attr("id"))
					.setLocation(e.child(3).text())
					.setSource(e.child(3).getElementsByTag("a").attr("href"))
					.setArea(Float.valueOf(e.child(4).text()))
					.setFloor(Integer.valueOf(e.child(5).text()))
					.setLandArea(UnitConverter.convertArea(e.child(6).text()))
					.setPrice(UnitConverter.getNumericValue(e.child(7).text()));
			log.debug(house.toString());
			realEstatesList.add(house);
			houseRepository.save(house);
		});
		return realEstatesList;
	}

	private List<Apartment> parseApartmentElements(Elements elements) {
		List<Apartment> realEstatesList = new ArrayList<>();
		log.info("Apartment listings collected: {}", elements.size());
		elements.forEach(e -> {
			Apartment apartment = new Apartment().setOriginId(e.attr("id"))
					.setLocation(e.child(3).text())
					.setSource(e.child(3).getElementsByTag("a").attr("href"))
					.setRooms(UnitConverter.validateIntegerValue(e.child(4).text()))
					.setArea(Float.valueOf(e.child(5).text()))
					.setFloor(UnitConverter.splitFloorOnSlash(e.child(6).text()).get("floor"))
					.setTotalFloors(UnitConverter.splitFloorOnSlash(e.child(6).text()).get("totalFloors"))
					.setApartmentType(e.child(7).text())
					.setPrice(UnitConverter.getNumericValue(e.child(8).text()));
			log.debug(apartment.toString());
			realEstatesList.add(apartment);
			apartmentRepository.save(apartment);
		});
		return realEstatesList;
	}
}
