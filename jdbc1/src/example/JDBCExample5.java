package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample5 {

	public static void main(String[] args) {
		// 부서명을 입력 받아
		// 해당 부서에 근무하는 사원의
		// 사번, 이름, 부서명, 직급명을 
		// 직급코드 내림 차순으로 조회
		
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			String type = "jdbc:oracle:thin:@";
			String host = "112.221.156.34";
			String port = ":12345";
			String dbName = ":XE";
			String userName = "KH02_KKM";
			String password = "KH1234";
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE", userName, password);
			
			StringBuilder sb = new StringBuilder();
			System.out.println("=== 부서명을 입력 받아 해당 부서에 근무하는 사원 조회 ===");
			System.out.println("부서명 입력 : ");
			String deptName = sc.next();
			
			sb.append("SELECT E.EMP_NO, E.EMP_NAME, D.DEPT_TITLE, J.JOB_NAME  ");
			sb.append("FROM EMPLOYEE E ");
			sb.append("JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)  ");
			sb.append("JOIN JOB J ON (E.JOB_CODE = J.JOB_CODE) ");
			sb.append("WHERE DEPT_TITLE =  '");
			sb.append(deptName);
			sb.append("' ORDER BY DEPT_CODE DESC");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sb.toString());
			
			while(rs.next()) {
				String empNo = rs.getString("EMP_NO");
				String empName = rs.getString("EMP_NAME");
				String outDeptName = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				
				System.out.printf("사번 : %s \t 이름 : %s \t 부서명 : %s \t 직급명 : %s \t\n", empNo, empName, outDeptName, jobName);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				if(sc != null) sc.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

}
