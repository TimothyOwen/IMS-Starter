package com.qa.ims.persistence.domain;

public class Order {
	private Long order_id;
	private Long customer_id;
	private Double cost;
	private String shipment_date;
	public Order(Long customer_id, Double cost, String shipment_date) {
		this.customer_id = customer_id;
		this.cost = cost;
		this.shipment_date = shipment_date;
	}
	public Order(Long order_id, Long customer_id, Double cost, String shipment_date) {
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.cost = cost;
		this.shipment_date = shipment_date;
	}
	public Long getOrderId() {
		return order_id;
	}
	public void setOrderId(Long order_id) {
		this.order_id = order_id;
	}
	public Long getCustomerId() {
		return customer_id;
	}
	public void setCustomerId(Long customer_id) {
		this.customer_id = customer_id;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getShipmentDate() {
		return shipment_date;
	}
	public void setShipmentDate(String shipment_date) {
		this.shipment_date = shipment_date;
	}
	//toString & hashCode & Equals
}
