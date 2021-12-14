<%@page import="dao.TicketDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="java.util.*"%>
<%@ page import="dto.Ticket"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/SaltProject/resources/css/memberDetail.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/bar.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
<%
	String sessionId = (String)session.getAttribute("sessionId"); 
%>
<sql:setDataSource var="dataSource" url="jdbc:mysql://localhost:3306/SaltLand" driver="com.mysql.jdbc.Driver" user="root" password="1234" />
<sql:query dataSource="${dataSource}" var="resultSet">
	select * from member where id = ?
	<sql:param value="<%=sessionId %>" />
</sql:query>

    <title>회원 정보</title>
</head>
<body>
	<script type="text/javascript" src="/SaltProject/resources/JS/memberDetailTab.js"></script>
    <jsp:include page="/menu.jsp"></jsp:include>
    <section id="reserve_main">
        <div class="background">
            <div class="wrap_body">
                <div class="content_wrap">
                    <div class="content_box">
                        <div class="box_head">
                            <p>회원 정보</p>
                         </div>
                        <div class="divine"></div>
                        <div class="info_box selected"> <!-- login tap container -->
                        	<c:forEach var="row" items="${resultSet.rows}">
	                            <div class="ps_container">
	                                <div class="personal_box">
	                                    <div id="input_box" class="id_box"> 
	                                        <p>아이디 : <c:out value="${row.id }"/></p>
	                                    </div>
	                                    <div id="input_box" class="name" >
	                                        <p>이름 : <c:out value="${row.name }" /></p>
	                                    </div>
	                                    <div id="input_box" class="birth">
	                                        <p>생년월일 : <c:out value="${row.birth }" /></p>
	                                    </div>
	                                    <div id="input_box" class="gender" >
	                                        <c:choose>
	                                        	<c:when test="${row.gender.equals('male') }">
	                                        		<p>성별 : 남</p>
	                                        	</c:when>
	                                        	<c:when test="${row.gender.equals('female') }">
	                                        		<p>성별 : 여</p>
	                                        	</c:when>
	                                        	<c:otherwise>
	                                        		<p> 미선택 </p>
	                                        	</c:otherwise>
	                                        </c:choose>
	                                    </div>
	                                    <div id="input_box" class="email" >
	                                        <p>이메일 : <c:out value="${row.mail }" /></p>
	                                    </div>
	                                    <div id="input_box" class="phone" >
	                                        <p>전화번호 : <c:out value="${row.phone }" /></p>
	                                    </div>
	                                </div>
	                                
	                                	<div class="table_box">
					                    	<table class="tbl_header">
												<thead>
											    	<tr>
											        	<th>방문일</th>
											            <th>인원 (성인/청소년/어린이)</th>
											            <th>예약 일시</th>
											        </tr>
											    </thead>
										         <tbody>
										            <%
										            	TicketDAO dao = TicketDAO.getInstance();
														List<Ticket> ticketList = dao.getTicketList(sessionId);
														
														for(int j = 0; j <ticketList.size(); j++){
															Ticket ticket = (Ticket) ticketList.get(j);
													%>
													<tr>
														<td>
															<a href="/SaltProject/ticket/ticketDetail.jsp?reserve_num=<%=ticket.getReserve_num()%>">
																<%=ticket.getVisit_date() %>
															</a>
														</td>
														<td>
															<a href="/SaltProject/ticket/ticketDetail.jsp?reserve_num=<%=ticket.getReserve_num()%>">
																<%=ticket.getAdult() %>/<%=ticket.getTeenager() %>/<%=ticket.getChildren() %>
															</a>
														</td>
														<td>
															<a href="/SaltProject/ticket/ticketDetail.jsp?reserve_num=<%=ticket.getReserve_num()%>">
																<%=ticket.getReserve_time() %>
															</a>
														</td>
													</tr>
													<%
														}
													%>
												</tbody>
											</table>
										</div>
									
	                            </div>
	                            <div class="btn_box">
	                                <a href="updateMember.jsp" class="btn_a">회원수정</a>
	                                <a href="deleteMember.jsp" class="btn_a">회원탈퇴</a>
	                            </div>
                            </c:forEach>
                        </div><!-- login tap container end -->
                        <div class="info_box selected">
                        	
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>