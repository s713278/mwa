package net.mwa.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.mwa.repository.FundRepository;
import net.mwa.vo.FundVO;

@Repository
public class FundDaoImpl implements FundDao {

	@Autowired
	private FundRepository fundRepository;
	
	
	public FundVO save(FundVO info) {
		return fundRepository.save(info);
	}

	public FundVO update(FundVO info) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<FundVO> findAll() {
		return fundRepository.findAll();
	}

	public FundVO findAny(Long fundId) {
		return fundRepository.findOne(fundId);
	}

	public void delete(Long fundId) {
		fundRepository.delete(fundId);;
	}

	public List<FundVO> listFundsInBrtnDates(Date startDate, String endDate) {
		return null;
	}

}
