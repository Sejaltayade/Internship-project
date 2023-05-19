package com.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "menu_item_id")
    @NotNull(message = "Menu item ID is required")
    private Integer menuItemId;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be a positive value")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem() {
    }

    public OrderItem(Integer menuItemId, Integer quantity) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(Integer menuItemId) {
		this.menuItemId = menuItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

    // Getters and setters

    // ...
}
