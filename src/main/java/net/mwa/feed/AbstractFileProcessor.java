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
import org.springframework.beans.factory.annotation.Autowired;

import net.mwa.common.CategoryTypes;
import net.mwa.common.PaymentTypes;
import net.mwa.dao.FeeDao;
import net.mwa.dao.MemberDao;
import net.mwa.dao.PaymentDao;
import net.mwa.vo.ApartmentVO;
import net.mwa.vo.CashPaymentVO;
import net.mwa.vo.CategoryVO;
import net.mwa.vo.ChequePaymentVO;
import net.mwa.vo.CommercialVO;
import net.mwa.vo.FeeVO;
import net.mwa.vo.MemberDetailsVO;

public abstract class AbstractFileProcessor implements FileProcessor {

	private static final Logger LOGGER = Logger.getLogger(AbstractFileProcessor.class.getName());
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private FeeDao feeDao;
	

	@Override
	public Map<String,Object> processFile(String fileType, String filePath) {
		List<MemberDetailsVO> list = new ArrayList<>();
		if (fileType == null || fileType.trim().length() == 0) {
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Invalid file type,IT should be either CSV/EXCEL");
			}
			System.out.println("Invalid file type,IT should be either CSV/EXCEL");
			return null;
		}
		if (filePath == null || filePath.trim().length() == 0) {
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Invalid filePath,It should be valid file path");
			}
			System.out.println("Invalid filePath,It should be valid file path");
			return null;
		}
		if ("CSV".equalsIgnoreCase(fileType)) {
			readMembersDataFromFile(filePath, list);
		} else if ("EXCEL".equalsIgnoreCase(fileType)) {

		} else {
			System.err.println("Invalid filetype mentioned and its is " + fileType);
			return null;
		}
		return processMembersData(list);
	}

	private void readMembersDataFromFile(String filePath, List<MemberDetailsVO> list) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(filePath));
			CategoryVO categoryVO = null;
			String data = null;
			int i=0;
			while ((line = br.readLine()) != null) {
				if(i==0){
					i++;
					continue;
				}
				// use comma as separator
				String[] row = line.split(cvsSplitBy);
				MemberDetailsVO member = new MemberDetailsVO();
				/*data = MessageFormat.format(
						"Name : {0},Plot :{1},Category:{2}, NoOfFlats:{3},MobileNo:{4},Road :{5}",
						new Object[] { row[2], row[3], row[4], row[6], row[8], row[5] });*/
				String[] names = row[2].split(" ");
				System.out.println("########################### Names :: "+Arrays.toString(names));
				if(names!=null && names.length==1){
					member.setOwnerFirstName(names[0]);
					member.setOwnerLastName(names[0]);
				}else if(names!=null && names.length==2){
					member.setOwnerFirstName(names[0]);
					member.setOwnerLastName(names[1]);
				}else if(names!=null && names.length==3){
					member.setOwnerFirstName(names[0]);
					member.setMiddleName(names[1]);
					member.setOwnerLastName(names[2]);
				}
				member.setPlotNo(row[3]);
				if (row[4] != null) {
					String category = row[4];
					categoryVO = new CategoryVO();
					if (CategoryTypes.INDEPENDENT.equalsIgnoreCase(category.trim())) {
						categoryVO.setId(1l);
						categoryVO.setCode(CategoryTypes.INDEPENDENT);
					} else if (CategoryTypes.APARTMENT.equalsIgnoreCase(category.trim())) {
						member.setApartmentName(row[2]);
						categoryVO.setId(2l);
						categoryVO.setCode(CategoryTypes.APARTMENT);
					} else if (CategoryTypes.COMMERCIAL.equalsIgnoreCase(category.trim())) {
						categoryVO.setId(3l);
						categoryVO.setCode(CategoryTypes.COMMERCIAL);
					}
					member.setCategory(categoryVO);
				}
				try {
					member.setNoOfFamilies(Integer.valueOf(row[6]));
				} catch (NumberFormatException e) {
					member.setNoOfFamilies(Integer.valueOf(1));
				}
				member.setRoadNo(row[5]);
				if (row[8] != null && row[8].trim().length() == 10) {
					member.setMobileNo(row[8]);
				}
				list.add(member);
				member = null;
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

	/**
	 * <p>
	 * This method process the list of data and persist into the data base.
	 * </p>
	 * 
	 * @param membersData
	 * @return
	 */
	private Map<String, Object> processMembersData(List<MemberDetailsVO> membersData) {
		Map<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
		List<MemberDetailsVO> existedAccounts = new ArrayList<MemberDetailsVO>();
		for (MemberDetailsVO member : membersData) {
			String plotNo = member.getPlotNo().trim();
			MemberDetailsVO existingRecord = memberDao.findByPlotNo(plotNo);
			if(existingRecord != null){
				existedAccounts.add(existingRecord);
				System.out.println(existingRecord.getOwnerFirstName() +"/"+existingRecord.getMobileNo()+" Record already existed for "+plotNo);
			}else{
				long categoryId = member.getCategory().getId();
				if (CategoryTypes.INDEPENDENT_ID == categoryId) {
					memberDao.save(member);
				} else if (CategoryTypes.APARTMENT_ID == categoryId) {
					ApartmentVO apartmentVO = new ApartmentVO();
					apartmentVO.setOwnerFirstName(member.getOwnerFirstName());
					apartmentVO.setOwnerLastName(member.getOwnerLastName());
					apartmentVO.setAprtmentName(member.getApartmentName());
					apartmentVO.setPlotNo(member.getPlotNo());
					apartmentVO.setRoadNo(member.getRoadNo());
					apartmentVO.setNoOfFamilies(member.getNoOfFamilies());
					apartmentVO.setCategory(member.getCategory());
					apartmentVO.setPresedentMobileNo(member.getMobileNo());
					memberDao.save(apartmentVO);
				} else if (CategoryTypes.COMMERCIAL_ID == categoryId) {
					CommercialVO commercialVO = new CommercialVO();
					commercialVO.setOwnerFirstName(member.getOwnerFirstName());
					commercialVO.setOwnerLastName(member.getOwnerLastName());
					commercialVO.setBusinessName(member.getOwnerFirstName());
					commercialVO.setCategory(member.getCategory());
					commercialVO.setPlotNo(member.getPlotNo());
					memberDao.save(commercialVO);
				}
			}
		}
		resultMap.put("DUPLICATE_RECORDS", existedAccounts);
		return resultMap;
	}


	@Override
	public Map<String, Object> processPaymentsFile(String fileType, String filePath) {
		List<PaymentLineVO> list = new ArrayList<PaymentLineVO>();
		readPaymentsDataFromFile(filePath,list);
		Map<String, Object> result = savePaymentDetails(list);
		return result;
	}
	
	
	private Map<String, Object> savePaymentDetails(List<PaymentLineVO> list){
		Map<String, Object> result= new ConcurrentHashMap<String, Object>();
		CashPaymentVO cashPaymentVO =null;
		ChequePaymentVO chequePaymentVO =null;
		List<String> invalidPlotNos= new ArrayList<String>();
		List<String> invalidFeeIds= new ArrayList<String>();
		FeeVO feeVO =null;
		MemberDetailsVO member=null;
		int totalCashPayments =0;
		int totalChequePayments =0;
		for(PaymentLineVO paymentLineVO : list){
				String plotNo = paymentLineVO.getPlotNo();
				member = memberDao.findByPlotNo(plotNo);
				if(member == null){
					if(LOGGER.isDebugEnabled()){
						LOGGER.debug("No member record found for #plot no :"+plotNo);
					}
					invalidPlotNos.add("No member record found for #plot no :"+plotNo);
					continue;
				}
				feeVO = feeDao.findAny(paymentLineVO.getFeeId());
				if(feeVO == null){
					if(LOGGER.isDebugEnabled()){
						LOGGER.debug("No fee record found for #id :"+paymentLineVO.getFeeId());
					}
					invalidPlotNos.add("No member record found for #id :"+paymentLineVO.getFeeId());
					continue;
				}
				if(PaymentTypes.CASH.equalsIgnoreCase(paymentLineVO.getModeOfPayment())){
					totalCashPayments++;
					cashPaymentVO = new CashPaymentVO();
					cashPaymentVO.setReceiptNo(paymentLineVO.getReceiptNo());
					cashPaymentVO.setPaidAmount(paymentLineVO.getPaidAmount());
					cashPaymentVO.setCollectedBy("FEED");
					cashPaymentVO.setPaidBy(paymentLineVO.getPaidBy());
					cashPaymentVO.setPaidDate(paymentLineVO.getPaidDate());
					cashPaymentVO.setMember(member);
					cashPaymentVO.setFee(feeVO);
					cashPaymentVO.setMobileNo(paymentLineVO.getMobileNo());
					paymentDao.save(cashPaymentVO);
				}else if(PaymentTypes.CHEQUE.equalsIgnoreCase(paymentLineVO.getModeOfPayment())){
					totalChequePayments++;
					chequePaymentVO = new ChequePaymentVO();
					chequePaymentVO.setReceiptNo(paymentLineVO.getReceiptNo());
					chequePaymentVO.setPaidAmount(paymentLineVO.getPaidAmount());
					chequePaymentVO.setPaidDate(paymentLineVO.getPaidDate());
					chequePaymentVO.setMember(member);
					chequePaymentVO.setFee(feeVO);
					chequePaymentVO.setChequeNo(paymentLineVO.getChequeNo());
					paymentDao.save(chequePaymentVO);
				}
			}
		result.put("INVALID_PLOT_IDS", invalidPlotNos);
		result.put("INVALID_FEE_IDS", invalidFeeIds);
		result.put("TOTAL_CHEQUE_PAYMENTS", totalChequePayments);
		result.put("TOTAL_CASH_PAYMENTS", totalCashPayments);
		return result;
	}
	
	/**
	 * <p>
	 * 
	 * </p>
	 * @param filePath
	 * @param list
	 */
	private void readPaymentsDataFromFile(String filePath, List<PaymentLineVO> list) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(filePath));
			int i=0;
			PaymentLineVO paymentLineVO = new PaymentLineVO();
			while ((line = br.readLine()) != null) {
				if(i==0){
					i++;
					continue;
				}
				// use comma as separator
				String[] row = line.split(cvsSplitBy);
				System.out.println(Arrays.toString(row));
				String receiptNo = row[0];
				String paidDate =row[1];
				String paidBy =row[2];
				String plotNo = row[3];
				String category =row[4] ;
				String noOfFamilies =row[5];
				String mobileNo =row[6];
				String paidAmount =row[7];
				String modeOfPayment=row[8];
				String chequeNo =row[9];
				String feeId =row[10];
				
				if(plotNo!=null && plotNo.trim().length()!=0){
					paymentLineVO = new PaymentLineVO();
					paymentLineVO.setPlotNo(plotNo);
					paymentLineVO.setReceiptNo(receiptNo);
					Date paidDate_v;
					try {
						paidDate_v = new SimpleDateFormat("yyyy-MMM-dd").parse(paidDate);
						paymentLineVO.setPaidDate(paidDate_v);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					paymentLineVO.setPaidBy(paidBy);
					paymentLineVO.setCategory(category);
					try {
						paymentLineVO.setNoOfFamilies(Integer.parseInt(noOfFamilies));
					} catch (NumberFormatException e) {
						paymentLineVO.setNoOfFamilies(1);
					}
					if(mobileNo!=null && mobileNo.trim().length()==10){
						paymentLineVO.setMobileNo(mobileNo);
					}
					try {
						paymentLineVO.setPaidAmount(Double.parseDouble(paidAmount));
					} catch (NumberFormatException e) {
						paymentLineVO.setPaidAmount(0d);
					}
					paymentLineVO.setModeOfPayment(modeOfPayment);
					paymentLineVO.setFeeId(Long.parseLong(feeId));
					paymentLineVO.setChequeNo(chequeNo);
				}
				list.add(paymentLineVO);
				paymentLineVO = null;
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
}
