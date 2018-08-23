/**
 *
 */
package com.oracle.member.test;
 
import com.oracle.member.dao.MemberDAO;
import com.oracle.member.dao.MemberDAOImpl;
 
/**
 * 개별 회원 정보 유무 점검(Check) 단위 테스트(Unit Test)
 * @author javateam
 *
 */
public class MemberCheckTest2 {
 
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
 
        MemberDAO dao = MemberDAOImpl.getInstance();
        System.out.println(dao.isMember("jsp12346", "12345678") == true ?
                            "회원입니다" : "회원이 아닙니다");
    } //
 
}