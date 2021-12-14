<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="popupLogin.css">
    <title>need to login</title>
    <script>
        function toLogin(){
        	location.opener.href="/member/login.jsp"
            self.close();
            
        }
    </script>
</head>
<body>
    <div class="popup_total">
        <div class="text_des">
            <h1>로그인이 필요한 서비스입니다</h1>
            <h3>로그인 창으로 이동하시겠습니까?</h3>
        </div>
        <div class="btn_box">
            <input type="button" value="확인" onclick="toLogin()"/>
            <input type="button" value="취소" onclick="self.close()"/>
        </div>
    </div>
    
</body>
</html>