package com.inventory.smartinventory.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.smartinventory.dto.SupplierDto;
import com.inventory.smartinventory.entity.Supplier;
import com.inventory.smartinventory.exception.ResourceNotFoundException;
import com.inventory.smartinventory.repository.SupplierRepository;
import com.inventory.smartinventory.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public SupplierDto saveSupplier(SupplierDto dto) {
        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(dto, supplier);
        
        Supplier savedSupplier = supplierRepository.save(supplier);
        
        SupplierDto responseDto = new SupplierDto();
        BeanUtils.copyProperties(savedSupplier, responseDto);
        return responseDto;
    }

    @Override
    public List<SupplierDto> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(supplier -> {
                    SupplierDto dto = new SupplierDto();
                    BeanUtils.copyProperties(supplier, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public SupplierDto getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));

        SupplierDto dto = new SupplierDto();
        BeanUtils.copyProperties(supplier, dto);
        return dto;
    }

    @Override
    public SupplierDto updateSupplier(Long id, SupplierDto dto) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));

        BeanUtils.copyProperties(dto, supplier, "id");
        
        Supplier updatedSupplier = supplierRepository.save(supplier);
        
        SupplierDto responseDto = new SupplierDto();
        BeanUtils.copyProperties(updatedSupplier, responseDto);
        return responseDto;
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));
        supplierRepository.delete(supplier);
    }
}
