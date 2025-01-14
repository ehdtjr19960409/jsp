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
	<h1>EL 연산자 사용</h1>
	<!-- 산술연산  -->
	<p> 10 + 5 = ${10 + 5} </p>
	<p> 10 % 3 = ${10%3} </p>
	<p> 10 mod 3 = ${10 mod 3} </p>
	
	
	<!-- 논리연산  -->
	<p>true && true = ${true && true}</p>
	<p>false && true = ${false && true}</p>
	<p>!false = ${!false} </p>
	<p>not false = ${not false} </p>
	
	<!-- 비교 연산 -->
	<p> 10 > 5 = ${10 > 5}</p>
	<p> 10 gt 5 = ${10 gt 5}</p>
	
	
	<!-- empty 연산 -->
	<c:set var = "list" value = "${empty myList}" />
	<p>빈 리스트 확인 : ${empty list} </p>
	
	
</body>
</html>