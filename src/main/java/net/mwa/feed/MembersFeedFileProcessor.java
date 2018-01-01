package net.mwa.feed;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import net.mwa.common.CategoryTypes;
import net.mwa.dao.MemberDao;
import net.mwa.vo.ApartmentVO;
import net.mwa.vo.CategoryVO;
import net.mwa.vo.CommercialVO;
import net.mwa.vo.IndependentHouseVO;
import net.mwa.vo.UserDetailsVO;

@Component
@Qualifier("MembersFeedFileProcessor")
public class MembersFeedFileProcessor implements FeedFileParser<MemberDetailsLineVO> {

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public List<MemberDetailsLineVO> parse(String inputFile) {
		List<MemberDetailsLineVO> list = new ArrayList<MemberDetailsLineVO>();
		BufferedReader br = null;
		String line = "";
		MemberDetailsLineVO memberVO = null;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (i == 0) {
					i++;
					continue;
				}
				String[] row = line.split(CSV_SEPARATOR);
				String firstName = row[0];
				String middleName = row[1];
				String lastName = row[2];
				String plotNo = row[3];
				String category = row[4];
				String roadNo = row[5];
				String noOfFamilies = row[6];
				String otherName = row[7];
				String mobileNo = row[8];
				
				String apartmentName="";
				String businesName ="";
				// use comma as separator
				memberVO = new MemberDetailsLineVO();
				if (category != null) {
					if (CategoryTypes.INDEPENDENT.equalsIgnoreCase(category.trim())) {
						memberVO.setCategoryId(1l);
					} else if (CategoryTypes.APARTMENT.equalsIgnoreCase(category.trim())) {
						apartmentName = otherName;
						memberVO.setCategoryId(2l);
						memberVO.setApartmentName(apartmentName);
					} else if (CategoryTypes.COMMERCIAL.equalsIgnoreCase(category.trim())) {
						businesName = otherName;
						memberVO.setCategoryId(3l);
						memberVO.setBusinesName(businesName);
					}
					memberVO.setFirstName(firstName);
					memberVO.setLastName(lastName);
					memberVO.setMiddleName(middleName);
					memberVO.setCategory(category);
					memberVO.setPlotNo(plotNo);
					memberVO.setRoadNo(roadNo);
					try {
						memberVO.setNoOfFamilies(Integer.valueOf(noOfFamilies));
					} catch (NumberFormatException e) {
						memberVO.setNoOfFamilies(1);
					}
					if (mobileNo != null && mobileNo.trim().length() == 10) {
						memberVO.setMobileNo(mobileNo);
					}else{
						memberVO.setMobileNo(DEFAULT_MOBILE_NO);
					}
					list.add(memberVO);
					memberVO = null;
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> updateDataInDB(List<MemberDetailsLineVO> membersData) {
		Map<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
		List<String> duplicateRecords=new ArrayList<>();
		CategoryVO categoryVO=null;
		int noOfApartmentsAdded=0;
		int noOfIndependetsAdded=0;
		int noOfCommercialsAdded=0;
		for (MemberDetailsLineVO memberLineVO : membersData) {
			String plotNo = memberLineVO.getPlotNo().trim();
			UserDetailsVO memberRecord = memberDao.findByPlotNo(plotNo);
			if(memberRecord != null){
				duplicateRecords.add(memberRecord.getPlotNo());
			}else{
				long categoryId = memberLineVO.getCategoryId();
				if (CategoryTypes.INDEPENDENT_ID == categoryId) {
					noOfIndependetsAdded++;
					IndependentHouseVO individualVO=new IndependentHouseVO();
					individualVO.setFirstName(memberLineVO.getFirstName());
					individualVO.setLastName(memberLineVO.getLastName());
					individualVO.setMiddleName(memberLineVO.getMiddleName());
					individualVO.setPlotNo(memberLineVO.getPlotNo());
					individualVO.setRoadNo(memberLineVO.getRoadNo());
					individualVO.setNoOfFamilies(memberLineVO.getNoOfFamilies());
					individualVO.setMobileNo(memberLineVO.getMobileNo());
					categoryVO=new CategoryVO();
					categoryVO.setId(memberLineVO.getCategoryId());
					individualVO.setCategory(categoryVO);
					memberDao.save(individualVO);
				} else if (CategoryTypes.APARTMENT_ID == categoryId) {
					noOfApartmentsAdded++;
					ApartmentVO apartmentVO = new ApartmentVO();
					apartmentVO.setAprtmentName(memberLineVO.getApartmentName());
					apartmentVO.setFirstName(memberLineVO.getFirstName());
					apartmentVO.setLastName(memberLineVO.getLastName());
					apartmentVO.setPresidentName(memberLineVO.getFirstName()+" "+memberLineVO.getLastName());
					apartmentVO.setPlotNo(memberLineVO.getPlotNo());
					apartmentVO.setRoadNo(memberLineVO.getRoadNo());
					apartmentVO.setNoOfFamilies(memberLineVO.getNoOfFamilies());
					apartmentVO.setMobileNo(memberLineVO.getMobileNo());
					categoryVO=new CategoryVO();
					categoryVO.setId(memberLineVO.getCategoryId());
					apartmentVO.setCategory(categoryVO);
					apartmentVO.setPresedentMobileNo(memberLineVO.getMobileNo());
					memberDao.save(apartmentVO);
				} else if (CategoryTypes.COMMERCIAL_ID == categoryId) {
					noOfCommercialsAdded++;
					CommercialVO commercialVO = new CommercialVO();
					commercialVO.setFirstName(memberLineVO.getFirstName());
					commercialVO.setLastName(memberLineVO.getLastName());
					commercialVO.setBusinessName(memberLineVO.getFirstName());
					 categoryVO=new CategoryVO();
					categoryVO.setId(memberLineVO.getCategoryId());
					commercialVO.setCategory(categoryVO);
					commercialVO.setPlotNo(memberLineVO.getPlotNo());
					commercialVO.setMobileNo(memberLineVO.getMobileNo());
					memberDao.save(commercialVO);
				}
			}
		}
		resultMap.put("NO_OF_COMMERCIALS_ADDED", noOfCommercialsAdded);
		resultMap.put("NO_OF_INDEPENDETS_ADDED", noOfIndependetsAdded);
		resultMap.put("DUPLICATE_RECORDS", duplicateRecords);
		resultMap.put("NO_OF_APARTMENTS_ADDED", noOfApartmentsAdded);
		return resultMap;
	}

	@Override
	public Map<String, Object> processFile() {
		List<MemberDetailsLineVO> list = parse("C:\\SwamyAll\\swamy\\Swamy1\\Mayurinagar\\11_CC Cameras\\Data\\memebrs_list_1.0.csv");
		return updateDataInDB(list);
	}
	

}
