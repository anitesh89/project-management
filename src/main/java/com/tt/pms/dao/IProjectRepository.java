package com.tt.pms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tt.pms.dto.ChartDataDto;
import com.tt.pms.entities.Project;

public interface IProjectRepository extends CrudRepository<Project, Long>{
	
	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery = true, value="select stage as label, count(*) as value from project group by stage")
	public List<ChartDataDto> getProjectStatsCount();
	
}
