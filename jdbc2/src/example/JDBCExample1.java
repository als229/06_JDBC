package example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample1 {
	public static void main(String[] args) {
		
		/* 입력 받은 아이디가 포함된 사용자의
		 * 사용자 번호 , 아이디, 이름, 가입일 조회
		 * 회원 번호 오름차순으로 조회(SELECT)
		 */
		
		Connection conn = null; // DB 연결 정보를 가지고 연결하는 객체
		Statement stmt = null; // SQL 수행, 결과 반환 받는 객체
		ResultSet rs = null; // SELECT 결과를 저장하고 1행씩 접근하는 객체
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			// 내 컴퓨터 DB 연결
			//jdbc:oracle:thin:@localhost:1521:XE
			// XE자리는 대소문자 안가림.
			
			// 학원 DB 서버 URL
			// - jdbc 드라이버가 어떤 데이터베이스에 연결할 지 지정
			String url = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
			String userName = "KH02_KKM"; // 사용자 계정명
			String password = "KH1234"; // 사용자 비밀번호
			
			conn = DriverManager.getConnection(url, userName, password);
			stmt = conn.createStatement();
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("검색할 아이디 입력 :");
			String inputId = sc.next();
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("		SELECT                  					");
			sb.append("		USER_NO                						");
			sb.append("		, USER_ID              						");
			sb.append("		, USER_NAME            						");
			sb.append("		, ENROLL_DATE          						");
			sb.append("		FROM TB_USER            					");
			sb.append("		WHERE USER_ID LIKE '%" + inputId + "%' 		");
			sb.append("		ORDER BY USER_NO ASC   						");
			
			rs = stmt.executeQuery(sb.toString());
			
			while(rs.next()) { // 커서를 다음 행으로 이동
				int opUserNo = rs.getInt("USER_NO"); 
				String opUserId = rs.getString("USER_ID");
				String opUserName = rs.getString("USER_NAME");
				Date opEnrollDate = rs.getDate("ENROLL_DATE");
				// java.sql.Date opEnroolDate = ~~ 이렇게도 사용 가능
				// java.sql.Date : DB의 Date 타입을 저장하는 클래스
				
				System.out.printf("%d /\t %s /\t %s /\t %s \t\n", opUserNo, opUserId, opUserName, opEnrollDate.toString());
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
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
