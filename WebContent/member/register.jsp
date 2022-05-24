<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="https://kit.fontawesome.com/a3555d8f42.js"></script>
	<link rel="stylesheet" href="/SaltProject/resources/css/register.css">
	<link rel="stylesheet" href="/SaltProject/resources/css/bar.css">
	<link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
	<script defer src="/SaltProject/resources/JS/validation_member.js"></script>

	<title>회원 가입</title>
</head>
<body>
	<jsp:include page="/menu.jsp"></jsp:include>
    <section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="content_box">
                    <div class="login_wrap">
                        <div class="box_head">
                            <p>Salt Land 계정 회원 가입</p>
                         </div>
                        <div class="divine"></div>
                        <form class="login_box" name="member" action="./AddMemberAction.do" onsubmit="return checkForm()" method="post">
                            <div class="container">
                                <div class="id_pw_box">
                                    <div id="input_box" class="id_box"> 
                                        <p>아이디</p>
                                        <input type="text" name="id" id="id" placeholder="아이디">
                                    </div>
                                    <div id="input_box" class="pw_box" >
                                        <p>비밀번호</p>
                                        <input type="password" name="password" id="password" placeholder="비밀번호">
                                    </div>
                                    <div id="input_box" class="pw_box_2" >
                                        <p>비밀번호 확인</p>
                                        <input type="password" name="password_confirm" id="password_confirm" placeholder="비밀번호 확인">
                                    </div>
                                </div>
                                <div class="divine_h"></div>
                                <div class="personal_box">
                                    <div id="input_box" class="name" >
                                        <p>이름</p>
                                        <input type="text" name="name" id="name" placeholder="이름">
                                    </div>
                                    <div id="input_box" class="birth">
                                        <p>생년월일</p>
                                        <input type="text" name="b_year" id="b_year" maxlength="4" placeholder="년(4자)">
                                             년
                                        <input type="text" name="b_month" id="b_month" maxlength="2" placeholder="월">
                                             월
                                        <input type="text" name="b_day" id="b_day" maxlength="2" placeholder="일">
                                             일
                                    </div>
                                    <div id="input_box" class="gender" >
                                        <p>성별</p>
                                        <select name="gender" id="gender">
                                            <option value="male">남</option>
                                            <option value="female">여</option>
                                        </select>
                                    </div>
                                    <div id="input_box" class="email" >
                                        <p>이메일</p>
                                        <input type="text" name="email1" id="email1" placeholder="이메일"> @
                                        <select name="email2" id="email2">
                                            <option value="naver.com">naver.com</option>
                                            <option value="daum.net">daum.net</option>
                                            <option value="nate.com">nate.com</option>
                                        </select>
                                    </div>
                                    <div id="input_box" class="phone" >
                                        <p>전화번호</p>
                                        <input type="text" name="phone_1" id="phone_1" maxlength="3" size="2"> -
                                        <input type="text" name="phone_2" id="phone_2" maxlength="4" size="3"> -
                                        <input type="text" name="phone_3" id="phone_3" maxlength="4" size="3">
                                    </div>
                                </div>
                            </div>
                            <div class="btn_box">
                                <input type="button" class="submit-button" value="회원가입" onclick="return checkMember()">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>