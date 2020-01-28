<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
    StringBuffer url = request.getRequestURL();
	/*
	java는 항상 모든 변수가 선언이 되어 있어야지만 쓸 수 있음
	그러나 파일 내에 request라는 변수를 선언한 적은 없음에도 불구하고 jsp에서는 사용할 수 있다.
	*/

    out.println("url : " + url.toString());
    out.println("<br>");
    /*
    out 또한 선언한 적이 없음에도 사용할 수 있음
 	이는 out이나 request가 jsp의 내장객체로서 이미 선언이 되어있기 때문 
    */
%>

</body>
</html>
