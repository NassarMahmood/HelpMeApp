package demo.data;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "INDIGENTS")
public class IndigentEntity {


	private String indigentId;
	private String fisrtName;
	private String lastName;
	private LocalDate birthdate;
	private Gender gender;
	private String phone;
	private AddressEntity address;
	private Boolean active;
	private Date signUpTimestamp;

	private int eatDays;
	private String notes;

	private SodalityEntity sodality;
	private Set<TaskEntity> tasks;





	public IndigentEntity() {
		setTasks(null);
		
	}


	
	
	
	
	
	
	

	public IndigentEntity(String indigentId, String fisrtName, String lastName, LocalDate birthdate, Gender gender,
			String phone, AddressEntity address, Boolean active, Date signUpTimestamp, int eatDays, String notes,
			SodalityEntity sodality, Set<TaskEntity> tasks) {
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
		this.eatDays = eatDays;
		this.notes = notes;
		this.sodality = sodality;
		setTasks(tasks);
	}









	@Id
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



	public LocalDate getBirthdate() {
		return birthdate;
	}



	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}


	@Enumerated(EnumType.STRING)//(EnumType.ORDINAL)
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


	@OneToOne
	public AddressEntity getAddress() {
		return address;
	}



	public void setAddress(AddressEntity address) {
		this.address = address;
	}



	public Boolean getActive() {
		return active;
	}



	public void setActive(Boolean active) {
		this.active = active;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getSignUpTimestamp() {
		return signUpTimestamp;
	}



	public void setSignUpTimestamp(Date signUpTimestamp) {
		this.signUpTimestamp = signUpTimestamp;
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


//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "sodality_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sodality_id", nullable = true)
	public SodalityEntity getSodality() {
		return sodality;
	}



	public void setSodality(SodalityEntity sodality) {
		this.sodality = sodality;
	}


	@ManyToMany(fetch = FetchType.LAZY , mappedBy = "indigents")
	public Set<TaskEntity> getTasks() {
		return tasks;
	}



	public void setTasks(Set<TaskEntity> tasks) {
		if(tasks == null)
			this.tasks = new HashSet<TaskEntity>();
		else
			this.tasks = tasks;
	}



	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}



	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}






}
