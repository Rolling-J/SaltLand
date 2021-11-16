<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
        function testToday() {
            var time = new Date();
            var year = time.getFullYear();
            var month = time.getMonth();
            var day = time.getDay(); 
            
            
        }
        function harold(standIn) {
            if (standIn < 10) {standIn = '0' + standIn}                 
            return standIn;
            }
        document.write(year);
        
        
    </script>
</body>
</html>