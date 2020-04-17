package kr.or.connect.webapiexam.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.connect.jdbcexam.dao.RoleDao;
import kr.or.connect.jdbcexam.dto.Role;

@WebServlet("/roles")
public class rolesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public rolesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json"); // json타입으로 응답을 보낼 것이란 의미. 
		//이 부분에 오타가 생긴다거나, 제대로 인식할 수 없을 경우 클라이언트는 어떤 방식으로 보내야 할지 결정할 수 없기 때문에 파일 다운로드 형식을 보여주게 됨
		
		RoleDao dao = new RoleDao();
		
		List<Role> list = dao.getRoles(); //role의 list를 얻어내려 함
		
		ObjectMapper objectMapper = new ObjectMapper();
		// 얻어낸 list는 json사용을 위한 라이브러리의 ObjectMapper객체를 이용한다면 json문자열로 바뀌어짐
		// json문자열을 객체로 바꿀 수도 있음
		String json = objectMapper.writeValueAsString(list);
		// writeValueAsString 메서드를 이용하면, 패러미터로 list를 넣어 줄 경우 해당 list가 json문자로 바뀌어서 리턴됨
		PrintWriter out = response.getWriter();
		out.println(json); //응답결과로 출력
		out.close();
	}

}
