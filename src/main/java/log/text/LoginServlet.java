package log.text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String dob = req.getParameter("dob");
		
		if(validateuser(email, dob)) {
			System.out.println("login sucessfully");
		}
		else {
			System.out.println("login not sucessfully");
		}
		
	}
	
	
	private boolean validateuser(String email,String dob) {
		
		boolean br = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbconnect","root","Puja2002");
			String sql1 = "SELECT * FROM register WHERE email = ? and dob = ?";
			PreparedStatement pre = conn.prepareStatement(sql1);
			pre.setString(1, email);
			pre.setString(2, dob);
		ResultSet rs =  pre.executeQuery();
		
		if(rs.next()) {
			br = true;
		}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		
		
		return br;
	
	}
	

}
