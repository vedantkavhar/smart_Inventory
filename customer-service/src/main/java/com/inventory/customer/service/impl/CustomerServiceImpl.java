package com.inventory.customer.service.impl;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.inventory.customer.dto.CustomerDto;
import com.inventory.customer.entity.Customer;
import com.inventory.customer.exception.ResourceNotFoundException;
import com.inventory.customer.repository.CustomerRepository;
import com.inventory.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) { this.customerRepository = customerRepository; }
    public CustomerDto saveCustomer(CustomerDto dto) { Customer customer = new Customer(); BeanUtils.copyProperties(dto, customer); return toDto(customerRepository.save(customer)); }
    public List<CustomerDto> getAllCustomers() { return customerRepository.findAll().stream().map(this::toDto).toList(); }
    public CustomerDto getCustomerById(Long id) { return toDto(customer(id)); }
    public CustomerDto updateCustomer(Long id, CustomerDto dto) { Customer customer = customer(id); BeanUtils.copyProperties(dto, customer, "id"); return toDto(customerRepository.save(customer)); }
    public void deleteCustomer(Long id) { customerRepository.delete(customer(id)); }
    private Customer customer(Long id) { return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id)); }
    private CustomerDto toDto(Customer customer) { CustomerDto dto = new CustomerDto(); BeanUtils.copyProperties(customer, dto); return dto; }
}
