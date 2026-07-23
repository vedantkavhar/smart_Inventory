package com.inventory.smartinventory.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemDto {

    private Long id;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    private BigDecimal price; // Usually calculated from product price or sent by client

    @NotNull(message = "Product ID is required")
    private Long productId;

    private String productName;

    public OrderItemDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
