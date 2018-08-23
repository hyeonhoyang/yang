/**
 * 
 */
package com.oracle.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.member.util.DbUtil;
import com.oracle.member.util.ExceptionMetadata;
import com.oracle.member.vo.MemberVO;

/**
 * DAO 구현 클래스 usage) MemberDAO dao = MemberDAOImpl.getInstance();
 * 
 * @author a
 *
 */
public class MemberDAOImpl implements MemberDAO {
	// public class final MemberDAOImpl implements MemberDAO {

	private static MemberDAOImpl instance = null;

	private MemberDAOImpl() {
	}

	// Singleton(독신자) 디자인 패턴
	// static과 final의 위치가 바뀌어도 무관하다 , final키워드가 붙으면 상속이 불가능하다.
	// 상속 / 피상속 금지 => 단독 클래스 : 보안!!!!!!!
	public static final MemberDAOImpl getInstance() {
		if (instance == null)
			instance = new MemberDAOImpl();
		return instance;
	}

	/**
	 * @see com.oracle.member.dao.MemberDAO#insertMember(com.oracle.member.vo.MemberVO)
	 */
	@Override
	public void insertMember(MemberVO member) {// throws Exception

		ExceptionMetadata emd = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		// 1.DB 연결 객체 활성화 -> 연결 메서드
		Connection con = DbUtil.connect();

		// 2.SQL처리 객체 생성
		PreparedStatement pstmt = null;

		// 3.SQL 구문
		String sql = "INSERT INTO member VALUES" + "(?,?,?,?,?,?,?,?,?,TO_DATE (? , 'yyyy-mm-dd'), SYSDATE)";
		try {
			// 4.SQL 구문 처리/예외처리(try~catch)
			pstmt = con.prepareStatement(sql);

			// 5.SQL 구문 인자 처리(대입)
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getTel());
			pstmt.setString(8, member.getZip());
			pstmt.setString(9, member.getAddress());
			pstmt.setString(10, member.getBirthday());

			// 6. SQL 구문 실행 /메시징
			if (pstmt.executeUpdate() == 1) {
				System.out.println("회원 정보 저장에 성공하였습니다.");
			} else {
				System.out.println("회원 정보 저장에 실패하였습니다.");
			}

		} catch (SQLException e) {
			emd.printErr(e, con, true);
		} catch (Exception e) {
			emd.printErr(e, con, true);
		} finally {
			// 7. DB 연결 해제(자원 반납)
			DbUtil.close(con, pstmt, null);
		} // try
	}// INSERT

	/**
	 * 
	 * @see com.javateam.member.dao.MemberDAO#updateMember(com.javateam.member.vo.
	 *      MemberVO)
	 */
	@Override
	public void updateMember(MemberVO member) throws Exception {
		ExceptionMetadata emd = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		// 1. DB 연결 객체 활성화 <- 연결 메서드
		Connection con = DbUtil.connect();

		// 2. SQL 처리 객체
		PreparedStatement pstmt = null;

		// 3. SQL 구문
		String sql = "UPDATE member SET " + "pw=?," + "name=?," + "gender=?," + "email=? ," + "phone = ?," + "tel = ?,"
				+ "zip = ?," + "address = ?," + "birthday = ? " + "WHERE id=?";

		try {
			// 4. SQL 구문 처리/예외처리(try ~ catch)
			pstmt = con.prepareStatement(sql);

			// 5. SQL 구문 인자 처리(대입)
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getGender());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getTel());
			pstmt.setString(7, member.getZip());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getBirthday());
			pstmt.setString(10, member.getId());
			// 6. SQL 구문 실행/메시징
			if (pstmt.executeUpdate() == 1) {
				System.out.println("회원 정보 갱신에 성공하였습니다.");
			} else {
				System.out.println("회원 정보 갱신에 실패하였습니다.");
			}

		} catch (SQLException e) {
			emd.printErr(e, con, true);
		} catch (Exception e) {
			emd.printErr(e, con, true);
		} finally {
			// 7. DB 연결 해제(자원 반납)
			DbUtil.close(con, pstmt, null);
		} // try

	} //

	@Override
	public void deleteMember(String id) throws Exception {
		// 1. DB 연결 객체 활성화 <= 연결 메스드
		Connection con = DbUtil.connect();

		// 2. SQL처리 객체 생성
		PreparedStatement pstmt = null;

		// 3. SQL 구문
		String sql = "DELETE FROM member " + "WHERE id = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 6. SQL 구문 실행/메시징
			if (pstmt.executeUpdate() == 1) {
				System.out.println("회원 정보 삭제에 성공하였습니다.");
			} else {
				System.out.println("회원 정보 삭제에 실패하였습니다.");
			}

		} catch (SQLException e) {
			System.out.println("MemberDAOImpl DeleteMember SE :");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("MemberDAOImpl DeleteMember E :");
			e.printStackTrace();
		} finally {
			// 7. DB 연결 해제(자원 반납)
			DbUtil.close(con, pstmt, null);
		} // try

	}

	@Override
	public List<MemberVO> getAllMembers() throws Exception {
		Connection con = DbUtil.connect();// DB연결
		List<MemberVO> list = new ArrayList<MemberVO>();
		PreparedStatement pstmt = null;// SQL 처리 객체
		ResultSet rs = null;// SQL 결과셋 객체

		String sql = "SELECT * FROM member ";

		try {
			pstmt = con.prepareStatement(sql);// SQL구문 처리
			rs = pstmt.executeQuery();// SQL구문실행 -> 결과셋

			// if(rs.next()){
			while (rs.next()) {
				MemberVO member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setGender(rs.getString("gender"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setTel(rs.getString("tel"));
				member.setZip(rs.getString("zip"));
				member.setAddress(rs.getString("address"));
				member.setBirthday(rs.getString("birthday"));
				member.setJoindate(rs.getString("joindate"));
				list.add(member);
			} // while

		} catch (SQLException e) {
			System.out.println("MemberDAOImpl getAllMember SE : ");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("MemberDAOImpl getAllMember E : ");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, rs);
		}

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		return list;
	}

	@Override
	public MemberVO getMember(String id) throws Exception {
		Connection con = DbUtil.connect();// DB연결
		MemberVO member = new MemberVO();// 결과값 (개별 횐원 정보)
		PreparedStatement pstmt = null;// SQL 처리 객체
		ResultSet rs = null;// SQL 결과셋 객체

		String sql = "SELECT * FROM member " + "WHERE ID =?";
		try {
			pstmt = con.prepareStatement(sql);// SQL구문 처리
			pstmt.setString(1, id);// 인자처리
			rs = pstmt.executeQuery();// SQL구문실행 -> 결과셋

			// if(rs.next()){
			while (rs.next()) {
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setGender(rs.getString("gender"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setTel(rs.getString("tel"));
				member.setZip(rs.getString("zip"));
				member.setAddress(rs.getString("address"));
				member.setBirthday(rs.getString("birthday"));
				member.setJoindate(rs.getString("joindate"));

			} // while

		} catch (SQLException e) {
			System.out.println("MemberDAOImpl getMember SE : ");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("MemberDAOImpl getMember E : ");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, rs);
		}

		return member;
	}

	@Override
	public boolean isMember(String id) throws Exception {

		Connection con = DbUtil.connect(); // DB 연결
		boolean flag = false; // 결과값(회원 존재 여부)
		PreparedStatement pstmt = null; // SQL 처리 객체
		ResultSet rs = null; // SQL 결과셋 객체
		String sql = "SELECT count(*) FROM member " + "WHERE id=?";
		try {
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(sql); // SQL 구문 처리
			pstmt.setString(1, id); // 인자 처리
			rs = pstmt.executeQuery(); // SQL 구문 실행 -> 결과셋

			if (rs.next()) {
				flag = rs.getInt(1) == 1 ? true : false;
			} //

			con.commit();

		} catch (SQLException e) {

			try {
				System.out.println("MemberDAOImpl isMember SE : ");
				e.printStackTrace();
				con.rollback();
			} catch (Exception e2) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			try {
				System.out.println("MemberDAOImpl isMember E : ");
				e.printStackTrace();
				con.rollback();
			} catch (Exception e2) {
				e.printStackTrace();
			}
		} finally {
			DbUtil.close(con, pstmt, rs);
		} //

		return flag;
	}

	@Override
	public boolean isMember(String id, String password) throws Exception {

		Connection con = DbUtil.connect(); // DB 연결
		boolean flag = false; // 결과값(회원 존재 여부)
		PreparedStatement pstmt = null; // SQL 처리 객체
		ResultSet rs = null; // SQL 결과셋 객체
		String sql = "SELECT count(*) FROM member " + "WHERE id=? AND password=?";
		try {
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(sql); // SQL 구문 처리
			pstmt.setString(1, id); // 인자 처리
			pstmt.setString(2, password); // 인자 처리
			rs = pstmt.executeQuery(); // SQL 구문 실행 -> 결과셋

			if (rs.next()) {
				flag = rs.getInt(1) == 1 ? true : false;
			} //

			con.commit();

		} catch (SQLException e) {

			try {
				System.out.println("MemberDAOImpl isMember SE : ");
				e.printStackTrace();
				con.rollback();
			} catch (Exception e2) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			try {
				System.out.println("MemberDAOImpl isMember E : ");
				e.printStackTrace();
				con.rollback();
			} catch (Exception e2) {
				e.printStackTrace();
			}
		} finally {
			DbUtil.close(con, pstmt, rs);
		} //

		return flag;

	}

	@Override
	public int getRowCount() throws Exception {
		int count = 0;
		ExceptionMetadata emd = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.connect();// DB연결
		PreparedStatement pstmt = null;// SQL 처리 객체
		ResultSet rs = null;// SQL 결과셋 객체

		String sql = "SELECT COUNT(*) FROM member ";
		// count : 테이블의 수 , (*) : 모든 필드를 검색한다.
		// ==> count(*) : 전체 필드의 테이블 수를 구하여라!.
		try {
			pstmt = con.prepareStatement(sql);// SQL구문 처리
			rs = pstmt.executeQuery();// SQL구문실행 -> 결과셋

			// if(rs.next()){
			while (rs.next()) {
				count = rs.getInt("count(*)");
				/*
				 * getInt() : 매개 변수 이름이 지정된 경우 지정된 매개 변수의 값을 검색하여 Java 프로그래밍 언어의 int로 반환합니다.
				 */
			} // while

		} catch (SQLException e) {
			System.out.println("MemberDAOImpl getRowCount SE : ");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("MemberDAOImpl getRowCount E : ");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, rs);
		}
		return count;

	}

	@Override
	public MemberVO[] getAllMembersArray() throws Exception {
		ExceptionMetadata emd = new ExceptionMetadata(new Exception().getStackTrace()[0]);
		Connection con = DbUtil.connect();// DB연결
		PreparedStatement pstmt = null;// SQL처리 객체
		ResultSet rs = null;// SQL 결과셋 객체
		MemberVO members[] = new MemberVO[this.getRowCount()];
		String sql = "SELECT * FROM member";

		int count = 0;

		try {
			pstmt = con.prepareStatement(sql);// SQL구문 처리
			rs = pstmt.executeQuery();// SQL 구문실행->결과셋

			while (rs.next()) {
				MemberVO member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setGender(rs.getString("gender"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setTel(rs.getString("tel"));
				member.setZip(rs.getString("zip"));
				member.setAddress(rs.getString("address"));
				member.setBirthday(rs.getString("birthday"));
				member.setJoindate(rs.getString("joindate"));
				members[count++] = member;
			} // while
		} catch (SQLException e) {
			System.out.println("MemberDAOImpl getRowCount SE : ");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("MemberDAOImpl getRowCount E : ");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, rs);
		}
		return members;

	}
}
