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
	<jsp:include page="/bar.jsp"></jsp:include>
    <section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="content_box">
                    <div class="box_wrap">
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
                                        <img src="./image/event02.jpg">
                                    </div>
                                    <div id="information" class="tkt_text">
                                        <ul>
                                            <li>
                                                <p class="text_tl">방문 일자</p>
                                                <p class="text_cont">2021. 10. 30 (토)</p>
                                            </li>
                                            <li>
                                                <p class="text_tl">방문 인원</p>
                                                <p class="text_cont">성인 3 명 / 청소년 2 명 / 어린이 1 명</p>
                                            </li>
                                            <li>
                                                <p class="text_tl">티켓 가격</p>
                                                <p class="text_cont">165,000 원</p>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="btn_box"> <!-- 로그인하러 가기 버튼-->
                            <a href="#">예약 취소</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>