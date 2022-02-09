<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="https://kit.fontawesome.com/a3555d8f42.js"></script>
	<link rel="stylesheet" href="/SaltProject/resources/css/bus.css" />
	<link rel="stylesheet" href="/SaltProject/resources/css/menu.css">
	<link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
	<title>오시는 길</title> 
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="a_box">
		<p class="category"> 홈>오시는 길</p>
		<div class="center_box">
			<div class="s_title">
				<strong class="a">오시는 길</strong>
				<p class="pb">위치 안내<br>버스 운행 정보</p>
			</div>
		</div>
	</div>
	<div class="bus">
		<b>셔틀버스 안내</b>
		<div class="bus_box">
			<p><strong>➊ Salt 셔틀버스</strong></p>
			<div class="bus_img">
				<i class="fas fa-bus"> 대저1동 출발 덕천정류장-만덕정류장-도착 </i><br>
			</div>
		</div>
		<div class="bus_box">
			<p><strong>➊ Sugar 셔틀버스</strong></p>
			<div class="bus_img">
				<i class="fas fa-bus"> 기장 출발 반송정류장-금사정류장-도착 </i><br>
			</div>
		</div>
		<div class="map">
			<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3259.381328021821!2d129.06202031538652!3d35.22187636263403!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xf1a31c13b8daf52b!2zMzXCsDEzJzE4LjciTiAxMjnCsDAzJzUxLjIiRQ!5e0!3m2!1sko!2skr!4v1638991085745!5m2!1sko!2skr"
			width="600" height="450" style="border:0;"></iframe>
		</div>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>