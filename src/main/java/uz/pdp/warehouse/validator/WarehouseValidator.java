package uz.pdp.warehouse.validator;
import org.springframework.stereotype.Component;

import uz.pdp.warehouse.model.dto.warehouse.WarehouseCreateDto;
@Component
public class WarehouseValidator {
            //WarehouseValidator

    public void validateOnCreate(WarehouseCreateDto warehouseCreateDto) {
        if (warehouseCreateDto == null) {
            throw new IllegalArgumentException("Warehouse data cannot be null");
        }

        // Validate name
        if (warehouseCreateDto.getName() == null || warehouseCreateDto.getName().isBlank()) {
            throw new IllegalArgumentException("Warehouse name cannot be null or blank");
        }

        // Validate capacity
        if (warehouseCreateDto.getCapacity() == null || warehouseCreateDto.getCapacity() <= 0) {
            throw new IllegalArgumentException("Warehouse capacity must be greater than 0");
        }

        // Validate current stock
        if (warehouseCreateDto.getCurrentStock() != null && warehouseCreateDto.getCurrentStock() < 0) {
            throw new IllegalArgumentException("Current stock cannot be negative");
        }

        // Optional: check if currentStock exceeds capacity
        if (warehouseCreateDto.getCurrentStock() != null && warehouseCreateDto.getCurrentStock() > warehouseCreateDto.getCapacity()) {
            throw new IllegalArgumentException("Current stock cannot exceed warehouse capacity");
        }
    }
}
