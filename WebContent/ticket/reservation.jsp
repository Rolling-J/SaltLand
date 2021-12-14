<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/SaltProject/resources/css/reservation.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/menu.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
    
    
    <title>티켓 예매</title>
</head>

<body onload="popupLogin()">
<%
	String sessionId = (String)session.getAttribute("sessionId"); 
	System.out.println(sessionId);
%>
	<script type="text/javascript" src="./resources/JS/validation_reservation.js"></script>
	<script type="text/javascript">
    
	    var time = new Date();
	    var year = time.getFullYear();
	    var month = time.getMonth()+1;
	    var date = time.getDate();
		var sessionId = <%=sessionId %>;
	    
        document.getElementById("reserve_year").placeholder = year;
        document.getElementById("reserve_month").placeholder = month;
        document.getElementById("reserve_day").placeholder = date+1;
        
        function popupLogin(){
        		window.open("/SaltProject/ticket/popupLogin.html","login_popup","width=400, height=300, left=700, top=200 ")
        }
        
        function chargeCal(){
        	var adultN = document.getElementById("adultN").value;
    		var teenagerN = document.getElementById("teenagerN").value;
    		var childN = document.getElementById("childN").value;
    		var totalCharge = adultN*10000 + teenagerN*7000 + childN*4000;
    		document.getElementById("totalCharge").innerHTML =  totalCharge.toLocaleString('ko-KR') + " 원";
    		document.getElementById("totalC").value = totalCharge;
        }
        
        if(sessionId==null){
        	popupLogin();
        }
        setInterval(chargeCal, 1000);

    </script>
    <jsp:include page="/menu.jsp"></jsp:include>
    <section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="contents_wrap">
                    <div class="contents_head">
                        <h1>티켓 예매</h1>
                    </div>
                    <div class="divine_h"></div>
                    <form class="reserve_box" name="reserveTicket" action="/SaltProject/ticket/processReservation.jsp"  method="post">
	                    <div class="contents_sellect">
	                        <div class="sellect_head">
	                            <div class="sellct_title">
	                                <p class="title_txt">방문일자/인원 선택</p>
	                            </div>
	                        </div>
	                        <div class="sellect_date">
	                             <div class="wrap_date">
	                                <ul class="reserve_date">
	                                    <li class="cnt_date">
	                                        <div class="cntD_input">
	                                           <input type="text" name="r_year" class="nbrY_input" id="reserve_year">
	                                        </div>
	                                        <div class="cntD_txt">
	                                            <p>연</p>
	                                        </div>
	                                    </li>
	                                    <li class="cnt_date">
	                                        <div class="cntD_input">
	                                           <input type="text" name="r_month" class="nbrM_input" id="reserve_month">
	                                        </div>
	                                        <div class="cntD_txt">
	                                            <p>월</p>
	                                        </div>
	                                    </li>
	                                    <li class="cnt_date">
	                                        <div class="cntD_input">
	                                           <input type="text" name="r_day" class="nbrD_input" id="reserve_day">
	                                        </div>
	                                        <div class="cntD_txt">
	                                            <p>일</p>
	                                        </div>
	                                    </li>
	                                </ul>
	                            </div>
	                        </div>
	                        <div class="sellect_people">
	                            <ul class="count_people">
	                                <li class="cnt_adult">
	                                    <div class="cnt_txt">
	                                        <div class="txt_a">
	                                            <p>성인</p>
	                                        </div>
	                                        <div class="txt_des">
	                                            <p>만 19세 이상</p>
	                                        </div>
	                                    </div>
	                                    <div class="cnt_input">
	                                        <input type="text" name="adultN" class="nbr_input" id="adultN" value="0">
	                                        <p>명</p>
	                                    </div>
	                                </li>
	                                <li class="cnt_teenager">
	                                    <div class="cnt_txt">
	                                        <div class="txt_a">
	                                            <p>청소년</p>
	                                        </div>
	                                        <div class="txt_des">
	                                            <p>만 13세 이상 ~ 만 18세</p>
	                                        </div>
	                                    </div>
	                                    <div class="cnt_input">
	                                        <input type="text" name="teenagerN" class="nbr_input" id="teenagerN" value="0">
	                                        <p>명</p>
	                                    </div>
	                                </li>
	                                <li class="cnt_child">
	                                    <div class="cnt_txt">
	                                        <div class="txt_a">
	                                            <p>어린이</p>
	                                        </div>
	                                        <div class="txt_des">
	                                            <p>만 3세 ~ 만 12세</p>
	                                        </div>
	                                    </div>
	                                    <div class="cnt_input">
	                                        <input type="text" name="childN" class="nbr_input" id= "childN" value="0">
	                                        <p>명</p>
	                                    </div>
	                                </li>
	                            </ul>
	                        </div>
	                    	<div class="chargeBlock">
	                    		<p class="charge_des">총액 : </p>
	                    		<p class="charge_nb" id="totalCharge">0 원</p>
	                    		<input type="hidden" id="totalC" name="totalC" value=""/>
	                    	</div>
	                    </div>
	                    <div class="divine_h"></div>
	                    <div class="reserve">
	                    	<input type="submit" value="예매하기" onclick="chargeCal()">
	                    </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="/footer.jsp"></jsp:include>
    
</body>
</html>