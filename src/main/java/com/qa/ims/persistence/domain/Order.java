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
	@Override
	public String toString() {
		return "Order ID: " + order_id + "  Customer ID: " + customer_id + "  Cost: " + cost + "  Shipment Date: "+ shipment_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
		result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((shipment_date == null) ? 0 : shipment_date.hashCode());
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj) 
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (!customer_id.equals(other.customer_id))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (shipment_date == null) {
			if (other.shipment_date != null)
				return false;
		} else if (!shipment_date.equals(other.shipment_date))
			return false;
		return true;
	}
}
