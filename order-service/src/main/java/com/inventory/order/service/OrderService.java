package com.inventory.order.service;
import java.util.List;
import com.inventory.order.dto.OrderDto;
public interface OrderService { OrderDto placeOrder(OrderDto dto); List<OrderDto> getAllOrders(); OrderDto getOrderById(Long id); OrderDto updateOrderStatus(Long id, String status); void deleteOrder(Long id); }
