package com.example.login.Boundaris;

import java.util.Date;

public class JoiningRequestsBoundary {

	
	
	
	private Long id;
	private UserBoundary user;
    private Date requestDate;
    private SodalityBoundary sodality;
    private boolean acceptable;
    
   
    
    
    
    public JoiningRequestsBoundary() {}





	public JoiningRequestsBoundary(Long id, UserBoundary user, Date requestDate, SodalityBoundary sodality,
			boolean acceptable) {
		super();
		this.id = id;
		this.user = user;
		this.requestDate = requestDate;
		this.sodality = sodality;
		this.acceptable = acceptable;
	}





	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public UserBoundary getUser() {
		return user;
	}





	public void setUser(UserBoundary user) {
		this.user = user;
	}





	public Date getRequestDate() {
		return requestDate;
	}





	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}





	public SodalityBoundary getSodality() {
		return sodality;
	}





	public void setSodality(SodalityBoundary sodality) {
		this.sodality = sodality;
	}





	public boolean isAcceptable() {
		return acceptable;
	}





	public void setAcceptable(boolean acceptable) {
		this.acceptable = acceptable;
	}





	@Override
	public String toString() {
		return "JoiningRequestsBoundary [id=" + id + ", user=" + user + ", requestDate=" + requestDate + ", sodality="
				+ sodality + ", acceptable=" + acceptable + "]";
	}

    
    

    
	
	
	
	
}
