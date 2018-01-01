package net.mwa.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mwa.common.AddOfflinePaymentRequest;
import net.mwa.common.AddOfflinePaymentResponse;
import net.mwa.common.ErrorCodes;
import net.mwa.common.PaymentDueResponse;
import net.mwa.common.PaymentHistoryRequest;
import net.mwa.common.PaymentHistoryResponse;
import net.mwa.common.PaymentRequest;
import net.mwa.common.PaymentResponse;
import net.mwa.dao.FeeDao;
import net.mwa.dao.MemberDao;
import net.mwa.dao.PaymentDao;
import net.mwa.vo.CashPaymentVO;
import net.mwa.vo.CategoryVO;
import net.mwa.vo.FeeVO;
import net.mwa.vo.PaymentDetailsVO;
import net.mwa.vo.UserDetailsVO;

@Service
public class PaymentService {

	private static Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);
	
	@Autowired
	private MemberDao memberRegDao;

	@Autowired
	private FeeDao feeDao;

	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	MemberServiceHelper memberServiceHelper;
	
	public MemberServiceHelper getMemberServiceHelper() {
		return memberServiceHelper;
	}

	public void setMemberServiceHelper(MemberServiceHelper memberServiceHelper) {
		this.memberServiceHelper = memberServiceHelper;
	}

	public PaymentResponse payAmount(PaymentRequest paymentRequest) {
		Long memberId = paymentRequest.getMemberId();
		PaymentResponse response = new PaymentResponse();
		UserDetailsVO memberDetailsVO = memberRegDao.findAny(memberId);
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
			response.setDeveloperMessage("No fee details found for the id # " + feeId);
			return response;
		}
		double enteredAmount = paymentRequest.getAmount();
		if (enteredAmount <= 0 && enteredAmount <= 10) {
			response.setSuccess(Boolean.FALSE);
			response.setUserMessage("Minimum amount should be Rs 10");
			response.setDeveloperMessage("Invalid amount is entered and entered amount is  " + enteredAmount);
			return response;
		}

		// If payment is already made by member
		List<PaymentDetailsVO> payments = paymentDao.findByFeeIdAndMemberId(feeId, memberId);
		double paidAmount = 0;
		if (payments != null && payments.size() > 0) {
			for (PaymentDetailsVO paymentDetailsVO : payments) {
				paidAmount += paymentDetailsVO.getPaidAmount();
			}
		}
		// If amount is entered that than the due amount
		boolean isFeebyFamity = actualFeeVO.isFeebyFamily();
		double actualAmount = actualFeeVO.getAmount();
		double dueAmount = 0;
		if(isFeebyFamity){
			actualAmount = actualAmount * memberDetailsVO.getNoOfFamilies();
		}
		dueAmount = actualAmount - paidAmount;
		if (enteredAmount > dueAmount) {
			enteredAmount = dueAmount;
		}
		if (dueAmount == 0) {
			response.setSuccess(Boolean.FALSE);
			response.setUserMessage("No dues pending..");
			String developerMessage = MessageFormat.format("No dues pending for the member #{0} and feeId #{1}",
					new Object[] { String.valueOf(memberId), feeId });
			response.setDeveloperMessage(developerMessage);
			return response;
		}
		CashPaymentVO detailsVO = new CashPaymentVO();
		detailsVO.setPaidAmount(enteredAmount);
		detailsVO.setMember(memberDetailsVO);
		detailsVO.setFee(actualFeeVO);
		detailsVO.setPaidBy(paymentRequest.getPaidBy());
		detailsVO.setCollectedBy(paymentRequest.getCollectedBy());
		detailsVO.setMobileNo(paymentRequest.getMobileNo());
		detailsVO = (CashPaymentVO) paymentDao.save(detailsVO);
		response.setSuccess(Boolean.TRUE);
		response.setTransactionId(String.valueOf(detailsVO.getId()));
		return response;
	}

	public Iterable<PaymentDetailsVO> listAllPayments() {
		Iterable<PaymentDetailsVO> ll = paymentDao.findAll();
		if(LOGGER.isInfoEnabled()){
			LOGGER.info("Inside listAllPayments() : ");
		}
		return ll;
	}

	public PaymentDueResponse getPaymentDuesByMemberId(final Long memberId) {
		PaymentDueResponse response = new PaymentDueResponse();
		UserDetailsVO memberDetailsVO = memberRegDao.findAny(memberId);
		if (memberDetailsVO == null) {
			response.setSuccess(Boolean.FALSE);
			return response;
		}
		CategoryVO categoryVO = memberDetailsVO.getCategory();
		// Get all fee payments
		List<FeeVO> allPayments = feeDao.findByCategoryId(categoryVO.getId());

		// Get already paid payments
		List<PaymentDetailsVO> paidPayments = paymentDao.findByMemberId(memberId);

		List<FeeVO> duePayments = new ArrayList<FeeVO>(allPayments);
		/*
		 * for(PaymentDetailsVO paymentDetailsVO : paidPayments){ duePayments =
		 * allPayments.stream().filter(fee->(paymentDetailsVO.getFee().getId()!=
		 * fee.getId())).collect(Collectors.toList()); }
		 */
		for (int i = 0; i < paidPayments.size(); i++) {
			PaymentDetailsVO paidPaymentVO = paidPayments.get(i);
			for (FeeVO feeVO : allPayments) {
				if (paidPaymentVO.getFee().getId() == feeVO.getId()) {
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
	 * 
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
	 * 
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

	/**
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param paymentRequest
	 * @return
	 */
	public PaymentHistoryResponse getPaymentsByMemberIdANDFeeId(PaymentHistoryRequest paymentRequest) {
		Long memberId = paymentRequest.getMemberId();
		PaymentHistoryResponse response = new PaymentHistoryResponse();
		UserDetailsVO memberDetailsVO = memberRegDao.findAny(memberId);
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
		// If any payments already made by member
		List<PaymentDetailsVO> paymentHistory = paymentDao.findByFeeIdAndMemberId(feeId, memberId);

		boolean isFeebyFamity = actualFeeVO.isFeebyFamily();
		double actualAmount = actualFeeVO.getAmount();
		double dueAmount = 0;
		double paidAmount = 0;
		
		if(isFeebyFamity){
			actualAmount = actualAmount * memberDetailsVO.getNoOfFamilies();
		}
		
		for (PaymentDetailsVO paymentDetailsVO : paymentHistory) {
			paidAmount += paymentDetailsVO.getPaidAmount();
		}		
		dueAmount = actualAmount - paidAmount;
		response.setSuccess(Boolean.TRUE);
		response.setCount(paymentHistory.size());
		response.setPaymentHistory(paymentHistory);
		response.setDueAmount(dueAmount);
		response.setLastDueDate(actualFeeVO.getExpireDate());
		return response;
	}

	
	public AddOfflinePaymentResponse addOffLinePayment(AddOfflinePaymentRequest request){
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Request : "+request);
		}
		UserDetailsVO userDetails = null;
		AddOfflinePaymentResponse response = new AddOfflinePaymentResponse();
		try {
			userDetails = getMemberServiceHelper().findORCreateMember(request);
			if(userDetails ==null || userDetails.getId() == null){
				response.setSuccess(Boolean.FALSE);
				response.setUserMessage("Invalid UserDetails");
				response.setDeveloperMessage("UserDetailsVO is not persisted in data base");
				return response;
			}
			Long feeId = request.getFeeId();
			FeeVO actualFeeVO = feeDao.findAny(feeId);
			if (feeId == null || actualFeeVO == null) {
				response.setSuccess(Boolean.FALSE);
				response.setUserMessage("Invalid fee id");
				response.setDeveloperMessage("No fee details found for the id # " + feeId);
				return response;
			}
			double paidAmount = request.getAmount();
			if (paidAmount <= 0) {
				response.setSuccess(Boolean.FALSE);
				response.setErrorCode(ErrorCodes.INVALID_INPUT_REQUEST);
				response.setUserMessage("Minimum amount should be Rs 10");
				response.setDeveloperMessage("Invalid amount is entered and entered amount is  " + paidAmount);
				return response;
			}
			Long transactionId = getMemberServiceHelper().addPayment(request,userDetails,actualFeeVO);
			response.setSuccess(Boolean.TRUE);
			response.setOnlineReferenceId(transactionId);
		} catch (InvalidInputException e) {
			response.setSuccess(Boolean.FALSE);
			response.setErrorCode(ErrorCodes.INVALID_INPUT_REQUEST);
			response.setDeveloperMessage("Seems AddOfflinePaymentRequest is null or required params are empty.");
		}finally {
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Response : "+response);
			}
		}
			
		return response;
	}
}
