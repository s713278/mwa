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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import net.mwa.common.PaymentTypes;
import net.mwa.dao.FeeDao;
import net.mwa.dao.MemberDao;
import net.mwa.dao.PaymentDao;
import net.mwa.vo.CashPaymentVO;
import net.mwa.vo.ChequePaymentVO;
import net.mwa.vo.FeeVO;
import net.mwa.vo.UserDetailsVO;
import net.mwa.vo.OnlinePaymentVO;
import net.mwa.vo.PaymentDetailsVO;

@Component
@Qualifier("PaymentsFeedFileProcessor")
public class PaymentsFeedFileProcessor implements FeedFileParser<PaymentLineVO> {

	private static final String DATE_FORMAT = "yyyy-MMM-dd";

	private static final Logger LOGGER = Logger.getLogger(PaymentsFeedFileProcessor.class.getName());

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
	public List<PaymentLineVO> parse(String filePath) {
		List<PaymentLineVO> list = new ArrayList<PaymentLineVO>();
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(filePath));
			int i = 0;
			PaymentLineVO paymentLineVO = new PaymentLineVO();
			while ((line = br.readLine()) != null) {
				if (i == 0) {
					i++;
					continue;
				}
				// use comma as separator
				String[] row = line.split(CSV_SEPARATOR);
				System.out.println(Arrays.toString(row));
				String receiptNo = row[0];
				String paidDate = row[1];
				String paidBy = row[2]+row[3]+row[4];
				String plotNo = row[5];
				String category = row[6];
				//String noOfFamilies = row[5];
				String paidAmount = row[7];
				String feeId = row[8];
				String modeOfPayment = row[9];
				String chequeNo = row[10];
				String mobileNo = row[11];
				String note = null;
				String fromBank = null;
				String transactionId = null;
				
				if (plotNo != null && plotNo.trim().length() != 0) {
					paymentLineVO = new PaymentLineVO();
					paymentLineVO.setPlotNo(plotNo.trim());
					paymentLineVO.setReceiptNo(receiptNo.trim());
					Date paidDate_v;
					try {
						paidDate_v = new SimpleDateFormat(DATE_FORMAT).parse(paidDate);
						paymentLineVO.setPaidDate(paidDate_v);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					paymentLineVO.setPaidBy(paidBy.trim());
					paymentLineVO.setCategory(category.trim());
					if (mobileNo != null && mobileNo.trim().length() == 10) {
						paymentLineVO.setMobileNo(mobileNo.trim());
					}else{
						paymentLineVO.setMobileNo(DEFAULT_MOBILE_NO);
					}
					try {
						paymentLineVO.setPaidAmount(Double.parseDouble(paidAmount));
					} catch (NumberFormatException e) {
						paymentLineVO.setPaidAmount(0d);
					}
					if(modeOfPayment!=null){
						paymentLineVO.setModeOfPayment(modeOfPayment.trim());	
					}
						paymentLineVO.setFeeId(Long.parseLong(feeId));
					if(chequeNo!=null){
						paymentLineVO.setChequeNo(chequeNo.trim());
					}
						paymentLineVO.setNote(note);
						paymentLineVO.setFromBank(fromBank);
						paymentLineVO.setTransactionId(transactionId);
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
		return list;
	}

	@Override
	public Map<String, Object> updateDataInDB(List<PaymentLineVO> list) {
		Map<String, Object> result = new ConcurrentHashMap<String, Object>();
		CashPaymentVO cashPaymentVO = null;
		ChequePaymentVO chequePaymentVO = null;
		OnlinePaymentVO onlinePaymentVO = null;
		List<String> newlyCreatedMemmbers = new ArrayList<String>();
		List<String> invalidFeeIds = new ArrayList<String>();
		List<String> alreadyUpdatedReceiptNos = new ArrayList<String>();
		FeeVO feeVO = null;
		UserDetailsVO member = null;
		int cashPayments = 0;
		int chequePayments = 0;
		int onlinePayments = 0;
		for (PaymentLineVO paymentLineVO : list) {
			String plotNo = paymentLineVO.getPlotNo();
			member = memberDao.findByPlotNo(plotNo);
			if (member == null) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("No member record found for #plot no :" + plotNo);
				}
				newlyCreatedMemmbers.add(plotNo);
				continue;
			}
			feeVO = feeDao.findAny(paymentLineVO.getFeeId());
			if (feeVO == null) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("No fee record found for #id :" + paymentLineVO.getFeeId());
				}
				invalidFeeIds.add(String.valueOf(paymentLineVO.getFeeId()));
				continue;
			}
			PaymentDetailsVO detailsVO= paymentDao.findByReceiptNo(paymentLineVO.getReceiptNo());
			if(detailsVO!=null){
				alreadyUpdatedReceiptNos.add(paymentLineVO.getReceiptNo());
				continue;
			}
			if (PaymentTypes.CASH.equalsIgnoreCase(paymentLineVO.getModeOfPayment())) {
				cashPayments++;
				cashPaymentVO = new CashPaymentVO();
				cashPaymentVO.setReceiptNo(paymentLineVO.getReceiptNo());
				cashPaymentVO.setPaidAmount(paymentLineVO.getPaidAmount());
				cashPaymentVO.setCollectedBy("MWA");
				cashPaymentVO.setPaidBy(paymentLineVO.getPaidBy());
				cashPaymentVO.setPaidDate(paymentLineVO.getPaidDate());
				cashPaymentVO.setMember(member);
				cashPaymentVO.setFee(feeVO);
				cashPaymentVO.setMobileNo(paymentLineVO.getMobileNo());
				paymentDao.save(cashPaymentVO);
			} else if (PaymentTypes.CHEQUE.equalsIgnoreCase(paymentLineVO.getModeOfPayment())) {
				chequePayments++;
				chequePaymentVO = new ChequePaymentVO();
				chequePaymentVO.setReceiptNo(paymentLineVO.getReceiptNo());
				chequePaymentVO.setPaidAmount(paymentLineVO.getPaidAmount());
				chequePaymentVO.setPaidDate(paymentLineVO.getPaidDate());
				chequePaymentVO.setMember(member);
				chequePaymentVO.setFee(feeVO);
				chequePaymentVO.setChequeNo(paymentLineVO.getChequeNo());
				paymentDao.save(chequePaymentVO);
			}else if (PaymentTypes.ONLINE.equalsIgnoreCase(paymentLineVO.getModeOfPayment())) {
				onlinePayments++;
				onlinePaymentVO = new OnlinePaymentVO();
				onlinePaymentVO.setReceiptNo(paymentLineVO.getReceiptNo());
				onlinePaymentVO.setPaidAmount(paymentLineVO.getPaidAmount());
				onlinePaymentVO.setPaidDate(paymentLineVO.getPaidDate());
				onlinePaymentVO.setMember(member);
				onlinePaymentVO.setFee(feeVO);
				onlinePaymentVO.setFromBank(paymentLineVO.getFromBank());
				onlinePaymentVO.setTransactionId(paymentLineVO.getTransactionId());
				paymentDao.save(onlinePaymentVO);
			}
		}
		result.put("NEWLY_CREATED_MEMMBERS", newlyCreatedMemmbers);
		result.put("INVALID_FEE_IDS", invalidFeeIds);
		result.put("CHEQUE_PAYMENTS", chequePayments);
		result.put("CASH_PAYMENTS", cashPayments);
		result.put("ONLINE_PAYMENTS", onlinePayments);
		result.put("ALREADY_UPDATED_RECEIPT_NOS", alreadyUpdatedReceiptNos);
		return result;

	}

	@Override
	public Map<String, Object> processFile() {
		List<PaymentLineVO> list = parse("C:\\SwamyAll\\swamy\\Swamy1\\Mayurinagar\\11_CC Cameras\\Data\\cc_payments_1.0.csv");
		return updateDataInDB(list);
	}

}
