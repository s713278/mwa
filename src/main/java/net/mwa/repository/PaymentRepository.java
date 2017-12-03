package net.mwa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.mwa.vo.PaymentDetailsVO;

public interface PaymentRepository extends CrudRepository<PaymentDetailsVO, Long> {

	List<PaymentDetailsVO> findByMemberId(final Long memberId);
	
	List<PaymentDetailsVO> findByFeeId(final Long feeId);
}
