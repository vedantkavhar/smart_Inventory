package com.inventory.supplier.service;
import java.util.List;
import com.inventory.supplier.dto.SupplierDto;
public interface SupplierService { SupplierDto saveSupplier(SupplierDto dto); List<SupplierDto> getAllSuppliers(); SupplierDto getSupplierById(Long id); SupplierDto updateSupplier(Long id, SupplierDto dto); void deleteSupplier(Long id); }
