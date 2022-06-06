<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="java.util.*"%>
<%@page import="mvc.model.TicketDAO"%>
<%@ page import="mvc.model.TicketDTO"%>
<%@ page import="mvc.model.MemberDTO" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/a3555d8f42.js"></script>
    <link rel="stylesheet" href="/SaltProject/resources/css/memberDetail.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/menu.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
	<%
		String sessionId = (String)session.getAttribute("sessionId");
		
		MemberDTO member = (MemberDTO) request.getAttribute("member");
		List<TicketDTO> ticketList = (List<TicketDTO>) request.getAttribute("ticketList"); //type safety : unchecked cast from List ... 알림 무시
		int total_record = ((Integer) request.getAttribute("total_ticket")).intValue();
		int pageNum = ((Integer) request.getAttribute("pageNum")).intValue();
		int total_page = ((Integer) request.getAttribute("total_page")).intValue();
	%>
	<script type="text/javascript">
		function deleteConfirm(id) {
			if (confirm(id+"님, 정말로 회원탈퇴 하시겠습니까?\n예약된 티켓이 있다면 모두 예약이 취소됩니다.") == true)
				location.href = "./DeleteMemberAction.do?id=<%=sessionId %>";
			else
				return;
		}
	</script>
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
                        <div class="info_box"> <!-- login tap container -->
	                            <div class="ps_container">
	                                <div class="personal_box">
	                                    <div id="input_box" class="id_box"> 
	                                        <p>아이디 : <c:out value="<%=member.getId() %>"/></p>
	                                    </div>
	                                    <div id="input_box" class="name" >
	                                        <p>이름 : <c:out value="<%=member.getName() %>" /></p>
	                                    </div>
	                                    <div id="input_box" class="birth">
	                                        <p>생년월일 : <c:out value="<%=member.getBirth() %>" /></p>
	                                    </div>
	                                    <div id="input_box" class="gender" >
	                                    	<c:set var="gender" value="<%=member.getGender() %>" />
	                                        <c:choose>
	                                        	<c:when test="${ gender.equals('male')}">
	                                        		<p>성별 : 남</p>
	                                        	</c:when>
	                                        	<c:when test="${ gender.equals('female')}">
	                                        		<p>성별 : 여</p>
	                                        	</c:when>
	                                        	<c:otherwise>
	                                        		<p> 미선택 </p>
	                                        	</c:otherwise>
	                                        </c:choose>
	                                    </div>
	                                    <div id="input_box" class="email" >
	                                        <p>이메일 : <c:out value="<%=member.getMail() %>" /></p>
	                                    </div>
	                                    <div id="input_box" class="phone" >
	                                        <p>전화번호 : <c:out value="<%=member.getPhone() %>" /></p>
	                                    </div>
	                                </div>
                                	<div class="ticket_box">
                                		<div class="table_box">
					                    	<table class="tbl_header">
												<thead>
											    	<tr>
											        	<th>방문일</th>
											            <th>인원<br>(성인/청소년/어린이)</th>
											            <th>예약 일시</th>
											        </tr>
											    </thead>
										        <tbody>
										            <%
										            	if(ticketList.isEmpty()){ //티켓 예약정보가 없을때
													%>
													<tr>
														<td colspan="3">
															<p>예약된 티켓이 없습니다</p>
														</td>
													</tr>
													<%
														}else{ //티켓 예약정보가 있을때
															for(int j = 0; j <ticketList.size(); j++){
																TicketDTO ticket = (TicketDTO) ticketList.get(j);
													%>
													<tr>
														<td>
															<a href="./TicketDetailView.do?reserve_num=<%=ticket.getReserve_num()%>">
																<%=ticket.getVisit_date() %>
															</a>
														</td>
														<td>
															<a href="./TicketDetailView.do?reserve_num=<%=ticket.getReserve_num()%>">
																<%=ticket.getAdult() %>/<%=ticket.getTeenager() %>/<%=ticket.getChildren() %>
															</a>
														</td>
														<td>
															<a href="./TicketDetailView.do?reserve_num=<%=ticket.getReserve_num()%>">
																<%=ticket.getReserve_time() %>
															</a>
														</td>
													</tr>
													<%
															}
														}
													%>
												</tbody>
											</table>
										</div>
										<!-- page button 자리 -->
										<div class="paging">
											<c:set var="pageNum" value="<%=pageNum %>"/>
											<c:set var="sessionId" value="<%=sessionId %>"/>
											<c:forEach var="i" begin="1" end="<%=total_page %>">
												<a href="<c:url value="./MemberDetailView.do?sessionId=${sessionId }&pageNum=${i }" />" ><c:out value="${i }" /></a>
											</c:forEach>
										</div>
									</div>
	                            </div>
	                            <div class="btn_box">
	                                <a href="./UpdateMemberForm.do?sessionId=<%=sessionId %>" class="btn_a">회원수정</a>
	                                <a href="#" onclick="deleteConfirm('<%=sessionId %>')" class="btn_a" role= "button">회원탈퇴</a>
	                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>