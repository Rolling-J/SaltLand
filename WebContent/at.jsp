<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="dto.attraction"%>
<%@ page import="dao.attractionbox"%>
<jsp:useBean id="attractionDAO" class="dao.attractionbox" scope="session" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="at.css">
    <script src="https://kit.fontawesome.com/a3555d8f42.js" crossorigin="anonymous"></script> 
    <title>Document</title>
    <link rel="stylesheet" href="./resources/css/at.css" />
</head>
<body>
<%
		String name = request.getParameter("name");
		attractionbox dao = attractionbox.getInstance();
		attraction attraction = dao.getattractionByname(name);
	%>
    <jsp:include page="menu.jsp"/>
    <div class="a_box">
        <p class="category"> 홈>즐길거리>어트랙션</p>
        <div class="s_title">
            <strong class="a"><%=attraction.getName()%></strong>
            <p class="pb"><%=attraction.getInfo()%></p>
        </div>
    </div>
    

    <div class="at_box">
        <div class="ride">
            <div class="r_box">
                <i class="fas fa-user-circle"></i><br>
                <p class="rp">탑승 인원</p><br>
                <strong class="pe"><%=attraction.getRide()%></strong>
            </div>
        </div>
        <div class="ride_info">
            <div class="r_box">
                <i class="fas fa-user-circle"></i><br>
                <strong>이용 정보</strong>
                <p><%=attraction.getTall()%><br><%=attraction.getAge()%></p>
            </div>
        </div>
        <div class="care">
            <div class="r_box">
                <div class="ti_1">
                <strong class="ti">주의사항</strong>               
                <p class="ti2">- 모든 소지품은 반드시 소지품 보관함에 넣고 탑승합니다.<br>
                    - 부상 예방을 위해 탑승 전 스트레칭은 필수입니다.<br>
                    - 손님들의 안전을 위해 시설물 상태 및 기상 여건에 따라 갑작스럽게 운영이 정지될 수 있습니다.<br>
                  
                </div>
                <div class="ti3"><img src="https://wwwcdn.everland.com/web/upload/images/favorite/22_imgEtcLimit0.png" alt="조건" style="width:403px; height:50px;"></div>
            </div>
        </div>
   </div>
   <div id="footer"></div>
</body>
</html>