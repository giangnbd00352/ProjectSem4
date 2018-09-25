package com.ensat.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the image_order database table.
 * 
 */
@Entity
@Table(name="image_order")
@NamedQuery(name="ImageOrder.findAll", query="SELECT i FROM ImageOrder i")
public class ImageOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "material")
	private String material;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "size")
	private String size;

	@Column(name = "username")
	private String username;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "address_detail")
	private String addressDetail;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaterial() {
		return this.material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
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
	
	

}