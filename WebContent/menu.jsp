<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String sessionId = (String)session.getAttribute("sessionId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="./resources/css/bar.css" />
</head>
<body>
<header>
	<a class="logo" href="/SaltProject/main.jsp"><img src="/SaltProject/resources/image/logo.png" alt="logo"></a>
	<nav>
		<ul class="nav__links">
			<li><a href="/SaltProject/attraction/attractions.jsp">어트랙션</a></li>
			<li><a href="/SaltProject/bus.jsp">오시는 길</a></li>
			<li><a href="<c:url value="/BoardListAction.do?pageNum=1"/>">공지사항</a></li>
			<li><a href="/SaltProject/ticket/reservation.jsp">예매</a></li>
		</ul>
	</nav>
	<c:choose>
		<c:when test="${empty sessionId }">
			<a class="cta" href="/SaltProject/member/login.jsp">로그인</a>
		</c:when>
		<c:otherwise>
			<p>[<%=sessionId %>님]</p>
			<a class="cta" href="/SaltProject/member/memberDetail.jsp">회원정보</a>
			<a class="cta" href="/SaltProject/member/logoutMember.jsp">로그아웃</a>
		</c:otherwise>
	</c:choose> 
</header>
</body>
</html>