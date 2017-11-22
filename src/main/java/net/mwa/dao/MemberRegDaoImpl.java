/**
 * 
 */
package net.mwa.dao;

import java.util.List;

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
public class MemberRegDaoImpl implements MemberRegDao{

	private static Logger logger = Logger.getLogger(MemberRegDaoImpl.class.getName());
	
	@Autowired
	private MemberRepository memberRepository;
	
	public MemberReg save(MemberReg info) {
		MemberReg memberVO=	memberRepository.save(info);
		logger.info("ID\t:"+memberVO.getId());
		return memberVO;
	}
	
	public Iterable<MemberReg> findAll(){
		return memberRepository.findAll();
	}
	
	public MemberReg findAny(Long memberId){
		MemberReg memberVO=	memberRepository.findOne(memberId);
		return memberVO;
	}
	
	public MemberReg findByMobileNo(Long memberId){
		return null;
	}

	public MemberReg update(MemberReg info) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MemberReg> findByFirstAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
