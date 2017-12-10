package net.mwa.feed;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.mwa.common.utils.SupportedFileTypes;
import net.mwa.vo.MemberDetailsVO;

@Service
public class CSVFileProcessor extends AbstractFileProcessor{

	private static final String CSV_FILE = "C:/SwamyAll/swamy/Swamy1/Mayurinagar/11_CC Cameras/Data/CC-Cameras.csv";
	
private static final String PAYMENT_CSV_FILE = "C:/SwamyAll/swamy/Swamy1/Mayurinagar/11_CC Cameras/Data/CC-Cameras_Payments.csv";
	
	@Override
	public Map<String,Object> processPaymentFile() {
		return processPaymentsFile(SupportedFileTypes.CSV_FILE_TYPE, PAYMENT_CSV_FILE);
	}
	
	public static void main11(String[] args) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(CSV_FILE));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                String data = MessageFormat.format("Name : {0},Plot :{1},Category:{2}, NoOfFlats:{3},MobileNo:{4}", new Object[]{row[2],row[3],row[4],row[6],row[8]});
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

	
	public Map<String,Object> processFile() {
		return processFile(SupportedFileTypes.CSV_FILE_TYPE, CSV_FILE);
	}

}
