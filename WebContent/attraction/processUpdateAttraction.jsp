<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.oreilly.servlet.*"%>
<%@ page import="com.oreilly.servlet.multipart.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ include file="/resources/database/dbconn.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");

	
	String fileName = "";
	String realFolder = "D:\\Study_Programming\\Web_programming\\SaltLand\\WebContent\\resources\\image";
	String encType = "utf-8";
	int maxSize = 5 * 1024 * 1024;
	
	MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());		
	
	String id = multi.getParameter("id");
	String name = multi.getParameter("name");
	String info = multi.getParameter("info");
	String tag = multi.getParameter("tag");
	String ride =multi.getParameter("ride");
	String age = multi.getParameter("age");
	String tall = multi.getParameter("tall");

	Enumeration files = multi.getFileNames();
	String fname = (String) files.nextElement();
	fileName = multi.getFilesystemName(fname);
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	
	String sql = "select * from attraction where id = ?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, id);
	rs = pstmt.executeQuery();
		if (rs.next()) {
			if (fileName != null) {
				sql = "UPDATE attraction SET name=?, info=?, tag=?, ride=?, age=?, tall=?, filename=? WHERE id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, info);
				pstmt.setString(3, tag);
				pstmt.setString(4, ride);
				pstmt.setString(5, age);
				pstmt.setString(6, tall);
				pstmt.setString(7, fileName);
				pstmt.setString(8, id);
				pstmt.executeUpdate();
			} else {
				sql = "UPDATE attraction SET name=?, info=?, tag=?, ride=?, age=?, tall=? WHERE id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, info);
				pstmt.setString(3, tag);
				pstmt.setString(4, ride);
				pstmt.setString(5, age);
				pstmt.setString(6, tall);
				pstmt.setString(7, id);
				pstmt.executeUpdate();
			}
		}
	if (rs != null)
		rs.close();
 	if (pstmt != null)
 		pstmt.close();
 	if (conn != null)
		conn.close();

	response.sendRedirect("editAttraction.jsp?edit=update");
%>


