package com.kh.homework.mvc.controller;

import java.util.List;

import com.kh.homework.mvc.model.dto.EmployeeDTO;
import com.kh.homework.mvc.model.service.EmployeeService;

public class EmployeeController {
	private EmployeeService employeeService = new EmployeeService();
	
	public List<EmployeeDTO> selectAllEmployee(){
		
		List<EmployeeDTO> list = employeeService.selectAllEmployee();
		
		return list;
	}
	
	public int insertEmployee(EmployeeDTO emp) {
		
		int result = employeeService.insertEmployee(emp);
		
		return result;
	}
	
	public EmployeeDTO selectEmpNoEmployee(String empId) {
		
		EmployeeDTO rEmp = employeeService.selectEmpNoEmployee(empId);
		
		return rEmp;
	}
	
	public int updateEmail(String email, String empId) {
		
		int result = employeeService.updateEmail(email,empId);
		
		return result;
	}
	
	public int deleteEmp(String empId) {
		
		int result = employeeService.deleteEmp(empId);
		
		return result;
	}
	
	
}
