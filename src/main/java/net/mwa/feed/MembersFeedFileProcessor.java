package net.mwa.feed;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import net.mwa.vo.MemberDetailsVO;

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
				String firstName = "";
				String lastName = "";
				String middleName = "";
				String apartmentName = "";
				String businesName = "";
				String plotNo = row[1];
				String category = row[2];
				String roadNo = row[3];
				String noOfFamilies = row[4];
				String mobileNo = row[6];
				// use comma as separator
				memberVO = new MemberDetailsLineVO();
				String[] names = row[0].split(" ");
				System.out.println("########################### Names :: " + Arrays.toString(names));
				if (category != null) {
					if (CategoryTypes.INDEPENDENT.equalsIgnoreCase(category.trim())) {
						if (names != null && names.length == 1) {
							firstName = names[0];
							lastName = names[0];
						} else if (names != null && names.length == 2) {
							firstName = names[0];
							lastName = names[1];
						} else if (names != null && names.length == 3) {
							firstName = names[0];
							middleName = names[1];
							lastName = names[2];
						}
						memberVO.setCategoryId(1l);
					} else if (CategoryTypes.APARTMENT.equalsIgnoreCase(category.trim())) {
						apartmentName = row[2];
						memberVO.setCategoryId(2l);
						memberVO.setApartmentName(apartmentName);
					} else {
						businesName = row[2];
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
					memberVO.setRoadNo(row[5]);
					if (mobileNo != null && mobileNo.trim().length() == 10) {
						memberVO.setMobileNo(mobileNo);
					}else{
						System.out.println(mobileNo +" is invalid ##################################################");
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
		MemberDetailsVO memberRecord = new MemberDetailsVO();
		CategoryVO categoryVO=null;
		int noOfApartmentsAdded=0;
		int noOfIndependetsAdded=0;
		int noOfCommercialsAdded=0;
		for (MemberDetailsLineVO memberLineVO : membersData) {
			String plotNo = memberLineVO.getPlotNo().trim();
			memberRecord = memberDao.findByPlotNo(plotNo);
			if(memberRecord != null){
				System.out.println(memberRecord.getOwnerFirstName() +"/"+memberRecord.getMobileNo()+" Record already existed for "+plotNo);
				duplicateRecords.add(memberRecord.getOwnerFirstName() +"/"+memberRecord.getMobileNo()+" Record already existed for "+plotNo);
			}else{
				long categoryId = memberLineVO.getCategoryId();
				if (CategoryTypes.INDEPENDENT_ID == categoryId) {
					noOfIndependetsAdded++;
					memberRecord=new MemberDetailsVO();
					memberRecord.setOwnerFirstName(memberLineVO.getFirstName());
					memberRecord.setOwnerLastName(memberLineVO.getLastName());
					memberRecord.setMiddleName(memberLineVO.getMiddleName());
					memberRecord.setPlotNo(memberLineVO.getPlotNo());
					memberRecord.setRoadNo(memberLineVO.getRoadNo());
					memberRecord.setNoOfFamilies(memberLineVO.getNoOfFamilies());
					memberRecord.setMobileNo(memberLineVO.getMobileNo());
					categoryVO=new CategoryVO();
					categoryVO.setId(memberLineVO.getCategoryId());
					memberRecord.setCategory(categoryVO);
					memberDao.save(memberRecord);
				} else if (CategoryTypes.APARTMENT_ID == categoryId) {
					noOfApartmentsAdded++;
					ApartmentVO apartmentVO = new ApartmentVO();
					apartmentVO.setPresedentFirstName(memberLineVO.getFirstName());
					apartmentVO.setPresedentLastName(memberLineVO.getLastName());
					apartmentVO.setAprtmentName(memberLineVO.getApartmentName());
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
					commercialVO.setOwnerFirstName(memberLineVO.getFirstName());
					commercialVO.setOwnerLastName(memberLineVO.getLastName());
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
		List<MemberDetailsLineVO> list = parse("C:\\TRU\\git\\mwa_1\\src\\main\\resources\\feed\\members_list.csv");
		return updateDataInDB(list);
	}
	

}
