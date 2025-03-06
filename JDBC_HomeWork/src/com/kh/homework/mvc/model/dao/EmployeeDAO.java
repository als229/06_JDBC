package com.kh.homework.mvc.model.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.homework.mvc.model.dto.EmployeeDTO;
import com.kh.homework.mvc.util.JdbcUtile;

public class EmployeeDAO {
//	FileWriter fw = null;
//	BufferedWriter bw = null; // 버퍼를 이용한 보조 스트림
	
	public List<EmployeeDTO> selectAllEmployee(Connection conn) {
		BufferedReader br = null;
		StringBuilder sb = null;

		sb = new StringBuilder();
		List<EmployeeDTO> list = new ArrayList();
		PreparedStatement pstmt = null;
		
//		sb.append("		SELECT      					");
//		sb.append("			EMP_ID     					");
//		sb.append("			, EMP_NAME 					");
//		sb.append("			, EMP_NO   					");
//		sb.append("			, EMAIL    					");
//		sb.append("		FROM        					");
//		sb.append("			EMPLOYEE   					");
//		
		// 파일을 만들어 보았다. 재미있다. 이번엔 파일을 읽어와 보자.
//		File folder = new File("src/com/kh/homework/mvc/sqlScript");
		
//		if(!folder.exists()) {
//			folder.mkdirs();
//		}
		
		try {
//			fw = new FileWriter(folder + "/" + "직원 전체 조회.txt");
//			bw = new BufferedWriter(fw);
//			bw.write(sb.toString());
			String path = "src/com/kh/homework/mvc/sqlScript/직원 전체 조회.txt";
			br = new BufferedReader(new FileReader(path)); 
			
			String temp = "";
			while(true) {
				temp = br.readLine();
				if(temp == null) {
					break;
				}
				sb.append(temp);
				sb.append("\n");
			}
			
			pstmt = conn.prepareStatement(sb.toString());
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				EmployeeDTO emp = new EmployeeDTO();
				
				emp.setEmpId(rs.getString("EMP_ID"));
				emp.setEmpName(rs.getString("EMP_NAME"));
				emp.setEmpNo(rs.getString("EMP_NO"));
				emp.setEmail(rs.getString("EMAIL"));
				
				list.add(emp);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtile.close(pstmt);
			JdbcUtile.close(br);
		}
		
		return list;
	}
	
	public int insertEmployee(EmployeeDTO emp , Connection conn) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		FileReader fr = null;
		PreparedStatement pstmt = null;
		String temp = "";
		
		int result = 0;
		
		try {
			fr = new FileReader("src/com/kh/homework/mvc/sqlScript/직원 추가.txt");
			br = new BufferedReader(fr);
			
			while(true) {
				
				temp = br.readLine();
				
				if(temp == null) {
					break;
				}
				sb.append(temp);
			}
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpNo());
			pstmt.setString(4, emp.getEmail());
			pstmt.setString(5, emp.getPhone());
			pstmt.setString(6, emp.getJobCode());
			pstmt.setString(7, emp.getSalLevel());
			
			result = pstmt.executeUpdate();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			JdbcUtile.close(pstmt);
			JdbcUtile.close(br);
			JdbcUtile.close(fr);
		}
		return result;
	}
	
	public EmployeeDTO selectEmpNoEmployee(String empId , Connection conn) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		FileReader fr = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String temp = "";
		EmployeeDTO emp = null;
		
		try {
			br = new BufferedReader(new FileReader("src/com/kh/homework/mvc/sqlScript/직원 번호로 조회.txt"));
		
			while(true) {
				
				temp = br.readLine();
				
				if(temp == null) {
					break;
				}
				
				sb.append(temp);
			}
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, empId);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				emp = new EmployeeDTO();
				emp.setEmpId(rs.getString("EMP_ID"));
				emp.setEmpName(rs.getString("EMP_NAME"));
				emp.setEmpNo(rs.getString("EMP_NO"));
				emp.setEmail(rs.getString("EMAIL"));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			JdbcUtile.close(pstmt);
			JdbcUtile.close(br);
			JdbcUtile.close(fr);
		}
		return emp;
	}
	
	public int updateEmail(String email, String empId, Connection conn) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		FileReader fr = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String temp = "";
		
		try {
			br = new BufferedReader(new FileReader("src/com/kh/homework/mvc/sqlScript/이메일 수정.txt"));
			
			while(true) {
				
				temp = br.readLine();
				
				if(temp == null) {
					break;
				}
				sb.append(temp);
			}
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, email);
			pstmt.setString(2, empId);
			
			result = pstmt.executeUpdate();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtile.close(pstmt);
			JdbcUtile.close(br);
			JdbcUtile.close(fr);
		}
		
		return result;
	}
	
	public int deleteEmp(String empId, Connection conn) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		FileReader fr = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String temp = "";
		
		try {
			br = new BufferedReader(new FileReader("src/com/kh/homework/mvc/sqlScript/직원 삭제.txt"));
			
			while(true) {
				
				temp = br.readLine();
				
				if(temp == null) {
					break;
				}
				sb.append(temp);
			}
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, empId);
			
			result = pstmt.executeUpdate();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtile.close(pstmt);
			JdbcUtile.close(br);
			JdbcUtile.close(fr);
		}
		
		return result;
	}
	
}
