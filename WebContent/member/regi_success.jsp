<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="./regi_success.css">
    <link rel="stylesheet" href="../bar.css">
	<link rel="stylesheet" href="../footer.css">
    <title>회원 가입 완료</title>
</head>
<body>
    <jsp:include page="../bar.jsp"></jsp:include>
    <section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="content_box">
                    <div class="login_wrap">
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
                            <p>로그인 하러 가기</p>
                            <a href="login.jsp">로그인</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>