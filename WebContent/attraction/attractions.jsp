<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/attractions.css" />
    <title>Document</title>
    <script src="https://kit.fontawesome.com/a3555d8f42.js" crossorigin="anonymous"></script>
    <script type="text/javascript">
	function search() {
		var tb = document.getElementById("p_search");
		var tbIndex = document.getElementById("p_search").options.selectedIndex;
		
		console.log("tb value : " + tb.options[tbIndex].value);
	}
</script>
</head>
<body>
<div class="all">
   <jsp:include page="/menu.jsp"/>
    <div class="a_box">
        <p class="category"> 홈>즐길거리>어트랙션</p>
        <div class="s_title">
            <strong class="a" > 어트랙션</strong>
            <p class="pb">일상 속의 짜릿함을 느껴보세요.</p>
        </div>
    </div>
    
    <!-- 검색 -->
    <div class="search_box">       
        <div class="inp">
            <h3>조건 검색</h3>
            <form method="post" action="attractions.jsp" class="search" accept-charset="utf-8">
                <select class="p_search" name="p_search">
                    <option value="서바이벌" selected>서바이벌</option>
                    <option value="호러" selected>호러</option>
                    <option value="어드벤쳐" selected>어드벤쳐</option>
                    <option value="체험관" selected>체험관</option>
                    <option value="키디존" selected>키디존</option>
                    <option value="포토존" selected>포토존</option>
                </select>
                <select class="age_search" name="age_search">
                    <option value="0세~4세">0세~8세</option>
        			<option value="9세~64세">9세~64세</option>
        			<option value="제한 없음">제한 없음</option>
                </select>
                <select class="tall_search" name="tall_search">
                    <option value="130cm~190cm">130cm~190cm</option>
        			<option value="제한 없음">제한 없음</option>
                </select>
                <p></p>
                <input type="submit" value="검색" class="s_btn">
                <input type="reset" value="다시" class="s_btn">
            </form>
        </div>
    </div>
    		
    		
    <div class="card_box">
    <%@ include file="/resources/database/dbconn.jsp" %>
    <%
    
    	
    	PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		request.setCharacterEncoding("utf-8");
		String p_search = request.getParameter("p_search");
		String age_search = request.getParameter("age_search");
		String tall_search = request.getParameter("tall_search");
	
		String sql = "select * from attraction";
		if (p_search == null && age_search == null && tall_search == null){
			sql = "select  * from attraction";
		}else{
			sql = "SELECT * FROM attraction where tag= '"+ p_search +"' and age='" + age_search + "' and tall='"+tall_search+"'";
		}
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
    %> 
        <a href="./dp.jsp?id=<%=rs.getString("id")%>" class="card1">
            <div class="in_card">
                <span class="tag"><%=rs.getString("tag")%></span>
            </div>    
                 <img src="/a/resources/image/<%=rs.getString("filename")%>" alt="play" style="width: 100%;">
            <div class="con">
                <h4 class="con1"><%=rs.getString("name")%></h4>
            </div>           
        </a>
      <%
			}
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
    %>
    </div>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>