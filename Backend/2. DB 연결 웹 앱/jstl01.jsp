<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--JSTL을 사용할 것이라는 걸 미리 알려줘야 함. taglib지시자 사용. 그 후 prefix로 태그명을 지정. uri로 태그 정보를 지정 --%>

<c:set var="value1" scope="request" value="lee"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Last Name : ${value1 } <br>
<c:remove var="value1" scope="request"/>
Last Name : ${value1 } <br>
</body>
</html>