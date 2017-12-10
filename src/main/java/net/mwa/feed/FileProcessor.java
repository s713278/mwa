package net.mwa.feed;

import java.util.Map;

public interface FileProcessor {

	Map<String,Object> processFile(String fileType , String filePath) ;

	Map<String,Object> processFile();
	
	Map<String,Object> processPaymentsFile(String fileType , String filePath) ;
	
	Map<String,Object> processPaymentFile();

}
