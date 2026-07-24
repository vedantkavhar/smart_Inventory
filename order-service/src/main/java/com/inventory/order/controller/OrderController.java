package com.inventory.order.controller;
import java.util.List;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.inventory.order.dto.OrderDto;
import com.inventory.order.service.OrderService;
import jakarta.validation.Valid;
@RestController @RequestMapping("/api/orders") public class OrderController {
    private final OrderService orderService; public OrderController(OrderService orderService) { this.orderService = orderService; }
    @PostMapping public ResponseEntity<OrderDto> placeOrder(@Valid @RequestBody OrderDto dto) { return new ResponseEntity<>(orderService.placeOrder(dto), HttpStatus.CREATED); }
    @GetMapping public ResponseEntity<List<OrderDto>> getAllOrders() { return ResponseEntity.ok(orderService.getAllOrders()); }
    @GetMapping("/{id}") public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) { return ResponseEntity.ok(orderService.getOrderById(id)); }
    @PutMapping("/{id}/status") public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long id, @RequestParam String status) { return ResponseEntity.ok(orderService.updateOrderStatus(id, status)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> deleteOrder(@PathVariable Long id) { orderService.deleteOrder(id); return ResponseEntity.noContent().build(); }
}
