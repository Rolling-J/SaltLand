package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import mvc.database.DBConnection;

public class TicketDAO {
	
	private static TicketDAO instance;
	
	private TicketDAO() {
		
	}
	
	public static TicketDAO getInstance() {
		if(instance == null) {
			instance = new TicketDAO();
		}
		return instance;
	}
	
	//getTicketList(�α��ε� ���̵�)
	//��� : Ƽ�� ����Ʈ ��������-�α����� ���̵�� ����� Ƽ���� ��ü ����Ʈ�� list�� ����ϴ�.
	//�Է°�:sessionId (�α��ε� ���̵�) , ��°�:null (��°�����)
	//�������� : DTO Ticket�� ��� ������ Ƽ�� ������ list�� ������� �����մϴ�.
	public ArrayList<TicketDTO> getTicketList(String sessionId ){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_ticket = getTicketCount(sessionId);
		int index = 1;
		String sql = "select * from ticket where id='"+ sessionId +"' order by reserve_num desc";
		
		ArrayList<TicketDTO> list = new ArrayList<TicketDTO>();
		//try-Ƽ�������� �ϳ��� ������ ArrayList list�� ����ϴ�.
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("getTicketList("+sessionId+") sql : "+sql);
			while(rs.absolute(index)) {
				TicketDTO ticket = new TicketDTO();
				
				ticket.setReserve_num(rs.getInt("reserve_num"));
				ticket.setId(rs.getString("id"));
				ticket.setVisit_date(rs.getString("visit_date"));
				ticket.setAdult(rs.getInt("adult"));
				ticket.setTeenager(rs.getInt("teenager"));
				ticket.setChildren(rs.getInt("children"));
				ticket.setCharge(rs.getInt("charge"));
				ticket.setReserve_time(rs.getString("reserve_time"));
				list.add(ticket);
				
				if(index <= total_ticket)
					index++;
				else
					break;
			}
			return list;
		}catch(Exception ex) {
			System.out.println("getTicketList Error : " + ex);
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
	
	//getTicketCount(�α��ε� ���̵�)
	//Ƽ���� ���� �������� - �α����� ���̵�� ����� Ƽ���� ��ü ������ �����ɴϴ�.
	//�Է°�:sessionId (�α��ε� ���̵�) , ��°�: ����� Ƽ�� ����
	public int getTicketCount(String sessionId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		String sql = "select * from ticket where id='"+ sessionId +"'";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = rs.getInt(1);
		}catch(Exception ex){
			System.out.println("getTicketCount() error : " + ex);
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
	
	//getTicketInfo(Ƽ�ϰ�����ȣ)
	//��� : Ƽ�� ���� �ҷ����� - ������ Ƽ���� ������ �����ɴϴ�.
	//�Է°�:ticketNumber (Ƽ�ϰ�����ȣ) , ��°�:null (��°�����)
	//�������� : DB�κ��� Ƽ�ϰ�����ȣ�� �������� Ƽ�� ������ �޾ƿ� DTO Ticket�� �����մϴ�.
	public TicketDTO getTicketInfo(int ticketNumber) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TicketDTO ticket = null;
		String sql = "select * from ticket where reserve_num='"+ ticketNumber +"'";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			System.out.println("getTicketInfo("+ticketNumber+") sql : "+sql);
			while(rs.next()) {
				ticket = new TicketDTO();
				
				ticket.setReserve_num(rs.getInt("reserve_num"));
				ticket.setId(rs.getString("id"));
				ticket.setVisit_date(rs.getString("visit_date"));
				ticket.setAdult(rs.getInt("adult"));
				ticket.setTeenager(rs.getInt("teenager"));
				ticket.setChildren(rs.getInt("children"));
				ticket.setCharge(rs.getInt("charge"));
				ticket.setReserve_time(rs.getString("reserve_time"));
				System.out.println("getMemberById working?");
				System.out.println("Test - charge From DTO = "+ticket.getCharge());
			}
			return ticket;
		}catch(Exception ex) {
			System.out.println("getTicketInfo() Error : " + ex);
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
	
	//reservingTicket(Ƽ��DTO)
	//��� : Ƽ�� �����ϱ� - ����ڰ� �Է��� ������ �������� ���������� �����մϴ�.
	//�Է°�:ticketNumber (Ƽ�ϰ�����ȣ) , ��°�:null (��°�����)
	//�������� : DTO Ticket���κ��� Ƽ�� ������ �޾� DB�� �����մϴ�.
	public TicketDTO reserveTicket(TicketDTO ticket) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into ticket values(?,?,?,?,?,?,?,?)";
		System.out.println("getTicketInfo() sql : "+sql);
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ticket.getReserve_num());
			pstmt.setString(2, ticket.getId());
			pstmt.setString(3, ticket.getVisit_date());
			pstmt.setInt(4, ticket.getAdult());
			pstmt.setInt(5, ticket.getTeenager());
			pstmt.setInt(6, ticket.getChildren());
			pstmt.setInt(7, ticket.getCharge());
			pstmt.setString(8, ticket.getReserve_time());
			
			pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("reserveTicket() Error : " + ex);
		}finally {
			try {
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
	
	
	
	//cancleTicket(Ƽ�ϰ�����ȣ)
	//��� : Ƽ�ϰ�����ȣ�� �޾� DB���� �ش� ���� ���� ����
	//�Է°� : ticketNumber (Ƽ�ϰ�����ȣ) , ��°�:null (��°�����)
	public void cancleTicket(int ticketNumber) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from ticket where reserve_num=?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ticketNumber);
			pstmt.executeUpdate();

		}catch(Exception ex) {
			System.out.println("cancleTicket() ���� : "+ex);
		}finally {
			try {
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			}catch(Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}	
	
}
