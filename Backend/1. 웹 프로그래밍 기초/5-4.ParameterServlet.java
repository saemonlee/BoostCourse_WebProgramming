package examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/param")
public class ParameterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ParameterServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답으로 보낼 부분은 response에 담는다.
		//내가 꺼내올 정보들은 request객체에서 얻어온다.
		response.setContentType("text/html;charset=utf-8"); //응답한 내용의 타입 선언
		PrintWriter out = response.getWriter(); //클라이언트와의 연결통로 생성
		out.println("<html>");
		out.println("<head><title>form</title></head>");
		out.println("<body>");
		//요청할 때 들고 들어온 값 중에는 패러미터 값들이 있음
		String name = request.getParameter("name"); //request의 getParameter메서드의 매개변수에 패러미터 이름을 넣으면 그 값이 반환됨
		String age = request.getParameter("age");
		
		out.println("name : " + name + "<br>"); //결과값을 브라우저 화면에 출력
		out.println("age : " +age + "<br>");
		
		out.println("</body>");
		out.println("</html>");
		//html의 input태그 안에 들어있는 값들도 패러미터로 넘어올 수 있음
	}
}
