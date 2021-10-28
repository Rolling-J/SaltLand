<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String passwd = request.getParameter("password");
%>

<sql:setDataSource var="dataSource" url="jdbc:mysql://localhost:3306/SaltLand"
	driver="com.mysql.jdbc.Driver" user="root" password="1234"/>
	
<sql:query dataSource="${dataSource }" var="resultSet">
	Select * from member where id=? and password=?
	<sql:param value="<%=id %>"/>
	<sql:param value="<%=passwd %>"/>
</sql:query>

<c:forEach var="row" items="${resultSet.rows }">
	<%
		session.setAttribute("sessionId", id);
	%>
	<c:redirect url="resultMember.jsp?msg=2" />
</c:forEach>

<c:redirect url="loginMember.jsp?error=1" />