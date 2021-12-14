<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%
	String sessionId = (String) session.getAttribute("sessionId");
	String reserve_num = request.getParameter("reserve_num");
	System.out.println("reserve_num : "+reserve_num);
%>

<sql:setDataSource var="dataSource" url="jdbc:mysql://localhost:3306/SaltLand?serverTimezone=Asia/Seoul&useSSL=false" driver="com.mysql.jdbc.Driver" user="root" password="1234"/>

<sql:update dataSource="${dataSource }" var="resultSet">
	delete from ticket where reserve_num = ?
	<sql:param value="<%=reserve_num %>" />
</sql:update>

<c:if test="${resultSet>=1}">

	<c:redirect url="/member/memberDetail.jsp" />
</c:if>
<c:if test="${resultSet<1 }">
	<c:out value="sql update error" />
</c:if>