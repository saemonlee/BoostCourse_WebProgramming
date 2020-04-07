package kr.or.connect.jdbcexam;

import kr.or.connect.jdbcexam.dao.RoleDao;
import kr.or.connect.jdbcexam.dto.Role;

public class JDBCExam1 {

	public static void main(String[] args) {
		RoleDao dao = new RoleDao(); // RoleDao가 제대로 실행되는지 알아보고자 함
		Role role = dao.getRole(100); // dao가 가지고 있는 getRole 메서드를 수행
		System.out.println(role);
	}

}
