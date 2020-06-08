package demo.rest.boundaries;

public class AddressBoundary {




	private String state;
	private String city;
	private String streetAddress;
	private int houseNumber;
	private int zipCode;




	public AddressBoundary() {}




	public AddressBoundary(String state, String city, String streetAddress, int houseNumber, int zipCode) {
		super();
		this.state = state;
		this.city = city;
		this.streetAddress = streetAddress;
		this.houseNumber = houseNumber;
		this.zipCode = zipCode;
	}




	public String getState() {
		return state;
	}




	public void setState(String state) {
		this.state = state;
	}




	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public String getStreetAddress() {
		return streetAddress;
	}




	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}




	public int getHouseNumber() {
		return houseNumber;
	}




	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}




	public int getZipCode() {
		return zipCode;
	}




	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}




	@Override
	public String toString() {
		return "Address [state=" + state + ", city=" + city + ", streetAddress=" + streetAddress + ", houseNumber="
				+ houseNumber + ", zipCode=" + zipCode + "]";
	}





}
