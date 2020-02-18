package examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ApplicationScope02")
public class ApplicationScope02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ApplicationScope02() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ServletContext application = getServletContext();
		try {
			int value = (int)application.getAttribute("value");
			value++; // value값에 더하기 1
			application.setAttribute("value", value);
			
			out.println("<h1>value : "+value+"</h1>");
		}catch (NullPointerException e) {
			out.print("value의 값이 설정되지 않았습니다.");
			// 만약 ApplicationScope02를 먼저 실행하는 경우 app scope에 저장된 value값이 없기에(null) 에러가 발생할 수 있음
			// 따라서 NullPointerException으로 예외처리를 함
		}
	}
}
