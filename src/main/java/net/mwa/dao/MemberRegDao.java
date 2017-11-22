/**
 * 
 */
package net.mwa.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.mwa.repository.MemberRepository;
import net.mwa.vo.MemberReg;

/**
 * @author Mahi
 *
 */
@Repository
public class MemberRegDao {

	private static Logger logger = Logger.getLogger(MemberRegDao.class.getName());
	
	@Autowired
	private MemberRepository memberRepository;
	
	public MemberReg save(MemberReg info) {
		MemberReg instituteInfo=	memberRepository.save(info);
		logger.info("ID\t:"+instituteInfo.getId());
		return instituteInfo;
	}
	
	public Iterable<MemberReg> findAll(){
		return memberRepository.findAll();
	}
}
