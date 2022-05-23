<%@ page contentType="text/html; charset=utf-8"%>
<%
	session.invalidate();
	Boolean delete = (Boolean)request.getAttribute("delete");
	if(delete!=null){
		response.sendRedirect("./MemberResultView.do?msg=4");
	}else{
		response.sendRedirect("./LoginView.do");
	}
%>