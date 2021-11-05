package ca.mcgill.ecse321.librarysystem07.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Library")
public class Library {

	private String name;
	private String city;
	private String phoneNumber;

	public Library(String aName, String aCity, String aPhoneNumber)
	{
		setName(aName);
		setCity(aCity);
		setPhoneNumber(aPhoneNumber);
	}

	@Id
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
