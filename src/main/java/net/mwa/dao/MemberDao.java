/**
 * 
 */
package net.mwa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.mwa.vo.UserDetailsVO;

/**
 * @author Mahi
 *
 */
@Repository
public interface MemberDao {
	
	public UserDetailsVO save(final UserDetailsVO info);
	
	public UserDetailsVO update( final UserDetailsVO info);

	public Iterable<UserDetailsVO> findAll();

	public UserDetailsVO findAny(final Long memberId);

	public UserDetailsVO  findByMobileNo(final String mobileNo);
	
	public UserDetailsVO  findByPlotNo(final String plotNo);
	
	public List<UserDetailsVO> findByFirstAndLastName(final String firstName,final String lastName);
}
