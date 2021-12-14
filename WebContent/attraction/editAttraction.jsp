<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/SaltProject/resources/css/editAttraction.css" />
    <link rel="stylesheet" href="/SaltProject/resources/css/menu.css">
    <link rel="stylesheet" href="/SaltProject/resources/css/footer.css">
    <title>Document</title>
    <script src="https://kit.fontawesome.com/a3555d8f42.js" crossorigin="anonymous"></script>

<script type="text/javascript">
	function deleteConfirm(name) {
		if (confirm("해당 상품을 삭제합니다!!") == true)
			location.href = "/SaltProject/attraction/deleteattraction.jsp?name=" + name;
		else
			return;
	}
</script>
</head>
<%
	String edit = request.getParameter("edit");
%>
</head>
<body>
<div class="all">
   <jsp:include page="/menu.jsp"/>
    <div class="a_box">
        <p class="category"> 홈>즐길거리>어트랙션</p>
        <div class="s_title">
            <strong class="a" > 어트랙션</strong>
            <p class="pb">일상 속의 짜릿함을 느껴보세요.</p>
        </div>
    </div>
    
    <!-- 검색 -->
    <div class="search_box">       
        <div class="inp">
            <h3>조건 검색</h3>
            <form method="post" action="/SaltProject/attraction/editAttraction.jsp" class="search">
                <select class="p_search" name="p_search">
                	<option value="all" selected>(분류-전체)</option>
                    <option value="survival">서바이벌</option>
                    <option value="horror">호러</option>
                    <option value="adventure">어드벤쳐</option>
                    <option value="experience">체험관</option>
                    <option value="kiddyzone">키디존</option>
                    <option value="photozone">포토존</option>
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
                <p></p>
                <input type="submit" value="검색" class="s_btn">
                <input type="reset" value="다시" class="s_btn">
            </form>
        </div>
    </div>
    		
    		
    <div class="card_box">
    <%@ include file="/resources/database/dbconn.jsp" %>
    <%
    	PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		request.setCharacterEncoding("utf-8");
		String p_search = request.getParameter("p_search");
		String age_search = request.getParameter("age_search");
		String tall_search = request.getParameter("tall_search");
		
		String sql = "select * from attraction";
		if ((p_search==null||p_search.equals("all")) && (age_search==null||age_search.equals("all")) && (tall_search==null||tall_search.equals("all"))){
			sql = "select  * from attraction";
		}else{
			if(p_search!=null&&!p_search.equals("all")){
			sql = sql + " where tag= '"+ p_search +"'";
				if(age_search!=null&&!age_search.equals("all")){
					sql = sql + " and age='" + age_search + "'";
					if(tall_search!=null&&!tall_search.equals("all")){
						sql = sql + " and tall='"+tall_search+"'";
					}	
				}else if(tall_search!=null&&!tall_search.equals("all")){
					sql = sql + "and tall='"+tall_search+"'";
				}
			}else if(age_search!=null&&!age_search.equals("all")){
				sql = sql + " where age='" + age_search + "'";
				if(tall_search!=null&&!tall_search.equals("all")){
					sql = sql + "and tall='"+tall_search+"'";
				}	
			}else if(tall_search!=null&&!tall_search.equals("all")){
				sql = sql + " where tall='"+tall_search+"'";
			}
		}
		
		System.out.println(sql); //sql console에서 확인
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
    %> 
        <div class="card1">
            <div class="in_card">
                <span class="tag">
					<%
                		switch(rs.getString("tag")){
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
            </div>    
                 <img src="/SaltProject/resources/image/<%=rs.getString("filename")%>" alt="play" style="width: 100%;">
            <div class="con">
                <h4 class="con1"><%=rs.getString("name")%></h4>
                <div class="btn_edit">
    		<%
             	if(edit.equals("update")){
 			%>
            	<a href="/SaltProject/attraction/updateAttraction.jsp?id=<%=rs.getString("id") %>" class="eidt_btn" role= "button">수정</a>
            <%
             	} else if(edit.equals("delete")){
            %>
             	<a href="#" onclick="deleteConfirm('<%=rs.getString("name")%>')" class="eidt_btn" role= "button">삭제</a>
            <%
             	}
            %>

				</div>
            </div>    
        	
        </div>     
		<%
			}
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	%>
    </div>
</div>
<jsp:include page="/footer.jsp"/>

</body>
</html>