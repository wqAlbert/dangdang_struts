package com.imvcc.domain;

import java.util.Set;

/**
 * DOrder generated by MyEclipse Persistence Tools
 */

public class Order implements java.io.Serializable {

	// Fields

	private Integer id;

	private Integer userId;

	private Integer status;

	private long orderTime;

	private String orderDesc;

	private double totalPrice;

	private ReceiveAddress address;
	
	private Set<Item> items;

	// Constructors

	/** default constructor */
	public Order() {
	}

	/** minimal constructor */
	public Order(Integer userId, Integer status, long orderTime,
			double totalPrice) {
		this.userId = userId;
		this.status = status;
		this.orderTime = orderTime;
		this.totalPrice = totalPrice;
	}


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public long getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderDesc() {
		return this.orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	


	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public ReceiveAddress getAddress() {
		return address;
	}

	public void setAddress(ReceiveAddress address) {
		this.address = address;
	}

}