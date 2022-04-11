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
	//���� : ID �������� ȸ������ ��������(dto)
	//�Է°� : ID / ��°� : ���DTO
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
			System.out.println("getMemberById() ���� : "+ex);
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
	
	//addMember(���DTO)
	//���� : DTO�� �޾� DB�� �ű� ���
	//�Է°� : ���DTO / ��°� : X
	public void addMember(MemberDTO member) {
		
	}
	
	//updateMember(���DTO)
	//���� : DTO�� �޾� DB�� ���� ������Ʈ
	//�Է°� : ���DTO / ��°� : X
	public void updateMember(MemberDTO member) {
		
	}
	
	//deleteMember(id)
	//���� : id�� �޾� DB���� �ش� ȸ�� ���� ����
	//�Է°� : id / ��°� : X
	public void deleteMember(String id) {
		
		String sql = "delete from member where id=?";
		
	}
}
