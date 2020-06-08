package com.example.login.Boundaris;



public class BirthDate {



	private int days;
	private int months;
	private int years;




	public BirthDate() {}





	public BirthDate(int days, int months, int years) {
		super();
		this.days = days;
		this.months = months;
		this.years = years;
	}



	public int getDays() {
		return days;
	}


	public void setDays(int days) {
		this.days = days;
	}


	public int getMonths() {
		return months;
	}


	public void setMonths(int months) {
		this.months = months;
	}


	public int getYears() {
		return years;
	}


	public void setYears(int years) {
		this.years = years;
	}



	@Override
	public String toString() {
		return "BirthDate [days=" + days + ", months=" + months + ", years=" + years + "]";
	}




}
