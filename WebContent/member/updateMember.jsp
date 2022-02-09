<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
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
	%>
	<sql:setDataSource var="dataSource" url="jdbc:mysql://localhost:3306/SaltLand" driver="com.mysql.jdbc.Driver" user="root" password="1234" />
	<sql:query dataSource="${dataSource}" var="resultSet">
		select * from member where id = ?
		<sql:param value="<%=sessionId %>" />
	</sql:query>
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
                        
                        <c:forEach var="row" items="${resultSet.rows}">
						<c:set var="mail" value="${row.mail }" />
						<c:set var="email1" value="${mail.split('@')[0] }" />
						<c:set var="email2" value="${mail.split('@')[1] }" />
						
						<c:set var="birth" value="${row.birth }" />
						<c:set var="b_year" value="${birth.split('/')[0] }" />
						<c:set var="b_month" value="${birth.split('/')[1] }" />
						<c:set var="b_day" value="${birth.split('/')[2] }" />
						
						<c:set var="phone" value="${row.phone }" />
						<c:set var="phone_1" value="${phone.split('-')[0] }" />
						<c:set var="phone_2" value="${phone.split('-')[1] }" />
						<c:set var="phone_3" value="${phone.split('-')[2] }" />	
							
                        <form class="login_box" name="member" action="/SaltProject/member/processUpdateMember.jsp" method="post">
                            <div class="container">
                                <div class="id_pw_box">
                                    <div id="input_box" class="id_box"> 
                                        <p>아이디 : <c:out value="${row.id }"/></p>
                                        <input type="hidden" name="id" id="id" value="${row.id }" />
                                    </div>
                                    <div id="input_box" class="pw_box" >
                                        <p>비밀번호</p>
                                        <input type="password" name="password" id="password" placeholder="비밀번호" value="<c:out value='${row.password }' />">
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
                                        <input type="text" name="name" id="name" placeholder="이름" value="<c:out value='${row.name }' />">
                                    </div>
                                    <div id="input_box" class="birth">
                                        <p>생년월일</p>
                                        <input type="text" name="b_year" id="b_year" maxlength="4" placeholder="년(4자)" value="<c:out value='${b_year }' />">
                                             년
                                        <input type="text" name="b_month" id="b_month" maxlength="2" placeholder="월" value="<c:out value='${b_month }' />">
                                             월
                                        <input type="text" name="b_day" id="b_day" maxlength="2" placeholder="일" value="<c:out value='${b_day }' />">
                                             일
                                    </div>
                                    <div id="input_box" class="gender" >
                                        <p>성별</p>
                                        <select name="gender" id="gender">
                                        	<option value="male" <c:if test="${row.gender eq 'male' }">selected</c:if>>남</option>
                                            <option value="female" <c:if test="${row.gender eq 'female' }">selected</c:if>>여</option>
                                        </select>
                                    </div>
                                    <div id="input_box" class="email" >
                                        <p>이메일</p>
                                        <input type="text" name="email1" id="email1" placeholder="이메일"  value="${email1 }"> @
                                        <select name="email2" id="email2">
                                            <option value="naver.com" <c:if test="${row.gender eq 'naver.com' }">selected</c:if>>naver.com</option>
                                            <option value="daum.net" <c:if test="${row.gender eq 'daum.net' }">selected</c:if>>daum.net</option>
                                            <option value="nate.com" <c:if test="${row.gender eq 'nate.com' }">selected</c:if>>nate.com</option>
                                        </select>
                                    </div>
                                    <div id="input_box" class="phone" >
                                        <p>전화번호</p>
                                        <input type="text" name="phone_1" id="phone_1" maxlength="3" size="2" value="${phone_1 }"> -
                                        <input type="text" name="phone_2" id="phone_2" maxlength="4" size="3" value="${phone_2 }"> -
                                        <input type="text" name="phone_3" id="phone_3" maxlength="4" size="3" value="${phone_3 }">
                                    </div>
                                </div>
                            </div>
                            <div class="btn_box">
                                <input type="button" class="submit-button" value="회원수정" onclick="return checkMember()">
                            </div>
                        </form>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="../footer.jsp"></jsp:include>
    
</body>
</html>