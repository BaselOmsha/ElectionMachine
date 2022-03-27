package app.model;

import java.io.Serializable;

public class Voter implements Serializable {
	private int id;
	private String fname;
	private String lname;
	private String ssn;
	private String email;
	private String uname;
	private String paswd;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPaswd() {
		return paswd;
	}
	public void setPaswd(String password) {
		this.paswd = password;
	}
	
	public String toString() {
		return id+" "+fname+" "+lname+" "+ssn+" "+email+" "+uname+" "+paswd;
	}
	
}
