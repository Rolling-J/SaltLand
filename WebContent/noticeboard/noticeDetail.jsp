<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "mvc.model.BoardDTO" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/a3555d8f42.js"></script>
    <link rel="stylesheet" href="/SaltProject/resources/css/noticeDetail.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/menu.css">
	<link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
	<script type="text/javascript">
	<%
		BoardDTO notice = (BoardDTO) request.getAttribute("board");
		int num = ((Integer) request.getAttribute("num")).intValue();
		String sessionId = (String) session.getAttribute("sessionId");
		
		String content = notice.getContent();
	%>
	function textLine(text){
		textLine = text.replace(/(\r\n|\r|\n)/g, '<br>');
		return textLine;
	}
	
	function load(){
		textLine();
	}
	</script>
    <title>공지 글</title>
</head>
<body onload="load()">
    <jsp:include page="/menu.jsp"/>
    <div class="a_box">
        <p class="category"> 홈>이용가이드>공지 및 알림</p>
        <div class="center_box">
            <div class="s_title">
                <strong class="a" >공지사항</strong>
            </div>
        </div>
    </div>
    <div class="board_wrap">
        <form name="newUpdate" action="UpdateViewAction.do?num=<%=notice.getNum()%>" method="post">
            <div class="board_box">
                <div class="board_header">
                    <div class="head_text">
                    	<p class="b_cat">
                    	<c:set var="category" value="<%=notice.getCategory() %>" />
                    	<c:choose>
                    		<c:when test="${ category.equals('notice')}">
                    			<c:out value="공지" />
                    		</c:when>
                    		<c:when test="${ category.equals('event')}">
                    			<c:out value="이벤트" />
                    		</c:when>
                    	</c:choose>
                    	</p>
                    	<p class="b_title"><c:out value="<%=notice.getTitle() %>" /></p>
                    	<p><c:out value="<%=notice.getRegist_day() %>" /></p>
                    </div>
                </div>
                <div class="board_body">
                    <div class="body_text">
                        <div class="board_img">
                            <img src="/SaltProject/resources/image/<%=notice.getFileName() %>">
                        </div>
                        <p id="contentText"><%=notice.getContent().replace("\n", "<br/>") %></p>
                    </div>
                </div>
            </div>
            <div class="buttons">
                <div class="personal_btns">
	                <c:choose>
			    		<c:when test="${!empty sessionId }">
			        		<input type="submit" value="수정" id="btm_buttons">
			    		</c:when>
			    	</c:choose>
                </div>
                <div class="list_btn">
                    <a href="./BoardListAction.do?pageNum=1" id="btm_buttons">목록으로</a>
                </div>
            </div>
        </form>
    </div>
    <jsp:include page="/footer.jsp"/>
</body>
</html>