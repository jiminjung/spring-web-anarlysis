package kr.ac.jbnu.se.awp.gitplay4.model;

public class Login {
	private String id;
	private String password;

	public Login() {
		
	}
	
	public Login(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}
}
