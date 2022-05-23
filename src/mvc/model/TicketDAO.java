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
	
	//getTicketList(로그인된 아이디)
	//기능 : 티켓 리스트 가져오기-로그인한 아이디로 예약된 티켓의 전체 리스트를 list에 담습니다.
	//입력값:sessionId (로그인된 아이디) , 출력값:null (출력값없음)
	//보조설명 : DTO Ticket에 잠시 저장한 티켓 정보를 list에 순서대로 저장합니다.
	public ArrayList<TicketDTO> getTicketList(String sessionId ){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_ticket = getTicketCount(sessionId);
		int index = 1;
		String sql = "select * from ticket where id='"+ sessionId +"' order by reserve_num desc";
		
		ArrayList<TicketDTO> list = new ArrayList<TicketDTO>();
		//try-티켓정보를 하나씩 윗줄의 ArrayList list에 담습니다.
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
	
	//getTicketCount(로그인된 아이디)
	//티켓의 수량 가져오기 - 로그인한 아이디로 예약된 티켓의 전체 수량을 가져옵니다.
	//입력값:sessionId (로그인된 아이디) , 출력값: 예약된 티켓 수량
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
	
	//getTicketInfo(티켓고유번호)
	//기능 : 티켓 정보 불러오기 - 지정된 티켓의 정보를 가져옵니다.
	//입력값:ticketNumber (티켓고유번호) , 출력값:null (출력값없음)
	//보조설명 : DB로부터 티켓고유번호를 바탕으로 티켓 정보를 받아와 DTO Ticket에 저장합니다.
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
	
	//reservingTicket(티켓DTO)
	//기능 : 티켓 예약하기 - 사용자가 입력한 정보를 바탕으로 예약정보를 저장합니다.
	//입력값:ticketNumber (티켓고유번호) , 출력값:null (출력값없음)
	//보조설명 : DTO Ticket으로부터 티켓 정보를 받아 DB에 저장합니다.
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
	
	
	
	//cancleTicket(티켓고유번호)
	//기능 : 티켓고유번호를 받아 DB에서 해당 예약 정보 삭제
	//입력값 : ticketNumber (티켓고유번호) , 출력값:null (출력값없음)
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
			System.out.println("cancleTicket() 에러 : "+ex);
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
