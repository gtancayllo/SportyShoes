package com.sportyshoes.entity;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="purchaseorder")
public class PurchaseOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name ="orderDate")
	private Date orderDate;
	
	@ManyToOne
    @JoinColumn(name="user_id")
    private User orderedByUser;
	
	@Column(name ="totalCost")
	private double totalCost;
	
	@OneToMany(mappedBy="purchaseOrder")
    private List<PurchaseOrderDetail> orderDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public User getOrderedByUser() {
		return orderedByUser;
	}

	public void setOrderedByUser(User orderedByUser) {
		this.orderedByUser = orderedByUser;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public List<PurchaseOrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<PurchaseOrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	public String getCategories(){
		return getOrderDetails()
				.stream()
				.map(od->od.getProduct().getCategory().getCategoryName())
				.distinct()
				.collect(Collectors.joining(";"));
	}
}
