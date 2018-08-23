/**
 * 
 */
package com.oracle.member.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author a DB 연결/해제(자원반납) 유틸리티
 *
 */
public class DbUtil {
/**
 * DB연결
 * @return DB연결 객체
 */
	public static Connection connect() {

		Connection con = null;// DB연결 객체
		final String driver = "oracle.jdbc.OracleDriver";// JDBC 드라이버명
		final String url = "jdbc:oracle:thin:@localhost:1521:xe";// JDBC URL
		final String id = "java";// 계정 아이디
		final String pw = "java";// 계정 패스워드

		try {
			// JDBC 검색/로딩(loading)
			Class.forName(driver);
			// DB 연결 객체 반환
			con = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("DbUtil connect CNFE : ");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DbUtil connect SE : ");
			e.printStackTrace();
		}
		return con;
	}// connect
	/**
	 * DB연결 해제 (자원반납)
	 * @param con : 연결객체
	 * @param pstmt : SQL처리객체
	 * @param rs : SQL처리 결과 셋
	 */
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}catch(SQLException e) {
			System.out.println("DbUtil close SE : ");
			e.printStackTrace();
		}
	}
}
