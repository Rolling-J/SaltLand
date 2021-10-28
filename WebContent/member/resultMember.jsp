<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" href="./resultMember.css">
<title>회원 정보</title>
</head>
<body>
	<jsp:include page="../bar.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">회원 정보</h1>
		</div>
	</div>
	    <section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="content_box">
                    <div class="login_wrap">
                        <%
							String msg = request.getParameter("msg");
						%>
						<c:choose>
							<c:when test="${msg.equals('0') }">
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
			                        <a href="../main.jsp">홈으로</a>
			                   </div>
							</c:when>
							<c:when test="${msg.equals('1') }">
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
			                                        <img src="../resources/image/허리케인.jpg">
			                                    </div>
			                                </div>
			                                <div id="play_card">
			                                    <p>체험</p>
			                                    <div>
			                                        <img src="../resources/image/체험.jpg">
			                                    </div>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
			                    <div class="btn_box">
			                        <a href="../main.jsp">홈으로</a>
			                    </div>
							</c:when>
							<c:when test="${msg.equals('2') }">
								<div class="box_head">
	                            	<p>로그인 성공</p>
	                        	</div>
	                        	<div class="divine"></div>
								<%
								String loginId = (String)session.getAttribute("sessionId");
								%>
								<div class="box_body">
									<div class="welcome">
		                                <p><%=loginId %>님 환영합니다.</p>
		                            </div>
		                            <div class="play">    
		                                <div class="card_text">
		                                    <p>함께 즐겨요</p>
		                                </div>
		                                <div class="cards_play">
		                                    <div id="play_card">
		                                        <p>놀이기구</p>
		                                        <div>
		                                            <img src="../resources/image/허리케인.jpg">
		                                        </div>
		                                    </div>
		                                    <div id="play_card">
		                                        <p>체험</p>
		                                        <div>
		                                            <img src="../resources/image/체험.jpg">
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		                     	</div>
		                        <div class="btn_box">
		                            <a href="../main.jsp">홈으로</a>
		                        </div>
							</c:when>
							<c:otherwise>
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
		                            <a href="../main.jsp">홈으로</a>
		                        </div>
							</c:otherwise>
						</c:choose>
                    </div>
                </div>
            </div>
        </div>
    </section>
	<jsp:include page="../footer.jsp" />
</body>
</html>