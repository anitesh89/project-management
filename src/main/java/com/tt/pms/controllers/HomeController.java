package com.tt.pms.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tt.pms.dao.IEmployeeRepository;
import com.tt.pms.dao.IProjectRepository;
import com.tt.pms.dto.ChartDataDto;
import com.tt.pms.dto.EmployeeProjectDto;
import com.tt.pms.entities.Project;

@Controller
public class HomeController {
	
	Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	IProjectRepository proRepo;
	
	@Autowired
	IEmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		Map<String, Object> map = new HashMap<>();
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);
		
		List<ChartDataDto> projectData = proRepo.getProjectStatsCount();
		
		//convert projectData list into json structure to be used in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		log.info("chart data ----------- "+jsonString);
		
		model.addAttribute("projectStatusCount", jsonString);
		
		List<EmployeeProjectDto> employeesProjectCount = empRepo.getEmployeeProjects();
		model.addAttribute("employeeListProjectsCount", employeesProjectCount);
		
		return "main/home";
	}
}
