<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="mvc.model.BoardDTO"%>

<%
	String sessionId = (String) session.getAttribute("sessionId");
	List boardList = (List) request.getAttribute("boardlist");
	int total_record = ((Integer) request.getAttribute("total_record")).intValue();
	int pageNum = ((Integer) request.getAttribute("pageNum")).intValue();
	int total_page = ((Integer) request.getAttribute("total_page")).intValue();
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="boardList.css">
    <link rel="stylesheet" href="../bar.css">

    <title>공지 게시판</title>
</head>
<body>
    <jsp:include page="../bar.jsp"></jsp:include>
    <div class="a_box">
        <p class="category"> 홈>이용가이드>공지 및 알림</p>
        <div class="center_box">
            <div class="s_title">
                <strong class="a" >공지사항</strong>
            </div>
            <div class="search_box">
                <div class="inp">
                    <select name="category" id="">
                        <option>전체</option>
                        <option>공지</option>
                        <option>이벤트</option>
                    </select>
                    <input type="text" name="text">
                    
                </div>
                <a href="#" class="search_btn">검색 </a>
            </div>
        </div>
    </div>
    <div class="board_box">
	    <c:when test="${!empty sessionId }">
	        <a href="./BoardWriteForm.do?id=<%=sessionId %>" class="b_add">새글작성</a>
	    </c:when>
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
						<td><%=notice.getCategory() %></td>
						<td>
							<a href="./BoardViewAction.do?num=<%=notice.getNum()%>&pageNum=<%=pageNum%>">
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
				<a href="<c:url value="./BoardListAction.do?pageNum=${i }" />" ></a>
			</c:forEach>
		</div>
    </div>
    <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>