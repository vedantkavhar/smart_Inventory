package com.inventory.smartinventory.service;

import java.util.List;

import com.inventory.smartinventory.dto.CustomerDto;

public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto dto);
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(Long id);
    CustomerDto updateCustomer(Long id, CustomerDto dto);
    void deleteCustomer(Long id);
}
