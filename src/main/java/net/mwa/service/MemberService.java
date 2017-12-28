/**
 * 
 */
package net.mwa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.mwa.api.vo.MemberRegRequest;
import net.mwa.api.vo.MemberRegResponse;
import net.mwa.common.APICommonResponse;
import net.mwa.common.CategoryTypes;
import net.mwa.common.ErrorCodes;
import net.mwa.common.SearchMemberRequest;
import net.mwa.common.SearchMemberResponse;
import net.mwa.dao.MemberDao;
import net.mwa.repository.RoleRepository;
import net.mwa.vo.IndependentHouseVO;
import net.mwa.vo.UserDetailsVO;
import net.mwa.vo.RoleVO;

/**
 * @author Mahi
 *
 */
@Service("memberService")
public class MemberService /*implements UserDetailsService*/{

	@Autowired
	private MemberDao memberRegDao;
	
	@Autowired
	RoleRepository roleRepository;

	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(MemberService.class.getName());
	
	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public APICommonResponse save(MemberRegRequest request) {
		String mobileNo = request.getMobileNo();
		MemberRegResponse response = new MemberRegResponse();
		UserDetailsVO momberDetails = memberRegDao.findByMobileNo(mobileNo);
		boolean recordExisted = Boolean.FALSE;
		if (momberDetails == null) {
			UserDetailsVO momberDetailsByPlotNo = memberRegDao.findByPlotNo(request.getPlotNo());
			if (momberDetailsByPlotNo == null) {
				if(CategoryTypes.INDEPENDENT_ID == request.getCategoryId()){
					IndependentHouseVO individualVO = new IndependentHouseVO();
					individualVO.setFirstName(request.getFirstName());
					individualVO.setLastName(request.getLastName());
					individualVO.setMiddleName(request.getMiddleName());
					individualVO.setPlotNo(request.getPlotNo());
					individualVO.setRoadNo(request.getRoadNo());
					individualVO.setNoOfFamilies(request.getNoOfFamilies());
					individualVO.setMobileNo(request.getMobileNo());
					individualVO.setId(request.getCategoryId());				
					individualVO.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
					RoleVO userRole = roleRepository.findByRole("ADMIN");
					individualVO.setRoles(new HashSet<RoleVO>(Arrays.asList(userRole)));
					momberDetails = memberRegDao.save(individualVO);
				}else{
					
				}
				response.setSuccess(Boolean.TRUE);
			} else {
				// If record found search by plotNo
				momberDetails = momberDetailsByPlotNo;
				recordExisted = Boolean.TRUE;
			}
		} else {// If record found search by mobileNo
			recordExisted = Boolean.TRUE;
		}
		if (recordExisted) {
			response.setSuccess(Boolean.FALSE);
			response.setErrorCode(ErrorCodes.RECORD_ALREADY_EXISTED);
			response.setDeveloperMessage("Record already existed in DB with " + mobileNo);
			response.setUserMessage("Record already existed with and member id is : " + momberDetails.getId());
		}
		response.setMemberId(momberDetails.getId());
		return response;
	}

	/**
	 * This method will search a member by mobileNo and
	 * 
	 * @param mobileNo
	 * @return
	 */
	public APICommonResponse getMemberDetails(SearchMemberRequest request) {
		String mobileNo =  request.getMobileNo();
		UserDetailsVO detailsVO = memberRegDao.findByMobileNo(mobileNo);
		SearchMemberResponse response = new SearchMemberResponse();
		if (detailsVO == null) {
			response.setSuccess(Boolean.FALSE);
			response.setErrorCode(ErrorCodes.NO_RECORD_EXISTED);
			response.setDeveloperMessage("No record existed in DB with " + mobileNo);
			response.setUserMessage("No details found with mobile no : " + mobileNo);
		} else {
			response.setSuccess(Boolean.TRUE);
			response.setMemberDetailsVO(detailsVO);
		}
		return response;
	}


	public Iterable<UserDetailsVO> listAllMemebrs() {
		return memberRegDao.findAll();
	}


	
	//@Override
	//@Transactional
	public UserDetails loadUserByUsername(String plotNo) throws UsernameNotFoundException {
		UserDetailsVO memberDetailsVO = memberRegDao.findByPlotNo(plotNo);
		List<GrantedAuthority> authorities = getUserAuthority(memberDetailsVO.getRoles());
		return buildUserForAuthentication(memberDetailsVO, authorities);
	}

	private List<GrantedAuthority> getUserAuthority(Set<RoleVO> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for (RoleVO role : userRoles) {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles);
		return grantedAuthorities;
	}

	private UserDetails buildUserForAuthentication(UserDetailsVO user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getPlotNo(), user.getPassword(), user.isActive(), true, true, true, authorities);
	}
}
