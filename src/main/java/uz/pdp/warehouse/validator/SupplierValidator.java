package uz.pdp.warehouse.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.warehouse.model.dto.supplier.SupplierCreateDto;
import uz.pdp.warehouse.repository.SupplierRepository;

@Component
@RequiredArgsConstructor
public class SupplierValidator {
    private final SupplierRepository supplierRepository;

    public void validateOnCreate(SupplierCreateDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Supplier cannot be null");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Supplier name cannot be blank");
        }
        if (dto.getPhone() == null || dto.getPhone().isBlank() || dto.getPhone().length()!=12 ||supplierRepository.findByPhone(dto.getPhone())) {
                throw new IllegalArgumentException("Phone number is invalid");
        }

        if (dto.getEmail() == null || dto.getEmail().isBlank() || supplierRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Supplier email cannot be blank");
        }
        if(dto.getAddress() == null || dto.getAddress().isBlank()) {
            throw new IllegalArgumentException("Supplier address cannot be blank");
        }
    }

}
