package uz.pdp.warehouse.exceptions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WarehouseNotFoundException extends RuntimeException {

    public WarehouseNotFoundException(String message) {
        super(message);
    }

    public WarehouseNotFoundException(Long id) {
        super("Warehouse not found with id: " + id);
    }

    public WarehouseNotFoundException(String field, String value) {
        super("Warehouse not found with " + field + ": " + value);
    }
}
