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
	
	//board ���̺��� ���ڵ� �� (���ǿ� �´� �Խ����� �� ���� sql���� ����Ͽ� ���ڷ� ������)
	public int getListCount(String items, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		String sql;
		
		if(items == null&& text == null) {
			sql = "select count(*) from board";
		}else {
			sql = "select count(*) from board where " + items + " like '%'" + text + "'%' ";
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
	
	
	//board ���̺��� ���ڵ� ��������(�� �������� �� �Խñ� ��� ��������)
	public ArrayList<BoardDTO> getBoardList(int page, int limit, String items, String text){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int total_record = getListCount(items, text);
		int start = (page - 1) * limit; //�� �������� ù �Խñ��� ������ȣ�� �������� ���� ���� ����.
		int index = start + 1; //�� �������� ù �Խñ��� ������ȣ.
		
		String sql;
		
		if(items == null && text == null)
			sql = "select * from board order by num desc";
		else
			sql = "select * from board where "+ items + " like '%" + text + "%' order by num desc";
		// order by : select�� ��ȸ�� �������� ������ ���ǿ� ���� ���Ľ�Ű�� ��.
		// order by num : num ������� �������� ����, ��������(ASC) �Ǵ� ��������(DESC).
		// �������� �����̹Ƿ�, �ֽű��� ���� �տ� ��ġ�� ��.
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.absolute(index)) { //absolute(����) : �ش� �ε���(������)�� ���ڵ�� �ٷ� �̵�
				BoardDTO board = new BoardDTO();
				
				board.setNum(rs.getInt("num"));
				board.setId(rs.getString("id"));
				board.setName(rs.getString("name"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setRegist_day(rs.getString("regist_day"));
				board.setHit(rs.getInt("hit"));
				board.setIp(rs.getString("ip"));
				list.add(board); //DTO ������ arraylist�� �� DTO ����
			
				if(index < (start + limit) && index <= total_record)
					index++; 
				//�Խñ��� ������ȣ index�� �������� ��� ä��� ���� �׸��� ������ȣ index�� �Խñ� �� �� ������
				else
					break; //while�� ����. �������� �Խñ� ��� list�� �߰���.
			}
			return list; //�������� ���Ե� �Խñ۵��� ���� list ��ȯ
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
	
	
	//board ���̺����� ������ id�� ����ڸ� �������� (�Ķ���� id �������� member ���̺��� name ��������)
	public String getLoginNameById(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String name = null;
		String sql = "select * from member where id = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id); //select ������ ���� id = ? �� ?�� �Ķ���� id �Է�
			rs = pstmt.executeQuery(); //sql �Է� �� ����� rs�� ����
			
			if(rs.next())
				name = rs.getString("name");
				//rs�� ����� name(����ڸ�)�� �޼��� ���� ���� name�� ����
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
	
	
	//board ���̺��� ���ο� �� �����ϱ�
	public void insertBoard(BoardDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into board values(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board.getNum());
			pstmt.setString(2, board.getId());
			pstmt.setString(3, board.getName());
			pstmt.setString(4, board.getSubject());
			pstmt.setString(5, board.getContent());
			pstmt.setString(6, board.getRegist_day());
			pstmt.setInt(7, board.getHit());
			pstmt.setString(8, board.getIp());
			
			pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("insertBoard() ���� : "+ ex);
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
	
	
	//���õ� ���� ��ȸ�� �����ϱ�
	public void updateHit(int num) {
		
	}
	
	
	//���õ� �� �� ���� �������� (num �޾� �ش� ��ȣ�� �� DTO�� ��� / page�� �ֹ���)
	public BoardDTO getBoardByNum(int num, int page) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO board = null;
		
		updateHit(num);
		String sql = "select * from board where num = ?";
		
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
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setRegist_day(rs.getString("regist_day"));
				board.setHit(rs.getInt("hit"));
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
	
	
	//���õ� �� ���� �����ϱ�
	public void updateBoard(BoardDTO board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update board set name=?, subject=?, content=?, where num=?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			conn.setAutoCommit(false);
			
			pstmt.setString(1, board.getName());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getNum());
			
			pstmt.executeUpdate();
			conn.commit();
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
	
	
	//���õ� �� �����ϱ�
	public void deleteBoard(int num) {
		
	}
}