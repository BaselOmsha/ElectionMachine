package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.model.Voter;

public class Dao {

	private Connection conn;

	// When new instance is created, also DB-connection is created
	public Dao() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/electionmachine", "team2",
					"kukkuu");
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Manually close DB-connection for releasing resource
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addUser(String fname, String lname, String ssn, String email, 
			String uname, String paswd, String salt) {
		
		String sql = "insert into voters(fname, lname, ssn, email, uname, paswd, salt) values (?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, fname);
			stmt.setString(2, lname);
			stmt.setString(3, ssn);
			stmt.setString(4, email);
			stmt.setString(5, uname);
			stmt.setString(6, paswd);
			stmt.setString(7, salt);

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public int saveVoter(Voter voter) {
//		Statement stmt = null;
//		int count = 0;
//		try {
//			stmt = conn.createStatement();
//			count = stmt.executeUpdate("insert into voters(fname, lname, ssn, email, uname, paswd, salt) values('"
//					+ voter.getFname() + " " + voter.getLname() + " " + voter.getSsn() + " " + voter.getEmail() + " "
//					+ voter.getUname() + " " + voter.getPaswd() + " " + voter.getSalt());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return count;
//	}
	
	public String getUserSalt(String uname) {
		String result = "";
		String sql = "select salt from voters where uname=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, uname);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getString("salt");
			}
			
		} catch (SQLException e ) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getUserpasswordHash(String uname) {
		String result = "";
		String sql = "select paswd from voters where uname=?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, uname);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getString("paswd");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
