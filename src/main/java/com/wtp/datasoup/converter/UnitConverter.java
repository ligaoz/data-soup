package com.wtp.datasoup.converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liga on Apr, 2020.
 */
public class UnitConverter {
	private UnitConverter(){}

	public static Float convertArea(String value){
		String[] valueAndUnit = splitStringBySpace(value);
		String unit;
		if(valueAndUnit.length == 2){
			unit = valueAndUnit[1];
		}else {
			unit = "m²";
		}
		Float numericValue = getNumericValue(valueAndUnit);
		if(unit.equals("m²")){
			return numericValue;
		}else {
			return numericValue*10000;
		}
	}
	public static Integer validateIntegerValue(String value){
		try{
			if(value.contains(".")){
				return new Float(value).intValue();
			}else {
				return Integer.valueOf(value);
			}
		}catch (NumberFormatException e){
			return 0;
		}
	}
	public static Map<String, Integer> splitFloorOnSlash(String floor){
		String[] str = floor.split("/");
		Map<String, Integer> map = new HashMap<>();
		map.put("floor", validateIntegerValue(str[0].trim()));
		map.put("totalFloors", validateIntegerValue(str[1].trim()));
		return map;
	}

	private static String[] splitStringBySpace(String value){
		return value.split("\\s+");
	}
	public static Float getNumericValue(String[] value){
		return Float.parseFloat(value[0]);
	}
	public static Float getNumericValue(String value){
		return Float.parseFloat(removeCommas(splitStringBySpace(value)[0]));
	}
	private static String removeCommas(String value){
		return value.replace(",","");
	}
}
