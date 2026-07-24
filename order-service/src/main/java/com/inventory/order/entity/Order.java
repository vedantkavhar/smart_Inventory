package com.inventory.order.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import jakarta.persistence.*;
@Entity @Table(name = "orders") public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id; @Column(nullable = false) private LocalDateTime orderDate; @Column(length = 50) private String status; @Column(nullable = false, precision = 12, scale = 2) private BigDecimal totalAmount;
    @Column(nullable = false) private Long customerId; @Column(length = 150) private String customerName;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) private List<OrderItem> orderItems = new ArrayList<>();
    public Long getId() { return id; } public void setId(Long id) { this.id = id; } public LocalDateTime getOrderDate() { return orderDate; } public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; } public String getStatus() { return status; } public void setStatus(String status) { this.status = status; } public BigDecimal getTotalAmount() { return totalAmount; } public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; } public Long getCustomerId() { return customerId; } public void setCustomerId(Long customerId) { this.customerId = customerId; } public String getCustomerName() { return customerName; } public void setCustomerName(String customerName) { this.customerName = customerName; } public List<OrderItem> getOrderItems() { return orderItems; } public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}
