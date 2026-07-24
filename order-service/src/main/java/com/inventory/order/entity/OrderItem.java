package com.inventory.order.entity;
import java.math.BigDecimal;
import jakarta.persistence.*;
@Entity public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id; @Column(nullable = false) private Integer quantity; @Column(nullable = false, precision = 10, scale = 2) private BigDecimal price; @Column(nullable = false) private Long productId; @Column(length = 150) private String productName;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "order_id", nullable = false) private Order order;
    public Long getId() { return id; } public void setId(Long id) { this.id = id; } public Integer getQuantity() { return quantity; } public void setQuantity(Integer quantity) { this.quantity = quantity; } public BigDecimal getPrice() { return price; } public void setPrice(BigDecimal price) { this.price = price; } public Long getProductId() { return productId; } public void setProductId(Long productId) { this.productId = productId; } public String getProductName() { return productName; } public void setProductName(String productName) { this.productName = productName; } public Order getOrder() { return order; } public void setOrder(Order order) { this.order = order; }
}
