package com.springsecurityandangular.springsecurityandangular.dto;

public class Resource {
	private int id;
	private String content;
		
	public Resource(int id, String content) {
		this.id = id;
		this.content = content;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getContent() {
		return this.content;
	}
}
