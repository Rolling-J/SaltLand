<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>  
<!DOCTYPE html>
<html>
<head>
  <script type ="text/javascript" src ="./resources/js/validation.js"></script>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <script src="https://kit.fontawesome.com/a3555d8f42.js" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="/SaltProject/resources/css/addAttraction.css" />
    <link rel="stylesheet" href="/SaltProject/resources/css/menu.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
  
<script>

</script>

</head>
<body>
  <jsp:include page="/menu.jsp"/>
	<%@ include file="/resources/database/dbconn.jsp" %>>
 	<%
  		String name = request.getParameter("id");
  
 		PreparedStatement pstmt = null;
	 	ResultSet rs = null;

		String sql = "select * from attraction where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		rs = pstmt.executeQuery();
		if (rs.next()) {
  %>

<!-- 테스트 -->
<div class="a_box">
	<form name ="newattraction" action="processaddattraction.jsp" class="form-horizontal" method="post" enctype="multipart/form-data">
		<h1>어트랙션 등록</h1>
		
	    <div class="search">
	      <label id="name-label" for="name">어트랙션 명</label>
	      <input type="text" name="name" id="name" class="s_box" value='<%=rs.getString("name")%>' placeholder="어트랙션 이름을 입력해주세요." required/>
	    </div>
	 
	   <div class="search">
	      <p>설명</p> 
	      <textarea id="info" name="info" class="info" rows="5" cols="50" placeholder="어트랙션 설명을 입력해주세요."> <%=rs.getString("info")%></textarea>
	    </div>
	
	    <div class="search">
	      <p>테마</p> 
	      <%
	      	String tag = rs.getString("tag");
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
	      <select id="ride" name="ride" class="s_box" required>
	        <option disabled selected value='<%=rs.getString("name")%>'>탑승 인원을 선택해주세요.</option>
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
	        <option disabled selected value>나이를 고르세요</option>
	        <option value="0~8">0세~8세</option>
	        <option value="9~64">9세~64세</option>
	        <option value="제한 없음">제한 없음</option>
	      </select>
	    </div>
	
	    <div class="search">
	      <p>키</p>
	      <select id="tall" name="tall" class="s_box" required>
	        <option disabled selected value>키를 고르세요</option>
	        <option value="130cm~190cm">130cm~190cm</option>
	        <option value="제한 없음">제한 없음</option>
	      </select>
	    </div>
	
	    
	
	    <div class="search">
	      <input type="file" name="filename" value='<%=rs.getString("filename")%>'>
	    </div>
	    <div class="search">
	    	<input type="button" class="submit-button" value="등록" onclick="checkaddattraction()">
	      <!--<button type="submit" id="submit" class="submit-button">전송</button>-->
	      <input type="reset" value="다시" class="s_btn">
	    </div>
	</form>
</div>
<%
	}
	if (rs != null)
		rs.close();
	if (pstmt != null)
			pstmt.close();
	if (conn != null)
		conn.close();
%>
<jsp:include page="/footer.jsp"/>
</body>
</html>