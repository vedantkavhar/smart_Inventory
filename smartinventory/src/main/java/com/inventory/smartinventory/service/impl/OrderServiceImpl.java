package com.inventory.smartinventory.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.smartinventory.dto.OrderDto;
import com.inventory.smartinventory.dto.OrderItemDto;
import com.inventory.smartinventory.entity.Customer;
import com.inventory.smartinventory.entity.Order;
import com.inventory.smartinventory.entity.OrderItem;
import com.inventory.smartinventory.entity.Product;
import com.inventory.smartinventory.exception.ResourceNotFoundException;
import com.inventory.smartinventory.repository.CustomerRepository;
import com.inventory.smartinventory.repository.OrderRepository;
import com.inventory.smartinventory.repository.ProductRepository;
import com.inventory.smartinventory.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public OrderDto placeOrder(OrderDto dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + dto.getCustomerId()));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDto itemDto : dto.getOrderItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + itemDto.getProductId()));

            // Check inventory (Phase 6 feature but basic implementation here)
            if (product.getQuantity() < itemDto.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }

            // Deduct stock
            product.setQuantity(product.getQuantity() - itemDto.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDto.getQuantity());
            
            // Use provided price or default to product price
            BigDecimal itemPrice = itemDto.getPrice() != null ? itemDto.getPrice() : product.getPrice();
            orderItem.setPrice(itemPrice);

            totalAmount = totalAmount.add(itemPrice.multiply(BigDecimal.valueOf(itemDto.getQuantity())));
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        return mapToDto(savedOrder);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return mapToDto(order);
    }

    @Override
    public OrderDto updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        return mapToDto(updatedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        orderRepository.delete(order);
    }

    private OrderDto mapToDto(Order order) {
        OrderDto dto = new OrderDto();
        BeanUtils.copyProperties(order, dto);
        dto.setCustomerId(order.getCustomer().getId());
        dto.setCustomerName(order.getCustomer().getName());

        List<OrderItemDto> itemDtos = order.getOrderItems().stream().map(item -> {
            OrderItemDto itemDto = new OrderItemDto();
            BeanUtils.copyProperties(item, itemDto);
            itemDto.setProductId(item.getProduct().getId());
            itemDto.setProductName(item.getProduct().getName());
            return itemDto;
        }).collect(Collectors.toList());

        dto.setOrderItems(itemDtos);
        return dto;
    }
}
