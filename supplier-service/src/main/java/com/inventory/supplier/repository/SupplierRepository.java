package com.inventory.supplier.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.inventory.supplier.entity.Supplier;
public interface SupplierRepository extends JpaRepository<Supplier, Long> { }
