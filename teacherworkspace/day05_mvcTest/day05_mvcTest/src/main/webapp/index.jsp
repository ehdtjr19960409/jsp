<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- ${pageContext.request.contextPath} jstl에서 제공하는 url경로 잡기  -->
	<form action="${pageContext.request.contextPath}/member/join.me" method="get">
		<button>회원가입</button>
	</form>
	<a href="http://localhost:8888/day05_mvcTest/member/login.me">로그인 하러가기</a>
</body>
</html>