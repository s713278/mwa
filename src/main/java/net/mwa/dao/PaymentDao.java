package net.mwa.dao;

import org.springframework.stereotype.Repository;

import net.mwa.vo.PaymentDetailsVO;

@Repository
public interface PaymentDao {

	public PaymentDetailsVO save(final PaymentDetailsVO info);
	
	public Iterable<PaymentDetailsVO> findAll();
}
