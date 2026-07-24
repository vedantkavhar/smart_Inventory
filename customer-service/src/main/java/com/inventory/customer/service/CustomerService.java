package com.inventory.customer.service;

import java.util.List;
import com.inventory.customer.dto.CustomerDto;

public interface CustomerService { CustomerDto saveCustomer(CustomerDto dto); List<CustomerDto> getAllCustomers(); CustomerDto getCustomerById(Long id); CustomerDto updateCustomer(Long id, CustomerDto dto); void deleteCustomer(Long id); }
