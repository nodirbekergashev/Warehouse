package uz.pdp.warehouse.exceptions.exceptionHandler;


public record ErrorResponse(int status, String message) {
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
