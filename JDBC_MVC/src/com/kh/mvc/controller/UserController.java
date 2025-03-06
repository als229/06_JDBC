package com.kh.mvc.controller;

import java.util.List;

import com.kh.mvc.model.dao.UserDAO;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.model.service.MemberService;

/**
 * VIEW 에서 온 요청을 처리해주는 클래스입니다.
 * 메서드로 전달된 데이터값을 가공처리한 후 DAO로 전달합니다
 * DAO로부터 반환받은 결과를 사용자가 보게될 View(응답화면)에 반환합니다.
 */
public class UserController {

	private MemberService userService = new MemberService();
	
	public List<UserDTO> findAll() {
//		return userDao.findAll();
		return userService.findAll();
	}
	
	public int insertUser(String userId, String userPw, String userName) {
		// requestParameter
		// 영속성 작업을 위해 값을 넘김
		UserDTO user = new UserDTO();
		
		user.setUserId(userId);
		user.setUserPw(userPw);
		user.setUserName(userName);
		
		return userService.insertUser(user);
	}
	
	public UserDTO selectUserNo(int userNo) {
		return userService.selectUserNo(userNo);
	}
	
	public UserDTO selectUserId(String userId) {
		return userService.selectUserId(userId);
	}
	
	public UserDTO selectUserPw(String userId , String userPw) {
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		user.setUserPw(userPw);
		
		return userService.selectUserPw(user);
	}
	
	public int updatePw(String userId, String userPw) {
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		user.setUserPw(userPw);
		return userService.updatePw(user);
	}
	
	public int deleteUser(String userId, String userPw) {
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		user.setUserPw(userPw);
		
		int result = userService.deleteUser(user);
		
		return result;
	}
	
}
