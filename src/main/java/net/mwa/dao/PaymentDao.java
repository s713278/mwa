package net.mwa.dao;

import java.util.List;

import net.mwa.vo.PaymentDetailsVO;

public interface PaymentDao {

	public PaymentDetailsVO save(final PaymentDetailsVO info);
	
	public Iterable<PaymentDetailsVO> findAll();
	
	List<PaymentDetailsVO> findByMemberId(final Long memberId);
	
	List<PaymentDetailsVO> findByFeeId(final Long feeId);
	
	List<PaymentDetailsVO> findByFeeIdAndMemberId(final Long feeId,final Long memberId);
	
	PaymentDetailsVO findByReceiptNo(final String receiptNo);
	
	
}
