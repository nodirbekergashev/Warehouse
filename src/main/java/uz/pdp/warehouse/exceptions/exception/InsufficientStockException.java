package uz.pdp.warehouse.exceptions.exception;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(Long productId, Integer requestedQuantity, Integer availableStock) {
    }

    public InsufficientStockException(String field, Integer value) {
            super("Category not found with " + field + ": " + value);
    }
}
