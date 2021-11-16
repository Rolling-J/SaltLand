<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<%@ include file="/resources/database/dbconn.jsp" %>

<!DOCTYPE html>
<html>
<body>
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
            <form method="post" action="attractions.jsp" class="search">
                <select class="p_search">
                    <option value="1" selected>서바이벌</option>
                    <option value="2" selected>호러</option>
                    <option value="3" selected>어드벤쳐</option>
                    <option value="4" selected>체험관</option>
                    <option value="5" selected>키디존</option>
                    <option value="6" selected>포토존</option>
                </select>
                <select class="age_search">
                    <option value="1" selected>0~4세</option>
                    <option value="2" selected>5~64세</option>
                    <option value="3" selected>65세 이상</option>
                </select>
                <select class="tall_search">
                    <option value="1" selected>100cm~150cm</option>
                    <option value="2" selected>150cm~185cm</option>
                </select>
                <p></p>
                <input type="submit" value="검색" class="s_btn">
                <input type="reset" value="다시" class="s_btn">
            </form>
        </div>
    </div>
    		
    		
    <div class="card_box">
    <%
    	PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String sql = "select * from attraction";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
    %> 
        <a href="./dp.jsp?name=<%=rs.getString("name")%>" class="card1">
            <div class="in_card">
                <span class="tag"><%=rs.getString("tag")%></span>
            </div>    
                 <img src="c:/upload/<%=rs.getString("filename")%>" alt="play" style="width: 100%;">
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
	<jsp:include page="/footer.jsp" />
</body>
</html>