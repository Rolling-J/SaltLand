
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
	
	//getListCount(ī�װ�,�˻���)
	//����: �˻����ǿ� �´� �Խ����� �� ���� �����ɴϴ�.
	//�Է°�: ī�װ�, �˻��� / ��°�: �Խñ� ��
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
				x = rs.getInt(1); //count(*) ������� 1���� �����Ƿ� 1��° �� ���ڸ� �޾ƿ�
		}catch(Exception ex){
			System.out.println("getListCount() ���� : " + ex);
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
	
	//getBoardList(������,�������� �ִ� �Խñ� ��, ī�װ�, �˻���)
	//����: �� �������� �� �Խñ� ��� �������� (�˻���� ���� ���ǿ� �´� ��� ��������)
	//�Է°�: ������, �������� �ִ� �Խñ� ��, ī�װ�, �˻��� /  ��°�:list (���� ����Ʈ)
	public ArrayList<BoardDTO> getBoardList(int page, int limit, String category, String text){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int total_record = getListCount(category, text);
		int start = (page - 1) * limit; //�Ʒ� index�� ���ϱ� ���� ����. �ּҰ��� 0��.
		int index = start + 1; //�� �������� ù �Խñ��� ������ȣ.
		
		String sql;
		
		if((category == null||category.equals("all"))&& (text == null||text.equals("")))
			// order by : select�� ��ȸ�� �������� ������ ���ǿ� ���� ���Ľ�Ű�� ��.
			// order by num : num ������� �������� ����, ��������(ASC) �Ǵ� ��������(DESC).
			// �������� �����̹Ƿ�, �ֽű��� ���� �տ� ��ġ�� ��.
			sql = "select * from noticeboard order by num desc";
		else
			sql = "select * from noticeboard where category='"+ category + "' and title like '%" + text + "%' order by num desc";
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//System.out.println(sql);
			while(rs.absolute(index)) { //absolute(����) : boolean��
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
				list.add(board); //list�� �� DTO ����
			
				if(index < (start + limit) || index < total_record)
					index++; 
				//�Խñ��� ������ȣ index�� �ش� �������� ��� ä��� ������ && ������ȣ index�� �Խñ� �� �� ���ϱ���
				else
					break;
			}
			return list;
		}catch(Exception ex) {
			System.out.println("getBoardList() ���� : " + ex);
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
	
	
	//getLoginNameById(���̵�)
	//���� : member ���̺��� ����� �̸� ��������
	//�Է°�: ���̵� / ��°�: �̸�
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
			System.out.println("getLoginNameById() ���� : " + ex);
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
	
	//insertBoard(���� DTO)
	//����: board ���̺� ���ο� �� �����ϱ�
	//�Է°�:����DTO / ��°�: ���� (void �޼���)
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
			System.out.println("insertBoard() ���� : "+ ex); //�޼��� ���� �޼���
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
	//����: ���� �ֽű��� �Խñ۹�ȣ�� �����ɴϴ�.
	//�Է°�:���� / ��°�:�ֽ� �Խñ� ��ȣ
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
				newNum = rs.getInt(1); //count(*) ������� 1���� �����Ƿ� 1��° �� ���ڸ� �޾ƿ�
		}catch(Exception ex){
			System.out.println("getListCount() ���� : " + ex);
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
	
	//getBoardByNum(�� ��ȣ)
	//����: ���õ� �� �� ���� �������� (dto�� �ش� �� ���� ����)
	//�Է°�: �� ��ȣ / ��°�: �ش� ���� ����DTO
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
			System.out.println("getBoardByNum() ���� : " + ex);
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
	
	//updateBoard(����DTO)
	//����: ���õ� �� ���� �����ϱ�
	//�Է°�: ����DTO / ��°�: ���� (void �޼���)
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
			System.out.println("updateBoard() ���� : "+ ex);
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
	
	//deleteBoard(�� ��ȣ)
	//����: ���õ� �� �����ϱ�
	//�Է°�: �� ��ȣ / ��°�: ���� (void �޼���)
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
			System.out.println("deleteBoard() ���� : " + ex);
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
