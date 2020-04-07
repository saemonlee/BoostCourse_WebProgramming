package kr.or.connect.jdbcexam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.connect.jdbcexam.dto.Role;

public class RoleDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbuser = "connectuser";
	private static String dbpasswd = "connect123!@#"; // 자주 쓰는 내용들을 미리 객체로 빼 놓음
	
	public Role getRole(Integer roleId) { // 데이터를 한 건 가져오는 메서드 추가. Role을 리턴하는 메서드.
		Role role = null; // 리턴해야 할 role 객체를 하나 선언
		Connection conn = null; // 연결을 맺어낼 수 있는 객체 선언
		PreparedStatement ps = null; // 명령을 선언할 statement 객체 선언
		ResultSet rs = null; // 결과값을 담아낼 객체 선언
		
		try { // 다양한 예외 상황을 상정한 예외처리
			Class.forName("com.mysql.jdbc.Driver");
			// 드라이버 로딩
			// MySQL이 제공하는 클래스가 메모리에 올라오는 작업을 하게 해줘야 함
			// Class 클래스의 forName 메서드를 실행하면 드라이버가 로딩
			conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
			// Connection 객체를 얻어옴.
			// java.sql 패키지의 DriverManager 클래스가 가진 getConnection이란 메서드를 이용.
			String sql = "SELECT role_id, description FROM role WHERE role_id = ?";
			ps = conn.prepareStatement(sql);
			// Connection 객체를 이용해 statement 객체를 얻어낼 수 있음
			// 쿼리문을 넣어줌
			// 조건문의 경우 인자에 무엇이 들어가느냐에 따라 그 안에 들어있는 값은 바뀌게 됨
			// 그렇기에 이 부분을 수행할 때 물음표를 대신 사용하는 것이 preparedStatement라는 쿼리의 특징
			// 이렇게 사용하게 되면 쿼리 자체는 계속 변경되지 않고 물음표가 바인딩되는 부분만 바뀌기 때문에 쿼리가 수행될 때 효율도 훨씬 좋음
			ps.setInt(1, roleId);
			// 반드시 물음표에 해당하는 값을 지정해주자.
			// role_id는 integer이기 때문에 setInt
			// parameterIndex는 물음표의 순서. 두번째 패러미터는 물음표의 값. getRole 매서드의 인자를 그 값으로 받아올 것
			rs = ps.executeQuery(); // 실행 코드. 실행 후엔 이 객체가 결과값을 알고 있게 됨
			
			if(rs.next()) { // next 메서드는 결과값이 있다면 첫 번째 레코드로 커서를 이동한 후 true를 리턴함
							// 만약 한 번 더 next 메서드가 수행되면 다음 레코드로 커서를 이동한 후 true를 리턴
							// 만약 없으면 false를 리턴
				int id = rs.getInt(1); // 값을 꺼냄. 컬럼 순서를 입력
				String description = rs.getString("description"); // 값을 꺼냄. 컬럼 이름을 입력할 수도 있음
				role = new Role(id, description); // 이렇게 꺼낸 값을 이용해 role 객체를 생성
			}
			
		}catch (Exception e) {
				e.printStackTrace();
		}finally { // 어떤 일이 있어도 반드시 실행되는 구절. 연걸을 하면 무조건 닫아야 하기에, 닫는 코드를 입력함
				if(rs != null) { // rs가 null이 아닐 때만 수행하게 함. rs객체를 만들 때 예외처리로 인해 생성되지 않을 수 있기 때문
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(ps != null) { // ps가 null이 아닐 때만 수행하게 함. ps객체를 만들 때 예외처리로 인해 생성되지 않을 수 있기 때문
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(conn != null) { // conn이 null이 아닐 때만 수행하게 함. conn객체를 만들 때 예외처리로 인해 생성되지 않을 수 있기 때문
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		}
		
		return role; // 선언한 role을 리턴
	}
	
	public int addRole(Role role) { // 한 건 입력하는 메서드 추가. 어떤 값을 입력할지는 이 메서드를 수행할 사용자에게 받아옴(role을 인자로 받음)
		int insertCount = 0; // 쿼리 수행 결과(*건 입력했습니다)를 담을 int형 변수 선언. 이 값을 리턴해줄 것
		// 이후 필요한 객체를 먼저 선언
		Connection conn = null;
		PreparedStatement ps = null;
		// insert문이기 때문에 ResultSet객체는 존재하지 않음. 결과값을 ResultSet으로 가져오지 않는다
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 드라이버 로딩
			conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
			String sql = "INSERT INTO role (role_id, description) VALUES (?, ?)";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, role.getRoleId());
			ps.setString(2, role.getDescription());
			// ?들에 들어가게 될 인자들은 role 객체가 갖고 있는 role_id와 description
			insertCount = ps.executeUpdate(); // insert, update, delete는 excuteUpdate메서드를 사용
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return insertCount;
	}
	public List<Role> getRoles() { // Role은 한 건의 데이터만 담을 수 있음. 이 Role의 리스트를 리턴해야 Role의 모든 정보를 반환받을 수 있게 됨
		List<Role> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT description, role_id FROM role order by role_id desc";
		try (Connection conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			// try-with resourse를 사용하는 게 새로 추가된 부분
			// try 부분에 사용할 리소스를 얻어올 코드를 만들어주면, 알아서 들어있는 객체들을 close하는 일을 수행
			// 그래서 catch블록은 있지만 finally에서 close하는 구문은 없음
			// 물음표 바인딩 부분은 sql 구문에 물음표가 없으니 패스
			try (ResultSet rs = ps.executeQuery()) { // ResultSet객체는 이 내부에서 얻어옴
				
				while (rs.next()) { // 한 건이 아닌 여러 건이기 때문에 while문을 통해 여러 번 수행
					String description = rs.getString(1);
					int id = rs.getInt("role_id");
					Role role = new Role(id, description);
					list.add(role); // 얻어진 role 객체를 리스트에 담음
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
