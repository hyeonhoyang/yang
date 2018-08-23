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
public class GetAllMembersArrayTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		MemberDAO dao = MemberDAOImpl.getInstance();
		MemberVO[] members = dao.getAllMembersArray();
	
		for(int i=0;i<members.length;i++) {
			System.out.println(members[i]);
		}

	}

}
