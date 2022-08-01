import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DemoCheckDatabase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","sou1234");  
		
			System.out.println("connect to db");  
		
			String sql = "SELECT * FROM testingdb.souviklogin";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		
			while(rs.next())
			{
				System.out.println(rs.getString(1) + "  " + rs.getString(2)); 
				
								
				
			}
			
			
		} 
	catch (SQLException e)
	{
		e.printStackTrace();
	}

	}

}
