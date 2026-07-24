package com.inventory.order.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import jakarta.validation.constraints.*;
public class OrderDto {
    private Long id; private LocalDateTime orderDate; private String status; private BigDecimal totalAmount;
    @NotNull(message = "Customer ID is required") private Long customerId; private String customerName;
    @NotEmpty(message = "Order must contain at least one item") private List<OrderItemDto> orderItems = new ArrayList<>();
    public Long getId() { return id; } public void setId(Long id) { this.id = id; } public LocalDateTime getOrderDate() { return orderDate; } public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; } public String getStatus() { return status; } public void setStatus(String status) { this.status = status; } public BigDecimal getTotalAmount() { return totalAmount; } public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; } public Long getCustomerId() { return customerId; } public void setCustomerId(Long customerId) { this.customerId = customerId; } public String getCustomerName() { return customerName; } public void setCustomerName(String customerName) { this.customerName = customerName; } public List<OrderItemDto> getOrderItems() { return orderItems; } public void setOrderItems(List<OrderItemDto> orderItems) { this.orderItems = orderItems; }
}
