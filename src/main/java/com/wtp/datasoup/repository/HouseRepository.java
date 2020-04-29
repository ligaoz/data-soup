package com.wtp.datasoup.repository;

import com.wtp.datasoup.model.House;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liga on Apr, 2020.
 */
@Repository
public interface HouseRepository extends GenericRepository<House> {

	List<House> findHouseByFloorIsGreaterThanEqual(Integer floor);

}
