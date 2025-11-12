package uz.pdp.warehouse.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.warehouse.model.dto.product.ProductCreateDto;
import uz.pdp.warehouse.repository.CategoryRepository;
import uz.pdp.warehouse.repository.ProductRepository;
import uz.pdp.warehouse.repository.SupplierRepository;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ProductValidator {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;


    public void validateOnCreate(ProductCreateDto dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        if (dto.getSku() == null || dto.getSku().isBlank()) {
            throw new IllegalArgumentException("SKU cannot be empty");
        }

        if (dto.getCategoryId() == null || categoryRepository.findById(dto.getCategoryId()).isPresent()) {
            throw new IllegalArgumentException("Category must be selected");
        }

        if (dto.getSupplierId() == null || supplierRepository.findById(dto.getSupplierId()).isPresent()) {
            throw new IllegalArgumentException("Supplier must be selected");
        }

        if (dto.getUnit() == null) {
            throw new IllegalArgumentException("Unit is required");
        }

        if (dto.getPrice() == null || dto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }

        if (productRepository.findBySku(dto.getSku()).isPresent()) {
            throw new IllegalArgumentException("Product with this SKU already exists");
        }
    }
}
