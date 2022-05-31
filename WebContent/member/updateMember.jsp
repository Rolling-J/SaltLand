<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="mvc.model.MemberDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="/SaltProject/resources/css/updateMember.css">
	<link rel="stylesheet" href="/SaltProject/resources/css/menu.css">
	<link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
	<script src="https://kit.fontawesome.com/a3555d8f42.js"></script>
	<script defer src="/SaltProject/resources/JS/validation_member.js"></script> 
	<%
		String sessionId = (String)session.getAttribute("sessionId");
		MemberDTO member = (MemberDTO) request.getAttribute("member");
		
		String[] mail = member.getMail().split("@");
		String email1 = mail[0];
		String email2 = mail[1];
		String[] birth = member.getBirth().split("/");
		String b_year = birth[0];
		String b_month = birth[1];
		String b_day = birth[2];
		String[] phone = member.getPhone().split("-");
		String phone_1 = phone[0];
		String phone_2 = phone[1];
		String phone_3 = phone[2];
	%>

    <title>회원 수정</title>
</head>
<body>
    <jsp:include page="/menu.jsp"></jsp:include>
    <section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="content_box">
                    <div class="login_wrap">
                        <div class="box_head">
                            <p>회원 정보 수정</p>
                         </div>
                        <div class="divine"></div>
                        <form class="login_box" name="member" action="./UpdateMemberAction.do" method="post">
                            <div class="container">
                                <div class="id_pw_box">
                                    <div id="input_box" class="id_box"> 
                                        <p>아이디 : <c:out value="<%=member.getId() %>"/></p>
                                        <input type="hidden" name="id" id="id" value="<%=member.getId() %>" />
                                    </div>
                                    <div id="input_box" class="pw_box" >
                                        <p>비밀번호</p>
                                        <input type="password" name="password" id="password" placeholder="비밀번호(4~10자리 숫자 조합)" value="<%=member.getPassword()%>">
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
                                        <input type="text" name="name" id="name" placeholder="이름" value="<%=member.getName() %>">
                                    </div>
                                    <div id="input_box" class="birth">
                                        <p>생년월일</p>
                                        <input type="text" name="b_year" id="b_year" maxlength="4" placeholder="년(4자)" value="<%=b_year%>">
                                             년
                                        <input type="text" name="b_month" id="b_month" maxlength="2" placeholder="월" value="<%=b_month %>">
                                             월
                                        <input type="text" name="b_day" id="b_day" maxlength="2" placeholder="일" value="<%=b_day %>">
                                             일
                                    </div>
                                    <div id="input_box" class="gender" >
                                        <p>성별</p>
                                        <select name="gender" id="gender">
                                        	<option value="" disabled selected>성별 선택</option>
                                        	<option value="male" <%if(member.getGender().equals("male")){%>selected<%} %>>남</option>
                                        	<option value="female" <%if(member.getGender().equals("female")){%>selected<%} %>>여</option>
                                        </select>
                                    </div>
                                    <div id="input_box" class="email" >
                                        <p>이메일</p>
                                        <input type="text" name="email1" id="email1" placeholder="이메일"  value="<%=email1 %>"> @
                                        <select name="email2" id="email2">
                                        	<option value="" disabled selected>email 선택</option>
                                        	<option value="naver.com" <%if(email2.equals("naver.com")){%>selected<%} %>>naver.com</option>
                                        	<option value="daum.net" <%if(email2.equals("daum.net")){%>selected<%} %>>daum.net</option>
                                        	<option value="nate.com" <%if(email2.equals("nate.com")){%>selected<%} %>>nate.com</option>
                                        </select>
                                    </div>
                                    <div id="input_box" class="phone" >
                                        <p>전화번호</p>
                                        <input type="text" name="phone_1" id="phone_1" maxlength="3" size="2" value="<%=phone_1 %>" placeholder="010,011,016~9 입력 가능"> -
                                        <input type="text" name="phone_2" id="phone_2" maxlength="4" size="3" value="<%=phone_2 %>" placeholder="3~4자리 숫자"> -
                                        <input type="text" name="phone_3" id="phone_3" maxlength="4" size="3" value="<%=phone_3 %>" placeholder="4자리 숫자">
                                    </div>
                                </div>
                            </div>
                            <div class="btn_box">
                                <input type="button" class="submit-button" value="회원수정" onclick="return checkMember()">
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