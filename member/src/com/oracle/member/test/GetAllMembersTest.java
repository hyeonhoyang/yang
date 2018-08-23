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
public class GetAllMembersTest {

	/**
	 * @param args
	 * @throws Exception 예외처리
	 */
	public static void main(String[] args) throws Exception {
		MemberDAO dao = MemberDAOImpl.getInstance();
		MemberVO member = dao.getMember("abc1235");
		System.out.println();
		System.out.println(dao.getAllMembers() + "\n");

	}

}
