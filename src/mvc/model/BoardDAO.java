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
	
	//board 테이블의 레코드 수 (조건에 맞는 게시판의 글 수를 sql에서 계산하여 숫자로 가져옴)
	public int getListCount(String category, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		String sql;
		
		if(category == null&& text == null) {
			sql = "select count(*) from noticeboard";
		}else {
			sql = "select count(*) from noticeboard where " + category + " like '%'" + text + "'%' ";
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
	
	
	//board 테이블의 레코드 가져오기(각 페이지에 들어갈 게시글 목록 가져오기)
	public ArrayList<BoardDTO> getBoardList(int page, int limit, String items, String text){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int total_record = getListCount(items, text);
		int start = (page - 1) * limit; //각 페이지의 첫 게시글의 순서번호를 가져오기 위한 숫자 변수.
		int index = start + 1; //각 페이지의 첫 게시글의 순서번호.
		
		String sql;
		
		if(items == null && text == null)
			sql = "select * from noticeboard order by num desc";
		else
			sql = "select * from noticeboard where "+ items + " like '%" + text + "%' order by num desc";
		// order by : select로 조회한 저장요소의 순서를 조건에 맞춰 정렬시키는 것.
		// order by num : num 순서대로 내림차순 정렬, 오름차순(ASC) 또는 내림차순(DESC).
		// 내림차순 정렬이므로, 최신글이 제일 앞에 위치할 것.
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.absolute(index)) { //absolute(숫자) : 해당 인덱스(저장요소)의 레코드로 바로 이동
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
				list.add(board); //DTO 형태의 arraylist에 위 DTO 저장
			
				if(index < (start + limit) && index <= total_record)
					index++; 
				//게시글의 순서번호 index가 페이지를 모두 채우기 전임 그리고 순서번호 index가 게시글 총 수 이하임
				else
					break; //while문 종료. 페이지의 게시글 모두 list에 추가됨.
			}
			return list; //페이지에 포함된 게시글들을 담은 list 반환
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
	
	
	//board 테이블에서 인증된 id의 사용자명 가져오기 (파라미터 id 바탕으로 member 테이블의 name 가져오기)
	public String getLoginNameById(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String name = null;
		String sql = "select * from member where id = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id); //select 구문의 조건 id = ? 의 ?에 파라미터 id 입력
			rs = pstmt.executeQuery(); //sql 입력 후 결과값 rs에 저장
			
			if(rs.next())
				name = rs.getString("name");
				//rs에 저장된 name(사용자명)을 메서드 내의 변수 name에 저장
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
	
	
	//board 테이블에 새로운 글 삽입하기
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
			System.out.println("insertBoard() 에러 : "+ ex);
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
	
	
	//선택된 글 상세 내용 가져오기 (num 받아 해당 번호의 글 DTO에 담기 / page는 왜받지)
	public BoardDTO getBoardByNum(int num, int page) {
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
	
	
	//선택된 글 내용 수정하기
	public void updateBoard(BoardDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update noticeboard set name=?, category=?, title=?, content=?, fileName=?, where num=?";
		
		try {
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
	
	
	//선택된 글 삭제하기
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
