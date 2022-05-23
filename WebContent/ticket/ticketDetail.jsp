<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="mvc.model.TicketDTO"%>
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
    <script src="https://kit.fontawesome.com/a3555d8f42.js"></script>
    <%
    TicketDTO ticket = (TicketDTO) request.getAttribute("ticket");
	%>
    <script type="text/javascript">
		function deleteConfirm(num) {
			if (confirm("예약을 취소하시겠습니까?") == true)
				location.href = "./CancleReservation.do?reserve_num=<%=ticket.getReserve_num() %>&sessionId=<%=ticket.getId() %>";
			else
				return;
		}
	</script>
    <title>티켓 확인</title>
</head>
<body>
	<jsp:include page="/menu.jsp"></jsp:include>
    <section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="content_box">
                    <div class="box_wrap">
                    	<form class="detail_box" name="reserveTicket" method="post">
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
	                                        <img src="/SaltProject/resources/image/event02.jpg">
	                                    </div>
	                                    <div id="information" class="tkt_text">
		                                        <ul>
		                                            <li>
		                                                <p class="text_tl">방문 일자</p>
		                                                <p class="text_cont"><c:out value="<%=ticket.getVisit_date() %>"/></p>
		                                            </li>
		                                            <li>
		                                                <p class="text_tl">방문 인원</p>
		                                                <p class="text_cont">성인 <c:out value="<%=ticket.getAdult() %>"/> 명 / 청소년 <c:out value="<%=ticket.getTeenager() %>"/> 명 / 어린이 <c:out value="<%=ticket.getChildren() %>"/> 명</p>
		                                            </li>
		                                            <li>
		                                                <p class="text_tl">티켓 가격</p>
		                                                <p class="text_cont"><c:out value="<%=ticket.getCharge() %>"/> 원</p>
		                                            </li>
		                                        </ul>
		                                        <input type="hidden" name="reserve_num" value="<%=ticket.getReserve_num() %>"/>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="btn_box">
	                        	<!-- 
	                        	<input type="submit" value="예약 취소" id="btm_buttons">
	                            <a href="./CancleReservation?reserve_num=<%=ticket.getReserve_num() %>">예약 취소</a>
	                            -->
	                            <a href="#" onclick="deleteConfirm(<%=ticket.getReserve_num() %>)" class="btn_a" role= "button">예약 취소</a>
	                             
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