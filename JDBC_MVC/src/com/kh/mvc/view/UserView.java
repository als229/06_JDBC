package com.kh.mvc.view;

import java.util.List;
import java.util.Scanner;

import com.kh.mvc.controller.UserController;
import com.kh.mvc.model.dto.UserDTO;

/**
 * 	MemberView 클래스는 JDBC 실습을 위해 생성하였으며,
 *  사용자에게 입력 및 출력을 수행하는 메서드를 제공합니다.
 *  
 *  @author : 종로 C강의장
 *  @version : 0.1
 *  @date : 2025-03-04
 */
public class UserView {

	private Scanner sc = new Scanner(System.in);
	private UserController userController = new UserController();
	
	/**
	 *  프로그램 시작 시 사용자에게 보여줄 메인 화면을 출력해주는 메서드입니다.
	 */
	
	/*
	 * 	1. 회원 번호를 가지고 단일회원 조회
		2. 회원 아이디 검색으로 조회
		3. 비밀번호 수정 => 회원 번호로 수정
		4. 회원 삭제 => 회원 번호로 삭제
	 */
	public void mainMenu() {
		
		while(true) {
			System.out.println("=== USER 테이블 관리 프로그램 ===");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 회원 추가");
			System.out.println("3. 회원 검색 (회원번호로)");
			System.out.println("4. 회원 검색 (아이디로)");
			System.out.println("5. 비밀번호 수정");
			System.out.println("6. 회원 삭제");
			System.out.println("9. 프로그램 종료");
			System.out.println("이용할 메뉴를 선택해주세요 > ");
			
			int menuNo = sc.nextInt();
			sc.nextLine();
			
			switch(menuNo) {
			case 1 : findAll(); break;
			case 2 : insertUser(); break;
			case 3 : selectUserNo(); break;
			case 4 : selectUserId(); break;
			case 5 : updatePw(); break;
			case 6 : deleteUser(); break;
			case 9 : System.out.println("프로그램 종료 ~"); return;
			default : System.out.println("잘못된 메뉴 선택입니다.");
			}
		}
	}
	
	// 회원 전체 정보를 조회해주는 기능
	private void findAll() {
		
		System.out.println("\n === 회원 전체 목록입니다~ ===");
		
		List<UserDTO> list = userController.findAll();
		
		System.out.println("\n조회된 총 회원의 수는 " + list.size() + "명 입니다.");
		
		// 조건식은 큰거 먼저 쓰는게 좋다
		// 그리고 부정적으로 쓰는게 좋다
		if (!list.isEmpty()) {
			
			System.out.println("======================================================================");
			for(UserDTO user : list) {
				System.out.println(user.getUserName() + "님의 정보~");
				System.out.println("아이디 : " + user.getUserId());
				System.out.println("가입일 : " + user.getEnrollDate());
				System.out.println();
			}
			System.out.println("======================================================================");
			
		} else {
			System.out.println("회원이 존재하지 않습니다.");
		}
		
	}
	
	/**
	 * TB_USER에 INSERT할 값을 사용자에게 입력받도록 유도하는 화면
	 */
	private void insertUser() {
		
		System.out.println("=== 회원 추가 ===");
		
		System.out.println("아이디를 입력하세요 > ");
		String userId = sc.nextLine();
		// UNIQUE 했다고 치고 입력받은 아이디 가지고 DB가서 WHERE 조건절 에다가 사용자가 입력한 아이디 넣어서 
		// 조회 결과 있으면 중복금지 처리
		// 정규식
		// 길이제한 30바이트

		System.out.println("비밀번호를 입력하세요 > ");
		String userPw = sc.nextLine();
		
		System.out.println("이름을 입력하세요 > ");
		String userName = sc.nextLine();
		
		int num = userController.insertUser(userId, userPw, userName);
		
		if(num != 0) {
			System.out.println("성공!");
		}else {
			System.out.println("실패!");
		}
	}
	
	/**
	 * 유저 번호로 단일 조회
	 */
	private void selectUserNo() {
		
		System.out.println("=== 회원 번호로 조회 ===");
		System.out.println("회원 번호를 입력하세요 > ");
		int userNo = sc.nextInt();
		sc.nextLine();
		
		UserDTO user = userController.selectUserNo(userNo);

		System.out.println("======================================================================");
		System.out.println();
		
		if (user != null) {
			System.out.println(user.getUserName() + "님의 정보~");
			System.out.println("아이디 : " + user.getUserId());
			System.out.println("가입일 : " + user.getEnrollDate());
		}else {
			System.out.println("검색한 회원 번호의 회원이 없슴당~");
		}
		
		System.out.println();
		System.out.println("======================================================================");
	}
	
	/**
	 * 회원 검색 (아이디로)
	 */
	private void selectUserId() {
		System.out.println("=== 회원 아이디로 조회 ===");
		System.out.println("회원 아이디를 입력하세요 > ");
		String userId = sc.nextLine();

		UserDTO user = userController.selectUserId(userId);
		
		System.out.println("======================================================================");
		System.out.println();
		
		if (user != null) {
			System.out.println(user.getUserName() + "님의 정보~");
			System.out.println("아이디 : " + user.getUserId());
			System.out.println("가입일 : " + user.getEnrollDate());
		}else {
			System.out.println("검색한 아이디의 회원이 없슴당~");
		}
		
		System.out.println();
		System.out.println("======================================================================");
	}
	
	/**
	 * 비밀번호 수정~ (userId 로 하장)
	 */
	private void updatePw() {
		boolean flag = true;
		String userId = "";
		
		System.out.println("=== 회원 아이디로 비밀번호 변경 ===");
		
		do{
			System.out.println("회원 아이디를 입력하세요 > ");
			userId = sc.nextLine();
			
			UserDTO userById = userController.selectUserId(userId);
			
			if (userById == null) {
				System.out.println("검색한 아이디가 없어유 ~ ");
				continue;
			}
			
			System.out.println("회원 비밀번호를 입력하세요 > ");
			String userPw = sc.nextLine();
			
			UserDTO userByPw = userController.selectUserPw(userId, userPw);
			
			if (userByPw == null) {
				System.out.println("비밀번호가 틀려유 ");
				continue;
			}
			
			flag = false;
		}while(flag);
		
		System.out.println("변경할 비밀번호를 입력해 주세유 > ");
		String newPw = sc.nextLine();
		int result = userController.updatePw(userId, newPw);
		
		if(result > 0) {
			System.out.println("수정 성공~");
		}else {
			System.out.println("수정 실패~~");
		}
	}
	
	/**
	 * 유저 삭제
	 */
	private void deleteUser() {
		boolean flag = true;
		String userId = "";
		String userPw = "";
		
		int result = 0;
		
		System.out.println("=== 회원 아이디로 비밀번호 변경 ===");
		
		do{
			System.out.println("회원 아이디를 입력하세요 > ");
			userId = sc.nextLine();
			
			UserDTO userById = userController.selectUserId(userId);
			
			if (userById == null) {
				System.out.println("검색한 아이디가 없어유 ~ ");
				continue;
			}
			
			System.out.println("회원 비밀번호를 입력하세요 > ");
			userPw = sc.nextLine();
			
			UserDTO userByPw = userController.selectUserPw(userId, userPw);
			
			if (userByPw == null) {
				System.out.println("비밀번호가 틀려유 ");
				continue;
			}
			
			flag = false;
		}while(flag);
		
		System.out.println("정말 삭제 할거에유?");
		String yn = sc.nextLine().toUpperCase();
		
		if(yn.equals("Y")) {
			result = userController.deleteUser(userId, userPw);
			System.out.println("삭제 성공~~");
		}else {
			System.out.println("삭제 취소~");
			return;
		}
	}
}
