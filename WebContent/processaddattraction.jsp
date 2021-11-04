<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.oreilly.servlet.*"%>
<%@ page import="com.oreilly.servlet.multipart.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ include file="dbconn.jsp" %>

<%
	request.setCharacterEncoding("UTF-8");

	String filename = "";
	String realFolder = "C:\\upload";
	String encType = "utf-8";
	int maxSize = 5 * 1024 * 1024;
	
	MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
	
	String name = multi.getParameter("name");
	String info = multi.getParameter("info");
	String tag = multi.getParameter("tag");
	String ride =multi.getParameter("ride");
	String age = multi.getParameter("age");
	String tall = multi.getParameter("tall");
	
	Enumeration files = multi.getFileNames();
	String fname = (String) files.nextElement();
	String fileName = multi.getFilesystemName(fname);
	
	PreparedStatement pstmt = null;

	String sql = "insert into attraction values(?,?,?,?,?,?,?)";
	pstmt= conn.prepareStatement(sql);
	pstmt.setString(1, name);
	pstmt.setString(2, info);
	pstmt.setString(3, tag);
	pstmt.setString(4, ride);
	pstmt.setString(5, age);
	pstmt.setString(6, tall );
	pstmt.setString(7, filename);
	pstmt.executeUpdate();


	if (pstmt != null)
 		pstmt.close();
 	if (conn != null)
		conn.close();

	response.sendRedirect("attractions.jsp");
%>
