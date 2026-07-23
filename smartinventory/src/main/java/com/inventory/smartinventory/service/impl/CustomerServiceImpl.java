package com.inventory.smartinventory.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.smartinventory.dto.CustomerDto;
import com.inventory.smartinventory.entity.Customer;
import com.inventory.smartinventory.exception.ResourceNotFoundException;
import com.inventory.smartinventory.repository.CustomerRepository;
import com.inventory.smartinventory.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto saveCustomer(CustomerDto dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        
        Customer savedCustomer = customerRepository.save(customer);
        
        CustomerDto responseDto = new CustomerDto();
        BeanUtils.copyProperties(savedCustomer, responseDto);
        return responseDto;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDto dto = new CustomerDto();
                    BeanUtils.copyProperties(customer, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        CustomerDto dto = new CustomerDto();
        BeanUtils.copyProperties(customer, dto);
        return dto;
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        BeanUtils.copyProperties(dto, customer, "id");
        
        Customer updatedCustomer = customerRepository.save(customer);
        
        CustomerDto responseDto = new CustomerDto();
        BeanUtils.copyProperties(updatedCustomer, responseDto);
        return responseDto;
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }
}
