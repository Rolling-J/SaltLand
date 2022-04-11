package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Ticket;
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
	public ArrayList<Ticket> getTicketList(String sessionId ){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_ticket = getTicketCount(sessionId);
		int index = 1;
		String sql = "select * from ticket where id='"+ sessionId +"' order by reserve_num desc";
		
		ArrayList<Ticket> list = new ArrayList<Ticket>();
		//try-티켓정보를 하나씩 윗줄의 ArrayList list에 담습니다.
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(sql);
			while(rs.absolute(index)) {
				Ticket ticket = new Ticket();
				
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
	
	
	
	
	
}
