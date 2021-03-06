<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="mvc.model.BoardDTO"%>


<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/a3555d8f42.js"></script>
    <link rel="stylesheet" href="/SaltProject/resources/css/boardList.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/menu.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/footer.css">

    <title>공지 게시판</title>
</head>
<body>
<%
	String sessionId = (String) session.getAttribute("sessionId");
	List boardList = (List) request.getAttribute("boardlist");
	int total_record = ((Integer) request.getAttribute("total_record")).intValue();
	int pageNum = ((Integer) request.getAttribute("pageNum")).intValue();
	int total_page = ((Integer) request.getAttribute("total_page")).intValue();
%>
    <jsp:include page="/menu.jsp"></jsp:include>
    <form action="<c:url value="./BoardListAction.do"/>" method="post">
	    <div class="a_box">
	        <p class="category"> 홈>이용가이드>공지 및 알림</p>
	        <div class="center_box">
	            <div class="s_title">
	                <strong class="a" >공지사항</strong>
	            </div>
	            <div class="search_box">
	                <div class="inp">
	                    <select name="s_category" id="">
	                        <option value="all">전체</option>
	                        <option value="notice">공지</option>
	                        <option value="event">이벤트</option>
	                    </select>
	                    <input type="text" name="s_text">
	                </div>
	                <input type=submit class="search_btn" value="검색">
	            </div>
	        </div>
	    </div>
	    <div class="board_box">
	    	<div class="board_head">
		    	<p>전체 <%=total_record %> 건</p>
		    	<c:choose>
				    <c:when test="${!empty sessionId }">
				        <a href="./BoardWriteForm.do?id=<%=sessionId %>" class="b_add">새글작성</a>
				    </c:when>
			    </c:choose>
		    </div>
	        <table class="tbl_header">
	            <thead>
	                <tr>
	                    <th>번호</th>
	                    <th>분류</th>
	                    <th>제목</th>
	                    <th>등록일</th>
	                    <th>작성자</th>
	                </tr>
	            </thead>
	        </table>
	        <table class="tbl_content">
	            <tbody>
					<%
						for(int j = 0; j <boardList.size(); j++){
							BoardDTO notice = (BoardDTO) boardList.get(j);
					%>
							<tr>
								<td><%=notice.getNum() %></td>
								<td>
									<c:set var="category" value="<%=notice.getCategory() %>" />
		                    		<c:choose>
			                    		<c:when test="${ category.equals('notice')}">
			                    			<c:out value="공지" />
			                    		</c:when>
			                    		<c:when test="${ category.equals('event')}">
			                    			<c:out value="이벤트" />
			                    		</c:when>
		                    		</c:choose>
								</td>
								<td>
									<a href="./BoardViewAction.do?num=<%=notice.getNum()%>">
										<%=notice.getTitle() %>
									</a>
								</td>
								<td><%=notice.getRegist_day() %></td>
								<td><%=notice.getName() %></td>				
							</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<div class="paging">
				<c:set var="pageNum" value="<%=pageNum %>"/>
				<c:forEach var="i" begin="1" end="<%=total_page %>">
					<a href="<c:url value="./BoardListAction.do?pageNum=${i }" />" ><c:out value="${i }" /></a>
				</c:forEach>
			</div>
	    </div>
	</form>
    <jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>