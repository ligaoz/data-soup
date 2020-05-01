package com.wtp.datasoup.job;

import com.wtp.datasoup.service.RealEstateService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liga on Apr, 2020.
 */
@Component
public class RealEstateJob implements Job {

	@Autowired
	RealEstateService realEstateService;

	private final String housesRigaURL = "homes-summer-residences/riga/all/sell/";
	private final String apartmentsRigaUrl = "flats/riga/all/sell/";
	private final String housesRigaRegionUrl = "homes-summer-residences/riga-region/all/sell/";
	private final String apartmentsRigaRegionUrl = "flats/riga-region/all/sell/";

	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		realEstateService.scrapeHouseUrl(housesRigaURL);
		realEstateService.scrapeHouseUrl(apartmentsRigaUrl);
		realEstateService.scrapeAptUrl(housesRigaRegionUrl);
		realEstateService.scrapeAptUrl(apartmentsRigaRegionUrl);
	}
}
