package com.tt.pms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tt.pms.dto.EmployeeProjectDto;
import com.tt.pms.entities.Employee;

public interface IEmployeeRepository extends CrudRepository<Employee, Long>{


	@Override
	public List<Employee> findAll();
	
    @Query(nativeQuery=true, value="select e.first_name as firstName, e.last_name as  lastName,  count(pe.employee_id) as projectCount from employee e\r\n" + 
    		"left join project_employee pe\r\n" + 
    		"on e.employee_id = pe.employee_id group by e.first_name, e.last_name order by 3 desc") 
    public List<EmployeeProjectDto> getEmployeeProjects();
	 
}
