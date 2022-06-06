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
	public ArrayList<TicketDTO> getTicketList(int page, int limit, String sessionId){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_ticket = getTicketCount(sessionId);
		int start = (page - 1) * limit; //아래 index를 구하기 위한 변수. 앞선 페이지에 담긴 모든 요소의 총합. 최소값은 0임.
		int index = start + 1; //각 페이지의 첫 티켓의 순서번호.
		String sql = "select * from ticket where id='"+ sessionId +"' order by reserve_num desc";
		
		//test codes
		System.out.println("getTicketList("+page+", "+limit+", "+sessionId+") start.");
		System.out.println("getTicketList message : 해당 페이지 앞의 요소 총합(start) - "+start+", 각 페이지 첫 티켓 순서번호(index) - "+index);
		System.out.println("getTicketList message : 해당 페이지의 한계 요소수 (start+limit) - ("+start+"+"+limit+"), 모든티켓수 (total_ticket) - "+total_ticket);
		ArrayList<TicketDTO> list = new ArrayList<TicketDTO>();
		//try-티켓정보를 하나씩 윗줄의 ArrayList list에 담습니다.
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.absolute(index)) {
				TicketDTO ticket = new TicketDTO();
				
				//test codes
				System.out.println("getTicketList message : "+index+"번의 자료를 불러옵니다");
				
				ticket.setReserve_num(rs.getInt("reserve_num"));
				ticket.setId(rs.getString("id"));
				ticket.setVisit_date(rs.getString("visit_date"));
				ticket.setAdult(rs.getInt("adult"));
				ticket.setTeenager(rs.getInt("teenager"));
				ticket.setChildren(rs.getInt("children"));
				ticket.setCharge(rs.getString("charge"));
				ticket.setReserve_time(rs.getString("reserve_time"));
				list.add(ticket);
				
				//test codes
				if(index<(start+limit))
					System.out.println("getTicketList message : 해당 페이지가 가득차지 않았습니다. 반복합니다.");
				else if(index==(start+limit))
					System.out.println("getTicketList message : 해당 페이지가 가득찼습니다. 반복을 멈춥니다.");
				else if(index<total_ticket)
					System.out.println("getTicketList message : 티켓의 수가 아직 total_ticket("+total_ticket+")보다 작습니다. 반복합니다.");
				else if(index==total_ticket)
					System.out.println("getTicketList message : 티켓의 수가 total_ticket("+total_ticket+")에 이르렀습니다. 반복을 멈춥니다.");
				
				//순서번호 index가 해당 페이지를 모두 채우기 전까지 && 순서번호 index가 티켓 총 수 이하까지 실행
				if(index<(start+limit)&&index<total_ticket)
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
		String sql = "select count(*) from ticket where id='"+ sessionId +"'";
		
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
				ticket.setCharge(rs.getString("charge"));
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
			pstmt.setString(7, ticket.getCharge());
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
	
	//getReserveNums(로그인된 아이디)
	//기능 : 로그인된 아이디로 예약되어있는 티켓 번호들을 array로 가져옵니다
	//입력값:sessionId (로그인된 아이디) , 출력값:array (예약번호 배열)
	//보조설명 : DB에서 검색된 티켓번호들을 array에 순서대로 저장합니다.
	public int[] getReserveNums(String sessionId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_ticket = getTicketCount(sessionId);
		int index = 1;
		String sql = "select * from ticket where id='"+ sessionId +"' order by reserve_num desc";
		
		int[] arrayNum = new int[total_ticket];
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.absolute(index)) {
				arrayNum[index-1] = rs.getInt("reserve_num");
				if(index <= total_ticket)
					index++;
				else
					break;
			}
			return arrayNum;
		}catch(Exception ex) {
			System.out.println("getReserveNums Error : " + ex);
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
	
	//cancleTicketByID(로그인ID)
	//기능 : 로그인ID를 받아 DB에서 해당 계정으로 예약된 티켓 정보 전체 삭제
	//입력값 : ticketNumber (티켓고유번호) , 출력값:null (출력값없음)
	public void cancleTicketByID(String sessionId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int[] arrayRNum = getReserveNums(sessionId);
		int total_ticket = getTicketCount(sessionId);
		int reserveNum=0;
		String sql = "delete from ticket where reserve_num=?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			for(int i=0; i<total_ticket; i++) {
				reserveNum = arrayRNum[i];
				pstmt.setInt(1, reserveNum);
				pstmt.executeUpdate();
			}
		}catch(Exception ex) {
			System.out.println("cancleTicketByID() 에러 : "+ex);
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
