package com.example.login.Boundaris;

import java.util.Date;
import java.util.Set;

public class TaskBoundary {



	private Long id;
	private Date date;
	private boolean taskDone;
	private int eayDays;
	private String notes;
	private UserBoundary volunteer;
	private SodalityBoundary sodality;
	private Set<IndigentBoundary> indigents;


	public TaskBoundary() {}


	public TaskBoundary(Long id, Date date, boolean taskDone, int eayDays, String notes, UserBoundary volunteer,
			SodalityBoundary sodality, Set<IndigentBoundary> indigents) {
		super();
		this.id = id;
		this.date = date;
		this.taskDone = taskDone;
		this.eayDays = eayDays;
		this.notes = notes;
		this.volunteer = volunteer;
		this.sodality = sodality;
		this.indigents = indigents;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public boolean isTaskDone() {
		return taskDone;
	}


	public void setTaskDone(boolean taskDone) {
		this.taskDone = taskDone;
	}


	public int getEayDays() {
		return eayDays;
	}


	public void setEayDays(int eayDays) {
		this.eayDays = eayDays;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public UserBoundary getVolunteer() {
		return volunteer;
	}


	public void setVolunteer(UserBoundary volunteer) {
		this.volunteer = volunteer;
	}


	public SodalityBoundary getSodality() {
		return sodality;
	}


	public void setSodality(SodalityBoundary sodality) {
		this.sodality = sodality;
	}


	public Set<IndigentBoundary> getIndigents() {
		return indigents;
	}


	public void setIndigents(Set<IndigentBoundary> indigents) {
		this.indigents = indigents;
	}


	@Override
	public String toString() {
		return "TaskBoundary [id=" + id + ", date=" + date + ", taskDone=" + taskDone + ", eayDays=" + eayDays
				+ ", notes=" + notes + ", volunteer=" + volunteer + ", sodality=" + sodality + ", indigents="
				+ indigents + "]";
	}





}
