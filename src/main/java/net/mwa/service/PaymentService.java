package net.mwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mwa.common.PaymentRequest;
import net.mwa.common.PaymentResponse;
import net.mwa.dao.FeeDao;
import net.mwa.dao.MemberDao;
import net.mwa.dao.PaymentDao;
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
		if(memberDetailsVO == null){
			response.setSuccess(Boolean.FALSE);	
			response.setUserMessage("Invalid member id");
			response.setDeveloperMessage("No member details found with # "+memberId);
			return response;
		}
		Long feeId = paymentRequest.getFeeId();
		FeeVO feeVO = feeDao.findAny(feeId);
		if(feeId == null || feeVO == null){
			response.setSuccess(Boolean.FALSE);	
			response.setUserMessage("Invalid fee id");
			response.setDeveloperMessage("No member fee details found with # "+feeId);
			return response;
		}
		double enteredAmount  = paymentRequest.getAmount();
		if(enteredAmount<=0){
			response.setSuccess(Boolean.FALSE);	
			response.setUserMessage("Amount must be greater than zero");
			response.setDeveloperMessage("Invalid amount is entered and entered amount is  "+enteredAmount);
			return response;
		}
		double dueAmount = feeVO.getAmount();
		if(enteredAmount != dueAmount){
			response.setSuccess(Boolean.FALSE);	
			response.setUserMessage(enteredAmount+" is not matching with due amount :"+dueAmount+". Please correct it and try again");
			response.setDeveloperMessage(enteredAmount+" is not matching with due amount :"+dueAmount+". Please correct it and try again");
			return response;
		}
		PaymentDetailsVO detailsVO = new PaymentDetailsVO();
		detailsVO.setPaidAmount(enteredAmount);
		detailsVO.setMember(memberDetailsVO);
		detailsVO.setFeeVO(feeVO);
		detailsVO = paymentDao.save(detailsVO);
		response.setSuccess(Boolean.TRUE);
		response.setReferenceID(detailsVO.getId());
		return response;
	}

	public Iterable<PaymentDetailsVO> listAllPayments(){
		return paymentDao.findAll();
	}
}
