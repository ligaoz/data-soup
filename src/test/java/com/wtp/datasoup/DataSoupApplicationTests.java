package com.wtp.datasoup;

import com.wtp.datasoup.converter.UnitConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;

class DataSoupApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(DataSoupApplicationTests.class);

	private InputStream xml = getClass().getClassLoader().getResourceAsStream("test.xml");

	@Test
	public void testAreaUnitConverter(){
		String area = "1200";
		String area2 = "1200 ha";
		String area3 = "1200 m²";
		Float expected = (float)1200.0;
		Float expectedHa = expected*10000;
		Assertions.assertEquals(expected,UnitConverter.convertArea(area));
		Assertions.assertEquals(expectedHa,UnitConverter.convertArea(area2));
		Assertions.assertEquals(expected, UnitConverter.convertArea(area3));
	}
	@Test
	public void testFloorSplitter(){
		String floor = "1.00/2";
		Map<String,Integer> map = UnitConverter.splitFloorOnSlash(floor);
		Assertions.assertEquals(Integer.valueOf(2), map.get("totalFloors"));
		Assertions.assertEquals(Integer.valueOf(1), map.get("floor"));
	}
}
