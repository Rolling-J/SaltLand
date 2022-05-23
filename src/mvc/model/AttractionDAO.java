package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import mvc.database.DBConnection;
import mvc.model.AttractionDTO;

public class AttractionDAO {
	
	private static AttractionDAO instance;

	
	public static AttractionDAO getInstance() {
		if(instance == null) {
			instance = new AttractionDAO();
		}
		return instance;
	}

	public static void setInstance(AttractionDAO instance) {
		AttractionDAO.instance = instance;
	}
	

	//getAttractions(�±�����, ��������, ��������)
	//����: ��Ʈ���� ��� �������� (�˻���� ���� ���ǿ� �´� ��� ��������)
	//�Է°�: �±�����, ��������, �������� / ��°�: ��Ʈ���� ����Ʈ
	// - DB���� �����͸� �˻��ؼ� id(������ȣ) ������������ �������� ������� ����
	// - �˻��� ���� ���� input �߰�
	public ArrayList<AttractionDTO> getAttractions(String tag, String age, String tall) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int index = 1;
		int listCount = getListCount(tag, age, tall);
		
		String sql = "select * from attraction";
		sql = addSearchCondition(sql, tag, age, tall)+" order by id asc";//�������� �˻��� �߰�
		ArrayList<AttractionDTO> list = new ArrayList<AttractionDTO>();
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.absolute(index)) {
				AttractionDTO atr = new AttractionDTO();
				
				atr.setId(rs.getInt("id"));
				atr.setName(rs.getString("name"));
				atr.setInfo(rs.getString("info"));
				atr.setTag(rs.getString("tag"));
				atr.setRide(rs.getString("ride"));
				atr.setAge(rs.getString("age"));
				atr.setTall(rs.getString("tall"));
				atr.setFilename(rs.getString("filename"));
				list.add(atr);
				
				if(index < listCount) {
					index++;
				}else {
					break;
				}
			}
			return list;
		}catch(Exception ex) {
			System.out.println("getAttractions() ���� : "+ex);
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
	
	//getListCount(�±�����, ��������, ��������)
	//����: ���ǿ� ���� ��Ʈ���� �� ����ؼ� ���
	//�Է°�: �±�����, ��������, �������� / ��°�: ��Ʈ���� ��
	public int getListCount(String tag, String age, String tall) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		// MySQL �˻���(sql)�� ���ǿ� ���� �ٸ��� �ο�
		String sql="select count(*) from attraction";// �˻������� ���� �� ����
		sql = addSearchCondition(sql, tag, age, tall);
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		}catch(Exception ex) {
			System.out.println("getListCount() ���� : "+ex);
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
		
		return x;
	}
	
	//addSearchCondition(�˻���, �±�����, ��������, ��������)
	//����: MySQL �߰��˻���(sql)�� ���ǿ� ���� �ٸ��� �ο�
	//�Է°�: sql�˻���, �±�����, ��������, �������� / ��°�: sql�˻���
	public String addSearchCondition(String sql, String tag, String age, String tall) {
		
		if(tag!=null&&!tag.equals("all")) {
			if(age!=null&&!age.equals("all")) {
				if(tall!=null&&!tall.equals("all")) {
					sql=sql+" where tag='"+tag+"'"+" and where age='"+age+"'"+" and where tall='"+tall+"'"; //�±�����+��������+�������� ����
				}
					sql=sql+" where tag='"+tag+"'"+" and where age='"+age+"'"; //�±�����+�������� ����
			}
			sql=sql+" where tag='"+tag+"'"; //�±����Ǹ� ����
		}else if(age!=null&&!age.equals("all")){
			if(tall!=null&&!tall.equals("all")) {
				sql=sql+" where age='"+age+"'"+" and where tall='"+tall+"'"; //��������+�������� ����
			}
				sql=sql+" where age='"+age+"'"; //�������Ǹ� ����
		}else if(tall!=null&&!tall.equals("all")) {
			sql=sql+" where tall='"+tall+"'"; //�������Ǹ� ����
		}
		System.out.println(sql);
		return sql;
	}
	
	//getAttractionById(��Ʈ���� ��ȣ)
	//����: ���õ� ��Ʈ���� �� ���� ��������(dto�� ���� ����)
	// - �޼���� ���� (�� �޼���� : getAttractionByname(string name))
	// - id�� �������� ���õ� 
	//�Է°�: ��Ʈ���� ��ȣ / ��°�: ��Ʈ����DTO
	public AttractionDTO getAttractionById(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AttractionDTO atr = null;
		
		String sql = "select * from attraction where id = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				atr = new AttractionDTO();
				
				atr.setId(rs.getInt("id"));
				atr.setName(rs.getString("name"));
				atr.setInfo(rs.getString("info"));
				atr.setTag(rs.getString("tag"));
				atr.setRide(rs.getString("ride"));
				atr.setAge(rs.getString("age"));
				atr.setTall(rs.getString("tall"));
				atr.setFilename(rs.getString("filename"));
			}
			return atr;
		}catch(Exception ex) {
			System.out.println("getAttractionById() ���� : "+ex);
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

	//addAttraction(��Ʈ����DTO)
	// - DTO�� �޾� DB�� �űԵ�� �ϴ� �޼���� ����
	//�Է°�: ��Ʈ����DTO / ��°�: X (void �޼���)
	public void addAttraction(AttractionDTO atr) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into attraction values(?,?,?,?,?,?,?,?)";

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, atr.getId());//id
			pstmt.setString(2, atr.getName());//name
			pstmt.setString(3, atr.getInfo());//info
			pstmt.setString(4, atr.getTag());//tag
			pstmt.setString(5, atr.getRide());//ride
			pstmt.setString(6, atr.getAge());//age
			pstmt.setString(7, atr.getTall());//tall
			pstmt.setString(8, atr.getFilename());//filename
			
			pstmt.executeUpdate();
			System.out.println("add Attraction's image : "+atr.getFilename());
		}catch(Exception ex) {
			System.out.println("addAttraction() ���� : "+ex);
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
	
	//updateAttraction(Attraction attraction)
	//���� : DTO�� �޾� DB�� ����������Ʈ
	//�Է°� : ��Ʈ����DTO / ��°� : ����
	public void updateAttraction(AttractionDTO atr) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "";
		System.out.println("updateAttraction() started");
		try {
			if(atr.getFilename()!=null) {
				sql = "update attraction set name=?, info=?, tag=?, ride=?, age=?, tall=?, fileName=? where id=?";
				conn = DBConnection.getConnection();
				pstmt = conn.prepareStatement(sql);
				System.out.println("updateAttraction() change the imageFile : true");
				conn.setAutoCommit(false);
				
				pstmt.setString(1, atr.getName());//name
				pstmt.setString(2, atr.getInfo());//info
				pstmt.setString(3, atr.getTag());//tag
				pstmt.setString(4, atr.getRide());//ride
				pstmt.setString(5, atr.getAge());//age
				pstmt.setString(6, atr.getTall());//tall
				pstmt.setString(7, atr.getFilename());//filename
				pstmt.setInt(8, atr.getId());//id
				
				pstmt.executeUpdate();
				conn.commit();
			}else {
				sql = "update attraction set name=?, info=?, tag=?, ride=?, age=?, tall=? where id=?";
				conn = DBConnection.getConnection();
				pstmt = conn.prepareStatement(sql);
				System.out.println("updateAttraction() change the imageFile : false");
				conn.setAutoCommit(false);
				
				pstmt.setString(1, atr.getName());//name
				pstmt.setString(2, atr.getInfo());//info
				pstmt.setString(3, atr.getTag());//tag
				pstmt.setString(4, atr.getRide());//ride
				pstmt.setString(5, atr.getAge());//age
				pstmt.setString(6, atr.getTall());//tall
				pstmt.setInt(7, atr.getId());//id
				
				pstmt.executeUpdate();
				conn.commit();
			}
		}catch(Exception ex){
			System.out.println("updateAttraction() ���� : "+ ex);
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
	
	//deleteAttraction(��Ʈ���� ��ȣ)
	//���� : id�� �޾� DB���� �ش� id�� ������ ����
	//�Է°� : id / ��°� : ����
	public void deleteAttraction(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from attraction where id=?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("deleteAttraction() : delete attraction id - "+id);
		}catch(Exception ex) {
			System.out.println("deleteAttraction() ���� : "+ex);
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
