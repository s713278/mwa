package net.mwa.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.mwa.repository.FundRepository;
import net.mwa.vo.FeeVO;

@Repository
public class FundDaoImpl implements FeeDao {

	@Autowired
	private FundRepository fundRepository;
	
	
	public FeeVO save(FeeVO info) {
		return fundRepository.save(info);
	}

	public FeeVO update(FeeVO info) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<FeeVO> findAll() {
		return fundRepository.findAll();
	}

	public FeeVO findAny(Long fundId) {
		return fundRepository.findOne(fundId);
	}

	public void delete(Long fundId) {
		fundRepository.delete(fundId);;
	}

	public List<FeeVO> listFundsInBtnDates(Date startDate, String endDate) {
		return null;
	}

	@Override
	public List<FeeVO> findByCategoryId(Long id) {
		return fundRepository.findByCategoryId(id);
	}

}
