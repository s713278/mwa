package net.mwa.repository;

import org.springframework.data.repository.CrudRepository;

import net.mwa.vo.FeeVO;

public interface FundRepository extends CrudRepository<FeeVO, Long> {

	
}
