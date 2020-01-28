package examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/header")
public class HeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HeaderServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html"); //응답을 보내기 전에 컨텐츠 타입을 알려줌
		PrintWriter out = response.getWriter(); //클라이언트로 응답을 보낼 통로 생성
		out.println("<html>");
		out.println("<head><title>form</title></head>");
		out.println("<body>"); //응답으로 보낼 html태그

		Enumeration<String> headerNames = request.getHeaderNames(); //요청이 들어올 때 모든 정보들은 WAS가 HttpServletRequest라는 객체를 만들어 그 안에다 담아둠
																	//request의 getHeaderNames메서드는 모든 헤더 이름을 문자열 Enumeration객체로 반환
		while(headerNames.hasMoreElements()) { //반환받은 Enumeration객체를 while문을 통해 알아보면 헤더의 name을 알아낼 수 있을 것
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName); //name을 알 수 있으면 request의 getHeader메서드를 통해 헤더의 값 정보를 알아낼 수 있을 것
			out.println(headerName + " : " + headerValue + " <br> "); //이렇게 알아낸 정보를 브라우저에 출력
		}		
		
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
