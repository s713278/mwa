/**
 * 
 */
package net.mwa.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import net.mwa.dao.FundDao;
import net.mwa.vo.FeeVO;

/**
 * @author Mahi
 *
 */
@Service
public class FundService {

	@Autowired
	private FundDao fundDao;
	
	private static Logger logger = Logger.getLogger(FundService.class.getName());
		
	public Long save(FeeVO fundVO) {
		FeeVO fundVO1=fundDao.save(fundVO);
		logger.info("ID\t:"+fundVO1.getId());
		return fundVO1.getId();
	}
	
	public Iterable<FeeVO> listAllFunds(){
		return fundDao.findAll();
	}
}
