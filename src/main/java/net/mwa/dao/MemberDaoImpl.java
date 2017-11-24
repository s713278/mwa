/**
 * 
 */
package net.mwa.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.mwa.repository.MemberRepository;
import net.mwa.vo.MemberDetailsVO;

/**
 * @author skunta
 *
 */
@Repository
public class MemberDaoImpl implements MemberDao{

	private static Logger logger = Logger.getLogger(MemberDaoImpl.class.getName());
	
	@Autowired
	private MemberRepository memberRepository;
	
	public MemberDetailsVO save(MemberDetailsVO info) {
		MemberDetailsVO memberVO=	memberRepository.save(info);
		logger.info("ID\t:"+memberVO.getId());
		return memberVO;
	}
	
	public Iterable<MemberDetailsVO> findAll(){
		return memberRepository.findAll();
	}
	
	public MemberDetailsVO findAny(Long memberId){
		MemberDetailsVO memberVO=	memberRepository.findOne(memberId);
		return memberVO;
	}
	
	

	public MemberDetailsVO  findByMobileNo(String mobileNo) {
		return memberRepository.findByMobileNo(mobileNo);
	}

	public MemberDetailsVO update(MemberDetailsVO info) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MemberDetailsVO> findByFirstAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	public MemberDetailsVO  findByPlotNo(String plotNo) {
		return memberRepository.findByPlotNo(plotNo);
	}
	
	
}
