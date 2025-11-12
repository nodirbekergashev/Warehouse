package uz.pdp.warehouse.exceptions.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SupplierNotFoundException extends RuntimeException{
    public SupplierNotFoundException(String message) {
        super(message);
    }

    public SupplierNotFoundException(Long id) {
        super("Supplier not found with id: " + id);
    }

    public SupplierNotFoundException(String field, String value) {
        super("Supplier not found with " + field + ": " + value);
    }
}
