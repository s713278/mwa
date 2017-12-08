package net.mwa.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mwa.common.PaymentDueResponse;
import net.mwa.common.PaymentHistoryResponse;
import net.mwa.common.PaymentRequest;
import net.mwa.common.PaymentResponse;
import net.mwa.dao.FeeDao;
import net.mwa.dao.MemberDao;
import net.mwa.dao.PaymentDao;
import net.mwa.vo.CashPaymentVO;
import net.mwa.vo.CategoryVO;
import net.mwa.vo.FeeVO;
import net.mwa.vo.MemberDetailsVO;
import net.mwa.vo.PaymentDetailsVO;

@Service
public class PaymentService {

	@Autowired
	private MemberDao memberRegDao;

	@Autowired
	private FeeDao feeDao;

	@Autowired
	private PaymentDao paymentDao;

	public PaymentResponse payAmount(PaymentRequest paymentRequest) {
		Long memberId = paymentRequest.getMemberId();
		PaymentResponse response = new PaymentResponse();
		MemberDetailsVO memberDetailsVO = memberRegDao.findAny(memberId);
		if (memberDetailsVO == null) {
			response.setSuccess(Boolean.FALSE);
			response.setUserMessage("Invalid member id");
			response.setDeveloperMessage("No member details found with # " + memberId);
			return response;
		}
		Long feeId = paymentRequest.getFeeId();
		FeeVO actualFeeVO = feeDao.findAny(feeId);
		if (feeId == null || actualFeeVO == null) {
			response.setSuccess(Boolean.FALSE);
			response.setUserMessage("Invalid fee id");
			response.setDeveloperMessage("No member fee details found with # " + feeId);
			return response;
		}
		double enteredAmount = paymentRequest.getAmount();
		if (enteredAmount <= 0 && enteredAmount <=10) {
			response.setSuccess(Boolean.FALSE);
			response.setUserMessage("Minimum amount should be Rs 10");
			response.setDeveloperMessage("Invalid amount is entered and entered amount is  " + enteredAmount);
			return response;
		}
		
		//If any payments already made by member
		List<PaymentDetailsVO> payments = paymentDao.findByFeeIdAndMemberId(feeId, memberId);
		double paidAmount = 0;
		if(payments!=null && payments.size()>0){
			for(PaymentDetailsVO paymentDetailsVO : payments){
				paidAmount += paymentDetailsVO.getPaidAmount();
			}
		}
		//If amount is entered that than the due amount
		double dueAmount = actualFeeVO.getAmount()-paidAmount;
		if (enteredAmount > dueAmount) {
			enteredAmount = dueAmount;
		}
		
		if (dueAmount == 0) {
			response.setSuccess(Boolean.FALSE);
			response.setUserMessage("No dues pending..");			
			String developerMessage = MessageFormat.format("No dues pending for the member #{0} and feeId #{1}", new Object[]{String.valueOf(memberId),feeId});
			response.setDeveloperMessage(developerMessage);
			return response;
		}
		/*if (enteredAmount < dueAmount) {
			response.setSuccess(Boolean.FALSE);
			response.setUserMessage(enteredAmount + " is not matching with due amount :" + dueAmount
					+ ". Please correct it and try again");
			response.setDeveloperMessage(enteredAmount + " is not matching with due amount :" + dueAmount
					+ ". Please correct it and try again");
			return response;
		}*/
		CashPaymentVO detailsVO = new CashPaymentVO();
		detailsVO.setPaidAmount(enteredAmount);
		detailsVO.setMember(memberDetailsVO);
		detailsVO.setFee(actualFeeVO);
		detailsVO = (CashPaymentVO) paymentDao.save(detailsVO);
		response.setSuccess(Boolean.TRUE);
		response.setTransactionId(String.valueOf(detailsVO.getId()));
		return response;
	}

	public Iterable<PaymentDetailsVO> listAllPayments() {
		return paymentDao.findAll();
	}

	public PaymentDueResponse getPaymentDuesByMemberId(final Long memberId) {
		PaymentDueResponse response = new PaymentDueResponse();
		MemberDetailsVO memberDetailsVO = memberRegDao.findAny(memberId);
		if(memberDetailsVO ==null){
			response.setSuccess(Boolean.FALSE);
			return response;
		}
		CategoryVO categoryVO = memberDetailsVO.getCategory();
		//Get all payments
		List<FeeVO> allPayments = feeDao.findByCategoryId(categoryVO.getId());
		
		//Get already paid payments
		List<PaymentDetailsVO> paidPayments = paymentDao.findByMemberId(memberId);
		
		List<FeeVO> duePayments = new ArrayList<FeeVO>(allPayments);
		/*for(PaymentDetailsVO paymentDetailsVO : paidPayments){
			duePayments = allPayments.stream().filter(fee->(paymentDetailsVO.getFee().getId()!=fee.getId())).collect(Collectors.toList());
		}*/
		for(int i = 0 ; i < paidPayments.size(); i++){
			PaymentDetailsVO paidPaymentVO= paidPayments.get(i);
			for(FeeVO feeVO : allPayments){
				if(paidPaymentVO.getFee().getId() == feeVO.getId()){
					duePayments.remove(feeVO);
				}
			}
		}
		response.setSuccess(Boolean.TRUE);
		response.setNoOfPaymentDues(duePayments.size());
		response.setPayemntDues(duePayments);
		response.setNoOfPaymentsCompleted(paidPayments.size());
		response.setPaymentHistory(paidPayments);
		return response;
	}

	/**
	 * <p>
	 * 
	 * </p>
	 * @param memberID
	 * @return
	 */
	public PaymentHistoryResponse getPaymentHistoryByMemberId(final Long memberID) {
		PaymentHistoryResponse response = new PaymentHistoryResponse();
		// final Long memberID = request.getMemberId();
		List<PaymentDetailsVO> result = paymentDao.findByMemberId(memberID);
		response.setCount(result.size());
		response.setPaymentHistory(result);
		return response;
	}

	/**
	 * <p>
	 * 
	 * </p>
	 * @param feeID
	 * @return
	 */
	public PaymentHistoryResponse getPaymentHistoryByFeeId(final Long feeID) {
		PaymentHistoryResponse response = new PaymentHistoryResponse();
		// final Long feeID = request.getFeeId();
		List<PaymentDetailsVO> result = paymentDao.findByFeeId(feeID);
		response.setCount(result.size());
		response.setPaymentHistory(result);
		return response;
	}
	
	
	
}
