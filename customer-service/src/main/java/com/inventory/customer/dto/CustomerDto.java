package com.inventory.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerDto {
    private Long id;
    @NotBlank(message = "Customer name is required") @Size(min = 2, max = 150, message = "Customer name must be between 2 and 150 characters") private String name;
    @NotBlank(message = "Email is required") @Email(message = "Invalid email format") private String email;
    @Size(max = 20, message = "Phone number cannot exceed 20 characters") private String phone;
    @Size(max = 500, message = "Address cannot exceed 500 characters") private String address;
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; } public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; } public void setAddress(String address) { this.address = address; }
}
