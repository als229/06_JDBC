package com.kh.mvc.run;

import com.kh.mvc.view.UserView;

public class Run {

	public static void main(String[] args) {
		
		// Entry point : 프로그램이 시작되는 시작점
		// Composition : 합성, java에서는 상속보단 Composition을 권장
		
		/*
		 * Model 		: 데이터 관련된 모든 작업 
		 * View 		: 화면 입 / 출력 (얘는 힘 안줄 것)
		 * Controller	: View에서의 요청을 받아서 처리해주는 역할
		 */
		
		new UserView().mainMenu();
		
		
	}

}
