package com.pennant.irctc.mvc;

public class PassengerModel {
	private Integer ticket_No;
	private String name;
	private Integer age;
	private String gender;
	private String preference;

	public Integer getTicket_No() {
		return ticket_No;
	}

	public void setTicket_No(Integer ticket_No) {
		this.ticket_No = ticket_No;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}
}
