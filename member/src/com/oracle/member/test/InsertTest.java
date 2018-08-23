/**
 * 
 */
package com.oracle.member.test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

import com.oracle.member.dao.MemberDAO;
import com.oracle.member.dao.MemberDAOImpl;
import com.oracle.member.vo.MemberVO;

/**
 * @author a 회원 정보 삽입 Create Row단위 테스트(Unit test)
 */
public class InsertTest {

	/**
	 * @param args
	 * @throws Exception 예외처리
	 */
	public static void main(String[] args) throws Exception {

		// 1. DAO 객체 생성
		MemberDAO dao = MemberDAOImpl.getInstance();

//		// 2. 회정정보 준비(입력자료 준비)
		MemberVO member = new MemberVO();

		member.setId("uhbyj1235");
		member.setPassword("asdf1232");
		member.setName("이정후");
		member.setGender("남");
		member.setEmail("asdf@asdfasdf.com");
		member.setPhone("010-3584-7424");
		member.setTel("02-2234-2234");
		member.setZip("12245");
		member.setAddress("경기도 고양시 일산서구");
		member.setBirthday("19911213");

		// 3. DAO method(CRUD)호출(실행)
		dao.insertMember(member);// try~catch문에서 처리 완료함

	}

}
