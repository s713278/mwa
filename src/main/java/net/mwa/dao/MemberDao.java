/**
 * 
 */
package net.mwa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.mwa.vo.MemberDetailsVO;

/**
 * @author Mahi
 *
 */
@Repository
public interface MemberDao {
	
	public MemberDetailsVO save(final MemberDetailsVO info);
	
	public MemberDetailsVO update( final MemberDetailsVO info);

	public Iterable<MemberDetailsVO> findAll();

	public MemberDetailsVO findAny(final Long memberId);

	public MemberDetailsVO  findByMobileNo(final String mobileNo);
	
	public MemberDetailsVO  findByPlotNo(final String plotNo);
	
	public List<MemberDetailsVO> findByFirstAndLastName(final String firstName,final String lastName);
}
