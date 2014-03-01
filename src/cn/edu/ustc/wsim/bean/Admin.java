package cn.edu.ustc.wsim.bean;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	
	/**
	 * 
	 */
//	private static final long serialVersionUID = 7973169668713216977L;
	private Integer id;
	private String email;
	private String password;

	// Constructors

	/** default constructor */
	public Admin() {
	}
	
	public Admin(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Admin(Integer id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}