/**
 * 
 */
package com.tt.pms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tt.pms.dao.IEmployeeRepository;
import com.tt.pms.entities.Employee;

/**
 * @author Anitesh
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	IEmployeeRepository empRep;
	
	@GetMapping
	public String displayEmployees(Model model) {
		List<Employee> employees = empRep.findAll();
		model.addAttribute("employees", employees);
		return "employees/list-employee";
	}

	@RequestMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employees/new-employee";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String addNewEmployee(Employee employee, Model model) {
		empRep.save(employee);
		return "redirect:/employee/new";
	}
}
