<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String sessionId = (String)session.getAttribute("sessionId");
%>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/bar.css" />
</head>
<body>
	<header>
    <a class="logo" href="main.jsp"><img src="image/로고.png" alt="logo"></a>
    <nav>
        <ul class="nav__links">
            <li><a href="play.html">즐길거리</a></li>
            <li><a href="#">예매</a></li>
            <li><a href="#">오시는 길</a></li>
        </ul>
    </nav>
    <c:choose>
        <c:when test="${empty sessionId }">
            <a class="cta" href="SaltLand/member/login.jsp">로그인</a>
        </c:when>
        <c:otherwise>
            <p>[<%=sessionId %>님]</p>
            <a class="cta" href="SaltLand/member/updateMember.jsp">회원정보</a>
            <a class="cta" href="SaltLand/member/logout.jsp">로그아웃</a>
        </c:otherwise>
    </c:choose>     
</header>
</body>
</html>
