package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample2 {

	public static void main(String[] args) {
		
		// EMPLOYEE 테이블에서 모든 사원의
		// 사번, 이름, 급여 조회
		// 급여 오름차순으로 조회
		
		Connection conn = null; // DB 연결 정보 저장, 연결하는 객체
		Statement stmt = null; // SQL 수행, 결과 반환 받는 객체
		ResultSet rs = null; // SELECT 수행 결과 저장 객체
		
		try {
			// Oracle JDBC Driver를 메모리에 적재 = 객체로 만듬
			Class.forName("oracle.jdbc.OracleDriver");
			
			// DB 연결 정보 작성
			String type = "jdbc:oracle:thin:@";
			String host = "112.221.156.34";
			String port = ":12345";
			String dbName = ":XE";
			String userName = "KH02_KKM";
			String password = "KH1234";
			
			// Connection 객체 생성해서 얻어오기
			conn = DriverManager.getConnection(type+host+port+dbName, userName, password);
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT EMP_ID, EMP_NAME, SALARY ");
			sb.append("FROM EMPLOYEE ");
			sb.append("ORDER BY SALARY ASC");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sb.toString());
			
			// rs.next() : ResultSet 의 Cursor를 다음 행으로 이동
			// 다음 행이 있으면 true 없으면 false
			while(rs.next()) {
				String empId 	= rs.getString("EMP_ID");
				String empName 	= rs.getString("EMP_NAME");
				int salary 		= rs.getInt("SALARY");
				
				System.out.printf("%s /\t %s /\t %s \n", empId, empName, salary);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
				try {
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
