<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>결과출력</h1>
	<h3><%= request.getAttribute("name") %></h3>
	<h3>${name}</h3>
	<% pageContext.setAttribute("name", "홍길동"); %>
	<h3>${name}</h3>
</body>
</html>