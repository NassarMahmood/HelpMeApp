package demo.data;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "TASKS")
public class TaskEntity {



	private Long taskId;
	private Date date;
	private boolean taskDone;
	private int eayDays;
	private String notes;

	private UserEntity volunteer;
	private SodalityEntity sodality;
	private Set<IndigentEntity> indigents;





	public TaskEntity() {
		setIndigents(null);
	}






	public TaskEntity(Long id, Date date, boolean taskDone, int eayDays, String notes, UserEntity volunteer,
			SodalityEntity sodality, Set<IndigentEntity> indigents) {
		super();
		this.taskId = id;
		this.date = date;
		this.taskDone = taskDone;
		this.eayDays = eayDays;
		this.notes = notes;
		this.volunteer = volunteer;
		this.sodality = sodality;
		setIndigents(indigents);
	}





	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return taskId;
	}





	public void setId(Long id) {
		this.taskId = id;
	}




	@Temporal(TemporalType.TIMESTAMP)
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



	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "volunteer_id", nullable = false)
	public UserEntity getVolunteer() {
		return volunteer;
	}





	public void setVolunteer(UserEntity volunteer) {
		this.volunteer = volunteer;
	}




	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sodality_id", nullable = false)
	public SodalityEntity getSodality() {
		return sodality;
	}





	public void setSodality(SodalityEntity sodality) {
		this.sodality = sodality;
	}




	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "TASK_INDIGENT",
			joinColumns = @JoinColumn(name = "task_id", referencedColumnName="id"),
			inverseJoinColumns = @JoinColumn(name = "indigent_id", referencedColumnName="indigentId") 
			)
	public Set<IndigentEntity> getIndigents() {
		return indigents;
	}





	public void setIndigents(Set<IndigentEntity> indigents) {
		if(indigents == null)
			this.indigents = new HashSet<IndigentEntity>();
		else
			this.indigents = indigents;
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
