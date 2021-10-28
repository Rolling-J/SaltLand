<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "mvc.model.BoardDTO" %>

<%
	BoardDTO notice = (BoardDTO) request.getAttribute("board");
	int num = ((Integer) request.getAttribute("num")).intValue();
	int nowpage = ((Integer) request.getAttribute("page")).intValue();
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="noticeDetail.css">
    <link rel="stylesheet" href="../bar.css">
    
    
    <title>공지 글</title>
</head>
<body>
    <jsp:include page="../bar.jsp"/>
    <div class="a_box">
        <p class="category"> 홈>이용가이드>공지 및 알림</p>
        <div class="center_box">
            <div class="s_title">
                <strong class="a" >공지사항</strong>
            </div>
        </div>
    </div>
    <div class="board_wrap">
        <form name="newUpdate" action="BoardUpdateAction.do?num=<%=notice.getNum() %>&pageNUm=<%=nowpage %>" method="post">
            <div class="board_box">
                <div class="board_header">
                    <div class="head_text">
                        <p class="b_cat">공지</p>
                        <p class="b_title">솔트 랜드 운영 안내</p>
                        <p class="b_date">2021.10.24</p>
                    </div>
                </div>
                <div class="board_body">
                    <div class="body_text">
                        <div class="board_img">
                            <img src="./image/event02.jpg">
                        </div>
                        <p>
                            안녕하세요.<br> 환상의 나라 솔트 랜드입니다.<br><br>
                            솔트 랜드 운영과 관련하여 공지합니다.
                        </p>
                    </div>
                </div>
            </div>
            <div class="buttons">
                <div class="personal_btns">
                    <input type="submit" value="수정" id="btm_buttons">
                    <a href="#" id="btm_buttons">삭제</a>
                </div>
                <div class="list_btn">
                    <a href="/BoardListAction.do?pageNum=1" id="btm_buttons">목록으로</a>
                </div>
            </div>
        </form>
    </div>
    <jsp:include page="../footer.jsp"/>
</body>
</html>