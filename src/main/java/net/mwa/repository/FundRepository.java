package net.mwa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.mwa.vo.FeeVO;

public interface FundRepository extends CrudRepository<FeeVO, Long> {

	List<FeeVO> findByCategoryId(Long id);

	
}
