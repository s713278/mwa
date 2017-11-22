/**
 * 
 */
package net.mwa.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.mwa.vo.FundVO;

/**
 * @author Mahi
 *
 */
@Repository
public interface FundDao {
	
	public FundVO save(final FundVO info);
	
	public FundVO update( final FundVO info);

	public Iterable<FundVO> findAll();

	public FundVO findAny(final Long fundId);
	
	public void delete(final Long fundId);

	public List<FundVO> listFundsInBrtnDates(final Date startDate,final String endDate);
}
