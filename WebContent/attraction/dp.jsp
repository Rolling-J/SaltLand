<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ page import="dto.attraction"%>
<%@ page import="dao.attractionbox"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/a3555d8f42.js"></script> 
    <link rel="stylesheet" href="/SaltProject/resources/css/dp.css" />
    <link rel="stylesheet" href="/SaltProject/resources/css/menu.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
    <%
		String name = request.getParameter("id");	
	%>
	<%@ include file="/resources/database/dbconn.jsp" %>
    <%  
		PreparedStatement pstmt = null;
 		ResultSet rs = null;

		String sql = "select * from attraction where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		rs = pstmt.executeQuery();
		if (rs.next()) {
		
    %>
	<title>어트랙션 상세</title> 
</head>
<body>
<div class="all">
    <jsp:include page="/menu.jsp"/>
    <div class="a_box"> 
        <div class="category"><p>홈>즐길거리>어트랙션</p></div>
        <div class="s_title">
            <strong class="a"><%=rs.getString("name")%></strong>
            <p class="pb"><%=rs.getString("info")%></p>
        </div>
    </div>
    

    <div class="at_box">  
        <div class="ride">
            <div class="r_box">
                <i class="fas fa-user-circle"></i><br>
                <p class="rp">탑승 인원</p><br>
                <strong class="pe"><%=rs.getString("ride")%>명</strong>
            </div>
        </div>
        <div class="ride_info">
            <div class="r_box">
                <i class="fas fa-user-circle"></i><br>
                <strong>이용 정보</strong>
                <p><br>키 제한 : </p> 
                <p>
                	<%
                	if(rs.getString("tall").equals("none")){
                	%>
                	제한 없음
                	<%	
                	}else{	
                	%>
                	<%=rs.getString("tall")%>
                	<%
                	}
                	%>
                </p>
	            <p><br>연령 제한 : </p>
	            <p>
	                <%
                	if(rs.getString("age").equals("none")){
                	%>
                	제한 없음
                	<%	
                	}else{	
                	%>
                	<%=rs.getString("age")%>세
                	<%
                	}
                	%>
                </p>
            </div>
        </div>
        <div class="care">
            <div class="r_box">
                <div class="ti_1">
	                <strong class="ti">주의사항</strong>               
	                <p class="ti2">
	                	- 모든 소지품은 반드시 소지품 보관함에 넣고 탑승합니다.<br>
	                    - 부상 예방을 위해 탑승 전 스트레칭은 필수입니다.<br>
	                    - 손님들의 안전을 위해 시설물 상태 및 기상 여건에 따라 갑작스럽게 운영이 정지될 수 있습니다.<br>
	                </p>
                </div>
                <div class="ti3"><img src="https://wwwcdn.everland.com/web/upload/images/favorite/22_imgEtcLimit0.png" alt="조건" style="width:403px; height:50px;"></div>
            </div>
        </div>
   </div>
   <%
	}
	if (rs != null)
		rs.close();
	if (pstmt != null)
			pstmt.close();
	if (conn != null)
		conn.close();
	%>
   
   
   <jsp:include page="/footer.jsp"/>
   </div>
</body>
</html>