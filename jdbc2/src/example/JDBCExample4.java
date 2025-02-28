package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCExample4 {

	public static void main(String[] args) {
		
		// 아이디, 비밀번호, 새 비밀번호를 입력 받아
		// 아이디, 비밀번호가 일치하는 회원의 비밀번호 변경
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			String url = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
			String userName = "KH02_KKM"; 
			String password = "KH1234";
			
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("아이디 입력 : ");
			String id = sc.next();

			System.out.println("비밀번호 입력 : ");
			String pw = sc.next();
			
			System.out.println("바꿀 비밀번호 입력 : ");
			String newPw = sc.next();
			
			String sql = "UPDATE TB_USER SET USER_PW = ? WHERE USER_ID = ? AND USER_PW = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPw);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("수정 성공~");
				conn.commit();
			}else {
				System.out.println("아이디 비번 일치 X");
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
