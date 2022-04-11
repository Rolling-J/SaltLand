package mvc.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		
		//try and catch 추가(2022-02-13)
		try{
			String url = "jdbc:mysql://localhost:3306/SaltLand?serverTimezone=Asia/Seoul&useSSL=false";
			String user = "root";
			String password = "1234";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection(url, user, password);
		}catch(SQLException ex){
			System.out.println("데이터베이스 연결에 실패하였습니다.<br>");
			System.out.println("SQLException : "+ex.getMessage());
		}
		
		return conn;
		
	}

}
