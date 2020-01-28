package examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LifecycleServlet")
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 WAS는 서블릿 요청을 받으면 그 서블릿이 메모리에 있는지 확인함
	 없을 경우 해당 서블릿 클래스를 메모리에 올림(객체 생성)
	 그 후 init, service메서드 호출
	 두 번째 요청부터는 생성자와 init메서드는 호출되지 않고, service메서드만 실행
	 */
    public LifecycleServlet() {
    	System.out.println("Make LifecycleServlet!");
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("Call init!"); //서블릿이 처음 호출될 때 init메서드 호출
	}

	public void destroy() {
		System.out.println("Call destroy! test"); //웹 애플리케이션이 갱신되거나 WAS가 종료될 때 destroy메서드 호출
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>form</title></head>");
		out.println("<body>");
		out.println("<form method='post' action='/firstweb/LifecycleServlet'>");
		out.println("name : <input type='text' name='name'><br>");
		out.println("<input type='submit' value='ok'><br>"); //submit이라는 버튼이 눌렸을 때 이 메서드를 가지고 이 주소로 요청해달라는 의미                                                 
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name"); //요청이 들어왔을 때 정보(request)에서 패러미터가 name으로 지정된 것의 값을 꺼내 string변수 name에 넣음
		out.println("<h1> hello " + name + "</h1>"); //그 값을 가지고 문구를 생성해 응답
		out.println("<form method='get' action='/firstweb/LifecycleServlet'>");
		out.println("<input type='submit' value='back'><br>");
		out.println("</form>");
		out.close();
	}

//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("Call service!"); //요청이 들어왔을 때 응답해야 하는 모든 내용은 service메서드에 구현
//						     //클라이언트의 요청이 GET일 경우 doget메서드를 호출
//						     //클라이언트의 요청이 POST일 경우 dopost메서드를 호출
//	}

	
}
