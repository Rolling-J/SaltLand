<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/SaltProject/resources/css/memberDetail.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/bar.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
<%
	String sessionId = (String)session.getAttribute("sessionId"); 
%>
<sql:setDataSource var="dataSource" url="jdbc:mysql://localhost:3306/SaltLand" driver="com.mysql.jdbc.Driver" user="root" password="1234" />
<sql:query dataSource="${dataSource}" var="resultSet">
	select * from member where id = ?
	<sql:param value="<%=sessionId %>" />
</sql:query>

    <title>회원 정보</title>
</head>
<body>
    <jsp:include page="../bar.jsp"></jsp:include>
    <section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="content_wrap">
                    <div class="content_box">
                        <div class="box_head">
                            <p>회원 정보</p>
                         </div>
                        <div class="divine"></div>
                        <!-- two taps -->
                        <div class="tap_box">
                        	<a class="tapBtn selected">회원 정보</a>
                        	<a class="tapBtn">티켓 예약 정보</a>
                        </div>
                        <!-- tap containers -->
                        <div class="info_box selected"> <!-- login tap container -->
                        	<c:forEach var="row" items="${resultSet.rows}">
	                            <div class="ps_container">
	                                <div class="personal_box">
	                                    <div id="input_box" class="id_box"> 
	                                        <p>아이디 : <c:out value="${row.id }"/></p>
	                                    </div>
	                                    <div id="input_box" class="name" >
	                                        <p>이름 : <c:out value="${row.name }" /></p>
	                                    </div>
	                                    <div id="input_box" class="birth">
	                                        <p>생년월일 : <c:out value="${row.birth }" /></p>
	                                    </div>
	                                    <div id="input_box" class="gender" >
	                                        <c:choose>
	                                        	<c:when test="${row.gender.equals('male') }">
	                                        		<p>성별 : 남</p>
	                                        	</c:when>
	                                        	<c:when test="${row.gender.equals('female') }">
	                                        		<p>성별 : 여</p>
	                                        	</c:when>
	                                        	<c:otherwise>
	                                        		<p> 미선택 </p>
	                                        	</c:otherwise>
	                                        </c:choose>
	                                    </div>
	                                    <div id="input_box" class="email" >
	                                        <p>이메일 : <c:out value="${row.mail }" /></p>
	                                    </div>
	                                    <div id="input_box" class="phone" >
	                                        <p>전화번호 : <c:out value="${row.phone }" /></p>
	                                    </div>
	                                </div>
	                                <!-- divineLine and TicketList will be here
	                                <div class="divine_h"></div>
	                                 -->
	                            </div>
	                            <div class="btn_box">
	                                	<a href="updateMember.jsp" class="btn_a">회원수정</a>
	                                	<a href="deleteMember.jsp" class="btn_a">회원탈퇴</a>
	                            </div>
                            </c:forEach>
                        </div><!-- login tap container end -->
                        <div class="info_box">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>