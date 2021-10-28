<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String name = (String) request.getAttribute("name");
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="addNotice.css">
    <link rel="stylesheet" href="../bar.css">
    
<script type="text/javascript">
	function checkForm(){
		if(!document.newWrite.name.value){
			alert("성명을 입력하세요.");
			return false;
		}
		if(!document.newWrite.subject.value){
			alert("제목을 입력하세요.");
			return false;
		}
		if(!document.newWrite.content.value){
			alert("내용을 입력하세요.");
			return false;
		}
	}

</script>    
    
    <title>공지 작성</title>
</head>
<body>
    <jsp:include page="../bar.jsp"></jsp:include>
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
            <p>새 글 작성</p>
        </div>
        <form name="newWrite" action="./BoardWriteAction.do" class="" method="post" onsubmit="return checkForm()">
            <div class="board_box">
                <div class="board_header">
                    <div class="head_text">
                        <select class="category">
                            <option>공지</option>
                            <option>이벤트</option>
                        </select>
                        <input type="text" name="title">
                        <input type="text" name="name" value="name">
                    </div>
                </div>
                <div class="board_body">
                    <div class="body_text">
                        <textarea name="content" class="b_content" cols="100" rows="25"></textarea>
                        <p>이미지 파일 선택</p>
                        <input type="file" name="imgName">
                    </div>
                </div>
            </div>
            <div class="buttons">
                <div class="personal_btns">
                    <input type="submit" value="저장" id="btm_buttons">
                </div>
                <div class="list_btn">
                    <a href="noticeboard.html" id="btm_buttons">목록으로</a>
                </div>
            </div>
        </form>
    </div>
    <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>