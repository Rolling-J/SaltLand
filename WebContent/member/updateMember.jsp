<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/SaltProject/resources/css/updateMember.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/bar.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
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
<body onload="init()">
    <jsp:include page="../bar.jsp"></jsp:include>
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
						<c:set var="phone_0" value="${phone.split(' / ')[0] }" />
						<c:set var="phonenum" value="${phone.split(' / ')[1] }" />
						<c:set var="phone_1" value="${phonenum.split('-')[0] }" />
						<c:set var="phone_2" value="${phonenum.split('-')[1] }" />
						<c:set var="phone_3" value="${phonenum.split('-')[2] }" />	
							
                        <form class="login_box" name="updateMemberForm" action="/SaltProject/member/processUpdateMember.jsp" onsubmit="return checkForm()" method="post">
                            <div class="container">
                                <div class="id_pw_box">
                                    <div id="input_box" class="id_box"> 
                                        <p>아이디 : <c:out value="${row.id }"/></p>
                                        <input type="hidden" name="id" value="${row.id }" />
                                    </div>
                                    <div id="input_box" class="pw_box" >
                                        <p>비밀번호</p>
                                        <input type="password" name="password" placeholder="비밀번호" value="<c:out value='${row.password }' />">
                                    </div>
                                    <div id="input_box" class="pw_box_2" >
                                        <p>비밀번호 확인</p>
                                        <input type="password" name="password_confirm" placeholder="비밀번호 확인">
                                    </div>
                                </div>
                                <div class="divine_h"></div>
                                <div class="personal_box">
                                    <div id="input_box" class="name" >
                                        <p>이름</p>
                                        <input type="text" name="name" placeholder="이름" value="<c:out value='${row.name }' />">
                                    </div>
                                    <div id="input_box" class="birth">
                                        <p>생년월일</p>
                                        <input type="text" name="b_year" maxlength="4" placeholder="년(4자)" value="<c:out value='${b_year }' />">
                                             년
                                        <input type="text" name="b_month" maxlength="2" placeholder="월" value="<c:out value='${b_month }' />">
                                             월
                                        <input type="text" name="b_day" maxlength="2" placeholder="일" value="<c:out value='${b_day }' />">
                                             일
                                    </div>
                                    <div id="input_box" class="gender" >
                                        <p>성별</p>
                                        <select name="gender" id="gender" onchange="setComboGenderValue()">
                                            <option value="male">남</option>
                                            <option value="female">여</option>
                                        </select>
                                    </div>
                                    <div id="input_box" class="email" >
                                        <p>이메일</p>
                                        <input type="text" name="email1" placeholder="이메일"  value="${email1 }"> @
                                        <select name="email2">
                                            <option value="naver.com">naver.com</option>
                                            <option value="daum.net">daum.net</option>
                                            <option value="nate.com">nate.com</option>
                                        </select>
                                    </div>
                                    <div id="input_box" class="phone" >
                                        <p>전화번호</p>
                                        <select name="phone_0" id="phone_0" onchange="etComboPhoneValue(${phone_0 })">
                                            <option value="KT">KT</option>
                                            <option value="SK">SK</option>
                                            <option value="LG">LG</option>
                                        </select>
                                        <input type="text" name="phone_1" maxlength="3" size="2" value="${phone_1 }"> -
                                        <input type="text" name="phone_2" maxlength="4" size="3" value="${phone_2 }"> -
                                        <input type="text" name="phone_3" maxlength="4" size="3" value="${phone_3 }">
                                    </div>
                                </div>
                            </div>
                            <div class="btn_box">
                                <input type="submit" value="회원 수정">
                            </div>
                        </form>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="../footer.jsp"></jsp:include>
    <script type="text/javascript">
	function init(){
		setComboMailValue("${email2}");
		setComboPhoneValue("${phone_0}");
		setComboGenderValue("${gender}");
		console.log("${gender}");
	}
	
	function setComboMailValue(val){
		var selectMail = document.getElementById('email2');
		for(i=0; j= selectMail.length; i<j; i++){
			if(selectMail.options[i].value == val){
				selectMail.options[i].selected = true;
				
				break;
			}
		}
	}
	
	function setComboPhoneValue(val){
		var selectPhone = document.getElementById('phone_0');
		console.log("${gender}");
		for(i=0; j= selectPhone.length; i<j; i++){
			if(selectPhone.options[i].value == val){
				selectPhone.options[i].selected = true;
				break;
			}
		}
	}
	
	function setComboGenderValue(val){
		var selectGender = document.getElementById('gender');
		for(i=0; j= selectGender.length; i<j; i++){
			if(selectGender.options[i].value.equals(val)){
				selectGender.options[i].selected = true;
				break;
			}
		}
	}
	
	function checkForm(){

		if(!document.updateMemberForm.password.value){
			alert("비밀번호를 입력하세요");
			return false;
		}
		if(document.updateMemberForm.password.value != document.updateMemberForm.password_confirm.value){
			alert("비밀번호를 동일하게 입력하세요");
			return false;
		}
	}
</script>
</body>
</html>