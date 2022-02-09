<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<%@ include file="/resources/database/dbconn.jsp" %>
<%
	String id = request.getParameter("id");
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "select * from attraction";
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();

	if (rs.next()) {
		sql = "delete from attraction where id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.executeUpdate();
		
	} else
		out.println("Error : 일치하는 상품이 없습니다");
	
	if (rs != null)
		rs.close();
 	if (pstmt != null)
 		pstmt.close();
 	if (conn != null)
		conn.close();
	
 	response.sendRedirect("/SaltProject/attraction/editAttraction.jsp?edit=delete");
%>
