package com.inventory.order.service.impl;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.inventory.order.dto.*;
import com.inventory.order.entity.*;
import com.inventory.order.exception.ResourceNotFoundException;
import com.inventory.order.repository.OrderRepository;
import com.inventory.order.service.OrderService;
@Service public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository; public OrderServiceImpl(OrderRepository orderRepository) { this.orderRepository = orderRepository; }
    @Transactional public OrderDto placeOrder(OrderDto dto) { Order order = new Order(); order.setCustomerId(dto.getCustomerId()); order.setCustomerName(dto.getCustomerName()); order.setOrderDate(LocalDateTime.now()); order.setStatus("PENDING"); BigDecimal total = BigDecimal.ZERO; for (OrderItemDto dtoItem : dto.getOrderItems()) { OrderItem item = new OrderItem(); item.setOrder(order); item.setProductId(dtoItem.getProductId()); item.setProductName(dtoItem.getProductName()); item.setQuantity(dtoItem.getQuantity()); item.setPrice(dtoItem.getPrice()); order.getOrderItems().add(item); total = total.add(dtoItem.getPrice().multiply(BigDecimal.valueOf(dtoItem.getQuantity()))); } order.setTotalAmount(total); return toDto(orderRepository.save(order)); }
    public List<OrderDto> getAllOrders() { return orderRepository.findAll().stream().map(this::toDto).toList(); }
    public OrderDto getOrderById(Long id) { return toDto(order(id)); }
    public OrderDto updateOrderStatus(Long id, String status) { Order order = order(id); order.setStatus(status); return toDto(orderRepository.save(order)); }
    public void deleteOrder(Long id) { orderRepository.delete(order(id)); }
    private Order order(Long id) { return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id)); }
    private OrderDto toDto(Order order) { OrderDto dto = new OrderDto(); dto.setId(order.getId()); dto.setOrderDate(order.getOrderDate()); dto.setStatus(order.getStatus()); dto.setTotalAmount(order.getTotalAmount()); dto.setCustomerId(order.getCustomerId()); dto.setCustomerName(order.getCustomerName()); dto.setOrderItems(order.getOrderItems().stream().map(item -> { OrderItemDto itemDto = new OrderItemDto(); itemDto.setId(item.getId()); itemDto.setProductId(item.getProductId()); itemDto.setProductName(item.getProductName()); itemDto.setQuantity(item.getQuantity()); itemDto.setPrice(item.getPrice()); return itemDto; }).toList()); return dto; }
}
