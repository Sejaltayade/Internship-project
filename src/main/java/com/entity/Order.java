package com.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Customer email is required")
    @Email(message = "Invalid email format")
    private String customerEmail;

    @NotNull(message = "Total price is required")
    @PositiveOrZero(message = "Total price must be a positive or zero value")
    private double totalPrice;

    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(String customerName, String customerEmail, double totalPrice, Date orderDate, List<OrderItem> orderItems) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

    // Getters and setters

    // ...
}

