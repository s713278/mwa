package net.mwa.feed;

import java.util.List;
import java.util.Map;

public interface FeedFileParser<T> {

	String FIRSTNAME = "FIRSTNAME";
	String LASTNAME = "LASTNAME";
	String DEFAULT_MOBILE_NO = "0000000000";

	String CSV_SEPARATOR = ",";

	List<T> parse(String inputFile);

	Map<String, Object> updateDataInDB(List<T> list);

	Map<String, Object> processFile();
}