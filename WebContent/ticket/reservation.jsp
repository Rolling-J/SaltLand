<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/SaltProject/resources/css/reservation.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/menu.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
    <script src="https://kit.fontawesome.com/a3555d8f42.js"></script>    
    <script type="text/javascript" src="./resources/JS/validation_reservation.js"></script>
    <script type="text/javascript">
    	<%
    		String sessionId = (String)session.getAttribute("sessionId");
    	%>
    	function autoDate(){
    		var time = new Date();
        	var year = time.getFullYear();
        	var month = time.getMonth()+1;
        	var date = time.getDate();
        	
        	document.getElementById("reserve_year").value = year;
        	document.getElementById("reserve_month").value = month;
        	document.getElementById("reserve_day").value = date+1;
    	}
    	function popupLogin(){
    		<%
    			if(sessionId==null){
			%>
				window.open("/SaltProject/ticket/popupLogin.jsp","login_popup","width=450, height=300, left=700, top=200");
			<%
    			}
    		%>
    	}
    	function chargeCal(){
    		var adultN = document.getElementById("adultN").value;
    		var teenagerN = document.getElementById("teenagerN").value;
    		var childN = document.getElementById("childN").value;
    		var costA = 10000;
    		var costT = 7000;
    		var costC = 4000;
    		var totalCharge = adultN*costA + teenagerN*costT + childN*costC;
    		
    		document.getElementById("totalCharge").innerHTML =  totalCharge.toLocaleString('ko-KR') + " 원";
    		document.getElementById("totalC").value = totalCharge;
    	}
    	
    	function load(){
    		autoDate();
    		popupLogin();
    	}
    	setInterval(chargeCal, 1000);
    </script>
    <title>티켓 예매</title>
</head>

<body onload="load()">
    <jsp:include page="/menu.jsp"></jsp:include>
    <section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="contents_wrap">
                    <div class="contents_head">
                        <h1>티켓 예매</h1>
                    </div>
                    <div class="divine_5"></div>
                    <form class="reserve_box" name="reserveTicket" action="./ReservateTicket.do?sessionId=<%=sessionId %>"  method="post">
						<div class="contents_select">
							<div class="select_head">
								<div class="select_title">
									<p class="title_txt">방문일자/인원 선택</p>
								</div>
							</div>
							<div class="divine_2"></div>
							<input type="hidden" name="id" id="id" value=<%=sessionId %>>
							<div class="select_date">
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
	                        <div class="divine_2"></div>
	                    	<div class="chargeBlock">
	                    		<p class="charge_des">총액 : </p>
	                    		<p class="charge_nb" id="totalCharge">0 원</p>
	                    		<input type="hidden" id="totalC" name="totalC" value=""/>
	                    	</div>
	                    </div>
	                    <div class="divine_5"></div>
	                    <div class="reserve">
	                    	<input type="submit" value="예매하기">
	                    </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="/footer.jsp"></jsp:include>
    
</body>
</html>