package net.mwa.repository;

import org.springframework.data.repository.CrudRepository;

import net.mwa.vo.MemberReg;

public interface MemberRepository extends CrudRepository<MemberReg, Long> {

	MemberReg findByMobileNo(final String mobileNo);
	
	MemberReg findByPlotNo(final String plotNo);
}
