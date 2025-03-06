package com.kh.mvc.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.kh.mvc.model.dao.UserDAO;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.util.JdbcUtil;

/**
 * Service : 비즈니스 로직 / 의사 결정 코드를 작성하는 부분
 * 			Controller 에서는 Service단의 메서드를 호출
 * 			Service에서 실질적인 동작시켜야하는 코드를 작성
 * 			=> Service단을 추가함으로 DAO는 순수하게 SQL문을 처리하는 부분만 남겨놓을 것
 */
public class MemberService {
	private UserDAO userDao = new UserDAO();

	public List<UserDTO> findAll(){
		Connection conn = JdbcUtil.getConnection();
		
		List<UserDTO> list = userDao.findAll(conn);
		
		JdbcUtil.close(conn);
		
		return list;
	};
	
	public int insertUser(UserDTO user) {
		Connection conn = JdbcUtil.getConnection();
		
		int result = userDao.insertUser(user, conn);
		
		if(result > 0) {
			JdbcUtil.commit(conn);
		}else {
			JdbcUtil.rollback(conn);
		}
		JdbcUtil.close(conn);

		return result;
	}
	
	public UserDTO selectUserNo(int userNo) {
		Connection conn = JdbcUtil.getConnection();

		UserDTO user = userDao.selectUserNo(userNo, conn);
		
		JdbcUtil.close(conn);
		
		return user;
	}
	
	public UserDTO selectUserId(String userId) {
		Connection conn = JdbcUtil.getConnection();
		
		UserDTO user = userDao.selectUserId(userId, conn);
		
		JdbcUtil.close(conn);
		
		return user;
	}
	
	public UserDTO selectUserPw(UserDTO inputUser) {
		Connection conn = JdbcUtil.getConnection();
		
		UserDTO user = userDao.selectUserPw(inputUser, conn);
		
		JdbcUtil.close(conn);
		
		return user;
	}
	
	public int updatePw(UserDTO user) {
		Connection conn = JdbcUtil.getConnection();
		
		int result = userDao.updatePw(user, conn);
		
		if(result > 0) {
			JdbcUtil.commit(conn);
		}else {
			JdbcUtil.rollback(conn);
		}
		
		JdbcUtil.close(conn);
		
		return result;
	}
	
	public int deleteUser(UserDTO user) {
		Connection conn = JdbcUtil.getConnection();
		
		int result = userDao.deleteUser(user, conn);
		
		if(result > 0) {
			JdbcUtil.commit(conn);
		}else {
			JdbcUtil.rollback(conn);
		}
		
		JdbcUtil.close(conn);
		
		return result;
	}
	
}
