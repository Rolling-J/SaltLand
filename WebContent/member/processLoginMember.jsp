<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%
	request.setCharacterEncoding("UTF-8");

	String id = (String) request.getAttribute("id");
	int x = ((Integer)request.getAttribute("result")).intValue();
	
%>
<c:set var="x" value="<%=x %>" />
<c:choose>
	<c:when test="${x==1 }">
		<%
			session.setAttribute("sessionId", id);
		%>
		<c:redirect url="/MemberResultView.do?msg=3" />
	</c:when>
	<c:otherwise>
		<c:redirect url="/LoginView.do?msg=0" />
	</c:otherwise>
</c:choose>
