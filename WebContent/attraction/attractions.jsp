<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="mvc.model.AttractionDTO" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/SaltProject/resources/css/attractions.css" />
    <link rel="stylesheet" href="/SaltProject/resources/css/menu.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
    <script src="https://kit.fontawesome.com/a3555d8f42.js"></script>
    <script type="text/javascript">
	function search() {
		var tb = document.getElementById("p_search");
		var tbIndex = document.getElementById("p_search").options.selectedIndex;
		
		console.log("tb value : " + tb.options[tbIndex].value);
	}
	</script>
	
	<title>어트랙션</title>
</head>
<body>
<%
	String tag_search = (String)request.getAttribute("tag_search");
	String age_search = (String)request.getAttribute("age_search");
	String tall_search = (String)request.getAttribute("tall_search");
	String sessionId = (String)session.getAttribute("sessionId");
	@SuppressWarnings("unchecked") //SuppressWarnings("unchecked") : 확인되지않은 형변환 경고 무시. controller에서 정해진 형식의 Attribute만 전달받으므로 추가함
	List<AttractionDTO> atrList = (List<AttractionDTO>)request.getAttribute("atrList");
%>
<div class="all">
   <jsp:include page="/menu.jsp"/>
    <div class="a_box">
        <p class="category"> 홈>즐길거리>어트랙션</p>
        <div class="s_title">
            <strong class="a" >어트랙션</strong>
            <p class="pb">일상 속의 짜릿함을 느껴보세요.</p>
        </div>
    </div>
    <!-- 검색 -->
    <div class="search_box">       
        <div class="inp">
            <h3>조건 검색</h3>
            <form method="post" action="./AttractionList.do" class="search" accept-charset="utf-8">
                <select class="p_search" name="tag_search">
                	<option value="all">(분류-전체)</option>
                    <option value="survival" >서바이벌</option>
                    <option value="horror" >호러</option>
                    <option value="adventure" >어드벤쳐</option>
                    <option value="experience" >체험관</option>
                    <option value="kiddyzone" >키디존</option>
                    <option value="photozone" >포토존</option>
                </select>
                <select class="age_search" name="age_search">
                	<option value="all" selected>(연령제한-전체)</option>
                    <option value="0~8">0세~8세</option>
        			<option value="9~64">9세~64세</option>
        			<option value="none">제한 없음</option>
                </select>
                <select class="tall_search" name="tall_search">
                	<option value="all" selected>(키 제한-전체)</option>
                    <option value="130cm~190cm">130cm~190cm</option>
        			<option value="none">제한 없음</option>
                </select>
                <input type="submit" value="검색" class="s_btn">
                <input type="reset" value="다시" class="s_btn">
            </form>
        </div>
    </div>	
    <div class="card_box">
    <%
    	if(sessionId!=null){
    %>
    	<div class="btns">
	     	<a href="./AddAttractionForm.do?">어트렉션 등록</a>
	        <a href="./EditAttractionView.do?edit=update">어트렉션 수정</a>
	        <a href="./EditAttractionView.do?edit=delete">어트렉션 삭제</a>
 		</div>
	<%
    	}
		
    	for(int j = 0; j <atrList.size(); j++){
			AttractionDTO atr = (AttractionDTO) atrList.get(j);
    %> 
    
    	<div class="card_innerbox">
			<button class="card1" onclick="javascript:window.location='./AttractionDetailView.do?id=<%=atr.getId() %>'">
				<span class="tag">
					<%
						switch(atr.getTag()){
							case "survival" :
					%>
								서바이벌
					<%
								break;
							case "horror" :
					%>
								호러
					<%
								break;
							case "adventure" :
		  			%>
								어드벤쳐
					<%
		  						break;
							case "experience" :
					%>
								체험관
					<%
								break;
							case "kiddyzone" :
					%>
								키디존
					<%
								break;
							case "photozone" :
					%>
								포토존
					<%
		 						break;
		  					default :
								System.out.print("Warning : tag error");
						}
					%>
				</span>
				<img src="/SaltProject/resources/image/<%=atr.getFilename() %>" alt="play" style="width: 100%;">
	            <div class="con">
	                <h4 class="con1"><%=atr.getName() %></h4>
	            </div>
			</button>
		</div>
      <%
			}
    %>
    </div>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>