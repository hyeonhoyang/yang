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
public class GetRowCountTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		 MemberDAO dao = MemberDAOImpl.getInstance();
		 int member = dao.getRowCount();
		 System.out.println("총 칼럼개수 : "+member);
	}

}
