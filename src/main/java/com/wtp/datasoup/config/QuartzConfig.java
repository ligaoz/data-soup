package com.wtp.datasoup.config;

import com.wtp.datasoup.job.RealEstateJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * Created by liga on Apr, 2020.
 */
@Configuration
@EnableAutoConfiguration
public class QuartzConfig {

	@Bean
	public JobDetail jobDetail() {
		return JobBuilder.newJob().ofType(RealEstateJob.class)
				.storeDurably()
				.withIdentity("RealEstate_Job_Detail")
				.withDescription("Real Estate Job service...")
				.build();
	}
	@Bean
	public Trigger trigger(JobDetail job) {
		return TriggerBuilder.newTrigger().forJob(job)
				.withIdentity("RE_Trigger")
				.withDescription("Daily Real Estate job trigger")
				.withSchedule(simpleSchedule().repeatForever().withIntervalInHours(24))
				.build();
	}
}
