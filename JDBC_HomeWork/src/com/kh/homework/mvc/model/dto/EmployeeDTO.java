package com.kh.homework.mvc.model.dto;

import java.sql.Date;

public class EmployeeDTO {
	
	private String empId;
	private String empName;
	private String email;
	private String phone;
	private String managerId;
	private String empNo;
	private String depteCode;
	private String jobCode;
	private String salLevel;
	private String entYn;
	private Date hireDate;
	private Date entDate;
	private long salary;
	private float bonus;
	
	public EmployeeDTO() {
		super();
	}
	
	public EmployeeDTO(String empId, String empName, String email, String phone, String managerId, String empNo,
			String depteCode, String jobCode, String salLevel, String entYn, Date hireDate, Date entDate, long salary,
			float bonus) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.email = email;
		this.phone = phone;
		this.managerId = managerId;
		this.empNo = empNo;
		this.depteCode = depteCode;
		this.jobCode = jobCode;
		this.salLevel = salLevel;
		this.entYn = entYn;
		this.hireDate = hireDate;
		this.entDate = entDate;
		this.salary = salary;
		this.bonus = bonus;
	}

	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getDepteCode() {
		return depteCode;
	}
	public void setDepteCode(String depteCode) {
		this.depteCode = depteCode;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getSalLevel() {
		return salLevel;
	}
	public void setSalLevel(String salLevel) {
		this.salLevel = salLevel;
	}
	public String getEntYn() {
		return entYn;
	}
	public void setEntYn(String entYn) {
		this.entYn = entYn;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public Date getEntDate() {
		return entDate;
	}
	public void setEntDate(Date entDate) {
		this.entDate = entDate;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public float getBonus() {
		return bonus;
	}
	public void setBonus(float bonus) {
		this.bonus = bonus;
	}

}
