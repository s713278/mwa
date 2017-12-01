package net.mwa.dao;

import net.mwa.vo.PaymentDetailsVO;

public interface PaymentDao {

	public PaymentDetailsVO save(final PaymentDetailsVO info);
	
	public Iterable<PaymentDetailsVO> findAll();
}
