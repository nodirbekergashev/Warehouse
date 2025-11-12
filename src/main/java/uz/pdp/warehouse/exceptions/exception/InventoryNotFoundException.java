package uz.pdp.warehouse.exceptions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InventoryNotFoundException extends RuntimeException {
    public InventoryNotFoundException(String message) {
        super(message);
    }

    public InventoryNotFoundException(Long id) {
        super("Inventory not found with id: " + id);
    }

    public InventoryNotFoundException(String field, String value) {
        super("Inventory not found with " + field + ": " + value);
    }
}
