<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/bar.css" />
</head>
<body>
<header>
    <a class="logo" href="main.jsp"><img src="./resources/image/로고.png" alt="logo"></a>
    <nav>
        <ul class="nav__links">
            <li><a href="attractions.jsp">즐길거리</a></li>
            <li><a href="#">예매</a></li>
            <li><a href="#">오시는 길</a></li>
            <li><a href="#">회원정보</a></li>
            <li><a href="add.jsp">어트렉션 등록</a></li>
            <li><a href="./editattraction.jsp?edit=update">어트렉션 수정</a></li>
            <li><a href="./editattraction.jsp?edit=delete">어트렉션 삭제</a></li>

        </ul>
    </nav>
    <a class="cta" href="#">로그인</a>     
</header>
</body>
</html>