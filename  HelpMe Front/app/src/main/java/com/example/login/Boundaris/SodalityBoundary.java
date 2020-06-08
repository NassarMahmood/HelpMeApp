package com.example.login.Boundaris;

public class SodalityBoundary {


	private Long id;
	private String name;
	private AddressBoundary address;
	private String phone;
	private String email;
	private String webSite;



	public SodalityBoundary() {}



	public SodalityBoundary(Long id, String name, AddressBoundary address, String phone, String email,
			String webSite) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.webSite = webSite;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public AddressBoundary getAddress() {
		return address;
	}


	public void setAddress(AddressBoundary address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getWebSite() {
		return webSite;
	}


	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}


	@Override
	public String toString() {
		return "HealthMaintenanceOrganization [id=" + id + ", name=" + name + ", address=" + address + ", phone="
				+ phone + ", email=" + email + ", webSite=" + webSite + ", getId()=" + getId() + ", getName()="
				+ getName() + ", getAddress()=" + getAddress() + ", getPhone()=" + getPhone() + ", getEmail()="
				+ getEmail() + ", getWebSite()=" + getWebSite() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}






}
