/**
 * 
 */
package net.mwa.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mwa.common.APICommonResponse;
import net.mwa.common.ErrorCodes;
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
		
	public APICommonResponse save(MemberReg info) {
		String mobileNo = info.getMobileNo();
		APICommonResponse response = new APICommonResponse();
		MemberReg momberDetails = memberRegDao.findByMobileNo(mobileNo);
		boolean recordExisted = Boolean.FALSE;
		if(momberDetails == null){
			MemberReg momberDetailsByPlotNo = memberRegDao.findByPlotNo(info.getPlotNo());
			if(momberDetailsByPlotNo ==null){
				momberDetails=memberRegDao.save(info);
				response.setSuccess(Boolean.TRUE);
			}else{
				momberDetails = momberDetailsByPlotNo; 
				recordExisted = Boolean.TRUE;
			}
		}
		if(recordExisted){
			response.setSuccess(Boolean.FALSE);
			response.setErrorCode(ErrorCodes.RECORD_ALREADY_EXISTED);
			response.setDeveloperMessage("Record already existed in DB with "+mobileNo);
			response.setUserMessage("Record already existed");
		}
		return response;
	}
	
	public Iterable<MemberReg> listAllMemebrs(){
		return memberRegDao.findAll();
	}
	
	
}
