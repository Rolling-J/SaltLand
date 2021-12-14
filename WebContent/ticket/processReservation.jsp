<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<% 
	request.setCharacterEncoding("UTF-8");

	String sessionId = (String)session.getAttribute("sessionId"); 
	

	String r_year = request.getParameter("r_year");
	String r_month = request.getParameter("r_month");
	String r_day = request.getParameter("r_day");
	String visitDate = r_year + "/" + r_month + "/" + r_day;
	int adultN = Integer.parseInt(request.getParameter("adultN"));
	int teenagerN = Integer.parseInt(request.getParameter("teenagerN"));
	int childN = Integer.parseInt(request.getParameter("childN"));
	int charge = Integer.parseInt(request.getParameter("totalC"));

	
	Date currentDatetime = new Date(System.currentTimeMillis());
	java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
	java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
%>

<sql:setDataSource var="dataSource" url="jdbc:mysql://localhost:3306/SaltLand" driver="com.mysql.jdbc.Driver" user="root" password="1234"/>

<sql:update dataSource="${dataSource }" var="ticketResultSet">
	insert into ticket(id, visit_date, adult, teenager, children, charge, reserve_time) values(?, ?, ?, ?, ?, ?, ?)
	<sql:param value="<%=sessionId %>"/>
	<sql:param value="<%=visitDate %>"/>
	<sql:param value="<%=adultN %>"/>
	<sql:param value="<%=teenagerN %>"/>
    <sql:param value="<%=childN %>"/>
    <sql:param value="<%=charge %>"/>
	<sql:param value="<%=timestamp %>"/>
</sql:update>

<c:if test="${ticketResultSet>=1}">
	<c:redirect url="/member/memberDetail.jsp" />
</c:if>