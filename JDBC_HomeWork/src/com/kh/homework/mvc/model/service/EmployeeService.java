package com.kh.homework.mvc.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.homework.mvc.model.dao.EmployeeDAO;
import com.kh.homework.mvc.model.dto.EmployeeDTO;
import com.kh.homework.mvc.util.JdbcUtile;

public class EmployeeService {

	private EmployeeDAO employeeDAO = new EmployeeDAO();
	
	public List<EmployeeDTO> selectAllEmployee(){
		Connection conn = JdbcUtile.getConnection();

		List<EmployeeDTO> list = employeeDAO.selectAllEmployee(conn);
		
		JdbcUtile.close(conn);

		return list;
	}
	
	public int insertEmployee(EmployeeDTO emp) {
		Connection conn = JdbcUtile.getConnection();
		
		int result = employeeDAO.insertEmployee(emp, conn);
		
		JdbcUtile.close(conn);
		
		return result;
	}
	
	public EmployeeDTO selectEmpNoEmployee(String empId) {
		Connection conn = JdbcUtile.getConnection();
		
		EmployeeDTO rEmp = employeeDAO.selectEmpNoEmployee(empId, conn);
		
		JdbcUtile.close(conn);
		return rEmp;
	}
	
	
	public int updateEmail(String email, String empId) {
		Connection conn = JdbcUtile.getConnection();
		
		int result = employeeDAO.updateEmail(email, empId, conn);
		
		JdbcUtile.close(conn);
		
		return result;
	}
	
	public int deleteEmp(String empId) {
		Connection conn = JdbcUtile.getConnection();
		
		int result = employeeDAO.deleteEmp(empId, conn);
		
		JdbcUtile.close(conn);
		
		return result;
	}
}
