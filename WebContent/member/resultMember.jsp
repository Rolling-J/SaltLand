<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<head>
	<link rel="stylesheet" href="/SaltProject/resources/css/resultMember.css">
	<link rel="stylesheet" href="/SaltProject/resources/css/menu.css">
	<link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
	<script src="https://kit.fontawesome.com/a3555d8f42.js"></script>
	<title>회원 정보</title>
</head>
<body>
	<jsp:include page="/menu.jsp" />
	<section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="content_box">
                    <div class="login_wrap">
                        <%
                        	String loginId = (String)session.getAttribute("sessionId");
                        	String msg = (String) request.getAttribute("msg");
                        	System.out.println("resultMember.jsp message : msg = "+msg);
						%>
						
						<c:set var="msg" value="<%=msg %>" />
						<c:choose>
							<c:when test="${msg.equals('1') }">
								<div class="box_head">
	                            	<p>회원정보 수정 완료</p>
	                        	</div>
	                        	<div class="divine"></div>
								<div class="box_body">
									<div class="welcome">
			                            <p>회원정보가 수정되었습니다</p>
			                        </div>
			                        <div class="play">    
			                            <div class="card_text">
			                                <p>수정하여 저장했습니다!</p>
			                            </div>
			                        </div>
			                   </div>
			                   <div class="btn_box">
			                        <a href="./MainPage.do">홈으로</a>
			                   </div>
							</c:when>
							<c:when test="${msg.equals('2') }">
								<div class="box_head">
	                            	<p>Salt Land 가입 완료</p>
	                        	</div>
	                        	<div class="divine"></div>
								<div class="box_body">
									<div class="welcome">
			                            <p>가입을 환영합니다!</p>
			                        </div>
			                        <div class="play">    
			                            <div class="card_text">
			                                <p>함께 즐겨요</p>
			                            </div>
			                            <div class="cards_play">
			                                <div id="play_card">
			                                    <p>놀이기구</p>
			                                    <div>
			                                        <img src="/SaltProject/resources/image/hurricane.jpg">
			                                    </div>
			                                </div>
			                                <div id="play_card">
			                                    <p>체험</p>
			                                    <div>
			                                        <img src="/SaltProject/resources/image/zombiescape.jpg">
			                                    </div>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
			                    <div class="btn_box">
			                        <a href="./MainPage.do">홈으로</a>
			                    </div>
							</c:when>
							<c:when test="${msg.equals('3') }">
								<div class="box_head">
	                            	<p>로그인 성공</p>
	                        	</div>
	                        	<div class="divine"></div>
								<%
								
								%>
								<div class="box_body">
									<div class="welcome">
		                                <p><%=loginId %>님 환영합니다.</p>
		                            </div>
		                            <div class="play">    
		                                <div class="card_text">
		                                    <p>매일매일 신나는 SaltLand에 또 방문해 주셨군요!</p>
		                                    <p>오늘도 즐거운 하루 되세요</p>
		                                </div>
		                            </div>
		                     	</div>
		                        <div class="btn_box">
		                            <a href="./MainPage.do">홈으로</a>
		                        </div>
							</c:when>
							<c:when test="${msg.equals('4') }"> <!--  -->
								<div class="box_head">
	                            	<p>Salt Land 회원 탈퇴 완료</p>
	                        	</div>
	                        	<div class="divine"></div>
								<div class="box_body">
								<div class="welcome">
		                                <p>회원정보가 삭제되었습니다.</p>
		                            </div>
		                            <div class="play">    
		                                <div class="card_text">
		                                    <p>아쉽네요. 다음에 만나는 날까지 안녕!</p>
		                                </div>
		                            </div>
		                     	</div>
		                        <div class="btn_box">
		                            <a href="./MainPage.do">홈으로</a>
		                        </div>
							</c:when>
						</c:choose>
                    </div>
                </div>
            </div>
        </div>
    </section>
	<jsp:include page="../footer.jsp" />
</body>
</html>