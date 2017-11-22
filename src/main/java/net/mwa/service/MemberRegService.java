/**
 * 
 */
package net.mwa.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mwa.dao.MemberRegDaoImpl;
import net.mwa.vo.MemberReg;

/**
 * @author Mahi
 *
 */
@Service
public class MemberRegService {

	@Autowired
	private MemberRegDaoImpl memberRegDao;
	
	private static Logger logger = Logger.getLogger(MemberRegService.class.getName());
		
	public Long save(MemberReg info) {
		MemberReg instituteInfo=memberRegDao.save(info);
		logger.info("ID\t:"+instituteInfo.getId());
		return instituteInfo.getId();
	}
	
	public Iterable<MemberReg> listAllMemebrs(){
		return memberRegDao.findAll();
	}
}
