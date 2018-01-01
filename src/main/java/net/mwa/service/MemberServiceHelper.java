package net.mwa.service;

import java.util.Arrays;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import net.mwa.api.vo.MemberRegRequest;
import net.mwa.common.AddOfflinePaymentRequest;
import net.mwa.common.CategoryTypes;
import net.mwa.common.PaymentRequest;
import net.mwa.common.PaymentTypes;
import net.mwa.common.utils.Constants;
import net.mwa.dao.MemberDao;
import net.mwa.dao.PaymentDao;
import net.mwa.repository.RoleRepository;
import net.mwa.vo.ApartmentVO;
import net.mwa.vo.CashPaymentVO;
import net.mwa.vo.CategoryVO;
import net.mwa.vo.ChequePaymentVO;
import net.mwa.vo.CommercialVO;
import net.mwa.vo.FeeVO;
import net.mwa.vo.IndependentHouseVO;
import net.mwa.vo.OnlinePaymentVO;
import net.mwa.vo.PaymentDetailsVO;
import net.mwa.vo.RoleVO;
import net.mwa.vo.UserDetailsVO;

@Component
public class MemberServiceHelper {
	
	@Autowired
	private MemberDao memberRegDao;
	
	@Autowired
	RoleRepository roleRepository;

	
	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	private final static Logger LOGGER = Logger.getLogger(MemberServiceHelper.class.getName());
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	public UserDetailsVO findORCreateMember(MemberRegRequest request) throws InvalidInputException {
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Start : createMember()");
		}
		if(request == null || StringUtils.isEmpty(request.getPlotNo())){
			throw new InvalidInputException("MemberRegRequest is null or not having plotNo");
		}
		UserDetailsVO memberDetails = memberRegDao.findByPlotNo(request.getPlotNo().trim());
		if(memberDetails !=null ){
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("Existed record found for the plotNo : "+request.getPlotNo());
			}
		}else{
			if(CategoryTypes.INDEPENDENT_ID == request.getCategoryId()){
				IndependentHouseVO individualVO = new IndependentHouseVO();
				individualVO.setFirstName(request.getFirstName());
				individualVO.setLastName(request.getLastName());
				individualVO.setMiddleName(request.getMiddleName());
				individualVO.setPlotNo(request.getPlotNo());
				individualVO.setRoadNo(request.getRoadNo());
				individualVO.setNoOfFamilies(request.getNoOfFamilies());
				individualVO.setMobileNo(request.getMobileNo());
				individualVO.setCategory(new CategoryVO(CategoryTypes.INDEPENDENT_ID));
				individualVO.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
				RoleVO userRole = roleRepository.findByRole(Constants.Roles.MEMBER.toString());
				individualVO.setRoles(new HashSet<RoleVO>(Arrays.asList(userRole)));
				memberDetails = memberRegDao.save(individualVO);
			}else if(CategoryTypes.APARTMENT_ID == request.getCategoryId()){
				ApartmentVO apartmentVO = new ApartmentVO();
				apartmentVO.setFirstName(request.getFirstName());
				apartmentVO.setLastName(request.getLastName());
				apartmentVO.setMiddleName(request.getMiddleName());
				apartmentVO.setAprtmentName(request.getApartmentName());
				apartmentVO.setPlotNo(request.getPlotNo());
				apartmentVO.setRoadNo(request.getRoadNo());
				apartmentVO.setNoOfFamilies(request.getNoOfFamilies());
				apartmentVO.setMobileNo(request.getMobileNo());
				apartmentVO.setCategory(new CategoryVO(CategoryTypes.APARTMENT_ID));
				apartmentVO.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
				RoleVO userRole = roleRepository.findByRole(Constants.Roles.MEMBER.toString());
				apartmentVO.setRoles(new HashSet<RoleVO>(Arrays.asList(userRole)));
				memberDetails = memberRegDao.save(apartmentVO);
			}else if(CategoryTypes.COMMERCIAL_ID == request.getCategoryId()){
				CommercialVO commercialVO = new CommercialVO();
				commercialVO.setFirstName(request.getFirstName());
				commercialVO.setLastName(request.getLastName());
				commercialVO.setMiddleName(request.getMiddleName());
				commercialVO.setBusinessName(request.getBusinessName());
				commercialVO.setPlotNo(request.getPlotNo());
				commercialVO.setRoadNo(request.getRoadNo());
				commercialVO.setNoOfFamilies(request.getNoOfFamilies());
				commercialVO.setMobileNo(request.getMobileNo());
				commercialVO.setCategory(new CategoryVO(CategoryTypes.COMMERCIAL_ID));
				commercialVO.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
				RoleVO userRole = roleRepository.findByRole(Constants.Roles.MEMBER.toString());
				commercialVO.setRoles(new HashSet<RoleVO>(Arrays.asList(userRole)));
				memberDetails = memberRegDao.save(commercialVO);
			}
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("New member record created for plot : "+request.getPlotNo());
			}
		}
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("End : createMember()");
		}
		
		return memberDetails;
	}

	@Autowired
	private PaymentDao paymentDao;

	public Long addPayment(AddOfflinePaymentRequest paymentRequest,UserDetailsVO member,FeeVO feeVO){
		PaymentDetailsVO detailsVO= paymentDao.findByReceiptNo(paymentRequest.getReceiptNo());
		if(detailsVO!=null){
			//Already available in DB.
			return detailsVO.getId();
		}else{
			if (PaymentTypes.CASH.equalsIgnoreCase(paymentRequest.getPaymentMode())) {
				//cashPayments++;
				CashPaymentVO cashPaymentVO = new CashPaymentVO();
				cashPaymentVO.setReceiptNo(paymentRequest.getReceiptNo());
				cashPaymentVO.setPaidAmount(paymentRequest.getAmount());
				cashPaymentVO.setCollectedBy("MWA");
				cashPaymentVO.setPaidDate(paymentRequest.getPaidDate());
				cashPaymentVO.setMember(member);
				cashPaymentVO.setFee(feeVO);
				cashPaymentVO.setPaidBy(paymentRequest.getPaidBy());
				cashPaymentVO.setMobileNo(paymentRequest.getMobileNo());
				detailsVO = paymentDao.save(cashPaymentVO);
			} else if (PaymentTypes.CHEQUE.equalsIgnoreCase(paymentRequest.getPaymentMode())) {
				//chequePayments++;
				ChequePaymentVO chequePaymentVO = new ChequePaymentVO();
				chequePaymentVO.setReceiptNo(paymentRequest.getReceiptNo());
				chequePaymentVO.setPaidAmount(paymentRequest.getAmount());
				chequePaymentVO.setPaidDate(paymentRequest.getPaidDate());
				chequePaymentVO.setMember(member);
				chequePaymentVO.setFee(feeVO);
				chequePaymentVO.setChequeNo(paymentRequest.getChequeNo());
				detailsVO = paymentDao.save(chequePaymentVO);
			}else if (PaymentTypes.ONLINE.equalsIgnoreCase(paymentRequest.getPaymentMode())) {
				//onlinePayments++;
				OnlinePaymentVO onlinePaymentVO = new OnlinePaymentVO();
				onlinePaymentVO.setReceiptNo(paymentRequest.getReceiptNo());
				onlinePaymentVO.setPaidAmount(paymentRequest.getAmount());
				onlinePaymentVO.setPaidDate(paymentRequest.getPaidDate());
				onlinePaymentVO.setMember(member);
				onlinePaymentVO.setFee(feeVO);
				onlinePaymentVO.setTransactionId(paymentRequest.getTransactionId());
				detailsVO = paymentDao.save(onlinePaymentVO);
			}
			return detailsVO.getId();

		}
	}
}
