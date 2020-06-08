package demo.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import demo.rest.boundaries.UserBoundary;



@Entity
@Table(name = "JIONING_REQUESTS")
public class JoiningRequestsEntity {




	private Long id;
	private Date requestDate;
	private UserEntity user;
	private SodalityEntity sodality;
	private boolean acceptable;



	public JoiningRequestsEntity() {}


	public JoiningRequestsEntity(Long id, UserEntity user, Date requestDate, SodalityEntity sodality, boolean acceptable) {
		super();
		this.id = id;
		this.user = user;
		this.requestDate = requestDate;
		this.acceptable = acceptable;
		this.sodality = sodality;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}




	@OneToOne
	public UserEntity getUser() {
		return user;
	}





	public void setUser(UserEntity user) {
		this.user = user;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getRequestDate() {
		return requestDate;
	}





	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}





	public boolean isAcceptable() {
		return acceptable;
	}


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sodality_id", nullable = false)
	public SodalityEntity getSodality() {
		return sodality;
	}


	public void setSodality(SodalityEntity sodality) {
		this.sodality = sodality;
	}


	public void setAcceptable(boolean acceptable) {
		this.acceptable = acceptable;
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
