package net.mwa.feed;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import net.mwa.common.AddOfflinePaymentRequest;
import net.mwa.common.CategoryTypes;
import net.mwa.dao.FeeDao;
import net.mwa.dao.MemberDao;
import net.mwa.dao.PaymentDao;
import net.mwa.service.MemberServiceHelper;
import net.mwa.vo.CashPaymentVO;
import net.mwa.vo.ChequePaymentVO;
import net.mwa.vo.FeeVO;
import net.mwa.vo.OnlinePaymentVO;
import net.mwa.vo.UserDetailsVO;

@Component
@Qualifier("MWADataFeedProcessor")
public class MWADataFeedProcessor implements FeedFileParser<AddOfflinePaymentRequest> {

	private static final String DATE_FORMAT = "dd-MMM-y";

	private static final Logger LOGGER = Logger.getLogger(MWADataFeedProcessor.class.getName());

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private FeeDao feeDao;

	/**
	 * <p>
	 * 
	 * </p>
	 */
	@Override
	public List<AddOfflinePaymentRequest> parse(String filePath) {
		List<AddOfflinePaymentRequest> list = new ArrayList<AddOfflinePaymentRequest>();
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(filePath));
			int i = 0;
			AddOfflinePaymentRequest dataFeedLineVO = new AddOfflinePaymentRequest();
			while ((line = br.readLine()) != null) {
				if (i == 0) {
					i++;
					continue;
				}
				// use comma as separator
				String[] row = line.split(CSV_SEPARATOR);
				System.out.println(Arrays.toString(row));
				//#RECEIPT	#DATE	#FNAME	#LNAME	#MNAME	#PLOT	#CATEGORY	RoadNo	#FLATS	#APARTMENTNAME	#MOBILE	#AMOUNT	#CHEQUE	#MODE	#FEEID
				String receiptNo = row[0];
				String paidDate = row[1];
				String fName = row[2];
				String lName = row[3];
				String mName = row[4];
				String paidBy = row[2]+" "+row[4]+" "+row[3];
				String plotNo = row[5];
				String category = row[6];
				String roadNo = row[7];
				String noOfFamilies = row[8];
				String apartmentName = row[9];
				String mobileNo = row[10];
				String paidAmount = row[11];
				String chequeNo = row[12];
				String modeOfPayment = row[13];
				String feeId = row[14];
				String note = null;
				String fromBank = null;
				String transactionId = null;

				if (plotNo != null && plotNo.trim().length() != 0) {
					dataFeedLineVO = new AddOfflinePaymentRequest();
					dataFeedLineVO.setPlotNo(plotNo.trim());
					dataFeedLineVO.setReceiptNo(receiptNo.trim());
					Date paidDate_v;
					try {
						paidDate_v = new SimpleDateFormat(DATE_FORMAT).parse(paidDate);
						dataFeedLineVO.setPaidDate(paidDate_v);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					dataFeedLineVO.setPaidBy(paidBy.trim());
					dataFeedLineVO.setCategoryId(1);
					if (mobileNo != null && mobileNo.trim().length() == 10) {
						dataFeedLineVO.setMobileNo(mobileNo.trim());
					}else{
						dataFeedLineVO.setMobileNo(DEFAULT_MOBILE_NO);
					}
					try {
						dataFeedLineVO.setAmount(Double.parseDouble(paidAmount));
					} catch (NumberFormatException e) {
						dataFeedLineVO.setAmount(0d);
					}
					if(modeOfPayment!=null){
						dataFeedLineVO.setPaymentMode(modeOfPayment.trim());	
					}
					dataFeedLineVO.setFeeId(Long.parseLong(feeId));
					if(chequeNo!=null){
						dataFeedLineVO.setChequeNo(chequeNo.trim());
					}
					try {
						dataFeedLineVO.setNoOfFamilies(Integer.parseInt(noOfFamilies));
					} catch (NumberFormatException e) {
						dataFeedLineVO.setNoOfFamilies(1);
					}
					if(category.equals(CategoryTypes.INDEPENDENT)){
						dataFeedLineVO.setCategoryId(1l);
					}else if(category.equals(CategoryTypes.APARTMENT)){
						dataFeedLineVO.setCategoryId(2l);
					}else if(category.equals(CategoryTypes.COMMERCIAL)){
						dataFeedLineVO.setCategoryId(3l);
					}
					dataFeedLineVO.setRoadNo(roadNo);
					dataFeedLineVO.setFirstName(fName);
					dataFeedLineVO.setLastName(lName);
					dataFeedLineVO.setMiddleName(mName);
					dataFeedLineVO.setApartmentName(apartmentName);
					dataFeedLineVO.setBusinessName(apartmentName);
					dataFeedLineVO.setTransactionId(transactionId);
				}
				list.add(dataFeedLineVO);
				dataFeedLineVO = null;
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
		return list;
	}

	@Autowired
	private MemberServiceHelper memberServiceHelper;
	
	
	
	public MemberServiceHelper getMemberServiceHelper() {
		return memberServiceHelper;
	}

	public void setMemberServiceHelper(MemberServiceHelper memberServiceHelper) {
		this.memberServiceHelper = memberServiceHelper;
	}

	@Override
	public Map<String, Object> updateDataInDB(List<AddOfflinePaymentRequest> list) {
		Map<String, Object> result = new ConcurrentHashMap<String, Object>();
		List<String> newlyCreatedMemmbers = new ArrayList<String>();
		List<String> invalidFeeIds = new ArrayList<String>();
		List<String> duplicateReceiptNos = new ArrayList<String>();
		FeeVO feeVO = null;
		UserDetailsVO userDetails = null;
		int cashPayments = 0;
		int chequePayments = 0;
		int onlinePayments = 0;
		int failedRecords = 0;
		Long onlineReferenceId = 0l;
		int totalSuccessRecords=0;
		int totalRecords = list.size();
		for (AddOfflinePaymentRequest request : list) {
			try {
				userDetails = getMemberServiceHelper().findORCreateMember(request);
				feeVO = feeDao.findAny(request.getFeeId());
				onlineReferenceId = getMemberServiceHelper().addPayment(request, userDetails, feeVO);
				totalSuccessRecords++;
			} catch (InvalidInputException e) {
				failedRecords++;
				if(LOGGER.isErrorEnabled()){
					LOGGER.error("InvalidInputException occured while updateDataInDB () for "+request);
				}
			}
		}
		result.put("TOTAL_RECORDS", totalRecords);
		result.put("TOTAL_SUCCESS_RECORDS", totalSuccessRecords);
		result.put("FAILED_RECORDS", failedRecords);
		/*result.put("CHEQUE_PAYMENTS", chequePayments);
		result.put("CASH_PAYMENTS", cashPayments);
		result.put("ONLINE_PAYMENTS", onlinePayments);
		result.put("DUPLICATE_RECEIPT_NOS", duplicateReceiptNos);*/
		return result;

	}

	@Override
	public Map<String, Object> processFile() {
		List<AddOfflinePaymentRequest> list = parse("C:\\SwamyAll\\swamy\\Swamy1\\Mayurinagar\\11_CC Cameras\\Data\\test.csv");
		return updateDataInDB(list);
	}

}
