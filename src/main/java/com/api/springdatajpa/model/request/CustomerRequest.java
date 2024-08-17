package com.api.springdatajpa.model.request;

import com.api.springdatajpa.model.entity.Customer;
import com.api.springdatajpa.model.entity.Email;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRequest {
    @NotEmpty(message = "Name Can not be empty")
    @NotBlank(message = "Name Can not be blank")
    @NotNull(message = "Name Can not be null")
    private String customerName;

    @NotEmpty(message = "Address Can not be empty")
    @NotBlank(message = "Address Can not be blank")
    @NotNull(message = "Address Can not be null")
    private String address;

    @NotEmpty(message = "Address Can not be empty")
    @NotBlank(message = "Address Can not be blank")
    @NotNull(message = "Address Can not be null")
    @Pattern(regexp = "^0[1-9][0-9]{7,8}$", message = "Invalid phone number")
    private String phoneNumber;

    @NotEmpty(message = "Email Can not be empty")
    @NotBlank(message = "Email Can not be blank")
    @NotNull(message = "Email Can not be null")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email address")
    private String email;

    public Customer toCustomerEntity() {
        Email email = new Email(this.email.toLowerCase());

        return new Customer(null,
                this.customerName.toUpperCase(),
                this.address.toUpperCase(),
                this.phoneNumber,
                email);
    }

    public Customer toCustomerEntity(Long customerId, Long emailId) {
        Email email = new Email(emailId, this.email.toLowerCase());

        return new Customer(customerId,
                this.customerName.toUpperCase(),
                this.address.toUpperCase(),
                this.phoneNumber,
                email);
    }
}
