package demo.data;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;






@Entity
@Table(name = "USERS")
public class UserEntity {

	


	private String userId;
	private String fisrtName;
	private String lastName;
	private LocalDate birthDate;
	private Gender gender;
	private String phone;
	private AddressEntity address;
	private Boolean active;
	private Date signUpTimestamp;
	private String email;
	private String password;
	private UserRole role;

	private SodalityEntity sodality;
	private Set<TaskEntity> tasks;
	private boolean haveRequest;
	

	



	public UserEntity() {
		setTasks(null);

	}







	public UserEntity(String userId, String fisrtName, String lastName, LocalDate birthDate, Gender gender,
			String phone, AddressEntity address, Boolean active, Date signUpTimestamp, String email, String password,
			UserRole role, SodalityEntity sodality, Set<TaskEntity> tasks , boolean haveRequest) {
		super();
		this.userId = userId;
		this.fisrtName = fisrtName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.active = active;
		this.signUpTimestamp = signUpTimestamp;
		this.email = email;
		this.password = password;
		this.role = role;
		this.sodality = sodality;
		setTasks(tasks);
		this.haveRequest = haveRequest;
	}






	@Id
	public String getUserId() {
		return userId;
	}







	public void setUserId(String userId) {
		this.userId = userId;
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







	public LocalDate getBirthDate() {
		return birthDate;
	}







	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
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







	public String getEmail() {
		return email;
	}







	public void setEmail(String email) {
		this.email = email;
	}







	public String getPassword() {
		return password;
	}







	public void setPassword(String password) {
		this.password = password;
	}






	@Enumerated(EnumType.STRING)//(EnumType.ORDINAL)
	public UserRole getRole() {
		return role;
	}







	public void setRole(UserRole role) {
		this.role = role;
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





	@OneToMany(mappedBy = "volunteer", fetch = FetchType.LAZY)
	public Set<TaskEntity> getTasks() {
		return tasks;
	}







	public void setTasks(Set<TaskEntity> tasks) {
		this.tasks = tasks;
	}


	
	

	public boolean isHaveRequest() {
		return haveRequest;
	}




	public void setHaveRequest(boolean haveRequest) {
		this.haveRequest = haveRequest;
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
