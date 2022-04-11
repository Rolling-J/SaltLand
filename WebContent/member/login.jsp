<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
	<script src="https://kit.fontawesome.com/a3555d8f42.js"></script>
    <link rel="stylesheet" href="/SaltProject/resources/css/login.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/bar.css">
	<link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
    <title>로그인</title>
</head>
<body>
    <jsp:include page="/menu.jsp"></jsp:include>
    <section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="content_box">
                    <div class="login_wrap">
                        <div class="box_head">
                            <p>Salt Land 계정으로 로그인</p>
                        </div>
                        <div class="divine"></div>
                        <%
							String error=request.getParameter("error");
							if(error!=null){
								out.println("<div class='alert_login'>");
								out.println("아이디와 비밀번호를 확인해 주십시오");
								out.println("</div>");
							}
						%>
                        <form class="login_box" action="./processLoginMember.jsp" method="post">
                            <div id="input_box" class="id_box"> 
                                <input type="text" name="id" placeholder="아이디">
                            </div>
                            <div id="input_box" class="pw_box" >
                                <input type="password" name="password" placeholder="비밀번호">
                            </div>
                            <div class="btn_box">
                                <input type="submit" value="로그인">
                            </div>
                        </form>
                        <div class="divine"></div>
                        <div class="regi_box">
                            <p>Salt Land 계정이 없으신가요?</p>
                            <div class="regi_btn">
                                <a href="register.jsp">회원가입</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>