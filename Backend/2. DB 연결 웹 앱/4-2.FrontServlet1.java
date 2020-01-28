package examples;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/front")
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FrontServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int diceValue = (int)(Math.random() * 6) + 1;
		request.setAttribute("dice", diceValue);
		//forward하기 위해 request의 setAttribute 메서드에 인자를 맡김. 첫번째 : 내가 임의로 정한 값(이름), 두번째 : 인자(오브젝트 타입)
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/next");
		/*
		request의 getRequestDispatcher메서드에 forward경로를 입력해 RequestDispatcher객체 생성
		forward경로는 앞에 반드시 /를 붙여야 함. Content루트 이하의 값만 사용
		또한 서버 안에서 움직이는 것이기 때문에 같은 웹 어플리케이션 안에서만 가능
		*/
		requestDispatcher.forward(request, response);
		//RequestDispatcher객체의 forward 메서드를 사용해 request, response 값을 넘겨줌
	}

}
