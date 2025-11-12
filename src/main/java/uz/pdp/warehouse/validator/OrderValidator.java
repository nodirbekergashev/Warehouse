package uz.pdp.warehouse.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.warehouse.exceptions.exception.ValidationException;
import uz.pdp.warehouse.model.dto.order.OrderCreateDto;
import uz.pdp.warehouse.repository.CustomerRepository;

@Component
@RequiredArgsConstructor
public class OrderValidator {
    private final CustomerRepository customerRepository;
    public void validateOnCreate(OrderCreateDto dto) {
        if (dto == null) {
            throw new ValidationException("Order DTO is null");
        }
        if (dto.getCustomerId() == null|| !customerRepository.findById(dto.getCustomerId()).isPresent()) {
            throw new IllegalArgumentException("Customer ID is null");
        }
    }
}
