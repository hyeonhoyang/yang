/**
 * 
 */
package com.oracle.member.test;

import java.util.Scanner;

import com.oracle.member.dao.MemberDAO;
import com.oracle.member.dao.MemberDAOImpl;

/**
 * @author a
 *
 */
public class DeleteTest {

	/**
	 * @param args
	 * @throws Exception  : 예외처리
	 */
	public static void main(String[] args) throws Exception {

		MemberDAO dao = MemberDAOImpl.getInstance();
		String id;
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제하실 ID를 입력하세요. : ");
		id = scan.next();
		
		
		
		// 3. DAO method(CRUD)호출(실행)
		dao.deleteMember(id);// try~catch문에서 처리 완료함
	}

}
