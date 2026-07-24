package com.inventory.supplier.service.impl;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.inventory.supplier.dto.SupplierDto;
import com.inventory.supplier.entity.Supplier;
import com.inventory.supplier.exception.ResourceNotFoundException;
import com.inventory.supplier.repository.SupplierRepository;
import com.inventory.supplier.service.SupplierService;
@Service public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository; public SupplierServiceImpl(SupplierRepository supplierRepository) { this.supplierRepository = supplierRepository; }
    public SupplierDto saveSupplier(SupplierDto dto) { Supplier supplier = new Supplier(); BeanUtils.copyProperties(dto, supplier); return toDto(supplierRepository.save(supplier)); }
    public List<SupplierDto> getAllSuppliers() { return supplierRepository.findAll().stream().map(this::toDto).toList(); }
    public SupplierDto getSupplierById(Long id) { return toDto(supplier(id)); }
    public SupplierDto updateSupplier(Long id, SupplierDto dto) { Supplier supplier = supplier(id); BeanUtils.copyProperties(dto, supplier, "id"); return toDto(supplierRepository.save(supplier)); }
    public void deleteSupplier(Long id) { supplierRepository.delete(supplier(id)); }
    private Supplier supplier(Long id) { return supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id)); }
    private SupplierDto toDto(Supplier supplier) { SupplierDto dto = new SupplierDto(); BeanUtils.copyProperties(supplier, dto); return dto; }
}
