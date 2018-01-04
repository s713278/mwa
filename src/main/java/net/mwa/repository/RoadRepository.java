package net.mwa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.mwa.vo.RoadDetailsVO;

public interface RoadRepository extends CrudRepository<RoadDetailsVO, Long> {

	List<RoadDetailsVO> findByPriority(Long id);

	List<RoadDetailsVO> findByRoadWidthInFeets(Long id);
	
}
