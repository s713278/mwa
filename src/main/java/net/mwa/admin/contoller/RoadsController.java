package net.mwa.admin.contoller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import net.mwa.common.APICommonRequest;
import net.mwa.common.APICommonResponse;
import net.mwa.repository.RoadRepository;
import net.mwa.vo.RoadDetailsVO;

@RestController
public class RoadsController {

	private RoadRepository roadRepository;
	
	@PostMapping("/addRoad")
	public APICommonResponse addRoad(APICommonRequest request){
		return null;
	}
	
	@GetMapping("/getRoads")
	public List<RoadDetailsVO> getRoads(){
		return null;
	}
}
