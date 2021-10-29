<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dto.attraction"%>
<%@ page import="dao.attractionbox"%> 
<jsp:useBean id="attractionDAO" class="dao.attractionbox" scope="session" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="play.css">
    <title>Document</title>
    <script src="https://kit.fontawesome.com/a3555d8f42.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./resources/css/play.css" />
</head>
<body>
    <jsp:include page="bar.jsp"/>
    <div class="a_box">
        <p class="category"> 홈>즐길거리>어트랙션</p>
        <div class="s_title">
            <strong class="a" > 어트랙션</strong>
            <p class="pb">일상 속의 짜릿함을 느껴보세요.</p>
        </div>
    </div>
    <div class="search_box">       
        <div class="inp">
            <h3>조건 검색</h3>
            <form method="post" action="play.html" class="search">
                <select class="p_search">
                    <option value="1" selected>서바이벌</option>
                    <option value="2" selected>호러</option>
                    <option value="3" selected>어드벤쳐</option>
                    <option value="4" selected>체험관</option>
                    <option value="5" selected>키디존</option>
                    <option value="6" selected>포토존</option>
                </select>
                <select class="age_search">
                    <option value="1" selected>0~4세</option>
                    <option value="2" selected>5~64세</option>
                    <option value="3" selected>65세 이상</option>
                </select>
                <select class="tall_search">
                    <option value="1" selected>100cm~150cm</option>
                    <option value="2" selected>150cm~185cm</option>
                </select>
                <p></p>
                <input type="submit" value="검색" class="s_btn">
                <input type="reset" value="다시" class="s_btn">
            </form>
        </div>
    </div>
    <%
		attractionbox dao = attractionbox.getInstance();
		ArrayList<attraction> listOfattractions = dao.getAllattractions();
	%>
	
    <div class="card_box">
    <%
			for (int i = 0; i < listOfattractions.size(); i++) {
				attraction attraction = listOfattractions.get(i);
		%>
        <a href="./at.jsp?name=<%=attraction.getName()%>" class="card1">
            <div class="in_card">
                <span class="tag"><%=attraction.getTag()%></span>
                <img src="https://adventure.lotteworld.com/image/2018/6/20180620074532108_275.jpg" alt="play" style="width: 100%;">
            </div>    
            <div class="con">
                <h4 class="con1"><%=attraction.getName()%></h4>
            </div>           
        </a>
      <%
			}
    %>
    </div>

<footer>
    <div class="footer_box">

      <div class="footer_content">

        <div class="company">
          <h4>솔트랜드</h4>
          <div class="list">
            <p>주소: 어디어디</p>
            <p>tel-xxx-xxx-xxx</p>
            <p>고객센터 문의</p>
            <p>jopixim237@jesdoit.com</p>
          </div>
        </div>
        
        <div class="company">
          <h4>sns</h4>
          <div class="sns">
            <a href="#"><i class="fab fa-facebook-f"></i></a>
            <a href="#"><i class="fab fa-twitter"></i></a>
            <a href="#"><i class="fab fa-instagram"></i></a>
            <a href="#"><i class="fab fa-linkedin-in"></i></a>
          </div>
        </div>

      </div>
    </div>
  </div>
  </div>
</footer>
</body>
</html>