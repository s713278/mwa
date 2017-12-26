/**
 * 
 */
package net.mwa.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.mwa.vo.FeeVO;

/**
 * @author Mahi
 *
 */
@Repository
public interface FeeDao {
	
	public FeeVO save(final FeeVO info);
	
	public FeeVO update( final FeeVO info);

	public Iterable<FeeVO> findAll();

	public FeeVO findAny(final Long fundId);
	
	public void delete(final Long fundId);

	public List<FeeVO> listFundsInBtnDates(final Date startDate,final String endDate);
	
	public List<FeeVO> findByCategoryId(Long id);
}
