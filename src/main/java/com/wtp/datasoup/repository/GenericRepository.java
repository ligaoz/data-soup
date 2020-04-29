package com.wtp.datasoup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by liga on Apr, 2020.
 */
@NoRepositoryBean
public interface GenericRepository<T> extends JpaRepository<T, Long> {
	@Query(value = "select a.date as date, avg(a.price) as avgPrice, COUNT(a.date) as count, (sum(a.price)/sum(a.area)) as avgPricePerSqM from #{#entityName} a group by a.date")
	List<RealEstateDAO> getMetricsByDate();

	@Query(value = "select a.date as date, avg(a.price) as avgPrice, COUNT(a.date) as count, (sum(a.price)/sum(a.area)) as avgPricePerSqM " +
			"from #{#entityName} a where a.source like :filter group by a.date")
	List<RealEstateDAO> getMetricsByDateFilter(@Param("filter") String filter);
}
