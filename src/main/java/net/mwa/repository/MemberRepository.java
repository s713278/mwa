package net.mwa.repository;

import org.springframework.data.repository.CrudRepository;

import net.mwa.vo.MemberDetailsVO;

public interface MemberRepository extends CrudRepository<MemberDetailsVO, Long> {

	MemberDetailsVO findByMobileNo(final String mobileNo);
	
	MemberDetailsVO findByPlotNo(final String plotNo);
}
