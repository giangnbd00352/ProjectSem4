package com.ensat.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the image_order database table.
 * 
 */
@Entity
@Table(name = "image_order")
@NamedQueries({ 
	@NamedQuery(name = "ImageOrder.findAll", query = "SELECT i FROM ImageOrder i"),
	@NamedQuery(name = "ImageOrder.getDistinctOrder", query = "SELECT DISTINCT i FROM ImageOrder i")
	})
public class ImageOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "order_code")
	private String orderCode;

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

	@Column(name = "image_name")
	private String imageName;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "date_order")
	private String dateOrder;

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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(String dateOrder) {
		this.dateOrder = dateOrder;
	}
	
	

}