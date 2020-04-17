package kr.or.connect.webapiexam.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.jdbcexam.dao.RoleDao;
import kr.or.connect.jdbcexam.dto.Role;

@WebServlet("/roles/*") // *는 어떤 문자든지 올 수 있다는 뜻
public class RoleByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RoleByIdServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		String pathInfo = request.getPathInfo(); // /roles/{roleId}
		// request가 가지고 있는 getPathInfo메서드를 가지고 path 정보를 읽어올 수 있음
		String[] pathParts = pathInfo.split("/");
		// 읽어온 path 정보를 /를 기준으로 잘라 배열로 만듬
		String idStr = pathParts[1]; // roleId에 해당하는 1번 인덱스 부분
		int id = Integer.parseInt(idStr); // 문자열이기에 Integer.parseInt 메서드를 이용해 int값으로 바꿔줌
		
		RoleDao dao = new RoleDao();
		Role role = dao.getRole(id); // 이렇게 만든 id객체를 RoleDao객체의 getRole메서드에 인자로 넣어줌
									 // 이때 입력받은 roleId에 해당하는 정보를 하나 읽어오게 됨
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(role); // json화
		
		PrintWriter out = response.getWriter();
		out.println(json); // json 출력
		out.close();
	}

}
