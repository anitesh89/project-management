package com.tt.pms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tt.pms.dao.IEmployeeRepository;
import com.tt.pms.dao.IProjectRepository;
import com.tt.pms.entities.Employee;
import com.tt.pms.entities.Project;

/**
 * @author Anitesh
 *
 */
@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	IProjectRepository proRep;
	
	@Autowired
	IEmployeeRepository empRep;
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = proRep.findAll();
		model.addAttribute("projects", projects);
		return "projects/list-project";
	}

	@RequestMapping("/new")
	public String displayProjectForm(Model model) {
		
		List<Employee> allEmployees = empRep.findAll();
		Project aProject = new Project();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees",allEmployees);
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		proRep.save(project);
		//redirecting to prevent duplicate submission
		return "redirect:/projects";
	}
}
