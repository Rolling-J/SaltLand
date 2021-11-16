<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="dto.attraction"%>
<%@ page import="dao.attractionbox"%>
<%@ page import="java.sql.*"%>
    
<!DOCTYPE html>
<html>
<head>
  <script type ="text/javascript" src ="./resources/js/validation.js"></script>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <script src="https://kit.fontawesome.com/a3555d8f42.js"></script>
  <link rel="stylesheet" href="./resources/css/add.css" />
</head>
<body>
  <jsp:include page="/menu.jsp"/>

<div class="a_box">
	<form name ="newattraction" action="processaddattraction.jsp" class="form-horizontal" method="post" enctype="multipart/form-data">
	  	<h1>어트랙션 등록</h1>
	
	    <div class="search">
	      <label id="name-label" for="name">어트랙션 명</label>
	      <input type="text" name="name" id="name" class="s_box" placeholder="어트랙션 이름을 입력해주세요." required/>
	    </div>
	 
	   	<div class="search">
	      <p>설명</p> 
	      <textarea id="info" name="info" class="info" rows="5" cols="50" placeholder="어트랙션 설명을 입력해주세요.">
	      </textarea>
	    </div>
	
	    <div class="search">
	      <p>테마</p> 
	      <label><input name="tag" value="서바이벌" type="radio" class="tema_tags" checked/>서바이벌</label>
	      <label><input name="tag" value="어드벤쳐" type="radio" class="tema_tags"/>어드벤쳐</label>
	      <label><input name="tag" value="키디존" type="radio" class="tema_tags" />키디존</label>
	      <label><input name="tag" value="호러" type="radio" class="tema_tags" />호러</label>
	      <label><input name="tag" value="포토존" type="radio" class="tema_tags" />포토존</label>
	      <label><input name="tag" value="체험관" type="radio" class="tema_tags" />체험관</label>    
	    </div>
	
	    <div class="search">
	      <p>탑승 인원</p>
	      <select id="ride" name="ride" class="s_box" required>
	        <option disabled selected>탑승 인원을 선택해주세요.</option>
	        <option value="10명">10명</option>
	        <option value="20명">20명</option>
	        <option value="30명">30명</option>
	        <option value="40명">40명</option>
	        <option value="50명">50명</option>
	      </select>
	    </div>
	
	    <div class="search">
	      <p>탑승 나이</p>
	      <select id="age" name="age" class="s_box" required>
	        <option disabled selected>나이를 고르세요</option>
	        <option value="0세~4세">0세~8세</option>
	        <option value="9세~64세">9세~64세</option>
	        <option value="제한 없음">제한 없음</option>
	      </select>
	    </div>
	
	    <div class="search">
	      <p>키</p>
	      <select id="tall" name="tall" class="s_box" required>
	        <option disabled selected>키를 고르세요</option>
	        <option value="130cm~190cm">130cm~190cm</option>
	        <option value="제한 없음">제한 없음</option>
	      </select>
	    </div>
	
	    <div class="search">
	      <input type="file" name="filename">
	    </div>
	    <div class="search">
	    	<input type="button" class="submit-button" value="등록" onclick="checkaddattraction()">
	      <!--<button type="submit" id="submit" class="submit-button">전송</button>-->
	      <input type="reset" value="다시" class="s_btn">
	    </div>
  	</form>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>