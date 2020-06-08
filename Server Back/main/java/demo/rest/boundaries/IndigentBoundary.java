package demo.rest.boundaries;

import java.util.Date;



public class IndigentBoundary {


	private String indigentId;
	private String fisrtName;
	private String lastName;
	private BirthDate birthdate;
	private Gender gender;
	private String phone;
	private AddressBoundary address;
	private Boolean active;
	private Date signUpTimestamp;

	private SodalityBoundary sodality;
	private int eatDays;
	private String notes;



	public IndigentBoundary() {}



	public IndigentBoundary(String indigentId, String fisrtName, String lastName, BirthDate birthdate, Gender gender,
			String phone, AddressBoundary address, Boolean active, Date signUpTimestamp, SodalityBoundary sodality,
			int eatDays, String notes) {
		super();
		this.indigentId = indigentId;
		this.fisrtName = fisrtName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.active = active;
		this.signUpTimestamp = signUpTimestamp;
		this.sodality = sodality;
		this.eatDays = eatDays;
		this.notes = notes;
	}



	public String getIndigentId() {
		return indigentId;
	}



	public void setIndigentId(String indigentId) {
		this.indigentId = indigentId;
	}



	public String getFisrtName() {
		return fisrtName;
	}



	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public BirthDate getBirthdate() {
		return birthdate;
	}



	public void setBirthdate(BirthDate birthdate) {
		this.birthdate = birthdate;
	}



	public Gender getGender() {
		return gender;
	}



	public void setGender(Gender gender) {
		this.gender = gender;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public AddressBoundary getAddress() {
		return address;
	}



	public void setAddress(AddressBoundary address) {
		this.address = address;
	}



	public Boolean getActive() {
		return active;
	}



	public void setActive(Boolean active) {
		this.active = active;
	}



	public Date getSignUpTimestamp() {
		return signUpTimestamp;
	}



	public void setSignUpTimestamp(Date signUpTimestamp) {
		this.signUpTimestamp = signUpTimestamp;
	}



	public SodalityBoundary getSodality() {
		return sodality;
	}



	public void setSodality(SodalityBoundary sodality) {
		this.sodality = sodality;
	}



	public int getEatDays() {
		return eatDays;
	}



	public void setEatDays(int eatDays) {
		this.eatDays = eatDays;
	}



	public String getNotes() {
		return notes;
	}



	public void setNotes(String notes) {
		this.notes = notes;
	}



	@Override
	public String toString() {
		return "IndigentBoundary [patientId=" + indigentId + ", fisrtName=" + fisrtName + ", lastName=" + lastName
				+ ", birthdate=" + birthdate + ", gender=" + gender + ", phone=" + phone + ", address=" + address
				+ ", active=" + active + ", signUpTimestamp=" + signUpTimestamp + ", sodality=" + sodality
				+ ", eatDays=" + eatDays + ", notes=" + notes + "]";
	}






}



