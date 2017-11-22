package net.mwa.repository;

import org.springframework.data.repository.CrudRepository;

import net.mwa.vo.MemberReg;

public interface MemberRepository extends CrudRepository<MemberReg, Long> {

	
}
