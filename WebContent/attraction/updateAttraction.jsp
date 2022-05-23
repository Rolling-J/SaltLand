<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="mvc.model.AttractionDTO" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="https://kit.fontawesome.com/a3555d8f42.js"></script>
	<link rel="stylesheet" href="/SaltProject/resources/css/addAttraction.css" />
	<link rel="stylesheet" href="/SaltProject/resources/css/menu.css">
	<link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
	<script defer src="/SaltProject/resources/JS/validation_attraction.js"></script>
	
	<title>어트랙션 수정</title>
</head>
<body>
<%
	AttractionDTO atr = (AttractionDTO) request.getAttribute("attraction");
%>
	<div class="all">
		<jsp:include page="/menu.jsp"/>
		<div class="a_box">
			<form name ="attraction" action="./UpdateAttractionAction.do?id=<%=atr.getId() %>" class="form-horizontal" method="post" enctype="multipart/form-data">
				<h1>어트랙션 수정</h1>
			    <div class="search">
					<label id="name-label" for="name">어트랙션 명</label>
					<input type="text" name="name" id="name" class="s_box" value=<%=atr.getName() %> placeholder="어트랙션 이름을 입력해주세요." required/>
			    </div>
				<div class="search">
					<p>설명</p> 
					<textarea id="info" name="info" class="info" rows="5" cols="50" placeholder="어트랙션 설명을 입력해주세요."> <%=atr.getInfo() %></textarea>
			    </div>
			    <div class="search">
					<p>테마</p> 
					<%
						String tag = atr.getTag();
					%>
					<label><input name="tag" value="survival" type="radio" class="tema_tags" <%if(tag.equals("survival")){ %>checked<%} %> />서바이벌</label>
					<label><input name="tag" value="adventure" type="radio" class="tema_tags" <%if(tag.equals("adventure")){ %>checked<%} %> />어드벤쳐</label>
					<label><input name="tag" value="kiddyzone" type="radio" class="tema_tags" <%if(tag.equals("kiddyzone")){ %>checked<%} %> />키디존</label>
					<label><input name="tag" value="horror" type="radio" class="tema_tags" <%if(tag.equals("horror")){ %>checked<%} %> />호러</label>
					<label><input name="tag" value="photozone" type="radio" class="tema_tags" <%if(tag.equals("photozone")){ %>checked<%} %> />포토존</label>
					<label><input name="tag" value="experience" type="radio" class="tema_tags" <%if(tag.equals("experience")){ %>checked<%} %> />체험관</label>    
			    </div>
			    <div class="search">
					<p>탑승 인원</p>
					<%
						String ride = atr.getRide();
					%>
					<select id="ride" name="ride" class="s_box" required>
						<option disabled selected>탑승 인원을 선택해주세요.</option>
						<option value="10" <%if(ride.equals("10")){ %>selected<%} %>>10명</option>
						<option value="20" <%if(ride.equals("20")){ %>selected<%} %>>20명</option>
						<option value="30" <%if(ride.equals("30")){ %>selected<%} %>>30명</option>
						<option value="40" <%if(ride.equals("40")){ %>selected<%} %>>40명</option>
						<option value="50" <%if(ride.equals("50")){ %>selected<%} %>>50명</option>
					</select>
			    </div>
		
			    <div class="search">
					<p>탑승 나이</p>
					<%
						String age = atr.getAge();
					%>
					<select id="age" name="age" class="s_box" required>
						<option disabled selected>나이를 고르세요</option>
						<option value="0~8" <%if(age.equals("0~8")){ %>selected<%} %>>0세~8세</option>
						<option value="9~64" <%if(age.equals("9~64")){ %>selected<%} %>>9세~64세</option>
						<option value="none" <%if(age.equals("none")){ %>selected<%} %>>제한 없음</option>
					</select>
			    </div>
				<div class="search">
					<p>키</p>
					<%
						String tall = atr.getTall();
					%>
					<select id="tall" name="tall" class="s_box" required>
						<option disabled selected>키를 고르세요</option>
						<option value="130cm~190cm" <%if(tall.equals("130cm~190cm")){ %>selected<%} %>>130cm~190cm</option>
						<option value="none" <%if(tall.equals("none")){ %>selected<%} %>>제한 없음</option>
					</select>
				</div>
			    <div class="search">
					<img src="/SaltProject/resources/image/<%=atr.getFilename() %>">
					<p><br>이미지를 수정하시려면 아래에서 파일을 추가해주세요.</p>
					<input type="file" name="filename" id="filename">
					<input type="hidden" name="imageName" id="imageName" value="<%=atr.getFilename() %>">
			    </div>
			    <div class="search">
			    	<input type="submit" class="submit-button" value="수정" onclick="return checkAttraction()">
			    	<input type="reset" value="다시" class="s_btn">
			    </div>
			</form>
		</div>
		<jsp:include page="/footer.jsp"/>
	</div>
</body>
</html>