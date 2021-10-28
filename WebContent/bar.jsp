<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String sessionId = (String)session.getAttribute("sessionId");
%>
<head>
<link rel="stylesheet" href="bar.css">
</head>
<header>
	<a class="logo" href="main.html"><img src="SaltLand/resources/image/로고.png" alt="logo"></a>
        <nav>
            <ul class="nav__links">
                <li><a href="#">즐길거리</a></li>
                <li><a href="#">요금/우대혜택</a></li>
                <li><a href="#">이용가이드</a></li>
            </ul>
        </nav>
    <c:choose>
    	<c:when test="${empty sessionId }">
			<a class="cta" href="SaltLand/member/login.jsp">로그인</a>
		</c:when>
		<c:otherwise>
			<p>[<%=sessionId %>님]</p>
			<a class="cta" href="SaltLand/member/updateMember.jsp">회원수정</a>
			<a class="cta" href="SaltLand/member/logout.jsp">로그아웃</a>
		</c:otherwise>
	</c:choose>
</header>
