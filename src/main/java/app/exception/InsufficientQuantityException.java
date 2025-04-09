package app.exception;

import org.springframework.http.HttpStatus;

public class InsufficientQuantityException extends ApiException {
    public InsufficientQuantityException(String productName, int available, int requested) {
        super(String.format("Not enough quantity for product '%s'. Available: %d, Requested: %d",
                        productName, available, requested),
                HttpStatus.BAD_REQUEST,
                "INSUFFICIENT_QUANTITY");
    }
}