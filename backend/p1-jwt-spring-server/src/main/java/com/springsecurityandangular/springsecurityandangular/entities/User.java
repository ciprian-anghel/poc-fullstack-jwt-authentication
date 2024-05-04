package com.springsecurityandangular.springsecurityandangular.entities;

//TODO: this will be used for database
//@Entity
//@Table("user")
public class User {

//	@Id
//	@GeneratedValue(strategy = GenerationType.INDENTIY)
	private Long id;
	
//	@Column...
	private String firstName;
	
	private String lastName;
	
	private String login;
	
	private String password;
	
	public User() { }	

	public User(Long id, String firstName, String lastName, String login, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
