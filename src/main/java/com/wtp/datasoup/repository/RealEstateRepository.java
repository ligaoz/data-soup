package com.wtp.datasoup.repository;

import com.wtp.datasoup.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liga on Apr, 2020.
 */
@Repository
public interface RealEstateRepository extends JpaRepository<House, Long> {
}
