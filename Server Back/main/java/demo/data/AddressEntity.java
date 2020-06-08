package demo.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name = "ADDRESS")
public class AddressEntity {
	
	private Long id;
	private String state;
	private String city;
	private String streetAddress;
	private int houseNumber;
	private int zipCode;
	
	
	
	
	public AddressEntity() {}




	public AddressEntity(String state, String city, String streetAddress, int houseNumber, int zipCode) {
		super();
		this.state = state;
		this.city = city;
		this.streetAddress = streetAddress;
		this.houseNumber = houseNumber;
		this.zipCode = zipCode;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
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






	
	
}
