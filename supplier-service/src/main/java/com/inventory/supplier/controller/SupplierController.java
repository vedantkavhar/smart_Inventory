package com.inventory.supplier.controller;
import java.util.List;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.inventory.supplier.dto.SupplierDto;
import com.inventory.supplier.service.SupplierService;
import jakarta.validation.Valid;
@RestController @RequestMapping("/api/suppliers") public class SupplierController {
    private final SupplierService supplierService; public SupplierController(SupplierService supplierService) { this.supplierService = supplierService; }
    @PostMapping public ResponseEntity<SupplierDto> saveSupplier(@Valid @RequestBody SupplierDto dto) { return new ResponseEntity<>(supplierService.saveSupplier(dto), HttpStatus.CREATED); }
    @GetMapping public ResponseEntity<List<SupplierDto>> getAllSuppliers() { return ResponseEntity.ok(supplierService.getAllSuppliers()); }
    @GetMapping("/{id}") public ResponseEntity<SupplierDto> getSupplierById(@PathVariable Long id) { return ResponseEntity.ok(supplierService.getSupplierById(id)); }
    @PutMapping("/{id}") public ResponseEntity<SupplierDto> updateSupplier(@PathVariable Long id, @Valid @RequestBody SupplierDto dto) { return ResponseEntity.ok(supplierService.updateSupplier(id, dto)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) { supplierService.deleteSupplier(id); return ResponseEntity.noContent().build(); }
}
