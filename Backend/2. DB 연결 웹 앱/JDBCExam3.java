package kr.or.connect.jdbcexam;

import java.util.List;

import kr.or.connect.jdbcexam.dao.RoleDao;
import kr.or.connect.jdbcexam.dto.Role;

public class JDBCExam3 {

	public static void main(String[] args) {
		RoleDao dao = new RoleDao();
		
		List<Role> list = dao.getRoles(); // dao가 갖고 있는 getRoles 메서드를 수행
		
		for (Role role : list) {
			System.out.println(role);
		}

	}

}
