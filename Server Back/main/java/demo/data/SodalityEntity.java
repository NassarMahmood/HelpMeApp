package demo.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity
@Table(name = "SODALITIES")
public class SodalityEntity {



	private Long id;
	private String name;
	private AddressEntity address;
	private String phone;
	private String email;
	private String webSite;

	private Set<UserEntity> users;
	private Set<IndigentEntity> indigents;
	private Set<TaskEntity> tasks;

	private Set<JoiningRequestsEntity> joinRequests;



	public SodalityEntity() {
		setUsers(users);
		setIndigents(indigents);
		setTasks(tasks);
	}






	public SodalityEntity(Long id, String name, AddressEntity address, String phone, String email, String webSite,
			Set<UserEntity> users, Set<IndigentEntity> indigents, Set<TaskEntity> tasks, Set<JoiningRequestsEntity> joinRequests) {

		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.webSite = webSite;
		setUsers(users);
		setIndigents(indigents);
		setTasks(tasks);
		
	}






	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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





	@OneToOne
	public AddressEntity getAddress() {
		return address;
	}






	public void setAddress(AddressEntity address) {
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





	@OneToMany(mappedBy = "sodality", fetch = FetchType.LAZY)
	public Set<UserEntity> getUsers() {
		return users;
	}




	public void setUsers(Set<UserEntity> users) {
		if(users == null)
			this.users = new HashSet<UserEntity>();
		else
			this.users = users;
	}





	@OneToMany(mappedBy = "sodality", fetch = FetchType.LAZY)
	public Set<IndigentEntity> getIndigents() {
		return indigents;
	}






	public void setIndigents(Set<IndigentEntity> indigents) {
		if(indigents == null)
			this.indigents = new HashSet<IndigentEntity>();
		else
			this.indigents = indigents;
	}





	@OneToMany(mappedBy = "sodality", fetch = FetchType.LAZY)
	public Set<TaskEntity> getTasks() {
		return tasks;
	}






	public void setTasks(Set<TaskEntity> tasks) {
		if(tasks == null)
			this.tasks = new HashSet<TaskEntity>();
		else
			this.tasks = tasks;
	}



	@OneToMany(mappedBy = "sodality", fetch = FetchType.LAZY)
	public Set<JoiningRequestsEntity> getJoinRequests() {
		return joinRequests;
	}






	public void setJoinRequests(Set<JoiningRequestsEntity> joinRequests) {
		this.joinRequests = joinRequests;
	}






	public void addUser(UserEntity user) {
		this.users.add(user);
	}




	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}


}
