package uz.pdp.warehouse.validator;

import org.springframework.stereotype.Component;
import uz.pdp.warehouse.model.dto.inventory.InventoryCreateDto;

@Component
public class InventoryValidator {
    public void validateOnCreate(InventoryCreateDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("InventoryCreateDto cannot be null");
        }
        if (dto.getProductId() == null) {
            throw new IllegalArgumentException("Product ID is required");
        }
        if (dto.getWarehouseId() == null) {
            throw new IllegalArgumentException("Warehouse ID is required");
        }
        if (dto.getQuantityInStock() == null || dto.getQuantityInStock() < 0) {
            throw new IllegalArgumentException("Quantity in stock must be a non-negative number");
        }
    }
}
