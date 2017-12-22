/**
 * 
 */
package net.mwa.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.mwa.repository.MemberRepository;
import net.mwa.vo.UserDetailsVO;

/**
 * @author skunta
 *
 */
@Repository
public class MemberDaoImpl implements MemberDao{

	private static Logger logger = Logger.getLogger(MemberDaoImpl.class.getName());
	
	@Autowired
	private MemberRepository memberRepository;
	
	public UserDetailsVO save(UserDetailsVO info) {
		UserDetailsVO memberVO=	memberRepository.save(info);
		logger.info("ID\t:"+memberVO.getId());
		return memberVO;
	}
	
	public Iterable<UserDetailsVO> findAll(){
		return memberRepository.findAll();
	}
	
	public UserDetailsVO findAny(Long memberId){
		UserDetailsVO memberVO=	memberRepository.findOne(memberId);
		return memberVO;
	}
	
	

	public UserDetailsVO  findByMobileNo(String mobileNo) {
		return memberRepository.findByMobileNo(mobileNo);
	}

	public UserDetailsVO update(UserDetailsVO info) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserDetailsVO> findByFirstAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserDetailsVO  findByPlotNo(String plotNo) {
		return memberRepository.findByPlotNo(plotNo);
	}
	
	
}
