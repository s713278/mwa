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
import net.mwa.vo.MemberDetailsVO;

@Component
@Qualifier("PaymentsFeedFileProcessor")
public class PaymentsFeedFileProcessor implements FeedFileParser<PaymentLineVO> {

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
		String cvsSplitBy = ",";
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
				String[] row = line.split(cvsSplitBy);
				System.out.println(Arrays.toString(row));
				String receiptNo = row[0];
				String paidDate = row[1];
				String paidBy = row[2];
				String plotNo = row[3];
				String category = row[4];
				String noOfFamilies = row[5];
				String mobileNo = row[6];
				String paidAmount = row[7];
				String modeOfPayment = row[8];
				String chequeNo = row[9];
				String feeId = row[10];

				if (plotNo != null && plotNo.trim().length() != 0) {
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
					if (mobileNo != null && mobileNo.trim().length() == 10) {
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
		return list;
	}

	@Override
	public Map<String, Object> updateDataInDB(List<PaymentLineVO> list) {

		Map<String, Object> result = new ConcurrentHashMap<String, Object>();
		CashPaymentVO cashPaymentVO = null;
		ChequePaymentVO chequePaymentVO = null;
		List<String> invalidPlotNos = new ArrayList<String>();
		List<String> invalidFeeIds = new ArrayList<String>();
		FeeVO feeVO = null;
		MemberDetailsVO member = null;
		int totalCashPayments = 0;
		int totalChequePayments = 0;
		for (PaymentLineVO paymentLineVO : list) {
			String plotNo = paymentLineVO.getPlotNo();
			member = memberDao.findByPlotNo(plotNo);
			if (member == null) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("No member record found for #plot no :" + plotNo);
				}
				invalidPlotNos.add("No member record found for #plot no :" + plotNo);
				continue;
			}
			feeVO = feeDao.findAny(paymentLineVO.getFeeId());
			if (feeVO == null) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("No fee record found for #id :" + paymentLineVO.getFeeId());
				}
				invalidPlotNos.add("No member record found for #id :" + paymentLineVO.getFeeId());
				continue;
			}
			if (PaymentTypes.CASH.equalsIgnoreCase(paymentLineVO.getModeOfPayment())) {
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
			} else if (PaymentTypes.CHEQUE.equalsIgnoreCase(paymentLineVO.getModeOfPayment())) {
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

	@Override
	public Map<String, Object> processFile() {
		List<PaymentLineVO> list = parse("C:\\TRU\\git\\mwa_1\\src\\main\\resources\\feed\\members_list.csv");
		return updateDataInDB(list);
	}

}
