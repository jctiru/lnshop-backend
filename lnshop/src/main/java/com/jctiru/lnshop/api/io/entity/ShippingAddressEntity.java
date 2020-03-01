package com.jctiru.lnshop.api.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shipping_address")
public class ShippingAddressEntity {

	@Id
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "country", nullable = false)
	private String country;

	@Column(name = "zip", nullable = false)
	private String zip;

	@Column(name = "line", nullable = false)
	private String line;

	@Column(name = "city", nullable = false)
	private String city;

	@OneToOne
	@MapsId // Make `id` column serve as both PK and FK
	@JoinColumn(name = "id", nullable = false) // Hibernate will generate/expect `id` column instead of `order_id`
	private OrderEntity order;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

}
