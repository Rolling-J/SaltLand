<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<% 
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String year = request.getParameter("b_year");
	String month = request.getParameter("b_month");
	String day = request.getParameter("b_day");
	String birth = year + "/" + month + "/" + day;
    String gender = request.getParameter("gender");
	String mail1 = request.getParameter("email1");
	String mail2 = request.getParameter("email2");
	String mail = mail1 + "@" + mail2;
	String phone1 = request.getParameter("phone_1");
	String phone2 = request.getParameter("phone_2");
	String phone3 = request.getParameter("phone_3");
	String phone = phone1 + "-" + phone2 + "-" + phone3;
	
	Date currentDatetime = new Date(System.currentTimeMillis());
	java.sql.Date sqlDate = new java.sql.Date(currentDatetime.getTime());
	java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDatetime.getTime());
%>

<sql:setDataSource var="dataSource" url="jdbc:mysql://localhost:3306/SaltLand"
	driver="com.mysql.jdbc.Driver" user="root" password="1234"/>
<sql:update dataSource="${dataSource }" var="resultSet">
	insert into member values(?, ?, ?, ?, ?, ?, ?, ?)
	<sql:param value="<%=id %>"/>
	<sql:param value="<%=password %>"/>
	<sql:param value="<%=name %>"/>
	<sql:param value="<%=birth %>"/>
    <sql:param value="<%=gender %>"/>
    <sql:param value="<%=mail %>"/>
	<sql:param value="<%=phone %>"/>
	<sql:param value="<%=timestamp %>"/>
</sql:update>
<c:if test="${resultSet>=1}">
	<c:redirect url="/member/resultMember.jsp?msg=1" /> 
	<!-- 
	jstl core url은 프로젝트명을 주소에 넣지 않음.
	href로 주소를 지정하는 경우에는 프로젝트명까지 입력.
	 -->
</c:if>