package kr.ac.jbnu.se.awp.gitplay4.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.jbnu.se.awp.gitplay4.model.RegistrationException;

public class UserManager {
	private static UserManager instance;
	private Connection conn;

	private UserManager() {
		conn = null;

		// 데이터베이스 연결관련정보를 문자열로 선언
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://127.0.0.1/jspdb";

		try {
			// JDBC 드라이버 로드
			Class.forName(jdbc_driver);

			// 데이터베이스 연결정보를 이용해 Connection 인스턴스 확보
			conn = DriverManager.getConnection(jdbc_url, "jimin", "jimin8053");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static UserManager getInstance() {
		if (instance == null)
			instance = new UserManager();

		return instance;
	}

	private boolean hasId(String id) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			final String queryCheck = "SELECT count(*) from messages WHERE id = ?";
			final PreparedStatement ps = conn.prepareStatement(queryCheck);
			ps.setString(1, id);
			final ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (Exception e) {
			;
		}
		return count == 1;
	}

	// User Registration
	public void registerUser(String id, String password) throws RegistrationException {
		if (hasId(id)) {
			throw new RegistrationException("id already exists");
		}
		if (id == null)
			throw new RegistrationException("id is null");

		PreparedStatement pstmt = null;

		try {
			// Connection 클래스의 인스턴스로 부터 SQL 문 작성을 위한 Statement 준비
			String sql = "insert into member values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RegistrationException("sql exception", e);
		}
	}

	// User Validity Check
	public boolean isValid(String id, String password) {
		if (hasId(id)) {
			return false;
		}

		ResultSet resultSet = null;
		String validPassword = null;

		PreparedStatement pstmt = null;
		try {
			// Connection 클래스의 인스턴스로 부터 SQL 문 작성을 위한 Statement 준비
			String sql = "select password from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				validPassword = resultSet.getString(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		if (resultSet == null)
			return false;
		if (validPassword == null)
			return false;

		return password.equals(validPassword);
	}
	
}
