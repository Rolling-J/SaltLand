package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mvc.database.DBConnection;

public class MemberDAO {

	private static MemberDAO instance;
	
	public static MemberDAO getInstance() {
		if(instance==null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	
	public static void setInstance(MemberDAO instance) {
		MemberDAO.instance = instance;
	}
	
	//loginTest(id, password)
	//역할 : 로그인 가능 여부 확인
	//입력값 : ID, PW / 출력값 : ID
	public Boolean loginTest(String id, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where id=? and password=?";
		Boolean x;

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				x = true;
				System.out.println("succeed : found member information");
			}else {
				x = false;
				System.out.println("failed : can't found member information");
			}
			return x;	
		}catch(Exception ex) {
			System.out.println("loginId() 에러 : "+ex);
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
		return null;
	}
	
	
	
	//getMemberById(Id)
	//역할 : ID 바탕으로 회원정보 가져오기(dto)
	//입력값 : ID / 출력값 : 멤버DTO
	public MemberDTO getMemberById(String id) {
		System.out.println("getMemberById() Started. id = "+id); //id 파라미터 수신 테스트
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO member = null;
		
		String sql = "select * from member where id = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			System.out.println(sql+" "+id);
			if(rs.next()) {
				member = new MemberDTO();
				
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setBirth(rs.getString("birth"));
				member.setGender(rs.getString("gender"));
				member.setMail(rs.getString("mail"));
				member.setPhone(rs.getString("phone"));
				member.setRegistDay(rs.getString("regist_day"));
				System.out.println("getMemberById working?");
				
			}else {
				System.out.println("getMemberById() has sql error. We do not have result from sql("+sql+") and "+id+")");
			}
			System.out.println("name From DTO = "+member.getName());
			return member;
		}catch(Exception ex) {
			System.out.println("getMemberById() 에러 : "+ex);
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			}catch(Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return null;
	}
	
	
	
	//addMember(멤버DTO)
	//역할 : DTO를 받아 DB에 신규 등록
	//입력값 : 멤버DTO / 출력값 : X
	public void addMember(MemberDTO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into member values(?,?,?,?,?,?,?,?)";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getBirth());
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getMail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getRegistDay());
			
			pstmt.executeUpdate();

		}catch(Exception ex) {
			System.out.println("addMember() 에러 : "+ex);
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
	
	//updateMember(멤버DTO)
	//역할 : DTO를 받아 DB에 수정 업데이트
	//입력값 : 멤버DTO / 출력값 : X
	public void updateMember(MemberDTO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update member set password=?, name=?, birth=?, gender=?, mail=?, phone=? where id=?";
		System.out.println("MemberDAO start. test : id = "+member.getId()+" , birth : "+member.getBirth()); //sessionId 파라미터 수신 테스트
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			conn.setAutoCommit(false);
			
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getBirth());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getMail());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getId());
			
			pstmt.executeUpdate();
			conn.commit();
		}catch(Exception ex) {
			System.out.println("updateMember() 에러 : "+ex);
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
	
	//deleteMember(id)
	//역할 : id를 받아 DB에서 해당 회원 정보 삭제
	//입력값 : id / 출력값 : X
	public void deleteMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from member where id=?";
		System.out.println("MemberDAO deleteMember("+id+") start.");
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();

		}catch(Exception ex) {
			System.out.println("deleteAttraction() 에러 : "+ex);
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
