package com.wtp.datasoup.repository;

import java.time.LocalDate;

/**
 * Created by liga on Apr, 2020.
 */
public interface RealEstateDAO {
	LocalDate getDate();
	Float getAvgPrice();
	Long getCount();
	Float getAvgPricePerSqM();
}
