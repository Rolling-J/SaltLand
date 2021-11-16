<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./resources/css/bus.css" />
    <script src="https://kit.fontawesome.com/a3555d8f42.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
    <script type="text/javascript">
      $(document).ready(function(){
         $("#bar").load("bar.html")    
      });
    </script>
    <script type="text/javascript">
        $(document).ready(function(){
           $("#footer").load("footer.html")    
        });
      </script> 
</head>
<body>
    <jsp:include page="menu.jsp"/>

    <div class="a_box">
        <p class="category"> 홈>오시는 길</p>
        <div class="s_title">
            <strong class="a"> 오시는 길</strong>
                <p class="pb">교통 정보<br>
               버스 운행 정보</p>
        </div>
    </div>

    <div class="bus">
        <b>오시는 길</b>
        <div class="bus_box">
            <p><strong>➊ 버스 이용</strong></p>
            <p>△△ 출발</p>
            <div class="bus_img">
                <i class="fas fa-bus"> 1889번 ○○정류장-○○정류장 </i><br>
            </div>
          </div>

          <div class="bus_box">
            <p><strong>➊ 버스 이용</strong></p>
            <p>△△ 출발</p>
            <div class="bus_img">
                <i class="fas fa-bus"> 1889번 ○○정류장-○○정류장 </i><br>
            </div>
          </div>

          <div class="map"><iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d3260.570437603415!2d129.08584292714937!3d35.192257583754575!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xd4dab00b47bfc7d!2z66Gv642w7JuU65Oc67aA7IKw7IKs66y07IaM!5e0!3m2!1sko!2skr!4v1635148437465!5m2!1sko!2skr"
            width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe></div>
        </div>
   
    <jsp:include page="footer.jsp"/>
</body>
</html>