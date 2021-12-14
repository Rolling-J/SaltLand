<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/SaltProject/resources/css/ticket.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/bar.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
    <title>티켓 확인</title>
</head>
<body>
<%
	String reserve_num = request.getParameter("reserve_num");
%>
	<sql:setDataSource var="dataSource" url="jdbc:mysql://localhost:3306/SaltLand" driver="com.mysql.jdbc.Driver" user="root" password="1234" />
	<sql:query dataSource="${dataSource}" var="resultSet">
		select * from ticket where reserve_num = ?
		<sql:param value="<%=reserve_num %>" />

	</sql:query>
	
	<jsp:include page="/menu.jsp"></jsp:include>
    <section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="content_box">
                    <div class="box_wrap">
                    	<form class="detail_box" name="reserveTicket" action="/SaltProject/ticket/deleteTicket.jsp"  method="post">
	                        <div class="box_head">
	                            <p>티켓 정보</p>
	                        </div>
	                        <div class="ticket_box">
	                            <div class="ticket_bg">
	                                <div class="ticket_title"> 
	                                    <p>Salt Land Ticket</p>
	                                </div> 
	                                <div class=ticket_info>
	                                    <div id="information" class="tkt_img">
	                                        <img src="./resources/image/event02.jpg">
	                                    </div>
	                                    <div id="information" class="tkt_text">
	                                    	<c:forEach var="row" items="${resultSet.rows}">
		                                        <ul>
		                                            <li>
		                                                <p class="text_tl">방문 일자</p>
		                                                <p class="text_cont"><c:out value="${row.visit_date }"/></p>
		                                            </li>
		                                            <li>
		                                                <p class="text_tl">방문 인원</p>
		                                                <p class="text_cont">성인 <c:out value="${row.adult }"/> 명 / 청소년 <c:out value="${row.teenager }"/> 명 / 어린이 <c:out value="${row.children }"/> 명</p>
		                                            </li>
		                                            <li>
		                                                <p class="text_tl">티켓 가격</p>
		                                                <p class="text_cont"><c:out value="${row.charge }"/> 원</p>
		                                            </li>
		                                        </ul>
		                                        <input type="hidden" name="reserve_num" value="${row.reserve_num }"/>
	                                        </c:forEach>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="btn_box">
	                            <a href="/SaltProject/ticket/deleteTicket.jsp?reserve_num=<%=reserve_num %>">예약 취소</a>
	                            <!-- 
	                            <input type="submit" value="예약 취소">
	                             -->
	                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>