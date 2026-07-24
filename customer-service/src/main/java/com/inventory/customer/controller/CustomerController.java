package com.inventory.customer.controller;

import java.util.List;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.inventory.customer.dto.CustomerDto;
import com.inventory.customer.service.CustomerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) { this.customerService = customerService; }
    @PostMapping public ResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDto dto) { return new ResponseEntity<>(customerService.saveCustomer(dto), HttpStatus.CREATED); }
    @GetMapping public ResponseEntity<List<CustomerDto>> getAllCustomers() { return ResponseEntity.ok(customerService.getAllCustomers()); }
    @GetMapping("/{id}") public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) { return ResponseEntity.ok(customerService.getCustomerById(id)); }
    @PutMapping("/{id}") public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto dto) { return ResponseEntity.ok(customerService.updateCustomer(id, dto)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) { customerService.deleteCustomer(id); return ResponseEntity.noContent().build(); }
}
