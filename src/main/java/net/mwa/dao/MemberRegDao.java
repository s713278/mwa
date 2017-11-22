/**
 * 
 */
package net.mwa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.mwa.vo.MemberReg;

/**
 * @author Mahi
 *
 */
@Repository
public interface MemberRegDao {
	
	public MemberReg save(final MemberReg info);
	
	public MemberReg update( final MemberReg info);

	public Iterable<MemberReg> findAll();

	public MemberReg findAny(final Long memberId);

	public MemberReg findByMobileNo(final Long memberId);
	
	public List<MemberReg> findByFirstAndLastName(final String firstName,final String lastName);
}
