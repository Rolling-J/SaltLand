<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
    <link rel="stylesheet" href="./login.css">
    <link rel="stylesheet" href="../bar.css">
	<link rel="stylesheet" href="../footer.css">
    <title>로그인</title>
</head>
<body>
    <jsp:include page="../bar.jsp"></jsp:include>
    <section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="content_box">
                    <div class="login_wrap">
                        <div class="box_head">
                            <p>Salt Land 계정으로 로그인</p>
                        </div>
                        <div class="divine"></div>
                        <form class="login_box" action="../main.html" method="post">
                            <div id="input_box" class="id_box"> 
                                <input type="text" name="id" placeholder="아이디">
                            </div>
                            <div id="input_box" class="pw_box" >
                                <input type="text" name="password" placeholder="비밀번호">
                            </div>
                            <div class="btn_box">
                                <input type="submit" value="로그인">
                            </div>
                        </form>
                        <div class="divine"></div>
                        <div class="regi_box">
                            <p>Salt Land 계정이 없으신가요?</p>
                            <div class="regi_btn">
                                <a href="register.html">회원가입</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>