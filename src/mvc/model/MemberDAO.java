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
	
	//getMemberById(Id)
	//역할 : ID 바탕으로 회원정보 가져오기(dto)
	//입력값 : ID / 출력값 : 멤버DTO
	public MemberDTO getMemberById(String id) {
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
			}
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
		
	}
	
	//updateMember(멤버DTO)
	//역할 : DTO를 받아 DB에 수정 업데이트
	//입력값 : 멤버DTO / 출력값 : X
	public void updateMember(MemberDTO member) {
		
	}
	
	//deleteMember(id)
	//역할 : id를 받아 DB에서 해당 회원 정보 삭제
	//입력값 : id / 출력값 : X
	public void deleteMember(String id) {
		
		String sql = "delete from member where id=?";
		
	}
}
