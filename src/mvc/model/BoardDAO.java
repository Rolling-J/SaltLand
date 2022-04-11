
package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import mvc.database.DBConnection;


public class BoardDAO {
	
	private static BoardDAO instance;
	
	private BoardDAO() {
	}
	
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	//getListCount(카테고리,검색어)
	//역할: 검색조건에 맞는 게시판의 글 수를 가져옵니다.
	//입력값: 카테고리, 검색어 / 출력값: 게시글 수
	public int getListCount(String category, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		String sql;
		
		System.out.println("category="+category);
		System.out.println("text="+text);
		
		if((category == null||category.equals("all"))&& (text == null||text.equals(""))) {
			sql = "select count(*) from noticeboard";
		}else {
			sql = "select count(*) from noticeboard where category='" + category + "' and title like '%" + text + "%' ";
		}
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = rs.getInt(1); //count(*) 결과값이 1열로 나오므로 1번째 열 숫자를 받아옴
		}catch(Exception ex){
			System.out.println("getListCount() 에러 : " + ex);
		}finally {
			try {
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			}catch(Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return x;
	}
	
	//getBoardList(페이지,페이지당 최대 게시글 수, 카테고리, 검색어)
	//역할: 각 페이지에 들어갈 게시글 목록 가져오기 (검색기능 사용시 조건에 맞는 목록 가져오기)
	//입력값: 페이지, 페이지당 최대 게시글 수, 카테고리, 검색어 /  출력값:list (보드 리스트)
	public ArrayList<BoardDTO> getBoardList(int page, int limit, String category, String text){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int total_record = getListCount(category, text);
		int start = (page - 1) * limit; //아래 index를 구하기 위한 변수. 최소값은 0임.
		int index = start + 1; //각 페이지의 첫 게시글의 순서번호.
		
		String sql;
		
		if((category == null||category.equals("all"))&& (text == null||text.equals("")))
			// order by : select로 조회한 저장요소의 순서를 조건에 맞춰 정렬시키는 것.
			// order by num : num 순서대로 내림차순 정렬, 오름차순(ASC) 또는 내림차순(DESC).
			// 내림차순 정렬이므로, 최신글이 제일 앞에 위치할 것.
			sql = "select * from noticeboard order by num desc";
		else
			sql = "select * from noticeboard where category='"+ category + "' and title like '%" + text + "%' order by num desc";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//System.out.println(sql);
			while(rs.absolute(index)) { //absolute(숫자) : boolean값
				BoardDTO board = new BoardDTO();
				
				board.setNum(rs.getInt("num"));
				board.setId(rs.getString("id"));
				board.setName(rs.getString("name"));
				board.setCategory(rs.getString("category"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setFileName(rs.getString("fileName"));
				board.setRegist_day(rs.getString("regist_day"));
				board.setIp(rs.getString("ip"));
				list.add(board); //list에 위 DTO 저장
			
				if(index < (start + limit) || index < total_record)
					index++; 
				//게시글의 순서번호 index가 해당 페이지를 모두 채우기 전까지 && 순서번호 index가 게시글 총 수 이하까지
				else
					break;
			}
			return list;
		}catch(Exception ex) {
			System.out.println("getBoardList() 에러 : " + ex);
		}finally {
			try {
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			}catch(Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		
		return null;
	}
	
	
	//getLoginNameById(아이디)
	//역할 : member 테이블에서 사용자 이름 가져오기
	//입력값: 아이디 / 출력값: 이름
	public String getLoginNameById(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String name = null;
		String sql = "select * from member where id = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id); 
			rs = pstmt.executeQuery(); 
			
			if(rs.next())
				name = rs.getString("name");

			return name;
		}catch(Exception ex) {
			System.out.println("getLoginNameById() 에러 : " + ex);
		}finally {
			try {				
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		
		return null;
	}
	
	//insertBoard(보드 DTO)
	//역할: board 테이블에 새로운 글 삽입하기
	//입력값:보드DTO / 출력값: 없음 (void 메서드)
	public void insertBoard(BoardDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into noticeboard values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board.getNum());
			pstmt.setString(2, board.getId());
			pstmt.setString(3, board.getName());
			pstmt.setString(4, board.getCategory());
			pstmt.setString(5, board.getTitle());
			pstmt.setString(6, board.getContent());
			pstmt.setString(7, board.getFileName());
			pstmt.setString(8, board.getRegist_day());
			pstmt.setString(9, board.getIp());
			
			pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("insertBoard() 에러 : "+ ex); //메서드 에러 메세지
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		
	}
	
	//getNumRecentBoard()
	//역할: 가장 최신글의 게시글번호를 가져옵니다.
	//입력값:없음 / 출력값:최신 게시글 번호
	public int getNumRecentBoard() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select num from noticeboard order by num desc";
		int newNum = 0;
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				newNum = rs.getInt(1); //count(*) 결과값이 1열로 나오므로 1번째 열 숫자를 받아옴
		}catch(Exception ex){
			System.out.println("getListCount() 에러 : " + ex);
		}finally {
			try {
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			}catch(Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		
		newNum++;
		return newNum;
	}
	
	//getBoardByNum(글 번호)
	//역할: 선택된 글 상세 내용 가져오기 (dto에 해당 글 정보 저장)
	//입력값: 글 번호 / 출력값: 해당 글의 보드DTO
	public BoardDTO getBoardByNum(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO board = null;
		
		String sql = "select * from noticeboard where num = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardDTO();
				
				board.setNum(rs.getInt("num"));
				board.setId(rs.getString("id"));
				board.setName(rs.getString("name"));
				board.setCategory(rs.getString("category"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setFileName(rs.getString("fileName"));
				board.setRegist_day(rs.getString("regist_day"));
				board.setIp(rs.getString("ip"));
			}
			
			return board;
		}catch(Exception ex) {
			System.out.println("getBoardByNum() 에러 : " + ex);
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		
		return null;
	}
	
	//updateBoard(보드DTO)
	//역할: 선택된 글 내용 수정하기
	//입력값: 보드DTO / 출력값: 없음 (void 메서드)
	public void updateBoard(BoardDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "";
		
		try {
			
			if(board.getFileName()!=null) {
				sql = "update noticeboard set name=?, category=?, title=?, content=?, fileName=? where num=?";
				conn = DBConnection.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				conn.setAutoCommit(false);
				
				pstmt.setString(1, board.getName());
				pstmt.setString(2, board.getCategory());
				pstmt.setString(3, board.getTitle());
				pstmt.setString(4, board.getContent());
				pstmt.setString(5, board.getFileName());
				pstmt.setInt(6, board.getNum());
				
				pstmt.executeUpdate();
				conn.commit();
			}else {
				sql = "update noticeboard set name=?, category=?, title=?, content=? where num=?";
				conn = DBConnection.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				conn.setAutoCommit(false);
				
				pstmt.setString(1, board.getName());
				pstmt.setString(2, board.getCategory());
				pstmt.setString(3, board.getTitle());
				pstmt.setString(4, board.getContent());
				pstmt.setInt(5, board.getNum());
				
				pstmt.executeUpdate();
				conn.commit();
			}
				
		}catch(Exception ex){
			System.out.println("updateBoard() 에러 : "+ ex);
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}
	
	//deleteBoard(글 번호)
	//역할: 선택된 글 삭제하기
	//입력값: 글 번호 / 출력값: 없음 (void 메서드)
	public void deleteBoard(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from noticeboard where num=?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		}catch(Exception ex){
			System.out.println("deleteBoard() 에러 : " + ex);
		}finally {
			try {										
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}
}
