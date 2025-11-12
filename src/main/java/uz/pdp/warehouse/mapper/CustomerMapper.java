
package uz.pdp.warehouse.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.warehouse.model.dto.customer.CustomerCreateDto;
import uz.pdp.warehouse.model.dto.customer.CustomerDto;
import uz.pdp.warehouse.model.dto.customer.CustomerUpdateDto;
import uz.pdp.warehouse.model.entity.Customer;

@Component
public class CustomerMapper {
    public Customer fromCreateDto(CustomerCreateDto dto) {
        Customer customer = new Customer();
        customer.setFullName(dto.getFullName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setAddress(dto.getAddress());
        return customer;
    }

    public Customer fromUpdateDto(CustomerUpdateDto dto, Customer existingCustomer) {
        existingCustomer.setFullName(dto.getFullName());
        existingCustomer.setPhone(dto.getPhone());
        existingCustomer.setEmail(dto.getEmail());
        existingCustomer.setAddress(dto.getAddress());
        existingCustomer.setDeleted(dto.getDeleted());
        return existingCustomer;
    }

    public CustomerDto toDto(Customer c) {
        CustomerDto dto = new CustomerDto();
        dto.setId(c.getId());
        dto.setFullName(c.getFullName());
        dto.setPhone(c.getPhone());
        dto.setEmail(c.getEmail());
        dto.setAddress(c.getAddress());
        dto.setEnabled(c.getEnabled());
        return dto;
    }
}
