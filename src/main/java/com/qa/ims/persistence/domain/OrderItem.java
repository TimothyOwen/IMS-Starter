package com.qa.ims.persistence.domain;

public class OrderItem {
	private Long order_item_id;
	private Long order_id;
	private Long item_id;
	private Integer item_quantity;
	public OrderItem(Long order_id, Long item_id, int item_quantity) {
		this.order_id = order_id;
		this.item_id = item_id;
		this.item_quantity = item_quantity;
	}
	public OrderItem(Long order_item_id, Long order_id, Long item_id, int item_quantity) {
		this.order_item_id = order_item_id;
		this.order_id = order_id;
		this.item_id = item_id;
		this.item_quantity = item_quantity;
	}
	public Long getOrderItemId() {
		return order_item_id;
	}
	public void setOrderItemId(Long order_item_id) {
		this.order_item_id = order_item_id;
	}
	public Long getOrderId() {
		return order_id;
	}
	public void setOrderId(Long order_id) {
		this.order_id = order_id;
	}
	public Long getItemId() {
		return item_id;
	}
	public void setItemId(Long item_id) {
		this.item_id = item_id;
	}
	public int getItemQuantity() {
		return item_quantity;
	}
	public void setItemQuantity(int item_quantity) {
		this.item_quantity = item_quantity;
	}
	@Override
	public String toString() {
		return "Order Item ID: " + order_item_id + "  Order ID: " + order_id + "  Item ID: " + item_id
				+ "  Item Quantity: " + item_quantity;
	}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order_item_id == null) ? 0 : order_item_id.hashCode());
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
		result = prime * result + ((item_id == null) ? 0 : item_id.hashCode());
		result = prime * result + ((item_quantity == null) ? 0 : item_quantity.hashCode());
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj) 
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (order_item_id == null) {
			if (other.order_item_id != null)
				return false;
		} else if (!order_item_id.equals(other.order_item_id))
			return false;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (item_id == null) {
			if (other.item_id != null)
				return false;
		} else if (!item_id.equals(other.item_id))
			return false;
		if (item_quantity == null) {
			if (other.item_quantity != null)
				return false;
		} else if (!item_quantity.equals(other.item_quantity))
			return false;
		return true;
	}
}