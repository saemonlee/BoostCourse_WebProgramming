package exam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TenServlet
 */
@WebServlet("/ten") //어노테이션으로 url mapping
public class TenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8"); //클라이언트가 응답을 받았을 때 응답 내용이 무엇인지 알아야 하기 때문에, 그것이 무슨 타입인지 알려줘야 함
											  				//캐릭터셋을 설정해주면 좋음
		PrintWriter out = response.getWriter(); //클라이언트에 응답할 내용을 넣을 통로를 설정. getWriter()메서드는 PrintWriter객체를 리턴
		out.print("<h1>1-10까지 출력!</h1>");//통로가 만들어지면 그 객체에 내용을 출력
		for (int i = 1; i <= 10; i++) {
			out.print(i+"<br>");
		}
		out.close(); //다 사용한 out객체는 close.
	}

}
