package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import mvc.database.DBConnection;
import dto.Attraction;


public class Attractionbox {
	
	private static Attractionbox instance;

	
	public static Attractionbox getInstance() {
		if(instance == null) {
			instance = new Attractionbox();
		}
		return instance;
	}

	public static void setInstance(Attractionbox instance) {
		Attractionbox.instance = instance;
	}
	

	//getAttractions(태그조건, 연령조건, 신장조건)
	//역할: 어트랙션 목록 가져오기 (검색기능 사용시 조건에 맞는 목록 가져오기)
	//입력값: 태그조건, 연령조건, 신장조건 / 출력값: 어트랙션 리스트
	// - DB에서 데이터를 검색해서 id(고유번호) 오름차순으로 가져오는 기능으로 수정
	// - 검색을 위한 조건 input 추가
	public ArrayList<Attraction> getAttractions(String tag, String age, String tall) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int index = 1;
		int listCount = getListCount(tag, age, tall);
		
		String sql = "select * from attraction";
		sql = addSearchCondition(sql, tag, age, tall)+" order by id asc";//오름차순 검색문 추가
		
		//DB에서 조건에 맞춰 검색한 리스트를 ArrayList에 삽입
		ArrayList<Attraction> list = new ArrayList<Attraction>();
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.absolute(index)) {
				Attraction atr = new Attraction();
				
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
			System.out.println("getAttractionList() 에러 : "+ex);
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
	
	//getListCount(태그조건, 연령조건, 신장조건)
	//역할: 조건에 맞춘 어트랙션 수 계산해서 출력
	//입력값: 태그조건, 연령조건, 신장조건 / 출력값: 어트랙션 수
	public int getListCount(String tag, String age, String tall) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		// MySQL 검색문(sql)을 조건에 따라 다르게 부여
		String sql="select count(*) from attraction";// 검색조건이 없을 때 적용
		sql = addSearchCondition(sql, tag, age, tall);
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		}catch(Exception ex) {
			System.out.println("getListCount() 에러 : "+ex);
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
	
	//addSearchCondition(검색문, 태그조건, 연령조건, 신장조건)
	//역할: MySQL 추가검색문(sql)을 조건에 따라 다르게 부여
	//입력값: sql검색문, 태그조건, 연령조건, 신장조건 / 출력값: sql검색문
	public String addSearchCondition(String sql, String tag, String age, String tall) {
		
		if(tag!=null&&!tag.equals("all")) {
			if(age!=null&&!age.equals("all")) {
				if(tall!=null&&!tall.equals("all")) {
					sql=sql+" where tag='"+tag+"'"+" and where age='"+age+"'"+" and where tall='"+tall+"'"; //태그조건+연령조건+신장조건 적용
				}
					sql=sql+" where tag='"+tag+"'"+" and where age='"+age+"'"; //태그조건+연령조건 적용
			}
			sql=sql+" where tag='"+tag+"'"; //태그조건만 적용
		}else if(age!=null&&!age.equals("all")){
			if(tall!=null&&!tall.equals("all")) {
				sql=sql+" where age='"+age+"'"+" and where tall='"+tall+"'"; //연령조건+신장조건 적용
			}
				sql=sql+" where age='"+age+"'"; //연령조건만 적용
		}else if(tall!=null&&!tall.equals("all")) {
			sql=sql+" where tall='"+tall+"'"; //신장조건만 적용
		}
		
		return sql;
	}
	
	//getAttractionById(어트랙션 번호)
	//역할: 선택된 어트랙션 상세 정보 가져오기(dto에 정보 저장)
	// - 메서드명 변경 (원 메서드명 : getAttractionByname(string name))
	// - id를 기준으로 선택된 
	//입력값: 어트랙션 번호 / 출력값: 어트랙션DTO
	public Attraction getAttractionById(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Attraction atr = null;
		
		String sql = "select * from attraction where id = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				atr = new Attraction();
				
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
			System.out.println("getAttractionById() 에러 : "+ex);
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

	//addAttraction(어트랙션DTO)
	// - DTO를 받아 DB에 신규등록 하는 메서드로 수정
	//입력값: 어트랙션DTO / 출력값: X (void 메서드)
	public void addAttraction(Attraction atr) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into attraction values(?,?,?,?,?,?,?)";

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

		}catch(Exception ex) {
			System.out.println("getAttractionList() 에러 : "+ex);
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
	//역할 : DTO를 받아 DB에 수정업데이트
	//입력값 : 어트랙션DTO / 출력값 : 없음
	public void updateAttraction(Attraction atr) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "";
		
		try {
			
			if(atr.getFilename()!=null) {
				sql = "update attraction set name=?, info=?, tag=?, ride=?, age=?, tall=?, fileName=? where id=?";
				conn = DBConnection.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				conn.setAutoCommit(false);
				
				pstmt.setInt(1, atr.getId());//id
				pstmt.setString(2, atr.getName());//name
				pstmt.setString(3, atr.getInfo());//info
				pstmt.setString(4, atr.getTag());//tag
				pstmt.setString(5, atr.getRide());//ride
				pstmt.setString(6, atr.getAge());//age
				pstmt.setString(7, atr.getTall());//tall
				pstmt.setString(8, atr.getFilename());//filename
				
				pstmt.executeUpdate();
				conn.commit();
			}else {
				sql = "update attraction set name=?, info=?, tag=?, ride=?, age=?, tall=? where id=?";
				conn = DBConnection.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				conn.setAutoCommit(false);
				
				pstmt.setInt(1, atr.getId());//id
				pstmt.setString(2, atr.getName());//name
				pstmt.setString(3, atr.getInfo());//info
				pstmt.setString(4, atr.getTag());//tag
				pstmt.setString(5, atr.getRide());//ride
				pstmt.setString(6, atr.getAge());//age
				pstmt.setString(7, atr.getTall());//tall
				
				pstmt.executeUpdate();
				conn.commit();
			}
		}catch(Exception ex){
			System.out.println("updateBoard() 에러 : "+ ex);
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
	
	//deleteAttraction(어트랙션 번호)
	//역할 : id를 받아 DB에서 해당 id의 데이터 삭제
	//입력값 : id / 출력값 : 없음
	public void deleteAttraction(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from attraction where id=?";

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
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
	