/**
 * 
 */
package com.oracle.member.test;

import com.oracle.member.dao.MemberDAO;
import com.oracle.member.dao.MemberDAOImpl;
import com.oracle.member.vo.MemberVO;

/**
 * @author a
 *
 */
public class UpdateTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// 1. DAO 객체 생성
		MemberDAO dao = MemberDAOImpl.getInstance();

		// 2. 회원 정보 준비
		MemberVO member = new MemberVO();
		
		member.setId("uhbyj1235");
		member.setPassword("asdf321321");
		member.setName("이정후");
		member.setGender("남");
		member.setEmail("asdf@asdfasdf.com");
		member.setPhone("010-3584-7424"); 
		member.setTel("02-2234-2234");
		member.setZip("12245");
		member.setAddress("경기도 고양시 일산서구");
		member.setBirthday("19911213");
		
		// 3. DAO method(CRUD) 호출(실행)
		dao.updateMember(member);

		
		
	}

}
