<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.oreilly.servlet.*"%>
<%@ page import="com.oreilly.servlet.multipart.*"%>
<%@ page import="java.util.*"%>
<%@ page import="dto.attraction"%>
<%@ page import="dao.attractionbox"%> 

<%
	request.setCharacterEncoding("UTF-8");

	String filename = "";
	String realFolder = "c:\\upload";
	int maxSize=5*1024*1024;
	String encType = "utf-8";
	
	MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());

	String name = multi.getParameter("name");
	String info = multi.getParameter("info");
	String tag = multi.getParameter("tag");
	String ride = multi.getParameter("ride");
	String age = multi.getParameter("age");
	String tall = multi.getParameter("tall");
	
	Enumeration files = multi.getFileNames();
	String fname = (String) files.nextElement();
	String fileName = multi.getFilesystemName(fname);
	
	attractionbox dao = attractionbox.getInstance();
	
	attraction newattraction = new attraction();
	newattraction.setName(name);
	newattraction.setInfo(info);
	newattraction.setTag(tag);
	newattraction.setRide(ride);
	newattraction.setAge(age);
	newattraction.setTall(tall);
	newattraction.setFilename(filename);
	
	dao.addattraction(newattraction);
	
	response.sendRedirect("play.jsp");
	



%>