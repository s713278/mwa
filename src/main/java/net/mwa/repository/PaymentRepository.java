package net.mwa.repository;

import org.springframework.data.repository.CrudRepository;

import net.mwa.vo.PaymentDetailsVO;

public interface PaymentRepository extends CrudRepository<PaymentDetailsVO, Long> {

}
