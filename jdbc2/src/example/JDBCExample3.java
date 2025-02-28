package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCExample3 {
	public static void main(String[] args) {
		
		// 아이디, 비밀번호, 이름을 입력받아 
		// 일치하는 사용자의 이름을 수정
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		// UPDATE는 수정된 행의 개수가 반환될 예정
		// ResultSet 필요 없음
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			String url = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
			String userName = "KH02_KKM"; 
			String password = "KH1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("수정할 아이디를 입력 : ");
			String id = sc.next();
			
			System.out.print("수정할 비밀번호를 입력 : ");
			String pw = sc.next();
			
			System.out.print("수정할 이름을 입력 : ");
			String name = sc.next();
			
			String sql = """
				UPDATE TB_USER SET
				USER_NAME = ?
				WHERE 
					USER_ID = ?
				AND USER_PW = ?
			""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("수정 성공!");
				conn.commit();
			}else {
				System.out.println("아이디 또는 비밀번호가 일치하지 않습니다~");
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
