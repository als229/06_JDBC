package com.kh.homework.mvc.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.kh.homework.mvc.controller.EmployeeController;
import com.kh.homework.mvc.model.dto.EmployeeDTO;

public class EmployeeView {
	
	private EmployeeController employeeController = new EmployeeController();
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("=== USER 테이블 관리 프로그램 ===");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 회원 추가");
			System.out.println("3. 회원 검색 (사원번호로)");
			System.out.println("4. EMAIL 수정");
			System.out.println("5. 회원 삭제");
			System.out.println("9. 프로그램 종료");
			System.out.println("이용할 메뉴를 선택해주세요 > ");
		
			try {
				int menuNum = Integer.parseInt(br.readLine());
				
				switch(menuNum) {
				case 1 : selectAllEmployee(); break;
				case 2 : insertEmployee(); break;
				case 3 : selectEmpNoEmployee(); break;
				case 4 : updateEmail();break;
				case 5 : deleteEmp();break;
				case 9 : return; 
				default : System.out.println("잘못 입력 데쓰"); break;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private void selectAllEmployee() {
		System.out.println("=== 직원 전체 조회 ===");
		
		List<EmployeeDTO> list = employeeController.selectAllEmployee();
		
		if(list.isEmpty()) {
			System.out.println("사원이 한 명도 없네유~");
		}else {
			System.out.println("총 사원 : " + list.size());
			for(EmployeeDTO emp : list) {
				System.out.printf("%s \t/\t%s \t/\t %s \t/\t%s\t\n", emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail());
			}
		}
	}
	
	private void insertEmployee() throws IOException {
		System.out.println("=== 직원 추가! ===");
		
		System.out.println("직원 아이디를 입력하세유 ");
		String empId = br.readLine();
		
		System.out.println("직원 이름을 입력하세유 ");
		String empName = br.readLine();
		
		System.out.println("직원 번호를 입력하세유 ");
		String empNo = br.readLine();
		
		System.out.println("이메일을 입력하세유 ");
		String email = br.readLine();
		
		System.out.println("전화번호를 입력하세유 ");
		String phone = br.readLine();
		
		System.out.println("자브코드를 입력하세유 ");
		String jobCode = br.readLine();
		
		System.out.println("살레벨을 입력하세유 ");
		String salLevel = br.readLine();
		
		EmployeeDTO emp = new EmployeeDTO();
		emp.setEmpId(empId);
		emp.setEmpName(empName);
		emp.setEmpNo(empNo);
		emp.setEmail(email);
		emp.setPhone(phone);
		emp.setJobCode(jobCode);
		emp.setSalLevel(salLevel);
		
		int result = employeeController.insertEmployee(emp);
		
		if(result > 0) {
			System.out.println("성공~");
		}else {
			System.out.println("실패 ~");
		}
		
	}
	
	private void selectEmpNoEmployee() {
		
		System.out.println("=== 직원 번호로 조회하기 ===");
		System.out.println("직원 번호를 입력하세유 ");
		try {
			String empId = br.readLine();
			
			EmployeeDTO emp = employeeController.selectEmpNoEmployee(empId);
			
			if(emp == null) {
				System.out.println("사원이 한 명도 없네유~");
			}else {
				System.out.printf("%s \t/\t%s \t/\t %s \t/\t%s\t\n", emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void updateEmail() {
		
		System.out.println("=== 비밀번호 수정 ===");
		
		System.out.println("수정할 사원 번호를 입력 해주세유");
		
		try {
			String empId = br.readLine();
			
			EmployeeDTO emp = employeeController.selectEmpNoEmployee(empId);
			
			if(emp == null) {
				System.out.println(empId + "번 사원이 없네유~");
				return;
			}
			
			System.out.println("바꿀 이메일을 입력해주세유 ");
			String email = br.readLine();
			
			int result = employeeController.updateEmail(email, empId);
			
			if(result > 0) {
				System.out.println("성공~");
			}else {
				System.out.println("실퓨페페페1!!~");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteEmp() throws IOException {
		
		System.out.println("삭제할 사원 번호를 입력해 주세유");
		
			
		String empId = br.readLine();
		
		EmployeeDTO emp = employeeController.selectEmpNoEmployee(empId);
		
		if(emp == null) {
			System.out.println(empId + "번 사원이 없네유~");
			return;
		}
		
		int result = employeeController.deleteEmp(empId);
		
		if(result > 0){
			System.out.println("삭제 성공~");
		}else {
			System.out.println("삭제 실패!!!~");
		}
	}
}
