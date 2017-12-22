package net.mwa.repository;

import org.springframework.data.repository.CrudRepository;

import net.mwa.vo.UserDetailsVO;

public interface MemberRepository extends CrudRepository<UserDetailsVO, Long> {

	UserDetailsVO findByMobileNo(final String mobileNo);
	
	UserDetailsVO findByPlotNo(final String plotNo);
}
