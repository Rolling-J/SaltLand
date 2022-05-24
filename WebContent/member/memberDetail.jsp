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
    <link rel="stylesheet" href="/SaltProject/resources/css/bar.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
	<%
		String sessionId = (String)session.getAttribute("sessionId");
		MemberDTO member = (MemberDTO) request.getAttribute("member");
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
                        <div class="info_box selected"> <!-- login tap container -->
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
									            	List<TicketDTO> ticketList = (List<TicketDTO>) request.getAttribute("ticketList");
									            	System.out.println("ticketList : "+ticketList);
									            	//type safety : unchecked cast from List ... 알림 무시
									            	if(ticketList.isEmpty()){
												%>
												<tr>
													<td></td>
													<td>
														<p>예약된 티켓이 없습니다</p>
													</td>
													<td></td>
												</tr>
												<%
													}else{
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
										<!-- page button 자리 -->
									</div>
	                            </div>
	                            <div class="btn_box">
	                                <a href="./UpdateMemberForm.do?sessionId=<%=sessionId %>" class="btn_a">회원수정</a>
	                                <a href="#" onclick="deleteConfirm('<%=sessionId %>')" class="btn_a" role= "button">회원탈퇴</a>
	                            </div>
                        </div><!-- login tap container end -->
                    </div>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>