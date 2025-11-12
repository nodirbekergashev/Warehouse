package uz.pdp.warehouse.validator;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.warehouse.model.dto.customer.CustomerCreateDto;
import uz.pdp.warehouse.exceptions.exception.ValidationException;
import uz.pdp.warehouse.repository.CustomerRepository;

@Component
@RequiredArgsConstructor
public class CustomerValidator {
    private final CustomerRepository repository;

    public void validateOnCreate(CustomerCreateDto dto) {
        if (dto == null) {
            throw new ValidationException("Email already exists");
        }

        validatePhone(dto.getPhone());
    }

    private void validatePhone(String phone) {
        if (StringUtils.isEmpty(phone) ||  phone.length() != 12) {
            throw new ValidationException("Phone number is invalid");
        }
    }
}
