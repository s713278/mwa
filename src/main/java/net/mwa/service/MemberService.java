/**
 * 
 */
package net.mwa.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mwa.common.APICommonResponse;
import net.mwa.common.ErrorCodes;
import net.mwa.common.MemberRegResponse;
import net.mwa.common.SearchMemberRequest;
import net.mwa.common.SearchMemberResponse;
import net.mwa.dao.FeeDao;
import net.mwa.dao.MemberDao;
import net.mwa.dao.PaymentDao;
import net.mwa.vo.MemberDetailsVO;

/**
 * @author Mahi
 *
 */
@Service
public class MemberService {

	@Autowired
	private MemberDao memberRegDao;
	
	@Autowired
	private FeeDao feeDao;
	
	@Autowired
	private PaymentDao paymentDao;

	private static Logger logger = Logger.getLogger(MemberService.class.getName());

	public APICommonResponse save(MemberDetailsVO info) {
		String mobileNo = info.getMobileNo();
		MemberRegResponse response = new MemberRegResponse();
		MemberDetailsVO momberDetails = memberRegDao.findByMobileNo(mobileNo);
		boolean recordExisted = Boolean.FALSE;
		if (momberDetails == null) {
			MemberDetailsVO momberDetailsByPlotNo = memberRegDao.findByPlotNo(info.getPlotNo());
			if (momberDetailsByPlotNo == null) {
				momberDetails = memberRegDao.save(info);
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
		MemberDetailsVO detailsVO = memberRegDao.findByMobileNo(mobileNo);
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


	public Iterable<MemberDetailsVO> listAllMemebrs() {
		return memberRegDao.findAll();
	}

}
