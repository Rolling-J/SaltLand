<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "mvc.model.BoardDTO" %>

<%
	BoardDTO notice = (BoardDTO) request.getAttribute("board");
	int num = ((Integer) request.getAttribute("num")).intValue();
	int nowpage = ((Integer) request.getAttribute("page")).intValue();
%>
<%
	String sessionId = (String)session.getAttribute("sessionId");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/SaltProject/resources/css/noticeDetail.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/bar.css">

    <title>공지 글</title>
</head>
<body>
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
        <div class="board_title">
            <p>게시글 수정</p>
        </div>
        <form name="newUpdate" enctype="multipart/form-data" action="BoardUpdateAction.do?num=<%=notice.getNum() %>&pageNum=<%=nowpage %>" method="post">
            <div class="board_box">
                <div class="board_header">
                    <div class="head_text">
                    	<select class="b_cat" name="category">
                            <option value="notice">공지</option>
                            <option value="event">이벤트</option>
                        </select>
                    	<input type="text" name="title" class="b_title" value=" <%=notice.getTitle() %>">
                    	<input type="text" name="name" class="b_name" value=" <%=notice.getName() %>">
                    	<p><c:out value="<%=notice.getRegist_day() %>" /></p>
                    </div>
                </div>
                <div class="board_body">
                    <div class="body_text">
                        <div class="board_img">
                        	<img src="/SaltProject/resources/image/<%=notice.getFileName() %>">
                        	<p><br>이미지를 수정하시려면 아래에서 파일을 추가해주세요.</p>
                        	<input type="file" name="filename">
                        </div>
                        <textarea name="content" class="b_content" cols="100" rows="25"><%=notice.getContent() %></textarea>
                    </div>
                </div>
            </div>
            <div class="buttons">
                <div class="personal_btns">
                    <input type="submit" value="확인" id="btm_buttons">
                    <a href="./BoardDeleteAction.do?num=<%=notice.getNum()  %>" id="btm_buttons">삭제</a>
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