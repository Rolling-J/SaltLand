<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="/SaltProject/resources/css/menu.css">
	<link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
	<link rel= "stylesheet" href="/SaltProject/resources/css/main.css">
	<script src="https://kit.fontawesome.com/a3555d8f42.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
     rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> 
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
     integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script>
		function clock() {
			var time = new Date(),hours = time.getHours(), minutes = time.getMinutes(), seconds = time.getSeconds();
			var ampm;
			if(hours>12){hours=hours-12; ampm="P. M"}else{ampm="A. M"}
			document.querySelectorAll('.ampm')[0].innerHTML = ampm;
			document.querySelectorAll('.clock')[0].innerHTML = harold(hours) + ":" + harold(minutes) + ":" + harold(seconds);
		}
		function harold(standIn) {
			if (standIn < 10) {standIn = '0' + standIn}                 
			return standIn;
		}
		
		setInterval(clock, 1000);
	</script>
	<title>메인 페이지</title>
</head>
<body>
	<jsp:include page="/menu.jsp"/>

	<!-- 슬라이드 이미지 -->
	<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
			<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
		</div>
		<div class="carousel-inner">
			<div class="carousel-item active">
			<img src="/SaltProject/resources/image/banner.jpg" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="/SaltProject/resources/image/banner2.jpg" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="/SaltProject/resources/image/banner4.jpg" class="d-block w-100" alt="...">
			</div>
		</div>
		<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span>
			<span class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span>
			<span class="visually-hidden">Next</span>
		</button>
    </div>
    
	<!-- 운영 시간 -->
	<div class="time">
		<p><i class="far fa-clock"></i></p>
		<p class="tp"><strong>파크 운영시간</strong></p> 
		<p class="t"><strong>10:00~21:00</strong></p>
	</div>

	<!-- 정보 -->
	<div class="titles"><strong>Information</strong></div>
	<div id="information">
		<div class="info_inner">
			<!-- 정보 - 테마별 어트랙션 버튼 -->
			<div class="place">   
				<div class="info_title">   
					<strong class="title_text">테마별</strong><hr>
				</div>   
				<div class="theme_box">
					<ul class="theme">
						<li><button id="tema_btn" onclick="javascript:window.location='./AttractionList.do?p_search=survival'">서바이벌</button></li>
						<li><button id="tema_btn2" onclick="javascript:window.location='./AttractionList.do?p_search=adventure'">어드벤쳐</button> </li>
						<li><button id="tema_btn" onclick="javascript:window.location='./AttractionList.do?p_search=kiddyzone'">키디존</button> </li>
					</ul>
					<ul class="theme">
						<li><button id="tema_btn2" onclick="javascript:window.location='./AttractionList.do?p_search=horror'">호러</button></li>
						<li><button id="tema_btn" onclick="javascript:window.location='./AttractionList.do?p_search=experience'">체험관</button> </li>
						<li><button id="tema_btn2" onclick="javascript:window.location='./AttractionList.do?p_search=photozone'">포토존</button> </li>
					</ul>
				</div>
			</div>
			<!-- 정보 - 게시글 -->
			<div class="place">   
				<div class="info_title">   
					<strong class="title_text">공지사항</strong><hr>
				</div>   
				<div>
					<p><a href="/BoardListAction.do?pageNum=1">공지사항 바로가기</a></p><hr>
				</div>
			</div>

			<!-- 정보 - 시간과 오시는 길 -->
			<div class="check">
				<div class="check_box">
					<h4 class="m_title"> 
							<strong class="check_text">현재 시간</strong>                
					</h4>
					<div class="text_content">
						<div class="ampm"><strong></strong></div>
						<div class="clock"><strong></strong></div>
					</div>
				</div>
				<button class="bus" onclick="javascript:window.location='./BusInfo.do'">
					<i class="fas fa-bus"></i>
					<strong class="bus_station">오시는 길</strong>
				</button>
			</div>
		</div>        
	</div>
	
	<!-- 어트랙션 미리보기 -->  
	
	<div class="titles"><strong>다양한 어트랙션</strong></div>
	<div class="play">
		<!-- 어트랙션 미리보기 - 카드박스 -->
		<div class="card_box">
			<div class="card">
				<img src="/SaltProject/resources/image/hurricane.jpg" width="100%">
				<div class="box">
					<b>허리케인</b>
                </div>
			</div>
			<div class="card">
                <img src="/SaltProject/resources/image/merkings_marry_go_round.jpg" width="100%">
                <div class="box">
					<b>머킹의 회전목마</b>
                </div>
			</div>
			<div class="card">
				<img src="/SaltProject/resources/image/swing_pangpang.jpg" width="100%">
				<div class="box">
					<b>스윙팡팡</b>
				</div>
			</div>
		</div>
		<button id="play_btn2" onclick="javascript:window.location='./AttractionList.do'">더 많은 어트랙션보기</button>
	</div>
	<jsp:include page="/footer.jsp"/>
</body>
</html>